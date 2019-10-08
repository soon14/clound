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
                               @click="handleEdit(scope.row,scope.index)">编辑
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-delete"
                               @click="handleDelete(scope.row,scope.index)">删除
                    </el-button>
                </template>

                <template slot="fieldContentForm" slot-scope="scope" :span="24" :table-loading="tableLoading">
                    <el-editable ref="editable" :data.sync="childData" class="el-table el-table--fit el-table--border">
                        <el-editable-column type="index" label="序号"></el-editable-column>
                        <el-editable-column prop="sjType" label="事件类型描述" :edit-render="{name: 'ElInput'}"></el-editable-column>
                        <el-editable-column prop="sjLabel" label="事件类型代码" :edit-render="{name: 'ElInput'}"></el-editable-column>
                        <el-editable-column prop="grade" label="预警等级" ></el-editable-column>
                        <el-editable-column prop="gradeRemark" label="备注" :edit-render="{name: 'ElInput'}"></el-editable-column>
                    </el-editable>
                </template>
            </avue-crud>
        </basic-container>
    </div>
</template>
<style>
    .el-table__body, .el-table__footer, .el-table__header {
        table-layout: fixed;
        border-collapse: collapse;
    }
</style>
<script>

    import Vue from 'vue'
    import VueElementExtends from 'vue-element-extends'
    import 'vue-element-extends/lib/index.css'

    export default {
        components: {VueElementExtends},
        data(){
            return {
                form:{},
                childForm:{},
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
                        name:'一天内涉毒人员入住分析',
                        warningContent:'分析一天内涉毒人员入住情况',
                        type:11,
                        status:'0',
                        gradeContent:'10~20（含20）人为一般，20~30（含30）人为较重，30~40（含40）人为严重，40~50（含50）人为特别严重',
                        remark:'分析一天内涉毒人员入住情况'
                    },
                    {
                        name:'一天内涉赌人员入住分析',
                        warningContent:'分析一天内涉赌人员入住情况',
                        type:12,
                        status:'0',
                        gradeContent:'10~20（含20）人为一般，20~30（含30）人为较重，30~40（含40）人为严重，40~50（含50）人为特别严重',
                        remark:'分析一天内涉赌人员入住情况'
                    },
                    {
                        name:'一天内涉黄人员入住分析',
                        warningContent:'分析一天内涉黄人员入住情况',
                        type:11,
                        status:'0',
                        gradeContent:'10~20（含20）人为一般，20~30（含30）人为较重，30~40（含40）人为严重，40~50（含50）人为特别严重',
                        remark:'分析一天内涉黄人员入住情况'
                    }
                ],
                option:{
                    page:false,
                    align:'center',
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    editBtn:false,
                    delBtn:false,
                    addBtn:false,
                    column:[
                        {
                            label:'预警名称',
                            prop:'name',
                            search:true,
                            span:24
                        },
                        {
                            label:'预警描述',
                            prop:'warningContent',
                            type:'textarea',
                            span:24,
                            minRows:3,
                            hide:true
                        },
                        {
                            label:'预警类型',
                            prop:'type',
                            type:'tree',
                            search:true,
                            parent:false,
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
                            }]
                        },
                        {
                            label:'启用状态',
                            prop:'status',
                            type:'radio',
                            search:true,
                            width:100,
                            dicData:[
                                {
                                    label:'是',
                                    value:'0'
                                },{
                                    label:'否',
                                    value:'1'
                                }
                            ]
                        },
                        {
                            label:'分级描述',
                            prop:'gradeContent',
                            type:'textarea',
                            span:24,
                            minRows:3
                        },
                        {
                            label:'',
                            hide:true,
                            formslot: true,
                            slot: true,
                            prop:'fieldContent',
                            span:24
                        },
                        {
                            label:'备注',
                            type:'textarea',
                            prop:'remark',
                            span:24,
                            hide:true
                        }
                    ]
                },
                LEVEL:[
                    {
                        label:'一般（Ⅳ级）',
                        value:'1'
                    },
                    {
                        label:'较重（Ⅲ级）',
                        value:'2'
                    },
                    {
                        label:'严重（Ⅱ级）',
                        value:'3'
                    },
                    {
                        label: '特别严重（Ⅰ级）',
                        value: '4'
                    }
                ],
                childData:[]
            }
        },
        created(){
        },
        computed: {
        },
        methods:{
            handleEdit:function (row,index) {
                this.childData = [
                    {
                        sjType:'10~20人',
                        sjLabel:'1',
                        grade:'一般（Ⅳ级）',
                        gradeRemark:''
                    },
                    {
                        sjType:'20~30人',
                        sjLabel:'2',
                        grade:'较重（Ⅲ级）',
                        gradeRemark:''
                    },
                    {
                        sjType:'30~40人',
                        sjLabel:'3',
                        grade:'严重（Ⅱ级）',
                        gradeRemark:''
                    },
                    {
                        sjType:'40~50人',
                        sjLabel:'4',
                        grade:'特别严重（Ⅰ级）',
                        gradeRemark:''
                    }
                ];
                this.$refs.crud.rowEdit(row, index);
            },
            handleDelete:function (row,index) {
                this.$refs.crud.rowDel(row, index);
            },
            handleView:function (row,index) {
                this.childData = [
                    {
                        sjType:'10~20人',
                        sjLabel:'1',
                        grade:'一般（Ⅳ级）',
                        gradeRemark:''
                    },
                    {
                        sjType:'20~30人',
                        sjLabel:'2',
                        grade:'较重（Ⅲ级）',
                        gradeRemark:''
                    },
                    {
                        sjType:'30~40人',
                        sjLabel:'3',
                        grade:'严重（Ⅱ级）',
                        gradeRemark:''
                    },
                    {
                        sjType:'40~50人',
                        sjLabel:'4',
                        grade:'特别严重（Ⅰ级）',
                        gradeRemark:''
                    }
                ];
                this.$refs.crud.rowView(row,index);
            },
            handleAdd:function () {
                this.childData = [
                    {
                        sjType:'',
                        sjLabel:'',
                        grade:'一般（Ⅳ级）',
                        gradeRemark:''
                    },
                    {
                        sjType:'',
                        sjLabel:'',
                        grade:'较重（Ⅲ级）',
                        gradeRemark:''
                    },
                    {
                        sjType:'',
                        sjLabel:'',
                        grade:'严重（Ⅱ级）',
                        gradeRemark:''
                    },
                    {
                        sjType:'',
                        sjLabel:'',
                        grade:'特别严重（Ⅰ级）',
                        gradeRemark:''
                    }
                ];
                this.$refs.crud.rowAdd();
            },
            editEvent:function(row,index){
                console.log(row);
                this.$refs.editable.setActiveRow(row);
            }
        }
    }
</script>