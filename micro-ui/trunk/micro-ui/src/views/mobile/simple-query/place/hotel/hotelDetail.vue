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
             <van-cell title="旅馆名称">{{this.jbxxForm.hname}}</van-cell>
             <van-cell title="经营地址">{{this.jbxxForm.haddress}}</van-cell>
             <van-cell title="场所类别">{{getLabelByVal(placeTypeData,this.jbxxForm.hotelKind)}}</van-cell>
             <van-cell title="企业状态">{{getLabelByVal(placeStateData,this.jbxxForm.state)}}</van-cell>
             <van-cell title="管辖单位">{{this.jbxxForm.deptName}}</van-cell>
             <van-cell title="旅馆星级">{{getLabelByVal(placeTypeData,this.jbxxForm.stars)}}</van-cell>
             <van-cell title="旅馆等级">{{getLabelByVal(placeTypeData,this.jbxxForm.grade)}}</van-cell>
             <van-cell title="联系电话">{{this.jbxxForm.telPhone}}</van-cell>
             <van-cell title="联机时间">{{this.jbxxForm.linkDate}}</van-cell>
             <van-cell title="房间数">{{this.jbxxForm.roomNum}}</van-cell>
             <van-cell title="床位数">{{this.jbxxForm.floors}}</van-cell>
             <van-cell title="法定负责人">{{this.jbxxForm.legalPerson}}</van-cell>
             <van-cell title="经营负责人">{{this.jbxxForm.principal}}</van-cell>
             <van-cell title="治安负责人">{{this.jbxxForm.socialDuty}}</van-cell>
             <van-cell title="责任民警名称">{{this.jbxxForm.socialDuty}}</van-cell>
         </van-cell-group>
        </div>

    </div>
</template>
<script>

    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";
    import {findOneHotel} from "@/api/portal/archive/lvye/hotel";
    import {validatenull} from "@/util/validate";
    import '@/styles/mobile/simpleQuery/style.scss?v=110'

    export default {
        data() {
            return {
                areaData: [],//行政区划
                placeTypeData:[],//单位类别（行业类型）
                placeStateData:[],//场所状态
                dictHotelStarsData:[],//旅馆星级
                dictHotelGradeData:[],//旅馆等级
                jbxxForm: {},
                id: '',
            }
        },
        created() {
            this.id = this.$route.query.id
            this.getDictData()
            this.findHotelDetail()
        },
        methods: {
            back() {
                history.go(-1);
            },
            toIndex() {
                this.$router.replace("/mobile/find");
            },
            findHotelDetail() {
                let form={}
                form.dwbh = this.id
                findOneHotel(Object.assign({}, form)).then(response => {
                    this.jbxxForm = response.data.data;
                })
            },
            //获取字典
            getDictData(){
                getDict('LGLB','ly').then(response => {
                    this.placeTypeData = response.data.data||[];
                }).catch((err) => {});
                getDict('0209','ly').then(response => {
                    this.placeStateData = response.data.data||[];
                }).catch((err) => {});
                getDict('0210','ly').then(response => {
                    this.dictHotelStarsData = response.data.data||[];
                }).catch((err) => {});
                getDict('0211','ly').then(response => {
                    this.dictHotelGradeData = response.data.data||[];
                }).catch((err) => {});
            },
            getLabelByVal(dicData,val){
                if(!validatenull(dicData)&&!validatenull(val)){
                    return getLabel(dicData,val)
                }
            },

        }
    }
</script>