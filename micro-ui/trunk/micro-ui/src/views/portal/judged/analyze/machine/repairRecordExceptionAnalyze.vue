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
                    <template slot="num" slot-scope="scope">
                        <div class=" el-range-editor el-input__inner" style="border:none!important; ">
                            <el-select v-model="searchForm.operator"
                                       type="select"
                                       size="small"
                            >
                                <el-option value="eq" label="等于"></el-option>
                                <el-option value="lt" label="小于等于"></el-option>
                                <el-option value="gt" label="大于等于"></el-option>
                            </el-select>
                            &nbsp;
                            <el-input onkeyup="value=value.replace(/[^\d]/g,'')"
                                      placeholder="默认等于-维修次数"
                                      v-model="searchForm.num"
                                      type="input"
                                      size="small"
                                      clearable>
                            </el-input>
                        </div>
                    </template>
                </avue-form>


                <div id="analyzeChart"></div>


            </div>
            <div id="timeLineDiv"
                 style="width: 50%!important;display:inline-block;margin-left: 51%;overflow-x:auto!important;"
                 v-loading="timeLineLoad">
                <avue-empty v-if="tableData==null || tableData.length<=0" style="padding-top: 20%"></avue-empty>
                <avue-timeline pending time v-if="tableData!=null && tableData.length>0">
                    <avue-timeline-item icon="el-icon-truck" color="red" v-for="(item,index) in tableData">
                        <div slot="time" style="color: black">
                            <p v-text="item.dayDate"></p>
                        </div>
                        <div slot="content" @click="vutDialog(item)" style="cursor:pointer">
                            <h4 v-text="index+1+'、'+item.placeName" ></h4>
                            <el-form class="infoform" >
                                <el-form-item label="管辖派出所：">
                                    {{item.deptName}}
                                </el-form-item>
                                <el-form-item label="行政区划：">

                                    {{findLabelByValue(dictCityData,item.cityCode)}}
                                </el-form-item>
                                <el-form-item label="单位地址：">
                                    {{item.address}}
                                </el-form-item>
                                <el-form-item label="营业状态：">
                                    {{findLabelByValue(dictQyztData,item.type)}}

                                </el-form-item>

                                <el-form-item label="维修次数：" >
                                    <el-tag>

                                        {{item.num}}

                                    </el-tag>
                                </el-form-item>
                                <el-form-item label="维修时间：">
                                    {{item.dayDate}}
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
        <el-dialog  title="维修信息"
                    :customClass="dialogStyle"
                    :visible.sync="undertakeVehicleTrajectoryDialog"
                    v-dialogDrag
                    append-to-body

         >
            <undertake-vehicle-trajectory v-bind:detailRow="detailRow"></undertake-vehicle-trajectory>
            <!--<div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="undertakeVehicleTrajectoryDialog=false">关闭 </el-button>
            </div>-->
        </el-dialog>
    </div>

</template>

<script>
    import {fetchListPage} from '@/api/portal/judged/analyze/machine/repairRecordException'
    import popTree from "@/components/ztree/poptree";
    import {validatenull} from '@/util/validate'
    import {getDict}  from  "@/api/portal/archive/dict/dict";
    import {findDictArea}  from  "@/api/portal/archive/common/query";
    import undertakeVehicleTrajectory from '@/views/portal/judged/analyze/machine/undertakeVehicleTrajectory';
    export default {
        components: {popTree,undertakeVehicleTrajectory},
        name: 'rreAnalyze',
        props: ["params"],
        data() {
            return {
                tableData: [],
                form: {},

                isShow: true,//是否显示搜索框
                undertakeVehicleTrajectoryDialog:false,
                dialogStyle:"dialogCss",
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 7 // 每页显示多少条
                },
                searchForm: {
                    num: '',
                    operator: 'eq',
                   searchDate:undefined,
                    searchTime: ''
                },
                detailRow:{},
                timeLineLoad: true,
                myChart:undefined,
                morePage: false,
                queryParams: {
                    placeId: undefined,
                },
                dictCityData:[],
                dictQyztData:[],
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
                        {label: "维修次数", prop: "num"},
                        {
                            label: "监控时间", prop: "searchTime",
                            search: true, type: 'daterange',
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
                chartOption: {
                    title: {
                        text: '维修记录异常分析',
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
                            name: '维修次数',
                            type: 'line',
                            stack: '总量',
                            areaStyle: {},
                            data: []
                        }
                    ]
                },

                searchOption: {
                    submitText: "查询",
                    column: [{
                        label: '维修次数',
                        prop: 'num',
                        formslot: true
                    },
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


        },
        methods: {
            vutDialogChart(row){
                this.undertakeVehicleTrajectoryDialog = true
                this.detailRow = row.data.record
            },
            vutDialog(row){
                this.undertakeVehicleTrajectoryDialog = true
                this.detailRow = row
            },
            initDictData(){
                findDictArea().then(response => {
                    this.dictCityData = response.data||[]
                })
                getDict('qyzt','jx').then(response => {
                    this.dictQyztData = response.data.data||[];
                }).catch((err) => {});
            },
            initSearch(){
                this.queryParams.placeId = this.params.placeId

                if (!validatenull( this.params.searchTime) &&  this.params.searchTime.indexOf("至") != -1) {
                    this.searchForm.searchDate =  this.params.searchTime.replace(/\s/g,"").split("至")
                    this.searchForm.searchTime =this.searchForm.searchDate.join(",");
                } else {
                    this.searchForm.searchDate = undefined
                    this.searchForm.searchTime=undefined
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
                fetchListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, Object.assign(this.searchForm, this.queryParams))).then(response => {
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
                            "value":da.num,
                            "record":da
                        })
                        xAxisData.push(da.dayDate)
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

            getList(page) {
                this.timeLineLoad = true
                fetchListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, Object.assign(this.searchForm, this.queryParams))).then(response => {
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
            searchChange(params) {
                if (!validatenull(params) && !validatenull(params.searchDate) && "undefined" != typeof (params.searchDate)) {
                    this.searchForm.searchTime = params.searchDate.join(",");
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

