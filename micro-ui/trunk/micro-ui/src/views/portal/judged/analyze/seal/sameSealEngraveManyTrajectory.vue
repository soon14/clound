<!--
 * @author wengshij
 * @date Created in 2019/7/2 14:41
 * @description:印章被多次刻制轨迹信息
 * @modified By:
 -->
<template>
    <div class="execution">
        <basic-container class="containerPath">
        <div class=" div-card">
            <h3 style="margin-left: 20%">印章所属单位：{{detail.useUnitName}}
                <br/> 印章名称：{{detail.sealName}}
                <br/>印章类型：{{findLabelByValue(dictSealTypeData,detail.sealType)}}
            </h3>
        </div>
            <div id="div-content" style="min-height: 400px;overflow-x: auto!important;margin-top:15px;" v-loading="timeLineLoad">
                <avue-empty v-if="tableData==null || tableData.length<=0" style="padding-top: 20%"></avue-empty>
                <avue-timeline pending time :time-width="110" v-if="tableData!=null && tableData.length>0">
                    <avue-timeline-item icon="el-icon-dessert" color="red" v-for="(item,index) in tableData">
                        <div slot="time">
                            <p v-text="item.engraveTime"></p>
                        </div>
                        <div slot="content">
                            <h4 v-text="index+1+'、'+item.placeName"></h4>
                            <el-form class="infoform">
                                <el-form-item label="刻制场所：">
                                    <span v-text="item.placeName"></span>
                                </el-form-item>
                                <el-form-item label="场所类型：">
                                    <span v-text="findLabelByValue(dictUnitTypeData,item.placeType) "></span>
                                </el-form-item>
                                <el-form-item label="场所地址：">
                                    <span v-text="item.placeAddress"></span>
                                </el-form-item>
                                <el-form-item label="管辖派出所：">
                                    <span v-text="item.deptName"></span>
                                </el-form-item>
                                <el-form-item label="行政区划：">
                                    <span v-text="findLabelByValue(dictCityData,item.cityCode)"></span>
                                </el-form-item>
                                <el-form-item label="刻制时间：">
                                    <span v-text="item.engraveTime"></span>
                                </el-form-item>
                            </el-form>
                        </div>
                    </avue-timeline-item>
                    <avue-timeline-item>
                        <a v-if="morePage" href="javascript:void(0)" @click="moreLoad()"
                           style="color: #409EFF;cursor: pointer;text-decoration: none;">
                            加载更多（剩余：{{page.total-(page.pageSize*page.currentPage)}} 条）</a>
                    </avue-timeline-item>
                </avue-timeline>
            </div>

        </basic-container>
    </div>
</template>


<script>
    import {fetchListPage} from '@/api/portal/judged/analyze/seal/sameSealEngraveMany'
    import {getDict} from "@/api/portal/archive/dict/dict";
    import {findDictArea} from "@/api/portal/archive/common/query";
    import {deepClone} from '@/util/util'

    export default {
        props: ["params"],
        name: "sameSealEngraveManyTrajectory",
        data() {
            return {
                morePage: false,
                tableLoading: false,
                timeLineLoad: true,
                searchForm: {
                    useUnitId: undefined,
                    sealType: undefined,
                    engraveMonth: undefined,
                    useUnitName: '',
                    sealName: ''
                },
                detail: {
                    useUnitId: undefined,
                    sealType: undefined,
                    engraveMonth: undefined,
                    useUnitName: '',
                    sealName: ''
                },

                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 5, // 每页显示多少条,
                },
                tableData: [],
                dictSealTypeData: [],
                dictUnitTypeData: [],
                dictCityData: [],


            }
        },
        watch: {
            params: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler(val) {
                    this.tableData = []
                    this.page.total = 0
                    this.page.currentPage = 1
                    this.page.pageSize = 5
                    this.detail = val
                    this.initSearchCond(val)
                    this.getList(this.page)
                    this.initDivHeight()
                },
                deep: true
            },

        },
        mounted() {
            this.initDivHeight()
        },
        created() {
            this.tableData = []
            this.page.total = 0
            this.page.currentPage = 1
            this.page.pageSize = 5

            this.initSearchCond(this.params)
            this.getList(this.page)

            getDict('seal_type', 'kyz').then(response => {
                this.dictSealTypeData = response.data.data || [];
            }).catch((err) => {
            });
            getDict('unit_type', 'kyz').then(response => {
                this.dictUnitTypeData = response.data.data || [];
            }).catch((err) => {
            });
            findDictArea().then(response => {
                this.dictCityData = response.data || []
            })


        },
        computed: {},
        methods: {

            initSearchCond(cond) {
                this.searchForm.useUnitId = cond.useUnitId
                this.searchForm.sealType = cond.sealType
                this.searchForm.engraveMonth = cond.engraveMonth
                this.searchForm.useUnitName = cond.useUnitName
                this.searchForm.sealName = cond.sealName
                this.detail = deepClone(this.searchForm)
                this.searchForm.sealType = undefined

            },


            moreLoad() {
                this.page.currentPage = this.page.currentPage + 1
                this.getList(this.page)
                if (this.page.total > this.page.pageSize * this.page.currentPage) {
                    this.morePage = true
                } else {
                    this.morePage = false
                }
            },

            initDivHeight() {
                const deviceHeight = document.documentElement.clientHeight;
                const divContent = document.getElementById('div-content');
                divContent.style.height = (Number(deviceHeight) - (Number(deviceHeight) / 2)) + 'px'; //数字是页面布局高度差
            },
            getList(page) {
                this.timeLineLoad = true
                fetchListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                }, this.searchForm)).then(response => {
                    this.tableData = this.tableData.concat(response.data.data.records)
                    this.page.total = response.data.data.total
                    this.timeLineLoad = false
                    if (this.page.total > (this.page.pageSize * this.page.currentPage)) {
                        this.morePage = true
                    } else {
                        this.morePage = false
                    }
                }).catch((error) => {
                    this.timeLineLoad = false
                })
            },
        }
    }
</script>
<style lang="scss">
    .dialogCss {
        overflow: hidden !important;

        .el-dialog__body {
            padding-top: 0px !important;
        }
    }
    .div-card{
        box-shadow: 1px 1px 9px 1px #ccc;
        border-radius: 5px;
        overflow: hidden;
        padding: 10px;
        margin-top: 2px;
        width: auto;

    }

    .containerPath {
        h4 {
            padding: 0;
            margin: 0
        }

        .el-form-item {
            margin-bottom: 0px !important;
        }

        .infoform {
            box-shadow: 1px 1px 9px 1px #ccc;
            border-radius: 5px;
            overflow: hidden;
            padding: 10px;
            margin-top: 10px;
            min-width: 40%;
            width: auto;
        }

        .imgs {
            position: fixed;
            right: 0;
            top: 20%;
        }
    }
</style>
