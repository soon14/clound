<template>
    <div class="app-container calendar-list-container">
        <basic-container>

            <avue-crud :option="option" ref="crud" :data="treeData"
                       @search-change="handleFilter"
                       @refresh-change="handleFilter"
                       @row-update="handleUpdate"
                       @row-save="handleSave"
                       :table-loading="listLoading"
                       v-model="form"
                       :before-open="handleOpenBefore"
            >
                <template slot="menuLeft">
                    <el-button type="primary"
                               @click="handleAdd"
                               size="small"
                               v-if="model_menu_add">新 增
                    </el-button>
                </template>

                <template slot="parentIdForm" slot-scope="scope">
                    <avue-input v-model="form.parentId"
                                type="tree"
                                placeholder="父级模型"
                                :dic="dicTreeData"
                                :props="dicTreeProps">
                    </avue-input>
                </template>
                <template slot="udId" slot-scope="scope">
             <span>
              <a href="javascript:void(0)" @click="copyText(scope.row.udId)">{{scope.row.udId}}  </a>
             </span>
                </template>
                <template slot="planLibIdForm"
                          slot-scope="scope">
                    <treeSelect
                            v-model="form.planLibId"
                            :disable-branch-nodes="true"
                            :options="planLibTreeData"
                            placeholder="请选择预案"
                            noChildrenText="暂无数据 "
                            zIndex="10099"
                            :append-to-body="true"
                            :clearable="false"
                            :normalizer="normalizer"
                    />
                </template>
                <template slot="iconForm" slot-scope="scope">
                    <icon-upload v-bind:icon-form="iconForm"></icon-upload>
                </template>
                <template slot="menu"
                          slot-scope="scope">
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-view"
                               @click="handView(scope.row,scope.index)">查看
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-plus"
                               v-if="model_menu_add"
                               @click="handAdd(scope.row)">添加子级
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-edit"
                               v-if="model_menu_edit"
                               @click="handleEdit(scope.row,scope.index)">编辑
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-delete"
                               v-if="model_menu_del"
                               @click="handleDelete(scope.row)">删除
                    </el-button>

                </template>
            </avue-crud>


        </basic-container>
    </div>
</template>

<script>
    import {addObj, delObj, fetchMenuTree, getObj, putObj} from '@/api/portal/judged/model/modelMenu'
    import {mapGetters} from 'vuex'
    import {tableOption} from '@/const/crud/portal/judged/model/modelMenu';
    import IconUpload from "./IconUpload";
    import {fetchLibrarylist} from '@/api/portal/judged/lib/PlanLibrary';


    export default {
        name: 'modelIndex',
        components: {IconUpload},
        comments: {
            "icon": IconUpload
        },
        data() {
            return {
                iconForm: {
                    id: '',
                    windowType: '',
                },
                list: null,
                total: null,
                formEdit: true,
                formAdd: true,
                option: tableOption,
                listLoading: true,
                listQuery: {
                    name: undefined,
                    type: undefined,
                },
                checkedKeys: [],
                treeData: [],
                dicTreeData: [],
                planLibTreeData: [],//预案库
                form: {
                    id: '',
                    planLibId: undefined,
                },
                dicTreeProps: {
                    label: "name",
                    value: 'id'
                },
                model_menu_add: false,
                model_menu_edit: false,
                model_menu_del: false
            }
        },
        created() {
            this.getList()
            this.getDicTreeData()
            this.getPlanLibData()
            this.model_menu_add = this.permissions['model_menu_add']
            this.model_menu_edit = this.permissions['model_menu_edit']
            this.model_menu_del = this.permissions['model_menu_del']
        },
        computed: {
            ...mapGetters([
                'elements',
                'permissions'
            ])
        },
        methods: {

            copyText(text) {
                this.$Clipboard({
                    text: text
                }).then(() => {
                    this.$message.success('复制成功')
                }).catch(() => {
                    this.$message.error('复制失败')
                });
            },
            getList() {
                this.listLoading = true
                fetchMenuTree(this.listQuery).then(response => {
                    this.treeData = response.data.data
                    this.listLoading = false
                })
            },
            getDicTreeData() {
                if (this.treeData) {
                    fetchMenuTree().then(response => {
                        this.dicTreeData = response.data.data
                    })
                }
            },
            getPlanLibData() {
                fetchLibrarylist().then(response => {
                    this.planLibTreeData = response.data.data || []
                })
            },

            normalizer(node) {
                if (node.hasChildren == "true" && node.children.length > 0) {
                    for (let i = 0; i < node.children.length; i++) {
                        let data = node.children[i];
                        if (data.children.length==0) {
                            let dataList = {}
                            dataList["id"] = data.id
                            dataList["code"] = data.code
                            dataList["name"] = data.name
                            dataList["parentId"] = data.parentId
                            dataList["sort"] = data.sort
                            dataList["hasChildren"] = "false"
                            dataList["children"] = ""
                            node.children[i] = dataList
                        }
                    }
                }
                return {
                    id: node.id,
                    label: node.name,
                    children: node.children,
                }
            },
            handleAdd: function () {
                this.iconForm.id = ''
                this.option.column[3].disabled = false
                this.form = {}
                this.form.udId = this.initValueDefault()
                this.$refs.crud.rowAdd()
            },
            handAdd: function (row) {
                this.iconForm.id = ''
                this.form = {}
                this.option.column[3].disabled = false
                this.form.udId = this.initValueDefault()
                this.form.parentId = row.id
                this.option.column[1].valueDefault = this.form.parentId
                this.$refs.crud.rowAdd()
            },

            initValueDefault() {
                let date = new Date();
                let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                let strDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                let currentDate = "MODEL_" + date.getFullYear() + month + strDate + date.getHours() + date.getMinutes() + date.getSeconds() + date.getMilliseconds();
                return currentDate
            },

            handView: function (row, index) {
                this.iconForm.id = row.id
                this.$refs.crud.rowView(row, index)
            },

            handleEdit(row, index) {
                if ('3' === row.type) {
                    this.option.column[3].disabled = true
                } else {
                    this.option.column[3].disabled = false
                }
                this.iconForm.id = row.id
                this.$refs.crud.rowEdit(row, index)
            },

            handleDelete(row) {
                this.$confirm('此操作将永久删除, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    delObj(row.id).then(() => {
                        this.getList()
                        this.getDicTreeData()
                        this.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success',
                            duration: 2000
                        })
                    })
                })
            },
            handleUpdate(row, index, done, loading) {
                this.form.id = row.id
                putObj(this.form).then(() => {
                    this.getList()
                    this.getDicTreeData()
                    done();
                    this.$notify({
                        title: '成功',
                        message: '更新成功',
                        type: 'success',
                        duration: 2000
                    })
                }).catch(() => {
                    loading();
                });
            },
            handleSave(row, done, loading) {
                addObj(this.form).then(() => {
                    this.getList()
                    this.getDicTreeData()
                    done();
                    this.$notify({
                        title: '成功',
                        message: '创建成功',
                        type: 'success',
                        duration: 2000
                    })
                }).catch(() => {
                    loading();
                });
            },
            handleFilter(param) {
                this.listQuery.name = param.name
                this.listQuery.type = param.type
                this.getList();
            },
            handleOpenBefore(show, type) {
                window.boxType = type;
                this.iconForm.windowType = type
                show();
            },

        },

        watch: {
            'form.type'() {
                if (this.form.type != '3') {
                    this.form.planLibId = undefined
                    this.option.column[6].rules[0].required = false;
                    this.option.column[7].rules[0].required = false;
                    this.option.column[9].viewDisplay = false;
                    this.option.column[9].addDisplay = false;
                    this.option.column[9].editDisplay = false;
                } else {
                    this.option.column[6].rules[0].required = true;
                    this.option.column[7].rules[0].required = true;
                    this.option.column[9].viewDisplay = true;
                    this.option.column[9].addDisplay = true;
                    this.option.column[9].editDisplay = true;
                }
            }
        }

    }
</script>

