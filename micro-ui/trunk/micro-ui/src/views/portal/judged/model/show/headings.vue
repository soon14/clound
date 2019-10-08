<template>
    <div class="headings"  v-loading="loading">
        <div v-for="(item,idx) in titleData" :key="idx">
                <h5 class="headings-title" v-if="item.type == 1 ">{{item.name}}</h5>
                <div class="headings-item" v-if="item.type=='1' && item.children.length>0">
                    <div class="item-part"   @click="dirClick(childItem)" v-if="childItem.type==2" v-for="(childItem,index) in item.children" :key="index" >
                        <div class="icon_img">
                            <img :id="childItem.id"
                                 :onload="initIcon(childItem,childItem.id)">
                        </div>
                        <h3 class="name" >{{childItem.name}}</h3>
                        <div class="number"><span>数量</span><span :id="'span_'+childItem.id" v-text="initCount(childItem.id)"></span></div>
                        <div class="introduction">
                            <avue-text-ellipsis
                                    :key="childItem.remark"
                                    :text="childItem.remark"
                                    :height="70"
                                    use-tooltip
                                    placement="top">
                                <small slot="more">...</small>
                            </avue-text-ellipsis>
                        </div>
                    </div>
                    <div class="all-item" v-if="childItem.type==3" v-for="(childItem,index) in item.children" :key="index" @click="appClick(childItem)">
                        <div class="tuoyuan"><img src="/img/pageimg/tuoyuan.png" alt=""></div>
                        <el-card class="all-item-box" shadow="hover" >
                            <div class="tuijian"><img src="/img/pageimg/tuijian.png" alt="" v-if="childItem.useBest=='0'"></div>
                            <div class="all-msg">
                                <div class="icon_img">
                                    <img :id="childItem.id"
                                         :onload="initIcon(childItem,childItem.id)">
                                </div>
                                <div class="icon_r">
                                    <h4 class="name ellipse">{{childItem.name}}</h4>
                                    <div class="time">{{childItem.createDate}}</div>
                                    <div class="collection" @click.stop="modelCollect(childItem,1)">
                                        <i class="iconfontlogo alshoucang" :id="childItem.id+'_heaCollect'"></i>
                                        <span>收藏</span>
                                    </div>
                                    <div @click.stop>
                                    <el-rate @click.stop
                                            @change="change(childItem.stars,childItem.id,childItem.name)"
                                            v-model="childItem.stars"
                                            show-text>
                                    </el-rate>
                                    </div>
                                </div>
                            </div>
                            <div class="introduction">
                                <avue-text-ellipsis
                                        :key="childItem.remark"
                                        :text="childItem.remark"
                                        :height="70"
                                        use-tooltip
                                        placement="top">
                                    <small slot="more">...</small>
                                </avue-text-ellipsis>
                            </div>
                        </el-card>
                    </div>
                </div>
            </div>
        <div class="headings-item">
                <div class="item-part" v-for="(diritem,idx ) in dirData" :key="idx"  @click="dirClick(diritem)">
                    <div class="icon_img">
                        <img :id="diritem.id"
                             :onload="initIcon(diritem,diritem.id)">
                    </div>
                    <h3 class="name">{{diritem.name}}</h3>
                    <div class="number"><span>数量</span><span :id="'span_'+diritem.id" v-text="initCount(diritem.id)"></span></div>
                    <div class="introduction">
                        <avue-text-ellipsis
                                :key="diritem.remark"
                                :text="diritem.remark"
                                :height="70"
                                use-tooltip
                                placement="top">
                            <small slot="more">...</small>
                        </avue-text-ellipsis>
                    </div>
                </div>
            </div>
        <div class="headings-item">
                <div class="all-item"  v-for="(appitem,idx) in appData" :key="idx" @click="appClick(appitem)">
                    <div class="tuoyuan"><img src="/img/pageimg/tuoyuan.png" alt=""></div>
                    <el-card class="all-item-box" shadow="hover" >
                        <div class="tuijian"><img src="/img/pageimg/tuijian.png" alt="" v-if="appitem.useBest=='0'"></div>
                        <div class="all-msg">
                            <div class="icon_img">
                                <img :id="appitem.id"
                                     :onload="initIcon(appitem,appitem.id)">
                            </div>
                            <div class="icon_r">
                                <h4 class="name ellipse">{{appitem.name}}</h4>
                                <div class="time">{{appitem.createDate}}</div>
                                <div class="collection" @click.stop="modelCollect(appitem,1)">
                                    <i class="iconfontlogo alshoucang" :id="appitem.id+'_heaCollect'"></i>
                                    <span>收藏</span>
                                </div>
                                <div @click.stop>
                                <el-rate
                                        @change="change(appitem.stars,appitem.id,appitem.name)"
                                        v-model="appitem.stars"
                                        show-text>
                                </el-rate>
                                </div>
                            </div>
                        </div>
                        <div class="introduction">
                            <avue-text-ellipsis
                                    :key="appitem.remark"
                                    :text="appitem.remark"
                                    :height="70"
                                    use-tooltip
                                    placement="top">
                                <small slot="more">...</small>
                            </avue-text-ellipsis>
                        </div>
                    </el-card>
                </div>
            </div>

        <el-dialog :title="modelTitle" width="80%" :visible.sync="modelDialogFlag" :append-to-body="true">
            <app-dialog v-bind:dialogParam="dialogParam"></app-dialog>
        </el-dialog>

    </div>

</template>
<script>
    import {getModelByLevel,getIconList,countModel} from '@/api/portal/judged/model/modelMenu'
    import {routerJump} from '@/api/portal/judged/model/routerJump'
    import {deepClone} from '@/util/util'
    import { validatenull } from "@/util/validate";
    import {findPage,getCollect,gradeModel,geteditIcon,modelAll} from '@/api/portal/judged/model/modelCollect'
    import appDialog from '../appDialog'

    export default {
        name: "headings",
        components: {appDialog},
        props:{
            listQ:{
                default:"",
            },
            backQuery:{
                default:"",
            },
            value:{
                default:'',
            }
        },
        data(){
            return {
                headloadsing:true,
                titleData: [],
                appData: [],
                collectData:[],
                dirData: [],
                optionData: [],
                listQuery: {},
                historyQuery:[],
                loading:false,

                iconMap: new Map(),
                collectMap: new Map(),

                modelTitle:'',//弹窗title
                modelDialogFlag:false,//弹窗是否展示
                dialogParam:{
                    id:'',
                    links:'',
                },
            }
        },
        mounted(){
            this.listQuery=this.listQ;
            this.initData(this.listQuery);
        },
        created(){
            this.initCollect();
            this.historyQuery.unshift(...this.value)
        },
        methods:{
            change(start,id,name){
                let params = {modelId:id,stars:start}
                gradeModel(params).then(response=>{
                    this.$message.success("感谢您为"+name+"评了"+start+"分!")
                })
            },

            initCollect(){
                findPage().then(response =>{
                    this.collectData = response.data.data.records
                }).catch((error) => {
                })
            },

            modelCollect(item, collect) {
                let collectMap = this.collectMap.get(item.id);
                if ('undefined' == typeof (collectMap) || '' === collectMap || null === collectMap || collect == 1) {
                    this.collectMap.set(item.id, item.id);
                    let headCollect = document.getElementById(item.id + '_heaCollect');
                    let allCollect = document.getElementById(item.id + '_collect');
                    let defCollect = document.getElementById(item.id + '_defCollect');
                    if (collect == 1) {
                        let params = {modelId: item.id, collect: collect, isCollect: headCollect.classList.value=='iconfontlogo alshoucang'?"0":"1"}
                        getCollect(params).then(response => {
                            if (params.isCollect=='0') {
                                if (headCollect != null) {
                                    headCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                                if (allCollect != null) {
                                    allCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                                if (defCollect != null) {
                                    defCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                                if (!validatenull(collect)) {
                                    this.$message.success("收藏成功")
                                }
                            } else {
                                if (headCollect != null) {
                                    headCollect.classList.value = 'iconfontlogo alshoucang'
                                }
                                if (allCollect != null) {
                                    allCollect.classList.value = 'iconfontlogo alshoucang'
                                }
                                if (defCollect != null) {
                                    defCollect.classList.value = 'iconfontlogo alshoucang'
                                }
                                if (!validatenull(collect)) {
                                    this.$message.success("取消成功")
                                }
                            }
                        })
                    } else {
                        this.collectData.forEach(ele => {
                            if (item.id === ele.modelId) {
                                if (headCollect != null) {
                                    headCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                                if (allCollect != null) {
                                    allCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                                if (defCollect != null) {
                                    defCollect.classList.value = 'iconfontlogo alshoucang1'
                                }
                            }
                        })
                    }
                }
            },

            initData(data){
                return new Promise((resolve, reject) => {
                        getModelByLevel(data).then( res => {
                            this.reset()
                            this.optionData = res.data.data;
                            this.headloadsing = false;
                            res.data.data.forEach((item,index)=>{
                                if(item.type=='1'){
                                    this.titleData.push(item)
                                }else if(item.type=='2'){
                                    this.dirData.push(item);
                                }else{
                                    this.appData.push(item);
                                }
                            })
                            this.loading = false;
                            this.$emit("headloads", this.headloadsing,deepClone(data));
                            resolve(res)
                        }).catch(err=>{
                            reject(err)
                        })
                })
            },
            reset(){
                this.optionData=[];
                this.titleData=[];
                this.appData=[];
                this.dirData=[];
            },
            initIcon(item,id) {
                this.iconMap.clear();
                let img=this.iconMap.get(id);
                if('undefined'==typeof (img)||''===img||null===img) {
                    getIconList(id).then(value => {
                        this.iconMap.set(id,value);
                        let imgTag =document.getElementById(id);
                        imgTag.src=value
                        this.modelCollect(item)
                    })
                }else{
                    let imgTag =document.getElementById(id);
                    imgTag.src=img
                }
            },
            initCount(id){
                countModel(id).then(response => {
                    let span=document.getElementById("span_"+id);
                    if(null!=span&&"undefined"!=typeof (span))
                        span.innerText=(response.data)
                })
            },
            dirClick(data){
                let count=document.getElementById("span_"+data.id).innerText;
                if (count != 0){
                    this.headloadsing=true;
                    this.loading = true;
                    this.$emit("headloads", this.headloadsing);
                    this.listQuery=this.listQ;
                    this.listQuery.level=this.listQ.level+2;
                    this.listQuery.id=data.id;
                    this.historyQuery.push(deepClone(this.listQuery))
                    this.initData(this.listQuery);
                }else{
                    this.$message.error('该目录下无主题可查看')
                }
            },
            appClick(data){
                if(data.state=='1'){
                    this.$alert('<strong>该模型正在建设中</strong>',data.name,{
                        dangerouslyUseHTMLString:true
                    });
                    return;
                }
                if(data.openType==='2'){
                    let httpStr = data.links.substr(0,5).toLowerCase()
                    if (httpStr =='http:'||httpStr=='https'){
                        let myHttpStr = httpStr+data.links.substring(5,data.links.length)
                        data.links = myHttpStr
                        window.open(data.links, '_blank', 'height=600, width=1000,top=50, left=150')
                    }else{
                        this.dialogParam.id=data.id
                        this.dialogParam.links = data.links
                        this.modelTitle=data.name
                        this.modelDialogFlag=true
                    }
                }else{
                    routerJump(data)
                }
            },
        },
        watch:{
            historyQuery:{
                handler(newVal){
                    this.$emit('input',newVal)
                },
                deep:true
            },
            value:{
                handler(newVal){
                    console.log(newVal)
                },
                deep:true
            }
        },
    }
</script>

<style lang="scss">
    .headings{
        *{
            margin:0;
            padding:0;
            box-sizing: border-box;
        }
        .headings_box{
            margin-top: 20px;
        }
        .headings-title{
            background: #f3e9d5;
            color:#ddbc87;
            margin: 0 5px;
            height: 40px;
            line-height: 40px;
            border-radius: 5px;
            padding-left: 20px;
        }
        .headings-item{
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            .item-part{
                text-align: center;
                border: 1px solid #fff;
                background-color: #fbf6f6;
                padding: 10px;
                margin: 10px;
                min-width: 200px;
                width: 23%;
                .icon_img{
                    margin-top: 20px;
                    width: 100%;
                    display: block;
                    height: 100px;
                    color: #d5b374;
                    img{
                        margin: 0 auto;
                        display: block;
                        height:80px;
                        width:80px;
                        border-radius: 5px
                    }
                }
                .name{
                    font-size: 22px;
                    color: #d5b374;
                    line-height: 32px;
                }
                .number{
                    font-size: 12px;
                    padding: 5px;
                    color:#ccc;
                    span{
                        color:#ccc;
                        padding: 0 5px;
                    }
                }
                .introduction{
                    margin-top: 5px;
                    height: 50px;
                    max-height: 50px;
                    width: 100%;
                    font-size: 12px;
                    color:#a7a6a4;
                }
            }
            .all-item{
                position: relative;
                width: 32.33%;
                height: 180px;
                margin: 10px 5px 30px 5px;
                .all-item-box{
                    position: relative;
                    top:0;
                    width:100%;
                    border: 1px solid #eee;
                    border-radius: 5px;
                    clear: both;
                    background-color: #fbf6f6;
                    padding: 10px;
                    z-index: 2;
                }
                .el-card.is-hover-shadow:hover {
                    cursor: pointer;
                    transform:translate(0,-3px);
                    background:#f3e9d5;
                }
                .tuoyuan{
                    width: 100%;
                    position: absolute;
                    bottom: -30px;
                    img{
                        width: 100%;
                    }
                }
                .tuijian{
                    position: absolute;
                    right:-10px;
                    top:-10px;
                }
                .all-msg{
                    clear: both;
                    overflow: hidden;
                    .icon_img{
                        float: left;
                        width: 30%;
                        color: #d5b374;
                        img{
                            margin: 0 auto;
                            height: 80px;
                            width: 80px;
                            border-radius: 5px;
                        }
                    }
                    .icon_r{
                        float: left;
                        width:70%;
                        .name{
                            width:90%;
                            color:#067af3;
                        }
                        .time{
                            font-size: 13px;
                            color:#969799;
                            padding: 4px;
                        }
                        .collection{
                            i{
                                color:red;
                            }
                            span{
                                margin-left: 10px;
                                font-size: 12px;
                                color:#969799;
                            }
                        }
                    }
                }
                .introduction{
                    padding-top: 10px;
                    max-height: 70px;
                    width: 100%;
                    font-size: 12px;
                    color:#a7a6a4;
                }
            }
        }
        .item-part:hover{
            cursor: pointer;
            background-color:#fffaec;
            border: 1px solid #f1dcb1;
        }

    }
</style>