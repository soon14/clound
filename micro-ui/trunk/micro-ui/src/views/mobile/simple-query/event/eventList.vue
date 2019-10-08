<template>
    <div class="place">
        <div>
            <van-nav-bar title="事件列表">
                <van-icon name="arrow-left" slot="left" size="20px" @click="back"/>
                <van-icon name="wap-home" slot="right" size="20px" @click="toIndex"/>
            </van-nav-bar>

            <van-dropdown-menu>
                <van-dropdown-item v-model="value"
                                   :options="tabsOption"
                                   @change="handleChange"
                                   :disabled="tabDisabled"
                                   @open="tabOpen"
                                   @close="tabClose"/>
                <van-dropdown-item title="筛选" ref="item" :disabled="filterDisabled" @open="filterOpen"
                                   @close="filterClose">
                    <van-field
                            v-model="param.dwmc"
                            clearable
                            label="单位名称"
                            placeholder="请输入单位名称"
                    />
                    <van-field
                            v-if="showEvent"
                            v-model="param.sjmc"
                            clearable
                            label="事件名称"
                            placeholder="请输入事件名称"
                    />
                    <van-field
                            v-if="showEvent"
                            v-model="param.sjbh"
                            clearable
                            label="事件编号"
                            placeholder="请输入事件编号"
                    />
                    <van-field
                            v-if="showEvent"
                            :value="sjlx"
                            readonly
                            clickable
                            label="事件类型"
                            placeholder="请选择事件类型"
                            @click="showSjlx=true"
                    />
                    <van-popup v-model="showSjlx" position="bottom">
                        <van-picker
                                show-toolbar
                                :columns="sjlxColumns"
                                @cancel="showSjlx=false"
                                @confirm="onConfirmSjlx"
                        />
                    </van-popup>
                    <van-field
                            v-if="!showEvent"
                            v-model="param.sjmtmc"
                            clearable
                            label="事件苗头名称"
                            placeholder="请输入事件苗头名称"
                    />
                    <van-field
                            v-if="!showEvent"
                            v-model="param.sjmtbh"
                            clearable
                            label="事件苗头编号"
                            placeholder="请输入事件苗头编号"
                    />
                    <van-field
                            v-if="!showEvent"
                            :value="mtlx"
                            readonly
                            clickable
                            label="事件苗头类型"
                            placeholder="请选择事件苗头类型"
                            @click="showMtlx=true"
                    />
                    <van-popup v-model="showMtlx" position="bottom">
                        <van-picker
                                show-toolbar
                                :columns="mtlxColumns"
                                @cancel="showMtlx=false"
                                @confirm="onConfirmMtlx"
                        />
                    </van-popup>
                    <van-field
                            :value="yy"
                            readonly
                            clickable
                            label="发生诱因"
                            placeholder="请选择发生诱因"
                            @click="showYy=true"
                    />
                    <van-popup v-model="showYy" position="bottom">
                        <van-picker
                                show-toolbar
                                :columns="yyColumns"
                                @cancel="showYy=false"
                                @confirm="onConfirmYy"
                        />
                    </van-popup>

                    <div class="timeLabel">
                        <span>发生时间</span>
                        <el-input
                                prefix-icon="el-icon-date"
                                placeholder="开始时间"
                                v-model="param.startTime"
                                @focus="showStartDate=true"
                        >
                        </el-input>
                        <span>至</span>
                        <el-input
                                prefix-icon="el-icon-date"
                                placeholder="结束时间"
                                v-model="param.endTime"
                                @focus="showEndDate=true">
                        </el-input>
                    </div>
                    <van-popup v-model="showStartDate" position="bottom">
                        <van-datetime-picker
                                type="date"
                                :min-date="minDate"
                                :max-date="maxDate"
                                v-model="startTime"
                                @cancel="showStartDate=false"
                                @confirm="onConfirmSDate"
                        />
                    </van-popup>
                    <van-popup v-model="showEndDate" position="bottom">
                        <van-datetime-picker
                                type="date"
                                :min-date="minDate"
                                :max-date="maxDate"
                                v-model="endTime"
                                @cancel="showEndDate=false"
                                @confirm="onConfirmEDate"
                        />
                    </van-popup>

                    <van-button block type="info" @click="onSubmit">确认</van-button>
                </van-dropdown-item>
            </van-dropdown-menu>

            <van-pull-refresh v-model="isDownLoading" @refresh="onDownRefresh">
                <van-list
                        v-model="isUpLoading"
                        :finished="upFinished"
                        :immediate-check="false"
                        :offset="10"
                        finished-text="已无更多数据"
                        @load="onLoadList"
                >
                    <van-card v-for="item in tableData" @click="viewEventDetail(item)" v-if="showEvent">
                        <div class="cardList" slot="title">
                            <span style="font-weight: bold;" v-text="item.sjmc"></span>
                        </div>
                        <div class="cardList" slot="desc">
                            <span>{{item.sjbh}}</span>
                        </div>
                        <div class="cardList" slot="tags">
                            <van-tag type="primary" v-if="!validatenull(item.sjlx)">
                                <span>{{getLabelByVal(sjlxData,item.sjlx)}}</span>
                            </van-tag>
                            <van-tag class="tagPadding" type="primary" v-if="!validatenull(item.yy)">
                                <span>{{getLabelByVal(yyData,item.yy)}}</span>
                            </van-tag>
                            <van-tag class="tagPadding" type="primary" v-if="!validatenull(item.fssjcz)">
                                <span>{{item.fssjcz}}</span>
                            </van-tag>
                        </div>
                        <div class="cardList" slot="tags">
                        <span>
                            <img src="/img/mobile/name.png">
                            <font> {{item.dwmc}}</font></span>
                        </div>
                        <div class="cardList" slot="tags">
                        <span>
                            <img src="/img/mobile/location.png">
                            {{validatenull(item.address)?'暂无数据':item.address}}
                        </span>
                        </div>
                    </van-card>

                    <van-card v-for="item in tableData" @click="viewEventDetail(item)" v-if="!showEvent">
                        <div class="cardList" slot="title">
                            <span style="font-weight: bold;" v-text="item.sjmtmc"></span>
                        </div>
                        <div class="cardList" slot="desc">
                            <span>{{item.sjmtbh}}</span>
                        </div>
                        <div class="cardList" slot="tags">
                            <van-tag type="primary" v-if="!validatenull(item.mtlx)">
                                <span>{{getLabelByVal(mtlxData,item.mtlx)}}</span>
                            </van-tag>
                            <van-tag class="tagPadding" type="primary" v-if="!validatenull(item.yy)">
                                <span>{{getLabelByVal(yyData,item.yy)}}</span>
                            </van-tag>
                            <van-tag class="tagPadding" type="primary" v-if="!validatenull(item.fxsj)">
                                <span>{{item.fxsj}}</span>
                            </van-tag>
                        </div>
                        <div class="cardList" slot="tags">
                        <span>
                            <img src="/img/mobile/name.png">
                            <font> {{item.dwmc}}</font></span>
                        </div>
                        <div class="cardList" slot="tags">
                        <span>
                            <img src="/img/mobile/location.png">
                            {{validatenull(item.address)?'暂无数据':item.address}}
                        </span>
                        </div>
                    </van-card>

                </van-list>
            </van-pull-refresh>

        </div>

        <div class="vanLoading">
            <van-loading type="spinner" color="#1989fa" v-if="listLoading" vertical/>
        </div>

        <el-backtop target="#app"></el-backtop>
    </div>

</template>

<script>

    import {mapGetters} from "vuex";
    import {validatenull} from "@/util/validate";
    import {remote} from "@/api/admin/dict";
    import {getDict, getLabel} from "@/api/portal/archive/dict/dict";
    import {findEventPage, findEventSignsPage} from '@/api/portal/archive/event/event'
    import '@/styles/mobile/simpleQuery/style.scss?v=110'
    import Picker from "element-ui/packages/date-picker/src/picker";

    export default {
        name: 'eventTab',
        components: {Picker},
        data() {
            return {
                tabDisabled: false,
                filterDisabled: false,
                searchForm: {
                    dwmc: '',
                    startTime: '',//查询开始时间
                    endTime: '',//查询结束时间
                    sjmc: '',//事件名称
                    sjbh: '',//事件编号
                    sjlx: '',//事件类型
                    sjzt: '',//事件状态
                    sjmtmc: '',//事件苗头名称
                    sjmtbh: '',//事件苗头编号
                    mtlx: '',//事件苗头类型
                    reservation06: '',//事件苗头状态
                    yy: '',//事件、事件苗头诱因
                },
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                tabsOption: [
                    {text: '事件', value: '0'},
                    {text: '事件苗头', value: '1'}
                ],
                showEvent: false,
                value: '0',//所选来源系统
                param: {    //查询条件
                    dwmc: '',// 单位名称
                    startTime: '',//查询开始时间
                    endTime: '',//查询结束时间
                    sjmc: '',//事件名称
                    sjbh: '',//事件编号
                    sjlx: '',//事件类型
                    sjzt: '',//事件状态
                    yy: '',//事件、事件苗头诱因
                    sjmtmc: '',//事件苗头名称
                    sjmtbh: '',//事件苗头编号
                    mtlx: '',//事件苗头类型
                    reservation06: '',//事件苗头状态
                },
                tableData: [],
                isDownLoading: false, // 下拉刷新
                isUpLoading: false, // 上拉加载
                upFinished: false, // 上拉加载完毕
                offset: 100, // 滚动条与底部距离小于 offset 时触发load事件
                listLoading: false,

                yyData: [],//事件、事件苗头诱因字典项
                yy: '',//查询条件-事件、事件苗头诱因
                showYy: false,//查询条件 是否展示事事件、事件苗头诱因弹框
                yyColumns: [],//事事件、事件苗头诱因查询弹框选择项

                sjlxData: [],//事件类型字典项
                sjlx: '',//查询条件-事件类型
                showSjlx: false,//查询条件 是否展示事件类型弹框
                sjlxColumns: [],//事件类型查询弹框选择项

                mtlxData: [],//事件苗头类型字典项
                mtlx: '',//查询条件-事件苗头类型
                showMtlx: false,//查询条件 是否展示事件苗头类型弹框
                mtlxColumns: [],//事件苗头类型查询弹框选择项

                minDate: new Date(1990,1,1),
                maxDate: new Date(),

                startTime: new Date(),
                endTime: new Date(),
                showStartDate: false,
                showEndDate: false,

            }
        },

        computed: {
            ...mapGetters(["permissions"])
        },
        activated(){
            if(!this.$route.meta.isBack){
            }else{
                this.getDictData();
                this.getList(this.page);
            }
            this.$route.meta.isBack=false;
        },
        beforeRouteEnter(to, from, next){
            if(
                from.path=='/mobile/simpleQuery/eventDetail'){
                to.meta.isBack=true;
            }
            to.meta.isBack=true;
            next();
        },
        methods: {
            back() {
                history.go(-1);
            },
            toIndex() {
                this.$router.replace("/mobile/find");
            },
            // 下拉加载请求方法
            onLoadList() {
                this.page.currentPage++
                this.getList(this.page)
            },
            //上拉刷新
            onDownRefresh() {
                this.page.currentPage = 1
                this.upFinished = false
                this.getList(this.page)
            },
            getList(page, params) {
                const _this = this
                if (_this.tableData.length == 0) {
                    _this.listLoading = true
                }
                if (this.value == '0') {
                    this.showEvent = true
                    findEventPage(Object.assign({
                        current: page.currentPage,
                        size: page.pageSize
                    }, this.searchForm)).then(response => {
                        _this.listLoading = false
                        this.page.total = response.data.data.total
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
                } else {
                    this.showEvent = false
                    findEventSignsPage(Object.assign({
                        current: page.currentPage,
                        size: page.pageSize
                    }, this.searchForm)).then(response => {
                        _this.listLoading = false
                        this.page.total = response.data.data.total
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
                }

            },

            //打开、关闭菜单栏时 下拉菜单禁用事件
            tabOpen() {
                this.filterDisabled = true
            },
            tabClose() {
                this.filterDisabled = false
            },
            filterOpen() {
                this.tabDisabled = true
            },
            filterClose() {
                this.tabDisabled = false
            },

            //业务系统切换
            handleChange() {
                this.tableData = []
                this.searchForm.sjmc = '';
                this.searchForm.sjbh = '';
                this.searchForm.sjlx = '';
                this.searchForm.sjzt = '';
                this.searchForm.sjmtmc = '';
                this.searchForm.sjmtbh = '';
                this.searchForm.mtlx = '';
                this.searchForm.reservation06 = '';
                this.page.total = 0;
                this.page.currentPage = 1;
                this.getList(this.page)
            },

            //获取字典
            getDictData() {
                getDict('sj06', 'jzxt').then(response => {
                    this.yyData = response.data.data || [];
                    this.yyColumns = this.formatColumns(this.yyData)
                }).catch((err) => {
                });
                getDict('sj09', 'jzxt').then(response => {
                    this.sjlxData = response.data.data || [];
                    this.sjlxColumns = this.formatColumns(this.sjlxData)
                }).catch((err) => {
                });
                getDict('sj03', 'jzxt').then(response => {
                    this.mtlxData = response.data.data || [];
                    this.mtlxColumns = this.formatColumns(this.mtlxData)
                }).catch((err) => {
                });
            },
            //格式化弹框查询条件展示
            formatColumns(dicData) {
                let arr = [];
                let empty = {text: '不限', value: ''}
                arr.push(empty);
                for (let i = 0; i < dicData.length; i++) {
                    let tmp = dicData[i];
                    let json = {text: tmp.label, value: tmp.value};
                    arr.push(json);
                }
                return arr
            },

            onConfirmSjlx(data) {
                this.sjlx = data.text
                this.param.sjlx = data.value
                this.showSjlx = false
            },
            onConfirmMtlx(data) {
                this.mtlx = data.text
                this.param.mtlx = data.value
                this.showMtlx = false
            },
            onConfirmYy(data) {
                this.yy = data.text
                this.param.yy = data.value
                this.showYy = false
            },
            dateFormat(date){
                let year = date.getFullYear();
                let month = date.getMonth()+1;
                if(month<=9){
                    month = '0'+month;
                }
                let day = date.getDate();
                if(day<=9){
                    day = '0'+day;
                }
                let formatDate = year+'-'+month+'-'+day
                return formatDate;
            },
            onConfirmSDate(data) {
                this.param.startTime = this.dateFormat(data)
                this.showStartDate = false
            },
            onConfirmEDate(data) {
                this.param.endTime = this.dateFormat(data)
                this.showEndDate = false
            },
            //提交查询参数
            onSubmit() {
                if(!validatenull(this.param.startTime) && !validatenull(this.param.endTime)){
                    if(this.param.startTime>this.param.endTime){
                        this.$toast('开始时间不能大于结束时间');
                        return false;
                    }
                }
                this.searchForm.dwmc = this.param.dwmc
                this.searchForm.yy = this.param.yy
                this.searchForm.startTime = this.param.startTime
                this.searchForm.endTime = this.param.endTime
                if (this.value == '0') {
                    this.searchForm.sjmc = this.param.sjmc
                    this.searchForm.sjbh = this.param.sjbh
                    this.searchForm.sjlx = this.param.sjlx
                    this.searchForm.sjzt = this.param.sjzt
                } else {
                    this.searchForm.sjmtmc = this.param.sjmtmc
                    this.searchForm.sjmtbh = this.param.sjmtbh
                    this.searchForm.mtlx = this.param.mtlx
                    this.searchForm.reservation06 = this.param.reservation06
                }
                this.$refs.item.toggle()
                this.page.currentPage = 1
                this.tableData = []
                this.getList(this.page)
            },

            getLabelByVal(dicData, val) {
                if (!validatenull(dicData) && !validatenull(val)) {
                    return getLabel(dicData, val)
                }
            },
            viewEventDetail(data) {
                this.$router.push({
                    path: this.$router.$avueRouter.getPath({
                        src: "/mobile/simpleQuery/eventDetail"
                    }),
                    query: {
                        id: data.systemid, source: this.value
                    }
                });
            }

        },

    }
</script>