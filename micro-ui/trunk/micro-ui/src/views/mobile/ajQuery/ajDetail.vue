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
                <van-cell title="案件名称">{{this.ajxxForm.ajmc}}</van-cell>
                <van-cell title="案件编号">{{this.ajxxForm.ajbh}}</van-cell>
                <van-cell title="案别">{{validatenull(getLabelByVal(abData,this.ajxxForm.ab))?'暂无数据':getLabelByVal(abData,this.ajxxForm.ab)}}</van-cell>
                <van-cell title="案件类型">{{validatenull(getLabelByVal(ajlxData,this.ajxxForm.ajlx))?'暂无数据':getLabelByVal(ajlxData,this.ajxxForm.ajlx)}}</van-cell>
                <van-cell title="案件状态">{{validatenull(getLabelByVal(ajstateData,this.ajxxForm.ajstate))?'暂无数据':getLabelByVal(ajstateData,this.ajxxForm.ajstate)}}</van-cell>
                <van-cell title="案件号">{{this.ajxxForm.ajwh}}</van-cell>
                <van-cell title="发案地点区县">{{validatenull(getLabelByVal(areaData,this.ajxxForm.faddQx))?'暂无数据':getLabelByVal(areaData,this.ajxxForm.faddQx)}}</van-cell>
                <van-cell title="发案地点详址">{{this.ajxxForm.fadd}}</van-cell>
                <van-cell title="发案时间初值">{{this.ajxxForm.fasjcz}}</van-cell>
                <van-cell title="发案时间终值">{{this.ajxxForm.fasjcz}}</van-cell>
                <van-cell title="发现形式">{{this.ajxxForm.fxxs}}</van-cell>
                <van-cell title="发现时间">{{this.ajxxForm.slfxrq}}</van-cell>
                <van-cell title="报警时间">{{this.ajxxForm.sljjsj}}</van-cell>
                <van-cell title="接警单位">{{this.ajxxForm.sljjdw}}</van-cell>
                <van-cell title="受理单位">{{this.ajxxForm.sljsdw}}</van-cell>
                <van-cell title="立案单位">{{this.ajxxForm.ladw}}</van-cell>
                <van-cell title="立案日期">{{this.ajxxForm.lasj}}</van-cell>
                <van-cell title="破案时间">{{this.ajxxForm.pasj}}</van-cell>
                <van-cell title="结案时间">{{this.ajxxForm.jaJasj}}</van-cell>
                <van-cell title="主办单位">{{this.ajxxForm.zbdw}}</van-cell>
                <van-cell title="简要案情">{{this.ajxxForm.zyaq}}</van-cell>
            </van-cell-group>
        </div>

    </div>
</template>
<script>

    import {getDict, getLabel} from "@/api/portal/archive/dict/dict";
    import {findDictArea} from '@/api/portal/archive/common/query';
    import {validatenull} from "@/util/validate";
    import '@/styles/mobile/simpleQuery/style.scss?v=110'

    export default {
        name:"ajDetail",
        data() {
            return {
                abData:[],
                ajstateData:[],
                ajlxData:[],
                areaData: [],//行政区划
                ajxxForm: {},
                id: '',
            }
        },
        created() {
            this.ajxxForm = this.$route.query.ajxx
            this.getDictData()
        },
        methods: {
            back() {
                history.go(-1);
            },
            toIndex() {
                this.$router.replace("/mobile/find");
            },

            //获取字典
            getDictData() {
                getDict('44', 'jzxt').then(response => {
                    this.ajlxData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('56', 'jzxt').then(response => {
                    this.ajstateData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('24', 'jzxt').then(response => {
                    this.abData = response.data.data || [];
                }).catch((err) => {
                });
                findDictArea().then(response => {
                    this.areaData = response.data || [];
                })
            },
            getLabelByVal(dicData, val) {
                if (!validatenull(dicData) && !validatenull(val)) {
                    return getLabel(dicData, val)
                }
            },

        }
    }
</script>