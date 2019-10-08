<template>
    <div></div>
</template>
<script>
    import {mapGetters} from "vuex";
    import {vaildatePc, validatenull} from '@/util/validate'

    let userAgent;
    export default {
        name: "mobileLogin",
        data() {
            return {
                wxForm: {
                    aCode: undefined,
                    idCode: undefined,
                    code: undefined,
                    type: undefined,
                },
            };
        },
        watch: {
            /* $route() {
                 const params = this.$route.query
                 this.wxForm.acode = params.acode
                 if (!validatenull(this.wxForm.acode)) {
                     const loading = this.$loading({
                         lock: true,
                         text: `登录中,请稍后。。。`,
                         spinner: 'el-icon-loading'
                     })
                     setTimeout(() => {
                         loading.close()
                     }, 2000)
                     this.handleLogin()
                 }
             }*/
        },
        created() {

            this.login()
        },
        mounted() {

        },
        computed: {
            ...mapGetters(["tagWel"])
        },
        props: [],
        methods: {
            login() {
                try {
                    const params = this.$route.query
                    this.wxForm.aCode = params.acode
                    this.wxForm.idCode = params.idcode
                    //todo 测试环境 方便测试 生产环境应该把这句给删除掉
                    this.wxForm.idCode="330781198509073456"
                    if (validatenull(this.wxForm.aCode) && validatenull(this.wxForm.idCode)) {
                        this.$router.push({path: "/M403"});
                        this.$message.error("参数丢失-[acode=null]");
                        return
                    }
                    if (vaildatePc()) {
                        this.$router.push({path: "/login"});
                    } else {
                        if (!validatenull(this.wxForm.idCode)) {
                            this.wxForm.code = this.wxForm.idCode
                            this.wxForm.type = "idCode"
                        } else {
                            this.wxForm.code = this.wxForm.aCode
                            this.wxForm.type = "weChat"
                        }
                        const loading = this.$loading({
                            lock: true,
                            text: `跳转中,请稍后。。。`,
                            spinner: 'el-icon-loading'
                        })
                        this.$store.dispatch("loginByCode", this.wxForm).then(() => {
                            this.$router.push({path: this.tagWel.value});
                            setTimeout(() => {
                                loading.close()
                            }, 2000)
                        }).catch((e) => {
                            console.log(e)
                            setTimeout(() => {
                                this.$router.push({path: "/M403"});
                                loading.close()
                            }, 2000)
                        });
                    }
                } catch (e) {
                    this.$router.push({path: "/M403"});
                }
            },


        }
    };
</script>

<style>
</style>
