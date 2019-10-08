<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-crud :option="option"
                       :data="data"
                       v-model="form"
                       ref="crud"
                       :page="page"
                       :table-loading="tableLoading">
                <template slot="search">
                    <el-form-item label="所属辖区">
                        <avue-input v-model="form.cityCode"
                                    type="tree"
                                    size="small"
                                    placeholder="所属辖区"
                                    :dic="treeAreaData"
                                    :nodeClick="areaNodeClick"
                                    :props="areaProps"></avue-input>
                    </el-form-item>
                    <el-form-item label="接收单位">
                        <popTree
                                v-model="inputValue"
                                :isShow="isShow"
                                :treeSeach="treeSeach"
                                :treeNode="treeNode"
                                :checkBox="checkBox"
                                :clickNode="clickNode"
                                :async="async"
                                :asyncCall="asyncCall"
                                :popVisible="popVisible"
                        >
                            <avue-input v-model="form.receiveOrg"
                                        slot="pop-btn"
                                        type="input"
                                        size="small"
                                        placeholder="接收单位"></avue-input>
                        </popTree>
                    </el-form-item>
                </template>
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
                               v-if="isShowBtn(scope.row)"
                               @click="handleEdit(scope.row,scope.index)">编辑
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-delete"
                               v-if="isShowBtn(scope.row)"
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
                <template slot="receiveOrgForm"
                          slot-scope="scope">
                    <avue-input v-model="form.receiveOrg"
                                type="tree"
                                placeholder="请选择上级部门"
                                :dic="treeData"
                                :props="defaultProps"></avue-input>
                </template>
            </avue-crud>
        </basic-container>
    </div>
</template>
<script>

    import {fetchTree,fetchTreelist} from "@/api/admin/dept";
    import {fetchArea} from '@/api/portal/archive/common/query.js';
    import popTree from "@/components/ztree/poptree";

    export default {
        components: {popTree},
        data(){
            return {
                form:{
                    cityCode:'',
                    receiveOrg:''
                },

                tableLoading: false,
                isShowSend:false,
                treeAreaData:[],
                treeDeptData:[],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                areaProps:{
                    label: "name",
                    value: 'code',
                },
                defaultProps: {
                    label: "name",
                    value: 'id',
                },
                loadImg:false,
                isShow:true,//是否显示搜索框
                text:"", //勾选的文本值
                inputValue:"",//搜索输入的值
                popVisible:false,
                treeNode: [],
                checkBox: false, //是否开启复选框
                hiddenLine: false,//是否连线
                async: true,//是否异步加载
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
                            label:'标题',
                            prop:'title',
                            span:24,
                            width: 300,
                            search:true
                        },
                        {
                            label:"类型",
                            prop:"type",
                            type:'select',
                            dicUrl: '/admin/dict/type/instruct_type',
                            dicMethod: 'get',
                            dicQuery: {},
                            span:24
                        },
                        {
                            label:'内容',
                            prop:'content',
                            type:'textarea',
                            span:24,
                            minRows:8,
                            hide:true
                        },
                        {
                            label: '接收单位',
                            prop: 'receiveOrg',
                            span:24,
                            formslot: true,
                            editDisabled:true
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
                    ],

                }
            }
        },
        created() {
            //初始化
            this.getAreaData();
            fetchTree().then(data=>{
                let zNodesData = data.data.data;
                this.treeNode=zNodesData;
            }).catch(() => {
            });
        },
        watch:{
            inputValue(newVal){//immediate:true,代表在wacth里声明了firstName这个方法之后立即先去执行handler方法
                console.log(newVal)
            },
            treeNode(newVal){
                console.log(newVal)
            }
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
            isShowBtn:function (row) {
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
            },
            getAreaData(){
                fetchArea().then(response => {

                    this.treeAreaData = response.data.data
                })
            },
            treeSeach(){
                let parmes={inputValue:this.inputValue};
                fetchTree(parmes).then(data=>{
                    let zNodesData = data.data.data;
                    this.treeNode=zNodesData;
                }).catch(() => {
                });
            },
            /*点击节点信息 上个点击节点信息*/
            clickNode(data, oldData) {
                alert(data);
                alert(oldData);
                /*let checkedNames = [], checkedIds = [];
                for(let i=0;i<checkBox.length;i++){
                    checkedIds.push(checkBox[i].id);
                    checkedNames.push(checkBox[i].name);
                }
                console.log(checkedIds);
                console.log(checkedNames);
                this.text=checkedNames.join(",");*/
            },
            /*异步回调函数 data 当前节点数据 call 回调函数*/
            asyncCall(data, call) {
                let pasmes={deptId:data.id,name:data.name};
                fetchTree(pasmes).then(data=>{
                    let zNodesData = data.data.data;
                    // this.treeNode = zNodesData;
                    call(zNodesData);
                }).catch(() => {
                });
            },
            areaNodeClick(ldata){
                //当所属辖区改变，管辖单位联动fetchTreelist
                let pasmes = {area:ldata.code};
                fetchTreelist(pasmes).then(data=>{
                    let zNodesData = data.data.data;
                    this.treeNode = zNodesData;
                }).catch(() => {
                });
            }
        }
    }
</script>
<style>
    .poptree>.el-button {
        padding: 0px;
    }
</style>