<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       v-model="form"
                       :page="page"
                       :option="tabOption"
                       :data="tableData"
                       :table-loading="tableLoading"
                       @on-load="getList"
                       @refresh-change="refreshChange"
                       @row-update="handleUpdate"
                       @row-save="handleSave"
                       @row-del="rowDel"
                       @search-change="searchChange"
                       >
                <template slot="infoForm" slot-scope="scope">
                    <avue-crud :option="infoOption" :data="infoData" >

                    </avue-crud>
                </template>

                <template slot="status" slot-scope="scope">
                    <div v-if="scope.row.status == '0'">
                        <el-tag type="success">{{findLabelByValue(dictStatusData,scope.row.status)}}</el-tag>
                    </div>
                    <div v-else>
                        <el-tag type="danger">{{findLabelByValue(dictStatusData,scope.row.status)}}</el-tag>
                    </div>
                </template>

                <template slot="menuLeft">
                    <el-button v-if="point_scheme_add"
                               class="filter-item"
                               @click="handleAdd"
                               size="small"
                               type="primary"
                               icon="el-icon-plus">新增
                    </el-button>

                    <el-button v-if="point_scheme_add"
                               class="filter-item"
                               @click="resetAllScore"
                               :loading="loadAllRest"
                               size="small"
                               type="danger"
                               icon="el-icon-warning-outline"> 重置所有
                    </el-button>
                </template>

                <template slot="menu" slot-scope="scope">
                    <el-button
                               size="small"
                               type="text"
                               icon="el-icon-view"
                               @click="handleView(scope.row,scope.index)">查看
                    </el-button>
                    <el-button v-if="point_scheme_edit"
                               size="small"
                               type="text"
                               :icon="scope.row.status == '1'?'el-icon-caret-right':'el-icon-error'"
                               @click="changeStatus(scope.row)">
                        {{ scope.row.status == '1'?'启用':'停用' }}
                    </el-button>
                    <el-button v-if="point_scheme_edit"
                               size="small"
                               type="text"
                               icon="el-icon-edit"
                               @click="handleEdit(scope.row,scope.index)">编辑
                    </el-button>
                    <el-button v-if="point_scheme_del"
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
    import {fetchList,fetchCycleList,addObj, putStatus, delObj, putObj,resetScore,deleteScore} from '@/api/portal/point/scheme/pointScheme'
    import {tableOption} from '@/const/crud/portal/point/scheme/pointScheme'
    import {validatenull} from "@/util/validate";
    import {remote}  from  "@/api/admin/dict";
    import {resetCurrentCycleScore,resetAllScore} from '@/api/portal/point/score/pointScore'
    export default {
        name: "pointScheme",
        data(){
            return{
                tableData: [],
                form: {},
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                loadAllRest:false,
                tableLoading: false,
                point_scheme_add: false,
                point_scheme_edit: false,
                point_scheme_del: false,
                point_scheme_area:false,
                point_scheme_universal:false,
                tabOption:tableOption,
                infoData:[],
                dictStatusData:[],
                infoOption:{
                    addBtn: false,
                    menu:false,
                    column: [
                        { label:"所属周期", prop:"cycle"},
                        { label:'周期开始时间', prop:'cycleStartTime',type: 'date',format: 'yyyy-MM-dd', valueFormat: 'yyyy-MM-dd',},
                        { label:'周期结束时间', prop:'cycleEndTime',type: 'date',format: 'yyyy-MM-dd',  valueFormat: 'yyyy-MM-dd',}]
                }
            }
        },
        created() {
            this.initPermission()
            remote('point_status').then(response => {
                this.dictStatusData = response.data.data||{}
            }).catch((err) => {});
        },
        computed: {
            ...mapGetters(['permissions'])
        },
        methods:{
            initPermission() {
                this.point_scheme_add = this.permissions['point_scheme_add']
                this.point_scheme_edit = this.permissions['point_scheme_edit']
                this.point_scheme_del = this.permissions['point_scheme_del']
                this.point_scheme_area = this.permissions['point_scheme_area'] || false
                this.point_scheme_universal = this.permissions['point_scheme_universal'] || false
                //通过控制菜单权限 控制是否可选择自动积分和手动积分 以及是否可操作其他地市的指标
                this.tabOption.column[6].addDisplay = this.point_scheme_universal
                this.tabOption.column[6].editDisplay = this.point_scheme_universal
                this.tabOption.column[4].addDisplay = this.point_scheme_area
                this.tabOption.column[4].editDisplay = this.point_scheme_area
            },
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

            cycleList(row){
                fetchCycleList(row.id).then(response => {
                    this.infoData = response.data.data
                })
            },
            searchChange(form) {
                this.getList(this.page, form)
            },
            handleView(row, index){
                this.$refs.crud.rowView(row, index)
                this.cycleList(row)
            },
            handleAdd: function () {
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

            changeStatus(row) {
                let id = row.id, status = row.status, _this = this;
                if (validatenull(id) || (status != "0" && status != "1")) {
                    _this.$message({
                        showClose: true,
                        message: '信息缺失、操作失败',
                        type: 'error'
                    })
                    return
                }
                this.$confirm('确定要' + (status == "0" ? "停用" : "启用") + '该方案？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return putStatus(id, status)
                }).then(data => {
                    if (data.data.code === 0) {
                        _this.$message({
                            showClose: true,
                            message: '操作成功',
                            type: 'success'
                        })
                    } else {
                        _this.$message({
                            showClose: true,
                            message: '操作失败、请刷新页面、稍后重试',
                            type: 'error'
                        })
                    }
                    this.refreshChange()
                }).catch(function (err) {

                })
            },

            rowDel: function (row, index) {
                let _this = this, status = row.status
                if (status != "1") {
                    _this.$message({
                        showClose: true,
                        message: '启用状态的指标不能被删除、请先停用！',
                        type: 'error'
                    })
                    return
                }
                this.$confirm('是否确认删除方案[' + row.name+']', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return delObj(row.id)
                }).then(data => {
                    deleteScore(row.id)
                    _this.tableData.splice(index, 1)
                    _this.$message({
                        showClose: true,
                        message: '删除成功',
                        type: 'success'
                    })
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
                    this.$message({
                        showClose: true,
                        message: '修改成功',
                        type: 'success'
                    })
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
                    this.$message({
                        showClose: true,
                        message: '添加成功',
                        type: 'success'
                    })
                    this.refreshChange()
                    done()
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

<style scoped>

</style>