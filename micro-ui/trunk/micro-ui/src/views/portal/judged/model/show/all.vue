<template>
    <div id="all_box" v-loading="modelLoading">
        <avue-empty v-if="showEmpty" class="empty"></avue-empty>
        <div class="all">
            <div class="all-item" v-for="(item,index) in appData" :key="index" @click="appClick(item)">
                <div class="tuoyuan"><img src="/img/pageimg/tuoyuan.png" alt=""></div>
                <el-card class="all-item-box" shadow="hover" >
                    <div class="tuijian"><img src="/img/pageimg/tuijian.png" alt="" v-if="item.useBest=='0'"></div>
                    <div class="all-msg">
                        <div class="icon_img">
                            <img style="height:80px; width:80px;border-radius: 5px;" :id="item.id+'_allApp'"
                                 :onload="initIcon(item,item.id)"
                            />
                        </div>
                        <div class="icon_r">
                            <h4 class="name ellipse">{{item.name}}</h4>
                            <div class="time">{{item.createDate}}</div>
                            <div class="collection" @click.stop="modelCollect(item,1)">
                                <i class="iconfontlogo alshoucang" :id="item.id+'_collect'"></i>
                                <span>收藏</span>
                            </div>
                            <div @click.stop>
                            <el-rate
                                    @change="change(item.stars,item.id,item.name)"
                                    v-model="item.stars"
                                    show-text>
                            </el-rate>
                            </div>
                        </div>
                    </div>
                    <div class="introduction">
                        <avue-text-ellipsis
                                :key="item.remark"
                                :text="item.remark"
                                :height="70"
                                use-tooltip
                                placement="top">
                            <small slot="more">...</small>
                        </avue-text-ellipsis>
                    </div>
                </el-card>
            </div>
        </div>

        <a href="javascript:void(0)" @click="loadMore()" v-show="morePage" class="morePageClass">
            <i class="el-icon-bottom"></i>点击加载更多（剩余：{{page.total-(page.pageSize*page.currentPage)}} 模型）
        </a>

        <el-dialog :title="modelTitle" width="80%" :visible.sync="modelDialogFlag" :append-to-body="true">
            <app-dialog v-bind:dialogParam="dialogParam"></app-dialog>
        </el-dialog>
    </div>

</template>

<script>
    import {getModelByLevel,getIconList,countModel,getApp} from '@/api/portal/judged/model/modelMenu'
    import {routerJump} from '@/api/portal/judged/model/routerJump'
    import { validatenull } from "@/util/validate";
    import {findPage,getCollect,gradeModel,geteditIcon,modelAll} from '@/api/portal/judged/model/modelCollect'
    import appDialog from '../appDialog'


    export default {
        name: "all",
        components: {appDialog},
        data(){
            return{
                morePage: false,
                activeName: 'first',
                showInput:false,
                showEmpty:false,
                appData:[],
                collectData:[],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 6, // 每页显示多少条,
                },
                searchForm: {
                    name:"",
                },
                modelLoading:true,

                iconMap: new Map(),
                collectMap: new Map(),

                modelTitle:'',//弹窗title
                modelDialogFlag:false,//弹窗是否展示
                dialogParam:{
                    id:'',
                    links:'',
                },
            }
        },
        created(){
           this.initCollect();
        },
        methods: {
              fetchAppInti(){
                  this.fetchApp(this.page);
              },
            change(start, id, name) {
                let params = {modelId: id, stars: start}
                gradeModel(params).then(response => {
                    this.$message.success("感谢您为" + name + "评了" + start + "分!")
                })
            },

            initCollect(){
                findPage().then(response =>{
                    this.collectData = response.data.data.records
                }).catch((error) => {
                })
            },

            modelCollect(item, collect) {
                let collectMap = this.collectMap.get(item.id);
                if ('undefined' == typeof (collectMap) || '' === collectMap || null === collectMap || collect == 1) {
                    this.collectMap.set(item.id, item.id);
                    let headCollect = document.getElementById(item.id + '_heaCollect');
                    let allCollect = document.getElementById(item.id + '_collect');
                    let defCollect = document.getElementById(item.id + '_defCollect');
                    if (collect == 1) {
                        let params = {modelId: item.id, collect: collect, isCollect: allCollect.classList.value=='iconfontlogo alshoucang'?"0":"1"}
                        getCollect(params).then(response => {
                            console.log(params.isCollect)
                            if (params.isCollect=='0') {
                                if (headCollect != null) {
                                    headCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                                if (allCollect != null) {
                                    allCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                                if (defCollect != null) {
                                    defCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                                if (!validatenull(collect)) {
                                    this.$message.success("收藏成功")
                                }
                            } else {
                                if (headCollect != null) {
                                    headCollect.classList.value = 'iconfontlogo alshoucang'
                                }
                                if (allCollect != null) {
                                    allCollect.classList.value = 'iconfontlogo alshoucang'
                                }
                                if (defCollect != null) {
                                    defCollect.classList.value = 'iconfontlogo alshoucang'
                                }
                                if (!validatenull(collect)) {
                                    this.$message.success("取消成功")
                                }
                            }
                        })
                    } else {
                        this.collectData.forEach(ele => {
                            if (item.id === ele.modelId) {
                                if (headCollect != null) {
                                    headCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                                if (allCollect != null) {
                                    allCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                                if (defCollect != null) {
                                    defCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                            }
                        })
                    }
                }
            },

            loadMore() {
                this.page.currentPage = this.page.currentPage + 1
                this.fetchApp(this.page)
                if (this.page.total > this.page.pageSize * this.page.currentPage) {
                    this.morePage = true
                } else {
                    this.morePage = false
                }
            },
            initIcon(item, id) {
                let suffix = "_allApp";
                let img = this.iconMap.get(id + suffix);
                if ('undefined' == typeof (img) || '' === img || null === img) {
                    getIconList(id, "app").then(value => {
                        this.iconMap.set(id + suffix, value);
                        let imgTag = document.getElementById(id + suffix);
                        imgTag.src = value
                        this.modelCollect(item)
                    })
                }else{
                    let imgTag =document.getElementById(id + suffix);
                    imgTag.src=img
                }
            },
           fetchApp(page) {
                this.iconMap.clear();
                this.collectMap.clear();
                this.modelLoading = true;
                this.initCollect();
                getApp(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                }, this.searchForm)).then(response => {
                    this.appData = this.appData.concat(response.data.data.records)
                    this.page.total = response.data.data.total
                    this.modelLoading = false
                    if (this.page.total > (this.page.pageSize * this.page.currentPage)) {
                        this.morePage = true
                    } else {
                        this.morePage = false
                    }
                    if (this.page.total > 0) {
                        this.showEmpty = false
                    } else {
                        this.showEmpty = true
                    }
                }).catch((error) => {
                    this.modelLoading = false
                })
            },
            searchBtn(data) {
                this.searchForm.name = data;
                this.page.total = 0
                this.page.currentPage = 1
                this.page.pageSize = 6
                this.appData = [];
                this.fetchApp(this.page)
            },
            appClick(data) {
                if(data.state=='1'){
                    this.$alert('<strong>该模型正在建设中</strong>',data.name,{
                        dangerouslyUseHTMLString:true
                    });
                    return;
                }
                if (data.openType === '2') {
                    let httpStr = data.links.substr(0,5).toLowerCase()
                    if (httpStr =='http:'||httpStr=='https'){
                        let myHttpStr = httpStr+data.links.substring(5,data.links.length)
                        data.links = myHttpStr
                        window.open(data.links, data.name, 'height=600, width=1000,top=50, left=150')
                    } else {
                        this.dialogParam.id = data.id
                        this.dialogParam.links = data.links
                        this.modelTitle = data.name
                        this.modelDialogFlag = true
                    }
                } else {
                    routerJump(data)
                }
            },
        },
    }
</script>

<style lang="scss">
    #all_box{
        .more{
            position: fixed;
            right: 9px;
            bottom: 110px;
            z-index: 10;
        }
    }
    .all{
        *{
            margin: 0;
            padding: 0;
        }
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        .all-item{
            position: relative;
            width: 32.33%;
            height: 180px;
            margin: 10px 5px 30px 5px;
            .all-item-box{
                position: relative;
                top:0;
                width:100%;
                border: 1px solid #eee;
                border-radius: 5px;
                clear: both;
                background-color: #fbf6f6;
                padding: 10px;
                z-index: 2;
            }
            .el-card.is-hover-shadow:hover {
                cursor: pointer;
                transform:translate(0,-3px);
                background:#f3e9d5;
            }
            .tuoyuan{
                width: 100%;
                position: absolute;
                bottom: -30px;
                img{
                    width: 100%;
                }
            }
            .tuijian{
                position: absolute;
                right:-10px;
                top:-10px;
            }
            .all-msg{
                clear: both;
                overflow: hidden;
                .icon_img{
                    float: left;
                    width: 30%;
                    color: #d5b374;
                    img{
                        margin: 0 auto;
                        height: 80px;
                        width: 80px;
                        border-radius: 5px;
                    }
                }
                .icon_r{
                    float: left;
                    width:70%;
                    .name{
                        width:90%;
                        color:#067af3;
                    }
                    .time{
                        font-size: 13px;
                        color:#969799;
                        padding: 4px;
                    }
                    .collection{
                        i{
                            color:red;
                        }
                        span{
                            margin-left: 10px;
                            font-size: 12px;
                            color:#969799;
                        }
                    }
                }
            }
            .introduction{
                padding-top: 10px;
                max-height: 70px;
                width: 100%;
                font-size: 12px;
                color:#a7a6a4;
            }
        }
    }
    .morePageClass{
        padding-left: 380px;
        color: rgb(64, 158, 255);
        cursor: pointer;
        text-decoration: none;
        font-size: 18px;
    }
    .empty {
        margin: 120px 8px;
        font-size: 14px;
        line-height: 22px;
        text-align: center;
    }
</style>