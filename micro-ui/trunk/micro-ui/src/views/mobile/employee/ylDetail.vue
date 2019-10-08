<template>
    <div>
        <van-nav-bar title="从业人员信息">
            <van-icon name="arrow-left" slot="left" size="20px" @click="back"/>
            <van-icon name="wap-home" slot="right" size="20px" @click="toIndex"/>
        </van-nav-bar>

        <van-cell-group>
            <van-cell >
                <div class="van-cell__title" style="height: 71px;">
                    <van-image src="/img/person.png" width="51px" height="71px"/>
                </div>
                <div class="van-cell__value title_content">
                    <div class="title_name">{{this.datas.rybaMc}}</div>
                    <div class="value_tag">
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.rybaXb)">
                            <font v-if="this.datas.rybaXb == '1'">男</font>
                            <font v-if="this.datas.rybaXb == '2'">女</font>
                        </van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.rybaMz)">{{this.datas.rybaMz}}</van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.ywLx)">{{findLabelByValue(placeTypeData,this.datas.ywLx)}}</van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.tagType)">{{this.datas.tagType}}</van-tag>
                    </div>
                </div>
            </van-cell>
        </van-cell-group>
        <van-cell-group class="vanCellBox" title="基本信息">
            <van-cell title="身份证号">{{this.datas.rybaZjhm}}</van-cell>
            <van-cell title="出生日期">{{this.datas.rybaCsrq}}</van-cell>
            <van-cell title="文化程度" v-if="!validatenull(this.datas.rybaWhcd)">{{findLabelByValue(whcdData,this.datas.rybaWhcd)}}</van-cell>
            <van-cell title="户籍区划" v-if="!validatenull(this.datas.rybaHjqhdm)">{{findLabelByValue(areaDatas,this.datas.rybaHjqhdm)}}</van-cell>
            <van-cell title="户籍地址">{{this.datas.rybaHjdz}}</van-cell>
            <van-cell title="居住地址">{{this.datas.rybaJzdz}}</van-cell>
            <van-cell title="联系电话">{{this.datas.rybaLxdh}}</van-cell>
        </van-cell-group>
        <van-cell-group class="vanCellBox" title="工作信息">
            <van-cell title="单位名称">{{this.datas.compName}}</van-cell>
            <van-cell title="管辖部门">{{this.dwDatas.compCodeName}}</van-cell>
            <van-cell title="经营地址">{{this.dwDatas.csbaJydz}}</van-cell>
            <van-cell title="人员类型" v-if="!validatenull(this.datas.rybaLb)">{{findLabelByValue(rylbData,this.datas.rybaLb)}}</van-cell>
            <van-cell title="岗位名称">{{this.datas.rybaGwmc}}</van-cell>
            <van-cell title="聘用期限">{{this.datas.rybaPyqx}}</van-cell>
            <van-cell title="从业状态">{{findLabelByValue(ryztData,this.datas.rybaCyzt)}}</van-cell>
            <van-cell title="登记时间" v-if="!validatenull(this.datas.rybaDjsj)">{{this.datas.rybaDjsj}}</van-cell>
            <van-cell title="离职日期" v-if="!validatenull(this.datas.rybaLzsj)">{{this.datas.rybaLzsj}}</van-cell>
        </van-cell-group>
    </div>
</template>

<script>

    import {findLabelByValue} from "@/util/util";
    import {remote}  from  "@/api/admin/dict";
    import {mapGetters} from "vuex";
    import {validatenull} from '@/util/validate';
    import {getRyDetail,getCsbaJbxx} from "@/api/portal/archive/ylcs/csba";
    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";
    import {ylcsDict}  from  "@/const/crud/portal/archive/ylcs/ylcsDict";

    export default {
        data() {
            return {
                systemId:'',
                datas:[],
                dwDatas:[],
                placeTypeData:[],//行业类型
                areaDatas:[],
                whcdData:[],
                ryztData:[],
                rylbData:[],
            }
        },
        created(){
            this.systemId = this.$route.query.systemId;
            this.dict();
            this.init();
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
            dict:function(){
                this.ryztData = ylcsDict.ryzt;
                this.rylbData = ylcsDict.rylb;
                getDict('业务类型','yl').then(response => {
                    this.placeTypeData = response.data.data||[];
                }).catch((err) => {});
                getDict('文化程度','yl').then(response => {
                    this.whcdData = response.data.data||[];
                }).catch((err) => {});
                getDict('07','jzxt').then(response => {
                    this.areaDatas = response.data.data
                })
            },
            init(){

                getRyDetail(this.systemId).then(response=>{
                    this.datas = response.data.data;
                    if(!validatenull(this.datas) && !validatenull(this.datas.compId) && "undefined" != typeof (this.datas.compId)){
                        getCsbaJbxx(this.datas.compId).then(response=>{
                            this.dwDatas = response.data.data;
                        }).catch(error=>{});
                    }
                }).catch(error=>{});
            }
        }
    }
</script>

<style lang="scss">
    .van-cell__title{
        float:left;
    }
    .title_name{
        font-size: 18px;
        font-weight: bold;
        color: #2f332a;
    }
    .value_tag{
        color:#6b6b6b;
        height: 30px;
    }
    .value_tag>.van-tag{
        margin-right: 5px;
    }
   .title_content{
        line-height: 25px;
        margin-top: 12px;
    }
</style>