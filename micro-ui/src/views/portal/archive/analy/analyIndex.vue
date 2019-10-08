<template>
    <div class="app-container calendar-list-container">
        <basic-container>

            <div id="myChart" style="width: auto; height: 400px"></div>
        </basic-container>

    </div>
</template>

<script>
    import echarts from 'echarts' //引入echarts
    Vue.prototype.$echarts = echarts //引入组件
    export default {
        name: 'eCharts',
        mounted(){
            this.initLine();
        },
        methods: {
            initLine(){
                var myChart = this.$echarts.init(document.getElementById('myChart'))
                myChart.setOption(  {
                    title : {
                        text: '2019-单位涉黄、涉赌、涉毒、涉维智能比对分析',
                        subtext: '纯属虚构'
                    },
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['涉黄','涉赌','涉毒','涉维','涉外']
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : ['2019年-01月','2019年-02月','2019年-03月','2019年-04月']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'涉黄',
                            type:'bar',
                            data:[2, 5, 7, 10],
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'},
                                    {type : 'min', name: '最小值'}
                                ]
                            }
                        },
                        {
                            name:'涉赌',
                            type:'bar',
                            data:[10, 4, 12, 8],
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'},
                                    {type : 'min', name: '最小值'}
                                ]
                            },
                        },
                        {
                            name:'涉毒',
                            type:'bar',
                            data:[3,7,9,10],
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'},
                                    {type : 'min', name: '最小值'}
                                ]
                            },
                        },
                        {
                            name:'涉维',
                            type:'bar',
                            data:[12,5,6,12],
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'},
                                    {type : 'min', name: '最小值'}
                                ]
                            },
                        },
                        {
                            name:'涉外',
                            type:'bar',
                            data:[1,3,2,3],
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'},
                                    {type : 'min', name: '最小值'}
                                ]
                            },
                        }
                    ]
                });
            }


        }
    }
</script>



