<template>
    <div class="info-cate">
        <!--气泡-->
        <vue-bubble id="videoBB" >
            <div class="bubble-box">
                <el-form label-width="60px" label-position="right" v-model="videoData">
                    <el-form-item label="编号:">{{videoData.bianhao}}</el-form-item>
                    <el-form-item label="名称:">{{videoData.mingcheng}}</el-form-item>
                    <el-form-item label="地址:">{{videoData.dizhi}}</el-form-item>
                    <el-form-item label="IP:">{{videoData.ip}}</el-form-item>
                    <el-form-item label="X坐标:">{{videoData.xzuobiao}}</el-form-item>
                    <el-form-item label="Y坐标:">{{videoData.yzuobiao}}</el-form-item>
                </el-form>
                <div class="video_play" @click="dialogVideoVisible=true">
                    <el-button type="primary" plain class="video_playbtn" size="small">视频播放</el-button>
                </div>
            </div>
        </vue-bubble>
        <vue-bubble id="generalBB" >
            <div class="bubble-box">
                <el-form label-width="60px" label-position="right" v-model="videoData">
                    <el-form-item label="编号:">{{videoData.bianhao}}</el-form-item>
                    <el-form-item label="名称:">{{videoData.mingcheng}}</el-form-item>
                    <el-form-item label="地址:">{{videoData.dizhi}}</el-form-item>
                    <el-form-item label="IP:">{{videoData.ip}}</el-form-item>
                    <el-form-item label="X坐标:">{{videoData.xzuobiao}}</el-form-item>
                    <el-form-item label="Y坐标:">{{videoData.yzuobiao}}</el-form-item>
                </el-form>
            </div>
        </vue-bubble>
        <!--内容-->
        <div class="fileInfo_main">
            <!--基础信息-->
            <el-form  class="form-main">
                <el-row  class="form-main-title">
                    <span class="main-title-name" v-text="detailForm.qyzcmc"> </span>
                    <span class="main-title-score" v-text="detailForm.pointScore==null?'':detailForm.pointScore + ' 分'">***分</span>
                    <span class="main-title-level main-title-score" v-text=" pointDict()">***级</span>
                   <place-collect :param="searchForm"></place-collect>
                </el-row>
                <el-row class="form-main-body" v-loading="detailLoading">
                    <el-col :span="18" class="form-body-l form-body-tabel">
                        <el-form label-width="135px" label-position="left" >
                            <el-col>
                                <el-col :span="8">

                                    <el-form-item label="经营名称">
                                        <span v-text="detailForm.qysjjymc"></span>
                                    </el-form-item>

                                </el-col>

                                <el-col :span="8">
                                    <el-form-item label="单位类别">
                                        <span v-text="dictLabel(dictUnitTypeData,detailForm.gxhylx) "></span>
                                    </el-form-item>
                                </el-col>
                                    <el-col :span="8">
                                        <el-form-item label="企业性质">
                                            <span v-text="dictLabel(dictNatureData,detailForm.qyxz)"></span>
                                        </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="行业类别">
                                        <span v-text="dictLabel(dictTradeTypeData,detailForm.hylb) "></span>
                                    </el-form-item>
                                </el-col>

                                <el-col :span="8">
                                    <el-form-item label="企业状态">
                                        <span v-text="dictLabel(dictBusinessStatusData,detailForm.jyzt)"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="开业日期">
                                        <span v-text="detailForm.kyrq" ></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="管辖单位">
                                       <!-- <span v-text="dictLabel(dictDeptData,detailForm.departmentCode)"></span>-->
                                        <span v-text="detailForm.deptName"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="责任民警名称">
                                        <span v-text="detailForm.zrmjxm"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="责任民警警号">
                                        <span v-text="detailForm.zrmjjh"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="主营">
                                        <span v-text="detailForm.jyxmzy==null?'无':detailForm.jyxmzy"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="兼营">
                                        <span v-text="detailForm.jyxmjy==null?'无':detailForm.jyxmjy"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="被兼营单位">
                                        <span v-text="detailForm.bjydw==null?'无':detailForm.bjydw"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>

                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="行政区划">
                                        <span v-text="dictLabel(dictCityData,detailForm.dwszdxzqh) "></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="联系电话">
                                        <span v-text="detailForm.lxdh"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="警务区电话">
                                        <span v-text="detailForm.jwqlxdh"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>

                            <el-col>

                                <!--旅业-start-->
                                <el-col :span="8">
                                    <el-form-item label="等级" v-show="containStr(detailForm.gxhylx,placeEnum.LGY.value)">
                                        <span v-text="dictLabel(dictLevelData,detailForm.dj) "></span>
                                    </el-form-item>
                                </el-col>

                                <el-col :span="8" v-show="containStr(detailForm.gxhylx,placeEnum.LGY.value)">
                                    <el-form-item label="星级">
                                        <span v-text="dictLabel(dictStarData,detailForm.xj)"></span>
                                    </el-form-item>
                                </el-col>
                                <!--旅业-end-->



                            </el-col>
                            <el-col>
                                <el-col :span="8" v-show="containStr(detailForm.gxhylx,placeEnum.KSY.value)">
                                    <!--开锁业-->
                                    <el-form-item label="开锁工艺">
                                        <span v-text="dictLabel(dictOpenLockData,detailForm.ksgy) "></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8" v-show="containStr(detailForm.gxhylx,placeEnum.KZZ.value)">
                                    <!--刻字业-->
                                    <el-form-item label="刻字方式">
                                        <span v-text="dictLabel(dictLetteringTypeData,detailForm.kzfs) "></span>
                                    </el-form-item>
                                </el-col>
                                <!--<el-col :span="8">
                                    <el-form-item label="街路巷">
                                        <span v-text="detailForm.jlx"></span>
                                    </el-form-item>
                                </el-col>-->
                                <el-col :span="8">
                                    <el-form-item label="门楼牌">
                                        <span v-text="detailForm.mlp"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="详细地址">
                                        <span v-text="detailForm.xxdz"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>




                            <el-col>
                                <el-col :span="8">

                                        <el-form-item label="经营地市省市(区县)">
                                            <span v-text="dictLabel(dictCityData,detailForm.dwszdxzqh)"></span>
                                        </el-form-item>

                                </el-col>
                                <el-col :span="16">
                                    <el-form-item label="经营地址">
                                        <span v-text="detailForm.jydz"></span>
                                        <a href="#"><i class="el-icon-location-outline"></i></a>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                        </el-form>
                    </el-col>
                    <el-col :span="6" class="form-body-r">
                        <viewer >
                        <img src="/img/unit.jpg" style="border-radius: 5px;" id="placeBaseImg" alt="">
                        </viewer>
                    </el-col>
                </el-row>
            </el-form>
            <!--卡片切换-->
            <el-tabs type="border-card" class="card-tab" v-model="tabActiveName">
                <el-tab-pane name="warnTab" label="异常警告">
                   <warn-info-tag :param="warnInfoParams"></warn-info-tag>
                </el-tab-pane>
                <el-tab-pane label="负责人">
                    <el-table
                            :header-cell-style="{background:'#eee',color:'#000'}"

                            :row-style="{height:'40px'}"
                            :data="dutyData"
                            style="width: 100%;padding: 0px!important;">
                        <el-table-column
                                prop="fzr"
                                align="center"
                                width="100"
                                label="">
                        </el-table-column>
                        <el-table-column
                                width="200"
                                prop="name"
                                label="姓名">

                        </el-table-column>
                        <el-table-column
                                prop="idType"
                                label="证件种类">
                        </el-table-column>
                        <el-table-column
                                prop="idCard"
                                min-width="120"
                                label="证件号码">
                        </el-table-column>
                        <el-table-column
                                prop="phone"
                                label="联系电话">
                        </el-table-column>

                    </el-table>
                </el-tab-pane>
                <el-tab-pane label="规模设施">
                    <el-form label-width="135px" label-position="left">
                        <el-row>
                            <el-col>
                                <el-col :span="6">
                                    <el-form-item label="面积(平方米)">
                                        <span v-text="detailForm.mj"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="注册资金(万元)">
                                        <span v-text="detailForm.zczj"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="技防设施">
                                        <span v-text="dictLabel(dictTechControlData, detailForm.jfss)"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="经营人员数">
                                        <span v-text="detailForm.zzjyrsl"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col v-show="containStr(detailForm.gxhylx,placeEnum.LGY.value)">
                                <!--旅馆业-->
                                <el-col >
                                    <el-col :span="6">
                                        <el-form-item label="楼层数">
                                            <span v-text="detailForm.lcs"></span>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="房间数">
                                            <span v-text="detailForm.fjs"></span>
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="6">
                                        <el-form-item label="床位数">
                                            <span v-text="detailForm.cws"></span>
                                        </el-form-item>
                                    </el-col>
                                </el-col>
                            </el-col>
                            <el-col  v-show="containStr(detailForm.gxhylx,placeEnum.GWYLCS.value)">
                               <!-- 歌舞娱乐场所-->
                                <el-col :span="6">
                                    <el-form-item label="舞池核定人数">
                                        <span v-text="detailForm.wchdrs"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="舞池面积">
                                        <span v-text="detailForm.wcmj"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="厢房数量">
                                        <span v-text="detailForm.xfsl"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">

                                    <el-form-item label="总核定人数">
                                        <span v-text="detailForm.zhdrs"></span>
                                    </el-form-item>

                                </el-col>
                            </el-col>

                            <el-col>

                                <el-col :span="6">
                                    <el-form-item label="保安人员总数">
                                        <span v-text="detailForm.baryzs"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="保安人员来源">
                                        <span v-text="dictLabel(dictBaSourceData,detailForm.baly)"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item  label-width="160px" label="保安组织负责人">
                                        <span v-text="detailForm.bazzfzrxm"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="保安组织联系电话">
                                        <span v-text="detailForm.bazzlxdf"></span>
                                    </el-form-item>
                                </el-col>


                            </el-col>



                            <el-col>
                                <el-col :span="6">
                                    <el-form-item label="保安派驻单位">
                                        <span v-text="detailForm.bapzdw"></span>
                                    </el-form-item>
                                </el-col>
                           <!--
                            <el-col :span="6">
                                    <el-form-item label="安全员数量">
                                        <span v-text="detailForm.aqysl"></span>
                                    </el-form-item>
                                </el-col>

                                <el-col :span="6">

                                    <el-form-item label="经营人员数量(兼职)">
                                        <span v-text="detailForm.jzjyrsl"></span>
                                    </el-form-item>

                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="培训上岗人数">
                                        <span v-text="detailForm.pxsgrs"></span>
                                    </el-form-item>
                                </el-col>-->
                            </el-col>



                        </el-row>
                    </el-form>

                </el-tab-pane>
                <el-tab-pane label="上网服务"  v-if="containStr(detailForm.gxhylx,placeEnum.LGY.value,placeEnum.GWYLCS.value,placeEnum.SWCS.value)">
                    <el-form label-width="135px" label-position="left">
                    <el-row>
                        <el-col v-show="containStr(detailForm.gxhylx,placeEnum.LGY.value,placeEnum.GWYLCS.value)">
                            <el-col :span="8">
                                <el-form-item label="是否提供上网服务">
                                    <span v-text="dictLabel(dictYesNoData,detailForm.sftgswfw)"></span>
                                </el-form-item>
                            </el-col>

                            <el-col :span="8">
                                <el-form-item label="是否提供上网电脑">
                                    <span v-text="dictLabel(dictYesNoData,detailForm.sftgswdn) "></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8" >
                                <el-form-item label-width="160px" label="是否安装网络安全设备">
                                    <span v-text="dictLabel(dictYesNoData,detailForm.sftgaqsb)"></span>
                                </el-form-item>
                            </el-col>
                        </el-col>

                        <el-col  v-show="containStr(detailForm.gxhylx,placeEnum.LGY.value,placeEnum.GWYLCS.value,placeEnum.SWCS.value)">
                            <el-col :span="8">
                                <el-form-item label="上网电脑数量">
                                    <span v-text="detailForm.swdnsl==null?'0':detailForm.swdnsl"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="上网方式">
                                    <span v-text="dictLabel(dictOnlineTypeData,detailForm.swfs) "></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="上网接口数量">
                                    <span v-text="detailForm.swjksl==null?'0':detailForm.swjksl"></span>
                                </el-form-item>
                            </el-col>
                        </el-col>

                        <el-col  v-show="containStr(detailForm.gxhylx,placeEnum.LGY.value,placeEnum.GWYLCS.value,placeEnum.SWCS.value)">
                            <el-col :span="8">
                                <el-form-item label="网络接入提供商" >
                                    <span v-text="detailForm.wljrtgs"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="IP地址(上网帐号)">
                                    <span v-text="detailForm.swzh"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                            <el-form-item label="网管负责人">
                                <span v-text="detailForm.wgfzrxm"></span>
                            </el-form-item>
                        </el-col>

                        </el-col>


                        <el-col v-show="containStr(detailForm.gxhylx,placeEnum.SWCS.value)">
                            <el-col :span="8">
                                <el-form-item label="网管软件名称">
                                    <span v-text="detailForm.wgrjmc"></span>
                                </el-form-item>
                            </el-col>

                            <el-col :span="8">
                                <el-form-item label="网管软件版本">
                                    <span v-text="detailForm.wgrjbb"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">

                                <el-form-item label="网管软件开发单位">
                                    <span v-text="detailForm.wgrjkfdw"></span>
                                </el-form-item>

                            </el-col>

                        </el-col>



                        <el-col  v-show="containStr(detailForm.gxhylx,placeEnum.SWCS.value)">
                            <el-col :span="8">
                                <el-form-item label="服务器数量">
                                    <span v-text="detailForm.fwqsl==null?'0':detailForm.fwqsl"></span>
                                </el-form-item>

                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="技术委托单位">
                                    <span v-text="detailForm.jswtdwmc"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="技术委托单位电话">
                                    <span v-text="detailForm.jswtdwdh"></span>
                                </el-form-item>
                            </el-col>
                        </el-col>
                            <el-col v-show="containStr(detailForm.gxhylx,placeEnum.SWCS.value)">
                                <el-col :span="8">

                                    <el-form-item label="技术委托单位地址">
                                        <span v-text="detailForm.jswtdwdz"></span>
                                    </el-form-item>

                                </el-col>
                                <el-col :span="16">
                                    <el-form-item label="是否记录网吧内IP地址分配情况" label-width="250px">
                                        <span v-text="detailForm.sfjldzfp"></span>
                                    </el-form-item>
                                </el-col>



                            </el-col>

                    </el-row>
                    </el-form>

                </el-tab-pane>
               <!-- <el-tab-pane label="平面图"> <img src="/img/unit.jpg" id="placePlanImg" style="width: 200px;height: 200px;border-radius: 5px;" alt=""></el-tab-pane>-->
            </el-tabs>
            <!--在岗人数列表-->
            <div class="table-box">
                <el-row class="table-box-top">
                    <el-col :span="4" class="box-top-item1">在岗人数 <span v-text="page.total">0</span></el-col>
                    <el-col :span="4" class="box-top-item2">本月新上岗 <span v-text="countNum.inc"
                                                                        v-loading="countLoading"></span>
                        <span>人</span><i
                                class="iconfontlogo aljiang1"></i></el-col>
                    <el-col :span="4" class="box-top-item3">本月离岗 <span v-text="countNum.quit"
                                                                       v-loading="countLoading"></span><span>人</span><i
                            class="iconfontlogo aljiang"></i></el-col>
                    <el-col :span="12" class="box-top-item4">
                        <el-button type="text" @click="moreClick(1)">更多>></el-button>
                    </el-col>
                </el-row>
                <el-table
                        :header-cell-style="{background:'#eee',color:'#000'}"
                        :cell-style="cellStyle"
                        :row-style="{height:'90px'}"
                        :data="employeeJzData"
                        :page="page"
                        v-loading="tableLoading"
                        style="width: 100%">
                    <el-table-column
                            width="50"
                            type="index"
                            align="center"
                            label="序号">
                    </el-table-column>
                    <el-table-column
                            prop="imgUrl"
                            label="照片"
                            align="center"
                            width="100">
                        <template slot-scope="scope">
                            <viewer >
                            <img style="height:80px; width:80px;border-radius: 5px;"
                                 :id="scope.row.systemId"
                                 :onload="initImg(scope.row.systemId)"
                               />
                            </viewer>
                        </template>
                    </el-table-column>

                    <el-table-column
                            prop="xm"
                            label="姓名">
                    </el-table-column>
                    <el-table-column
                            prop="zjhm"
                            width="200"
                            label="证件号码">
                        <template slot-scope="scope">
                            <button type="text" class="el-button el-button--text el-button--small"
                                    @click="viewEmployeeDialog(scope.row)">
                                {{scope.row.zjhm}}
                            </button>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="xb"
                            :formatter ="sexDict"
                            label="姓别">
                    </el-table-column>
                    <el-table-column
                            prop="csrq"
                            min-width="120"
                            label="出生日期">
                    </el-table-column>
                    <el-table-column
                            prop="gzgwmc"
                            label="岗位类型">
                    </el-table-column>
                    <el-table-column
                            prop="sgrq"
                            min-width="120"
                            label="上岗日期">
                    </el-table-column>
                    <el-table-column
                            width="200"
                            prop="tagType"
                            label="七类人员类型">
                        <template slot-scope="scope">
                            <div>
                                <el-tag
                                        v-for=" (tag,index) in findLabelByValues(tagDictData,scope.row.tagType,'无',true)"

                                        v-bind:key="Math.random()+'_'+index"
                                        color="#fff"
                                        size="mini"
                                >{{tag}}</el-tag>
                            </div>
                        </template>
                    </el-table-column>
                </el-table>
                <!--从业人员详情弹窗-->
                <el-dialog title="详情" :visible.sync="employeeView" fullscreen :append-to-body="true" @close="closeDialog">
                    <employee-jz-detail v-bind:detail-row="employeeDetail"/>
                    <div slot="footer"
                         class="dialog-footer">
                        <el-button type="primary" @click="closeDialog">关 闭
                        </el-button>
                    </div>
                </el-dialog>

            </div>

            <!--涉案场所切换表-->
            <div class="table-place">
                <el-row  class="table-place-body">
                    <div class="table-place-item">
                        <!--tab-->
                        <el-tabs tabPosition="left"
                                 type="border-card">
                            <el-tab-pane>
                                <el-row slot="label" class="place-tab-body">
                                    <el-col :span="11"
                                            class="place-tab-body-icon">
                                        <i class="iconfontlogo alchangsuo"></i>
                                    </el-col>
                                    <el-col :span="13"
                                            class="place-tab-body-content">
                                        <h5>涉案信息</h5>
                                        <p>100</p>
                                    </el-col>
                                </el-row>
                                <div class="place-body">
                                    <el-table
                                            :header-cell-style="{background:'#eee',color:'#000'}"
                                            :data="snxxData"
                                            :cell-style="snxxStyle"
                                            style="width: 100%">
                                        <el-table-column
                                                fixed
                                                width="200"
                                                prop="ajbh"
                                                label="案件编号">
                                            <template slot-scope="scope">
                                                <a :href="'http://'+scope.row.ajbh"
                                                   target="_blank"
                                                   class="buttonText">{{scope.row.ajbh}}
                                                </a>
                                            </template>
                                        </el-table-column>
                                        <el-table-column
                                                prop="ajmc"
                                                width="150"
                                                label="案件名称">
                                        </el-table-column>
                                        <el-table-column
                                                prop="ab"
                                                width="150"
                                                label="案别">
                                        </el-table-column>
                                        <el-table-column
                                                prop="ajlx"
                                                width="100"
                                                label="案件类型">
                                        </el-table-column>
                                        <el-table-column
                                                prop="ajzt"
                                                width="100"
                                                label="案件状态">
                                        </el-table-column>
                                        <el-table-column
                                                prop="fakssj"
                                                width="200"
                                                label="发案开始时间">
                                        </el-table-column>
                                        <el-table-column
                                                prop="sldw"
                                                min-width="150"
                                                label="受理单位">
                                        </el-table-column>
                                    </el-table>
                                </div>
                            </el-tab-pane>
                            <el-tab-pane>
                                <el-row slot="label" class="place-tab-body">
                                    <el-col :span="11"
                                            class="place-tab-body-icon">
                                        <i class="iconfontlogo alshijian"></i>
                                    </el-col>
                                    <el-col :span="13"
                                            class="place-tab-body-content">
                                        <h5>事件</h5>
                                        <p>100</p>
                                    </el-col>
                                </el-row>
                                <div class="place-body">
                                    <el-table
                                            :header-cell-style="{background:'#eee',color:'#000'}"
                                            :data="snxxData"
                                            :cell-style="snxxStyle"
                                            style="width: 100%">
                                        <el-table-column
                                                fixed
                                                width="200"
                                                prop="ajbh"
                                                label="案件编号">
                                            <template slot-scope="scope">
                                                <a :href="'http://'+scope.row.ajbh"
                                                   target="_blank"
                                                   class="buttonText">{{scope.row.ajbh}}
                                                </a>
                                            </template>
                                        </el-table-column>
                                        <el-table-column
                                                prop="ajmc"
                                                width="150"
                                                label="案件名称">
                                        </el-table-column>
                                        <el-table-column
                                                prop="ab"
                                                width="150"
                                                label="案别">
                                        </el-table-column>
                                        <el-table-column
                                                prop="ajlx"
                                                width="100"
                                                label="案件类型">
                                        </el-table-column>
                                        <el-table-column
                                                prop="ajzt"
                                                width="100"
                                                label="案件状态">
                                        </el-table-column>
                                        <el-table-column
                                                prop="fakssj"
                                                width="200"
                                                label="发案开始时间">
                                        </el-table-column>
                                        <el-table-column
                                                prop="sldw"
                                                width="150"
                                                label="受理单位">
                                        </el-table-column>
                                    </el-table>
                                </div>
                            </el-tab-pane>
                            <el-tab-pane>
                                <el-row slot="label" class="place-tab-body">
                                    <el-col :span="11"
                                            class="place-tab-body-icon">
                                        <i class="iconfontlogo aljiejing"></i>
                                    </el-col>
                                    <el-col :span="13"
                                            class="place-tab-body-content">
                                        <h5>110接警处</h5>
                                        <p>100</p>
                                    </el-col>
                                </el-row>
                                <div class="place-body">
                                    <el-table
                                            :header-cell-style="{background:'#eee',color:'#000'}"
                                            :data="snxxData"
                                            :cell-style="snxxStyle"
                                            style="width: 100%">
                                        <el-table-column
                                                fixed
                                                width="200"
                                                prop="ajbh"
                                                label="案件编号">
                                            <template slot-scope="scope">
                                                <a :href="'http://'+scope.row.ajbh"
                                                   target="_blank"
                                                   class="buttonText">{{scope.row.ajbh}}
                                                </a>
                                            </template>
                                        </el-table-column>
                                        <el-table-column
                                                prop="ajmc"
                                                width="150"
                                                label="案件名称">
                                        </el-table-column>
                                        <el-table-column
                                                prop="ab"
                                                width="150"
                                                label="案别">
                                        </el-table-column>
                                        <el-table-column
                                                prop="ajlx"
                                                width="100"
                                                label="案件类型">
                                        </el-table-column>
                                        <el-table-column
                                                prop="ajzt"
                                                width="100"
                                                label="案件状态">
                                        </el-table-column>
                                        <el-table-column
                                                prop="fakssj"
                                                width="200"
                                                label="发案开始时间">
                                        </el-table-column>
                                        <el-table-column
                                                prop="sldw"
                                                width="150"
                                                label="受理单位">
                                        </el-table-column>
                                    </el-table>
                                </div>
                            </el-tab-pane>
                        </el-tabs>
                    </div>
                    <!--点击更多-->
                    <div class="table-place-more">
                        <el-button type="text" @click="dialogTablePlaceVisible = true">
                            <p>更</p>
                            <p>多</p>
                            <p><i class="iconfontlogo almore"></i>
                            </p>
                        </el-button>
                    </div>
                </el-row>
            </div>
            <!--人脸识别-->
            <div class="table-face">
                <div class="table-face-top">
                    <el-row>
                        <el-col :span="20" class="face-top-title">
                            人脸识别
                        </el-col>
                        <el-col  :span="4"  class="face-top-more">
                            <el-button type="text" @click="dialogTablePlaceVisible = true">更多>></el-button>
                        </el-col>
                    </el-row>
                </div>
                <el-table
                        class="table-face-body"
                        :header-cell-style="{background:'#eee',color:'#000'}"
                        :cell-style="facecellStyle"
                        :data="employeeJzData"
                        style="width: 100%">
                    <el-table-column
                            width="50"
                            prop="id"
                            label="序号">
                    </el-table-column>
                    <el-table-column
                            prop="name"
                            label="姓名">
                    </el-table-column>
                    <el-table-column
                            prop="zjhm"
                            width="200"
                            label="证件号码">
                        <template slot-scope="scope">
                            <el-button type="text" >{{scope.row.zjhm}}</el-button>

                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="sex"
                            width="70"
                            label="姓别">
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            min-width="120"
                            label="出生日期">
                    </el-table-column>
                    <el-table-column
                            prop="jobdate"
                            min-width="120"
                            label="出现时间">
                    </el-table-column>
                    <el-table-column
                            width="250"
                            prop="persontype"
                            label="七类人员类型">
                        <template slot-scope="scope">
                            <div>
                                <el-tag
                                        v-for="tag in scope.row.persontype"
                                        :key="tag.name"
                                        color="#fff"
                                        size="mini"
                                >{{tag.name}}</el-tag>
                            </div>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <!--地图区域-->
            <div class="map table-face">
                <div class="table-face-top">
                    <el-row>
                        <el-col :span="20" class="face-top-title">
                            地图区域
                        </el-col>
                    </el-row>
                </div>
                <div class="map-box" style="position: relative">
                    <div id="fmap"></div>
                    <!--按钮-->
                    <div class="programme-desc">
                        <div  @click="addVideoLayer"><div class="desc_button desc_button1" @click="Change(1)" :class="{'btn_actives':select==1}">视频摄像头</div></div>
                        <div  @click="addPlaceLayer"><div class="desc_button desc_button2" @click="Change(2)" :class="{'btn_actives':select==2}">特行场所</div></div>
                    </div>
                    <!--列表-->
                    <div class="address_list">
                        <div class="address_list_body">
                            <div class="address_list_item" v-for="item in address_list">
                                <div class="address_list_title"><i class="iconfontlogo alchangsuo"></i>{{item.name}}</div>
                                <div class="address_list_content">{{item.address}}</div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!--弹框-->
        <el-dialog title="在岗人数"
                   width="80%"
                   :visible.sync="dialogTableVisible">
            <el-table
                    max-height="450"
                    :header-cell-style="{background:'#eee',color:'#000'}"
                    :cell-style="cellStyle"
                    :row-style="{height:'90px'}"
                    :data="tableData"
                    style="width: 100%">
                <el-table-column
                        fixed
                        prop="imgUrl"
                        height="100px"
                        width="80">
                    <template slot-scope="scope">
                        <img :src="scope.row.imgUrl" />
                    </template>
                </el-table-column>
                <el-table-column
                        prop="id"
                        label="序号">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名">
                </el-table-column>
                <el-table-column
                        prop="catenumber"
                        width="200"
                        label="证件号码">
                </el-table-column>
                <el-table-column
                        prop="sex"
                        label="姓别">
                </el-table-column>
                <el-table-column
                        prop="date"
                        min-width="120"
                        label="出生日期">
                </el-table-column>
                <el-table-column
                        prop="job"
                        label="岗位类型">
                </el-table-column>
                <el-table-column
                        prop="jobdate"
                        min-width="120"
                        label="上岗日期">
                </el-table-column>
                <el-table-column
                        width="200"
                        prop="persontype"
                        label="七类人员类型">
                    <template slot-scope="scope">
                        <div>
                            <el-tag
                                    v-for="tag in scope.row.persontype"
                                    :key="tag.name"
                                    color="#fff"
                                    size="mini"
                            >{{tag.name}}</el-tag>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>
        <el-dialog title=""
                   width="80%"
                   :visible.sync="dialogTablePlaceVisible">
            <el-table
                    height="350px"
                    :header-cell-style="{background:'#eee',color:'#000'}"
                    :data="tableData"
                    style="width: 100%">
                <el-table-column
                        prop="id"
                        label="案件编号">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="案件名称">
                </el-table-column>
                <el-table-column
                        prop="catenumber"
                        label="案别">
                </el-table-column>
                <el-table-column
                        prop="date"
                        label="案件类型">
                </el-table-column>
                <el-table-column
                        prop="job"
                        label="案件状态">
                </el-table-column>
                <el-table-column
                        prop="jobdate"
                        label="发案开始时间">
                </el-table-column>
                <el-table-column
                        prop="jobdate"
                        label="受理时间">
                </el-table-column>
            </el-table>
        </el-dialog>
        <el-dialog title="人脸识别"
                   width="80%"
                   :visible.sync="dialogTablePlaceVisible">
            <el-table
                    class="table-face-body"
                    :header-cell-style="{background:'#eee',color:'#000'}"
                    :cell-style="facecellStyle"
                    :data="tableData"
                    style="width: 100%">
                <el-table-column
                        width="50"
                        prop="id"
                        label="序号">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名">
                </el-table-column>
                <el-table-column
                        prop="catenumber"
                        width="200"
                        label="证件号码">
                </el-table-column>
                <el-table-column
                        prop="sex"
                        width="70"
                        label="姓别">
                </el-table-column>
                <el-table-column
                        prop="date"
                        min-width="120"
                        label="出生日期">
                </el-table-column>
                <el-table-column
                        prop="job"
                        label="人员类型">
                </el-table-column>
                <el-table-column
                        prop="jobdate"
                        min-width="120"
                        label="出现时间">
                </el-table-column>
                <el-table-column
                        width="200"
                        prop="persontype"
                        label="七类人员类型">
                    <template slot-scope="scope">
                        <div>
                            <el-tag
                                    v-for="tag in scope.row.persontype"
                                    :key="tag.name"
                                    color="#fff"
                                    size="mini"
                            >{{tag.name}}</el-tag>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>
        <!--视频弹框-->
        <el-dialog
                width="90%"
                :visible.sync="dialogVideoVisible">
            <div>
                <video id="my_video"
                       src="http://68.26.21.53:58088/fmap-example/examples/datas/video/h1_video.mp4"
                       autoplay>
                </video>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import vueBubble from "./vue-bubble.vue"
    let ocrParametersList= [
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4591&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "PGIS矢量地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4592&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "PGIS影像地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4593&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "PGIS矢影地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4594&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "SMAP矢量地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4595&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "SMAP影像地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4596&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "SMAP矢影地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4597&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "SMAP午夜蓝地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4598&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "CMAP矢量地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4599&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "CMAP影像地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4600&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "CMAP矢影地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4601&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "AMAP矢量地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4602&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "AMAP影像地图"
        },
        {
            "applyMapUrl": "http://68.26.21.93:8084/services/synframe/synframe/map/getMapImage.do?mapId=4603&keyinfo=D00ha6ht8xapapL3WRlFAWUYddJgm3t7birhzMMHGWcza5iHQXpNRsK0EX9UlCKNejdTuOaurTbYuy1AcYl71Aaa&l={z}&x={y}&y={x}",
            "mapName": "AMAP矢影地图"
        }
    ];
    let imgUrl="/cdn/fmap/img/video.png";

    import {findOnePlace} from "@/api/portal/archive/jingz/place";
    import {findEmployeeJzPage,employeeMonthCount} from "@/api/portal/archive/jingz/employeeJz";
    import {getDict,getLabel}  from  "@/api/portal/archive/dict/dict";
    import {remote}  from  "@/api/admin/dict";
    import {findDictArea}  from  "@/api/portal/archive/common/query";
    import  {placeTypeEnum} from  "@/const/crud/portal/archive/commom/constant";
    import  {containStr} from  "@/const/crud/portal/archive/commom/strUtil";
    import { validatenull } from "@/util/validate";

    import  employeeJzDetail from '../employee/employeeJzDetail'
    import  placeCollect from '@/views/portal/archive/mechanical/fileInfo/cate/Csgz'
    import {getImg} from "@/api/portal/archive/jingz/placeFile"
    import '@/styles/portal/archive/place-detail.scss?v=110'
    import  warnInfoTag from '@/views/portal/judged/warn/warnInfoTag'
    export default {
        name: 'index',
        components:{
            vueBubble:vueBubble,
            employeeJzDetail:employeeJzDetail,
            placeCollect:placeCollect,
            warnInfoTag:warnInfoTag
        },
        props: ["paramForm"],
        data () {
            return{
                searchForm: {
                    dwbh: undefined,
                    menuId: undefined,
                    lyxt:'0'
                },
                warnInfoParams: {
                    placeId: undefined,
                    sourceType: '0',
                    modelId: undefined
                },
                imgMap : new Map(),
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 5 // 每页显示多少条
                },
                employeeJzData:[],
                countNum: {
                    quit: 0,
                    inc: 0
                },
                placeEnum:placeTypeEnum,
                containStr:containStr,
                tabActiveName:'warnTab',
                select:2,
                employeeDetail:{},
                employeeView: false,
                dialogTableVisible: false,
                dialogTablePlaceVisible:false,
                dialogVideoVisible:false,
                fmap:null,
                tableLoading:true,
                detailLoading:true,
                countLoading:false,
                detailForm:{},
                tagDictData:[],
                dutyData:[
                    {
                        fzr:'法定',
                        name:undefined,
                        idCard:undefined,
                        idType:undefined,
                        phone:undefined,
                        telPhone:undefined
                    },
                    {
                        fzr:'经营',
                        name:undefined,
                        idCard:undefined,
                        idType:undefined,
                        phone:undefined,
                        telPhone:undefined
                    },
                    /* {
                        fzr:'消防',
                        name:undefined,
                        idCard:undefined,
                        idType:undefined,
                        phone:undefined,
                        telPhone:undefined
                    },
                    {
                        fzr:'治安保卫',
                        name:undefined,
                        idCard:undefined,
                        idType:undefined,
                        phone:undefined,
                        telPhone:undefined
                    },
                   {
                        fzr:'网络管理',
                        name:undefined,
                        idCard:undefined,
                        idType:undefined,
                        phone:undefined,
                        telPhone:undefined
                    },*/
                ],
                pointTypeData:[],
                imgUrl:imgUrl,
                center:{
                    lon:113.27817764,
                    lat:23.13744564,
                    zoom: 12,
                    minZoom: 2,
                    maxZoom: 21
                },
                layers:null,
                videoData:{
                    bianhao: "1234343545432",
                    mingcheng: "广东省清远市佛冈县龙山镇泚镇村委会泚镇管理区淽镇村176号",
                    dizhi:"广东省广州市越秀区黄花岗街水荫南社区天河路45号之一十三",
                    ip:"172.17.21.2",
                    xzuobiao:"113.58137083",
                    yzuobiao:"24.58137083",
                },
                tableData:[],
                snxxData: [
                    {
                        ajbh:'AJ12345678901234567',
                        ajmc: '"1021"打击行动',
                        ab:'危害国家安全案',
                        ajlx:'形式案件',
                        ajzt: '结案',
                        fakssj:'2016-05-02 12:11:00',
                        sldw:'虎门派出所'
                    },
                    {
                        ajbh:'AJ12345678901234567',
                        ajmc: '"1021"打击行动',
                        ab:'危害国家安全案',
                        ajlx:'形式案件',
                        ajzt: '结案',
                        fakssj:'2016-05-02 12:11:00',
                        sldw:'虎门派出所'
                    },
                    {
                        ajbh:'AJ12345678901234567',
                        ajmc: '"1021"打击行动',
                        ab:'危害国家安全案',
                        ajlx:'形式案件',
                        ajzt: '结案',
                        fakssj:'2016-05-02 12:11:00',
                        sldw:'虎门派出所'
                    },
                    {
                        ajbh:'AJ12345678901234567',
                        ajmc: '"1021"打击行动',
                        ab:'危害国家安全案',
                        ajlx:'形式案件',
                        ajzt: '结案',
                        fakssj:'2016-05-02 12:11:00',
                        sldw:'虎门派出所'
                    },
                    {
                        ajbh:'AJ12345678901234567',
                        ajmc: '"1021"打击行动',
                        ab:'危害国家安全案',
                        ajlx:'形式案件',
                        ajzt: '结案',
                        fakssj:'2016-05-02 12:11:00',
                        sldw:'虎门派出所'
                    } ],
                address_list:[
                    {
                        name:"江门市七天酒店",
                        address:"广东省江门市蓬江区江门市胜利路28号"
                    },
                    {
                        name:"江门市七天酒店",
                        address:"广东省江门市蓬江区江门市胜利路28号广东省江门市蓬江区江门市胜利路28号"
                    },
                    {
                        name:"江门市七天酒店",
                        address:"广东省江门市蓬江区江门市胜利路28号"
                    },
                    {
                        name:"江门市七天酒店",
                        address:"广东省江门市蓬江区江门市胜利路28号"
                    },
                    {
                        name:"江门市七天酒店",
                        address:"广东省江门市蓬江区江门市胜利路28号"
                    },
                    {
                        name:"江门市七天酒店",
                        address:"广东省江门市蓬江区江门市胜利路28号"
                    },
                    {
                        name:"江门市七天酒店",
                        address:"广东省江门市蓬江区江门市胜利路28号"
                    },
                    {
                        name:"江门市七天酒店",
                        address:"广东省江门市蓬江区江门市胜利路28号"
                    },
                ],
                dictNatureData:[] ,//企业性质字典数据
                dictUnitTypeData:[],//单位类别字典数据
                dictTradeTypeData:[],//行业类别字典数据
                dictBusinessStatusData:[], //企业经营状态字典数据
                dictDeptData:[],//机构代码字典数据
                dictCityData:[],//行政区划字典数据
                dictIdCardTypeData:[],//证件种类字典数据
                dictOnlineTypeData:[],//上网方式字典数据
                dictStarData:[],//旅业星级字典数据
                dictLevelData:[],//旅业等级字典数据
                dictTechControlData:[],//技术防控字典数据
                dictOpenLockData:[],//开锁业-开锁工艺字典数
                dictLetteringTypeData:[],//刻字业-刻字方式
                dictBaSourceData:[],//保安来源
                dictSexData:[],//从业人员性别
                dictYesNoData:[{
                    label:'是',
                    value:'1'
                },{label:'否', value:'0'}]
            }
        },
        mounted(){
           /* this.MapInit();*/
        },
        created(){
            this.placeDictData()
            this.searchForm.dwbh = this.paramForm.unitId;
            this.searchForm.menuId = this.paramForm.menuId;
            this.warnInfoParams.placeId=this.paramForm.unitId;
            this.findPlaceDetail(this.searchForm);
            this.getTagDictData()

        },
        watch: {
            paramForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.searchForm.dwbh = this.paramForm.unitId;
                    this.searchForm.menuId = this.paramForm.menuId;
                    this.warnInfoParams.placeId=this.paramForm.unitId;
                    this. tabActiveName='warnTab'
                    this.findPlaceDetail(this.searchForm);
                },
                deep: true
            },
        },
        methods: {
            getTagDictData(){
                remote('QLRYLX').then(response => {
                    this.tagDictData = response.data.data||{}
                }).catch((err) => {});
            },

            initImg(systemId) {
                let img=this.imgMap.get(systemId);
                if('undefined'==typeof (img)||''===img||null===img) {
                    getImg(Object.assign({ 'ywbbh':systemId,'photoName':'1'}, {}),'',false).then(value => {
                        this.imgMap.set(systemId,value);
                        let imgTag =document.getElementById(systemId);
                        imgTag.src=value
                    })
                }else{
                    let imgTag =document.getElementById(systemId);
                    imgTag.src=img
                }
            },

            findEmployeePage(zbdh){
                this.imgMap.clear()
                this.employeeJzData=[] //必须先清空数据、不然会把上一次的旧数据重复请求图片
                this.tableLoading = true
                findEmployeeJzPage(Object.assign({
                    current: this.page.currentPage,
                    size: this.page.pageSize,
                }, { ryzt:'1',
                    zbbh: zbdh})).then(response => {
                    this.employeeJzData = response.data.data.records
                    this.page.total = response.data.data.total
                    if(this.page.total>0){
                       this.employeeDictData()
                    }
                    this.tableLoading = false
                    this.employeeCount(zbdh)
                }).catch((err) => {
                    this.tableLoading = false
                });
            },

            employeeCount(unitId) {
                this.countLoading = true
                employeeMonthCount(unitId).then(response => {
                    let arr = response.data.data
                    if (validatenull(arr)) {
                        this.countNum.inc = 0
                        this.countNum.quit = 0
                    } else {
                        this.countNum = arr
                    }
                    this.countLoading = false
                }).catch((err) => {
                    this.countNum.inc = 0
                    this.countNum.quit = 0
                    this.countLoading = false
                });
            },
            findPlaceDetail(form) {
                this.detailLoading=true
                findOnePlace(Object.assign({}, form)).then(response => {
                    this.detailForm = response.data.data;
                    this.findEmployeePage( this.detailForm.systemId)

                    this.dutyData[0].name=validatenull(this.detailForm.frdb)?'无':this.detailForm.frdb
                    const idTypeOne=this.dictLabel(this.dictIdCardTypeData,this.detailForm.reservation14);
                    this.dutyData[0].idType=validatenull(idTypeOne)?'-':idTypeOne
                    this.dutyData[0].idCard=validatenull(this.detailForm.reservation15)?'-':this.detailForm.reservation15
                    this.dutyData[0].phone= validatenull(this.detailForm.reservation16)?'-':this.detailForm.reservation16

                    this.dutyData[1].name=validatenull(this.detailForm.sjjyrxm)?'无':this.detailForm.sjjyrxm
                    const idTypeTwo=this.dictLabel(this.dictIdCardTypeData,this.detailForm.reservation07);
                    this.dutyData[1].idType=validatenull(idTypeTwo)?'-':idTypeTwo
                    this.dutyData[1].idCard=validatenull(this.detailForm.reservation08)?'-':this.detailForm.reservation08
                    this.dutyData[1].phone= validatenull(this.detailForm.reservation09)?'-':this.detailForm.reservation09

                    /*  this.dutyData[2].name=this.detailForm.reservation28
                     this.dutyData[2].idType=''
                     this.dutyData[2].idCard=''
                     this.dutyData[2].phone=''
                     this.dutyData[2].telPhone= this.detailForm.reservation29

                     this.dutyData[3].name=''
                     this.dutyData[3].idType=this.detailForm.reservation14
                     this.dutyData[3].idCard=this.detailForm.reservation15
                     this.dutyData[3].phone=''
                     this.dutyData[3].telPhone= this.detailForm.reservation20

                    this.dutyData[4].name=this.detailForm.wgfzrxm
                     this.dutyData[4].idType=''
                     this.dutyData[4].idCard=this.detailForm.wgzjhm
                     this.dutyData[4].phone=''
                     this.dutyData[4].telPhone= this.detailForm.wglxdh*/
                    this.detailLoading=false
                    getImg(Object.assign({ 'ywbbh':this.detailForm.systemId,'photoName':'1'}, {}),"placeBaseImg",true)
               /*     getImg(Object.assign({ 'zbbh':this.detailForm.systemId,'fjmc':'内部,平面'}, {}),"placePlanImg")*/
                }).catch((err) => {this.detailLoading=false});
            },


            pointDict(){
                return getLabel( this.pointTypeData, this.detailForm.pointType)
            },
            viewEmployeeDialog(row) {
                this.employeeView = true
                this.employeeDetail = row
            },
            closeDialog(){
                this.employeeView=false
            },
            employeeDictData(){
                getDict('01','jzxt').then(response => {
                    this.dictSexData = response.data.data||[];
                }).catch((err) => {});
            },
            sexDict(row, column){
                return getLabel(this.dictSexData,row.xb)
            },

            placeDictData(){
                remote('jfdj').then(response => {
                    this.pointTypeData = response.data.data||{}
                }).catch((err) => {});
                getDict('47','jzxt').then(response => {
                    this.dictNatureData = response.data.data||[];
                }).catch((err) => {});
                getDict('za_002','jzxt').then(response => {
                    this.dictUnitTypeData = response.data.data||[];
                    this.dictTradeTypeData= response.data.data||[];
                }).catch((err) => {});
                getDict('za_003','jzxt').then(response => {
                    this.dictBusinessStatusData = response.data.data||[];
                }).catch((err) => {});
               /* getDict('06','jzxt').then(response => {
                    this.dictDeptData = response.data.data||[];
                }).catch((err) => {});*/
                getDict('wg_03','jzxt').then(response => {
                    this.dictIdCardTypeData = response.data.data||[];
                }).catch((err) => {});
                getDict('za_013','jzxt').then(response => {
                    this.dictOnlineTypeData = response.data.data||[];
                }).catch((err) => {});

                    getDict('za_004','jzxt').then(response => {
                        this.dictStarData = response.data.data||[];
                    }).catch((err) => {});
                    getDict('za_007','jzxt').then(response => {
                        this.dictLevelData = response.data.data||[];
                    }).catch((err) => {});

                getDict('za_008','jzxt').then(response => {
                    this.dictTechControlData = response.data.data||[];
                }).catch((err) => {});


                    getDict('za_006','jzxt').then(response => {
                        this.dictOpenLockData = response.data.data||[];
                    }).catch((err) => {});


                   getDict('za_011','jzxt').then(response => {
                       this.dictLetteringTypeData = response.data.data||[];
                   }).catch((err) => {});


                getDict('za_016','jzxt').then(response => {
                    this.dictBaSourceData = response.data.data||[];
                }).catch((err) => {});

                findDictArea().then(response => {
                    this.dictCityData = response.data||[]
                })

            },
            moreClick(i) {
                this.$emit("moreClick", i);
            },
            /**
             * 翻译字典
             * */
            dictLabel(dictData,val){

                let label=getLabel( dictData,val);
                return validatenull(label)?"-":label;
            },
            Change(index){
                this.select=index;
                console.log(this.select);
            },
            cellStyle({row, column, rowIndex, columnIndex}) {
                if (columnIndex === 3) {
                    return 'color:#6aaff8'
                } else {
                    return ''
                }
            },
            snxxStyle({row, column, rowIndex, columnIndex}) {
                if (columnIndex === 0) {
                    return 'color:#6aaff8'
                }else {
                    return ''
                }
            },
            facecellStyle({row, column, rowIndex, columnIndex}) {
                if (columnIndex === 2) {
                    return 'color:#6aaff8'
                } else if(columnIndex === 0){
                    return 'text-align:center'
                }else {
                    return ''
                }
            },
            //地图
            MapInit(){
                // 注册要素图元缺省配置
                FUtil.registSymbolDefaultSetting({
                    src:this.imgUrl,
                    size: [32,46]
                });
                // 注册要素图元
                FUtil.registSymbols(['video']);
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
                var layers = {
                    'testLayer': {
                        name: 'testLayer',
                        bubble: 'generalBB',
                        // bubbleable: true,
                        symbol: 'default',
                        onclick:(event)=>{
                            // alert('鼠标点击视频'+ event.feature.getId());
                        },
                        style: 'symbol:default;text:{name};text-font:Verdana 48px;text-align:left;text-baseline:bottom;text-offectY:-32;'
                    },
                    'videoLayer': {
                        name: 'videoLayer',
                        symbol: 'video',
                        onclick: (event)=>{
                            // alert('鼠标点击视频'+ event.feature.getId());
                        },
                        bubble: 'videoBB,'},
                };
                // 创建地图实例
                this.fmap = new FMap({
                    element:'fmap',
                    bglayer:bglayer,
                    center:this.center,
                    layers:layers,
                    sign:true
                });
                // 点击要素时查询要素详情信息
                this.fmap.on('fmap.feature.selected',  (event)=>{
                    var client = event.feature.getClients();
                    // console.log(client)//这里默认将client=dataObj的值
                    /* post('example/getFeatureDetial.do', {id: client.id}).done((proList)=>{
                     });*/
                });
                this.addRadomData("testLayer");
            },
            addRadomData(layer){
                var data= [
                    {
                        id:"d5601dee-6ale-4aee-9503-c93cff4c5bc6",
                        name: "广东省广州市越秀区黄花岗街水荫南社区天河路45号之一十三",
                        ACGE0: "113.3071623.1387",
                        x: 113.30716,
                        y: 23.1387
                    },
                    {
                        id: "cc080400-0101-410e-9e8b-e56d73a495ca",
                        name: "广东省广州市增城区新塘镇新墩村基尾街16号",
                        AC_GE0: "113.55402,23.1153",
                        x: 113.55402,
                        y: 23.1153
                    },

                    {
                        id: "46f54bb5-1990-4407-8d16-baoc09160044 ",
                        name: "广东省清远市佛冈县龙山镇泚镇村委会泚镇管理区淽镇村176号",
                        ACGE0: "113.4193738123.75749459",
                        x: 113.41937381,
                        y: 23.75749459
                    },
                    {
                        id: "12345678-1234-1234-8d16-baoc09160044 ",
                        name: "广东省清远市佛冈县龙山镇泚镇村委会泚镇管理区淽镇村176号",
                        ACGE0: "113.4193738123.75749459",
                        x: 113.419567,
                        y: 23.345643
                    },
                    {
                        id: "36987412-1234-1234-8d16-baoc09160044 ",
                        name: "广东省清远市佛冈县龙山镇泚镇村委会泚镇管理区淽镇村176号",
                        ACGE0: "113.4193738123.75749459",
                        x: 113.852145,
                        y: 23.876254
                    },
                ];
                this.fmap.addFeatures(data, {layer: layer});
                //var extent = this.fmap.getExtent();
                //console.log(extent);
                /*  post('example/randomFeatures.do', {count:5, extent: extent}).done((data)=>{
                      this.fmap.addFeatures(data, {layer: layer});
                  });*/
            },
            addPlaceLayer(){
                this.fmap.clearLayer("videoLayer");
                this.addRadomData("testLayer");
            },
            addVideoLayer(){
                this.fmap.clearLayer("testLayer");
                this.addRadomData("videoLayer");
            },
        },
    }
</script>

