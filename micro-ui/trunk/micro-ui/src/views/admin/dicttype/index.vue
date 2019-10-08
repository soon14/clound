
<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       :page="page"
                       :data="tableData"
                       :table-loading="tableLoading"
                       :option="tableOption"
                       @on-load="getList"
                       @row-update="handleUpdate"
                       @row-save="handleSave"
                       @search-change="searchChange"
                       @refresh-change="refreshChange"
                       @search-reset="handleSearchReset"
                       @row-del="rowDel">
                <template slot-scope="scope"
                          slot="menu">

                    <el-button type="text"
                               icon="el-icon-tickets"
                               size="mini"
                               plain
                               @click="handleDictDetail(scope.row,scope.index)">字典项
                    </el-button>

                    <el-button type="text"
                               v-if="permissions.sys_dict_edit"
                               icon="el-icon-edit"
                               size="mini"
                               plain
                               @click="handleEdit(scope.row,scope.index)">编辑
                    </el-button>
                    <el-button type="text"
                               v-if="permissions.sys_dict_del"
                               icon="el-icon-delete"
                               size="mini"
                               plain
                               @click="handleDel(scope.row,scope.index)">删除
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>
    </div>
</template>

<script>
    import {fetchDictTypeList, addObj, getObj, delObj, putObj} from '@/api/admin/dict-type'
    import {tableOption} from '@/const/crud/admin/dict-type'
    import {mapGetters} from 'vuex'
    export default {
        name: "dictType",
        data() {
            return {
                tableData: [],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableLoading: false,
                tableOption: tableOption,
                queryParams:[],
            }
        },
        created() {
        },
        computed: {
            ...mapGetters(['permissions'])
        },
        methods: {
            getList(page) {
                this.tableLoading = true
                fetchDictTypeList(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, this.queryParams)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            handleDel(row, index) {
                this.$refs.crud.rowDel(row, index)
            },
            refreshChange({page, searchForm}){
                let params = this.getParams(searchForm);
                this.getList(page, params)
            },
            rowDel: function (row, index) {
                var _this = this
                this.$confirm('是否确认删除字典类型为"' + row.innerFlag + '",字典名称为"' + row.chineseName + '"的数据项?', '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return delObj(row)
                }).then(() => {
                    this.getList(this.page)
                    _this.$message({
                        showClose: true,
                        message: '删除成功',
                        type: 'success'
                    })
                }).catch(function () {
                })
            },
            /**
             * @title 数据更新
             * @param row 为当前的数据
             * @param index 为当前更新数据的行数
             * @param done 为表单关闭函数
             *
             **/
            handleUpdate: function (row, index, done) {
                putObj(row).then(() => {
                    this.tableData.splice(index, 1, Object.assign({}, row))
                    this.$message({
                        showClose: true,
                        message: '修改成功',
                        type: 'success'
                    })
                    this.getList(this.page)
                    done()
                }).catch(function (error) {
                    console.log(error);
                })
            },
            /**
             * @title 数据添加
             * @param row 为当前的数据
             * @param done 为表单关闭函数
             *
             **/
            handleSave: function (row, done) {
                addObj(row).then(data => {
                    this.tableData.push(Object.assign({}, row))
                    this.$message({
                        showClose: true,
                        message: '添加成功',
                        type: 'success'
                    })
                    this.getList(this.page)
                    done()
                })
            },
            /**
             * 清除全局检索条件
             */
            handleSearchReset() {
                this.queryParams = [];
            },
            searchChange(form) {
                this.queryParams = this.filterForm(form)
                this.getList(this.page)
            },
            handleEdit(row, index) {
                this.$refs.crud.rowEdit(row, index)
            },
            handleDictDetail(row, index){
                this.$store.commit("DEL_TAG_BY_LABEL","字典项");
                this.$router.push({
                    path: this.$router.$avueRouter.getPath({
                        name: "字典项",
                        src: "/admin/dict"
                    }),
                    query: {type: row.innerFlag, isTree: row.isTree}
                });
            }
        }
    };
</script>

<style lang="scss" scoped>
</style>
