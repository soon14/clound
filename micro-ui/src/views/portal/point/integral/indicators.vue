<template>
    <div class="execution">
        <basic-container>
            <avue-crud
                       :data="tableData"
                       :option="tableOption"
                       :page="page"
                       @row-update="handleEdit"
                       @row-save="handleSav"
                       v-model="form"
                      >

                <template slot="menuLeft">
                    <el-button
                            class="filter-item"
                            @click="dialogVisible = true"
                            size="small"
                            type="primary"
                            icon="el-icon-download">导出
                    </el-button>
                </template>
                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            @click="handleDelete()">删除
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
    let dict = {
        sycs: [
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
            {"label": "所有", "value": "32"}
        ],
        type: [{
            label: '自动',
            value: '0'
        }, {
            label: '手动',
            value: '1'
        }],
        status: [{
            label: '启用',
            value: '0'
        }, {
            label: '停用',
            value: '1'
        }
        ]
    }

    import exportFile from "@/page/export/exportFile"
    export default {
        name: 'indicators',
        components: { "exportFile" : exportFile},
        data() {
            return {
                page: {
                    total: 4, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                form:{},
                exportUrl: '',
                fileName:  '积分指标管理',
                exprotFields : {
                    name : "指标名称",
                    status : "指标状态",
                    type : "指标类型",
                    sycs : "适用场所",
                    score : "分值",
                    remarks: "描述",
                    createBy : "创建人",
                    createDate : "创建时间",
                    createDept: "创建部门"
                },
                dialogVisible: false,
                tableData: [
                    {
                        name:'涉黄人员入住',
                        status:'0',
                        type:'0',
                        sycs:'01',
                        score: '10',
                        remarks:'涉黄人员入住积分指标',
                        createBy:'张三',
                        createDate:'2019-03-25 17:14:01',
                        createDept:'江门市公安局',
                    },
                    {
                        name:'涉赌人员入住',
                        status:'0',
                        type:'0',
                        sycs:'01',
                        score: '10',
                        remarks:'涉赌人员入住积分指标',
                        createBy:'李四',
                        createDate:'2019-03-25 17:15:01',
                        createDept:'江门市公安局',
                    },
                    {
                        name:'涉毒人员入住',
                        status:'0',
                        type:'0',
                        sycs:'01',
                        score: '10',
                        remarks:'涉毒人员入住积分指标',
                        createBy:'王五',
                        createDate:'2019-03-25 17:16:01',
                        createDept:'江门市公安局',
                    },
                    {
                        name:'其他',
                        status:'0',
                        type:'1',
                        sycs:'32',
                        score: '10',
                        remarks:'其他积分指标',
                        createBy:'赵六',
                        createDate:'2019-03-25 17:17:01',
                        createDept:'江门市公安局',
                    }
                ],
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
                    viewBtn: true,
                    delBtn: false,
                    viewBtn: true,
                    card:true,
                    column: [{
                        label: '指标名称',
                        prop: 'name',
                        search:'true',
                        overHidden: true,
                        rules: [{
                            required: true,
                            message: '请输入指标名称',
                            trigger: 'blur'
                        }]
                    },
                        {
                            label: '指标状态',
                            prop: 'status',
                            type: 'select',
                            dicData: dict.status,
                            overHidden: true,
                            rules: [{
                                required: true,
                                message: '请选择指标状态',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '指标类型',
                            prop: 'type',
                            search:'true',
                            type: 'select',
                            dicData: dict.type,
                            overHidden: true,
                            rules: [{
                                required: true,
                                message: '请选择指标类型',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '适用场所',
                            prop: 'sycs',
                            search:'true',
                            type: 'select',
                            dicData: dict.sycs,
                            overHidden: true,
                            rules: [{
                                required: true,
                                message: '请选择适用场所',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '分值',
                            prop: 'score',
                            overHidden: true,
                            rules: [{
                                required: true,
                                message: '请输入分值',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '描述',
                            type:'textarea',
                            prop: 'remarks',
                            span:24,
                            hide:true,
                        },
                        {
                            label: '创建人',
                            prop: 'createBy',
                            overHidden: true,
                            span:6
                        },
                        {
                            label: '创建时间',
                            prop: 'createDate',
                            type: "datetime",
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            overHidden: true,
                            span:6
                        },
                        {
                            label: '创建单位',
                            prop: 'createDept',
                            hide:true,
                            span:6
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

