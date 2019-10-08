<template>
    <div class="iconUpload">
        <el-upload
                class="upload-demo"
                :headers="uploadHeaders"
                :action="uploadUrl"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :limit="1"
                :before-upload="beforeUpload"
                :on-exceed="handleExceed"
                :file-list="fileList"
                list-type="picture"
                :on-success="handleIconSuccess"
                :data="fileParams">
            <el-button size="small" type="primary" v-show="showBtn()">点击上传</el-button>
            <div slot="tip" class="el-upload__tip" v-show="showBtn()">只能上传jpg/png文件，且不超过30kb</div>
        </el-upload>
    </div>
</template>

<script>
    import {getIcon,delIcon} from '@/api/portal/judged/model/modelMenu'
    import {getStore} from '@/util/store'
    import store from "@/store";
    import request from '@/router/axios'

    export default {
        props:["iconForm"],
        data() {
            return {
                windowType:'',
                fileList: [],
                uploadUrl: '/portal/judged/model/upload',
                fileParams: {
                    id: '',
                },
                uploadHeaders: {},
                flag:'',
            };
        },

        created() {

            let token =  store.getters.access_token
            if (token) {
                this.uploadHeaders.Authorization = 'Bearer ' + token// token
            }
            this.windowType=this.iconForm.windowType
            this.fileParams.id = this.iconForm.id
            this.fileList=[]
            if ("add"!=this.iconForm.windowType){
                this.flag=this.iconForm.windowType
                this.getList();
            }else{
                this.flag="";
                this.handleRemove()
            }

        },
        methods: {
            getList(){
                this.fileList=[];
                getIcon(this.fileParams.id,this.flag).then(response => {
                    let blob = new Blob([response.data])
                    let url = URL.createObjectURL(blob)
                //    url= url.replace("blob:","")
                    this.fileList=[{name:"图标",url:url}]
                    if(""===blob||null===blob||undefined===typeof (blob)||blob.size<=0){
                        this.fileList=[];
                    }else{
                        this.fileList=[{name:"图标",url:url}]
                    }

                })
            },
            /**
             * 处理删除
             */
            handleRemove() {
                delIcon()
            },
            /**
             * 预览
             */
            handlePreview(file) {
                console.log(file)
                var link = file.url;
                var data= [
                    { thumbUrl: `${link}`, url: `${link}` },
                ]
                this.$ImagePreview(data)
            },
            beforeUpload(file) {
                const isLt30Kb = file.size / 1024 / 1024 < 0.0292969;
                if (file.type != "image/jpeg"&&file.type !="image/jpg"&&file.type !="image/png") {
                    this.$message.error('只能上传jpg/png文件!');
                    return false
                }
                if (!isLt30Kb) {
                    this.$message.error('上传图片大小不能超过 30kb!');
                    return false
                }
                return true
            },
            /**
             * 上传限制
             */
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 1 个文件,请先移除已存在文件再上传`);
            },
            handleIconSuccess(res, file) {
               let url= URL.createObjectURL(file.raw);
               this.fileList=[{name:"图标",url:url}]
            },
            showBtn(){
                if(this.windowType!='view'){
                    return true
                }else{
                    return false
                }
            }
        },
        watch: {
            iconForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    let token =  store.getters.access_token
                    if (token) {
                        this.uploadHeaders.Authorization = 'Bearer ' + token// token
                    }
                    this.windowType=this.iconForm.windowType
                    this.fileParams.id = this.iconForm.id
                    this.fileList=[]
                    if ("add"!=this.iconForm.windowType){
                        this.flag=this.iconForm.windowType
                        this.getList();
                    }else{
                        this.flag="";
                        this.handleRemove()
                    }
                },
                deep: true
            },
        },
    }
</script>
<style lang="scss">
    .avue-image-preview{
        z-index:3000!important;
    }
</style>

