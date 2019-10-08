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
                       :before-open="handleOpenBefore"
                       :table-loading="tableLoading">

                <template slot="fjLeixingid" slot-scope="scope">
                    {{scope.row.fileTypeName}}
                </template>
                <template slot="fjNeirongForm" slot-scope="scope">
                    <img :id="scope.row.fjId" style="height:300px; width:350px;"/>
                </template>
            </avue-crud>
        </basic-container>

    </div>
</template>


<script>

    import {findCsbafjPage,getJbxxImg} from "@/api/portal/archive/ylcs/csba";

    export default {
        name: 'zzxx',
        props:["csbaForm"],
        data(){
            return {
                csbaId:'',
                form:{
                },
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableData: [],
                imgQuery:{
                    fjId:'',
                },
                option:{
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
                    viewBtn:true,
                    refreshBtn: false,
                    searchBtn:false,
                    columnBtn:false,
                    menuWidth:100,
                    card:true,
                    column:[
                        { label:"附件内容",prop:"fjNeirong",hide:true,formslot:true,span:24},
                        { label: "附件名称",prop: 'fjMingcheng',search:true},
                        { label:"附件大小", prop:"fjDaxiao",},
                        { label:"附件类型", prop:"fjLeixingid",slot:true},
                        { label:"附件上传时间", prop:"fjShijian",labelWidth:120},
                        { label:"业务类型",
                            prop:"ywLx",
                            hide:true,
                            dicUrl: '/portal/dict/getDict/业务类型/yl',
                            dicMethod: 'get',
                            dicQuery: {}
                        }
                        ]
                }
            }
        },
        created(){
            this.csbaId = this.csbaForm.csbaId
        },
        computed: {
        },
        watch: {
            csbaForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.csbaId = this.csbaForm.csbaId
                    this.getList(this.page,{});
                },
                deep: true
            },
        },
        methods:{
            getList(page, params) {
                this.tableLoading = true
                findCsbafjPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    csbaId:this.csbaId,
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            handleFilter(param) {
                this.page.page = 1;
                this.getList(this.page, param);
            },
            handleOpenBefore(show, type) {
                window.boxType = type;
                this.form.fjLeixingid = this.form.fileTypeName;
                if(type==='view'){
                    this.imgQuery.fjId=this.form.fjId
                    getJbxxImg(this.imgQuery,this.form.fjId)
                }
                show();
            },

        }
    }
</script>