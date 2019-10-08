<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="option"
                       :data="employeeData"
                       v-model="form"
                       ref="crud"
                       :page="page"
                       @size-change="sizeChange"
                       @on-load="findEmployee"
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
                <template slot="menu" slot-scope="scope" >
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-view"
                               @click="viewDialog(scope.row)">详细
                    </el-button>

                </template>
            </avue-crud>
        </basic-container>

        <el-dialog title="详情"  :visible.sync="formView" fullscreen :append-to-body="true">
            <employee-jz-detail v-bind:detail-row="formDetail"></employee-jz-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="formView=false">关 闭
                </el-button>
            </div>

        </el-dialog>
    </div>
</template>


<script>
    import  employeeJzDetail from './employeeJzDetail'
    import {findEmployeeJzPage} from "@/api/portal/archive/jingz/employeeJz";
    import {remote}  from  "@/api/admin/dict";
    export default {
        components:{
            employeeJzDetail
        },
        name: 'employee',
        props:["paramForm"],
        data(){
            return {
                form:{
                },
                searchFrom: {
                    unitId: undefined,
                    menuId: undefined,
                },

                formDetail:{},
                formView:false,
                formTitle:'',
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tagDictData:[],
                tableData: [],
                employeeData:[],
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
                    menuWidth:'100',
                    card:true,
                    column:[
                        { label: "姓名",prop: 'xm',search:true},
                        {
                            label: "证件号码",
                            prop: "zjhm",
                            search: true,
                            minWidth:'140'
                        },
                        {
                            label: "性别", prop: "xb",
                            dicUrl: '/portal/dict/getDict/01/jzxt',
                            dicMethod: 'get',
                            searchFilterable: true
                        },
                        { label:"出生日期", prop:"csrq",},
                       /* {
                            label: "人员单位类别", prop: "rylb", search: true, type: 'select',
                            dicUrl: '/portal/dict/getDict/za_cyry_rylb/jzxt',
                            dicMethod: 'get',
                            searchFilterable: true
                        },*/
                        {
                            label: "岗位名称", prop: "gzgwmc",
                        },
                        { label:"上岗日期", prop:"sgrq",},
                        { label:"离岗日期", prop:"lgrq",},
                        {
                            label: "人员状态",
                            prop: "ryzt",
                            type: 'select',
                            search: true,
                            searchDefault: '',
                            dicData:[
                               {
                                   label:'有效',value:"1"
                               },{
                                   label:'注销',value:'0',
                               }
                           ],
                            searchFilterable: true
                        },
                        { label:"人员类型", prop:"tagType",
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
           this.searchFrom.menuId=this.paramForm.menuId
            this.searchFrom.unitId=this.paramForm.unitId
            this.getTagDictData()
        },
        computed: {
        },
        watch: {
            paramForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.searchFrom.dwbh = this.paramForm.unitId;
                    this.searchFrom.menuId = this.paramForm.menuId;

                    this.findEmployee(this.page,{});
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
            sizeChange(size) {
                this.page.pageSize = size;
            },
            findEmployee(page,params) {
                try{
                    let   param=this.$refs["crud"].searchForm||{}
                    params=param
                }catch (e) {}
                this.tableLoading=true
                findEmployeeJzPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    zbbh:this.searchFrom.unitId
                },params)).then(response => {
                    this.employeeData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false

                })
            },

            handleFilter(param) {

                this.page.page = 1;
                this.findEmployee(this.page, param);
            },
            viewDialog(row){
                this.formView=true
                this.formDetail=row

            }

        }
    }
</script>

