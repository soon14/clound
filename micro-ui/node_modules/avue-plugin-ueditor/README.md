# avue-plugin-ueditor

## avue 富文本编辑器

## npm
[avue-plugin-ueditor](https://www.npmjs.com/package/avue-plugin-ueditor)

## git
[avue-plugin-ueditor](https://gitee.com/smallweigit/avue-plugin-ueditor)

## demo
<p align="center">
  <img width="600" src="https://gitee.com/smallweigit/avue-plugin-ueditor/raw/master/packages/demo/demo.png">
</p>

## use
```
1.安装
npm install avue-plugin-ueditor --save

2.导入
import AvueUeditor from 'avue-plugin-ueditor'
Vue.use(AvueUeditor);

3.使用
...
column:[
  ...
    {
      label:'test',
      prop:'test',
      component: "AvueUeditor"
    }
  ...
]
或者直接
<avue-ueditor v-model="text" ></avue-ueditor>

4.图片上传配置————(支持oss,支持ctrl+v粘贴图片)
具体用法参考https://avuex.avue.top/#/doc/form-upload
upload: {
  //普通图片上传
  action: "https://avueupload.91eic.com/upload/list",
  props: {
    res: "data.0",
    url: "url"
  },
  //七牛云oss配置
  qiniu: {
    AK: "",
    SK: "",
    scope: "test",
    url: "http://pm7cc17lu.bkt.clouddn.com/",
    deadline: 1
  },
  //阿里云oss配置
  ali: {
    region: "oss-cn-beijing",
    endpoint: "oss-cn-beijing.aliyuncs.com",
    accessKeyId: "",
    accessKeySecret: "",
    bucket: "avue"
  }
}
...
```

