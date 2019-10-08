<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                :option="tableOption"
                :data="tableData"
                :table-loading="tableLoading"
                @search-change="handleFilter">
            </avue-crud>
        </basic-container>
    </div>
</template>

<script>
    import {treeList} from "@/api/portal/statistics/rectification/rectification.js";
    import {validatenull} from '@/util/validate'

    export default {
        data() {
            return {
                tableLoading:false,
                tableData: [],
                searchForm: {
                    code: undefined,
                    hylx: undefined,
                    sendTime:undefined,
                },
                tableOption: {
                    expandLevel: 1,
                    tree:true,
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
                    menu: false,
                    refreshBtn:false,
                    column: [
                        {
                            label: "所属区划",
                            prop: "code",
                            dicUrl: '/portal/area/areaData',
                            type: "tree",
                            dicMethod: 'get',
                            search: true,
                            editDisabled: true,
                            addDisplay: false,
                            editDisplay: false,
                            dicQuery: {
                                level: 2
                            },
                            props: {
                                label: "name",
                                value: 'code'
                            }
                        },
                        {
                            label:'行业类型',
                            prop:'hylx',
                            search:true,
                            type:'select',
                            dicUrl: '/admin/dict/type/HYLX_DL',
                            dicMethod: 'get',
                            dicQuery: {},
                            span:12,
                            hide:true
                        },
                        {
                            label: '任务总数',
                            prop: 'total',
                            align: 'left',
                            overHidden: true,
                        },
                        {
                            label: '已完成任务数',
                            prop: 'completed',
                            align: 'left',
                            overHidden: true
                        },
                        {
                            label: '任务完成率',
                            prop: 'completedPercent',
                            align: 'left',
                            overHidden: true,
                            formatter:function (row, value, label, column) {
                                let val = value*100;
                                return val+"%";
                            }
                        },
                        {
                            label: '任务及时率',
                            prop: 'onTimePercent',
                            align: 'left',
                            overHidden: true,
                            formatter:function (row, value, label, column) {
                                let val = value*100;
                                return val+"%";
                            }
                        },
                        {
                            label: '统计时间',
                            prop: 'sendTime',
                            type:"daterange",
                            align: 'left',
                            hide: true,
                            search:'true'
                        },
                        ]
                }

            }
        },
        created() {
            //初始化
            this.getTree({});
        },
        computed: {
        },
        methods:{
            getTree(params){
                this.tableLoading = true
                treeList(params).then(response=>{
                    this.tableLoading = false;
                    this.tableData = response.data.data;
                }).catch(()=>{});
            },
            handleFilter(params){
                if (!validatenull(params) && !validatenull(params.sendTime) && "undefined" != typeof (params.sendTime)) {
                    this.searchForm.sendTime = params.sendTime.join(",");
                    params.sendTime = params.sendTime.join(",");

                }
                this.searchForm.hylx = params.hylx;
                this.searchForm.code = params.code;
                this.searchForm = Object.assign(this.filterForm(params),this.searchForm)
                this.getTree(this.searchForm);
            }
        }
    }
</script>

