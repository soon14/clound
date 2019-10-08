<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-crud :option="option" :data="data"
                       :page="page"
                       @row-update="handleOpt"
                       @row-save="handleSav"
                       v-model="form">
                <template slot="zzhm"
                          slot-scope="scope">
                    <button type="button" class="el-button el-button--text el-button--small"  @click="openDialog(scope.row.zzhm)" >{{scope.row.zzhm}}</button>
                </template>
                <template slot="imgForm" slot-scope="scope">
                   <file-upload tbPk="licence_id"
                                tbName="sys_file"/>
                </template>
                <template slot="menu" slot-scope="scope">

                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="openDialog(scope.row.zzhm)">预览
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
        <el-dialog :title="title"  :visible.sync="view" append-to-body>
            <avue-detail :option="option" v-model="fromDetail" >
                <template slot="img" slot-scope="scope">
                    <img height="100px" width="100px" src="/img/portal/timg.png"/>
                </template>
            </avue-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="view=false">关 闭
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import fileUpload from "@/page/file/fileUpload"
    export default {
        components: { "fileUpload" : fileUpload},
        data() {
            return {
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                view:false,
                title:'预览',
                form: {},
                fromDetail:{
                    zzmc:'2',
                    zzhm:'注册号44071247564',
                    fzrq:'2018-09-12 12:22',
                    yxqq:'2019-01-01 12:22',
                    gq:'1',
                    fzdw:'440702',
                    remark:'江门市工商管理局颁发',
                    spr:'张某某',
                    sprq:'2018-09-12 12:22'
                },
                data: [{
                    zzmc:'0',
                    zzhm:'注册号44071247564',
                    fzrq:'2018-09-12 12:22',
                    yxqq:'2019-01-01 12:22',
                    gq:'1',
                    fzdw:'440703',
                    remark:'开平市消防大队局颁发',
                    spr:'张某某',
                    sprq:'2018-09-12 12:22'




                },{
                    zzmc:'2',
                    zzhm:'江公消验XXXX',
                    fzrq:'2018-09-12 12:22',
                    yxqq:'2019-01-01 12:22',
                    gq:'1',
                    fzdw:'440702',
                    remark:'江门市工商管理局颁发',
                    spr:'张某某',
                    sprq:'2018-09-12 12:22'




                },],
                option: {
                    dialogHeight: '100%',
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: true,
                    viewBtn: false,
                    delBtn: false,
                    editBtn:true,
                    card:true,
                    column: [
                        {
                            label: '证照名称',
                            prop: 'zzmc',
                            search:true,
                            type:'select',
                            dicData:[{
                                label:'消防安全审核意见书',
                                value:'0'
                            },{
                                label:'卫生许可证',
                                value:'1'
                            },{
                                label:'工商营业执照',
                                value:'2'
                            },{
                                label:'特种行业经营许可证',
                                value:'3'
                            },{
                                label:'其他',
                                value:'4'
                            }]
                        },
                        {
                            label: '证照号码',
                            prop: 'zzhm',
                            search:true,
                            slot:true,
                        },
                        {
                            label: '发证日期',
                            prop: 'fzrq',
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',

                        },
                        {
                            label: '有效期',
                            prop: 'yxqq',
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',

                        },
                        {
                            label: '过期',
                            prop: 'gq',
                            type:'select',
                            dicData:[{
                                label:'是',
                                value:'0'
                            },{
                                label:'否',
                                value:'1'
                            }],
                            addDisplay:false,
                            editDisplay:false

                        },
                        {
                            label: '发证单位',
                            prop: 'fzdw',
                            type:'select',
                            dicData:[{
                                label:'江门市工商管理局',
                                value:'440702'
                            },{
                                label:'开平市消防大队',
                                value:'440703'
                            }]

                        },
                        {
                            label: '审批人',
                            prop: 'spr',
                            addDisplay:false,
                            editDisplay:false

                        },{
                            label: '审批日期',
                            prop: 'sprq',
                            addDisplay:false,
                            editDisplay:false

                        },{
                            label: '备注',
                            prop: 'remark',
                            hide:true,
                            span:24

                        }, {
                            label: '证照',
                            prop: 'img',
                            formslot:true,
                            slot:true,
                            hide:true,
                            span:24

                        },

                    ],

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
            openDialog(name) {
                this.view = true
                this.title = name

            },
            handleOpt(row, index, done, loading) {
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