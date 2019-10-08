
<template>
    <div class="user">
        <basic-container>
            <el-row :span="24">
                <el-col :xs="24"
                        :sm="24"
                        :md="5"
                        class="user__tree">
                    <Tree
                            :isShow="isShow"
                            v-model="inputValue"
                            :treeSeach="treeSeach"
                            :treeNode="treeNode"
                            :checkBox="checkBox"
                            :beforeClick="beforeClick"
                            :clickNode="clickNode"
                            :checkBoxType="checkBoxType"
                            :async="async"
                            :asyncCall="asyncCall"
                            :loadImg="loadImg"
                    />
                </el-col>
                <el-col :xs="24"
                        :sm="24"
                        :md="19"
                        class="user__main">
                    <template>
                        <el-form :inline="true" :model="tableform" class="demo-form-inline">
                            <el-form-item label="机构名称:">
                                <avue-input v-model="tableform.name" placeholder="机构名称"></avue-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" icon="el-icon-search" size="small" @click="searchChange(tableform.name)" >查 询</el-button>
                                <el-button size="small"   icon="el-icon-delete" @click="searchClear" >清 空</el-button>
                            </el-form-item>
                        </el-form>
                        <el-button size="small" type="primary" icon="el-icon-plus" @click="addFormview=true">添 加</el-button>
                        <div>
                            <el-table
                                    :data="treeData"
                                    v-loading="loading"
                                    style="width:100%"
                                    row-key="id"
                                    border
                                    lazy
                                    :load="load">
                                <el-table-column
                                        type="index"
                                        width="50">
                                </el-table-column>
                                <el-table-column
                                        prop="name"
                                        label="部门名称"
                                        width="300">
                                </el-table-column>
                                <el-table-column
                                        prop="code"
                                        label="部门编码"
                                >
                                </el-table-column>
                                <el-table-column
                                        prop="sort"
                                        label="排序"
                                >
                                </el-table-column>
                                <el-table-column label="操作" >
                                    <template slot-scope="scope">
                                        <el-button type="text" size="mini" icon="el-icon-edit" @click="rowclick(scope.row)" >编 辑</el-button>
                                        <el-button type="text" size="mini" icon="el-icon-delete" @click="rowDel(scope.row)">删 除</el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                            <el-dialog title="编辑"  :visible.sync="editFormview" :append-to-body="true">
                                <el-form :model="editForm" status-icon :rules="rules" ref="editForm" label-width="100px" class="demo-ruleForm">
                                    <el-form-item label="部门名称" prop="name">
                                        <el-input v-model="editForm.name"></el-input>
                                    </el-form-item>
                                    <el-form-item label="部门编码" prop="code">
                                        <el-input v-model="editForm.code"></el-input>
                                    </el-form-item>
                                    <el-form-item label="排序" prop="sort">
                                        <el-input v-model.number="editForm.sort"></el-input>
                                    </el-form-item>
                                    <el-form-item label="id" prop="id" hidden>
                                        <el-input v-model="editForm.id"></el-input>
                                    </el-form-item>
                                    <el-form-item label="上级部门" prop="parentId">
                                        <popTree
                                                v-model="inputValue"
                                                :isShow="isShow"
                                                :treeSeach="treeSeach"
                                                :treeNode="treeNode"
                                                :checkBox="checkBox"
                                                :beforeClick="beforeClick"
                                                :clickNode="btnclickNode"
                                                :checkBoxType="checkBoxType"
                                                :async="async"
                                                :asyncCall="asyncCall"
                                                :popVisible="popVisible"
                                                @submit="submit"
                                        >
                                            <el-input style="width: 100%;"
                                                      slot="pop-btn"
                                                      v-model="form.upName"
                                                      placeholder="请选择上级部门"
                                            ></el-input>
                                        </popTree>
                                    </el-form-item>
                                </el-form>
                                <div slot="footer"
                                     class="dialog-footer">
                                    <el-button type="primary" size="small" @click="update('editForm')">保 存</el-button>
                                    <el-button size="small" @click="closeForm('editForm')">关 闭</el-button>
                                </div>
                            </el-dialog >

                            <el-dialog title="添加"  :visible.sync="addFormview" :append-to-body="true">
                                <el-form :model="addForm" status-icon :rules="rules" ref="addForm" label-width="100px" class="demo-ruleForm">
                                    <el-form-item label="部门名称" prop="name">
                                        <el-input v-model="addForm.name"></el-input>
                                    </el-form-item>
                                    <el-form-item label="部门编码" prop="code">
                                        <el-input v-model="addForm.code"></el-input>
                                    </el-form-item>
                                    <el-form-item label="排序" prop="sort">
                                        <el-input v-model.number="addForm.sort"></el-input>
                                    </el-form-item>
                                    <el-form-item label="id" prop="id" hidden>
                                        <el-input v-model="addForm.id"></el-input>
                                    </el-form-item>
                                    <el-form-item label="上级部门" prop="parentId">
                                        <popTree
                                                v-model="inputValue"
                                                :isShow="isShow"
                                                :treeSeach="treeSeach"
                                                :treeNode="treeNode"
                                                :checkBox="checkBox"
                                                :beforeClick="beforeClick"
                                                :clickNode="btnclickNode"
                                                :checkBoxType="checkBoxType"
                                                :async="async"
                                                :asyncCall="asyncCall"
                                                :popVisible="popVisible"
                                                @submit="submit"
                                        >
                                            <el-input style="width: 100%;"
                                                      slot="pop-btn"
                                                      type="input"
                                                      v-model="form.upName"
                                                      placeholder="请选择上级部门"
                                            ></el-input>
                                        </popTree>
                                    </el-form-item>
                                </el-form>

                                <div slot="footer"
                                     class="dialog-footer">
                                    <el-button type="primary" size="small" @click="create('addForm')">保 存</el-button>
                                    <el-button size="small" @click="resetForm('addForm')">清 空 </el-button>
                                    <el-button size="small" @click="closeForm('addForm')">关 闭</el-button>
                                </div>
                            </el-dialog>
                        </div>
                    </template>
                </el-col>
            </el-row>
        </basic-container>
    </div>
</template>

<script>
    import {deepClone} from '@/util/util'
    import {tableOption} from '@/const/crud/admin/dept'
    import {mapGetters} from 'vuex'
    import Tree from "@/components/ztree/vtree";
    import popTree from "@/components/ztree/poptree";
    import {addObj, delObj, fetchTree,fetchTreelist, getObj, putObj} from '@/api/admin/dept'
    import { validatenull } from "@/util/validate";


    export default {
        name:'dept-bak',
        components: {Tree,popTree},
        data(){
            return {
                search: '',
                loadImg:true,
                isShow:true,//是否显示搜索框
                text:"", //勾选的文本值
                inputValue:"",//搜索输入的值
                popVisible:false,
                treeNode: [],
                checkBox: false, //是否开启复选框
                checkBoxType: false, //是否级联选中
                hiddenLine: false,//是否连线
                async: true,//是否异步加载

                id:'',
                parentId:'',
                upName:'',
                loading:false,
                addFormview:false,
                editFormview:false,
                tableLoading:false,
                tableOption: tableOption,

                treeData: [],
                tableform:{
                    name:'',
                    parentId:'',
                    code:'',
                    upName:''
                },
                editForm:{
                    name:'',
                    code:'',
                    sort:'',
                    id:'',
                    upName:'',
                    parentId:'',
                },
                addForm: {
                    name:'',
                    code:'',
                    sort:'',
                    id:'',
                    upName:'',
                    parentId:'',
                },
                form: {
                    name:'',
                    code:'',
                    sort:'',
                    id:'',
                    upName:'',
                    parentId:'',
                },
                rules: {
                    name:[{ required: true, message: '请输入机构名称', trigger: 'blur' },],
                    code:[{ required: true, message: '请输入机构编码', trigger: 'blur' },],
                },
                deptManager_btn_add: false,
                deptManager_btn_edit: false,
                deptManager_btn_del: false
            }
        },
        created() {
            //初始化
            fetchTree().then(data=>{
                let zNodesData = data.data.data;
                this.treeNode=zNodesData;
                this.treeData = zNodesData;
            }).catch(() => {
            });

            this.deptManager_btn_add = this.permissions['sys_dept_add']
            this.deptManager_btn_edit = this.permissions['sys_dept_edit']
            this.deptManager_btn_del = this.permissions['sys_dept_del']
        },
        computed: {
            ...mapGetters([
                'elements',
                'permissions'
            ])
        },
        methods: {
            load(tree, treeNode, resolve) {
                setTimeout(() => {
                    let pasmes={deptId:tree.id};
                    fetchTree(pasmes).then(data=>{
                        let zNodesData = data.data.data;
                        resolve(zNodesData);
                    }).catch(() => {
                    });
                }, 1000)
            },


            /**
             * 刷新回调
             */
            refreshChange() {
                this.getList(this.form)
            },
            closeForm(formName){
                this.editFormview = false
                this.addFormview = false
                this.form.upName = ''
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.form.upName = ''
            },
            rowclick(row){
                this.editFormview = true
                let from = deepClone(row);
                this.editForm = from
                this.form.upName = row.upName
            },
            submit(){
                this.form.upName = this.upName
                this.addForm.parentId = this.parentId
                this.addForm.upName = this.upName
                this.editForm.parentId = this.parentId
                this.editForm.upName = this.upName
                this.popVisible=false
            },

            btnclickNode(data, oldData) {
               this.parentId = data.id
               this.upName = data.name
            },

            searchChange(form) {
                if (this.tableform.name!==''|| this.tableform.name!==undefined) {
                    this.getList({parentId:this.tableform.deptId,code:this.tableform.code,name:this.tableform.name});
                }else {
                    alert("查询条件不能为空!")
                }

            },
            searchClear(){
                this.tableform.name=''
            },
            treeSeach(){
                console.log(this.inputValue);
                let parmes={name:this.inputValue};
                if (!validatenull(this.inputValue)) {
                    fetchTreelist(parmes).then(data=>{
                        let zNodesData = data.data.data;
                        this.treeNode=zNodesData;
                    }).catch(() => {
                    });
                }else if (this.inputValue!=="公安局" || this.inputValue!=="分局" || this.inputValue!=="派出所" || this.inputValue!=="支队") {
                    fetchTree(parmes).then(data=>{
                        let zNodesData = data.data.data;
                        this.treeNode=zNodesData;
                    }).catch(() => {
                    });
                }else {
                    alert("请输入部门全称")
                }
            },

            //击节点前触发回调函数，return false 无法触发clickNode事件
            beforeClick(node) {

                return true;
            },
            /*点击节点信息 上个点击节点信息*/
            clickNode(data, oldData) {
                this.tableform.deptId = data.id
                this.tableform.code = data.code
                let code = data.code
                if (code!=''){
                    this.getList({parentId: data.id,code:data.code});
                }
            },

            getList(pasme) {
                this.loading = true
                fetchTreelist(pasme).then(response => {
                    this.treeData = response.data.data
                    this.loading = false
                })
            },

            /*异步回调函数 data 当前节点数据 call 回调函数*/
            asyncCall(data, call) {
                let pasmes={deptId:data.id};
                fetchTree(pasmes).then(data=>{
                    let zNodesData = data.data.data;
                    call(zNodesData);
                }).catch(() => {
                });
            },

            create(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let pasmes={deptId:this.addForm.id};
                        addObj(this.addForm).then(() => {
                            this.getList(pasmes);
                            this.addFormview = false
                            this.$notify({
                                title: "成功",
                                message: "创建成功",
                                type: "success",
                                duration: 2000
                            });
                        }).catch(() => {

                        });
                    }else {
                        this.$message({
                            showClose: true,
                            message: '请填写完整信息!',
                            type: 'error submit!!'
                        })
                        return false;
                    }
                });
            },

            update(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.editForm.deptId=this.editForm.id
                        let pasmes={deptId:this.editForm.id};
                        putObj(this.editForm).then(() => {
                            this.getList(pasmes);
                            this.editFormview = false
                            this.$notify({
                                title: "成功",
                                message: "修改成功",
                                type: "success",
                                duration: 2000
                            });
                        }).catch(() => {

                        });
                    }else {
                        this.$message({
                            showClose: true,
                            message: '请填写完整信息!',
                            type: 'error submit!!'
                        })
                        return false;
                    }
                });
            },

            rowDel: function (row, index) {
                var _this = this
                this.$confirm('是否确认删除"' + row.name + '"?', '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return delObj(row.id)
                }).then(data => {
                    let pasmes={deptId:row.id};
                    this.getList(pasmes)
                    _this.$message({
                        showClose: true,
                        message: '删除成功',
                        type: 'success'
                    })
                }).catch(function (err) {
                })
            },
        },
        watch:{
            inputValue(newVal){
                console.log(newVal)
            }
        }
    }
</script>
<style lang="scss">
    .user {
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
