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
               @refresh-change="handleFilter"
               :table-loading="tableLoading">
        <template slot="menu" slot-scope="scope" >
            <el-button size="mini"
                       type="text"
                       icon="el-icon-view"
                       @click="handleDetail(scope.row)">查看
            </el-button>
        </template>
    </avue-crud>

   </basic-container>
     <el-dialog :title="formTitle"  :visible.sync="formView" :fullscreen="true" :append-to-body="true">
         <xfjc-detail :param="rowDetail"></xfjc-detail>
     </el-dialog>
    </div>
</template>


<script>
    import {findXfjcPage} from "@/api/portal/archive/jingz/xfjc";
    import xfjcJzDetail from "./xfjcJzDetail";

    export default {
        name: 'xfjc',
        props:["paramForm"],
        components: {
            "xfjcDetail":xfjcJzDetail
        },
        data(){
            return {
                form:{
                },
                searchFrom: {
                    unitId: undefined,
                    menuId: undefined,
                },
                rowDetail:{
                    systemId:''
                },
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableData: [],
                formView:false,
                formTitle:'',
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
                    menuWidth:100,
                    column:[
                        { label:"检查日期", prop:"jcrq",},
                        {
                            label: '检查日期',
                            prop: 'searchDate',
                            type: 'daterange',
                            search:true,
                            hide:true,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyyMMdd'
                        },
                        { label:"检查类型", prop:"reservation04",search:true,type:'select',
                            dicUrl: '/portal/dict/getDict/XF_022/jzDict',
                            dicMethod: 'get',
                        },
                        { label:"监督检查人员", prop:"jdjcry",hide:true},
                        {label: '单位主管人员', prop: 'dwzgry',},
                        { label:"是否合格", prop:"reservation02",search:true,type:'select',
                            dicUrl: '/portal/dict/getDict/XF_020/jzDict',
                            dicMethod: 'get',},
                        { label:"备注",prop: 'bz',overHidden:true},
                    ]
                }
            }
        },
        created(){
            this.searchFrom.menuId=this.paramForm.menuId
            this.searchFrom.unitId=this.paramForm.unitId
            this.getList(this.page,{});
        },
        computed: {
        },
        watch: {
            paramForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.searchFrom.unitId = this.paramForm.unitId;
                    this.searchFrom.menuId = this.paramForm.menuId;
                    this.getList(this.page,{});
                },
                deep: true
            },
        },
        methods:{
            getList(page, params) {
                try{
                    let param=this.$refs["crud"].searchForm||{}
                    params=param
                    if(params){
                        let date=params.searchDate;
                        if(date&&Array.isArray(date) && date.length === 2){
                            params.startTime=date[0]
                            params.endTime=date[1]
                        }else{
                            params.startTime=date[0]
                            params.endTime=date[0]
                        }
                    }
                }catch (e) {}
                this.tableLoading = true
                findXfjcPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    dwbh:this.searchFrom.unitId
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
            handleDetail(row){
                this.rowDetail.systemId=row.systemid
                this.formView = true
                this.formTitle = "检查详情"
            },
        }
    }
</script>