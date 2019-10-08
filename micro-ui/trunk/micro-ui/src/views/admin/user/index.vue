

<template>
  <div class="user">
    <basic-container>
      <el-row :span="24">
        <el-col :xs="24"
                :sm="24"
                :md="5">
          <Tree
                  :isShow="isShow"
                  v-model="inputValue"
                  :treeSeach="treeSeach"
                  :treeNode="treeNode"
                  :checkBox="checkBox"
                  :beforeClick="beforeClick"
                  :clickNode="clickNode"
                  :checkBoxType="checkBoxType"
                  @checkBoxCall="asyncCheckBoxCall"
                  :async="async"
                  :asyncCall="asyncCall"
                  :loadImg="loadImg"
          />
        </el-col>
        <el-col :xs="24"
                :sm="24"
                :md="19"
                class="user__main">
          <avue-crud :option="option"
                     ref="crud"
                     v-model="form"
                     :page="page"
                     @on-load="getList"
                     :table-loading="listLoading"
                     @search-change="handleFilter"
                     @refresh-change="handleRefreshChange"
                     @row-update="update"
                     @row-save="create"
                     :before-open="handleOpenBefore"
                     :data="list">
            <template slot="menuLeft">
              <el-button v-if="sys_user_add"
                         class="filter-item"
                         @click="handleCreate"
                         size="small"
                         type="primary"
                         icon="el-icon-edit">添加
              </el-button>
            </template>

            <template slot="role"
                      slot-scope="scope">
              <span >
                <el-tag>{{scope.row.sysRole==null?'角色已被删除':scope.row.sysRole.roleName}} </el-tag>&nbsp;&nbsp;
              </span>
            </template>
            <template slot="deptId"
                      slot-scope="scope">
              {{scope.row.deptName}}
            </template>
            <template slot="lockFlag"
                      slot-scope="scope">
              <el-tag>{{scope.label}}</el-tag>
            </template>
            <template slot="menu"
                      slot-scope="scope">
              <el-button v-if="sys_user_edit"
                         size="small"
                         type="text"
                         icon="el-icon-edit"
                         @click="handleUpdate(scope.row,scope.index)">编辑
              </el-button>
              <el-button v-if="sys_user_del"
                         size="small"
                         type="text"
                         icon="el-icon-delete"
                         @click="deletes(scope.row,scope.index)">删除
              </el-button>
            </template>
            <template slot="deptIdForm" slot-scope="scope">
              <tree-select
                      v-model="form.deptName"
                      :default-expand-level="1"
                      :normalizer="normalizer"
                      :load-options="loadOptions"
                      noResultsText="暂无数据"
                      :options="treeNode"
                      zIndex="10099"
                      noChildrenText="暂无子节点"
                      placeholder="请选择"
                      :maxHeight="240"
                      :clearable="false"
                      @select="treeSelect"
              />
            </template>
            <template slot="roleForm"
                      slot-scope="scope">
              <avue-select v-model="form.role"
                                placeholder="请选择角色"
                                :dic="rolesOptions"
                                :props="roleProps"></avue-select>
            </template>
          </avue-crud>
        </el-col>
      </el-row>
    </basic-container>
  </div>

</template>

<script>
  import {addObj, delObj, fetchList, getObj, putObj} from "@/api/admin/user";
  import {deptRoleList} from "@/api/admin/role";
  import {fetchDeptTree,fetchTree,fetchTreelist} from '@/api/admin/dept';
  import {tableOption} from '@/const/crud/admin/user';
  import {mapGetters} from "vuex";
  import Tree from "@/components/ztree/vtree";
  import popTree from "@/components/ztree/poptree";
  import { validatenull } from "@/util/validate";

  export default {
    name: "table_user",
    components: {Tree,popTree},
    data() {
      return {
        isShow:true,//是否显示搜索框
        text:"", //勾选的文本值
        inputValue:"",//搜索输入的值
        popVisible:false,
        treeNode: [],
        checkBox: false, //是否开启复选框
        checkBoxType: false, //是否级联选中
        hiddenLine: false,//是否连线
        async: true,//是否异步加载
        loadImg:true,

        treeOption: {
          nodeKey: 'id',
          addBtn: false,
          menu: false,
          props: {
            label: 'name',
            value: 'id'
          }
        },
        option: tableOption,
        treeDeptData: [],
        checkedKeys: [],
        roleProps: {
          label: "roleName",
          value: 'roleId'
        },
        defaultProps: {
          label: "name",
          value: 'id',
        },
        page: {
          total: 0, // 总页数
          currentPage: 1, // 当前页数
          pageSize: 20, // 每页显示多少条,
          isAsc: false//是否倒序
        },
        list: [],
        listLoading: true,
        form: {},
        rolesOptions: [],
      };
    },
    computed: {
      ...mapGetters(["permissions"])
    },

    created() {
      //初始化
      fetchTree().then(data=>{
        let zNodesData = data.data.data;
        this.treeNode=zNodesData;
      }).catch(() => {
      });
      this.sys_user_add = this.permissions["sys_user_add"];
      this.sys_user_edit = this.permissions["sys_user_edit"];
      this.sys_user_del = this.permissions["sys_user_del"];

    },
    methods: {
      treeSeach(){
        console.log(this.inputValue);
        let parmes={name:this.inputValue};
        if (!validatenull(this.inputValue)) {
          if (this.inputValue=='公安局' || this.inputValue=='分局' || this.inputValue=='派出所' || this.inputValue=='支队') {
            alert("请输入部门全称")
          }else{
            fetchTreelist(parmes).then(data=>{
              let zNodesData = data.data.data;
              this.treeNode=zNodesData;
            }).catch(() => {
            });
          }
        }else {
          fetchTree(parmes).then(data=>{
            let zNodesData = data.data.data;
            this.treeNode=zNodesData;
          }).catch(() => {
          });
        }
      },

      asyncCheckBoxCall(checkBox) {
        let checkedNames = [], checkedIds = [];
        for(let i=0;i<checkBox.length;i++){
          checkedIds.push(checkBox[i].id);
          checkedNames.push(checkBox[i].name);
        }
        console.log(checkedIds);
        console.log(checkedNames);

        this.text=checkedNames.join(",");
      },
      //击节点前触发回调函数，return false 无法触发clickNode事件
      beforeClick(node) {

        return true;
      },
      /*点击节点信息 上个点击节点信息*/
      clickNode(data, oldData) {
        console.log(data.id , data.name);
        this.page.page = 1;
        this.getList(this.page, {deptId: data.id});
      },
        btnclickNode(data, oldData) {
            this.form.deptId = data.id
            this.form.deptName = data.name
            this.popVisible = false
        },

      /*异步回调函数 data 当前节点数据 call 回调函数*/
      asyncCall(data, call) {
        let pasmes={deptId:data.id,name:data.name};
        fetchTree(pasmes).then(data=>{
          let zNodesData = data.data.data;
          call(zNodesData);
          console.log(this.treeNode)
        }).catch(() => {
        });
      },

      //表单 部门树弹框 选中的节点数据
      treeSelect(node){
        this.form.deptId=node.id
      },
      //表单 部门树弹框 分支节点延迟加载
      loadOptions({action, parentNode, callback}) {
        let children = parentNode.children || []
        if (action === "LOAD_CHILDREN_OPTIONS" && children.length <= 0) {
          fetchTree({deptId: parentNode.id, name: parentNode.name}).then(data => {
            let nodeData = data.data.data || []
            if (nodeData.length <= 0) {
              parentNode.hasChildren = "false"
            }
            parentNode.children = nodeData;
            callback();
          }).catch(() => {
          });
        }
      },
      //表单 部门树弹框 自定义键值名称
      normalizer(node) {
        let children = node.children
        let hasChildren = node.hasChildren
        children = ((children == null || children.length <= 0) && hasChildren == "true") ? null : children
        return {
          id: node.id,
          label: node.name,
          children: children,
        }
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
      getNodeData(data) {
        deptRoleList().then(response => {
          this.rolesOptions = response.data.data;
        });
      },
      handleDept() {
        fetchTree().then(response => {
          this.treeDeptData = response.data.data;
        });
      },
      handleFilter(param) {
        this.page.page = 1;
        this.getList(this.page, param);
      },
      handleRefreshChange() {
        this.getList(this.page)
      },
      handleCreate() {
        this.$refs.crud.rowAdd();
      },
      handleOpenBefore(show, type) {
        window.boxType = type;
        this.handleDept();
        if (['edit', 'views'].includes(type)) {
          if(null!=this.form.sysRole && typeof(this.form.sysRole.roleId)!="undefined" && null!= this.form.sysRole.roleId){
            this.form.role= this.form.sysRole.roleId ;
          }
        } else if (type === 'add') {
          this.role = '';
        }
        deptRoleList().then(response => {
          this.rolesOptions = response.data.data;
        });
        show();
      },
      handleUpdate(row, index) {
        this.$refs.crud.rowEdit(row, index);
        this.form.password = undefined
      },
      create(row, done, loading) {
        addObj(this.form).then(() => {
          this.getList(this.page);
          done();
          this.$notify({
            title: "成功",
            message: "创建成功",
            type: "success",
            duration: 2000
          });
        }).catch(() => {
          loading();
        });
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
          "此操作将永久删除该用户(用户名:" + row.username + "), 是否继续?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        ).then(() => {
          delObj(row.userId).then(() => {
              this.list.splice(index, 1);
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
      }
    }
  };
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

