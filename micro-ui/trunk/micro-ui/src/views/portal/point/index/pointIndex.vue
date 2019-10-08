<template>
    <div class="execution">
        <basic-container>
            <avue-crud
                    ref="crud"
                    v-model="form"
                    :page="page"
                    :data="tableData"
                    :option="tableOption"
                    :table-loading="tableLoading"
                    @on-load="getList"
                    @refresh-change="refreshChange"
                    :before-open="handleOpenBefore"
                    @row-update="handleUpdate"
                    @row-save="handleSave"
                    @row-del="rowDel"
                    @search-change="searchChange"
                    @search-reset="handleSearchReset"
            >
                <template slot="name" slot-scope="scope">
                    <el-tag @click="handleView(scope.row,scope.row.index)"
                            class="el-button el-button--text el-button--small">{{scope.row.name}}
                    </el-tag>
                </template>
                <template slot="status" slot-scope="scope">
                    <div v-if="scope.row.status == '0'">
                        <el-tag type="success">{{findLabelByValue(dictStatusData,scope.row.status)}}</el-tag>
                    </div>
                    <div v-else>
                        <el-tag type="danger">{{findLabelByValue(dictStatusData,scope.row.status)}}</el-tag>
                    </div>
                </template>
                <template slot="upperLimit" slot-scope="scope">
                 <span>
                     {{scope.row.upperLimit==0?'无上限':scope.row.upperLimit}}
                 </span>
                </template>
                <template slot="udId" slot-scope="scope">
                 <span>
                         <a href="javascript:void(0)" @click="copyText(scope.row.udId)">{{scope.row.udId}}  </a>
                 </span>
                </template>

                <template slot="menuLeft">
                    <el-button v-if="point_index_add"
                               class="filter-item"
                               @click="handleAdd"
                               size="small"
                               type="primary"
                               icon="el-icon-plus">新增
                    </el-button>

                    <el-button v-if="point_index_add"
                               class="filter-item"
                               @click="resetScore"
                               :loading="loadRest"
                               size="small"
                               type="danger"
                               icon="el-icon-warning-outline">重置分数
                    </el-button>

                    <el-button v-if="point_index_add"
                               class="filter-item"
                               @click="resetAllScore"
                               :loading="loadAllRest"
                               size="small"
                               type="danger"
                               icon="el-icon-warning-outline"> 重置所有
                    </el-button>
                </template>

                <template slot="menu" slot-scope="scope">
                    <el-button v-if="point_index_edit"
                               size="small"
                               type="text"
                               :icon="scope.row.status == '1'?'el-icon-caret-right':'el-icon-error'"
                               @click="changeStatus(scope.row)">
                        {{ scope.row.status == '1'?'启用':'停用' }}
                    </el-button>

                    <el-button v-if="point_index_edit"
                               size="small"
                               type="text"
                               icon="el-icon-edit"
                               @click="handleEdit(scope.row,scope.index)">编辑
                    </el-button>

                    <el-button v-if="point_index_del"
                               size="small"
                               type="text"
                               icon="el-icon-delete"
                               @click="rowDel(scope.row,scope.index)">删除
                    </el-button>


                </template>

            </avue-crud>


        </basic-container>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import {fetchList, addObj, getObj, delObj, putObj, putStatus} from '@/api/portal/point/index/pointIndex'
    import {tableOption, defaultUdId} from '@/const/crud/portal/point/index/pointIndex'
    import {validatenull} from "@/util/validate"
    import {remote} from "@/api/admin/dict"
    import {resetCurrentCycleScore,resetAllScore} from '@/api/portal/point/score/pointScore'

    export default {
        name: 'pointIndex',
        data() {
            return {
                tableData: [],
                form: {
                    udId: undefined
                },
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableLoading: false,
                tableOption: tableOption,
                point_index_add: false,
                loadRest: false,
                loadAllRest:false,
                point_index_edit: false,
                point_index_del: false,
                point_index_manager_area: false,
                point_index_manage_type: false,
                queryParams: [],
                dictStatusData: [],
            }
        },
        created() {
            remote('point_status').then(response => {
                this.dictStatusData = response.data.data || {}
            }).catch((err) => {
            });
        },
        mounted: function () {
            this.initPermission()

        },
        computed: {
            ...mapGetters(['permissions'])
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
            /**
             * 清除全局检索条件
             */
            handleSearchReset() {
                this.queryParams = [];
            },
            initPermission() {
                this.point_index_add = this.permissions['point_index_add'] || false
                this.point_index_edit = this.permissions['point_index_edit'] || false
                this.point_index_del = this.permissions['point_index_del'] || false
                this.point_index_manager_area = this.permissions['point_index_manager_area'] || false
                this.point_index_manage_type = this.permissions['point_index_manage_type'] || false
                //通过控制菜单权限 控制是否可选择自动积分和手动积分 以及是否可操作其他地市的指标
                this.tableOption.column[2].addDisplay = this.point_index_manage_type
                this.tableOption.column[2].editDisplay = this.point_index_manage_type
                this.tableOption.column[3].addDisplay = this.point_index_manager_area
                this.tableOption.column[3].editDisplay = this.point_index_manager_area

            },
            getList(page) {
                this.tableLoading = true
                fetchList(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, this.queryParams)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            searchChange(form) {
                this.queryParams = this.filterForm(form)
                this.getList(this.page)
            },
            resetAllScore(){
                let _this = this
                this.$confirm("重置所有分值将会耗费较长的时间、确定要重置吗？", '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    _this.loadAllRest = true
                    return resetAllScore()
                }).then(data => {
                    let title = data.data.data;
                    if (data.data.code === 0) {
                        this.$notify({
                            title: "成功",
                            message: title || "重置分值成功",
                            type: "success"
                        });
                    } else {
                        this.$notify.error({
                            title: '错误',
                            message: title || '重置分值失败、请刷新页面、稍后重试！'
                        });

                    }
                    _this.loadAllRest = false
                }).catch(function (err) {
                    _this.loadAllRest = false
                })

            },
            resetScore() {
                let _this = this
                this.$confirm("重置分值将会耗费较长的时间、确定要重置吗？", '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    _this.loadRest = true
                    return resetCurrentCycleScore()
                }).then(data => {
                    let title = data.data.data;
                    if (data.data.code === 0) {
                        this.$notify({
                            title: "成功",
                            message: title || "重置分值成功",
                            type: "success"
                        });
                    } else {
                        this.$notify.error({
                            title: '错误',
                            message: title || '重置分值失败、请刷新页面、稍后重试！'
                        });

                    }
                    _this.loadRest = false
                }).catch(function (err) {
                    _this.loadRest = false
                })

            },

            handleAdd: function () {
                this.form.udId = defaultUdId()
                this.$refs.crud.rowAdd()
            },
            handleEdit(row, index) {
                let status = row.status
                if (status != "1") {
                    this.$notify.error({
                        title: '错误',
                        message: '启用状态的指标不能编辑、请先停用！'
                    });
                    return
                }
                this.$refs.crud.rowEdit(row, index)
            },
            handleView(row) {
                this.$refs.crud.rowView(row)
            },
            handleOpenBefore(show, type) {
                window.boxType = type;
                show();
            },

            rowDel: function (row, index) {
                let status = row.status, _this = this;
                if (status != "1") {
                    this.$notify.error({
                        title: '错误',
                        message: '启用状态的指标不能删除、请先停用！'
                    });
                    return
                }
                this.$confirm('是否确认删除 指标名称：[' + row.name + ']', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return delObj(row.id)
                }).then(data => {
                    _this.tableData.splice(index, 1)
                    _this.$notify({
                        title: "成功",
                        message: "删除成功",
                        type: "success"
                    });

                    this.refreshChange()
                }).catch(function (err) {
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
                putObj(row).then(data => {
                    this.tableData.splice(index, 1, Object.assign({}, row))
                    this.$notify({
                        title: "成功",
                        message: "修改成功",
                        type: "success"
                    });
                    this.refreshChange()
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
                addObj(row).then(data => {
                    this.tableData.push(Object.assign({}, row))
                    this.$notify({
                        title: "成功",
                        message: "添加成功",
                        type: "success"
                    });
                    this.refreshChange()
                    done()
                }).catch(function (err) {
                    this.refreshChange()
                    done()
                })
            },
            /**
             * 刷新回调
             */
            refreshChange() {
                this.getList(this.page)
            },
            changeStatus(row) {
                let id = row.id, status = row.status;
                if (validatenull(id) || (status != "0" && status != "1")) {
                    this.$notify.error({
                        title: '错误',
                        message: '信息缺失、操作失败！'
                    });
                    return
                }
                let msg = (status == "0" ? "确定要停用该指标？ 注：使用到该指标项的分值均会被撤回、下个调度任务生效、该操作不可逆，请谨慎操作！" : "确定要启用该指标项吗？");
                this.$confirm(msg, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return putStatus(id, status)
                }).then(data => {
                    if (data.data.code === 0) {
                        this.$notify({
                            title: "成功",
                            message: "修改成功",
                            type: "success"
                        });
                    } else {
                        this.$notify.error({
                            title: '错误',
                            message: '修改失败、请刷新页面、稍后重试！'
                        });

                    }
                    this.refreshChange()
                }).catch(function (err) {

                })


            }
        }
    }
</script>

