<template>
    <div class="info-cate">
        <div class="fileInfo_main">
            <!--基础信息-->
            <el-form class="form-main">
                <el-row class="form-main-title">
                    <span class="main-title-name" v-text="detailForm.hname"> </span>
                    <span class="main-title-score" v-text="detailForm.pointScore+' 分'">90分</span>
                    <span class="main-title-level main-title-score" v-text=" pointDict()">A级</span>
                    <a href="javascript:void(0)"  v-loading="collectLoading">
                        <span class="main-title-wsc" @click="editCollect"><i class="iconfontlogo alicon_shoucang"></i>未收藏</span>
                    </a>
                   <!-- <a href="#" v-if="yscshow"><span class="main-title-ysc" @click="editCollect"><i
                            class="iconfontlogo alicon_shoucang"></i>已收藏</span></a>
-->

                </el-row>
                <el-row class="form-main-body">
                    <el-col :span="18" class="form-body-l form-body-tabel">
                        <el-form label-width="135px" label-position="left">
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="行业类别">
                                        <span v-text="detailForm.placeType"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="旅馆类别">
                                        <span v-text="detailForm.hotelKind"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="企业状态">
                                        <span v-text="detailForm.state "></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>

                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="星级">
                                        <span v-text="detailForm.stars"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="等级">
                                        <span v-text="detailForm.grade"></span>
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
                                        <span v-text="detailForm.psCode"></span>
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
                        <img src="/img/unit.jpg" alt="">
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

                           <img style="height:80px; width:80px;border-radius: 5px;"
                                 src="/img/people.png"
                                 :id="scope.row.psid"
                                 :src="initImg(scope.row.psid,scope.row.city)"/>

                        </template>
                    </el-table-column>


                    <el-table-column
                            prop="xm"
                            label="姓名">
                        <template slot-scope="scope">
                            <button type="text" class="el-button el-button--text el-button--small"
                                    @click="viewEmployeeDialog(scope.row)">
                                {{scope.row.xm}}
                            </button>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="zjhm"
                            width="200"
                            label="证件号码">
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
                            prop="rylb"
                            label="从业人员类别">
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
                <div class="map_box">

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
    import {findOneHotel} from "@/api/portal/archive/lvye/hotel";
    import {findEmployeePage, monthCount, getImg} from "@/api/portal/archive/lvye/employee";
    import '@/styles/portal/archive/place-detail.scss'
    import employeeDetail from '../employee/employeeDetail'
    import {findLabel,getDict,getLabel}  from  "@/api/portal/archive/dict/dict";
    import {remote}  from  "@/api/admin/dict";
    export default {
        components: {
            employeeDetail
        },
        name: 'hotelDetail',
        props: ["paramForm"],
        data() {
            return {
                detailForm: {},
                imgMap : new Map(),
                employeeView: false,
                employeeDetail: {},
                searchFrom: {
                    dwbh: undefined,
                    menuId: undefined,
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
                pointTypeData:[],
            }
        },
        created() {

            this.employeeDictData()
            this.hotelDictData()
            this.searchFrom.dwbh = this.paramForm.unitId;
            this.searchFrom.menuId = this.paramForm.menuId;
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
                    this.findHotelDetail(this.searchFrom);
                    this.findEmployee(this.page, this.searchFrom);
                    this.employeeCount(this.searchFrom.dwbh);


                },
                deep: true
            },
        },
        methods: {
            closeDialog(){
                this.employeeView=false
                this.imgMap.clear()
            },
            editCollect(){
                this.$message({
                    type:'success',
                    dangerouslyUseHTMLString: true,
                    duration:4000,
                    message: '操作成功'
                });
                this.collectLoading=true
                alert('收藏信息')
                this.collectLoading=false
            },


            initDict(){

                findLabel('LGLB','ly',this.detailForm.hotelKind).then(value=>{
                    this.detailForm.hotelKind=value
                }).catch((err) => {});
                findLabel('0209','ly',this.detailForm.state).then(value=>{
                    this.detailForm.state=value
                }).catch((err) => {});
                findLabel('0210','ly',this.detailForm.stars).then(value=>{
                    this.detailForm.stars=value
                }).catch((err) => {});
                findLabel('0211','ly',this.detailForm.grade).then(value=>{
                    this.detailForm.grade=value
                }).catch((err) => {});


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
            },

            initImg(id, code) {
                let img=this.imgMap.get(id);
                if(null===img||'undefined'===typeof (img)||''===img) {
                    getImg(id, code)
                    this.imgMap.set(id,id);
                }
            },
            findHotelDetail(form) {
                findOneHotel(Object.assign({}, form)).then(response => {
                    this.detailForm = response.data.data;

                    this. initDict()
                })
            },
            findEmployee(page, form) {
                this.imgMap.clear()
                this.tableLoading = true
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


