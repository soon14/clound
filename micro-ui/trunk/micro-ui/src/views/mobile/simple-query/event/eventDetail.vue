<template>
    <div class="place">
        <div>
            <van-nav-bar title="事件详情">
                <van-icon name="arrow-left" slot="left" size="20px" @click="back"/>
                <van-icon name="wap-home" slot="right" size="20px" @click="toIndex"/>
            </van-nav-bar>
        </div>

        <div class="vanLoading">
            <van-loading type="spinner" color="#1989fa" v-if="formLoading" vertical></van-loading>
        </div>

        <div v-if="!formLoading">
            <van-cell-group class="vanCellBox" v-if="showEvent">
                <van-cell title="事件编号">{{this.jbxxForm.sjbh}}</van-cell>
                <van-cell title="事件名称">{{this.jbxxForm.sjmc}}</van-cell>
                <van-cell title="事件类型">{{getLabelByVal(sjlxData,this.jbxxForm.sjlx)}}</van-cell>
                <van-cell title="事件状态">{{getLabelByVal(sjztData,this.jbxxForm.sjzt)}}</van-cell>
                <van-cell title="事件原因">{{getLabelByVal(yyData,this.jbxxForm.yy)}}</van-cell>
                <van-cell title="简要情况">{{this.jbxxForm.jyqk}}</van-cell>
                <van-cell title="发生时间">{{this.jbxxForm.fssjcz}}</van-cell>
                <van-cell title="终结原因">{{this.jbxxForm.sjzjyy}}</van-cell>
                <van-cell title="终结时间">{{this.jbxxForm.sjzjsj}}</van-cell>
                <van-cell title="所属区域">{{getAreaLabel(this.jbxxForm.departmentcode)}}</van-cell>
               <!-- <van-cell title="所属街路巷">{{this.jbxxForm.jlx}}</van-cell>-->
                <van-cell title="详细地址">{{this.jbxxForm.qzlyd}}</van-cell>
                <van-cell title="发生地点">{{this.jbxxForm.fsdd}}</van-cell>
                <van-cell title="发生地域">{{getLabelByVal(fsdyData,this.jbxxForm.fsdy)}}</van-cell>
                <van-cell title="发生处所">{{getLabelByVal(fscsData,this.jbxxForm.fscs)}}</van-cell>
                <van-cell title="行为方式">{{getLabelByVal(xwfsData,this.jbxxForm.xwfs)}}</van-cell>
                <van-cell title="督办级别">{{this.jbxxForm.dbjb}}</van-cell>
                <!--<van-cell title="处置级别">{{getLabelByVal(czjbData,this.jbxxForm.czjb)}}</van-cell>-->
                <van-cell title="活动范围">{{getLabelByVal(hdfwData,this.jbxxForm.hdfw)}}</van-cell>
                <van-cell title="跨区情况">{{getLabelByVal(kqqkData,this.jbxxForm.kqqk)}}</van-cell>
                <van-cell title="持续时间">{{getLabelByVal(cxsjData,this.jbxxForm.cxsj)}}</van-cell>
                <van-cell title="参与人数">{{this.jbxxForm.cyrs}}</van-cell>
                <van-cell title="围观人数">{{this.jbxxForm.wgrs}}</van-cell>
                <van-cell title="死亡人数">{{this.jbxxForm.swrs}}</van-cell>
                <van-cell title="重伤人数">{{this.jbxxForm.zsrs}}</van-cell>
                <van-cell title="轻伤人数">{{this.jbxxForm.qsrs}}</van-cell>
                <van-cell title="使用警械">{{getLabelByVal(syjxData,this.jbxxForm.syjx)}}</van-cell>
                <van-cell title="逮捕人数">{{this.jbxxForm.db}}</van-cell>
                <van-cell title="警告人数">{{this.jbxxForm.jg}}</van-cell>
                <van-cell title="出动船次">{{this.jbxxForm.cdcc}}</van-cell>
                <van-cell title="出动警犬数">{{this.jbxxForm.cdjqs}}</van-cell>
                <van-cell title="出动民警数">{{this.jbxxForm.cdgamjs}}</van-cell>
                <van-cell title="出动武警数">{{this.jbxxForm.cdwjs}}</van-cell>
                <van-cell title="出警人数总数">{{this.jbxxForm.cjryzs}}</van-cell>
                <van-cell title="出动航空器次">{{this.jbxxForm.cdhkqc}}</van-cell>
                <van-cell title="出动机动车次">{{this.jbxxForm.cdjdcc}}</van-cell>
                <van-cell title="刑事拘留人数">{{this.jbxxForm.xsjl}}</van-cell>
                <van-cell title="劳动教养人数">{{this.jbxxForm.ldjy}}</van-cell>
                <van-cell title="治安拘留人数">{{this.jbxxForm.zajl}}</van-cell>
                <van-cell title="强制驱散人数">{{this.jbxxForm.qzqs}}</van-cell>
                <van-cell title="留置盘问人数">{{this.jbxxForm.lzpw}}</van-cell>
                <van-cell title="诉求">{{this.jbxxForm.sq}}</van-cell>
                <van-cell title="其它">{{this.jbxxForm.qt}}</van-cell>
                <van-cell title="备注">{{this.jbxxForm.bz}}</van-cell>
            </van-cell-group>

            <van-cell-group class="vanCellBox" v-if="!showEvent">
                <van-cell title="苗头编号">{{this.jbxxForm.sjmtbh}}</van-cell>
                <van-cell title="警情编号">{{this.jbxxForm.jqbh}}</van-cell>
                <van-cell title="苗头名称">{{this.jbxxForm.sjmtmc}}</van-cell>
                <van-cell title="苗头类型">{{getLabelByVal(mtlxData,this.jbxxForm.mtlx)}}</van-cell>
                <van-cell title="苗头诱因">{{getLabelByVal(yyData,this.jbxxForm.yy)}}</van-cell>
               <!-- <van-cell title="街 路 巷">{{this.jbxxForm.jlx}}</van-cell>-->
                <van-cell title="所属区域">{{getAreaLabel(this.jbxxForm.departmentcode)}}</van-cell>
                <van-cell title="详细地址">{{this.jbxxForm.qzlyd}}</van-cell>
                <van-cell title="发现时间">{{this.jbxxForm.fxsj}}</van-cell>
                <van-cell title="简要情况">{{this.jbxxForm.jyqk}}</van-cell>
                <van-cell title="处理情况">{{getLabelByVal(clqkData,this.jbxxForm.clqk)}}</van-cell>
                <van-cell title="工作情况">{{this.jbxxForm.gzqk}}</van-cell>
                <van-cell title="活动范围">{{getLabelByVal(hdfwData,this.jbxxForm.hdfw)}}</van-cell>
                <van-cell title="领导批示">{{this.jbxxForm.ldps}}</van-cell>
                <van-cell title="审核结果">{{getLabelByVal(shjgData,this.jbxxForm.shjg)}}</van-cell>
                <van-cell title="参与人数">{{this.jbxxForm.cyrs}}</van-cell>
                <van-cell title="参与人员类别">{{getLabelByVal(cyrylbData,this.jbxxForm.cyrylb)}}</van-cell>
                <van-cell title="可能发生地点">{{this.jbxxForm.knfsdd}}</van-cell>
                <van-cell title="可能发生时间">{{this.jbxxForm.knfssj}}</van-cell>
                <van-cell title="组织串联情况">{{getLabelByVal(zzclqkData,this.jbxxForm.zzclqk)}}</van-cell>
            </van-cell-group>
        </div>

    </div>
</template>
<script>

    import {getDict, getLabel} from "@/api/portal/archive/dict/dict";
    import {findDictArea} from '@/api/portal/archive/common/query';
    import {getEventDetail, getEventSignsDetail} from '@/api/portal/archive/event/event'
    import {validatenull} from "@/util/validate";
    import '@/styles/mobile/simpleQuery/style.scss?v=110'

    export default {
        data() {
            return {
                formLoading: false,
                showEvent: false,
                areaData: [],//行政区划
                jbxxForm: {},
                id: '',//事件、事件苗头id
                source: '',//来源（事件、事件苗头）
                yyData: [],//事件、事件苗头诱因
                hdfwData: [],//事件、事件苗头活动范围字典
                sjlxData: [],//事件类型字典
                sjztData: [],//事件状态字典
                fsdyData: [],//事件发生地域字典
                fscsData: [],//事件发生处所字典
                xwfsData: [],//事件行为方式字典
                czjbData: [],//事件处置级别字典
                kqqkData: [],//事件跨区情况字典
                cxsjData: [],//事件持续时间字典
                syjxData: [],//事件使用警械字典
                mtlxData: [],//事件苗头类型字典
                clqkData: [],//事件苗头处理情况字典
                shjgData: [],//事件苗头审核结果字典
                cyrylbData: [],//事件苗头主要参与人员类别字典
                zzclqkData: [],//时间苗头组织串联情况字典
            }
        },
        created() {
            this.source = this.$route.query.source
            this.id = this.$route.query.id
            this.getDictData()
            this.findDetail()
        },
        methods: {
            back() {
                history.go(-1);
            },
            toIndex() {
                this.$router.replace("/mobile/find");
            },
            findDetail() {
                this.jbxxForm = {}
                this.formLoading = true
                if (this.source == '0' || this.source == 0) {
                    this.showEvent = true
                    getEventDetail(this.id).then(response => {
                        this.jbxxForm = response.data.data;
                        this.formLoading = false
                    })
                } else {
                    this.showEvent = false
                    getEventSignsDetail(this.id).then(response => {
                        this.jbxxForm = response.data.data;
                        this.formLoading = false
                    })
                }

            },
            //获取字典
            getDictData() {
                findDictArea().then(response => {
                    this.areaData = response.data||[];
                })
                getDict('sj06', 'jzxt').then(response => {
                    this.yyData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('sj05', 'jzxt').then(response => {
                    this.hdfwData = response.data.data || [];
                }).catch((err) => {
                });
                if (this.source == '0' || this.source == 0) {
                    getDict('sj08', 'jzxt').then(response => {
                        this.sjztData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sj09', 'jzxt').then(response => {
                        this.sjlxData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sj10', 'jzxt').then(response => {
                        this.xwfsData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sj11', 'jzxt').then(response => {
                        this.fsdyData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sj12', 'jzxt').then(response => {
                        this.fscsData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sj14', 'jzxt').then(response => {
                        this.czjbData = response.data.data || [];
                        console.log("this.czjbData:",this.czjbData)
                    }).catch((err) => {
                    });
                    getDict('sj15', 'jzxt').then(response => {
                        this.kqqkData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sj16', 'jzxt').then(response => {
                        this.cxsjData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sj17', 'jzxt').then(response => {
                        this.syjxData = response.data.data || [];
                    }).catch((err) => {
                    });
                } else {
                    getDict('sj03', 'jzxt').then(response => {
                        this.mtlxData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sj07', 'jzxt').then(response => {
                        this.clqkData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sjmt_shjg', 'jzxt').then(response => {
                        this.shjgData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sj04', 'jzxt').then(response => {
                        this.cyrylbData = response.data.data || [];
                    }).catch((err) => {
                    });
                    getDict('sj01', 'jzxt').then(response => {
                        this.zzclqkData = response.data.data || [];
                    }).catch((err) => {
                    });
                }
            },
            getLabelByVal(dicData, val) {
                if (!validatenull(dicData) && !validatenull(val)) {
                    let list = val.split(',');
                    let value;
                    for (let i = 0; i < list.length; i++) {
                        if (validatenull(value)) {
                            value = getLabel(dicData, list[i])
                        } else {
                            value += getLabel(dicData, list[i])
                        }
                        if (i < list.length - 1) {
                            value += ','
                        }
                    }
                    return value
                }
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