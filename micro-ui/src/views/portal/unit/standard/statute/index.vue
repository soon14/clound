

<template>
    <div class="user">
        <basic-container>
            <el-row :span="24">
                <el-col :xs="24"
                        :sm="24"
                        :md="5"
                        class="user__tree">

                    <avue-tree :option="treeOption"
                               :data="treeData"
                               ></avue-tree>
                </el-col>
                <el-col :xs="24"
                        :sm="24"
                        :md="19"
                        class="user__main">
                    <avue-crud :option="option"
                               ref="crud"
                               v-model="form"
                               :page="page"
                               :table-loading="listLoading"
                               :data="data">

                        <template slot="fjForm" slot-scope="scope">
                            <file-upload tbPk="licence_id"
                                         tbName="sys_file"/>
                        </template>
                        <template slot="lx"
                                  slot-scope="scope">
                           {{scope.row.lx}}
                        </template>
                        <template slot="zt"
                                  slot-scope="scope">
                            <span :style="scope.row.zt==0?'color: green':'color: red'">
                                {{scope.row.zt=='0'?'启用':'停用'}}
                            </span>
                        </template>
                        <template slot="lxForm"
                                  slot-scope="scope">
                            <avue-input v-model="form.lx"
                                        type="tree"
                                        placeholder="请选择类型"
                                        :dic="treeData"
                                        :props="defaultProps"></avue-input>
                        </template>
                        <template slot="commentForm"
                                  slot-scope="scope">
                            <avue-ueditor v-model="form.comment"
                            ></avue-ueditor>
                        </template>
                        <template slot-scope="scope"
                                  slot="menuBtn">
                            <el-dropdown-item divided
                                              @click.native="handleDel(scope.row,scope.index)">删 除
                            </el-dropdown-item>
                        </template>

                    </avue-crud>
                </el-col>
            </el-row>
        </basic-container>


    </div>

</template>

<script>
    import avueUeditor from 'avue-plugin-ueditor/packages';
    import fileUpload from "@/page/file/fileUpload"
    Vue.use(avueUeditor);
    export default {
        name: "statute",
        components: { "fileUpload" : fileUpload},
        data() {
            return {
                viewDialog:false,
                title:'预览',
                defaultProps: {
                    label: "label",
                    value: 'value',
                },
                treeOption: {
                    nodeKey: 'id',
                    addBtn: true,
                    menu: false,
                    props: {
                        label: 'label',
                        value: 'value'
                    }
                },
                treeData: [
                    {
                    id:0,
                    label:'特种行业',
                    value:0,
                    children:[
                        {
                            id:1,
                            label:'旅馆业',
                            value:1,
                        },{
                            id:2,
                            label:'机修业',
                            value:2,
                        }
                    ]
                },{
                    id:3,
                    label:'公共场所',
                    value:0,
                    children:[
                        {
                            id:4,
                            label:'歌舞娱乐场所',
                            value:4,
                        },{
                            id:5,
                            label:'按摩娱乐场所',
                            value:5,
                        }
                    ]
                }],
                fromDetail:{},

                treeDeptData: [],
                checkedKeys: [],
                page: {
                    total: 9, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                data: [
                    {
                        bt:'旅馆业法律规定',
                        bzsj:'2019-03-28',
                        bzr:'高妙妙',
                        bzbm:'江门市公安局',
                        lx:'旅馆业',
                        remark:'不要触碰法律底线',
                        comment:'<ul><li class="ql-align-center"><span class="ql-size-huge">法律底线不是你能触摸的</span><strong><span class="ql-cursor">﻿</span></strong></li></ul>'
                     },
                    {
                        bt:'机修业法律规定',
                        bzsj:'2019-03-28',
                        bzr:'高妙妙',
                        bzbm:'江门市公安局',
                        lx:'机修业',
                        remark:'不要触碰法律底线',
                        comment:'<ul><li class="ql-align-center"><span class="ql-size-huge">法律底线不是你能触摸的</span><strong><span class="ql-cursor">﻿</span></strong></li></ul>'
                    },{
                        bt:'歌舞娱乐场所法律规定',
                        bzsj:'2019-03-28',
                        bzr:'高妙妙',
                        bzbm:'江门市公安局',
                        lx:'歌舞娱乐场所',
                        remark:'不要触碰法律底线',
                        comment:'<ul><li class="ql-align-center"><span class="ql-size-huge">法律底线不是你能触摸的</span><strong><span class="ql-cursor">﻿</span></strong></li></ul>'
                    }

                ],
                option:   {
                    dialogFullscreen: true,
                    dialogHeight:'100%',
                    border: true,
                    index: true,
                    delBtn:false,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    editBtn: true,
                    menuType:'menu',
                    align: 'center',
                    addBtn: true,
                    viewBtn:true,
                    menuWidth:'120',
                    card: true,
                    column: [
                        {
                            label: '标题',
                            prop: 'bt',
                            search: true,

                        },{
                            label: '类型',
                            prop: 'lx',
                            formslot:true,

                        },
                        {
                            label: '编制时间',
                            prop: 'bzsj',
                            type: "daterange",
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                            addDisplay:false,
                            editDisplay:false,
                            viewDisplay:false,
                            search:true,
                        },
                        {
                            label: '编制人',
                            prop: 'bzr',
                            addDisplay:false,
                            editDisplay:false,

                        },{
                            label: '编制部门',
                            prop: 'bzbm',
                            addDisplay:false,
                            editDisplay:false,
                        },
                        {
                            label: '说明',
                            prop: 'remark',
                            overHidden:true,
                            hide:true,

                        },
                        {
                            label: '内容',
                            prop: 'comment',
                            overHidden:true,
                            slot:true,
                            span:24,
                            formslot:true,
                            hide:true
                        },
                        {
                            label: '附件',
                            prop: 'fj',
                            slot:true,
                            span:24,
                            formslot:true,
                            hide:true
                        }




                    ]
                },
                listLoading: false,
                form: {},
            };
        },


        created() {

        },
        methods: {
            openDialog(row){
                this.viewDialog=true
                    this.title=row.mc
                this.fromDetail=row

            },
            handleDel(row, index){
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
            }




        }
    };
</script>
<style lang="scss">
    .user {
        height: 100%;

        &__tree {
            padding-top: 3px;
            padding-right: 20px;
        }

        &__main {
            .el-card__body {
                padding-top: 0;
            }
        }
    }
</style>

