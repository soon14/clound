<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-crud :option="option" :data="data"
                       :page="page"
                       @row-update="handleOpt"
                       @row-save="handleSav"
                       v-model="form">

                <template slot="menu" slot-scope="scope">

                    <el-button

                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            @click="handleDelete()">删除
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>


    </div>
</template>


<script>

    export default {
        data() {
            return {
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                form: {},
                data: [{
                    name: '治综资源库',
                    type: 'ORACLE',
                    ip:'10.40.42.31',
                    account:'za_res',
                    schema:'res',
                    updateDate: '2019-03-17 17:30',
                    pwd:'123456',
                    port:1521,
                    serverName:'orcl',
                    dept:'440700000000',
                    phone:'15915719754',
                    dbOwner:'翁工'

                }, {
                    name: '警钟资源库',
                    type: 'ORACLE',
                    ip:'10.40.42.31',
                    account:'za_jz',
                    schema:'jz',
                    pwd:'123456',
                    port:1521,
                    serverName:'orcl',
                    updateDate: '2019-03-17 17:30',
                    dept:'440700000000',
                    phone:'15915719754',
                    dbOwner:'翁工'
                },
                    {
                    name: '旅业库',
                    type: 'MYSQL',
                        ip:'68.196.18.3',
                        account:'za_bg',
                        schema:'bg',
                    updateDate: '2019-03-17 17:30',
                        pwd:'123456',
                        port:3306,
                        serverName:'db_bg',
                        dept:'440700000000',
                        phone:'15915719754',
                        dbOwner:'翁工'
                }
                ],
                option: {
                    dialogHeight: '100%',
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: true,
                    viewBtn: true,
                    delBtn: false,
                    column: [{
                        label: '库名称',
                        prop: 'name',
                        search:'true',
                        rules: [{
                            required: true,
                            message: '请输入数据库名称',
                            trigger: 'blur'
                        }]
                    },
                        {
                            label: '类型',
                            prop: 'type',
                            search:'true',
                            type:'select',
                            rules: [{
                                required: true,
                                message: '请选择数据类型',
                                trigger: 'blur'
                            }],
                            dicData:[{
                                label:'MYSQL',
                                value:'MYSQL'
                            },{
                                label:'ORACLE',
                                value:'ORACLE'
                            },{
                                label:'DB2',
                                value:'DB2'
                            },{
                                label:'SQL SERVER',
                                value:'SQL SERVER'
                            }
                            ]
                        },
                        {
                            label: 'IP地址',
                            prop: 'ip',
                            rules: [{
                                required: true,
                                message: '请输入数据库IP地址',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '账号',
                            prop: 'account',
                            rules: [{
                                required: true,
                                message: '请输入数据库账号',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '密码',
                            prop: 'pwd',
                            hide: true,
                            type:'password',
                            rules: [{
                                required: true,
                                message: '请输入数据库密码',
                                trigger: 'blur'
                            }]

                        },
                        {
                            label: '端口',
                            prop: 'port',
                            hide: true,
                            type:'number',
                            rules: [{
                                required: true,
                                message: '请输入数据库端口号',
                                trigger: 'blur'
                            }]

                        },
                        {
                            label: '实例名称',
                            prop: 'serverName',
                            hide: true,
                            rules: [{
                                required: true,
                                message: '请输入数据库名称、实例名',
                                trigger: 'blur'
                            }]
                        },

                        {
                            label: 'schema名',
                            prop: 'schema',
                        },
                        {
                            label: '来源单位',
                            prop: 'dept',
                            type:'select',
                            hide: true,
                            rules: [{
                                required: true,
                                message: '请选择来源单位',
                                trigger: 'blur'
                            }],
                            dicData:[{
                                label:'江门市公安局',
                                value:'440700000000'
                            },{
                                label:'附城派出所',
                                value:'440781510000'
                            },{
                                label:'南塘派出所',
                                value:'440781520000'
                            },{
                                label:'环南派出所',
                                value:'440781530000'
                            }
                            ]
                        },
                        {
                            label: '联系人',
                            prop: 'dbOwner',
                            hide: true,
                            rules: [{
                                required: true,
                                message: '请输入数据库联系人姓名',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '联系方式',
                            hide: true,
                            prop: 'phone',
                            type: 'phone',
                            value: '',
                            span: 12,
                            rules: [{

                                min: 6,
                                max: 20,
                                message: '长度在 11 个字符',
                                trigger: 'blur'
                            },
                                {
                                    required: true,
                                    message: '请输入数据库联系人电话',
                                    trigger: 'blur'
                                }
                            ]
                        },



                        {
                            label: '更新时间',
                            prop: 'updateDate',
                            type: 'date',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm',
                            editDisplay: false,
                            addDisplay: false,
                        }

                        ]
                }

            }
        },
        computed: {
            infoData() {
                return this.form.info
            }
        },
        methods:{
            handleOpt(row, index, done, loading){
                done();
                this.$notify({
                    title: '成功',
                    message: '操作成功',
                    type: 'success',
                    duration: 2000
                })
            },
            handleSav(row, done, loading){
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