<template>
    <div>
        <van-nav-bar title="从业人员信息">
            <van-icon name="arrow-left" slot="left" size="20px" @click="back"/>
            <van-icon name="wap-home" slot="right" size="20px" @click="toIndex"/>
        </van-nav-bar>

        <van-cell-group >
            <van-cell >
                <div class="van-cell__title" style="height: 71px;">
                    <van-image src="/img/person.png" width="51px" height="71px"/>
                </div>
                <div class="van-cell__value title_content">
                    <div class="title_name">{{this.datas.xm}}</div>
                    <div class="value_tag">
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.xb)">
                            <font v-if="this.datas.xb == '1'">男</font>
                            <font v-if="this.datas.xb == '2'">女</font>
                        </van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.mz)">{{findLabelByValue(mzData,this.datas.mz)}}</van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(this.dwDatas.hylb)">{{findLabelByValue(hylxDict,this.dwDatas.hylb.substr(0,2))}}</van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.tagType)">{{this.datas.tagType}}</van-tag>
                    </div>
                </div>
            </van-cell>
        </van-cell-group>
        <van-cell-group class="vanCellBox" title="基本信息">
            <van-cell title="身份证号">{{this.datas.zjhm}}</van-cell>
            <van-cell title="出生日期">{{this.datas.csrq}}</van-cell>
            <van-cell title="文化程度" v-if="!validatenull(this.datas.whcd)">{{findLabelByValue(whcdData,this.datas.whcd)}}</van-cell>
            <van-cell title="户籍区划" v-if="!validatenull(this.datas.hjdqhmc)">{{this.datas.hjdqhmc}}</van-cell>
            <van-cell title="户籍地址">{{this.datas.hjdxz}}</van-cell>
            <van-cell title="居住地区划" v-if="!validatenull(this.datas.xzdqhmc)">{{this.datas.xzdqhmc}}</van-cell>
            <van-cell title="居住地址">{{this.datas.xzdxz}}</van-cell>
            <van-cell title="联系电话" v-if="!validatenull(this.datas.jtdh)">{{this.datas.jtdh}}</van-cell>
            <van-cell title="移动电话" v-if="!validatenull(this.datas.yddh)">{{this.datas.yddh}}</van-cell>
        </van-cell-group>
        <van-cell-group class="vanCellBox" title="工作信息">
            <van-cell title="单位名称">{{this.datas.dwmc}}</van-cell>
            <van-cell title="管辖部门">{{this.dwDatas.deptName}}</van-cell>
            <van-cell title="经营地址">{{this.dwDatas.jydz}}</van-cell>
            <van-cell title="人员类型" v-if="!validatenull(this.datas.rylb)">{{findLabelByValue(rylbData,this.datas.rylb)}}</van-cell>
            <van-cell title="岗位名称">{{this.datas.gzgwmc}}</van-cell>
            <van-cell title="从业状态">{{findLabelByValue(ryztData,this.datas.ryzt)}}</van-cell>
            <van-cell title="登记时间" v-if="!validatenull(this.datas.sgrq)">{{this.datas.sgrq}}</van-cell>
            <van-cell title="离职日期" v-if="!validatenull(this.datas.lgrq)">{{this.datas.lgrq}}</van-cell>
        </van-cell-group>
    </div>
</template>

<script>

    import {findLabelByValue} from "@/util/util";
    import {remote}  from  "@/api/admin/dict";
    import {mapGetters} from "vuex";
    import {validatenull} from '@/util/validate';
    import {findDictArea} from '@/api/portal/archive/common/query';
    import {findById} from "@/api/portal/archive/jingz/employeeJz";
    import {findOnePlace} from "@/api/portal/archive/jingz/place";
    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";
    import {ylcsDict}  from  "@/const/crud/portal/archive/ylcs/ylcsDict";

    export default {
        data() {
            return {
                systemId:'',
                datas:[],
                dwDatas:[],
                hylxDict:[],//行业类型
                areaDatas:[],
                whcdData:[],
                ryztData:[],
                rylbData:[],
                mzData:[],
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
                getDict('05','jzxt').then(response=>{
                    this.mzData = response.data.data||[];
                }).catch(error=>{});
                remote("HYLX_DL").then(response=>{
                    this.hylxDict = response.data.data;
                }).catch((err) => {});
                getDict('02','jzxt').then(response => {
                    this.whcdData = response.data.data||[];
                }).catch((err) => {});
                getDict('cyry_zt','jzxt').then(response => {
                    this.ryztData = response.data.data||[];
                }).catch((err) => {});
                getDict('za_cyry_rylb','jzxt').then(response=>{
                    this.rylbData = response.data.data;
                }).catch(err=>{});
            },
            init(){
                findById(this.systemId).then(response=>{
                    this.datas = response.data.data;
                    if(!validatenull(this.datas) && !validatenull(this.datas.dwbh) && "undefined" != typeof (this.datas.dwbh)){
                        findOnePlace({dwbh:this.datas.dwbh}).then(response=>{
                            this.dwDatas = response.data.data;
                        }).catch(err=>{});
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