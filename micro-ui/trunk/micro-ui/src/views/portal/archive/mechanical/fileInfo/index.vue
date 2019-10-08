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
        <div class="fileInfo-body" :is="currentView" v-bind:paramForm="searchForm"  v-on:moreClick="moreClick">

        </div>
    </div>
</template>

<script>
    import  cateindex from './cate/index.vue'
    import  cyryxx from './cyry/index.vue'
    import  cjclxx from './cjcl/index.vue'
    export default {
        name: 'fileInfo',
        props:["paramForm"],
        components:{
         cateindex,
         cyryxx,
         cjclxx
        },
        data () {
            return{
                selected:0,
                tabData:[
                    {"name":"单位信息","icontfont":"alzhengjianxinxi"},
                    /*{"name":"证件信息","icontfont":"aljilu1"},*/
                    {"name":"从业人员","icontfont":"alcongyerenyuan"},
                    {"name":"承接车辆","icontfont":"aljilu1"},
                    {"name":"视频监控","icontfont":"alshipin"},
                ],
                currentView:"cateindex",
                tabView:["cateindex","cyryxx","cjclxx"],/*"unitindex",*/
                actives: 'cateindex',
                searchForm: {
                    menuId:'',
                    dwbh:''
                }
            }
        },
        created(){
            this.searchForm.menuId = this.paramForm.menuId
            this.searchForm.dwbh = this.paramForm.dwbh
        },
        watch:{
            paramForm:{
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler () {
                    this.searchForm.menuId = this.paramForm.menuId
                    this.searchForm.dwbh = this.paramForm.dwbh
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
                console.log(this.actives);
            },

            moreClick(index){
                this.tabChange(this.tabView[index],index)
            }

        }
    }
</script>

<style lang="scss">
.fileInfo{
    *{
        box-sizing: border-box;
    }
    position: relative;
    .fileInfo-tab{
        width: 100%;
        clear: both;
        position: fixed;
        overflow: hidden;
        background: #fff;
        padding-left: 18px;
        z-index: 10;

        .fileInfo-tab-item{
            height:76px;
            line-height: 76px;
            margin: 20px 5px 20px 0;
            width: 9.8%;
            float: left;
            border:1px solid #ccc;
            border-radius: 5px;
            background-color:#f8fbff;
            color:#5ca9f7;
            .fileInfo-tab-iconfont{
                font-size: 24px;
                vertical-align: middle;
                margin-left: 10%;
            }
        }

    }
    .fileInfo-body{
        padding:0 10px;
        background: #ebedf0;
        padding-top: 140px;
    }
    .actives{
         background: #067af3 !important;
         color: #fff !important;
     }
}
</style>