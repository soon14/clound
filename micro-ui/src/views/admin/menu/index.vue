<template>
  <div class="app-container calendar-list-container">
    <basic-container>


      <avue-crud  :option="option"  ref="crud" :data="treeData"
                  @search-change="handleFilter"
                  @refresh-change="handleFilter"
                  @row-update="handleUpdate"
                  @row-save="handleSave"
                  :table-loading="listLoading"
                  v-model="form"
      >
        <template slot="menuLeft">
          <el-button type="primary"
                     @click="handleAdd"
                     size="small"
                     v-if="menuManager_btn_add">新 增</el-button>
        </template>
        <template slot="icon" slot-scope="scope">
          <i v-if="scope.row.icon" :class="scope.row.icon" style="font-size:24px"></i>
        </template>

        <template slot="parentIdForm" slot-scope="scope">
          <avue-input v-model="form.parentId"
                      type="tree"
                      placeholder="父级菜单"
                      :dic="dicTreeData"
                      :props="dicTreeProps">
          </avue-input>
        </template>
        <template slot="menu"
                  slot-scope="scope" >
          <el-button size="mini"
                     type="text"
                     icon="el-icon-plus"
                     v-if="menuManager_btn_add"
                     @click="handAdd(scope.row)">添加子级
          </el-button>
            <el-button size="mini"
                       type="text"
                       icon="el-icon-edit"
                       v-if="menuManager_btn_edit"
                       @click="handleEdit(scope.row,scope.index)">编辑
            </el-button>
            <el-button size="mini"
                       type="text"
                       icon="el-icon-delete"
                       v-if="menuManager_btn_del"
                       @click="handleDelete(scope.row)">删除
            </el-button>

        </template>
      </avue-crud>


    </basic-container>
  </div>
</template>

<script>
  import {addObj, delObj, fetchMenuTree, getObj, putObj} from '@/api/admin/menu'
  import {mapGetters} from 'vuex'
  import {tableOption} from '@/const/crud/admin/menu';
  export default {
    name: 'menu',
    data() {
      return {
        list: null,
        total: null,
        formEdit: true,
        formAdd: true,
        option: tableOption,
          listLoading: true,
        listQuery: {
          name: undefined
        },
        checkedKeys:[],
        treeData: [],
        dicTreeData:[],
        form: {
          menuId:''
        },
        dicTreeProps: {
          label: "name",
          value: 'id'
        },
        menuManager_btn_add: false,
        menuManager_btn_edit: false,
        menuManager_btn_del: false
      }
    },
    created() {
      this.getList()
        this.getDicTreeData()
      this.menuManager_btn_add = this.permissions['sys_menu_add']
      this.menuManager_btn_edit = this.permissions['sys_menu_edit']
      this.menuManager_btn_del = this.permissions['sys_menu_del']
    },
    computed: {
      ...mapGetters([
        'elements',
        'permissions'
      ])
    },
    methods: {
      getList() {
          this.listLoading = true
        fetchMenuTree(this.listQuery).then(response => {
          this.treeData = response.data.data
            this.listLoading = false
        })
      },
      getDicTreeData(){
          if(this.treeData){
              fetchMenuTree().then(response => {
                  this.dicTreeData=response.data.data
              })
          }

      },
      handleAdd: function () {

        this.form={}
        this.$refs.crud.rowAdd()
      },
      handAdd: function (row) {
        this.form.parentId=row.id
        this.option.column[1].valueDefault = this.form.parentId
        this.$refs.crud.rowAdd()
      },
      handleEdit(row, index) {
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
        this.form.menuId=row.id
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
          }).catch(() => {
            loading();
          });
        })
      } ,handleFilter(param) {
        this.listQuery.name=param.name
        this.getList();
      }

    },

    watch:{
      'form.type'(){
        if(this.form.type==='1'){
          this.option.column[6].rules[0].required=false;
          this.option.column[7].rules[0].required=false;
        }else{
          this.option.column[6].rules[0].required=true;
          this.option.column[7].rules[0].required=true;
        }
      }
    }

  }
</script>

