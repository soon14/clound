<template>
        <div class="fileInfo">
            <!--tab-->
            <div  class="fileInfo-tab">
                <div class="fileInfo-tab-item"
                     v-for="(item,index) in tabData"
                     :key="index"
                     :class="{'actives':selected===index}" @click="tabChange(tabView[index],index)">
                    <i class="iconfontlogo fileInfo-tab-iconfont" :class="item.icontfont"></i>
                    <span>{{item.name}}</span>
                </div>
            </div>
            <!--main-->

            <div class="fileInfo-body" :is="currentView"  v-bind:paramForm="searchForm"  v-on:moreClick="moreClick">

            </div>
        </div>
</template>

<script>
    import  detail from './detail'
    import  employee from '../employee/employeeList'
    import  routineCheck from '../routine-check/checkList'
    export default {
        props:["paramForm"],
        name: 'hotelTab',
        components:{
            detail,
            employee,
            routineCheck
        },
        data () {

            return{
                selected:0,
                tabData:[
                    {"name":"单位信息","icontfont":"alzhengjianxinxi"},
                    {"name":"从业人员","icontfont":"alcongyerenyuan"},
                    {"name":"国内旅客","icontfont":"alyidengjihetongpifu"},
                    {"name":"港澳台旅客","icontfont":"alyidengjihetongpifu"},
                    {"name":"国外旅客","icontfont":"alyidengjihetongpifu"},
                    {"name":"日常检查","icontfont":"alzhian"},
                    {"name":"视频监控","icontfont":"alshipin"},
                    ],
                currentView:"detail",
                tabView:["detail","employee","employee","employee","employee","routineCheck","employee"],
                actives: 'detail',
                searchForm: {
                    menuId:'',
                    unitId:''
                }
            }
        },
        created(){
            this.searchForm.menuId = this.paramForm.menuId
            this.searchForm.unitId = this.paramForm.unitId
        },
        watch:{
            paramForm:{
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler () {
                    this.searchForm.menuId = this.paramForm.menuId
                    this.searchForm.unitId = this.paramForm.unitId
                    this.tabChange(this.tabView[0],0);
                },
                deep:true
            },

        },
        methods: {
            tabChange(tabItem,index){
                this.selected=index;
                this.actives = tabItem;
                this.currentView=tabItem;
            },
            moreClick(index){
             this.tabChange(this.tabView[index],index)
            }
        },

    }
</script>

<style lang="scss">
    .fileInfo{
        *{
            box-sizing: border-box;
        }
        /*position: relative;*/
        .fileInfo-tab{
            width: 100%;
            overflow: hidden;
            background: #fff;
            padding-left:15px;
            z-index: 10;
            position: absolute!important;
            display: flex;
            flex-wrap:nowrap;
            .fileInfo-tab-item{
                flex: 1;
                height:76px;
                line-height: 76px;
                margin: 20px 15px 20px 0;
                border:1px solid #ccc;
                border-radius: 5px;
                background-color:#f8fbff;
                color:#5ca9f7;
                text-align: center;
                span{
                    margin-left:5px;
                    vertical-align: -2px;
                }
                .fileInfo-tab-iconfont{
                    font-size:30px;
                    vertical-align: middle;
                }
            }
            .fileInfo-tab-item:hover {
                cursor: pointer;
            }

        }
        .fileInfo-body{
            padding:120px 0px 0px 0px;
            background:#dbdfe6;
        }
        .actives{
            background: #067af3 !important;
            color: #fff !important;
        }
    }
</style>