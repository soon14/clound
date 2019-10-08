<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-from :option="option" v-model="modelEditFrom">
                <template slot="iconForm" slot-scope="scope">
                            <img style="height: 100px;width: 100px" id="icon_img"/>
                </template>
            </avue-from>
        </basic-container>
    </div>
</template>

<script>
    import {findPage} from '@/api/portal/judged/model/modelCollect'
    import {getIconList} from '@/api/portal/judged/model/modelCollect'
    import {routerJump} from '@/api/portal/judged/model/routerJump'
    import {tableOption} from '@/const/crud/portal/judged/model/modelCollect';
    export default {
        name:"modelEdit",
        props:["paramForm"],
        data(){
            return{
                addImgMap: new Map(),
                modelEditFrom: {},
                searchForm: {
                    modelId:'',
                },
                option: tableOption,
            }
        },
        created() {
            this.searchForm.modelId = this.paramForm.modelId
            this.getList({modelId:this.searchForm.modelId,type:'3',})
            this.initIcon(this.searchForm.modelId,'icon')
        },
        methods:{
            appClick(data){
                routerJump(data)
            },
            closeDialog(){

            },
            initIcon(id,imgid) {
                let img=this.addImgMap.get(id);
                if('undefined'==typeof (img)||''===img||null===img) {
                    getIconList(id,imgid).then(value => {
                        this.addImgMap.set(id,value);
                        let imgTag =document.getElementById(imgid);
                        imgTag.src=value
                    })
                }else{
                    let imgTag =document.getElementById(imgid);
                    imgTag.src=img
                }
            },
            getList(param) {
                this.addImgMap.clear()
                findPage(param).then(response => {
                    this.modelEditFrom = response.data.data.records[0]
                })
            },
        },
        watch:{
            paramForm:{
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler () {
                    this.searchForm.modelId = this.paramForm.modelId
                    this.getList({modelId:this.searchForm.modelId,type:'3',})
                    this.initIcon(this.searchForm.modelId,'icon')
                },
                deep:true
            },

        },
    }
</script>
<style>

</style>
