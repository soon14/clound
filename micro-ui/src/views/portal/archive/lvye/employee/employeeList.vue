<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="option"
                       :data="employeeData"
                       v-model="form"
                       ref="crud"
                       :page="page"
                       @on-load="findEmployee"
                       @search-change="handleFilter"
                       :table-loading="tableLoading">

                <template slot="xm" slot-scope="scope">
                    <button type="button" class="el-button el-button--text el-button--small"
                    @click="viewDialog(scope.row)"
                    >{{scope.row.xm}}</button>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog title="详情"  :visible.sync="formView" fullscreen :append-to-body="true">
           <employee-detail v-bind:detail-row="formDetail"/>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="formView=false">关 闭
                </el-button>
            </div>

        </el-dialog>
    </div>
</template>


<script>


    import {findEmployeePage} from "@/api/portal/archive/lvye/employee";
    import  employeeDetail from '../employee/employeeDetail'

    export default {
        components:{
            employeeDetail
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
                    searchBtn:false,
                    columnBtn:false,
                    menu:false,
                    card:true,
                    column:[
                        { label: "姓名",prop: 'xm',slot:true,search:true},
                        { label:"证件号码", prop:"zjhm",search:true},
                        {
                            label: "性别", prop: "xb",
                            dicUrl: '/portal/dict/getDict/0122/ly',
                            dicMethod: 'get',
                            searchFilterable: true
                        },
                        { label:"出生日期", prop:"csrq",},
                        {
                            label: "类别", prop: "rylb", search: true, type: 'select',

                            dicUrl: '/portal/dict/getDict/RYLB/ly',
                            dicMethod: 'get',
                            searchFilterable: true
                        },
                        {
                            label: "岗位名称", prop: "gzgw",
                            dicUrl: '/portal/dict/getDict/GZGW/ly',
                            dicMethod: 'get',
                        },
                        { label:"上岗日期", prop:"sgrq",},
                        { label:"离岗日期", prop:"lgrq",},
                        {
                            label: "状态",
                            prop: "ryzt",
                            type: 'select',
                            search: true,
                            searchDefault: '',
                            dicUrl: '/portal/dict/getDict/RYZT/ly',
                            dicMethod: 'get',
                            searchFilterable: true
                        },
                        { label:"七类人员类型", prop:"rybaLb",},
                        ]
                }
            }
        },
        created(){
           this.searchFrom.menuId=this.paramForm.menuId
            this.searchFrom.unitId=this.paramForm.unitId
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
            findEmployee(page,params) {
                this.tableLoading=true
                findEmployeePage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    dwbh:this.searchFrom.unitId
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

