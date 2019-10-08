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
                <van-cell title="场所名称">{{this.jbxxForm.qyzcmc}}</van-cell>
                <van-cell title="经营名称">{{this.jbxxForm.qysjjymc}}</van-cell>
                <van-cell title="经营地址">{{this.jbxxForm.jydz}}</van-cell>
                <van-cell title="门楼牌">{{this.jbxxForm.mlp}}</van-cell>
                <van-cell title="详细地址">{{this.jbxxForm.xxdz}}</van-cell>
                <van-cell title="所属区域">{{getLabelByVal(areaData,this.jbxxForm.dwszdxzqh)}}</van-cell>
                <van-cell title="行业类别">{{getLabelByVal(dictTradeTypeData,this.jbxxForm.hylb)}}</van-cell>
                <van-cell title="单位类别">{{getLabelByVal(dictUnitTypeData,this.jbxxForm.gxhylx)}}</van-cell>
                <van-cell title="企业性质">{{getLabelByVal(dictNatureData,this.jbxxForm.qyxz)}}</van-cell>
                <van-cell title="企业状态">{{getLabelByVal(dictBusinessStatusData,this.jbxxForm.jyzt)}}</van-cell>
                <van-cell title="开业日期">{{this.jbxxForm.kyrq}}</van-cell>
                <van-cell title="管辖单位">{{this.jbxxForm.deptName}}</van-cell>
                <van-cell title="责任民警">{{this.jbxxForm.zrmjxm}}</van-cell>
                <van-cell title="主营">{{validatenull(this.jbxxForm.jyxmzy)?'无':this.jbxxForm.jyxmzy}}</van-cell>
                <van-cell title="兼营">{{validatenull(this.jbxxForm.jyxmjy)?'无':this.jbxxForm.jyxmjy}}</van-cell>
                <van-cell title="被兼营单位">{{validatenull(this.jbxxForm.bjydw)?'无':this.jbxxForm.bjydw}}</van-cell>
                <van-cell title="行政区划">{{getLabelByVal(areaData,this.jbxxForm.dwszdxzqh)}}</van-cell>
                <van-cell title="联系电话">{{this.jbxxForm.lxdh}}</van-cell>
                <van-cell title="警务区电话">{{this.jbxxForm.jwqlxdh}}</van-cell>
                <!--旅业-start-->
                <van-cell v-show="containStr(this.jbxxForm.gxhylx,placeEnum.LGY.value)" title="等级">{{getLabelByVal(dictLevelData,this.jbxxForm.dj)}}</van-cell>
                <van-cell v-show="containStr(this.jbxxForm.gxhylx,placeEnum.LGY.value)" title="星级">{{getLabelByVal(dictStarData,this.jbxxForm.xj)}}</van-cell>
                <!--旅业-end-->
                <!--开锁业-start-->
                <van-cell v-show="containStr(this.jbxxForm.gxhylx,placeEnum.KSY.value)" title="开锁工艺">{{getLabelByVal(dictOpenLockData,this.jbxxForm.ksgy)}}</van-cell>
                <!--开锁业-end-->
                <!--刻字业-start-->
                <van-cell v-show="containStr(this.jbxxForm.gxhylx,placeEnum.KZZ.value)" title="刻字方式">{{getLabelByVal(dictLetteringTypeData,this.jbxxForm.kzfs)}}</van-cell>
                <!--刻字业-end-->
            </van-cell-group>

        </div>

    </div>
</template>
<script>

    import {placeTypeEnum} from "@/const/crud/portal/archive/commom/constant";
    import {containStr} from "@/const/crud/portal/archive/commom/strUtil";
    import {getDict, getLabel} from "@/api/portal/archive/dict/dict";
    import {ylcsDict} from "@/const/crud/portal/archive/ylcs/ylcsDict";
    import {findOnePlace} from "@/api/portal/archive/jingz/place";
    import {findDictArea} from '@/api/portal/archive/common/query'
    import {validatenull} from "@/util/validate";
    import '@/styles/mobile/simpleQuery/style.scss?v=110'

    export default {
        data() {
            return {
                placeEnum: placeTypeEnum,
                containStr: containStr,
                dictNatureData: [],//企业性质字典数据
                dictUnitTypeData: [],//单位类别字典数据
                dictTradeTypeData: [],//行业类别字典数据
                dictBusinessStatusData: [], //企业经营状态字典数据
                dictDeptData: [],//机构代码字典数据
                dictCityData: [],//行政区划字典数据
                dictIdCardTypeData: [],//证件种类字典数据
                dictOnlineTypeData: [],//上网方式字典数据
                dictStarData: [],//旅业星级字典数据
                dictLevelData: [],//旅业等级字典数据
                dictTechControlData: [],//技术防控字典数据
                dictOpenLockData: [],//开锁业-开锁工艺字典数
                dictLetteringTypeData: [],//刻字业-刻字方式
                dictBaSourceData: [],//保安来源
                dictSexData: [],//从业人员性别
                dictYesNoData: [{
                    label: '是',
                    value: '1'
                }, {label: '否', value: '0'}],
                areaData: [],
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
                let form={}
                form.dwbh = this.id
                findOnePlace(Object.assign({}, form)).then(response => {
                    this.jbxxForm = response.data.data
                });
            },
            //获取字典
            getDictData() {
                getDict('47', 'jzxt').then(response => {
                    this.dictNatureData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('za_002', 'jzxt').then(response => {
                    this.dictUnitTypeData = response.data.data || [];
                    this.dictTradeTypeData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('za_003', 'jzxt').then(response => {
                    this.dictBusinessStatusData = response.data.data || [];
                }).catch((err) => {
                });
                /* getDict('06','jzxt').then(response => {
                     this.dictDeptData = response.data.data||[];
                 }).catch((err) => {});*/
                getDict('wg_03', 'jzxt').then(response => {
                    this.dictIdCardTypeData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('za_013', 'jzxt').then(response => {
                    this.dictOnlineTypeData = response.data.data || [];
                }).catch((err) => {
                });

                getDict('za_004', 'jzxt').then(response => {
                    this.dictStarData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('za_007', 'jzxt').then(response => {
                    this.dictLevelData = response.data.data || [];
                }).catch((err) => {
                });

                getDict('za_008', 'jzxt').then(response => {
                    this.dictTechControlData = response.data.data || [];
                }).catch((err) => {
                });


                getDict('za_006', 'jzxt').then(response => {
                    this.dictOpenLockData = response.data.data || [];
                }).catch((err) => {
                });


                getDict('za_011', 'jzxt').then(response => {
                    this.dictLetteringTypeData = response.data.data || [];
                }).catch((err) => {
                });


                getDict('za_016', 'jzxt').then(response => {
                    this.dictBaSourceData = response.data.data || [];
                }).catch((err) => {
                });

                findDictArea().then(response => {
                    this.areaData = response.data || [];
                })
            },
            getLabelByVal(dicData, val) {
                let label = getLabel(dicData, val);
                return validatenull(label) ? "-" : label;
            },

        }
    }
</script>