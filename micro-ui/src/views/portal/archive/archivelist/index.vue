<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-crud :option="option" :data="data"
                       :page="page"
                       @row-update="handleOpt"
                       @row-save="handleSav"
                       v-model="form">
                <template slot="search">
                    <div class="el-form-item">
                        <el-form-item label="积分">
                            <div class="el-form-item__content">
                                <div class="el-tooltip avue-date" aria-describedby="el-tooltip-6185" tabindex="0">
                                    <div class="el-date-editor el-range-editor el-input__inner el-date-editor--daterange el-range-editor--small">
                                        <input autocomplete="off"  placeholder="大于" type="number"
                                               name=""
                                               class="el-range-input"><span
                                            class="el-range-separator">至</span><input  type="number" autocomplete="off" placeholder="小于"
                                                                                       name=""  class="el-range-input"></div>
                                </div></div>
                        </el-form-item>
                    </div>

                </template>
                <template slot="dwmc"
                          slot-scope="scope">
                    <button type="button" class="el-button el-button--text el-button--small"
                            @click="openUnitDialog(scope.row.dwmc)">{{scope.row.dwmc}}
                    </button>
                </template>
                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click=" preview(scope.row.dwmc)">档案预览
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog :title="unitTitle" fullscreen :visible.sync="unitView" append-to-body>
            <avue-tabs :option="optionTap" @change="handleChange"></avue-tabs>
            <span v-if="type.prop==='unitInfo'">
                <avue-detail :option="optionForm" v-model="fromDetail" labelWidth="0">
                    <template slot="imgUi">
                       <avue-data-imgtext :option="optionImg"></avue-data-imgtext>
                    </template>

                </avue-detail>

            </span>
            <span v-if="type.prop==='unitBaseInfo'">

                <avue-detail :option="optionBase" v-model="fromBaseDetail">
                    <template slot="imgBase">
                       <avue-data-imgtext :option="optionImg"></avue-data-imgtext>

                    </template>
                </avue-detail>

            </span>
            <span v-else-if="type.prop==='jfLevel'">
              <jf-level/>
            </span>
            <span v-else-if="type.prop==='zhengZhao'">
               <licence-info/>
            </span>
            <span v-else-if="type.prop==='sheAn'">
                <case-info/>
            </span>
            <span v-else-if="type.prop==='zhuSu'">
                <hotel-record/>
            </span>
            <span v-else-if="type.prop==='daKa'">
               <clock-info/>
            </span>

            <span v-else-if="type.prop==='cyryInfo'">
                <cyry-info/>

            </span>
            <span v-else-if="type.prop==='zajcInfo'">
                <zajc-info/>
            </span>
            <span v-else-if="type.prop==='xfjcInfo'">
                <xfjc-info/>
            </span>
            <span v-else-if="type.prop==='log'">

              <log-info/>
            </span>
            <span v-else-if="type.prop==='analy'">
                <analy-info/>
                <!-- <avue-tabs :option="optionAnaly" @change="handleAnalyChange"/>
                       <span v-if="analyType.prop==='sheHuang'">
                       涉黄分析
                   </span>
                   <span v-else-if="analyType.prop==='sheDu4'">
                      涉赌分析

                   </span>
                   <span v-else-if="analyType.prop==='sheDu2'">
                       涉毒分析
                   </span>
                   <span v-else-if="analyType.prop==='sheWei'">
                       涉维分析
                   </span>-->



            </span>

            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="unitView=false">关 闭
                </el-button>
            </div>
        </el-dialog>


    </div>
</template>


<script>
    let dict = {
        dwlb: [
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
        ],
        yes_no: [{
            label: '是',
            value: '0'
        }, {
            label: '否',
            value: '1'
        }],
        hylb: [{
            label: '个体经营',
            value: '01'
        },
            {
                label: '有限公司',
                value: '02'
            }],
        yqzt: [
            {
                label: '歇业',
                value: '01'
            },
            {
                label: '关闭',
                value: '02'
            },
            {
                label: '正常',
                value: '03'
            }
        ],
        pcs: [{
            label: '白沙派出所',
            value: '440703500000'
        }, {
            label: '北街派出所',
            value: '440703510000'
        },
            {
                label: '仓后派出所',
                value: '440703520000'
            }],
        zjlx: [{
            label: '公民身份证',
            value: '1',
        }, {
            label: '港澳通行证',
            value: '2',
        }

        ],
        jwq: [
            {
                label: '甘化社区警务区',
                value: '1',
            },
            {
                label: '海傍社区警务区',
                value: '2',
            },
            {
                label: '常安社区警务区',
                value: '3',
            }

        ],
        sq: [
            {
                label: '虎山',
                value: '1'
            },
            {
                label: '黄屋',
                value: '2'
            }, {
                label: '建新',
                value: '3'
            }

        ],
        jjlx: [{
            label: '内资',
            value: '01',
        },
            {
                label: '国有全资',
                value: '02',
            },
            {
                label: '集体全资',
                value: '03',
            }],

    }
    let jbxx_label = 120, jbxx_span = 8, dzxx_label = 200, dzxx_span = 8;

    import cyryInfo from '@/views/portal/unit/basis/practitioners/index';
    import zajcInfo from '@/views/portal/unit/inspect/register/dailyIndex';
    import xfjcInfo from '@/views/portal/unit/inspect/register/fireIndex';
    import logInfo from '@/views/portal/log/history/index';
    import hotelRecord from '@/views/portal/archive/unit-record/hotelRecord';
    import licenceInfo from '@/views/portal/archive/licence/licenceIndex';
    import clockInfo from '@/views/portal/archive/clock-on-off/clockIndex';
    import caseInfo from '@/views/portal/archive/case/caseIndex';
    import analyInfo from '@/views/portal/archive/analy/analyIndex';
    import jfLevel from '@/views/portal/point/rating_result/index';

    export default {
        name: "archivelist",
        components: {
            "cyryInfo": cyryInfo,
            "zajcInfo": zajcInfo,
            "xfjcInfo": xfjcInfo,
            "logInfo": logInfo,
            "hotelRecord": hotelRecord,
            "licenceInfo": licenceInfo,
            "clockInfo": clockInfo,
            "caseInfo": caseInfo,
            "analyInfo": analyInfo,
            "jfLevel":jfLevel
        },
        data() {

            return {
                type: {},
                analyType: {},
                optionImg: {
                    span: 4,
                    align: 'left',
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
                optionAnaly: {
                    column: [{
                        icon: 'el-icon-info',
                        label: '涉黄分析',
                        prop: 'sheHuang',
                    },
                        {
                            icon: 'el-icon-info',
                            label: '涉黄分析',
                            prop: 'sheDu4',
                        },
                        {
                            icon: 'el-icon-info',
                            label: '涉毒分析',
                            prop: 'sheDu2',
                        },
                        {
                            icon: 'el-icon-info',
                            label: '涉维分析',
                            prop: 'sheWei',
                        },
                    ]
                },
                optionTap: {
                    column: [{
                        icon: 'el-icon-info',
                        label: '基本信息',
                        prop: 'unitInfo',
                    }, {
                        icon: 'el-icon-info',
                        label: '旅馆业',
                        prop: 'unitBaseInfo',
                    }, {
                        icon: 'el-icon-bell',
                        label: '证照扫描（2）',
                        prop: 'zhengZhao',
                    }, {
                        icon: 'el-icon-bell',
                        label: '积分等级(4)',
                        prop: 'jfLevel',
                    },
                        {
                            icon: 'el-icon-bell',
                            label: '涉案（4）',
                            prop: 'sheAn',
                        },
                        {
                            icon: 'el-icon-bell',
                            label: '登记信息（23515）',
                            prop: 'zhuSu',
                        },
                        {
                            icon: 'el-icon-bell',
                            label: '上下班打卡（4356）',
                            prop: 'daKa',
                        },
                        {
                            icon: 'el-icon-bell',
                            label: '从业人员（2）',
                            prop: 'cyryInfo',
                        }, {
                            icon: 'el-icon-bell',
                            label: '治安检查（4）',
                            prop: 'zajcInfo',
                        }, {
                            icon: 'el-icon-bell',
                            label: '消防检查（4）',
                            prop: 'xfjcInfo',
                        }
                        /*, {
                            icon: 'el-icon-bell',
                            label: '智能分析（4）',
                            prop: 'analy',
                        }*/
                        , {
                            icon: 'el-icon-bell',
                            label: '日志变更（10）',
                            prop: 'log',
                        }]
                },

                page: {
                    total: 10, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                unitView: false,
                unitTitle: '单位信息',
                form: {},
                fromDetail: {
                    dwmc: '江门市七天酒店',
                    hylb: '01',
                    dwlb: '01',
                    jjlx: '01',
                    dzxz: '广东省江门市莲江区XXX路XX号',
                    fddbr: '张某某',
                    zafzr: '李某某',
                    zzjgdm: '440703520000',
                    lxdh: '0750-8812344',
                    qyzt: '03',
                    gxpsc: '440703520000',
                    clrq: '2017-01-02 12:00',
                    wzjy: '0',
                    zddw: '0',
                    swdw: '0',
                    kddsd: '0',
                    jydssxq: '广东省江门市莲江区',
                    xzjd: '环市街道',
                    mplh: 'XX号',
                    jlxc: 'XX村',
                    jydz: '广东省江门市莲江区环市街道',
                    jydzbz: '广东省江门市莲江区环市街道',
                    ccdzyjjdzyz: '1',
                    ssxq: '广东省江门市莲江区',
                    sssq: '1',
                    zcdz: '广东省江门市莲江区环市街道',
                    fdfzrxm: '张某某',
                    fdfzrzjzl: '1',
                    fdfzrzjhm: '441323199009091234',
                    fdfzrlxdh: '0750-8000001',
                    fdfzrsjhm: '15915719990',
                    dwfzrxm: '张某某',
                    dwfzrzjzl: '1',
                    dwfzrzjhm: '441323199009091234',
                    dwfzrlxdh: '0750-8000001',
                    dwfzrsjhm: '15915719990',
                    zafzrxm: '李某某',
                    zafzrzjzl: '1',
                    zafzrlxdh: '0750-8876321',
                    zafzrsjhm: '15788990090',
                    zafzrzjhm: '440304199009091234',
                    fddbryz: '0',
                    xffzrglr: '吴某某',
                    glrlxdh: '13413099008',
                    aqzzr: '孙某某',
                    aqzrrlxdh: '15799003943',
                    remark: '需要重点关注',
                    xzqh:'江门市莲江区',
                    zhjcsj:'2019-03-25',
                    jf:'90',
                    sfsd:'1',
                    sfsh:'1',
                    sfswe:'1'
                },
                fromBaseDetail: {
                    dwmc: '江门市七天酒店',
                    jymc: '江门市七天酒店',
                    jyzt: '正常',
                    dwxz: '内资',
                    hylb: '宾馆',
                    kyrq: '2017-01-02 12:00',
                    lxdh: '2017-01-02 12:00',
                    xj: '五星',
                    fjs: '123',
                    cws: '454',
                    djd: 'A级',
                    sjzgbm: '无',
                    abxm: '张某某',
                    ablxdh: '1348236423652',
                    xqpcs: '仓后派出所',
                    gajg: '仓后派出所',
                    zrmjjh: '523545',
                    zrmjxm: '李某某',
                    jyxmzy: '旅业',
                    jyxmjy: '无',
                    bjydw: '无',
                    fddbrxm: '张某某',
                    fddbrzjzl: '公民身份证',
                    fddbrlxdh: '15915719990',
                    fddbrzjhm: '441323199009091234',
                    sjjyrxm: '张某某',
                    sjjyrzjzl: '公民身份证',
                    sjjyrlxdh: '15915719990',
                    sjjyrzjhm: '441323199009091234',
                    xzqh: '广东省江门市莲江区',
                    jlx: 'XX村',
                    ssjwq: '广东省江门市莲江区',
                    sssq: '虎山',
                    mpl: 'XX号',
                    xxdz: '广东省江门市莲江区环市街',
                    jjdz: '广东省江门市莲江区环市街',
                    cyrs: '12',
                    zapxrs: '2',
                    baryzs: '3',
                    zjbas: '4',
                    baly: '自建',
                    bapzdw: '无',
                    tgswfw: '是',
                    tgswdn: '是',
                    swjksl: '123',
                    wljrs: '电信',
                    swdnzs: '123',
                    swfs: 'wifi',
                    ipdz: '127.0.0.1',
                    wgfzr: '张某',
                    wlaqsb: '是',
                    bz: '需要重点关注',
                    lrr: '录入人',
                    lrrq: '录入日期',
                    lrqk: '录入情况',
                    zhxgr: '最后修改人',
                    zhxgsj: '最后修改时间',
                    mj: '123',
                    lcs: '10',
                    ds: '2',

                },
                data: [{
                    dwmc: '江门市七天酒店',
                    hylb: '01',
                    dwlb: '01',
                    jjlx: '01',
                    dzxz: '广东省江门市莲江区XXX路XX号',
                    fddbr: '张某某',
                    zafzr: '李某某',
                    zzjgdm: '440703520000',
                    lxdh: '0750-8812344',
                    qyzt: '03',
                    gxpsc: '440703520000',
                    clrq: '2017-01-02 12:00',
                    wzjy: '0',
                    zddw: '0',
                    swdw: '0',
                    kddsd: '0',
                    jydssxq: '广东省江门市莲江区',
                    xzjd: '环市街道',
                    mplh: 'XX号',
                    jlxc: 'XX寸',
                    jydz: '广东省江门市莲江区环市街道',
                    jydzbz: '广东省江门市莲江区环市街道',
                    ccdzyjjdzyz: '1',
                    ssxq: '广东省江门市莲江区',
                    sssq: '1',
                    zcdz: '广东省江门市莲江区环市街道',
                    fdfzrxm: '张某某',
                    fdfzrzjzl: '1',
                    fdfzrzjhm: '441323199009091234',
                    fdfzrlxdh: '0750-8000001',
                    fdfzrsjhm: '15915719990',
                    dwfzrxm: '张某某',
                    dwfzrzjzl: '1',
                    dwfzrzjhm: '441323199009091234',
                    dwfzrlxdh: '0750-8000001',
                    dwfzrsjhm: '15915719990',
                    zafzrxm: '李某某',
                    zafzrzjzl: '1',
                    zafzrlxdh: '0750-8876321',
                    zafzrsjhm: '15788990090',
                    zafzrzjhm: '440304199009091234',
                    fddbryz: '0',
                    xffzrglr: '吴某某',
                    glrlxdh: '13413099008',
                    aqzzr: '孙某某',
                    aqzrrlxdh: '15799003943',
                    remark: '需要重点关注',
                    xzqh:'江门市莲江区',
                    zhjcsj:'2019-03-25',
                    jf:'90',
                    sfsd:'1',
                    sfsh:'1',
                    sfswe:'1'
                },{
                    dwmc: '江门市七天酒店',
                    hylb: '01',
                    dwlb: '01',
                    jjlx: '01',
                    dzxz: '广东省江门市莲江区XXX路XX号',
                    fddbr: '张某某',
                    zafzr: '李某某',
                    zzjgdm: '440703520000',
                    lxdh: '0750-8812344',
                    qyzt: '03',
                    gxpsc: '440703520000',
                    clrq: '2017-01-02 12:00',
                    wzjy: '0',
                    zddw: '0',
                    swdw: '0',
                    kddsd: '0',
                    jydssxq: '广东省江门市莲江区',
                    xzjd: '环市街道',
                    mplh: 'XX号',
                    jlxc: 'XX寸',
                    jydz: '广东省江门市莲江区环市街道',
                    jydzbz: '广东省江门市莲江区环市街道',
                    ccdzyjjdzyz: '1',
                    ssxq: '广东省江门市莲江区',
                    sssq: '1',
                    zcdz: '广东省江门市莲江区环市街道',
                    fdfzrxm: '张某某',
                    fdfzrzjzl: '1',
                    fdfzrzjhm: '441323199009091234',
                    fdfzrlxdh: '0750-8000001',
                    fdfzrsjhm: '15915719990',
                    dwfzrxm: '张某某',
                    dwfzrzjzl: '1',
                    dwfzrzjhm: '441323199009091234',
                    dwfzrlxdh: '0750-8000001',
                    dwfzrsjhm: '15915719990',
                    zafzrxm: '李某某',
                    zafzrzjzl: '1',
                    zafzrlxdh: '0750-8876321',
                    zafzrsjhm: '15788990090',
                    zafzrzjhm: '440304199009091234',
                    fddbryz: '0',
                    xffzrglr: '吴某某',
                    glrlxdh: '13413099008',
                    aqzzr: '孙某某',
                    aqzrrlxdh: '15799003943',
                    remark: '需要重点关注',
                    xzqh:'江门市莲江区',
                    zhjcsj:'2019-03-25',
                    jf:'90',
                    sfsd:'1',
                    sfsh:'1',
                    sfswe:'1'
                },{
                    dwmc: '江门市七天酒店',
                    hylb: '01',
                    dwlb: '01',
                    jjlx: '01',
                    dzxz: '广东省江门市莲江区XXX路XX号',
                    fddbr: '张某某',
                    zafzr: '李某某',
                    zzjgdm: '440703520000',
                    lxdh: '0750-8812344',
                    qyzt: '03',
                    gxpsc: '440703520000',
                    clrq: '2017-01-02 12:00',
                    wzjy: '0',
                    zddw: '0',
                    swdw: '0',
                    kddsd: '0',
                    jydssxq: '广东省江门市莲江区',
                    xzjd: '环市街道',
                    mplh: 'XX号',
                    jlxc: 'XX寸',
                    jydz: '广东省江门市莲江区环市街道',
                    jydzbz: '广东省江门市莲江区环市街道',
                    ccdzyjjdzyz: '1',
                    ssxq: '广东省江门市莲江区',
                    sssq: '1',
                    zcdz: '广东省江门市莲江区环市街道',
                    fdfzrxm: '张某某',
                    fdfzrzjzl: '1',
                    fdfzrzjhm: '441323199009091234',
                    fdfzrlxdh: '0750-8000001',
                    fdfzrsjhm: '15915719990',
                    dwfzrxm: '张某某',
                    dwfzrzjzl: '1',
                    dwfzrzjhm: '441323199009091234',
                    dwfzrlxdh: '0750-8000001',
                    dwfzrsjhm: '15915719990',
                    zafzrxm: '李某某',
                    zafzrzjzl: '1',
                    zafzrlxdh: '0750-8876321',
                    zafzrsjhm: '15788990090',
                    zafzrzjhm: '440304199009091234',
                    fddbryz: '0',
                    xffzrglr: '吴某某',
                    glrlxdh: '13413099008',
                    aqzzr: '孙某某',
                    aqzrrlxdh: '15799003943',
                    remark: '需要重点关注',
                    xzqh:'江门市莲江区',
                    zhjcsj:'2019-03-25',
                    jf:'90',
                    sfsd:'1',
                    sfsh:'1',
                    sfswe:'1'
                },{
                    dwmc: '江门市七天酒店',
                    hylb: '01',
                    dwlb: '01',
                    jjlx: '01',
                    dzxz: '广东省江门市莲江区XXX路XX号',
                    fddbr: '张某某',
                    zafzr: '李某某',
                    zzjgdm: '440703520000',
                    lxdh: '0750-8812344',
                    qyzt: '03',
                    gxpsc: '440703520000',
                    clrq: '2017-01-02 12:00',
                    wzjy: '0',
                    zddw: '0',
                    swdw: '0',
                    kddsd: '0',
                    jydssxq: '广东省江门市莲江区',
                    xzjd: '环市街道',
                    mplh: 'XX号',
                    jlxc: 'XX寸',
                    jydz: '广东省江门市莲江区环市街道',
                    jydzbz: '广东省江门市莲江区环市街道',
                    ccdzyjjdzyz: '1',
                    ssxq: '广东省江门市莲江区',
                    sssq: '1',
                    zcdz: '广东省江门市莲江区环市街道',
                    fdfzrxm: '张某某',
                    fdfzrzjzl: '1',
                    fdfzrzjhm: '441323199009091234',
                    fdfzrlxdh: '0750-8000001',
                    fdfzrsjhm: '15915719990',
                    dwfzrxm: '张某某',
                    dwfzrzjzl: '1',
                    dwfzrzjhm: '441323199009091234',
                    dwfzrlxdh: '0750-8000001',
                    dwfzrsjhm: '15915719990',
                    zafzrxm: '李某某',
                    zafzrzjzl: '1',
                    zafzrlxdh: '0750-8876321',
                    zafzrsjhm: '15788990090',
                    zafzrzjhm: '440304199009091234',
                    fddbryz: '0',
                    xffzrglr: '吴某某',
                    glrlxdh: '13413099008',
                    aqzzr: '孙某某',
                    aqzrrlxdh: '15799003943',
                    remark: '需要重点关注',
                    xzqh:'江门市莲江区',
                    zhjcsj:'2019-03-25',
                    jf:'90',
                    sfsd:'1',
                    sfsh:'1',
                    sfswe:'1'
                },{
                    dwmc: '江门市七天酒店',
                    hylb: '01',
                    dwlb: '01',
                    jjlx: '01',
                    dzxz: '广东省江门市莲江区XXX路XX号',
                    fddbr: '张某某',
                    zafzr: '李某某',
                    zzjgdm: '440703520000',
                    lxdh: '0750-8812344',
                    qyzt: '03',
                    gxpsc: '440703520000',
                    clrq: '2017-01-02 12:00',
                    wzjy: '0',
                    zddw: '0',
                    swdw: '0',
                    kddsd: '0',
                    jydssxq: '广东省江门市莲江区',
                    xzjd: '环市街道',
                    mplh: 'XX号',
                    jlxc: 'XX寸',
                    jydz: '广东省江门市莲江区环市街道',
                    jydzbz: '广东省江门市莲江区环市街道',
                    ccdzyjjdzyz: '1',
                    ssxq: '广东省江门市莲江区',
                    sssq: '1',
                    zcdz: '广东省江门市莲江区环市街道',
                    fdfzrxm: '张某某',
                    fdfzrzjzl: '1',
                    fdfzrzjhm: '441323199009091234',
                    fdfzrlxdh: '0750-8000001',
                    fdfzrsjhm: '15915719990',
                    dwfzrxm: '张某某',
                    dwfzrzjzl: '1',
                    dwfzrzjhm: '441323199009091234',
                    dwfzrlxdh: '0750-8000001',
                    dwfzrsjhm: '15915719990',
                    zafzrxm: '李某某',
                    zafzrzjzl: '1',
                    zafzrlxdh: '0750-8876321',
                    zafzrsjhm: '15788990090',
                    zafzrzjhm: '440304199009091234',
                    fddbryz: '0',
                    xffzrglr: '吴某某',
                    glrlxdh: '13413099008',
                    aqzzr: '孙某某',
                    aqzrrlxdh: '15799003943',
                    remark: '需要重点关注',
                    xzqh:'江门市莲江区',
                    zhjcsj:'2019-03-25',
                    jf:'90',
                    sfsd:'1',
                    sfsh:'1',
                    sfswe:'1'
                },{
                    dwmc: '江门市七天酒店',
                    hylb: '01',
                    dwlb: '01',
                    jjlx: '01',
                    dzxz: '广东省江门市莲江区XXX路XX号',
                    fddbr: '张某某',
                    zafzr: '李某某',
                    zzjgdm: '440703520000',
                    lxdh: '0750-8812344',
                    qyzt: '03',
                    gxpsc: '440703520000',
                    clrq: '2017-01-02 12:00',
                    wzjy: '0',
                    zddw: '0',
                    swdw: '0',
                    kddsd: '0',
                    jydssxq: '广东省江门市莲江区',
                    xzjd: '环市街道',
                    mplh: 'XX号',
                    jlxc: 'XX寸',
                    jydz: '广东省江门市莲江区环市街道',
                    jydzbz: '广东省江门市莲江区环市街道',
                    ccdzyjjdzyz: '1',
                    ssxq: '广东省江门市莲江区',
                    sssq: '1',
                    zcdz: '广东省江门市莲江区环市街道',
                    fdfzrxm: '张某某',
                    fdfzrzjzl: '1',
                    fdfzrzjhm: '441323199009091234',
                    fdfzrlxdh: '0750-8000001',
                    fdfzrsjhm: '15915719990',
                    dwfzrxm: '张某某',
                    dwfzrzjzl: '1',
                    dwfzrzjhm: '441323199009091234',
                    dwfzrlxdh: '0750-8000001',
                    dwfzrsjhm: '15915719990',
                    zafzrxm: '李某某',
                    zafzrzjzl: '1',
                    zafzrlxdh: '0750-8876321',
                    zafzrsjhm: '15788990090',
                    zafzrzjhm: '440304199009091234',
                    fddbryz: '0',
                    xffzrglr: '吴某某',
                    glrlxdh: '13413099008',
                    aqzzr: '孙某某',
                    aqzrrlxdh: '15799003943',
                    remark: '需要重点关注',
                    xzqh:'江门市莲江区',
                    zhjcsj:'2019-03-25',
                    jf:'90',
                    sfsd:'1',
                    sfsh:'1',
                    sfswe:'1'
                }
                ],
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
                    card: true,
                    group: [
                        {
                            label: '基本信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [
                                {label: '单位名称', prop: 'dwmc',},
                                {label: '经营名称', prop: 'jymc',},
                                {label: '经营状态', prop: 'jyzt',},
                                {label: '单位性质', prop: 'dwxz',},
                                {label: '行业类别', prop: 'hylb',},
                                {label: '开业日期', prop: 'kyrq',},
                                {label: '联系电话', prop: 'lxdh',},
                                {label: '星级', prop: 'xj',},

                                {label: '等级评定', prop: 'djd',},
                                {label: '上级主管部门', prop: 'sjzgbm',},

                                {label: '管辖区派出所', prop: 'xqpcs',},
                                {label: '主管公安机关', prop: 'gajg',},
                                {label: '责任民警警号', prop: 'zrmjjh',},
                                {label: '责任民警姓名', prop: 'zrmjxm',},
                                {label: '主营', prop: 'jyxmzy',},
                                {label: '兼营', prop: 'jyxmjy',},
                                {label: '被兼营单位', prop: 'bjydw',},

                                {label: '备注', prop: 'bz', span: 24},
                                /*{label:'录入人',prop:'lrr',},
                                {label:'录入日期',prop:'lrrq',},
                                {label:'录入情况',prop:'lrqk',},
                                {label:'最后修改人',prop:'zhxgr',},
                                {label:'最后修改时间',prop:'zhxgsj',},*/
                            ]
                        }, {
                            label: '规模情况',
                            prop: 'gmqk',
                            icon: 'el-icon-info',
                            column: [
                                {label: '面积(平方米)', prop: 'mj',},
                                {label: '楼层数', prop: 'lcs',},
                                {label: '栋数', prop: 'ds',},
                                {label: '房间数', prop: 'fjs',},
                                {label: '床位数', prop: 'cws',},
                            ]
                        },
                        {
                            label: '地址信息',
                            prop: 'dzxx',
                            icon: 'el-icon-info',
                            column: [{label: '行政区划', prop: 'xzqh',},
                                {label: '街路巷', prop: 'jlx',},
                                {label: '所属警务区', prop: 'ssjwq',},
                                {label: '所属社区', prop: 'sssq',},
                                {label: '门楼牌', prop: 'mpl',},
                                {label: '详细地址', prop: 'xxdz',},
                                {label: '经营地址', prop: 'jjdz',},]
                        }, {
                            label: '法定代表人',
                            prop: 'fddbr',
                            icon: 'el-icon-info',
                            column: [{
                                label: '姓名', prop: 'fddbrxm',
                            },
                                {label: '证件种类', prop: 'fddbrzjzl',},
                                {label: '证件号码', prop: 'fddbrzjhm',},
                                {label: '联系电话', prop: 'fddbrlxdh',},
                            ]
                        }, {
                            label: '实际经营人',
                            prop: 'sjjyr',
                            icon: 'el-icon-info',
                            column: [
                                {label: '姓名', prop: 'sjjyrxm',},
                                {label: '证件种类', prop: 'sjjyrzjzl',},
                                {label: '证件号码', prop: 'sjjyrzjhm',},
                                {label: '联系电话', prop: 'sjjyrlxdh',},
                            ]
                        }, {
                            label: '人员情况',
                            prop: 'ryqk',
                            icon: 'el-icon-info',
                            column: [
                                {label: '从业人员总数', prop: 'cyrs',},
                                {label: '治安培训人数', prop: 'zapxrs',},
                                {label: '保安人员总数', prop: 'baryzs',},
                                {label: '自建保安数', prop: 'zjbas',},
                                {label: '保安来源', prop: 'baly',},
                                {label: '保安派驻单位', prop: 'bapzdw',},
                                {label: '安保组织负责人姓名', prop: 'abxm',},
                                {label: '安保组织负责人联系电话', prop: 'ablxdh',},
                            ]
                        }, {
                            label: '上网服务',
                            prop: 'swfw',
                            icon: 'el-icon-info',
                            column: [
                                {label: '是否提供上网服务', prop: 'tgswfw',},
                                {label: '是否提供上网电脑', prop: 'tgswdn',},
                                {label: '上网接口数量', prop: 'swjksl',},
                                {label: '网络接入提供商', prop: 'wljrs',},
                                {label: '上网电脑总数', prop: 'swdnzs',},
                                {label: '上网方式', prop: 'swfs',},
                                {label: 'IP地址（上网账号）', prop: 'ipdz',},
                                {label: '网管负责人姓名', prop: 'wgfzr',},
                                {label: '是否安装网络安全设备', prop: 'wlaqsb',},
                            ]
                        }, {
                            label: '照片信息',
                            prop: 'swfw',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '图片',
                                    prop: 'imgBase',
                                    formslot: 'true',
                                    slot: true,
                                    span: 24,
                                    hide: true
                                },
                            ]
                        },

                    ]
                },
                option: {
                    dialogHeight: '100%',
                    dialogWidth: '100%',
                    dialogFullscreen: true,
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    viewBtn: false,
                    delBtn: false,
                    editBtn: false,
                    card: true,
                    menuWidth: '100',
                    column: [

                        {
                            label: '单位名称',
                            prop: 'dwmc',
                            search: 'true',
                            slot: true,
                            rules: [{
                                required: true,
                                message: '请输入单位名称',
                                trigger: 'blur'
                            }],

                        },
                        {
                            label: '单位类别',
                            prop: 'dwlb',
                            search: 'true',
                            type: 'select',
                            rules: [{
                                required: true,
                                message: '请选择单位类别',
                                trigger: 'blur'
                            }],
                            dicData: dict.dwlb,
                            searchFilterable:true
                        }, {
                            label: '涉黄单位',
                            prop: 'sfsh',
                            search: 'true',
                            type: 'select',
                            dicData: dict.yes_no,
                            searchFilterable:true
                        }, {
                            label: '涉毒单位',
                            prop: 'sfsd',
                            search: 'true',
                            type: 'select',
                            dicData: dict.yes_no,
                            searchFilterable:true
                        },
                        {
                            label: '涉维单位',
                            prop: 'sfswe',
                            search: 'true',
                            type: 'select',
                            dicData: dict.yes_no,
                            searchFilterable:true
                        }, {
                            label: '无证经营',
                            prop: 'wzjy',
                            type: 'radio',
                            dicData: dict.yes_no,
                            span: jbxx_span,
                            hide: true,
                            labelWidth: jbxx_label,
                            searchFilterable:true
                        },
                        {
                            label: '重点单位',
                            prop: 'zddw',
                            type: 'radio',
                            hide: true,
                            dicData: dict.yes_no,
                            span: jbxx_span,
                            labelWidth: jbxx_label,
                            rules: [{
                                required: true,
                                message: '请选择重点单位',
                                trigger: 'blur'
                            }],
                        },
                        {
                            label: '涉外单位',
                            prop: 'swdw',
                            type: 'radio',
                            dicData: dict.yes_no,
                            span: jbxx_span,
                            labelWidth: jbxx_label,
                            search: true,
                            searchFilterable:true

                        }
                        , {
                            label: '积分',
                            prop: 'jf',
                        }
                        ,
                        {
                            label: '经营状态',
                            prop: 'qyzt',
                            type: 'select',
                            span: jbxx_span,
                            hide: true,
                            labelWidth: jbxx_label,
                            dicData: dict.yqzt,

                        },
                        {
                            label: '行政区划',
                            prop: 'xzqh',
                            overHidden: true,
                        },
                        {
                            label: '法定代表人',
                            prop: 'fddbr',
                        },
                        {
                            label: '责任民警',
                            prop: 'zafzr',
                        },
                        {
                            label: '辖区派出所',
                            prop: 'zzjgdm',
                            type: 'select',
                            dicData: dict.pcs,
                            search: true,
                            overHidden: true,
                            searchFilterable:true
                        },{
                            label: '最后检查时间',
                            prop: 'zhjcsj',

                        },


                    ],
                },
                optionForm: {
                    card: true,

                    group: [
                        {
                            label: '基本信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [

                                {
                                    label: '单位名称',
                                    prop: 'dwmc',
                                    slot: true,
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请输入单位名称',
                                        trigger: 'blur'
                                    }],
                                },
                                {
                                    label: '行业类别',
                                    prop: 'hylb',
                                    type: 'select',
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,
                                    dicData: dict.hylb,

                                },
                                {
                                    label: '单位类别',
                                    prop: 'dwlb',
                                    type: 'select',
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,
                                    dicData: dict.dwlb,
                                },

                                {
                                    label: '经济类型',
                                    prop: 'jjlx',
                                    type: 'select',
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,
                                    dicData: dict.jjlx,
                                    rules: [{
                                        required: true,
                                        message: '请选择经济类型',
                                        trigger: 'blur'
                                    }],
                                },

                                {
                                    label: '组织机构代码',
                                    prop: 'zzjgdm',
                                    span: jbxx_span,
                                    labelWidth: jbxx_label

                                },
                                {
                                    label: '联系电话',
                                    prop: 'lxdh',
                                    span: jbxx_span,
                                    labelWidth: jbxx_label

                                },
                                {
                                    label: '企业状态',
                                    prop: 'qyzt',
                                    type: 'select',
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,
                                    dicData: dict.yqzt,
                                    rules: [{
                                        required: true,
                                        message: '请选择企业状态',
                                        trigger: 'blur'
                                    }],
                                },
                                {
                                    label: '管辖派出所',
                                    prop: 'gxpsc',
                                    type: 'select',
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,
                                    dicData: dict.pcs
                                },
                                {
                                    label: '成立日期',
                                    prop: 'clrq',
                                    type: 'date',
                                    span: jbxx_span,
                                    labelWidth: jbxx_label
                                }, {
                                    label: '无证经营',
                                    prop: 'wzjy',
                                    type: 'radio',
                                    dicData: dict.yes_no,
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请选择无证经营',
                                        trigger: 'blur'
                                    }],
                                },
                                {
                                    label: '重点单位',
                                    prop: 'zddw',
                                    type: 'radio',
                                    dicData: dict.yes_no,
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请选择重点单位',
                                        trigger: 'blur'
                                    }],
                                },
                                {
                                    label: '快递代收点',
                                    prop: 'kddsd',
                                    type: 'radio',

                                    dicData: dict.yes_no,
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请选择快递代收点',
                                        trigger: 'blur'
                                    }],
                                },
                                {
                                    label: '备注',
                                    prop: 'remark',
                                    type: 'textarea',
                                    span: jbxx_span
                                }


                            ]
                        },
                        {
                            label: '分析信息',
                            prop: 'fxxx',
                            icon: 'el-icon-info',
                            column: [

                                {
                                    label: '涉黄单位',
                                    prop: 'sfsh',
                                    search: 'true',
                                    type: 'select',
                                    dicData: dict.yes_no
                                }, {
                                    label: '涉毒单位',
                                    prop: 'sfsd',
                                    search: 'true',
                                    type: 'select',
                                    dicData: dict.yes_no
                                },
                                {
                                    label: '涉维单位',
                                    prop: 'sfswe',
                                    search: 'true',
                                    type: 'select',
                                    dicData: dict.yes_no
                                },
                                {
                                    label: '涉外单位',
                                    prop: 'swdw',
                                    type: 'radio',
                                    dicData: dict.yes_no,
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,

                                },
                                {
                                    label: '最后检查时间',
                                    prop: 'zhjcsj',
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,

                                },
                                {
                                    label: '积分',
                                    prop: 'jf',
                                    span: jbxx_span,
                                    labelWidth: jbxx_label,

                                }



                            ],
                            className:'aaa'
                        },
                        {
                            label: '地址信息',
                            prop: 'dzxx',
                            icon: 'el-icon-info',
                            display:true,
                            column: [
                                {
                                    label: '经营地省市县区',
                                    prop: 'jydssxq',
                                    span: dzxx_span,
                                    labelWidth: dzxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请输入经营地省市县区',
                                        trigger: 'blur'
                                    }],
                                },
                                {
                                    label: '所属警务区',
                                    prop: 'jydssxq',
                                    type: 'select',
                                    span: dzxx_span,
                                    labelWidth: dzxx_label,
                                    dicData: dict.jwq,
                                    rules: [{
                                        required: true,
                                        message: '请选择警务区',
                                        trigger: 'blur'
                                    }],
                                },
                                {
                                    label: '所属社区',
                                    prop: 'sssq',
                                    type: 'select',
                                    span: dzxx_span,
                                    labelWidth: dzxx_label,
                                    dicData: dict.sq,
                                    rules: [{
                                        required: true,
                                        message: '请选择社区',
                                        trigger: 'blur'
                                    }],

                                },
                                {
                                    label: '乡镇街道',
                                    prop: 'xzjd',
                                    span: dzxx_span,
                                    labelWidth: dzxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请输入乡镇街道',
                                        trigger: 'blur'
                                    }],
                                }, {
                                    label: '街路巷（村）',
                                    prop: 'jlxc',
                                    span: dzxx_span,
                                    labelWidth: dzxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请输入街路巷',
                                        trigger: 'blur'
                                    }],
                                }
                                , {
                                    label: '门牌楼号',
                                    prop: 'mplh',
                                    span: dzxx_span,
                                    labelWidth: dzxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请输入门牌楼号',
                                        trigger: 'blur'
                                    }],
                                }, {
                                    label: '经营地址',
                                    prop: 'jydz',
                                    span: dzxx_span,
                                    labelWidth: dzxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请输入经营地址',
                                        trigger: 'blur'
                                    }],
                                }, {
                                    label: '经营地址（标准）',
                                    prop: 'jydzbz',
                                    span: dzxx_span,
                                    labelWidth: dzxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请输入经营地址',
                                        trigger: 'blur'
                                    }],
                                },
                                {
                                    label: '注册地址与经营地址一致',
                                    prop: 'ccdzyjjdzyz',
                                    type: 'radio',
                                    dicData: dict.yes_no,
                                    span: dzxx_span,
                                    labelWidth: dzxx_label
                                },
                                {
                                    label: '省市县区（注册地址）',
                                    prop: 'ssxq',
                                    span: dzxx_span,
                                    labelWidth: dzxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请输入注册地址',
                                        trigger: 'blur'
                                    }],
                                },
                                {
                                    label: '注册地址',
                                    prop: 'zcdz',
                                    span: dzxx_span,
                                    labelWidth: dzxx_label,
                                    rules: [{
                                        required: true,
                                        message: '请输入注册地址',
                                        trigger: 'blur'
                                    }],
                                },


                            ]
                        },
                        {
                            label: '法定负责人',
                            prop: 'fdfzr',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '姓名',
                                    prop: 'fdfzrxm',
                                    rules: [{
                                        required: true,
                                        message: '请输入姓名',
                                        trigger: 'blur'
                                    }],
                                    span: 8
                                },
                                {
                                    label: '证件种类',
                                    prop: 'fdfzrzjzl',
                                    type: 'select',
                                    dicData: dict.zjlx,
                                    rules: [{
                                        required: true,
                                        message: '请选择证件种类',
                                        trigger: 'blur'
                                    }],
                                    span: 8
                                },
                                {
                                    label: '证件号码',
                                    prop: 'fdfzrzjhm',
                                    rules: [{
                                        required: true,
                                        message: '请输入证件号码',
                                        trigger: 'blur'
                                    }],
                                    span: 8
                                },
                                {
                                    label: '联系电话',
                                    prop: 'fdfzrlxdh',
                                    span: 8
                                },
                                {
                                    label: '手机号码',
                                    prop: 'fdfzrsjhm',
                                    span: 8
                                },


                            ]
                        },
                        {
                            label: '单位负责人',
                            prop: 'dwfzr',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '姓名',
                                    prop: 'dwfzrxm',
                                    rules: [{
                                        required: true,
                                        message: '请输入姓名',
                                        trigger: 'blur'
                                    }],
                                    span: 8
                                },
                                {
                                    label: '证件种类',
                                    prop: 'dwfzrzjzl',
                                    type: 'select',
                                    dicData: dict.zjlx,
                                    rules: [{
                                        required: true,
                                        message: '请选择证件种类',
                                        trigger: 'blur'
                                    }],
                                    span: 8
                                },
                                {
                                    label: '证件号码',
                                    prop: 'dwfzrzjhm',
                                    rules: [{
                                        required: true,
                                        message: '请输入证件号码',
                                        trigger: 'blur'
                                    }],
                                    span: 8

                                },
                                {
                                    label: '联系电话',
                                    prop: 'dwfzrlxdh',
                                    span: 8
                                },
                                {
                                    label: '手机号码',
                                    prop: 'dwfzrsjhm',
                                    span: 8
                                },


                            ]
                        },
                        {
                            label: '治安负责人',
                            prop: 'zafzr',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '姓名',
                                    prop: 'zafzrxm',
                                    rules: [{
                                        required: true,
                                        message: '请输入姓名',
                                        trigger: 'blur'
                                    }],
                                    span: 8
                                },
                                {
                                    label: '证件种类',
                                    prop: 'zafzrzjzl',
                                    type: 'select',
                                    dicData: dict.zjlx,
                                    rules: [{
                                        required: true,
                                        message: '请选择证件种类',
                                        trigger: 'blur'
                                    }],
                                    span: 8
                                },
                                {
                                    label: '证件号码',
                                    prop: 'zafzrzjhm',
                                    rules: [{
                                        required: true,
                                        message: '请输入证件号码',
                                        trigger: 'blur'
                                    }],
                                    span: 8

                                },
                                {
                                    label: '联系电话',
                                    prop: 'zafzrlxdh',
                                    span: 8
                                },
                                {
                                    label: '手机号码',
                                    prop: 'zafzrsjhm',
                                    span: 8
                                },
                                {
                                    label: '法定代表人一致',
                                    prop: 'fddbryz',
                                    type: 'radio',
                                    dicData: dict.yes_no,
                                    labelWidth: 150,
                                    span: 8
                                }


                            ]
                        },
                        {
                            label: '消防负责人',
                            prop: 'xffzr',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '管理人',
                                    prop: 'xffzrglr',
                                    labelWidth: 120
                                },
                                {
                                    label: '联系电话',
                                    prop: 'glrlxdh',

                                },
                                {
                                    label: '安全责任人',
                                    prop: 'aqzzr',
                                    labelWidth: 120
                                },
                                {
                                    label: '联系电话',
                                    prop: 'aqzrrlxdh'
                                }
                            ]
                        },
                        {
                            label: '照片信息',
                            prop: 'zpxx',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    prop: 'imgUi',
                                    slot: true,
                                    span: 24,
                                    label: '照片信息'
                                }
                            ]
                        },


                    ]
                }

            }
        },
        created() {
            this.type = this.optionTap.column[0]
            this.analyType = this.optionAnaly.column[0]
        },
        computed: {
            infoData() {
                return this.form.info
            }
        },
        methods: {
            openUnitDialog(name) {
                this.unitView = true
                this.unitTitle = name

            },

            handleChange(column) {
                this.type = column
            },
            handleAnalyChange(column) {
                this.analyType = column
            },

            handleOpt(row, index, done, loading) {
                done();
                this.$notify({
                    title: '成功',
                    message: '操作成功',
                    type: 'success',
                    duration: 2000
                })
            },
            handleSav(row, done, loading) {
                done();
                this.$notify({
                    title: '成功',
                    message: '操作成功',
                    type: 'success',
                    duration: 2000
                })
            },
            handleDelete() {
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
            },
            preview(scoperowdwmc){
                this.$router.push({
                    path: this.$router.$avueRouter.getPath({
                        name: "基本档案信息",
                        src: "/portal/archive/fileInfo/index"
                    }),
                    query: {dwmc:scoperowdwmc}
                });
            },
        }
    }
</script>