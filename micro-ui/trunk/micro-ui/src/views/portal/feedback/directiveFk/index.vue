<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       :page="page"
                       :option="tabOption"
                       :data="data"
                       @row-update="handleUpdate"
                       @row-save="handleSav"
                       @row-del="handleDelete"
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
                               @click="handleView(scope.row,scope.index)">详细信息
                    </el-button>
                </template>

            </avue-crud>
            <export-file :exprotFields="exprotFields"
                         :dialogVisible="dialogVisible"
                         :url="exportUrl"
                         :params="form"
                         :pageSize="page.pageSize"
                         :currentPage="page.currentPage"
                         :ascs="page.ascs"
                         :descs="page.descs"
                         :fileName="fileName"
                         v-on:hiddeDialg="dialogVisible = false" >
            </export-file>
        </basic-container>
    </div>
</template>

<script>
    import exportFile from "@/page/export/exportFile"
    let dict ={
        zrdw:[
            {label: '江门市公安局', value: '0'},
            {label: '甘化社区警务区', value: '1',},
            {label: '海傍社区警务区', value: '2',},
            {label: '常安社区警务区',value: '3',}
        ],
        zyjb:[{label: '一级', value: 'A'},
            {label: '二级', value: 'B'},
            {label: '三级', value: 'C'},
            {label: '橙色', value: 'D'},
            {label: '黄色', value: 'E'},
            {label: '红色', value: 'F'},],
        clzt:[{label:'已处理',value:'01'},
            {label:'未处理',value:'02'},
        ],
        cslb:[
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
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                dialogVisible: false,
                exportUrl: 'portal/feedback/dealCenter/export',
                fileName:  '处置中心信息',
                exprotFields : {
                    yjms: '预警描述',
                    yjlx: '预警类型',
                    yjjb:'重要级别',
                    cldw:'责任单位',
                    clzt:'处理状态',
                },
                form: {},
                tabOption:{
                    indexLabel: '序号',
                    index: true,
                    editBtn:false,
                    addBtn: false,
                    viewBtn: false,
                    delBtn:false,
                    border: true,
                    card:true,
                    column: [
                        {
                            label: '预警描述',
                            prop: 'yjms',
                            display:false,
                            span:12,
                        },
                        {
                            label: '预警类型',
                            prop: 'yjlx',
                            type:'select',
                            search:true,
                            hide:'true',
                            span:12,
                            dicData: dict.cslb,
                        },
                        {
                            label: '处理状态',
                            prop: 'clzt',
                            type:'select',
                            hide:'true',
                            dicData:dict.clzt,
                            span:12,
                        },
                        {
                            label: '发布时间',
                            prop: 'fbsj',
                           type:'datetime',
                            hide:'true',
                            span:12,
                        },
                        {
                            label: '预警级别',
                            prop: 'yjjb',
                            type:'select',
                            dicData:dict.zyjb,
                            hide:'true',
                            span:12
                        },
                        {
                            label: '要求反馈时间',
                            prop: 'yqwcsj',
                            type: 'datetime',
                            hide:'true',
                            span:12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '所属派出所',
                            prop: 'sspcs',
                            dicData:dict.zrdw,
                            hide:'true',
                            span:12,
                        },
                        {
                            label:'预警描述',
                            prop:'yjmsxx',
                            type:'textarea',
                            hide:'true',
                            span:24,
                        },
                        {
                            label:'场所名称',
                            prop:'csmc',
                            hide:'true',
                            span:12,
                        },
                        {
                            label:'场所类别',
                            prop:'cslb',
                            type:'select',
                            hide:'true',
                            dicData:dict.cslb,
                            span:12,
                        },
                        {
                            label: '处理单位',
                            prop: 'cldw',
                            display:false,
                            search:true,
                            type:'select',
                            dicData:dict.zrdw,
                            span:12,
                        },
                        {
                            label: '处理结果',
                            prop: 'cljg',
                            type:'textarea',
                            span:24,
                        },
                        {
                            label: '处理人',
                            prop: 'clr',
                            type:'select',
                            search:true,
                            span:12,
                        },
                        {
                            label: '处理时间',
                            prop: 'cxclsj',
                            type: 'datetimerange',
                            search:true,
                            display:false,
                            hide:"true",
                            span:12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '处理时间',
                            prop: 'clsj',
                            type: 'datetime',
                            span:12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                    ]
                },
                data:[
                    {
                        yjms:'涉黄人员集聚',
                        yjlx:'01',
                        clzt:'01',
                        fbsj:'2019-03-28',
                        yjjb:'A',
                        yqwcsj:'2019-03-28',
                        sspcs:'0',
                        yjmsxx:'九天酒店在2019年03月15日有6名涉黄人员聚集',
                        csmc:'九天酒店',
                        cslb:'01',
                        cljg:'抓获胡顶天,夏立地,张牙,胡抓立案并做刑事拘留处理',
                        cldw:'0',
                        clr:'李一天',
                        clsj:'2019-03-27',
                    },
                    {
                        yjms:'涉黄人员集聚',
                        yjlx:'01',
                        clzt:'01',
                        fbsj:'2019-03-28',
                        yjjb:'A',
                        yqwcsj:'2019-03-28',
                        sspcs:'0',
                        yjmsxx:'九天酒店在2019年03月15日有6名涉黄人员聚集',
                        csmc:'九天酒店',
                        cslb:'01',
                        cljg:'抓获胡顶天,夏立地,张牙,胡抓立案并做刑事拘留处理',
                        cldw:'0',
                        clr:'李一天',
                        clsj:'2019-03-27',
                    },
                    {
                        yjms:'涉黄人员集聚',
                        yjlx:'01',
                        clzt:'01',
                        fbsj:'2019-03-28',
                        yjjb:'A',
                        yqwcsj:'2019-03-28',
                        sspcs:'0',
                        yjmsxx:'九天酒店在2019年03月15日有6名涉黄人员聚集',
                        csmc:'九天酒店',
                        cslb:'01',
                        cljg:'抓获胡顶天,夏立地,张牙,胡抓立案并做刑事拘留处理',
                        cldw:'0',
                        clr:'李一天',
                        clsj:'2019-03-27',
                    },
                    {
                        yjms:'涉黄人员集聚',
                        yjlx:'01',
                        clzt:'01',
                        fbsj:'2019-03-28',
                        yjjb:'A',
                        yqwcsj:'2019-03-28',
                        sspcs:'0',
                        yjmsxx:'九天酒店在2019年03月15日有6名涉黄人员聚集',
                        csmc:'九天酒店',
                        cslb:'01',
                        cljg:'抓获胡顶天,夏立地,张牙,胡抓立案并做刑事拘留处理',
                        cldw:'0',
                        clr:'李一天',
                        clsj:'2019-03-27',
                    },
                    {
                        yjms:'涉黄人员集聚',
                        yjlx:'01',
                        clzt:'01',
                        fbsj:'2019-03-28',
                        yjjb:'A',
                        yqwcsj:'2019-03-28',
                        sspcs:'0',
                        yjmsxx:'九天酒店在2019年03月15日有6名涉黄人员聚集',
                        csmc:'九天酒店',
                        cslb:'01',
                        cljg:'抓获胡顶天,夏立地,张牙,胡抓立案并做刑事拘留处理',
                        cldw:'0',
                        clr:'李一天',
                        clsj:'2019-03-27',
                    },
                    {
                        yjms:'涉黄人员集聚',
                        yjlx:'01',
                        clzt:'01',
                        fbsj:'2019-03-28',
                        yjjb:'A',
                        yqwcsj:'2019-03-28',
                        sspcs:'0',
                        yjmsxx:'九天酒店在2019年03月15日有6名涉黄人员聚集',
                        csmc:'九天酒店',
                        cslb:'01',
                        cljg:'抓获胡顶天,夏立地,张牙,胡抓立案并做刑事拘留处理',
                        cldw:'0',
                        clr:'李一天',
                        clsj:'2019-03-27',
                    },
                ],
            }
        },

        computed:{

        },

        methods: {
            handleChange(column) {
                this.type = column
            },
            handleView(row, index) {
                this.$refs.crud.rowView(row, index)
            },
            handleEdit (row, index) {
                this.$refs.crud.rowEdit(row, index);
            },

            handleSav(row, done, loading){
                done();
                this.$notify({
                    title: '成功',
                    message: '新增成功',
                    type: 'success',
                    duration: 2000
                })
            },
            handleUpdate(row, index, done, loading) {
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
                    loading();
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
        }
    }
</script>