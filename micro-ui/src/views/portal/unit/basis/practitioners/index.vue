<template>
    <div class="execution">
        <basic-container>
            <avue-crud
                       :data="tableData"
                       :option="tableOption"
                       :page="page"
                       @row-update="handleEdit"
                       @row-save="handleSav"
                       v-model="form"
                      >
                <template slot="imgUiForm" slot-scope="scope"  >
                    <img height="100px" width="100px" src="/img/hg.jpg"/>
                </template>

                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            @click="handleDelete()">删除
                    </el-button>
                </template>

            </avue-crud>
        </basic-container>
    </div>
</template>

<script>

    export default {
        data() {
            return {
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                form:{},
                tableData: [
                    {
                        ryssdwlb:'0',
                        rylb:'0',
                        dwmc:'XX电子游戏城',
                        gj: '0',
                        cardType:'0',
                        idCode:'410926188012231201',
                        name:'张三',
                        mz:'0',
                        birthday:'1880-12-23',
                        sex:'0',
                        Tel1:'300861',
                        gwmc:'前台服务员',
                        zwlb:'0',
                        gwbh:'001',
                        sgrq:'2018-01-01',
                        ryzt:'0',
                        zw:'前台',
                        gwzrms:'前台接待',
                        sgzjbh:'001',
                        jyxkz:'有',
                        xzzssx:'4',
                        xzzxz:'广东省江门市恩平市锦江明珠花园',
                        xzzpcs:'5',
                        hjdssx:'4',
                        ryly:'0',
                        zs:'0',
                        zz:'0',
                        whcd:'2',
                        height:'165cm',
                        bloodType:'0',
                        zzmm:'1',
                        hyzk:'0',
                        jkzk:'1',
                        remarks:'',
                    },
                    {
                        ryssdwlb:'1',
                        rylb:'0',
                        dwmc:'XX电子游戏城',
                        gj: '0',
                        cardType:'0',
                        idCode:'440100198512231201',
                        name:'李四',
                        mz:'0',
                        birthday:'1985-12-23',
                        sex:'0',
                        Tel1:'100879',
                        gwmc:'前台服务员',
                        zwlb:'0',
                        gwbh:'001',
                        sgrq:'2018-01-01',
                        ryzt:'0',
                        zw:'前台',
                        gwzrms:'前台接待',
                        sgzjbh:'001',
                        jyxkz:'有',
                        xzzssx:'4',
                        xzzxz:'广东省江门市恩平市锦江明珠花园',
                        xzzpcs:'5',
                        hjdssx:'4',
                        ryly:'0',
                        sfzs:'0',
                        sfzz:'0',
                        whcd:'2',
                        height:'165cm',
                        bloodType:'0',
                        zzmm:'1',
                        hyzk:'0',
                        jkzk:'1',
                        remarks:'',
                    }
                ],
                tableOption: {
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: true,
                    viewBtn: true,
                    delBtn: false,
                    column: [{
                        label: '证件号码',
                        prop: 'idCode',
                        overHidden: true,
                    },
                        {
                            label: '姓名',
                            prop: 'name',
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
                            search:'true',
                            overHidden: true,
                        },
                        {
                            label: '职务类别',
                            prop: 'zwlb',
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
                            prop: 'sgrq',
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
                            prop: 'xzzxz',
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
                        }/*,
                        {
                            label: '所属警务区',
                            prop: 'ssjwq',
                            type: 'select',
                            search:'true',
                            hide:'true',
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
                        }*/
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
                                    prop: 'name',
                                    span:6,
                                    rules: [{
                                        required: true,
                                        message: '请输入人员姓名',
                                        trigger: 'blur'
                                    }]
                                },
                                {
                                    label: '性别',
                                    prop: 'sex',
                                    type:'select',
                                    span:6,
                                    dicData: [{
                                        label: '男',
                                        value: '0'
                                    }, {
                                        label: '女',
                                        value: '1'
                                    }, {
                                        label: '男变女',
                                        value: '2'
                                    }, {
                                        label: '女变男',
                                        value: '3'
                                    }, {
                                        label: '不详',
                                        value: '4'
                                    }
                                    ],
                                    rules: [{
                                        required: true,
                                        message: '请选择性别',
                                        trigger: 'blur'
                                    }]
                                },
                                {
                                    label: '民族',
                                    prop: 'mz',
                                    type:'select',
                                    span:6,
                                    dicData: [{
                                        label: '汉族',
                                        value: '0'
                                    }, {
                                        label: '满族',
                                        value: '1'
                                    }, {
                                        label: '壮族',
                                        value: '2'
                                    }, {
                                        label: '彝族',
                                        value: '3'
                                    }
                                    ],
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
                                    prop: 'gj',
                                    labelWidth: 120,
                                    span:6,
                                    dicData: [{
                                        label: '中国',
                                        value: '0'
                                    }, {
                                        label: '美国',
                                        value: '1'
                                    }, {
                                        label: '意大利',
                                        value: '2'
                                    }, {
                                        label: '韩国',
                                        value: '3'
                                    }
                                    ],
                                    rules: [{
                                        required: true,
                                        message: '请选择国籍(地区)',
                                        trigger: 'blur'
                                    }]
                                },
                                {
                                    label: '证件种类',
                                    prop: 'cardType',
                                    type:'select',
                                    span:6,
                                    dicData: [{
                                        label: '无证件',
                                        value: '0'
                                    }, {
                                        label: '身份证',
                                        value: '1'
                                    }, {
                                        label: '外交护照',
                                        value: '2'
                                    }, {
                                        label: '公务护照',
                                        value: '3'
                                    }, {
                                        label: '普通护照',
                                        value: '4'
                                    }, {
                                        label: '中华人民共和国旅行证',
                                        value: '5'
                                    }
                                    ],
                                    rules: [{
                                        required: true,
                                        message: '请选择证件种类',
                                        trigger: 'blur'
                                    }]
                                },
                                {
                                    label: '证件号码',
                                    prop: 'idCode',
                                    span:6,
                                    rules: [{
                                        required: true,
                                        message: '请输入证件号码',
                                        trigger: 'blur'
                                    }]
                                },
                                {
                                    label: '出生日期',
                                    prop: 'birthday',
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
                                    prop: 'gwmc',
                                    span:6,
                                    rules: [{
                                        required: true,
                                        message: '请输入岗位名称',
                                        trigger: 'blur'
                                    }]
                                },
                                {
                                    label: '岗位编号',
                                    prop: 'gwbh',
                                    span:6
                                },
                                {
                                    label: '职务类别',
                                    prop: 'zwlb',
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
                                    prop: 'sgrq',
                                    span:6,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss'
                                },
                                {
                                    label: '离岗日期',
                                    prop: 'lgrq',
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
                                    prop: 'xzzssx',
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
                                    prop: 'xzzxz',
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
                                    prop: 'xzzjwq',
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
                                    prop: 'xzzpcs',
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
                                    prop: 'dzbm',
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
                                    prop: 'whcd',
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
                                    prop: 'lrsj',
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
                                    prop: 'lrdw',
                                    span:8,
                                    addDisplay:false,
                                    editDisplay:false,
                                    viewDisplay:false
                                }
                            ]
                        }

                    ]

                },

            }
        },
        computed: {
            infoData() {
                return this.form.info
            }
        },
        methods: {
            handleEdit(row, index, done, loading) {
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
        }
    }
</script>

