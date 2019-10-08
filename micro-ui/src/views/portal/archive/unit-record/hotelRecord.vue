<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-crud :option="option" :data="data"
                       :page="page"

                       v-model="form">

                <template slot="xm"
                          slot-scope="scope">
                    <button type="button" class="el-button el-button--text el-button--small"  >{{scope.row.xm}}</button>
                </template>
                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="openDialog(scope.row.xm)">预览
                    </el-button>
                </template>

            </avue-crud>
        </basic-container>

        <el-dialog :title="title" fullscreen :visible.sync="recordView" append-to-body>
            <avue-detail :option="option" v-model="fromDetail" >
                <template slot="imgUi" slot-scope="scope">
                    <avue-data-imgtext :option="optionImg"></avue-data-imgtext>
                </tempLate>
            </avue-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="recordView=false">关 闭
                </el-button>
            </div>
        </el-dialog>


    </div>

</template>


<script>

    let dict={
        xzqh:[{
            label:'广东省广州市',
            value:'4401'
        },
            {
                label:'广东省江门市',
                value:'4407'
            },
        ]
    }
    export default {
        data() {
            return {
                page: {
                    total: 10, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                recordView:false,
                title:'预览',
                form: {},
                fromDetail:{lkbh:'440723523534532',
                    th:'235346474535345',
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    cws:'123',
                    fjs:'204',
                    xj:'五星级',
                    zafzr:'李某某',
                    zt:'正常',
                    fdfzr:'张某某',
                    rzfh:'403',
                    rzsj:'2017-01-02 12:30',
                    tfsj:'2017-01-03 12:30',
                    rzdbr:1,
                    tfdbr:2,
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    xyklx:'',
                    xykhm:'',
                    qtdjsj:'2017-01-02 12:30',
                    jssj:'2017-01-02 12:30',
                    rksj:'2017-01-02 12:30',
                    scsj:'2017-01-02 12:30'},
                data: [{
                    lkbh:'440723523534532',
                    th:'235346474535345',
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    cws:'123',
                    fjs:'204',
                    xj:'五星级',
                    zafzr:'李某某',
                    zt:'正常',
                    fdfzr:'张某某',
                    rzfh:'403',
                    rzsj:'2017-01-02 12:30',
                    tfsj:'2017-01-03 12:30',
                    rzdbr:1,
                    tfdbr:2,
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    xyklx:'',
                    xykhm:'',
                    qtdjsj:'2017-01-02 12:30',
                    jssj:'2017-01-02 12:30',
                    rksj:'2017-01-02 12:30',
                    scsj:'2017-01-02 12:30'
               },{
                    lkbh:'440723523534532',
                    th:'235346474535345',
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    cws:'123',
                    fjs:'204',
                    xj:'五星级',
                    zafzr:'李某某',
                    zt:'正常',
                    fdfzr:'张某某',
                    rzfh:'403',
                    rzsj:'2017-01-02 12:30',
                    tfsj:'2017-01-03 12:30',
                    rzdbr:1,
                    tfdbr:2,
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    xyklx:'',
                    xykhm:'',
                    qtdjsj:'2017-01-02 12:30',
                    jssj:'2017-01-02 12:30',
                    rksj:'2017-01-02 12:30',
                    scsj:'2017-01-02 12:30'
                },{
                    lkbh:'440723523534532',
                    th:'235346474535345',
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    cws:'123',
                    fjs:'204',
                    xj:'五星级',
                    zafzr:'李某某',
                    zt:'正常',
                    fdfzr:'张某某',
                    rzfh:'403',
                    rzsj:'2017-01-02 12:30',
                    tfsj:'2017-01-03 12:30',
                    rzdbr:1,
                    tfdbr:2,
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    xyklx:'',
                    xykhm:'',
                    qtdjsj:'2017-01-02 12:30',
                    jssj:'2017-01-02 12:30',
                    rksj:'2017-01-02 12:30',
                    scsj:'2017-01-02 12:30'
                },{
                    lkbh:'440723523534532',
                    th:'235346474535345',
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    cws:'123',
                    fjs:'204',
                    xj:'五星级',
                    zafzr:'李某某',
                    zt:'正常',
                    fdfzr:'张某某',
                    rzfh:'403',
                    rzsj:'2017-01-02 12:30',
                    tfsj:'2017-01-03 12:30',
                    rzdbr:1,
                    tfdbr:2,
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    xyklx:'',
                    xykhm:'',
                    qtdjsj:'2017-01-02 12:30',
                    jssj:'2017-01-02 12:30',
                    rksj:'2017-01-02 12:30',
                    scsj:'2017-01-02 12:30'
                },{
                    lkbh:'440723523534532',
                    th:'235346474535345',
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    cws:'123',
                    fjs:'204',
                    xj:'五星级',
                    zafzr:'李某某',
                    zt:'正常',
                    fdfzr:'张某某',
                    rzfh:'403',
                    rzsj:'2017-01-02 12:30',
                    tfsj:'2017-01-03 12:30',
                    rzdbr:1,
                    tfdbr:2,
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    xyklx:'',
                    xykhm:'',
                    qtdjsj:'2017-01-02 12:30',
                    jssj:'2017-01-02 12:30',
                    rksj:'2017-01-02 12:30',
                    scsj:'2017-01-02 12:30'
                },{
                    lkbh:'440723523534532',
                    th:'235346474535345',
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    cws:'123',
                    fjs:'204',
                    xj:'五星级',
                    zafzr:'李某某',
                    zt:'正常',
                    fdfzr:'张某某',
                    rzfh:'403',
                    rzsj:'2017-01-02 12:30',
                    tfsj:'2017-01-03 12:30',
                    rzdbr:1,
                    tfdbr:2,
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    xyklx:'',
                    xykhm:'',
                    qtdjsj:'2017-01-02 12:30',
                    jssj:'2017-01-02 12:30',
                    rksj:'2017-01-02 12:30',
                    scsj:'2017-01-02 12:30'
                },{
                    lkbh:'440723523534532',
                    th:'235346474535345',
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    cws:'123',
                    fjs:'204',
                    xj:'五星级',
                    zafzr:'李某某',
                    zt:'正常',
                    fdfzr:'张某某',
                    rzfh:'403',
                    rzsj:'2017-01-02 12:30',
                    tfsj:'2017-01-03 12:30',
                    rzdbr:1,
                    tfdbr:2,
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    xyklx:'',
                    xykhm:'',
                    qtdjsj:'2017-01-02 12:30',
                    jssj:'2017-01-02 12:30',
                    rksj:'2017-01-02 12:30',
                    scsj:'2017-01-02 12:30'
                },{
                    lkbh:'440723523534532',
                    th:'235346474535345',
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    cws:'123',
                    fjs:'204',
                    xj:'五星级',
                    zafzr:'李某某',
                    zt:'正常',
                    fdfzr:'张某某',
                    rzfh:'403',
                    rzsj:'2017-01-02 12:30',
                    tfsj:'2017-01-03 12:30',
                    rzdbr:1,
                    tfdbr:2,
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    xyklx:'',
                    xykhm:'',
                    qtdjsj:'2017-01-02 12:30',
                    jssj:'2017-01-02 12:30',
                    rksj:'2017-01-02 12:30',
                    scsj:'2017-01-02 12:30'
                }],
                option: {
                    dialogHeight: '100%',
                    dialogWidth: '100%',

                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    viewBtn: false,
                    delBtn: false,
                    editBtn:false,
                    card:true,
                    column: [
                        {
                            label: '姓名',
                            prop: 'xm',
                            search:true
                        },{
                            label: '证件号码',
                            prop: 'zjhm',
                           width:'160',
                            search:true
                        },
                        {
                            label: '性别',
                            prop: 'xb',
                            type:'select'
                        },{
                            label: '行政区划',
                            prop: 'xzqh',
                            type:'select',
                            dicData:dict.xzqh,
                            search:true
                        },{
                            label: '入住房号',
                            prop: 'rzfh',
                        },{
                            label: '入住时间',
                            prop: 'rzsj',
                        },{
                            label: '退房时间',
                            prop: 'tfsj',
                        },{
                            label: '旅馆名称',
                            prop: 'lgmc',
                        },{
                            label: '所属派出所',
                            prop: 'sspcs',
                            type:'select'
                        },{
                            label: '治安负责人',
                            prop: 'zafzr',
                        }


                    ],
                    group: [
                        {
                            label: '入住信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [
                                 {
                                    label: '姓名',
                                    prop: 'xm',
                                },{
                                    label: '证件类型',
                                    prop: 'zjlx',
                                    type:'select'
                                },{
                                    label: '证件号码',
                                    prop: 'zjhm',
                                }, {
                                    label: '姓名拼音',
                                    prop: 'xmpy',
                                },
                                {
                                    label: '民族',
                                    prop: 'mz',
                                    type:'select'
                                },{
                                    label: '性别',
                                    prop: 'xb',
                                    type:'select'
                                },{
                                    label: '行政区划',
                                    prop: 'xzqh',
                                    type:'select',
                                    dicData:dict.xzqh
                                },{
                                    label: '出生日期',
                                    prop: 'scrq',
                                },{
                                    label: '籍贯地址',
                                    prop: 'jgdz',
                                },
                                {
                                    label: '旅客编号',
                                    prop: 'lkbh',
                                }, {
                                    label: '团号',
                                    prop: 'th',
                                },
                                {
                                    label: '入住时间',
                                    prop: 'rzsj',
                                },{
                                    label: '退房时间',
                                    prop: 'tfsj',
                                },{
                                    label: '入住房间',
                                    prop: 'rzfh',
                                },{
                                    label: '入住当班人',
                                    prop: 'rzdbr',
                                },{
                                    label: '退房当班人',
                                    prop: 'tfdbr',
                                },{
                                    label: '信用卡类型',
                                    prop: 'xyklx',
                                },{
                                    label: '信用卡号码',
                                    prop: 'xykhm',
                                },{
                                    label: '登记时间',
                                    prop: 'qtdjsj',
                                },{
                                    label: '接收时间',
                                    prop: 'jssj',
                                },{
                                    label: '入库时间',
                                    prop: 'rksj',
                                },{
                                    label: '上传时间',
                                    prop: 'scsj',
                                }

                            ]
                        },
                        {   label: '旅馆信息',
                            prop: 'hotelInfo',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '旅馆名称',
                                    prop: 'lgmc',
                                },{
                                    label: '床位数',
                                    prop: 'cws',
                                },{
                                    label: '房间数',
                                    prop: 'fjs',
                                },{
                                    label: '星级',
                                    prop: 'xj',
                                },{
                                    label: '状态',
                                    prop: 'zt',
                                },{
                                    label: '治安负责人',
                                    prop: 'zafzr',
                                },{
                                    label: '所属派出所',
                                    prop: 'sspcs'
                                },{
                                    label: '法定负责人',
                                    prop: 'fdfzr',
                                },{
                                    label: '联系电话',
                                    prop: 'lxdh',
                                },{
                                    label: '旅馆地址',
                                    prop: 'lgdz',
                                    span:24
                                }
                            ]
                        },{
                            label: '照片信息',
                            prop: 'zpxx',
                            icon: 'el-icon-info',
                            column:[{
                                label: '照片',
                                prop: 'imgUi',
                                span:24,
                                slot:true,
                                header:false,
                                labelWidth:'-1'
                            }]
                        }

                        ]
                },
                optionImg: {
                    span:4,
                    align:'left',
                    data: [
                        {
                            title: '姓名：任某某',
                            imgsrc: '/img/hg.jpg',
                            content: '身份证：440101199909092234',
                        },
                        {
                            title: '江门七天酒店',
                            imgsrc: 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553075629632&di=440052f6fecf1e7e27a1ea03b7f40685&imgtype=0&src=http%3A%2F%2Fp2.pccoo.cn%2Fstore%2F20140617%2F2014061716513496878687.jpg',
                            content: '地址：广东省江门市莲江区环市街道',


                        },

                    ]
                },

            }
        },
        created(){

        },
        computed: {
            infoData() {
                return this.form.info
            }
        },
        methods: {
            openDialog(name) {
                this.recordView = true
                this.title = name

            },



        }
    }
</script>