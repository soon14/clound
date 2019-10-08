
<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="option"
                       :data="tableData"
                       v-model="form"
                       ref="crud"
                       @on-load="getList"
                       @search-change="searchChange"
                       @refresh-change="refreshChange"
                       :page="page"
                       :table-loading="tableLoading">
            </avue-crud>
        </basic-container>
    </div>
</template>


<script>
    import {findPage} from "@/api/portal/archive/dict/dict";
    let dict ={
        lyxt:[
            {label: '旅业', value: 'ly'},
            {label: '娱乐', value: 'yl'},
            {label: '旅业出租屋', value: 'lyczw'},
            {label: '机修', value: 'jx'},
            {label: '来访人员', value: 'lfry'},
            {label: '流动人员', value: 'ldyr'},
            {label: '警钟系统', value: 'jzxt'},
            {label: '治安', value: 'zadict'},
            {label: '刻印章业', value: 'kyz'},
            {label: '统一用户', value: 'tyyh'},

        ],
    }
    export default {
        name:"dict",
        data(){
            return {
                form:{codetype:''},
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                },
                tableData: [],
                option: {
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    page:false,
                    align:'center',
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn:false,
                    delBtn:false,
                    addBtn:false,
                    viewBtn:false,
                    menu:false,
                    menuWidth:'100',
                    column:[
                        {
                            label: "字典类型",
                            prop: "codetype",
                            search:true,
                            span:6
                        },
                        {
                            label: "字典代码",
                            prop: "code",
                            search:true,
                            span:6
                        },
                        {
                            label: "字典名称",
                            prop: "title",
                            search:true,
                            span:6,

                        },
                        {
                            label:"来源系统",
                            prop:"lyxt",
                            search:true,
                            type:'select',
                            dicData:dict.lyxt,
                            span:6
                        }
                    ]
                },
            }
        },
        created(){

        },
        computed: {
        },
        methods:{
            refreshChange(){
                this.getList(this.page)
            },
            searchChange(form) {
                this.getList(this.page, form)
            },
            getList(page, params) {
                try{
                    let   param=this.$refs["crud"].searchForm||{}
                    params=param
                }catch (e) {}
                this.tableLoading = true
                findPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    codetype: this.form.codetype
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
        }
    }
</script>