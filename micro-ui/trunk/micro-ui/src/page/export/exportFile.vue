<template>
    <el-dialog title="导出" :visible.sync="dialogVisible" width="33%" :showClose="false">
        <table border="1" style="width:400px"><tbody>
        <tr>
            <td style="width: 100px;">导出文件类型</td>
            <td>
                <el-radio-group v-model="fileType"  v-for="(value, key) in fileTypes">
                    <el-radio :label="key">{{value}}&nbsp;</el-radio>
                </el-radio-group>
            </td>
        </tr>
        <tr>
            <td>导出内容</td>
            <td>
                <ul style="list-style: none;  margin-left: 0px;">
                    <li>
                        <el-checkbox v-model="selFieldsAlll" @change="changeCheckAll">全选</el-checkbox>
                    </li>
                    <el-checkbox-group v-model="selExprotFields" v-for="(value, key) in exprotFields"  >
                        <li style="width: 120px; float: left;">
                            <el-checkbox @change="changeFieldSel" :label="key" :key="key">{{value}}</el-checkbox>
                        </li>
                    </el-checkbox-group>
                </ul>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td>导出范围</td>
            <td>
                <el-radio-group v-model="exportSize"  v-for="(value, key) in exportSizes" >
                    <el-radio :label="key">{{value}}&nbsp;</el-radio>
                </el-radio-group>
            </td>
        </tr>
        </tbody>
        </table>
        <span slot="footer" class="dialog-footer">
              <el-button @click="hiddeDialg">关闭</el-button>
              <el-button type="primary" @click="doExport">导出</el-button>
        </span>
    </el-dialog>
</template>

<script>
import {doexport} from '@/const/export'
export default {
    name: "export-file",
    data(){
      return{
          exportSize: "1",
          exportSizes: {
              1: "当前页",
              2: "最大条数"
          },
          fileType: "1",
          fileTypes: {
              1 : "excel",
              2 : "pdf",
              3 : "csv",
              4 : "word",
              5 : "txt"
          },
          selFieldsAlll: false,
          selExprotFields: []
      }
    },
    props: {
        exprotFields: {
            type: Object,
            required: true
        },
        dialogVisible: {
            type: Boolean,
            default: false
        },
        params: {
            type: Object,
            required: true
        },
        currentPage: {
            type: Number,
            required: true
        },
        pageSize: {
            type: Number,
            required: true
        },
        url: {
            type: String,
            required: true
        },
        ascs: {
            type: String,
            required: true
        },
        descs: {
            type: String,
            required: true
        },
        fileName: {
            type: String,
            required: true
        }
    },
    methods: {
        changeCheckAll(value){
            if(value){
                for(var key in this.exprotFields){
                    this.selExprotFields.push(key)
                }
            }else{
                this.selExprotFields = []
            }
        },
        changeFieldSel(value){
            if(!value){
                this.selFieldsAlll = false
            }
        },
        doExport() {
            doexport(this.$props.url,
                this.$props.form,
                this.exportSize,
                this.selExprotFields,
                this.fileType,
                this.$props.ascs,
                this.$props.descs,
                this.$props.currentPage,
                this.$props.pageSize,
                this.$props.fileName,
                this.$message,
                this.hiddeDialg
                );
        },
        hiddeDialg(){
            this.$emit("hiddeDialg") //触发父组件中的increaseMore事件,并将num传递给父组件
        }
    }
};
</script>

<style scoped>
</style>
