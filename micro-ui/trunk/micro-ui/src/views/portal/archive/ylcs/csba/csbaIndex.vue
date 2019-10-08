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
            <div class="fileInfo-body" :is="currentView" v-bind:csba-form="searchForm" v-on:moreClick="moreClick">

            </div>
        </div>
</template>

<script>
    import  jbxx from './jbxx.vue'
    import  zzxx from '../csbafj/zzxx.vue'
    import  ryba from '../ryba/rybaList.vue'
    import  rykq from '../ryLog/rykq.vue'
    import  baxj from '../baxj/baxjList.vue'
    import  aqjc from '../aqjc/aqjcList.vue'

    export default {
        props:["paramForm"],
        name: 'csbaIndex',
        data () {
            return{
                selected:0,
                tabData:[
                    {"name":"单位信息","icontfont":"alzhengjianxinxi"},
                    {"name":"证件信息","icontfont":"aljilu1"},
                    {"name":"从业人员","icontfont":"alcongyerenyuan"},
                    {"name":"上下班打卡","icontfont":"alwuzhengruzhulvguan"},
                    {"name":"保安巡检","icontfont":"alyidengjihetongpifu"},
                    {"name":"安全检查","icontfont":"alzhian"},
                    {"name":"视频监控","icontfont":"alshipin"},
                    ],
                currentView:"jbxx",
                tabView:["jbxx","zzxx","ryba","rykq","baxj","aqjc"],
                actives: 'jbxx',
                searchForm: {
                    csbaId:'',
                    csbaBabh:'',
                    compId:'',
                    menuId:'',
                }
            }
        },
        created(){
            this.searchForm.csbaId = this.paramForm.csbaId
            this.searchForm.csbaBabh = this.paramForm.csbaBabh
            this.searchForm.compId = this.paramForm.compId
            this.searchForm.menuId = this.paramForm.menuId
        },
        watch:{
            paramForm:{
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler () {
                    this.searchForm.csbaId = this.paramForm.csbaId
                    this.searchForm.csbaBabh = this.paramForm.csbaBabh
                    this.searchForm.compId = this.paramForm.compId
                    this.searchForm.menuId = this.paramForm.menuId
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
        components:{
            jbxx,
            zzxx,
            ryba,
            rykq,
            baxj,
            aqjc
        }
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
            position: absolute;
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
            padding:0 20px;
            background:#dbdfe6;
            padding-top: 140px;
        }
        .actives{
            background: #067af3 !important;
            color: #fff !important;
        }
    }
</style>