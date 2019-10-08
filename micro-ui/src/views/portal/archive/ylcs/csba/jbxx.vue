<template>
    <div class="info-cate">
        <div class="fileInfo_main">
            <!--基础信息-->
            <el-form class="form-main">
                <el-row class="form-main-title">
                    <span class="main-title-name"> {{this.jbxxForm.csbaZcmc}}</span>
                    <span class="main-title-score">90分</span>
                    <span class="main-title-level main-title-score">A级</span>
                    <a href="#" v-if="wscshow">
                        <span class="main-title-wsc" @click="wsc"><i class="iconfontlogo alicon_shoucang"></i>未收藏</span>
                    </a>
                    <a href="#" v-if="yscshow"><span class="main-title-ysc" @click="ysc"><i
                            class="iconfontlogo alicon_shoucang"></i>已收藏</span></a>
                </el-row>
                <el-row class="form-main-body">
                    <el-col :span="18" class="form-body-l form-body-tabel">
                        <el-form label-width="135px" label-position="left">
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="法人代表">
                                        {{this.jbxxForm.csbaFddbr}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="证件号码">
                                        {{this.jbxxForm.csbaFddbrzj}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="开业日期">
                                        {{this.jbxxForm.csbaKyrq}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="备案进度">
                                        {{dict.getLabel(dict.csbaJindu,this.jbxxForm.csbaJindu)}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="经济类型">
                                        {{this.jbxxForm.csbaJjlx}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="区域编码">
                                        {{this.jbxxForm.compCode}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="营业执照">
                                        {{this.jbxxForm.csbaYyzz}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="备案节点">
                                        {{this.jbxxForm.notpass}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="备案状态">
                                        {{this.jbxxForm.state}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-tooltip placement="top" effect="light">
                                        <div slot="content">{{this.jbxxForm.csbaJymc}}</div>
                                        <el-form-item label="实际经营名称">
                                            {{this.jbxxForm.csbaJymc}}
                                        </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="8">
                                    <el-tooltip placement="top" effect="light">
                                        <div slot="content">{{this.jbxxForm.csbaYlnr}}</div>
                                        <el-form-item label="娱乐项目内容">
                                            {{this.jbxxForm.csbaYlnr}}
                                        </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="签证签注种类">
                                        {{this.jbxxForm.csbaQzzl}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="上级主管部门">
                                        {{this.jbxxForm.csbaSjzgbm}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="备案通过时间">
                                        {{this.jbxxForm.csbaTongguoshijian}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="治安管理机构">
                                        {{this.jbxxForm.csbaZagljg}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col>
                                    <el-col :span="8">
                                        <el-form-item label="股东信息">
                                            {{this.jbxxForm.csbaGdxx}}
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="8">
                                        <el-form-item label="备案登记人">
                                            {{this.jbxxForm.csbaDjr}}
                                        </el-form-item>
                                    </el-col>
                                    <el-col :span="8">
                                        <el-form-item label="登记人证件号">
                                            {{this.jbxxForm.csbaDjrzj}}
                                        </el-form-item>
                                    </el-col>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="版本">
                                        {{this.jbxxForm.version}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-tooltip placement="top" effect="light">
                                        <div slot="content">{{this.jbxxForm.csbaBz}}</div>
                                        <el-form-item label="备注">
                                            {{this.jbxxForm.csbaBz}}
                                        </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="备案编号">
                                        {{this.jbxxForm.csbaBabh}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="24">
                                    <el-form-item label="经营地址">
                                        {{this.jbxxForm.csbaJydz}}
                                        <a href="#"><i class="el-icon-location-outline"></i></a>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                        </el-form>
                    </el-col>
                    <el-col :span="6" class="form-body-r">
                        <img style="height: 100px;width: 100px" id="jbxxImg" />
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
                <el-tab-pane label="规模设施">
                    <el-form>
                        <el-col>
                            <el-col :span="12">
                                <el-form-item label="经营面积">
                                    {{this.jbxxForm.csbaJymj}}
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="包厢数量">
                                    {{this.jbxxForm.csbaBxsl}}
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="场所经度">
                                    {{this.jbxxForm.csbaJd}}
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="场所纬度">
                                    {{this.jbxxForm.csbaWd}}
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="核定消费者数量">
                                    {{this.jbxxForm.csbaHdxfzsl}}
                                </el-form-item>
                            </el-col>
                        </el-col>
                    </el-form>
                </el-tab-pane>
                <el-tab-pane label="平面图">
                    <img id="pmtImg" style="height:165px; width:300px;"/>
                </el-tab-pane>
            </el-tabs>
            <!--在岗人数列表-->
            <div class="table-box">
                <el-row class="table-box-top">
                    <el-col :span="4" class="box-top-item1">在职人数 <span>{{page.total}}</span></el-col>
                    <el-col :span="4" class="box-top-item2">本月新上岗 <span>{{this.rybaCount.bysg}}人</span><i
                            class="iconfontlogo aljiang1"></i></el-col>
                    <el-col :span="4" class="box-top-item3">本月离岗 <span>{{this.rybaCount.bylg}}人</span><i
                            class="iconfontlogo aljiang"></i></el-col>
                    <el-col :span="12" class="box-top-item4">
                        <el-button type="text" @click="moreClick">更多>></el-button>
                    </el-col>
                </el-row>
                <el-table
                        :header-cell-style="{background:'#eee',color:'#000'}"
                        :row-style="{height:'90px'}"
                        :data="tableData"
                        style="width: 100%"
                        :page="page"
                        :table-loading="tableLoading">
                    <el-table-column
                            width="50"
                            type="index"
                            label="序号">
                    </el-table-column>
                    <el-table-column
                            prop="rybaZp"
                            label="照片"
                            align="center"
                            width="100">
                        <template slot-scope="scope">
                            <img style="height:80px; width:80px;border-radius: 5px;" :id="scope.row.rybaId"
                                 src="/img/people.png"
                                 :src="initRyImg(scope.row.rybaZp,scope.row.rybaId)"/>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="rybaMc"
                            label="姓名">
                        <template slot-scope="scope">
                            <button type="button" class="el-button el-button--text el-button--small"
                                    @click="openRyxqDialog(scope.row)">{{scope.row.rybaMc}}
                            </button>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="rybaZjhm"
                            width="200"
                            label="证件号码">
                    </el-table-column>
                    <el-table-column
                            prop="rybaXb"
                            label="性别">
                        <template slot-scope="scope">
                            <span>{{ dict.getLabel(dict.sex,scope.row.rybaXb)}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="rybaCsrq"
                            min-width="120"
                            label="出生日期">
                    </el-table-column>
                    <el-table-column
                            prop="rybaLb"
                            min-width="120"
                            label="类别">
                        <template slot-scope="scope">
                            <span>{{ dict.getLabel(dict.rylb,scope.row.rybaLb)}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="rybaGwmc"
                            label="岗位名称">
                    </el-table-column>
                    <el-table-column
                            prop="rybaDjsj"
                            min-width="120"
                            label="上岗日期">
                    </el-table-column>
                    <el-table-column
                            prop="rybaLzsj"
                            min-width="120"
                            label="离岗日期">
                    </el-table-column>
                    <el-table-column
                            prop="rybaCyzt"
                            min-width="120"
                            label="状态">
                        <template slot-scope="scope">
                            <span>{{ dict.getLabel(dict.ryzt,scope.row.rybaCyzt)}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            width="200"
                            prop="rybaLb"
                            label="七类从业人员类别">
                    </el-table-column>
                </el-table>
            </div>
            <!--人脸识别-->
            <div class="table-face">
                <div class="table-face-top">
                    <el-row>
                        <el-col :span="20" class="face-top-title">
                            人脸识别
                        </el-col>
                        <el-col :span="4" class="face-top-more">
                            <el-button type="text" @click="dialogTablePlaceVisible = true">更多>></el-button>
                        </el-col>
                    </el-row>
                </div>
                <el-table
                        class="table-face-body"
                        :header-cell-style="{background:'#eee',color:'#000'}"
                        :data="tableData"
                        style="width: 100%">
                    <el-table-column
                            width="50"
                            type="index"
                            label="序号">
                    </el-table-column>
                    <el-table-column
                            prop="rybaMc"
                            label="姓名">
                    </el-table-column>
                    <el-table-column
                            prop="rybaZjhm"
                            width="200"
                            label="证件号码">
                    </el-table-column>
                    <el-table-column
                            prop="rybaXb"
                            width="70"
                            label="姓别">
                    </el-table-column>
                    <el-table-column
                            prop="rybaCsrq"
                            min-width="120"
                            label="出生日期">
                    </el-table-column>
                    <el-table-column
                            prop="rybaLb"
                            label="人员类型">
                    </el-table-column>
                    <el-table-column
                            prop="rybaDjsj"
                            min-width="120"
                            label="出现时间">
                    </el-table-column>
                    <el-table-column
                            width="200"
                            prop="rybaLb"
                            label="七类人员类型">
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
                <div class="map_box">

                </div>
            </div>
        </div>
        <!--从业人员详情弹窗-->
        <el-dialog :title="rybaFormTitle" :visible.sync="rybaFormView" :fullscreen="true" :append-to-body="true" @close='closeRyDialog'>
            <avue-detail :option="rybaFormOption" v-model="rybaFromDetail">
                <template slot="rybaZpContent" slot-scope="scope">
                    <img style="height: 100px;width: 100px"  id="ryImgPerson"/>
                </template>
            </avue-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="closeRyDialog">关 闭
                </el-button>
            </div>
        </el-dialog>
        <el-dialog title="人脸识别"
                   width="80%"
                   :visible.sync="dialogTablePlaceVisible">
            <el-table
                    class="table-face-body"
                    :header-cell-style="{background:'#eee',color:'#000'}"
                    :data="tableData"
                    style="width: 100%">
                <el-table-column
                        label="序号"
                        type="index">
                </el-table-column>
                <el-table-column
                        prop="rybaMc"
                        label="姓名">
                </el-table-column>
                <el-table-column
                        prop="rybaZjhm"
                        width="200"
                        label="证件号码">
                </el-table-column>
                <el-table-column
                        prop="rybaXb"
                        label="姓别">
                </el-table-column>
                <el-table-column
                        prop="rybaCsrq"
                        min-width="120"
                        label="出生日期">
                </el-table-column>
                <el-table-column
                        prop="rybaLb"
                        label="人员类型">
                </el-table-column>
                <el-table-column
                        prop="rybaDjsj"
                        min-width="120"
                        label="出现时间">
                </el-table-column>
                <el-table-column
                        width="200"
                        prop="rybaLb"
                        label="七类人员类型">
                </el-table-column>
            </el-table>
        </el-dialog>
    </div>
</template>

<script>
    import {
        getCsbaJbxx,
        findRyListPage,
        getRybaCount,
        getRyDetail,
        ShouCan,
        IsGz,
        getRyImg,
        getJbxxImg,
        getPmtImg
    } from "@/api/portal/archive/ylcs/csba";
    import {formOption} from '@/const/crud/portal/archive/ylcs/ryba/index';
    import {ylcsDict} from "@/const/crud/portal/archive/ylcs/ylcsDict";
    import '@/styles/portal/archive/place-detail.scss'

    export default {
        name: 'jbxx',
        props: ["csbaForm"],
        data() {
            return {
                csbaId: '',
                csbabh: '',
                compId: '',
                dict: ylcsDict,
                jbxxForm: {},
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 5 // 每页显示多少条
                },
                dialogTableVisible: false,
                dialogTablePlaceVisible: false,
                tableData: [],
                rybaCount: {},
                rybaFormView: false,
                rybaFormOption: formOption,
                rybaFromDetail: {},
                rybaFormTitle: '',
                wscshow: false,
                yscshow: false,
                csgzQuery: {
                    dwbh: '',
                    menuId: ''
                },
                imgQuery:{
                    csbaId:'',
                    fjLeixingid:'',
                },
                ryImgMap : new Map(),
            }
        },
        created() {
            this.csbaId = this.csbaForm.csbaId
            this.csbabh = this.csbaForm.csbaBabh
            this.compId = this.csbaForm.compId
            this.csgzQuery.menuId = this.csbaForm.menuId;
            this.csgzQuery.dwbh = this.csbaForm.csbaId;
            this.getJbxxform();
            this.getList(this.page);
            this.getRybaCount();
            this.getIsGz();
        },
        methods: {
            getList(page, params) {
                this.ryImgMap.clear()
                this.tableData = []
                this.tableLoading = true
                findRyListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    compId: this.compId,
                    csbabh: this.csbabh,
                    rybaCyzt: '10'
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            getJbxxform() {
                getCsbaJbxx(this.csbaId).then(response => {
                    this.jbxxForm = response.data.data
                });
                this.imgQuery.csbaId = this.csbaId
                this.imgQuery.fjLeixingid='csba_fj_3'
                getJbxxImg(this.imgQuery,'jbxxImg')
                getPmtImg(this.csbaId,'pmtImg')
            },
            getRybaCount() {
                getRybaCount(this.compId, this.csbabh).then(response => {
                    this.rybaCount = response.data.data
                })
            },
            getRybaFormDetail(id) {
                getRyDetail(id).then(response => {
                    this.rybaFromDetail = response.data.data
                })
            },
            openRyxqDialog(row) {
                this.rybaFormView = true
                this.rybaFormTitle = "人员详情"
                this.getRybaFormDetail(row.rybaId)
                getRyImg(row.rybaZp,'ryImgPerson')
            },
            moreClick() {
                this.$emit("moreClick", "2");
            },
            getIsGz() {
                IsGz(this.csgzQuery).then(response => {
                    var is = response.data.data
                    if (is > 0) {
                        this.yscshow = true
                        this.wscshow = false
                    } else {
                        this.wscshow = true
                        this.yscshow = false
                    }
                })
            },
            wsc() {
                ShouCan(this.csgzQuery).then((res) => {
                    this.wscshow = false
                    this.yscshow = true
                })
            },
            ysc() {
                ShouCan(this.csgzQuery).then((res) => {
                    this.wscshow = true
                    this.yscshow = false
                })
            },
            closeRyDialog(){
                this.rybaFormView=false
                this.ryImgMap.clear()
            },
            initRyImg(rybaZp,id) {
                let ryImg=this.ryImgMap.get(rybaZp);
                if(null===ryImg||'undefined'===typeof (ryImg)||''===ryImg) {
                    getRyImg(rybaZp,id)
                    this.ryImgMap.set(rybaZp,rybaZp);
                }
            },

        },

        watch: {
            csbaForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.csbaId = this.csbaForm.csbaId
                    this.csbabh = this.csbaForm.csbaBabh
                    this.compId = this.csbaForm.compId
                    this.csgzQuery.menuId = this.csbaForm.menuId;
                    this.csgzQuery.dwbh = this.csbaForm.csbaId;
                    this.getJbxxform();
                    this.getList(this.page);
                    this.getRybaCount();
                    this.getIsGz();
                },
                deep: true
            },
        }

    }
</script>