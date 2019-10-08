<template>
    <div class="info-cate">
        <div class="fileInfo_main">
            <!--基础信息-->
            <el-form class="form-main">
                <el-row class="form-main-title">
                    <span class="main-title-name"> {{this.jbxxForm.csbaZcmc}}</span>
                    <span class="main-title-score">90分</span>
                    <span class="main-title-level main-title-score">A级</span>
                    <csgz :param="csgzQuery"></csgz>
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
                        <viewer>
                        <img style="height: 100px;width: 100px" id="jbxxImg" />
                        </viewer>
                    </el-col>
                </el-row>
            </el-form>
            <!--卡片切换-->
            <el-tabs type="border-card" class="card-tab">
                <el-tab-pane label="异常警告">
                    <warn-info-tag :param="warnInfoParams"></warn-info-tag>
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
                    <viewer>
                    <img id="pmtImg" style="height:165px; width:300px;"/>
                    </viewer>
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
                            <viewer >
                            <img style="height:80px; width:80px;border-radius: 5px;" :id="scope.row.rybaId"
                                 :onload="initRyImg(scope.row.rybaZp,scope.row.rybaId)"
                                 />
                            </viewer>
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
                <div class="map_box" style="height:400px">
                    <bacsmap v-model="address"/>
                </div>
            </div>
        </div>
        <!--从业人员详情弹窗-->
        <el-dialog :title="rybaFormTitle" :visible.sync="rybaFormView" :fullscreen="true" :append-to-body="true" @close='closeRyDialog'>
            <avue-detail :option="rybaFormOption" v-model="rybaFromDetail">
                <template slot="rybaZpContentForm" slot-scope="scope">
                    <viewer>
                    <img style="height: 100px;width: 100px"  id="ryImgPerson"/>
                    </viewer>
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
        getRyImg,
        getJbxxImg,
        getPmtImg
    } from "@/api/portal/archive/ylcs/csba";
    import {formOption} from '@/const/crud/portal/archive/ylcs/ryba/index';
    import {ylcsDict} from "@/const/crud/portal/archive/ylcs/ylcsDict";
    import  Csgz from '../../mechanical/fileInfo/cate/Csgz.vue'
    import '@/styles/portal/archive/place-detail.scss?v=110'
    import {remote}  from  "@/api/admin/dict";
    import  warnInfoTag from '@/views/portal/judged/warn/warnInfoTag'
    import bacsmap from '@/components/map/bacsMap'
    export default {
        name: 'jbxx',
        props: ["csbaForm"],
        components:{
            Csgz,
            warnInfoTag,
            bacsmap,
        },
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
                    menuId: '',
                    lyxt:'1'
                }, warnInfoParams: {
                    placeId: undefined,
                    sourceType: '1',
                    modelId: undefined
                },
                imgQuery:{
                    csbaId:'',
                    fjLeixingid:'',
                },
                ryImgMap : new Map(),
                tagDictData:[],

                address:{
                    //调用的接口
                    type:"matchForward",
                    //参数
                    data:{
                        "xz":"广州南站",
                        "province":"广东省"
                    }
                }
            }
        },
        created() {
            this.csbaId = this.csbaForm.csbaId
            this.csbabh = this.csbaForm.csbaBabh
            this.compId = this.csbaForm.compId
            this.csgzQuery.menuId = this.csbaForm.menuId;
            this.csgzQuery.dwbh = this.csbaForm.csbaId;
            this.warnInfoParams.placeId=this.csbaForm.csbaId;
            this.getJbxxform();
            this.getList(this.page);
            this.getRybaCount();
            this.getTagDictData()
        },
        methods: {
            getTagDictData(){
                remote('QLRYLX').then(response => {
                    this.tagDictData = response.data.data||{}
                }).catch((err) => {});
            },
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
            closeRyDialog(){
                this.rybaFormView=false
            },
            initRyImg(rybaZp,id) {
                let img=this.ryImgMap.get(id);
                if('undefined'==typeof (img)||''===img||null===img) {
                    getRyImg(rybaZp).then(value => {
                        this.ryImgMap.set(id,value);
                        let imgTag =document.getElementById(id);
                        imgTag.src=value
                    })
                }else{
                    let imgTag =document.getElementById(id);
                    imgTag.src=img
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
                    this.warnInfoParams.placeId=this.csbaForm.csbaId;
                    this.getJbxxform();
                    this.getList(this.page);
                    this.getRybaCount();
                },
                deep: true
            },
        }

    }
</script>