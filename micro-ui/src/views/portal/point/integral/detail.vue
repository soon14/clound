<template>
    <div class="execution">
        <basic-container>
            <avue-crud
                       :data="tableData"
                       :option="tableOption"
                       :page="page"
                       v-model="form"
                       @row-update="handleEdit"
                       @row-save="handleSav"
                       @date-change="dateChange"
                      >

                <template slot="menuLeft">
                    <el-button
                            class="filter-item"
                            size="small"
                            type="primary"
                            icon="el-icon-plus"
                            @click="openHandleAddDialog()">手工加分
                    </el-button>
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

        <el-dialog :title="handleAddTitle" fullscreen :visible.sync="handleAdd" :append-to-body="true">
            <handle-add-info/>
        </el-dialog>
    </div>
</template>

<script>
    let dict = {
        cslb: [
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
        name:[{
            label: '涉黄人员入住',
            value: '0'
        },{
            label: '涉赌人员入住',
            value: '1'
        },{
            label: '涉毒人员入住',
            value: '2'
        },{
            label: '其他',
            value: '3'
        }
        ]
    }

    import exportFile from "@/page/export/exportFile";
    import handleAddInfo from "@/views/portal/point/integral/handleAdd"
    export default {
        name: 'detail',
        components: {
            "exportFile" : exportFile,
            "handleAddInfo" : handleAddInfo
        },
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
                fileName:  '积分明细',
                exprotFields : {
                    csmc: "场所名称",
                    name : "指标名称",
                    type : "指标类型",
                    cslb : "适用场所",
                    score : "分值",
                    count: "次数",
                    scoreSum : "积分小计",
                    remarks: "描述",
                    createBy : "创建人",
                    createDate : "创建时间",
                    createDept: "创建部门"
                },
                handleAdd: false,
                dialogVisible: false,
                tableData: [
                    {
                        csmc: "7天酒店",
                        name:'涉黄人员入住',
                        type:'0',
                        cslb:'01',
                        score: '10',
                        count:'12',
                        bcjf:'10',
                        scoreSum : "120",
                        remarks:'七天酒店涉黄人员入住积分明细',
                        createBy:'张三',
                        createDate:'2019-03-25 17:14:01',
                        createDept:'江门市公安局'
                    },
                    {
                        csmc: "7天酒店",
                        name:'涉赌人员入住',
                        type:'0',
                        cslb:'01',
                        score: '10',
                        count:'6',
                        bcjf:'10',
                        scoreSum : "60",
                        remarks:'七天酒店涉赌人员入住积分明细',
                        createBy:'李四',
                        createDate:'2019-03-25 17:15:01',
                        createDept:'江门市公安局'
                    },
                    {
                        csmc: "7天酒店",
                        name:'涉毒人员入住',
                        type:'0',
                        cslb:'01',
                        score: '10',
                        count:'8',
                        bcjf:'10',
                        scoreSum : "80",
                        remarks:'七天酒店涉毒人员入住积分明细',
                        createBy:'王五',
                        createDate:'2019-03-25 17:16:01',
                        createDept:'江门市公安局'
                    },
                    {
                        csmc: "7天酒店",
                        name:'其他',
                        type:'1',
                        cslb:'32',
                        score: '10',
                        count:'1',
                        bcjf:'10',
                        scoreSum : "10",
                        remarks:'七天酒店涉毒人员入住积分明细',
                        createBy:'赵六',
                        createDate:'2019-03-25 17:17:01',
                        createDept:'江门市公安局'
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
                    menuWidth:'200',
                    align: 'center',
                    addBtn: false,
                    delBtn: false,
                    viewBtn: false,
                    editBtn: true,
                    dateBtn:true,
                    column: [
                        {
                            label: '场所类别',
                            prop: 'cslb',
                            search:'true',
                            type: 'select',
                            dicData: dict.cslb,
                            addDisabled:true,
                            editDisabled: true,
                            valueDefault:'0',
                            rules: [{
                                required: true,
                                message: '请选择场所类别',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '场所名称',
                            prop: 'csmc',
                            search:'true',
                            addDisabled: true,
                            editDisabled: true,
                            valueDefault:'七天酒店',
                            rules: [{
                                required: true,
                                message: '请输入场所名称',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '指标类型',
                            prop: 'type',
                            type: 'select',
                            dicData: dict.type,
                            addDisabled:true,
                            editDisabled:true,
                            valueDefault:'1',
                            overHidden: true,
                            rules: [{
                                required: true,
                                message: '请选择指标类型',
                                trigger: 'blur'
                            }]
                        },
                        {
                        label: '指标名称',
                        prop: 'name',
                        type:'select',
                        editDisabled: true,
                        dicData: dict.name,
                        overHidden: true,
                         rules: [{
                                required: true,
                                message: '请选择指标',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '分值',
                            prop: 'score',
                            overHidden: true,
                           /* addDisabled:true,*/
                            editDisabled:true,
                            type: 'number',
                            rules: [{
                                required: true,
                                message: '请输入单项分值',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '次数',
                            prop: 'count',
                            overHidden: true,
                            type: 'number',
                            rules: [{
                                required: true,
                                message: '请输入次数',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '本次加分',
                            prop: 'bcjf',
                            overHidden: true,
                            hide:true,
                            type: 'number',
                            rules: [{
                                required: true,
                                message: '请输入本次加分',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '积分小计',
                            prop: 'scoreSum',
                            overHidden: true,
                            addDisplay:true,
                            editDisplay:true
                        },
                        {
                            label: '描述',
                            type:'textarea',
                            prop: 'remarks',
                            hide:true,
                            span:24
                        },
                        {
                            label: '创建人',
                            prop: 'createBy',
                            overHidden: true,
                            hide:true,
                            span:6
                        },
                        {
                            label: '创建时间',
                            prop: 'createDate',
                            type: "datetime",
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            overHidden: true,
                            hide:true,
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

            dateChange(val){
                /*this.$message.success(val);*/
            },

            openHandleAddDialog() {
                this.handleAdd = true;
                this.handleAddTitle='手工加分'
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

<style>
    .avue-date__group {
        display: flex;
        -ms-flex-align: center;
        align-items: center;
        padding-right: 528px!important;
    }
</style>