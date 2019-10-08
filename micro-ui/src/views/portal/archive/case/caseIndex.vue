<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-crud :option="option" :data="data"
                       :page="page"

                       v-model="form">
                <template slot="AJBH"
                          slot-scope="scope">
                    <button type="button" @click="openDialog(scope.row.sadw)" class="el-button el-button--text el-button--small">{{scope.row.AJBH}}</button>
                </template>

                <div class="el-form-item"></div>
                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="openDialog(scope.row.sadw)">预览
                    </el-button>
                </template>

            </avue-crud>
        </basic-container>

        <el-dialog :title="title" fullscreen :visible.sync="recordView" append-to-body>
            <avue-detail :option="option" v-model="fromDetail">
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
    export default {
        data() {
            return {
                page: {
                    total: 6, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                recordView: false,
                title: '预览',
                form: {},
                fromDetail: {
                    sadw: '江门七天酒店',
                    SYSTEMID: '523532523523523',
                    AJBH: 'AJ440723432543',
                    SLJSDW: '仓后派出所',
                    AJSTATE: '结案',
                    ZAZT: '已受理',
                    SL_BJSLH: '23423423',
                    SL_JJFS: '工作发现',
                    AJLX: '刑事案件',
                    AB: '危害国家安全案',
                    ZABZ: '专案标识',
                    AJMC: '案件名称',
                    AJWH: '案件号',
                    SLJJSJ: '2016-01-02',
                    SLFXRQ: '2016-01-01',
                    FASJCZ: '2016-01-02',
                    FASJZZ: '2016-03-02',
                    FADD_QX: '广东省江门市逢江区',
                    FADD_JD: '水南路',
                    AJSSJQ: '江华派出所',
                    FADD: '广东省江门市',
                    SSSQ: '会龙社区',

                    SLFACS: '其他',
                    FXXS: '民众举报',
                    AJWHCD: '严重',
                    BLYY: '未确定',
                    ZYAQ: 'XXX单位涉嫌贪污',
                    XZSJ: '作案时机',
                    XZCS: '机场',
                    XZDX: '病人',
                    XZWP: '选择物品',
                    ZAGJ: '作案工具',
                    XZBW: '选择部位',
                    ZARS: '2',
                    SDTD: '其他',
                    SWRS: '0',
                    SSRS: '0',
                    SSJZ: '234234',
                    SAZZ: '223442',
                    LASJ: '2016-01-02',
                    PASJ: '2016-01-02',
                    JA_JASJ: '2016-01-02',
                    XA_XASJ: '2016-01-02',
                    YSSJ: '2016-01-02',
                    SLJJDW: '江华派出所',
                    SL_LRR: '张某某',
                    SL_LRSJ: '2016-01-02',
                    SLJJRY: '备用',
                    SL_SLRXM: '李某某',
                    SL_SLSJ: '2016-01-02',
                    SLFAQH: '广东省江门市逢江区',
                    LADW: '江华派出所',
                    AJLARY: '蔡某某',
                    ZBDW: '江华派出所',
                    AJZBRY: '张某某',
                    AJXBRY: '任某某',
                    AJBARP: '13423482342',
                    LA_LRR: '任某某',
                    LA_LRSJ: '2016-01-02',
                    LA_PZR: '何某某',
                    LA_PZSJ: '2016-01-02',
                    LA_ZHXGR: '李某某',
                    LA_ZHXGSJ: '2016-01-02',
                    LA_PSSTATE: '统一',
                    DBXX: '督办信息',
                    AJLY: '案件来源',
                    BZ: '备注',
                    DEPARTMENTCODE: '440702124442',
                    CREATOR: '李某某',
                    CREATEDTIME: '2016-01-02',
                    SECURITYGRADE: 'A级',
                    LASTUPDATEDBY: '李某某',
                    LASTUPDATEDTIME: '2016-01-02',
                    REFRESHTIME: '2016-01-02',

                    YSDW: '移送单位',
                    YSCBR: '移送承办人',
                    YSDWDH: '移送单位电话',
                    WHSSJZ: '0',
                    DBJB: '分局督办',
                    JTAJLY: '具体案件来源',
                    FZZTLX: '单位',
                    SFSW: '否',
                    SJGJDQ: '中国',
                    YISHENSJ: '2016-01-01',
                    ESSJ: '2016-01-02',
                    YSDW_NEW: '移送单位',
                    YSDWDH_NEW: '02342-124354',
                    YSCBR_NEW: '李某某',
                    YSSJ_NEW: '2016-01-02',

                },
                data: [
                    {
                    sadw: '江门七天酒店',
                    SYSTEMID: '523532523523523',
                    AJBH: 'AJ440723432543',
                    SLJSDW: '仓后派出所',
                    AJSTATE: '结案',
                    ZAZT: '已受理',
                    SL_BJSLH: '23423423',
                    SL_JJFS: '工作发现',
                    AJLX: '案件类型',
                    AB: '危害国家安全案',
                    ZABZ: '专案标识',
                    AJMC: 'XX单位XX',
                    AJWH: '案件号',
                    SLJJSJ: '2016-01-02',
                    SLFXRQ: '2016-01-01',
                    FASJCZ: '2016-01-02',
                    FASJZZ: '2016-03-02',
                    FADD_QX: '广东省江门市逢江区',
                    FADD_JD: '水南路',
                    AJSSJQ: '江华派出所',
                    FADD: '广东省江门市',
                    SSSQ: '会龙社区',
                    FADY: '发案地域（取消）',
                    SLFACS: '其他',
                    FXXS: '发现形式',
                    AJWHCD: '危害程度',
                    BLYY: '未确定',
                    ZYAQ: 'XXX单位涉嫌贪污',
                    XZSJ: '作案时机',
                    XZCS: '机场',
                    XZDX: '病人',
                    XZWP: '选择物品',
                    ZAGJ: '作案工具',
                    XZBW: '选择部位',
                    ZARS: '2',
                    SDTD: '其他',
                    SWRS: '0',
                    SSRS: '0',
                    SSJZ: '234234',
                    SAZZ: '223442',
                    LASJ: '2016-01-02',
                    PASJ: '2016-01-02',
                    JA_JASJ: '2016-01-02',
                    XA_XASJ: '2016-01-02',
                    YSSJ: '2016-01-02',
                    SLJJDW: '江华派出所',
                    SL_LRR: '张某某',
                    SL_LRSJ: '2016-01-02',
                    SLJJRY: '备用',
                    SL_SLRXM: '李某某',
                    SL_SLSJ: '2016-01-02',
                    SLFAQH: '广东省江门市逢江区',
                    LADW: '江华派出所',
                    AJLARY: '蔡某某',
                    ZBDW: '江华派出所',
                    AJZBRY: '张某某',
                    AJXBRY: '任某某',
                    AJBARP: '13423482342',
                    LA_LRR: '任某某',
                    LA_LRSJ: '2016-01-02',
                    LA_PZR: '何某某',
                    LA_PZSJ: '2016-01-02',
                    LA_ZHXGR: '李某某',
                    LA_ZHXGSJ: '2016-01-02',
                    LA_PSSTATE: '统一',
                    DBXX: '督办信息',
                    AJLY: '案件来源',
                    BZ: '备注',
                    DEPARTMENTCODE: '440702124442',
                    CREATOR: '李某某',
                    CREATEDTIME: '2016-01-02',
                    SECURITYGRADE: 'A级',
                    LASTUPDATEDBY: '李某某',
                    LASTUPDATEDTIME: '2016-01-02',
                    REFRESHTIME: '2016-01-02',

                    YSDW: '移送单位',
                    YSCBR: '移送承办人',
                    YSDWDH: '移送单位电话',
                    WHSSJZ: '0',
                    DBJB: '分局督办',
                    JTAJLY: '具体案件来源',
                    FZZTLX: '犯罪主体类型',
                    SFSW: '否',
                    SJGJDQ: '中国',
                    YISHENSJ: '2016-01-01',
                    ESSJ: '2016-01-02',
                    YSDW_NEW: '移送单位',
                    YSDWDH_NEW: '02342-124354',
                    YSCBR_NEW: '李某某',
                    YSSJ_NEW: '2016-01-02',


                },
                    {
                        sadw: '江门七天酒店',
                        SYSTEMID: '523532523523523',
                        AJBH: 'AJ440723432543',
                        SLJSDW: '仓后派出所',
                        AJSTATE: '结案',
                        ZAZT: '已受理',
                        SL_BJSLH: '23423423',
                        SL_JJFS: '工作发现',
                        AJLX: '案件类型',
                        AB: '危害国家安全案',
                        ZABZ: '专案标识',
                        AJMC: 'XX单位XX',
                        AJWH: '案件号',
                        SLJJSJ: '2016-01-02',
                        SLFXRQ: '2016-01-01',
                        FASJCZ: '2016-01-02',
                        FASJZZ: '2016-03-02',
                        FADD_QX: '广东省江门市逢江区',
                        FADD_JD: '水南路',
                        AJSSJQ: '江华派出所',
                        FADD: '广东省江门市',
                        SSSQ: '会龙社区',
                        FADY: '发案地域（取消）',
                        SLFACS: '其他',
                        FXXS: '发现形式',
                        AJWHCD: '危害程度',
                        BLYY: '未确定',
                        ZYAQ: 'XXX单位涉嫌贪污',
                        XZSJ: '作案时机',
                        XZCS: '机场',
                        XZDX: '病人',
                        XZWP: '选择物品',
                        ZAGJ: '作案工具',
                        XZBW: '选择部位',
                        ZARS: '2',
                        SDTD: '其他',
                        SWRS: '0',
                        SSRS: '0',
                        SSJZ: '234234',
                        SAZZ: '223442',
                        LASJ: '2016-01-02',
                        PASJ: '2016-01-02',
                        JA_JASJ: '2016-01-02',
                        XA_XASJ: '2016-01-02',
                        YSSJ: '2016-01-02',
                        SLJJDW: '江华派出所',
                        SL_LRR: '张某某',
                        SL_LRSJ: '2016-01-02',
                        SLJJRY: '备用',
                        SL_SLRXM: '李某某',
                        SL_SLSJ: '2016-01-02',
                        SLFAQH: '广东省江门市逢江区',
                        LADW: '江华派出所',
                        AJLARY: '蔡某某',
                        ZBDW: '江华派出所',
                        AJZBRY: '张某某',
                        AJXBRY: '任某某',
                        AJBARP: '13423482342',
                        LA_LRR: '任某某',
                        LA_LRSJ: '2016-01-02',
                        LA_PZR: '何某某',
                        LA_PZSJ: '2016-01-02',
                        LA_ZHXGR: '李某某',
                        LA_ZHXGSJ: '2016-01-02',
                        LA_PSSTATE: '统一',
                        DBXX: '督办信息',
                        AJLY: '案件来源',
                        BZ: '备注',
                        DEPARTMENTCODE: '440702124442',
                        CREATOR: '李某某',
                        CREATEDTIME: '2016-01-02',
                        SECURITYGRADE: 'A级',
                        LASTUPDATEDBY: '李某某',
                        LASTUPDATEDTIME: '2016-01-02',
                        REFRESHTIME: '2016-01-02',

                        YSDW: '移送单位',
                        YSCBR: '移送承办人',
                        YSDWDH: '移送单位电话',
                        WHSSJZ: '0',
                        DBJB: '分局督办',
                        JTAJLY: '具体案件来源',
                        FZZTLX: '犯罪主体类型',
                        SFSW: '否',
                        SJGJDQ: '中国',
                        YISHENSJ: '2016-01-01',
                        ESSJ: '2016-01-02',
                        YSDW_NEW: '移送单位',
                        YSDWDH_NEW: '02342-124354',
                        YSCBR_NEW: '李某某',
                        YSSJ_NEW: '2016-01-02',


                    },
                    {
                        sadw: '江门七天酒店',
                        SYSTEMID: '523532523523523',
                        AJBH: 'AJ440723432543',
                        SLJSDW: '仓后派出所',
                        AJSTATE: '结案',
                        ZAZT: '已受理',
                        SL_BJSLH: '23423423',
                        SL_JJFS: '工作发现',
                        AJLX: '案件类型',
                        AB: '危害国家安全案',
                        ZABZ: '专案标识',
                        AJMC: 'XX单位XX',
                        AJWH: '案件号',
                        SLJJSJ: '2016-01-02',
                        SLFXRQ: '2016-01-01',
                        FASJCZ: '2016-01-02',
                        FASJZZ: '2016-03-02',
                        FADD_QX: '广东省江门市逢江区',
                        FADD_JD: '水南路',
                        AJSSJQ: '江华派出所',
                        FADD: '广东省江门市',
                        SSSQ: '会龙社区',
                        FADY: '发案地域（取消）',
                        SLFACS: '其他',
                        FXXS: '发现形式',
                        AJWHCD: '危害程度',
                        BLYY: '未确定',
                        ZYAQ: 'XXX单位涉嫌贪污',
                        XZSJ: '作案时机',
                        XZCS: '机场',
                        XZDX: '病人',
                        XZWP: '选择物品',
                        ZAGJ: '作案工具',
                        XZBW: '选择部位',
                        ZARS: '2',
                        SDTD: '其他',
                        SWRS: '0',
                        SSRS: '0',
                        SSJZ: '234234',
                        SAZZ: '223442',
                        LASJ: '2016-01-02',
                        PASJ: '2016-01-02',
                        JA_JASJ: '2016-01-02',
                        XA_XASJ: '2016-01-02',
                        YSSJ: '2016-01-02',
                        SLJJDW: '江华派出所',
                        SL_LRR: '张某某',
                        SL_LRSJ: '2016-01-02',
                        SLJJRY: '备用',
                        SL_SLRXM: '李某某',
                        SL_SLSJ: '2016-01-02',
                        SLFAQH: '广东省江门市逢江区',
                        LADW: '江华派出所',
                        AJLARY: '蔡某某',
                        ZBDW: '江华派出所',
                        AJZBRY: '张某某',
                        AJXBRY: '任某某',
                        AJBARP: '13423482342',
                        LA_LRR: '任某某',
                        LA_LRSJ: '2016-01-02',
                        LA_PZR: '何某某',
                        LA_PZSJ: '2016-01-02',
                        LA_ZHXGR: '李某某',
                        LA_ZHXGSJ: '2016-01-02',
                        LA_PSSTATE: '统一',
                        DBXX: '督办信息',
                        AJLY: '案件来源',
                        BZ: '备注',
                        DEPARTMENTCODE: '440702124442',
                        CREATOR: '李某某',
                        CREATEDTIME: '2016-01-02',
                        SECURITYGRADE: 'A级',
                        LASTUPDATEDBY: '李某某',
                        LASTUPDATEDTIME: '2016-01-02',
                        REFRESHTIME: '2016-01-02',

                        YSDW: '移送单位',
                        YSCBR: '移送承办人',
                        YSDWDH: '移送单位电话',
                        WHSSJZ: '0',
                        DBJB: '分局督办',
                        JTAJLY: '具体案件来源',
                        FZZTLX: '犯罪主体类型',
                        SFSW: '否',
                        SJGJDQ: '中国',
                        YISHENSJ: '2016-01-01',
                        ESSJ: '2016-01-02',
                        YSDW_NEW: '移送单位',
                        YSDWDH_NEW: '02342-124354',
                        YSCBR_NEW: '李某某',
                        YSSJ_NEW: '2016-01-02',


                    },
                    {
                        sadw: '江门七天酒店',
                        SYSTEMID: '523532523523523',
                        AJBH: 'AJ440723432543',
                        SLJSDW: '仓后派出所',
                        AJSTATE: '结案',
                        ZAZT: '已受理',
                        SL_BJSLH: '23423423',
                        SL_JJFS: '工作发现',
                        AJLX: '案件类型',
                        AB: '危害国家安全案',
                        ZABZ: '专案标识',
                        AJMC: 'XX单位XX',
                        AJWH: '案件号',
                        SLJJSJ: '2016-01-02',
                        SLFXRQ: '2016-01-01',
                        FASJCZ: '2016-01-02',
                        FASJZZ: '2016-03-02',
                        FADD_QX: '广东省江门市逢江区',
                        FADD_JD: '水南路',
                        AJSSJQ: '江华派出所',
                        FADD: '广东省江门市',
                        SSSQ: '会龙社区',
                        FADY: '发案地域（取消）',
                        SLFACS: '其他',
                        FXXS: '发现形式',
                        AJWHCD: '危害程度',
                        BLYY: '未确定',
                        ZYAQ: 'XXX单位涉嫌贪污',
                        XZSJ: '作案时机',
                        XZCS: '机场',
                        XZDX: '病人',
                        XZWP: '选择物品',
                        ZAGJ: '作案工具',
                        XZBW: '选择部位',
                        ZARS: '2',
                        SDTD: '其他',
                        SWRS: '0',
                        SSRS: '0',
                        SSJZ: '234234',
                        SAZZ: '223442',
                        LASJ: '2016-01-02',
                        PASJ: '2016-01-02',
                        JA_JASJ: '2016-01-02',
                        XA_XASJ: '2016-01-02',
                        YSSJ: '2016-01-02',
                        SLJJDW: '江华派出所',
                        SL_LRR: '张某某',
                        SL_LRSJ: '2016-01-02',
                        SLJJRY: '备用',
                        SL_SLRXM: '李某某',
                        SL_SLSJ: '2016-01-02',
                        SLFAQH: '广东省江门市逢江区',
                        LADW: '江华派出所',
                        AJLARY: '蔡某某',
                        ZBDW: '江华派出所',
                        AJZBRY: '张某某',
                        AJXBRY: '任某某',
                        AJBARP: '13423482342',
                        LA_LRR: '任某某',
                        LA_LRSJ: '2016-01-02',
                        LA_PZR: '何某某',
                        LA_PZSJ: '2016-01-02',
                        LA_ZHXGR: '李某某',
                        LA_ZHXGSJ: '2016-01-02',
                        LA_PSSTATE: '统一',
                        DBXX: '督办信息',
                        AJLY: '案件来源',
                        BZ: '备注',
                        DEPARTMENTCODE: '440702124442',
                        CREATOR: '李某某',
                        CREATEDTIME: '2016-01-02',
                        SECURITYGRADE: 'A级',
                        LASTUPDATEDBY: '李某某',
                        LASTUPDATEDTIME: '2016-01-02',
                        REFRESHTIME: '2016-01-02',

                        YSDW: '移送单位',
                        YSCBR: '移送承办人',
                        YSDWDH: '移送单位电话',
                        WHSSJZ: '0',
                        DBJB: '分局督办',
                        JTAJLY: '具体案件来源',
                        FZZTLX: '犯罪主体类型',
                        SFSW: '否',
                        SJGJDQ: '中国',
                        YISHENSJ: '2016-01-01',
                        ESSJ: '2016-01-02',
                        YSDW_NEW: '移送单位',
                        YSDWDH_NEW: '02342-124354',
                        YSCBR_NEW: '李某某',
                        YSSJ_NEW: '2016-01-02',


                    }
                ],
                option: {
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    viewBtn: false,
                    delBtn: false,
                    editBtn: false,
                    card: true,
                    column: [
                        {
                            label: '案件号',
                            prop: 'AJBH',
                            search: true,
                            slot:true,

                        }, {
                            label: '案件名称',
                            prop: 'AJMC',
                            search: false,


                        }, {
                            label: '案别',
                            prop: 'AB',
                            width: 160,
                            search: true,
                            type: 'select',
                            dicData: [
                                {
                                    label: '危害国家安全案',
                                    value: '01'
                                },
                                {
                                    label: '背叛国家案',
                                    value: '02'
                                },
                                {
                                    label: '颠覆国家政权案',
                                    value: '03'
                                }
                            ],
                        }, {
                            label: '案件类型',
                            prop: 'AJLX',
                            type: 'select',
                            hide: true,
                            dicData: [{
                                label: '刑事案件',
                                value: '01'
                            }]
                        }, {
                            label: '案件状态',
                            prop: 'AJSTATE',
                            search: true,
                            type: 'select',
                            dicData: [{
                                label: '已受理',
                                value: '01',
                            }, {
                                label: '已破案',
                                value: '03',
                            }, {
                                label: '已结案',
                                value: '03',
                            }, {
                                label: '已移交',
                                value: '04',
                            }
                            ]
                        }, {
                            label: '作案状态',
                            prop: 'ZAZT',
                            type: 'select',
                            hide: true,

                        }, {
                            label: '受理单位',
                            prop: 'SLJSDW',
                            hide: true,
                        }, {
                            label: '案发时间',
                            prop: 'inspectDate',
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                            type: "daterange",
                            search: true,
                            hide: true,
                            viewDisplay: false,
                        },

                        {
                            label: '接警受理号',
                            prop: 'SL_BJSLH',
                            hide: true,
                        }, {
                            label: '接警方式',
                            prop: 'SL_JJFS',
                            hide: true,
                        },
                        {
                            label: '专案标识',
                            prop: 'ZABZ',
                            hide: true
                        },
                        {
                            label: '报警时间',
                            prop: 'SLJJSJ',
                            hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',

                        }, {
                            label: '发现时间',
                            prop: 'SLFXRQ',
                            hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        }, {
                            label: '涉案单位',
                            prop: 'sadw',
                        },
                        {
                            label: '发案时间初值',
                            prop: 'FASJCZ',
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '发案时间终值',
                            prop: 'FASJZZ',
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '发案地点区县',
                            prop: 'FADD_QX',
                            hide: true,
                            type: 'select'
                        },
                        {
                            label: '发案地点街道',
                            prop: 'FADD_JD',
                            hide: true,
                        },
                        {
                            label: '所属警区',
                            prop: 'AJSSJQ',
                            hide: true,
                            type: 'select'
                        },
                        {
                            label: '发案地点详址',
                            prop: 'FADD',
                            hide: true,
                        },
                        {
                            label: '所属社区',
                            prop: 'SSSQ',
                            hide: true,
                            type: 'select'
                        },
                        {label: '发案处所', prop: 'SLFACS', hide: true,},
                        {label: '发现形式', prop: 'FXXS', hide: true,},
                        {
                            label: '危害程度', prop:
                                'AJWHCD',
                            hide: true,
                            type: 'select'
                        },
                        {label: '补立原因', prop: 'BLYY', hide: true,},
                        {label: '案情', prop: 'ZYAQ',},
                        {label: '作案时机', prop: 'XZSJ', hide: true,},
                        {label: '选择处所', prop: 'XZCS', hide: true,},
                        {label: '选择对象', prop: 'XZDX', hide: true,},
                        {label: '选择物品', prop: 'XZWP', hide: true,},
                        {label: '作案工具', prop: 'ZAGJ', hide: true,},
                        {label: '选择部位', prop: 'XZBW', hide: true,},
                        {label: '作案人数', prop: 'ZARS', hide: true,},
                        {label: '作案手段特点', prop: 'SDTD', hide: true,},
                        {label: '死亡人数', prop: 'SWRS', hide: true,},
                        {label: '受伤人数', prop: 'SSRS', hide: true,},
                        {label: '损失折款', prop: 'SSJZ', hide: true,},
                        {label: '涉案总值', prop: 'SAZZ', hide: true,},
                        {
                            label: '立案日期', prop: 'LASJ', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '破案时间', prop: 'PASJ', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '结案时间', prop: 'JA_JASJ', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '销案时间', prop: 'XA_XASJ', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '移送时间', prop: 'YSSJ', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {label: '接警单位', prop: 'SLJJDW', hide: true,},
                        {label: '受理人', prop: 'SL_LRR', hide: true,},
                        {
                            label: '受理时间', prop: 'SL_LRSJ', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {label: '备用', prop: 'SLJJRY', hide: true,},
                        {label: '填表人', prop: 'SL_SLRXM', hide: true,},
                        {
                            label: '填表时间', prop: 'SL_SLSJ', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '发案地政区划', prop: 'SLFAQH', hide: true,
                            type: 'select'
                        },
                        {
                            label: '立案单位', prop: 'LADW', hide: true,
                            type: 'select'
                        },
                        {label: '立案人', prop: 'AJLARY', hide: true,},
                        {label: '主办单位', prop: 'ZBDW', hide: true,},
                        {label: '主要办案人', prop: 'AJZBRY', hide: true,},
                        {label: '案件协办人', prop: 'AJXBRY', hide: true,},
                        {label: '办案人联系电话', prop: 'AJBARP', hide: true,},
                        {label: '立案录入人', prop: 'LA_LRR', hide: true,},
                        {
                            label: '立案录入时间', prop: 'LA_LRSJ', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {label: '立案批准人', prop: 'LA_PZR', hide: true,},
                        {
                            label: '立案批时间', prop: 'LA_PZSJ', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {label: '案后修改人', prop: 'LA_ZHXGR', hide: true,},
                        {
                            label: '案最后修改时间', prop: 'LA_ZHXGSJ', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '批示状态', prop: 'LA_PSSTATE', hide: true,
                            type: 'select'
                        },
                        {label: '督办信息', prop: 'DBXX', hide: true,},
                        {
                            label: '案件来源', prop: 'AJLY', hide: true,
                            type: 'select'
                        },
                        {label: '备注', prop: 'BZ', hide: true,},
                        {label: '部门编号', prop: 'DEPARTMENTCODE', hide: true,},
                        {label: '创建者', prop: 'CREATOR', hide: true,},
                        {
                            label: '创建时间', prop: 'CREATEDTIME', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {label: '密级', prop: 'SECURITYGRADE', hide: true,},
                        {label: '最终修改人', prop: 'LASTUPDATEDBY', hide: true,},
                        {
                            label: '最后修改时间', prop: 'LASTUPDATEDTIME', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '更新时间', prop: 'REFRESHTIME', hide: true,
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {label: '移送单位', prop: 'YSDW', hide: true,},
                        {label: '移送承办人', prop: 'YSCBR', hide: true,},
                        {label: '移送单位电话', prop: 'YSDWDH', hide: true,},

                        {
                            label: '督办级别', prop: 'DBJB', hide: true,
                            type: 'select'
                        },
                        {
                            label: '具体案件来源', prop: 'JTAJLY', hide: true,
                            type: 'select'
                        },
                        {
                            label: '犯罪主体类型', prop: 'FZZTLX', hide: true,
                            type: 'select'
                        },
                        {
                            label: '案情是否涉外', prop: 'SFSW', hide: true,
                            type: 'select'
                        },
                        {
                            label: '案情涉及国家地区', prop: 'SJGJDQ', hide: true,
                            type: 'select'
                        },
                        {
                            label: '一审时间', prop: 'YISHENSJ', hide: true, type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '二审时间', prop: 'ESSJ', hide: true, type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {label: '移送单位', prop: 'YSDW_NEW', hide: true,},
                        {label: '移送单位电话', prop: 'YSDWDH_NEW', hide: true,},
                        {label: '移送承办人', prop: 'YSCBR_NEW', hide: true,},
                        {
                            label: '移送时间', prop: 'YSSJ_NEW', hide: true, type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },

                    ],
                    group: [
                        {
                        label: '基本信息',
                        prop: 'jbxx',
                        icon: 'el-icon-info',
                        column: [{
                            label: '案件号',
                            prop: 'AJBH',
                            search: true,

                        }, {
                            label: '案件名称',
                            prop: 'AJMC',
                            search: false,


                        }, {
                            label: '案别',
                            prop: 'AB',
                            width: 160,
                            search: true,
                            type: 'select',
                            dicData: [
                                {
                                    label: '危害国家安全案',
                                    value: '01'
                                },
                                {
                                    label: '背叛国家案',
                                    value: '02'
                                },
                                {
                                    label: '颠覆国家政权案',
                                    value: '03'
                                }
                            ],
                        }, {
                            label: '案件类型',
                            prop: 'AJLX',
                            type: 'select',
                            hide: true,
                            dicData: [{
                                label: '刑事案件',
                                value: '01'
                            }]
                        }, {
                            label: '案件状态',
                            prop: 'AJSTATE',
                            search: true,
                            type: 'select',
                            dicData: [{
                                label: '已受理',
                                value: '01',
                            }, {
                                label: '已破案',
                                value: '03',
                            }, {
                                label: '已结案',
                                value: '03',
                            }, {
                                label: '已移交',
                                value: '04',
                            }
                            ]
                        }, {
                            label: '作案状态',
                            prop: 'ZAZT',
                            type: 'select',
                            hide: true,

                        }, {
                            label: '受理单位',
                            prop: 'SLJSDW',
                            hide: true,
                        },

                            {
                                label: '接警受理号',
                                prop: 'SL_BJSLH',
                                hide: true,
                            }, {
                                label: '接警方式',
                                prop: 'SL_JJFS',
                                hide: true,
                            },
                            {
                                label: '专案标识',
                                prop: 'ZABZ',
                                hide: true
                            },
                            {
                                label: '涉案单位',
                                prop: 'sadw',
                            },
                            {
                                label: '发案时间初值',
                                prop: 'FASJCZ',
                                type: 'datetime',
                                format: 'yyyy-MM-dd HH:mm',
                                valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            },
                            {
                                label: '发案时间终值',
                                prop: 'FASJZZ',
                                type: 'datetime',
                                format: 'yyyy-MM-dd HH:mm',
                                valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            },{label: '发案处所', prop: 'SLFACS', hide: true,},
                            {label: '发现形式', prop: 'FXXS', hide: true,},
                            {label: '作案时机', prop: 'XZSJ', hide: true,},
                            {label: '选择处所', prop: 'XZCS', hide: true,},
                            {label: '选择对象', prop: 'XZDX', hide: true,},
                            {label: '选择物品', prop: 'XZWP', hide: true,},
                            {label: '作案工具', prop: 'ZAGJ', hide: true,},
                            {label: '选择部位', prop: 'XZBW', hide: true,},
                            {label: '作案人数', prop: 'ZARS', hide: true,},
                            {label: '作案手段特点', prop: 'SDTD', hide: true,},

                        ],
                    },
                        {
                            label: '危害程度',
                            prop: 'whcd',
                            icon: 'el-icon-info',
                            column: [


                                {
                                    label: '危害程度', prop:
                                        'AJWHCD',
                                    hide: true,
                                    type: 'select'
                                },

                                {label: '死亡人数', prop: 'SWRS', hide: true,},
                                {label: '受伤人数', prop: 'SSRS', hide: true,},
                                {label: '损失折款', prop: 'SSJZ', hide: true,},
                                {label: '涉案总值', prop: 'SAZZ', hide: true,},
                                {label: '挽回损失价值', prop: 'WHSSJZ', hide: true,},
                                {label: '案情', prop: 'ZYAQ',span:24},


                            ],
                        },
                        {
                        label: '发案地点',
                        prop: 'fadd',
                        icon: 'el-icon-info',
                        column: [

                            {
                                label: '区县',
                                prop: 'FADD_QX',
                                hide: true,
                                type: 'select'
                            },
                            {
                                label: '街道',
                                prop: 'FADD_JD',
                                hide: true,
                            },
                            {
                                label: '所属警区',
                                prop: 'AJSSJQ',
                                hide: true,
                                type: 'select'
                            },
                            {
                                label: '发案地点详址',
                                prop: 'FADD',
                                hide: true,
                            },
                            {
                                label: '所属社区',
                                prop: 'SSSQ',
                                hide: true,
                                type: 'select'
                            },
                            {label: '发案处所', prop: 'SLFACS', hide: true,},
                            {label: '发现形式', prop: 'FXXS', hide: true,},
                            {
                                label: '危害程度', prop:
                                    'AJWHCD',
                                hide: true,
                                type: 'select'
                            },
                            {label: '补立原因', prop: 'BLYY', hide: true,},



                            {
                                label: '发案地政区划', prop: 'SLFAQH', hide: true,
                                type: 'select'
                            },

                            {label: '督办信息', prop: 'DBXX', hide: true,},
                            {
                                label: '案件来源', prop: 'AJLY', hide: true,
                                type: 'select'
                            },

                            {label: '密级', prop: 'SECURITYGRADE', hide: true,},

                            {
                                label: '督办级别', prop: 'DBJB', hide: true,
                                type: 'select'
                            },
                            {
                                label: '具体案件来源', prop: 'JTAJLY', hide: true,
                                type: 'select'
                            },
                            {
                                label: '犯罪主体类型', prop: 'FZZTLX', hide: true,
                                type: 'select'
                            },
                            {
                                label: '案情是否涉外', prop: 'SFSW', hide: true,
                                type: 'select'
                            },
                            {
                                label: '案情涉及国家地区', prop: 'SJGJDQ', hide: true,
                                type: 'select'
                            },

                        ],
                    },
                        {
                            label: '案件阶段时间',
                            prop: 'jdsj',
                            icon: 'el-icon-info',
                            column: [

                                {
                                    label: '立案日期', prop: 'LASJ', hide: true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {
                                    label: '破案时间', prop: 'PASJ', hide: true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {
                                    label: '结案时间', prop: 'JA_JASJ', hide: true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {
                                    label: '销案时间', prop: 'XA_XASJ', hide: true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {
                                    label: '移送时间', prop: 'YSSJ', hide: true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {label: '接警单位', prop: 'SLJJDW', hide: true,},
                                {label: '受理人', prop: 'SL_LRR', hide: true,},
                                {
                                    label: '受理时间', prop: 'SL_LRSJ', hide: true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {label: '填表人', prop: 'SL_SLRXM', hide: true,},
                                {
                                    label: '填表时间', prop: 'SL_SLSJ', hide: true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },

                                {
                                    label: '立案单位', prop: 'LADW', hide: true,
                                    type: 'select'
                                },
                                {label: '立案人', prop: 'AJLARY', hide: true,},
                                {label: '主办单位', prop: 'ZBDW', hide: true,},
                                {label: '主要办案人', prop: 'AJZBRY', hide: true,},
                                {label: '案件协办人', prop: 'AJXBRY', hide: true,},
                                {label: '办案人联系电话', prop: 'AJBARP', hide: true,},
                                {label: '立案录入人', prop: 'LA_LRR', hide: true,},
                                {
                                    label: '立案录入时间', prop: 'LA_LRSJ', hide: true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {label: '立案批准人', prop: 'LA_PZR', hide: true,},
                                {
                                    label: '立案批时间', prop: 'LA_PZSJ', hide: true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {label: '案后修改人', prop: 'LA_ZHXGR', hide: true,},
                                {
                                    label: '案最后修改时间', prop: 'LA_ZHXGSJ', hide: true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },

                                {label: '移送单位', prop: 'YSDW', hide: true,},
                                {label: '移送承办人', prop: 'YSCBR', hide: true,},
                                {label: '移送单位电话', prop: 'YSDWDH', hide: true,},

                                {
                                    label: '一审时间', prop: 'YISHENSJ', hide: true, type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {
                                    label: '二审时间', prop: 'ESSJ', hide: true, type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {label: '移送单位', prop: 'YSDW_NEW', hide: true,},
                                {label: '移送单位电话', prop: 'YSDWDH_NEW', hide: true,},
                                {label: '移送承办人', prop: 'YSCBR_NEW', hide: true,},
                                {
                                    label: '移送时间', prop: 'YSSJ_NEW', hide: true, type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                            ],
                        }]
                }

            }
        },
        created() {

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