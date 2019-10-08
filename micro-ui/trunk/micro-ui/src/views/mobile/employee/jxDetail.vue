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
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.xbdm)">
                            <font v-if="this.datas.xbdm == '1'">男</font>
                            <font v-if="this.datas.xbdm == '2'">女</font>
                        </van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.mzdm)">{{findLabelByValue(mzData,this.datas.mzdm)}}</van-tag>
                        <van-tag plain type="primary">机动车修理业</van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.tagType)">{{this.datas.tagType}}</van-tag>
                    </div>
                </div>
            </van-cell>
        </van-cell-group>
        <van-cell-group class="vanCellBox" title="基本信息">
            <van-cell title="身份证号">{{this.datas.zjhm}}</van-cell>
            <van-cell title="文化程度" v-if="!validatenull(this.datas.xldm)">{{findLabelByValue(whcdData,this.datas.xldm)}}</van-cell>
            <van-cell title="户籍区划" v-if="!validatenull(this.datas.hjdzssxqdm)">{{findLabelByValue(areaDatas,this.datas.hjdzssxqdm)}}</van-cell>
            <van-cell title="户籍地址">{{this.datas.hjdzqhnxxdz}}</van-cell>
            <van-cell title="居住地区划" v-if="!validatenull(this.datas.dzmcssxqdm)">{{findLabelByValue(areaDatas,this.datas.dzmcssxqdm)}}</van-cell>
            <van-cell title="居住地址">{{this.datas.dzmcqhnxxdz}}</van-cell>
        </van-cell-group>
        <van-cell-group class="vanCellBox" title="工作信息">
            <van-cell title="单位名称">{{this.datas.dwmc}}</van-cell>
            <van-cell title="管辖部门">{{this.dwDatas.djdwgajgmc}}</van-cell>
            <van-cell title="经营地址">{{this.dwDatas.dwdzqhnxxdz}}</van-cell>
            <van-cell title="法定负责人">{{this.dwDatas.fddbrxm}}</van-cell>
            <van-cell title="法定负责人身份证">{{this.dwDatas.fddbrzjhm}}</van-cell>
            <van-cell title="法定负责人联系电话">{{this.dwDatas.fddbrlxdh}}</van-cell>
            <van-cell title="工种名称">{{this.datas.gzmc}}</van-cell>
            <van-cell title="入职日期" v-if="!validatenull(this.datas.rzrq)">{{this.datas.rzrq}}</van-cell>
            <van-cell title="离职日期" v-if="!validatenull(this.datas.lzrq)">{{this.datas.lzrq}}</van-cell>
        </van-cell-group>
    </div>
</template>

<script>

    import {findLabelByValue} from "@/util/util";
    import {remote}  from  "@/api/admin/dict";
    import {mapGetters} from "vuex";
    import {validatenull} from '@/util/validate';
    import {getRyDetail,findOne} from "@/api/portal/archive/mechanical/jxyxt";
    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";

    export default {
        data() {
            return {
                systemId:'',
                datas:[],
                dwDatas:[],
                areaDatas:[],
                whcdData:[],
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
                getDict('民族','yl').then(response => {
                    this.mzData = response.data.data||[];
                }).catch((err) => {});
                getDict('whcd','jx').then(response => {
                     this.whcdData = response.data.data||[];
                 }).catch((err) => {});
                getDict('07','jzxt').then(response => {
                    this.areaDatas = response.data.data||[];
                }).catch((err) => {});

            },
            init(){
                getRyDetail(this.systemId).then(response=>{
                    this.datas = response.data.data;
                    if(!validatenull(this.datas) && !validatenull(this.datas.zagldwbm) && "undefined" != typeof (this.datas.zagldwbm)){
                        findOne(this.datas.zagldwbm).then(response=>{
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