<template>
    <div class="analysisCenter_box">
        <div class="analysisCenter">
            <div class="main">
                <div class="demo-input-suffix1">
                    <el-input
                            v-show="showInput"
                            placeholder="请输入模型名称"
                            v-model="inputVal">
                        <i slot="suffix" class="el-input__icon el-icon-search" @click="searchBtn(inputVal)"></i>
                    </el-input>
                </div>
                <template>
                    <div id="all_box" v-loading="modelLoading">
                        <div class="all">
                            <div class="all-item" v-for="(item,index) in appData" :key="index" @click="appClick(item)">
                                <div class="tuoyuan"><img src="/img/pageimg/tuoyuan.png" alt=""></div>
                                <el-card class="all-item-box" shadow="hover" >
                                    <div class="tuijian"><img src="/img/pageimg/tuijian.png" alt="" v-if="item.useBest=='0'"></div>
                                    <div class="all-msg">
                                        <div class="icon_img">
                                            <img style="height:80px; width:80px;border-radius: 5px;" :id="item.modelId"
                                                 :onload="initIcon(item.modelId)"
                                            />
                                        </div>
                                        <div class="icon_r">
                                            <h4 class="name ellipse">{{item.name}}</h4>
                                            <div class="time">{{item.createTime}}</div>
                                            <div class="collection" @click.stop="deModel(item.modelId)">
                                                <i class="iconfontlogo alshoucang1"></i>
                                                <span>收藏</span>
                                            </div>
                                            <div @click.stop>
                                                <el-rate
                                                        @change="change(item.stars,item.modelId,item.name)"
                                                        v-model="item.stars"
                                                        show-text>
                                                </el-rate>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="introduction ellipses">{{item.remark}}</div>
                                </el-card>
                            </div>
                        </div>
                        <div class="all" >
                            <div class="all-item" @click="openModel()">
                                <div class="tuoyuan">
                                    <img src="/img/pageimg/tuoyuan.png" alt="">
                                </div>
                                <div class="el-card all-item-box is-hover-shadow">
                                    <div class="el-card__body" style=" border: 1px dashed rgb(592, 200, 500);">
                                        <div style="margin-top: 15%; margin-bottom: 15%; text-align: center;">
                                            <div class="el-add-card">
                                                <a href="#" class="href_url" style="align-content: center;">
                                                    <i class="el-icon-plus"></i>
                                                    <span>添加</span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <a href="javascript:void(0)" @click="loadMore()" v-show="morePage" class="morePageClass">
                            <i class="el-icon-bottom"></i>点击加载更多（剩余：{{page.total-(page.pageSize*page.currentPage)}} 模型）
                        </a>
                    </div>
                </template>
                <el-dialog title="添加模型" :visible.sync="addModel" width="90%" @close="close()">
                        <add-Model/>
                </el-dialog>
            </div>
        </div>
    </div>
</template>

<script>
    import {getIconList} from '@/api/portal/judged/model/modelMenu'
    import {routerJump} from '@/api/portal/judged/model/routerJump'
    import { validatenull } from "@/util/validate";
    import {findPage,getCollect,deleteCollect,gradeModel} from '@/api/portal/judged/model/modelCollect'
    import addModel from './addModel.vue';
    export default {
        name: "all",
        components: {
            addModel
        },
        data(){
            return{
                inputVal:"",
                addModel:false,
                morePage: false,
                activeName: 'first',
                showInput:false,
                showEmpty:false,
                appData:[],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 6, // 每页显示多少条,
                },
                searchForm: {
                    name:"",
                },
                modelLoading:false,

                iconMap: new Map(),
                collectMap: new Map(),

                modelTitle:'',//弹窗title
                modelDialogFlag:false,//弹窗是否展示
                modelLinks:'',
            }
        },
        mounted(){
            this.fetchApp(this.page)
        },
        methods:{
            change(start,id,name){
                let params = {modelId:id,stars:start}
                gradeModel(params).then(response=>{
                    this.$message.success("感谢您为"+name+"评了"+start+"分!")
                })
            },

            deModel(id) {
                deleteCollect(id).then(response => {
                    this.$message.success("移除成功")
                    this.appData=[];
                    this.fetchApp(this.page)
                }).catch((err) => {
                    this.$message.error("操作失败")
                })
            },

            openModel(){
                this.addModel = true
            },
            close(){
                this.page.total = 0
                this.page.currentPage = 1
                this.page.pageSize = 6
                this.appData=[];
                this.fetchApp(this.page)
            },
            loadMore(){
                this.page.currentPage = this.page.currentPage + 1
                this.fetchApp(this.page)
                if (this.page.total > this.page.pageSize * this.page.currentPage) {
                    this.morePage = true
                } else {
                    this.morePage = false
                }
            },
            initIcon(id) {
                this.iconMap.clear();
                let img=this.iconMap.get(id);
                if('undefined'==typeof (img)||''===img||null===img) {
                    getIconList(id).then(value => {
                        this.iconMap.set(id,value);
                        let imgTag =document.getElementById(id);
                        imgTag.src=value
                    })
                }else{
                    let imgTag =document.getElementById(id);
                    imgTag.src=img
                }
            },
            fetchApp(page){
                this.modelLoading=true;
                findPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                }, this.searchForm)).then(response => {
                    this.appData = this.appData.concat(response.data.data.records)
                    this.page.total = response.data.data.total
                    this.modelLoading=false
                    if (this.page.total > (this.page.pageSize * this.page.currentPage)) {
                        this.morePage = true
                    } else {
                        this.morePage = false
                    }
                }).catch((error) => {
                    this.modelLoading = false
                })
            },
            searchBtn(data){
                this.searchForm.name=data;
                this.page.total = 0
                this.page.currentPage = 1
                this.page.pageSize = 6
                this.appData=[];
                this.fetchApp(this.page)
            },
            appClick(data) {
                if (data.openType === '2') {
                    if (data.links.includes('http') || data.links.includes('https')) {
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
            height: 162px;
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
                .icon_img{
                    float: left;
                    width: 30%;
                    color: #d5b374;
                    i{
                        font-size: 70px;
                        text-align: center;
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
                overflow: hidden;
                -o-text-overflow: ellipsis;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 3;
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
    .el-tooltip__popper{
        max-width: 400px;
    }
    .analysisCenter_box{
        *{
            box-sizing: border-box;
        }
        padding:0 8px 10px;
        box-sizing: border-box;
        height: 100%;

        /*滚动条样式*/
        ::-webkit-scrollbar {
            width: 6px;
            height: 6px;
            background-color: #f5f5f5;
        }
        ::-webkit-scrollbar-thumb {
            background-color: #999;
        }
        ::-webkit-scrollbar-track {
            background-color: #fff;
        }
    }
    .analysisCenter {
        overflow: auto;
        background: #fff;
        border-radius: 6px;
        min-width: 1120px;
        height: 100%;

        .main {
            padding: 10px;
            background: #fff;
        }

        * {
            box-sizing: border-box;
        }

        position: relative;

        .el-card {
            height: 100%;
        }

        ::-webkit-input-placeholder {
            color: #838383 !important;
        }

        :-ms-input-placeholder {
            color: #838383 !important;
        }

        ::-moz-placeholder {
            color: #838383 !important;
        }

        :-moz-placeholder {
            color: #838383 !important;
        }

        .demo-input-suffix1 {
            width: 400px;
            min-width: 200px;
            z-index: 10;
        }

        .el-input__inner {
            background-color: #f4f4f4 !important;
            border-radius: 26px !important;
        }

        .el-input__suffix {
            font-size: 18px !important;
            margin-right: 10px !important;
            color: #838383 !important;
        }

        .el-tabs__nav-wrap::after {
            position: static !important;
        }

        .el-tabs__nav {
            float: right !important;
        }

        .el-tabs__active-bar {
            background-color: #fff !important;
        }

        .back {
            position: absolute;
            height: 40px;
            line-height: 40px;
            right: 200px;
            font-size: 14px;
            color: #409eff;
            z-index: 100;
            cursor: pointer;
        }

        .el-tabs__content {
            position: static !important;
        }

        .el-tabs__item.is-active {
            color: #303133 !important;
        }

        .el-tabs__item {
            color: #409eff !important;
        }
    }
</style>