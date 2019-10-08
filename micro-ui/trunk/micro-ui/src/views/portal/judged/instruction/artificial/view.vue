<template>
    <div class="execution">
        <basic-container>
            <div class="avue-detail">
                <!-- 指令信息 -->
                <div class="el-row avue-group__item" span="24">
                    <div class="avue-group__header">
                        <i class="el-icon-info avue-group__icon">
                        </i><h1 class="avue-group__title">指令信息</h1>
                    </div>
                    <el-row style="margin-top: 40px;">
                        <el-col :span="24"><font class="col-font" style="float: left;">标题：</font><pre><p>{{paramForm.title}}</p></pre></el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24">
                            <font class="col-font"  style="float:left;">内容：</font>
                            <pre v-html="paramForm.remark"></pre>
                        </el-col>
                    </el-row>
                    <el-row v-if="paramForm.planLibraryRemark!=null && paramForm.planLibraryRemark!=''">
                        <el-col :span="24"><font class="col-font" style="float:left;">预案：</font><pre v-html="paramForm.planLibraryRemark"></pre></el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12"><font class="col-font">类型：</font><span>{{findLabelByValue(this.typeDict,this.paramForm.type)}}</span></el-col>
                        <el-col :span="12"><font class="col-font">状态：</font><span>{{findLabelByValue(this.statusDict,this.paramForm.status)}}</span></el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12"><font class="col-font">限期时间：</font><span>{{paramForm.timeLimit}}</span></el-col>
                        <el-col :span="12"><font class="col-font">发送人：</font><span>{{paramForm.updateByName}}</span></el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12"><font class="col-font">发送单位：</font><span>{{paramForm.sendOrgLabel}}</span></el-col>
                        <el-col :span="12"><font class="col-font">发送时间：</font><span>{{paramForm.sendTime}}</span></el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12"><font class="col-font">接收单位：</font><span>{{paramForm.receiveOrgLabel}}</span></el-col>
                        <el-col :span="12"><font class="col-font">下发方式：</font><span>{{findLabelByValue(this.sendTypeDict,this.paramForm.sendType)}}</span></el-col>
                    </el-row>
                </div>
                <!-- 指令信息 -->
                <!-- 反馈列表 -->
                <div class="el-row avue-group__item" span="24" v-if="isShowListCard()">
                    <div class="avue-group__header">
                        <i class="el-icon-info avue-group__icon">
                        </i><h1 class="avue-group__title">反馈信息</h1>
                    </div>
                    <el-table
                            :data="replyList"
                            style="width: 100%">
                        <el-table-column
                                prop="replyTime"
                                label="日期"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="policeName"
                                label="上报人"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="remark"
                                label="内容">
                        </el-table-column>
                    </el-table>
                </div>
                <!-- 反馈列表 -->
                <!-- 反馈表单 -->
                <div class="el-row avue-group__item" span="24" v-if="isShowFormCard()">
                    <div class="avue-group__header">
                        <i class="el-icon-info avue-group__icon">
                        </i><h1 class="avue-group__title">反馈填报</h1>
                    </div>
                    <el-form ref="replyForm" :model="replyForm" label-width="80px">
                        <el-input v-model="replyForm.remark"
                                type="textarea"
                                :rows="5"
                                placeholder="请输入内容">
                        </el-input>
                        <!--<avue-ueditor v-model="replyForm.remark" :min-rows="8"></avue-ueditor>-->
                    </el-form>
                </div>
                <!-- 反馈表单 -->
            </div>
        </basic-container>
    </div>
</template>


<script>

    import {findLabelByValue} from "@/util/util";
    import {remote} from "@/api/admin/dict";
    import {findReplyListByInstructionId} from "@/api/portal/judged/instruction/instruction";
    import {mapGetters} from "vuex";

    export default {
        name: 'artificialView',
        props:["paramForm","replyForm"],
        components:{
        },
        data() {
            return {
                remarkPreWidth:0,
                sendTypeDict:[],
                typeDict:[],
                statusDict:[],
                replyData:[],
                form: {},
                replyList:[],
                replyListOption:{
                    menu:false,
                    addBtn:false,
                    refreshBtn:false,
                    columnBtn:false,
                    column: [{
                        label: '时间',
                        prop: 'replyTime',
                    },{
                        label:'填报人',
                        prop:'policeNo'
                    },{
                        label: '内容',
                        prop: 'remark',
                    }]
                },
                editorOption:{
                    placeholder: "请输入内容",
                    readonly: false
                }
            }
        },
        created(){
            this.initDict();
            console.log(this.paramForm)
            this.form = this.paramForm;
            this.replyForm.instructId = this.paramForm.id;
            if(this.form.sendType == '1'){
                if(this.form.status=='3'){
                    this.initReplyData(this.form.id);
                }
            }
        },
        computed: {
            ...mapGetters(["userInfo","userDept"])
        },
        watch: {
            "paramForm.id":{
                handler (newParam) {
                    this.form = this.paramForm;
                    this.replyForm.instructId = this.paramForm.id;
                    if(this.form.sendType == '1'){
                        if(this.form.status=='3'){
                            this.initReplyData(this.form.id);
                        }else{
                            this.replyList = [];
                        }
                    }
                },
                deep: true
            },
        },
        methods:{
            initReplyData:function(instructionId){
                //获取反馈列表
                let params = {instructionId:instructionId};
                findReplyListByInstructionId(params).then(data=>{
                    this.replyList = data.data.data;
                });
            },
            initDict:function () {
                remote("instruct_type").then(response=>{
                    this.typeDict = response.data.data;
                }).catch((err) => {});
                remote("instruct_status").then(response=>{
                    this.statusDict = response.data.data;
                }).catch((err) => {});
                remote("send_type").then(response=>{
                    this.sendTypeDict = response.data.data;
                }).catch((err) => {});
            },
            isShowListCard:function(){
                let status = this.paramForm.status;
                //反馈列表，下发成功状态，可见
                return status=='1'||status=='3';
            },
            isShowFormCard:function () {
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

        }
    }
</script>
<style lang="scss">
    .avue-group__header{
        margin-bottom: 0px;
    }
    .col-font{
        margin-right: 10px;
        min-width: 80px;
        display: inline-block;
        color: #606266;
        text-align: right;
    }
    pre{
        display: inline-block;
/*
        border:1px solid #eee;
*/
    }
    .el-row{
        margin: 20px 0px;
    }
    pre{
        display: inherit;
        margin-left: 80px;
        margin-top: -18px;
        border: 1px solid #eee;
        padding: 5px 10px;
        border-top-left-radius: 5px;
        border-top-right-radius: 5px;
        border-bottom-left-radius: 5px;
        border-bottom-right-radius: 5px;
    }
</style>