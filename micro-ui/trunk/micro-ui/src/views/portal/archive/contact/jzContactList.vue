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
                <template slot="placeState" slot-scope="scope">
                    <span  v-if="scope.row.lyxtType=='1'">
                        {{ dict.getLabel(dict.csbaState,scope.row.placeState)}}
                    </span>
                    <span  v-if="scope.row.lyxtType=='2'"
                           v-text="jxDict(scope.row.placeState)">
                    </span>
                    <span  v-if="scope.row.lyxtType=='3'"
                           v-text="lyDict(scope.row.placeState)">
                    </span>
                    <span  v-if="scope.row.lyxtType=='4'"
                           v-text="kyzDict(scope.row.placeState)">
                    </span>
                </template>
                <template slot="sspcs" slot-scope="scope">
                    <span  v-if="scope.row.lyxtType=='1'||scope.row.lyxtType=='2'||scope.row.lyxtType=='4'">
                        {{ scope.row.sspcs}}
                    </span>
                    <span  v-if="scope.row.lyxtType=='3'"
                           v-text="lyGxdwDict(scope.row.sspcs)">
                    </span>
                </template>
                <template slot="hylx" slot-scope="scope">
                      <span  v-if="scope.row.lyxtType=='1'"
                             v-text="ylHylxDict(scope.row.hylx)">
                    </span>
                    <span  v-if="scope.row.lyxtType=='2'"
                           v-text="jxHylxDict(scope.row.hylx)">
                    </span>
                    <span  v-if="scope.row.lyxtType=='3'"
                           v-text="lyHylxDict(scope.row.hylx)">
                    </span>
                    <span  v-if="scope.row.lyxtType=='4'"
                           v-text="kyzHylxDict(scope.row.hylx)">
                    </span>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog :title="detailTitle" fullscreen :visible.sync="showDetail" :append-to-body="applyToBody">
            <place-jump :param="paramForm"></place-jump>
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
                    <el-form-item label="来源系统">
                        <el-select v-model="searchForm.lyxtType" placeholder="请选择">
                            <el-option
                                    v-for="item in lyxtData"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </template>
                <template slot="menuLeft">
                    <el-button @click="importData"
                               icon="el-icon-plus"
                               type="primary"
                               size="small">关联</el-button>
                </template>
                <template slot="placeState" slot-scope="scope">
                    <span  v-if="scope.row.lyxtType=='1'">
                        {{ dict.getLabel(dict.csbaState,scope.row.placeState)}}
                    </span>
                    <span  v-if="scope.row.lyxtType=='2'"
                           v-text="jxDict(scope.row.placeState)">
                    </span>
                    <span  v-if="scope.row.lyxtType=='3'"
                           v-text="lyDict(scope.row.placeState)">
                    </span>
                    <span  v-if="scope.row.lyxtType=='4'"
                           v-text="kyzDict(scope.row.placeState)">
                    </span>
                </template>
                <template slot="sspcs" slot-scope="scope">
                    <span  v-if="scope.row.lyxtType=='1'||scope.row.lyxtType=='2'||scope.row.lyxtType=='4'">
                        {{ scope.row.sspcs}}
                    </span>
                    <span  v-if="scope.row.lyxtType=='3'"
                           v-text="lyGxdwDict(scope.row.sspcs)">
                    </span>
                </template>
                <template slot="hylx" slot-scope="scope">
                      <span  v-if="scope.row.lyxtType=='1'"
                             v-text="ylHylxDict(scope.row.hylx)">
                    </span>
                    <span  v-if="scope.row.lyxtType=='2'"
                           v-text="jxHylxDict(scope.row.hylx)">
                    </span>
                    <span  v-if="scope.row.lyxtType=='3'"
                           v-text="lyHylxDict(scope.row.hylx)">
                    </span>
                    <span  v-if="scope.row.lyxtType=='4'"
                           v-text="kyzHylxDict(scope.row.hylx)">
                    </span>
                </template>
            </avue-crud>

        </el-dialog>

    </div>
</template>


<script>
    import {jzTableOption,jzFormOption} from '@/const/crud/portal/archive/contact/contact';
    import {fetchJzGlList,fetchJzList,delObj,addJzObj,autoAddObj} from "@/api/portal/archive/contact/contact";
    import {fetchDictTree} from "@/api/admin/dict";
    import {fetchArea} from '@/api/portal/archive/common/query'
    import {findLabel,getDict,getLabel}  from  "@/api/portal/archive/dict/dict";
    import {ylcsDict} from "@/const/crud/portal/archive/ylcs/ylcsDict";
    import placeJump from "./placeJump";
    import {mapGetters} from 'vuex'
    import {remote}  from  "@/api/admin/dict";


    export default {
        name:'jzContactList',
        props:["contactForm"],
        components: {
             "placeJump":placeJump
        },
        data(){
            return {
                dict:ylcsDict,//娱乐场所字典
                jxPlaceStateData:[],//机修业经营状态字典
                lyPlaceStateData:[],//旅馆业经营状态字典
                kyzPlaceStateData:[],//刻印章经营状态字典
                lyGxdwData:[],//旅业管辖单位字典
                ylHylxData:[],//娱乐场所行业类别字典
                jxHylxData:[],//机修行业类别字典
                lyHylxData:[],//旅馆业行业类别字典
                kyzHylxData:[],//刻印章业行业类别字典

                listQuery:{
                    jzdwbh:'',
                },//场所关联列表查询条件
                form:{},
                formTitle:'',//添加关联弹窗标题
                showForm:false,
                applyToBody: true,

                showDetail: false,//档案预览
                detailTitle: '',
                paramForm: {
                    lyxtType:'',
                    unitId:'',
                    compId:'',//娱乐场所需两个id，一场所备案id（unitId），二单位id（compId）
                },//场所详情页面跳转所需参数

                tableLoading: false,
                listPage: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableData: [],
                option:jzTableOption,//关联列表

                formData:[],
                formOption:jzFormOption,//添加要关联的场所列表
                formPage:{
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                formLoading:false,
                glForm:{},
                searchForm:{
                    lyxtType:undefined,
                    jzdwbh:undefined,
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
                lyxtData:[],//来源系统
                selData: [],//选中的要关联的场所数据
            }
        },
        created(){
            this.listQuery.jzdwbh = this.contactForm.jzdwbh
           // this.getGlList(this.listPage)
            this.getHylxData()
            this.getXzqhData()
            this.csDictData()
        },
        computed: {
            ...mapGetters(["isAdmin"]),
        },
        methods:{
            getGlList(listPage, params) {
                this.tableData=[]
                this.tableLoading = true;
                fetchJzGlList(Object.assign({
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
                this.paramForm.lyxtType=row.lyxtType
                this.paramForm.unitId=row.dwbh
                this.paramForm.compId=row.compId
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
                    delObj(this.listQuery.jzdwbh,row.dwbh).then(() => {
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
                this.searchForm.jzdwbh=this.contactForm.jzdwbh
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
                this.selData=[]
                this.formData=[]
                this.formLoading = true;
                fetchJzList(Object.assign({
                    current: formPage.currentPage,
                    size: formPage.pageSize
                }, this.searchForm)).then(response => {
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
                        let defineForm ={}
                        /*添加关联 一对多改为一对一，每次只能导入添加一条数据*/
                       /* var nodes = new Array();
                        for(let i = 0; i < this.selData.length; i++){
                            nodes.push(JSON.parse(JSON.stringify(this.selData[i])));
                        }
                        defineForm['selectList']=nodes*/
                        defineForm['jzdwbh']=this.listQuery.jzdwbh
                        for(let i = 0; i < this.selData.length; i++){
                            defineForm['qtdwbh']=this.selData[i].dwbh
                            defineForm['lyxtType']=this.selData[i].lyxtType
                        }
                        addJzObj(defineForm).then(() => {
                            this.showForm = false;
                            this.selData=[]
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
                this.searchForm.lyxtType=''
            },
            csDictData(){
                getDict('qyzt','jx').then(response => {
                    this.jxPlaceStateData = response.data.data||[];
                }).catch((err) => {});
                getDict('0209','ly').then(response => {
                    this.lyPlaceStateData = response.data.data||[];
                    console.log("旅业经营状态", this.kyzPlaceStateData)
                }).catch((err) => {});
                getDict('enterprise_state','kyz').then(response => {
                    this.kyzPlaceStateData = response.data.data||[];
                    console.log("刻印章经营状态", this.kyzPlaceStateData)
                }).catch((err) => {});
                getDict('06','jzxt').then(response => {
                    this.lyGxdwData = response.data.data||[];
                }).catch((err) => {});
                getDict('业务类型','yl').then(response => {
                    this.ylHylxData = response.data.data||[];
                }).catch((err) => {});
                getDict('qylb','jx').then(response => {
                    this.jxHylxData = response.data.data||[];
                }).catch((err) => {});
                getDict('LGLB','ly').then(response => {
                    this.lyHylxData = response.data.data||[];
                }).catch((err) => {});
                remote('kyz_dwlx').then(response => {
                    this.kyzHylxData = response.data.data||[]
                }).catch((err) => {});
                remote('unit_source_type').then(response => {
                    this.lyxtData = response.data.data||[]
                }).catch((err) => {});
            },
            jxDict(placeState){
                return getLabel( this.jxPlaceStateData, placeState)
            },
            lyDict(placeState){
                return getLabel( this.lyPlaceStateData, placeState)
            },
            kyzDict(placeState){
                return getLabel( this.kyzPlaceStateData, placeState)
            },
            lyGxdwDict(sspcs){
                return getLabel( this.lyGxdwData, sspcs)
            },
            ylHylxDict(hylx){
                return getLabel( this.ylHylxData, hylx)
            },
            jxHylxDict(hylx){
                return getLabel( this.jxHylxData, hylx)
            },
            lyHylxDict(hylx){
                return getLabel( this.lyHylxData, hylx)
            },
            kyzHylxDict(hylx){
                return getLabel( this.kyzHylxData, hylx)
            }
        },

        watch: {
            contactForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.listQuery.jzdwbh = this.contactForm.jzdwbh
                    this.getGlList(this.listPage);
                    this.getHylxData()
                    this.getXzqhData()
                    this.csDictData()
                },
                deep: true
            },
        }
    }
</script>