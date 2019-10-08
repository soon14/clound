<template>
    <div class="execution">
        <basic-container>
            <div class="from-box">
                <h3><i class="el-icon-info"></i>人员基本信息</h3>
                <el-form  class="form-main">
                    <el-row>
                        <el-col :span="24">
                            <el-row class="from-row">
                                <el-col :span="8"><el-form-item label="姓名:">
                                   {{paramForm.name}}
                                </el-form-item></el-col>
                                <el-col :span="8" class="from-row"> <el-form-item label="身份证:">
                                    {{paramForm.zjhm}}
                                </el-form-item></el-col>
                                <el-col :span="8" class="from-row"> <el-form-item label="人员类型:">
                                    {{paramForm.rylx}}
                                </el-form-item></el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
            <div class="empty-div" v-if="ajList==null || ajList.length==0">
                <avue-empty desc="暂无违法信息"></avue-empty>
            </div>
            <div v-else v-for="(aj,ajIndex) in ajList" :key="ajIndex"  class="from-box" v-loading="recordsLoading">
                <h3><i class="el-icon-info"></i>案件信息{{ajIndex+1}}</h3>
                <el-form  class="form-main">
                    <el-row class="from-row"><!--行-->
                        <el-col :span="12"><el-form-item label="案件编号:">
                            {{aj.ajbh}}
                        </el-form-item></el-col>
                        <el-col :span="12" class="from-row"> <el-form-item label="案件名称:">
                            {{aj.ajmc}}
                        </el-form-item></el-col>
                    </el-row>
                    <el-row class="from-row">
                        <el-col :span="12"><el-form-item label="案别:">
                            {{findLabelByValue(abDicts,aj.ab)}}
                        </el-form-item></el-col>
                        <el-col :span="12" class="from-row"> <el-form-item label="案件状态:">
                            {{findLabelByValue(ajstateDicts,aj.ajstate)}}
                        </el-form-item></el-col>
                    </el-row>
                    <el-row class="from-row">
                        <el-col :span="12" class="from-row"> <el-form-item label="案发时间:">
                            {{aj.fasjcz}}
                        </el-form-item></el-col>
                        <el-col :span="12" class="from-row"> <el-form-item label="接警单位:">
                            {{aj.sljsdwName}}
                        </el-form-item></el-col>
                    </el-row>
                    <el-row class="from-row">
                        <el-col :span="24" ><el-form-item label="案发地点:">
                            {{aj.fadd}}
                        </el-form-item></el-col>
                    </el-row>
                    <el-row class="from-row">
                        <el-col :span="24" ><el-form-item label="案情简介:">
                            {{aj.zyaq}}
                        </el-form-item></el-col>
                    </el-row>
                </el-form>
            </div>
        </basic-container>
    </div>
</template>


<script>

    import {illegalityListByZjhm} from "@/api/portal/statistics/employee/employee.js";
    import {findLabelByValue} from "@/util/util";
    import {getDict}  from  "@/api/portal/archive/dict/dict";

    export default {
        name: 'illegalityList',
        props:["paramForm"],
        data() {
            return {
                recordsLoading:false,
                ajList:[],
                abDicts:[],
                ajstateDicts:[]
            }
        },
        created(){
            this.initDict();
            //this.getList(this.paramForm.zjhm);
        },
        computed: {
        },
        watch: {
            "paramForm.zjhm":{
                handler (newParam) {
                    this.getList(this.paramForm.zjhm);
                },
                deep: true
            },
        },
        methods:{
            initDict(){
                getDict('158','jzxt').then(response => {
                    this.abDicts = response.data.data||[];
                }).catch((err) => {});
                getDict('56','jzxt').then(response => {
                    this.ajstateDicts = response.data.data||[];
                }).catch((err) => {});
            },
            getList(param){
                this.recordsLoading = true;
                illegalityListByZjhm(param).then(response=>{
                    this.ajList = response.data.data;
                    this.recordsLoading = false;
                }).catch(()=>{});
            }
        }
    }
</script>
<style type="text/css">
    .from-box{
        width:90%;
        margin: 0 auto;
        box-shadow: 2px 1px 8px rgba(0,0,0,.15);
        padding:10px 20px;
        border-radius: 5px;
        box-sizing: border-box;
        margin-bottom: 20px;
    h3{
        border-bottom: 1px solid #eee;
        height: 50px;
        line-height: 50px;
    }
    .el-icon-info{
        height: 50px;
        line-height: 50px;
        font-size:25px;
        vertical-align: middle;
        margin-right: 10px;
    }
    img{
        display: block;
        height: 120px;
        width: 90px;
        margin: 20px auto;
        cursor: pointer;
        transition: all 0.6s;
    }
    img:hover{
        transform: scale(2.2);
    }
    }
</style>