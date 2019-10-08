<!--
 * @author wengshij
 * @date Created in 2019/7/2 14:41
 * @description:机修 维修记录异常分析详情（条形图 及轨迹信息）
 * @modified By:
 -->
<template>
    <div class="execution">
        <basic-container class="containerPath">
            <div style="width: 45%;display:inline-block; position:absolute;"  v-loading="timeLineLoad">

                <avue-form :option="searchOption" ref="searchForm" v-model="searchForm" @submit="searchChange">

                </avue-form>

                <div id="analyzeChart"></div>

            </div>
            <div id="timeLineDiv" style="width: 50%!important;display:inline-block;margin-left: 51%;overflow-x:auto!important;" v-loading="timeLineLoad">
                <avue-empty v-if="tableData==null || tableData.length<=0" style="padding-top: 20%"></avue-empty>
                <avue-timeline pending time v-if="tableData!=null && tableData.length>0">
                    <avue-timeline-item icon="el-icon-truck" color="red" v-for="(item,index) in tableData" :key="index">
                        <div slot="time">
                            <p v-text="item.repairMonth"></p>
                        </div>
                        <div slot="content" @click="vutDialog(item)" style="cursor:pointer">
                            <h4 v-text="index+1+'、'+item.carNo" ></h4>
                            <el-form class="infoform">
                                <el-form-item label="车主：">
                                    <span v-text="item.name"></span>
                                </el-form-item>
                                <el-form-item label="车辆型号：">
                                    <span v-text="item.carType"></span>
                                </el-form-item>
                        <!--        <el-form-item label="发动机号：">
                                    <span v-text="item.engineNo"></span>findLabelByValue(dictCarColorData,
                                </el-form-item>-->
                                <el-form-item label="车身颜色：">
                                    <span v-text="findLabelByValue(dictCarColorData,item.carColor)"></span>
                                </el-form-item>
                                <el-form-item label="车行个数：" >
                                    <el-tag>
                                        {{item.sum}}
                                    </el-tag>
                                </el-form-item>
                                <el-form-item label="送修人：">
                                    <span v-text="item.name"></span>
                                </el-form-item>
                                <el-form-item label="送修人身份证：">
                                    <span v-text="item.idCard"></span>
                                </el-form-item>
                            </el-form>
                        </div>
                    </avue-timeline-item>
                </avue-timeline>


            </div>
            <div style="text-align: center;display:block;  padding-top:10px;">
                <a v-if="morePage" href="javascript:void(0)" @click="moreLoad()"
                   style="color: #409EFF;cursor: pointer;text-decoration: none;">
                    加载更多（剩余：{{page.total-(page.pageSize*page.currentPage)}} 条）</a>
            </div>
        </basic-container>
        <el-dialog  :title="title" fullscreen :visible.sync="undertakeVehicleTrajectoryDialog"  width="100%" append-to-body >
            <avue-crud ref="crud" v-model="form" :page="dialogPage" :data="tableDatadialog"  :option="tableOption"
                       :table-loading="tableLoading"
                       @search-change="searchDialog"
                       @on-load="tabledialog">
                <template slot="engineNo" slot-scope="scope" >
                    <el-tag>{{scope.row.engineNo}}</el-tag>
                </template>
            </avue-crud>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="undertakeVehicleTrajectoryDialog=false">关闭 </el-button>
            </div>
        </el-dialog>
    </div>

</template>

<script>
    import {analyzePage,dialog} from '@/api/portal/judged/analyze/machine/sameSiteMultipleModification'
    import {validatenull} from '@/util/validate'
    import {findDictArea}  from  "@/api/portal/archive/common/query";
    import {getDict} from "@/api/portal/archive/dict/dict";
    export default {
        name: 'ssmAnalyze',
        props: ["params"],
        data() {
            return {
                tableData: [],
                form: {
                    carNo:undefined,
                    repairMonth:""},
                dialogStyle:"dialogCss",
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 7 // 每页显示多少条
                },
                dialogPage: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                searchForm: {
                    type: undefined,
                    carNo:undefined,
                    name:undefined,
                    repairMonth:""
                },
                title:"",
                tableLoading:false,
                detailRow:{},
                undertakeVehicleTrajectoryDialog:false,
                timeLineLoad: true,
                myChart:undefined,
                morePage: false,
                queryParams: {
                    type: undefined,
                    carNo:undefined,
                    name:undefined,
                    repairMonth:""
                },
                dictCityData:[],
                dictQyztData:[],
                dictCarColorData: [],
                tableDatadialog:[],
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
                    menu:false,
                    column: [
                        { label:"场所ID", prop:"placeId"},
                        { label:"场所名称", prop:"placeName"},
                        { label:"机动车所有人", prop:"name"},
                        { label:"车牌号", prop:"carNo"},
                        { label:"发动机号码", prop:"engineNo",slot:true},
                        { label:"车辆类型", prop:"type",type:"select",dicUrl: '/portal/dict/getDict/cllx/jx',},
                        { label:"车辆型号", prop:"carType",},
                        { label:"车身颜色", prop:"carColor",dicUrl: '/portal/dict/getDict/csys/jx',},
                        {
                            label: "维修日期", prop: "repairTime", type: 'datetimerange',
                            editDisabled: false, viewDisplay: false,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',hide: true
                        },
                        { label:"场所地址", prop:"address"},
                        { label:"批次ID", prop:"batchId",hide:true},
                        { label:"行政区划", prop:"cityCode",hide:true},
                        { label:"管辖派出所", prop:"deptCode",hide:true},
                        { label:"主键ID", prop:"id",hide:true},
                        { label:"机动车所有人身份证号码", prop:"idCard",hide:true},
                        { label:"模型唯一标识", prop:"modelUdId",hide:true},
                        { label:"检测时间", prop:"monitorTime",hide:true},


                    ]
                },
                chartOption: {
                    title: {
                        text: '同车辆多车行维修分析',
                        top: '10',
                    },
                    noDataLoadingOption: {
                        text: '暂无数据',
                        effect: 'bubble',
                        effectOption: {
                            effect: {
                                n: 0
                            }
                        }
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            label: {
                                backgroundColor: '#6a7985'
                            }
                        }
                    },

                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: []
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                        }
                    ],
                    series: [
                        {
                            name: '车行个数',
                            type: 'line',
                            stack: '总量',
                            areaStyle: {},
                            data: []
                        }
                    ]
                },
                searchOption: {
                    submitText: "查询",
                    column: [
                        {
                            label: '维修时间',
                            prop: 'searchDate',
                            type: 'daterange',
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                            size: "small"

                        }
                    ]
                },


            }
        },
        watch: {
            params: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler(val) {
                    this.tableData=[]
                    this.initPage()
                    this.initSearch()
                    this.getList(this.page)
                    this.initDivHeight()
                    this.drawLine( this.chartOption);
                },
                deep: true
            },

        },
        mounted() {
            this.initDivHeight()
            this.myChart = echarts.init(document.getElementById("analyzeChart"));
            this.myChart.on('click',this.vutDialogChart)
            this.drawLine( this.chartOption);
        },
        created() {
            this.tableData=[]
            this.initDictData()
            this.initSearch()
            this.getList(this.page)
            getDict('csys', 'jx').then(response => {
                this.dictCarColorData = response.data.data || [];
            }).catch((err) => {
            });

        },
        methods: {
            vutDialog(row){
                this.title = row.repairMonth+'月'+row.carNo+'的维修记录'
                this.undertakeVehicleTrajectoryDialog = true
                this.form.repairMonth = row.repairMonth,
                this.form.carNo = row.carNo
                this.tabledialog(this.dialogPage)
            },

            initDictData(){
                findDictArea().then(response => {
                    this.dictCityData = response.data||[]
                })

            },
            initSearch(){
                this.queryParams.carNo = this.params.carNo
                this.queryParams.repairMonth = this.params.repairMonth
                if (!validatenull( this.params.searchTime) &&  this.params.searchTime.indexOf("至") != -1) {
                    this.searchForm.searchDate =  this.params.searchTime.replace(/\s/g,"").split("至")
                    this.searchForm.repairMonth =this.searchForm.searchDate.join(",");
                    this.queryParams.repairMonth=this.searchForm.searchDate.join(",");
                } else {
                    this.searchForm.searchDate = undefined
                    this.searchForm.repairMonth=undefined
                    this.queryParams.repairMonth=undefined
                }
            },

            moreLoad() {
                this.page.currentPage = this.page.currentPage + 1
                this.moreList(this.page)
                if (this.page.total > this.page.pageSize*this.page.currentPage) {
                    this.morePage = true
                }else{
                    this.morePage = false
                }
            },
            moreList(page) {
                this.timeLineLoad = true
                analyzePage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, Object.assign(this.queryParams))).then(response => {
                    this.tableData = this.tableData.concat(response.data.data.records)
                    this.page.total = response.data.data.total

                    this.initChartData(this.tableData)
                    this.timeLineLoad = false
                }).catch((error) => {
                    this.timeLineLoad = false
                })
            },
            drawLine( data) {
                this.myChart.setOption(data);
                this.myChart. resize();
            },
            initDivHeight() {
                const deviceHeight = document.documentElement.clientHeight;
                const timeLine = document.getElementById('timeLineDiv');
                const analyzeChart=document.getElementById('analyzeChart');
                timeLine.style.height = (Number(deviceHeight) - 200) + 'px'; //数字是页面布局高度差
                analyzeChart.style.height = (Number(deviceHeight) - Number(deviceHeight)/2) + 'px'; //数字是页面布局高度差
            },

            initChartData(data) {
                let xAxisData = [], seriesData = []
                if (!validatenull(data)) {
                    for (let da of data) {
                        seriesData.push({
                            "value":da.sum,
                            "record":da
                        })
                        xAxisData.push(da.repairMonth)
                    }
                }
                this.chartOption.xAxis[0].data = xAxisData.reverse()
                this.chartOption.series[0].data = seriesData.reverse()
                this.drawLine( this.chartOption);
            },
            initPage(){
                this.page.total=0
                this.page.currentPage=1
                this.page.pageSize=7
            },
            tabledialog(page){
                this.tableLoading = true
                dialog(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                },this.form)).then(response => {
                    this.tableDatadialog = response.data.data.records
                    this.dialogPage.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            getList(page) {
                this.timeLineLoad = true
                analyzePage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, Object.assign(this.queryParams))).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    if (this.page.total > (this.page.pageSize*this.page.currentPage)) {
                        this.morePage = true
                    }else{
                        this.morePage = false
                    }
                    this.initChartData(this.tableData)
                    this.timeLineLoad = false
                }).catch((error) => {
                    this.timeLineLoad = false
                })
            },
            searchDialog(params){
                this.getList(this.page)
            },
            searchChange(params) {
                if (!validatenull(params) && !validatenull(params.searchDate) && "undefined" != typeof (params.searchDate)) {
                    this.queryParams.repairMonth = params.searchDate.join(",");
                }
                this.initPage()
                this.getList(this.page)
            },

            /**
             * 刷新回调
             */
            refreshChange() {
                this.getList(this.page)
            },

        }
    }
</script>

<style lang="scss">

    .containerPath {
        h4 {
            padding: 0;
            margin: 0
        }

        .el-form-item {
            margin-bottom: 0px !important;
        }

        .infoform {
            box-shadow: 1px 1px 9px 1px #ccc;
            border-radius: 5px;
            overflow: hidden;
            padding: 10px;
            margin-top: 10px;
            min-width: 40%;
            width: auto;
        }

        .imgs {
            position: fixed;
            right: 0;
            top: 20%;
        }


    }

    .dialogCss {
        overflow: hidden !important;
        .el-dialog__body{
            padding-top: 0px !important;

        }
    }

</style>

