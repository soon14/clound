

<template>
    <div class="metadata">
        <basic-container>
            <el-row :span="24">
                <el-col :xs="24"
                        :sm="24"
                        :md="5"
                        class="metadata__tree">

                    <avue-tree :option="treeOption"
                               :data="treeData"
                               @node-click="nodeClick"></avue-tree>
                </el-col>
                <el-col :xs="24"
                        :sm="24"
                        :md="19"
                        class="metadata__main">
                    <avue-crud :option="option"
                               ref="crud"
                               v-model="form"
                               :page="page"
                               @on-load="getList"
                               @row-update="update"
                               :table-loading="listLoading"
                               @search-change="handleFilter"
                               @refresh-change="handleRefreshChange"
                               :before-open="handleOpenBefore"
                               :data="list">
                        <template slot="menuLeft">
                            <el-button v-if="isPreView()"
                                       @click="preViewTest()"
                                       size="small"
                                       type="primary"
                                       icon="el-icon-download"
                                       >预览导入
                            </el-button>
                        </template>

                        <template slot="menu"
                                  slot-scope="scope">
                            <el-button v-if="data_table_edit"
                                       size="small"
                                       type="text"
                                       icon="el-icon-edit"
                                       @click="handleUpdate(scope.row,scope.index)">编辑
                            </el-button>
                            <el-button v-if="data_table_del"
                                       size="small"
                                       type="text"
                                       icon="el-icon-delete"
                                       @click="deletes(scope.row,scope.index)">删除
                            </el-button>
                            <el-button type="text"
                                       icon="el-icon-tickets"
                                       size="mini"
                                       plain
                                       @click="handleFieldDetail(scope.row,scope.index)">字段编辑
                            </el-button>
                        </template>

                        <template slot="dbId"
                                  slot-scope="scope">
                            {{scope.row.dbName}}
                        </template>
                    </avue-crud>
                </el-col>
            </el-row>
        </basic-container>

        <el-dialog title="预览导入" fullscreen :visible.sync="preViewFlag" :append-to-body="true">
            <avue-crud  ref="preViewCrud"
                        :option="preViewOption"
                        :data="preViewData"
                        :page="preViewPage"
                        @on-load="getPreViewList"
                        :table-loading="preViewlistLoading"
                        @search-change="preHandleFilter"
                        @refresh-change="preHandleRefreshChange"
                        @selection-change="selectionChange">
                <template slot="menuLeft">
                    <el-button v-if="data_table_add"
                               @click="importTable"
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
    import {fetchPreViewList,fetchTree,addObj, delObj, fetchList, getObj, putObj,checkDbConn} from "@/api/portal/metadata/standardfields/table";
    import {tableOption,preViewOption,newVue} from '@/const/crud/portal/metadata/standardfields/table';
    import {mapGetters} from "vuex";

    export default {
        name: "table",
        data() {
            return {
                treeOption: {
                    nodeKey: 'id',
                    addBtn: false,
                    menu: false,
                    props: {
                        label: 'label',
                        value: 'value',
                        children:'children'
                    }
                },
                preViewFlag:false,
                treeData: [],
                option: tableOption,
                preViewOption: preViewOption,
                checkedKeys: [],
                tempDbId:undefined,
                tempDbType:undefined,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                preViewPage: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                list: [],
                preViewData: [],
                listLoading: true,
                preViewlistLoading: true,
                form: {},
                selData: [],
                editId:"",
            };
        },
        computed: {
            ...mapGetters(["permissions"])
        },

        created() {
            this.data_table_add = this.permissions["data_table_add"];
            this.data_table_edit = this.permissions["data_table_edit"];
            this.data_table_del = this.permissions["data_table_del"];
            newVue.obj = this
            this.init();
        },
        methods: {
            init() {
                fetchTree().then(response => {
                    this.treeData = response.data.data;
                });
            },
            nodeClick(data) {
                this.page.page = 1;
                this.tempDbId=data.id;
                this.tempDbType = data.value;
                this.getList(this.page, {dbId: data.id,dbType:data.value});
            },
            getList(page, params) {
                this.listLoading = true;
                fetchList(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, params)).then(response => {
                    this.list = response.data.data.records;
                    this.page.total = response.data.data.total
                    this.listLoading = false;
                });
            },
            getPreViewList(preViewPage, params) {
                this.preViewData =[]
               if(params === undefined ||params === null || params === '' ){
                   params ={dbId: this.tempDbId}
               }
                this.preViewlistLoading = true;
                fetchPreViewList(Object.assign({
                    current: preViewPage.currentPage,
                    size: preViewPage.pageSize
                }, params)).then(response => {
                    this.preViewData = response.data.data;
                    this.preViewPage.total = response.data.data.total
                    this.preViewlistLoading = false;
                });
                this.$refs.preViewCrud.selectClear();
            },
            handleFilter(param) {
                this.page.page = 1;
                param.dbId=this.tempDbId;
                param.dbType=this.tempDbType
                this.getList(this.page, param);
            },
            preHandleFilter(param) {
                this.preViewPage.page = 1;
                param.dbId=this.tempDbId;
                param.dbType=this.tempDbType
                this.getPreViewList(this.preViewPage, param);
            },
            handleRefreshChange() {
                this.getList(this.page,{dbId: this.tempDbId,dbType:this.tempDbType})
            },
            preHandleRefreshChange() {
                this.getPreViewList(this.preViewPage,{dbId: this.tempDbId,dbType:this.tempDbType})
            },
            handleOpenBefore(show, type) {
                window.boxType = type;
                this.form.dbId = this.form.dbName;
                show();
            },
            handleUpdate(row, index) {
                this.$refs.crud.rowEdit(row, index);
                this.tempDbId = row.dbId
                this.editId=row.id;
            },
            importTable() {
                if(this.selData.length > 0){
                    this.$confirm('确认导入?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        let defineForm ={}
                        var nodes = new Array();
                        for(let i = 0; i < this.selData.length; i++){
                            nodes.push(JSON.parse(JSON.stringify(this.selData[i])));
                        }
                        defineForm['dbId']=this.tempDbId
                        defineForm['tableList']=nodes
                        addObj(defineForm).then(() => {
                            this.preViewFlag = false;
                            this.getList(this.page,{dbId: this.tempDbId,dbType:this.tempDbType})
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
            selectionChange(data){
                this.selData = data
            },
            update(row, index, done, loading) {
                putObj(this.form).then(() => {
                    this.getList(this.page);
                    done();
                    this.$notify({
                        title: "成功",
                        message: "修改成功",
                        type: "success",
                        duration: 2000
                    });
                }).catch(() => {
                    loading();
                });
            },
            deletes(row, index) {
                this.$confirm(
                    "此操作将永久删除该表(" + row.name + "), 是否继续?",
                    "提示",
                    {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }
                ).then(() => {
                    delObj(row.id).then(() => {
                        this.getList(this.page)
                        this.$notify({
                            title: "成功",
                            message: "删除成功",
                            type: "success",
                            duration: 2000
                        });
                    }).cache(() => {
                        this.$notify({
                            title: "失败",
                            message: "删除失败",
                            type: "error",
                            duration: 2000
                        });
                    });
                });
            },

            handleFieldDetail(row, index){
                this.$router.push({
                    path: this.$router.$avueRouter.getPath({
                        name: "字段编辑",
                        src: "/portal/unit/standard/metadata"
                    }),
                    query: {tbId: row.id,tbName:row.name,dbId:this.tempDbId}
                });
            },

            isPreView:function () {
                if(this.tempDbId===undefined){
                    return false;
                }
                if(this.tempDbType=='Oracle'||this.tempDbType=='MySql'||this.tempDbType=='SQLServer'||this.tempDbType=='DB2'){
                    return false;
                }else{
                    return true;
                }
            },

            preViewTest:function () {
                checkDbConn(this.tempDbId).then(response => {
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
    };
</script>
<style lang="scss">
    .metadata {
        height: 100%;

        &__tree {
            padding-top: 3px;
            padding-right: 20px;
        }

        &__main {
            .el-card__body {
                padding-top: 0;
            }
        }
    }
</style>

