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

        <div class="fileInfo_main">
            <!--基础信息-->
            <el-form  class="form-main">
                <el-row  class="form-main-title">
                    <span class="main-title-name"> 江门市七天酒店</span>
                    <span class="main-title-score">90分</span>
                    <span class="main-title-level main-title-score">A级</span>
                    <a href="#">
                        <span class="main-title-score main-title-shoucang"><i class="iconfontlogo alicon_shoucang"></i>已收藏</span>
                    </a>
                </el-row>
                <el-row class="form-main-body">
                    <el-col :span="18" class="form-body-l form-body-tabel">
                        <el-form label-width="135px" label-position="left">
                            <el-col>
                                <el-col :span="8">
                                        <el-form-item label="行业类别">
                                            旅馆业
                                        </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="单位类别">
                                        酒店
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="经济类型">
                                        内资
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-tooltip content="440703520000000000" placement="top" effect="light">
                                    <el-form-item label="组织机构代码">
                                        440703520000000000
                                    </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="企业状态">
                                        正常
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="开业日期">
                                        2017-01-02
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-tooltip content="开平市公安局潭江派出所" placement="top" effect="light">
                                    <el-form-item label="管辖派出所">
                                        开平市公安局潭江派出所
                                    </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="责任民警名称">
                                        李某
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="责任民警警号">
                                        523455
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="主营">
                                        铝业
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="兼营">
                                        无
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="被兼营单位">
                                        无
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="重点单位">
                                        是
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-tooltip content="2019-01-30至2019-04-30" placement="top" effect="light">
                                    <el-form-item label="经营有效时间段">
                                        2019-01-30至2019-04-30
                                    </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="联系电话">
                                        0750-8812345
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-tooltip content=" 广东省江门市蓬江区" placement="top" effect="light">
                                    <el-form-item label="经营地市省市(区县)">
                                        广东省江门市蓬江区
                                    </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="16">
                                    <el-form-item label="经营地址">
                                        广东省江门市蓬江区胜利路28号
                                        <a href="#"><i class="el-icon-location-outline"></i></a>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                        </el-form>
                    </el-col>
                    <el-col :span="6" class="form-body-r">
                        <img src="../../../../../../public/img/portal/7day1.png" alt="">
                    </el-col>
                </el-row>
            </el-form>
            <!--卡片切换-->
            <el-tabs type="border-card" class="card-tab">
                <el-tab-pane label="异常警告">
                   <ul>
                       <li>1</li>
                       <li>[治安管理]</li>
                       <li><a href="#">从业人员一个月没有变动</a></li>
                       <li>2019-05-15 11:18:00</li>
                   </ul>
                    <ul>
                        <li>1</li>
                        <li>[治安管理]</li>
                        <li><a href="#">从业人员一个月没有变动</a></li>
                        <li>2019-05-15 11:18:00</li>
                    </ul>
                    <ul>
                        <li>1</li>
                        <li>[治安管理]</li>
                        <li><a href="#">从业人员一个月没有变动</a></li>
                        <li>2019-05-15 11:18:00</li>
                    </ul>
                    <ul>
                        <li>1</li>
                        <li>[治安管理]</li>
                        <li><a href="#">从业人员一个月没有变动</a></li>
                        <li>2019-05-15 11:18:00</li>
                    </ul>
                    <ul>
                        <li>1</li>
                        <li>[治安管理]</li>
                        <li><a href=" ">从业人员一个月没有变动</a></li>
                        <li>2019-05-15 11:18:00</li>
                    </ul>
                </el-tab-pane>
                <el-tab-pane label="负责人">配置管理</el-tab-pane>
                <el-tab-pane label="规模设施">角色管理</el-tab-pane>
                <el-tab-pane label="上网服务">定时任务补偿</el-tab-pane>
                <el-tab-pane label="平面图">定时任务补偿</el-tab-pane>
            </el-tabs>
            <!--在岗人数列表-->
            <div class="table-box">
                <el-row class="table-box-top">
                    <el-col :span="4" class="box-top-item1">在岗人数 <span>1000</span></el-col>
                    <el-col :span="4" class="box-top-item2">本月新上岗  <span>1000人</span><i class="iconfontlogo aljiang1"></i></el-col>
                    <el-col :span="4" class="box-top-item3">本月离岗  <span>1000人</span><i class="iconfontlogo aljiang"></i></el-col>
                    <el-col :span="12" class="box-top-item4">
                        <el-button type="text" @click="dialogTableVisible = true">更多>></el-button>
                    </el-col>
                </el-row>
                <el-table
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
                        <template slot-scope="scope">
                            <a :href="'http://'+scope.row.catenumber"
                               target="_blank"
                               class="buttonText">{{scope.row.catenumber}}
                            </a>
                        </template>
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
                            <template slot-scope="scope">
                                <a :href="'http://'+scope.row.catenumber"
                                   target="_blank"
                                   class="buttonText">{{scope.row.catenumber}}
                                </a>
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
                        <div  @click="addPlaceLayer"><div class="desc_button desc_button2" @click="Change(2)" :class="{'btn_actives':select==2}">特性场所</div></div>
                    </div>
                    <div class="address_list">
                        <div class="address_list_body">
                            <div class="address_list_item" v-for="items in address_list">
                                <div class="address_list_title"><i class="iconfontlogo alchangsuo"></i>{{items.name}}</div>
                                <div class="address_list_content">{{items.address}}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--弹框-->
        <el-dialog title="在岗人数"
                   width="80%"
                   v-dialogDrag
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
                   v-dialogDrag
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
                   v-dialogDrag
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
    import '../dire.js'
    import vuebubble from "../vue-bubble.vue"
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
    export default {
        name: 'index',
        components:{
            vueBubble:vuebubble
        },
        data () {
            return{
                positionX: 0,
                positionY: 0,
                select:2,
                dialogTableVisible: false,
                dialogTablePlaceVisible:false,
                dialogVideoVisible:false,
                fmap:null,
                center:{
                    lon:113.27817764,
                    lat:23.13744564,
                    zoom: 12,
                    minZoom: 2,
                    maxZoom: 21
                },
                videoData:{
                    bianhao: "1234343545432",
                    mingcheng: "广东省清远市佛冈县龙山镇泚镇村委会泚镇管理区淽镇村176号",
                    dizhi:"广东省广州市越秀区黄花岗街水荫南社区天河路45号之一十三",
                    ip:"172.17.21.2",
                    xzuobiao:"113.58137083",
                    yzuobiao:"24.58137083",
                },
                tableData:[
                    {
                    imgUrl:require("../../../../../../public/img/person.png"),
                    id:'1',
                    name: '王小虎',
                    catenumber:'452126199511102128',
                    sex:'女',
                    date: '2016-05-02',
                    job:'按摩',
                    jobdate:'2016-05-02',
                    persontype: [
                        { name: '标签一' },
                        { name: '标签二' },
                        { name: '标签三' },
                    ]
                },
                    {
                    imgUrl:require("../../../../../../public/img/person.png"),
                    id:'2',
                    name: '王小虎',
                    catenumber:'452126199511102128',
                    sex:'女',
                    date: '2016-05-02',
                    job:'按摩',
                    jobdate:'2016-05-02',
                    persontype:[
                        { name: '标签一' },
                        { name: '标签二' },
                        { name: '标签三' },
                    ],
                },
                    {
                    imgUrl:require("../../../../../../public/img/person.png"),
                    id:'3',
                    name: '王小虎',
                    catenumber:'452126199511102128',
                    sex:'女',
                    date: '2016-05-02',
                    job:'按摩',
                    jobdate:'2016-05-02',
                    persontype: [
                        { name: '标签一' },
                        { name: '标签二' },
                        { name: '标签三' },
                    ]
                },
                    {
                    // imgUrl:require("../../../../../../public/img/person.png"),
                    id:'2',
                    name: '王小虎',
                    catenumber:'452126199511102128',
                    sex:'女',
                    date: '2016-05-02',
                    job:'按摩',
                    jobdate:'2016-05-02',
                    persontype:[
                        { name: '标签一' },
                        { name: '标签二' },
                        { name: '标签三' },
                    ],
                },
                    {
                    imgUrl:require("../../../../../../public/img/person.png"),
                    id:'3',
                    name: '王小虎',
                    catenumber:'452126199511102128',
                    sex:'女',
                    date: '2016-05-02',
                    job:'按摩',
                    jobdate:'2016-05-02',
                    persontype: [
                        { name: '标签一' },
                        { name: '标签二' },
                        { name: '标签三' },
                    ]
                }, ],
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
                ]
            }
        },
        mounted(){
            this.MapInit();
            //this.text();
        },
        methods: {
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
                    sourcePath:"/cdn/fmap/img",
                    size: [32,46]
                });
                // 注册要素图元
                FUtil.registSymbols(['shuidi','video']);
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
                        symbol: 'shuidi',
                        style:(feature)=>{
                            var clients = feature.get('clients');
                            // 这里的逻辑根据你自己的调,取你的业务值进行显示
                            var value = clients.rank;
                            var style = 'symbol:shuidi' +
                                '' +
                                '' +
                                ';text:' + value + ';text-fill:white;text-baseline:bottom;text-offectY:-15;text-font: 20px Verdana;'
                            return style;
                        }},
                    'videoLayer': {name: 'videoLayer', symbol: 'video', bubble: 'videoBB'},
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
                   // var client = event.feature.getClients();
                   /* post('example/getFeatureDetial.do', {id: client.code, coord: event.feature.getWgs84Coord()}).done((proList)=>{
                        // that.fmap.renderBubble(proList);
                    });*/
                });
                this.addRadomData("testLayer");
            },
            addRadomData(layer){
                var data= [
                {
                rank:"1",
                id:"d5601dee-6ale-4aee-9503-c93cff4c5bc6",
                name: "广东省广州市越秀区黄花岗街水荫南社区天河路45号之一十三",
                ACGE0: "113.3071623.1387",
                x: 113.30716,
                y: 23.1387
                },
                {
                rank:"2",
                id: "cc080400-0101-410e-9e8b-e56d73a495ca",
                name: "广东省广州市增城区新塘镇新墩村基尾街16号",
                AC_GE0: "113.55402,23.1153",
                x: 113.55402,
                y: 23.1153
                },
                {
                rank:"3",
                id: "46f54bb5-1990-4407-8d16-baoc09160044 ",
                name: "广东省清远市佛冈县龙山镇泚镇村委会泚镇管理区淽镇村176号",
                ACGE0: "113.4193738123.75749459",
                x: 113.41937381,
                y: 23.75749459
                },
                {
                rank:"4",
                id: "12345678-1234-1234-8d16-baoc09160044 ",
                name: "广东省清远市佛冈县龙山镇泚镇村委会泚镇管理区淽镇村176号",
                ACGE0: "113.4193738123.75749459",
                x: 113.419567,
                y: 23.345643
                },
                {
                rank:"5",
                id: "36987412-1234-1234-8d16-baoc09160044 ",
                name: "广东省清远市佛冈县龙山镇泚镇村委会泚镇管理区淽镇村176号",
                ACGE0: "113.4193738123.75749459",
                x: 113.852145,
                y: 23.876254
                },
                ];
                this.fmap.addFeatures(data, {layer: layer});
               /* var extent = this.fmap.getExtent();
                post('example/randomFeatures.do', {count:5, extent: extent}).done((data)=>{
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

<style lang="scss">
.info-cate{
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
            .video_playbtn{
                margin: auto;

            }
        }
    }
    #my_video{
        width:100%;
    }
    a{
        color:#409eff;
    }
    .fileInfo_main{
        box-shadow: 1px 1px 9px 8px #eee;
    }
    *{
        box-sizing: border-box;
    }
    .aljiang1{
          color:#009944;
        font-weight: bold;
      }
    .aljiang{
        color:red;
        font-weight: bold;
    }
    .el-card__body {
        padding: 0px;
    }
    .el-col {
        margin-bottom: 0px;
    }
    .el-tag{
        margin-right: 10px !important;
    }
    .fileInfo_main{
        background: #fff;
        padding:0 10px;
    }
    .form-main{
        overflow: hidden;
        font-size: 15px;
        .form-main-body{
            padding-left: 10px;
        }
        .form-main-title{
            height: 60px;
            line-height: 60px;
            font-size: 25px;
            color: #067af3;
            font-weight: bold;
            padding-left: 10px;
            .main-title-name{
                margin-right: 25px;
            }
            .main-title-score{
                color:#ff9913;
                background-color:#feedd5;
                padding: 5px 10px;
                font-size: 12px;
                margin-right: 10px;
            }
            .main-title-shoucang{
                background-color: #fff;
                .alicon_shoucang{
                    margin-right:5px;
                }
            }

        }
        .el-form-item {
            margin-bottom: 0px;
        }
        .el-icon-location-outline{
            color:#71a1e2;
            font-size: 18px;
            font-weight: bold;
        }
        .el-form-item__label {
            color: #7f7f7f;
        }
        .el-form-item__content{
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            padding-right: 5px;
        }
        .form-body-r{
            img{
                display: block;
                width: 270px;
                min-width: 270px;
                height: 230px;
                min-height: 230px;
            }
        }
    }
    .card-tab{
        margin-top: 20px;
        min-height: 190px;
        overflow: hidden;
        -webkit-box-shadow: 0 0px 0px 0 rgba(0,0,0,.12), 0 0 0px 0 rgba(0,0,0,.04) !important;
        box-shadow: 0 0px 0px 0 rgba(0,0,0,.12), 0 0 0px 0 rgba(0,0,0,.04) !important;
        border-left: 1px solid #409eff;
        border-right:none;
        border-bottom: 1px solid #409eff;
        border-top:none;
        .el-tabs__header {
            background-color: #fff!important;
            border-bottom: 1px solid #409eff!important;
        }
        .el-tabs__item {
            border: 1px solid #409eff;
            margin: 0px -1px -1px;
            color: #303133;
            margin-right: 10px;
            float: left;
        }
        .is-active {
            border-right-color: #409eff!important;
            border-left-color: #409eff !important;
            border-top:2px solid #409eff !important;
            border-bottom: none;
        }
        .el-tabs__content {
            padding: 15px;
            height: 190px;
            border-right:1px solid #409eff;
        }
        ul{
            list-style:none;
            margin: 0;
            padding: 0;
            clear: both;
            overflow: hidden;
            width:100%;
            li{
                float:left;
                font-size: 15px;
                color:#000;
                height: 30px;
                line-height: 30px;
            }
            li:nth-child(1){
                width: 3%;
            }
            li:nth-child(2){
                width:8%;
                font-weight: bold;
            }
            li:nth-child(3){
                width: 69%;
                color:#97baea;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }
            li:nth-child(4){
                width: 20%;
                text-align: right;
                padding-right: 10px;
            }
        }
    }
    .table-box{
        margin-top: 20px;
        border: 1px solid #74a2de;
        .aljiantou-lv{
            font-size: 12px;
            color: #069b48;
        }
        .aljiantou-hong{
            font-size: 12px;
            color: #e60012;
        }
        .table-box-top{
            height: 40px;
            line-height: 40px;
            padding-left: 10px;
            border-bottom: 1px solid #74a2de;
            overflow: hidden;
            .box-top-item1{
                font-weight: bold;
                color: #000000;
                span{
                    color: #44da8f;
                    font-size: 22px;
                    height: 40px;
                    line-height: 40px;
                    display: inline-block;
                    vertical-align: -3%;
                }
            }
            .box-top-item2,.box-top-item3{
                font-size: 14px;
            }
            .box-top-item2{
                span{
                    color: #000;
                    font-weight: bold;
                }
            }
            .box-top-item3{
                span{
                    color: #000;
                    font-weight: bold;
                }
            }
            .box-top-item4{
                text-align: right;
                padding-right: 20px;
                color:#1f94f4;
                font-size: 15px;
            }
        }
    }
    .table-place{
        margin-top: 20px;
        height:315px;
        overflow: hidden;
        max-height:360px ;
        border-bottom: 1px solid #74a2de;
        position: relative;
        .iconfontlogo{
            font-size: 40px;
        }
        .almore{
            font-size: 20px;
        }

        .el-tabs--border-card {
             border:none;
             -webkit-box-shadow: 0 0px 0px 0 rgba(0,0,0,.12), 0 0 0px 0 rgba(0,0,0,.04);
             box-shadow: 0 0px 0px 0 rgba(0,0,0,.12), 0 0 0px 0 rgba(0,0,0,.04);
        }
        .table-place-item{
            margin-right: 40px;
            overflow: hidden;
            padding-right: 0px !important;
        }
        .table-place-more{
            width:40px;
            border-right: 1px solid #74a2de;
            padding-left: 0px !important;
            padding-right: 0px !important;
            height: 360px;
            text-align: center;
            border-top: 1px solid  #409eff;
            border-bottom: 1px solid  #409eff;
            border-left: 1px solid  #409eff;
            max-width: 40px;
            right:0;
            top:0;
            position: absolute;
            .el-button{
                height: 360px;
            }
        }
        .el-tabs__active-bar{
            background-color:#fff !important;
        }
        .el-tabs__item{
            width:150px;
            padding-left: 0px !important;
            padding-right: 0px !important;
            height: 70px;
            overflow: hidden;
            margin-bottom: 10px;
        }
        .el-tabs__item+.el-tabs__item{
            margin-bottom:10px;
        }
        .el-tabs__item:first-child{
            margin-bottom: 10px;
        }
        .el-tabs__item:last-child{
            margin-bottom:0px;
        }
        .el-tabs--border-card>.el-tabs__header{
            background: #fff;
            border-bottom: none;
        }
        .el-tabs--left .el-tabs__item.is-left {
            text-align:left;
            border: 1px solid #409eff;
            margin: 0px -1px 15px 1px;
            width: 180px;
            height: 95px;
            min-width: 180px;
            min-height:95px;
        }
        .el-tabs--left .el-tabs__item.is-left:last-child{
            margin: 0px -1px 0px 1px;
        }
        .el-tabs--left .el-tabs__header.is-left {
            margin-right:0px;
            border-right: 1px solid #409eff;
            height: 315px;
            width:180px;
        }
        .el-tabs__item{
            color:#fff;
            background: #74a2de;
        }
        .is-active {
            color:#74a2de;
            background: #FFF;
            border-color: #409eff transparent;
        }
        .place-tab-body-icon{
            height: 95px;
            line-height: 95px;
            font-size: 30px;
            text-align: center;
        }
        .place-tab-body-content{
            text-align: right;
            padding-right: 10px;
            h5{
                margin: 0;
                padding: 0;
                height: 40px;
                line-height: 40px;
                font-size:18px;
            }
            p{
                margin: 0;
                padding: 0;
                font-size:25px;
            }
        }
        .el-tabs--border-card>.el-tabs__content {
            padding:0px;
            border-top: 1px solid  #409eff;
        }
        .place-body{
            padding-left: 25px;
        }
    }
    .table-face{
        margin-top: 20px;
        .table-face-body{
            border:1px solid #74a2de;
        }
        .table-face-top{
            color:#74a2de;
            font-size: 16px;
            font-weight: bold;
            height: 40px;
            line-height: 40px;
            .face-top-more{
                text-align: right;
                padding-right: 20px;

            }
        }
    }
    .map{
        margin-bottom:100px;
        .map-box{
            border: 1px solid #1f94f4;
            clear: both;
            height: 350px;
        }
        #fmap{
            border-right: 1px solid #1f94f4;
            height: 350px;
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
                padding:0 10px;
            }
            .address_list_item{
                border-bottom: 1px dashed #000;
                padding: 10px 0;
                font-size: 14px;
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
}

</style>