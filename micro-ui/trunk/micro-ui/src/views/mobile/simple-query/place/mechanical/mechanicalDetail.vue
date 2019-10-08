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
                <van-cell title="单位名称">{{this.jbxxForm.dwmc}}</van-cell>
                <van-cell title="经营名称">{{this.jbxxForm.jymc}}</van-cell>
                <van-cell title="经营地址">{{this.jbxxForm.dwdzqhnxxdz}}</van-cell>
                <van-cell title="管辖单位">{{this.jbxxForm.djdwgajgmc}}</van-cell>
                <van-cell title="场所类别">{{getLabelByVal(placeTypeData,this.jbxxForm.dwlbdm)}}</van-cell>
                <van-cell title="企业状态">{{getLabelByVal(placeStateData,this.jbxxForm.qyztdm)}}</van-cell>
                <van-cell title="单位性质">{{getLabelByVal(placeNatureData,this.jbxxForm.dwxz)}}</van-cell>
                <van-cell title="重点单位">{{dict.getLabel(dict.zddwbs,this.jbxxForm.zddwbs)}}</van-cell>
                <van-cell title="主营项目">{{this.jbxxForm.jyfwzy}}</van-cell>
                <van-cell title="经营面积">{{this.jbxxForm.jycdmjpfm}}</van-cell>
                <van-cell title="企业等级">{{dict.getLabel(dict.dwdj,this.jbxxForm.dwdj)}}</van-cell>
                <van-cell title="注册日期">{{this.jbxxForm.zcrq}}</van-cell>
                <van-cell title="注册资本">{{this.jbxxForm.zczb}}</van-cell>
                <van-cell title="开业日期">{{this.jbxxForm.kyrq}}</van-cell>
                <van-cell title="登记人_姓名">{{this.jbxxForm.djrxm}}</van-cell>
                <van-cell title="联系电话">{{this.jbxxForm.lxdh}}</van-cell>
                <van-cell title="法定代表人姓名">{{this.jbxxForm.fddbrxm}}</van-cell>
                <van-cell title="保卫负责人姓名">{{this.jbxxForm.bwfzrxm}}</van-cell>
                <van-cell title="营业执照号">{{this.jbxxForm.yyzzh}}</van-cell>
                <van-cell title="营业执照发证机关">{{this.jbxxForm.fzjgyyzhfzjgmc}}</van-cell>
                <van-cell title="行业许可证号">{{this.jbxxForm.hyxkzh}}</van-cell>
                <van-cell title="行业许可发证机关">{{this.jbxxForm.fzjghyxkzfzjgmc}}</van-cell>
            </van-cell-group>
        </div>

    </div>
</template>
<script>

    import {cyryDict} from "@/const/crud/portal/archive/mechanical/jxyxt";
    import {getDict, getLabel} from "@/api/portal/archive/dict/dict";
    import {findOne}from "@/api/portal/archive/mechanical/jxyxt";
    import {validatenull} from "@/util/validate";
    import '@/styles/mobile/simpleQuery/style.scss?v=110'

    export default {
        data() {
            return {
                dict: cyryDict,
                areaData: [],//行政区划
                placeTypeData: [],//单位类别（行业类型）
                placeStateData: [],//场所状态
                placeNatureData: [],//单位性质（经济类型）
                jbxxForm: {},
                id: '',
            }
        },
        created() {
            this.id = this.$route.query.id
            this.getDictData()
            this.getDwxx()
        },
        methods: {
            back() {
                history.go(-1);
            },
            toIndex() {
                this.$router.replace("/mobile/find");
            },
            getDwxx() {
                findOne(this.id).then(response => {
                    this.jbxxForm = response.data.data
                })
            },
            //获取字典
            getDictData() {
                getDict('jjlx', 'jx').then(response => {
                    this.placeNatureData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('qyzt', 'jx').then(response => {
                    this.placeStateData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('qylb', 'jx').then(response => {
                    this.placeTypeData = response.data.data || [];
                }).catch((err) => {
                });
            },
            getLabelByVal(dicData, val) {
                if (!validatenull(dicData) && !validatenull(val)) {
                    return getLabel(dicData, val)
                }
            },

        }
    }
</script>