
<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="option"
                       :data="tableData"
                       v-model="form"
                       ref="crud"
                       @on-load="getList"
                       @refresh-change="refreshChange"
                       @search-change="searchChange"
                       :page="page"
                       :table-loading="tableLoading">

                <template slot="menu" slot-scope="scope" >
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-view"
                               @click="handleDetail(scope.row)">查看
                    </el-button>
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-edit"
                               @click="contact(scope.row)">场所关联
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>
        <el-dialog :title="detailTitle" fullscreen :visible.sync="showDetail"  :append-to-body="applyToBody">
            <keep-alive>
                <file-Info v-bind:paramForm="paramForm"/>
            </keep-alive>

        </el-dialog>
        <el-dialog :title="contactTitle" fullscreen :visible.sync="showContact"  :append-to-body="applyToBody">
            <keep-alive>
                <contact-list v-bind:contact-form="contactForm"/>
            </keep-alive>
        </el-dialog>
    </div>
</template>


<script>
    import {findListPage} from "@/api/portal/archive/common/unitQuery";
    import fileInfo from './fileInfo/index.vue';
    import contactList from "../contact/contactList"

    export default {
        components: {
            fileInfo,
            "contactList" : contactList
        },
        props:["searchForm"],
        name:"jxyxt",
        data(){
            return {
                paramForm: {
                    dwmc:undefined,
                    zagldwbm:undefined,
                    menuId:undefined,
                    dwbh:undefined
                },
                form:{ },
                showDetail: false,
                applyToBody: true,
                detailTitle: '',
                viewFlag: false,

                contactTitle:'',//添加关联弹窗title
                showContact:false,//添加关联弹窗是否展示
                contactForm: {
                    lyxtType: undefined,
                    qtdwbh:undefined,
                    dwmc:undefined,
                    hylx: undefined,
                    placeState:undefined,
                    areaCode:undefined,
                    address:undefined,
                    sspcs:undefined,
                },
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
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
                    card:true,
                    column:[
                        {
                            label: "单位编号",
                            prop: "zagldwbm",
                            hide:true,
                            span:6
                        },
                        {
                            label: "单位名称",
                            prop: "dwmc",
                            search:true,
                            searchDefault:'',
                            span:6,

                        },
                        {
                            label:"经营地址",
                            prop:"单位地址地址编码",
                            hide:true,
                            span:6
                        },
                        {
                            label:"联系电话",
                            prop:"lxdh",
                            hide:true,
                            viewDisplay:true,
                            span:6
                        },
                        {
                            label:"法定代表人",
                            prop:"fddbrxm",
                            hide:true,
                            viewDisplay:true,
                            span:6
                        },
                        {
                            label:"行业类别",
                            prop:"dwlbdm",
                            search:true,
                            type:'select',
                            viewDisplay:true,
                            dicUrl: '/portal/dict/getDict/qylb/jx',
                            dicMethod: 'get',
                            dicQuery: {},
                            span:6
                        },
                        {
                            label:"经营状态",
                            prop:"qyztdm",
                            type:'select',
                            search:true,
                            viewDisplay:true,
                            dicUrl: '/portal/dict/getDict/qyzt/jx',
                            dicMethod: 'get',
                            dicQuery: {},
                            span:6
                        },
                        {
                            label:"单位性质",
                            prop:"jjlxdm",
                            type:'select',
                            dicUrl: '/portal/dict/getDict//jjlx/jx',
                            dicMethod: 'get',
                            dicQuery: {},
                            span:6
                        },
                        {
                            label:"责任民警警号",
                            prop:"zrmjjh",
                            hide:true,
                            span:6
                        },
                        {
                            label:"责任民警姓名",
                            prop:"zrmjxm",
                            hide:true,
                            span:6
                        },
                        {
                            label:"录入情况",
                            prop:"lrqk",
                            hide:true,
                            span:6
                        },
                        {
                            label:"辖区派出所",
                            prop:"sjgsdwmc",
                            type:'select',
                            span:6,
                        },
                        {
                            label:"行政区划",
                            prop:"dwdzssxqdm",
                            type:'tree',
                            search: true,
                            searchFilterable: true,
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
                            label: "总积分", prop: "pointScore",

                        },
                        {
                            label: "积分等级", prop: "pointType", search: true, type: 'select', searchFilterable: true
                            , dicUrl: '/admin/dict/type/jfdj',
                            dicMethod: 'get',
                            dicQuery: {},
                        },
                    ]
                },
            }
        },
        created(){
            this.paramForm.dwmc=this.searchForm.dwmc
            this.paramForm.menuId=this.searchForm.menuId
            this.option.column[1].searchDefault=this.paramForm.dwmc
        },
        computed: {
        },
        methods:{
            refreshChange() {
                this.getList(this.page)
            },

            getList(page, params) {
                this.tableLoading = true
                findListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, this.searchForm)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },

            searchChange(form) {
                this.searchForm.dwmc=form.dwmc
                this.searchForm.placeType= form.dwlbdm
                this.searchForm.placeState=form.qyztdm
                this.searchForm.orgCode=form.dwdzssxqdm,
                this.getList(this.page, this.searchForm)
                this.$emit("changeCount",this.searchForm);

            },
            handleDetail(row) {
                this.showDetail = true
                this.detailTitle = row.dwmc
                this.paramForm.menuId =  this.paramForm.menuId
                this.paramForm.dwbh = row.zagldwbm
            },
            contact(row){
                this.contactTitle='场所关联'
                this.showContact=true
                this.contactForm.lyxtType='2'
                this.contactForm.qtdwbh=row.zagldwbm
                this.contactForm.dwmc=row.dwmc
                this.contactForm.hylx='05'
                this.contactForm.address=row.dwdzqhnxxdz
                this.contactForm.areaCode=row.dwdzssxqdm
                this.contactForm.sspcs=row.sjgsdwmc
            }
        }
    }
</script>