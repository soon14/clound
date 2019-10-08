<template>
    <div class="execution">
        <basic-container>
                <avue-crud ref="crud"
                           :page="page"
                           :option="tabOption1"
                           :data="data1"
                           v-model="form"
                           @row-update="handleUpdate"
                           @row-save="handleSav"
                           @row-del="handleDelete">

                        <template slot-scope="scope" slot="menuLeft">
                            <el-button type="primary"
                                       icon="el-icon-download"
                                       size="small"
                                       @click="dialogVisible = true">导出</el-button>
                        </template>

                    <template slot="fieldContentForm" slot-scope="scope" :span="12">
                        <avue-crud :option="tableOption2" :data="tableData2" @row-update="addUpdate"></avue-crud>
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
        option:[
            {label: '无', value: '0'},
            {label: '大于', value: 'gt'},
            {label: '大于等于', value: 'gt='},
            {label: '小于', value: 'lt'},
            {label: '小于等于', value: 'lt='},
        ],
        sydw:[
            {label: '江门市公安局', value: '0'},
            {label: '甘化社区警务区', value: '1',},
            {label: '海傍社区警务区', value: '2',},
            {label: '常安社区警务区',value: '3',}
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
                dialogVisible: false,
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                form: {},
                exportUrl: 'portal/point/rating/export',
                fileName:  '等级评定方案信息',
                exprotFields : {
                    famc: '方案名称',
                    ssnd: '所属年度',
                    sydw: '使用单位',
                    sycslb: '适用场所类别',
                    cjr:'创建人',
                    cjdw:'创建单位',
                    cjsj:'创建时间'
                },
                tabOption1:{
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: true,
                    delBtn: true,
                    viewBtn: true,
                    column: [
                        {
                            label: '方案名称',
                            prop: 'famc',
                            span:12,
                            search:true,
                            rules: [
                                {
                                    required: true,
                                    message: '请输入方案名称',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '所属年度',
                            prop: 'ssnd',
                            type:'year',
                            span:12,
                            search:true,
                        },
                        {
                            label: '使用单位',
                            prop: 'sydw',
                            type:'select',
                            search:true,
                            span:12,
                            dicData: dict.sydw,
                            rules: [
                                {
                                    required: true,
                                    message: '请输入使用单位',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '适用场所类别',
                            prop: 'sycslb',
                            type:'select',
                            search:true,
                            span:12,
                            dicData: dict.sycslb,
                            rules: [
                                {
                                    required: true,
                                    message: '请输入使用场所类别',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label:'评定等级',
                            hide:true,
                            formslot: true,
                            slot: true,
                            prop:'fieldContent',
                            span:24
                        },
                        {
                            label: '说明',
                            prop: 'sm',
                            type: 'textarea',
                            hide: 'true',
                            span:24
                        },
                        {
                            label: '创建人',
                            prop: 'cjr',
                            search:true,
                            editDisabled:'true',
                            span:12,
                        },
                        {
                            label:'创建单位',
                            prop:'cjdw',
                            span:12,
                            rules: [
                                {
                                    required: true,
                                    message: '请输入评定等级机关',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '时间范围',
                            prop: 'cxsj',
                            type: 'datetimerange',
                            search:true,
                            hide:'true',
                            display:false,
                            span:12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '创建时间',
                            prop: 'cjsj',
                            type: 'datetime',
                            editDisabled:'true',
                            span:12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '修改人',
                            prop: 'xgr',
                            hide:'true',
                            addDisplay:false,
                            editDisabled:'true',
                            span:12,
                        },
                        {
                            label: '修改时间',
                            prop: 'xgsj',
                            type: 'datetime',
                            hide:"true",
                            editDisabled:'true',
                            addDisplay:false,
                            span:12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                    ]
                },
                data1:[
                    {
                        famc:'旅馆业等级评定方案',
                        ssnd:'2018',
                        sydw:'0',
                        sycslb:'01',
                        sm: '郝建2019.03.20在公安局检查了改单位情况',
                        cjr:'郝建',
                        cjdw:'江门市公安局',
                        cjsj:'2019-03-20'
                    },
                    {
                        famc:'旅馆业等级评定方案',
                        ssnd:'2018',
                        sydw:'0',
                        sycslb:'01',
                        sm: '郝建2019.03.20在公安局检查了改单位情况',
                        cjr:'郝建',
                        cjdw:'江门市公安局',
                        cjsj:'2019-03-20'
                    },
                    {
                        famc:'旅馆业等级评定方案',
                        ssnd:'2018',
                        sydw:'0',
                        sycslb:'01',
                        sm: '郝建2019.03.20在公安局检查了改单位情况',
                        cjr:'郝建',
                        cjdw:'江门市公安局',
                        cjsj:'2019-03-20'
                    },
                    {
                        famc:'旅馆业等级评定方案',
                        ssnd:'2018',
                        sydw:'0',
                        sycslb:'01',
                        sm: '郝建2019.03.20在公安局检查了改单位情况',
                        cjr:'郝建',
                        cjdw:'江门市公安局',
                        cjsj:'2019-03-20'
                    },
                    {
                        famc:'旅馆业等级评定方案',
                        ssnd:'2018',
                        sydw:'0',
                        sycslb:'01',
                        sm: '郝建2019.03.20在公安局检查了改单位情况',
                        cjr:'郝建',
                        cjdw:'江门市公安局',
                        cjsj:'2019-03-20'
                    },
                ],
                tableOption2:{
                    addBtn:false,
                    editBtn:false,
                    addRowBtn:true,
                    cellBtn:true,
                    refreshBtn:false,
                    columnBtn:false,
                    filterBtn:false,
                    border:true,
                    size:'mini',
                    keyId: 'id',
                    column: [{
                        label: '等级',
                        prop: 'dj',
                        cell: true,
                    },
                        {
                            label:'',
                            prop:'option1',
                            type:'select',
                            dicData:dict.option,
                            cell:true,
                        },
                        {
                        label:'分值',
                        prop:'max',
                        cell:true,
                    },
                        {
                            label:'',
                            prop:'option2',
                            type:'select',
                            dicData:dict.option,
                            cell:true,
                        },
                        {
                            label:'分值',
                            prop:'min',
                            cell:true,
                        }]
                },
                tableData2:[
                    {
                        dj:'A',
                        option1:'0',
                        max:'',
                        option2:'lt',
                        min:'30',
                    },
                    {
                        dj:'B',
                        option1:'gt',
                        max:'30',
                        option2:'lt',
                        min:'70',
                    },
                    {
                        dj:'C',
                        option1:'gt',
                        max:'70',
                        option2:'0',
                        min:'',
                    },
                ],
            }
        },
        methods: {

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