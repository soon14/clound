<template>
    <div>
        <van-nav-bar title="从业人员信息">
            <van-icon name="arrow-left" slot="left" size="20px" @click="back"/>
            <van-icon name="wap-home" slot="right" size="20px" @click="toIndex"/>
        </van-nav-bar>

        <van-dropdown-menu>
            <van-dropdown-item v-model="paramsForm.sourceType" :options="tabsOption" @change="getList"/>
            <van-dropdown-item title="筛选" ref="item">
                <van-field
                        v-model="paramsForm.name"
                        clearable
                        label="姓名"
                        placeholder="请输入姓名"
                />
                <van-field
                        v-model="paramsForm.zjhm"
                        clearable
                        label="身份证号"
                        placeholder="请输入身份证号"
                />
                <van-field
                        v-model="paramsForm.dwmc"
                        clearable
                        label="单位名称"
                        placeholder="请输入单位名称"
                />
                <van-field
                        readonly
                        clickable
                        label="行业类型"
                        :value="paramsForm.hylxLabel"
                        placeholder="请选择行业类型"
                        @click="showHylxChoose=true"
                        v-show="useHylx"
                />
                <van-popup v-model="showHylxChoose" position="bottom">
                    <van-picker
                            show-toolbar
                            :columns="hylxData"
                            @cancel="showHylxChoose=false"
                            @confirm="onConfirmHylx"
                    />
                </van-popup>
                <van-field
                        readonly
                        clickable
                        label="从业状态"
                        :value="paramsForm.ryztLabel"
                        placeholder="请选择从业状态"
                        @click="showRyztChoose=true"
                        v-show="useRyzt"
                />
                <van-popup v-model="showRyztChoose" position="bottom">
                    <van-picker
                            show-toolbar
                            :columns="cyztDict"
                            @cancel="showRyztChoose=false"
                            @confirm="onConfirmCyzt"
                    />
                </van-popup>

                <van-field
                        readonly
                        clickable
                        label="人员类型"
                        :value="paramsForm.tagTypeLabel"
                        placeholder="请选择人员类型"
                        @click="showTagTypeChoose=true"
                />
                <van-popup v-model="showTagTypeChoose" position="bottom">
                    <van-picker
                            show-toolbar
                            :columns="rylxDict"
                            @cancel="showTagTypeChoose=false"
                            @confirm="onConfirmTagType"
                    />
                </van-popup>
                <van-button block type="info" @click="onsubmit">确认</van-button>
            </van-dropdown-item>
        </van-dropdown-menu>

        <van-list v-model="loading"
                  :finished="finished"
                  finished-text="没有更多了..."
                  @load="onLoadList">
            <!-- 警综 -->
            <van-card v-for="item in list"
                      thumb="/img/person.png"
                    @click="detail(item)">
                <div slot="title" class="van-card__title">
                    <font class="card_name">{{item.name}} </font><font class="card_zjhm">   {{item.zjhm}}</font>
                    <div class="card_tag">
                        <van-tag plain type="primary" v-if="!validatenull(item.hylx)">{{findLabelByValue(hylxDict,item.hylx)}}</van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(item.rylx)">{{item.rylx}}</van-tag>
                        <van-tag plain type="primary" v-if="!validatenull(item.sgrq)">{{item.sgrq}}</van-tag>
                    </div>
                </div>
                <div slot="desc" class="van-card__desc">
                    <!--<div><font color="black">{{item.zjhm}}</font></div>-->
                    <div>
                        <img src="/img/mobile/name.png">
                        <font>   {{item.dwmc}}</font></div>
                    <div>
                        <img src="/img/mobile/location.png" v-if="!validatenull(item.dwdz)">
                        <font v-if="!validatenull(item.dwdz)">   {{item.dwdz}}</font></div>
                </div>
            </van-card>
        </van-list>
    </div>
</template>

<script>

    import {findLabelByValue} from "@/util/util";
    import {remote}  from  "@/api/admin/dict";
    import {employeeListBySourceType} from "@/api/portal/statistics/employee/employee";
    import {mapGetters} from "vuex";
    import {validatenull} from '@/util/validate';
    import {fetchArea} from '@/api/portal/archive/common/query';
    import {ylcsDict}  from  "@/const/crud/portal/archive/ylcs/ylcsDict";
    import {getDict,getLabel} from "@/api/portal/archive/dict/dict";


    export default {
        data() {
            return {
                loading:false,
                finished:false,
                showTagTypeChoose:false,
                showRyztChoose:false,
                showHylxChoose:false,
                useRyzt:true,
                useHylx:true,
                paramsForm:{
                    sourceType:0,
                    name:'',
                    zjhm:'',
                    tagType:'',
                    tagTypeLabel:'',
                    ryzt:'',
                    ryztLabel:'',
                    dwmc:'',
                    hylx:'',
                    hylxLabel:'',
                },
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                list:[],
                tabsOption:[],
                hylxDict:[],
                rylxDict:[],
                cyztDict:[],
                hylxData:[],
            }
        },
        activated(){
            if(!this.$route.meta.isBack){
            }else{
                this.getTabs();
                this.initDict();
                this.getList();
            }
            this.$route.meta.isBack=false;
        },
        beforeRouteEnter(to, from, next){
            if(
                from.path=='/mobile/employee/jz/detail'){
                to.meta.isBack=true;
            }
            next();
        },
        computed: {
            ...mapGetters(["permissions"])
        },
        methods:{
            back(){
                history.go(-1);
            },
            toIndex(){
                this.$router.replace("/mobile/find");
            },
            getTabs(){
                remote('unit_source_type').then(response => {
                    let datas = response.data.data||[]
                    let arr = [];
                    for (let i=0;i<datas.length;i++){
                        let tmp = datas[i];
                        if(tmp.value!='4' && tmp.value!=4){
                            let json = {text:tmp.label,value:tmp.value};
                            arr.push(json);
                            if(i==0){
                                this.paramsForm.sourceType = tmp.value;
                            }
                        }
                    }
                    this.tabsOption = arr;
                }).catch((err) => {});
            },
            getList(){
                this.list = [];
                if (this.paramsForm.sourceType=='2'){
                    this.useRyzt = false;
                    this.useHylx = false;
                } else{
                    this.useRyzt = true;
                    this.useHylx = true;
                }
                this.paramsForm.name='';
                this.paramsForm.zjhm='';
                this.paramsForm.tagType='';
                this.paramsForm.tagTypeLabel='';
                this.paramsForm.ryzt='';
                this.paramsForm.ryztLabel='';
                this.paramsForm.dwmc='';
                this.paramsForm.hylx='';
                this.paramsForm.hylxLabel='';
                this.page.total = 0;
                this.page.currentPage = 1;
                this.page.pageSize = 10;
                this.finished = false;
                setTimeout(this.loadData(),100)
            },
            loadData(){
                this.loading = true;
                employeeListBySourceType(Object.assign({
                    current: this.page.currentPage,
                    size: this.page.pageSize
                }, this.paramsForm)).then(response=>{
                    let datas = response.data.data.records;
                    this.page.total = response.data.data.total
                    this.page.currentPage = response.data.data.current;
                    this.page.pageSize = response.data.data.size;
                    /*this.list = [];*/
                    for (let i=0;i<datas.length;i++){
                        this.list.push(datas[i]);
                    }
                    this.loading = false;
                    let index = (this.page.currentPage-1)*this.page.pageSize+datas.length;
                    if(index>=this.page.total){
                        this.finished = true;
                    }
                }).catch(()=>{})
            },
            initDict:function () {
                remote("HYLX_DL").then(response=>{
                    this.hylxDict = response.data.data;
                    let data = response.data.data;
                    let arr = [];
                    let empty = {text:'不限',value:''}
                    arr.push(empty);
                    for (let i=0;i<data.length;i++){
                        let tmp = data[i];
                        let json = {text:tmp.label,value:tmp.value};
                        arr.push(json);
                    }
                    this.hylxData = arr;
                }).catch((err) => {});
                remote('QLRYLX').then(response=>{
                    let data = response.data.data;
                    let arr = [];
                    let empty = {text:'不限',value:''}
                    arr.push(empty);
                    for (let i=0;i<data.length;i++){
                        let tmp = data[i];
                        let json = {text:tmp.label,value:tmp.value};
                        arr.push(json);
                    }
                    this.rylxDict = arr;
                }).catch(err=>{});

                if(this.paramsForm.sourceType=='1'){
                    let data = ylcsDict.ryzt;
                    let arr = [];
                    let empty = {text:'不限',value:''}
                    arr.push(empty);
                    for (let i=0;i<data.length;i++){
                        let tmp = data[i];
                        let json = {text:tmp.label,value:tmp.value};
                        arr.push(json);
                    }
                    this.cyztDict = arr;
                }else if(this.paramsForm.sourceType == '3'){
                    getDict('RYZT','ly').then(response=>{
                        let data = response.data.data||[];
                        let arr = [];
                        let empty = {text:'不限',value:''}
                        arr.push(empty);
                        for (let i=0;i<data.length;i++){
                            let tmp = data[i];
                            let json = {text:tmp.label,value:tmp.value};
                            arr.push(json);
                        }
                        this.cyztDict = arr;
                    })
                }else{
                    getDict('cyry_zt','jzxt').then(response => {
                        let data = response.data.data||[];
                        let arr = [];
                        let empty = {text:'不限',value:''}
                        arr.push(empty);
                        for (let i=0;i<data.length;i++){
                            let tmp = data[i];
                            let json = {text:tmp.label,value:tmp.value};
                            arr.push(json);
                        }
                        this.cyztDict = arr;
                    }).catch((err) => {});
                }

            },
            detail(row){
                let sourceType = row.sourceType;
                if(sourceType=='1'){
                    //娱乐
                    this.$router.push({
                        path: this.$router.$avueRouter.getPath({
                            src: "/mobile/employee/yl/detail"
                        }),
                        query: {
                            systemId: row.systemId,
                        }
                    });
                }else if(sourceType=='2'){
                    //机修
                    this.$router.push({
                        path: this.$router.$avueRouter.getPath({
                            src: "/mobile/employee/jx/detail"
                        }),
                        query: {
                            systemId: row.systemId,
                        }
                    });
                }else if(sourceType=='3'){
                    //旅馆
                    this.$router.push({
                        path: this.$router.$avueRouter.getPath({
                            src: "/mobile/employee/ly/detail"
                        }),
                        query: {
                            systemId: row.systemId,
                            code:row.gxqh.substring(0,4)
                        }
                    });
                }else {
                    //警综
                    this.$router.push({
                        path: this.$router.$avueRouter.getPath({
                            src: "/mobile/employee/jz/detail"
                        }),
                        query: {
                            systemId: row.systemId,
                        }
                    });
                }
            },
            onConfirmTagType(data){
                this.paramsForm.tagTypeLabel = data.text;
                this.paramsForm.tagType = data.value;
                this.showTagTypeChoose = false;
            },
            onConfirmCyzt(data){
                this.paramsForm.ryztLabel = data.text;
                this.paramsForm.ryzt = data.value;
                this.showRyztChoose = false;
            },
            onConfirmHylx(data){
                this.paramsForm.hylxLabel = data.text;
                this.paramsForm.hylx = data.value;
                this.showHylxChoose = false;
            },
            onsubmit(){
                this.list=[];
                this.$refs.item.toggle();
                this.loadData();
            },
            onLoadList(){
                this.page.currentPage++;
                this.loadData();
            }
        }
    }
</script>

<style lang="scss">
    .van-card__title{
        margin: 5px 0px;
        max-height: 50px;
    }
    .card_name{
        font-size: 16px;
        font-weight: 600;
    }
    .card_zjhm{
        color:#d1cece;
        font-size: 12px;
        font-weight: 200;
    }
    .van-tag{
        margin-right: 5px;
        margin-top: 3px;
        padding: 5px;
    }
    .card_tag{
        height: 28px;
    }
</style>