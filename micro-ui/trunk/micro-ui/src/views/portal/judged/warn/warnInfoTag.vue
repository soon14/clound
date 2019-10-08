<template>

    <div v-loading="loading">
        <ul v-show="isHasData" v-for="(warn, index) in warnData" :key="index" >
            <li>{{index+1}}</li>
            <li>[<a href="javascript:void(0)" @click="openModel(warn)" >{{warn.modelMenu.name}}</a>]</li>
            <li><a href="javascript:void(0)" @click="openDialog(warn)">{{warn.title}}</a></li>
            <li :title="warn.brief">{{warn.brief}}</li>
            <li>{{warn.warnTime}}</li>
        </ul>
        <avue-empty v-show="!isHasData"  desc="暂无数据"></avue-empty>

        <el-dialog title="警告明细"  :visible.sync="warnView" append-to-body>
            <warn-info-detail :detailRow="formDetail"></warn-info-detail>
            <div slot="footer"  class="el-dialog--center" style="text-align: right!important;">
                <el-button type="primary" @click="warnView=false">关闭 </el-button>
            </div>
        </el-dialog>
    </div>





</template>

<script>
    import {findWarnInfoPage,findRelationPage} from "@/api/portal/judged/warn/warnInfo"
    import  warnInfoDetail from '@/views/portal/judged/warn/warnInfoDetail'
    export default {
        name: "warnInfoTag",
        components: {warnInfoDetail},
        props: ["param"],
        comments:{
            warnInfoDetail
        },
        data() {
            return {
                warnData: [],
                params: {
                    placeId: undefined,
                    sourceType: undefined,
                    modelId: undefined
                },
                formDetail:{},
                warnView:false,
                loading: true,
                isHasData:false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 5 // 每页显示多少条
                },


            }
        },
        created() {
            this.initParam()
            this.fetchRelationPage(this.params)
        },
        computed: {},
        watch: {
            param: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.initParam()
                    this.fetchRelationPage(this.params)
                },
                deep: true
            },
        },
        methods: {
            openModel(row){
                this.msg("模型功能暂未开放","error")
            },
            openDialog(row){
                this.warnView=true
                this.formDetail=row
            },
            initParam() {
                this.params.placeId = this.param.placeId
                this.params.sourceType = this.param.sourceType
                this.params.modelId = this.param.modelId
            },
            msg(title, type) {
                this.$message({
                    type: type,
                    dangerouslyUseHTMLString: true,
                    duration: 4000,
                    message: title
                });
            },
            fetchRelationPage(params){
                this.loading = true
                this.isHasData=false
                this.warnData = []
                //todo 测试数据-没有放开查询条件
                findRelationPage(Object.assign({
                    current: this.page.currentPage,
                    size: this.page.pageSize,
                }, {})).then(response => {
                    this.warnData = response.data.data.records
                    let total = response.data.data.total
                    if(total>0){
                        this.isHasData=true
                    }
                    this.loading = false
                }).catch((err) => {
                    this.loading = false
                });
            },

            fetchWarnInfoPage(params) {
                this.loading = true
                this.isHasData=false
                this.warnData = []
                findWarnInfoPage(Object.assign({
                    current: this.page.currentPage,
                    size: this.page.pageSize,
                }, params)).then(response => {
                    this.warnData = response.data.data.records
                    let total = response.data.data.total
                    if(total>0){
                        this.isHasData=true
                    }
                   this.loading = false
                }).catch((err) => {
                    this.loading = false
                });
            },
        }
    }
</script>