<template>
    <div class="execution">
        <basic-container>
            <avue-crud :data="data"
                       :option="option"
                       v-model="obj"
                       :page="page"
                       @on-load="onLoad"
                       :table-loading="tableLoading">
                <template slot="zjhm" slot-scope="scope">
                    <el-button type="text" @click="showRyxx(scope.row)">{{scope.row.zjhm}}</el-button>
                </template>
                <template slot="dwmc" slot-scope="scope">
                    <el-button type="text" @click="showDwxx(scope.row.dwid)">{{scope.row.dwmc}}</el-button>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog title="违法记录" :visible.sync="ryxxVible"  :append-to-body="applyToBody" width="80%" :before-close="closeRyxxDialog">
            <illegality-list v-bind:param-form="ryxxForm"></illegality-list>
            <span slot="footer" class="dialog-footer">
                <el-button @click="closeRyxxDialog">关闭</el-button>
            </span>
        </el-dialog>

    </div>
</template>


<script>

    import {mapGetters} from "vuex";
    import {employeeList} from "@/api/portal/statistics/employee/employee.js";
    import IllegalityList from "./illegality_list";

    export default {
        name: 'cyryList',
        props:["paramForm"],
        components:{
            IllegalityList
        },
        data() {
            return {
                obj:{},
                data: [],
                tableLoading:false,
                ryxxVible:false,
                dwxxVible:false,
                applyToBody: true,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                option:{
                    page:true,
                    align:'center',
                    addBtn:false,
                    menu:false,
                    index:true,
                    column:[
                        {
                            label:'姓名',
                            prop:'name'
                        },
                        /*{
                            label:'性别',
                            prop:'xb_ch'
                        },*/
                        {
                            label: "证件号码",
                            prop: "zjhm",
                            slot:true
                        },
                        {
                            label: "人员类型",
                            prop: "rylx"
                        },
                        {
                            label: "上岗日期",
                            prop: "sgrq"
                        },
                        {
                            label: "从业状态",
                            prop: "cyzt"
                        },
                        {
                            label: "单位名称",
                            prop: "dwmc",
                        },
                        {
                            label: "行业类型",
                            prop: "hylx",
                            type:'select',
                            dicUrl: '/admin/dict/type/HYLX_DL',
                            dicMethod: 'get',
                            dicQuery: {},
                        },
                        {
                            label: "单位地址",
                            prop: "dwdz",
                        },
                    ]
                },
                ryxxForm:{
                    zjhm:"",
                    name:"",
                    rylx:""
                },
            }
        },
        created(){
            //this.getList(this.page,this.paramForm);
        },
        computed: {
            ...mapGetters(["userInfo","userDept"])
        },
        watch: {
            "paramForm.ranNum":{
                handler (newParam) {
                    this.getList(this.page,this.paramForm);
                },
                deep: true
            },
        },
        methods:{
            getList(page,params){
                this.tableLoading = true
                employeeList(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, params)).then(response=>{
                    this.data = response.data.data.records
                    this.page.total = response.data.data.total
                    this.page.currentPage = response.data.data.current;
                    this.page.pageSize = response.data.data.size;
                    console.log(this.data)
                    this.tableLoading = false
                }).catch(()=>{});
            },
            onLoad(page){
                this.getList(page,this.paramForm);
            },
            showRyxx(row){
                this.ryxxForm.zjhm = row.zjhm;
                this.ryxxForm.name = row.name;
                this.ryxxForm.rylx = row.rylx;
                this.ryxxVible = true;
            },
            closeRyxxDialog:function () {
                this.ryxxVible = false;
            },

        }
    }
</script>