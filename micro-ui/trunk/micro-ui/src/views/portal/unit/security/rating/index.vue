<template>
    <div class="execution">
        <basic-container>
            <avue-tabs :option="option" @change="handleChange"></avue-tabs>
            <span v-if="type.prop==='tab1'">
                <avue-crud ref="crud"
                           :page="page"
                           :option="tabOption1"
                           :data="data1"
                           @row-update="handleUpdate"
                           @row-save="handleSav"
                           @row-del="handleDelete"
                           v-model="form">
                <template slot="pdrq" slot-scope="scope">
                    <button type="button" class="el-button el-button--text el-button--small"  @click="unitView=true">{{scope.row.pdrq}}</button>
                </template>

                 <template slot="menu" slot-scope="scope">
                    <el-button :size="scope.size" :type="scope.type" @click="">查看历史轨迹</el-button>
                </template>
                </avue-crud>

            </span>
            <span v-else-if="type.prop==='tab2'">
                <avue-crud :page="page"
                           :option="tabOption2"
                           :data="data2"
                           >
                </avue-crud>
            </span>
        </basic-container>
        <el-dialog title="等级评定信息" fullscreen :visible.sync="unitView"  >
                <avue-detail :option="tabOption1" v-model="fromDetail"></avue-detail>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="unitView=false">关 闭</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    let dict ={
        pddj:[
            {"label": "A级", "value": "01"},
            {"label": "B级", "value": "02"},
            {"label": "C级", "value": "03"},
            {"label": "D级", "value": "04"},
            {"label": "E级", "value": "05"},
       ]
    }
    export default {
        data() {
            return {
                type:{},
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                option:{
                    column: [{
                        label: '等级评定信息',
                        prop: 'tab1',
                    }, {
                        label: '半年日常检查情况',
                        prop: 'tab2',
                    },]
                },
                unitView:false,
                form: {},
                fromDetail:{
                    pdrq:'2019-03-20',
                    pddj:'01',
                    pddjjg:'公安局',
                    pddjfzrxm:'郝建',
                    lrr:'郝建',
                    bz: '郝建2019.03.20在公安局检查了改单位情况',
                    zhxgr:'郝建',
                    lrrq:'2019-03-20',
                    zhxgrrq:'2019-03-20'
                },
                tabOption1:{
                    indexLabel: '序号',
                    viewBtn: false,
                    editBtn: true,
                    delBtn: false,
                    listLoading: true,
                    border: true,
                    column: [
                        {
                            label: '评定日期',
                            prop: 'pdrq',
                            type: 'datetime',
                            slot: true,
                            span:12,
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            rules: [
                                {
                                    required: true,
                                    message: '请输入评定日期',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '评定等级',
                            prop: 'pddj',
                            type:'select',
                            span:12,
                            dicData: dict.pddj,
                            rules: [
                                {
                                    required: true,
                                    message: '请输入评定等级',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label:'评定等级机关',
                            prop:'pddjjg',
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
                            label: '评定负责人姓名',
                            prop: 'pddjfzrxm',
                            span:12,
                        },
                        {
                            label: '备注',
                            prop: 'bz',
                            formWidth: '100%',
                            type: 'textarea',
                            hide: 'true',
                            span:24
                        },
                        {
                            label: '录入人',
                            prop: 'lrr',
                            hide: 'true',
                            span:12,
                        },
                        {
                            label: '录入日期',
                            prop: 'lrrq',
                            type: 'datetime ',
                            hide: 'true',
                            span:12,
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '最后修改人',
                            prop: 'zhxgr',
                            hide: 'true',
                            span:12,
                        },
                        {
                            label: '最后修改日期',
                            prop: 'zhxgrrq',
                            type: 'datetime',
                            hide: 'true',
                            span:12,
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                    ]
                },
                data1:[
                    {
                        pdrq:'2019-03-20',
                        pddj:'01',
                        pddjjg:'公安局',
                        pddjfzrxm:'郝建',
                        lrr:'郝建',
                        bz: '郝建2019.03.20在公安局检查了改单位情况',
                        zhxgr:'郝建',
                        lrrq:'2019-03-20',
                        zhxgrrq:'2019-03-20'
                    },
                    {
                        pdrq:'2019-03-20',
                        pddj:'02',
                        pddjjg:'公安局',
                        pddjfzrxm:'张上',
                        lrr:'张上',
                        zhxgr:'张上',
                        lrrq:'2019-03-20',
                        pddjfzrxm:'张上',
                    },
                ],
                tabOption2:{
                    indexLabel: '序号',
                    viewBtn: true,
                    editBtn: false,
                    delBtn: false,
                    addBtn: false,
                    listLoading: true,
                    border: true,
                    column: [
                        {
                            label: '检查时间',
                            prop: 'jcsj',
                            type:'datetime',
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                        },
                        {
                            label: '检查人',
                            prop: 'jcr',
                        },
                        {
                            label: '检查单位',
                            prop: 'jcdw',
                        },
                        {
                            label: '处理意见',
                            prop: 'clyj',
                        },
                        {
                            label: '处理结果',
                            prop: 'cljg',
                        },
                        {
                            label: '当前分数',
                            prop: 'dqfs',
                        },
                        {
                            label: '最后修改人',
                            prop: 'zhxgr',
                            hide: 'true',
                        },
                    ]
                },
                data2:[
                    {
                        jcsj:'2019-03-20',
                        jcr:'郝建',
                        jcdw:'公安局',
                        clyj:'罚款',
                        cljg:'存在问题',
                        dqfs:'88分',
                        zhxgr:'郝建',
                    },
                    {
                        jcsj:'2019-03-20',
                        jcr:'郝建',
                        jcdw:'公安局',
                        clyj:' ',
                        cljg:'正常',
                        dqfs:'100分',
                        zhxgr:'郝建',
                    },
                ],
            }
        },
        created(){
            this.type = this.option.column[0];
        },
        methods: {
            handleChange(column) {
                this.type = column
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