<template>
    <div class="error404">
        <div class="error404-body-con">
            <el-card class="box-card">
                <div class="error404-body-con-title">4
                    <span>0</span>&nbsp;1
                </div>
                <p class="error404-body-con-message">抱歉，您还未授权访问该系统，请联系管理员</p>
                <div class="error404-btn-con">
                    <el-button @click="loginOut" size="large" style="width: 200px;" type="text">退出登录</el-button>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script>
    import {validatenull} from '@/util/validate'
    export default {
        name: "Error401",
        methods: {
            loginOut() {
                this.$store.dispatch("loginOutCas").then((res) => {
                    if(!validatenull(res)){
                        window.location = res
                    }
                }).catch((e) => {
                });
            }
        }
    };
</script>
<style lang="scss" scoped>
    @keyframes error404animation {
        0% {
            transform: rotateZ(0deg);
        }
        20% {
            transform: rotateZ(-60deg);
        }
        40% {
            transform: rotateZ(-10deg);
        }
        60% {
            transform: rotateZ(50deg);
        }
        80% {
            transform: rotateZ(-20deg);
        }
        100% {
            transform: rotateZ(0deg);
        }
    }

    .error404 {
        &-body-con {
            width: 700px;
            height: 500px;
            position: absolute;
            left: 45%;
            top: 50%;
            transform: translate(-50%, -50%);

            &-title {
                text-align: center;
                font-size: 240px;
                font-weight: 700;
                color: #2d8cf0;
                height: 260px;
                line-height: 260px;

                span {
                    display: inline-block;
                    color: #19be6b;
                    font-size: 230px;
                    animation: error404animation 3s ease 0s infinite alternate;
                }
            }

            &-message {
                display: block;
                text-align: center;
                font-size: 22px;
                font-weight: 500;
                color: #dddde2;
            }
        }

        &-btn-con {
            text-align: center;
            padding: 20px 0;
            margin-bottom: 40px;
        }
    }
</style>
