<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <div v-loading="modelLoading">
                <a href="javascript:history.go(0)"><i v-if="this.listQuery.level!='2'" class="el-icon-d-arrow-left" style="font-size:24px"></i></a>
                <div class="demo-input-suffix"  v-show="showInput">
                    <el-input v-model="searchForm.name" placeholder="请输入模型名称" style="width: 50%"/>
                    <el-button
                            type="primary"
                            class="el-button-s"
                            @click="searchBtn"
                            style=" margin-left: 25px;"
                    >搜 索</el-button>
                </div>
                <template>
                    <el-tabs v-model="activeName" @tab-click="btnClick">
                        <el-tab-pane label="默认分类" name="first">
                            <div v-for="item in modelData">
                                <el-card class="box-card">
                                    <div slot="header" class="clearfix">
                                        <span v-if="item.type=='1'">{{item.name}}</span>
                                    </div>
                                    <div class="el-row text item" span="24" style="margin-left: -10px; margin-right: -10px;">
                                        <div class="el-col el-col-8" v-if="item.type=='1' && item.children.length>0"  v-for="child in item.children"
                                             style="padding-left: 10px; padding-right: 10px;">
                                            <div class="avue-card__item">
                                                <div class="avue-card__body">

                                                    <div v-if="child.type=='2'" @click="dirClick(child)">
                                                        <viewer >
                                                            <img style="height:80px; width:80px;border-radius: 5px;" :id="child.id"
                                                                 :onload="initIcon(child.id)"
                                                            />
                                                        </viewer>
                                                        <div style="padding: 14px;">
                                                            <div>{{child.name}}</div>
                                                            <div>主题：<span :id="'span_'+child.id" v-text="initCount(child.id)"></span> 个</div>
                                                        </div>
                                                    </div>
                                                    <div v-if="child.type=='3'" @click="appClick(child)">
                                                        <div class="avue-card__avatar" >
                                                            <viewer >
                                                                <img style="height:80px; width:80px;border-radius: 5px;" :id="child.id"
                                                                     :onload="initIcon(child.id)"
                                                                />
                                                            </viewer>
                                                        </div>
                                                        <div class="avue-card__detail">
                                                            <div class="avue-card__title">{{child.name}}</div>
                                                            <div class="avue-card__info">{{child.remark}}</div>
                                                            <el-rate
                                                                    v-model="rate"
                                                                    :colors="colors">
                                                            </el-rate>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div v-if="item.type!='1'" class="el-col el-col-8" style="padding-left: 10px; padding-right: 10px;">
                                            <div class="avue-card__item">
                                                <div class="avue-card__body">
                                                    <div v-if="item.type=='2'" @click="dirClick(item)">
                                                        <viewer >
                                                            <img style="height:80px; width:80px;border-radius: 5px;" :id="item.id"
                                                                 :onload="initIcon(item.id)"
                                                            />
                                                        </viewer>
                                                        <div style="padding: 14px;">
                                                            <div>{{item.name}}</div>
                                                            <div>主题：<span :id="'span_'+item.id" v-text="initCount(item.id)"></span> 个</div>
                                                        </div>
                                                    </div>

                                                    <div v-if="item.type=='3'" @click="appClick(item)">
                                                        <div class="avue-card__avatar" >
                                                            <viewer >
                                                                <img style="height:80px; width:80px;border-radius: 5px;" :id="item.id"
                                                                     :onload="initIcon(item.id)"
                                                                />
                                                            </viewer>
                                                        </div>
                                                        <div class="avue-card__detail">
                                                            <div class="avue-card__title">{{item.name}}</div>
                                                            <div class="avue-card__info">{{item.remark}}</div>
                                                            <el-rate
                                                                    v-model="rate"
                                                                    :colors="colors">
                                                            </el-rate>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </el-card>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="展开全部" name="second">
                            <div>
                                <el-card class="box-card">
                                    <div class="el-row text item" span="24" style="margin-left: -10px; margin-right: -10px;">
                                        <div class="el-col el-col-8"  v-for="item in appData"  style="padding-left: 10px; padding-right: 10px;">
                                            <div class="avue-card__item">
                                                <div class="avue-card__body" >
                                                    <div @click="appClick(item)">
                                                        <div class="avue-card__avatar" >
                                                            <viewer >
                                                                <img style="height:80px; width:80px;border-radius: 5px;" :id="item.id"
                                                                     :onload="initIcon(item.id)"
                                                                />
                                                            </viewer>
                                                        </div>
                                                        <div class="avue-card__detail">
                                                            <div class="avue-card__title">{{item.name}}</div>
                                                            <div class="avue-card__info">{{item.remark}}</div>
                                                            <el-rate
                                                                    v-model="rate"
                                                                    :colors="colors">
                                                            </el-rate>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </el-card>
                            </div>
                            <a v-if="morePage" href="javascript:void(0)" @click="moreLoad()"
                               style="color: #409EFF;cursor: pointer;text-decoration: none;">
                                加载更多（剩余：{{page.total-(page.pageSize*page.currentPage)}} 条）</a>
                        </el-tab-pane>
                    </el-tabs>
                </template>
            </div>

        </basic-container>

        <el-dialog :title="modelTitle" width="80%" :visible.sync="modelDialogFlag" :append-to-body="true">
            <app-dialog v-bind:dialogParam="dialogParam"></app-dialog>
        </el-dialog>

    </div>
</template>
<script>
    import {getModelByLevel,getIconList,countModel,getApp} from '@/api/portal/judged/model/modelMenu'
    import {routerJump} from '@/api/portal/judged/model/routerJump'
    import appDialog from "./appDialog";


    export default {
        name: "modelShow",
        components: {appDialog},
        data() {
            return {
                morePage: false,
                activeName: 'first',
                showInput:false,
                appData:[],
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 5, // 每页显示多少条,
                },
                searchForm: {
                    name: undefined
                },

                modelTitle:'',//弹窗title
                modelDialogFlag:false,//弹窗是否展示
                dialogParam:{
                    id:'',
                    links:'',
                },

                modelLoading:false,
                iconMap: new Map(),
                rate:5,
                colors: ['#99A9BF', '#F7BA2A', '#FF9900'],
                tableLoading: false,
                modelData:[],
                listQuery:{
                    level:'',
                    id:'',
                },

            }
        },
        created() {
            this.tableData = []
            this.appData = []
            this.page.total = 0
            this.page.currentPage = 1
            this.page.pageSize = 5
            this.listQuery.level=2
            this.getList()
        },
        computed: {},
        methods: {
            getList() {
                this.iconMap.clear()
                this.modelData=[]
                this.modelLoading=true
                getModelByLevel(this.listQuery).then(response => {
                    this.modelData = response.data.data
                    this.modelLoading=false
                })
            },
            initIcon(id) {
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
            initCount(id){
                countModel(id).then(response => {
                    let span=document.getElementById("span_"+id);
                    if(null!=span&&"undefined"!=typeof (span))
                        span.innerText=(response.data)
                })
            },
            dirClick(data){
                let count=document.getElementById("span_"+data.id).innerText;
                if (count != 0){
                    this.listQuery.level=this.listQuery.level+2
                    this.listQuery.id=data.id
                    this.getList()
                }else{
                    this.$message.error('该目录下无主题可查看')
                }
            },
            appClick(data){
                if(data.openType==='2'){
                    let httpStr = data.links.substr(0,5).toLowerCase()
                    if (httpStr =='http:'||httpStr=='https'){
                        let myHttpStr = httpStr+data.links.substring(5,data.links.length)
                        data.links = myHttpStr
                        window.open(data.links, data.name, 'height=600, width=1000,top=50, left=150')
                    }else{
                        this.dialogParam.id=data.id
                        this.dialogParam.links = data.links
                        this.modelTitle=data.name
                        this.modelDialogFlag=true
                    }
                }else{
                    routerJump(data)
                }
            },
            fetchApp(page){
                this.iconMap.clear()
                this.modelLoading=true
                getApp(Object.assign({
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
            btnClick(tab, event) {
                if(tab.name!="first"){
                    this.appData=[]
                    this.showInput=true;
                    this.fetchApp(this.page)
                }else{
                    this.showInput=false;
                    this.getList()
                }
            },
            moreLoad() {
                this.page.currentPage = this.page.currentPage + 1
                this.fetchApp(this.page)
                if (this.page.total > this.page.pageSize * this.page.currentPage) {
                    this.morePage = true
                } else {
                    this.morePage = false
                }
            },
            searchBtn(){
                this.page.total = 0
                this.page.currentPage = 1
                this.page.pageSize = 5
                this.appData=[]
                this.fetchApp(this.page)
            }

        },

    }
</script>
<style>
    .image {
        height: 30%;
        width: 100%;
        display: block;
    }
</style>