<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-crud :option="option"
                       :data="data"
                       v-model="form"
                       ref="crud"
                       :page="page"
                       :table-loading="tableLoading">
                <template slot="menuLeft">
                    <el-button type="primary"
                               @click="handleAdd"
                               icon="el-icon-plus"
                               size="small">新 增
                    </el-button>
                </template>
                <template slot="menu" slot-scope="scope" >
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-edit"
                               @click="handleView(scope.row,scope.index)">查看
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-edit"
                               v-if="isShow(scope.row)"
                               @click="handleEdit(scope.row,scope.index)">编辑
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-delete"
                               v-if="isShow(scope.row)"
                               @click="handleDelete(scope.row,scope.index)">删除
                    </el-button>
                </template>
                <template slot="menuForm" slot-scope="scope">
                    <el-button type="danger"
                               @click="handleAdd"
                               size="medium"
                                v-if="isShowSend">下 发
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>
    </div>
</template>
<script>

    export default {
        data(){
            return {
                form:{},
                tableLoading: false,
                isShowSend:false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                data: [
                    {
                        type:'1',
                        title:'江门某某酒店一天内涉黄人员入住超过10人',
                        content:'江门某某酒店一天内涉黄人员入住超过10人',
                        recUnit:'440703500000',
                        sendDate:'2014-02-23 00:00:00',
                        status:'4',
                        reDate:'2014-02-23 00:00:00'
                    }, {
                        type:'1',
                        title:'江门某某酒店一天内涉毒人员入住超过10人',
                        content:'江门某某酒店一天内涉黄人员入住超过10人',
                        recUnit:'440703500000',
                        sendDate:'2014-02-23 00:00:00',
                        status:'3',
                        reDate:'2014-02-23 00:00:00'
                    }, {
                        type:'1',
                        title:'江门某某酒店一天内涉赌人员入住超过10人',
                        content:'江门某某酒店一天内涉黄人员入住超过10人',
                        recUnit:'440703500000',
                        sendDate:'2014-02-23 00:00:00',
                        status:'2',
                        reDate:'2014-02-23 00:00:00'
                    }, {
                        type:'2',
                        title:'故意伤害案件（行政）48小时内未出具鉴定聘请书',
                        content:'某某王故意伤害案件（行政）48小时内未出具鉴定聘请书',
                        recUnit:'440703500000',
                        sendDate:'2014-02-23 00:00:00',
                        status:'1',
                        reDate:'2014-02-23 00:00:00'
                    },
                    {
                        type:'2',
                        title:'违反治安管理行为在6个月内没有被发现，其他违法行为在2年内没有被发现的，不再给与行政处罚',
                        content:'某某张违反治安管理行为在6个月内没有被发现，其他违法行为在2年内没有被发现的，不再给与行政处罚',
                        recUnit:'440703500000',
                        sendDate:'2014-02-23 00:00:00',
                        status:'2',
                        reDate:'2014-02-23 00:00:00'
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
                            label:'指令类型',
                            prop:'type',
                            disabled:true,
                            type:'select',
                            valueDefault:'2',
                            span:24,
                            width:90,
                            search:true,
                            dicData:[
                                {
                                    label:'自动指令',
                                    value:'1'
                                },
                                {
                                    label:'人工指令',
                                    value:'2'
                                }
                            ]
                        },
                        {
                            label:'标题',
                            prop:'title',
                            span:24,
                            width: 300
                        },
                        {
                            label:'描述',
                            prop:'content',
                            type:'textarea',
                            span:24,
                            minRows:8,
                            hide:true
                        },
                        {
                            label:'接收单位',
                            prop:'recUnit',
                            type:'select',
                            search:true,
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
                            }]
                        },
                        {
                            label: "发送时间",
                            prop: "sendDate",
                            type: "date",
                            disabled:true,
                            format: "yyyy-MM-dd hh:mm:ss",
                            valueFormat: "yyyy-MM-dd hh:mm:ss",
                        },
                        {
                            label:'处理状态',
                            prop:'status',
                            type:'select',
                            search:true,
                            width:80,
                            dicData:[
                                {
                                    label:'草稿',
                                    value:'1'
                                },
                                {
                                    label:'已发送',
                                    value:'2'
                                },
                                {
                                    label:'已处理',
                                    value:'3'
                                },
                                {
                                    label:'已超时',
                                    value:'4'
                                }
                            ]
                        },
                        {
                            label: "反馈时间",
                            prop: "reDate",
                            type: "date",
                            disabled:true,
                            format: "yyyy-MM-dd hh:mm:ss",
                            valueFormat: "yyyy-MM-dd hh:mm:ss",
                        }
                    ]
                }
            }
        },
        created(){
        },
        computed: {
        },
        methods:{

            handleEdit:function (row,index) {
                this.isShowSend = true;
                this.$refs.crud.rowEdit(row, index);
            },
            handleDelete:function (row,index) {
                this.$refs.crud.rowDel(row, index);
            },
            isShow:function (row) {
                if(row.type=='1'||row.status!='1'){
                    return false;
                }else{
                    return true;
                }
            },
            handleView:function (row,index) {
                this.isShowSend = false;
                this.$refs.crud.rowView(row,index);
            },
            handleAdd:function () {
                this.isShowSend = true;
                this.$refs.crud.rowAdd();
            }
        }
    }
</script>