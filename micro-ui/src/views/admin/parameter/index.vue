

<template>
  <div class="execution">
    <basic-container>
      <avue-crud ref="crud"
                 :page="page"
                 :data="tableData"
                 :table-loading="tableLoading"
                 :option="tableOption"
                 @on-load="getList"
                 @search-change="handleFilter"
                 @refresh-change="refreshChange"
                 @row-update="handleUpdate"
                 @row-save="handleSave"
                 :before-open="handleOpenBefore"
                 @row-del="rowDel"
                 v-model="form">
        <template slot-scope="scope"
                  slot="menu">
          <el-button type="text"
                     v-if="sys_parameter_edit"
                     icon="el-icon-check"
                     size="small"
                     @click="handleEdit(scope.row,scope.index)">编辑</el-button>
          <el-button type="text"
                      v-if="sys_parameter_del"
                     icon="el-icon-delete"
                     size="small"
                     plain
                     @click="handleDel(scope.row,scope.index)">删除</el-button>
        </template>
      </avue-crud>
    </basic-container>
  </div>
</template>

<script>
  import { fetchList, getObj, addObj, putObj, delObj } from '@/api/admin/parameter'
  import { tableOption} from '@/const/crud/admin/parameter'
  import { mapGetters } from 'vuex'


  export default {
    name: 'parameter',
    data() {
      return {
        tableData: [],
        form:{},
        page: {
          total: 0, // 总页数
          currentPage: 1, // 当前页数
          pageSize: 20 // 每页显示多少条
        },
        tableLoading: false,
        tableOption: tableOption,
        sys_parameter_add: false,
        sys_parameter_edit: false,
        sys_parameter_del: false,
      }
    },
      created() {
          this.sys_parameter_add = this.permissions['sys_parameter_add']
          this.sys_parameter_edit = this.permissions['sys_parameter_edit']
          this.sys_parameter_del = this.permissions['sys_parameter_del']
      },
    mounted: function() { },
    computed: {

      ...mapGetters(['permissions']),
    },
    methods: {

      getList(page, params) {
        this.tableLoading = true
        fetchList(Object.assign({
          current: page.currentPage,
          size: page.pageSize
        }, params)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      /**
       * @title 打开新增窗口
       * @detail 调用crud的handleadd方法即可
       *
       **/
      handleAdd: function() {
        this.$refs.crud.rowAdd()
      },
      handleEdit(row, index) {
        this.$refs.crud.rowEdit(row, index);
      },
      handleDel(row, index) {
        this.$refs.crud.rowDel(row, index)
      },
        handleFilter(param) {
            console.log(param);
            this.page.page = 1;
            this.getList(this.page, param);
        },
      rowDel: function(row, index) {
        var _this = this
        this.$confirm('是否确认删除参数:' + row.parameterKey , '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function() {
            return delObj(row.id)
          }).then(data => {
          _this.tableData.splice(index, 1)
          _this.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          })
          this.getList(this.page)
        }).catch(function(err) { })
      },

        handleOpenBefore(show, type) {
            window.boxType = type;
            show();
        },
      /**
       * @title 数据更新
       * @param row 为当前的数据
       * @param index 为当前更新数据的行数
       * @param done 为表单关闭函数
       *
       **/
      handleUpdate: function(row, index, done) {
        putObj(row).then(data => {
          this.tableData.splice(index, 1, Object.assign({}, row))
          this.$message({
            showClose: true,
            message: '修改成功',
            type: 'success'
          })
          done()
          this.getList(this.page)
        })
      },
      /**
       * @title 数据添加
       * @param row 为当前的数据
       * @param done 为表单关闭函数
       *
       **/
      handleSave: function(row, done) {
        addObj(row).then(data => {
          this.tableData.push(Object.assign({}, row))
          this.$message({
            showClose: true,
            message: '添加成功',
            type: 'success'
          })
          done()
          this.getList(this.page)
        })
      },
      /**
       * 刷新回调
       */
      refreshChange() {
        this.getList(this.page)
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>
