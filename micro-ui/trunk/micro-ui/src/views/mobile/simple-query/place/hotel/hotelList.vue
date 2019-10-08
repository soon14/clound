<template >
    <div class="place">
        <div>
            <van-pull-refresh v-model="isDownLoading" @refresh="onDownRefresh">
                <van-list
                        v-model="isUpLoading"
                        :finished="upFinished"
                        :immediate-check="false"
                        :offset="10"
                        finished-text="已无更多数据"
                        @load="onLoadList"
                  >
                    <van-card v-for="item in tableData" @click="viewPlaceDetail(item)">
                        <div class="cardList" slot="title">
                            <span style="font-weight: bold;" v-text="item.hname"></span>
                        </div>
                        <div class="cardList" slot="desc">
                            <span>{{validatenull(getLabelByVal(placeTypeData,item.hotelKind))?'暂无数据':getLabelByVal(placeTypeData,item.hotelKind)}}|</span>
                            <span>{{validatenull(getLabelByVal(placeStateData,item.state))?'暂无数据':getLabelByVal(placeStateData,item.state)}}|</span>
                            <span>{{validatenull(getLabelByVal(areaData,item.cityCode))?'暂无数据':getLabelByVal(areaData,item.cityCode)}}</span>
                        </div>
                        <div class="cardList" slot="tags">
                           <van-tag type="primary">
                              <span>{{validatenull(getLabelByVal(pointTypeData,item.pointType))?'暂无数据':getLabelByVal(pointTypeData,item.pointType)}}</span>
                           </van-tag>
                            <van-tag class="tagPadding" type="primary">
                                <span>{{validatenull(item.pointScore)?'暂无数据':item.pointScore+'分'}}</span>
                            </van-tag>
                        </div>
                        <div class="cardList" slot="tags">
                           <span><img src="/img/mobile/location.png">
                               {{validatenull(item.haddress)?'暂无数据':item.haddress}}</span>
                        </div>

                    </van-card>
                </van-list>
            </van-pull-refresh>
        </div>

        <div class="vanLoading">
            <van-loading type="spinner" color="#1989fa" v-if="listLoading" vertical />
        </div>

    </div>
</template>
<script>

    import {findListPage} from "@/api/portal/archive/common/unitQuery";
    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";
    import {remote}  from  "@/api/admin/dict";
    import {findDictArea} from '@/api/portal/archive/common/query';
    import {validatenull} from "@/util/validate";
    import '@/styles/mobile/simpleQuery/style.scss?v=110'

    export default {
        props:["searchForm"],
        data() {
            return {
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                placeTypeData:[],//行业类型
                placeStateData:[],//场所状态
                areaData:[],//行政区划
                pointTypeData:[],//积分等级
                tableData: [], // 请求数据
                isDownLoading: false, // 下拉刷新
                isUpLoading: false, // 上拉加载
                upFinished: false, // 上拉加载完毕
                offset: 100, // 滚动条与底部距离小于 offset 时触发load事件
                listLoading: false,
            }
        },
        created() {
            this.getDictData();
            this.getList(this.page)
        },
        watch: {
            searchForm: {
                handler() {
                    this.page.currentPage = 1
                    this.tableData=[]
                    this.getList(this.page)
                },
                deep: true
            },
        },
        methods: {
            //获取字典
            getDictData(){
                remote('jfdj').then(response => {
                    this.pointTypeData = response.data.data || []
                })
                getDict('0209','ly').then(response => {
                    this.placeStateData = response.data.data||[];
                }).catch((err) => {});
                getDict('LGLB','ly').then(response => {
                    this.placeTypeData = response.data.data||[];
                }).catch((err) => {});
                findDictArea().then(response => {
                    this.areaData = response.data||[];
                })
            },
            getLabelByVal(dicData,val){
                if(!validatenull(dicData)&&!validatenull(val)){
                    return getLabel(dicData,val)
                }
            },

            getList(page, params) {
                const _this = this
                if (_this.tableData.length == 0) {
                    _this.listLoading = true
                }
                findListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, this.searchForm)).then(response => {
                        this.page.total=response.data.data.total
                        _this.listLoading = false
                        const rows = response.data.data.records
                        if (rows == null || rows.length === 0) {
                            // 加载结束
                            _this.upFinished = true
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
                    this.$message({
                        showClose: true,
                        message: '获取资源列表异常{' + error + '}',
                        type: 'error'
                    })
                }).finally(() => {
                    _this.isDownLoading = false
                    _this.isUpLoading = false
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

            viewPlaceDetail(row){
                this.$router.push({
                    path: this.$router.$avueRouter.getPath({
                        src: "/mobile/simpleQuery/hotelDetail"
                    }),
                    query: {
                        id: row.hnoHotel
                    }
                });
            },

        }
    }
</script>