export const clxqOption = {
    dialogWidth: '100%',
    dialogHeight: '100%',
    dialogFullscreen: true,
    page:false,
    align:'center',
    indexLabel: '序号',
    border: true,
    index: true,
    editBtn:false,
    delBtn:false,
    addBtn:false,
    viewBtn:false,
    refreshBtn: false,
    searchBtn:false,
    columnBtn:false,
    menu:false,
    card:true,
    group: [
        {
            label: '车辆信息',
            prop: 'jbxx',
            icon: 'el-icon-info',
            column: [
                {prop: 'imgUrl',formslot: true,},
                {label: '所有人名称', prop: 'jdcsyrmc',},
                {label: '车牌号码', prop: 'jdchphm',},
                {label: '车辆类型', prop: 'jdccllxdm',dicUrl: '/portal/dict/getDict/cllx/jx',},
                {label: '车辆型号', prop: 'clxh',},
                {label: '中文品牌名称', prop: 'zwppmc',},
                {label: '英文品牌名称', prop: 'ywppmc',},
                {label: '车身颜色', prop: 'jdccsysdm',dicUrl: '/portal/dict/getDict/csys/jx',},
                {label: '车身颜色第一辅色', prop: 'jdccsysdyfsdm',},
                {label: '车身颜色第二辅色', prop: 'jdccsysdefsdm',},
                {label: '发动机号', prop: 'jdcfdjddjh',},
                {label: '行驶公里数', prop: 'jdcxsgls'},
            ]
        },
        {
            label: '送修登记',
            prop: 'sxdj',
            icon: 'el-icon-info',
            column: [
                {label: '送修人', prop: 'sxrxm',},
                {label: '证件号码', prop: 'sxrzjhm',},
                {label: '常用证件代码', prop: 'sxrcyzjdm',dicUrl: '/portal/dict/getDict/cyzjlx/jx'},
                {label: '联系电话', prop: 'sxrlxdh',},
                {label: '送修时间', prop: 'sxsj',},
                {label: '收车人', prop: 'scrxm',},
            ]
        },
        {
            label: '交接登记',
            prop: 'jjdj',
            icon: 'el-icon-info',
            column: [
                {label: '取车人', prop: 'qcrxm',},
                {label: '证件号码', prop: 'qcrzjhm',},
                {label: '常用证件代码', prop: 'qcrcyzjdm',dicUrl: '/portal/dict/getDict/cyzjlx/jx'},
                {label: '联系电话', prop: 'qcrlxdh',},
                {label: '取车时间', prop: 'qcsj',},
            ]
        },
    ]
}

export const formOption = {
    dialogWidth: '100%',
    dialogHeight: '100%',
    dialogFullscreen: true,
    page:false,
    align:'center',
    indexLabel: '序号',
    border: true,
    index: true,
    editBtn:false,
    delBtn:false,
    addBtn:false,
    viewBtn:false,
    refreshBtn: false,
    searchBtn:false,
    columnBtn:false,
    menu:false,
    card:true,
    group: [
        {
            label: '基本信息',
            prop: 'jbxx',
            icon: 'el-icon-info',
            column: [
                {prop: 'imgUrl',formslot:true,},
                {label: '姓名', prop: 'xm'},
                {label: '出生日期', prop: 'rybaCsrq',},
                {label: '证件号码', prop: 'zjhm',},
                {label: '别名', prop: 'rybaBieming',},
                {label: '婚姻状况', prop: 'rybaHyzkdm',},
                {label: '文化程度', prop: 'xldm',dicUrl: '/portal/dict/getDict/WHCD/ly'},
                {label: '性别',prop: 'xbdm',dicData:[{label:'男',value:'1'},{label:'女',value:'2'}]},
                {label: '户籍地址', prop: 'hjdzqhnxxdz',},
                {label: '居住地址', prop: 'dzmcqhnxxdz',},
                {label: '民族', prop: 'mzdm', dicUrl: '/portal/dict/getDict/0201/ly'},
                {label: '签发机关', prop: 'sjgsdwmc',},
                {label: '健康状况', prop: 'rybaJkzk',},
                {label: '国籍', prop: 'gjdm',dicUrl: '/portal/dict/getDict/0223/ly'},
                {label: '体检日期', prop: 'rybaTjrq',},
                {label: '常用证件代码', prop: 'cyzjdm',dicUrl: '/portal/dict/getDict/cyzjlx/jx'},
                {label: '身高', prop: 'rybaSg',},
                {label: '暂住证号码', prop: 'rybaZzzhm'},
                {label: '户籍所在地区划代码', prop: 'hjdzdzbm'},
                {label: '血型', prop: 'rybaXx',},
                {label: '备注', prop: 'rybaBz', span: 12},
            ]
        },
        {
            label: '从业情况',
            prop: 'cyqk',
            icon: 'el-icon-info',
            column: [
                {label: '从业单位', prop: 'dwmc',},
                {label: '联系电话', prop: 'rybaLxdh',},
                {label: '从业人员类别', prop: 'gzmc',},
                {label: '岗位名称', prop: 'gzmc',},
                {label: '岗位职责', prop: 'gzmc',},
                {label: '从业人员编号', prop: 'rybaBh',},
                {label: '从业状态', prop: 'rybaCyzt',},
                {label: '所属负责人', prop: 'rybaSsfzr',},
                {label: '所属负责人电话', prop: 'rybaSsfzrdh',},
                {label: '岗位编号', prop: 'rybaGwbh',},
                {label: '紧急联系人', prop: 'rybaJjlxr',},
                {label: '紧急联系人电话', prop: 'rybaJjlxrdh',},
                {label: '离职时间', prop: 'lzrq',},
            ]
        },
        {
            label: '备案情况',
            prop: 'baqk',
            icon: 'el-icon-info',
            column: [
                {label: '备案状态', prop: 'state',},
                {label: '备案节点', prop: 'notpass',},
                {label: '登记日期', prop: 'djsj',},
                {label: '更新日期', prop: 'gxsj',},
                {label: '注销日期', prop: 'rybaZxsj',},
                {label: '变更状态', prop: 'bgState',},
                {label: '变更节点', prop: 'bgNotpass',},
                {label: '制卡状态', prop: 'rybaZhika',},
                {label: '版 本 号', prop: 'version',},
                {label: '补备原因', prop: 'rybaBbyy',},
                {label: '补备告知日期', prop: 'rybaBbgzsj',},
                {label: '补备完成日期', prop: 'rybaBbwcsj',},
                {label: '变更登记时间', prop: 'bgDjsj',},
                {label: '变更通过时间', prop: 'bgTgsj',},
                {label: '备案(通过)日期', prop: 'rybaBasj',},
            ]
        },
    ]
}

export const cycyOption = {
        dialogWidth: '100%',
        dialogHeight: '100%',
        dialogFullscreen: true,
        border: true,
        index: true,
        indexLabel: '序号',
        stripe: true,
        menuAlign: 'center',
        align: 'center',
        addBtn: false,
        viewBtn: true,
        editBtn: false,
        delBtn: false,
        column: [
            {
            label: '证件号码',
            prop: 'zjhm',
            overHidden: true,
        },
            {
                label: '姓名',
                prop: 'xm',
                search:'true',
                overHidden: true,
            },
            {
                label: '',
                prop: 'imgUi',
                hide:true,
                slot:true,
                formslot:true,
            },

            {
                label: '单位名称',
                prop: 'dwmc',
                overHidden: true,
            },
            {
                label: '职务类别',
                prop: 'gzmc',
                search:'true',
                type: 'select',
                overHidden: true,
                dicData: [{
                    label: '服务人员',
                    value: '0'
                }, {
                    label: '管理人员',
                    value: '1'
                }, {
                    label: '一般服务人员',
                    value: '2'
                }, {
                    label: '其他管理人员',
                    value: '3'
                }
                ]
            },
            {
                label: '上岗日期',
                prop: 'rzrq',
                format: 'yyyy-MM-dd HH:mm',
                valueFormat: 'yyyy-MM-dd HH:mm:ss',
                overHidden: true,
            },
            {
                label: '所属单位类别',
                prop: 'ryssdwlb',
                type: 'select',
                overHidden: true,
                dicData: [{
                    label: '旅馆业',
                    value: '0'
                }, {
                    label: '歌舞娱乐场所',
                    value: '1'
                }, {
                    label: '游戏游艺场所',
                    value: '2'
                }, {
                    label: '机修业',
                    value: '3'
                }, {
                    label: '按摩服务场所',
                    value: '4'
                }
                ]
            }
            ,
            {
                label: '人员状态',
                prop: 'ryzt',
                overHidden: true,
                dicData: [{
                    label: '在职',
                    value: '0'
                }, {
                    label: '离职',
                    value: '1'
                }
                ]
            } ,
            {
                label: '现住址详址',
                prop: 'dzmcqhnxxdz',
                overHidden: true,
            },
            {
                label: '数据来源',
                prop: 'source',
                type: 'select',
                hide:true,
                overHidden: true,
                dicData: [{
                    label: '治综',
                    value: '0'
                }, {
                    label: '警钟',
                    value: '1'
                }, {
                    label: '旅业',
                    value: '2'
                }
                ]
            }
        ],
        card:true,
        group:[
            {
                label: '基本信息',
                prop: 'jbxx',
                icon: 'el-icon-info',
                column: [
                    {
                        label: '姓名',
                        prop: 'xm',
                        span:6,
                        rules: [{
                            required: true,
                            message: '请输入人员姓名',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '性别',
                        prop: 'xbdm',
                        type:'select',
                        span:6,
                        dicData:[{label:'男',value:1},{label:'女',value:2}],
                        rules: [{
                            required: true,
                            message: '请选择性别',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '民族',
                        prop: 'mzdm',
                        type:'select',
                        span:6,
                        dicUrl: '/portal/dict/getDict/0201/jx',
                        rules: [{
                            required: true,
                            message: '请选择所属民族',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '',
                        prop: 'imgUi',
                        span:6,
                        slot:true,
                        formslot:true,
                    },
                    {
                        label: '国籍(地区)',
                        type: 'select',
                        prop: 'gjdm',
                        labelWidth: 120,
                        span:6,
                        rules: [{
                            required: true,
                            message: '请选择国籍(地区)',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '证件种类',
                        prop: 'cyzjdm',
                        type:'select',
                        span:6,
                        dicUrl: '/portal/dict/getDict/cyzjlx/jx'
                    },
                    {
                        label: '证件号码',
                        prop: 'zjhm',
                        span:6,
                        rules: [{
                            required: true,
                            message: '请输入证件号码',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '出生日期',
                        prop: 'csrq',
                        type: 'date',
                        span:6,
                        rules: [{
                            required: true,
                            message: '请选择出生日期',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '所属单位类别',
                        prop: 'ryssdwlb',
                        type: 'select',
                        labelWidth: 120,
                        span:6,
                        dicData: [{
                            label: '旅馆业',
                            value: '0'
                        }, {
                            label: '歌舞娱乐场所',
                            value: '1'
                        }, {
                            label: '游戏游艺场所',
                            value: '2'
                        }, {
                            label: '机修业',
                            value: '3'
                        }, {
                            label: '按摩服务场所',
                            value: '4'
                        }
                        ]
                    },
                    {
                        label: '单位名称',
                        prop: 'dwmc',
                        span:6,
                        rules: [{
                            required: true,
                            message: '请输入单位名称',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '人员类别',
                        prop: 'rylb',
                        type: 'select',
                        span:6,
                        dicData: [{
                            label: '流动人员',
                            value: '0'
                        }, {
                            label: '本地户籍人员',
                            value: '1'
                        }, {
                            label: '外来人员',
                            value: '2'
                        }
                        ]
                    },
                    {
                        label: '联系方式1',
                        prop: 'Tel1',
                        labelWidth: 100,
                        span:6,
                        rules: [{
                            required: true,
                            message: '请输入联系方式',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '联系方式2',
                        prop: 'Tel2',
                        span:6
                    },
                    {
                        label: '英文姓',
                        prop: 'eSurname',
                        span:6
                    },
                    {
                        label: '英文名',
                        prop: 'eName',
                        span:6
                    }
                ]
            },
            {
                label: '工作岗位',
                prop: 'gzgw',
                icon: 'el-icon-info',
                column: [
                    {
                        label: '岗位名称',
                        prop: 'gzmc',
                        span:6,
                        rules: [{
                            required: true,
                            message: '请输入岗位名称',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '岗位编号',
                        prop: 'xxid',
                        span:6
                    },
                    {
                        label: '职务类别',
                        prop: 'gzmc',
                        span:6,
                        type: 'select',
                        dicData: [{
                            label: '服务人员',
                            value: '0'
                        }, {
                            label: '管理人员',
                            value: '1'
                        }, {
                            label: '一般服务人员',
                            value: '2'
                        }, {
                            label: '其他管理人员',
                            value: '3'
                        }
                        ]
                    },
                    {
                        label: '人员状态',
                        prop: 'ryzt',
                        span:6,
                        type: 'select',
                        dicData: [{
                            label: '在职',
                            value: '0'
                        }, {
                            label: '离职',
                            value: '1'
                        }
                        ]
                    },
                    {
                        label: '上岗日期',
                        prop: 'rzrq',
                        span:6,
                        type: 'datetime',
                        format: 'yyyy-MM-dd HH:mm',
                        valueFormat: 'yyyy-MM-dd HH:mm:ss'
                    },
                    {
                        label: '离岗日期',
                        prop: 'lzrq',
                        span:6,
                        type: 'datetime',
                        format: 'yyyy-MM-dd HH:mm',
                        valueFormat: 'yyyy-MM-dd HH:mm:ss'
                    },
                    {
                        label: '职务',
                        prop: 'zw',
                        span:6
                    },
                    {
                        label: '岗位责任描述',
                        prop: 'gwzrms',
                        labelWidth: 120,
                        span:6
                    },
                    {
                        label: '上岗证件编号',
                        prop: 'sgzjbh',
                        labelWidth: 120,
                        span:6
                    },
                    {
                        label: '有无就业许可证',
                        prop: 'jyxkz',
                        labelWidth: 120,
                        span:6
                    },
                    {
                        label: '外国人就业许可证编号',
                        prop: 'wgrjyxkzbh',
                        labelWidth: 160,
                        span:12
                    }
                ]
            },
            {
                label: '地址信息',
                prop: 'dzxx',
                icon: 'el-icon-info',
                column: [
                    {
                        label: '现住址省市县(区)',
                        prop: 'hjdzssxqdm',
                        type: 'select',
                        labelWidth: 130,
                        span:8,
                        dicData: [{
                            label: '广东省江门市蓬江区',
                            value: '0'
                        }, {
                            label: '广东省开平市',
                            value: '1'
                        }, {
                            label: '广东省广州市',
                            value: '2'
                        }, {
                            label: '广东省东莞市',
                            value: '3'
                        }
                            , {
                                label: '广东省恩平市',
                                value: '4'
                            }
                        ],
                        rules: [{
                            required: true,
                            message: '请选择现住址省市县(区)',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '现住址详址',
                        prop: 'hjdzqhnxxdz',
                        labelWidth: 120,
                        span:8,
                        rules: [{
                            required: true,
                            message: '请输入现住址详址',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '现住址警务区',
                        prop: 'sjgsdwmc',
                        labelWidth: 120,
                        span:8,
                        type: 'select',
                        dicData: [{
                            label: '海傍社区警务区',
                            value: '0'
                        }, {
                            label: '甘化社区警务区',
                            value: '1'
                        }, {
                            label: '常安社区警务区',
                            value: '2'
                        }, {
                            label: '常安社区警务区',
                            value: '3'
                        }, {
                            label: '良化北社区警务区',
                            value: '4'
                        }
                        ]
                    },
                    {
                        label: '现住址派出所',
                        prop: 'sjgsdwdm',
                        type: 'select',
                        labelWidth: 120,
                        span:8,
                        dicData: [
                            {
                                label: '江门市公安局',
                                value: '4'
                            },{
                                label: '广州市公安局花都区分局指挥中心',
                                value: '0'
                            }, {
                                label: '深圳市公安局罗湖分局行政科',
                                value: '1'
                            }, {
                                label: '开平市公安局谭江派出所',
                                value: '2'
                            }, {
                                label: '深圳市公安局罗湖分局监察科',
                                value: '3'
                            },{
                                label: '恩平市公安局',
                                value: '5'
                            },
                        ],
                        rules: [{
                            required: true,
                            message: '请选择现住址派出所',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '户籍地详址',
                        prop: 'hjdxz',
                        labelWidth: 120,
                        span:8
                    },
                    {
                        label: '户籍地省市县(区)',
                        prop: 'hjdssx',
                        type: 'select',
                        labelWidth: 120,
                        span:8,
                        dicData: [{
                            label: '广东省江门市蓬江区',
                            value: '0'
                        }, {
                            label: '广东省开平市',
                            value: '1'
                        }, {
                            label: '广东省广州市',
                            value: '2'
                        }, {
                            label: '广东省东莞市',
                            value: '3'
                        }, {
                            label: '广东省恩平市',
                            value: '4'
                        }
                        ]
                    },
                    {
                        label: '地址编码',
                        prop: 'dzmcdzbm',
                        span:8
                    },
                    {
                        label: '现住址社区居委会/村委会',
                        prop: 'xzzjwh',
                        labelWidth: 175,
                        span:16,
                        type: 'select',
                        dicData: [{
                            label: '群星社区',
                            value: '0'
                        }, {
                            label: '棠下社区',
                            value: '1'
                        }
                        ]
                    }
                ]
            },
            {
                label: '属性信息',
                prop: 'sxxx',
                icon: 'el-icon-info',
                column: [
                    {
                        label: '人员来源',
                        prop: 'ryly',
                        type: 'select',
                        span:6,
                        rules: [{
                            required: true,
                            message: '请选择人员来源',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '手机串号',
                        prop: 'sjch',
                        span:6
                    },
                    {
                        label: '工作管辖范围',
                        prop: 'gzgxfw',
                        labelWidth: 120,
                        span:6
                    },
                    {
                        label: '培训情况',
                        prop: 'pxqk',
                        type: 'select',
                        span:6
                    },
                    {
                        label: '紧急联系人电话',
                        prop: 'jjlxrTel',
                        labelWidth: 120,
                        span:6
                    },
                    {
                        label: '紧急联系人',
                        prop: 'jjlxr',
                        labelWidth: 100,
                        span:6
                    },
                    {
                        label: '是否政审',
                        prop: 'zs',
                        type: 'radio',
                        span:6,
                        dicData: [{
                            label: '是',
                            value: '0'
                        }, {
                            label: '否',
                            value: '1'
                        }
                        ]
                    },
                    {
                        label: '是否专职',
                        prop: 'zz',
                        type: 'radio',
                        span:6,
                        dicData: [{
                            label: '是',
                            value: '0'
                        }, {
                            label: '否',
                            value: '1'
                        }
                        ]
                    }
                ]
            },
            {
                label: '其他信息',
                prop: 'qtxx',
                icon: 'el-icon-info',
                column: [
                    {
                        label: '文化程度',
                        prop: 'xldm',
                        type: 'select',
                        span:6,
                        dicData: [{
                            label: '本科',
                            value: '0'
                        }, {
                            label: '专科',
                            value: '1'
                        }, {
                            label: '高中',
                            value: '2'
                        }, {
                            label: '初中',
                            value: '3'
                        }
                        ],
                        rules: [{
                            required: true,
                            message: '请选择文化程度',
                            trigger: 'blur'
                        }]
                    },
                    {
                        label: '政治面貌',
                        prop: 'zzmm',
                        type: 'select',
                        span:6,
                        dicData: [{
                            label: '党员',
                            value: '0'
                        }, {
                            label: '共青团员',
                            value: '1'
                        }, {
                            label: '无',
                            value: '2'
                        }
                        ]
                    },
                    {
                        label: '婚姻状况',
                        prop: 'hyzk',
                        type: 'select',
                        span:6,
                        dicData: [{
                            label: '未婚',
                            value: '0'
                        }, {
                            label: '已婚',
                            value: '1'
                        }, {
                            label: '离婚',
                            value: '2'
                        }
                        ]
                    },
                    {
                        label: '健康状况',
                        prop: 'jkzk',
                        type: 'select',
                        span:6,
                        dicData: [{
                            label: '优',
                            value: '0'
                        }, {
                            label: '良',
                            value: '1'
                        }, {
                            label: '差',
                            value: '2'
                        }
                        ]
                    },
                    {
                        label: '身高',
                        prop: 'height',
                        span:12
                    },
                    {
                        label: '血型',
                        prop: 'bloodType',
                        type: 'select',
                        span:12,
                        dicData: [{
                            label: 'A型',
                            value: '0'
                        }, {
                            label: 'B型',
                            value: '1'
                        }, {
                            label: 'O型',
                            value: '2'
                        }
                        ]
                    },
                    {
                        label: '备注',
                        prop: 'remarks',
                        type: 'textarea',
                        span:24
                    }
                ]
            },
            {
                column: [
                    {
                        label: '录入人',
                        prop: 'lrr',
                        span:8,
                        addDisplay:false,
                        editDisplay:false,
                        viewDisplay:false
                    },
                    {
                        label: '录入时间',
                        prop: 'djsj',
                        type: 'datetime',
                        format: 'yyyy-MM-dd HH:mm',
                        valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        span:8,
                        addDisplay:false,
                        editDisplay:false,
                        viewDisplay:false
                    },
                    {
                        label: '录入单位',
                        prop: 'sjgsdwmc',
                        span:8,
                        addDisplay:false,
                        editDisplay:false,
                        viewDisplay:false
                    }
                ]
            }

        ]

    }

export const cjclOption = {

    dialogWidth: '100%',
    dialogHeight: '100%',
    dialogFullscreen: true,
    align:'center',
    indexLabel: '序号',
    stripe: true,
    border: true,
    index: true,
    editBtn:false,
    delBtn:false,
    addBtn:false,
    viewBtn:true,
    menuWidth:'100',
    card:true,
    column:[
        {
            label: "业务流水号",
            prop: "ywlsh",
            span:6
        },
        {
            label: "单位编号",
            prop: "zagldwbm",
            span:6
        },
        {
            label: "单位名称",
            prop: "dwmc",
            span:6,

        },
        {
            label:"机动车所有人",
            prop:"jdcsyrmc",
            span:6
        },
        {
            label:"车辆类型",
            prop:"jdccllxdm",
            dicUrl: '/portal/dict/getDict/cllx/jx',
            viewDisplay:true,

            span:6
        },
        {
            label:"品牌名称",
            prop:"zwppmc",
            viewDisplay:true,
            span:6
        },
        {
            label:"车辆型号",
            prop:"clxh",
            viewDisplay:true,
            span:6
        },
        {
            label:"车身颜色",
            prop:"jdccsysdm",
            viewDisplay:true,
            dicUrl: '/portal/dict/getDict/csys/jx',
            span:6
        },
        {
            label:"车牌号码",
            prop:"jdchphm",
            search:true,
            viewDisplay:true,
            span:6
        },
        {
            label:"送修人",
            prop:"sxrxm",
            viewDisplay:true,
            span:6
        },
        {
            label:"送修人证件号码",
            prop:"sxrzjhm",
            viewDisplay:true,
            span:6
        },
        {
            label:"收车人",
            prop:"scrxm",
            viewDisplay:true,
            span:6
        },
        {
            label:"送修时间",
            prop:"sxsj",
            viewDisplay:true,
            span:6,
        },
        {
            label:"简要情况",
            prop:"clgzjyqk",
            hide:true,
            viewDisplay:true,
            span:6
        }
    ]
}

export const cyryDict = {
    /**从业人员 人员从业状态 */
    ryzt: [
        {label: '在职', value: '10'},
        {label: '离职', value: '20'},
        {label: '在岗', value: '11'},
        {label: '出差', value: '12'},
        {label: '请假', value: '13'},
        {label: '其他在职状态', value: '19'},
        {label: '其他状态', value: '90'},
        {label: '其他', value: '99'}
    ],
    /**从业人员 人员类别*/
    rylb: [
        {label: '管理人员', value: '10'},
        {label: '法定代表人或主要负责人', value: '11'},
        {label: '主要股东组成人员', value: '12'},
        {label: '治安负责人', value: '13'},
        {label: '其他管理人员', value: '19'},
        {label: '服务人员', value: '20'},
        {label: '一般服务人员', value: '21'},
        {label: '临时服务人员', value: '22'},
        {label: '演艺人员', value: '23'},
        {label: '其他服务人员', value: '29'},
        {label: '保安人员', value: '30'},
        {label: '安全检查人员', value: '40'},
        {label: '其他从业人员', value: '99'}
    ],
    /**人员性别 */
    xbdm: [
        {label: '男', value: 1},
        {label: '女', value: 2},
    ],
    /**人员性别 */
    zddwbs: [
        {label: '是', value: 0},
        {label: '否', value: 1},
    ],
    /**企业等级 */
    dwdj: [
        {label: '一类', value: 0},
        {label: '二类', value: 1},
        {label: '三类', value: 3},
        {label: '其他', value: 4},
    ],

    /**车身颜色*/
    jdccsysdm:[
        {label: '白', value: 'A'},
        {label: '灰', value: 'B'},
        {label: '黄', value: 'C'},
        {label: '粉', value: 'D'},
        {label: '红', value: 'E'},
        {label: '紫', value: 'F'},
        {label: '绿', value: 'G'},
        {label: '蓝', value: 'H'},
        {label: '棕', value: 'I'},
        {label: '黑', value: 'J'},
        {label: '其他', value: 'Z'},

    ],
    /**车辆类型 */
    jdccllxdm:[
        {label: '客车', value: 'K'},
        {label: '货车', value: 'H'},
        {label: '牵引车', value: 'Q'},
        {label: '农用车', value: 'N'},
        {label: '轮式机械', value: 'J'},
        {label: '半挂车', value: 'B'},
        {label: '专项作业车', value: 'Z'},
        {label: '电车', value: 'D'},
        {label: '摩托车', value: 'M'},
        {label: '拖拉机', value: 'T'},
        {label: '全挂车', value: 'G'},
        {label: '其他', value: 'X'},
        {label: '大型普通客车', value: 'K11'},
        {label: '大型双层客车', value: 'K12'},
        {label: '大型卧铺客车', value: 'K13'},
        {label: '大型铰接客车', value: 'K14'},
        {label: '大型越野客车', value: 'K15'},
        {label: '中型普通客车', value: 'K21'},
        {label: '中型双层客车', value: 'K22'},
        {label: '中型卧铺客车', value: 'K23'},
        {label: '中型铰接客车', value: 'K24'},
        {label: '中型越野客车', value: 'K25'},
        {label: '小型普通客车', value: 'K31'},
        {label: '小型越野客车', value: 'K32'},
        {label: '轿车', value: 'K33'},
        {label: '微型普通客车', value: 'K41'},
        {label: '微型越野客车', value: 'K42'},
        {label: '微型轿车', value: 'K43'},
        {label: '重型普通货车', value: 'H11'},
        {label: '重型厢式货车', value: 'H12'},
        {label: '重型封闭货车', value: 'H13'},
        {label: '重型罐式货车', value: 'H14'},
        {label: '重型平板货车', value: 'H15'},
        {label: '重型集装箱车', value: 'H16'},
        {label: '重型自卸货车', value: 'H17'},
        {label: '重型特殊结构货车', value: 'H18'},
        {label: '中型普通货车', value: 'H21'},
        {label: '中型厢式货车', value: 'H22'},
        {label: '中型封闭货车', value: 'H23'},
        {label: '中型罐式货车', value: 'H24'},
        {label: '中型平板货车', value: 'H25'},
        {label: '中型集装箱车', value: 'H26'},
        {label: '中型自卸货车', value: 'H27'},
        {label: '中型特殊结构货车', value: 'H28'},
        {label: '轻型普通货车', value: 'H31'},
        {label: '轻型厢式货车', value: 'H32'},
        {label: '轻型封闭货车', value: 'H33'},
        {label: '轻型罐式货车', value: 'H34'},
        {label: '轻型平板货车', value: 'H35'},
        {label: '轻型自卸货车', value: 'H37'},
        {label: '轻型特殊结构货车', value: 'H38'},
        {label: '微型普通货车', value: 'H41'},
        {label: '微型厢式货车', value: 'H42'},
        {label: '微型封闭货车', value: 'H43'},
        {label: '微型罐式货车', value: 'H44'},
        {label: '微型自卸货车', value: 'H45'},
        {label: '微型特殊结构货车', value: 'H46'},
        {label: '重型半挂牵引车', value: 'Q11'},
        {label: '中型半挂牵引车', value: 'Q21'},
        {label: '轻型半挂牵引车', value: 'Q31'},
        {label: '大型专项作业车', value: 'Z11'},
        {label: '中型专项作业车', value: 'Z21'},
        {label: '小型专项作业车', value: 'Z31'},
        {label: '微型专项作业车', value: 'Z41'},
        {label: '重型专项作业车', value: 'Z51'},
        {label: '轻型专项作业车', value: 'Z71'},
        {label: '无轨电车', value: 'D11'},
        {label: '有轨电车', value: 'D12'},
        {label: '普通正三轮摩托车', value: 'M11'},
        {label: '轻便正三轮摩托车', value: 'M12'},
        {label: '正三轮载客摩托车', value: 'M13'},
        {label: '正三轮载货摩托车', value: 'M14'},
        {label: '侧三轮摩托车', value: 'M15'},
        {label: '普通二轮摩托车', value: 'M21'},
        {label: '轻便二轮摩托车', value: 'M22'},
        {label: '三轮农用运输车', value: 'N11'},
        {label: '四轮农用普通货车', value: 'N21'},
        {label: '四轮农用厢式货车', value: 'N22'},
        {label: '四轮农用罐式货车', value: 'N23'},
        {label: '四轮农用自卸货车', value: 'N24'},
        {label: '大型轮式拖拉机', value: 'T11'},
        {label: '小型轮式拖拉机', value: 'T21'},
        {label: '手扶拖拉机', value: 'T22'},
        {label: '手扶变形运输机', value: 'T23'},
        {label: '轮式装载机械', value: 'J11'},
        {label: '轮式挖掘机械', value: 'J12'},
        {label: '轮式平地机械', value: 'J13'},
        {label: '重型普通全挂车', value: 'G11'},
        {label: '重型厢式全挂车', value: 'G12'},
        {label: '重型罐式全挂车', value: 'G13'},
        {label: '重型平板全挂车', value: 'G14'},
        {label: '重型集装箱全挂车', value: 'G15'},
        {label: '重型自卸全挂车', value: 'G16'},
        {label: '中型普通全挂车', value: 'G21'},
        {label: '中型厢式全挂车', value: 'G22'},
        {label: '中型罐式全挂车', value: 'G23'},
        {label: '中型平板全挂车', value: 'G24'},
        {label: '中型集装箱全挂车', value: 'G25'},
        {label: '中型自卸全挂车', value: 'G26'},
        {label: '轻型普通全挂车', value: 'G31'},
        {label: '轻型厢式全挂车', value: 'G32'},
        {label: '轻型罐式全挂车', value: 'G33'},
        {label: '轻型平板全挂车', value: 'G34'},
        {label: '轻型自卸全挂车', value: 'G35'},
        {label: '重型普通半挂车', value: 'B11'},
        {label: '重型厢式半挂车', value: 'B12'},
        {label: '重型罐式半挂车', value: 'B13'},
        {label: '重型平板半挂车', value: 'B14'},
        {label: '重型集装箱半挂车', value: 'B15'},
        {label: '重型自卸半挂车', value: 'B16'},
        {label: '中型普通半挂车', value: 'B21'},
        {label: '中型厢式半挂车', value: 'B22'},
        {label: '中型罐式半挂车', value: 'B23'},
        {label: '中型平板半挂车', value: 'B24'},
        {label: '中型集装箱半挂车', value: 'B25'},
        {label: '中型自卸半挂车', value: 'B26'},
        {label: '轻型普通半挂车', value: 'B31'},
        {label: '轻型厢式半挂车', value: 'B32'},
        {label: '轻型罐式半挂车', value: 'B33'},
        {label: '轻型平板半挂车', value: 'B34'},
        {label: '轻型自卸半挂车', value: 'B35'},
        {label: '其他', value: 'X99'},

    ],

    getLabel: (obj, val) => {
        let name = val
        if (Array.isArray(obj) && obj.length > 0) {
            for (let i = 0; i < obj.length; i++) {
                if (obj[i].value == val) {
                    name = obj[i].label
                    break;
                }
            }
        }return name
    }
}

