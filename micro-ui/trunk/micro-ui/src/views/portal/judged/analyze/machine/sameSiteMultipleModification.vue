<!--
 * @author llc
 * @date Created in 2019/7/10 14:41
 * @description:机修 同一车辆不同车行多次修改分析
 * @modified By:same_site_multiple_modification
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
                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="openDialog(scope.row)">分析
                    </el-button>
                </template>
            </avue-crud>

            <el-dialog :title="analyzeTitle"
                       fullscreen
                       :customClass="dialogStyle"
                       top="0"
                       :visible.sync="analyzeDialog"
                       width="100%"
                       append-to-body>
                <ssm-analyze v-bind:params="analyzeParams"></ssm-analyze>
            </el-dialog>
        </basic-container>
    </div>
</template>

<script>
    import {fetchListPage} from '@/api/portal/judged/analyze/machine/sameSiteMultipleModification'
    import popTree from "@/components/ztree/poptree";
    import ssmAnalyze from './sameSiteMultipleModifiAnalyze';
    import {validatenull} from '@/util/validate'
    export default {
        components: {popTree, ssmAnalyze},
        name: 'sameSiteMultipleModification',
        data() {
            return {
                tableData: [],
                form: {},
                analyzeParams: {
                    type: undefined,
                    carNo:undefined,
                    name:undefined,
                    repairMonth:undefined
                },
                searchForm: {
                    type: undefined,
                    carNo:undefined,
                    name:undefined,
                    repairMonth:""
                },
                dialogStyle:"dialogCss",

                analyzeDialog: false,//分析弹出框 显示 或者隐藏
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                analyzeTitle:'分析',
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
                        { label:"机动车所有人", prop:"name",search: true},
                        { label:"车辆类型", prop:"type",type:"select",search: true,dicUrl: '/portal/dict/getDict/cllx/jx',},
                        { label:"车牌号", prop:"carNo",search: true},
                        { label:"车辆型号", prop:"carType",},
                        { label:"车身颜色", prop:"carColor",dicUrl: '/portal/dict/getDict/csys/jx',},
                        { label:"周期", prop:"repairMonth"},
                        { label:"车行个数", prop:"sum",},
                        {
                            label: "维修日期", prop: "repairTime", type: 'datetimerange',
                            editDisabled: false, viewDisplay: false,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd', search: true, hide: true
                        },
                        { label:"场所地址", prop:"address",hide:true},
                        { label:"批次ID", prop:"batchId",hide:true},
                        { label:"行政区划", prop:"cityCode",hide:true},
                        { label:"管辖派出所", prop:"deptCode",hide:true},
                        { label:"发动机号码", prop:"engineNo",hide:true},
                        { label:"主键ID", prop:"id",hide:true},
                        { label:"机动车所有人身份证号码", prop:"idCard",hide:true},
                        { label:"模型唯一标识", prop:"modelUdId",hide:true},
                        { label:"检测时间", prop:"monitorTime",hide:true},
                        { label:"场所ID", prop:"placeId",hide:true},
                        { label:"场所名称", prop:"placeName",hide:true},

                    ]
                },

            }
        },
        created() {
            this.initDefaultValue()
        },
        methods: {
            initDefaultValue(){
                const date = new Date();
                const date7 = new Date(new Date().getTime() - 720*60*60*1000);
                const  arrayDate=[]
                arrayDate.push("".concat(date7.getFullYear().toString(),"-", (date7.getMonth()+1).toString(),"-",( date7.getDate()).toString()))
                arrayDate.push("".concat(date.getFullYear().toString(),"-", (date.getMonth()+1).toString(),"-",( date.getDate()).toString()))
                this.tableOption.column[7].searchDefault=arrayDate
                this.searchForm.repairMonth=arrayDate.join(",")
                this.queryParams = Object.assign(this.searchForm,{})
            },

            getList(page) {
                this.tableLoading = true
                fetchListPage(Object.assign({
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
                if (!validatenull(params) && !validatenull(params.repairTime) && "undefined" != typeof (params.repairTime)) {
                    this.searchForm.repairMonth = params.repairTime.join(",");
                    this.analyzeParams.repairMonth = this.searchForm.repairMonth
                    params.repairTime = params.repairTime.join(",");
                }
                this.queryParams = Object.assign(this.searchForm, this.filterForm(params))

                this.getList(this.page)
            },
            /**
             * 清除全局检索条件
             */
            handleSearchReset() {
                this.queryParams = [];
                this.$refs["crud"].searchForm.searchDate=[]
                this.searchForm = {
                    searchTime:''
                }
            },

            /**
             * 刷新回调
             */
            refreshChange() {
                this.getList(this.page)
            },

            openDialog(row) {
                this.analyzeTitle= row.carNo
                this.analyzeParams.searchTime= row.repairMonth
                this.analyzeParams.type=row.type
                this.analyzeParams.name=row.name
                this.analyzeParams.carNo=row.carNo
                this.analyzeDialog = true
            }
        }
    }
</script>

<style>

</style>

