<template>
    <main>
        <div class="find">
            <van-cell is-link to="/mobile/simpleQuery" color="red">
                <template slot="title">
                    <van-icon name="records" class="icon_img" color="#196cf3" size="1.7rem"/>
                    <span class="cell_title">特行场所基本信息</span>
                </template>
            </van-cell>
            <van-cell is-link to="/mobile/employee/index" color="red">
                <template slot="title">
                    <van-icon name="manager" class="icon_img" color="#196cf3" size="1.7rem"/>
                    <span class="cell_title">特行场所从业人员基本信息</span>
                </template>
            </van-cell>
            <van-cell is-link to="/mobile/ajQuery/index" color="red">
                <template slot="title">
                    <van-icon name="tosend" class="icon_img" color="#4fcc6a" size="1.7rem"/>
                    <span class="cell_title">特行场所案件查询</span>
                </template>
            </van-cell>
            <van-cell is-link to="/mobile/simpleQuery/event" color="red">
                <template slot="title">
                    <van-icon name="send-gift-o" class="icon_img" color="#4fcc6a" size="1.7rem"/>
                    <span class="cell_title">特行场所事件查询</span>
                </template>
            </van-cell>
            <van-cell is-link color="red">
                <template slot="title">
                    <van-icon name="location-o" class="icon_img" color="#4fcc6a" size="1.7rem"/>
                    <span class="cell_title">特行场所地图查询</span>
                </template>
            </van-cell>
            <!--<van-cell is-link color="red">
                <template slot="title">
                    <van-icon name="flag-o" class="icon_img" color="#196cf3" size="1.7rem"/>
                    <span class="cell_title">指令查询</span>
                </template>
            </van-cell>-->
        </div>
    </main>
</template>

<script>
    export default {
        name: "find",
        data(){
            return{

            }
        }
    }
</script>

<style lang="scss">
    main{
        overflow: hidden;
        box-sizing: border-box;
        padding: 0 0.625rem;
        width: 100%;
        position: absolute;
        top: 10rem;
        left: 0;
        height: 27.3125rem;
        border-radius: 0.5rem;
    }
    .find{
        border-radius: 0.5rem;
        border-radius: 0.5rem;
        overflow:auto;
        height: 27.3125rem;
        width:100%;
        .van-cell{
            border-bottom: 0.0625rem solid #ccc;
            position: static;
        }
        .icon_img{
            vertical-align: -25%;
            margin-right: 0.625rem;
            font-size: 1.25rem;
        }
        .cell_title{
            font-size:18px;
        }
    }
</style>