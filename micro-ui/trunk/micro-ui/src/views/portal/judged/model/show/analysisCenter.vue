<template>
    <div class="analysisCenter_box">
        <div class="analysisCenter"  ref="viewBox">
            <div class="main"  v-loading="loading">
                <div class="back" v-show="showBack" @click="back"><i class="el-icon-d-arrow-left"></i> 返回</div>
                <div class="demo-input-suffix">
                    <el-input
                            v-show="showInput"
                            placeholder="请输入模型名称"
                            v-model="inputVal">
                        <i slot="suffix" class="el-input__icon el-icon-search" @click="searchBtn(inputVal)"></i>
                    </el-input>
                </div>
                <template>
                    <el-tabs v-model="activeName" @tab-click="handleClick">
                        <el-tab-pane label="默认分类" name="first">
                            <defaultCom @handleChange="tabChange" v-if="showDefaultCom" @loads="loads"/>
                            <headingsCom v-if="!showDefaultCom"
                                         :listQ="listQuery1"
                                         :backQuery="backQuery"
                                         @headloads="headloads"
                                         v-model="historyQuery" ref="heading"/>
                        </el-tab-pane>
                        <el-tab-pane label="展开全部" name="second">
                            <allCom
                                    ref="all"
                                    :inputVal="inputVal"
                            />
                        </el-tab-pane>
                    </el-tabs>
                </template>
            </div>
        </div>
        <el-backtop target=".analysisCenter"></el-backtop>
    </div>
</template>

<script>
    import defaultCom from "./default";
    import allCom from "./all";
    import headingsCom from "./headings";
    import {deepClone} from '@/util/util';
    import {routerJump} from '@/api/portal/judged/model/routerJump'
    export default {
        name: "analysisCenter",
        components:{
            defaultCom,
            allCom,
            headingsCom
        },
        data() {
            return {
                showBack:false,
                loading:true,
                inputVal:"",
                activeName: 'first',
                showDefaultCom:true,
                showInput:false,
                historyQuery:[],
                listQuery1:{},
                backQuery:{},
                one:true,
            };
        },
        mounted(){
        },
        methods: {
            headloads(dispaly,data,list){
                this.loading=dispaly;//false
                if(!dispaly) this.listQuery1 = data
            },
            loads(data){
                this.loading=data;//false
            },
            handleClick(tab, event) {
                if(tab.name!="first"){
                    //展开全部
                    this.showInput = true;
                    this.showBack = false;
                    if(this.one){
                        this.one=false;
                        this.$refs.all.fetchAppInti();
                    }
                }else{
                    this.showInput = false;
                    this.showBack=!this.showDefaultCom;
                }
            },
            tabChange(data){
                this.listQuery1 = data;
                this.historyQuery = [];
                this.historyQuery.push(data)
                this.showDefaultCom=!this.showDefaultCom;
                this.showBack=!this.showDefaultCom;
            },
            back(){
                if(this.historyQuery.length==1){
                    this.backQuery=this.historyQuery[0];
                    this.historyQuery=[];
                    this.showDefaultCom=!this.showDefaultCom;
                    this.showBack=!this.showDefaultCom;
                }else{
                    this.historyQuery.splice(-1, 1);
                    this.backQuery = this.historyQuery[this.historyQuery.length - 1];
                }
                if(!this.showDefaultCom){
                    setTimeout(()=>{
                        this.$refs.heading.loading = true
                        this.$refs.heading.initData(this.backQuery)
                    },100)
                }

            },
            searchBtn(inputVal){
                this.$refs.all.searchBtn(inputVal)
            }
        },
    }
</script>

<style lang="scss">
    .el-tooltip__popper{
        max-width: 400px;
    }
    .analysisCenter_box{
        *{
            box-sizing: border-box;
        }
        padding:0 8px 10px;
        box-sizing: border-box;
        height: 100%;

        /*滚动条样式*/
        ::-webkit-scrollbar {
            width: 6px;
            height: 6px;
            background-color: #f5f5f5;
        }
        ::-webkit-scrollbar-thumb {
            background-color: #999;
        }
        ::-webkit-scrollbar-track {
            background-color: #fff;
        }
    }
    .analysisCenter{
    overflow: auto;
    background: #fff;
    border-radius: 6px;
    min-width: 1120px;
    height: 100%;
    .main{
        padding: 10px;
        background: #fff;
    }
    *{box-sizing: border-box;}
    position: relative;
    .el-card{
        height: 100%;
    }
    ::-webkit-input-placeholder{
        color: #838383!important;
    }
    :-ms-input-placeholder {
        color: #838383!important;
    }
    ::-moz-placeholder {
        color: #838383!important;
    }
    :-moz-placeholder {
        color: #838383!important;
    }
    .demo-input-suffix{
        width: 400px;
        min-width:200px;
        z-index: 10;
        position: absolute;
    }
    .el-input__inner{
        background-color: #f4f4f4!important;
        border-radius: 26px!important;
    }
    .el-input__suffix {
        font-size: 18px!important;
        margin-right: 10px!important;
        color: #838383!important;
    }
    .el-tabs__nav-wrap::after{
        position: static!important;
    }
    .el-tabs__nav{
        float: right!important;
    }
    .el-tabs__active-bar{
        background-color: #fff!important;
    }
    .back{
        position: absolute;
        height: 40px;
        line-height: 40px;
        right: 200px;
        font-size: 14px;
        color: #409eff;
        z-index: 100;
        cursor: pointer;
    }
    .el-tabs__content {
        position: static!important;
    }
    .el-tabs__item.is-active {
        color: #303133 !important;
    }
    .el-tabs__item {
        color: #409eff !important;
    }
}
</style>