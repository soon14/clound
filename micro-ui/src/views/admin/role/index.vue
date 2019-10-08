<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-crud :option="tableOption"
                       :data="list"
                       ref="crud"
                       :page="page"
                       v-model="form"
                       :table-loading="listLoading"
                       :before-open="handleOpenBefore"
                       @on-load="getList"
                       @search-change="handleFilter"
                       @refresh-change="handleRefreshChange"
                       @row-update="update"
                       @row-save="create">

                <template slot="dsType"
                          slot-scope="scope">
              <span >
                <el-tag>{{getDictLabel(scope.row.dsType)}} </el-tag>
              </span>
                </template>
                <template slot="menuLeft">
                    <el-button v-if="roleManager_btn_add"
                               class="filter-item"
                               @click="handleCreate"
                               size="small"
                               type="primary"
                               icon="el-icon-edit">添加
                    </el-button>
                </template>
                <template slot="dsTypeForm" slot-scope="scope">
                    <avue-select v-model="form.dsType"
                                 placeholder="请选择数据权限"
                                 :dic="dictScopeData"
                                 :props="dictProps"></avue-select>



                </template>

                <template slot="dsScopeForm" slot-scope="scope">
                    <div v-if="form.dsType == 7">
                        <el-tree class="filter-tree"
                                 :data="dsScopeData"
                                 :check-strictly="true"
                                 node-key="code"
                                 highlight-current
                                 :props="deptTreeProps"
                                 ref="scopeTree"
                                 :default-checked-keys="checkedDsScope"
                                 show-checkbox>
                        </el-tree>
                    </div>
                </template>


                <template slot="menu"
                          slot-scope="scope" >
                    <label v-if="showHideDdl(scope.row.lockUp,scope.row.updateBy)" style="padding-left: 10px">
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-edit"
                               v-if="roleManager_btn_edit"
                               @click="handleUpdate(scope.row,scope.index)">编辑
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-delete"
                               v-if="roleManager_btn_del"
                               @click="handleDelete(scope.row,scope.index)">删除
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-plus"
                               plain
                               @click="handlePermission(scope.row,scope.index)"
                               v-if="roleManager_btn_perm">权限
                    </el-button>
                    </label>
                </template>
            </avue-crud>
        </basic-container>
        <el-dialog title="分配权限"
                   :visible.sync="dialogPermissionVisible">
            <el-tree class="filter-tree"
                     :data="treeData"
                     :default-checked-keys="checkedKeys"
                     :check-strictly="false"
                     node-key="id"
                     highlight-current
                     :props="defaultProps"
                     show-checkbox
                     ref="menuTree"
                     :filter-node-method="filterNode"
            >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span v-if=" data.permission " v-show="node.checked">
          <el-button type="text" size="mini" @click="definedDataScope(data.id,data.permission)">
              <span v-html="getLevelLabel(data.id)"></span>
          </el-button>
           <el-button v-if="exitScope(data.id)" type="text" size="mini" style="color: red" @click="clearDefinedDataScope(data.id)">
               &nbsp;&nbsp;清除
           </el-button>

        </span>
      </span>
            </el-tree>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" ref="buttonUpdate"
                           @click="updatePermession(roleId, roleCode)">更 新
                </el-button>
            </div>
        </el-dialog>
        <el-dialog title="自定义菜单数据权限" :visible.sync="dialogDefinedPermission">
            <el-form v-model="definedForm">

                <el-form-item label="数据权限" prop="levelId">
                    <el-select v-model="definedForm.levelId" placeholder="请选择权限级别">
                        <el-option v-for="dict in dictScopeData" :label="dict.label" :value="dict.value" :key="dict.value"/>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <div v-if="definedForm.levelId == 7">
                        <el-tree class="filter-tree"
                                 :data="definedDeptData"
                                 :check-strictly="true"
                                 node-key="code"
                                 highlight-current
                                 :props="deptTreeProps"
                                 ref="definedTree"
                                 :default-checked-keys="definedLevelId"
                                 :filter-node-method="filterNode"
                                 show-checkbox>
                        </el-tree>

                    </div>

                </el-form-item>
            </el-form>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="saveDefinedDataScope">确 定
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {addObj, delObj, fetchList, fetchRoleTree, getObj, permissionUpd, putObj,updateRoleMenu,fetchRoleMenuScope} from '@/api/admin/role'
    import {tableOption} from '@/const/crud/admin/role'
    import {fetchMenuTree} from '@/api/admin/menu'
    import {remote} from "@/api/admin/dict";
    import {fetchDeptTree, fetchTree} from "@/api/admin/dept";
    import {mapGetters} from 'vuex'
    export default {
        name: 'table_role',
        data() {
            return {
                tableOption: tableOption,
                dsScopeData: [],
                definedDeptData: [],
                treeData: [],
                checkedKeys: [],
                checkedDsScope: [],
                definedLevelId: [],
                defaultProps: {
                    label: "name",
                    value: 'id'
                },
                deptTreeProps:{
                    label: "name",
                    value: 'code'
                },
                dictProps: {
                    label: "label",
                    value: 'value'
                },
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20 // 每页显示多少条
                },
                menuIds: '',
                list: [],
                listLoading: true,
                form: {},
                buttonUpdate: '',
                definedForm: {
                    levelId: '',
                    tempMenuId: '',
                    tempDeptId: [],
                    menuPermission:''
                },
                treeDeptData: [],
                dictData: {
                },
                dictScopeData:{},
                scopeMenuInfo: new Map(),
                scopeMenuInfoChange:0,
                roleId: undefined,
                roleCode: undefined,
                rolesOptions: undefined,
                dialogPermissionVisible: false,
                roleManager_btn_add: false,
                roleManager_btn_edit: false,
                roleManager_btn_del: false,
                roleManager_btn_perm: false,
                dialogDefinedPermission: false
            }
        },
        created() {
            this.roleManager_btn_add = this.permissions['sys_role_add']
            this.roleManager_btn_edit = this.permissions['sys_role_edit']
            this.roleManager_btn_del = this.permissions['sys_role_del']
            this.roleManager_btn_perm = this.permissions['sys_role_perm']

            remote('data_scope').then(response => {
                this.dictData = response.data.data
                if(this.isAdmin==true){
                    this.dictScopeData = response.data.data
                    return ;
                }
                 let type=this.userRole.dsType
                 let tempDict=[]
                switch(type){
                    case "0":
                        this.dictScopeData = response.data.data
                        break;
                    case "1":
                        for(let dict of response.data.data){
                            if(dict.value > 0){
                                tempDict.push(dict);
                            }
                        }
                        this.dictScopeData=tempDict;
                        break;
                    case "2":
                        for(let dict of response.data.data){
                            if(dict.value > 1){
                                tempDict.push(dict);
                            }
                        }
                        this.dictScopeData=tempDict;
                        break;
                    case "3":
                        for(let dict of response.data.data){
                            if(dict.value > 2){
                                tempDict.push(dict);
                            }
                        }
                        this.dictScopeData=tempDict;
                        break;
                    case "4":
                        let level=this.userRole.codeLevel;
                        switch (level) {
                            case '0':
                                for(let dict of response.data.data){
                                    if(dict.value > 0){
                                        tempDict.push(dict);
                                    }
                                }
                                this.dictScopeData=tempDict;
                                break;
                            case '1':
                                for(let dict of response.data.data){
                                    if(dict.value > 0){
                                        tempDict.push(dict);
                                    }
                                }
                                this.dictScopeData=tempDict;
                                break;
                            case '2':
                                for(let dict of response.data.data){
                                    if(dict.value > 1){
                                        tempDict.push(dict);
                                    }
                                }
                                this.dictScopeData=tempDict;
                            default:
                                for(let dict of response.data.data){
                                    if(dict.value > 3){
                                        tempDict.push(dict);
                                    }
                                }
                                this.dictScopeData=tempDict;
                              break;


                        }
                        break;

                    default:
                        for(let dict of response.data.data){
                            if(dict.value > 3){
                                tempDict.push(dict);
                            }
                        }
                        this.dictScopeData=tempDict;
                }
            })


        },

        computed: {
            ...mapGetters(['elements', 'permissions',"userInfo","userRole","userDept","isAdmin"]),

        },
        methods: {
            showHideDdl:function(lockUp,userId){
                if(lockUp ==='0'){
                    if(userId===this.userInfo.userId||this.isAdmin==true){
                        return true;
                    }
                    return false;
                }
               return true;
            },
            addMap:function(key,value){
                this.scopeMenuInfo.set("menu_"+key,value)
                this.scopeMenuInfoChange +=1
            },
            deleteMap:function(key){
                this.scopeMenuInfo.delete("menu_"+key)
                this.scopeMenuInfoChange +=1
            },
            clearMap:function(){
              this.scopeMenuInfoChange=0
              this.scopeMenuInfo.clear()
                this.definedLevelId=[]
            },
            getList(page, params) {
                this.listLoading = true
                fetchList(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, params)).then(response => {
                    this.list = response.data.data.records
                    this.page.total = response.data.data.total
                    this.listLoading = false
                })
            },
            exitScope:function(menuId){
              return this.scopeMenuInfo.has("menu_"+menuId)
            },
            handleRefreshChange() {
                this.getList(this.page)
            },
            handleFilter(param) {
                this.page.page = 1;
                this.getList(this.page, param);
            },
            handleCreate() {
                this.$refs.crud.rowAdd();
            },
            handleOpenBefore(show, type) {
                window.boxType = type;
                this.handleDept();
                fetchTree().then(response => {
                    this.dsScopeData = response.data.data;
                    if (this.form.dsScope) {
                        this.checkedDsScope = (this.form.dsScope).split(",")
                    } else {
                        this.checkedDsScope = []
                    }
                });
                show();
            },
            handleUpdate(row, index) {
                this.$refs.crud.rowEdit(row, index);
            },
            handlePermission(row) {

                this.clearMap();
                fetchRoleMenuScope(row.roleId).then(response => {
                    let menuScope=response.data;
                    if(typeof (menuScope)!="undefined" ){
                        for(let ms of menuScope){
                            let  scopeMenu = {};
                            scopeMenu['menuId'] = ms.menuId
                            scopeMenu['dsType'] = ms.dsType
                            scopeMenu['dsScope'] = ms.dsScope
                            scopeMenu['permission']=ms.permission
                            scopeMenu['label'] = ''
                            this.dictData.forEach(function(obj){
                                if(obj.value===scopeMenu['dsType']){
                                    scopeMenu['label'] = obj.label
                                    return
                                }
                            })
                            this.addMap(ms.menuId, scopeMenu);
                        }
                    }
                });

                fetchRoleTree(row.roleId)
                    .then(response => {
                        this.checkedKeys = response.data
                        return fetchMenuTree()
                    })
                    .then(response => {
                        this.treeData = response.data.data

                        // 解析出所有的太监节点
                        this.checkedKeys = this.resolveAllEunuchNodeId(this.treeData, this.checkedKeys, [])
                        this.dialogStatus = 'permission'
                        this.dialogPermissionVisible = true
                        this.roleId = row.roleId
                        this.roleCode = row.roleCode
                    });




            },
            /**
             * 解析出所有的太监节点id
             * @param json 待解析的json串
             * @param idArr 原始节点数组
             * @param temp 临时存放节点id的数组
             * @return 太监节点id数组
             */
            resolveAllEunuchNodeId(json, idArr, temp) {
                for (let i = 0; i < json.length; i++) {
                    const item = json[i]
                    // 存在子节点，递归遍历;不存在子节点，将json的id添加到临时数组中
                    if (item.children && item.children.length !== 0) {
                        this.resolveAllEunuchNodeId(item.children, idArr, temp)
                    } else {
                        temp.push(idArr.filter(id => id === item.id))
                    }
                }
                return temp
            },
            filterNode(value, data) {
                if (!value) return true
                return data.label.indexOf(value) !== -1
            },
            getNodeData(data, done) {
                done();
            },
            handleDelete(row, index) {
                var _this = this
                this.$confirm('是否确认删除名称为"' + row.roleName + '"' + '"的数据项?', '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return delObj(row.roleId)
                }).then(() => {
                    this.getList(this.page)
                    this.list.splice(index, 1);
                    _this.$message({
                        showClose: true,
                        message: '删除成功',
                        type: 'success'
                    })
                }).catch(function () {
                })
            },
            create(row, done, loading) {
                if (this.form.dsType === 1) {
                    this.form.dsScope = this.$refs.scopeTree.getCheckedKeys().join(',')
                }
                addObj(this.form).then(() => {
                    this.getList(this.page)
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
            update(row, index, done, loading) {
                if (this.form.dsType === '7') {
                    this.form.dsScope = this.$refs.scopeTree.getCheckedKeys().join(',')
                }
                putObj(this.form).then(() => {
                    this.getList(this.page)
                    done();
                    this.$notify({
                        title: '成功',
                        message: '修改成功',
                        type: 'success',
                        duration: 2000
                    })
                }).catch(() => {
                    loading();
                });
            },
            updatePermession(roleId, roleCode) {
                this.menuIds = ''
                this.menuIds = this.$refs.menuTree.getCheckedKeys().join(',').concat(',').concat(this.$refs.menuTree.getHalfCheckedKeys().join(','))
                let defineForm ={}
                let nodes =new Array();
                for(let value of this.scopeMenuInfo.values()){
                    nodes.push(JSON.parse(JSON.stringify(value)));
                }
                defineForm['menuIds']=this.menuIds
                defineForm['roleId']=roleId
                defineForm['roleMenuScopeList']=nodes
                updateRoleMenu(defineForm).then(() => {
                    this.dialogPermissionVisible = false
                    fetchMenuTree()
                        .then(response => {
                            this.form = response.data.data
                            return fetchRoleTree(roleId)
                        })
                        .then(response => {
                            this.checkedKeys = response.data
                            this.$notify({
                                title: '成功',
                                message: '修改成功',
                                type: 'success',
                                duration: 2000
                            })
                        })
                })
            },
            definedDataScope(id,permission) {
                this.dialogDefinedPermission = true
                this.definedForm.tempMenuId = id
                this.definedForm.menuPermission=permission
                let scopeMenu=this.scopeMenuInfo.get("menu_"+id);
                if(scopeMenu&&typeof (scopeMenu)!="undefined"){
                    this.definedForm.levelId=scopeMenu.dsType
                    if(this.definedForm.levelId==='7' && scopeMenu.dsScope ){
                        this.definedLevelId=scopeMenu.dsScope.split(',')
                    }else{
                        this.definedLevelId=[]
                    }

                }else{
                    this.definedForm.levelId=''
                }
                if (this.definedDeptData) {
                    fetchTree().then(response => {
                        this.definedDeptData = response.data.data;
                    });
                }
            },
            clearDefinedDataScope(menuId){
                this.deleteMap(menuId)
            },
            saveDefinedDataScope() {
                if (this.definedForm.levelId === '7') {
                    this.definedForm.tempDeptId = this.$refs.definedTree.getCheckedKeys().join(',')
                    this.definedLevelId = this.$refs.definedTree.getCheckedKeys();
                }else{
                    this.definedForm.tempDeptId=''
                    this.definedLevelId=[]
                }

                var  scopeMenu = {};
                scopeMenu['menuId'] = this.definedForm.tempMenuId
                scopeMenu['dsType'] = this.definedForm.levelId
                scopeMenu['dsScope'] = this.definedForm.tempDeptId
                scopeMenu['label'] = ''
                scopeMenu['permission']=this.definedForm.menuPermission
                this.dictData.forEach(function(obj){
                   if(obj.value===scopeMenu['dsType']){
                       scopeMenu['label'] = obj.label
                       return
                   }
                })
                this.addMap(this.definedForm.tempMenuId, scopeMenu);
                this.dialogDefinedPermission = false

            },

            handleDept() {
                fetchTree().then(response => {
                    this.treeDeptData = response.data.data;
                });
            },

        getDictLabel(value){
            let label='未定义'
            for(let dict of this.dictData){
                if(dict.value === value){
                    label=dict.label;
                    return label
                }
            }

            return label;
        }
        ,
            getLevelLabel(menuId) {
                let butText = '自定义';
                let x = this.scopeMenuInfoChange;
                let menuInfo = this.scopeMenuInfo.get("menu_"+menuId);
                if (typeof (menuInfo)!="undefined" && null!=menuInfo) {
                    butText = menuInfo.label;
                }
                return "&nbsp;&nbsp;" + butText;
            }

        }
    }
</script>
