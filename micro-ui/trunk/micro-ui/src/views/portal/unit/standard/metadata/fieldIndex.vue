<template>
    <div class="execution">
        <basic-container>

            <avue-crud  ref="crud"
                        v-model="form"
                        :page="page"
                        :data="tableData"
                        :table-loading="tableLoading"
                        :option="tableOption"
                        @on-load="getList"
                        @row-update="handleUpdate"
                        @row-save="handleSave"
                        @search-change="handleFilter"
                        @refresh-change="getList"
                        @selection-change="selectionChange"
                        :before-open="handleOpenBefore">

                <template slot="menuLeft">
                    <el-button @click="handleAdd"
                               v-if="permissions.data_table_add"
                               icon="el-icon-plus"
                               type="primary"
                               size="small">添加</el-button>
                    <el-button type="primary"
                               v-if="permissions.data_table_del"
                               icon="el-icon-delete"
                               @click="handleDelAll"
                               size="small">删除</el-button>
                    <el-button @click="preViewTest()"
                               v-if="permissions.data_table_add"
                               icon="el-icon-download"
                               type="primary"
                               size="small">预览导入</el-button>
                </template>

                <template slot-scope="scope" slot="menu">
                    <el-button type="text"
                               v-if="permissions.data_table_edit"
                               icon="el-icon-edit"
                               size="mini"
                               plain
                               @click="handleEdit(scope.row,scope.index)">编辑
                    </el-button>
                    <el-button type="text"
                               v-if="permissions.data_table_del"
                               icon="el-icon-delete"
                               size="mini"
                               plain
                               @click="handleDel(scope.row,scope.index)">删除
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog title="预览导入" fullscreen :visible.sync="preViewFlag" :append-to-body="true">
            <avue-crud   ref="preViewCrud"
                        :option="preViewOption"
                        :data="preViewData"
                        :page="preViewPage"
                        :table-loading="preViewlistLoading"
                        @on-load="getPreViewList"
                        @search-change="preHandleFilter"
                        @refresh-change="getPreViewList"
                        @selection-change="preSelectionChange">
                <template slot="menuLeft">
                    <el-button v-if="permissions.data_table_add"
                               @click="importField"
                               size="small"
                               type="primary"
                               icon="el-icon-download">导入
                    </el-button>
                </template>
            </avue-crud>
        </el-dialog>

    </div>
</template>

<script>
    import {fetchPreViewList,preViewAddObj,addObj, getObj,delObj, fetchList, putObj,checkDbConn} from '@/api/portal/metadata/standardfields/field'
    import {tableOption,preViewOption,newVue} from '@/const/crud/portal/metadata/standardfields/field'
    import {mapGetters} from 'vuex'

    export default {
        name: 'field',
        data() {
            return {
                preViewFlag:false,
                tableData: [],
                preViewData: [],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                preViewPage: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableLoading: false,
                preViewlistLoading: true,
                tableOption: tableOption,
                preViewOption:preViewOption,
                tableId: undefined,
                tableName: undefined,
                dbId:undefined,
                selData: [],
                preViewSelData: [],
                form: {},
                editId:"",
            }
        },
        created() {
            this.tableId = this.$route.query.tbId
            this.tableName = this.$route.query.tbName
            this.dbId = this.$route.query.dbId
            this.tableOption.column[1].valueDefault = this.tableName
            newVue.obj = this
        },
        mounted: function () {
        },
        computed: {
            ...mapGetters(['permissions'])
        },
        methods: {
            getList(page, params) {
                this.tableLoading = true
                fetchList(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    tableId : this.tableId
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            getPreViewList(preViewPage, params) {
                this.preViewData=[]
                this.preViewlistLoading = true;
                fetchPreViewList(Object.assign({
                    current: preViewPage.currentPage,
                    size: preViewPage.pageSize,
                    tableId: this.tableId
                }, params)).then(response => {
                    this.preViewData = response.data.data;
                    this.preViewPage.total = response.data.data.total
                    this.preViewlistLoading = false;
                });
                this.$refs.preViewCrud.selectClear();
            },
            /**
             * @title 打开新增窗口
             * @detail 调用crud的handleadd方法即可
             *
             **/
            handleAdd: function () {
                this.$refs.crud.rowAdd()
            },
            handleEdit(row, index) {
                row.tableId = this.tableName;
                this.$refs.crud.rowEdit(row, index)
                this.editId=row.id;
            },
            handleOpenBefore(show, type) {
                window.boxType = type;
                show();
            },
            handleDel: function (row, index) { //删除行
                var _this = this
                let title = '是否确认删除字段名为"' + row.name + '",字段类型为"' + row.type + '"的数据项?'
                this.$confirm(title, '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return delObj(row.id, _this.tableId)
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
                })
            },
            /**
             * @title 数据添加
             * @param row 为当前的数据
             * @param done 为表单关闭函数
             *
             **/
            handleSave: function (row, done) {
                row.tableId = this.tableId;
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
            handleFilter(param) {
                this.page.page = 1;
                this.getList(this.page,param)
            },
            preHandleFilter(param){
                this.preViewPage.page = 1;
                this.getPreViewList(this.preViewPage,param);
            },
            handleDelAll(){// 列表批量删除
                if(this.selData.length > 0){
                    this.$confirm('此操作将永久删除, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        let ids = ""
                        for(let i = 0; i < this.selData.length; i++){
                            ids += this.selData[i].id + ","
                        }
                        delObj(ids).then(() => {
                            this.getList(this.page)
                            this.$message({
                                showClose: true,
                                message: '删除成功',
                                type: 'success'
                            })
                        })
                    })
                }else {
                    this.$message({
                        showClose: true,
                        message: '请选择要删除的字段',
                        type: 'warning'
                    })
                }
            },
            selectionChange(data){
                this.selData = data
            },
            importField() {
                if(this.preViewSelData.length > 0){
                    this.$confirm('确认导入?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        let defineForm ={}
                        var nodes = new Array();
                        for(let i = 0; i < this.preViewSelData.length; i++){
                            nodes.push(JSON.parse(JSON.stringify(this.preViewSelData[i])));
                        }
                        defineForm['tableId']=this.tableId
                        defineForm['fieldList']=nodes
                        console.log(defineForm)
                        preViewAddObj(defineForm).then(() => {
                            this.preViewFlag = false;
                            this.getList(this.preViewPage)
                            this.$notify({
                                title: "成功",
                                message: "导入成功",
                                type: "success",
                                duration: 2000
                            });
                        })
                    })
                }else {
                    this.$message({
                        showClose: true,
                        message: '请选择要导入的表',
                        type: 'warning'
                    })
                }
            },
            preSelectionChange(data){
                this.preViewSelData = data
            },
            preViewTest:function () {
                checkDbConn(this.dbId).then(response => {
                    if(response.data.message==="测试数据源连接成功"){
                        this.preViewFlag=true
                        this.getPreViewList(this.preViewPage);
                    }else{
                        this.$alert(response.data.message, '连接结果', {
                            confirmButtonText: '确定',
                            type: 'warning',
                            callback: action => {
                            },
                        });
                    }

                })
            }
        }
    }
</script>

<style lang="scss" scoped>
</style>

