<template>
  <div class="app-container calendar-list-container">
    <basic-container>

      <avue-crud  ref="crud"
                  :option="option"
                  :data="treeData"
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
                     v-if="area_btn_add">新 增</el-button>
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
                     v-if="area_btn_add"
                     @click="handAdd(scope.row)">添加子级
          </el-button>
            <el-button size="mini"
                       type="text"
                       icon="el-icon-edit"
                       v-if="area_btn_edit"
                       @click="handleEdit(scope.row,scope.index)">编辑
            </el-button>
            <el-button size="mini"
                       type="text"
                       icon="el-icon-delete"
                       v-if="area_btn_del"
                       @click="handleDelete(scope.row)">删除
            </el-button>

        </template>
      </avue-crud>


    </basic-container>
  </div>
</template>

<script>
  import {addObj, delObj, fetchAreaTree, getObj, putObj} from '@/api/portal/area/area'
  import {mapGetters} from 'vuex'
  import {tableOption,newVue} from '@/const/crud/portal/area/area';
  export default {
    name: 'area',
    data() {
      return {
        option: tableOption,
        listLoading: true,
        listQuery: {
          name: undefined,
          type: undefined
        },
        checkedKeys:[],
        treeData: [],
        dicTreeData:[],
        form: {
          id:''
        },
        dicTreeProps: {
          label: "name",
          value: 'id',
        },
        area_btn_add: false,
        area_btn_edit: false,
        area_btn_del: false,
        editId:"",
      }
    },
    created() {
      this.getList()
      this.getDicTreeData()
      this.area_btn_add = this.permissions['area_add']
      this.area_btn_edit = this.permissions['area_edit']
      this.area_btn_del = this.permissions['area_del']
      newVue.obj = this
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
        fetchAreaTree(this.listQuery).then(response => {
          this.treeData = response.data.data
            this.listLoading = false
        })
      },
      getDicTreeData(){
          if(this.treeData){
              fetchAreaTree().then(response => {
                  this.dicTreeData=response.data.data
              })
          }

      },
      handleAdd: function () {
        this.form={}
        this.form.parentId=''
        this.$refs.crud.rowAdd()
      },
      handAdd: function (row) {
        this.form.parentId=row.id
        this.option.column[2].valueDefault = this.form.parentId
        this.$refs.crud.rowAdd()
      },
      handleEdit(row, index) {
        this.$refs.crud.rowEdit(row, index)
        this.editId=row.id;
      },
      handleOpenBefore(show, type) {
        window.boxType = type;
        show();
      },
      handleDelete(row) {
        this.$confirm("此操作将删除(" + row.name + ") , 是否继续?", '提示', {
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
          }).cache(() => {
            this.$notify({
              title: "失败",
              message: "删除失败",
              type: "error",
              duration: 2000
            });
          });

        })
      },
      handleUpdate(row, index, done, loading) {
        this.form.id=row.id
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
      } ,
      handleFilter(param) {
        this.listQuery.name=param.name
        this.listQuery.type=param.type
        this.getList();
      }

    },

  }
</script>

