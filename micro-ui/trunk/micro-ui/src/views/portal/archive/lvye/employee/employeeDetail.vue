<template>
    <div class="execution">
        <basic-container>
            <div class="avue-detail">
                <div class="avue-group avue-group--card">
                    <div class="el-row avue-group__item" span="24">
                        <div class="avue-group__header">
                            <i class="el-icon-info avue-group__icon">
                            </i><h1 class="avue-group__title">基本信息</h1>
                        </div>
                        <el-form class="form-main">
                            <el-col>
                                <el-col :span="6">
                                    <el-form-item label="姓名：">
                                        <span v-text="formDetail.xm"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="性别：">
                                       <span v-text="formDetail.xb"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="民族：">
                                        <span v-text="formDetail.mz"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6" >
                                    <el-form-item label="" >
                                        <span style="position: absolute;float: right">
                                            <viewer>
                                        <img style="height: 100px;width: 100px" id="imgPerson"/>
                                                </viewer>
                                        </span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="6">
                                    <el-form-item label="出生日期：">
                                        <span v-text="formDetail.csrq"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="6">
                                    <el-form-item label="血型：">
                                        <span v-text="formDetail.xx"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item label="身高：">
                                        <span v-text="formDetail.sg"></span>
                                    </el-form-item>
                                </el-col>




                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="英文名：">
                                        <span v-text="formDetail.ywx"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="英文姓：">
                                        <span v-text="formDetail.ywx"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="婚姻状况：">
                                        <span v-text="formDetail.hyzk"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="文化程度：">
                                        <span v-text="formDetail.whcd"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="证件种类：">
                                        <span v-text="formDetail.zjzl"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="证件号码：">
                                        <span v-text="formDetail.zjhm"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="单位类别：">
                                        <span v-text="formDetail.ssdwlb"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="居住证有效期：">
                                        <span v-text="formDetail.jzzyxq"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="居住证：">
                                        <span v-text="formDetail.jzz"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="职务：">
                                        <span v-text="formDetail.zw"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="国籍：">
                                        <span v-text="formDetail.gj"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="政治面貌：">
                                        <span v-text="formDetail.zzmm"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="人员类别：">
                                        <span  v-text="formDetail.rylb"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="从业状态：">
                                        <span v-text="formDetail.ryzt"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="工作岗位：" >
                                      <span v-text="formDetail.gzgw "></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="证件编号：">
                                        <span v-text="formDetail.sgzjbh"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="上岗日期：">
                                        <span v-text="formDetail.sgrq"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="离岗日期：">
                                        <span v-text="formDetail.lgrq"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>

                            <el-col>
                                <el-col :span="24">
                                    <el-form-item label="离岗原因：">
                                        <span v-text="formDetail.lgyy"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                        </el-form>


                    </div></div>
                <div class="avue-group avue-group--card">
                    <div class="el-row avue-group__item" span="24">
                        <div class="avue-group__header">
                            <i class="el-icon-info avue-group__icon">
                            </i><h1 class="avue-group__title">联系信息</h1>
                        </div>
                        <el-form class="form-main">
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="联系方式1：">
                                        <span v-text="formDetail.lxfs1"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="联系方式2：">
                                        <span v-text="formDetail.lxfs2"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="联系方式3：">
                                        <span v-text="formDetail.lxfs3"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="紧急联系人：">
                                        <span v-text="formDetail.jjlxr"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="紧急联系人电话：">
                                        <span v-text="formDetail.jjlxrdh"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">

                                </el-col>

                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="登记人：">
                                        <span v-text="formDetail.lrr"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="登记时间：">
                                        <span v-text="formDetail.lrsj"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">

                                </el-col>

                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="户籍地区号：">
                                        <span v-text="formDetail.hjdqh"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="现住址编码：">
                                        <span v-text="formDetail.xzzbm"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="现住址区号：">
                                        <span v-text="formDetail.xzzqh"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                            <el-col>
                                <el-col :span="24">
                                    <el-form-item label="户籍地详细地址：">
                                        <span v-text="formDetail.hjdxz"></span>
                                    </el-form-item>
                                </el-col>


                            </el-col>
                            <el-col>
                                <el-col :span="24">
                                    <el-form-item label="现住址详细地址：">
                                        <span v-text="formDetail.xzzxz"></span>
                                    </el-form-item>
                                </el-col>


                            </el-col>
                            <el-col>
                                <el-col :span="24">
                                    <el-form-item label="备注：">
                                        <span v-text="formDetail.bz"></span>
                                    </el-form-item>
                                </el-col>


                            </el-col>
                        </el-form>


                    </div></div>
            </div>
        </basic-container>
    </div>
</template>


<script>

    import { getImg} from "@/api/portal/archive/lvye/employee";
    import {findLabel}  from  "@/api/portal/archive/dict/dict";
    import {deepClone} from '@/util/util'
    export default {
        name: 'employeeDetail',
        props:["detailRow"],
        data(){
            return {
                dict:{},

                formDetail:{},

            }
        },
        created(){
            //通过复制对象、防止该内容改变导致整块改变
            this.formDetail=deepClone(this.detailRow)
            this.initData()
        },

        watch: {
            detailRow: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.formDetail=deepClone(this.detailRow)
                    this.initData()
                },
                deep: true
            },
        },
        methods: {

            initData() {
                findLabel('RYZT', 'ly', this.formDetail.ryzt).then(value => {
                    this.formDetail.ryzt = value
                }).catch((err) => {
                });
                findLabel('RYLB', 'ly', this.formDetail.rylb).then(value => {
                    this.formDetail.rylb = value
                }).catch((err) => {
                });
                findLabel('0122', 'ly', this.formDetail.xb).then(value => {
                    this.formDetail.xb = value
                }).catch((err) => {
                });
                findLabel('0201', 'ly', this.formDetail.mz).then(value => {
                    this.formDetail.mz = value
                }).catch((err) => {
                });
                findLabel('WHCD', 'ly', this.formDetail.whcd).then(value => {
                    this.formDetail.whcd = value
                }).catch((err) => {
                });
                findLabel('0999', 'ly', this.formDetail.gj).then(value => {
                    this.formDetail.gj = value
                }).catch((err) => {
                });
                findLabel('0203', 'ly', this.formDetail.zjzl).then(value => {
                    this.formDetail.zjzl = value
                }).catch((err) => {
                });

                findLabel('0129', 'ly', this.formDetail.hjdqh).then(value => {
                    this.formDetail.hjdqh = value
                }).catch((err) => {
                });
                findLabel('0129', 'ly', this.formDetail.xzzqh).then(value => {
                    this.formDetail.xzzqh = value
                }).catch((err) => {
                });
                getImg(this.formDetail.psid, this.formDetail.city, 'imgPerson')

            },

        }
    }
</script>

<style>
    .el-col{
        margin: 0px !important;
    }
    .el-form-item{
        margin-bottom: 10px!important;
    }
</style>