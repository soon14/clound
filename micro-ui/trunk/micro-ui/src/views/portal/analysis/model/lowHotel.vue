<template>
 <div class="execution">
   <basic-container>
    <avue-crud :option="option"
               :data="data"
               v-model="form"
               ref="crud"
               :page="page"
               :table-loading="tableLoading">

        <template slot="menu" slot-scope="scope" >
            <el-button size="mini"
                       type="text"
                       icon="el-icon-view"
                       @click="viewFlag=true">查看
            </el-button>
        </template>

    </avue-crud>
   </basic-container>
    <el-dialog title="查看" fullscreen :visible.sync="viewFlag" :append-to-body="true">
        <avue-detail  :option="viewOption"  v-model="viewData" >
        </avue-detail>
        <div slot="footer"
             class="dialog-footer">
            <el-button type="primary" @click="viewFlag=false">关 闭
            </el-button>
        </div>
    </el-dialog>
    </div>
</template>


<script>

    export default {
        name:"lowHotel",
        data(){
            return {
                form:{},
                viewFlag:false,
                tableLoading: false,
                page: {
                    total: 3, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                viewData:
                    {
                        lgbh:'4401130094',
                        lgmc:'长隆酒店',
                        fr:'张某某',
                        fzr:'马某某',
                        zafzr:'马某某',
                        adress:'广东省广州市迎宾路',
                        lxdh:'8888888',
                        babdh:'',
                        fjs:'1234',
                        zzjgdm:'4401135700',
                        cws:'2300',
                        lcs:'8',
                        star:'5星',
                        dj:'特级',
                        zt:'其他',
                        ztgbrq:'2017-11-16',
                        ljsj:'2018-01-18 16:00:00',
                        lglx:'旅馆业',
                        lgqtbb:'20101201',
                        scfs:'未上传',
                        sspcs:'大石派出所',
                        remarks:'',
                        djfjs:'1200',
                        sjkfs:'928',
                        rzsj:'2018-03-01',
                        rzl:'28%'
                    },
                viewOption:{
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    page:false,
                    align:'center',
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn:false,
                    delBtn:false,
                    addBtn:false,
                    viewBtn:false,
                    menuWidth:'100',
                    card:true,
                    group: [
                        {
                            label: '基本信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: "旅馆编号",
                                    prop: "lgbh",
                                },
                                {
                                    label: "旅馆名称",
                                    prop: "lgmc"
                                },
                                {
                                    label:"旅馆地址",
                                    prop:"adress"
                                },{
                                    label:"旅馆类型",
                                    prop:"lglx"
                                }, {
                                    label:"行政区划",
                                    prop:"xzqh",
                                    dicData:[
                                        {
                                            label:"蓬江区",
                                            value:1
                                        },
                                        {
                                            label:"江海区",
                                            value:1
                                        },
                                        {
                                            label:"新会区",
                                            value:1
                                        },
                                        {
                                            label:"台山市",
                                            value:1
                                        },
                                        {
                                            label:"开平市",
                                            value:1
                                        },
                                        {
                                            label:"鹤山市",
                                            value:1
                                        },
                                        {
                                            label:"恩平市",
                                            value:1
                                        }
                                    ]
                                }, {
                                    label:"联机时间",
                                    prop:"ljsj"
                                }, {
                                    label:"上传方式",
                                    prop:"scfs"
                                },{
                                    label: "联系电话",
                                    prop: "lxdh"
                                }, {
                                    label:"等级",
                                    prop:"dj"
                                }, {
                                    label:"状态改变日期",
                                    prop:"ztgbrq"
                                },{
                                    label:"所属派出所",
                                    prop:"ssdw"
                                },{
                                    label:"星级",
                                    prop:"star"
                                },
                                {
                                    label:"旅馆前台版本",
                                    prop:"lgqtbb"
                                }, {
                                    label: "保安部电话",
                                    prop: "babdh"
                                },  {
                                    label:"状态",
                                    prop:"zt"
                                },
                                {
                                    label: "组织机构代码",
                                    prop: "zzjgdm"
                                },{
                                    label: "治安负责人",
                                    prop: "zafzr"
                                },
                                {
                                    label: "法人",
                                    prop: "fr"
                                },
                                {
                                    label: "负责人",
                                    prop: "fzr"
                                },
                                {
                                    label:"备注",
                                    prop:"remarks",
                                    span:12
                                }
                            ]
                        },
                        {
                            label: '规模情况',
                            prop: 'gmqk',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: "床位数",
                                    prop: "cws"
                                },
                                {
                                    label: "楼层数",
                                    prop: "lcs"
                                },

                                {
                                    label: "房间数",
                                    prop: "fjs"
                                },
                            ]
                        },
                        {
                            label: '入住情况',
                            prop: 'rzqk',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label:"登记房间数",
                                    prop:"djfjs"
                                },
                                {
                                    label:"实际开房数",
                                    prop:"sjkfs"
                                },
                                {
                                    label:"入住率",
                                    prop:"rzl"
                                }
                            ]
                        }

                    ]
                },
                data: [
                    {
                        lgbh:'4401130094',
                        lgmc:'长隆酒店',
                        fr:'张某某',
                        fzr:'马某某',
                        zafzr:'马某某',
                        adress:'广东省广州市迎宾路',
                        lxdh:'8888888',
                        babdh:'',
                        fjs:'1234',
                        zzjgdm:'4401135700',
                        cws:'2300',
                        lcs:'8',
                        star:'5星',
                        dj:'特级',
                        zt:'其他',
                        ztgbrq:'2017-11-16',
                        ljsj:'2018-01-18 16:00:00',
                        lglx:'旅馆业',
                        lgqtbb:'20101201',
                        scfs:'未上传',
                        sspcs:'大石派出所',
                        remarks:'',
                        djfjs:'1200',
                        sjkfs:'928',
                        rzsj:'2018-03-01',
                        rzl:'28%',
                    },
                    {
                        lgbh:'4401130094',
                        lgmc:'南美大酒店',
                        fr:'张某某',
                        fzr:'马某某',
                        zafzr:'马某某',
                        adress:'滨江西路',
                        lxdh:'8888888',
                        babdh:'',
                        fjs:'1234',
                        zzjgdm:'4401135700',
                        cws:'2300',
                        lcs:'8',
                        star:'4星',
                        dj:'特级',
                        zt:'其他',
                        ztgbrq:'2017-11-16',
                        ljsj:'2018-01-18 16:00:00',
                        lglx:'旅馆业',
                        lgqtbb:'20101201',
                        scfs:'未上传',
                        sspcs:'大石派出所',
                        remarks:'',
                        djfjs:'100',
                        sjkfs:'67',
                        rzsj:'2018-03-01',
                        rzl:'35%',
                    },
                    {
                        lgbh:'4401130094',
                        lgmc:'七天酒店',
                        fr:'张某某',
                        fzr:'马某某',
                        zafzr:'马某某',
                        adress:'广东省广州市迎宾路',
                        lxdh:'8888888',
                        babdh:'',
                        fjs:'1234',
                        zzjgdm:'4401135700',
                        cws:'2300',
                        lcs:'8',
                        star:'其他',
                        dj:'特级',
                        zt:'其他',
                        ztgbrq:'2017-11-16',
                        ljsj:'2018-01-18 16:00:00',
                        lglx:'旅馆业',
                        lgqtbb:'20101201',
                        scfs:'未上传',
                        sspcs:'大石派出所',
                        remarks:'',
                        djfjs:'666',
                        sjkfs:'510',
                        rzsj:'2018-03-01',
                        rzl:'30%',
                    }
                ],
                option:{
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    page:false,
                    align:'center',
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn:false,
                    delBtn:false,
                    addBtn:false,
                    viewBtn:false,
                    menuWidth:'100',
                    card:true,
                    column:[
                        {
                            label: "旅馆名称",
                            prop: "lgmc",
                            search:true,
                            span:6
                        },
                        {
                            label:"旅馆地址",
                            prop:"adress",
                            span:6
                        },
                        {
                            label:"登记房间数",
                            prop:"djfjs",
                            viewDisplay:true,
                            span:6
                        },
                        {
                            label:"实际开房数",
                            prop:"sjkfs",
                            viewDisplay:true,
                            span:6
                        },
                        {
                            label:"入住率",
                            prop:"rzl",
                            viewDisplay:true,
                            span:6
                        },
                        {
                            label:"行政区划",
                            prop:"xzqh",
                            search:true,
                            hide:true,
                            type:'select',
                            viewDisplay:true,
                            span:6,
                            dicData:[
                                {
                                    label:"蓬江区",
                                    value:1
                                },
                                {
                                    label:"江海区",
                                    value:1
                                },
                                {
                                    label:"新会区",
                                    value:1
                                },
                                {
                                    label:"台山市",
                                    value:1
                                },
                                {
                                    label:"开平市",
                                    value:1
                                },
                                {
                                    label:"鹤山市",
                                    value:1
                                },
                                {
                                    label:"恩平市",
                                    value:1
                                }
                            ]
                        },
                        {
                            label:"入住时间",
                            type:'datetimerange',
                            prop:"rzsj",
                            search:true,
                            hide:true,
                            viewDisplay:true,
                            span:6
                        }
                    ]
                }
            }
        },
        created(){
        },
        computed: {
        },
        methods:{
        }
    }
</script>