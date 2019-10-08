<template>
    <div class="error403">
        <div class="error403-body-con">
            <el-card class="box-card">
                <div class="error403-body-con-title">
                 
               <img src="/img/mobile/error.png">
                </div>
                <p class="error403-body-con-message">您没有访问该应用的权限<br/>请联系该应用的管理员!</p>
                <!--<div class="error403-btn-con">
                    <el-button @click="goHome" size="large" style="width: 200px;" type="text">返回首页</el-button>
                    <el-button @click="backPage" size="large" style="width: 200px;margin-left: 40px;" type="primary">
                        返回上一页
                    </el-button>
                </div>-->
            </el-card>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ErrorM403",
        methods: {
            backPage() {
                this.$router.go(-1);
            },
            goHome() {
                this.$router.push({
                    path: "/"
                });
            }
        }
    };
</script>
<style lang="scss" scoped>
    @keyframes error403animation {
        0% {
            transform: rotateZ(0deg);
        }
        40% {
            transform: rotateZ(-20deg);
        }
        45% {
            transform: rotateZ(-15deg);
        }
        50% {
            transform: rotateZ(-20deg);
        }
        55% {
            transform: rotateZ(-15deg);
        }
        60% {
            transform: rotateZ(-20deg);
        }
        100% {
            transform: rotateZ(0deg);
        }
    }

    .error403 {
        &-body-con {
            width: 100%;
            height: 500px;
            position: absolute;
            left: 50%;
            top: 40%;
            transform: translate(-50%, -50%);
            &-title {
                text-align: center;
                font-size: 240px;
                font-weight: 700;
                color: #2d8cf0;
                height: 260px;
                line-height: 260px;
            }

            &-message {
                display: block;
                text-align: center;
                font-size: 16px;
                font-weight: 500;
                letter-spacing: 4px;
                color: #409eff;
            }
        }


    }
</style>
