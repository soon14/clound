<template>
  <div class="avue-ueditor">
    <quill-editor :style="{height:`${minRows*40+100}px`,maxHeight:`${maxRows*40+100}px`}"
                  v-model="text"
                  @change="handleChange"
                  @click.native="handleClick"
                  ref="myQuillEditor"
                  :options="options">

    </quill-editor>
    <div class="avue-ueditor__dialog"
         v-if="box">
      <div class="avue-ueditor__mask"
           @click.stop="box=false"></div>
      <div class="avue-ueditor__content">
        <p class="avue-ueditor__tip"
           v-if="loading">正在上传，请稍后</p>
        <div class="avue-ueditor__img">
          <div class="avue-ueditor__img-left">
            <p>
              宽度:<input type="text"
                     class="avue-ueditor__img-input"
                     v-model="img.width" />
            </p>
            <p>
              高度:<input type="text"
                     class="avue-ueditor__img-input"
                     v-model="img.height" />
            </p>
          </div>
          <div class="avue-ueditor__img-right">
            <img :src="img.url"
                 class="avue-ueditor__img-img"
                 ref="img"
                 :style="styles"
                 alt="" />
          </div>
        </div>
        <div class="avue-ueditor__menu">
          <div class="avue-ueditor__upload">
            <button class="avue-ueditor__btn">上传</button>
            <input type="file"
                   id="file"
                   @change="handleUpload"
                   class="avue-ueditor__file" />
          </div>
          <button class="avue-ueditor__btn"
                  @click="handleSubmit">确定</button>
        </div>

      </div>
    </div>
  </div>
</template>
<script>
import quillEditor from "vue-quill-editor/src/editor";
import { getClient } from "./upload/ali";
import { getToken } from "./upload/qiniu";
import { getObjValue } from "./upload/util";
export default {
  name: "AvueUeditor",
  components: {
    quillEditor
  },
  computed: {
    props() {
      return this.upload.props;
    },
    oss() {
      return this.upload.oss;
    },
    action() {
      return this.upload.action;
    },
    qiniu() {
      return this.upload.qiniu;
    },
    ali() {
      return this.upload.ali;
    },
    styles() {
      if (this.img.width === 0 || this.img.height === 0) {
        return {};
      }
      return {
        width: this.img.width + "px",
        height: this.img.height + "px"
      };
    },
    isQiniuOSS() {
      return this.oss === "qiniu";
    },
    isAliOSS() {
      return this.oss === "ali";
    }
  },
  props: {
    upload: {
      type: Object,
      default: () => {
        return {};
      }
    },
    value: {
      type: String,
      default: ""
    },
    readonly: {
      type: Boolean,
      default: false
    },
    height: {
      default: ""
    },
    minRows: {
      type: Number,
      default: 5
    },
    maxRows: {
      type: Number,
      default: 10
    }
  },
  data() {
    return {
      loading: false,
      text: undefined,
      box: false,
      img: {
        url: "",
        width: 0,
        height: 0,
        calc: ""
      },
      options: {
        placeholder: "请输入内容",
        readonly: false
      }
    };
  },
  watch: {
    "img.width"(n, o) {
      if (n === 0 || o === 0) return;
      this.img.height = parseInt(n * this.img.calc);
    },
    value() {
      this.text = this.value;
    }
  },
  created() {
    this.init();
  },
  mounted() {
    this.$refs.myQuillEditor.quill
      .getModule("toolbar")
      .addHandler("image", this.imgHandler);
  },
  methods: {
    handleSubmit() {
      let quill = this.$refs.myQuillEditor.quill;
      quill.root.innerHTML = this.text + this.$refs.img.outerHTML;
      quill.focus();
      this.img.url = "";
      this.img.width = 0;
      this.img.height = 0;
      this.box = false;
    },
    handleUpload(e) {
      this.loading = true;
      const file = e.target.files[0];
      this.handleFile(file).then(() => {
        this.setImgParam();
      });
    },
    handleFile(file) {
      return new Promise(resolve => {
        const headers = { "Content-Type": "multipart/form-data" };
        let oss_config = {};
        let client;
        let param = new FormData();
        let url = this.action;
        param.append("file", file, file.name);
        if (this.isQiniuOSS) {
          oss_config = this.qiniu;
          const token = getToken(oss_config.AK, oss_config.SK, {
            scope: oss_config.scope,
            deadline: new Date().getTime() + oss_config.deadline * 3600
          });
          param.append("token", token);
          url = "http://up.qiniu.com/";
        } else if (this.isAliOSS) {
          oss_config = this.ali;
          client = getClient({
            region: oss_config.region,
            endpoint: oss_config.endpoint,
            accessKeyId: oss_config.accessKeyId,
            accessKeySecret: oss_config.accessKeySecret,
            bucket: oss_config.bucket
          });
        }
        (() => {
          if (this.isAliOSS) {
            return client.put(file.name, file);
          } else {
            return this.$httpajax.post(url, param, { headers });
          }
        })().then(res => {
          let list = {};
          if (this.isAliOSS) {
            list = res;
            this.img.url = list.url;
          } else if (this.isQiniuOSS) {
            list = res.data;
            list.key = oss_config.url + list.key;
            this.img.url = list.key;
          } else {
            list = getObjValue(res.data, this.props.res, "object");
            this.img.url = list[this.props.url];
          }
          this.loading = false;
          resolve();
        });
      });
    },
    setImgParam() {
      const img = this.$refs.img;
      img.onload = () => {
        this.img.width = img.width;
        this.img.height = img.height;
        this.img.calc = img.height / img.width;
      };
    },
    imgHandler() {
      this.box = true;
    },
    init() {
      this.text = this.value;
      this.options.placeholder = this.placeholder || "请输入内容";
      this.options.readonly = this.readonly;
      //粘贴键
      document.addEventListener("paste", e => {
        //获取剪切板文件
        const getFile = event => {
          if (event.clipboardData || event.originalEvent) {
            var clipboardData =
              event.clipboardData || event.originalEvent.clipboardData;
            if (clipboardData.items) {
              var items = clipboardData.items,
                len = items.length,
                blob = null;
              for (var i = 0; i < len; i++) {
                if (items[i].type.indexOf("image") !== -1) {
                  blob = items[i].getAsFile();
                  return blob;
                }
              }
            }
          }
        };
        const file = getFile(e);
        this.box = true;
        this.handleFile(file).then(() => {
          this.setImgParam();
        });
      });
    },
    handleClick() {
      if (typeof this.click === "function")
        this.click({ value: this.text, column: this.column });
    },
    handleChange(value) {
      if (typeof this.change === "function")
        this.change({ value: value.html, column: this.column });
      this.$emit("input", value.html);
      this.$emit("change", value.html);
    }
  }
};
</script>
<style lang="scss">
@import "../../theme-chack/src/index.scss";
</style>
