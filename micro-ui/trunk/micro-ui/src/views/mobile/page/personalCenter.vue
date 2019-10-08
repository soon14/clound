<template>
         <div class="main">
             <div class="centerTab" >
                 <van-tabs  @disabled="onClickdisabled">
                     <van-tab >
                         <div slot="title" class="title">
                             <i class="el-icon-postcard icon_style"></i>
                             <div class="title_text">
                                 <p>我的指令</p>
                                 <p>点击查看</p>
                             </div>
                         </div>
                         <div class="tab_content">
                             <van-search placeholder="请输入搜索关键词"
                                         v-model="form.title"
                                         background="#bebebe"
                                         show-action
                                         @search="onSearch">
                                 <div slot="action" @click="onSearch">搜索</div>
                             </van-search>
                         </div>
                         <div class="table">
                             <div class="tabel_top">
                                 <div>任务名称</div>
                                 <div>发送时间</div>
                                 <div>状态</div>
                             </div>
                             <div class="table_main">
                                 <van-list v-model="isUpLoading"
                                           :finished="upFinished"
                                           :immediate-check="false"
                                           :offset="10"
                                           finished-text="已无更多数据"
                                           @load="onRefresh">
                                     <div class="table_item" v-for="item in listData" @click="detail(item)">
                                         <div class="table_item_name ellipse" v-text="item.title"></div>
                                         <div class="table_item_time" v-text="item.sendTime"></div>
                                         <div class="table_item_status">{{findLabelByValue(statusDict,item.status)}}</div>
                                     </div>
                                 </van-list>
                             </div>
                         </div>
                     </van-tab>
                     <van-tab disabled>
                             <div slot="title" class="title">
                                 <i class="el-icon-postcard icon_style"></i>
                                 <div class="title_text">
                                     <p>我的勤务</p>
                                     <p>点击查看</p>
                                 </div>
                             </div>
                             <!--<div class="tab_content">
                                 <van-search placeholder="请输入搜索关键词" v-model="value" background="#bebebe" />
                             </div>
                             <div class="table">
                                 <div class="tabel_top">
                                     <div>任务名称</div>
                                     <div>发送时间</div>
                                     <div>状态</div>
                                 </div>
                                 <div class="table_main">
                                     <div class="table_item" v-for="item in listData">
                                         <div class="table_item_name ellipse" v-text="item.name"></div>
                                         <div class="table_item_time">
                                             <p v-text="item.day"></p><p v-text="item.time"></p>
                                         </div>
                                         <div class="table_item_status" v-text="item.status"></div>
                                     </div>
                                 </div>
                             </div>-->
                         </van-tab>


                 </van-tabs >
             </div>
         </div>
</template>

<script>

    import {findListPage} from "@/api/portal/judged/instruction/instruction";
    import {mapGetters} from "vuex";
    import {findLabelByValue} from "@/util/util";
    import {remote}  from  "@/api/admin/dict";

    export default {
        data(){
            return{
                active: 2,
                listData:[],
                statusDict:[],
                isUpLoading: false, // 上拉加载
                upFinished: false, // 上拉加载完毕
                form:{
                    title:"",
                    receiveOrg:'',
                },
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
            }
        },
        created() {
            this.dict();
            this.getList();
        },
        computed: {
            ...mapGetters(["userInfo","userDept"])
        },
        methods:{
            dict(){
                remote("instruct_status").then(response=>{
                    this.statusDict = response.data.data;
                }).catch((err) => {});
            },
            onClickdisabled(name,title){
               this.$notify({
                    message:'建设中...',
                });
            },
            onRefresh(){
                this.page.currentPage++;
                this.getList();
            },
            getList() {
                this.isUpLoading = true;
                this.upFinished = false;
                this.form.receiveOrg = this.userDept.code;
                findListPage(Object.assign({
                    current: this.page.currentPage,
                    size: this.page.pageSize
                }, this.form)).then(response => {
                    let datas = response.data.data.records
                    this.page.total = response.data.data.total
                    this.page.currentPage = response.data.data.current;
                    this.page.pageSize = response.data.data.size;
                    for (let i=0;i<datas.length;i++){
                        this.listData.push(datas[i]);
                    }

                    let index = (this.page.currentPage-1)*this.page.pageSize+datas.length;
                    if(index>=this.page.total){
                        this.upFinished = true;
                    }
                    this.isUpLoading = false;
                })
            },
            onSearch(){
                this.listData = [];
                this.page.currentPage = 1;
                this.getList();
            },
            detail(row){
                this.$router.push({
                    path: this.$router.$avueRouter.getPath({
                        src: "/mobile/peosoncenter/instruction"
                    }),
                    query: {
                        id: row.id,
                        title:row.title,
                        remark:row.remark,
                        planLibraryRemark:row.planLibraryRemark,
                        type:row.type,
                        status:row.status,
                        timeLimit:row.timeLimit,
                        createByName:row.createByName,
                        sendOrgLabel:row.sendOrgLabel,
                        sendTime:row.sendTime,
                        receiveOrgLabel:row.receiveOrgLabel,
                        sendType:row.sendType,
                    }
                });
            },
        },
    }
</script>

<style lang="scss">
    .main{
        box-sizing: border-box;
        padding: 0 0.625rem;
        left: 0;
        width: 100%;
        position: absolute;
        top: 10rem;
        height: 27.3125rem;
        border-radius: 0.5rem;
        .centerTab{
            height: 100%;
            .van-tab + .van-tab{
                margin-left: 0.9375rem;
            }
            .van-field__body{
                height: 100%;
            }
            .icon_style{
                float:left;
            }
            .van-tabs__nav{
                background: none!important;
            }
            .van-tabs--line {
                padding-top:0rem !important;
                height: 5rem !important;
                padding-bottom:0rem !important;
            }
            .van-tabs__wrap {
                height: 5rem;
                position: static !important;
            }
            .van-tabs__line {
                width: 0 !important;
            }
            .van-tabs__nav--line {
                height: 5rem;
                padding-bottom:0rem !important;
                border-radius: 0.5rem;
            }
            [class*=van-hairline]::after {
                border:none;
            }
            .van-tab{
                background: #fff;
                border-radius: 0.5rem;
            }
            .van-tab--active {
                height: 5rem !important;
                background: #f5f6ff;
                border-radius: 0.5rem;
            }
            .icon_style{
                float: left;
                font-size: 2.5rem;
                height:5rem;
                line-height: 5rem;
                padding-left: 1.25rem;
                color:#376cff;
            }
            .title{
                height:5rem;
                p{
                    padding: 0;
                    margin: 0;
                }
            }
            .title_text{
                height:5rem;
                float:left;
                margin-left:0.625rem;
            }
            .title_text p:nth-child(1){
                color:#000;
            }
            .title_text p:nth-child(2){
                line-height: 0.75rem;
                font-size: 0.875rem;
                color: #909399;
            }
            .tab_content{
                border-radius: 0.3125rem;
                margin-top: 1.25rem;
                overflow: hidden;
                .van-search__content{
                    border-radius: 0.5rem!important;
                }
                .van-cell{
                    line-height: 2.375rem !important;
                    padding: 0rem 0.625rem 0rem 0 !important;
                }
            }
            .table{
                background: #fff;
                .tabel_top{
                    display: flex;
                    text-align: center;
                    height: 2.5rem;
                    line-height: 2.5rem;
                    background: #e7e7e7;
                    >div{
                        flex: 1;
                    }
                }
                .table_item{
                    display: flex;
                    text-align: center;
                    height: 3.75rem;
                    border-bottom: 0.0625rem solid #ccc;
                    >div{
                        flex: 1;
                    }
                    .table_item_name{
                        line-height: 3.75rem;
                        padding-left: 0.3125rem;
                    }
                    .table_item_time{
                        padding-top: 8px;
                    }
                    .table_item_status{
                        line-height: 3.75rem;
                        color:#2d8cf0;
                    }
                }
                .table_main{
                    overflow: auto;
                    height: 15rem;
                }
            }
        }
    }
</style>