<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="option"
                       :data="employeeData"
                       v-model="form"
                       ref="crud"
                       :page="page"
                       @on-load="findZjxx"
                       @search-change="handleFilter"
                       :table-loading="tableLoading">

                <template slot="menu" slot-scope="scope" >
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-view"
                               @click="viewDialog(scope.row)">详细
                    </el-button>

                </template>
            </avue-crud>
        </basic-container>

        <el-dialog title="证件信息详情"  :visible.sync="formView" fullscreen :append-to-body="true">
            <avue-detail :option="zjxxFormOption" v-model="zjxxFromDetail" >
                <template slot="imgUrlForm" slot-scope="scope">
                    <img style="height: 250px;width: 250px"  id="imgZjzp"/>
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
    import {findZjxxPage,getZjxxImg} from "@/api/portal/archive/jingz/zjxx";
    export default {
        name: 'zjxxList',
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
                zjxxFormOption:{ dialogWidth: '100%',
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
                        { label:"", prop:"imgUrl", formslot:'true',},
                        { label: "证件名称",prop: 'fjmc',},
                        { label: "证件号码",prop: 'zjhm',},
                        {label: "证件类型",prop: "zjdm",type:"select",hide: true,dicUrl:'/portal/dict/getDict/za_015/jzxt'},
                        { label: "发证日期", prop: "fzrq", },
                        { label:"发证单位", prop:"fzdw",},
                        {label: "审批人", prop: "spr",},
                        { label:"审批日期", prop:"sprq",},
                        { label:"有效日期", prop:"yxqx",},
                        { label:"是否过期", prop:"reservation18",},
                        { label:"备注", prop:"bz",},
                    ]},
                zjxxFromDetail:[],
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
                        { label: "证件名称",prop: 'fjmc',search:true},
                        { label: "证件号码",prop: 'zjhm',search:true},
                        {label: "证件类型",prop: "zjdm",type:"select",hide: true,dicUrl:'/portal/dict/getDict/za_015/jzxt'},
                        /*{label: "行业类型",prop: "gxhylx",type:"select",hide: true, dicUrl:'/portal/dict/getDict/za_002/jzxt'},*/
                        { label: "发证日期", prop: "fzrq", },
                        { label:"发证单位", prop:"fzdw",},
                        {label: "审批人", prop: "spr",},
                        { label:"审批日期", prop:"sprq",},
                        { label:"有效日期", prop:"yxqx",},
                        { label:"是否过期", prop:"reservation18",},
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
                    this.searchFrom.unitId = this.paramForm.unitId;
                    this.searchFrom.menuId = this.paramForm.menuId;

                    this.findZjxx(this.page,{});
                },
                deep: true
            },
        },
        methods:{
            findZjxx(page,params) {
                this.tableLoading=true
                findZjxxPage(Object.assign({
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
                this.findZjxx(this.page, param);
            },
            viewDialog(row){
                this.formView=true
                this.zjxxFromDetail=row
                getZjxxImg(row.fileid,'imgZjzp')
            }

        }
    }
</script>

