<template>
    <div>
        <div>
            <van-nav-bar title="场所列表">
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
                <van-dropdown-item title="筛选" ref="item" :disabled="filterDisabled" @open="filterOpen" @close="filterClose">
                    <van-field
                            v-model="param.dwmc"
                            clearable
                            label="场所名称"
                            placeholder="请输入场所名称"
                    />

                    <van-field
                            readonly
                            clickable
                            label="积分等级"
                            :value="pointType"
                            placeholder="请选择积分等级"
                            @click="showPicker=true"
                    />
                    <van-popup v-model="showPicker" position="bottom">
                        <van-picker
                                show-toolbar
                                :columns="djColumns"
                                @cancel="showPicker=false"
                                @confirm="onConfirmDj"
                        />
                    </van-popup>

                    <van-field
                            readonly
                            clickable
                            label="所属区域"
                            :value="orgCode"
                            placeholder="请选择所属区域"
                            @click="showOrgCode"
                    />
                    <van-popup v-model="showArea" position="bottom">
                        <van-area :area-list="areaData" @cancel="showArea=false" @confirm="onConfirmArea"/>
                    </van-popup>
                    <van-popup v-model="showCity" position="bottom">
                        <van-picker
                                show-toolbar
                                :columns="cityColumns"
                                @cancel="showCity=false"
                                @confirm="onConfirmArea"
                        />
                    </van-popup>

                    <van-field
                            readonly
                            clickable
                            label="行业类型"
                            :value="placeType"
                            placeholder="请选择行业类型"
                            @click="showPlaceType=true"
                    />
                    <van-popup v-model="showPlaceType" position="bottom">
                        <van-picker
                                show-toolbar
                                :columns="placeTypeColumns"
                                @cancel="showPlaceType=false"
                                @confirm="onConfirmPlaceType"
                        />
                    </van-popup>

                    <van-field
                            readonly
                            clickable
                            label="企业状态"
                            :value="placeState"
                            placeholder="请选择企业状态"
                            @click="showPlaceState=true"
                    />
                    <van-popup v-model="showPlaceState" position="bottom">
                        <van-picker
                                show-toolbar
                                :columns="placeStateColumns"
                                @cancel="showPlaceState=false"
                                @confirm="onConfirmPlaceState"
                        />
                    </van-popup>

                    <van-button block type="info" @click="onSubmit">确认</van-button>
                </van-dropdown-item>
            </van-dropdown-menu>

            <div v-show="showType" :is="type.prop" v-bind:search-form="searchForm">
            </div>
        </div>
        <el-backtop target="#app"></el-backtop>
    </div>

</template>

<script>

    import {countAll, findTab} from "@/api/portal/archive/common/unitQuery";
    import gxdw from "./jzxt/jzList";
    import jxyxt from "./mechanical/mechanicalList"
    import csba from "./ylcs/ylcsList";
    import hotel from "./hotel/hotelList";
    import kyzy from "./kyzy/kyzyList";
    import {mapGetters} from "vuex";
    import {validatenull} from "@/util/validate";
    import {remote} from "@/api/admin/dict";
    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";
    import {fetchArea} from '@/api/portal/archive/common/query';
    import {ylcsDict} from "@/const/crud/portal/archive/ylcs/ylcsDict";

    const component = {
        "gxdw": gxdw,
        "jxyxt": jxyxt,
        "csba": csba,
        "hotel": hotel,
        "kyzy": kyzy
    }
    export default {
        name: 'queryTab',
        components: component,
        data() {
            return {
                tabDisabled:false,
                filterDisabled:false,
                type: {},
                showType: false,
                tabLoading: false,
                searchForm: {
                    dwmc: '',
                    placeType: undefined,
                    placeState:undefined,
                    pointType: undefined,
                    orgCode: undefined,
                    menuId: undefined
                },
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                tabsOption: [],
                value: '',//所选来源系统
                pointType: '',//所选等级 前端展示
                param: {    //查询条件
                    dwmc: '',// 单位名称
                    pointType: '',//所选等级代码
                    orgCode: '',//所属区域代码
                    placeType:'',//行业类型代码
                    placeState:'',//企业状态代码
                },
                showPicker: false,//是否展示等级选择器
                djColumns: [], //等级项
                showArea: false,//展示地域（省、市）选择项 弹框
                showCity: false,//展示区域（区）选择项 弹框
                cityColumns: [],//所属区域选择项
                orgCode: '',
                areaData: {
                    province_list: {},
                    city_list: {},
                    county_list: {}
                },
                placeType:'',//行业类型
                showPlaceType:false,//是否展示行业类型弹框
                placeTypeColumns:[],//行业类型数据项
                ylPlaceTypeData:[],//娱乐场所行业类型字典
                lyPlaceTypeData:[],//旅业行业类型字典
                jxPlaceTypeData:[],//机修业行业类型字典
                kyzPlaceTypeData:[],//刻印章业行业类型字典
                jzPlaceTypeData:[],//警综行业类型字典
                placeState:'',//企业状态
                showPlaceState:false,//是否展示企业状态弹框
                placeStateColumns:[],//企业状态数据项
                lyPlaceStateData:[],//旅业企业状态字典
                jxPlaceStateData:[],//机修业企业状态字典
                kyzPlaceStateData:[],//刻印章业企业状态字典
                jzPlaceStateData:[],//警综企业状态字典,
            }
        },

        computed: {
            ...mapGetters(["permissions"])
        },
        activated(){
            if(!this.$route.meta.isBack){
            }else{
                this.getDictData();
                this.tabList();
            }
            this.$route.meta.isBack=false;
        },
        beforeRouteEnter(to, from, next){
            if(
                from.path=='/mobile/simpleQuery/ylcsDetail'){
                    to.meta.isBack=true;
                }
                next();
        },
        methods: {
            back() {
                history.go(-1);
            },
            toIndex() {
                this.$router.replace("/mobile/find");
            },
            tabList() {
                findTab().then(response => {
                    let datas = response.data.data
                    let arr = [];
                    for (let i = 0; i < datas.length; i++) {
                        let tmp = datas[i];
                        if (this.isComponent(tmp.prop) == true) {
                            let json = {
                                text: tmp.sourceName,
                                value: tmp.id,
                                prop: tmp.prop,
                                beanName: tmp.beanName,
                                dataScope: tmp.dataScope
                            };
                            arr.push(json);
                            if (i == 0) {
                                this.value = tmp.id;
                            }
                        }
                    }
                    this.tabsOption = arr;
                    this.handleChange(this.tabsOption[0])
                })
            },

            //打开、关闭菜单栏时 下拉菜单禁用事件
            tabOpen(){this.filterDisabled=true},
            tabClose(){this.filterDisabled=false},
            filterOpen(){ this.tabDisabled=true},
            filterClose(){this.tabDisabled=false},

            //业务系统切换
            handleChange(val) {
                if (!validatenull(val.prop)) {
                    this.showType = true
                    this.type = val
                    this.searchForm.menuId = this.type.value;
                } else {
                    let tab = {}
                    for (let i = 0; i < this.tabsOption.length; i++) {
                        let data = this.tabsOption[i]
                        if (data.value === val) {
                            tab = data
                            break;
                        }
                    }
                    this.showType = true
                    this.type = tab
                    this.searchForm.menuId = this.type.value;
                }
                //因各业务系统行业类型和企业状态字典不一致，故业务系统切换时，清空行业类型和企业状态值
                this.placeType=''
                this.searchForm.placeType=''
                this.placeState=''
                this.searchForm.placeState=''
                this.showChoose()
            },

            isComponent(str) {
                for (let key in component) {
                    if (key === str || key == str) {
                        return true
                    }
                }
                return false
            },

            //获取字典
            getDictData() {

                //行政区划
                fetchArea().then(response => {
                    this.cityColumns = []
                    this.areaData.province_list = {}
                    this.areaData.city_list = {}
                    this.areaData.county_list = {}
                    let data = response.data.data || [];
                    if (data.length > 0) {
                        switch (data[0].type) {
                            case '1':
                                this.areaData.province_list[data[0].code] = data[0].name
                                if (!validatenull(data[0].children)) {
                                    this.areaData.city_list[data[0].code] = '不限'
                                    let ds = data[0].children
                                    for (let i = 0; i < ds.length; i++) {
                                        let tmpDs = ds[i]
                                        this.areaData.city_list[tmpDs.code] = tmpDs.name
                                        if (!validatenull(tmpDs.children)) {
                                            this.areaData.county_list[tmpDs.code] = '不限'
                                            let qh = tmpDs.children
                                            for (let j = 0; j < qh.length; j++) {
                                                let tmpQh = qh[j]
                                                this.areaData.county_list[tmpQh.code] = tmpQh.name
                                            }
                                        }
                                    }
                                }
                                break;
                            case '2':
                                this.areaData.province_list = {440000: '广东省'}
                                for (let i = 0; i < data.length; i++) {
                                    let tmp = data[i];
                                    this.areaData.city_list[tmp.code] = tmp.name
                                    if (!validatenull(tmp.children)) {
                                        this.areaData.county_list[tmp.code] = '不限'
                                        let child = tmp.children
                                        for (let j = 0; j < child.length; j++) {
                                            let tmpChild = child[j]
                                            this.areaData.county_list[tmpChild.code] = tmpChild.name
                                        }
                                    }
                                }
                                break;
                            case '3':
                                let arr = [];
                                for (let i = 0; i < data.length; i++) {
                                    let tmp = data[i];
                                    let json = {text: tmp.fullName, value: tmp.code};
                                    arr.push(json);
                                }
                                this.cityColumns = arr;
                                break;
                            default:
                                break;
                        }
                    }
                });

                //娱乐场所-行业类型
                getDict('业务类型','yl').then(response => {
                    this.ylPlaceTypeData = response.data.data||[];
                }).catch((err) => {});
                //旅业-行业类型
                getDict('LGLB','ly').then(response => {
                    this.lyPlaceTypeData = response.data.data||[];
                }).catch((err) => {});
                //机修业-行业类型
                getDict('qylb','jx').then(response => {
                    this.jxPlaceTypeData = response.data.data||[];
                }).catch((err) => {});
                //刻印章业-行业类型
                remote('kyz_dwlx').then(response => {
                    this.kyzPlaceTypeData = response.data.data || []
                })
                //警综-行业类型
                getDict('za_024','jzxt').then(response => {
                    this.jzPlaceTypeData = response.data.data||[];
                }).catch((err) => {});
                //旅业-企业状态
                getDict('0209','ly').then(response => {
                    this.lyPlaceStateData = response.data.data||[];
                }).catch((err) => {});
                //机修-企业状态
                getDict('qyzt','jx').then(response => {
                    this.jxPlaceStateData = response.data.data||[];
                }).catch((err) => {});
                //刻印章-企业状态
                getDict('enterprise_state','kyz').then(response => {
                    this.kyzPlaceStateData = response.data.data||[];
                }).catch((err) => {});
                //警综-企业状态
                getDict('za_003', 'jzxt').then(response => {
                    this.jzPlaceStateData = response.data.data || [];
                }).catch((err) => {
                });

                //积分等级
                remote('jfdj').then(response => {
                    let data = response.data.data || []
                    let arr = [];
                    let empty = {text: '不限', value: ''}
                    arr.push(empty);
                    for (let i = 0; i < data.length; i++) {
                        let tmp = data[i];
                        let json = {text: tmp.label, value: tmp.value};
                        arr.push(json);
                    }
                    this.djColumns = arr;
                })

                this.showChoose()
            },

            //判断展示省市还是区域弹框
            showOrgCode() {
                if (this.cityColumns.length != 0) {
                    this.showCity = true
                } else if (!validatenull(this.areaData.county_list) && !validatenull(this.areaData.city_list)) {
                    this.showArea = true
                } else {
                    this.$toast("您当前无权限查看其它区域的数据")
                }
            },

            //判断展示当前业务系统场所的行业类型/企业状态
            showChoose(){
                switch (this.type.prop) {
                    case 'csba':
                        this.placeTypeColumns = this.formatColumns(this.ylPlaceTypeData)
                        this.placeStateColumns = this.formatColumns(ylcsDict.csbaState)
                        break;
                    case 'hotel':
                        this.placeTypeColumns = this.formatColumns(this.lyPlaceTypeData)
                        this.placeStateColumns = this.formatColumns(this.lyPlaceStateData)
                        break;
                    case 'jxyxt':
                        this.placeTypeColumns = this.formatColumns(this.jxPlaceTypeData)
                        this.placeStateColumns = this.formatColumns(this.jxPlaceStateData)
                        break;
                    case 'kyzy':
                        this.placeTypeColumns = this.formatColumns(this.kyzPlaceTypeData)
                        this.placeStateColumns = this.formatColumns(this.kyzPlaceStateData)
                        break;
                    case 'gxdw':
                        this.placeTypeColumns =  this.formatColumns(this.jzPlaceTypeData)
                        this.placeStateColumns = this.formatColumns(this.jzPlaceStateData)
                        break;
                    default:
                        break;
                }
            },

            //格式化场所行业类型
            formatColumns(dicData){
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

            //获取选中的积分等级
            onConfirmDj(data) {
                this.pointType = data.text
                this.param.pointType = data.value
                this.showPicker = false
            },
            //获取选中的所属区域
            onConfirmArea(data) {
                if (Array.isArray(data)) {
                    if (data[1].name == '不限') {
                        this.orgCode = data[0].name
                        this.param.orgCode = data[0].code
                    } else if (data[2].name == '不限') {
                        this.orgCode = data[0].name.concat(data[1].name)
                        this.param.orgCode = data[1].code
                    } else {
                        this.orgCode = data[0].name.concat(data[1].name).concat(data[2].name)
                        this.param.orgCode = data[2].code
                    }
                } else {
                    this.orgCode = data.text
                    this.param.orgCode = data.value
                }
                this.showArea = false
                this.showCity = false
            },
            //获取选中的行业类型
            onConfirmPlaceType(data) {
                this.placeType = data.text
                this.param.placeType = data.value
                this.showPlaceType = false
            },
            //获取选中的企业状态
            onConfirmPlaceState(data){
                this.placeState = data.text
                this.param.placeState = data.value
                this.showPlaceState = false
            },
            //提交查询参数
            onSubmit() {
                this.searchForm.dwmc = this.param.dwmc
                this.searchForm.pointType = this.param.pointType
                this.searchForm.orgCode = this.param.orgCode
                this.searchForm.placeType = this.param.placeType
                this.searchForm.placeState = this.param.placeState
                this.$refs.item.toggle()
            },

        },
    }
</script>
