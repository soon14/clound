<template>
    <div class="unusual">
        <basic-container>
            <avue-crud ref="crud"
                       :page="page"
                       :option="tableOption"
                       :data="data1"
                       @row-update="handleUpdate"
                       @row-save="handleSav"
                       @row-del="handleDelete"
                       @date-change="dateChange"
                       v-model="form">
                <template slot="menuLeft">
                    <el-button
                            class="filter-item"
                            @click="dialogVisible = true"
                            size="small"
                            type="primary"
                            icon="el-icon-download">导出
                    </el-button>
                </template>

                <template slot="menu" slot-scope="scope" >
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-view"
                               @click="handleView(scope.row,scope.index)">查看明细
                    </el-button>
                </template>

                <template slot="infoForm" slot-scope="scope">
                    <avue-crud :option="infoOption" :data="infoData"
                               :page="page"
                               @row-update="handleUpdate"
                               @row-save="handleSav"
                               @row-del="handleDelete()">
                        <template slot-scope="scope" slot="menu">
                            <el-button size="mini"
                                       type="text"
                                       icon="el-icon-view"
                                       @click="getView(scope.row,scope.index)">查看
                            </el-button>
                        </template>
                    </avue-crud>
                </template>

                <template slot="mapForm" slot-scope="scope" :span="12">
                    <div class="statistical">
                        <div id="figure" style="width:100%;height:400px;"></div>
                    </div>
                </template>

                <template slot="detailForm" slot-scope="scope" :span="12">
                    <avue-detail :option="detailoption" v-model="form"></avue-detail>
                </template>
            </avue-crud>
            <el-dialog
                    title="详情"
                    fullscreen :visible.sync="dialogVisible"
                   >
                <div>
                    <div class="from-box">
                        <h3><i class="el-icon-info"></i>人员基本信息</h3>
                        <el-form  class="form-main">
                            <el-row>
                                <el-col :span="10">
                                    <el-row class="from-row">
                                        <el-col :span="12"><el-form-item label="姓名:">
                                            黄小小
                                        </el-form-item></el-col>
                                        <el-col :span="12" class="from-row"> <el-form-item label="性别:">
                                            女
                                        </el-form-item></el-col>
                                    </el-row>
                                    <el-row class="from-row">
                                        <el-col :span="12"><el-form-item label="民族:">
                                            汉
                                        </el-form-item></el-col>
                                        <el-col :span="12" class="from-row"> <el-form-item label="身份证:">
                                            121625327368278
                                        </el-form-item></el-col>
                                    </el-row>
                                    <el-row class="from-row">
                                        <el-form-item label="地址:">
                                            广东省江门市蓬江区港口一路单井里1好
                                        </el-form-item>
                                    </el-row>
                                </el-col>
                                <el-col :span="10">
                                    <el-row class="from-row">
                                        <el-col :span="24" ><el-form-item label="出生日期:">
                                            2010-11-12
                                        </el-form-item></el-col>
                                    </el-row>
                                    <el-row class="from-row">
                                        <el-col :span="12"> <el-form-item label="户籍:">
                                            广州
                                        </el-form-item></el-col>
                                    </el-row>
                                </el-col>
                                <el-col :span="4">
                                    <img src="../../../../../public/img/person.png" alt="">
                                </el-col>
                            </el-row>
                        </el-form>
                    </div>
                    <div class="from-box">
                        <h3><i class="el-icon-info"></i>案件信息</h3>
                        <el-form  class="form-main">
                            <el-row class="from-row"><!--行-->
                                <el-col :span="12"><el-form-item label="案件编号:">
                                    AAAAA1234567889
                                </el-form-item></el-col>
                                <el-col :span="12" class="from-row"> <el-form-item label="案件名称:">
                                    周某某贩卖1505儿童
                                </el-form-item></el-col>
                            </el-row>
                            <el-row class="from-row">
                                <el-col :span="12"><el-form-item label="案别:">
                                    贩卖儿童
                                </el-form-item></el-col>
                                <el-col :span="12" class="from-row"> <el-form-item label="案件状态:">
                                    以处罚
                                </el-form-item></el-col>
                            </el-row>
                            <el-row class="from-row">
                                <el-col :span="12" class="from-row"> <el-form-item label="案发时间:">
                                    以处罚
                                </el-form-item></el-col>
                                <el-col :span="12" class="from-row"> <el-form-item label="接警单位:">
                                    江门公安局
                                </el-form-item></el-col>
                            </el-row>
                            <el-row class="from-row">
                                <el-col :span="24" ><el-form-item label="案发地点:">
                                    江门蓬江区1505
                                </el-form-item></el-col>
                            </el-row>
                            <el-row class="from-row">
                                <el-col :span="24" ><el-form-item label="案情简介:">
                                    周某某拐卖1505儿童，被群众举报
                                </el-form-item></el-col>
                            </el-row>
                        </el-form>
                    </div>
                    <div class="table-box">
                        <avue-crud :data="data" :option="tableData">
                            <template slot="zp" slot-scope="scope" >
                                <img src="../../../../../public/img/person.png">
                            </template>
                        </avue-crud>
                    </div>
                </div>
            </el-dialog>

        </basic-container>
    </div>
</template>

<script>
   import exportFile from "@/page/export/exportFile"
    let dict ={
        sydw:[
            {label: '江门市公安局', value: '0'},
            {label: '甘化社区警务区', value: '1',},
            {label: '海傍社区警务区', value: '2',},
            {label: '常安社区警务区',value: '3',}
        ],
        dj:[{label: 'A', value: 'A'},
            {label: 'B', value: 'B'},
            {label: 'C', value: 'C'},],
        sycslb:[
            {"label":"旅馆业","value":"01"},
            {"label":"刻印章业","value":"02"},
            {"label":"旧货业","value":"03"},
            {"label":"印刷业","value":"04"},
            {"label":"机动车修理业","value":"05"},
            {"label":"开锁业","value":"06"},
            {"label":"其他管理行业","value":"07"},
            {"label":"歌舞娱乐场所","value":"08"},
            {"label":"按摩服务业","value":"09"},
            {"label":"游戏游艺场所","value":"10"},
            {"label":"录像放映场所","value":"11"},
            {"label":"上网服务场所","value":"12"},
            {"label":"体育场馆","value":"13"},
            {"label":"广场公园","value":"14"},
            {"label":"其他场所","value":"15"},
            {"label":"报废机动车拆解业","value":"16"},
            {"label":"典当业","value":"17"},
            {"label":"废旧金属业","value":"18"}
        ],
    }
    export default {
        components: { "exportFile" : exportFile},
        data() {
            return {
                dialogVisible:false,
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                dialogVisible: false,
                exportUrl: 'portal/point/rating_result/export',
                fileName:  '等级评定结果信息',
                exprotFields : {
                    ranking:'排名',
                    csmc: '场所名称',
                    ssnd: '所属年度',
                    cslb: '场所类别',
                    gxpcs:'管辖派出所',
                    jf:'积分',
                },
                form: {},
                tableOption:{
                    indexLabel: '排名',
                    index: true,
                    editBtn:false,
                    addBtn: false,
                    viewBtn: false,
                    delBtn:false,
                    border: true,
                    dateBtn:true,
                    card:true,
                    dialogWidth:'100%',
                    dialogHeight:'100%',
                    dialogFullscreen:true,
                    column: [
                        {
                            label:'',
                            hide:true,
                            formslot: true,
                            slot: true,
                            prop:'detail',
                            span:26,
                        },
                        {
                            label:'',
                            hide:true,
                            formslot: true,
                            slot: true,
                            prop:'map',
                            span:26,
                        },
                        {
                            label: '场所名称',
                            prop: 'csmc',
                            search:true,
                            formslot: true,
                            display:false,
                        },
                        {
                            label: '场所类别',
                            prop: 'sycslb',
                            type:'select',
                            display:false,
                            dicData: dict.sycslb,
                        },
                        {
                            label: '所属辖区',
                            prop: 'ssxq',
                            type:'select',
                            search:true,
                            display:false,
                            formslot: true,
                            dicData: dict.sydw,
                        },
                        {
                            label: '管辖单位',
                            prop: 'ssxq',
                            type:'select',
                            search:true,
                            display:false,
                            dicData: dict.sydw,
                        },
                        {
                            label: '出现时间',
                            prop: 'cxsj',
                            display:false,
                        },
                        {
                            label: '重点人员数量',
                            prop: 'zdrysl',
                            display:false,
                        },
                        {
                            label: "时间日期范围",
                            value: 'datetimerange',
                            type: 'datetimerange',
                            hide:true,
                            span:24,
                            row:true,
                            display:false,
                            startPlaceholder: '时间日期开始范围自定义',
                            endPlaceholder: '时间日期结束范围自定义',
                        },
                         {
                            labelWidth:0,
                            label: '',
                            prop: 'info',
                            span:24,
                            hide:true,
                            formslot:true,
                        }
                    ]
                },
                data1:[
                    {
                        csmc:'七天酒店',
                        ssnd:'2018',
                        sycslb:'01',
                        ssxq:'0',
                        gxpcs:'东街派出所',
                        zdrysl:11111,
                        cxsj:"2019-1-11",
                        jf:'95',
                        djpd:'A',
                        info:[
                            {
                                zbmc:'涉黄人员入住',
                                zblx:'自动',
                                fz:'2',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉赌人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉毒人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'人证不一致',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },

                        ]
                    },
                    {
                        csmc:'全家酒店',
                        ssnd:'2018',
                        sycslb:'01',
                        ssxq:'0',
                        gxpcs:'东街派出所',
                        zdrysl:3311,
                        cxsj:"2019-4-15",
                        jf:'95',
                        djpd:'A',
                        info:[
                            {
                                zbmc:'涉黄人员入住',
                                zblx:'自动',
                                fz:'2',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉赌人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉毒人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'人证不一致',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },

                        ]
                    },
                ],
                infoOption:{
                    addBtn:false,
                    delBtn:false,
                    editBtn:false,
                    viewBtn:false,
                    dialogWidth:'100%',
                    dialogHeight:'100%',
                    dialogFullscreen:true,
                    column:[
                        {
                            label:'人员类型',
                            prop:'zbmc',
                            span:12,
                        },
                        {
                            label:'最后出现时间',
                            prop:'zblx',
                            span:12,
                        },
                        {
                            label:'抓拍次数',
                            prop:'fz',
                            span:12,
                        },
                    ]
                },
                detailoption:{
                    icon:'el-icon-info',
                    label: '场所信息',
                    prop: 'group',
                    column:[
                        {
                            label: '场所名称',
                            prop: 'csmc',
                        },
                        {
                            label: '场所类别',
                            prop: 'sycslb',
                            type:'year',
                        },
                        {
                            label: '场所类别',
                            prop: 'sycslb',
                            type:'select',
                            dicData: dict.sycslb,
                        },
                        {
                            label: '所属辖区',
                            prop: 'ssxq',
                            dicData: dict.sydw,
                        },
                        {
                            label: '管辖单位',
                            prop: 'gxdw',
                        },
                    ]},
                option : {
                    title: {
                        text: '折线图'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['涉赌','涉毒','涉稳','在逃人员','肇事肇祸精神病人','重大刑事前科人员','涉恐']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data:[],
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name:'涉赌',
                            type:'line',
                            stack: '总量',
                            data:[120, 132, 101, 134, 90, 230, 210, 230, 210,120, 132, 101]
                        },
                        {
                            name:'涉毒',
                            type:'line',
                            stack: '总量',
                            data:[220, 182, 191, 234, 290, 330, 310, 230, 210,234, 290, 330]
                        },
                        {
                            name:'涉稳',
                            type:'line',
                            stack: '总量',
                            data:[150, 232, 201, 154, 190, 330, 410, 230, 210,410, 230, 210]
                        },
                        {
                            name:'在逃人员',
                            type:'line',
                            stack: '总量',
                            data:[320, 332, 301, 334, 390, 330, 320, 230, 210,1213.4546,123]
                        },
                        {
                            name:'肇事肇祸精神病人',
                            type:'line',
                            stack: '总量',
                            data:[820, 932, 901, 934, 1290, 1330, 1320, 230, 210,456,3423,4545]
                        },
                        {
                            name:'涉恐',
                            type:'line',
                            stack: '总量',
                            data:[340, 952, 91, 934, 1590, 1730, 2320, 230, 210,343,876,343]
                        },
                        {
                            name:'重大刑事前科人员',
                            type:'line',
                            stack: '总量',
                            data:[320, 532, 701, 964, 1890, 1330, 1320, 230, 210,322,532,344]
                        },
                    ]
                },
                hours:[],
                tableData:{
                    indexLabel: '序号',
                    index:true,
                    menuAlign: 'left',
                    addBtn:false,
                    page:false,
                    menu:false,
                    border: true,
                    columnBtn:false,
                    refreshBtn:false,
                    align:'center',
                    placeholder1:1,
                    placeholder2:"字符",
                    column:[
                        {
                            label:'照片',
                            prop:'zp',
                            align: 'center',
                            slot:true,
                        },
                        {
                            label:'姓名',
                            prop:'xm',
                            align: 'left',
                        },
                        {
                            label:'性别',
                            prop:'xb',
                            width:80,
                            align: 'left',
                        },
                        {
                            label:'身份证号码',
                            prop:'sfzhm',
                            align: 'left',
                        },
                        {
                            label:'人员类型',
                            prop:'rylx',
                            align: 'left',
                        },
                        {
                            label:'出现时间',
                            prop:'cxsj',
                            align: 'left',
                        }
                    ]
                },
                data: [
                    {
                        xm:'黄某某',
                        xb:19,
                        sfzhm:4512319983456,
                        rylx:"涉黄",
                        cxsj:"2019-11-11 15:33:12",
                    },
                    {
                        xm:'黄某某',
                        xb:19,
                        sfzhm:4512319983456,
                        rylx:"涉黄",
                        cxsj:"2019-11-11 15:33:12",
                    },
                    {
                        xm:'黄某某',
                        xb:19,
                        sfzhm:4512319983456,
                        rylx:"涉黄",
                        cxsj:"2019-11-11 15:33:12",
                    },
                ],
             }
        },
        computed:{
            infoData(){
                return this.form.info
            }
        },
        created(){
            this.getTime();
        },
        methods: {
            drawLine(id,data) {
                let myChart =echarts.init(document.getElementById(id));
                myChart.setOption(data);
            },
            dateChange(val){
                this.$message.success(val);
            },

            handleChange(column) {
                this.type = column
            },
            handleView(row, index) {
               this.$refs.crud.rowView(row, index);
                this.$nextTick(()=>{
                    setTimeout(()=>{
                        this.option.xAxis.data = this.hours;
                        this.drawLine('figure',this.option);
                    },100)
                })
            },
            getView(row, index){
                this.dialogVisible=true;

            },
            handleEdit (row, index) {
                this.$refs.crud.rowEdit(row, index);
            },

            handleSav(row, done){
                done();
                this.$notify({
                    title: '成功',
                    message: '新增成功',
                    type: 'success',
                    duration: 2000
                })
            },
            handleUpdate(row, index, done) {
                this.form.id=row.id
                putObj(this.form).then(() => {
                    this.getList()
                    done();
                    this.$notify({
                        title: '成功',
                        message: '更新成功',
                        type: 'success',
                        duration: 2000
                    })
                }).catch(() => {
                });
            },
            handleDelete() {
                var _this = this
                this.$confirm('是否确认删除该记录吗"?', '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    _this.$message({
                        showClose: true,
                        message: '删除成功',
                        type: 'success'
                    })
                }).catch(function (err) {
                })
            },
            getTime(){
                let data =new Date();
                for(let i=12;i>0;i--){
                    let hours= new Date(data.getTime()-3600000*i).getHours();
                    this.hours.push(hours)
                }
            },
        }
    }
</script>
<style lang="scss">
    .unusual{
        .from-box{
            width:90%;
            margin: 0 auto;
            box-shadow: 2px 1px 8px rgba(0,0,0,.15);
            padding:0 20px;
            border-radius: 5px;
            box-sizing: border-box;
            margin-bottom: 20px;
            h3{
                border-bottom: 1px solid #eee;
                height: 50px;
                line-height: 50px;
            }
            .el-icon-info{
                height: 50px;
                line-height: 50px;
                font-size:25px;
                vertical-align: middle;
                margin-right: 10px;
            }
            img{
                display: block;
                height: 120px;
                width: 90px;
                margin: 20px auto;
                cursor: pointer;
                transition: all 0.6s;
            }
            img:hover{
                transform: scale(2.2);
            }
        }
    }
</style>