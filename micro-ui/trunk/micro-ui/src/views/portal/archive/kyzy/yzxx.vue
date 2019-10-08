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
                <template slot-scope="scope" slot="menu">
                    <el-button type="text"
                               icon="el-icon-view"
                               size="small"
                               @click="viewDialog(scope.row)">详情</el-button>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog title="详情"  :visible.sync="formView" :append-to-body="true" width="70%">
            <yzxx-detail v-bind:detail-row="formDetail"/>
                <div slot="footer"
                  class="dialog-footer">
                 <el-button type="primary" @click="formView=false">关 闭
                 </el-button>
                </div>
        </el-dialog>
    </div>
</template>


<script>
    import {findSealInfoPage} from "@/api/portal/archive/kyzy/kyzy";
    import {kyzyDict} from "@/const/crud/portal/archive/kyzy/kyzy";
    import yzxxDetail from "./yzxxDetail";
    export default {
        name: 'yzxx',
        props:["searchForm"],
        components:{
            findSealInfoPage,
            kyzyDict,
            yzxxDetail
        },
        data(){
            return {
                params:{
                    menuId:'',
                    id:''
                },
                form:{},
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                formDetail:{},
                formView:false,
                tableData: [],
                option:{
                    dialogWidth: '80%',
                    dialogHeight: '100%',
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
                    column:[
                        {
                            label:"印章id",
                            prop:"id",
                            hide:true
                        },
                        {
                            label:"使用单位",
                            prop:"approveUnitName",
                            search:true
                        },
                        {
                            label:"法人",
                            prop:"approveUnitLegalName"
                        },
                        {
                            label:"印章名称",
                            prop:"name",
                            search:true
                        },
                        {
                            label:"状态",
                            prop:"status",
                            type:'select',
                            dicUrl: '/portal/dict/getDict/seal_status/kyz',
                            dicMethod: 'get',
                            dicQuery: {},
                            search:true
                        },
                        {
                            label:"类型",
                            prop:"type",
                            type:'select',
                            dicUrl: '/portal/dict/getDict/seal_type/kyz',
                            dicMethod: 'get',
                            dicQuery: {},
                            search:true
                        },
                        {
                            label:"材料",
                            prop:"material",
                            type:'select',
                            dicUrl: '/portal/dict/getDict/seal_material/kyz',
                            dicMethod: 'get',
                            dicQuery: {},
                            hide:true
                        },
                        {
                            label:"横长(宽)",
                            prop:"width",
                            hide:true
                        },
                        {
                            label:"竖长(高)",
                            prop:"height",
                            hide:true
                        },
                        {
                            label:"中心图案",
                            prop:"centerDesign",
                            type:'select',
                            dicUrl: '/portal/dict/getDict/seal_center_design/kyz',
                            dicMethod: 'get',
                            dicQuery: {},
                            hide:true
                        },
                        {
                            label:"形状",
                            prop:"shape",
                            type:'select',
                            dicUrl: '/portal/dict/getDict/seal_shape/kyz',
                            dicMethod: 'get',
                            dicQuery: {},
                            hide:true
                        },
                        {
                            label:"制作时间",
                            prop:"produceDate",
                        },
                        {
                            label: '制作时间',
                            prop: 'searchDate',
                            type: 'daterange',
                            search:true,
                            hide:true,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyyMMdd'
                        },
                        {
                            label:"交付时间",
                            prop: 'deliverDate',
                            hide:true
                        },
                        {
                            label:"失效时间",
                            prop:"disableDate",
                            hide:true
                        },
                        {
                            label:"失效类型",
                            prop:"disableType",
                            type:'select',
                            dicUrl: '/portal/dict/getDict/seal_cancel_type/kyz',
                            dicMethod: 'get',
                            dicQuery: {},
                            hide:true
                        },
                        {
                            label:"代加工",
                            prop:"processSign",
                            formatter:function (row,value,label,column) {
                                if(value=='0'){
                                    return "是";
                                }else {
                                    return "否";
                                }
                            },
                            hide:true
                        },
                        {
                            label:"检验结果",
                            prop:"inspectMark",
                            formatter:function(row,value,label,column){
                                return kyzyDict.getLabel(kyzyDict.inspectMark,value);
                            }
                        },
                    ]
                }
            }
        },
        created(){
            this.params.produceUnitId = this.searchForm.id;
        },
        computed: {
        },
        watch: {
            searchForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.params.produceUnitId = this.searchForm.id;
                    this.getList(this.page,this.params);
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
                findSealInfoPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    /*areaCode:this.areaCode*/
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
            viewDialog(row){
                this.formView=true;
                this.formDetail=row;

            }

        }
    }
</script>