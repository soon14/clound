<template>
    <div class="monitor">
        <el-row type="flex" class="monitor-main" :gutter="15">
            <el-col :span="7" class="monitor-col ml20 monitor-col1">
                <div class="grid-content">
                    <div class="panelbox" v-for="(panelitem,index) in panelData" v-bind:key="Math.random()+'_'+index">
                        <div class="panelbox-son panelbox-icon">
                            <div class="icon-father">
                                <i :class="panelitem.icon"></i>
                            </div>
                        </div>
                        <div class="panelbox-son panelbox-data">
                            <p v-text="panelitem.title"></p>
                            <p v-text="panelitem.num"></p>
                        </div>
                        <!--<div class="panelbox-son panelbox-hint">
                            <el-popover
                                    placement="top-start"
                                    trigger="hover"
                                    width="200"
                                    content="">
                                <i class="iconfontlogo ali" slot="reference"></i>
                            </el-popover>
                        </div>-->
                    </div>
                </div>
                <div class="records">
                    <h4>变更记录</h4>

                    <div class="records-main" v-loading="recordsLoading">
                        <div class="empty-div" v-if="recordsData==null || recordsData.length==0">
                            <avue-empty></avue-empty>
                        </div>
                        <div v-else class="records-item" v-for="(recordsItem,index) in recordsData"  v-bind:key="Math.random()+'_'+index">
                            <span class="records-change" v-text="recordsItem.totalScore"></span>
                            <h6 style="width: 84%;" @click="openDialog(recordsItem.placeId)">
                                <a href="#" @click="openDialog(recordsItem.placeId)">
                                    <avue-text-ellipsis
                                            :key="recordsItem.placeName"
                                            :text="recordsItem.placeName"
                                            use-tooltip
                                            :height="40"
                                            placement="top">
                                        <small slot="more">...</small>
                                    </avue-text-ellipsis>
                                </a>
                            </h6>
                            <p class="update-remark" v-text="recordsItem.pointIndex.name"></p>
                            <div class="records-time"><span>{{findLabelByValue(hylxDict,recordsItem.pointIndex.placeType)}}</span><span
                                    v-text="recordsItem.bonusTime"></span></div>
                        </div>
                    </div>
                </div>
            </el-col>
            <el-col :span="8" class="monitor-col monitor-col3 ">
                <div class="grid-content">
                    <div class="content" v-loading="hotelLoading">
                        <h4>{{industry.LGY}}</h4>
                        <span class="hint"><el-popover
                                placement="top-start"
                                trigger="hover"
                                width="200"
                                :content="industry.LGY">
                               <i class="iconfontlogo ali" slot="reference"></i>
                           </el-popover></span>
                        <div class="empty-div" v-if="lgyData==null || lgyData.length==0">
                            <avue-empty></avue-empty>
                        </div>
                        <div v-else class="tabel-box">
                            <ul class="tabel-header">
                                <li>排名</li>
                                <li>特行场所</li>
                                <li>总分</li>
                                <li>等级</li>
                            </ul>
                            <ul class="tabel-item" v-for="(item,index) in lgyData" v-bind:key="Math.random()+'_'+index">
                                <li>{{index+1}}</li>
                                <li v-text="item.placeName" :title="item.placeName" class="palce"
                                    @click="openScoreDetailDialog(item)"></li>
                                <li v-text="item.totalScore"></li>
                                <li v-text="findLabelByValue(dictLevelTypeData,item.levelType) "></li>
                            </ul>
                        </div>
                        <div class="more" v-if="lgyData!=null && lgyData.length > 0">
                            <span @click="popUpFn(sourceType.hotel)">查看完整排名</span>
                        </div>
                    </div>
                    <div class="content" v-loading="machineLoading">
                        <h4>{{industry.JXY}}</h4>
                        <span class="hint"><el-popover
                                placement="top-start"
                                trigger="hover"
                                width="200"
                                :content="industry.JXY">
                               <i class="iconfontlogo ali" slot="reference"></i>
                           </el-popover></span>
                        <div class="empty-div" v-if="machineData==null || machineData.length==0">
                            <avue-empty></avue-empty>
                        </div>
                        <div v-else class="tabel-box">
                            <ul class="tabel-header">
                                <li>排名</li>
                                <li>特行场所</li>
                                <li>总分</li>
                                <li>等级</li>
                            </ul>
                            <ul class="tabel-item" v-for="(item,index) in  machineData" v-bind:key="Math.random()+'_'+index">
                                <li>{{index+1}}</li>
                                <li v-text="item.placeName" :title="item.placeName" class="palce"
                                    @click="openScoreDetailDialog(item)"></li>
                                <li v-text="item.totalScore"></li>
                                <li v-text="findLabelByValue(dictLevelTypeData,item.levelType) "></li>
                            </ul>
                        </div>
                        <div class="more" v-if="machineData!=null && machineData.length > 0">
                            <span @click="popUpFn(sourceType.machine)">查看完整排名</span>
                        </div>
                    </div>
                    <!--   <div class="content">
                           <h4>{{industry.KSY}}</h4>
                           <span class="hint"><el-popover
                                   placement="top-start"
                                   trigger="hover"
                                   width="200"
                                   content="">
                                  <i class="iconfontlogo ali" slot="reference"></i>
                              </el-popover></span>
                           <div class="tabel-box">
                               <ul class="tabel-header">
                                   <li>排名</li>
                                   <li>特行场所</li>
                                   <li>总分</li>
                                   <li>等级</li>
                               </ul>
                               <ul class="tabel-item" v-for="(item,index) in entertainmentData">
                                   <li>{{index+1}}</li>
                                   <li  v-text="item.placeName" :title="item.placeName" class="palce" @click="showDetail(item.placeName)"></li>
                                   <li v-text="item.totalScore"></li>
                                   <li v-text="findLabelByValue(dictLevelTypeData,item.levelType) "></li>
                               </ul>
                           </div>
                           <div class="more">
                               <span @click="popUpFn(industry.KSY)">查看完整排名</span>
                           </div>
                       </div>-->
                    <div class="content" v-loading="otherLoading">
                        <h4>{{industry.QTHY}}</h4>
                        <span class="hint"><el-popover
                                placement="top-start"
                                trigger="hover"
                                width="200"
                                :content="industry.QTHY">
                               <i class="iconfontlogo ali" slot="reference"></i>
                           </el-popover></span>
                        <div class="empty-div" v-if="otherData==null || otherData.length==0">
                            <avue-empty></avue-empty>
                        </div>
                        <div v-else class="tabel-box">

                            <ul class="tabel-header">
                                <li>排名</li>
                                <li>特行场所</li>
                                <li>总分</li>
                                <li>等级</li>
                            </ul>
                            <ul class="tabel-item" v-for="(item,index) in otherData" v-bind:key="Math.random()+'_'+index">
                                <li>{{index+1}}</li>
                                <li v-text="item.placeName" :title="item.placeName" class="palce"
                                    @click="openScoreDetailDialog(item)"></li>
                                <li v-text="item.totalScore"></li>
                                <li v-text="findLabelByValue(dictLevelTypeData,item.levelType) "></li>
                            </ul>
                        </div>
                        <div class="more" v-if="otherData!=null &&  otherData.length >0">
                            <span @click="popUpFn(sourceType.other)">查看完整排名</span>
                        </div>
                    </div>
                </div>
            </el-col>
            <el-col :span="8" class="monitor-col monitor-col4 ">
                <div class="grid-content">
                    <div class="content" v-loading="entertainmentLoading">
                        <h4>{{industry.GWYLCS}}</h4>
                        <span class="hint"><el-popover
                                placement="top-start"
                                trigger="hover"
                                width="200"
                                :content="industry.GWYLCS">
                               <i class="iconfontlogo ali" slot="reference"></i>
                           </el-popover></span>
                        <div class="empty-div" v-if="entertainmentData==null || entertainmentData.length==0">
                            <avue-empty></avue-empty>
                        </div>
                        <div v-else class="tabel-box">
                            <ul class="tabel-header">
                                <li>排名</li>
                                <li>特行场所</li>
                                <li>总分</li>
                                <li>等级</li>
                            </ul>
                            <ul class="tabel-item" v-for="(item,index) in entertainmentData" v-bind:key="Math.random()+'_'+index">
                                <li>{{index+1}}</li>
                                <li v-text="item.placeName" :title="item.placeName" class="palce"
                                    @click="openScoreDetailDialog(item)"></li>
                                <li v-text="item.totalScore"></li>
                                <li v-text="findLabelByValue(dictLevelTypeData,item.levelType) "></li>
                            </ul>
                        </div>
                        <div class="more" v-if="entertainmentData!=null &&  entertainmentData.length > 0">
                            <span @click="popUpFn(sourceType.entertainment)">查看完整排名</span>
                        </div>
                    </div>
                    <div class="content" v-loading="sealLoading">
                        <h4>{{industry.KYZY}}</h4>
                        <span class="hint"><el-popover
                                placement="top-start"
                                trigger="hover"
                                width="200"
                                :content="industry.KYZY">
                               <i class="iconfontlogo ali" slot="reference"></i>
                           </el-popover></span>
                        <div class="empty-div" v-if="sealData==null || sealData.length==0">
                            <avue-empty></avue-empty>
                        </div>
                        <div v-else class="tabel-box">
                            <ul class="tabel-header">
                                <li>排名</li>
                                <li>特行场所</li>
                                <li>总分</li>
                                <li>等级</li>
                            </ul>
                            <ul class="tabel-item" v-for="(item,index) in sealData" v-bind:key="Math.random()+'_'+index">
                                <li>{{index+1}}</li>
                                <li v-text="item.placeName" :title="item.placeName" class="palce"
                                    @click="openScoreDetailDialog(item)"></li>
                                <li v-text="item.totalScore"></li>
                                <li v-text="findLabelByValue(dictLevelTypeData,item.levelType) "></li>
                            </ul>
                        </div>
                        <div class="more" v-if="sealData!=null && sealData.length>0">
                            <span @click="popUpFn(sourceType.seal)">查看完整排名</span>
                        </div>
                    </div>

                </div>
            </el-col>
        </el-row>


        <el-dialog title="查看" :visible.sync="visibleView" :append-to-body="applyToBody" width="80%"
                   :before-close="closeDialog">
            <score-detail v-bind:detail-row="detailRow"></score-detail>
            <span slot="footer"
                  class="dialog-footer">
                <el-button @click="closeDialog">关闭</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

    import {totalTodayDetail, findNewestAutomaticList} from "@/api/portal/point/monitor/monitor"
    import {currentCycleScoreByPlaceId, topScoreList} from '@/api/portal/point/score/pointScore'
    import {starCycle} from '@/api/portal/point/scheme/pointScheme'
    import {deepClone} from '@/util/util'
    import {remote} from "@/api/admin/dict"
    import scoreDetail from '@/views/portal/point/score/scoreDetail'

    export default {
        name: "monitor",
        components: {
            scoreDetail,
        },
        data() {
            return {
                pointSchemeId: "",
                visibleView: false,
                applyToBody: true,
                dictLevelTypeData: [],
                hylxDict: [],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                detailRow: {},
                lgyData: [],
                title: "总排名",
                industry: {
                    ZPM: "总排名",
                    LGY: "旅馆业",
                    GWYLCS: "歌舞娱乐场所",
                    JXY: "机修业",
                    KYZY: "刻印章业",
                    KSY: "开锁业",
                    QTHY: "其他行业"
                },
                sourceType: {
                    hotel: '3',
                    entertainment: '1',
                    machine: '2',
                    seal: '4',
                    other: '0'
                },

                panelData: [
                    {
                        icon: "el-icon-collection",
                        title: "今日自动积分",
                        num: 0,
                        type: "0"
                    },
                    {
                        icon: "el-icon-reading",
                        title: "今日手动积分",
                        num: 0,
                        type: "1"
                    },
                ],
                recordsData: [],

                popUp: {
                    XQ: false,
                },
                entertainmentData: [],//娱乐场所数据
                machineData: [],//机修业
                sealData: [],//刻字业
                otherData: [],//其他特行场所
                hotelLoading: false,
                entertainmentLoading: false,
                machineLoading: false,
                sealLoading: false,
                otherLoading: false,
                recordsLoading: false

            }
        },
        mounted() {
            this.initDict();
            this.initTotal();  //获取当日新增数
            this.currentCycle().then((res) => {
                this.recordsLoading = true
                this.pointSchemeId = res.id;
                this.initUpdateList();//获取当前周期自动积分更新列表
            });
            //加载各行业排名
            this.initScoreData();


        },
        created() {

        },
        methods: {
            currentCycle() {
                return new Promise(function (resolve, reject) {
                    starCycle().then(respond => {
                        resolve(respond.data.data);
                    }).catch(() => {
                    });
                })
            },
            initTotal() {
                //统计今日两种指令分别数量
                totalTodayDetail().then(data => {
                    let result = data.data.data;
                    if (result != null && result.length > 0) {
                        let map = {};
                        for (let i = 0; i < result.length; i++) {
                            let re = result[i];
                            map[re.TYPE] = re.TOTAL;
                        }
                        let panelData = this.panelData;
                        let array = [];
                        for (j = 0; j < panelData.length; j++) {
                            let d = panelData[j];
                            d.num = map[d.type] == undefined ? 0 : map[d.type];
                            array.push(deepClone(d));
                        }
                        this.panelData = array;
                    }
                }).catch((e) => {
                    console.log(e)
                });
            },
            initUpdateList() {
                this.recordsLoading = true
                findNewestAutomaticList(this.pointSchemeId).then(respond => {
                    this.recordsData = respond.data.data || [];
                    this.recordsLoading = false
                }).catch(() => {
                    this.recordsLoading = false
                });
            },
            initDict() {
                remote("HYLX_DL").then(response => {
                    this.hylxDict = response.data.data || [];
                })
                remote('jfdj').then(response => {
                    this.dictLevelTypeData = response.data.data || []
                })
            },
            initScoreData() {
                this.hotelLoading = true
                this.entertainmentLoading = true
                this.machineLoading = true
                this.sealLoading = true
                this.otherLoading = true
                topScoreList(Object.assign({}, {sourceType: '3'})).then(response => {
                    this.lgyData = response.data.data || [];
                    this.hotelLoading = false
                }).catch((err) => {
                    this.hotelLoading = false
                });
                topScoreList(Object.assign({}, {sourceType: '1'})).then(response => {
                    this.entertainmentData = response.data.data || [];
                    this.entertainmentLoading = false
                }).catch((err) => {
                    this.entertainmentLoading = false
                });
                topScoreList(Object.assign({}, {sourceType: '2'})).then(response => {
                    this.machineData = response.data.data || [];
                    this.machineLoading = false
                }).catch((err) => {
                    this.machineLoading = false
                });
                topScoreList(Object.assign({}, {sourceType: '4'})).then(response => {
                    this.sealData = response.data.data || [];
                    this.sealLoading = false
                }).catch((err) => {
                    this.sealLoading = false
                });

                topScoreList(Object.assign({}, {sourceType: '0'})).then(response => {
                    this.otherData = response.data.data || [];
                    this.otherLoading = false
                }).catch((err) => {
                    this.otherLoading = false
                });


            },
            openDialog(placeId) {
                currentCycleScoreByPlaceId(placeId).then(respond => {
                    let result = respond.data.data;
                    if (result != null) {
                        this.detailRow = deepClone(result);
                        this.visibleView = true;
                    } else {
                        this.$message.info("无积分信息")
                    }

                }).catch(() => {
                });

            },
            openScoreDetailDialog(row) {
                this.detailRow = deepClone(row);
                this.detailRow.cycleType = '1' //去除撤销
                this.visibleView = true
            },
            closeDialog() {
                this.visibleView = false;
            },
            popUpFn(data) {
                this.$store.commit("DEL_TAG_BY_LABEL", "积分排名");
                this.$router.push({
                    path: this.$router.$avueRouter.getPath({
                        name: '积分排名',
                        src: "/portal/point/score",
                    }),
                    query: {
                        sourceType: data
                    }
                });
            },

        },
    }
</script>

<style lang="scss">
    .empty-div {
        height: 160px;
        margin: 20px 0px 0px 0px;
    }

    .monitor {
        * {
            box-sizing: border-box;
        }

        .ml20 {
            margin-left: 30px;
        }

        h4, ul {
            margin: 0;
            padding: 0;
        }

        ul, li {
            list-style: none;
        }

        .hint {
            position: absolute;
            right: 10px;
            top: 0;
            height: 40PX;
            line-height: 40px;
        }

        h4 {
            padding-left: 10px;
            height: 40PX;
            line-height: 40px;
            border-bottom: 1px solid #ccc;
        }

        .content {
            position: relative;
            background: #fff;
        }

        .tabel-box {
            padding: 0 10px;
            overflow: hidden;

            ul {
                overflow: hidden;
                clear: both;
                border-bottom: 1px solid #ccc;

                li {
                    float: left;
                    height: 50px;
                    line-height: 50px;
                    text-align: center;
                    font-size: 14px;
                    color: #666;
                }

                li:nth-child(1), li:nth-child(3), li:nth-child(4) {
                    width: 15%;
                }

                li:nth-child(2) {
                    width: 55%;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    white-space: nowrap;
                }
            }

            .tabel-header li {
                font-weight: bold;
            }

            .tabel-item li {
                height: 40px;
                line-height: 40px;
            }

            .tabel-item {
                li:nth-child(2) {
                    width: 55%;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    white-space: nowrap;
                    color: #2e83db;
                }
            }
        }

        .more {
            text-align: center;
            cursor: pointer;
            width: 100%;

            span {
                border-bottom: 1px solid #68b6ff;
                color: #68b6ff;
                font-size: 14px;
                font-weight: bold;
                height: 40px;
                line-height: 40px;
            }
        }

        .monitor-main {
            padding-top: 5px;
        }

        .monitor-col {
            min-width: 317px;

            .panelbox {
                overflow: hidden;
                clear: both;
                height: 100px;
                background: #fff;

                .panelbox-son {
                    float: left;
                }

                .panelbox-icon {
                    display: flex;
                    justify-content: center;
                    width: 40%;
                    height: 100px;

                    .icon-father {
                        margin: auto;
                        width: 70px;
                        height: 70px;
                        background: #108eff;
                        border-radius: 50%;
                        line-height: 70px;
                        text-align: center;
                        color: #fff;

                        i {
                            height: 70px;
                            line-height: 70px;
                            font-size: 35px;
                        }
                    }
                }

                .panelbox-data {
                    width: 45%;
                    font-size: 16px;
                    height: 100px;
                    text-align: center;

                    p {
                        margin: 0;
                        padding: 0;
                    }

                    p:nth-child(1) {
                        height: 50px;
                        line-height: 60px;
                        color: #747c8e;
                    }

                    p:nth-child(2) {
                        font-size: 20px;
                        font-weight: 600;
                    }
                }

                .panelbox-hint {
                    height: 100px;
                    line-height: 100px;
                    width: 10%;
                    text-align: center;
                    font-size: 20px;
                }

            }

            .panelbox:nth-child(2) {
                margin-top: 20px;
            }

            .records {
                background: #fff;
                margin-top: 20px;

                h4 {
                    margin: 0;
                    padding-left: 10px;
                    height: 40px;
                    line-height: 50px;
                    border-bottom: 1px solid #e6e6e6;
                }

                p {
                    margin: 0;
                    padding: 0;
                }

                .records-item {
                    height: 100px;
                    position: relative;
                    margin: 0 10px;
                    border-bottom: 1px solid #e6e6e6;

                    .records-change {
                        position: absolute;
                        right: 0;
                        top: 0;
                        margin-right: 10px;
                        height: 30px;
                        line-height: 30px;
                        font-weight: bold;
                        padding: 7px 0px;
                    }
                    h6 {
                        margin: 0;
                        padding: 5px 0px;
                        height: 35px;
                        line-height: 35px;
                        a{
                            height: 35px;
                            font-size: 19px;
                            color: #2482e3;
                        }
                    }
                    p {
                        font-size: 16px;
                    }

                    .records-time {
                        overflow: hidden;
                        text-overflow: ellipsis;
                        white-space: nowrap;
                        padding: 8px 0;
                        font-size: 12px;
                        color: #666;

                        span:nth-child(1) {
                            margin-right: 5px;
                        }
                    }
                }

                .records-item:nth-last-child(1) {
                    border-bottom: none;
                }
            }
        }

        .palce {
            cursor: pointer;
        }

        .monitor-col2 {
            min-width: 380px;

            .content {
                position: relative;
            }

            .more {
                position: absolute;
                bottom: 0;
            }

        }

        .monitor-col3, .monitor-col4 {
            min-width: 380px;

            .content:nth-child(2),
            .content:nth-child(3) {
                margin-top: 20px;
            }
        }

        .update-remark {
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            padding: 5px 0px !important;
        }

        .records-null {
            height: 110px;
            line-height: 6;
            text-align: center;
            color: #8a8282;
        }
    }

</style>
