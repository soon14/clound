<template>
    <a href="#"  v-loading="collectLoading">
    <span  class="isshouc" :class="{'main-title-ysc':ysc,'main-title-wsc':wsc}" @click="getIsGz(1)"><i class="iconfontlogo alicon_shoucang"></i>{{this.issc}}</span>
    </a>
</template>

<script>
    import {IsGz}from "@/api/portal/archive/csgz/csgz";
    import { validatenull } from "@/util/validate";
    export default {
        name: "Csgz",
        props: ["param"],
        data(){
            return{
                issc:'',
                wsc:false,
                ysc:false,
                collectLoading:false,
                params:{
                    dwbh:'',
                    lyxt:''
                },
            }
        },
        created(){
            this.params.dwbh = this.param.dwbh;
            this.params.lyxt = this.param.lyxt;
            this.getIsGz( )
        },
        computed: {

        },
        watch: {
            param: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.params.dwbh = this.param.dwbh;
                    this.params.lyxt = this.param.lyxt;
                    this.getIsGz()
                },
                deep: true
            },
        },
        methods:{
            msg(title,type){
                this.$message({
                    type:type,
                    dangerouslyUseHTMLString: true,
                    duration:4000,
                    message: title
                });
            },
            getIsGz(isgz) {
                this.collectLoading=true
                IsGz(Object.assign({
                    isgz:isgz
                },this.params)).then(response => {
                    let is =  response.data.data
                    if (is>0){
                        this.ysc = true
                        this.wsc = false
                        this.issc="已收藏"
                        if(!validatenull(isgz)){
                            this.msg("收藏成功","success")
                        }

                    } else {
                        this.wsc = true
                        this.ysc= false
                        this.issc="未收藏"
                        if(!validatenull(isgz)){
                            this.msg("取消收藏成功","success")
                        }
                    }
                    this.collectLoading=false
                }).catch((err) => {
                    this.collectLoading=false
                    this.msg("操作失败","error")
                });
            },
        }
    }
</script>

<style lang="scss">
    .info-cate{
        .form-main{
            overflow: hidden;
            font-size: 15px;
            .form-main-title{
                height: 60px;
                line-height: 60px;
                font-size: 25px;
                color: #067af3;
                font-weight: bold;
                padding-left: 10px;
                .main-title-wsc{
                    color:#CDD;
                    //   background-color:#feedd5;
                    padding: 5px 10px;
                    font-size: 12px;
                    margin-right: 10px;

                }
                .main-title-ysc{
                    color:#ff9913;
                    //    background-color:#feedd5;
                    padding: 5px 10px;
                    font-size: 12px;
                    margin-right: 10px;

                }

            }
        }
    }

</style>