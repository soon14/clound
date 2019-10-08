

<template>
  <div class="log">
    <basic-container>
      <avue-crud ref="crud"
                 :page="page"
                 :data="tableData"
                 :table-loading="tableLoading"
                 :option="tableOption"
                 @on-load="getList"
                 @search-change="searchChange"
                 @refresh-change="refreshChange"
                 @sort-change="sortChange"
                 @row-del="rowDel">

        <template slot="menuLeft">
          <el-button
                  class="filter-item"
                  @click="dialogVisible = true"
                  size="small"
                  type="primary"
                  icon="el-icon-download">导出
          </el-button>
        </template>
        <template slot-scope="scope"
                  slot="menu">
          <el-button type="text"
                     v-if="permissions.sys_log_del"
                     icon="el-icon-delete"
                     size="mini"
                     @click="handleDel(scope.row,scope.index)">删除
          </el-button>
        </template>
        <template slot="userName"
                  slot-scope="scope">
              <span >
               {{ null==scope.row.sysUser?'无':scope.row.sysUser.username}}
              </span>
        </template>
        <template slot="policeNumber"
                  slot-scope="scope">
              <span >
               {{ null==scope.row.sysUser?'无':scope.row.sysUser.policeNumber }}
              </span>
        </template>
      </avue-crud>

      <export-file :exprotFields="exprotFields"
                   :dialogVisible="dialogVisible"
                   :url="exportUrl"
                   :params="form"
                   :pageSize="page.pageSize"
                   :currentPage="page.currentPage"
                   :ascs="page.ascs"
                   :descs="page.descs"
                   :fileName="fileName"
                   v-on:hiddeDialg="dialogVisible = false" ></export-file>

    </basic-container>
  </div>
</template>

<script>
  import {delObj, fetchList} from '@/api/admin/log'
  import {tableOption} from '@/const/crud/admin/log'
  import {mapGetters} from 'vuex'
  import exportFile from "@/page/export/exportFile"

  export default {
    name: 'log',
    components: { "exportFile" : exportFile},
    data() {
      return {
        exportUrl: '/admin/log/export',
        fileName:  '日志访问信息',
        exprotFields : {
            type : "日志类型",
            title : "标题",
            remoteAddr : "地址",
            requestUri : "请求地址",
            method : "请求方法",
            createBy: "访问者"
        },
        form: {
            type: undefined
        },
        dialogVisible: false,
        tableData: [],
        page: {
          total: 0, // 总页数
          currentPage: 1, // 当前页数
          pageSize: 20, // 每页显示多少条,
          ascs: "",
          descs: ""
        },
        tableLoading: false,
        tableOption: tableOption
      }
    },
    created() {
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
          descs: page.descs,
          ascs: page.ascs,
          current: page.currentPage,
          size: page.pageSize
        }, params)).then(response => {
          this.tableData = response.data.data.records
          this.page.total = response.data.data.total
          this.tableLoading = false
        })
      },
      handleDel(row, index) {
        this.$refs.crud.rowDel(row, index)
      },
      rowDel: function (row, index) {
        var _this = this
        this.$confirm('是否确认删除ID为"' + row.id + '"的日志?', '警告', {
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
      /**
       * 搜索回调
       */
      searchChange(form) {
        this.getParams(form)
        this.getList(this.page, this.form)
      },
      /**
       * 条件处理
       */
      getParams(form){
          if(form.type != undefined){
              this.form.type = form.type
          }
      },
      /**
       * 刷新回调
       */
      refreshChange() {
        this.getList(this.page, this.form)
      },
        /**
         * 排序
          * @param val
         */
      sortChange(val){
        //将驼峰转出下划线连接
        let field = val.prop.replace(/\B([A-Z])/g, '_$1').toLowerCase()
        if('ascending' == val.order){//升序
            this.page.ascs = field
            this.page.descs = ""
        }else if('descending' == val.order){//降序
            this.page.descs = field
            this.page.ascs = ""
        }else{//移除
            this.page.descs = ""
            this.page.ascs = ""
        }
        this.getList(this.page, this.form)
      }
    }
  }
</script>

<style lang="scss" scoped>
</style>

