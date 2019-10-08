<!--
 * @author wengshij
 * @date Created in 2019/7/2 14:41
 * @description:历史积分信息（条形图 及列表信息）
 * @modified By:
 -->
<template>
    <div class="execution">
        <basic-container class="containerPath">
            <div style="width: 45%;display:inline-block; position:absolute;" v-loading="tableLoading">
                <div id="analyzeChart"></div>
            </div>
            <div id="timeLineDiv"
                 style="width: 50%!important;display:inline-block;margin-left: 51%;overflow-x:auto!important;">
                <avue-crud
                        ref="crud"
                        v-model="form"
                        :data="tableData"
                        :option="tableOption"
                        :table-loading="tableLoading"
                >
                    <template slot-scope="scope" slot="placeName">
                        <el-tag @click="showDetail(scope.row)"
                                class="el-button el-button--text el-button--small">{{scope.row.placeName}}
                        </el-tag>
                    </template>

                </avue-crud>

            </div>

        </basic-container>
        <el-dialog :title="detailTitle"
                   :visible.sync="viewFlag"
                   width="80%"
                   v-dialogDrag
                   append-to-body
        >
            <score-detail v-bind:detail-row="formRow"></score-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="viewFlag=false">关 闭
                </el-button>
            </div>

        </el-dialog>
    </div>

</template>

<script>

    import {placeHistoryScore} from '@/api/portal/point/score/pointScore'
    import {validatenull} from '@/util/validate'
    import {remote} from "@/api/admin/dict";
    import scoreDetail from '@/views/portal/point/score/scoreDetail'
    import {deepClone} from '@/util/util'

    export default {
        components: {
            scoreDetail,
        },
        name: 'historyScore',
        props: ["detailRow"],
        data() {
            return {
                tableData: [],
                form: {},
                dialogStyle: "dialogCss",
                timeLineLoad: true,
                myChart: undefined,
                detailTitle: '',
                formRow: {},
                viewFlag: false,
                queryParams: {
                    placeId: undefined,
                },
                dictPlaceTypeData: [],
                dictLevelTypeData: [],
                tableLoading: true,
                tableOption: {
                    border: true,
                    index: true,
                    menu: false,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    viewBtn: false,
                    pageshow: true,
                    editBtn: false,
                    delBtn: false,
                    submitBtn: false,
                    refreshBtn: false,
                    searchBtn: false,
                    columnBtn: false,
                    emptyBtn: true,
                    column: [
                        {
                            label: "场所名称",
                            prop: "placeName",
                            overHidden: true,
                            slot: true
                        },
                        {
                            label: "场所类型", prop: "placeType",
                            dicUrl: '/admin/dict/type/HYLX',
                            dicMethod: 'get',
                            dicQuery: {},
                        },
                        /* {label: "管辖派出所", prop: "deptName"},*/
                        {label: "总分", prop: "totalScore",},
                        {
                            label: "积分等级", prop: "levelType",
                            searchFilterable: true,
                            dicUrl: '/admin/dict/type/jfdj',
                            dicMethod: 'get',
                            dicQuery: {},
                        },
                        {
                            label: "周期", prop: "cycleName",

                        },
                        {
                            label: "评定时间", prop: "judgeTime",

                        },
                    ]
                },
                chartOption: {
                    title: {
                        text: '特行场所积分排名',
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
                        },
                        formatter: function (params, ticket, callback) {
                            let result = new Array();
                            result.push('场所名称：' + params[0].data.row.placeName);
                            result.push('场所类型：' + params[0].data.placeType);
                            result.push('评分周期：' + params[0].axisValue);
                            result.push('周期积分：' + params[0].data.row.totalScore);
                            result.push('积分等级：' + params[0].data.value);
                            result.push('评定时间：' + params[0].data.row.judgeTime);
                            return result.join("</br>");
                            ;
                        }
                    },
                    /*   toolbox: {
                           feature: {
                               saveAsImage: {}
                           }
                       },*/
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
                            data: []
                        }
                    ],
                    series: [
                        {
                            name: '周期积分',
                            type: 'line',
                            stack: '积分',

                            data: []
                        }
                    ]
                },
            }
        },
        watch: {
            detailRow: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler(val) {
                    this.queryParams.placeId = val.placeId
                    this.tableData = []
                    this.getList()
                    this.initDivHeight()
                    this.drawLine(this.chartOption);
                },
                deep: true
            },

        },
        mounted() {
            this.initDivHeight()
            this.myChart = echarts.init(document.getElementById("analyzeChart"));
            this.myChart.on('click', this.chartDetail)
            this.drawLine(this.chartOption);
        },
        created() {
            this.initDictData()
            this.queryParams.placeId = this.detailRow.placeId
            this.tableData = []
            this.getList()
        },
        methods: {
            chartDetail(eRow) {
                let row = eRow.data.row
               console.log(row)

                this.showDetail(row)
            },

            showDetail(row) {
                this.viewFlag = true
                this.detailTitle = row.placeName
                this.formRow = deepClone(row)
            },
            initDictData() {
                remote('HYLX').then(response => {
                    this.dictPlaceTypeData = response.data.data || []
                })
                remote('jfdj').then(response => {
                    this.dictLevelTypeData = response.data.data || []
                })
            },
            drawLine(data) {
                this.myChart.setOption(data);
                this.myChart.resize();
            },
            initDivHeight() {
                const deviceHeight = document.documentElement.clientHeight;
                const timeLine = document.getElementById('timeLineDiv');
                const analyzeChart = document.getElementById('analyzeChart');
                timeLine.style.height = (Number(deviceHeight) - 200) + 'px'; //数字是页面布局高度差
                analyzeChart.style.height = (Number(deviceHeight) - Number(deviceHeight) / 2) + 'px'; //数字是页面布局高度差
            },
            initChartData(data) {
                let xAxisData = [], seriesData = [], yAxisData = [], dictLevel = this.dictLevelTypeData,
                    dictPlaceType = this.dictPlaceTypeData
                if (!validatenull(data)) {
                    for (let da of data) {
                        seriesData.push({
                            "value": this.findLabelByValue(dictLevel, da.levelType),
                            "placeType": this.findLabelByValue(dictPlaceType, da.placeType),
                            "row": da
                        })
                        xAxisData.push(da.cycleName)
                    }
                }
                for (let dict of dictLevel) {
                    yAxisData.push({value: dict.label, name: dict.value})
                }
                this.chartOption.yAxis[0].data = yAxisData
                this.chartOption.xAxis[0].data = xAxisData.reverse()
                this.chartOption.series[0].data = seriesData.reverse()
                this.drawLine(this.chartOption);
            },

            getList() {
                this.tableLoading = true
                if (validatenull(this.queryParams.placeId)) {
                    this.tableLoading = false
                    return
                }
                placeHistoryScore(this.queryParams.placeId).then(response => {
                    this.tableData = response.data.data
                    this.initChartData(this.tableData)
                    this.tableLoading = false
                }).catch((error) => {
                    this.tableLoading = false
                })
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

        .el-dialog__body {
            padding-top: 0px !important;

        }
    }

</style>

