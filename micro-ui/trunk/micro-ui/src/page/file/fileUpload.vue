<template>
    <div>
        <el-upload
                class="upload-demo"
                :headers="uploadHeaders"
                :data="fileParams"
                :action="uploadUrl"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                multiple
                :limit="limit"
                :before-upload="beforeUpload"
                :accept="accept"
                :on-exceed="handleExceed"
                :file-list="fileList">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传{{acceptDesc}}文件，且不超过 {{fileSize/ (1024* 1024) }}M</div>
        </el-upload>
    </div>
</template>

<script>
import { mapGetters, userDept } from "vuex";
import {fetchFileList,  getObj, delObj, getList, download} from '@/api/admin/file'
import {getStore} from '@/util/store'
import store from "@/store";

export default {
    name: "fileUpload",
    data() {
        return {
            fileList: [],
            uploadUrl: '/admin/file/upload',
            fileParams: {
                tbPk: undefined,
                tbName: undefined,
                createBy: undefined,
                createDeptCode: undefined,
                storageModel: undefined
            },
            uploadHeaders: {}
        }
    },
    props: {
        tbPk: { //当前实体表主键
            type: String,
            required: true
        },
        tbName: { //当前实体表名称
            type: String,
            required: true
        },
        accept: { // 文件类型限制
            type: String,
            required: false,
            default:'image/*,.pdf,.doc,.docx,.xml,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
        },
        limit:{ //文件个数
            type: Number,
            required: false,
            default: 3
        },
        acceptDesc:{ // 文件类型限制描述
            type: String,
            required: false,
            default:'图片、word、excel、ppt、pdf'
        },
        fileSize:{ // 文件大小
            type: Number,
            required: false,
            default: 52428800
        },
        storageModel: {
            type: String,
            required: false,
            default:'both'
        }
    },
    computed: {
        ...mapGetters([ 'userInfo', 'userDept' ])
    },
    created() {
        const TENANT_ID = getStore({name: 'tenantId'})
        let token =  store.getters.access_token
        if (token) {
            this.uploadHeaders.Authorization = 'Bearer ' + token// token
        }

        if (TENANT_ID) {
            this.uploadHeaders.TENANT_ID = TENANT_ID // 租户ID
        }

        this.fileParams.createBy = this.userInfo.userId
        this.fileParams.createDeptCode = this.userDept.code
        this.fileParams.tbPk = this.userInfo.userId
        this.fileParams.tbPk = this.$props.tbPk
        this.fileParams.tbName = this.$props.tbName
        this.fileParams.storageModel = this.$props.storageModel

        getList(Object.assign({
            tbPk: this.$props.tbPk,
            tbName: this.$props.tbName
        })).then(response => {
            let files = response.data.data
            for(let i = 0; i < files.length; i++){
                let item = files[i]
                this.fileList.push({
                    name: item.fileName + item.extName,
                    id: item.id
                })
            }
        })
    },
    methods: {
        /**
         * 删除提示
         */
        beforeRemove(file, fileList) {
            let id;
            if(file.response == undefined){
                id = file.id
            }else{
                id = file.response.data.sysFile.id;
            }

            if(id != undefined){
                return this.$confirm(`确定移除 ${ file.name }？`);
            }else {
                return true
            }
        },
        /**
         * 处理删除
         */
        handleRemove(file, fileList) {
            let id;
            if(file.response == undefined){
                id = file.id
            }else{
                id = file.response.data.sysFile.id;
            }
            if(id != undefined){
                delObj(id)
            }
        },
        /**
         * 预览 下载
         */
        handlePreview(file) {
            let id;
            let fileName;
            if(file.response == undefined){
                id = file.id
                fileName = file.name
            }else{
                id = file.response.data.sysFile.id
                fileName = file.response.data.sysFile.fileName + file.response.data.sysFile.extName
            }
            download(id, fileName, this.$message)
        },
        beforeUpload(file){
            if(this.$props.fileSize < file.size){
                this.$message.error('上传文件大小不能超过 50MB!');
                return false;
            }
            return true;
        },
        /**
         * 上传限制
         */
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
    }
};
</script>

<style scoped>
</style>
