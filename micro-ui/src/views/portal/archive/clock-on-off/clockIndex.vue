<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-crud :option="option" :data="data"
                       :page="page"

                       v-model="form">

                <template slot="xm"
                          slot-scope="scope">
                    <button type="button" class="el-button el-button--text el-button--small"  >{{scope.row.xm}}</button>
                </template>
                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="openDialog(scope.row.xm)">预览
                    </el-button>
                </template>

            </avue-crud>
        </basic-container>

        <el-dialog :title="title"  :visible.sync="recordView" append-to-body>
            <avue-detail :option="option" v-model="fromDetail" >
            </avue-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="recordView=false">关 闭
                </el-button>
            </div>
        </el-dialog>


    </div>

</template>


<script>
    export default {
        data() {
            return {
                page: {
                    total: 6, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                recordView:false,
                title:'预览',
                form: {},
                fromDetail:{
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    sbsj:'2017-01-02 12:30',
                    xbsj:'2017-01-03 12:30',
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    djsj:'2017-01-02 12:30',
                    rzsj:'2016-01-02 12:30',
                    dwmc:'江门七天酒店',
                },
                data: [{
                    xm:'任某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    sbsj:'2017-01-02 12:30',
                    xbsj:'2017-01-03 12:30',
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    djsj:'2017-01-02 12:30',
                    rzsj:'2016-01-02 12:30',
                    dwmc:'江门七天酒店',

               },{

                    xm:'李某某',
                    xmpy:'renmoumou',
                    mz:'汉族',
                    xb:'男',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    sbsj:'2017-01-02 12:30',
                    xbsj:'2017-01-03 12:30',
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    djsj:'2017-01-02 12:30',
                    rzsj:'2016-01-02 12:30',
                    dwmc:'江门七天酒店',

                },{

                    xm:'佘某某',
                    xmpy:'shemoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    sbsj:'2017-01-02 12:30',
                    xbsj:'2017-01-03 12:30',
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    djsj:'2017-01-02 12:30',
                    rzsj:'2016-01-02 12:30',
                    dwmc:'江门七天酒店',

                },{

                    xm:'覃某某',
                    xmpy:'qinmoumou',
                    mz:'汉族',
                    xb:'男',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    sbsj:'2017-01-02 12:30',
                    xbsj:'2017-01-03 12:30',
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    djsj:'2017-01-02 12:30',
                    rzsj:'2016-01-02 12:30',
                    dwmc:'江门七天酒店',

                },{

                    xm:'翁某某',
                    xmpy:'wengmoumou',
                    mz:'汉族',
                    xb:'男',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    sbsj:'2017-01-02 12:30',
                    xbsj:'2017-01-03 12:30',
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    djsj:'2017-01-02 12:30',
                    rzsj:'2016-01-02 12:30',
                    dwmc:'江门七天酒店',

                },{

                    xm:'黄某某',
                    xmpy:'huangmoumou',
                    mz:'汉族',
                    xb:'女',
                    xzqh:'4401',
                    scrq:'2000-11-22',
                    jgdz:'广东省广州市天河区',
                    lgmc:'江门七天酒店',
                    lgdz:'广东省江门市莲江区环市街道',
                    sspcs:'仓后派出所',
                    lxdh:'0750-8812344',
                    sbsj:'2017-01-02 12:30',
                    xbsj:'2017-01-03 12:30',
                    zjlx:'身份证',
                    zjhm:'440101199909092234',
                    djsj:'2017-01-02 12:30',
                    rzsj:'2016-01-02 12:30',
                    dwmc:'江门七天酒店',

            }],
                option: {
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    viewBtn: false,
                    delBtn: false,
                    editBtn:false,
                    card:true,
                    column: [
                        {
                            label: '姓名',
                            prop: 'xm',
                            search:true,
                        },{
                            label: '证件类型',
                            prop: 'zjlx',
                            type:'select',
                            hide:true,
                        },{
                            label: '证件号码',
                            prop: 'zjhm',
                            width:160,
                            search:true,
                        },{
                            label: '性别',
                            prop: 'xb',
                            type:'select'
                        }, {
                            label: '姓名拼音',
                            prop: 'xmpy',
                            hide:true,
                        },{
                            label: '民族',
                            prop: 'mz',
                            type:'select',
                            hide:true
                        },{
                            label: '入职时间',
                            prop: 'rzsj',
                        },

                        {
                            label: '上班时间',
                            prop: 'sbsj',
                        },{
                            label: '下班时间',
                            prop: 'xbsj',
                        },
                        {
                            label: '出生日期',
                            prop: 'scrq',
                            hide:true
                        },
                        {
                            label: '籍贯地址',
                            prop: 'jgdz',
                            overHidden:true,
                        },{
                            label: '单位名称',
                            prop: 'dwmc',
                        },
                        {
                            label: '打卡时间',
                            prop: 'dasj',
                            search:true,
                            hide:true,
                            editDisplay:false,
                            viewDisplay:false,
                            addDisplay:false,
                            type: "daterange",
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                        }
                    ],
                },
            }
        },
        created(){

        },
        computed: {
            infoData() {
                return this.form.info
            }
        },
        methods: {
            openDialog(name) {
                this.recordView = true
                this.title = name

            },



        }
    }
</script>