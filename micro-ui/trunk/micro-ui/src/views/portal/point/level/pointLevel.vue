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
                    @refresh-change="handleFilter"
                    @row-update="handleUpdate"
                    @row-save="handleSave"
                    @row-del="rowDel"
                    @search-change="handleFilter"
                    :before-open="handleOpenBefore"
                    @size-change="sizeChange"
            >
                <template slot="status" slot-scope="scope">
                    <div v-if="scope.row.status == '0'">
                        <el-tag type="success">{{findLabelByValue(statusData,scope.row.status)}}</el-tag>
                    </div>
                    <div v-else>
                        <el-tag type="danger">{{findLabelByValue(statusData,scope.row.status)}}</el-tag>
                    </div>
                </template>

                <template slot="menuLeft">
                    <el-button v-if="point_level_add"
                               class="filter-item"
                               @click="handleAdd"
                               size="small"
                               type="primary"
                               icon="el-icon-plus">新增
                    </el-button>
                    <el-button v-if="point_level_add"
                               class="filter-item"
                               @click="resetScore"
                               :loading="loadRest"
                               size="small"
                               type="danger"
                               icon="el-icon-warning-outline">重置分数
                    </el-button>
                </template>

                <template slot="menu"
                          slot-scope="scope">
                    <el-button size="small"
                               type="text"
                               icon="el-icon-view"
                               @click="handleDetail(scope.row,scope.index)">查看
                    </el-button>
                    <el-button v-if="point_level_edit"
                               size="small"
                               type="text"
                               :icon="scope.row.status == '1'?'el-icon-caret-right':'el-icon-error'"
                               @click="changeStatus(scope.row)">
                        {{ scope.row.status == '1'?'启用':'停用' }}
                    </el-button>
                    <el-button v-if="point_level_edit"
                               size="small"
                               type="text"
                               icon="el-icon-edit"
                               @click="handleEdit(scope.row,scope.index)">编辑
                    </el-button>
                    <el-button v-if="point_level_del"
                               size="small"
                               type="text"
                               icon="el-icon-delete"
                               @click="rowDel(scope.row,scope.index)">删除
                    </el-button>
                </template>

                <template slot="levelChildForm" slot-scope="scope">
                    <avue-crud ref="childCrud"
                               :option="childOption"
                               :data="childData"
                               :table-loading="childLoading"
                               @cell-mouse-enter="editClick"
                    >
                        <template slot="linkType" slot-scope="scope">
                            <el-select v-model="scope.row.linkType" placeholder="请选择链接类型"
                                       @change="linkTypeChange(scope.row)">
                                <el-option v-for="dict in linkTypeData" :label="dict.label" :value="dict.value"
                                           :key="dict.value"/>
                            </el-select>
                        </template>
                        <template slot="score" slot-scope="scope">
                            <el-form-item v-if="scope.row.linkType=='range'">
                                <el-input-number @change="checkScore(scope.row)" class="inputRange" :step="1"
                                                 step-strictly v-model="scope.row.leftScore" controls-position="right"
                                                 size="mini" :min="0" placeholder="大于等于"></el-input-number>
                                <span>-</span>
                                <el-input-number @change="checkScore(scope.row)" class="inputRange" :step="1"
                                                 step-strictly v-model="scope.row.rightScore" controls-position="right"
                                                 size="mini" :min="0" placeholder="小于等于"></el-input-number>
                            </el-form-item>
                            <el-input-number v-else class="inputNumber" @change="checkScore(scope.row)" :step="1"
                                             step-strictly v-model="scope.row.leftScore" controls-position="right"
                                             size="mini" :min="0" placeholder="分值"></el-input-number>
                        </template>

                        <template slot="menu"
                                  slot-scope="scope">
                            <el-button v-if="point_level_del"
                                       size="small"
                                       type="text"
                                       icon="el-icon-delete"
                                       @click="rowCancel(scope.row,scope.index)">移除
                            </el-button>
                        </template>
                    </avue-crud>
                </template>

            </avue-crud>


        </basic-container>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import {
        addObj,
        changeStatus,
        delObj,
        fetchList,
        getChild,
        getObj,
        putObj
    } from '@/api/portal/point/level/pointLevel'
    import {childOption, tableOption} from '@/const/crud/portal/point/level/pointLevel'
    import {validatenull} from "@/util/validate";
    import {remote} from "@/api/admin/dict";
    import {resetCurrentCycleScore} from '@/api/portal/point/score/pointScore'
    import {deepClone} from '@/util/util'

    export default {
        name: 'pointLevel',
        data() {
            return {
                form: {},
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                loadRest: false,
                tableLoading: false,
                childLoading: false,
                tableOption: tableOption,
                childOption: childOption,
                tableData: [],
                childData: [],
                linkTypeData: [],//链接类型字典
                statusData: [],//状态字典
                point_level_add: false,
                point_level_edit: false,
                point_level_del: false,
                point_level_manager_universal: false,
            }
        },
        created() {
            remote('point_status').then(response => {
                this.statusData = response.data.data || {}
            }).catch((err) => {
            });
            remote('point_level_link_type').then(response => {
                this.linkTypeData = response.data.data || {}
            }).catch((err) => {
            });

            this.initPermission()
        },
        mounted: function () {
        },
        computed: {
            ...mapGetters(['permissions', "isAdmin"])
        },
        methods: {
            initPermission() {
                this.point_level_add = this.permissions['point_level_add']
                this.point_level_edit = this.permissions['point_level_edit']
                this.point_level_del = this.permissions['point_level_del']
                this.point_level_manager_universal = this.permissions['point_level_manager_universal'] || false
                //通过控制菜单权限 控制是否可选择通用 以及是否可操作其他地市的评定方案
                this.tableOption.column[2].addDisplay = this.point_level_manager_universal
                this.tableOption.column[2].editDisplay = this.point_level_manager_universal
                this.tableOption.column[2].viewDisplay = this.point_level_manager_universal
                this.tableOption.column[3].addDisplay = this.point_level_manager_universal
                this.tableOption.column[3].editDisplay = this.point_level_manager_universal
                this.tableOption.column[3].viewDisplay = this.point_level_manager_universal
            },
            /**
             * @title 分页获取等级评定方案
             * @param page 分页信息，params 查询参数
             *
             **/
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
             * @title 获取等级评定子项
             * @param id 为当前方案id
             *
             **/
            getChildList(id) {
                this.childData = []
                this.childLoading = true
                getChild(id).then(response => {
                    this.childData = response.data.data
                    this.childLoading = false
                })
            },
            handleFilter(param) {
                this.page.currentPage = 1
                this.getList(this.page, param)
            },
            sizeChange(size) {
                this.page.pageSize = size;
            },
            handleOpenBefore(show, type) {
                window.boxType = type;
                this.childData = []
                show();
            },
            handleAdd: function () {
                this.form = {}
                this.$refs.crud.rowAdd()
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
                            message: title || '修改失败、请刷新页面、稍后重试！'
                        });

                    }
                    _this.loadRest = false
                }).catch(function (err) {
                    _this.loadRest = false
                })

            },
            handleEdit(row, index) {
                if (row.status != "1" && row.universal!="0") {
                        this.$message({
                            title: '错误',
                            message: '启用状态的方案不能被修改、请先停用！',
                            type: 'error',
                            duration: 2000
                        })
                     return
                }
                this.getChildList(row.id)
                this.$refs.crud.rowEdit(row, index)
            },

            /**　弹框 行删除操作*/
            rowCancel(row, index) {
                this.$refs.childCrud.$data.list.splice(index, 1)
            },
            /**
             * @title 弹框 行编辑
             * @param row 当前行
             **/
            editClick(row) {
                this.$refs.childCrud.rowCell(row, row.$index)
            },

            /**　启用、停用等级评定方案*/
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
                if(row.universal=="0" && status=="0"){
                    _this.$message({
                        showClose: true,
                        message: '启用状态的通用方案不能被停用！',
                        type: 'error'
                    })
                    return
                }
                this.$confirm('确定要' + (status == "0" ? "停用" : "启用") + '该方案？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return changeStatus(id, status)
                }).then(data => {
                    if (data.data.code === 0) {
                        _this.$message({
                            title: '成功',
                            message: '操作成功',
                            type: 'success',
                            duration: 2000
                        })
                    } else {
                        _this.$message({
                            title: '失败',
                            message: '操作失败、请刷新页面、稍后重试',
                            type: 'error',
                            duration: 2000
                        })
                    }
                    this.handleFilter()
                }).catch(function (err) {

                })
            },
            /**查看详情*/
            handleDetail(row, index) {
                this.getChildList(row.id)
                this.$refs.crud.rowView(row, index)
            },
            /**
             * @title 数据新增 保存
             * @param row 为当前的数据
             * @param done 为表单关闭函数
             *
             **/
            handleSave(row, done, loading) {
                let result = this.beforeSaveValid(this.$refs.childCrud.$data.list)
                if (!result) {
                    loading();
                } else {
                    let defineForm = {}
                    defineForm['pointLevelChildList'] = this.$refs.childCrud.$data.list
                    defineForm['name'] = this.form.name
                    defineForm['placeType'] = this.form.placeType
                    defineForm['cityCode'] = this.form.cityCode
                    defineForm['status'] = this.form.status
                    defineForm['universal'] = this.form.universal
                    addObj(defineForm).then(() => {
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
                }

            },
            /**
             * @title 数据更新
             * @param row 为当前的数据
             * @param index 为当前更新数据的行数
             * @param done 为表单关闭函数
             *
             **/
            handleUpdate(row, index, done, loading) {
                let result = this.beforeSaveValid(this.$refs.childCrud.$data.list)
                if (!result) {
                    loading();
                } else {
                    let updateForm = {}
                    updateForm['pointLevelChildList'] = this.$refs.childCrud.$data.list
                    updateForm['id'] = row.id
                    updateForm['name'] = this.form.name
                    updateForm['placeType'] = this.form.placeType
                    updateForm['cityCode'] = this.form.cityCode
                    updateForm['status'] = this.form.status
                    updateForm['universal'] = this.form.universal
                    putObj(updateForm).then(() => {
                        this.getList(this.page)
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
                }
            },
            /**链接类型修改时，清空当前行的分值 */
            linkTypeChange(data) {
                data.leftScore = ""
                data.rightScore = ""
            },
            /**校验分值 */
            checkScore(data) {
                if (data.linkType == 'range' && !validatenull(data.leftScore) && !validatenull(data.rightScore)) {
                    if (data.rightScore < data.leftScore &&data.rightScore!=0) {
                        data.leftScore = ""
                        data.rightScore = ""
                        this.$message.error("该分值不符合规范");
                    }
                }
            },

            /**
             *校验等级信息
             * 规则：
             * 1、只有一个大于号 并且只有一个小于号
             * 2、大于号必须大于范围的最大值
             * 3、小于号 必须小于范围的最小值
             * 4、范围可按左分值或者左分值排序均可、并且范围之间不能有重复
             * */
            validLevelType(childList) {
                let ltList = [], gtList = [], rangeList = [];
                for (let child of childList) {
                    let type = child.linkType;
                    switch (type) {
                        case 'range':
                            rangeList.push(child)
                            break;
                        case 'lt':
                            ltList.push(child)
                            break;
                        case 'gt':
                            gtList.push(child)
                            break;
                        default:
                    }
                }
                //如果大于号或者小于号有一个的长度 大于1 说明这样的分值有问题
                if (ltList.length > 1 || gtList.length > 1) {
                    return false;
                }
                //对范围进行排序
                if (rangeList.length > 0) {
                    rangeList.sort(function (val1, val2) {
                        if(val1.leftScore<val2.leftScore){
                            return 1;
                        }
                        if(val1.leftScore>val2.leftScore){
                            return -1;
                        }
                      /*  if (val1.leftScore < val2.leftScore) {
                            return false
                        } else {
                            return true
                        }*/
                    })
                }
                if (validatenull(rangeList)) {
                    if (ltList.length == 1 && gtList.length == 1) {
                        //必须要连续、小于值+1 必须等于 大于值
                        if (ltList[0].leftScore != gtList[0].leftScore + 1) {
                            return false
                        }
                    }
                } else {
                    //1、首先判断范围内是否重合
                    for (let i = 0; i < rangeList.length - 1; i++) {
                        let first = rangeList[i]
                        let sencond = rangeList[i + 1]
                        if (first.leftScore != sencond.rightScore + 1) {
                            return false
                        }
                    }
                    //2、判断 大于号的值 与范围的最大值是否能正常连接
                    if (!validatenull(gtList)) {
                        if (gtList[0].leftScore != rangeList[0].rightScore) {
                            return false;
                        }
                    }
                    //3、判断小于号的值 是否与范围的最小值正常连接
                    if (!validatenull(ltList)) {
                        if (ltList[0].leftScore != rangeList[rangeList.length - 1].leftScore) {
                            return false;
                        }
                    }


                }
                return true

            }
            ,

            /**新增、修改保存前校验
             * 等级评定子项是否有空值
             * 等级是否存在重复值
             * 判断等级区间是否有重叠，是否连续*/
            beforeSaveValid: function (childList) {
                let res = true
                let valArr = []//用来存找到的对象
                if (childList.length > 0) {
                    for (let i = 0; i < childList.length; i++) {
                        let data = childList[i];
                        //校验有无空值
                        if (validatenull(data.type)) {
                            this.$message.error("等级不能为空");
                            res = false;
                            break;
                        } else {
                            valArr.push(data.type)
                        }
                        if (validatenull(data.linkType)) {
                            this.$message.error("链接类型不能为空");
                            res = false;
                            break;
                        } else {
                            if (validatenull(data.leftScore) || (data.linkType == "range" && validatenull(data.rightScore))) {
                                this.$message.error("分值不能为空");
                                res = false;
                                break;
                            }
                        }
                    }
                }
                if (res) {
                    let nary = valArr.slice().sort();
                    for (let i = 0; i < valArr.length; i++) {
                        if (nary[i] == nary[i + 1]) {
                            this.$message.error("等级不能重复");
                            res = false;
                            break;
                        }
                    }
                }
                if (res && childList.length > 1) {
                    console.log("验证:",this.validLevelType(childList))
                    if(!this.validLevelType(childList)){
                        this.$message.error("您输入的等级评定子项不符合规范");
                        res=false
                    }
                }
                return res
            }
            ,
            /**删除等级评定方案*/
            rowDel(row) {
                let _this = this;
                if (row.status != "1") {
                    _this.$message({
                        title: '失败',
                        message: '启用状态的方案不能被删除、请先停用！',
                        type: 'error',
                        duration: 2000
                    })
                    return
                }
                this.$confirm('是否确认删除(等级评定方案:' + row.name + ')', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return delObj(row.id)
                }).then(() => {
                    _this.getList(this.page)
                    _this.$notify({
                        title: '成功',
                        message: '删除成功',
                        type: 'success',
                        duration: 2000
                    })

                })
            }
            ,
        },

        watch: {
            'form.universal'() {
                if (this.form.universal != '1' && this.form.universal != '') {
                    this.tableOption.column[1].rules[0].required = false;
                    this.tableOption.column[2].rules[0].required = false;
                } else {
                    this.tableOption.column[1].rules[0].required = true;
                    this.tableOption.column[2].rules[0].required = true;
                }
            },
        }


    }
</script>
<style>
    .inputRange {
        width: 120px;
        line-height: 26px;
    }

    .inputNumber {
        width: 248px;
        line-height: 26px;
    }

    .avue-crud .el-input--mini input, .avue-form .el-input--mini input {
        height: 38px !important;
        line-height: 28px !important;
    }
</style>
