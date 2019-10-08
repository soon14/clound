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
         <zajc-jz-detail :param="rowDetail"></zajc-jz-detail>
     </el-dialog>
    </div>
</template>


<script>
    import {findZajcPage} from "@/api/portal/archive/jingz/zajc";
    import zajcJzDetail from "./zajcJzDetail";

    export default {
        name: 'zajc',
        props:["paramForm"],
        components: {
            "zajcJzDetail":zajcJzDetail
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
                        { label:"检查时间", prop:"jcsj",},
                        {
                            label: '检查时间',
                            prop: 'searchDate',
                            type: 'daterange',
                            search:true,
                            hide:true,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyyMMdd'
                        },
                        { label:"检查单位", prop:"jcdw",},
                        { label:"检查人", prop:"jcr",},
                        {label: '处理意见', prop: 'clyj',search:true,type:'select',
                            dicUrl: '/portal/dict/getDict/za_022/jzxt',
                            dicMethod: 'get',
                        },
                        { label:"处理结果", prop:"cljg",},
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
                findZajcPage(Object.assign({
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