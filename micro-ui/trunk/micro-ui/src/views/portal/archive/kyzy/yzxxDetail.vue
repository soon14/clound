<template>
    <div class="execution">
        <basic-container>
            <div class="avue-detail">
                <div class="avue-group avue-group--card">
                    <el-form class="form-main">
                        <el-col>
                            <el-col :span="8">
                                <el-form-item label="使用单位：">
                                    <span v-text="formDetail.approveUnitName"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="法人：">
                                   <span v-text="formDetail.approveUnitLegalName"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="地址：">
                                    <span v-text="formDetail.approveUnitAddress"></span>
                                </el-form-item>
                            </el-col>
                        </el-col>
                        <el-col>
                            <el-col :span="8">
                                <el-form-item label="印章名称：">
                                    <span v-text="formDetail.name"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="状态：">
                                    <span v-text="formDetail.status"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="类型：">
                                    <span v-text="formDetail.type"></span>
                                </el-form-item>
                            </el-col>
                        </el-col>
                        <el-col>
                            <el-col :span="8">
                                <el-form-item label="材料：">
                                    <span v-text="formDetail.material"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="中心图案：">
                                    <span v-text="formDetail.centerDesign"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="形状：">
                                    <span v-text="formDetail.shape"></span>
                                </el-form-item>
                            </el-col>
                        </el-col>
                        <el-col>
                            <el-col :span="8">
                                <el-form-item label="横长(宽)：">
                                    <span v-text="formDetail.width"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="竖长(高)：">
                                    <span v-text="formDetail.height"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="代加工：">
                                    <span v-text="formDetail.processSign"></span>
                                </el-form-item>
                            </el-col>
                        </el-col>
                        <el-col>
                            <el-col :span="8">
                                <el-form-item label="制作时间：">
                                    <span v-text="formDetail.produceDate"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="交付时间：">
                                    <span v-text="formDetail.deliverDate"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="检验结果：">
                                    <span v-text="formDetail.inspectMark"></span>
                                </el-form-item>
                            </el-col>

                        </el-col>
                        <el-col>
                            <el-col :span="8">
                                <el-form-item label="失效时间：">
                                    <span v-text="formDetail.zw"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="失效类型：">
                                    <span v-text="formDetail.disableType"></span>
                                </el-form-item>
                            </el-col>
                        </el-col>
                        <el-col>
                            <el-col :span="8" >
                                <el-form-item label="排版印章图像：" >
                                    <span >
                                        <viewer>
                                    <img style="height: 100px;width: 100px"  id="makeImage" />
                                        </viewer>
                                    </span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8" >
                                <el-form-item label="备案印章图像：" >
                                    <span >
                                        <viewer>
                                    <img style="height: 100px;width: 100px"  id="auditImage" />
                                        </viewer>
                                    </span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="8" >
                                <el-form-item label="章模扫描件：" >
                                    <span >
                                        <viewer>
                                    <img style="height: 100px;width: 100px"  id="scanImage" />
                                        </viewer>
                                    </span>
                                </el-form-item>
                            </el-col>
                        </el-col>
                    </el-form>
                </div>
            </div>
        </basic-container>
    </div>
</template>


<script>

    import {findLabel}  from  "@/api/portal/archive/dict/dict";
    import {deepClone} from '@/util/util'
    import {kyzyDict} from "@/const/crud/portal/archive/kyzy/kyzy";
    import {getScanImage,getAuditImage,getMakeImage} from "@/api/portal/archive/kyzy/kyzy";
    export default {
        name: 'yzxxDetail',
        props:["detailRow"],
        data(){
            return {
                dict:{},

                formDetail:{},

            }
        },
        created(){
            //通过复制对象、防止该内容改变导致整块改变
            this.formDetail=deepClone(this.detailRow);
            this.formDetail.processSign = this.getProcessSignLabel(this.formDetail.processSign);
            this.formDetail.inspectMark = kyzyDict.getLabel(kyzyDict.inspectMark,this.formDetail.inspectMark);
            getScanImage(this.formDetail.id,"scanImage");
            getAuditImage(this.formDetail.id,"auditImage");
            getMakeImage(this.formDetail.id,"makeImage");
            this.initData();
        },

        watch: {
            detailRow: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.formDetail=deepClone(this.detailRow);
                    this.formDetail.processSign = this.getProcessSignLabel(this.formDetail.processSign);
                    this.formDetail.inspectMark = kyzyDict.getLabel(kyzyDict.inspectMark,this.formDetail.inspectMark);
                    getScanImage(this.formDetail.id,"scanImage");
                    getAuditImage(this.formDetail.id,"auditImage");
                    getMakeImage(this.formDetail.id,"makeImage");
                    this.initData();

                },
                deep: true
            },
        },
        methods: {

            initData() {
                findLabel('seal_status','kyz',this.formDetail.status).then(value=>{
                    this.formDetail.status = value;
                }).catch((err) => {});
                findLabel('seal_type','kyz',this.formDetail.type).then(value=>{
                    this.formDetail.type = value;
                }).catch((err) => {});
                findLabel('seal_material','kyz',this.formDetail.material).then(value=>{
                    this.formDetail.material = value;
                }).catch((err) => {});
                findLabel('seal_center_design','kyz',this.formDetail.centerDesign).then(value=>{
                    this.formDetail.centerDesign = value;
                }).catch((err) => {});
                findLabel('seal_shape','kyz',this.formDetail.shape).then(value=>{
                    this.formDetail.shape = value;
                }).catch((err) => {});
                findLabel('seal_cancel_type','kyz',this.formDetail.disableType).then(value=>{
                    this.formDetail.disableType = value;
                }).catch((err) => {});
            },
            getProcessSignLabel(value) {
                if(value=='0'){
                    return "是";
                }else {
                    return "否";
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