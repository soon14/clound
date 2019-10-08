<template>
    <basic-container class="map-qurey" v-loading="pageloading">
        <vue-bubble id="videoBB" >
            <div class="bubble-box">
                <el-form label-width="60px" label-position="right" ref="form" :model="videoData" >
                    <el-form-item label="编号:">{{videoData.standardAddressCode}}</el-form-item>
                    <el-form-item label="名称:">{{videoData.standardAddressName}}</el-form-item>
                    <el-form-item label="地址:">{{videoData.regionName}}</el-form-item>
                    <el-form-item label="IP:">{{videoData.politicalName}}</el-form-item>
                </el-form>
                <div class="video_play" @click="dialogVideoVisible=true">
                    <el-button type="primary" plain class="video_playbtn" size="small">视频播放</el-button>
                </div>
            </div>
        </vue-bubble>
        <vue-bubble id="generalBB" >
            <div class="bubble-box">
                <el-form label-width="60px" label-position="right" ref="form" :model="shuidiData">
                    <el-form-item label="编号:">{{shuidiData.standardAddressCode}}</el-form-item>
                    <el-form-item label="地址:">{{shuidiData.standardAddressName}}</el-form-item>
                    <el-form-item label="辖区:">{{shuidiData.regionName}}</el-form-item>
                    <el-form-item label="区县">{{shuidiData.politicalName}}</el-form-item>
                </el-form>
                <div class="video_play generalBB_box">
                    <el-button size="small"
                               class="generalBB_btn"
                               type="primary"
                               icon="el-icon-view"
                               @click="generalDialog=true">查看详情
                    </el-button>
                </div>

            </div>
        </vue-bubble>

        <div class="map-qurey-body">
           <div class="map-qurey-top">
                <div class="query-content">
                    <div class="query-content-condition query-content-choose">
                        <div class="content-condition-title content_title">查询地址</div>
                        <el-input v-model="addressVal" placeholder="请输入查询地址" class="addressVal"></el-input>
                    </div>
                    <div class="query-content-condition query-content-choose">
                        <div class="content-condition-title content_title">条件选择</div>
                        <el-form  :model="formInline"  :inline="true">
                                <el-form-item label="行业类型:">
                                    <el-select
                                            v-model="industryV"
                                            multiple
                                            collapse-tags
                                            placeholder="请选择">
                                        <el-option
                                                v-for="item in options.sycslb"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="积分等级:">
                                    <el-select
                                            v-model="levelV"
                                            multiple
                                            collapse-tags
                                            placeholder="请选择">
                                        <el-option
                                                v-for="item in options.jfde"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-form>
                    </div>
                    <div class="query-content-range query-content-choose">
                        <div class="content-range-title content_title">范围选择</div>
                        <el-form  :model="formInline"  :inline="true">
                                <el-form-item label="所属地市:">
                                    <el-select
                                            v-model="cityV"
                                            @change="getCounty"
                                            placeholder="全省">
                                        <el-option
                                                v-for="item in options.ssds"
                                                :key="item.code"
                                                :label="item.name"
                                                :value="item.code">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="所属区县:">
                                    <el-select
                                            v-model="countyV"
                                            placeholder="市辖区">
                                        <el-option
                                                v-for="item in options.ssqx"
                                                :key="item.code"
                                                :label="item.name"
                                                :value="item.code">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-form>
                    </div>
                </div>
           </div>
           <div class="qurey_btn">
                <el-button type="primary" size="small" icon="el-icon-search" class="btn" @click="searchBtn">搜索</el-button>
                <el-button type="primary" size="small" icon="el-icon-refresh" class="btn" @click="resetBtn">重置</el-button>
              <!--  <el-button  size="small" icon="el-icon-close" class="btn">取消</el-button>-->
            </div>

            <!--地图-->
            <div class="map-qurey-main">
                <div id="famp"></div>
            </div>
        </div>
        <!--弹框-->
        <el-dialog
                width="100%"
                fullscreen :visible.sync="generalDialog">
            <div>
            </div>
        </el-dialog>
    </basic-container>
</template>

<script>
    let ocrParametersList= [
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4591&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "PGIS矢量地图"
        },
    ];
    import {fetchArea,fetchHylx,fetchJfdj,fetchAddress} from '@/api/portal/archive/common/query'
    import {matchForward} from '@/api/map/map'
    import { validatenull } from "@/util/validate";
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
    import vuebubble from "@/components/map/vue-bubble.vue"
    export default {
        name: "index",
        components:{
            vueBubble:vuebubble
        },
        data() {
            return {
                addressVal:"",
                pageloading:false,
                options: {
                    sycslb:DIC.sycslb,
                    jfde:DIC.jfdj,
                    ssds:DIC.ssds,
                    ssqx:DIC.ssqx,
                },

                industryV: [],
                levelV: [],
                cityV: '',
                countyV:'',

                formInline:{},

                //map
                dialogVideoVisible:false,
                generalDialog:false,

                fmap:null,
                center:{
                    lon:113.27817764,
                    lat:23.13744564,
                    zoom: 12,
                    minZoom: 0,
                    maxZoom: 21
                },
                intiData: [],

                videoData: {
                    "standardAddressCode": "1E7B8816-2BAE-4C87-A3E9-C9D0779E8313",
                    "standardAddressName": "广东省广州市越秀区流花街道火车站社区环市西路159号广州站东广场",
                    "politicalName": "越秀区",
                    "politicalCode": null,
                    "regionName": "广州市公安局越秀区分局广场派出所",
                    "regionCode": null,
                    "matchPrecision": 5000,
                    "matchStatus": "1",
                    "matchCaseDescription": null,
                    "geo": "113.25322947 23.1502309",
                    "jcwmc": "火车站社区",
                    "distance": 0,
                    "dicLx": 0,
                    "x": 113.25322947,
                    "y": 23.1502309
                },
                shuidiData:{
                    "standardAddressCode": "6BBD34F4-F713-4EF4-A631-6BF7E64C1170",
                    "standardAddressName": "广东省广州市番禺区钟村街道石壁四村广州南站",
                    "politicalName": "番禺区",
                    "politicalCode": null,
                    "regionName": "广州市公安局番禺区分局石壁派出所",
                    "regionCode": null,
                    "matchPrecision": 5000,
                    "matchStatus": "1",
                    "matchCaseDescription": null,
                    "geo": "113.26410694 22.99149967",
                    "jcwmc": "石壁四村",
                    "distance": 0,
                    "dicLx": 0,
                    "x": 113.26410694,
                    "y": 22.99149967
                },
            }
        },
        mounted(){
            this.getCity();
            this.MapInit();
            this.selectedClick();
        },
        methods:{
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
            //按钮
            searchBtn(){
                this.pageloading=true;
                let levels = this.levelV,industrys = this.industryV,levelType ='',placeType ='',cityCode = '',address = this.addressVal;
                if(industrys.length>0){
                    for (let i = 0; i < industrys.length; i++) {
                        placeType += industrys[i]
                        if(i<industrys.length-1){
                            placeType += ','
                        }
                    }
                }

                if(levels.length>0){
                    for (let i = 0; i < levels.length; i++) {
                        levelType += levels[i]
                        if(i<levels.length-1){
                            levelType += ','
                        }
                    }
                }
                if (!validatenull(this.countyV)) {
                    cityCode = this.countyV
                }else {
                    cityCode = this.cityV
                }
                let parame ={levelType:levelType,placeType:placeType,cityCode:cityCode,placeAddress:address,rowNum:5}

                fetchAddress(parame).then(data=>{
                    let addressList=data.data.data;
                    this.intiData=[];
                    this.fmap.clearLayer("testLayer");
                    addressList.forEach(item=>{
                        let parame={
                            "province":"广东省",
                            "xz":item.placeAddress
                        }
                        matchForward(parame).then(data=>{
                            this.intiData.push(...data.data.data);
                            this.pageloading=false;
                            this.fmap.clearLayer("testLayer");
                            this.addRadomData(this.intiData,"testLayer");
                        }).catch(error=>{
                            this.pageloading=false;
                            this.$message.error('服务器出错!')
                        })
                    })

               /* })*/
            })
            },
            resetBtn(){
                    this.industryV=[];
                    this.levelV=[];
                    this.cityV="";
                    this.countyV="";
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
                //console.log(bglayer);
                var layers = {
                    'testLayer': {
                        name: 'testLayer',
                        bubble: 'generalBB',
                        symbol: 'shuidi',
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
                    'videoLayer': {name: 'videoLayer', symbol: 'video', bubble: 'videoBB'},
                    'defaultLayer': {name: 'defaultLayer', symbol: 'default',bubble: 'generalBB'},
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
                //this.selectedClick();
            },
            addRadomData(data,layer){
                this.fmap.addFeatures(data, {layer: layer});
            },
            selectedClick(){
                // 点击要素时查询要素详情信息
                this.fmap.on('fmap.feature.selected',(event)=>{
                    let clients = event.feature.values_.clients;
                    this.shuidiData=clients;
                });
            },
        }

    }
</script>

<style lang="scss">
    .map-qurey{
        *{
            box-sizing: border-box;
        }
        padding:20px;
        .map-qurey-body{
            box-shadow: 0px 0px 2px 1px #ccc;
            border-radius: 5px;
            .map-qurey-top{
                .inputV{
                    margin-left: 20px;
                    width:550px;
                }
                .qurey-title{
                    font-size: 18px;
                    font-weight: bold;
                    color:#067bf4;
                }
                .query-content-choose{
                    border-bottom: 1px solid #ccc;
                    overflow: hidden;
                    .content_title{
                        background: #f5faff;
                        width:130px;
                        box-sizing: border-box;
                        padding-right:20px;
                        text-align: right;
                        float:left;
                        height:80px;
                        line-height:80px;
                    }
                }
                .query-content-condition{
                    .content-condition-title{
                    }

                }
                .query-content-range{
                    .content-range-title{
                    }
                }
            }
        }
        .map-qurey-top{
            .el-form-item {
                margin-bottom: 0px !important;
            }

            .el-form-item__label{
                height: 80px;
                line-height:80px!important;
                margin-left: 20px;
            }
            .el-form-item__content{
                height: 80px;
                line-height:80px!important;
            }
        }
        .addressVal{
            width: 72%;
            padding-top: 20px;
            margin-left: 10px;
        }
        .qurey_btn{
            border-bottom: 1px solid #ccc;
            text-align: center;
            padding: 15px 0;
            .btn{
                margin:0 20px
            }
        }

        .fc-map-single-bubble.normal-bubble-box {
            min-width: 100px;
            min-height: 32px;
            width: auto;
            height: auto;
            background-color: #fff;
            border: 1px solid #23b1ed;
            webkit-border-radius: 15px;
            -moz-border-radius: 15px;
            border-radius: 6px;
            -webkit-box-shadow: 2px 2px 4px #888;
            -moz-box-shadow: 2px 2px 4px #888;
            box-shadow: 2px 2px 4px #888;
        }
        .fc-map-single-bubble.normal-bubble-box:before {
            content: ' ';
            position: absolute;
            width: 0;
            height: 0;
            left: 15px;
            bottom: -21px;
            border: 11px solid;
            border-color: #23b1ed transparent transparent #23b1ed;
        }
        .fc-map-single-bubble.normal-bubble-box:after {
            content: ' ';
            position: absolute;
            width: 0;
            height: 0;
            left: 16px;
            bottom: -19px;
            border: 10px solid;
            border-color: #fff transparent transparent #fff;
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
            border-right: 1px solid #1f94f4;
            overflow: hidden;
            height: 300px;
            width:75%;
            float:left;
        }
        .programme-desc{
            position: absolute;
            top:180px;
            right:25%;
            .desc_button{
                position: absolute;
                margin-top: 10px;
                width:112px;
                padding:8px 5px;
                color:#fff;
                font-size: 15px;
                border-left: 8px solid #067af3;
                background-color: #333;
                opacity: 0.7;
                text-align: center;
                cursor: pointer;
                right: 0;
            }
            .desc_button2{
                top:50px;
            }
            .desc_button:hover{
                color: #409EFF;
                background-color: #e4e7ed;
            }
            .btn_actives{
                width:120px;
                animation:my_move 1s;
                color: #409EFF;
                background-color: #e4e7ed;
            }
            @keyframes my_move{
                0%{ width:112px;}100%{width:120px;}
            }
        }
        .address_list{
            overflow: hidden;
            .address_list_body{
                overflow-y:scroll;
                height: 350px;
            }
            .address_list_item{
                border-bottom: 1px dashed #000;
                padding: 10px 0;
                font-size: 14px;
                cursor: pointer;
                margin: 0 10px;
            }
            .address_list_box:hover{
                background: #b8e8fe;
            }
            .address_list_boxactive{
                background: #b8e8fe;
            }
            .address_list_title{
                i{
                    font-size: 16px;
                    color:#1f94f4;
                    margin-right: 10px;
                }
                font-weight: bold;
            }
            .address_list_content{
                margin-left:26px;
            }
        }
    }

</style>