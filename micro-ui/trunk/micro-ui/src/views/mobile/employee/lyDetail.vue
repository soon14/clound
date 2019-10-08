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
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.ssdwlb)">{{findLabelByValue(placeTypeData,this.datas.ssdwlb)}}</van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(this.datas.tagType)">{{this.datas.tagType}}</van-tag>
                    </div>
                </div>
            </van-cell>
        </van-cell-group>
        <van-cell-group class="vanCellBox" title="基本信息">
            <van-cell title="身份证号">{{this.datas.zjhm}}</van-cell>
            <van-cell title="出生日期">{{this.datas.csrq}}</van-cell>
            <van-cell title="文化程度" v-if="!validatenull(this.datas.whcd)">{{findLabelByValue(whcdData,this.datas.whcd)}}</van-cell>
            <van-cell title="户籍区划" v-if="!validatenull(this.datas.hjdqh)">{{findLabelByValue(areaDatas,this.datas.hjdqh)}}</van-cell>
            <van-cell title="户籍地址">{{this.datas.hjdxz}}</van-cell>
            <van-cell title="居住地区划" v-if="!validatenull(this.datas.xzzqh)">{{findLabelByValue(areaDatas,this.datas.xzzqh)}}</van-cell>
            <van-cell title="居住地址">{{this.datas.xzzxz}}</van-cell>
            <van-cell title="联系电话" v-if="!validatenull(this.datas.lxfs1)">{{this.datas.lxfs1}}</van-cell>
        </van-cell-group>
        <van-cell-group class="vanCellBox" title="工作信息">
            <van-cell title="单位名称">{{this.dwDatas.hname}}</van-cell>
            <van-cell title="星级">{{findLabelByValue(xjData,this.dwDatas.stars)}}</van-cell>
            <van-cell title="管辖部门">{{this.dwDatas.deptName}}</van-cell>
            <van-cell title="经营地址">{{this.dwDatas.haddress}}</van-cell>
            <van-cell title="人员类别">{{findLabelByValue(rylbData,this.datas.rylb)}}</van-cell>
            <van-cell title="职务" v-if="!validatenull(this.datas.zw)">{{findLabelByValue(rylbData,this.datas.zw)}}</van-cell>
            <van-cell title="岗位名称">{{findLabelByValue(gzgwData,this.datas.gzgw)}}</van-cell>
            <van-cell title="从业状态">{{findLabelByValue(ryztData,this.datas.ryzt)}}</van-cell>
            <van-cell title="上岗日期" v-if="!validatenull(this.datas.sgrq)">{{this.datas.sgrq}}</van-cell>
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
    import {getOne} from "@/api/portal/archive/lvye/employee";
    import {findOneHotel} from "@/api/portal/archive/lvye/hotel";
    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";

    export default {
        data() {
            return {
                systemId:'',
                code:'',
                datas:[],
                dwDatas:[],
                placeTypeData:[],//行业类型
                areaDatas:[],
                whcdData:[],
                ryztData:[],
                rylbData:[],
                mzData:[],
                xjData:[],
                gzgwData:[],
            }
        },
        created(){
            this.systemId = this.$route.query.systemId;
            this.code = this.$route.query.code;
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
                getDict('0201','ly').then(response => {
                    this.mzData = response.data.data||[];
                }).catch((err) => {});
                getDict('LGLB','ly').then(response => {
                    this.placeTypeData = response.data.data||[];
                }).catch((err) => {});
                getDict('07','jzxt').then(response => {
                    this.areaDatas = response.data.data||[];
                }).catch((err) => {});
                getDict('0210','ly').then(response => {
                    this.xjData = response.data.data||[];
                }).catch((err) => {});
                getDict('RYLB','ly').then(response => {
                     this.rylbData = response.data.data||[];
                 })
                getDict('GZGW','ly').then(response=>{
                    this.gzgwData = response.data.data||[];
                })
                getDict('RYZT','ly').then(response=>{
                    this.ryztData = response.data.data||[];
                })
            },
            init(){
                getOne(this.systemId,this.code).then(response=>{
                    this.datas = response.data.data;
                    if(!validatenull(this.datas) && !validatenull(this.datas.dwbh) && "undefined" != typeof (this.datas.dwbh)){
                        findOneHotel({dwbh:this.datas.dwbh}).then(response=>{
                            this.dwDatas = response.data.data;
                        }).catch(error=>{});
                    }
                }).catch(error=>{});
            },
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