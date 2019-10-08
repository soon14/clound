<template>
    <div class="mobilePage">
        <router-view/>
        <div class="main_box">
            <header class="header">
                <div class="avatar">
                    <div class="avatar_box"><img src="../../../../public/img/logo.png" alt=""></div>
                </div>
                <div class="nick">
                    <p>张三</p>
                    <p>ID:123456</p>
                </div>
                <!--<div class="edit">
                    <van-button round  type="default" icon="edit" size="small">编辑</van-button>
                </div>-->
            </header>
            <van-tabbar v-model="active"  route>
                <van-tabbar-item icon="home-o"
                                 replace
                                 to="/mobile/peosoncenter">个人中心</van-tabbar-item>
                <van-tabbar-item icon="search"
                                 replace
                                 to="/mobile/find">简项查询</van-tabbar-item>
            </van-tabbar>
        </div>
    </div>
</template>

<script>
    import '../../../../public/cdn/vant/2.1.7/vant.min'
    import "vant/lib/index.css";
    import "vant/lib/icon/local.css";
    export default {
        name: "mainpage",
        data() {
            return {
                active:0,
            }
        },
        beforeRouteLeave(to,from,next){
            if(to.path=="/mobile/simpleQuery"||
                to.path=="/mobile/employee/index"||
                to.path=="/mobile/simpleQuery/event"){
                to.meta.isBack=true;
            }
            next()
        }
    }
</script>

<style lang="scss">
    .mobilePage{
        /*注：1rem=16px*/
        box-sizing: border-box;
        height: 100%;
        background:url("../../../../src/mobile/img/bg.png")no-repeat top ;
        background-size:100%  ;
        background-color: #f2f2f2;
        padding: 0 0.625rem;
        .main_box{
            width:100%;
            height: 100%;
            .header{
                height:10rem;
                width:100%;
                overflow: hidden;
                display: flex;
                flex-direction: row;
                justify-content:center;
                padding-top:3.125rem;
                .avatar{
                    flex: 1;
                    align-content: center;
                    height:6.25rem;
                    .avatar_box{
                        width:6.25rem;
                        height:6.25rem;
                        line-height: 6.25rem;
                        text-align: center;
                        margin:auto;
                        border-radius: 50%;
                        img{
                            background: #fff;
                            box-shadow: 0rem 0rem 0.25rem 0.125rem #ccc;
                            line-height: 5.875rem;
                            margin:auto;
                            display: block;
                            width:5rem;
                            height:5rem;
                            border:1px solid #ccc;
                            border-radius: 50%;
                        }
                    }
                }
                .nick{
                    flex: 3;
                    height:6.25rem;
                    padding-top: 0.9375rem;
                    padding-left: 0.625rem;
                    p:nth-child(1){
                        color:#fff;
                        font-weight: bold;
                    }
                    p:nth-child(2){
                        color:#aac0fe;
                        font-weight: bold;
                    }
                }
                .edit{
                    height:6.25rem;
                    flex: 2;
                    line-height:4.375rem;
                    text-align: center;
                    .van-button--small {
                        min-width: 4.375rem !important;
                    }
                    .van-button--default {
                        color: #fff !important;
                        background-color: #1a41c2 !important;
                        border: 1px solid #1a41c2 !important;
                    }
                }
            }
            .header>div{
                float:left;
            }
            .main{
                height: 27.3125rem;
                border-radius: 0.5rem;
                // overflow: auto;
            }
        }
    }
</style>