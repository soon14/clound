<template>

    <basic-container class="search-container">
        <avue-crud
                ref="crud"
                :data="tableData"
                :option="option"
                v-model="form"
                :page="page"
                :table-loading="tableLoading"
                @refresh-change="refreshChange"
                @search-change="searchChange"
                @on-load="getPage"
                @search-reset="handleSearchReset"
        >

            <template slot="menu"
                      slot-scope="scope">
                <el-button size="small"
                           type="text"
                           icon="el-icon-view"
                           @click="handleDetail(scope.row,scope.index)">查看
                </el-button>
            </template>

        </avue-crud>

        <el-dialog title="详情" :visible.sync="detailView" :append-to-body="true" :fullscreen="true">
            <place-jump :param="paramForm">
            </place-jump>
        </el-dialog>

    </basic-container>


</template>

<script>

    import {listPage} from '@/api/portal/search/show/searchShow'
    import {validatenull} from '@/util/validate';
    import placeJump from "@/views/portal/archive/contact/placeJump";
    import request from '@/router/axios'
    import {getPageData} from '@/api/portal/rest/maya/mayaApi'

    export default {
        name: "showList",
        components: {
            "placeJump": placeJump
        },
        props: {
            detailRow: {
                type: Object
            },
            schemeId: {
                type: String
            }
        },
        data() {
            return {
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                },
                tableData: [],
                tempTableData: [],
                option: {},
                queryParams: {},
                tempSchemeId: undefined,
                listLoading: false,
                form: {},
                detailView: false,
                paramForm: {
                    placeType: '',//单位类型 警综页面需要判断每个场所对应的显示字段和字典翻译
                    lyxtType: '',
                    unitId: '',
                    compId: '',//娱乐场所需两个id，一场所备案id（unitId），二单位id（compId）
                },//场所详情页面跳转所需参数
            };
        },

        mounted() {


        },
        created() {
            this.option = this.detailRow
            this.tempSchemeId = this.schemeId
            this.getAllList(this.page)

        },
        watch: {
            detailRow: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.option = this.detailRow
                    this.tempSchemeId = this.schemeId
                    this.getList(this.page)
                },
                deep: true
            },
        },
        methods: {
            getPage(page) {
                let flag = this.option.pseudo || false
                if (flag) {
                    this.tableData = getPageData(this.tempTableData, page)
                } else {
                    this.getList(page)
                }

            },
            getAllList(page) {
                let flag = this.option.pseudo || false
                if (flag) {
                    this.getTempList(page)

                } else {
                    this.getList(page)
                }
            },
            getTempList(page) {
                this.tableLoading = true
                let obj = {};
                Object.keys(this.queryParams).forEach(ele => {
                    if (!validatenull(this.queryParams[ele])) {
                        obj["searchMap[" + ele + "]"] = this.queryParams[ele]
                    }
                });
                listPage(Object.assign({
                    current: page.currentPage,
                    size: 200,
                    schemeId: this.tempSchemeId
                }, obj)).then(response => {
                    this.tempTableData = response.data.data.records || []
                    this.tableData = getPageData(this.tempTableData, page)
                    this.page.total = this.tempTableData.length
                    this.tableLoading = false
                })
            },
            getList(page) {
                this.tableLoading = true
                let obj = {};
                Object.keys(this.queryParams).forEach(ele => {
                    if (!validatenull(this.queryParams[ele])) {
                        obj["searchMap[" + ele + "]"] = this.queryParams[ele]
                    }
                });
                listPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    schemeId: this.tempSchemeId
                }, obj)).then(response => {
                    this.tempTableData = response.data.data.records || []
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            searchChange(form) {
                this.queryParams = this.filterForm(form)
                this.getAllList(this.page)
            },
            refreshChange() {
                this.getAllList(this.page)
            },
            handleSearchReset() {
                this.queryParams = [];
            },
            handleDetail(row, index) {
                if (validatenull(this.option.lyxt) || validatenull(this.option.pk)) {
                    this.$refs.crud.rowView(row, index)
                } else {
                    this.paramForm.lyxtType = this.option.lyxt
                    let pk = this.option.pk.split(",")
                    let unitId = row[pk[0]]
                    this.paramForm.unitId = unitId
                    if (this.option.lyxt == '0') {
                        this.paramForm.placeType = row.gxhylx
                    }
                    if (this.option.lyxt == '1') {
                        let compId = validatenull(pk[1]) ? this.paramForm.unitId : row[pk[1]]
                        this.paramForm.compId = compId
                    }
                    this.detailView = true
                }

            }

        }
    };
</script>

<style>
    .search-container .avue-crud__menu {
        display: none;
    }

    .search-container .el-card__body {
        padding: 0px 20px 20px 20px !important;
    }
</style>


