<template>

    <el-form class="login-form"
             status-icon
             :rules="loginRules"
             ref="loginForm"
             :model="loginForm"
             label-width="0">
        <el-form-item prop="username">
            <el-input size="small"
                      @keyup.enter.native="handleLogin"
                      v-model="loginForm.username"
                      auto-complete="off"
                      placeholder="请输入用户名">
                <i slot="prefix"
                   class="icon-yonghu"></i>
            </el-input>
        </el-form-item>
        <el-form-item prop="password">
            <el-input size="small"
                      @keyup.enter.native="handleLogin"
                      :type="passwordType"
                      v-model="loginForm.password"
                      auto-complete="off"
                      placeholder="请输入密码">
                <i class="el-icon-view el-input__icon"
                   slot="suffix"
                   @click="showPassword"></i>
                <i slot="prefix"
                   class="icon-mima"></i>
            </el-input>
        </el-form-item>
        <!-- <el-form-item prop="code">
           <el-row :span="24">
             <el-col :span="16">
               <el-input size="small"
                         @keyup.enter.native="handleLogin"
                         :maxlength="code.len"
                         v-model="loginForm.code"
                         auto-complete="off"
                         placeholder="请输入验证码">
                 <i slot="prefix"
                    class="icon-yanzhengma"></i>
               </el-input>
             </el-col>
             <el-col :span="8">
               <div class="login-code">
                 <span class="login-code-img"
                       @click="refreshCode"
                       v-if="code.type == 'text'">{{code.value}}</span>
                 <img :src="code.src"
                      class="login-code-img"
                      @click="refreshCode"
                      v-else/>
                 &lt;!&ndash; <i class="icon-shuaxin login-code-icon" @click="refreshCode"></i> &ndash;&gt;
               </div>
             </el-col>
           </el-row>

         </el-form-item>-->
        <el-form-item>
            <div>

                <div style="float: left;width: 42%">
                    <el-button type="primary"
                               size="small"
                               @click.native.prevent="handleLogin"
                               class="login-submit">账户登录
                    </el-button>
                </div>
                <div style="float: right;width: 50%">
                    <el-button type="primary"
                               size="small"
                               @click.native.prevent="pkiLogin"
                               class="login-submit">证书登陆
                    </el-button>
                </div>


                <object v-if="userAgent" id="JITDSignOcx" classid="clsid:B0EF56AD-D711-412D-BE74-A751595F3633"
                        codebase="/oci/JITComVCTK_S.cab#version=2,1,1,3"></object>

                <embed v-else id="JITDSignOcx" type="application/x-jit-sign-vctk-s-plugin-boc" height="0px" width="0px"/>
            </div>
        </el-form-item>
    </el-form>

</template>

<script>

    import {randomLenNum} from "@/util/util";
    import {mapGetters} from "vuex";
     let userAgent;

    export default {
        name: "userlogin",
        data() {

            return {
                socialForm: {
                    code: '',
                    state: ''
                },
                pkiForm: {
                    code: '',
                    state: ''
                },
                loginForm: {
                    username: "admin",
                    password: "123456",
                    code: "",
                    redomStr: ""
                },
                JITDSignOcx: [],
                checked: false,
                code: {
                    src: "/code",
                    value: "",
                    len: 4,
                    type: "image"
                },
                loginRules: {
                    username: [
                        {required: true, message: "请输入用户名", trigger: "blur"}
                    ],
                    password: [
                        {required: true, message: "请输入密码", trigger: "blur"},
                        {min: 6, message: "密码长度最少为6位", trigger: "blur"}
                    ]/*,
        code: [
          { required: true, message: "请输入验证码", trigger: "blur" },
          { min: 4, max: 4, message: "验证码长度为4位", trigger: "blur" }
        ]*/
                },
                passwordType: "password"
            };
        },
        watch: {
            $route() {
                const params = this.$route.query
                this.socialForm.state = params.state
                this.socialForm.code = params.code
                if (!validatenull(this.socialForm.state)) {
                    const loading = this.$loading({
                        lock: true,
                        text: `登录中,请稍后。。。`,
                        spinner: 'el-icon-loading'
                    })
                    setTimeout(() => {
                        loading.close()
                    }, 2000)
                    this.handleSocialLogin()
                }
            }
        },
        created() {
            this.refreshCode();
            this.choseBrowser();
        },
        mounted() {
            this.JITDSignOcx = document.getElementById('JITDSignOcx')
        },
        computed: {
            ...mapGetters(["tagWel"])
        },
        props: [],
        methods: {
            choseBrowser(){
                let browser = navigator.userAgent.toLocaleLowerCase();
                if(browser.indexOf("msie") >= 0 || browser.indexOf("trident") >= 0){
                    this.userAgent=true
                }else{
                    this.userAgent=false
                }

            },
            refreshCode() {
                this.loginForm.code = ''
                this.loginForm.randomStr = randomLenNum(this.code.len, true)
                this.code.type === 'text'
                    ? (this.code.value = randomLenNum(this.code.len))
                    : (this.code.src = `${this.codeUrl}?randomStr=${this.loginForm.randomStr}`)
            },
            showPassword() {
                this.passwordType == ''
                    ? (this.passwordType = 'password')
                    : (this.passwordType = '')
            },
            handleSocialLogin() {
                this.$store.dispatch('LoginBySocial', this.socialForm).then(
                    () => {
                        this.$router.push({path: this.tagWel.value});
                    }
                )
            },
            handleLogin() {
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        this.$store.dispatch("LoginByUsername", this.loginForm).then(() => {
                            this.$router.push({path: this.tagWel.value});
                        }).catch(() => {
                            this.refreshCode()
                        })

                    }
                });
            },
            pkiLogin() {
                const loading = this.$loading({
                    lock: true,
                    text: `登录中,请稍后。。。`,
                    spinner: 'el-icon-loading'
                })
                this.initJitOcx(loading)
                if(this.pkiForm.code){

                    this.$store.dispatch("LoginByPki", this.pkiForm).then(() => {
                        this.$router.push({path: this.tagWel.value});
                        setTimeout(() => {
                            loading.close()
                        }, 2000)
                    }).catch(() => {
                        setTimeout(() => {
                            loading.close()
                        }, 2000)
                    });

                }
            },
            msg(msg){
                this.$message({
                    type:'error',
                    dangerouslyUseHTMLString: true,
                    duration:4000,
                    message: msg
                });

            },
            initJitOcx(loading) {
                try {
                    let InitParam = "<?xml version=\"1.0\" encoding=\"gb2312\"?><authinfo><liblist><lib type=\"CSP\" version=\"1.0\" dllname=\"\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib><lib type=\"SKF\" version=\"1.1\" dllname=\"SERfR01DQUlTLmRsbA==\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib><lib type=\"SKF\" version=\"1.1\" dllname=\"U2h1dHRsZUNzcDExXzMwMDBHTS5kbGw=\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib><lib type=\"SKF\" version=\"1.1\" dllname=\"U0tGQVBJLmRsbA==\" ><algid val=\"SHA1\" sm2_hashalg=\"sm3\"/></lib></liblist></authinfo>";
                    this.JITDSignOcx.Initialize(InitParam);
                    if (this.JITDSignOcx.GetErrorCode() != 0) {
                        loading.close()
                        this.msg("初始化失败，错误码：" + this.JITDSignOcx.GetErrorCode() + " 错误信息：" + this.JITDSignOcx.GetErrorMessage(this.JITDSignOcx.GetErrorCode()))
                        this.JITDSignOcx.Finalize();

                        return false;
                    }
                    //控制证书为一个时，不弹出证书选择框
                    this.JITDSignOcx.SetCertChooseType(1);
                    this.JITDSignOcx.SetCert("SC", "", "", "", "", "");
                    if (this.JITDSignOcx.GetErrorCode() != 0) {
                        loading.close()
                        this.msg("错误码：" + this.JITDSignOcx.GetErrorCode() + "　错误信息：" + this.JITDSignOcx.GetErrorMessage(this.JITDSignOcx.GetErrorCode()))
                        this.JITDSignOcx.Finalize();

                        return false;
                    } else {
                        let temp_DSign_Result = this.JITDSignOcx.DetachSignStr("", "ygnet");
                        if (this.JITDSignOcx.GetErrorCode() != 0) {
                            loading.close()
                            this.msg("错误码：" + JITDSignOcx.GetErrorCode() + "　错误信息：" + this.JITDSignOcx.GetErrorMessage(this.JITDSignOcx.GetErrorCode()));
                            this.JITDSignOcx.Finalize();

                            return false;
                        }
                        this.JITDSignOcx.Finalize();
                        this.pkiForm.code=temp_DSign_Result;
                    }
                    loading.close()
                    return false;
                } catch (e) {
                    loading.close()
                   this.msg('PKI证书驱动存在问题、无法获取PKI证书。\n浏览器不兼容证书登录:\n');

                }
            }

        }
    };
</script>

<style>
</style>
