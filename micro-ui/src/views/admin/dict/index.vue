<template>
  <div class="execution">
    <basic-container>


      <avue-crud  ref="crud"
                  :page="page"
                  :data="tableData"
                  :table-loading="tableLoading"
                  :option="tableOption"
                  @on-load="getList"
                  @row-update="handleUpdate"
                  @row-save="handleSave"
                  @search-change="searchChange"
                  @refresh-change="getList"
                  @selection-change="selectionChange">

      <template slot="menuLeft">
        <el-button @click="handleAdd"
                     v-if="permissions.sys_dict_add"
                     icon="el-icon-plus"
                     type="primary"
                     size="small">添加</el-button>
        <el-button type="danger"
                     v-if="permissions.sys_dict_del && isTree == 0"
                     icon="el-icon-delete"
                     @click="handleDelAll"
                     size="small">删除</el-button>
      </template>

        <template slot-scope="scope" slot="menu">
          <el-button type="text" v-if="isTree == 1"
                     icon="el-icon-plus"
                     size="mini"
                     plain
                     @click="handleAddChild(scope.row,scope.index)">添加下级节点
          </el-button>
          <el-button type="text"
                     v-if="permissions.sys_dict_edit"
                     icon="el-icon-edit"
                     size="mini"
                     plain
                     @click="handleEdit(scope.row,scope.index)">编辑
          </el-button>
          <el-button type="text"
                     v-if="permissions.sys_dict_del"
                     icon="el-icon-delete"
                     size="mini"
                     plain
                     @click="handleDel(scope.row,scope.index)">删除
          </el-button>
        </template>
      </avue-crud>
    </basic-container>
  </div>
</template>

<script>
    import {addObj, delObj, fetchList, putObj, fetchDictTree} from '@/api/admin/dict'
    import {tableOption} from '@/const/crud/admin/dict'
    import {mapGetters} from 'vuex'

    export default {
        name: 'dict',
        data() {
            return {
                tableData: [],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20 // 每页显示多少条
                },
                tableLoading: false,
                tableOption: tableOption,
                isTree: false,
                type: undefined,
                parentId: '0',
                parentIds: '0',
                selData: []
            }
        },
        created() {
            this.type = this.$route.query.type
            this.isTree = this.$route.query.isTree
            this.tableOption.column[2].valueDefault = this.type
            if(this.isTree == 0){
                this.tableOption.selection = true
                this.tableOption.column[6].editDisplay = false
                this.tableOption.column[7].editDisplay = false
            }else {
                this.tableOption.selection = false
                this.tableOption.column[6].editDisplay = true
                this.tableOption.column[7].editDisplay = true
            }
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
                    current: page.currentPage,
                    size: page.pageSize,
                    type : this.type,
                    isTree: this.isTree
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
            handleAdd: function () {
                this.parentId = '0'
                this.parentIds = '0'
                this.$refs.crud.rowAdd()
            },
            handleEdit(row, index) {
                this.parentId = row.parentId
                this.parentIds = row.parentIds
                this.$refs.crud.rowEdit(row, index)
            },
            /**
             * 添加下级节点
             */
            handleAddChild(row, index){
                this.parentId = row.id
                this.parentIds = row.parentIds + ',' + row.id
                this.$refs.crud.rowAdd()
            },
            handleDel: function (row, index) { //删除行
                var _this = this
                let title = '是否确认删除标签名为"' + row.label + '",数据类型为"' + row.type + '"'
                if(this.isTree == 1){
                    title += '及其子节点'
                }
                title += '的数据项?'
                this.$confirm(title, '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return delObj(row.id, _this.type, _this.isTree)
                }).then(() => {
                    this.getList(this.page)
                    _this.$message({
                        showClose: true,
                        message: '删除成功',
                        type: 'success'
                    })
                }).catch(function () {
                })
            },
            /**
             * @title 数据更新
             * @param row 为当前的数据
             * @param index 为当前更新数据的行数
             * @param done 为表单关闭函数
             *
             **/
            handleUpdate: function (row, index, done) {
                row.parentId = this.parentId
                row.parentIds = this.parentIds
                putObj(row).then(() => {
                    this.tableData.splice(index, 1, Object.assign({}, row))
                    this.$message({
                        showClose: true,
                        message: '修改成功',
                        type: 'success'
                    })
                    this.getList(this.page)
                    done()
                })
            },
            /**
             * @title 数据添加
             * @param row 为当前的数据
             * @param done 为表单关闭函数
             *
             **/
            handleSave: function (row, done) {
                row.parentId = this.parentId
                row.parentIds = this.parentIds
                addObj(row).then(data => {
                    this.tableData.push(Object.assign({}, row))
                    this.$message({
                        showClose: true,
                        message: '添加成功',
                        type: 'success'
                    })
                    this.getList(this.page)
                    done()
                })
            },
            searchChange(form) {
                this.getList(this.page, form)
            },
            handleDelAll(){// 列表批量删除
                if(this.selData.length > 0){
                    this.$confirm('此操作将永久删除, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        let ids = ""
                        for(let i = 0; i < this.selData.length; i++){
                            ids += this.selData[i].id + ","
                        }
                        delObj(ids, this.type, this.isTree).then(() => {
                            this.getList(this.page)
                            this.$message({
                                showClose: true,
                                message: '删除成功',
                                type: 'success'
                            })
                        })
                    })
                }else {
                    this.$message({
                        showClose: true,
                        message: '请选择要删除的字典',
                        type: 'warning'
                    })
                }
            },
            selectionChange(data){
                this.selData = data
            }
        }
    }
</script>

<style lang="scss" scoped>
</style>

