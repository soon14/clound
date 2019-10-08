<template>
 <div class="execution">
   <basic-container>
    <avue-crud :option="option"
               :data="tableData"
               v-model="form"
               ref="crud"
               :page="listPage"
               @on-load="getGlList"
               :table-loading="tableLoading">
        <template slot="menuLeft">
            <el-button @click="addContact"
                       icon="el-icon-plus"
                       type="primary"
                       size="small">添加关联</el-button>
            <el-button v-if="this.isAdmin"
                       @click="autoContact"
                       icon="el-icon-plus"
                       type="primary"
                       size="small">自动关联</el-button>
        </template>
        <template slot-scope="scope" slot="menu">
            <el-button size="mini"
                       type="text"
                       icon="el-icon-close"
                       @click="handleDelete(scope.row)">取消关联
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
         <keep-alive>
             <place-tab v-bind:param-form="paramForm"/>
         </keep-alive>

     </el-dialog>

     <el-dialog :title="formTitle" fullscreen :visible.sync="showForm"  :append-to-body="applyToBody">
         <avue-crud :option="formOption"
                    :data="formData"
                    v-model="glForm"
                    ref="contact"
                    :page="formPage"
                    @search-reset="clearForm"
                    @search-change="searchChange"
                    @refresh-change="refreshChange"
                    :table-loading="formLoading"
                    @current-row-change="selectionChange"
                    @on-load="getFormList">
             <template slot="search">
                 <el-form-item label="场所名称">
                     <el-input
                             placeholder="请输入内容"
                             v-model="searchForm.dwmc"
                             clearable>
                     </el-input>
                 </el-form-item>
                 <el-form-item label="经营地址">
                     <el-input
                             placeholder="请输入内容"
                             v-model="searchForm.address"
                             clearable>
                     </el-input>
                 </el-form-item>
                 <el-form-item label="行业类型">
                     <avue-input v-model="searchForm.hylx"
                                 type="tree"
                                 placeholder=""
                                 expandLevel="1"
                                 :dic="hylxTreeData"
                                 :props="hylxTreeProps">
                     </avue-input>
                 </el-form-item>
                 <el-form-item label="行政区划">
                     <avue-input v-model="searchForm.areaCode"
                                 type="tree"
                                 placeholder=""
                                 expandLevel="1"
                                 :dic="xzqhTreeData"
                                 :props="xzqhTreeProps">
                     </avue-input>
                 </el-form-item>
             </template>
             <template slot="menuLeft">
                 <el-button @click="importData"
                            icon="el-icon-plus"
                            type="primary"
                            size="small">关联</el-button>
             </template>
         </avue-crud>

     </el-dialog>

    </div>
</template>


<script>
    import {tableOption,formOption} from '@/const/crud/portal/archive/contact/contact';
    import {fetchGlList,fetchList,addObj,delObj,autoAddObj} from "@/api/portal/archive/contact/contact";
    import {fetchDictTree} from "@/api/admin/dict";
    import {fetchArea} from '@/api/portal/archive/common/query'
    import placeTab from "../jingz/cs/placeTab";
    import {mapGetters} from 'vuex'

    export default {
        name:'contactList',
        props:["contactForm"],
        components: {
            "placeTab": placeTab,
        },
        data(){
            return {
                listQuery:{
                    qtdwbh:'',
                },//场所关联列表查询条件
                form:{},
                formTitle:'',//添加关联弹窗标题
                showForm:false,
                applyToBody: true,

                showDetail: false,//档案预览
                detailTitle: '',
                paramForm: {
                    menuId: undefined,
                    unitId: undefined,
                    placeType:undefined,
                },

                tableLoading: false,
                listPage: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableData: [],
                option:tableOption,//关联列表

                formData:[],
                formOption:formOption,//添加要关联的场所列表
                formPage:{
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                formLoading:false,
                glForm:{},
                searchForm:{
                    lyxtType:undefined,
                    qtdwbh:undefined,
                    sspcs:undefined,
                    areaCode:undefined,
                    dwmc:undefined,
                    address:undefined,
                    hylx:'',
                },//场所关联 自定义搜索
                hylxTreeData:[],
                hylxTreeProps: {
                    label: "label",
                    value: 'value',
                },
                hylxQuery: {
                    type: 'HYLX'
                },
                xzqhTreeData:[],
                xzqhTreeProps: {
                    label: "name",
                    value: 'code'
                },
                selData: [],//选中的要关联的场所数据
            }
        },
        created(){
            this.listQuery.qtdwbh = this.contactForm.qtdwbh
            this.getHylxData()
            this.getXzqhData()
        },
        computed: {
            ...mapGetters(["isAdmin"]),
        },
        methods:{
            getGlList(listPage, params) {
                this.tableData=[]
                this.tableLoading = true;
                fetchGlList(Object.assign({
                    current: listPage.currentPage,
                    size: listPage.pageSize
                }, this.listQuery)).then(response => {
                    this.tableData = response.data.data.records;
                    this.listPage.total = response.data.data.total
                    this.tableLoading = false;
                });
            },
            handleDetail(row) {
                this.showDetail = true
                this.detailTitle = row.dwmc
                this.paramForm.menuId = '51b7e0b979a76db30a8e329740ce1e57'
                this.paramForm.unitId = row.dwbh
                this.paramForm.placeType = row.hylx
            },
            handleDelete(row){
                this.$confirm(
                    "确认取消关联?",
                    "提示",
                    {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning"
                    }
                ).then(() => {
                    delObj(row.dwbh,this.listQuery.qtdwbh).then(() => {
                        this.getGlList(this.listPage)
                        this.$notify({
                            title: "成功",
                            message: "取消关联成功",
                            type: "success",
                            duration: 2000
                        });
                    }).cache(() => {
                        this.$notify({
                            title: "失败",
                            message: "取消关联失败",
                            type: "error",
                            duration: 2000
                        });
                    });
                });
            },
            addContact(){
                if(this.tableData.length>0){
                    this.$message.error("该场所已存在关联数据，若要添加关联请先执行取消当前关联操作")
                    return
                }
                this.formTitle="添加关联"
                this.showForm=true
                this.searchForm.lyxtType=this.contactForm.lyxtType
                this.searchForm.qtdwbh=this.contactForm.qtdwbh
                this.searchForm.dwmc=this.contactForm.dwmc
                this.searchForm.hylx = this.contactForm.hylx
                this.searchForm.address = this.contactForm.address
                this.searchForm.areaCode = this.contactForm.areaCode
                this.searchForm.sspcs = this.contactForm.sspcs
                //this.getFormList(this.formPage,this.searchForm)
            },
            autoContact(){
                this.$confirm('确认自动关联?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    autoAddObj().then(() => {
                        this.getGlList(this.listPage)
                        this.$notify({
                            title: "成功",
                            message: "操作成功",
                            type: "success",
                            duration: 2000
                        });
                    }).catch(function () {
                    })
                })
            },
            getFormList(formPage, params) {
                this.selData=[];
                this.formData=[]
                this.formLoading = true;
                fetchList(Object.assign({
                    current: formPage.currentPage,
                    size: formPage.pageSize
                }, this.searchForm)).then(response => {
                    console.log(response.data.data.records)
                    this.formData = response.data.data.records;
                    this.formPage.total = response.data.data.total
                    this.formLoading = false;
                });
            },
            getHylxData(){
                this.hylxTreeData=[]
                fetchDictTree(this.hylxQuery).then(response => {
                    this.hylxTreeData = response.data.data
                })
            },
            getXzqhData(){
                this.xzqhTreeData=[]
                fetchArea().then(response => {
                    this.xzqhTreeData = response.data.data
                })
            },
            selectionChange(data){
                this.$refs.contact.setCurrentRow(data)
                this.selData.push(data)
            },
            refreshChange() {
                this.getFormList(this.formPage)
            },
            searchChange() {
                this.formPage.page = 1;
                this.getFormList(this.formPage)
            },
            importData(){
                if(this.selData.length > 0){
                    this.$confirm('确认关联?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        /*添加关联 一对多改为一对一，每次只能导入添加一条数据*/
                        let defineForm ={}
                        for(let i = 0; i < this.selData.length; i++){
                           /* ids += this.selData[i].dwbh
                            if(i<this.selData.length-1){
                                ids += ','
                            }*/
                            defineForm['jzdwbh']=this.selData[i].dwbh
                        }
                        defineForm['qtdwbh']=this.listQuery.qtdwbh
                        defineForm['lyxtType']=this.searchForm.lyxtType
                        addObj(defineForm).then(() => {
                            this.showForm = false;
                            this.formData=[];
                            this.selData=[];
                            this.getGlList(this.listPage)
                            this.$notify({
                                title: "成功",
                                message: "场所关联成功",
                                type: "success",
                                duration: 2000
                            });
                        })
                    })
                }else {
                    this.$message({
                        showClose: true,
                        message: '请选择要关联的数据',
                        type: 'warning'
                    })
                }
            },
            clearForm(){
                this.searchForm.dwmc=''
                this.searchForm.hylx=''
                this.searchForm.address=''
                this.searchForm.areaCode=''
            },

        },

        watch: {
            contactForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.listQuery.qtdwbh = this.contactForm.qtdwbh
                    this.getGlList(this.listPage);
                    this.getHylxData()
                    this.getXzqhData()
                },
                deep: true
            },
        }
    }
</script>
<style>
    .el-table--striped .el-table__body tr.el-table__row--striped.current-row td, .el-table__body tr.current-row>td {
        color: #fff;
        background-color: #258cf5 !important;
    }
</style>