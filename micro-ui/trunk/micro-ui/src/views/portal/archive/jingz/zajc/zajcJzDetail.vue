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
                                <el-col :span="8">
                                    <el-form-item label="检查单位：">
                                        <span v-text="formDetail.jcdw"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="检查时间：">
                                       <span v-text="formDetail.jcsj"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="检查方式：">
                                        <span v-text="formDetail.jcfs"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="检查人员：">
                                        <span v-text="formDetail.jcr"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="受检单位：">
                                        <span v-text="formDetail.bjcdw"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="管辖行业类型：">
                                        <span v-text="dictLabel(hylxData,formDetail.gxhylx)"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="受检单位当值负责人：">
                                        <span v-text="formDetail.qydzfzr"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                        </el-form>
                    </div></div>

                <div class="avue-group avue-group--card">
                    <div class="el-row avue-group__item" span="24">
                        <div class="avue-group__header">
                            <i class="el-icon-info avue-group__icon">
                            </i><h1 class="avue-group__title">检查详情</h1>
                        </div>
                        <el-table :data="checkData"
                                  border
                                  style="width: 100%"
                        >
                            <el-table-column
                                    label="检查内容"
                                    prop="jcxmname"
                                    align="center"
                                    width="307">
                            </el-table-column>

                            <el-table-column
                                    label="检查情况"
                                    width="220"
                                    align="center">
                                <template slot-scope="scope" >
                                    <div v-text="getJcqk(scope.row.checkState)"></div>
                                </template>
                            </el-table-column>

                            <el-table-column
                                    label="详细情况"
                                    prop="nrmkname"
                                    width="640"
                                    align="center">
                            </el-table-column>
                        </el-table>
                    </div></div>

                <div class="avue-group avue-group--card">
                    <div class="el-row avue-group__item" span="24">
                        <div class="avue-group__header">
                            <i class="el-icon-info avue-group__icon">
                            </i><h1 class="avue-group__title">检查结果</h1>
                        </div>
                        <el-form class="form-main">
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="处理意见：">
                                        <span v-text="formDetail.clyj"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="处理结果：">
                                        <span v-text="formDetail.cljg"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="限期整改：">
                                        <span v-text="formDetail.xqzgrq"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col v-show="containStr(formDetail.gxhylx,placeEnum.GWYLCS.value,placeEnum.AMFWCS.value,placeEnum.YXYYCS.value,placeEnum.LXFYCS.value)">
                                <el-col :span="8">
                                    <el-form-item label="当前分数：">
                                        <span v-text="formDetail.dqfs"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="扣分分数：">
                                        <span v-text="formDetail.kffs"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="扣分情况：">
                                        <span v-text="formDetail.kfqk"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="24">
                                    <el-form-item label="其他情况：">
                                        <span v-text="formDetail.qt"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item label="备注：">
                                        <span v-text="formDetail.bz"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                        </el-form>
                    </div></div>

                <div class="avue-group avue-group--card" v-show="false">
                    <div class="el-row avue-group__item" span="24">
                        <div class="avue-group__header">
                            <i class="el-icon-info avue-group__icon">
                            </i><h1 class="avue-group__title">其他</h1>
                        </div>
                        <el-form class="form-main">
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="其他检查内容：">
                                        <span v-text="formDetail.qtjcnr"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="发现问题：">
                                        <span v-text="formDetail.fzwt"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="检查意见：">
                                        <span v-text="formDetail.jcyj"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="整改落实截止日期：">
                                        <span v-text="formDetail.zglsjzrq"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="落实情况：">
                                        <span v-text="formDetail.lsqk"></span>
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
    import {fetchDictTree} from "@/api/admin/dict";
    import {getZajcDetail,getZajcContent} from "@/api/portal/archive/jingz/zajc";
    import {placeTypeEnum} from  "@/const/crud/portal/archive/commom/constant";
    import { validatenull } from "@/util/validate";

    export default {
        name: 'zajcJzDetail',
        props:["param"],
        data(){
            return {
                placeEnum:placeTypeEnum,
                formDetail:{},
                checkData:[],
                hylxData:[],
                hylxQuery: {
                    type: 'HYLX'
                },
            }
        },
        created(){
            this.getFormDetail(this.param.systemId)
            this.getFormCheckDetail(this.param.systemId)
        },

        watch: {
            param: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.getFormDetail(this.param.systemId)
                    this.getFormCheckDetail(this.param.systemId)
                },
                deep: true
            },
        },
        methods: {
            getFormDetail(id) {
                getZajcDetail(id).then(response => {
                    this.formDetail = response.data.data
                    this.initData()
                })
            },
            getFormCheckDetail(id){
                getZajcContent(id).then(response => {
                    this.checkData = response.data.data
                })
            },
            initData() {
                findLabel('za_022', 'jzxt', this.formDetail.clyj).then(value => {
                    this.formDetail.clyj = value
                }).catch((err) => {
                });
                findLabel('za_014', 'jzxt', this.formDetail.jcfs).then(value => {
                    this.formDetail.jcfs = value
                }).catch((err) => {
                });
                fetchDictTree(this.hylxQuery).then(response => {
                    this.hylxData = response.data.data
                })
            },
            /**
             * 是否包含某个字符串
             * */
            containStr(str,val,val2,val3,val4){
                if(!validatenull(str)&&((str.trim()).indexOf(val)==0||(str.trim()).indexOf(val2)==0||(str.trim()).indexOf(val3)==0||(str.trim()).indexOf(val4)==0)){
                    return true
                }else{
                    return false
                }
            },
            /**
             * 翻译字典
             * */
            dictLabel(dictData,val){
                let label=getLabel( dictData,val);
                return validatenull(label)?"-":label;
            },
            getJcqk(state){
                if(state == '0'){
                    return "否"
                }
                if(state == '1'){
                    return "是"
                }
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