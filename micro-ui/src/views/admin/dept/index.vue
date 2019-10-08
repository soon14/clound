`

<template>
  <div class="app-container calendar-list-container">
    <basic-container>
      <avue-crud ref="crud"
                 v-model="form"
                 :option="tableOption"
                 :data="treeData"
                 @on-load="getList"
                 @refresh-change="getList"
                 @row-save="create"
                 @row-del="rowDel"
                 @row-update="update"
        >
        <template slot="menuLeft">
          <el-button
                  v-if="deptManager_btn_add"
                     class="filter-item"
                     @click="handleCreate"
                     size="small"
                     type="primary"
                     icon="el-icon-edit">添加
          </el-button>
        </template>
        <template slot="menu" slot-scope="scope">
          <el-button
                  v-if="deptManager_btn_edit"
                  size="small"
                  type="text"
                  icon="el-icon-edit"
                  @click="handlerEdit(scope.row,scope.index)">编辑
          </el-button>
          <el-button
                  v-if="deptManager_btn_del"
                  size="small"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row,scope.index)">删除
          </el-button>
        </template>
        <template slot="parentIdForm"
                  slot-scope="scope">
          <avue-input v-model="form.parentId"
                           type="tree"
                           placeholder="请选择上级部门"
                           :dic="treeData"
                           :props="defaultProps"></avue-input>
        </template>
      </avue-crud>
    </basic-container>
  </div>
</template>

<script>
  import {tableOption} from '@/const/crud/admin/dept'
  import {mapGetters} from 'vuex'
  import {addObj, delObj, fetchTree, getObj, putObj} from '@/api/admin/dept'


  export default {
    name:'dept-bak',
    data(){
      return {
        tableOption: tableOption,
        listQuery: {
          name: undefined
        },
        treeData: [],
        form: {
          deptId:''
        },
        defaultProps: {
          label: "name",
          value: 'id',
        },
        deptManager_btn_add: false,
        deptManager_btn_edit: false,
        deptManager_btn_del: false
      }
    },
    created() {
      this.getList()
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
      getList() {
        fetchTree(this.listQuery).then(response => {
          this.treeData = response.data.data
        })
      },
      handleCreate() {
        this.$refs.crud.rowAdd();
      },
      handlerEdit(row, index) {
        this.$refs.crud.rowEdit(row, index);
      },
      handleDelete(row, index) {
        this.$refs.crud.rowDel(row, index);
      },
      handleDept(show) {
        fetchTree().then(response => {
          this.treeDeptData = response.data.data;
      });
        show();
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
      },rowDel: function (row, index) {
        var _this = this
        this.$confirm('是否确认删除"' + row.name + '"?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          return delObj(row.id)
        }).then(data => {
          this.getList(this.page)
          _this.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          })
        }).catch(function (err) {
        })
      },
      update(row, index, done, loading) {
        this.form.deptId=row.id
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
    }
  }
</script>

