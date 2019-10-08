<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       :option="tabOption"
                       :data="tabData"
                       @search-change="searchChange"
                       @refresh-change="refreshChange"
                       :table-loading="tableLoading"
                       v-model="form"
                      >
            </avue-crud>
        </basic-container>
    </div>
</template>

<script>
    import {siteListTree} from "@/api/portal/statistics/sitecase/sitecase.js";
    import {mapGetters} from "vuex";
    export default {
        name: "siteCase",
        data() {
            return {
                tableLoading:false,
                form: {},
                tabData:[],
                tabOption: {
                    rowKey :'id',
                    expandRowKeys:["1"],
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    tree:true,
                    align: 'center',
                    addBtn: false,
                    delBtn: false,
                    viewBtn: false,
                    menu: false,
                    column: [
                        {
                            label:'地域',
                            prop:'cityCode',
                            type:'tree',
                            search:true,
                            dicUrl: '/portal/area/areaData',
                            dicMethod: 'get',
                            dicQuery: {},
                            props: {
                                label: "name",
                                value: 'code'
                            },
                            span:12,
                        },
                        {
                            label:'特行场所类别',
                            prop:'type',
                            search:true,
                            dicUrl: '/admin/dict/type/HYLX_DL',
                            type: "tree",
                            dicMethod: 'get',
                            dicQuery: {type: 'HYLX'},
                            hide:true,
                            span:12,
                        },
                        {
                            label:'统计起止时间',
                            prop:'searchTime',
                            search:true,
                            type:'datetimerange',
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                            searchDefault:'',
                            hide:true,
                            span:12,
                        },
                        {
                            label:'单位总量',
                            prop:'total',
                            span:12,
                        },
                        {
                            label:'新增量',
                            prop:'xzs',
                            span:12,
                        },
                        {
                            label:'关闭量',
                            prop:'gbs',
                            span:12,
                        },
                        {
                            label:'违法率',
                            prop:'wfl',
                            span:12,
                        },
                    ]
                },
            }
        },
        computed: {
            ...mapGetters(["permissions"])
        },
        created() {
            this.initDefaultValue()
        },
        methods: {
            initDefaultValue(){
                debugger
                const date = new Date().format("yyyy-mm-dd");
                const date180 = new Date(new Date().getTime() - 4320*60*60*1000).format("yyyy-mm-dd");
                const  arrayDate=[]
                debugger
                arrayDate.push(date)
                arrayDate.push(date180)
                this.tabOption.column[2].searchDefault=arrayDate
                let params = {searchTime:arrayDate}
                this.getList(params)
            },

            getList(params){
                try{
                if(params){
                    let date=params.searchTime;
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
                siteListTree(params).then(response=>{

                    this.tabData = response.data.data
                    this.tableLoading = false
                }).catch(() => {
                });
            },

            searchChange(params) {
                this.getList(params)
            },
            refreshChange() {
                this.getList( )
            },

        }
    }
</script>

<style scoped>

</style>