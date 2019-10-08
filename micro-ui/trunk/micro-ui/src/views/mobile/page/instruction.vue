<template>
    <div>
        <van-nav-bar title="详情">
            <van-icon name="arrow-left" slot="left" size="20px" @click="back"/>
            <van-icon name="wap-home" slot="right" size="20px" @click="toIndex"/>
        </van-nav-bar>
        <van-tabs v-model="active">
            <van-tab title="指令信息">
                <van-cell title="标题">{{this.title}}</van-cell>
                <van-cell title="内容">{{this.remark}}</van-cell>
                <van-cell title="预案" v-if="!validatenull(this.planLibraryRemark)">{{this.planLibraryRemark}}</van-cell>
                <van-cell title="类型">{{findLabelByValue(typeDict,this.type)}}</van-cell>
                <van-cell title="状态">{{findLabelByValue(statusDict,this.status)}}</van-cell>
                <van-cell title="限期时间">{{this.timeLimit}}</van-cell>
                <van-cell title="发送人">{{this.createByName}}</van-cell>
                <van-cell title="发送单位">{{this.sendOrgLabel}}</van-cell>
                <van-cell title="发送时间">{{this.sendTime}}</van-cell>
                <van-cell title="接收人">{{this.receiveOrgLabel}}</van-cell>
            </van-tab>
            <van-tab title="反馈历史">
                <van-cell-group>
                    <van-card v-for="item in replyList">
                        <div slot="title">
                            <font class="list-name">{{item.policeName}}</font>
                            <font style="color: #a79595;">{{item.replyTime}}</font>
                        </div>
                        <div slot="desc" style="margin:8px 0px;">{{item.remark}}</div>
                    </van-card>
                </van-cell-group>
                <div class="empty-block" v-if="showEmpty">
                    <avue-empty desc="暂无反馈信息"></avue-empty>
                </div>
            </van-tab>
            <van-tab title="反馈填报">
                <van-cell-group>
                    <van-field v-model="form.remark" placeholder="请输入反馈信息" type="textarea" rows="20"></van-field>

                </van-cell-group>
                <van-button block size="small" type="primary" @click="onsubmit">提交</van-button>
                <div class="vanLoading">
                    <van-loading type="spinner" color="#1989fa" v-if="listLoading" vertical />
                </div>
            </van-tab>
        </van-tabs>


    </div>
</template>

<script>

    import {findLabelByValue} from "@/util/util";
    import {remote}  from  "@/api/admin/dict";
    import {mapGetters} from "vuex";
    import {validatenull} from '@/util/validate';
    import {findReplyListByInstructionId} from "@/api/portal/judged/instruction/instruction";
    import {saveReply} from "@/api/portal/judged/instruction/instruction";
    import '@/styles/mobile/simpleQuery/style.scss?v=110'

    export default {
        data() {
            return {
                active:0,
                id:undefined,
                title:undefined,
                remark:undefined,
                planLibraryRemark:undefined,
                type:undefined,
                status:undefined,
                timeLimit:undefined,
                createByName:undefined,
                sendOrgLabel:undefined,
                sendTime:undefined,
                receiveOrgLabel:undefined,
                sendType:undefined,
                typeDict:[],
                statusDict:[],
                replyList:[],
                upFinished:true,
                isUpLoading:false,
                showEmpty:false,
                form:{
                    instructId:'',
                    remark:'',
                },
                listLoading:false,
            }
        },
        created(){
            this.id = this.$route.query.id;
            this.title=this.$route.query.title;
            this.remark=this.$route.query.remark;
            this.planLibraryRemark=this.$route.query.planLibraryRemark;
            this.type=this.$route.query.type;
            this.status=this.$route.query.status;
            this.timeLimit=this.$route.query.timeLimit;
            this.createByName=this.$route.query.createByName;
            this.sendOrgLabel=this.$route.query.sendOrgLabel;
            this.sendTime=this.$route.query.sendTime;
            this.receiveOrgLabel=this.$route.query.receiveOrgLabel;
            this.sendType=this.$route.query.sendType;
            this.form.instructId = this.id;
            this.init();
            this.initReplyData();

        },
        computed: {
            ...mapGetters(["permissions"])
        },
        methods:{
            back(){
                history.go(-1);
            },
            toIndex(){
                this.$router.replace("/mobile/find");
            },
            init(){
                remote("instruct_type").then(response=>{
                    this.typeDict = response.data.data;
                }).catch((err) => {});
                remote("instruct_status").then(response=>{
                    this.statusDict = response.data.data;
                }).catch((err) => {});
            },
            initReplyData:function(){
                //获取反馈列表
                let params = {instructionId:this.id};
                findReplyListByInstructionId(params).then(data=>{
                    this.replyList = data.data.data;
                    this.showEmpty = this.isShowEmpty(this.replyList);
                });
            },
            isShowEmpty(list){
              if(!validatenull(list)&&list.length>0){
                  return false;
              }else{
                  return true;
              }
            },
            onsubmit(){
                this.$confirm('提交后不能修改，确定提交？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    if(!validatenull(this.form) && !validatenull(this.form.remark) && "undefined" != typeof (this.form.remark)){
                        saveReply(this.form).then(response=>{
                            this.form.remark = '';
                            this.initReplyData();
                            this.listLoading = false;
                            this.active = 1;
                            this.$notify({
                                title: '成功',
                                message: '提交成功',
                                type: 'success',
                                duration: 2000
                            }).catch(() => {
                            });
                        }).catch(err=>{})
                    }else{
                        this.$notify({
                            message:"反馈信息不能为空"
                        })
                    }
                })
            },
        }
    }
</script>

<style lang="scss">
    .avue-empty__image {
        padding: 20px;
    }
    .list-name{
        font-size: 14px;
        font-weight: bold;
        margin-right: 10px;
        color:#888888;
    }
</style>