<template>
    <div class="fileInfo">
        <!--tab-->
        <div  class="fileInfo-tab">
            <div class="fileInfo-tab-item"
                 v-for="(item,index) in tabData"
                 :key="index"
                 :class="{'actives':selected===index}" @click="tabChange(tabData[index].tabView,index)">
                <i class="iconfontlogo fileInfo-tab-iconfont" :class="item.icontfont"></i>
                <span>{{item.name}}</span>
            </div>
        </div>
        <!--main-->
        <div class="fileInfo-body" :is="currentView"   v-bind:paramForm="searchForm" v-on:moreClick="moreClick">
        </div>
    </div>
</template>

<script>
    import  placeDetail from './placeDetail'
    import  employeeJz from  '../employee/employeeJzList'
    import  zjxxList from '../zjxx/zjxxList'
    import zajcJz from '../zajc/zajcJzList'
    import placeFile from '../file/placeFileList'
    import xfjcJz from '../xfjc/xfjcJzList'
    import  {containStr} from  "@/const/crud/portal/archive/commom/strUtil"
    import  {placeTypeEnum} from  "@/const/crud/portal/archive/commom/constant"
    import {deepClone} from '@/util/util'
    export default {
        props:["paramForm"],
        components:{
            placeDetail,
            employeeJz,
            zjxxList,
            placeFile,
            zajcJz,
            xfjcJz
        },
        name: 'gxdwTab',
        data () {
            return{
                selected:0,
                insertIndex:2,
                hotelTabData:[
                    {name:"国内旅客",icontfont:"alyidengjihetongpifu",tabView:"placeDetail"},
                    {name:"港澳台旅客",icontfont:"alyidengjihetongpifu",tabView:"placeDetail"},
                    {name:"国外旅客",icontfont:"alyidengjihetongpifu",tabView:"placeDetail"},
                  ],
                otherTabData:[
                    {name:"单位信息",icontfont:"alzhengjianxinxi",tabView:"placeDetail"},
                    {name:"从业人员",icontfont:"alcongyerenyuan",tabView:"employeeJz"},
                    {name:"证件信息",icontfont:"aljilu1",tabView:"zjxxList"},
                    {name:"附件信息",icontfont:"aljilu1",tabView:"placeFile"},
                    {name:"治安检查",icontfont:"alzhian",tabView:"zajcJz"},
                    {name:"消防检查",icontfont:"alicon-test",tabView:"xfjcJz"},
                    {name:"视频监控",icontfont:"alshipin",tabView:"zajcJz"},
                ],
                tabData:[],
                currentView:"placeDetail",
                actives: 'placeDetail',
                searchForm: {
                    menuId:'',
                    unitId:''
                }
            }
        },
        created(){
            this.hasTap(this.paramForm.placeType)
            this.searchForm.menuId = this.paramForm.menuId
            this.searchForm.unitId = this.paramForm.unitId
        },
        watch:{
            paramForm:{
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler () {
                    this.hasTap(this.paramForm.placeType)
                    this.searchForm.menuId = this.paramForm.menuId
                    this.searchForm.unitId = this.paramForm.unitId
                    this.tabChange(this.tabData[0].tabView,0);


                },
                deep:true
            },

        },
        methods: {
            hasTap(activePlaceType){
                if(containStr(activePlaceType,placeTypeEnum.LGY.value)){
                    this.tabData=[]
                    for ( let  i = 0; i <this.otherTabData.length; i++){
                        if(i===this.insertIndex){
                            for(let arr of this.hotelTabData ){
                                this.tabData.push(arr)
                            }
                        }else{
                            this.tabData.push(this.otherTabData[i])
                        }
                    }
                }else{
                    this.tabData=deepClone(this.otherTabData)
                }
            },
            tabChange(tabItem,index){
                this.selected=index;
                this.actives = tabItem;
                this.currentView=tabItem;
            },
            moreClick(index){
                this.tabChange(this.tabData[index].tabView,index)
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
            padding:120px 0px 0px 0px;
            background:#dbdfe6;

        }
        .actives{
            background: #067af3 !important;
            color: #fff !important;
        }
    }
</style>