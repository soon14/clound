<template>

    <basic-container>
        <avue-crud
                ref="crud"
                :data="tableData"
                :option="option"
                v-model="form"
                :page="page"
                @on-load="getPage"
                :table-loading="tableLoading"
                @refresh-change="refreshChange"
                @search-change="searchChange"
                @search-reset="handleSearchReset"
        >
            <template slot="menu" slot-scope="scope" >
                <el-button size="mini"
                           type="text"
                           icon="el-icon-view"
                           @click="viewDialog(scope.row)">详细
                </el-button>
            </template>
        </avue-crud>
        <el-dialog title="人员详情"  :visible.sync="hkFormView" :fullscreen="true" :append-to-body="true">
            <avue-detail :option="hkFormOption" v-model="hkFromDetail">
            </avue-detail>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" @click="hkFormView=false">关 闭
                </el-button>
            </div>
        </el-dialog>
    </basic-container>


</template>

<script>

    import {fetchPage, getPageData} from '@/api/portal/rest/maya/mayaApi'
    import {validatenull} from '@/util/validate';
    import {hkFormOption}  from  "@/const/crud/portal/archive/lvye/routineCheck";

    export default {
        name: "hkHotelList",
        props:["paramForm"],
        data() {
            return {
                hkFormView:false,
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                },
                tableData: [],
                tempTableData: [],
                option: {
                    dialogHeight: '90%',
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    delBtn: false,
                    viewBtn: false,
                    editBtn: false,
                    menuWidth:'100',
                    column: [
                        {
                            label: "姓名",
                            prop: "xm",
                            search: true,
                        },
                        {
                            label: "性别",
                            prop: "xb",
                        },
                        {
                            label: "出生日期",
                            prop: "csrq",
                        },
                        {
                            label: "证件号码",
                            prop: "zjhm",
                            search: true,
                        },
                        {
                            label: "国家和地区",
                            prop: "gjhdq",
                        },
                        {
                            label: "证件类型",
                            prop: "zjlxmc",
                        },
                        {
                            label: "入住时间",
                            prop: "rzsj",
                        },
                        {
                            label: "退房时间",
                            prop: "tfsj",
                        },
                        {
                            label: "时间范围",
                            prop: "searchTime",
                            hide:true,
                            search: true,
                            type: 'datetimerange',
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                        },
                    ]
                },
                hkFormOption:hkFormOption,
                hkFromDetail:[],
                queryParams: {xm:''},
                listLoading: false,

                form: {},
            };
        },

        mounted() {


        },
        created() {
            this.queryParams.orderFields='rzsj';
            this.queryParams.orderSorts='desc';
            this.queryParams.lgbm = this.paramForm.unitId;
            this.getList(this.page)

        },

        methods: {
            viewDialog(row){
                this.hkFormView=true
                this.hkFromDetail=row
            },

            getPage(page) {
                this.tableData = getPageData(this.tempTableData, page)
            },


            getList(page,form) {
                this.tableLoading = true
                fetchPage({
                    urlKey: "hk-hotel-url",
                    method: "POST",
                    current: page.currentPage,
                    size: 200
                }, this.queryParams).then(response => {
                    this.tempTableData = response.data.data.records || []
                    this.tableData = getPageData(this.tempTableData, page)
                    this.page.total = this.tempTableData.length
                    this.tableLoading = false
                })
            },
            searchChange(form) {
                try{
                    let   param=this.$refs["crud"].searchForm||{}
                    form=param
                    if(form){
                        let date=form.searchTime;
                        if(date&&Array.isArray(date) && date.length === 2){
                            this.queryParams.rzsj='between#'+date[0]+',' +date[1]
                        }else{
                            this.queryParams.rzsj=date[0]
                            this.queryParams.tfsj=date[0]
                        }
                    }
                }catch (e) {}
                if (!validatenull(form.xm)) {
                    this.queryParams.xm = 'like#'+form.xm;
                }
                if (!validatenull(form.zjhm)) {
                    this.queryParams.zjhm = form.zjhm;
                }
                this.getList(this.page,form)
            },
            refreshChange() {
                this.getList(this.page)
            },
            handleSearchReset() {
                this.queryParams.xm = '',
                    this.queryParams.zjhm ='',
                    this.queryParams.rzsj = ''
            },

            watch: {
                paramForm: {
                    //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                    handler() {
                        this.queryParams.lgbm = this.paramForm.unitId;
                        this.getList(this.page);
                    },
                    deep: true
                },
            },

        }
    };
</script>


