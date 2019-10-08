<!--
 * @author wengshij
 * @date Created in 2019/7/26 14:41
 * @description:积分排名明细信息
 * @modified By:
 -->
<template>
    <div class="execution">
        <basic-container class="integralDetail">
            <div class="main">
                <div class="from-box">
                    <h3>场所信息</h3>
                    <el-form class="form-main">
                        <el-row class="from-row"><!--行-->
                            <el-col :span="12">
                                <el-form-item label="场所名称:" class="place">
                                    <span v-text="detail.placeName"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="场所地址:">
                                    <span v-text="detail.placeAddress"></span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row class="from-row">
                            <el-col :span="12">
                                <el-form-item label="行业类型:">
                                    <span v-text="findLabelByValue(dictPlaceTypeData,detail.placeType)"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="评分周期:">
                                    <span v-text="detail.cycleName"></span>
                                </el-form-item>
                            </el-col>

                        </el-row>
                        <el-row class="from-row">
                            <el-col :span="12">
                                <el-form-item label="行政区划:">
                                    <span v-text="findLabelByValue(dictCityData,detail.cityCode)"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="管辖派出所:">
                                    <span v-text="detail.deptName"></span>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row class="from-row">
                            <el-col :span="12">
                                <el-form-item label="当前积分:">
                                    <span v-text="detail.totalScore"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="当前等级:">
                                    <span v-text="findLabelByValue(dictLevelTypeData,detail.levelType) "></span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </div>
                <div class="table-box">
                    <avue-crud
                            ref="crud"
                            v-model="form"
                            :page="page"
                            :data="tableData"
                            :option="option"
                            :table-loading="tableLoading"
                            @search-change="searchChange"
                            @refresh-change="refreshChange"
                            @search-reset="handleSearchReset">
                        <template slot="placeName" slot-scope="scope">
                            <span v-text="scope.row.pointIndex==null?'其他':scope.row.pointIndex.name"></span>
                        </template>
                        <template slot="pointType" slot-scope="scope">
                            <span v-text="scope.row.pointIndex==null?'其他':findLabelByValue(dictPointTypeData,scope.row.pointIndex.type) "></span>
                        </template>
                        <template slot="detail" slot-scope="scope">
                            <span v-text="scope.row.pointIndex==null?'无':scope.row.pointIndex.remark"></span>
                        </template>
                        <template slot="menu" slot-scope="scope">
                            <el-button size="mini"
                                       type="text"
                                       icon="el-icon-brush"
                                       @click="withDraw(scope.row)"
                                       v-if="scope.row.delFlag == '0' && detail.cycleType == '0' "> 撤回
                            </el-button>
                            <el-button size="mini"
                                       type="text"
                                       icon="el-icon-view"
                                       @click="detailScore(scope.row)"> 明细
                            </el-button>
                        </template>
                    </avue-crud>
                </div>
            </div>
        </basic-container>
        <el-dialog :title="detailTitle"
                   :visible.sync="viewFlag"
                   v-dialogDrag
                   append-to-body>
            <index-detail v-bind:detail-row="scoreDetailRow"></index-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="viewFlag=false">关 闭</el-button>
            </div>

        </el-dialog>
    </div>
</template>

<script>
    import {countPage} from '@/api/portal/point/detail/indexDetail'
    import {findDictArea} from "@/api/portal/archive/common/query"
    import {fetchDictTree} from "@/api/admin/dict"
    import {rollBackScore, getPlaceById} from '@/api/portal/point/score/pointScore'
    import {validatenull} from '@/util/validate'
    import indexDetail from '@/views/portal/point/score/indexDetail'

    export default {
        name: "scoreDetail",
        components: {
            indexDetail,
        },
        props: {
            detailRow: {
                type: Object,
            },

        },

        data() {
            return {
                viewFlag: false,
                detailTitle: '明细',
                dictCityData: [],
                dictPlaceTypeData: [],
                dictLevelTypeData: [],
                dictPointTypeData: [],
                scoreDetailRow: {
                    delFlag: undefined,
                    placeId: undefined,
                    cycleId: undefined,
                    indexUdId: undefined,
                    indexVersion: undefined,
                    sourceType: undefined,
                    indexName: undefined,
                    pointType: undefined
                },
                detail: {},
                tableData: [],
                queryParams: [],
                defaultParams: {
                    placeId: undefined,
                    cycleId: undefined,
                    sourceType: undefined,
                },
                tableLoading: false,
                form: {},
                option: {
                    dialogFullscreen: true,
                    menuWidth: '200',
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    refreshBtn: false,
                    filterBtn: false,
                    columnBtn: false,
                    searchBtn: false,
                    addBtn: false,
                    editBtn: false,
                    delBtn: false,
                    viewBtn: false,
                    card: true,
                    column: [
                        {
                            label: '指标项',
                            prop: 'placeName',
                            overHidden: true,
                            search: true,
                            slot: true
                        }, {
                            label: '指标类型',
                            prop: 'pointType',
                            dicUrl: '/admin/dict/type/point_index_type',
                            dicMethod: 'get',
                            type: 'select',
                            search: true,
                            dicQuery: {},
                            slot: true,
                            searchFilterable: true
                        }, {
                            label: '评分规则',
                            prop: 'detail',
                            overHidden: true,
                            slot: true
                        }, {
                            label: '积分时间',
                            prop: 'bonusTime',
                            overHidden: true,
                        }, {
                            label: '次数',
                            prop: 'num',
                        }, {
                            label: '累计',
                            prop: 'totalScore',
                        },
                        {
                            label: '是否撤回',
                            prop: 'delFlag',
                            hide: true,
                            search: true,
                            type: 'select',
                            dicUrl: '/admin/dict/type/index_detail_type',
                            dicMethod: 'get',
                            searchDefault: '0',
                            searchFilterable: true,
                            searchClearable: false
                        },
                    ]
                },
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 5, // 每页显示多少条
                    pageSizes: [5, 10, 20, 30, 40],
                }
            }
        },
        created() {
            this.initParams()
            findDictArea().then(response => {
                this.dictCityData = response.data || []
            })
            fetchDictTree({type: 'HYLX'}).then(response => {
                this.dictPlaceTypeData = response.data.data || []
            })
            fetchDictTree({type: 'jfdj'}).then(response => {
                this.dictLevelTypeData = response.data.data || []
            })
            fetchDictTree({type: 'point_index_type'}).then(response => {
                this.dictPointTypeData = response.data.data || []
            })


        },
        methods: {
            detailScore(row) {
                this.detailTitle = this.detail.placeName
                this.scoreDetailRow.delFlag = row.delFlag
                this.scoreDetailRow.indexUdId = row.indexUdId
                this.scoreDetailRow.indexVersion = row.indexVersion
                this.scoreDetailRow.pointType = row.pointIndex == null ? '' : row.pointIndex.type
                this.scoreDetailRow.indexName = row.pointIndex == null ? '' : row.pointIndex.name
                this.scoreDetailRow.cycleId = this.defaultParams.cycleId
                this.scoreDetailRow.placeId = this.defaultParams.placeId
                this.scoreDetailRow.sourceType = this.defaultParams.sourceType
                this.viewFlag = true
            },
            initParams() {
                this.detail = this.detailRow
                this.defaultParams.cycleId = this.detailRow.cycleId
                this.defaultParams.placeId = this.detailRow.placeId
                this.defaultParams.sourceType = this.detailRow.sourceType
                this.handleSearchReset()
                this.getList(this.page)
            },
            getPlace(placeId, score) {
                getPlaceById(placeId).then(data => {
                    let row = data.data.data;
                    if (!validatenull(row)) {
                        this.detail.totalScore = row.totalScore
                        this.detail.levelType = row.levelType
                    } else {
                        this.detail.totalScore = score
                    }

                })
            },

            withDraw(row) {
                let _this = this
                let rowData = {
                    cycleId: row.cycleId,
                    indexUdId: row.indexUdId,
                    indexVersion: row.indexVersion,
                    pointType: row.pointType,
                    totalScore: (this.detail.totalScore - row.totalScore),
                    placeType: this.detail.placeType,
                    cityCode: this.detail.cityCode,
                    placeId: this.defaultParams.placeId,
                    sourceType: this.defaultParams.sourceType
                }
                this.$confirm("确定要撤回该类评分吗？该操作不可逆，请谨慎操作！", '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    _this.tableLoading = true
                    return rollBackScore(Object.assign(rowData, {}))
                }).then(data => {
                    let title = data.data.data;
                    if (data.data.code === 0) {
                        this.$notify({
                            title: "成功",
                            message: title || "积分撤回成功",
                            type: "success"
                        });
                    } else {
                        this.$notify.error({
                            title: '错误',
                            message: title || '积分撤回失败、请刷新页面、稍后重试！'
                        });

                    }
                    this.refreshChange()
                    this.getPlace(this.defaultParams.placeId, rowData.totalScore)
                    this.$emit("refresh", true);
                    _this.tableLoading = false
                }).catch(function (err) {
                    _this.tableLoading = false
                })
            },
            handleSearchReset() {
                this.queryParams = [];
            },
            refreshChange() {
                this.getList(this.page)
            },
            searchChange(form) {
                this.queryParams = this.filterForm(form)
                this.getList(this.page)
            },
            getList(page) {
                this.tableLoading = true
                countPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, Object.assign(this.queryParams, this.defaultParams))).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                }).catch(function (err) {
                    this.tableLoading = false
                })
            },
        },
        watch: {
            detailRow: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.initParams()
                    this.$refs["crud"].searchForm.placeName = ''
                    this.$refs["crud"].searchForm.pointType = ''
                    this.$refs["crud"].searchForm.delFlag = '0'

                },
                deep: true
            },
        },
    }
</script>

<style lang="scss">
    .integralDetail {
        .el-card {
        }

        .main {
            width: 100%;
            margin: auto;
        }

        .el-form-item {
            margin-bottom: 0px !important;
        }

        .el-col {
            margin-bottom: 0px !important;
        }

        .from-box {
            padding: 10px;
            overflow: hidden;
            box-shadow: 0px 1px 8px 0px #979292;
            margin: auto;

            h3 {
                padding: 0;
                padding-left: 20px;
                margin: 0;
                height: 40px;
                line-height: 45px;
                border-bottom: 1px solid #ccc;
            }

            .from-row {
                padding-left: 50px;
            }

            .place {
                color: #0a15cc;
            }

            .form-main {
                overflow: hidden;
            }
        }

        .table-box {
            margin-top: 10px;
        }

    }
</style>
