
<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="option"
                       :data="tableData"
                       v-model="form"
                       ref="crud"
                       @on-load="getList"
                       @search-change="searchChange"
                       @refresh-change="refreshChange"
                       :page="page"
                       :table-loading="tableLoading">

                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="NoGz(scope.row,scope.index)">取消关注
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-view"
                               @click="handleDetail(scope.row)">档案预览
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog :title="detailTitle" fullscreen :visible.sync="showDetail" :append-to-body="applyToBody">
            <place-jump :param="paramForm"></place-jump>
        </el-dialog>
    </div>
</template>


<script>
    import {findPage,NoGz} from "@/api/portal/archive/csgz/csgz";
    import placeJump from "../contact/placeJump";
    let dict ={
        jyzt:[
            {label: '正常', value: '正常'},
            {label: '停业', value: '停业'},
            {label: '歇业', value: '歇业'},
            {label: '勒令停业', value: '勒令停业'},
            {label: '自主停业', value: '自主停业'},
            {label: '取缔', value: '取缔'},
            {label: '倒闭', value: '倒闭'},
            {label: '注销', value: '注销'},
            {label: '装机开业', value: '装机开业'},
            {label: '装机停业', value: '装机停业'},
            {label: '未装机开业', value: '未装机开业'},
            {label: '未装机停业', value: '未装机停业'},
            {label: '其它', value: '其它'},
        ],
    }
    export default {
        name:"csgz",
        components: {
            "placeJump":placeJump
        },
        data(){
            return {
                form:{},
                showDetail: false,
                applyToBody: true,
                detailTitle: '',
                viewFlag: false,
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                },
                paramForm: {
                    lyxtType:'',
                    dwbh:'',
                    unitId:'',
                    compId:'',//娱乐场所需两个id，一场所备案id（unitId），二单位id（compId）
                },//场所详情页面跳转所需参数
                tableData: [],
                option: {
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    page:false,
                    align:'center',
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn:false,
                    delBtn:false,
                    addBtn:false,
                    viewBtn:false,
                    menuWidth:'100',
                    column:[
                        {
                            label: "单位名称",
                            prop: "dwmc",
                            search:true,
                            span:6
                        },
                        {
                            label: "经营状态",
                            prop: "jyzt",
                            type:'select',
                            dicData:dict.jyzt,
                            search:true,
                            span:6
                        },
                        {
                            label: "行业类型",
                            prop: "hylb",
                            dicUrl: '/admin/dict/type/HYLX',
                            span:6
                        },
                        {
                            label:"积分等级",
                            prop:"jfdj",
                        },
                        {
                            label: "行政区划",
                            prop: "ssds",
                            type:'tree',
                            search:true,
                            dicUrl: '/portal/area/areaData',
                            dicMethod: 'get',
                            expandLevel: 1,
                            dicQuery: {},
                            props: {
                                label: "name",
                                value: 'code'
                            },
                            span:6,

                        },
                        {
                            label:"所属区县",
                            prop:"ssqx",
                            hide:true,
                        },
                        {
                            label:"所属派出所",
                            prop:"sspcs",
                        },
                        {
                            label:"单位地址",
                            prop:"dwdz",
                        },
                        {
                            label:"来源系统",
                            prop:"lyxt",
                            dicUrl: '/admin/dict/type/unit_source_type',
                            span:6
                        }
                    ]
                },
            }
        },
        created(){

        },
        computed: {
        },
        methods:{
            handleDetail(row) {
                this.showDetail = true
                this.detailTitle = row.dwmc
                this.paramForm.lyxtType=row.lyxt
                this.paramForm.unitId=row.dwbh
            },
            NoGz(row,index) {
                this.$confirm('确定取消关注该场所吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    NoGz(row.dwbh).then(() => {
                        this.$message({
                            showClose: true,
                            message: '取消成功',
                            type: 'success',
                        })
                        this.getList(this.page)
                    }).catch(function () {
                        this.getList(this.page)
                    })
                })
            },

            refreshChange(){
                this.getList(this.page)
            },
            searchChange(form) {
                this.getList(this.page, form)
            },
            getList(page, params) {
                try{
                    let   param=this.$refs["crud"].searchForm||{}
                    params=param
                }catch (e) {}
                this.tableLoading = true
                findPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
        }
    }
</script>