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
    </avue-crud>
   </basic-container>

    </div>
</template>


<script>
    import {findBaxjPage} from "@/api/portal/archive/ylcs/csba";
    import {ylcsDict}  from  "@/const/crud/portal/archive/ylcs/ylcsDict";
    export default {
        name: 'baxj',
        props:["csbaForm"],
        data(){
            return {
                compId:'',
                form:{
                },
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
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
                    viewBtn:true,
                    menuWidth:'100',
                    column:[
                        { label:"巡检单位", prop:"compName",hide:true},
                        { label:"场所编号", prop:"csbaBh",hide:true},
                        { label:"业务类型", prop:"ywLx",hide:true,type:'select',
                          dicUrl: '/portal/dict/getDict/业务类型/yl',
                          dicMethod: 'get',
                          dicQuery: {},
                        },
                        { label:"巡检人", prop:"baxjXjr",search:true},
                        { label:"巡检数",prop: 'baxjXjs',},
                        { label:"漏检数", prop:"baxjLjs",},
                        { label:"巡检开始时间", prop:"baxjKssj",labelWidth:120,overHidden:true,},
                        { label:"巡检结束时间", prop:"baxjJssj",labelWidth:120,overHidden:true,},
                        { label:"巡检线路", prop:"xjlxName",overHidden:true,span:24},
                        { label:"备注", prop:"baxjBz",overHidden:true,span:24},
                    ]
                }
            }
        },
        created(){
            this.compId = this.csbaForm.compId
        },
        computed: {
        },
        watch: {
            csbaForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.compId = this.csbaForm.compId
                    this.getList(this.page,{});
                },
                deep: true
            },
        },
        methods:{
            getList(page, params) {
                this.tableLoading = true
                findBaxjPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    compId:this.compId
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            handleFilter(param) {
                this.page.page = 1;
                this.getList(this.page, param);
            }

        }
    }
</script>