<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="option"
                       :data="tableData"
                       v-model="form"
                       ref="crud"
                       :page="page"
                       @size-change="sizeChange"
                       @search-reset="clearForm"
                       @on-load="getList"
                       @search-change="searchChange"
                       :table-loading="tableLoading">

                <template slot="menu" slot-scope="scope">
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-view"
                               @click="handleDetail(scope.row)">查看
                    </el-button>
                </template>
            </avue-crud>
        </basic-container>

        <el-dialog :title="detailTitle" fullscreen :visible.sync="showDetail" :append-to-body="applyToBody">
            <keep-alive>
                <hotel-tab v-bind:param-form="paramForm"/>
            </keep-alive>

        </el-dialog>


    </div>
</template>


<script>
    import {findListPage} from "@/api/portal/archive/common/unitQuery";
    import hotelTab from "./hotelTab";

    export default {
        components: {
            "hotelTab": hotelTab,
        },
        props: ["searchForm"],
        data() {
            return {
                form: {
                    hname: ''
                },
                paramForm: {
                    menuId: undefined,
                    unitId: undefined,
                },
                showDetail: false,
                applyToBody: true,
                detailTitle: '',
                viewFlag: false,
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tempSearchForm: {},
                tableData: [],
                option: {
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    page: false,
                    align: 'center',
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn: false,
                    delBtn: false,
                    addBtn: false,
                    viewBtn: false,
                    menuWidth: '100',
                    card: true,
                    column: [
                        {label: "旅馆名称", prop: "hname", search: true, searchDefault: ''},
                        {
                            label: "旅馆类别", prop: "hotelKind",
                            dicUrl: '/portal/dict/getDict/LGLB/ly',
                            dicMethod: 'get',
                        },
                        {
                            label: "行政区划", prop: "cityCode", type: 'tree', search: true,
                            searchFilterable: true,
                            dicUrl: '/portal/area/areaData',
                            dicMethod: 'get',
                            expandLevel: 1,
                            dicQuery: {},
                            props: {
                                label: "name",
                                value: 'code'
                            }
                        },
                        {label: "辖区派出所", prop: "psCode",
                            dicUrl: '/portal/dict/getDict/06/jzxt',
                            dicMethod: 'get',
                            overHidden:true,},
                        {label: "治安负责人", prop: "socialDuty"},
                        {
                            label: "企业状态",
                            prop: "state",
                            search: true,
                            type: 'select',
                            searchFilterable: true,
                            dicUrl: '/portal/dict/getDict/0209/ly',
                            dicMethod: 'get',
                        },
                        {
                            label: "总积分", prop: "pointScore",

                        },
                        {
                            label: "积分等级", prop: "pointType", search: true, type: 'select', searchFilterable: true
                            , dicUrl: '/admin/dict/type/jfdj',
                            dicMethod: 'get',
                          /*  searchMmultiple: true,*/
                            dicQuery: {},
                        },
                        /* {  label:"床位数", prop:"bedNum", hide:true ,},
                         {  label:"数据加密方式", prop:"dbEncrypt", hide:true ,},
                         {  label:"标记位", prop:"flag", hide:true ,},
                         {  label:"床位数", prop:"floors", hide:true ,},
                         {  label:"等级", prop:"grade", hide:true ,},
                         {  label:"地址", prop:"haddress", hide:true ,},
                         {  label:"旅馆编号", prop:"hnoHotel",hide:true },
                         {  label:"旅馆前台类型", prop:"hotelType", hide:true ,},
                         {  label:"旅馆前台版本", prop:"hotelVer", hide:true ,},
                         {  label:"后台入库时间", prop:"inputTime", hide:true ,},
                         {  label:"法人", prop:"legalPerson", hide:true ,},
                         {  label:"联机时间", prop:"linkDate", hide:true ,},
                         {  label:"负责人", prop:"principal", hide:true ,},
                         {  label:"组织机构代码", prop:"psorgan", hide:true ,},
                         {  label:"其它类型", prop:"qtlx", hide:true ,},
                         {  label:"备注", prop:"remark", hide:true ,},
                         {  label:"房间数", prop:"roomNum", hide:true ,},
                         {  label:"保安部电话", prop:"safeTel", hide:true ,},
                         {  label:"星级", prop:"stars", hide:true ,},
                         {  label:"状态改变日期", prop:"stateDate", hide:true ,},
                         {  label:"辖区派出所", prop:"station", hide:true ,},
                         {  label:"辖区派出所", prop:"stationEx", hide:true ,},
                         {  label:"联系电话", prop:"telPhone", hide:true ,},
                         {  label:"前台第三方软件名称", prop:"thirdPart", hide:true ,},
                         {  label:"上传方式", prop:"transType", hide:true ,},
                         {  label:"验证码", prop:"validateCode", hide:true ,}*/
                    ]
                }
            }
        },
        created() {
            this.form.hname = this.searchForm.dwmc
            this.option.column[0].searchDefault = this.form.hname
        },
        computed: {},
        methods: {

            clearForm() {
                this.tempSearchForm = {}
            },
            getList(page, params) {
                this.tableLoading = true
                findListPage(Object.assign({
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
            searchChange(form) {
                    this.searchForm.dwmc = form.hname
                    this.searchForm.placeType= form.placeType
                    this.searchForm.placeState=form.state
                       /* this.searchForm.pointType= form.pointType,*/
                    this.searchForm.orgCode=form.cityCode,
                    this.tempSearchForm=this.searchForm


                this.getList(this.page, form)
                this.$emit("changeCount", this.searchForm);

            },


            handleDetail(row) {
                this.showDetail = true
                this.detailTitle = row.hname
                this.paramForm.menuId = this.searchForm.menuId
                this.paramForm.unitId = row.hnoHotel
            }
        }
    }
</script>


