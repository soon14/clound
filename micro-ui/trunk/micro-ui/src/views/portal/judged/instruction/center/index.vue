<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-crud :option="option"
                       :data="data"
                       :page="page"
                       v-model="form"
                       ref="crud"
                       @row-save="handleSave"
                       @row-update="handleUpdate"
                       @search-change="handleFilter"
                       @refresh-change="handleFilter"
                       @search-reset="handleReset"
                       @on-load="onLoad"
                       :table-loading="tableLoading">
                <template slot="search">
                    <el-form-item label="所属辖区">
                        <avue-input v-model="searchForm.cityCode"
                                    type="tree"
                                    size="small"
                                    placeholder="所属辖区"
                                    :dic="treeAreaData"
                                    :nodeClick="areaNodeClick"
                                    :props="areaProps"></avue-input>
                    </el-form-item>
                    <el-form-item label="接收单位">
                        <div class="receiveOrgSearch_cls">
                            <popTree
                                    :treeNode="receiveOrgSearchTreeNode"
                                    :checkBox="receiveOrgSearchCheckBox"
                                    :async="receiveOrgAsync"
                                    :asyncCall="asyncCall"
                                    :inputSize="small"
                                    @treeSeach="receiveOrgSearchTreeSearch"
                                    @submit="submitSearchTreeNode"
                                    @clear="clearSearchTreeNode"
                            ></popTree>
                        </div>
                    </el-form-item>
                    <!--<el-form-item label="指令状态">
                        <avue-select v-model="searchForm.status"
                                     size="small"
                                     placeholder="指令状态"
                                     :dic="statusData"></avue-select>
                    </el-form-item>-->
                </template>
                <!--<template slot="menuLeft">
                    <el-button type="primary"
                               @click="handleAdd"
                               icon="el-icon-plus"
                               size="small">新 增
                    </el-button>
                </template>-->
                <template slot="menu" slot-scope="scope" >
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-edit"
                               v-if="isShowViewBtn(scope.row)"
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
                               @click="handleSend"
                               size="medium"
                               v-if="isShowSend">下 发
                    </el-button>
                </template>
                <template slot="remarkForm"
                          slot-scope="scope">
                    <avue-ueditor v-model="form.remark" :min-rows="8"></avue-ueditor>
                </template>
                <template slot="receiveOrgForm"
                          slot-scope="scope">
                    <div class="receiveOrg_cls">
                        <popTree v-model="form"
                                 :treeNode="receiveOrgTreeNode"
                                 :checkBox="receiveOrgCheckBox"
                                 :async="receiveOrgAsync"
                                 :asyncCall="asyncCall"
                                 :inputSize="medium"
                                 @treeSeach="receiveOrgTreeSearch"
                                 @submit="submitTreeNode"
                                 @clear="clearTreeNode"
                                 :defaultInput="form.receiveOrgLabel"
                        ></popTree>
                    </div>
                </template>

                <template slot="planLibIdForm"
                          slot-scope="scope">
                    <div class="planLibrary_cls">
                        <popTree v-model="form"
                                 :treeNode="planLibraryTreeNode"
                                 :checkBox="planLibraryCheckBox"
                                 :async="planLibraryAsync"
                                 :checkBoxType="checkBoxType"
                                 :inputSize="medium"
                                 @treeSeach="planLibraryTreeSearch"
                                 @submit="submitPlanLibraryTreeNode"
                                 @clear="clearPlanLibraryTreeNode"
                                 :defaultInput="form.planLibraryLabel"
                        ></popTree>
                    </div>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog title="查看" :visible.sync="showView"  :append-to-body="applyToBody" width="80%" :before-close="closeDialog">
            <artificial-view v-bind:param-form="paramForm" v-bind:reply-form="replyForm"></artificial-view>
            <span slot="footer"
                  class="dialog-footer">
                <el-button type="primary" @click="submitReply" v-show="isShowCommitBtn()">提交</el-button>
                <el-button @click="closeDialog">关闭</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>

    import artificialView from "../artificial/view";
    import {fetchTree,fetchTreelist} from "@/api/admin/dept";
    import {fetchArea} from '@/api/portal/archive/common/query.js';
    import {remote} from "@/api/admin/dict";
    import {findListPage,saveArtificialInstruction,delObj,saveReply,sendArtificialInstruction} from "@/api/portal/judged/instruction/instruction";
    import popTree from "@/components/ztree/poptree";
    import {instructDict} from "@/const/crud/portal/judged/instruct/instruct";
    import avueUeditor from 'avue-plugin-ueditor/packages';
    import {mapGetters} from "vuex";
    import {fetchLibrarylist} from '@/api/portal/judged/lib/PlanLibrary';

    Vue.use(avueUeditor);

    export default {
        components: {artificialView, popTree},
        data(){
            //限期内时间校验
            var checkTimeLimit = (rule, value, callback) => {
                setTimeout(() => {
                    let today = Date.now();
                    let timelimit = new Date(value).getTime();
                    if (today>timelimit) {
                        callback(new Error('限期时间不能小于当前时间'));
                    } else {
                        callback();
                    }
                }, 1000);
            };
            return {
                showView:false,
                applyToBody: true,
                form:{
                    title:undefined,
                    receiveOrg:undefined,
                    receiveOrgLabel:undefined,
                    planLibraryLabel:undefined,
                    planLibrary:undefined,
                    remark:undefined,
                    sendType:undefined,
                    timeLimit:undefined,
                    planLibId:undefined
                },
                searchForm:{
                    title:'',
                    cityCode:'',
                    receiveOrg:'',
                    receiveOrgLabel:'',
                    status:'',
                    sendType:'',
                    createBy:'',
                },
                paramForm:{

                },
                replyForm:{
                    remark:"",
                    instructId: "",
                },
                tableLoading: false,
                isShowSend:false,
                treeAreaData:[],
                treeDeptData:[],
                statusData:[],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                areaProps:{
                    label: "name",
                    value: 'code',
                },
                /********************** poptree begin **********************/
                small:"small",
                medium:"medium",
                /*-------------------- 公共部分 接收单位 ------------------*/
                receiveOrgAsync: true,//是否异步加载
                planLibraryAsync: false,
                checkBoxType:false,
                /*-------------------- 搜索栏 接收单位 ------------------*/
                receiveOrgSearchTreeNode: [],//树节点
                receiveOrgSearchCheckBox: false, //是否开启复选框
                /*-------------------- 表单 接收单位 ------------------*/
                receiveOrgTreeNode: [],//树节点
                receiveOrgCheckBox: false, //是否开启复选框
                planLibraryTreeNode: [],//树节点
                planLibraryCheckBox: false, //是否开启复选框
                /********************** poptree begin **********************/
                data: [],
                option:{
                    page:true,
                    align:'center',
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn:false,
                    delBtn:false,
                    addBtn:false,
                    dialogWidth: '80%',
                    dialogHeight: '75%',
                    column:[
                        {
                            label:'标题',
                            prop:'title',
                            span:24,
                            width: 300,
                            search:true,
                            rules:[
                                {
                                    required: true,
                                    message: "请输入标题",
                                    trigger: "blur"
                                },
                            ]
                        },
                        {
                            label:"类型",
                            prop:"type",
                            type:'select',
                            dicUrl: '/admin/dict/type/instruct_type',
                            dicMethod: 'get',
                            dicQuery: {},
                            span:12,
                            addDisplay: false,
                            editDisplay: false,
                            viewDisplay:false,
                        },
                        {
                            label:'指令内容',
                            prop:'remark',
                            span:24,
                            hide:true,
                            slot:true,
                            formslot:true,
                            rules:[
                                {
                                    required: true,
                                    message: "请输入指令内容",
                                    trigger: "blur"
                                },
                            ]
                        },
                        {
                            label: '预案',
                            prop: 'planLibId',
                            span:24,
                            formslot: true,
                            slot:true,
                            editDisabled:true,
                            hide:true,
                            rules:[
                                {
                                    required: true,
                                    message: "请输入预案",
                                    trigger: "blur"
                                },
                            ]
                        },/*
                        {
                            label:"预案",
                            prop:"planLibId",
                            hide:true,
                            span:24,
                        },*/
                        {
                            label: "发送时间",
                            prop: "sendTime",
                            type: "date",
                            disabled:true,
                            format: "yyyy-MM-dd hh:mm:ss",
                            valueFormat: "yyyy-MM-dd hh:mm:ss",
                            addDisplay: false,
                            editDisplay: false,
                            viewDisplay:false,
                        },
                        {
                            label: '接收单位',
                            prop: 'receiveOrg',
                            span:24,
                            formslot: true,
                            slot:true,
                            editDisabled:true,
                            hide:true,
                            rules:[
                                {
                                    required: true,
                                    message: "请输入接收单位",
                                    trigger: "blur"
                                },
                            ]
                        },
                        {
                            label: '接收单位',
                            prop: 'receiveOrgLabel',
                            span:24,
                            addDisplay: false,
                            editDisplay: false,
                            viewDisplay:false,
                        },
                        {
                            label:"下发方式",
                            prop:"sendType",
                            type:"select",
                            dicUrl: '/admin/dict/type/send_type',
                            dicMethod: 'get',
                            dicQuery: {},
                            span:12,
                            rules:[
                                {
                                    required: true,
                                    message: "请输入下发方式",
                                    trigger: "blur"
                                },
                            ]
                        },
                        {
                            label:"限期时间",
                            prop:"timeLimit",
                            type: "datetime",
                            format: "yyyy-MM-dd hh:mm:ss",
                            valueFormat:"yyyy-MM-dd hh:mm:ss",
                            overHidden: true,
                            rules:[
                                {
                                    required: true,
                                    message: "请输入限期时间",
                                    trigger: "blur"
                                },
                                {
                                    validator: checkTimeLimit,
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label:"指令状态",
                            prop:"status",
                            type:'select',
                            dicUrl: '/admin/dict/type/instruct_status',
                            dicMethod: 'get',
                            dicQuery: {},
                            span:12,
                            addDisplay: false,
                            editDisplay: false,
                            viewDisplay:false,
                        },

                    ],

                }
            }
        },
        created() {
            //初始化
            this.getAreaData();
            this.initDeptData();
            this.initDict();
            this.getList(this.page,{});
        },
        mounted(){

        },
        watch:{
        },
        computed: {
            ...mapGetters(["userInfo","userDept"])
        },
        methods:{
            handleView:function (row,index) {
                this.isShowSend = false;
                //this.$refs.crud.rowView(row,index);
                this.showView = true;
                this.paramForm = row;
            },
            handleAdd:function () {
                this.isShowSend = true;
                this.$refs.crud.rowAdd();
            },
            handleEdit:function (row,index) {
                this.isShowSend = true;
                this.$refs.crud.rowEdit(row, index);
            },
            handleDelete:function (row,index) {
                //this.$refs.crud.rowDel(row, index);
                //删除并刷新列表
                this.$confirm('此操作将永久删除, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    delObj(row.id).then(() => {
                        this.clearForm();
                        this.getList(this.page,{});
                        this.$notify({
                            title: '成功',
                            message: '删除指令成功',
                            type: 'success',
                            duration: 2000
                        }).catch(() => {
                        });
                    })
                })
            },
            handleSave(row, done, loading) {
                saveArtificialInstruction(this.form).then(() => {
                    //重置
                    fetchTree().then(data=>{
                        let zNodesData = data.data.data;
                        this.treeNode=zNodesData;
                    }).catch(() => {
                    });
                    this.clearForm();
                    this.getList(this.page,{});
                    done();
                    this.$notify({
                        title: '成功',
                        message: '保存指令成功',
                        type: 'success',
                        duration: 2000
                    }).catch(() => {
                        loading();
                    });
                })
            } ,

            handleUpdate(row, index, done, loading) {
                saveArtificialInstruction(this.form).then(() => {
                    //重置
                    fetchTree().then(data=>{
                        let zNodesData = data.data.data;
                        this.treeNode=zNodesData;
                    }).catch(() => {
                    });
                    this.clearForm();
                    this.getList(this.page,{});
                    done();
                    this.$notify({
                        title: '成功',
                        message: '修改指令成功',
                        type: 'success',
                        duration: 2000
                    });
                })
            } ,
            handleFilter(param) {
                this.page.currentPage = 1;
                this.page.pageSize = 10;
                this.searchForm.title = param.title;
                this.getList(this.page,{});
            },
            handleReset:function(){
                this.clearForm();
                this.getList(this.page,{});
            },
            closeDialog:function(){
                this.replyForm.instructId = "";
                this.replyForm.remark = "";
                this.showView = false;
            },
            handleSend(){
                this.$confirm('下发指令信息后将不能修改, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let t = this;
                    t.$refs.crud.$refs.dialogForm.$refs.tableForm.validate(function(e) {
                        if (e) {
                            sendArtificialInstruction(t.form).then(() => {
                                //重置
                                fetchTree().then(data=>{
                                    let zNodesData = data.data.data;
                                    t.treeNode=zNodesData;
                                }).catch(() => {});
                                t.clearForm();
                                t.getList(t.page,{});
                                t.$refs.crud.$refs.dialogForm.closeDialog(); //关闭弹窗
                                t.$refs.crud.$notify({
                                    title: '成功',
                                    message: '修改指令成功',
                                    type: 'success',
                                    duration: 2000
                                });
                            })
                        }
                    })
                }).catch(err=>{})
            },
            submitReply:function(){
                let remark = this.replyForm.remark;
                if(remark==null||remark==''||remark==undefined){
                    this.$message.warning('提交的反馈信息不能为空，请填写');
                }else{
                    this.$confirm('提交反馈信息后将不能修改, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        let params = {instructId: this.replyForm.instructId,remark:this.replyForm.remark};
                        saveReply(params).then(response=>{
                            this.closeDialog();
                            this.clearForm();
                            this.getList(this.page,{});
                            this.$notify({
                                title: '成功',
                                message: '提交成功',
                                type: 'success',
                                duration: 2000
                            }).catch(() => {
                            });
                        }).catch(err=>{})
                    })
                }
            },
            clearForm:function(){
                //字段清空
                this.$refs["crud"].searchForm.title="";
                this.searchForm.title="";
                this.searchForm.cityCode="";
                this.searchForm.receiveOrg="";
                this.searchForm.receiveOrgLabel="";
                this.searchForm.status="";
                //重置页数
                this.page.currentPage = 1;
                this.page.pageSize = 10;
            },
            onLoad:function(page){
                this.getList(page,this.searchForm);
            },
            isShowBtn:function (row) {
                if(row.status=='2'||row.status=='0'){
                    return true;
                }else{
                    return false;
                }
            },
            isShowViewBtn:function(row){
                if(row.status == "1"||row.status=='3')
                    return true;
                else
                    return false;
            },
            getAreaData(){
                fetchArea().then(response => {
                    this.treeAreaData = response.data.data
                })
            },
            initDeptData(){
                fetchTree().then(data=>{
                    let zNodesData = data.data.data;
                    this.receiveOrgSearchTreeNode = zNodesData;
                    this.receiveOrgTreeNode = zNodesData;
                }).catch(() => {
                });
            },
            areaNodeClick(ldata){
                //当所属辖区改变，管辖单位联动fetchTreelist
                let pasmes = {area:ldata.code};
                fetchTreelist(pasmes).then(data=>{
                    let zNodesData = data.data.data;
                    if(zNodesData!=null){
                        this.receiveOrgSearchTreeNode=zNodesData;
                    }else{
                        this.receiveOrgSearchTreeNode = [];
                    }
                }).catch(() => {
                });
            },
            initDict(){
                //处理状态
                remote('instruct_status').then(response => {
                    this.statusData = response.data.data||[];
                }).catch((err) => {});
            },
            getList(page, params) {
                this.tableLoading = true
                this.searchForm.receiveOrg = this.userDept.code;
                findListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, this.searchForm)).then(response => {
                    this.data = response.data.data.records
                    this.page.total = response.data.data.total
                    this.page.currentPage = response.data.data.current;
                    this.page.pageSize = response.data.data.size;
                    this.tableLoading = false
                })
            },
            isShowCommitBtn:function () {
                //反馈表单，下发成功或已反馈状态下，接收单位可见
                let status = this.paramForm.status;
                let receiveOrg = this.paramForm.receiveOrg;
                let flag = false;
                if(status == '1' || status == '3'){
                    if(receiveOrg==this.userDept.code){
                        flag = true;
                    }
                }
                return flag;
            },
            /***********************************************************/
            /********************** poptree begin **********************/
            /***********************************************************/
            /*-------------------- 公共部分 接收单位 ------------------*/
            /*异步回调函数 data 当前节点数据 call 回调函数*/
            asyncCall(data, call) {
                let params={deptId:data.id,};
                fetchTree(params).then(data=>{
                    let zNodesData = data.data.data;
                    // this.treeNode = zNodesData;
                    call(zNodesData);
                }).catch(() => {
                });
            },
            /*-------------------- 搜索栏 接收单位 --------------------*/
            submitSearchTreeNode(data){
                this.searchForm.receiveOrg = data.code;
                this.searchForm.receiveOrgLabel = data.name;
            },
            clearSearchTreeNode(){
                this.searchForm.receiveOrg = "";
                this.searchForm.receiveOrgLabel = "";
            },
            receiveOrgSearchTreeSearch(value){
                let parmes={name:value,code:this.searchForm.cityCode};
                fetchTreelist(parmes).then(data=>{
                    let zNodesData = data.data.data;
                    if(zNodesData!=null){
                        this.receiveOrgSearchTreeNode=zNodesData;
                    }else{
                        this.receiveOrgSearchTreeNode = [];
                    }
                }).catch(() => {
                });
            },
            /*-------------------- 表单 接收单位 --------------------*/
            submitTreeNode(data){
                this.form.receiveOrg = data.code;
                this.form.receiveOrgLabel = data.name;
            },
            clearTreeNode(){
                this.form.receiveOrg = "";
                this.form.receiveOrgLabel = "";
            },
            receiveOrgTreeSearch(value){
                let parmes={name:value};
                fetchTreelist(parmes).then(data=>{
                    let zNodesData = data.data.data;
                    if(zNodesData!=null){
                        this.treeNode=zNodesData;
                    }else{
                        this.treeNode = [];
                    }
                }).catch(() => {
                });
            },

            submitPlanLibraryTreeNode(data){
                this.form.planLibId = data.id;
                this.form.planLibraryLabel = data.name;
            },
            clearPlanLibraryTreeNode(){
                this.form.planLibId = "";
                this.form.planLibraryLabel = "";
            },
            planLibraryTreeSearch(value){
                let parmes={name:value};
                fetchLibrarylist(parmes).then(data=>{
                    let zNodesData = data.data.data;
                    // console.log(zNodesData);
                    if(zNodesData!=null){
                        this.planLibraryTreeNode=zNodesData;
                    }else{
                        this.planLibraryTreeNode = [];
                    }
                }).catch(() => {
                });
            },
            /***********************************************************/
            /*********************** poptree end ***********************/
            /***********************************************************/

        }
    }
</script>
<style>
    .poptree>.el-button {
        padding: 0px;
    }
    .receiveOrg_cls > .poptree{
        width:100%;
    }
    .receiveOrgSearch_cls > .poptree{
        width:200px;
    }
    .planLibrary_cls > .poptree{
        width:100%;
    }
    .planLibrarySearch_cls > .poptree{
        width:200px;
    }
</style>