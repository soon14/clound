<template>
    <div class="info-cate">
        <div class="fileInfo_main">
            <!--基础信息-->
            <el-form class="form-main">
                <el-row class="form-main-title">
                    <span class="main-title-name" v-text="detailForm.hname"> </span>
                    <span class="main-title-score" v-text="detailForm.pointScore+' 分'">90分</span>
                    <span class="main-title-level main-title-score" v-text=" pointDict()">A级</span>
                    <place-collect :param="searchFrom"></place-collect>
                </el-row>
                <el-row class="form-main-body">
                    <el-col :span="18" class="form-body-l form-body-tabel">
                        <el-form label-width="135px" label-position="left">
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="行业类别">
                                        <span > 旅馆业</span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="旅馆类别">
                                        <span v-text="findLabelByValue(dictHotelTypeData,detailForm.hotelKind) "></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="企业状态">
                                        <span v-text=" findLabelByValue(dictHotelStateData,detailForm.state)  "></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>

                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="星级">
                                        <span v-text="findLabelByValue(dictHotelStarsData, detailForm.stars)"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="等级">
                                        <span v-text="findLabelByValue(dictHotelGradeData, detailForm.grade) "></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="联机时间">
                                        <span v-text="detailForm.linkDate"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>

                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="房间数">
                                        <span v-text="detailForm.roomNum"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="床位数">
                                        <span v-text="detailForm.floors"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="组织机构代码">
                                        <span v-text="detailForm.psorgan"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="管辖派出所">
                                        <span v-text="detailForm.deptName"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="责任民警名称">
                                        <span v-text="detailForm.socialDuty"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="责任民警警号">
                                        <span v-text="detailForm.socialDuty"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="法定负责人">
                                        <span v-text="detailForm.legalPerson"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="经营负责人">
                                        <span v-text="detailForm.principal"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="治安负责人">
                                        <span v-text="detailForm.socialDuty"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>

                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="详细地址">
                                        <span v-text="detailForm.haddress"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="联系电话">
                                        <span v-text="detailForm.telPhone"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>

                        </el-form>
                    </el-col>
                    <el-col :span="6" class="form-body-r">
                        <viewer >
                        <img src="/img/unit.jpg" alt="">
                        </viewer>
                    </el-col>
                </el-row>
            </el-form>
            <!--卡片切换-->
            <el-tabs type="border-card" class="card-tab">
                <el-tab-pane label="异常警告">
                    <warn-info-tag :param="warnInfoParams"></warn-info-tag>
                </el-tab-pane>
                <el-tab-pane label="平面图">
                    <img src="/img/unit.jpg" alt="" style="height: 170px;">
                </el-tab-pane>
            </el-tabs>
            <!--在岗人数列表-->
            <div class="table-box">
                <el-row class="table-box-top">
                    <el-col :span="4" class="box-top-item1">在岗人数 <span v-text="page.total"
                                                                       v-loading="tableLoading">0</span></el-col>
                    <el-col :span="4" class="box-top-item2">本月新上岗 <span v-text="countNum.inc"
                                                                        v-loading="tableLoading"></span>
                        <span>人</span><i
                                class="iconfontlogo aljiang1"></i></el-col>
                    <el-col :span="4" class="box-top-item3">本月离岗 <span v-text="countNum.quit"
                                                                       v-loading="tableLoading"></span><span>人</span><i
                            class="iconfontlogo aljiang"></i></el-col>
                    <el-col :span="12" class="box-top-item4">
                        <el-button type="text" @click="moreClick">更多>></el-button>
                    </el-col>
                </el-row>
                <el-table
                        :header-cell-style="{background:'#eee',color:'#000'}"
                        :cell-style="cellStyle"
                        :row-style="{height:'90px'}"
                        :data="employeeData"
                        style="width: 100%"
                        :page="page"
                        v-loading="tableLoading"
                >
                    <el-table-column
                            width="50"
                            type="index"
                            label="序号">
                    </el-table-column>
                    <el-table-column
                            prop="imgUrl"
                            label="照片"
                            align="center"
                            width="100"
                    >
                        <template  slot-scope="scope">
                            <viewer >
                           <img style="height:80px; width:80px;border-radius: 5px;"
                                 :id="scope.row.psid"
                                 :onload="initImg(scope.row.psid,scope.row.city)"
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
                            label="姓别"
                            :formatter ="sexDict"
                    >

                    </el-table-column>
                    <el-table-column
                            prop="csrq"
                            min-width="120"
                            label="出生日期">
                    </el-table-column>
                    <el-table-column
                            prop="gzgw"
                            label="岗位名称"
                            :formatter ="gwDict"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="lxfs1"
                            min-width="120"
                            label="联系电话">
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
                        :cell-style="facecellStyle"
                        :data="employeeData"
                        style="width: 100%">
                    <el-table-column
                            width="50"
                            type="index"
                            label="序号">
                    </el-table-column>
                    <el-table-column
                            prop="xm"
                            label="姓名">
                    </el-table-column>
                    <el-table-column
                            prop="zjhm"
                            width="200"
                            label="证件号码">
                    </el-table-column>
                    <el-table-column
                            prop="xb"
                            width="70"
                            label="姓别">
                    </el-table-column>
                    <el-table-column
                            prop="csrq"
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
                <div class="map_box" style="height:400px">
                    <bacsmap v-model="address"/>
                </div>
            </div>
        </div>
        <!--从业人员详情弹窗-->
        <el-dialog title="详情" :visible.sync="employeeView" fullscreen :append-to-body="true" @close='closeDialog'>
            <employee-detail v-bind:detail-row="employeeDetail"/>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="closeDialog">关 闭
                </el-button>
            </div>

        </el-dialog>
        <el-dialog title="人脸识别"
                   width="80%"
                   :visible.sync="dialogTablePlaceVisible">
            <el-table
                    class="table-face-body"
                    :header-cell-style="{background:'#eee',color:'#000'}"
                    :cell-style="facecellStyle"
                    :data="employeeData"
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
    import bacsmap from '@/components/map/bacsMap'
    import {findOneHotel} from "@/api/portal/archive/lvye/hotel";
    import {findEmployeePage, monthCount, getImg} from "@/api/portal/archive/lvye/employee";
    import employeeDetail from '../employee/employeeDetail'
    import {findLabel,getDict,getLabel}  from  "@/api/portal/archive/dict/dict";
    import {remote}  from  "@/api/admin/dict";
    import  placeCollect from '@/views/portal/archive/mechanical/fileInfo/cate/Csgz'
    import '@/styles/portal/archive/place-detail.scss?v=110'
    import  warnInfoTag from '@/views/portal/judged/warn/warnInfoTag'
    export default {
        components: {
            employeeDetail,
            placeCollect,
            warnInfoTag,
            bacsmap
        },
        name: 'hotelDetail',
        props: ["paramForm"],
        data() {
            return {
                address:{
                    //调用的接口
                    type:"matchForward",
                    //参数
                    data:{
                        "xz":"广州南站",
                        "province":"广东省"
                    }
                },
                detailForm: {},
                imgMap : new Map(),
                employeeView: false,
                employeeDetail: {},
                searchFrom: {
                    dwbh: undefined,
                    menuId: undefined,
                    lyxt:'3'
                },
                warnInfoParams: {
                    placeId: undefined,
                    sourceType: '3',
                    modelId: undefined
                },
                countNum: {
                    quit: 0,
                    inc: 0
                },
                tableLoading: true,
                collectLoading:false,
                appleToBody: true,
                url: undefined,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 5 // 每页显示多少条
                },
                dialogTableVisible: false,
                dialogTablePlaceVisible: false,
                employeeData: [],
                sexDictData:[],
                gwDictData:[],
                tagDictData:[],
                pointTypeData:[],
                dictHotelTypeData:[],
                dictHotelStateData:[],
                dictHotelStarsData:[],
                dictHotelGradeData:[],

            }
        },
        created() {
            this.getTagDictData()
            this.employeeDictData()
            this.hotelDictData()
            this.searchFrom.dwbh = this.paramForm.unitId;
            this.searchFrom.menuId = this.paramForm.menuId;
            this.warnInfoParams.placeId=this.paramForm.unitId;
            this.findHotelDetail(this.searchFrom);
            this.findEmployee(this.page, this.searchFrom);
            this.employeeCount(this.searchFrom.dwbh);
        },
        watch: {
            paramForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.searchFrom.dwbh = this.paramForm.unitId;
                    this.searchFrom.menuId = this.paramForm.menuId;
                    this.warnInfoParams.placeId=this.paramForm.unitId;
                    this.findHotelDetail(this.searchFrom);
                    this.findEmployee(this.page, this.searchFrom);
                    this.employeeCount(this.searchFrom.dwbh);
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

            closeDialog(){
                this.employeeView=false
            },
            sexDict(row, column){
                return getLabel(this.sexDictData,row.xb)
            },
            gwDict(row, column){
                return getLabel(this.gwDictData,row.gzgw)
            },
            pointDict(){
               return getLabel( this.pointTypeData, this.detailForm.pointType)
            },
            viewEmployeeDialog(row) {

                this.employeeView = true
                this.employeeDetail = row


            },
            employeeDictData(){
                getDict('GZGW','ly').then(response => {
                    this.gwDictData = response.data.data||[];
                }).catch((err) => {});
                getDict('0122','ly').then(response => {
                    this.sexDictData = response.data.data||[];
                }).catch((err) => {});

            },
            hotelDictData(){
                remote('jfdj').then(response => {
                    this.pointTypeData = response.data.data||{}
                }).catch((err) => {});
                getDict('LGLB','ly').then(response => {
                    this.dictHotelTypeData = response.data.data||[];
                }).catch((err) => {});
                getDict('0209','ly').then(response => {
                    this.dictHotelStateData = response.data.data||[];
                }).catch((err) => {});
                getDict('0210','ly').then(response => {
                    this.dictHotelStarsData = response.data.data||[];
                }).catch((err) => {});
                getDict('0211','ly').then(response => {
                    this.dictHotelGradeData = response.data.data||[];
                }).catch((err) => {});


            },

             initImg(id, code) {
                let img=this.imgMap.get(id);
                if('undefined'==typeof (img)||''===img||null===img) {
                    getImg(id, code).then(value => {
                        this.imgMap.set(id,value);
                        let imgTag =document.getElementById(id);
                        imgTag.src=value
                    })
                }else{
                    let imgTag =document.getElementById(id);
                    imgTag.src=img
                }
            },
            findHotelDetail(form) {
                findOneHotel(Object.assign({}, form)).then(response => {
                    this.detailForm = response.data.data;
                })
            },
            findEmployee(page, form) {
                this.imgMap.clear()
                this.tableLoading = true
                this.employeeData=[]   //必须先清空数据、不然会把上一次的旧数据重复请求图片
                findEmployeePage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    quitType: '20'
                }, form)).then(response => {
                    this.employeeData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false

                })
            },
            employeeCount(unitId) {
                monthCount(unitId).then(response => {
                    this.countNum = response.data.data

                })
            },


            moreClick() {
                this.$emit("moreClick", 1);
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
                } else {
                    return ''
                }
            },
            facecellStyle({row, column, rowIndex, columnIndex}) {
                if (columnIndex === 2) {
                    return 'color:#6aaff8'
                } else if (columnIndex === 0) {
                    return 'text-align:center'
                } else {
                    return ''
                }
            },

        }
    }
</script>


