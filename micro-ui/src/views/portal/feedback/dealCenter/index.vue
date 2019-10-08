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
                    <label v-if="showHideView(scope.row.clzt)" style="padding-left: 10px">
                        <el-button size="mini"
                                   type="text"
                                   icon="el-icon-view"
                                   @click="viewFlag=true">查看
                        </el-button>
                    </label>
                    <label v-if="showHideEdit(scope.row.clzt)" style="padding-left: 10px">
                        <el-button size="mini"
                                   type="text"
                                   icon="el-icon-edit"
                                   @click="editFlag=true">处理
                        </el-button>
                    </label>
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
        <el-dialog title="指令查看" fullscreen :visible.sync="viewFlag" :append-to-body="true">
            <avue-detail  :option="formOption"  v-model="viewData" >

            </avue-detail>

            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="viewFlag=false">关 闭
                </el-button>
            </div>

        </el-dialog>

        <el-dialog title="指令处理" fullscreen :visible.sync="editFlag" :append-to-body="true">
            <avue-form  :option="formOption"  v-model="formData" >
            </avue-form>
            <div slot="footer"  class="el-dialog--center">
                <el-button type="primary" @click="handleSav">提 交
                </el-button>
            </div>
        </el-dialog>

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
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                viewFlag:false,
                editFlag:false,
                dialogVisible: false,
                exportUrl: 'portal/feedback/dealCenter/export',
                fileName:  '处置中心信息',
                exprotFields : {
                    zlms: '指令描述',
                    zllx: '指令类型',
                    yqwcsj: '要求完成时间',
                    zyjb:'重要级别',
                    zrdw:'责任单位',
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
                            label: '指令描述',
                            prop: 'zlms',
                            span:12,
                        },
                        {
                            label: '指令类型',
                            prop: 'zllx',
                            type:'select',
                            search:true,
                            span:12,
                            dicData: dict.sycslb,
                        },
                        {
                            label: '重要级别',
                            prop: 'zyjb',
                            type:'select',
                            dicData:dict.zyjb,
                            search:true,
                            span:12
                        },
                        {
                            label: '要求完成时间',
                            prop: 'yqwcsj',
                            type: 'datetimerange',
                            display:false,
                            search:true,
                            span:12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '责任单位',
                            prop: 'zrdw',
                            display:false,
                            search:true,
                            type:'select',
                            dicData:dict.zrdw,
                            span:12,
                        },
                        {
                            label: '处理状态',
                            prop: 'clzt',
                            type:'select',
                            search:true,
                            dicData:dict.clzt,
                            span:12,
                        },
                        {
                            label: '处理时间',
                            prop: 'clsj',
                            type: 'datetimerange',
                            hide:'true',
                            display:false,
                            search:true,
                            span:12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                    ]
                },
                data:[
                    {
                        zlms:'涉黄人员集聚',
                        zllx:'01',
                        yqwcsj:'2019-03-28',
                        zyjb:'A',
                        zrdw:'0',
                        clzt:'01',
                    },
                    {
                        zlms:'涉黄人员集聚',
                        zllx:'01',
                        yqwcsj:'2019-03-28',
                        zyjb:'A',
                        zrdw:'0',
                        clzt:'01',
                    },
                    {
                        zlms:'涉黄人员集聚',
                        zllx:'01',
                        yqwcsj:'2019-03-28',
                        zyjb:'A',
                        zrdw:'0',
                        clzt:'01',
                    },
                    {
                        zlms:'涉黄人员集聚',
                        zllx:'01',
                        yqwcsj:'2019-03-28',
                        zyjb:'A',
                        zrdw:'0',
                        clzt:'02',
                    },
                    {
                        zlms:'涉黄人员集聚',
                        zllx:'01',
                        yqwcsj:'2019-03-28',
                        zyjb:'A',
                        zrdw:'0',
                        clzt:'02',
                    },
                ],
                formOption: {
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    editBtn: false,
                    delBtn: false,
                    viewBtn: false,
                    emptyBtn:false,
                    submitBtn:false,
                    card:true,
                    group:[
                        {
                            label: '基本信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [{
                                label: '预警描述',
                                prop: 'name',
                                type:'textarea',
                                span:24,
                                disabled:true
                            },{
                                label: '预警类型',
                                prop: 'type',
                                type:'select',
                                dicData:dict.type,
                                disabled:true
                            },
                                {
                                    label: '预警级别',
                                    prop: 'level',
                                    type: 'select',
                                    dicData:dict.level,
                                    disabled:true
                                },
                                {
                                    label: '预警场所',
                                    prop: 'yjcs',
                                    disabled:true
                                },
                                {
                                    label: '发布时间',
                                    prop: 'publishDate',
                                    type: "datetime",
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                    disabled:true
                                },
                                {
                                    label: '所属辖区',
                                    prop: 'ssxq',
                                    type: 'select',
                                    dicData:dict.ssxq,
                                    disabled:true
                                } ,
                                {
                                    label: '所属派出所',
                                    prop: 'sspcs',
                                    dicData:dict.sspcs,
                                    disabled:true,
                                    labelWidth: 100
                                }
                            ]
                        },
                        {
                            label: '处理信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [{
                                label: '处理意见',
                                prop: 'method',
                                type:'textarea',
                                span:24,
                                rules: [{
                                    required: true,
                                    message: '请输处理意见',
                                    trigger: 'blur'
                                }]
                            },
                                {
                                    label: '处理人',
                                    prop: 'dealBy'
                                },
                                {
                                    label: '处理时间',
                                    prop: 'dealDate',
                                    type: "datetime",
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss'
                                }
                            ]
                        }
                    ]

                },
                formData:
                    {
                        name:'涉黄人员聚集',
                        type:'01',
                        publishDate:'2019-03-27 12:00:00',
                        status: '0',
                        level:'0',
                        ssxq:'0',
                        sspcs:'西街派出所',
                        yjcs:'七天酒店',
                        method:'',
                        dealBy:'',
                        dealDate:'',
                    },
                viewData:
                    {
                        name:'涉赌人员聚集',
                        type:'08',
                        publishDate:'2019-03-25 10:00:00',
                        status: '1',
                        level:'3',
                        ssxq:'0',
                        sspcs:'西街派出所',
                        yjcs:'大三元',
                        method:'抓捕犯罪嫌疑人张三、李四，立案并做刑事拘留处理',
                        dealBy:'张警官',
                        dealDate:'2019-03-26 12:00:00',

                    },
            }
        },

        computed:{
            infoData(){
                return this.form.info
            }
        },

        methods: {
            showHideView:function(clzt){
                if(clzt ==='01'){
                    return true;
                }
                
            },
            showHideEdit:function(clzt){
                if(clzt ==='02'){
                    return true;
                }
                
            },
            
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