<template>
    <div class="user">
        <basic-container>
            <el-row :span="24">
                <el-col :xs="24"
                        :sm="24"
                        :md="5"
                        class="user__tree">
                    <avue-tree :option="treeOption"
                               :data="treeData"
                               ></avue-tree>
                </el-col>
                <el-col :xs="24"
                        :sm="24"
                        :md="19"
                        class="user__main">
                    <avue-crud :option="option"
                               ref="crud"
                               v-model="form"
                               :page="page"
                               :table-loading="listLoading"
                               :data="data">
                        <template slot="qymc"
                                  slot-scope="scope">
                            <button type="button" class="el-button el-button--text el-button--small"
                                    @click="openDialog(scope.row)">{{scope.row.qymc}}
                            </button>
                        </template>
                        <template slot-scope="scope"
                                  slot="menu">
                            <el-button
                                    size="small"
                                    type="text"
                                    icon="el-icon-view"
                                    @click="openDialog(scope.row)">详情
                            </el-button>
                        </template>

                    </avue-crud>
                </el-col>
            </el-row>
        </basic-container>
        <el-dialog :title="title" fullscreen :visible.sync="view"  >
            <avue-detail :option="option" v-model="fromDetail" labelWidth="0">
                <template slot="imgUi">
                    <img src="/img/portal/clty.png"> &nbsp;&nbsp;&nbsp;
                    <img src="/img/portal/clty1.png">

                </template>

            </avue-detail>
        </el-dialog>

    </div>

</template>

<script>
    import avueUeditor from 'avue-plugin-ueditor/packages';
    Vue.use(avueUeditor);
    let span_len=6;
    export default {
        name: "search",
        data() {
            return {
                view:false,
                title:'预览',
                defaultProps: {
                    label: "label",
                    value: 'value',
                },
                treeOption: {
                    nodeKey: 'id',
                    addBtn: true,
                    menu: false,
                    props: {
                        label: 'label',
                        value: 'value'
                    }
                },
                treeData: [
                    {
                    id:0,
                    label:'特种行业',
                    value:0,
                    children:[
                        {
                            id:1,
                            label:'机修登记信息',
                            value:0,
                        },{
                            id:2,
                            label:'旅业入住信息',
                            value:0,
                        }

                    ]
                },{
                    id:3,
                    label:'保安管理',
                    value:0,
                    children:[
                        {
                            id:42,
                            label:'保安服务公司',
                            value:0,
                        },{
                            id:43,
                            label:'保安押运公司',
                            value:0,
                        },{
                            id:43,
                            label:'保安培训机构',
                            value:0,
                        },{
                            id:54,
                            label:'保安人员',
                            value:0,
                        }
                    ]
                },
                    {
                        id:11,
                        label:'案事件',
                        value:0,
                        children:[
                            {
                                id:121,
                                label:'案件信息',
                                value:0,
                            },
                            {
                                id:121,
                                label:'涉案单位',
                                value:0,
                            },{
                                id:131,
                                label:'涉案人员',
                                value:0,
                            },{
                                id:91,
                                label:'涉案物品',
                                value:0,
                            }
                        ]
                    }
                    ,{
                        id:6,
                        label:'人员信息',
                        value:0,
                        children:[
                            {
                                id:8,
                                label:'从业人员',
                                value:0,
                            },{
                                id:7,
                                label:'保安服务人员',
                                value:0,
                            },{
                                id:9,
                                label:'涉黄赌毒人员',
                                value:0,
                            },{
                                id:10,
                                label:'重点人员',
                                value:0,
                            },{
                                id:110,
                                label:'机动车驾驶员信息',
                                value:0,
                            },{
                                id:101,
                                label:'常住人口信息',
                                value:0,
                            },{
                                id:102,
                                label:'暂住人口信息',
                                value:0,
                            }
                        ]
                    }

                ],
                fromDetail:{},
                treeDeptData: [],
                checkedKeys: [],
                page: {
                    total: 234521, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                data: [
                    {
                        qymc:'金乐汽车销售有限公司',
                        ywzl:'1',
                        djsj:'2019-03-28 13:22',
                        hpzl:'03',
                        cph:'粤AD2013',
                        sbdh:'LJ13R6EJ69F001537',
                        clsyr:'陈柏佐',
                        cpxh:'江淮牌',
                        cllx:'重型自卸货车',
                        fdjh:'1107J048474',
                        csys:'主色：黄',
                        clxszgls:'280000',
                        scrzjlb:'大陆居民身份证',
                        scrzjhm:'440781197906123534',
                        scrxm:'麦海文',
                        scrsjhm:'13824001208',
                        scsj:'2019-03-28 11:17',
                        scrxm:'李三',
                        qcrzjlb:'大陆居民身份证',
                        qcrzjhm:'440781197906123534',
                        qcrxm:'麦海文',
                        qcrsjh:'13824001208',
                        qcsj:'2019-03-29 11:17',
                        gzbw:'暂无记录！'

                    },
                    {
                        qymc:'荷塘裕强车辆修配厂',
                        ywzl:'1',
                        djsj:'2019-03-28 13:22',
                        hpzl:'00',
                        cph:'粤TQR756',
                        sbdh:'LFMAP86CTE0419368',
                        clsyr:'吴清松',
                        cpxh:'卡罗拉',
                        cllx:'轿车',
                        fdjh:'F474026',
                        csys:'主色：灰',
                        clxszgls:'169638',
                        scrzjlb:'大陆居民身份证',
                        scrzjhm:'511602199304261910',
                        scrxm:'吴请松',
                        scrsjhm:'13178690198',
                        scsj:'2019-03-28 11:17',
                        scrxm:'李三',
                        qcrzjlb:'大陆居民身份证',
                        qcrzjhm:'511602199304261910',
                        qcrxm:'511602199304261910',
                        qcrsjh:'13178690198',
                        qcsj:'2019-03-29 11:17',
                        gzbw:'暂无记录！'

                    },
                    {
                        qymc:'荷塘裕强车辆修配厂',
                        ywzl:'1',
                        djsj:'2019-03-28 13:22',
                        hpzl:'00',
                        cph:'粤TQR756',
                        sbdh:'LFMAP86CTE0419368',
                        clsyr:'吴清松',
                        cpxh:'卡罗拉',
                        cllx:'轿车',
                        fdjh:'F474026',
                        csys:'主色：灰',
                        clxszgls:'169638',
                        scrzjlb:'大陆居民身份证',
                        scrzjhm:'511602199304261910',
                        scrxm:'吴请松',
                        scrsjhm:'13178690198',
                        scsj:'2019-03-28 11:17',
                        scrxm:'李三',
                        qcrzjlb:'大陆居民身份证',
                        qcrzjhm:'511602199304261910',
                        qcrxm:'511602199304261910',
                        qcrsjh:'13178690198',
                        qcsj:'2019-03-29 11:17',
                        gzbw:'暂无记录！'

                    },
                    {
                        qymc:'荷塘裕强车辆修配厂',
                        ywzl:'1',
                        djsj:'2019-03-28 13:22',
                        hpzl:'00',
                        cph:'粤TQR756',
                        sbdh:'LFMAP86CTE0419368',
                        clsyr:'吴清松',
                        cpxh:'卡罗拉',
                        cllx:'轿车',
                        fdjh:'F474026',
                        csys:'主色：灰',
                        clxszgls:'169638',
                        scrzjlb:'大陆居民身份证',
                        scrzjhm:'511602199304261910',
                        scrxm:'吴请松',
                        scrsjhm:'13178690198',
                        scsj:'2019-03-28 11:17',
                        scrxm:'李三',
                        qcrzjlb:'大陆居民身份证',
                        qcrzjhm:'511602199304261910',
                        qcrxm:'511602199304261910',
                        qcrsjh:'13178690198',
                        qcsj:'2019-03-29 11:17',
                        gzbw:'暂无记录！'

                    },
                    {
                        qymc:'荷塘裕强车辆修配厂',
                        ywzl:'1',
                        djsj:'2019-03-28 13:22',
                        hpzl:'00',
                        cph:'粤TQR756',
                        sbdh:'LFMAP86CTE0419368',
                        clsyr:'吴清松',
                        cpxh:'卡罗拉',
                        cllx:'轿车',
                        fdjh:'F474026',
                        csys:'主色：灰',
                        clxszgls:'169638',
                        scrzjlb:'大陆居民身份证',
                        scrzjhm:'511602199304261910',
                        scrxm:'吴请松',
                        scrsjhm:'13178690198',
                        scsj:'2019-03-28 11:17',
                        scrxm:'李三',
                        qcrzjlb:'大陆居民身份证',
                        qcrzjhm:'511602199304261910',
                        qcrxm:'511602199304261910',
                        qcrsjh:'13178690198',
                        qcsj:'2019-03-29 11:17',
                        gzbw:'暂无记录！'

                    },
                    {
                        qymc:'荷塘裕强车辆修配厂',
                        ywzl:'1',
                        djsj:'2019-03-28 13:22',
                        hpzl:'00',
                        cph:'粤TQR756',
                        sbdh:'LFMAP86CTE0419368',
                        clsyr:'吴清松',
                        cpxh:'卡罗拉',
                        cllx:'轿车',
                        fdjh:'F474026',
                        csys:'主色：灰',
                        clxszgls:'169638',
                        scrzjlb:'大陆居民身份证',
                        scrzjhm:'511602199304261910',
                        scrxm:'吴请松',
                        scrsjhm:'13178690198',
                        scsj:'2019-03-28 11:17',
                        scrxm:'李三',
                        qcrzjlb:'大陆居民身份证',
                        qcrzjhm:'511602199304261910',
                        qcrxm:'511602199304261910',
                        qcrsjh:'13178690198',
                        qcsj:'2019-03-29 11:17',
                        gzbw:'暂无记录！'

                    },
                    {
                        qymc:'荷塘裕强车辆修配厂',
                        ywzl:'1',
                        djsj:'2019-03-28 13:22',
                        hpzl:'00',
                        cph:'粤TQR756',
                        sbdh:'LFMAP86CTE0419368',
                        clsyr:'吴清松',
                        cpxh:'卡罗拉',
                        cllx:'轿车',
                        fdjh:'F474026',
                        csys:'主色：灰',
                        clxszgls:'169638',
                        scrzjlb:'大陆居民身份证',
                        scrzjhm:'511602199304261910',
                        scrxm:'吴请松',
                        scrsjhm:'13178690198',
                        scsj:'2019-03-28 11:17',
                        scrxm:'李三',
                        qcrzjlb:'大陆居民身份证',
                        qcrzjhm:'511602199304261910',
                        qcrxm:'511602199304261910',
                        qcrsjh:'13178690198',
                        qcsj:'2019-03-29 11:17',
                        gzbw:'暂无记录！'

                    },
                    {
                        qymc:'荷塘裕强车辆修配厂',
                        ywzl:'1',
                        djsj:'2019-03-28 13:22',
                        hpzl:'00',
                        cph:'粤TQR756',
                        sbdh:'LFMAP86CTE0419368',
                        clsyr:'吴清松',
                        cpxh:'卡罗拉',
                        cllx:'轿车',
                        fdjh:'F474026',
                        csys:'主色：灰',
                        clxszgls:'169638',
                        scrzjlb:'大陆居民身份证',
                        scrzjhm:'511602199304261910',
                        scrxm:'吴请松',
                        scrsjhm:'13178690198',
                        scsj:'2019-03-28 11:17',
                        scrxm:'李三',
                        qcrzjlb:'大陆居民身份证',
                        qcrzjhm:'511602199304261910',
                        qcrxm:'511602199304261910',
                        qcrsjh:'13178690198',
                        qcsj:'2019-03-29 11:17',
                        gzbw:'暂无记录！'

                    },
                    {
                        qymc:'荷塘裕强车辆修配厂',
                        ywzl:'1',
                        djsj:'2019-03-28 13:22',
                        hpzl:'00',
                        cph:'粤TQR756',
                        sbdh:'LFMAP86CTE0419368',
                        clsyr:'吴清松',
                        cpxh:'卡罗拉',
                        cllx:'轿车',
                        fdjh:'F474026',
                        csys:'主色：灰',
                        clxszgls:'169638',
                        scrzjlb:'大陆居民身份证',
                        scrzjhm:'511602199304261910',
                        scrxm:'吴请松',
                        scrsjhm:'13178690198',
                        scsj:'2019-03-28 11:17',
                        scrxm:'李三',
                        qcrzjlb:'大陆居民身份证',
                        qcrzjhm:'511602199304261910',
                        qcrxm:'511602199304261910',
                        qcrsjh:'13178690198',
                        qcsj:'2019-03-29 11:17',
                        gzbw:'暂无记录！'

                    },
                    {
                        qymc:'荷塘裕强车辆修配厂',
                        ywzl:'1',
                        djsj:'2019-03-28 13:22',
                        hpzl:'00',
                        cph:'粤TQR756',
                        sbdh:'LFMAP86CTE0419368',
                        clsyr:'吴清松',
                        cpxh:'卡罗拉',
                        cllx:'轿车',
                        fdjh:'F474026',
                        csys:'主色：灰',
                        clxszgls:'169638',
                        scrzjlb:'大陆居民身份证',
                        scrzjhm:'511602199304261910',
                        scrxm:'吴请松',
                        scrsjhm:'13178690198',
                        scsj:'2019-03-28 11:17',
                        scrxm:'李三',
                        qcrzjlb:'大陆居民身份证',
                        qcrzjhm:'511602199304261910',
                        qcrxm:'511602199304261910',
                        qcrsjh:'13178690198',
                        qcsj:'2019-03-29 11:17',
                        gzbw:'暂无记录！'

                    }

                ],
                option:   {
                    dialogFullscreen: true,
                    dialogHeight:'100%',
                    border: true,
                    index: true,
                    delBtn:false,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    editBtn: true,
                    align: 'center',
                    addBtn: false,
                    editBtn:false,
                    viewBtn:false,
                    menuWidth:'120',
                    card: true,
                    column: [
                        {
                            label: '企业名称',
                            prop: 'qymc',
                            search: true,
                            slot:true,
                            overHidden:true,
                            width:'auto'

                        },{
                            label: '业务种类',
                            prop: 'ywzl',
                            search: true,
                            dicData:[
                                {label:'回收',value:'0'},
                                {label:'维修',value:'1'},
                                {label:'租赁',value:'2'},
                                {label:'交易',value:'3'},
                                {label:'其他',value:'4'}
                                ],
                            type:'select',
                            searchFilterable:true

                        },{
                            label: '号牌种类',
                            prop: 'hpzl',
                            dicData:[
                                {label:'小型汽车',value:'00'},
                                {label:'无牌',value:'01'},
                                {label:'新车未上牌',value:'02'},
                                {label:'大型汽车',value:'03'},
                                {label:'使馆汽车',value:'1' },
                                {label:'领馆汽车',value:'2' },
                                {label:'境外汽车',value:'3' },
                                {label:'外籍汽车',value:'4' },
                                {label:'两、三轮摩托车',value:'5' },
                                {label:'轻便摩托车',value:'6' },
                                {label:'使馆摩托车',value:'7' },
                                {label:'领馆摩托车',value:'8' },
                                {label:'境外摩托车',value:'9' },
                                {label:'外籍摩托车',value:'10' },
                                {label:'农用运输车类',value:'11' },
                                {label:'拖拉机',value:'12' },
                                {label:'挂车',value:'13' },
                                {label:'教练汽车',value:'14' },
                                {label:'教练摩托车',value:'15' },
                                {label:'试验汽车',value:'16' },
                                {label:'试验摩托车',value:'17' },
                                {label:'临时入境汽车',value:'18' },
                                {label:'临时入境摩托车',value:'19' },
                                {label:'临时行使车',value:'20' },
                                {label:'警用汽车号牌',value:'21' },
                                {label:'警用摩托车号牌',value:'22' },
                                {label:'军用车辆号牌',value:'23' },
                                {label:'其他号牌',value:'24' },
                            ],
                            search:true,
                            type:'select',
                            searchFilterable:true
                        },{
                            label: '车牌号',
                            prop: 'cph',
                            search:true,
                        },{
                            label: '识别代号/车架号',
                            prop: 'sbdh',
                            hide:true,
                        },{
                            label: '车辆所有人',
                            prop: 'clsyr',
                        },{
                            label: '厂牌型号',
                            prop: 'cpxh',
                            hide:true,
                        },{
                            label: '车辆类型',
                            prop: 'cllx',
                            dicData:[
                                {label:'轿车',value:'0'},
                                {label:'重型厢式货车',value:'1'},
                                {label:'中型厢式半挂车',value:'2'},
                                {label:'中型厢式货车',value:'3'},
                            ]
                        },{
                            label: '登记时间',
                            prop: 'djsj',
                            search: true,
                            type: "daterange",
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',

                        },{
                            label: '发动机号',
                            prop: 'fdjh',
                            hide:true,
                        },{
                            label: '车身颜色',
                            prop: 'csys',
                            hide:true,
                        },{
                            label: '车辆行驶总公里数',
                            prop: 'clxszgls',
                            hide:true,
                        },{
                            label: '送车人证件类别',
                            prop: 'scrzjlb',
                            hide:true,
                        },{
                            label: '送车人证件号',
                            prop: 'scrzjhm',
                            hide:true,
                        },{
                            label: '送车人姓名',
                            prop: 'scrxm',
                            hide:true,
                        },{
                            label: '送车人手机号',
                            prop: 'scrsjhm',
                            hide:true,
                        },{
                            label: '收车时间',
                            prop: 'scsj',
                            hide:true,
                        },{
                            label: '收车人姓名',
                            prop: 'scrxm',
                            hide:true,
                        },{
                            label: '取车人证件类别',
                            prop: 'qcrzjlb',
                            hide:true,
                        },{
                            label: '取车人证件号',
                            prop: 'qcrzjhm',
                            hide:true,
                        },{
                            label: '取车人姓名',
                            prop: 'qcrxm',
                            hide:true,
                        },{
                            label: '取车人手机号',
                            prop: 'qcrsjh',
                            hide:true,
                        },{
                            label: '取车时间',
                            prop: 'qcsj',
                            hide:true,
                        }


                    ],
                    group:[
                        {
                        label: '车辆基本信息',
                        prop: 'cljbxx',
                        icon: 'el-icon-info',
                        column: [
                            {
                                label: '企业名称',
                                prop: 'qymc',
                                search: true,

                                span:span_len

                            },{
                                label: '业务种类',
                                prop: 'ywzl',
                                search: true,
                                dicData:[
                                    {label:'回收',value:'0'},
                                    {label:'维修',value:'1'},
                                    {label:'租赁',value:'2'},
                                    {label:'交易',value:'3'},
                                    {label:'其他',value:'4'}
                                ],
                                type:'select',
                                searchFilterable:true,
                                span:span_len

                            },{
                                label: '登记时间',
                                prop: 'djsj',
                                search: true,
                                type: "daterange",
                                format: 'yyyy-MM-dd',
                                valueFormat: 'yyyy-MM-dd',
                                span:span_len

                            },{
                                label: '号牌种类',
                                prop: 'hpzl',
                                dicData:[
                                    {label:'小型汽车',value:'00'},
                                    {label:'无牌',value:'01'},
                                    {label:'新车未上牌',value:'02'},
                                    {label:'大型汽车',value:'03'},
                                    {label:'使馆汽车',value:'1' },
                                    {label:'领馆汽车',value:'2' },
                                    {label:'境外汽车',value:'3' },
                                    {label:'外籍汽车',value:'4' },
                                    {label:'两、三轮摩托车',value:'5' },
                                    {label:'轻便摩托车',value:'6' },
                                    {label:'使馆摩托车',value:'7' },
                                    {label:'领馆摩托车',value:'8' },
                                    {label:'境外摩托车',value:'9' },
                                    {label:'外籍摩托车',value:'10' },
                                    {label:'农用运输车类',value:'11' },
                                    {label:'拖拉机',value:'12' },
                                    {label:'挂车',value:'13' },
                                    {label:'教练汽车',value:'14' },
                                    {label:'教练摩托车',value:'15' },
                                    {label:'试验汽车',value:'16' },
                                    {label:'试验摩托车',value:'17' },
                                    {label:'临时入境汽车',value:'18' },
                                    {label:'临时入境摩托车',value:'19' },
                                    {label:'临时行使车',value:'20' },
                                    {label:'警用汽车号牌',value:'21' },
                                    {label:'警用摩托车号牌',value:'22' },
                                    {label:'军用车辆号牌',value:'23' },
                                    {label:'其他号牌',value:'24' },
                                ],
                                search:true,
                                type:'select',
                                searchFilterable:true,
                                span:span_len
                            },{
                                label: '车牌号',
                                prop: 'cph',
                                search:true,
                                span:span_len
                            },{
                                label: '识别代号/车架号',
                                prop: 'sbdh',
                                hide:true,
                                span:span_len
                            },{
                                label: '车辆所有人',
                                prop: 'clsyr',
                                span:span_len
                            },{
                                label: '厂牌型号',
                                prop: 'cpxh',
                                hide:true,
                                span:span_len
                            },{
                                label: '车辆类型',
                                prop: 'cllx',
                                dicData:[
                                    {label:'轿车',value:'0'},
                                    {label:'重型厢式货车',value:'1'},
                                    {label:'中型厢式半挂车',value:'2'},
                                    {label:'中型厢式货车',value:'3'},
                                ],
                                span:span_len
                            },{
                                label: '发动机号',
                                prop: 'fdjh',
                                hide:true,
                                span:span_len
                            },{
                                label: '车身颜色',
                                prop: 'csys',
                                hide:true,
                                span:span_len
                            },{
                                label: '车辆行驶总公里数',
                                prop: 'clxszgls',
                                hide:true,
                                span:span_len
                            }
                        ]
                    },
                        {
                            label: '车辆交接信息',
                            prop: 'cljjxx',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '送车人证件类别',
                                    prop: 'scrzjlb',
                                    hide:true,
                                    span:span_len
                                },{
                                    label: '送车人证件号',
                                    prop: 'scrzjhm',
                                    hide:true,
                                    span:span_len
                                },{
                                    label: '送车人姓名',
                                    prop: 'scrxm',
                                    hide:true,
                                    span:span_len
                                },{
                                    label: '送车人手机号',
                                    prop: 'scrsjhm',
                                    hide:true,
                                    span:span_len
                                },{
                                    label: '收车时间',
                                    prop: 'scsj',
                                    hide:true,
                                    span:span_len
                                },{
                                    label: '收车人姓名',
                                    prop: 'scrxm',
                                    hide:true,
                                    span:span_len
                                },{
                                    label: '取车人证件类别',
                                    prop: 'qcrzjlb',
                                    hide:true,
                                    span:span_len
                                },{
                                    label: '取车人证件号',
                                    prop: 'qcrzjhm',
                                    hide:true,
                                    span:span_len
                                },{
                                    label: '取车人姓名',
                                    prop: 'qcrxm',
                                    hide:true,
                                    span:span_len
                                },{
                                    label: '取车人手机号',
                                    prop: 'qcrsjh',
                                    hide:true,
                                    span:span_len
                                },{
                                    label: '取车时间',
                                    prop: 'qcsj',
                                    hide:true,
                                    span:span_len
                                }
                            ]
                        },
                        {
                            label: '车辆损毁或改装部位',
                            prop: 'gzbw',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '损毁或改装',
                                    prop: 'gzbw',
                                    hide:true,
                                    span:24
                                }
                            ]
                        },{
                            label: '车身图片',
                            prop: 'cstp',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '车身图片',
                                    prop: 'imgUi',
                                    hide:true,
                                    span:24,
                                    slot:true
                                }
                            ]
                        }
                    ],


                },
                listLoading: false,
                form: {},
            };
        },


        created() {

        },
        methods: {
            openDialog(row){
                this.view=true
                    this.title=row.qymc
                this.fromDetail=row

            },
            handleDel(row, index){
                var _this = this
                this.$confirm('是否确认删除该记录吗"?', '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    _this.$message({
                        showClose: true,
                        message: '删除成功',
                        type: 'success'
                    })
                }).catch(function (err) {
                })
            }




        }
    };
</script>
<style lang="scss">
    .user {
        height: 100%;

        &__tree {
            padding-top: 3px;
            padding-right: 20px;
        }

        &__main {
            .el-card__body {
                padding-top: 0;
            }
        }
    }
</style>

