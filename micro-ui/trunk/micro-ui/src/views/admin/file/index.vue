
<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       :page="page"
                       :data="tableData"
                       :table-loading="tableLoading"
                       :option="tableOption"
                       @on-load="getList"
                       @search-change="searchChange"
                       @refresh-change="refreshChange">
                <template slot-scope="scope"
                          slot="menu">

                    <el-button type="text"
                               icon="el-icon-tickets"
                               size="mini"
                               plain
                               @click="handleDownload(scope.row,scope.index)">下载
                    </el-button>
                </template>
            </avue-crud>

            <file-upload :tbPk="tbPk"
                         :tbName="tbName">
            </file-upload>

        </basic-container>
    </div>
</template>

<script>
    import {fetchFileList,  getObj, delObj, putObj, download} from '@/api/admin/file'
    import {tableOption} from '@/const/crud/admin/file'
    import fileUpload from "@/page/file/fileUpload"
    export default {
        name: "file",
        components: { "fileUpload" : fileUpload},
        data() {
            return {
                tbPk: 'test_id', // 用于示例测试，不是真实id
                tbName: 'sys_file',// 用于示例测试，不是真实id
                tableData: [],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20 // 每页显示多少条
                },
                tableLoading: false,
                tableOption: tableOption
            }
        },
        created() {},
        methods: {
            getList(page, params) {
                this.tableLoading = true
                fetchFileList(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            refreshChange({page, searchForm}){
                let params = this.getParams(searchForm);
                if(page == undefined){
                    page = this.page
                }
                this.getList(page, params)
            },
            getParams(form){
                let params ={}
                if(form.fileName != "")
                    params.fileName = form.fileName
                if(form.extName != "")
                    params.extName = form.extName
                return params;
            },
            searchChange(form) {
                let params = this.getParams(form);
                this.getList(this.page, params)
            },
            handleDownload(row, index){
                download(row.id, row.fileName + row.extName, this.$message)
            }
        }
    };
</script>

<style lang="scss" scoped>
</style>