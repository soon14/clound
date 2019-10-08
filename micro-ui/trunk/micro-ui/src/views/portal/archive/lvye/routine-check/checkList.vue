<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="optionTable"
                       :data="tableData"
                       v-model="form"
                       ref="crud"
                       :page="page"
                       @on-load="findCheck"
                       @search-change="handleFilter"
                       :table-loading="tableLoading">
                <template slot="mj" slot-scope="scope">
                  <span> {{scope.row.jcmjZh}} {{scope.row.jcmjTwoZh==null?'':'、'+scope.row.jcmjTwoZh}}</span>
                </template>
                <template slot="menu" slot-scope="scope">
                    <el-button
                            size="small"
                            type="text"
                            icon="el-icon-view"
                            @click="preview(scope.row)">详情
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog title="详情"   :visible.sync="checkView" append-to-body>

                <avue-detail :option="optionForm" v-model="fromDetail" >

                </avue-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="checkView=false">关闭 </el-button>
            </div>
        </el-dialog>


    </div>
</template>


<script>


    import {findCheckPage} from "@/api/portal/archive/lvye/routineCheck";
    import {option,optionDetail}  from  "@/const/crud/portal/archive/lvye/routineCheck";

    export default {
        name: 'routineCheck',
        props:["paramForm"],
        data(){
            return {
                optionTable:option,
                optionForm:optionDetail,
                form:{
                },
                searchFrom: {
                    unitId: undefined,
                    menuId: undefined,
                },
                checkView:false,
                tableLoading: false,
                fromDetail:[],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableData: [],

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
                    this.findCheck(this.page,{});
                },
                deep: true
            },
        },
        methods:{
            preview(row){
              this.checkView=true
                this.fromDetail=row
            },
            findCheck(page,params) {
                if(params){
                    let date=params.checkTime;
                    if(date&&Array.isArray(date) && date.length === 2){
                        params.startTime=date[0]
                        params.endTime=date[1]
                         }
                }
                this.tableLoading=true
                findCheckPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    noHotel:this.searchFrom.unitId
                },params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },

            handleFilter(param) {
                console.log(this.$refs.checkTime)
                this.page.page = 1;
                this.findCheck(this.page, param);
            },

        }
    }
</script>