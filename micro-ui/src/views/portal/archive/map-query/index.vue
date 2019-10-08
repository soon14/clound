<template>
    <basic-container class="map-qurey">
        <div class="map-qurey-body">
           <div class="map-qurey-top">
               <div class="qurey-title">
                   <el-input v-model="input" placeholder="请输入场所名称"></el-input>
               </div>
                <div class="query-content">
                    <div class="query-content-condition query-content-choose">
                        <div class="content-condition-title content_title">
                            条件选择
                        </div>
                        <div class="content_body">
                            <el-form  :model="formInline"  :inline="true">
                                <el-form-item label="行业类型:">
                                    <el-select
                                            v-model="value1"
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
                                            v-model="value2"
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
                    </div>
                    <div class="query-content-range query-content-choose">
                        <div class="content-range-title content_title">
                            范围选择
                        </div>
                        <div class="content_body">
                            <el-form  :model="formInline"  :inline="true">
                                <el-form-item label="所属地市:">
                                    <el-select
                                            v-model="value3"
                                            placeholder="全省">
                                        <el-option
                                                v-for="item in options.sycslb"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="所属区县:">
                                    <el-select
                                            v-model="value4"
                                            placeholder="越秀区">
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
                    </div>
                </div>
           </div>
            <div class="qurey_btn">
                <el-button type="primary" size="small" icon="el-icon-search" class="btn">搜索</el-button>
                <el-button type="primary" size="small" icon="el-icon-refresh" class="btn">重置</el-button>
                <el-button  size="small" icon="el-icon-close" class="btn" @click="linkMap()">取消</el-button>
            </div>
            <div class="map-qurey-main" style="height: 400px">
                <div id="querymap">

                </div>
            </div>
        </div>
    </basic-container>
</template>

<script>
    let ocrParametersList= [
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4591&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "PGIS矢量地图"
        },
    ];
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

    };
    export default {
        name: "index.vue",
        data() {
            return {
                obj: {},
                input:"",
                options: {
                    sycslb:DIC.sycslb,
                    jfde:DIC.jfdj,
                },
                value1: [],
                value2: [],
                value3: [],
                value4: [],
                querymap:null,
            }
        },
        computed: {
            option(){
                return{

                }
            }
        },
        mounted(){
            this.MapInit();

        },
        methods:{
            linkMap(){
                this.$router.replace({
                    path: this.$router.$avueRouter.getPath({
                        name:'基础档案信息',
                        src: "/portal/archive/fileInfo/index",
                    }),
                    query: {
                    }
                });
            },
            MapInit(){
                var bglayer=FUtil.FPM({
                    url:ocrParametersList[0].applyMapUrl,
                    origin:[109.5,  27],
                    /* origin:[
                     [64,  64],
                     [64,  64],
                     [64,  64],
                     [64,  64],
                     [64,  64],
                     [64,  64],
                     [64,  64],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28],
                     [107.5,  28]
                     ],*/
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
                    //crossOrigin:true,  // 如果跨域情况下，添加此配置
                });
                // 创建地图实例
                this.querymap = new FMap({
                    element:'querymap',
                    bglayer:bglayer,
                    center:{
                        lon:113.27817764,
                        lat:23.13744564,
                        zoom: 12,
                        minZoom: 2,
                        maxZoom: 21
                    },
                    sign:true
                });
            },

        }

    }
</script>

<style lang="scss">
    .map-qurey{
        padding:20px;
        .map-qurey-body{
            box-shadow: 0px 0px 2px 1px #ccc;
            border-radius: 5px;
            .map-qurey-top{
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
        .qurey_btn{
            border-bottom: 1px solid #ccc;
            text-align: center;
            padding: 15px 0;
            .btn{
                margin:0 20px
            }
        }
    }

</style>