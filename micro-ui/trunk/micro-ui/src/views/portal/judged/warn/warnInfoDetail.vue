<template>
    <div class="execution">
        <basic-container>
            <div class="avue-detail" style="padding-top: 0px!important;">
                <div class="avue-group avue-group--card" style="padding: 0 !important;">
                    <div class="el-row avue-group__item" span="24" >
                        <el-form class="form-main">
                            <el-col>
                                <el-col :span="12">
                                    <el-form-item label="警告标题：">
                                        <span v-text="formDetail.title"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="12">
                                    <el-form-item label="警告时间：">
                                       <span v-text="formDetail.warnTime"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="24">
                                    <el-form-item label="警告简要：">
                                        <span v-text="formDetail.brief"></span>
                                    </el-form-item>
                                </el-col>

                            </el-col>
                            <el-col>
                            <el-col :span="24">
                                <el-form-item label="警告详情：">
                                    <span v-text="formDetail.detail"></span>
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
    import {deepClone} from '@/util/util'
    export default {
        name: 'warnInfoDetail',
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
        },

        watch: {
            detailRow: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.formDetail=deepClone(this.detailRow)

                },
                deep: true
            },
        },
        methods: {


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