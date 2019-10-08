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
                                    <el-form-item label="主营:">
                                        {{this.form.jyfwzy}}
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
                                        <el-form-item label="企业等级:">
                                            {{dict.getLabel(dict.dwdj,this.form.dwdj)}}
                                        </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="企业状态:">
                                        {{this.form.qyztdm}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="注册资本:">
                                        {{this.form.zczb}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="注册日期:">
                                        {{this.form.zcrq}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="开业日期:">
                                        {{this.form.kyrq}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="经营场地_面积:">
                                        {{this.form.jycdmjpfm}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                        <el-form-item label="社会信用代码:">
                                            {{this.form.zzjgdm}}
                                        </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="行业许可证号:">
                                        {{this.form.hyxkzh}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="营业执照号:">
                                        {{this.form.yyzzh}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-tooltip placement="top" effect="light">
                                        <el-form-item label="管辖派出所:">
                                            {{this.form.djdwgajgmc}}
                                        </el-form-item>
                                    </el-tooltip>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="行业许可发证机关:">
                                        {{this.form.fzjghyxkzfzjgmc}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="营业执照发证机关:">
                                        {{this.form.fzjgyyzhfzjgmc}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                        <el-form-item label="法定代表人姓名:">
                                            {{this.form.fddbrxm}}
                                        </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="单位负责人姓名:">
                                        {{this.form.dwfzrxm}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="保卫负责人姓名:">
                                        {{this.form.bwfzrxm}}
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
                                        <el-form-item label="登记人_姓名:">
                                            {{this.form.djrxm}}
                                        </el-form-item>
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
                    <warn-info-tag :param="warnInfoParams"></warn-info-tag>
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
                            <viewer>
                            <img style="height:80px; width:80px;border-radius: 5px;" :id="scope.row.xxid"
                                 :onload="initRyImg(scope.row.xxid,scope.row.xxid)"
                            />
                            </viewer>
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
                            prop="tagType"
                            label="人员类型">
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
                            width="200"
                            prop="tagType"
                            label="人员类型">
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
        <!--弹框-->
        <!--从业人员详情弹窗-->
        <el-dialog title="人员详情"  :visible.sync="cyryFormView" :fullscreen="true" :append-to-body="true" @close='closeDialog'>
            <avue-detail :option="cyryFormOption" v-model="cyryFromDetail" >
                <template slot="imgUrlForm" slot-scope="scope">
                    <div style="text-align: center;">
                        <viewer>
                            <img style="height: 100px;width: 100px"  id="imgPerson"/>
                        </viewer>
                    </div>
                </template>
            </avue-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="closeDialog">关 闭
                </el-button>
            </div>
        </el-dialog>
        <!--承接车辆详情弹窗-->
        <el-dialog title="车辆详情"  :visible.sync="cjclFormView" :fullscreen="true" :append-to-body="true" @close='closeDialog'>
            <avue-detail :option="cjclFormOption" v-model="cjclFromDetail" >
                <template slot="imgUrlForm" slot-scope="scope">
                    <div style="text-align: center;">
                    <viewer>
                    <img style="height: 250px;width: 250px"  id="imgCjcl"/>
                    </viewer>
                    </div>
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
    import bacsmap from '@/components/map/bacsMap'
    import {findCjclPage, findCyryPage, findOne,CountCyry,CountCjcl,
            getRyDetail,getClxqDetail,getRyImg,getClImg}
    from "@/api/portal/archive/mechanical/jxyxt";
    import  Csgz from './Csgz.vue'
    import {findLabel}  from  "@/api/portal/archive/dict/dict";
    import {formOption,clxqOption,cyryDict} from "@/const/crud/portal/archive/mechanical/jxyxt";
    import '@/styles/portal/archive/place-detail.scss?v=110'
    import  warnInfoTag from '@/views/portal/judged/warn/warnInfoTag'
    import {remote}  from  "@/api/admin/dict";
    export default {
        props: ["paramForm"],
        name: 'index',
        components:{Csgz,warnInfoTag,bacsmap},
        data () {
            return{
                address:{
                    //调用的接口
                    type:"matchForward",
                    //参数
                    data:{
                        "xz":"广州南站",
                        "province":"广东省"
                    }
                },
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
                ImgMap : new Map(),
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
                }, warnInfoParams: {
                    placeId: undefined,
                    sourceType: '2',
                    modelId: undefined
                },
                cyryData: [],
                cjclData: [],
                tagDictData:[],

            }
        },
        created(){
            this.params.menuId = this.paramForm.menuId;
            this.params.dwbh = this.paramForm.dwbh;
            this.warnInfoParams.placeId=this.paramForm.dwbh;
            this.getdwxx(this.params.dwbh)
            this.getJxCyry( )
            this.getJxCjcl( )
            this.getCountCyry( )
            this.getCountCjcl( )
            this.getTagDictData()

        },
        computed: {

        },
        watch: {
            paramForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.params.dwbh = this.paramForm.dwbh;
                    this.params.menuId = this.paramForm.menuId;
                    this.warnInfoParams.placeId=this.paramForm.dwbh;
                    this.getdwxx(this.params.dwbh)
                    this.getJxCyry()
                    this.getJxCjcl()
                    this.getCountCyry()
                    this.getCountCjcl()

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

/*            initRyImg(imgId,id) {
                let img=this.ImgMap.get(id);
                if('undefined'==typeof (img)||''===img||null===img) {
                    getRyImg(imgId).then(value => {
                        this.ImgMap.set(id,value);
                        let imgTag = document.getElementById(id);
                        imgTag.src=value
                    })
                }else{
                    let imgTag = document.getElementById(id);
                    imgTag.src=img
                }
            },*/

            initRyImg(id,imgId) {
                let img=this.ImgMap.get(id);
                if(null==img||'undefined'==typeof (img)||''==img) {
                    getRyImg(id,imgId)
                    this.ImgMap.set(id,id);
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
                getClImg(row.xxid,'imgCjcl')
            },

            getRyxqFormDetail(xxid) {
                getRyDetail(xxid).then(response => {
                    this.cyryFromDetail = response.data.data
                })
            },

            openRyxqDialog(row){
                this.cyryFormView = true
                this.getRyxqFormDetail(row.xxid)
                getRyImg(row.xxid,'imgPerson')
            },

            closeDialog(){
                this.cyryFormView=false
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


            getJxCjcl( ) {
                findCjclPage(Object.assign({
                    current: this.cjclpage.currentPage,
                    size: this.cjclpage.pageSize
                }, this.params)).then(response => {
                    this.cjclData = response.data.data.records
                    this.initcjclDict();
                })
            },

            getdwxx(dwbh) {
                findOne(dwbh).then(response => {
                    this.form = response.data.data
                    this.initDict()
                })
            },

            getJxCyry( ) {
                this.ImgMap.clear()
                this.cyryData = []
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
