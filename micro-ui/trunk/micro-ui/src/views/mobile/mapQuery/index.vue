<template>
    <div class="mapQurey">
        <van-nav-bar title="地图查询">
            <van-icon name="arrow-left" slot="left" size="20px" @click="back"/>
            <van-icon name="wap-home" slot="right" size="20px" @click="toIndex"/>
        </van-nav-bar>
        <router-link to="/mobile/searchList">
            <van-search placeholder="请输入搜索关键词" shape="round"/>
        </router-link>
        <div class="mapQurey_main">
            <div id="famp" ></div>
        </div>
        <van-popup v-model="show"
                   round position="bottom"
                   :style="{height:'22%'}"
                   :close-on-click-overlay="false"
                   :overlay="false">
            <div class="listItem">
                <p>
                    <van-icon name="close" @click="closePopup" size="20px" color="#1989fa" class="closePopup"/>
                </p>
                <div  class="listItem">
                    <van-icon name="location" color="#1989fa" size="20px"/>{{addressData.standardAddressName}}</div>
                <van-cell>{{addressData.regionName}}</van-cell>
            </div>
        </van-popup>
    </div>
</template>

<script>
    import vuebubble from "@/components/map/vue-bubble.vue"
    let ocrParametersList= [
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4591&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "PGIS矢量地图"
        },
    ];
    import {fetchArea} from '@/api/portal/archive/common/query'
    import {matchForward} from '@/api/map/map'
    const DIC = {
        sycslb:[
            {"label":"旅馆业","value":"01"},
            {"label":"刻印章业","value":"02"},
            {"label":"旧货业","value":"03"},
            {"label":"印刷业","value":"04"},
            {"label":"机动车修理业","value":"05"},
            {"label":"开锁业","value":"06"},
            {"label":"其他管理行业","value":"07"},
            {"label":"歌舞娱乐场所","value":"08"},
            {"label":"按摩服务业","value":"09"},
            {"label":"游戏游艺场所","value":"10"},
            {"label":"录像放映场所","value":"11"},
            {"label":"上网服务场所","value":"12"},
            {"label":"体育场馆","value":"13"},
            {"label":"广场公园","value":"14"},
            {"label":"其他场所","value":"15"},
            {"label":"报废机动车拆解业","value":"16"},
            {"label":"典当业","value":"17"},
            {"label":"废旧金属业","value":"18"}
        ],
        jfdj:[
            {"label":"A级","value":"01"},
            {"label":"B级","value":"02"},
            {"label":"C级","value":"03"},
            {"label":"D级","value":"04"},
        ],
        ssds:[
            {"label":"广州","value":"01"},
            {"label":"江门","value":"02"},
        ],
        ssqx:[
            {"label":"天河区","value":"01"},
            {"label":"越秀区","value":"02"},
            {"label":"番禺区","value":"03"},
            {"label":"白云区","value":"04"},
        ],
    };
    export default {
        name: "index",
        components:{
            vueBubble:vuebubble
        },
        data() {
            return {
                show:false,
                options: {
                    sycslb:DIC.sycslb,
                    jfde:DIC.jfdj,
                    ssds:DIC.ssds,
                    ssqx:DIC.ssqx,
                },

                industryV: [],
                levelV: [],
                cityV: "",
                countyV:"",

                fmap:null,
                center:{
                    lon:113.27817764,
                    lat:23.13744564,
                    zoom: 12,
                    minZoom: 2,
                    maxZoom: 21
                },
                intiData: [],
                addressData:{},
                id:"",
            }
        },
        mounted(){
            this.getCity();
            this.MapInit();
            this.id = this.$route.query.id;
            if(!!this.id)this.searchBtn()
        },
        methods:{
            back() {
                this.$router.go(-1);
            },
            toIndex() {
                this.$router.replace("/mobile/find");
            },
            getCity(){
                fetchArea().then(data=>{
                    this.options.ssds=data.data.data[0].children;
                })
            },
            getCounty(){
                this.options.ssds.forEach(item=>{
                    if(item.code==this.cityV){
                        this.options.ssqx = item.children;
                    }
                })
            },
            closePopup(){
                this.show=false;
            },
            //按钮
            searchBtn(){
                let parame={
                    "province":"广东省",
                    "xz":this.id
                }
                matchForward(parame).then(data=>{
                    this.$notify({
                        type:'primary',
                        message:data.data.msg
                    })
                    this.intiData=data.data.data;
                    this.fmap.clearLayer("testLayer");
                    this.addRadomData(this.intiData,"testLayer");
                })
            },
            //地图
            MapInit(){
                // 注册要素图元缺省配置
                FUtil.registSymbolDefaultSetting({
                    sourcePath:"/cdn/fmap/img",
                    size: [32,46]
                });
                // 注册要素图元
                FUtil.registSymbols(['shuidi','video']);
                var bglayer=FUtil.FPM({
                    url:ocrParametersList[0].applyMapUrl,
                    origin:[107.5,  28],
                    resolutions:[2.0000081722216954,1.0000040861108477,
                        0.50000204305542384,0.25000102152771203,
                        0.12500051076385602,0.062500255381928008,
                        0.031250127690964004,0.015625063845482002,
                        0.007812531922741001,0.0039062659613704467,
                        0.0019531329806852234,0.00097656649034265201,
                        0.00048828324517127884,0.00024414162258569,
                        0.000122070811292845,6.10354056464225e-005,
                        3.0517702823161505e-005,1.5258851411580759e-005,
                        7.6294257058400141e-006,3.8147128528703159e-006,
                        1.9073515436177414e-006

                    ],
                    crossOrigin:true, // 如果跨域情况下，添加此配置
                });
                var layers = {
                    'testLayer': {
                        name: 'testLayer',
                        symbol: 'shuidi',
                        bubbleable: false,
                        style:(feature)=>{
                            this.intiData.forEach((items,index)=>{
                                items.idx= index+1;
                            });
                            let clients = feature.get('clients');
                            let value=clients.idx;
                            let style = 'symbol:shuidi' +
                                '' +
                                '' +
                                ';text:' + value + ';text-fill:white;text-baseline:bottom;text-offectY:-15;text-font: 20px Verdana;'
                            return style;
                        }
                    },
                };
                // 创建地图实例
                this.fmap = new FMap({
                    element:'famp',
                    bglayer:bglayer,
                    center:this.center,
                    layers:layers,
                    sign:true
                });
                this.addRadomData(this.intiData,"testLayer");
                this.selectedClick();
            },
            addRadomData(data,layer){
                this.fmap.addFeatures(data, {layer: layer});
            },
            selectedClick(){
                // 点击要素时查询要素详情信息
                let that=this;
                this.fmap.on('fmap.feature.selected',(event)=>{
                    that.show=true;
                    let clients = event.feature.values_.clients;
                    that.addressData=clients;
                });
            },
        },
    }
</script>

<style lang="scss">
    .mapQurey{
        height: 100%;
        overflow: hidden;
        *{
            box-sizing: border-box;
        }
        .mapQurey_main{
            height: 100%;
        }
        .bubble-box{
            max-width: 300px;
            padding-right: 10px;
            .el-form-item {
                margin-bottom:0px !important;
            }
            .el-form-item__label,.el-form-item__content {
                line-height: 25px;
                font-size: 12px;
            }
            .video_play{
                width:100%;
                display: flex;
                margin-bottom: 10px;
                .video_playbtn,.generalBB_btn{
                    margin: auto;
                }
            };
        }
        #fmap{
            overflow: hidden;
            float:left;
        }
        .van-field__body{
          margin-top:3px;
      }
        .listItem{padding:6px}
        p{
            text-align: right;
            padding: 0;
            margin: 0;
            .closePopup{
                padding: 5px;
            }
        }

    }

</style>