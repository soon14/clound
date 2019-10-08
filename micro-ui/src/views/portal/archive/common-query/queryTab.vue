<template>
    <div class="app-container calendar-list-container ">
        <basic-container>
            <avue-tabs :option="option" @change="handleChange" v-loading="tabLoading"></avue-tabs>
            <span  :is="type.prop" v-bind:search-form="searchForm" v-on:changeCount="changeDataCount"> </span>
        </basic-container>

    </div>
</template>

<script>
    import {countAll, findListPage, findOne,findTab} from "@/api/portal/archive/common/unitQuery";
    import lowHotel from "../../analysis/model/lowHotel";
    import gxdw from "../jingz/cs/gxdwList";
    import jxyxt from "../mechanical/jxyxt"
    import csba from "../ylcs/csba/csbaList";
    import hotel from "../lvye/hotel/hotelList";
    import {mapGetters} from "vuex";

    export default {
        name:'',
        components: {
            "lowHotel": lowHotel,
            "gxdw": gxdw,
            "hotel":hotel,
            "jxyxt":jxyxt,
            "lowHotel":lowHotel,
            "gxdw":gxdw,
            "csba":csba,
            "hotel":hotel
        },
        data() {
            return {
                type: {},
                tabLoading: false,
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

        created() {
            this.searchForm.dwmc = this.$route.query.dwmc
            this.searchForm.placeType = this.$route.query.placeType
            this.searchForm.pointType = this.$route.query.pointType
            this.searchForm.orgCode = this.$route.query.orgCode
            this.tabList();
            },
        methods: {
            tabList() {
                this.tabLoading = true
                findTab().then(response => {
                    this.option.column = response.data.data
                    this.type = this.option.column[0];
                    this.searchForm.menuId = this.type.id;
                    this.tabLoading = false
                    setTimeout(() => {
                        this.countList();
                    }, 1000);

                })
            },
            countList(){
                countAll(Object.assign({}, this.searchForm)).then(response => {
                    this.option.column = response.data.data
                })
            },

            handleChange(column) {
                this.type = column
                this.searchForm.menuId = this.type.id;
            },
            changeDataCount(data) {
                this.searchForm=data
                this.searchForm.menuId = this.type.id;
                countAll(data).then(response => {
                    this.option.column = response.data.data
                })
            },

        }
    };
</script>


