<template>
    <a href="#" >
    <span  class="isshouc" :class="{'main-title-ysc':ysc,'main-title-wsc':wsc}" @click="getIsGz(1)"><i class="iconfontlogo alicon_shoucang"></i>{{this.issc}}</span>
    </a>
</template>

<script>
    import {IsGz}from "@/api/portal/archive/csgz/csgz";
    export default {
        name: "Csgz",
        props: ["param"],
        data(){
            return{
                issc:'',
                wsc:false,
                ysc:false,
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
            getIsGz(isgz) {
                IsGz(Object.assign({
                    isgz:isgz
                },this.params)).then(response => {
                    var is =  response.data.data
                    if (is>0){
                        this.ysc = true
                        this.wsc = false
                        this.issc="已收藏"
                    } else {
                        this.wsc = true
                        this.ysc= false
                        this.issc="未收藏"
                    }
                })
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