<template>
    <div class="execution">
        <basic-container>
            <avue-crud
                       :data="tableData"
                       :option="tableOption"
                       v-model="form"
                      >
            </avue-crud>
        </basic-container>
    </div>
</template>

<script>
let dict = {

    area: [
        {
            label:'广州市',
            value:1,
            children:[
                {
                    label:'天河区',
                    value:11,
                },{
                    label:'越秀区',
                    value:12,
                } ,{
                    label:'白云区',
                    value:13,
                }
            ]
        },{
            label:'深圳市',
            value:2,
            children:[
                {
                    label:'南山区',
                    value:21,
                }
            ]
        },{
            label:'佛山市',
            value:3,
        },{
            label:'江门市',
            value:4,
            children:[
                {
                    label:'蓬江区',
                    value:21,
                    children:[
                        {
                            label:'派出所1',
                            value:31,
                        },{
                            label:'派出所2',
                            value:32,
                        },{
                            label:'派出所3',
                            value:33,
                        },{
                            label:'派出所4',
                            value:34,
                        },{
                            label:'派出所5',
                            value:35,
                        }
                    ]
                },
                {
                    label:'江海区',
                    value:22,
                },
                {
                    label:'新会市',
                    value:23,
                },
                {
                    label:'台上市',
                    value:24,
                },
                {
                    label:'开平市',
                    value:25,
                }
            ]
        },{
            label:'珠海市',
            value:5
        }],

    type: [
        {"label": "旅馆业", "value": "01"},
        {"label": "刻印章业", "value": "02"},
        {"label": "旧货业", "value": "03"},
        {"label": "印刷业", "value": "04"},
        {"label": "机动车修理业", "value": "05"},
        {"label": "开锁业", "value": "06"},
        {"label": "其他管理行业", "value": "07"},
        {"label": "歌舞娱乐场所", "value": "08"},
        {"label": "按摩服务业", "value": "09"},
        {"label": "游戏游艺场所", "value": "10"},
        {"label": "录像放映场所", "value": "11"},
        {"label": "上网服务场所", "value": "12"},
        {"label": "体育场馆", "value": "13"},
        {"label": "广场公园", "value": "14"},
        {"label": "其他场所", "value": "15"},
        {"label": "报废机动车拆解业", "value": "16"},
        {"label": "典当业", "value": "17"},
        {"label": "废旧金属业", "value": "18"},
        {"label": "涉枪单位", "value": "19"},
        {"label": "易制爆单位", "value": "20"},
        {"label": "剧毒化学品单位", "value": "21"},
        {"label": "旅业式出租屋", "value": "22"},
        {"label": "烟花爆竹单位", "value": "23"},
        {"label": "重点单位", "value": "24"},
        {"label": "一般单位", "value": "25"},
        {"label": "学校/幼儿园", "value": "26"},
        {"label": "保安服务公司", "value": "27"},
        {"label": "保安押运公司", "value": "28"},
        {"label": "保安培训机构", "value": "29"},
        {"label": "内部保安组织", "value": "30"},
        {"label": "放射性同位素", "value": "31"},
        {"label": "其他单位", "value": "32"}
    ]
}
    export default {
        name:"review",
        data() {
            return {
                form:{},
                tableData: [
                    {
                        id: '3',
                        area:'江门市',
                        taskTotal:'12000',
                        completeTotal: '12000',
                        completion:'100%',
                        TimelyRate:'99%',
                        children: [
                            {
                                id: 'a',
                                parentId:'3',
                                area:'蓬江区',
                                taskTotal:'3000',
                                completeTotal: '3000',
                                completion:'100%',
                                TimelyRate:'99%',
                                children: [
                                    {
                                        id: 'a1',
                                        parentId:'a',
                                        area:'派出所1',
                                        taskTotal:'600',
                                        completeTotal: '600',
                                        completion:'100%',
                                        TimelyRate:'99%',
                                    },
                                    {
                                        id: 'a2',
                                        parentId:'a',
                                        area:'派出所2',
                                        taskTotal:'600',
                                        completeTotal: '600',
                                        completion:'100%',
                                        TimelyRate:'99%',
                                    },
                                    {
                                        id: 'a3',
                                        parentId:'a',
                                        area:'派出所3',
                                        taskTotal:'600',
                                        completeTotal: '600',
                                        completion:'100%',
                                        TimelyRate:'99%',
                                    },
                                    {
                                        id: 'a4',
                                        parentId:'a',
                                        area:'派出所4',
                                        taskTotal:'600',
                                        completeTotal: '600',
                                        completion:'100%',
                                        TimelyRate:'99%',
                                    },
                                    {
                                        id: 'a5',
                                        parentId:'a',
                                        area:'派出所5',
                                        taskTotal:'600',
                                        completeTotal: '600',
                                        completion:'100%',
                                        TimelyRate:'99%',
                                    }
                                ]
                            },
                            {
                                id: 'b',
                                parentId:'3',
                                area:'江海区',
                                taskTotal:'3000',
                                completeTotal: '3000',
                                completion:'100%',
                                TimelyRate:'99%',
                                children: [
                                    {
                                        id: 'b1',
                                        parentId:'b',
                                        area:'派出所1',
                                        taskTotal:'600',
                                        completeTotal: '600',
                                        completion:'100%',
                                        TimelyRate:'99%',
                                    },
                                    {
                                        id: 'b2',
                                        parentId:'b',
                                        area:'派出所2',
                                        taskTotal:'600',
                                        completeTotal: '600',
                                        completion:'100%',
                                        TimelyRate:'99%',
                                    },
                                    {
                                        id: 'b3',
                                        parentId:'b',
                                        area:'派出所3',
                                        taskTotal:'600',
                                        completeTotal: '600',
                                        completion:'100%',
                                        TimelyRate:'99%',
                                    },
                                    {
                                        id: 'b4',
                                        parentId:'b',
                                        area:'派出所4',
                                        taskTotal:'600',
                                        completeTotal: '600',
                                        completion:'100%',
                                        TimelyRate:'99%',
                                    },
                                    {
                                        id: 'b5',
                                        parentId:'b',
                                        area:'派出所5',
                                        taskTotal:'600',
                                        completeTotal: '600',
                                        completion:'100%',
                                        TimelyRate:'99%',
                                    }
                                ]
                            },
                            {
                                id: 'c',
                                parentId:'3',
                                area:'新会区',
                                taskTotal:'2000',
                                completeTotal: '2000',
                                completion:'100%',
                                TimelyRate:'99%',
                            },
                            {
                                id: 'c',
                                parentId:'3',
                                area:'台上市',
                                taskTotal:'2000',
                                completeTotal: '2000',
                                completion:'100%',
                                TimelyRate:'99%',
                            },
                            {
                                id: 'c',
                                parentId:'3',
                                area:'开平市',
                                taskTotal:'2000',
                                completeTotal: '2000',
                                completion:'100%',
                                TimelyRate:'99%',
                            }
                        ]
                    },
                    {
                        id: '0',
                        area:'广州市',
                        taskTotal:'12000',
                        completeTotal: '12000',
                        completion:'100%',
                        TimelyRate:'99%',
                        children:[
                            {
                                id: '0a',
                                parentId:'0',
                                area:'天河区',
                                taskTotal:'4000',
                                completeTotal: '4000',
                                completion:'100%',
                                TimelyRate:'99%'
                            },
                            {
                                id: '0b',
                                parentId:'0',
                                area:'越秀区',
                                taskTotal:'4000',
                                completeTotal: '4000',
                                completion:'100%',
                                TimelyRate:'99%',
                            },
                            {
                                id: '0c',
                                parentId:'0',
                                area:'白云区',
                                taskTotal:'4000',
                                completeTotal: '4000',
                                completion:'100%',
                                TimelyRate:'99%',
                            },
                        ]
                    },
                    {
                        id: '1',
                        area:'深圳市',
                        taskTotal:'12000',
                        completeTotal: '12000',
                        completion:'100%',
                        TimelyRate:'99%',
                        children:[
                            {
                                id: '1a',
                                parentId:'1',
                                area:'南山区',
                                taskTotal:'12000',
                                completeTotal: '12000',
                                completion:'100%',
                                TimelyRate:'99%'
                            }
                        ]
                    },
                    {
                        id:'2',
                        area:'佛山市',
                        taskTotal:'12000',
                        completeTotal: '12000',
                        completion:'100%',
                        TimelyRate:'99%',
                    },
                    {
                        id: '4',
                        area:'珠海市',
                        taskTotal:'12000',
                        completeTotal: '12000',
                        completion:'100%',
                        TimelyRate:'99%',
                    }

                ],
                tableOption: {
                    expandLevel: 1,
                    tree:true,
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    viewBtn: false,
                    editBtn: false,
                    delBtn: false,
                    menu: false,
                    refreshBtn:false,
                    column: [
                        {
                            label: '区域',
                            prop: 'area',
                            search:'true',
                            align: 'left',
                            type:'tree',
                            expandAll: false,
                            expandLevel: 1,
                            width: 200,
                            dicData:dict.area,
                            props:{
                                label:'label',
                                value:'value'
                            }
                        },
                        {
                            label: '任务总数',
                            prop: 'taskTotal',
                            align: 'left',
                            overHidden: true,
                        },
                        {
                            label: '已完成任务数',
                            prop: 'completeTotal',
                            align: 'left',
                            overHidden: true
                        },
                        {
                            label: '任务完成率',
                            prop: 'completion',
                            align: 'left',
                            overHidden: true,
                        },
                        {
                            label: '任务及时率',
                            prop: 'TimelyRate',
                            align: 'left',
                            overHidden: true
                        },
                        {
                            label: '场所类别',
                            prop: 'type',
                            hide: true,
                            type:'select',
                            searchFilterable:true,
                            dicData: dict.type,
                            search:'true',
                            align: 'left'
                        },
                        {
                            label: '统计时间',
                            prop: 'countDate',
                            type:"datetimerange",
                            align: 'left',
                            hide: true,
                            search:'true'
                        },
                        ]
                }

            }
        },
        computed: {
            infoData() {
                return this.form.info
            }
        },

    }
</script>

