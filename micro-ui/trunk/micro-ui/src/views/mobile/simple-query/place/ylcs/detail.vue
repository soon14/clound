<template>
    <div>
        <div>
            <van-nav-bar title="场所详情">
                <van-icon name="arrow-left" slot="left" size="20px" @click="back"/>
                <van-icon name="wap-home" slot="right" size="20px" @click="toIndex"/>
            </van-nav-bar>
        </div>

        <div>
         <van-cell-group class="vanCellBox">
             <van-cell title="场所名称">{{this.jbxxForm.csbaZcmc}}</van-cell>
             <van-cell title="经营名称">{{this.jbxxForm.csbaJymc}}</van-cell>
             <van-cell title="经营地址">{{this.jbxxForm.csbaJydz}}</van-cell>
             <van-cell title="行业类型">{{dict.getLabel(ywlxData,this.jbxxForm.ywLx)}}</van-cell>
             <van-cell title="企业状态">{{dict.getLabel(dict.csbaState,this.jbxxForm.csbaState)}}</van-cell>
             <van-cell title="所属区域">{{getAreaLabel(this.jbxxForm.compCode)}}</van-cell>
             <van-cell title="管辖单位">{{this.jbxxForm.csbaZagljg}}</van-cell>
             <van-cell title="责任民警">{{this.jbxxForm.csbaZrmj}}</van-cell>
             <van-cell title="经济类型">{{this.jbxxForm.csbaJjlx}}</van-cell>
             <van-cell title="经营范围">{{this.jbxxForm.csbaYlnr}}</van-cell>
             <van-cell title="经营面积">{{this.jbxxForm.csbaJymj}}</van-cell>
             <van-cell title="包厢数量">{{this.jbxxForm.csbaBxsl}}</van-cell>
             <van-cell title="开业日期">{{this.jbxxForm.csbaKyrq}}</van-cell>
             <van-cell title="法人代表">{{this.jbxxForm.csbaFddbr}}</van-cell>
             <van-cell title="证件号码">{{this.jbxxForm.csbaFddbrzj}}</van-cell>
             <van-cell title="联系方式">{{this.jbxxForm.csbaLxdh}}</van-cell>
             <van-cell title="股东信息">{{this.jbxxForm.csbaGdxx}}</van-cell>
             <van-cell title="核定消费者数量">{{this.jbxxForm.csbaHdxfzsl}}</van-cell>
             <van-cell title="备注">{{this.jbxxForm.csbaBz}}</van-cell>
         </van-cell-group>
        </div>

    </div>
</template>
<script>

    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";
    import {ylcsDict} from "@/const/crud/portal/archive/ylcs/ylcsDict";
    import {getCsbaJbxx} from "@/api/portal/archive/ylcs/csba";
    import {findDictArea} from '@/api/portal/archive/common/query'
    import {validatenull} from "@/util/validate";
    import '@/styles/mobile/simpleQuery/style.scss?v=110'

    export default {
        data() {
            return {
                dict: ylcsDict,
                areaData: [],//行政区划
                ywlxData:[],//业务类型
                jbxxForm: {},
                id: '',
            }
        },
        created() {
            this.id = this.$route.query.id
            this.getDictData()
            this.getPlaceDetail()
        },
        methods: {
            back() {
                history.go(-1);
            },
            toIndex() {
                this.$router.replace("/mobile/find");
            },
            getPlaceDetail() {
                getCsbaJbxx(this.id).then(response => {
                    this.jbxxForm = response.data.data
                });
            },
            //获取字典
            getDictData(){
                getDict('业务类型','yl').then(response => {
                    this.ywlxData = response.data.data||[];
                }).catch((err) => {});

                findDictArea().then(response => {
                    this.areaData = response.data||[];
                })
            },
            getAreaLabel(val){
                if(!validatenull(val)){
                    let value = val.substr(0,6)
                    return getLabel(this.areaData,value)
                }
            },

        }
    }
</script>