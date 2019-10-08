<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="option"
                       :data="tableData"
                       v-model="form"
                       ref="crud"
                       :page="page"
                       @on-load="getList"
                       @search-change="handleFilter"
                       :table-loading="tableLoading">
                <template slot="tagType" slot-scope="scope">
                    <el-tag
                            v-for=" (tag,index) in findLabelByValues(tagDictData,scope.row.tagType,'无',true)"
                            v-bind:key="Math.random()+'_'+index"
                            color="#fff"
                            size="mini"
                    >{{tag}}</el-tag>
                </template>
                <template slot="rybaMc" slot-scope="scope">
                    <button type="button" class="el-button el-button--text el-button--small"
                            @click="openRyxqDialog(scope.row)" >{{scope.row.rybaMc}}</button>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog :title="formTitle"  :visible.sync="formView" :fullscreen="true" :append-to-body="true">
            <avue-detail :option="formOption" v-model="fromDetail" >
                <template slot="rybaZpContentForm" slot-scope="scope"  >
                    <viewer>
                    <img style="height: 100px;width: 100px"  id="ryImg"/>
                    </viewer>
                </template>
            </avue-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="formView=false">关 闭
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>


<script>

    import {findRyListPage,getRyDetail,getRyImg} from "@/api/portal/archive/ylcs/csba";
    import { formOption} from '@/const/crud/portal/archive/ylcs/ryba/index'
    import {ylcsDict}  from  "@/const/crud/portal/archive/ylcs/ylcsDict";
    import {remote}  from  "@/api/admin/dict";

    export default {
        name: 'ryba',
        props:["csbaForm"],
        data(){
            return {
                compId:'',
                csbabh:'',
                form:{
                },
                fromDetail:{},
                formView:false,
                formTitle:'',
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                formOption:formOption,
                tableData: [],
                tagDictData:[],
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
                    refreshBtn: false,
                    searchBtn:false,
                    columnBtn:false,
                    menu:false,
                    card:true,
                    column:[
                        { label: "姓名",prop: 'rybaMc',slot:true,search:true},
                        { label:"证件号码", prop:"rybaZjhm",search:true},
                        { label:"性别", prop:"rybaXb",type:'select',dicData:ylcsDict.sex},
                        { label:"出生日期", prop:"rybaCsrq",},
                        { label:"类别", prop:"rybaLb",search:true,type:'select',dicData: ylcsDict.rylb},
                        { label:"岗位名称", prop:"rybaGwmc",},
                        { label:"上岗日期", prop:"rybaDjsj",},
                        { label:"离岗日期", prop:"rybaLzsj",},
                        { label:"状态", prop:"rybaCyzt",type:'select',search:true,searchDefault:'',dicData: ylcsDict.ryzt},
                        { label:"七类人员类型", prop:"tagType",
                            type: 'select',
                            search: true,
                            searchDefault: '',
                            dicUrl: '/admin/dict/type/QLRYLX',
                            dicMethod: 'get',
                            searchFilterable: true,
                            slot:true
                        }
                        ]
                }
            }
        },
        created(){
            this.compId = this.csbaForm.compId
            this.csbabh = this.csbaForm.csbaBabh
            this.option.column[8].searchDefault = ylcsDict.ryzt[0].value
            this.getTagDictData()
        },
        computed: {
        },
        watch: {
            csbaForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.compId = this.csbaForm.compId
                    this.csbabh = this.csbaForm.csbaBabh
                    this.option.column[8].searchDefault = ylcsDict.ryzt[0].value
                    this.getList(this.page);
                },
                deep: true
            },
        },
        methods:{
            getTagDictData(){
                remote('QLRYLX').then(response => {
                    this.tagDictData = response.data.data||{}
                }).catch((err) => {});
            },
            getList(page, params) {
                if(params === undefined ||params === null || params === '' ){
                    params ={rybaCyzt: ylcsDict.ryzt[0].value}
                }
                this.tableLoading = true
                findRyListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    compId:this.compId,
                    csbabh:this.csbabh
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            handleFilter(param) {
                this.page.page = 1;
                this.getList(this.page, param);
            },
            getFormDetail(id) {
                getRyDetail(id).then(response => {
                    this.fromDetail = response.data.data
                })
            },
            openRyxqDialog(row){
                this.formView = true
                this.formTitle = "人员详情"
                this.getFormDetail(row.rybaId)
                getRyImg(row.rybaZp,'ryImg')
            },

        }
    }
</script>