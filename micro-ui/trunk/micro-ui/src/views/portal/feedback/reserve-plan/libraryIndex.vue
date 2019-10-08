

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
                        <template slot="ydfaForm"
                                  slot-scope="scope">
                            <avue-ueditor v-model="form.ydfa"></avue-ueditor>
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
    Vue.use(avueUeditor);
    export default {
        name: "table_user",
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
                    label:'旅馆业',
                    value:0,
                    children:[
                        {
                            id:1,
                            label:'聚纵吸毒',
                            value:0,
                        },{
                            id:2,
                            label:'非法聚赌',
                            value:0,
                        }
                    ]
                },{
                    id:3,
                    label:'机修业',
                    value:0,
                    children:[
                        {
                            id:4,
                            label:'盗抢车辆维修',
                            value:0,
                        },{
                            id:5,
                            label:'频繁维修',
                            value:0,
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
                        mc:'非法聚赌解决方案一',
                        bzsj:'2019-03-27',
                        lx:'非法聚赌',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'0',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                    },
                    {
                        mc:'非法聚赌解决方案二',
                        bzsj:'2019-03-28',
                        lx:'非法聚赌',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'1',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                    },
                    {
                        mc:'非法聚赌解决方案三',
                        bzsj:'2019-03-29',
                        lx:'非法聚赌',
                        zt:'1',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'2',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                    },
                    {
                        mc:'非法聚赌解决方案四',
                        bzsj:'2019-03-29',
                        lx:'非法聚赌',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'3',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                    },
                    {
                        mc:'聚纵吸毒解决方案一',
                        bzsj:'2019-03-21',
                        lx:'聚纵吸毒',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'0',
                        qzxs:10,
                    },
                    {
                        mc:'聚纵吸毒解决方案二',
                        bzsj:'2019-03-22',
                        lx:'聚纵吸毒',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'1',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                    },
                    {
                        mc:'聚纵吸毒解决方案三',
                        bzsj:'2019-03-21',
                        lx:'聚纵吸毒',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'2',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                    },
                    {
                        mc:'聚纵吸毒解决方案四',
                        bzsj:'2019-03-22',
                        lx:'聚纵吸毒',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'3',
                        qzxs:10,
                        bzr:'张三',
                        bzbm:'江门市公安局',
                    },
                    {
                        mc:'聚纵吸毒解决方案⑤',
                        bzsj:'2019-03-22',
                        lx:'聚纵吸毒',
                        zt:'0',
                        remark:'信息说明',
                        ydfa:'<h2>派出十名武警出动（狙击手1名）,配置装甲车及各式各样的高端武器、前门守卫两人、后门守卫两人、狙击手选好有利位置、其余人数直接冲进去抓人！</h2>',
                        jb:'3',
                        qzxs:50,
                        bzr:'张三',
                        bzbm:'江门市公安局',
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
                            label: '名称',
                            prop: 'mc',
                            search: true,

                        },
                        {
                            label: '类型',
                            prop: 'lx',
                            slot:true,
                            formslot:true,
                        },{
                            label: '级别',
                            prop: 'jb',
                            type:'select',
                            overHidden:true,
                            dicData:[
                                {label:'一般（Ⅳ级）', value:'0'},
                                {label:'较重（Ⅲ级）', value:'1'},
                                {label:'严重（Ⅱ级）', value:'2'},
                                {label:'特别严重（Ⅰ级）', value:'3'},
                            ],
                            search:true,


                        },
                        {
                            label: '状态',
                            prop: 'zt',
                            type: 'select',
                            slot:true,
                            dicData: [{ label: '启用', value: '0' }, { label: '停用', value: '1' }],
                            search: true
                        },

                        {
                            label: '权重',
                            tip:'权重系数越高说明事件越严重',
                            prop: 'qzxs',
                            type:'number',

                        },
                        {
                            label: '编制时间',
                            prop: 'bzsj',
                            type: "datetime",
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                            addDisplay:false,
                            editDisplay:false,
                        },
                        {
                            label: '编制人',
                            prop: 'bzr',
                            addDisplay:false,
                            editDisplay:false,
                            hide:true,
                        },{
                            label: '编制部门',
                            prop: 'bzbm',
                            addDisplay:false,
                            editDisplay:false,
                            hide:true,
                        },
                        {
                            label: '说明',
                            prop: 'remark',
                            overHidden:true,
                            hide:true
                        }
                        ,
                        {
                            label: '应对措施',
                            prop: 'ydfa',
                            span:24,
                            slot:true,
                            formslot:true,
                            hide:true,
                        },



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

