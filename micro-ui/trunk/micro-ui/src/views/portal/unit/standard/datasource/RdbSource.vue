<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>关系型数据库</span>
                </div>
                    <div class="el-row text item" span="24" style="margin-left: -10px; margin-right: -10px;">
                        <div class="el-col el-col-8"  v-for="(item,index) in data" style="padding-left: 10px; padding-right: 10px;">
                            <div class="avue-card__item"><div class="avue-card__body">
                                <div v-if="item.type==='Oracle'" class="avue-card__avatar">
                                    <img src="../../../../../../public/img/portal/oracle.jpg" alt="">
                                </div>
                                <div v-if="item.type==='MySql'" class="avue-card__avatar">
                                    <img src="../../../../../../public/img/portal/mysql.jpg" alt="">
                                </div>
                                <div v-if="item.type==='SQLServer'" class="avue-card__avatar">
                                    <img src="../../../../../../public/img/portal/sqlserver.jpg" alt="">
                                </div>
                                <div v-if="item.type==='DB2'" class="avue-card__avatar">
                                    <img src="../../../../../../public/img/portal/db2.jpg" alt="">
                                </div>
                                <div class="avue-card__detail">
                                    <div class="avue-card__title">{{item.title}}</div>
                                    <div class="avue-card__info">{{item.text}}</div>
                                </div>
                            </div>
                                <div class="avue-card__menu">
                                    <span @click="addtype(item)">添加数据源</span>
                                    <span @click="listtype(item)">查看数据源</span>
                                </div>
                            </div>
                        </div>
                    </div>
            </el-card>
            <el-card class="box-card">
                <div slot="header" class="clearfix">
                    <span>非关系型数据库</span>
                </div>
                <div class="el-row text item" span="24" style="margin-left: -10px; margin-right: -10px;">
                    <div class="el-col el-col-8"  v-for="(item,index) in data" style="padding-left: 10px; padding-right: 10px;">

                    </div>
                </div>
            </el-card>

<!--            <avue-card  ref="form" :option="option" :data="data" v-model="form">
                <template slot="menu" slot-scope="scope">
                    <span @click.stop="addtype(scope.row,scope.index)">添加数据源</span>
                    <span @click.stop="listtype(scope.row,scope.index)">查看数据源</span>
                </template>

            </avue-card>-->
            <el-dialog title="添加数据源" fullscreen :visible.sync="addsource" :append-to-body="true">
                <avue-form  :option="addOption" :data="addData" v-model="addForm" ref="addForm">

                </avue-form>

                    <div slot="footer"  class="el-dialog--center">
                        <el-button type="primary" @click="handletest('addForm')">测试连接  </el-button>

                        <el-button type="primary" @click="submitForm('addForm')">保存 </el-button>

                        <el-button @click="resetForm('addForm')">清空 </el-button>
                    </div>
            </el-dialog>

            <el-dialog title="修改数据源" fullscreen :visible.sync="editsource" :append-to-body="true">
                <avue-form  :option="editOption" :data="editData" v-model="editForm" ref="editForm">

                </avue-form>


                <div slot="footer"  class="el-dialog--center">
                    <el-button type="primary" @click="edittest('editForm')">测试连接  </el-button>

                    <el-button type="primary" @click="handleOpt('editForm')">修改 </el-button>

                    <el-button type="primary" @click="editsource=false">关闭 </el-button>

                </div>
            </el-dialog>

            <el-dialog title="数据源列表" fullscreen :visible.sync="listsource" :append-to-body="true">
                <el-form :inline="true" :v-model="formInline" ref="formInline" class="demo-form-inline">
                    <el-form-item label="数据库名称">
                        <el-input v-model="formInline.name" placeholder="数据库名称"></el-input>
                    </el-form-item>
                    <el-form-item label="数据库类型">
                    <el-select  v-model="formInline.type"  placeholder="数据库类型">
                        <el-option label="MySql" value="MySql"></el-option>
                        <el-option label="Oracle" value="Oracle"></el-option>
                        <el-option label="SQLServer" value="SQLServer"></el-option>
                        <el-option label="DB2" value="DB2"></el-option>
                    </el-select >

                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary"
                                   size="small"
                                   icon="el-icon-search"
                                   @click="handlelistsource('formInline')">搜索</el-button>
                    </el-form-item>
                    <el-form-item>
                        <el-button
                                size="small"
                                icon="el-icon-delete"
                                @click="reset('formInline')">清空</el-button>
                    </el-form-item>
                </el-form>
                    <avue-crud :option="listOption" :data="listData"
                               :page="page"
                               :table-loading="tableLoading"

                               @refresh-change="getList"
                               @search-change="handlelistsource"
                               @row-update="handleOpt"
                               v-model="listform" ref="listForm">

                        <template slot-scope="scope" slot="menuLeft">
                            <el-button type="primary"
                                       icon="el-icon-plus"
                                       size="small"
                                       @click="addalltype('formInline')">新增</el-button>
                        </template>

                        <template slot="menu" slot-scope="scope">
                            <el-button size="mini"
                                       type="text"
                                       icon="el-icon-edit"
                                       @click="editbutton(scope.row,scope.index)">编辑
                            </el-button>

                            <el-button
                                    size="small"
                                    type="text"
                                    icon="el-icon-delete"
                                    @click="handleDelete(scope.row,scope.index)">删除
                            </el-button>
                        </template>
                    </avue-crud>
            </el-dialog>
        </basic-container>


    </div>
</template>
<script>
    import { fetchList,handleCheck, getObj,addObj, putObj, delObj } from '@/api/portal/metadata/datasource/RdbSource'
    import { tableOption,addsourceOption,listOption,editsourceOption } from '@/const/crud/portal/metadata/datasource/RdbSource'
    import { mapGetters } from 'vuex'
    export default {
        name: "RdbSource",
        data() {

            return {
                formInline:{
                    name:'',
                    type:'',
                },
                form:{
                    type:''
                },
                addForm:{
                    type:'',
                    ip:'',
                    account:'',
                    pwd:'',
                    port:'',
                    serverName:'',
                },
                editForm:{
                    type:'',
                    ip:'',
                    account:'',
                    pwd:'',
                    port:'',
                    serverName:'',
                },
                listform:{ type:'',name:''},
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableLoading: false,
                addsource:false,
                listsource:false,
                editsource:false,
                listOption:listOption,
                addOption:addsourceOption,
                addData:[],
                editOption:editsourceOption,
                editData:[],
                listData:[],
                option: tableOption,
                data: [
                   {
                    title: 'Oracle',
                    type:'Oracle',
                    text: 'Oracle是甲骨文公司的一款关系数据库管理系统,它是在数据库领域一直处于领先地位的产品。',
                },
                    {
                    title: 'MySql',
                     type:'MySql',
                    text:  'MySQL是一种开放源代码的关系型数据库管理系统（RDBMS）。',
                },
                    {
                    title: 'SQL Server',
                    type:'SQLServer',
                    text: 'SQL Server是由Microsoft开发和推广的关系数据库管理系统（RDBMS）。',
                },
                    {
                        title: 'DB2',
                        type:'DB2',
                        text: 'DB2 是美国IBM公司开发的一套关系型数据库管理系统，它主要的运行环境为UNIX。',
                    }
                ]
            }
        },
        computed: {
        },
        methods:{
            listtype:function(row, index){

                this.listsource = true;
                this.formInline.type = row.type
                this.$emit("input",this.formInline)
                this.handlelistsource();
            },

            addtype:function(row, index){
                this.addsource = true;
                this.addForm = row
                this.$emit("input",this.addForm)
            },

            addalltype:function(formInline){
                this.addsource = true;
                this.addForm.type = this.formInline.type
                this.$emit("input",this.addForm)
            },

            editbutton:function(row, index){
                this.editsource = true;
                this.editForm=row,
                this.$emit("input",this.editForm)
            },

            handlelistsource(param) {
                this.page.page = 1;
                this.getList(this.page,this.formInline)
            },

            getList(page, params) {
                this.tableLoading = true
                fetchList(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    type: this.formInline.type
                }, params)).then(response => {
                    this.listData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },

            /**
             * @title 测试连接数据库
             * @param params 为当前的数据
             **/
            handletest(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                var params = this.addForm;
                let JdbcUrl = '';
                if(params.type == 'MySql'){
                    JdbcUrl += 'jdbc:mysql://'+params.ip+':'+params.port+'/'+params.serverName+'';
                }else if(this.addForm.type == 'Oracle'){
                    JdbcUrl += 'jdbc:oracle:thin:@'+params.ip+':'+params.port+'/'+params.serverName+'';
                }else if (this.addForm.type == 'DB2'){
                    JdbcUrl += 'jdbc:DB2://'+params.ip+':'+params.port+'/'+params.serverName+'';
                }else if (this.addForm.type == 'SQLServer'){
                    JdbcUrl += 'jdbc:sqlserver://'+params.ip+':'+params.port+'';
                }

                handleCheck(Object.assign({
                    type:this.addForm.type,
                    account: this.addForm.account,
                    pwd:this.addForm.pwd,
                    JdbcUrl:JdbcUrl,
                }, formName)).then(response => {
                        this.$alert(response.data.message, '连接结果', {
                            confirmButtonText: '确定',
                            type: 'warning',
                            callback: action => {

                            },
                        });
                })
                    } else {
                        this.$message({
                            showClose: true,
                            message: '请填写完整信息后再测试!',
                            type: 'error submit!!'
                        })

                        return false;
                    }
                });
            },

            /**
             * @title edit测试连接数据库
             * @param params 为当前的数据
             **/
            edittest(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        var params = this.editForm;
                        let JdbcUrl = '';
                        if(params.type == 'MySql'){
                            JdbcUrl += 'jdbc:mysql://'+params.ip+':'+params.port+'/'+params.serverName+'';
                        }else if(params.type == 'Oracle'){
                            JdbcUrl += 'jdbc:oracle:thin:@'+params.ip+':'+params.port+'/'+params.serverName+'';
                        }else if (params.type == 'DB2'){
                            JdbcUrl += 'jdbc:DB2://'+params.ip+':'+params.port+'/'+params.serverName+'';
                        }else if (params.type == 'SQLServer'){
                            JdbcUrl += 'jdbc:sqlserver://'+params.ip+':'+params.port+'';
                        }

                        handleCheck(Object.assign({
                            type:params.type,
                            account: params.account,
                            pwd:params.pwd,
                            JdbcUrl:JdbcUrl,
                        }, formName)).then(response => {
                            this.$alert(response.data.message, '连接结果', {
                                confirmButtonText: '确定',
                                type: 'warning',
                                callback: action => {

                                },
                            });
                        })
                    } else {
                        this.$message({
                            showClose: true,
                            message: '请填写完整信息后再测试!',
                            type: 'error submit!!'
                        })

                        return false;
                    }
                });
            },

            /**
             * @title 数据添加
             * @param data 为当前的数据
             **/
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        addObj(this.addForm).then((res) => {
                            if(res.status==200){
                               /* this.$refs[formName].resetForm();*/
                                this.addsource = false;
                                this.handlelistsource();
                                this.$notify({
                                    title: '成功',
                                    message: '保存成功',
                                    type: 'success',
                                    duration: 2000
                                }).catch(() => {
                                    loading();
                                });

                            }else{
                                this.$alert("提交失败", '提交结果', {
                                    confirmButtonText: '确定',
                                    type: 'warning',
                                    callback: action => {

                                    },
                                });
                            }
                        })
                    } else {
                        this.$message({
                            showClose: true,
                            message: '请填写完整信息后再保存!',
                            type: 'error submit!!'
                        })

                        return false;
                    }
                });
            },

            resetForm(formName) {
                this.$refs[formName].resetForm();
                this.addForm.type = this.formInline.type
                this.$emit("input",this.addForm)
            },

            reset(formName) {
                this.$refs[formName].resetFields();
            },


            /**
             * @title 修改
             * @param data 为当前的数据
             **/
            handleOpt(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        putObj(this.editForm).then((res) => {
                            if(res.status==200){
                                this.editsource = false;
                                this.handlelistsource();
                                this.$notify({
                                    title: '成功',
                                    message: '修改成功',
                                    type: 'success',
                                    duration: 2000
                                }).catch(() => {
                                    loading();
                                });

                            }else{
                                this.$alert("修改失败", '修改结果', {
                                    confirmButtonText: '确定',
                                    type: 'warning',
                                    callback: action => {

                                    },
                                });
                            }
                        })
                    } else {
                        this.$message({
                            showClose: true,
                            message: '请填写完整信息后再修改!',
                            type: 'error submit!!'
                        })

                        return false;
                    }
                });
            },

            handleDelete(row) {
                this.$confirm('此操作将永久删除, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    delObj(row.id,row.type).then(() => {
                        this.handlelistsource();
                        this.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success',
                            duration: 2000
                        })
                    })
                })
            },
            },
    }
</script>
<style>

</style>