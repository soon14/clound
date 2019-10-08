<!--
 * @author wengshij
 * @date Created in 2019/7/2 14:41
 * @description:机修 维修记录异常分析首页
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
                    <el-tag @click="vutDialog(scope.row)">
                        <span v-text="scope.row.num"></span>
                    </el-tag>
                </template>
                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="openDialog(scope.row)">分析
                    </el-button>
                </template>
                <template slot="search">
                    <el-form-item label="维修次数">
                        <div class=" el-range-editor el-input__inner" style="border:none!important; ">
                            <el-select v-model="searchForm.operator" style="width: 140px!important;"
                                       type="select"
                                       size="small">
                                <el-option value="eq" label="等于"></el-option>
                                <el-option value="lt" label="小于等于"></el-option>
                                <el-option value="gt" label="大于等于"></el-option>
                            </el-select>
                            &nbsp;
                            <el-input onkeyup="value=value.replace(/[^\d]/g,'')"
                                      placeholder="默认小于等于-维修次数"
                                      v-model="searchForm.num"
                                      type="input"
                                      size="small"
                                      clearable>
                            </el-input>
                        </div>
                    </el-form-item>
                    <el-form-item label="管辖单位" class="tree-item">
                        <div>
                            <tree-select
                                    v-model="searchForm.deptCode"
                                    :default-expand-level="1"
                                    :normalizer="normalizer"
                                    :load-options="loadOptions"
                                    noResultsText="暂无数据"
                                    style="width: 300px;"
                                    :options="treeNode"
                                    zIndex="10099"
                                    noChildrenText="暂无子节点"
                                    placeholder="请选择"
                            />
                        </div>

                    </el-form-item>
                </template>
            </avue-crud>

            <el-dialog :title="analyzeTitle"
                       fullscreen
                       :customClass="dialogStyle"
                       top="0"
                       :visible.sync="analyzeDialog"
                       width="100%"
                       append-to-body>
                <rre-analyze v-bind:params="analyzeParams"></rre-analyze>
            </el-dialog>

            <el-dialog title="维修信息"
                       :customClass="dialogStyle"
                       :visible.sync="undertakeVehicleTrajectoryDialog"
                       v-dialogDrag
                       append-to-body

            >
                <undertake-vehicle-trajectory v-bind:detailRow="detailRow"></undertake-vehicle-trajectory>

            </el-dialog>
        </basic-container>
    </div>
</template>

<script>
    import {analyzePage} from '@/api/portal/judged/analyze/machine/repairRecordException'
    import {fetchTree} from "@/api/admin/dept";
    import popTree from "@/components/ztree/poptree";
    import {validatenull} from '@/util/validate'
    import rreAnalyze from '@/views/portal/judged/analyze/machine/repairRecordExceptionAnalyze';
    import undertakeVehicleTrajectory from '@/views/portal/judged/analyze/machine/undertakeVehicleTrajectory';

    export default {
        components: {popTree, rreAnalyze, undertakeVehicleTrajectory},
        name: 'repairRecordException',
        data() {
            return {
                tableData: [],
                form: {},
                isShow: true,//是否显示搜索框
                treeNode: [],
                checkBox: false, //是否开启复选框
                hiddenLine: false,//是否连线
                async: true,//是否异步加载
                popVisible: false,
                analyzeParams: {
                    placeId: undefined,
                    searchTime: undefined
                },
                dialogStyle: "dialogCss",
                undertakeVehicleTrajectoryDialog: false,
                detailRow: {},
                analyzeDialog: false,//分析弹出框 显示 或者隐藏
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                analyzeTitle: '分析',
                searchForm: {
                    deptCode: undefined,
                    deptName: '',
                    num: '3',
                    operator: 'lt',
                    searchTime: ""
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
                        {label: "场所名称", prop: "placeName", search: true, overHidden: true},
                        {
                            label: "营业状态", prop: "type",
                            dicUrl: '/portal/dict/getDict/qyzt/jx',
                            dicMethod: 'get',
                            dicQuery: {},
                        },
                        {
                            label: "行政区划", prop: "cityCode",
                            dicUrl: '/portal/area/areaData',
                            dicMethod: 'get',
                            expandLevel: 1,
                            dicQuery: {},
                            props: {
                                label: "name",
                                value: 'code'
                            },
                        },

                        {label: "管辖派出所", prop: "deptCode", hide: true},
                        {label: "管辖派出所", prop: "deptName",},
                        {label: "维修次数", prop: "num",/*slot:true*/},
                        {
                            label: "维修时间", prop: "searchDate",
                            search: true, type: 'daterange',
                            editDisabled: false, viewDisplay: false,
                            hide: true,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                        },
                        {
                            label: "维修时间", prop: "searchTime",
                            search: false, type: 'daterange',
                            editDisabled: false, viewDisplay: false,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                        },

                        {label: "统计时间", prop: "dayDate", hide: true},
                        {label: "地址", prop: "address", overHidden: true},
                        {label: "检测时间", prop: "monitorTime", hide: true},
                        {label: "批次ID", prop: "batchId", hide: true},
                        {label: "主键ID", prop: "id", hide: true},
                        {label: "模型标识", prop: "modelUdId", hide: true},
                    ]
                },

            }
        },
        created() {
            this.initDefaultValue()
            fetchTree().then(data => {
                let zNodesData = data.data.data;
                this.treeNode = zNodesData;
            }).catch(() => {
            });

        },
        methods: {

            initDefaultValue() {
                const date = new Date();
                const date7 = new Date(new Date().getTime() - 144 * 60 * 60 * 1000);

                const arrayDate = []
                arrayDate.push("".concat(date7.getFullYear().toString(), "-", (date7.getMonth() + 1).toString(), "-", (date7.getDate()).toString()))
                arrayDate.push("".concat(date.getFullYear().toString(), "-", (date.getMonth() + 1).toString(), "-", (date.getDate()).toString()))
                this.tableOption.column[6].searchDefault = arrayDate
                this.searchForm.searchTime = arrayDate.join(",")
                this.queryParams = Object.assign(this.searchForm, {})


            },
            vutDialog(row) {
                this.undertakeVehicleTrajectoryDialog = true
                this.detailRow = row
            },

            loadOptions({action, parentNode, callback}) {

                let children = parentNode.children || []
                if (action === "LOAD_CHILDREN_OPTIONS" && children.length <= 0) {

                    fetchTree({deptId: parentNode.id, name: parentNode.name}).then(data => {
                        console.log("nodeData", data.data.data)
                        let nodeData = data.data.data || []
                        if (nodeData.length <= 0) {
                            parentNode.hasChildren = "false"
                        }
                        parentNode.children = nodeData;
                        callback();
                    }).catch(() => {
                    });


                }
            },
            normalizer(node) {
                let children = node.children
                let hasChildren = node.hasChildren
                children = ((children == null || children.length <= 0) && hasChildren == "true") ? null : children
                return {
                    id: node.code,
                    label: node.name,
                    children: children,
                }
            },

            /*异步回调函数 data 当前节点数据 call 回调函数*/
            asyncCall(data, call) {
                let pasmes = {deptId: data.id, name: data.name};
                fetchTree(pasmes).then(data => {
                    let zNodesData = data.data.data;
                    call(zNodesData);
                }).catch(() => {
                });
            },
            getList(page) {
                this.tableLoading = true
                analyzePage(Object.assign({
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

                if (!validatenull(params) && !validatenull(params.searchDate) && "undefined" != typeof (params.searchDate)) {
                    this.searchForm.searchTime = params.searchDate.join(",");
                    params.searchDate = params.searchDate.join(",");
                }
                this.queryParams = Object.assign(this.filterForm(params), this.searchForm)

                this.getList(this.page)
            },
            /**
             * 清除全局检索条件
             */
            handleSearchReset() {
                this.queryParams = [];
                this.$refs["crud"].searchForm.searchDate = []

                this.searchForm = {
                    deptCode: undefined,
                    deptName: '',
                    num: '',
                    operator: 'lt',
                    searchTime: ''
                }
            },

            /**
             * 刷新回调
             */
            refreshChange() {
                this.getList(this.page)
            },
            beforeClick: function () {
                return true;
            },
            openDialog(row) {
                this.analyzeTitle = row.placeName
                this.analyzeParams.placeId = row.placeId
                this.analyzeParams.searchTime = row.searchTime
                this.analyzeDialog = true
            }
        }
    }
</script>

<style>
    .poptree > .el-button {
        padding: 0px;
    }
    .tree-item .el-form-item__content{
        line-height: 32px;
    }

</style>

