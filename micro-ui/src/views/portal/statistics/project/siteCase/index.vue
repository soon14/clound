<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       :page="page"
                       :option="tabOption"
                       :data="tabData"
                       v-model="form"
                       @row-update="handleUpdate"
                       @row-save="handleSav"
                       @row-del="handleDelete">

            </avue-crud>
        </basic-container>
    </div>
</template>

<script>
    let dict ={
        area: [
            {
                label:'广东省',
                value:0,
                children:[
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
                    },
                    {
                        label:'深圳市',
                        value:2,
                        children:[
                            {
                                label:'南山区',
                                value:21,
                            }
                        ]
                    },
                    {
                        label:'佛山市',
                        value:3,
                    },
                    {
                        label:'江门市',
                        value:4,
                        children:[
                            {
                                label:'蓬江区',
                                value:21,
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
                    },
                    {
                        label:'珠海市',
                        value:5
                    }
                ]
            }],
        dw:[
            {label: '广州市', value: '0'},
            {label: '深圳市', value: '1'},
            {label: '佛山市', value: '2'},
            {label: '江门市',value: '3'},
            {label: '珠海市',value: '4'},
        ],
        dy:[
            {label: '省级', value: '0'},
            {label: '市级', value: '1'},
            {label: '县级', value: '2'},
            {label: '派出所',value: '3'},
        ],
        cslb:[
            {"label":"旅馆业","value":"01"},
            {"label":"刻印章业","value":"02"},
            {"label":"旧货业","value":"03"},
            {"label":"印刷业","value":"04"},
            {"label":"机动车修理业","value":"05"},
            {"label":"开锁业","value":"06"},
            {"label":"其他管理行业","value":"07"},
            {"label":"歌舞娱乐场所","value":"08"},
            {"label":"按摩服务业","value":"09"},
            {"label":"游戏游艺场所","value":"10"},
            {"label":"录像放映场所","value":"11"},
            {"label":"上网服务场所","value":"12"},
            {"label":"体育场馆","value":"13"},
            {"label":"广场公园","value":"14"},
            {"label":"其他场所","value":"15"},
            {"label":"报废机动车拆解业","value":"16"},
            {"label":"典当业","value":"17"},
            {"label":"废旧金属业","value":"18"}
        ],
    }
    export default {
        name: "siteCase",
        data() {
            return {
                dialogVisible: false,
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                form: {},
                tabOption: {
                    expandAll: false,
                    expandLevel: 1,
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    tree:true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    delBtn: false,
                    viewBtn: false,
                    menu: false,
                    column: [
                        {
                            label:'地域',
                            prop:'dy',
                            type:'tree',
                            search:true,
                            expandLevel: 2,
                            dicData:dict.area,
                            span:12,
                        },
                        {
                            label:'特行场所类别',
                            prop:'tjqzsj',
                            search:true,
                            type:'select',
                            hide:true,
                            dicData:dict.cslb,
                            span:12,
                        },
                        {
                            label:'统计起止时间',
                            prop:'tjqzsj',
                            search:true,
                            type:'datetimerange',
                            hide:true,
                            span:12,
                        },
                        {
                            label:'单位总量',
                            prop:'dwzl',
                            span:12,
                        },
                        {
                            label:'新增量',
                            prop:'xzl',
                            span:12,
                        },
                        {
                            label:'关闭量',
                            prop:'gbl',
                            span:12,
                        },
                        {
                            label:'违法率',
                            prop:'wfl',
                            span:12,
                        },
                    ]
                },
                tabData:[
                    {
                        dy:'广州市',
                        dwzl:'12285',
                        xzl:'336',
                        gbl:'235',
                        wfl:'2.10%',
                        children: [
                            {
                                dy:'白云区',
                                dwzl:'12285',
                                xzl:'336',
                                gbl:'235',
                                wfl:'2.10%',
                                children: [
                                    {
                                        dy:'派出所1',
                                        dwzl:'12285',
                                        xzl:'336',
                                        gbl:'235',
                                        wfl:'2.10%',
                                    },
                                    {
                                        dy:'派出所2',
                                        dwzl:'12285',
                                        xzl:'336',
                                        gbl:'235',
                                        wfl:'2.10%',
                                    }
                                ]
                            },
                            {
                                dy:'黄埔区',
                                dwzl:'12285',
                                xzl:'336',
                                gbl:'235',
                                wfl:'2.10%',
                            },
                        ]

                    },
                    {
                        dy:'深圳市',
                        dwzl:'15285',
                        xzl:'536',
                        gbl:'335',
                        wfl:'2.30%',
                        children: [
                            {
                                dy:'宝安区',
                                dwzl:'12285',
                                xzl:'336',
                                gbl:'235',
                                wfl:'2.10%',
                                children: [
                                    {
                                        dy:'派出所1',
                                        dwzl:'12285',
                                        xzl:'336',
                                        gbl:'235',
                                        wfl:'2.10%',
                                    },
                                    {
                                        dy:'派出所2',
                                        dwzl:'12285',
                                        xzl:'336',
                                        gbl:'235',
                                        wfl:'2.10%',
                                    }
                                ]
                            },
                            {
                                dy:'黄埔区',
                                dwzl:'12285',
                                xzl:'336',
                                gbl:'235',
                                wfl:'2.10%',
                            },
                        ]

                    },
                    {
                        dy:'佛山市',
                        dwzl:'9285',
                        xzl:'321',
                        gbl:'311',
                        wfl:'1.80%',
                        children: [
                            {
                                dy:'顺德区',
                                dwzl:'12285',
                                xzl:'336',
                                gbl:'235',
                                wfl:'2.10%',
                                children: [
                                    {
                                        dy:'派出所1',
                                        dwzl:'12285',
                                        xzl:'336',
                                        gbl:'235',
                                        wfl:'2.10%',
                                    },
                                    {
                                        dy:'派出所2',
                                        dwzl:'12285',
                                        xzl:'336',
                                        gbl:'235',
                                        wfl:'2.10%',
                                    }
                                ]
                            },
                            {
                                dy:'黄埔区',
                                dwzl:'12285',
                                xzl:'336',
                                gbl:'235',
                                wfl:'2.10%',
                            },
                        ]
                    },
                    {
                        dy:'江门市',
                        dwzl:'9123',
                        xzl:'265',
                        gbl:'219',
                        wfl:'1.10%',
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>