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
                                        <img style="height: 100px;width: 100px" src="/img/people.png" id="imgPerson" />
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
                                    <el-form-item label="单位名称：">
                                        <span v-text="formDetail.dwmc"></span>
                                    </el-form-item>
                                </el-col>

                                <el-col :span="8">
                                    <el-form-item label="单位类别：">
                                        <span v-text="formDetail.rylb"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="体检日期：">
                                        <span v-text="formDetail.tjrq"></span>
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
                                    <el-form-item label="从业状态：">
                                        <span v-text="formDetail.ryzt"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="工作岗位：" >
                                      <span v-text="formDetail.gzgwmc "></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="上岗日期：">
                                        <span v-text="formDetail.sgrq"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                            <el-col>
                                <!-- <el-col :span="8">
                                    <el-form-item label="工作描述：">
                                        <span v-text="formDetail.gzgwms"></span>
                                    </el-form-item>
                                </el-col>-->
                              <!--  <el-col :span="8">
                                    <el-form-item label="健康状态：">
                                        <span v-text="formDetail.jkzk"></span>
                                    </el-form-item>
                                </el-col>-->

                                <el-col :span="8">
                                    <el-form-item label="离岗日期：">
                                        <span v-text="formDetail.lgrq"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="16">
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
                                    <el-form-item label="家庭电话：">
                                        <span v-text="formDetail.jtdh"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="登记人：">
                                        <span v-text="formDetail.djr"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="登记时间：">
                                        <span v-text="formDetail.djsj"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="登记单位：">
                                        <span v-text="formDetail.djdw"></span>
                                    </el-form-item>

                                </el-col>

                            </el-col>
                            <!--<el-col>
                                <el-col :span="8">
                                    <el-form-item label="工作地所属警务区：">
                                        <span v-text="formDetail.gzdjwqmc"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="工作地所属派出所：">
                                        <span v-text="formDetail.gzdpcsmc"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="工作地区县：">
                                        <span v-text="formDetail.gzdqhmc"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>

                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="工作地所属社区居委会：">
                                        <span v-text="formDetail.gzdsqmc"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="户籍地警务区：">
                                        <span v-text="formDetail.hjdjwqmc"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="户籍地派出所：">
                                        <span v-text="formDetail.hjdpcsmc"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>

                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="户籍地省市县：">
                                        <span v-text="formDetail.hjdqhmc"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="户籍地社区：">
                                        <span v-text="formDetail.hjdsqmc"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="现在地警区：">
                                        <span v-text="formDetail.xzdjqmc"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="现在地派出所：">
                                        <span v-text="formDetail.xzdpcsmc"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="现在地区县：">
                                        <span v-text="formDetail.xzdqhmc"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="现在地警区：">
                                        <span v-text="formDetail.xzdjqmc"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>



                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="户籍地警务区：">
                                        <span v-text="formDetail.hjdjwq"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="地址编码：">
                                        <span v-text="formDetail.xzzbm"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="现在地社区名称：">
                                        <span v-text="formDetail.xzdsqmc"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
-->

                            <el-col>
                                <el-col :span="12">
                                    <el-form-item label="户籍详细地址：">
                                        <span v-text="formDetail.hjdxz"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item label="现住址详细地址：">
                                        <span v-text="formDetail.xzdxz"></span>
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


    import {findLabel,getLabel}  from  "@/api/portal/archive/dict/dict";
    import {deepClone} from '@/util/util'
    import {getImg} from "@/api/portal/archive/jingz/placeFile";
    export default {
        name: 'employeeDetail',
        props:["detailRow"],
        data(){
            return {
                dict:{},
                formDetail:{},
                dictPeopleStatusData: [
                    {
                        label: '有效', value: "1"
                    }, {
                        label: '注销', value: '0',
                    }
                ]

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

                findLabel('01', 'jzxt', this.formDetail.xb).then(value => {
                    this.formDetail.xb = value
                }).catch((err) => {
                });
                findLabel('05', 'jzxt', this.formDetail.mz).then(value => {
                    this.formDetail.mz = value
                }).catch((err) => {
                });
                findLabel('wg_03', 'jzxt', this.formDetail.zjzl).then(value => {
                    this.formDetail.zjzl = value
                }).catch((err) => {
                });
                findLabel('za_cyry_rylb', 'jzxt', this.formDetail.rylb).then(value => {
                    this.formDetail.rylb = value
                }).catch((err) => {
                });
                findLabel('04', 'jzxt', this.formDetail.gj).then(value => {
                    this.formDetail.gj = value
                }).catch((err) => {
                });
                findLabel('cyry_009', 'jzxt', this.formDetail.lgyy).then(value => {
                    this.formDetail.lgyy = value
                }).catch((err) => {
                });

                findLabel('02', 'jzxt', this.formDetail.whcd).then(value => {
                    this.formDetail.whcd = value
                }).catch((err) => {
                });

                findLabel('wg_19', 'jzxt', this.formDetail.hyzk).then(value => {
                    this.formDetail.hyzk = value
                }).catch((err) => {
                });
                findLabel('16', 'jzxt', this.formDetail.xx).then(value => {
                    this.formDetail.xx = value
                }).catch((err) => {
                });
                findLabel('08', 'jzxt', this.formDetail.zzmm).then(value => {
                    this.formDetail.zzmm = value
                }).catch((err) => {
                });

                this.formDetail.ryzt=getLabel(this.dictPeopleStatusData,this.formDetail.ryzt)
                getImg(Object.assign({ 'ywbbh':this.formDetail.systemId,'photoName':'1'}, {}),"imgPerson",false)


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