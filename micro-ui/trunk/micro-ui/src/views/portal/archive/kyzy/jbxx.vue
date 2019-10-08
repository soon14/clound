<template>
    <div class="info-cate">
        <div class="fileInfo_main">
            <!--基础信息-->
            <el-form  class="form-main">
                <el-row  class="form-main-title">
                    <span class="main-title-name"> {{this.form.name}}</span>
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
                                    <el-form-item label="行业类别:">
                                        刻印章业
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="单位类别:">
                                        {{dict.getLabel(dict.type,this.form.type)}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="门店类型:">
                                        {{dict.getLabel(dict.shopType,this.form.shopType)}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="营业执照编号:">
                                        {{this.form.businessLicenseNumber}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="审批单位:">
                                        {{this.form.approveCodeName}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="发证日期:">
                                        {{this.form.issuingDate}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="有效起始时间:">
                                        {{this.form.startDate}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="有效结束时间:">
                                        {{this.form.endDate}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="电子印章:">
                                        {{dict.getLabel(dict.isStart,this.form.eseal)}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="法人姓名:">
                                        {{this.form.legalName}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="法人证件类型:">
                                        {{dict.getLabel(this.idcardTypeDict,this.form.idcardType)}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="法人证件号码:">
                                        {{this.form.idcardNumber}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="经营地址:">
                                        {{this.form.address}}
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="联系电话:">
                                        {{this.form.phone}}
                                    </el-form-item>
                                </el-col>
                            </el-col>
                        </el-form>
                    </el-col>
                    <el-col :span="6" class="form-body-r">
                        <viewer>
                        <img src="../../../../../public/img/portal/7day1.png" alt="">
                        </viewer>
                    </el-col>
                </el-row>
            </el-form>
            <!--卡片切换-->
            <el-tabs type="border-card" class="card-tab">
                <el-tab-pane label="异常警告">
                    <warn-info-tag :param="warnInfoParams"></warn-info-tag>
                </el-tab-pane>
            </el-tabs>
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
                    <bacsmap v-model="address"/>
                </div>
            </div>
        </div>
        <!--弹框-->
        <!--<el-dialog title="人脸识别"
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
        </el-dialog>-->
    </div>
</template>

<script>
    import bacsmap from '@/components/map/bacsMap'
    import {findById} from "@/api/portal/archive/kyzy/kyzy";
    import  Csgz from '../mechanical/fileInfo/cate/Csgz.vue';
    import {getDict}  from  "@/api/portal/archive/dict/dict";
    import {kyzyDict} from "@/const/crud/portal/archive/kyzy/kyzy";
    import '@/styles/portal/archive/place-detail.scss?v=110'
    import  warnInfoTag from '@/views/portal/judged/warn/warnInfoTag'
    export default {
        name: 'index',
        props:["searchForm"],
        components:{
            Csgz,
            warnInfoTag,
            bacsmap
        },
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
                form:{},
                params:{
                    menuId:'',
                    id:''
                },
                warnInfoParams: {
                    placeId: undefined,
                    sourceType: '4',
                    modelId: undefined
                },
                dict: kyzyDict,
                select:2,
                dialogTableVisible: false,
                dialogTablePlaceVisible:false,
                dialogVideoVisible:false,
                idcardTypeDict:[],
                tableData:[
                    {
                        //imgUrl:require("../../../../../../public/img/person.png"),
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
                        //imgUrl:require("../../../../../../public/img/person.png"),
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
                       // imgUrl:require("../../../../../../public/img/person.png"),
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
                        //imgUrl:require("../../../../../../public/img/person.png"),
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
                    }, ]
            }
        },
        created(){
            this.params.menuId = this.searchForm.menuId;
            this.params.id = this.searchForm.id;
            this.warnInfoParams.placeId=this.searchForm.id;
            this.getDwxx(this.params.id);
            this.initDict();
        },
        watch:{
            searchForm:{
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler (newParam) {
                    this.params.menuId = newParam.menuId;
                    this.params.id = newParam.id;
                    this.warnInfoParams.placeId=this.searchForm.id;
                },
                deep:true
            },
            'params.id':{
                handler(newVal){
                    this.getDwxx(newVal)
                    this.initDict();
                },
                deep:true
            }
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
            getDwxx(id){
                findById(id).then(response => {
                    this.form = response.data.data
                })
            },
            initDict(){
                getDict('idcard_type','kyz').then(response => {
                    this.idcardTypeDict = response.data.data||[];
                }).catch((err) => {});
            }
        },
    }
</script>
