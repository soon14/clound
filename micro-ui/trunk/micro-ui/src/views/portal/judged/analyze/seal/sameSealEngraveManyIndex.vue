<!--
 * @author wengshij
 * @date Created in 2019/7/2 14:41
 * @description:刻印章 同一单印章被多次刻制
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
                    @size-change="sizeChange"
                    @refresh-change="refreshChange"
                    @search-change="searchChange"
                    @search-reset="handleSearchReset"
            >
                <template slot="num" slot-scope="scope">
                    <a href="#">{{scope.row.num}}</a>
                    <!-- <el-tag>
                         <span v-text="scope.row.num"></span>
                     </el-tag>-->
                </template>
                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="openDialog(scope.row)">详情
                    </el-button>
                </template>
                <template slot="search">
                    <el-form-item label="刻制次数">
                        <div class=" el-range-editor el-input__inner" style="border:none!important; ">
                            <el-select v-model="searchTempForm.operator" style="width: 140px!important;"
                                       type="select"
                                       size="small">
                                <el-option value="eq" label="等于"></el-option>
                                <el-option value="lt" label="小于等于"></el-option>
                                <el-option value="gt" label="大于等于"></el-option>
                            </el-select>
                            &nbsp;
                            <el-input onkeyup="value=value.replace(/[^\d]/g,'')"
                                      placeholder="默认小于等于-维修次数"
                                      v-model="searchTempForm.num"
                                      type="input"
                                      size="small"
                                      clearable>
                            </el-input>
                        </div>
                    </el-form-item>

                </template>
            </avue-crud>

            <el-dialog :title="analyzeTitle"
                       :customClass="dialogStyle"
                       :visible.sync="analyzeDialog"
                       v-dialogDrag
                       append-to-body>
                <same-seal-engrave-many-trajectory v-bind:params="analyzeParams"></same-seal-engrave-many-trajectory>
            </el-dialog>
        </basic-container>
    </div>
</template>

<script>
    import {analyPage} from '@/api/portal/judged/analyze/seal/sameSealEngraveMany'
    import popTree from "@/components/ztree/poptree"
    import {validatenull} from '@/util/validate'
    import sameSealEngraveManyTrajectory from '@/views/portal/judged/analyze/seal/sameSealEngraveManyTrajectory';

    export default {
        components: {popTree, sameSealEngraveManyTrajectory},
        name: 'repairRecordException',
        data() {
            return {
                tableData: [],
                form: {},
                isShow: true,//是否显示搜索框
                analyzeParams: {
                    useUnitId: undefined,
                    sealType: undefined,
                    engraveMonth: undefined,
                    useUnitName: undefined,
                    sealName: undefined,
                },
                dialogStyle: "dialogCss",
                detailRow: {},
                analyzeDialog: false,//分析弹出框 显示 或者隐藏
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },


                analyzeTitle: '详情',
                searchTempForm: {
                    num: '3',
                    operator: 'gt',
                    engraveMonth: ""
                },
                queryParams: [],
                tableLoading: false,
                tableOption: {
                    dialogHeight: '100%',
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    viewBtn: false,
                    menuWidth: '100',
                    editBtn: false,
                    delBtn: false,
                    submitBtn: true,
                    emptyBtn: true,
                    column: [
                        {label: "印章所属单位", prop: "useUnitName", overHidden: true, search: true},
                        {
                            label: "单位类型", prop: "useUnitType", dicUrl: '/portal/dict/getDict/unit_type/kyz',
                            dicMethod: 'get',
                            dicQuery: {},
                        },
                        {label: "单位法人", prop: "useUnitLegal",},
                        {label: "证件号码", prop: "useUnitIdCard",},
                        {label: "联系方式", prop: "useUnitPhone",},
                        {
                            label: "印章名称", prop: "sealName",
                            search: true,
                        },
                        {
                            label: "印章类型", prop: "sealType",
                            dicUrl: '/portal/dict/getDict/seal_type/kyz',
                            dicMethod: 'get',
                            dicQuery: {},
                            hide: true,
                            type: 'select',
                            searchFilterable: true
                        },
                        {label: "刻制次数", prop: "num", slot: true},
                        {label: "刻制时间", prop: "engraveMonth"},
                        {
                            label: "刻制时间", prop: "engraveDate", type: 'datetimerange',
                            editDisabled: false, viewDisplay: false,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd', search: true, hide: true
                        },
                        {label: "印章使用单位地址", prop: "useUnitAddress", hide: true},
                        {label: "批次ID", prop: "batchId", hide: true},
                        {label: "场所行政区划代码", prop: "cityCode", hide: true},
                        {label: "刻章单位管辖派出所编码", prop: "deptCode", hide: true},
                        {label: "刻章单位管辖派出所名称", prop: "deptName", hide: true},
                        {label: "刻制时间", prop: "engraveTime", hide: true},
                        {label: "主键ID", prop: "id", hide: true},
                        {label: "模型标识", prop: "modelUdId", hide: true},
                        {label: "检测时间", prop: "monitorTime", hide: true},
                        {label: "场所地址", prop: "placeAddress", hide: true},
                        {label: "场所单位ID", prop: "placeId", hide: true},
                        {label: "场所单位名称", prop: "placeName", hide: true},

                        {label: "场所类型", prop: "placeType", hide: true},

                        {label: "印章使用单位ID", prop: "useUnitId", hide: true},


                    ]
                },

            }
        },
        created() {
            this.queryParams = Object.assign(this.searchTempForm, {})

        },
        methods: {


            getList(page) {
                this.tableLoading = true
                analyPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, this.queryParams)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            sizeChange(size) {
                this.page.pageSize = size;
            },
            searchChange(params) {
                if (!validatenull(params) && !validatenull(params.engraveDate) && "undefined" != typeof (params.engraveDate)) {
                    this.searchTempForm.engraveMonth = params.engraveDate.join(",");
                    this.analyzeParams.engraveMonth = this.searchTempForm.engraveMonth
                    params.engraveDate = params.engraveDate.join(",");
                }
                this.queryParams = Object.assign( this.filterForm(params),this.searchTempForm)


                this.getList(this.page)
            },
            /**
             * 清除全局检索条件
             */
            handleSearchReset() {
                this.queryParams = [];
                this.$refs["crud"].searchTempForm.searchDate = []
                this.searchTempForm = {
                    num: '',
                    operator: 'gt',
                    searchTime: ''
                }
            },

            /**
             * 刷新回调
             */
            refreshChange() {
                this.getList(this.page)
            },

            openDialog(row) {
                this.analyzeParams.useUnitName = row.useUnitName
                this.analyzeParams.sealName = row.sealName
                this.analyzeParams.useUnitId = row.useUnitId
                this.analyzeParams.sealType = row.sealType
                if (validatenull(this.analyzeParams.engraveMonth)) {
                    this.analyzeParams.engraveMonth = row.engraveMonth
                } else if (this.analyzeParams.engraveMonth.indexOf(",") == -1) {
                    this.analyzeParams.engraveMonth = row.engraveMonth
                }

                this.analyzeDialog = true
            }
        }
    }
</script>

<style>
    .poptree > .el-button {
        padding: 0px;
    }

</style>

