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
             <van-cell title="场所名称">{{this.jbxxForm.name}}</van-cell>
             <van-cell title="经营地址">{{this.jbxxForm.address}}</van-cell>
             <van-cell title="行业类型" value="刻印章业"></van-cell>
             <van-cell title="企业状态">{{getLabelByVal(placeStateData,validatenull(this.jbxxForm.enterpriseState)?'1':this.jbxxForm.enterpriseState)}}</van-cell>
             <van-cell title="单位类别">{{dict.getLabel(dict.type,this.jbxxForm.type)}}</van-cell>
             <van-cell title="门店类型">{{dict.getLabel(dict.shopType,this.jbxxForm.shopType)}}</van-cell>
             <van-cell title="所属区域">{{dict.getLabel(areaData,this.jbxxForm.areaCode)}}</van-cell>
             <van-cell title="审批单位">{{this.jbxxForm.approveCodeName}}</van-cell>
             <van-cell title="电子印章">{{dict.getLabel(dict.isStart,this.jbxxForm.eseal)}}</van-cell>
             <van-cell title="法人姓名">{{this.jbxxForm.legalName}}</van-cell>
             <van-cell title="联系电话">{{this.jbxxForm.phone}}</van-cell>
             <van-cell title="发证日期">{{this.jbxxForm.issuingDate}}</van-cell>
             <van-cell title="有效起始时间">{{this.jbxxForm.startDate}}</van-cell>
             <van-cell title="有效结束时间">{{this.jbxxForm.endDate}}</van-cell>
             <van-cell title="法人证件类型">{{getLabelByVal(this.idCardTypeDict,this.jbxxForm.idcardType)}}</van-cell>
             <van-cell title="法人证件号码">{{this.jbxxForm.idcardNumber}}</van-cell>
             <van-cell title="营业执照编号">{{this.jbxxForm.businessLicenseNumber}}</van-cell>
         </van-cell-group>
        </div>

    </div>
</template>
<script>

    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";
    import {kyzyDict} from "@/const/crud/portal/archive/kyzy/kyzy";
    import {findById} from "@/api/portal/archive/kyzy/kyzy";
    import {findDictArea} from '@/api/portal/archive/common/query'
    import {validatenull} from "@/util/validate";
    import '@/styles/mobile/simpleQuery/style.scss?v=110'

    export default {
        data() {
            return {
                dict: kyzyDict,
                areaData: [],//行政区划
                idCardTypeDict:[],//证件类型
                placeStateData:[],//企业状态
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
                findById(this.id).then(response => {
                    this.jbxxForm = response.data.data
                })
            },
            //获取字典
            getDictData(){

                getDict('enterprise_state','kyz').then(response => {
                    this.placeStateData = response.data.data||[];
                }).catch((err) => {});

                getDict('idcard_type','kyz').then(response => {
                    this.idCardTypeDict = response.data.data||[];
                }).catch((err) => {});

                findDictArea().then(response => {
                    this.areaData = response.data||[];
                })
            },

            getLabelByVal(dicData,val){
                if(!validatenull(dicData)&&!validatenull(val)){
                    return getLabel(dicData,val)
                }
            },

        }
    }
</script>