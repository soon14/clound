<template>
    <div class="user">
        <basic-container>
            <avue-crud :option="option"
                       :data="data"
                       v-model="form"
                       ref="crud"
                       :page="page"
                       :table-loading="tableLoading">
                <template slot="menu" slot-scope="scope" >
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-info"
                               @click="unitView=true">查看
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>
        <el-dialog title="查看详情" fullscreen :visible.sync="unitView" :append-to-body="true">
            <avue-detail :option="optionBase" v-model="fromBaseDetail">
                <template slot="imgBase"  >
                    <avue-data-imgtext :option="optionImg"></avue-data-imgtext>
                </template>
            </avue-detail>
        </el-dialog>
    </div>
</template>
<script>


    export default {
        name:"ysshlycsfx",
        data(){
            return {
                form:{},
                unitView:false,
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                data: [
                    {
                        lymc:'江门市七天酒店',
                        cyrs:117,
                        shrs:10
                    },
                    {
                        lymc:'江门市万福豪华酒店',
                        cyrs:85,
                        shrs:9
                    },
                    {
                        lymc:'江门市蓬江区天水围按摩保健度假村',
                        cyrs:81,
                        shrs:8
                    },
                    {
                        lymc:'月弯弯休闲宾馆',
                        cyrs:77,
                        shrs:7
                    },
                    {
                        lymc:'某某镇富贵城星期八休闲度假村',
                        cyrs:57,
                        shrs:6
                    },
                    {
                        lymc:'江门金苹果酒店',
                        cyrs:47,
                        shrs:5
                    },
                    {
                        lymc:'万年康招待所',
                        cyrs:37,
                        shrs:4
                    }
                ],
                option:{
                    page:false,
                    align:'center',
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn:false,
                    delBtn:false,
                    addBtn:false,
                    column:[
                        {
                            label: "旅业名称",
                            prop: "lymc",
                            width:600
                        },
                        {
                            label:"从业人数",
                            prop:"cyrs"
                        },
                        {
                            label:"涉黄人数",
                            prop:"shrs"
                        },
                        {
                            label:"行政区划",
                            prop:"xzqh",
                            search:true,
                            hide:true,
                            type:'select',
                            dicData:[
                                {
                                    label:"蓬江区",
                                    value:1
                                },
                                {
                                    label:"江海区",
                                    value:1
                                },
                                {
                                    label:"新会区",
                                    value:1
                                },
                                {
                                    label:"台山市",
                                    value:1
                                },
                                {
                                    label:"开平市",
                                    value:1
                                },
                                {
                                    label:"鹤山市",
                                    value:1
                                },
                                {
                                    label:"恩平市",
                                    value:1
                                }
                            ]
                        },
                        {
                            label:"所属单位",
                            prop:"ssdw",
                            search:true,
                            hide:true,
                            type:'select',
                            dicData:[{
                                    label: '白沙派出所',
                                    value: '440703500000'
                                }, {
                                    label: '北街派出所',
                                    value: '440703510000'
                                },
                                {
                                    label: '仓后派出所',
                                    value: '440703520000'
                                },
                                {
                                    label: '江门市公安局环市派出所',
                                    value: '440703530000'
                                }
                            ]
                        },
                        {
                            label:"行业类型",
                            prop:"hylx",
                            type:"select",
                            search:true,
                            hide:true,
                            dicData:[
                                {
                                    label:"宾馆",
                                    value:"1"
                                },
                                {
                                    label:"酒店",
                                    value:"2"
                                },
                                {
                                    label:"饭店",
                                    value:"3"
                                },
                                {
                                    label:"度假村",
                                    value:"4"
                                },
                                {
                                    label:"其他旅业",
                                    value:"5"
                                },
                                {
                                    label:"招待所",
                                    value:"6"
                                }
                            ]
                        }
                    ]
                },
                optionImg: {
                    span:4,
                    align:'left',
                    data: [
                        {
                            title: '江门七天酒店',
                            imgsrc: '/img/portal/7day1.png',
                            content: '地址：广东省江门市莲江区环市街道',

                        },
                        {
                            title: '江门七天酒店',
                            imgsrc: '/img/portal/7day2.png',
                            content: '地址：广东省江门市莲江区环市街道',


                        },

                    ]
                },
                optionBase: {
                    dialogHeight: '100%',
                    dialogWidth: '100',
                    dialogFullscreen: true,
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: true,
                    viewBtn: false,
                    delBtn: false,
                    card:true,
                    group: [
                        {
                            label: '基本信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [
                                {label:'单位名称',prop:'dwmc',},
                                {label:'经营名称',prop:'jymc',},
                                {label:'经营状态',prop:'jyzt',},
                                {label:'单位性质',prop:'dwxz',},
                                {label:'行业类别',prop:'hylb',},
                                {label:'开业日期',prop:'kyrq',},
                                {label:'联系电话',prop:'lxdh',},
                                {label:'星级',prop:'xj',},

                                {label:'等级评定',prop:'djd',},
                                {label:'上级主管部门',prop:'sjzgbm',},

                                {label:'管辖区派出所',prop:'xqpcs',},
                                {label:'主管公安机关',prop:'gajg',},
                                {label:'责任民警警号',prop:'zrmjjh',},
                                {label:'责任民警姓名',prop:'zrmjxm',},
                                {label:'主营',prop:'jyxmzy',},
                                {label:'兼营',prop:'jyxmjy',},
                                {label:'被兼营单位',prop:'bjydw',},

                                {label:'备注',prop:'bz',span:24},
                                /*{label:'录入人',prop:'lrr',},
                                {label:'录入日期',prop:'lrrq',},
                                {label:'录入情况',prop:'lrqk',},
                                {label:'最后修改人',prop:'zhxgr',},
                                {label:'最后修改时间',prop:'zhxgsj',},*/
                            ]
                        },{
                            label: '规模情况',
                            prop: 'gmqk',
                            icon: 'el-icon-info',
                            column: [
                                {label:'面积(平方米)',prop:'mj',},
                                {label:'楼层数',prop:'lcs',},
                                {label:'栋数',prop:'ds',},
                                {label:'房间数',prop:'fjs',},
                                {label:'床位数',prop:'cws',},
                            ]
                        },
                        {
                            label: '地址信息',
                            prop: 'dzxx',
                            icon: 'el-icon-info',
                            column: [{label:'行政区划',prop:'xzqh',},
                                {label:'街路巷',prop:'jlx',},
                                {label:'所属警务区',prop:'ssjwq',},
                                {label:'所属社区',prop:'sssq',},
                                {label:'门楼牌',prop:'mpl',},
                                {label:'详细地址',prop:'xxdz',},
                                {label:'经营地址',prop:'jjdz',},]
                        },{
                            label: '法定代表人',
                            prop: 'fddbr',
                            icon: 'el-icon-info',
                            column: [ {
                                label:'姓名',prop:'fddbrxm',},
                                {label:'证件种类',prop:'fddbrzjzl',},
                                {label:'证件号码',prop:'fddbrzjhm',},
                                {label:'联系电话',prop:'fddbrlxdh',},
                            ]
                        },{
                            label: '实际经营人',
                            prop: 'sjjyr',
                            icon: 'el-icon-info',
                            column: [
                                {label:'姓名',prop:'sjjyrxm',},
                                {label:'证件种类',prop:'sjjyrzjzl',},
                                {label:'证件号码',prop:'sjjyrzjhm',},
                                {label:'联系电话',prop:'sjjyrlxdh',},
                            ]
                        },{
                            label: '人员情况',
                            prop: 'ryqk',
                            icon: 'el-icon-info',
                            column: [
                                {label:'从业人员总数',prop:'cyrs',},
                                {label:'治安培训人数',prop:'zapxrs',},
                                {label:'保安人员总数',prop:'baryzs',},
                                {label:'自建保安数',prop:'zjbas',},
                                {label:'保安来源',prop:'baly',},
                                {label:'保安派驻单位',prop:'bapzdw',},
                                {label:'安保组织负责人姓名',prop:'abxm',},
                                {label:'安保组织负责人联系电话',prop:'ablxdh',},
                            ]
                        },{
                            label: '上网服务',
                            prop: 'swfw',
                            icon: 'el-icon-info',
                            column: [
                                {label:'是否提供上网服务',prop:'tgswfw',},
                                {label:'是否提供上网电脑',prop:'tgswdn',},
                                {label:'上网接口数量',prop:'swjksl',},
                                {label:'网络接入提供商',prop:'wljrs',},
                                {label:'上网电脑总数',prop:'swdnzs',},
                                {label:'上网方式',prop:'swfs',},
                                {label:'IP地址（上网账号）',prop:'ipdz',},
                                {label:'网管负责人姓名',prop:'wgfzr',},
                                {label:'是否安装网络安全设备',prop:'wlaqsb',},
                            ]
                        },{
                            label: '照片信息',
                            prop: 'swfw',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '图片',
                                    prop: 'imgBase',
                                    formslot:'true',
                                    slot:true,
                                    span:24,
                                    hide:true
                                },
                            ]
                        },

                    ]
                },
                fromBaseDetail:{
                    dwmc:'江门市七天酒店',
                    jymc:'江门市七天酒店',
                    jyzt:'正常',
                    dwxz:'内资',
                    hylb:'宾馆',
                    kyrq:'2017-01-02 12:00',
                    lxdh:'2017-01-02 12:00',
                    xj:'五星',
                    fjs:'123',
                    cws:'454',
                    djd:'A级',
                    sjzgbm:'无',
                    abxm:'张某某',
                    ablxdh:'1348236423652',
                    xqpcs:'仓后派出所',
                    gajg:'仓后派出所',
                    zrmjjh:'523545',
                    zrmjxm:'李某某',
                    jyxmzy:'旅业',
                    jyxmjy:'无',
                    bjydw:'无',
                    fddbrxm:'张某某',
                    fddbrzjzl:'公民身份证',
                    fddbrlxdh:'15915719990',
                    fddbrzjhm:'441323199009091234',
                    sjjyrxm:'张某某',
                    sjjyrzjzl:'公民身份证',
                    sjjyrlxdh:'15915719990',
                    sjjyrzjhm:'441323199009091234',
                    xzqh:'广东省江门市莲江区',
                    jlx:'XX村',
                    ssjwq:'广东省江门市莲江区',
                    sssq:'虎山',
                    mpl:'XX号',
                    xxdz:'广东省江门市莲江区环市街',
                    jjdz:'广东省江门市莲江区环市街',
                    cyrs:'12',
                    zapxrs:'2',
                    baryzs:'3',
                    zjbas:'4',
                    baly:'自建',
                    bapzdw:'无',
                    tgswfw:'是',
                    tgswdn:'是',
                    swjksl:'123',
                    wljrs:'电信',
                    swdnzs:'123',
                    swfs:'wifi',
                    ipdz:'127.0.0.1',
                    wgfzr:'张某',
                    wlaqsb:'是',
                    bz:'需要重点关注',
                    lrr:'录入人',
                    lrrq:'录入日期',
                    lrqk:'录入情况',
                    zhxgr:'最后修改人',
                    zhxgsj:'最后修改时间',
                    mj:'123',
                    lcs:'10',
                    ds:'2'

                }
            }
        },
        created(){
        },
        computed: {
        },
        methods:{
           /* openDialog:function (){
                this.unitView = true;
            }*/
        }
    }
</script>