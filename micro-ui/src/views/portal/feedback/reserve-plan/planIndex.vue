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
                            @click="openDialog(scope.row)">处置预案
                    </el-button>


                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            @click="handleDelete()">删除
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>
        <el-dialog :title="title" fullscreen :visible.sync="view"  >
            <avue-crud :option="yaOption"
                       ref="crud"
                       v-model="form"
                       :page="page"
                       :table-loading="listLoading"
                       :data="tempData">


                <template slot="lx"
                          slot-scope="scope">
                    {{scope.row.lx}}
                </template>
                <template slot="zt"
                          slot-scope="scope">
                            <span :style="scope.row.zt==0?'color: green':'color: red'">
                                {{scope.row.zt=='0'?'启用':'停用'}}
                            </span>
                </template>
                <template slot="lxForm"
                          slot-scope="scope">
                    <avue-input v-model="form.lx"
                                type="tree"
                                placeholder="请选择类型"
                                :dic="treeData"
                                :props="defaultProps"></avue-input>
                </template>
                <template slot="score"
                          slot-scope="scope">
                    <div role="slider" aria-valuenow="3" aria-valuetext="" aria-valuemin="0" aria-valuemax="5" tabindex="0" class="el-rate el-tooltip" aria-describedby="el-tooltip-137" style="margin-top: 10px;">
                        <span class="el-rate__item" style="cursor: pointer;" v-for="i in 5 " :key="i">

                            <i v-if="scope.row.score >= i " class="el-rate__icon el-icon-star-on" style="color: rgb(247, 186, 42);"></i>
                       <i v-else class="el-rate__icon el-icon-star-off" style="color: rgb(198, 209, 222);"><!----></i></span>
                       </div>
                </template>
                <template slot="ydfaForm"
                          slot-scope="scope">
                    <avue-ueditor v-model="form.ydfa"></avue-ueditor>
                </template>
                <template slot-scope="scope"
                          slot="menu">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            @click="handleDelete()">删除
                    </el-button>
                </template>

            </avue-crud>
        </el-dialog>

    </div>

</template>


<script>
    import avueUeditor from 'avue-plugin-ueditor/packages';
    Vue.use(avueUeditor);
    export default {
        data() {
            return {
                page: {
                    total: 10, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                view:false,
                title:'预览',
                listLoading:false,
                form: {},
                tempData:{},
                data: [
                    {
                        yamc:'江门市七天酒店非法聚赌预案',
                        sjmc:'旅馆业非法聚赌分析',
                        lx:'非法聚赌',
                        jbxx:'单位名称：江门七天酒店<br/>' +
                            ' 区域派出所：仓后派出所</br>' +
                            '聚赌人数：20人 <br/>' +
                            '房间号:404 <br/>' +
                            '分析时间:2019-03-27 12:00 <br/>'+
                            '地址：广东省江门市莲江区环市街道3号 <br/>',
                        createDate:'2019-03-27 12:33'
               },
                    {
                        yamc:'江门市如家酒店非法聚赌预案',
                        sjmc:'旅馆业聚纵吸毒分析',
                        lx:'聚纵吸毒',
                        jbxx:'单位名称：江门市如家酒店<br/>' +
                            ' 区域派出所：仓后派出所</br>' +
                            '聚赌人数：4人 <br/>' +
                            '房间号:102 <br/>' +
                            '分析时间:2019-03-28 12:00 <br/>'+
                            '地址：广东省江门市莲江区环市街道33号 <br/>',
                        createDate:'2019-03-28 12:33'
                    }

               ],
                option: {
                    dialogHeight: '100%',
                    dialogWidth: '100%',
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
                            label: '预案名称',
                            prop: 'yamc',
                            search: true,
                        },
                        {
                            label: '事件名称',
                            prop: 'sjmc',
                            search:true,
                        },

                        {
                            label: '基本信息',
                            prop: 'jbxx',
                        },
                        {
                            label: '类型',
                            prop: 'lx',
                        },{
                            label: '创建时间',
                            prop: 'createDate',
                        },


                    ],

                },
                jdData:[
                    {
                        mc:'聚纵吸毒解决方案二',
                        bzsj:'2019-03-22',
                        lx:'聚纵吸毒',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'1',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                        score:5
                    },
                    {
                        mc:'聚纵吸毒解决方案一',
                        bzsj:'2019-03-21',
                        lx:'聚纵吸毒',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'0',
                        qzxs:10,
                        score:3
                    },

                    {
                        mc:'聚纵吸毒解决方案三',
                        bzsj:'2019-03-21',
                        lx:'聚纵吸毒',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'2',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                        score:1
                    },
                    {
                        mc:'聚纵吸毒解决方案四',
                        bzsj:'2019-03-22',
                        lx:'聚纵吸毒',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'3',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                        score:0
                    },
                    {
                        mc:'聚纵吸毒解决方案五',
                        bzsj:'2019-03-22',
                        lx:'聚纵吸毒',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'3',
                        qzxs:50,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                        score:0
                    }
                ],
                yaData: [
                    {
                        mc:'非法聚赌解决方案二',
                        bzsj:'2019-03-28',
                        lx:'非法聚赌',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'1',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                        score:4
                    },
                    {
                        mc:'非法聚赌解决方案一',
                        bzsj:'2019-03-27',
                        lx:'非法聚赌',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'0',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                        score:2
                    },


                    {
                        mc:'非法聚赌解决方案四',
                        bzsj:'2019-03-29',
                        lx:'非法聚赌',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'3',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                        score:1
                    },

                ],
                defaultProps: {
                    label: "label",
                    value: 'value',
                },
                treeOption: {
                    nodeKey: 'id',
                    addBtn: true,
                    menu: false,
                    props: {
                        label: 'label',
                        value: 'value'
                    }
                },
                treeData: [
                    {
                        id:0,
                        label:'旅馆业',
                        value:0,
                        children:[
                            {
                                id:1,
                                label:'聚纵吸毒',
                                value:0,
                            },{
                                id:2,
                                label:'非法聚赌',
                                value:0,
                            }
                        ]
                    },{
                        id:3,
                        label:'机修业',
                        value:0,
                        children:[
                            {
                                id:4,
                                label:'盗抢车辆维修',
                                value:0,
                            },{
                                id:5,
                                label:'频繁维修',
                                value:0,
                            }
                        ]
                    }],
                yaOption:   {
                    expand:false,
                    refreshBtn:false,
                    filterBtn:false,
                    columnBtn:false,
                    searchBtn:false,
                    dialogFullscreen: true,
                    dialogHeight:'100%',
                    border: true,
                    index: true,
                    delBtn:false,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    editBtn: true,

                    align: 'center',
                    addBtn: false,
                    viewBtn:true,
                    card: true,
                    column: [
                        {
                            label: '名称',
                            prop: 'mc',
                            addDisplay:false,
                            editDisplay:false,


                        },
                        {
                            label: '类型',
                            prop: 'lx',
                            slot:true,
                            formslot:true,
                            addDisplay:false,
                            editDisplay:false,
                            hide:true
                        },{
                            label: '级别',
                            prop: 'jb',
                            type:'select',
                            overHidden:true,
                            dicData:[
                                {label:'一般（Ⅳ级）', value:'0'},
                                {label:'较重（Ⅲ级）', value:'1'},
                                {label:'严重（Ⅱ级）', value:'2'},
                                {label:'特别严重（Ⅰ级）', value:'3'},
                            ],
                            addDisplay:false,
                            editDisplay:false,


                        },
                         {
                            label: '推荐星级',
                            prop: 'score',
                            type: 'rate',
                             slot:true,
                             addDisplay:false,
                             editDisplay:true,
                             viewDisplay:false
                        },
                        {
                            label: '权重',
                            tip:'权重系数越高说明事件越严重',
                            prop: 'qzxs',
                            type:'number',
                            addDisplay:false,
                            editDisplay:false,
                            viewDisplay:false

                        },
                        {
                            label: '编制时间',
                            prop: 'bzsj',
                            type: "datetime",
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                            addDisplay:false,
                            editDisplay:false,
                        },

                        {
                            label: '编制人',
                            prop: 'bzr',
                            addDisplay:false,
                            editDisplay:false,
                            hide:true,
                        },{
                            label: '编制部门',
                            prop: 'bzbm',
                            addDisplay:false,
                            editDisplay:false,
                            hide:true,
                        },
                        {
                            label: '说明',
                            prop: 'remark',
                            overHidden:true,
                            hide:true
                        }
                        ,
                        {
                            label: '应对措施',
                            prop: 'ydfa',
                            span:24,
                            slot:true,
                            formslot:true,
                            hide:true,
                        },



                    ]
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
            openDialog(row) {
                this.view = true
                this.title = row.yamc
                if(row.lx=='非法聚赌'){
                    this.tempData=this.yaData
                }else{
                    this.tempData=this.jdData
                }


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



        }
    }
</script>