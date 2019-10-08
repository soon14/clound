<!--
 * @author wengshij
 * @date Created in 2019/7/2 14:41
 * @description:承接车辆轨迹信息
 * @modified By:
 -->
<template>
    <div class="execution">
        <basic-container class="containerPath">
            <div class="div-card">
            <h3 style="margin-left: 12%">场所名称：{{detail.placeName}}<br/>管辖派出所：{{detail.deptName}}</h3>
            </div>
            <div id="div-content" style="min-height: 400px;overflow-x: auto!important;margin-top: 15px"  v-loading="timeLineLoad">
                <avue-empty v-if="tableData==null || tableData.length<=0" style="padding-top: 20%"></avue-empty>
                <avue-timeline pending time :time-width="100" v-if="tableData!=null && tableData.length>0">
                    <avue-timeline-item icon="el-icon-truck" color="red" v-for="(item,index) in tableData">
                        <div slot="time">
                            <p v-text="item.sxsj"></p>
                        </div>
                        <div slot="content">
                            <h4 v-text="index+1+'、'+item.jdchphm" ></h4>
                            <el-form class="infoform">
                                <el-form-item label="车主：">
                                    <span v-text="item.jdcsyrmc"></span>
                                </el-form-item>
                                <el-form-item label="车牌号：">
                                    <span v-text="item.jdchphm"></span>
                                </el-form-item>
                                <el-form-item label="车辆型号：">
                                    <span v-text="item.clxh"></span>
                                </el-form-item>
                                <el-form-item label="发动机号：">
                                    <span v-text="item.jdcfdjddjh"></span>
                                </el-form-item>
                                <el-form-item label="车身颜色：">
                                    <span v-text="findLabelByValue(dictCarColorData,item.jdccsysdm) "></span>
                                </el-form-item>

                                <el-form-item label="送修人：">
                                    <span v-text="item.sxrxm"></span>
                                </el-form-item>
                                <el-form-item label="送修人身份证：">
                                    <span v-text="item.sxrzjhm"></span>
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
    import {findCjclPage, getClxqDetail, getClImg} from "@/api/portal/archive/mechanical/jxyxt";
    import {getDict} from "@/api/portal/archive/dict/dict";
    import {validatenull} from '@/util/validate'

    export default {
        props: ["detailRow"],
        name: "undertakeVehicleTrajectory",
        data() {
            return {
                morePage: false,
                tableLoading: false,
                timeLineLoad:true,
                searchForm: {
                    dwbh: undefined,
                    startTime: undefined,
                    endTime: undefined
                },
                dictCarColorData: [],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 5, // 每页显示多少条,
                },
                detail: {},
                tableData: [],



            }
        },
        watch: {
            detailRow: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler(val) {
                    this.tableData=[]
                    this.page.total = 0
                    this.page.currentPage = 1
                    this.page.pageSize = 5
                    this.detail = val
                    this.initSearchCond(val)
                    this.getList(this.page)
                },
                deep: true
            },

        },
        mounted() {
            this.initDivHeight()
        },
        created() {
            this.tableData=[]
            this.page.total = 0
            this.page.currentPage = 1
            this.page.pageSize = 5
            this.detail = this.detailRow
            this.initSearchCond(this.detailRow)
            this.getList(this.page)
            getDict('csys', 'jx').then(response => {
                this.dictCarColorData = response.data.data || [];
            }).catch((err) => {
            });
        },
        computed: {},
        methods: {
            moreLoad() {
                this.page.currentPage = this.page.currentPage + 1
                this.getList(this.page)
                if (this.page.total > this.page.pageSize * this.page.currentPage) {
                    this.morePage = true
                } else {
                    this.morePage = false
                }
            },
            initSearchCond(row) {
                this.searchForm.dwbh = row.placeId
                if (!validatenull(row.searchTime) && row.searchTime.indexOf("至") != -1) {
                    let time = row.searchTime.replace(/\s/g, "").split("至")
                    this.searchForm.startTime = time[0]
                    this.searchForm.endTime = time[1];
                } else {
                    this.searchForm.startTime = row.dayDate
                    this.searchForm.endTime = row.dayDate
                }

            },
            initDivHeight() {
                const deviceHeight = document.documentElement.clientHeight;
                const divContent = document.getElementById('div-content');
                divContent.style.height = ((Number(deviceHeight)-(Number(deviceHeight)/2) )) + 'px'; //数字是页面布局高度差
            },
            getList(page) {
                this.timeLineLoad = true
                findCjclPage(Object.assign({
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
        .el-dialog__body{
            padding-top: 0px !important;
            .el-card__body{
                padding-top: 0px;
                .el-card__body{
                    padding-top: 0px;
                }
            }
        }
    }
    .div-card{
        box-shadow: 1px 1px 9px 1px #ccc;
        border-radius: 5px;
        overflow: hidden;
        padding: 10px;
        margin-top: 10px;
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
