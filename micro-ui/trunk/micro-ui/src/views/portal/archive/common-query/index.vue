<template>
    <basic-container class="page-common-query">
        <div class="common-query-box">
            <div class="common-query-contenbox">
                <div class="archives_num"> 特行场所单位档案:   <span>10000</span>
                </div>
                <div class="search_box">
                    <div class="icon-edit"> <el-button @click="show = !show"></el-button></div>
                    <input v-model="input" placeholder="场所名称" />
                    <el-button
                            type="primary"
                            class="el-button-s"
                            @click="search"
                    >搜 索</el-button>
                </div>
                <transition name="el-zoom-in-top">
                    <div class="query-checkbox"  v-show="show">
                        <el-form  class="el-form-s transition-box" label-width="80px" >
                            <el-form-item label="行业类型:" class="hylx_content">
                                <template>
                                    <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
                                    <el-checkbox-group v-model="checkedHylx" @change="handleCheckedHylxChange" v-loading="hylxLoading">
                                        <el-checkbox-button v-for="hylx in hylxData" :label="hylx">{{hylx.label}}</el-checkbox-button>
                                    </el-checkbox-group>
                                </template>
                            </el-form-item>
                            <el-form-item label="积分等级:" class="ds_content hylx_content">
                                <template>
                                    <el-radio-group v-model="jfdj_check" v-loading="jfdjLoading">
                                        <el-radio-button :label="clearJfdjCheck" @change.native="clearJfdjChecked()"></el-radio-button>
                                        <el-radio-button v-for="jfdj in jfdjData" :label="jfdj.label"
                                                         @change.native="jfdj_handleCheckedJfdjChange(jfdj.value,jfdj.label)">
                                        </el-radio-button>
                                    </el-radio-group>
                                </template>
                            </el-form-item>

                            <el-form-item label="辖区范围:" class="ds_content hylx_content ds_content_box">
                                <template>
                                    <el-radio-group v-model="dishi" v-loading="xqfwLoading">
                                        <el-radio-button :label="clearCheck" @change.native="clearChecked()"></el-radio-button>
                                        <el-radio-button v-for="item in ds_cities" :label="item.name" v-if="item.name!='省厅'&&item.name!='广东省'"
                                                         @change.native="getChildren(item.code,item.name,item.children)"
                                                        ></el-radio-button>
                                    </el-radio-group>
                                </template>
                            </el-form-item>

                            <el-form-item  class="ds_content hylx_content">
                                <template>
                                    <el-radio-group v-model="quxian">
                                        <el-radio-button v-for="item in xq_cities" :label="item.name" @change.native="selectChange(item.code,item.name)"></el-radio-button>
                                    </el-radio-group>
                                </template>
                            </el-form-item>
                        </el-form>

                </div>
                </transition>
            </div>

        </div>

    </basic-container>
</template>

<script>
    import {fetchArea,fetchHylx,fetchJfdj} from '@/api/portal/archive/common/query'

    export default {
        data () {
            return {
                hylxLoading:true,
                jfdjLoading:true,
                xqfwLoading:true,
                hylxQuery: {
                    type: 'HYLX'
                },
                checkAll: false,//是否全选
                checkedHylx:[],//默认选中
                hylxData:[],//行业类型
                isIndeterminate: true, //标识

                jfdj_check: '',
                clearJfdjCheck:'不限',
                jfdjData:[],

                clearCheck:'不限',
                dishi:'',
                quxian:'',
                ds_cities:[],
                xq_cities:[],
                orgCode:'',//页面跳转参数 区域code
                placeType:'',//页面跳转参数 行业类型value
                pointType:'',//页面跳转参数 积分等级
                input:'',////页面跳转参数 单位名称
                show: true,
                height:1024,
                areaQuery: {
                    flag:1
                },
             };
        },
        mounted(){
            window.addEventListener('resize', this.getDomHeight);
            this.getDomHeight();
        },
        beforeDestroy(){
            window.removeEventListener('resize', this.getDomHeight);
        },
        created(){
          this.getArea();
          this.getHylx();
          this.getJfdj();
        },
        methods: {
            getHylx(){
                this.hylxData=[]
                this.hylxLoading=true
                fetchHylx(this.hylxQuery).then(response => {
                    this.hylxData = response.data.data
                    this.hylxLoading=false
                })
            },
            getJfdj(){
                this.jfdjData = []
                this.jfdjLoading=true
                fetchJfdj().then(response => {
                    this.jfdjData = response.data.data
                    this.jfdjLoading=false
                })
            },
            getArea(){
                this.ds_cities=[]
                this.xqfwLoading=true
                fetchArea(this.areaQuery).then(response => {
                    this.ds_cities = response.data.data
                    this.xqfwLoading=false
                })
            },
            clearChecked(){
                this.orgCode='';
                this.dishi='不限';
                this.quxian = ''
            },
            getChildren(code,name,data){
                this.xq_cities = [];
                this.xq_cities = data;
                this.dishi = name;
                this.selectChange(code,name)
            },
            selectChange(code,name){
                this.orgCode = code
                this.quxian = name
            },
            getDomHeight(){
                    let commonQueryDom=document.querySelector(".page-common-query");
                    if(!!commonQueryDom) this.height=commonQueryDom.offsetHeight;
                    let contentHeight=this.height-160-100;
                    let formDom=document.querySelector(".el-form-s");
                    let contentChildHeight=0;
                    if(!!formDom) contentChildHeight=formDom.offsetHeight;
                    var queryCheckbox= document.querySelector(".query-checkbox");
                    if(!!queryCheckbox) queryCheckbox.style.height=0;
                    if(contentChildHeight <= contentHeight){
                        queryCheckbox = contentChildHeight+20+'px';
                    }else{
                        queryCheckbox = contentHeight+'px';
                    }
            },
            handleCheckAllChange(val) {
                this.checkedHylx = val ? this.hylxData : [];
                this.isIndeterminate = false;
                this.placeType=''
                if(this.checkedHylx.length>0){
                    for (let i = 0; i < this.checkedHylx.length; i++) {
                        this.placeType += this.checkedHylx[i].value
                          if(i<this.checkedHylx.length-1){
                            this.placeType += ','
                           }
                        }
                }
            },
            handleCheckedHylxChange(value) {
                let checkedCount = value.length;
                this.checkAll = checkedCount === this.hylxData.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.hylxData.length;
                this.placeType=''
                if(value.length>0){
                    for (let i = 0; i < value.length; i++) {
                        this.placeType += value[i].value
                        if(i<value.length-1){
                            this.placeType += ','
                        }
                    }
                }
            },
            jfdj_handleCheckedJfdjChange(value,label) {
                this.pointType = value
                this.jfdj_check = label
            },
            clearJfdjChecked(){
                this.pointType ='';
                this.jfdj_check='不限';
            },
            search(){
                //删除所有档案列表信息、只打开一个列表tab
                this.$store.commit("DEL_TAG_BY_LABEL","档案列表");
                this.$router.push({
                    path: this.$router.$avueRouter.getPath({
                        name:'档案列表',
                        src: "/portal/archive/common-query",
                    }),
                    query: {
                        dwmc:this.input,
                        placeType:this.placeType,
                        pointType:this.pointType,
                        orgCode:this.orgCode,
                    }
                });
            }

        },
    }
</script>

<style lang="scss">
    .page-common-query {
        height: 100%;
        min-width: 900px;
        min-height: 500px;
        .el-card.is-always-shadow {
            height: 100% !important;
        }
        .common-query-box{
            height:100%;
            background: url("../../../../../public/img/archives/bg.png")no-repeat;
            background-size: 100% 100%;
            -webkit-background-size:100% 100%;
        }
        .common-query-contenbox{
            width: 70%;
            min-width: 580px;
            margin: 0 auto;
            padding-top:80px;
        }
        .el-card__body {
             padding: 0px !important;
             height: 100%;
        }
        .archives_num {
            height: 50px;
            text-align: center;
            font-size: 20px;
            color: #E0F0FF;
            span {
                font-family:"DS-Digital" ;
                font-size: 40px;
            }
        }
        .search_box {
            position:relative;
            width:100%;
            height: 50px;
            border-radius: 50px;
            border: 1px solid #ccc;
            overflow: hidden;
            background: #fff;
            .icon-edit {
                width:70px;
                float: left;
                height: 50px;
                text-align: center;
                line-height: 60px;
                font-size: 23px;
                color: #0066F2;
                margin-right: 5px;
                .el-button{
                    width: 100%;
                    height: 50px;
                    border: none;
                    background: url("../../../../../public/img/archives/gang.png")no-repeat right;
                }
            }
            input {
                outline: none;
                border: 0px;
                height: 50px;
                width: 70%;
                overflow: hidden;
                font-size: 15px;
                color:#333;
            }
            .el-button-s {
                position: absolute;
                right:0;
                height: 50px;
                width: 110px;
                border-radius: 36px;
                font-size: 23px;
                background: #E0F0FF;
                border: 0px;
                color: #0066F2;
                font-size: 17px;
                font-weight: 550;
            }
        }
        .el-icon-office-building {
            float: left;
        }

        .query-checkbox {
            width: 95%;
            margin: auto;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding-top: 10px;
            padding-bottom: 20px;
            background: #fff;
            padding:10px;
            box-sizing: border-box;
            box-shadow: 1px 1px 5px #ccc;
            min-height:320px;
            overflow-y: auto;
        }
        .el-form-item__label{
            font-size: 14px;
            color: #067AF5;
        }
        .el-checkbox-s {
            font-size: 16px;
        }
        .el-xiaqu{
            box-sizing: border-box;
            /* float: left; */
            width: 410px;
            border: 1px solid #dcdfe6;
            border-radius: 6px;
            padding: 0 2px 3px 6px;
            font-size: 16px;
            font-weight: 600;
            margin-right: 10px;
            .shu-ru-di-shi{
                color:#8D8D8D;
            }
            .di-shi-span{
                margin-right:10px;
            }
        }
        .el-icon-caret-bottom:before {
            float: right;
        }
        .hylx_content{
            margin-bottom: 20px;
            .el-checkbox {
                float: left;
            }
            .el-checkbox-button__inner {
                line-height: 1;
                border:none !important;
                padding: 10px 15px;
            }
            .el-checkbox-button.is-checked .el-checkbox-button__inner {
                color:#409EFF;
                background-color: #fff;
                border-color: #fff;
                -webkit-box-shadow:none;
                box-shadow:none;
            }
        }
        .jfdj_content{
            .el-checkbox-group {
                border-top: 1px solid #ccc;
                border-bottom: 1px solid #ccc;
                padding:10px 0;
            }
            .el-checkbox{
                padding:10px 0;
            }
            .el-form-item__label {
                padding-top:10px;
            }
        }
        .ds_content{
            .el-radio-button__inner{
                border: none;
            }
            .el-radio-button__orig-radio:checked+.el-radio-button__inner {
                color:#1f94f4;
                background-color: #fff;
                -webkit-box-shadow:none;
                box-shadow:none;
            }
        }
        .ds_content_box .el-radio-group{
            border-bottom: 1px solid #ccc;
        }

    }
</style>