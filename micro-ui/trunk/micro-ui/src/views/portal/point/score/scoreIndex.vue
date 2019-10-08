<!--
 * @author wengshij
 * @date Created in 2019/7/26 14:41
 * @description:积分排名信息
 * @modified By:
 -->
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
                    @search-change="searchChange"
                    @refresh-change="refreshChange"
                    @search-reset="handleSearchReset"
            >
                <template slot-scope="scope" slot="placeName">
                    <el-tag @click="showHistoryScoreDialog(scope.row)"
                            class="el-button el-button--text el-button--small">{{scope.row.placeName}}
                    </el-tag>
                </template>

                <template slot="menu" slot-scope="scope">

                    <el-button size="mini"
                               type="text"
                               icon="el-icon-view"
                               @click="showDetail(scope.row)"> 明细
                    </el-button>

                    <!-- <el-button size="mini"
                                type="text"

                                icon="el-icon-view"
                                @click="showHistoryScoreDialog(scope.row)"> 历史排名
                     </el-button>-->

                    <el-button
                            v-if="point_passive_score && scope.row.cycleType=='0'"
                            size="mini"
                            type="text"
                            icon="el-icon-edit"
                            @click="passiveDetail(scope.row)"> 手动评分
                    </el-button>

                </template>
            </avue-crud>
        </basic-container>

        <el-dialog :title="detailTitle"
                   :visible.sync="viewFlag"
                   width="80%"
                   v-dialogDrag
                   append-to-body
                   @close="closeDialog">
            <score-detail v-bind:detail-row="detailRow" v-on:refresh="refreshChild"></score-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="viewFlag=false">关 闭
                </el-button>
            </div>

        </el-dialog>


        <el-dialog :title="passiveTitle"
                   :visible.sync="passiveFlag"
                   width="80%"
                   v-dialogDrag
                   append-to-body
                   @close="closeDialog">
            <passive-detail v-bind:detail-row="detailRow" v-on:refresh="refreshPassive"></passive-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="passiveFlag=false">关 闭
                </el-button>
            </div>

        </el-dialog>

        <el-dialog :title="historyScoreTitle"
                   :visible.sync="historyScoreFlag"
                   fullscreen
                   v-dialogDrag
                   append-to-body
                   @close="closeDialog">
            <history-score v-bind:detail-row="detailRow"></history-score>
        </el-dialog>


    </div>
</template>

<script>

    import {fetchList} from '@/api/portal/point/score/pointScore'
    import scoreDetail from '@/views/portal/point/score/scoreDetail'
    import passiveDetail from '@/views/portal/point/score/passiveDetail'
    import {deepClone} from '@/util/util'
    import {mapGetters} from 'vuex'
    import historyScore from '@/views/portal/point/score/historyScore'


    export default {
        components: {
            scoreDetail,
            passiveDetail,
            historyScore
        },
        data() {
            return {
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                point_passive_score: false,
                historyScoreFlag: false,
                passiveFlag: false,
                passiveTitle: '手动积分',
                historyScoreTitle: '历史积分',
                form: {sourceType: undefined},
                detailRow: {},
                queryParams: [],
                viewFlag: false,
                editFlag: false,
                tableLoading: false,
                detailTitle: '明细',
                tableData: [],
                refreshFlag: false,
                tableOption: {
                    dialogFullscreen: true,
                    menuWidth: '200',
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    refreshBtn: true,
                    filterBtn: false,
                    columnBtn: true,
                    searchBtn: true,
                    addBtn: false,
                    editBtn: false,
                    delBtn: false,
                    viewBtn: false,
                    card: true,
                    column: [
                        {
                            label: '场所名称',
                            prop: 'placeName',
                            search: true,
                            slot: true,
                            overHidden: true,
                        }, {
                            label: '行政区划',
                            prop: 'cityCode',
                            search: true,
                            dicUrl: '/portal/area/level/tree',
                            type: 'tree',
                            props: {
                                label: "name",
                                value: 'code'
                            },
                            overHidden: true,
                        }, {
                            label: '管辖单位',
                            prop: 'deptName',
                            overHidden: true
                        }, {
                            label: '行业类型',
                            prop: 'placeType',
                            search: true,
                            searchFilterable: true,
                            overHidden: true,
                            type: 'tree',
                            dicUrl: '/admin/dict/type/HYLX_DL',
                            dicMethod: 'get',
                        }, {
                            label: '场所地址',
                            prop: 'placeAddress',
                            overHidden: true,
                        }, {
                            label: '所属系统',
                            prop: 'sourceType',
                            search: true,
                            type: 'select',
                            searchFilterable: true,
                            dicUrl: '/admin/dict/type/unit_source_type',
                            dicMethod: 'get',
                            dicQuery: {},
                        }, {
                            label: '总分',
                            prop: 'totalScore',
                        }, {
                            label: "等级", prop: "levelType",
                            search: true,
                            type: 'select',
                            searchFilterable: true,
                            dicUrl: '/admin/dict/type/jfdj',
                            dicMethod: 'get',
                            dicQuery: {},
                        }, {
                            label: "评分周期", prop: "cycleName",
                        }, {
                            label: "周期", prop: "cycleType",
                            search: true,
                            type: 'select',
                            searchFilterable: true,
                            dicUrl: '/admin/dict/type/point_score_type',
                            dicMethod: 'get',
                            hide: true,
                            searchDefault: '0',
                            searchClearable: false
                        }, {
                            label: "时间周期", prop: "cycleTime",
                            hide: true,
                            type: 'month',
                            search: true,
                            format: 'yyyy-MM',
                            valueFormat: 'yyyy-MM',
                        }
                    ]

                },
            }
        },
        created() {
            this.form.sourceType = this.$route.query.sourceType
            this.tableOption.column[5].searchDefault = this.$route.query.sourceType
            this.queryParams = this.form

        },
        computed: {
            ...mapGetters(['permissions'])
        },
        mounted() {
            this.point_passive_score = this.permissions['point_passive_score'] || false

        },
        methods: {

            /**
             * 清除全局检索条件
             */
            handleSearchReset() {
                this.$refs["crud"].searchForm.sourceType = undefined
                this.queryParams = [];
            },
            /**
             * 刷新回调
             */
            refreshChange() {
                this.getList(this.page)
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
            showDetail(row) {
                this.refreshFlag = false
                this.viewFlag = true
                this.detailTitle = row.placeName
                this.detailRow = deepClone(row)
            },
            showHistoryScoreDialog(row) {
                this.historyScoreFlag = true
                this.historyScoreTitle = "(" + row.placeName + '）历史排名'
                this.detailRow = deepClone(row)

            },
            passiveDetail(row) {
                this.refreshFlag = false
                this.passiveFlag = true
                this.passiveTitle = row.placeName
                this.detailRow = deepClone(row)
            },
            refreshChild(data) {
                this.refreshFlag = data
            },
            refreshPassive(data) {
                this.passiveFlag = data
                this.getList(this.page)
            },
            closeDialog() {
                this.detailRow = {}
                if (this.refreshFlag) {
                    this.getList(this.page)
                }

            }


        }
    }
</script>
<style lang="scss">
    .execution {
        .el-form-item__content {
            margin-right: 30px;
        }
    }

</style>

