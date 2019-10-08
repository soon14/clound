<template>
    <div class="searchList">
        <van-nav-bar title="地图查询">
            <van-icon name="arrow-left" slot="left" size="20px" @click="back"/>
            <van-icon name="wap-home" slot="right" size="20px" @click="toIndex"/>
        </van-nav-bar>

        <van-search placeholder="请输入搜索关键词" shape="round" v-model="inputVal"/>
        <div class="fromBox">
            <el-form  :model="formInline"  :inline="true">
                <el-form-item label="行业类型:">
                    <el-select
                            v-model="industryV"
                            multiple
                            collapse-tags
                            placeholder="请选择">
                        <el-option
                                v-for="item in options.sycslb"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="积分等级:">
                    <el-select
                            v-model="levelV"
                            multiple
                            collapse-tags
                            placeholder="请选择">
                        <el-option
                                v-for="item in options.jfde"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="所属地市:">
                    <el-select
                            v-model="cityV"
                            @change="getCounty"
                            placeholder="全省">
                        <el-option
                                v-for="item in options.ssds"
                                :key="item.code"
                                :label="item.name"
                                :value="item.code">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="所属区县:">
                    <el-select
                            v-model="countyV"
                            placeholder="市辖区">
                        <el-option
                                v-for="item in options.ssqx"
                                :key="item.code"
                                :label="item.name"
                                :value="item.code">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <van-button type="primary" size="large" color="#409eff" @click="clickBtn">匹配地址</van-button>
        </div>
        <div class="addressList">
             <van-loading vertical type="spinner" color="#1989fa" style="margin-top: 30px" v-if="showLoad">加载中...</van-loading>
            <avue-empty v-if="showEmpty"></avue-empty>
            <van-cell class="listItem"
                       v-for="(item,idx) in addressList"
                       :key="idx"
                       @click="viewPlaceDetail(item)">
                 <van-icon name="location" color="#1989fa" size="20px" style=" vertical-align: top;"/>&nbsp;&nbsp;{{item.placeAddress}}
             </van-cell>
        </div>
    </div>

</template>

<script>
    import {fetchArea,fetchAddress} from '@/api/portal/archive/common/query'
    import { validatenull } from "@/util/validate";
    const DIC = {
        sycslb:[
            {"label":"旅馆业","value":"01"},
            {"label":"刻印章业","value":"02"},
            {"label":"旧货业","value":"03"},
            {"label":"印刷业","value":"04"},
            {"label":"机动车修理业","value":"05"},
            {"label":"开锁业","value":"06"},
            {"label":"其他管理行业","value":"07"},
            {"label":"歌舞娱乐场所","value":"08"},
            {"label":"按摩服务业","value":"09"},
            {"label":"游戏游艺场所","value":"10"},
            {"label":"录像放映场所","value":"11"},
            {"label":"上网服务场所","value":"12"},
            {"label":"体育场馆","value":"13"},
            {"label":"广场公园","value":"14"},
            {"label":"其他场所","value":"15"},
            {"label":"报废机动车拆解业","value":"16"},
            {"label":"典当业","value":"17"},
            {"label":"废旧金属业","value":"18"}
        ],
        jfdj:[
            {"label":"A级","value":"01"},
            {"label":"B级","value":"02"},
            {"label":"C级","value":"03"},
            {"label":"D级","value":"04"},
        ],
        ssds:[
            {"label":"广州","value":"01"},
            {"label":"江门","value":"02"},
        ],
        ssqx:[
            {"label":"天河区","value":"01"},
            {"label":"越秀区","value":"02"},
            {"label":"番禺区","value":"03"},
            {"label":"白云区","value":"04"},
        ],
    };
    export default {
        name: "searchList",
        data() {
            return {
                show:true,
                options: {
                    sycslb:DIC.sycslb,
                    jfde:DIC.jfdj,
                    ssds:DIC.ssds,
                    ssqx:DIC.ssqx,
                },
                formInline:{},

                industryV: [],
                levelV: [],
                cityV: '',
                countyV:'',

                addressList:[],
                inputVal:"",

                showLoad:false,
                showEmpty:false,
            }
        },
        mounted(){
            this.getCity();
        },
        methods:{
            back() {
                this.$router.go(-1);
            },
            toIndex() {
                this.$router.replace("/mobile/find");
            },
            getCity(){
                fetchArea().then(data=>{
                    this.options.ssds=data.data.data[0].children;
                })
            },
            getCounty(){
                this.options.ssds.forEach(item=>{
                    if(item.code==this.cityV){
                        this.options.ssqx = item.children;
                    }
                })
            },
            clickBtn(){
                    let levels = this.levelV,industrys = this.industryV,levelType ='',placeType ='',cityCode = '',address = this.inputVal;
                    if(industrys.length>0){
                        for (let i = 0; i < industrys.length; i++) {
                            placeType += industrys[i]
                            if(i<industrys.length-1){
                                placeType += ','
                            }
                        }
                    }

                    if(levels.length>0){
                        for (let i = 0; i < levels.length; i++) {
                            levelType += levels[i]
                            if(i<levels.length-1){
                                levelType += ','
                            }
                        }
                    }
                    if (!validatenull(this.countyV)) {
                        cityCode = this.countyV
                    }else {
                        cityCode = this.cityV
                    }

                let parame ={levelType:levelType,placeType:placeType,cityCode:cityCode,placeAddress:address,rowNum:15}
                this.showLoad=true;
                this.addressList=[];
                this.showEmpty=false;
                fetchAddress(parame).then(data=>{
                    this.addressList=data.data.data;
                    this.showLoad=false;
                    if(this.addressList.length<=0) this.showEmpty=true;
                })
            },
            viewPlaceDetail(item){
                this.$router.push({
                    path: this.$router.$avueRouter.getPath({
                        src: "/mobile/mapQuery/"
                    }),
                    query: {
                        id: item.placeAddress
                    }
                });
            },
        }
    }
</script>

<style lang="scss">
    .searchList{
        .fromBox{
            padding:10px;
            margin-bottom: 20px;
            border-bottom: 11px solid #eee;
        }
        .el-form-item__label{
            width:21%;
            color: rgba(64, 158, 255, 0.9);
        }
        .el-form-item{
            margin-bottom: 5px!important;
            width: 100%;
        }
        .el-form-item__content{
            width: 79%;
        }
        .el-select{
            width: 100%;
        }
    }

</style>