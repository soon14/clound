<template>
    <div class="execution">
        <basic-container class="containerPath">
            <avue-crud
                    ref="crud"
                    v-model="form"
                    :page="page"
                    :data="tableData"
                    :option="tableOption"
                    :table-loading="tableLoading"
                    @on-load="getList"
                    @size-change="sizeChange"
                    @refresh-change="refreshChange"
                    @search-change="searchChange"
                    @search-reset="handleSearchReset"
            >
            </avue-crud>

        </basic-container>
    </div>
</template>


<script>
    import {fetchListPage} from '@/api/portal/judged/analyze/entertainment/personTruthReport'
    import {validatenull} from '@/util/validate'

    export default {
        props: ["params"],
        name: "personTruthReportDetail",
        data() {
            return {
                form:{},
                tableLoading: false,
                searchForm: {
                    placeId: undefined,
                    clockMonth: undefined,
                    xm:undefined,
                },
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                },
                tableData: [],
                tableOption:{
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    viewBtn: false,
                    editBtn: false,
                    delBtn: false,
                    menu:false,
                    column: [
                        {label: "姓名", prop: "xm", search: true,overHidden: true},
                        {label: "曾用名", prop: "cym",overHidden: true},
                        {label: "性别", prop: "xb",overHidden: true,
                            dicUrl: '/portal/dict/getDict/01/ldrk',
                            dicMethod: 'get',
                            dicQuery: {},
                        },
                        {label: "民族", prop: "mz",overHidden: true,
                            dicUrl: '/portal/dict/getDict/05/ldrk',
                            dicMethod: 'get',
                            dicQuery: {},
                        },
                        {label: "国籍", prop: "gj", overHidden: true,
                            dicUrl: '/portal/dict/getDict/04/ldrk',
                            dicMethod: 'get',
                            dicQuery: {},
                        },
                        {label: "证件号码", prop: "idCard",overHidden: true},
                        {label: "出生日期", prop: "csrq",overHidden: true},
                        {label: "户籍地址", prop: "hjdzxz",overHidden: true},
                        {label: "居住地址", prop: "zzdzqz",overHidden: true},
                        {label: "文化程度", prop: "whcd",overHidden: true,
                            dicUrl: '/portal/dict/getDict/02/ldrk',
                            dicMethod: 'get',
                            dicQuery: {},
                        },
                        {label: "联系电话", prop: "lxdh",overHidden: true},
                        {
                            label: "最新打卡时间", prop: "clockTime",
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                        },
                        {
                            label: "最新打卡时间", prop: "searchTime",
                            search: true, type: 'daterange',
                            editDisabled: false, viewDisplay: false,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',hide:true
                        }
                    ]
                },
            }
        },
        watch: {
            params: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.$refs["crud"].searchForm.xm=""
                    this.$refs["crud"].searchForm.searchTime=[]
                    this.initSearchParam()
                    this.getList(this.page)
                },
                deep: true
            },

        },
        mounted() {
        },
        created() {
            this.initSearchParam()
            this.getList(this.page)
        },
        computed: {},
        methods: {
            initSearchParam(){
                this.searchForm={}
                this.searchForm.placeId = this.params.placeId
                this.searchForm.clockMonth = this.params.clockMonth
            },
            getList(page) {
                this.tableData = []
                this.tableLoading = true
                fetchListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                }, this.searchForm)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            sizeChange(size) {
                this.page.pageSize = size;
            },
            searchChange(params) {
                if (!validatenull(params) && !validatenull(params.searchTime) && "undefined" != typeof (params.searchTime)) {
                    this.searchForm.clockMonth = params.searchTime.join(",");
                    params.searchTime = params.searchTime.join(",");
                }
                this.searchForm = Object.assign(this.filterForm(params),this.searchForm)
                this.getList(this.page)
            },
            /**
             * 刷新回调
             */
            refreshChange() {
                this.getList(this.page)
            },
            /**
             * 清除全局检索条件
             */
            handleSearchReset() {
                this.$refs["crud"].searchForm.searchTime=[]
                this.searchForm={
                    xm:'',
                    placeId:this.params.placeId,
                    clockMonth:this.params.clockMonth
                }
                this.getList(this.page)
            },
        }
    }
</script>
<style lang="scss">
</style>