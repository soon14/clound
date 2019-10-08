<template>
    <div>
        <van-nav-bar title="特行场所案件信息">
            <van-icon name="arrow-left" slot="left" size="20px" @click="back"/>
            <van-icon name="wap-home" slot="right" size="20px" @click="toIndex"/>
        </van-nav-bar>
        <van-dropdown-menu>
            <van-dropdown-item title="筛选" ref="item">
              <van-field v-model="param.ajmc"
                         clearable
                         label="案件名称"
                         placeholder="请输入案件名称"
              ></van-field>
                <van-field v-model="param.ajbh"
                           clearable
                           label="案件编号"
                           placeholder="请输入案件编号"
                ></van-field>
                <van-button block type="info" @click="onSubmit">确认</van-button>
            </van-dropdown-item>
        </van-dropdown-menu>
        <div class="road">
            <van-pull-refresh v-model="isDownLoading" @refresh="onDownRefresh">
                <van-list
                        v-model="isUpLoading"
                        :finished="upFinished"
                        :immediate-check="false"
                        :offset="10"
                        finished-text="已无更多数据"
                        @load="onLoadList"
                >
            <van-card v-for="item in tableData" @click="viewDetail(item)">
                <div class="cardList" slot="title">
                    <span style="font-weight: bold;" v-text="item.ajmc"></span>
                </div>
                <div class="cardList" slot="desc">
                    <span>{{validatenull(getLabelByVal(ajstateData,item.ajstate))?'暂无数据':getLabelByVal(ajstateData,item.ajstate)}}|</span>
                    <span>{{validatenull(getLabelByVal(ajlxData,item.ajlx))?'暂无数据':getLabelByVal(ajlxData,item.ajlx)}}|</span>
                    <span>{{validatenull(getLabelByVal(areaData,item.faddQx))?'暂无数据':getLabelByVal(areaData,item.faddQx)}}</span>
                </div>
                <div class="cardList" slot="tags">
                    <van-tag type="primary">
                        <span>{{item.ajbh}}</span>
                    </van-tag>&nbsp;
                    <van-tag type="primary">
                        <span>{{validatenull(getLabelByVal(abData,item.ab))?'暂无数据':getLabelByVal(abData,item.ab)}}</span>
                    </van-tag>
                </div>
                <div class="cardList" slot="tags">
                    报警时间：<span>{{validatenull(item.sljjsj)?'暂无数据':item.sljjsj}}</span>
                    接收单位：<span>{{validatenull(item.sljsdw)?'暂无数据':item.sljsdw}}</span>
                </div>
                <div class="cardList" slot="tags">
                    发案地：<span>{{validatenull(item.fadd)?'暂无数据':item.fadd}}</span>
                </div>
            </van-card>
                </van-list>
            </van-pull-refresh>
        </div>
        <div class="vanLoading">
            <van-loading v-if="loading" size="24px" vertical>加载中...</van-loading>
        </div>
        </div>
</template>

<script>
    import {findListPage} from "@/api/portal/archive/DanweiAsj/ajQuery";
    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";
    import {findDictArea} from '@/api/portal/archive/common/query';
    import {validatenull} from "@/util/validate";
    export default {
        name: "index",
        data(){
            return{
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                param:{},
                sources:0,
                finished:false,
                loading:false,
                isDownLoading: false, // 下拉刷新
                isUpLoading: false, // 上拉加载
                upFinished: false, // 上拉加载完毕
                offset: 100, // 滚动条与底部距离小于 offset 时触发load事件
                areaData:[],
                ajstateData:[],
                ajlxData:[],
                abData:[],

                tableData: [], // 请求数据
            }
        },
        created() {
            this.getList(this.page)
            this.getDictData()
        },
        methods:{
            onSubmit(){
                this.getList(this.page) // 加载数据
                this.$refs.item.toggle()
            },

            getLabelByVal(dicData,val){
                if(!validatenull(dicData)&&!validatenull(val)){
                    return getLabel(dicData,val)
                }
            },

            getDictData() {
                getDict('44', 'jzxt').then(response => {
                    this.ajlxData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('56', 'jzxt').then(response => {
                    this.ajstateData = response.data.data || [];
                }).catch((err) => {
                });
                getDict('24', 'jzxt').then(response => {
                    this.abData = response.data.data || [];
                }).catch((err) => {
                });
                findDictArea().then(response => {
                    this.areaData = response.data || [];
                })
            },
            getList(page, params) {
                const _this = this
                _this.loading=true
                findListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, this.param)).then(response => {
                    _this.loading=false
                    this.page.total=response.data.data.records
                    const rows = response.data.data.records
                    if (rows == null || rows.length === 0) {
                        // 加载结束
                        _this.upFinished = true
                        _this.tableData = rows
                        return
                    }
                    if (rows.length < this.page.pageSize) {
                        // 最后一页不足10条的情况
                        _this.upFinished = true
                    }
                    // 处理数据
                    if (_this.page.currentPage === 1) {
                        _this.tableData = rows
                    } else {
                        _this.tableData = _this.tableData.concat(rows)
                    }
                }).catch(error => {
                    _this.loading=false
                    this.$message({
                        showClose: true,
                        message: '获取资源列表异常{' + error + '}',
                        type: 'error'
                    })
                }).finally(() => {
                    _this.isDownLoading = false
                    _this.isUpLoading = false
                    _this.loading=false
                })
            },

            onDownRefresh() {
                this.page.currentPage = 1
                this.upFinished = false // 不写这句会导致你上拉到底过后在下拉刷新将不能触发下拉加载事件
                this.getList(this.page) // 加载数据
            },
            // 上拉加载请求方法
            onLoadList() {
                this.page.currentPage++
                this.getList(this.page)
            },
            back(){
                history.go(-1);
            },
            toIndex(){
                this.$router.replace("/mobile/find");
            },
            viewDetail(row){
                this.$router.push({
                    path: this.$router.$avueRouter.getPath({
                        src: "/mobile/ajQuery/ajDetail"
                    }),
                    query: {
                        ajxx: row
                    }
                });
            },
        }
    }
</script>

<style scoped>

</style>