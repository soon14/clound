<template>
    <div class="mapBox" >
        <!--气泡-->
        <vue-bubble id="videoBB" >
            <div class="bubble-box">
                <el-form label-width="60px" label-position="right" ref="form">
                    <el-form-item  v-for="(item,key) in videoBBData" :label="item.label" :key="key">{{item.valve}}</el-form-item>
                </el-form>
                <div class="video_play" @click="dialogVideoVisible=true">
                    <el-button type="primary" plain class="video_playbtn" size="small">视频播放</el-button>
                </div>
            </div>
        </vue-bubble>
        <vue-bubble id="generalBB">
            <div class="bubble-box">
                <el-form label-position="right" ref="form">
                    <el-form-item  v-for="(item,key) in generalBBData" :label="item.label" :key="key">{{item.value}}</el-form-item>
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

        <!--列表-->
        <div class="mapList" :class={mapList_active:isActive}>
            <div class="unfold" @click="showList">
                <i class="iconfontlogo alshouqi" v-if="isActive"></i>
                <i class="iconfontlogo aldaohangzhankai-" v-else></i>
            </div>
            <div class="unfoldMain">
                <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane name="first">
                        <span slot="label" class="tab-label">场所</span>
                        <div class="mapListItem" v-for="(item,idx) in intiData" :key="idx"
                             @click="setBgChange(idx)"
                             :class="{'mapListItem_active':bgchange== idx }">
                            <div class="mapListMsg" @click="listClick(item),setMapCenter([item.x,item.y])">
                                <div class="mapListMsg_icon">
                                    <avue-text-ellipsis :text=item.standardAddressName :height="200" >
                                        <i class="iconfontlogo alchangsuo" slot="before"> {{idx+1}} </i>
                                        <small slot="more" >...</small>
                                    </avue-text-ellipsis>
                                </div>
                            </div>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane name="second">
                        <span slot="label" class="tab-label">摄像头</span>
                       <!-- <div class="mapListItem" v-for="(item,idx) in intiData" :key="idx"
                             @click="setBgChange(idx)"
                             :class="{'mapListItem_active':bgchange== idx }">
                            <div class="mapListMsg" @click="listClick(item),setMapCenter([item.x,item.y])">
                                <div>{{idx+1}}-{{item.standardAddressName}}</div>
                            </div>
                        </div>-->
                    </el-tab-pane>
                </el-tabs>
            </div>
        </div>

        <div class="mapMain">
            <!--地图容器-->
            <div id="fmaps"></div>
        </div>

        <!--弹框-->
        <el-dialog
                width="100%"
                fullscreen :visible.sync="generalDialog">
                <slot></slot>
        </el-dialog>
    </div>
</template>

<script>
    import vuebubble from "./vue-bubble.vue"
    import {matchForward,autoComplete,intSearch,matchPoint,getAreaBoundary} from '@/api/map/map'
    let ocrParametersList= [
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4591&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "PGIS矢量地图"
        },
    ];

    export default {
        name: "bacsMap",
        components:{
            vueBubble:vuebubble
        },
        props:{
            value:{
                default:function () {
                    return {
                        type:"",
                        data:{},
                    }
                },
            }
        },
        data () {
            return{
                fmap:null,
                parmars:{
                    "xz":"广州南站",
                    "province":"广东省"
                },
                generalDialog:false,
                center:{
                    lon:113.27817764,
                    lat:23.13744564,
                    zoom: 12,
                    minZoom: 2,
                    maxZoom: 21
                },
                singleData:[],
                intiData: [],
                bgchange:null,

                videoBBData:[],
                generalBBData:[],

                activeName: 'first',
                isActive:true,
            }
        },
        mounted(){
            this.MapInit();
            this.getList();
        },
        methods: {
            //地图
            MapInit(){
                // 注册要素图元缺省配置
                FUtil.registSymbolDefaultSetting({
                    sourcePath:"/cdn/fmap/img",
                    size: [32,46]
                });
                // 注册要素图元
                FUtil.registSymbols(['shuidi','video','pointred']);
                let bglayer=FUtil.FPM({
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
                let layers = {
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
                    'redLayer': {name: 'redLayer', symbol: 'pointred',bubble: 'generalBB'},
                };
                // 创建地图实例
                this.fmap = new FMap({
                    element:'fmaps',
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
            addLayer(clearLayer,addLayer){
                this.fmap.clearLayer(clearLayer);
                this.addRadomData(this.intiData,addLayer);
            },
            selectedClick(){
                // 点击要素时查询要素详情信息
                this.fmap.on('fmap.feature.selected',(event)=>{
                    let clients = event.feature.values_.clients;
                    this.$emit("selectedClick",clients);
                    if(this.value.type=="matchForward"){
                        this.generalBBData=[
                            {label:"标准地址名称",value:clients.standardAddressName,key:"standardAddressName"},
                            {label:"管理辖区名称",value:clients.politicalName?clients.politicalName:"暂无数据",key:"politicalName"},
                            {label:"行政区划名称",value:clients.regionName,key:"regionName"}
                        ];
                    }else if(this.value.type=="autoComplete"){
                        this.generalBBData=[
                            {label:"地址名称",value:clients.dzqc,key:"dzqc"}
                        ];
                    }else if(this.value.type=="intSearch"){
                        this.generalBBData=[
                            {label:"id",value:clients.id,key:"id"},
                            {label:"地址名称",value:clients.dzqc,key:"dzqc"},
                        ];
                    }else if(this.value.type=="matchPoint"){
                        this.generalBBData=[
                            {label:"标准地址名称",value:clients.standardAddressName,key:"standardAddressName"},
                        ];
                    }else if(this.value.type=="getAreaBoundary"){
                        this.generalBBData=[
                            {label:"区划或辖区名称",value:clients.areaName,key:"areaName"},
                        ];
                    }else{
                        this.$message('暂无地址');
                    }
                });
            },
            setMapCenter([lon, lat]) {
                this.fmap.navigate2Center([lon, lat]);
            },
            listClick(data){
                this.addRadomData(this.intiData,"testLayer");
                this.fmap.clearLayer("redLayer");
                this.singleData=[data];
                this.intiData.forEach( (items,index) => {
                    if (items.standardAddressCode == data.standardAddressCode){
                        this.addRadomData(this.singleData,"redLayer");
                        let changeDate =JSON.parse(JSON.stringify(this.intiData));
                        changeDate.splice(index,1);
                        this.fmap.clearLayer("testLayer");
                        this.addRadomData(changeDate,"testLayer");;
                    }
                })

            },
            getList(){
                this.fmap.clearLayer("testLayer");
                if(this.value.type=="matchForward"){
                    matchForward(this.value.data).then(data=>{
                        this.intiData=data.data.data;
                        this.addRadomData(this.intiData,"testLayer");
                    })
                }else if(this.value.type=="autoComplete"){
                    autoComplete(this.value.data).then(data=>{
                        this.intiData=data.data.data;
                        this.addRadomData(this.intiData,"testLayer");
                    })
                }else if(this.value.type=="intSearch"){
                    intSearch(this.value.data).then(data=>{
                        this.intiData=data.data.data;
                        this.addRadomData(this.intiData,"testLayer");
                    })
                }else if(this.value.type=="matchPoint"){
                    matchPoint(this.value.data).then(data=>{
                        this.intiData=data.data.data;
                        this.addRadomData(this.intiData,"testLayer");
                    })
                }else if(this.value.type=="getAreaBoundary"){
                    getAreaBoundary(this.value.data).then(data=>{
                        this.intiData=data.data.data;
                        this.addRadomData(this.intiData,"testLayer");
                    })
                }else{
                    this.$message('暂无地址');
                }
            },
            setBgChange(index){
                this.bgchange=index;
            },
            handleClick(tab, event) {
              //console.log(tab, event);
            },
            showList(){
                this.isActive=!this.isActive;
            }
        },
        watch:{
            value:{
                handler(newVal){
                  //  console.log(newVal);
                }
            }
        }
    }
</script>

<style lang="scss">
    .mapBox{
        *{box-sizing: border-box;}
        height:100%;
        width:100%;
        border:1px solid #ccc;
        position: relative;
        overflow: hidden;

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
            max-width: 400px;
            padding:0 10px;
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
        .mapMain{
            height:100%;
            width:100%;
        }
        .mapList{
            .tab-label{
                padding:0 10px
            }
            position: absolute;
            right:2px;
            bottom:0;
            width:400px;
            max-height:100%;
            border:1px solid #409eff;
            border-radius: 4px;
            z-index: 100;
            font-size: 14px;
            background: #fff;
            transition: all 2s;
            .unfold{
                position: absolute;
                top: 24%;
                left: -36px;
                border: 1px solid #409eff;
                border-right: 0;
                padding:5px 0px 5px 5px;
                border-top-left-radius: 5px;
                border-bottom-left-radius: 5px;
                border-bottom: 0;
                background-color: #fff;
                z-index: 101;
                transform: translate(0, -50%);
                &::after{
                    content: "";
                    height: 20px;
                    position: absolute;
                    right: 0px;
                    width: 3px;
                    bottom: -12px;
                    background: #fff;
                    border-radius: 5px;
                    border-bottom: 1px solid #409eff;
                }
                &::before{
                    content: '';
                    width: 35px;
                    height: 9px;
                    border-bottom: 1px solid #409eff;
                    position: absolute;
                    bottom: -5px;
                    background: #fff;
                    left: 0px;
                    transform: rotate(25deg);
                }
                i{
                    font-size: 30px;
                    color:#409eff;
                }
            }
            .unfoldMain{
                min-height: 240px;
             }
            .mapListMsg{
                padding: 8px 10px;
                .mapListMsg_icon{
                    color:#409eff;
                }
            }
            .mapListItem{
                cursor: pointer;
                border-bottom: 1px solid #ccc;
            }
            .mapListItem:last-child{
                border-bottom: none;
            }
            .mapListItem :hover{
                background-color: #409eff;
                .mapListMsg_icon{
                    color:#fff;
                }
            }
            .mapListItem_active{
                background-color: #409eff;
                .mapListMsg_icon{
                    color:#fff;
                }
            }

        }
        .mapList_active{
            transform: translateX(405px);
        }
    }
</style>