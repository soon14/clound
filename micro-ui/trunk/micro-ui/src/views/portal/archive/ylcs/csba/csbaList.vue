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
               @search-reset="clearForm"
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
             <csba-index v-bind:param-form="paramForm"/>
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
    import {ylcsDict} from "@/const/crud/portal/archive/ylcs/ylcsDict";
    import csbaIndex from "./csbaIndex";
    import contactList from "../../contact/contactList"
    export default {
        components: {
            "csbaIndex": csbaIndex,
            "contactList" : contactList
        },
        props:["searchForm"],
        data(){
            return {
                form:{
                },
                paramForm: {
                    csbaId: undefined,
                    csbaBabh:undefined,
                    compId:undefined,
                    menuId: undefined,
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
                        { label: "企业名称",prop: 'csbaZcmc',search:true,searchDefault:'',},
                        { label:"行业类型",
                          prop:"ywLx",
                          type:'select',
                          search:true,
                          searchFilterable: true ,
                          dicUrl: '/portal/dict/getDict/业务类型/yl',
                          dicMethod: 'get',
                          dicQuery: {},
                        },
                        { label:"经济类型", prop:"csbaJjlx",},
                        { label:"行政区划", prop:'compCode',type:'tree',expandLevel: 1,search:true,searchDefault:'',
                            dicUrl: '/portal/area/areaData',
                            dicMethod: 'get',
                            dicQuery: {},
                            props:{
                                label: "name",
                                value: 'code'
                            }
                         },
                        { label:"辖区派出所", prop:"sspcs",},
                        { label:"企业状态", prop:"csbaState",type:'select',search: true,searchFilterable: true,dicData:ylcsDict.csbaState},
                        { label: "总积分", prop: "pointScore"},
                        {label: "积分等级", prop: "pointType", search: true, searchDefault:'',searchFilterable: true,
                         type: 'select',
                         dicUrl: '/admin/dict/type/jfdj',
                         dicMethod: 'get',
                         dicQuery: {},
                        },
                        { label: "经营地址", prop: "csbaJydz",hide:true},
                    ]
                }
            }
        },
        created(){
            this.initDefaultValue()

        },
        computed: {

        },
        methods:{

            initDefaultValue(){
                this.option.column[0].searchDefault=this.searchForm.dwmc
                this.option.column[7].searchDefault=this.searchForm.pointType

                setTimeout(() => {
                    try{
                        this.$refs["crud"].searchForm.compCode=this.searchForm.orgCode
                    }catch (e) {}
                }, 1500);
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
                console.log("searchChange")
                this.searchForm.dwmc=form.csbaZcmc
                this.searchForm.orgCode = form.compCode
                this.searchForm.placeType = form.ywLx
                this.searchForm.placeState=form.csbaState
                this.searchForm.pointType=form.pointType
                this.getList(this.page, this.searchForm)
                this.$emit("changeCount",this.searchForm);
            },
            clearForm(){
                this.$refs.crud.searchForm.csbaZcmc=''
                this.$refs.crud.searchForm.compCode=''
                this.$refs.crud.searchForm.pointType=''
            },
            handleDetail(row){
                if(row.compId===null){
                    row.compId = row.csbaId
                }
                this.showDetail = true
                this.detailTitle = row.csbaZcmc
                this.paramForm.csbaId = row.csbaId
                this.paramForm.csbaBabh = row.csbaBabh
                this.paramForm.compId = row.compId
                this.paramForm.menuId = this.searchForm.menuId
            },

            contact(row){
                this.contactTitle='场所关联'
                this.showContact=true
                this.contactForm.lyxtType='1'
                this.contactForm.qtdwbh=row.csbaId
                this.contactForm.dwmc=row.csbaZcmc
                this.contactForm.hylx='08'
                this.contactForm.address=row.csbaJydz
                this.contactForm.areaCode=row.compCode
                this.contactForm.sspcs=row.compCode
            }
        }
    }
</script>