<template>
    <div>
        <basic-container>
            <avue-tabs :option="option" @change="handleChange" v-loading="tabLoading"></avue-tabs>

            <div v-show="showType" :is="type.prop" v-bind:search-form="searchForm"
                 v-on:changeCount="changeDataCount"></div>
            <iframe v-show="!showType" id="show-iframe" frameborder=0 scrolling=auto :src="iframeSrc"></iframe>
        </basic-container>

    </div>
</template>

<script>
    import {countAll, findListPage, findOne, findTab} from "@/api/portal/archive/common/unitQuery";
    import gxdw from "../jingz/cs/gxdwList";
    import jxyxt from "../mechanical/jxyxt"
    import csba from "../ylcs/csba/csbaList";
    import hotel from "../lvye/hotel/hotelList";
    import kyzy from "../kyzy/list";
    import {mapGetters} from "vuex";
     const component={
             "gxdw": gxdw,
             "hotel": hotel,
             "jxyxt": jxyxt,
             "csba": csba,
             "hotel": hotel,
             "kyzy": kyzy
         }

    export default {
        name: '',
        components: component,
        data() {
            return {
                type: {},
                showType: false,
                tabLoading: false,
                iframeSrc:"",
                searchForm: {
                    dwmc: '',
                    placeType: undefined,
                    pointType: undefined,
                    orgCode: undefined,
                    menuId: undefined
                },
                option: {
                    column: [{
                        icon: '',
                        label: '',
                        prop: '',
                        id: ''
                    }]
                },
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                list: [],
                preViewData: [],
                listLoading: true,
                form: {},
            };
        },
        computed: {
            ...mapGetters(["permissions"])
        },
        mounted() {
            this.initFrame()
        },

        created() {
            this.searchForm.dwmc = this.$route.query.dwmc
            this.searchForm.placeType = this.$route.query.placeType
            this.searchForm.pointType = this.$route.query.pointType
            this.searchForm.orgCode = this.$route.query.orgCode
            this.tabList();
        },
        methods: {
           initFrame(){
               /**
                * iframe-宽高自适应显示
                */
               const oIframe = document.getElementById('show-iframe');
               const deviceHeight = document.documentElement.clientHeight;
               oIframe.style.width = "100%"; //数字是页面布局宽度差值
               oIframe.style.height = (Number(deviceHeight) - 120) + 'px'; //数字是页面布局高度差
           } ,
            tabList() {
                this.tabLoading = true
                findTab().then(response => {
                    this.option.column = response.data.data
                    this.handleChange(this.option.column[0])
                    this.searchForm.menuId = this.type.id;
                    this.tabLoading = false
                    setTimeout(() => {
                        this.countList();
                    }, 1000);

                })
            },
            countList() {
                countAll(Object.assign({}, this.searchForm)).then(response => {
                    this.option.column = response.data.data
                })
            },

            handleChange(column) {
                if(this.isComponent(column.prop)==true){
                    this.showType = true
                    this.type = column
                    this.searchForm.menuId = this.type.id;
                }else{
                    this.showType = false
                    this.iframeSrc = column.prop
                    this.initFrame()
                }
            },
            isComponent(str){
                for(let key in component){
                    if(key===str||key==str){
                        return true
                    }
                }
                return false
            },

            changeDataCount(data) {
                this.searchForm = data
                this.searchForm.menuId = this.type.id;
                countAll(data).then(response => {
                    this.option.column = response.data.data
                })
            },

        }
    };
</script>


