<template>
    <div class="execution">
        <basic-container>
            <avue-crud
                    :data="tableData"
                    :option="tableOption"
                    :page="page"
                    @row-update="handleEdit"
                    @row-save="handleSav"
                    v-model="form">

                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="viewFlag=true">查看
                    </el-button>

                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            @click="handleDelete()">删除
                    </el-button>

                    <el-button icon="el-icon-view"
                               type="text"
                               size="small"
                               @click="openLogDialog(scope.row.id)"
                    >变更日志</el-button>

                    <el-button icon="el-icon-message"
                               type="text"
                               size="small"
                               @click="openNoticeDialog(scope.row.id)"
                    >打印整改通知</el-button>

                </template>

                <template slot="tableInfoForm" slot-scope="scope">
                    <el-table :data="checkData"
                              border
                              style="width: 100%" >
                        <el-table-column
                                label="检查内容"
                                prop="content"
                                width="200"
                        >
                        </el-table-column>
                        <el-table-column
                                label="检查情况"
                                width="200"
                                align="center"
                        >
                            <template slot-scope="scope">
                                <el-form-item :prop="checkData.jcqk">
                                    <el-radio v-model="scope.row.jcqk" label="0">是</el-radio>
                                    <el-radio v-model="scope.row.jcqk" label="1">否</el-radio>
                                </el-form-item>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="详细情况"
                                width="748"
                                align="center"
                        >
                            <template slot-scope="scope">
                                <el-form-item :prop="checkData.xxqk">
                                    <el-input v-model="scope.row.xxqk"></el-input>
                                </el-form-item>
                            </template>

                        </el-table-column>
                    </el-table>
                </template>

                <template slot="imgUrlForm" slot-scope="scope">
                    <file-upload tbPk="tbPk"
                                 tbName="tbName">
                    </file-upload>
                </template>

            </avue-crud>
        </basic-container>

        <el-dialog title="查看" fullscreen :visible.sync="viewFlag" :append-to-body="true">
            <avue-detail  :option="viewOption"  v-model="viewData" >
                <template slot="tableInfo" slot-scope="scope">
                    <el-table :data="checkData"
                              border
                              style="width: 100%"
                              v-modeal="checkData"
                    >
                        <el-table-column
                                label="检查内容"
                                prop="content"
                                width="200">
                        </el-table-column>

                        <el-table-column
                                label="检查情况"
                                width="200"
                                align="center">
                            <template slot-scope="scope" >
                                <div v-text="getDict(scope.row.jcqk)"></div>
                            </template>
                        </el-table-column>

                        <el-table-column
                                label="详细情况"
                                width="748"
                                align="center">
                            <template slot-scope="scope">
                                <div>{{scope.row.xxqk}}</div>
                            </template>
                        </el-table-column>



                    </el-table>
                </template>

                <template slot="imgUrl" slot-scope="scope">
                    <img height="100px" width="100px" src="/img/hg.jpg"/>
                </template>
            </avue-detail>

            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="viewFlag=false">关 闭
                </el-button>
            </div>

        </el-dialog>


        <el-dialog :title="logTitle" fullscreen :visible.sync="logView" :append-to-body="true">
                <log-info  />
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="logView=false">关 闭
                </el-button>
            </div>
        </el-dialog>

        <el-dialog :title="noticeTitle" fullscreen :visible.sync="noticeView" :append-to-body="true">
            <notice-info  />
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="noticeView=false">打印</el-button>
                <el-button type="primary" @click="noticeView=false">导出</el-button>
                <el-button type="primary" @click="noticeView=false">关闭 </el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>

    let dict = {
        gxhylb: [
            {"label": "旅馆业", "value": "01"},
            {"label": "刻印章业", "value": "02"},
            {"label": "旧货业", "value": "03"},
            {"label": "印刷业", "value": "04"},
            {"label": "机动车修理业", "value": "05"},
            {"label": "开锁业", "value": "06"},
            {"label": "其他管理行业", "value": "07"},
            {"label": "歌舞娱乐场所", "value": "08"},
            {"label": "按摩服务业", "value": "09"},
            {"label": "游戏游艺场所", "value": "10"},
            {"label": "录像放映场所", "value": "11"},
            {"label": "上网服务场所", "value": "12"},
            {"label": "体育场馆", "value": "13"},
            {"label": "广场公园", "value": "14"},
            {"label": "其他场所", "value": "15"},
            {"label": "报废机动车拆解业", "value": "16"},
            {"label": "典当业", "value": "17"},
            {"label": "废旧金属业", "value": "18"},
            {"label": "涉枪单位", "value": "19"},
            {"label": "易制爆单位", "value": "20"},
            {"label": "剧毒化学品单位", "value": "21"},
            {"label": "旅业式出租屋", "value": "22"},
            {"label": "烟花爆竹单位", "value": "23"},
            {"label": "重点单位", "value": "24"},
            {"label": "一般单位", "value": "25"},
            {"label": "学校/幼儿园", "value": "26"},
            {"label": "保安服务公司", "value": "27"},
            {"label": "保安押运公司", "value": "28"},
            {"label": "保安培训机构", "value": "29"},
            {"label": "内部保安组织", "value": "30"},
            {"label": "放射性同位素", "value": "31"},
            {"label": "其他单位", "value": "32"}
        ],
        clyj: [{
            label: '罚款',
            value: '0'
        }, {
            label: '限期整改',
            value: '1'
        }, {
            label: '停业整顿',
            value: '2'
        }, {
            label: '发函告知',
            value: '3'
        }, {
            label: '收回许可证号',
            value: '4'
        }, {
            label: '吊销营业执照',
            value: '5'
        }
        ],
        jcjg: [{
            label: '正常',
            value: '0'
        }, {
            label: '存在问题',
            value: '1'
        }
        ],
        cljg: [{
            label: '当场处理',
            value: '0'
        }, {
            label: '线索上报',
            value: '1'
        }, {
            label: '其他',
            value: '2'
        }
        ],
        sspcs: [{
            label: '白沙派出所',
            value: '440703500000'
        }, {
            label: '北街派出所',
            value: '440703510000'
        },
            {
                label: '仓后派出所',
                value: '440703520000'
            },
            {
                label: '江门市公安局环市派出所',
                value: '440703530000'
            }],
        ssjwq: [{
            label: '海傍社区警务区',
            value: '0'
        }, {
            label: '甘化社区警务区',
            value: '1'
        }, {
            label: '常安社区警务区',
            value: '2'
        }, {
            label: '常安社区警务区',
            value: '3'
        }, {
            label: '良化北社区警务区',
            value: '4'
        }],
        checkPeople:[{
            label:'张三',
            value:'0'
        },{
            label:'李四',
            value:'1'
        },{
            label:'王五',
            value:'2'
        },{
            label:'赵六',
            value:'3'
        }
        ],
        checkType:[{
            label:'日常检查',
            value:'0'
        },{
            label:'消防检查',
            value:'1'
        }
        ]
    }

    import fileUpload from "@/page/file/fileUpload";
    import logInfo from '@/views/portal/log/history/index';
    import noticeInfo from '@/views/portal/unit/inspect/register/notice';
    export default {
        components: {
            "fileUpload" : fileUpload,
            "logInfo"  :logInfo,
            "noticeInfo" : noticeInfo
        },
        data() {
            return {

                text: "",
                upload: {
                    //普通图片上传
                    action: "https://avueupload.91eic.com/upload/list",
                    props: {
                        res: "data.0",
                        url: "url"
                    },
                    //七牛云oss配置
                    qiniu: {
                        AK: "",
                        SK: "",
                        scope: "test",
                        url: "http://pm7cc17lu.bkt.clouddn.com/",
                        deadline: 1
                    },
                    //阿里云oss配置
                    ali: {
                        region: "oss-cn-beijing",
                        endpoint: "oss-cn-beijing.aliyuncs.com",
                        accessKeyId: "",
                        accessKeySecret: "",
                        bucket: "avue"
                    }
                },

                tbPk: 'test_id', // 用于示例测试，不是真实id
                tbName: 'sys_file',// 用于示例测试，不是真实id
                viewFlag:false,
                logView:false,
                noticeView:false,
                logTitle:'',
                noticeTitle:'',
                page: {
                    total: 4, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                form:{},
                checkData: [
                    {
                        content:'入住登记制度健全落实',
                        jcqk:'0',
                        xxqk:'入住登记制度健全落实'
                    },
                    {
                        content:'来访登记制度健全落实',
                        jcqk:'0',
                        xxqk:''
                    },
                    {
                        content:'值班巡查制度健全落实',
                        jcqk:'1',
                        xxqk:''
                    },
                    {
                        content:'财务保管制度情况健全落实',
                        jcqk:'0',
                        xxqk:''
                    },
                    {
                        content:'报告制度健全落实',
                        jcqk:'0',
                        xxqk:''
                    }
                ],
                tableData: [
                    {
                        checkDate:'2019-03-21',
                        inspectedUnit:'七天连锁酒店',
                        gxhylb:'01',
                        checkPeople:'0',
                        ssjwq:'0',
                        checkUnit:'江门市公安局环市派出所',
                        checkType:'0',
                        qtjcr:'王某某',
                        qydzfzr:'李某某',
                        qtqk:'无',
                        jcjg:'0',
                        cljg:'2',
                        clyj:'',
                        xlzgrq:'2019-03-22',
                        remarks:'情况良好'
                    },
                    {
                        checkDate:'2019-03-21',
                        inspectedUnit:'江门市XX宾馆',
                        gxhylb:'01',
                        checkPeople:'0',
                        ssjwq:'0',
                        checkUnit:'江门市公安局环市派出所',
                        checkType:'0',
                        qtjcr:'王某某',
                        qydzfzr:'李某某',
                        qtqk:'无',
                        jcjg:'0',
                        cljg:'2',
                        clyj:'',
                        xlzgrq:'2019-03-22',
                        remarks:'情况良好'
                    },
                    {
                        checkDate:'2019-03-21',
                        inspectedUnit:'希尔顿酒店',
                        gxhylb:'01',
                        checkPeople:'0',
                        ssjwq:'0',
                        checkUnit:'江门市公安局环市派出所',
                        checkType:'0',
                        qtjcr:'王某某',
                        qydzfzr:'李某某',
                        qtqk:'无',
                        jcjg:'0',
                        cljg:'2',
                        clyj:'',
                        xlzgrq:'2019-03-22',
                        remarks:'情况良好'
                    },
                    {
                        checkDate:'2019-03-21',
                        inspectedUnit:'商务酒店',
                        gxhylb:'01',
                        checkPeople:'0',
                        ssjwq:'0',
                        checkUnit:'江门市公安局环市派出所',
                        checkType:'0',
                        qtjcr:'王某某',
                        qydzfzr:'李某某',
                        qtqk:'无',
                        jcjg:'1',
                        clyj:'2',
                        clyj:'',
                        xlzgrq:'2019-03-22',
                        remarks:''
                    }
                ],
                viewData:
                    {
                        checkDate:'2019-03-21',
                        checkPeople:'0',
                        inspectedUnit:'七天连锁酒店',
                        ssjwq:'0',
                        checkUnit:'江门市公安局环市派出所',
                        checkType:'0',
                        qtjcr:'王某某',
                        qydzfzr:'李某某',
                        qtqk:'无',
                        jcjg:'0',
                        cljg:'2',
                        clyj:'',
                        xlzgrq:'2019-03-22',
                        remarks:'情况良好'
                    },
                tableOption: {
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: true,
                    editBtn: true,
                    viewBtn: false,
                    delBtn: false,
                    column: [
                        {
                            label: '行业类别',
                            prop: 'gxhylb',
                            type: 'select',
                            dicData: dict.gxhylb,
                            overHidden: true,
                            editDisplay:false,
                            addDisplay:false
                        },
                        {
                            label: '检查时间',
                            prop: 'checkDate',
                            type: "datetime",
                            search:'true',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            overHidden: true,
                            span:6,
                            rules: [{
                                required: true,
                                message: '请选择检查时间',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '检查人',
                            prop: 'checkPeople',
                            type: 'select',
                            dicData: dict.checkPeople,
                            span:6,
                            hide:true,
                            rules: [{
                                required: true,
                                message: '请选择检查人',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '被检查单位',
                            prop: 'inspectedUnit',
                            search:'true',
                            overHidden: true,
                            labelWidth: 100,
                            span:6,
                            rules: [{
                                required: true,
                                message: '请输入被检查单位',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '所属警务区',
                            prop: 'ssjwq',
                            type:'select',
                            dicData: dict.ssjwq,
                            labelWidth: 100,
                            span:6,
                            hide:true,
                            rules: [{
                                required: true,
                                message: '请选择所属警务区',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '检查单位',
                            prop: 'checkUnit',
                            span:6
                        },
                        {
                            label: '检查方式',
                            prop: 'checkType',
                            type: "select",
                            dicData: dict.checkType,
                            span:6,
                            hide:true
                        },
                        {
                            label: '其他检查人',
                            prop: 'qtjcr',
                            span:6,
                            labelWidth: 100,
                            hide:true
                        },
                        {
                            label: '企业当值负责人',
                            prop: 'qydzfzr',
                            span:6,
                            labelWidth: 110,
                            hide:true
                        },
                        {
                            prop: 'tableInfo',
                            slot:true,
                            formslot:true,
                            span:24,
                            hide:true
                        },
                        {
                            label: '其他情况',
                            prop: 'qtqk',
                            type:'textarea',
                            span:24,
                            hide:true
                        },
                        {
                            label: '检查结果',
                            prop: 'jcjg',
                            type: 'select',
                            search:'true',
                            span:12,
                            dicData: dict.jcjg,
                            overHidden: true
                        },
                        {
                            label: '处理结果',
                            prop: 'cljg',
                            type: 'select',
                            span:12,
                            dicData: dict.cljg
                        },
                        {
                            label: '处理意见',
                            prop: 'clyj',
                            type: 'select',
                            span:12,
                            dicData: dict.clyj,
                            hide:true,
                            overHidden: true,
                        },
                        {
                            label: '限令整改日期',
                            prop: 'xlzgrq',
                            type: 'date',
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                            labelWidth: 100,
                            span:12,
                            hide:true
                        },
                        {
                            label: '备注',
                            prop: 'remarks',
                            type: 'textarea',
                            span: 24
                        },
                        {
                            label: '附件',
                            prop: 'imgUrl',
                            slot:true,
                            formslot:true,
                            span: 24,
                            hide:true
                        }
                    ]

                },
                viewOption: {
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
                    viewBtn: false,
                    delBtn: false,
                    column: [
                        {
                            label: '检查时间',
                            prop: 'checkDate',
                            type: "datetime",
                            search:'true',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            span:6,
                            rules: [{
                                required: true,
                                message: '请选择检查时间',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '检查人',
                            prop: 'checkPeople',
                            type: 'select',
                            dicData: dict.checkPeople,
                            span:6,
                            rules: [{
                                required: true,
                                message: '请选择检查人',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '被检查单位',
                            prop: 'inspectedUnit',
                            labelWidth: 100,
                            span:6,
                            rules: [{
                                required: true,
                                message: '请输入被检查单位',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '所属警务区',
                            prop: 'ssjwq',
                            type:'select',
                            dicData: dict.ssjwq,
                            labelWidth: 100,
                            span:6,
                            rules: [{
                                required: true,
                                message: '请选择所属警务区',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '检查单位',
                            prop: 'checkUnit',
                            span:6
                        },
                        {
                            label: '检查方式',
                            prop: 'checkType',
                            type: "select",
                            dicData: dict.checkType,
                            span:6
                        },
                        {
                            label: '其他检查人',
                            prop: 'qtjcr',
                            span:6,
                            labelWidth: 100
                        },
                        {
                            label: '企业当值负责人',
                            prop: 'qydzfzr',
                            span:6,
                            labelWidth: 110
                        },
                        {
                            label: '检查详情',
                            prop: 'tableInfo',
                            slot:true,
                            formslot:true,
                            span:24
                        },
                        {
                            label: '其他情况',
                            prop: 'qtqk',
                            type:'textarea',
                            span:24
                        },
                        {
                            label: '检查结果',
                            prop: 'jcjg',
                            type: 'select',
                            span:12,
                            dicData: dict.jcjg
                        },
                        {
                            label: '处理结果',
                            prop: 'cljg',
                            type: 'select',
                            span:12,
                            dicData: dict.cljg
                        },
                        {
                            label: '处理意见',
                            prop: 'clyj',
                            type: 'select',
                            span:12,
                            dicData: dict.clyj
                        },
                        {
                            label: '限令整改日期',
                            prop: 'xlzgrq',
                            type: 'date',
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                            labelWidth: 100,
                            span:12
                        },
                        {
                            label: '备注',
                            prop: 'remarks',
                            type: 'textarea',
                            span: 24
                        },
                        {
                            label: '附件',
                            prop: 'imgUrl',
                            slot:true,
                            formslot:true,
                            span: 24
                        }
                    ]

                },

            }
        },
        computed: {
            infoData() {
                return this.form.info
            }
        },
        methods: {
            getDict(jcqk){
                let sz = jcqk;
                if(sz == 0){
                    return "是"
                }else{
                    return "否"
                }
            },

            openLogDialog(id) {
                this.logView = true
                this.logTitle = '变更日志'
            },

            openNoticeDialog(id) {
                this.noticeView = true
            },

            handleEdit(row, index, done, loading) {
                done();
                this.$notify({
                    title: '成功',
                    message: '操作成功',
                    type: 'success',
                    duration: 2000
                })
            },
            handleSav(row, done, loading) {
                done();
                this.$notify({
                    title: '成功',
                    message: '操作成功',
                    type: 'success',
                    duration: 2000
                })
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

