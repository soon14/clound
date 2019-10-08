<template>
 <div class="execution">
   <basic-container>
    <avue-crud :option="option"
               :data="tableData"
               v-model="form"
               ref="crud"
               :page="page"
               @on-load="getList"
               @search-change="searchChange"
               @refresh-change="searchChange"
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
             <kyzy-index v-bind:param-form="paramForm"/>
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
    import {countAll, findListPage, findOne} from "@/api/portal/archive/common/unitQuery";
    import kyzyIndex from "./index";
    import contactList from "../contact/contactList"
    export default {
        components: {
            "kyzyIndex": kyzyIndex,
            "contactList" : contactList
        },
        props:["searchForm"],
        data(){
            return {
                form:{
                },
                paramForm: {
                    id: undefined,
                    menuId: undefined,
                    ranNum: undefined
                },
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
                    pageSize: 10 // 每页显示多少条
                },
                tableData: [],
                option:{
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
                   /* searchResetBtn:false,*/
                    card:true,
                    column:[
                        {
                            label: "企业名称",
                            prop: 'name',
                            search:true,
                            searchDefault:''
                        },
                        {
                            label:"行业类型",
                            prop:"type",
                            type:'select',
                            search:true,
                            searchFilterable: true ,
                            dicUrl: '/admin/dict/type/kyz_dwlx',
                            dicMethod: 'get',
                            dicQuery: {}
                        },
                        {
                            label:"行政区划",
                            prop:'areaCode',
                            type:'tree',
                            expandLevel: 1,
                            search:true,
                            searchDefault:'',
                            dicUrl: '/portal/area/areaData',
                            dicMethod: 'get',
                            dicQuery: {},
                            props:{
                                label: "name",
                                value: 'code'
                            }
                         },
                        {
                            label:"地址",
                            prop:"address"
                        },
                        {
                            label:"企业状态",
                            prop:"enterpriseState",
                            type:'select',
                            search: true,
                            searchFilterable: true,
                            dicUrl: '/portal/dict/getDict/enterprise_state/kyz',
                            dicMethod: 'get',
                            dicQuery: {}
                        },
                        {
                            label: "总积分",
                            prop: "pointScore"
                        },
                        {
                            label: "积分等级",
                            prop: "pointType",
                            search: true,
                            searchDefault:'',
                            searchFilterable: true,
                            type: 'select',
                            dicUrl: '/admin/dict/type/jfdj',
                            dicMethod: 'get',
                            dicQuery: {}
                        }
                    ]
                }
            }
        },
        created(){
            this.option.column[0].searchDefault=this.searchForm.dwmc
            this.option.column[2].searchDefault=this.searchForm.orgCode
            this.option.column[6].searchDefault=this.searchForm.pointType

        },
        computed: {

        },
        methods:{
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
                this.searchForm.dwmc=form.name
                this.searchForm.orgCode = form.areaCode
                this.searchForm.placeType = form.type
                this.searchForm.placeState=form.enterpriseState
                this.searchForm.pointType=form.pointType
                this.getList(this.page, this.searchForm)
                this.$emit("changeCount",this.searchForm);
            },
            handleDetail(row){
                this.showDetail = true
                this.detailTitle = row.name
                this.paramForm.id = row.id
                this.paramForm.ranNum = new Date();
                this.paramForm.menuId = this.searchForm.menuId
            },

            contact(row){
                this.contactTitle='场所关联'
                this.showContact=true
                this.contactForm.lyxtType='4'
                this.contactForm.qtdwbh=row.id
                this.contactForm.dwmc=row.name
                this.contactForm.hylx='02'
                this.contactForm.address=row.address
                this.contactForm.areaCode=row.areaCode
                this.contactForm.sspcs=''
            }
        }
    }
</script>