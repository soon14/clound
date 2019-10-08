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
                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="openDialog(scope.row)">详情
                    </el-button>
                </template>
                <template slot="search">
                    <el-form-item label="管辖单位">
                        <popTree
                                v-model="searchForm.deptName"
                                :isShow="isShow"
                                :treeSeach="treeSeach"
                                :treeNode="treeNode"
                                :checkBox="checkBox"
                                :async="async"
                                :asyncCall="asyncCall"
                                :popVisible="popVisible"
                                :beforeClick="beforeClick"
                                @submit="submitSearchTreeNode"
                        >
                        </popTree>
                    </el-form-item>
                </template>
            </avue-crud>

            <el-dialog :title="analyzeTitle"
                       :visible.sync="analyzeDialog"
                       fullscreen
                       append-to-body
            >
                <person-truth-report-detail v-bind:params="analyzeParams"></person-truth-report-detail>
            </el-dialog>
        </basic-container>
    </div>
</template>

<script>
    import {ylcsDict} from "@/const/crud/portal/archive/ylcs/ylcsDict";
    import {analyzePage} from '@/api/portal/judged/analyze/entertainment/personTruthReport'
    import {fetchTree} from "@/api/admin/dept";
    import popTree from "@/components/ztree/poptree";
    import {validatenull} from '@/util/validate'
    import personTruthReportDetail from '@/views/portal/judged/analyze/entertainment/personTruthReportDetail';

    export default {
        components: {popTree, personTruthReportDetail},
        name: 'personTruthReport',
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
                    clockMonth:undefined
                },
                detailRow:{},
                analyzeTitle:'详情',
                analyzeDialog: false,//详情弹出框 显示 或者隐藏
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                searchForm: {
                    deptCode: '',
                    deptName: '',
                    clockMonth:'',
                },
                queryParams: [],
                tableLoading: false,
                tableOption: {
                    dialogWidth:'100%',
                    dialogHeight:'100%',
                    dialogFullscreen:true,
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
                        {label: "场所名称", prop: "name", search: true, overHidden: true},
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
                        {label: "管辖派出所", prop: "deptName",hide: true},
                        {label: "经营地址", prop: "address", overHidden: true},
                        {label: "营业状态", prop: "type",dicData:ylcsDict.csbaState,},
                        {
                            label: "最新打卡时间", prop: "clockMonth",
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                        },
                        {
                            label: "最新打卡时间", prop: "searchTime",
                            search: true, type: 'daterange',
                            editDisabled: false, viewDisplay: false,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',hide:true
                        },
                        {label: "实际从业人数", prop: "actuleNum"},
                        {label: "打卡异常人数", prop: "falseNum"},
                        {label: "检测时间", prop: "monitorTime", hide: true},
                        {label: "批次ID", prop: "batchId", hide: true},
                        {label: "主键ID", prop: "id", hide: true},
                        {label: "模型标识", prop: "modelUdId", hide: true},
                        {label: "场所ID", prop: "placeId", hide: true},
                    ]
                },

            }
        },
        created() {
            fetchTree().then(data => {
                let zNodesData = data.data.data;
                this.treeNode = zNodesData;
            }).catch(() => {
            });
            this.queryParams = Object.assign(this.searchForm, {})
        },
        methods: {

            submitSearchTreeNode(data){
                this.searchForm.deptCode = data.code;
                this.searchForm.deptName = data.name;
            },
            treeSeach() {
                let parmes={name:this.searchForm.deptName,code:this.searchForm.deptCode};
                fetchTree(parmes).then(data => {
                    let zNodesData = data.data.data;
                    if(zNodesData!=null){
                        this.treeNode = zNodesData;
                    }else{
                        this.treeNode = [];
                    }
                }).catch(() => {
                });
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

                if (!validatenull(params) && !validatenull(params.searchTime) && "undefined" != typeof (params.searchTime)) {
                    this.searchForm.clockMonth = params.searchTime.join(",");
                    this.analyzeParams.clockMonth = this.searchForm.clockMonth
                    params.searchTime = params.searchTime.join(",");
                }
                this.queryParams = Object.assign(this.filterForm(params),this.searchForm)
                this.getList(this.page)
            },
            /**
             * 清除全局检索条件
             */
            handleSearchReset() {
                this.queryParams = [];
                this.$refs["crud"].searchForm.searchTime=[]
                this.analyzeParams.clockMonth=''
                this.searchForm = {
                    deptName:'',
                    deptCode: '',
                    clockMonth:''
                },
                this.getList(this.page)
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
                this.analyzeTitle= row.name
                this.analyzeParams.placeId = row.placeId
                if (validatenull(this.analyzeParams.clockMonth)) {
                    this.analyzeParams.clockMonth = row.clockMonth
                } else if (this.analyzeParams.clockMonth.indexOf(",") == -1) {
                    this.analyzeParams.clockMonth = row.clockMonth
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

