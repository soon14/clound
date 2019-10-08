<template>
    <div class="execution">
        <basic-container>
            <avue-crud
                       :data="tableData"
                       :option="tableOption"
                       :page="page"
                       @row-save="handleSav"
                       v-model="form"
                      >
                <template slot="menu" slot-scope="scope" >
                    <label v-if="showHideView(scope.row.status)" style="padding-left: 10px">
                        <el-button size="mini"
                                   type="text"
                                   icon="el-icon-view"
                                   @click="viewFlag=true">查看
                        </el-button>
                    </label>
                    <label v-if="showHideEdit(scope.row.status)" style="padding-left: 10px">
                        <el-button size="mini"
                                   type="text"
                                   icon="el-icon-edit"
                                   @click="editFlag=true">处理
                        </el-button>
                    </label>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog title="预警查看" fullscreen :visible.sync="viewFlag" :append-to-body="true">
            <avue-detail  :option="formOption"  v-model="viewData" >

            </avue-detail>

            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="viewFlag=false">关 闭
                </el-button>
            </div>

        </el-dialog>

        <el-dialog title="预警处理" fullscreen :visible.sync="editFlag" :append-to-body="true">
            <avue-form  :option="formOption"  v-model="formData" >
            </avue-form>
            <div slot="footer"  class="el-dialog--center">
                <el-button type="primary" @click="handleSav">提 交
                </el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    let dict = {
        status: [{
            label: '未处理',
            value: '0'
        }, {
            label: '已处理',
            value: '1'
        }
        ],
        level: [{
            label: '一般(Ⅳ级)',
            value: '0'
        }, {
            label: '较重(Ⅲ级)',
            value: '1'
        }, {
            label: '严重(Ⅱ级)',
            value: '2'
        }, {
            label: '特别严重(Ⅰ级)',
            value: '3'
        }
        ],
        sspcs: [{
            label: '西街派出所',
            value: '440703100000'
        },{
            label: '东街派出所',
            value: '440703200000'
        },{
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
            }],
        ssxq: [
            {
                label: '江门',
                value: '0'
            },
            {
                label: '广州',
                value: '1'
            },
            {
                label: '东莞',
                value: '2'
            }, {
                label: '深圳',
                value: '3'
            }

        ]
    }
    export default {
        data() {
            return {
                page: {
                    total: 6, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                viewFlag:false,
                editFlag:false,
                form:{},
                formData:
                    {
                        name:'涉黄人员聚集',
                        remarks:'涉黄人员聚集',
                        type:13,
                        publishDate:'2019-03-27 12:00:00',
                        status: '0',
                        level:'0',
                        ssxq:'0',
                        sspcs:'西街派出所',
                        yjcs:'七天酒店',
                        method:'',
                        dealBy:'',
                        dealDate:'',
                    },
                viewData:
                    {
                        name:'涉赌人员聚集',
                        remarks:'涉赌人员聚集',
                        type:12,
                        publishDate:'2019-03-25 10:00:00',
                        status: '1',
                        level:'3',
                        ssxq:'0',
                        sspcs:'西街派出所',
                        yjcs:'大三元',
                        method:'抓捕犯罪嫌疑人张三、李四，立案并做刑事拘留处理',
                        dealBy:'张警官',
                        dealDate:'2019-03-26 12:00:00',

                    },
                tableData: [
                    {
                        name:'涉黄人员聚集',
                        remarks:'涉黄人员聚集',
                        type:13,
                        publishDate:'2019-03-27 12:00:00',
                        status: '0',
                        level:'0',
                        ssxq:'0',
                        sspcs:'440703100000',
                        dealDate:'',
                    },
                    {
                        name:'涉黄人员聚集',
                        remarks:'涉黄人员聚集',
                        type:13,
                        publishDate:'2019-03-27 12:00:00',
                        status: '0',
                        level:'0',
                        ssxq:'0',
                        sspcs:'440703100000',
                        dealDate:'',
                    },
                    {
                        name:'涉黄人员聚集',
                        remarks:'涉黄人员聚集',
                        type:13,
                        publishDate:'2019-03-27 12:00:00',
                        status: '0',
                        level:'0',
                        ssxq:'0',
                        sspcs:'440703100000',
                        dealDate:'',
                    },
                    {
                        name:'涉赌人员聚集',
                        remarks:'涉赌人员聚集',
                        type:12,
                        publishDate:'2019-03-25 10:00:00',
                        status: '1',
                        level:'3',
                        ssxq:'0',
                        sspcs:'440703100000',
                        dealDate:'2019-03-26 12:00:00',
                    },
                    {
                        name:'涉赌人员聚集',
                        remarks:'涉赌人员聚集',
                        type:12,
                        publishDate:'2019-03-25 10:00:00',
                        status: '1',
                        level:'3',
                        ssxq:'0',
                        sspcs:'440703100000',
                        dealDate:'2019-03-26 12:00:00',
                    },
                    {
                        name:'涉赌人员聚集',
                        remarks:'涉赌人员聚集',
                        type:12,
                        publishDate:'2019-03-25 10:00:00',
                        status: '1',
                        level:'3',
                        ssxq:'0',
                        sspcs:'440703100000',
                        dealDate:'2019-03-26 12:00:00',
                    }
                ],
                tableOption: {
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    menuWidth:'120',
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    editBtn: false,
                    delBtn: false,
                    viewBtn: false,
                    card:true,
                    column: [{
                        label: '预警名称',
                        prop: 'name',
                        overHidden: true,
                    }, {
                        label: '预警描述',
                        prop: 'remarks',
                        hide:true,
                        overHidden: true,
                    },{
                        label: '预警类型',
                        prop: 'type',
                        search:true,
                        type:'tree',
                        width:120,
                        props:{
                            label:'label',
                            value:'value'
                        },
                        dicData: [
                            {
                                label:'旅馆业',
                                value:1,
                                children:[
                                    {
                                        label:'聚纵吸毒',
                                        value:11,
                                    },{
                                        label:'非法聚赌',
                                        value:12,
                                    } ,{
                                        label:'非法涉黄',
                                        value:13,
                                    }
                                ]
                            },{
                                label:'机修业',
                                value:2,
                                children:[
                                    {
                                        label:'盗抢车辆维修',
                                        value:21,
                                    },{
                                        label:'频繁维修',
                                        value:22,
                                    }
                                ]
                            }],
                        overHidden: true
                    },
                        {
                            label: '发布时间',
                            prop: 'publishDate',
                            type: "daterange",
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            search:true,
                            overHidden: true,
                        },
                        {
                            label: '处理状态',
                            prop: 'status',
                            search:true,
                            type:'select',
                            searchFilterable:true,
                            overHidden: true,
                            dicData:dict.status
                        },
                        {
                            label: '处理时间',
                            prop: 'dealDate',
                            type: "datetime",
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            overHidden: true,
                            hide:true,
                        },
                        {
                            label: '预警级别',
                            prop: 'level',
                            search:true,
                            type: 'select',
                            searchFilterable:true,
                            overHidden: true,
                            dicData:dict.level
                        },
                        {
                            label: '所属辖区',
                            prop: 'ssxq',
                            type: 'select',
                            searchFilterable:true,
                            search:true,
                            overHidden: true,
                            dicData:dict.ssxq
                        } ,
                        {
                            label: '所属派出所',
                            prop: 'sspcs',
                            overHidden: true,
                            dicData:dict.sspcs
                        }
                        ]

                },
                formOption: {
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
                    editBtn: false,
                    delBtn: false,
                    viewBtn: false,
                    emptyBtn:false,
                    submitBtn:false,
                    card:true,
                    group:[
                        {
                            label: '基本信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '预警名称',
                                    prop: 'name',
                                    disabled:true
                                },
                                {
                                label: '预警描述',
                                prop: 'remarks',
                                disabled:true
                            },{
                                label: '预警类型',
                                prop: 'type',
                                type:'tree',
                                width:120,
                                props:{
                                        label:'label',
                                        value:'value'
                                    },
                                 dicData: [
                                        {
                                            label:'旅馆业',
                                            value:1,
                                            children:[
                                                {
                                                    label:'聚纵吸毒',
                                                    value:11,
                                                },{
                                                    label:'非法聚赌',
                                                    value:12,
                                                } ,{
                                                    label:'非法涉黄',
                                                    value:13,
                                                }
                                            ]
                                        },{
                                            label:'机修业',
                                            value:2,
                                            children:[
                                                {
                                                    label:'盗抢车辆维修',
                                                    value:21,
                                                },{
                                                    label:'频繁维修',
                                                    value:22,
                                                }
                                            ]
                                        }],
                                disabled:true
                            },
                                {
                                    label: '预警级别',
                                    prop: 'level',
                                    type: 'select',
                                    dicData:dict.level,
                                    disabled:true
                                },
                                {
                                    label: '预警场所',
                                    prop: 'yjcs',
                                    disabled:true
                                },
                                {
                                    label: '发布时间',
                                    prop: 'publishDate',
                                    type: "datetime",
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                    disabled:true
                                },
                                {
                                    label: '所属辖区',
                                    prop: 'ssxq',
                                    type: 'select',
                                    dicData:dict.ssxq,
                                    disabled:true
                                } ,
                                {
                                    label: '所属派出所',
                                    prop: 'sspcs',
                                    dicData:dict.sspcs,
                                    disabled:true,
                                    labelWidth: 100
                                }
                            ]
                    },
                        {
                            label: '处理信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [{
                                label: '处理意见',
                                prop: 'method',
                                type:'textarea',
                                span:24,
                                rules: [{
                                    required: true,
                                    message: '请输处理意见',
                                    trigger: 'blur'
                                }]
                            },
                                {
                                    label: '处理人',
                                    prop: 'dealBy'
                                },
                                {
                                    label: '处理时间',
                                    prop: 'dealDate',
                                    type: "datetime",
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss'
                                }
                            ]
                        }
                    ]

                },
            }
        },
        computed: {
        },
        methods: {

            showHideView:function(status){
                if(status ==='0'){
                    return false;
                }
                return true;
            },
            showHideEdit:function(status){
                if(status ==='1'){
                    return false;
                }
                return true;
            },
            handleSav(row, done, loading) {
                this.editFlag=false
                this.$notify({
                    title: '成功',
                    message: '操作成功',
                    type: 'success',
                    duration: 2000
                })
            }
        }
    }
</script>

