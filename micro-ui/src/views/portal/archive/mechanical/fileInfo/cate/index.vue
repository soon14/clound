<template>
    <div class="info-cate">
        <div class="fileInfo_main">
            <!--基础信息-->
            <el-form  class="form-main" :model="form">
                <el-row  class="form-main-title">
                    <span class="main-title-name"> {{this.form.dwmc}}</span>
                    <span class="main-title-score">90分</span>
                    <span class="main-title-level main-title-score">{{this.form.dwdj}}</span>
                    <csgz :param="params"></csgz>
                </el-row>
                <el-row class="form-main-body">
                    <el-col :span="18" class="form-body-l form-body-tabel">
                        <el-form label-width="135px" label-position="left">
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="行业类别:">
                                        {{this.form.dwlbdm}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="单位类别:">
                                        {{this.form.dwlb}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="经济类型:">
                                        {{this.form.dwxz}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-tooltip content="440703520000000000" placement="top" effect="light">
                                        <el-form-item label="组织机构代码:">
                                            {{this.form.djdwgajgjgdm}}
                                        </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="企业状态:">
                                        {{this.form.qyztdm}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="开业日期:">
                                        {{this.form.kyrq}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-tooltip content="开平市公安局潭江派出所" placement="top" effect="light">
                                        <el-form-item label="管辖派出所:">
                                            {{this.form.djdwgajgmc}}
                                        </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="责任民警名称:">
                                        {{this.form.zrmjxm}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="责任民警警号:">
                                        {{this.form.zrmjjg}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="主营:">
                                        {{this.form.jyfwzy}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="兼营:">
                                        {{this.form.jyfwjy}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="被兼营单位:">
                                        {{this.form.jyfwbjy}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="重点单位:">
                                        {{ dict.getLabel(dict.zddwbs,this.form.zddwbs)}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-tooltip content="2019-01-30至2019-04-30" placement="top" effect="light">
                                        <el-form-item label="经营有效时间段:">
                                            {{this.form.kyrq}}
                                        </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="联系电话:">
                                        {{this.form.lxdh}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-tooltip content="经营地市省市" placement="top" effect="light">
                                        <el-form-item label="经营地市省市(区县):">
                                            {{this.form.dwdzdzbm}}
                                        </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="16">
                                    <el-form-item label="经营地址:">
                                        {{this.form.dwdzqhnxxdz}}
                                        <a href="#"><i class="el-icon-location-outline"></i></a>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                        </el-form>
                    </el-col>
                    <el-col :span="6" class="form-body-r">
                        <img src="../../../../../../../public/img/unit.jpg" alt="">
                    </el-col>
                </el-row>
            </el-form>
            <!--卡片切换-->
            <el-tabs type="border-card" class="card-tab">
                <el-tab-pane label="异常警告">
                    <ul v-for="(item,index) in ycjgData" :key="index">
                        <li>{{item.xh}}</li>
                        <li>[{{item.yplx}}]</li>
                        <li><a href="#">{{item.ycnr}}</a></li>
                        <li>{{item.ycsj}}</li>
                    </ul>
                </el-tab-pane>
            </el-tabs>
            <!--在岗人数列表-->
            <div class="table-box">
                <el-row class="table-box-top" :model="cyrycount">
                    <el-col :span="4" class="box-top-item1">在岗人数 <span>{{this.cyrycount.zgrs}}人</span></el-col>
                    <el-col :span="4" class="box-top-item2">本月新上岗  <span>{{this.cyrycount.bysg}}人</span><i class="iconfontlogo aljiang"></i></el-col>
                    <el-col :span="4" class="box-top-item3">本月离岗  <span>{{this.cyrycount.bylg}}人</span><i class="iconfontlogo aljiang1"></i></el-col>
                    <el-col :span="12" class="box-top-item4">
                        <el-button type="text" @click="moreClick(1)">更多>></el-button>
                      <!--  <el-button type="text" @click="cyryTableVisible=true">更多>></el-button>-->
                    </el-col>
                </el-row>
                <el-table
                        :header-cell-style="{background:'#eee',color:'#000'}"
                        :cell-style="cellStyle"
                        :row-style="{height:'90px'}"
                        :data="cyryData"
                        style="width: 100%">
                    <el-table-column
                            width="50"
                            prop="xh1"
                            label="序号">
                    </el-table-column>
                    <el-table-column
                            prop="imgUrl"
                            label="照片"
                            align="center"
                            width="100">
                        <template slot-scope="scope">
                            <img style="height:80px; width:80px;border-radius: 5px;" :id="scope.row.xxid"
                                 src="/img/people.png"
                                 :src="initRyImg(scope.row.xxid,scope.row.xxid)"/>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="xm"
                            label="姓名">
                        <template slot-scope="scope">
                            <button type="button" class="el-button el-button--text el-button--small"
                                    @click="openRyxqDialog(scope.row)" >{{scope.row.xm}}</button>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="zjhm"
                            width="200"
                            label="证件号码">
                    </el-table-column>
                    <el-table-column
                            prop="xbdm"
                            label="姓别">
                        <template slot-scope="scope">
                            <span >{{ dict.getLabel(dict.xbdm,scope.row.xbdm)}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            min-width="120"
                            label="出生日期">
                    </el-table-column>
                    <el-table-column
                            prop="gzmc"
                            label="岗位类型">
                    </el-table-column>
                    <el-table-column
                            prop="rzrq"
                            min-width="120"
                            label="上岗日期"
                            format="yyyy-MM-dd"
                            valueFormat= "yyyy-MM-dd">
                    </el-table-column>
                    <el-table-column
                            width="200"
                            label="七类人员类型"
                            prop="persontype">
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
            <!--承接车辆列表-->
            <div class="table-box">
                <el-row class="table-box-top" :model="cjclcount">
                    <el-col :span="4" class="box-top-item1">承接车辆 <span>{{this.cjclcount.cjcls}}</span></el-col>
                    <el-col :span="4" class="box-top-item2">交接车辆  <span>{{this.cjclcount.jjcls}}</span></el-col>
                    <el-col :span="12" class="box-top-item4">
                        <el-button type="text" @click="moreClick(2)">更多>></el-button>
                       <!-- <el-button type="text" @click="cjclTableVisible = true">更多>></el-button>-->
                    </el-col>
                </el-row>
                <el-table
                        :header-cell-style="{background:'#eee',color:'#000'}"
                        :cell-style="cjclStyle"
                        :row-style="{height:'90px'}"
                        :data="cjclData"
                        style="width: 100%">
                    <el-table-column
                            prop="xh1"
                            label="序号">
                    </el-table-column>
                    <el-table-column
                            prop="jdcsyrmc"
                            label="机动车所有人">
                    </el-table-column>
                    <el-table-column
                            prop="jdccllxdm"
                            label="车辆类型">
                        <template slot-scope="scope">
                            <span >{{ dict.getLabel(dict.jdccllxdm,scope.row.jdccllxdm)}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="jdchphm"
                            label="车牌号码">
                    </el-table-column>
                    <el-table-column
                            prop="clxh"
                            label="车辆型号">
                    </el-table-column>
                    <el-table-column
                            prop="jdccsysdm"
                            label="车身颜色">
                        <template slot-scope="scope">
                            <span >{{ dict.getLabel(dict.jdccsysdm,scope.row.jdccsysdm)}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="sxrxm"
                            label="送修人">
                    </el-table-column>
                    <el-table-column
                            prop="sxrzjhm"
                            min-width="100"
                            label="送修人证件号">
                    </el-table-column>
                    <el-table-column
                            prop="sxsj"
                            min-width="120"
                            label="送修日期">
                    </el-table-column>
                    <el-table-column
                            prop="scrxm"
                            label="收车人">
                    </el-table-column>
                    <el-table-column
                            prop="jjzt"
                            label="交接状态">
                    </el-table-column>
                    <el-table-column>
                        <template slot-scope="scope">
                                    <el-button color="#fff" size="mini" @click="openClxqDialog(scope.row)" >查看</el-button>
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
                        <el-col  :span="4"  class="face-top-more">
                            <el-button type="text" @click="dialogTablePlaceVisible = true">更多>></el-button>
                        </el-col>
                    </el-row>
                </div>
                <el-table
                        class="table-face-body"
                        :header-cell-style="{background:'#eee',color:'#000'}"
                        :cell-style="facecellStyle"
                        :data="cyryData"
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
                <div class="map_box">

                </div>
            </div>
        </div>
        <!--弹框-->
        <!--从业人员详情弹窗-->
        <el-dialog title="人员详情"  :visible.sync="cyryFormView" :fullscreen="true" :append-to-body="true" @close='closeDialog'>
            <avue-detail :option="cyryFormOption" v-model="cyryFromDetail" >
                <template slot="imgUrl" slot-scope="scope">
                    <img style="height: 100px;width: 100px"  id="imgPerson" :src="initRyImg(cyryFromDetail.xxid,'imgPerson')"/>
                </template>
            </avue-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="closeDialog">关 闭
                </el-button>
            </div>
        </el-dialog>
        <!--承接车辆详情弹窗-->
        <el-dialog title="车辆详情"  :visible.sync="cjclFormView" :fullscreen="true" :append-to-body="true">
            <avue-detail :option="cjclFormOption" v-model="cjclFromDetail" >
                <template slot="imgUrl" slot-scope="scope">
                    <img style="height: 250px;width: 250px"  id="imgCjcl"/>
                </template>
            </avue-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="cjclFormView=false">关 闭
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {findCjclPage, findCyryPage, findOne,CountCyry,CountCjcl,
            getRyDetail,getClxqDetail,getRyImg,getClImg,findYcjg}
    from "@/api/portal/archive/mechanical/jxyxt";
    import  Csgz from './Csgz.vue'
    import {findLabel}  from  "@/api/portal/archive/dict/dict";
    import {formOption,clxqOption,cyryDict} from "@/const/crud/portal/archive/mechanical/jxyxt";
    export default {
        props: ["paramForm"],
        name: 'index',
        components:{Csgz},
        data () {
            return{
                dialogTableVisible: false,
                dialogTablePlaceVisible:false,

                cyrypage: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 5, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                cjclpage: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 5, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                imgMap : new Map(),
                dict:cyryDict,
                cyryFormView:false,
                cyryFormOption:formOption,
                cyryFromDetail:{},

                cjclFormView:false,
                cjclFormOption:clxqOption,
                cjclFromDetail:{},

                form:{ },
                cyrycount:{},
                cjclcount:{},
                params:{
                    dwbh:'' ,menuId:'',lyxt:'2'
                },
                cyryData: [],
                cjclData: [],
                ycjgData: [],
            }
        },
        created(){
            this.params.menuId = this.paramForm.menuId;
            this.params.dwbh = this.paramForm.dwbh;
            this.getdwxx( )
            this.getJxCyry( )
            this.getJxCjcl( )
            this.getCountCyry( )
            this.getCountCjcl( )
            this.getYcjg( )
        },
        computed: {

        },
        watch: {
            paramForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.params.dwbh = this.paramForm.dwbh;
                    this.params.menuId = this.paramForm.menuId;
                    this.getdwxx()
                    this.getJxCyry()
                    this.getJxCjcl()
                    this.getCountCyry()
                    this.getCountCjcl()
                    this.getYcjg( )
                },
                deep: true
            },
        },
        methods: {

            initDict() {
                findLabel('qylb', 'jx', this.form.dwlbdm).then(value => {
                    this.form.dwlbdm= value
                }).catch((err) => {
                });
                findLabel('qyzt', 'jx', this.form.qyztdm).then(value => {
                    this.form.qyztdm= value
                }).catch((err) => {
                });
            },

            initcjclDict() {
                findLabel('cllx', 'jx', this.cjclData.jdccllxdm).then(value => {
                    this.form.cjclData= value
                }).catch((err) => {
                });
                findLabel('csys', 'jx', this.cjclData.jdccsysdm).then(value => {
                    this.form.cjclData= value
                }).catch((err) => {
                });
            },

            initClImg(id,imgId) {
                getClImg(id,imgId)
            },

            initRyImg(id,imgId) {
                let img=this.imgMap.get(id);
                if(null==img||'undefined'==typeof (img)||''==img) {
                    getRyImg(id,imgId)
                    this.imgMap.set(id,id);
                }
            },
            moreClick(num) {
                this.$emit("moreClick", num);
            },

            getClxqFormDetail(ywlsh) {
                getClxqDetail(ywlsh).then(response => {
                    this.cjclFromDetail = response.data.data
                })
            },

            openClxqDialog(row){
                this.cjclFormView = true
                this.getClxqFormDetail(row.ywlsh)
            },

            getRyxqFormDetail(xxid) {
                getRyDetail(xxid).then(response => {
                    this.cyryFromDetail = response.data.data
                })
            },

            openRyxqDialog(row){
                this.cyryFormView = true
                this.getRyxqFormDetail(row.xxid)
                initClImg(row.xxid,'imgCjcl')
            },

            closeDialog(){
                this.cyryFormView=false
                this.imgMap.clear()
            },

            getCountCjcl( ) {
                CountCjcl(Object.assign({ }, this.params)).then(response => {
                    this.cjclcount = response.data.data
                    if (response.data.data.jjcls==null){
                        this.cjclcount.jjcls = 0;
                    }
                })
            },

            getCountCyry( ) {
                CountCyry(Object.assign({ }, this.params)).then(response => {
                    this.cyrycount = response.data.data
                    if (response.data.data.zgrs==null){
                        this.cyrycount.zgrs = 0;
                    } if (response.data.data.bysg==null) {
                        this.cyrycount.bysg = 0;
                    } if (response.data.data.bylg==null) {
                        this.cyrycount.bylg = 0;
                    }

        })
    },
            getYcjg( ) {
                findYcjg(Object.assign({
                    current: this.cjclpage.currentPage,
                    size: this.cjclpage.pageSize
                }, this.params)).then(response => {
                    this.ycjgData = response.data.data.records
                })
            },

            getJxCjcl( ) {
                findCjclPage(Object.assign({
                    current: this.cjclpage.currentPage,
                    size: this.cjclpage.pageSize
                }, this.params)).then(response => {
                    this.cjclData = response.data.data.records
                    this.initcjclDict();
                })
            },

            getdwxx( ) {
                findOne(Object.assign({ }, this.params)).then(response => {
                    this.form = response.data.data
                    this.initDict()
                })
            },

            getJxCyry( ) {
                this.imgMap.clear()
                findCyryPage(Object.assign({
                    current: this.cyrypage.currentPage,
                    size: this.cyrypage.pageSize
                }, this.params)).then(response => {
                    this.cyryData = response.data.data.records
                })
            },

            cellStyle({row, column, rowIndex, columnIndex}) {
                if (columnIndex === 2) {
                    return 'color:#6aaff8'
                } else {
                    return ''
                }
            },
            cjclStyle({row, column, rowIndex, columnIndex}) {
                if (columnIndex === 1) {
                    return ''/*display:none;*/
                } else if(columnIndex === 0){
                    return 'text-align:center'
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
        },

    }
</script>

<style lang="scss">
    .info-cate{
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
                .main-title-wsc{
                    color:#CDD;
                 //   background-color:#feedd5;
                    padding: 5px 10px;
                    font-size: 12px;
                    margin-right: 10px;

                }
                .main-title-ysc{
                    color:#ff9913;
                //    background-color:#feedd5;
                    padding: 5px 10px;
                    font-size: 12px;
                    margin-right: 10px;

                }
/*                .main-title-shoucang{
                    background-color: #fff;
                    .alicon_shoucang{
                        margin-right:5px;
                    }
                }*/

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
            .map_box{
                height: 400px;
            }
        }
    }

</style>