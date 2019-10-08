<template>
    <basic-container class="personPath">
        <img src="../../../../../public/img/pic.png" alt="" class="imgs">
        <h3 style="margin-left: 12%">姓名：李四 &nbsp;&nbsp;身份证号：450823199911131234</h3>
        <avue-timeline pending time :time-width="200">
            <avue-timeline-item :icon="item.icon" :color="item.color" v-for="item in timeData">
                <div slot="time">
                    <p v-text="item.time"></p>
                </div>
                <div slot="content">
                    <h4 v-text="item.title"></h4>
                    <el-form class="infoform">
                        <el-form-item v-for="itemData in item.data" :label="itemData.label">
                             {{itemData.value}}
                        </el-form-item>
                        <el-button v-if="item.title=='人像比对'" size="small" type="primary" style="float: right">比对</el-button>
                    </el-form>
                </div>
            </avue-timeline-item>
            <avue-timeline-item>
                <a href="#" style="color: #409EFF;cursor: pointer;text-decoration: none;">加载更多</a>
            </avue-timeline-item>
        </avue-timeline>
    </basic-container>
</template>

<script>
    export default {
        data(){
            return{
                timeData:[
                    {
                        color:"blue",
                        icon:"el-icon-s-custom",
                        time:" 2012-12-23",
                        title:"从业人员信息",
                        data:[
                            {
                                label:"单位名称",
                                value:"教师"
                            },
                            {
                                label:"行业类型",
                                value:"旅馆业"
                            },
                            {
                                label:"地址",
                                value:"广州江门"
                            },
                            {
                                label:"职务类型",
                                value:"外勤"
                            },
                            {
                                label:"在职状态",
                                value:"在职"
                            },
                        ]
                    },
                    {
                        color:"red",
                        icon:"el-icon-bicycle",
                        time:" 2012-12-23",
                        title:"承接车辆",
                        data:[
                            {
                                label:"车牌号码",
                                value:"8888888"
                            },
                            {
                                label:"车辆类型",
                                value:"单车"
                            },
                            {
                                label:"车辆型号",
                                value:"CSD879"
                            },
                            {
                                label:"车辆颜色",
                                value:"绿色"
                            },
                            {
                                label:"维修单位",
                                value:"广州江门新家维修"
                            },
                            {
                                label:"单位地址",
                                value:"江门市蓬江区890号"
                            },
                        ]
                    },
                    {
                        color:"green",
                        icon:"el-icon-tickets",
                        time:" 2012-12-23",
                        title:"涉案信息",
                        data:[
                            {
                                label:"案件编号",
                                value:"HIOJM238928432EWNJKDS"
                            },
                            {
                                label:"案别",
                                value:"涉毒"
                            },
                            {
                                label:"名称",
                                value:"江门8999"
                            },
                            {
                                label:"案件状态",
                                value:"案件状态"
                            },
                            {
                                label:"简要案情",
                                value:"聚众赌博"
                            },
                        ]
                    },
                    {
                        color:"red",
                        icon:"el-icon-s-custom",
                        time:" 2012-12-23",
                        title:"人像比对",
                        data:[
                            {
                                label:"单位名称",
                                value:"江门市"
                            },
                            {
                                label:"地址",
                                value:"江门市蓬江区环市街道88号"
                            },
                        ]
                    },
                ],
            }
        }
    }
</script>

<style lang="scss">
.personPath{
    h4{padding: 0;margin: 0}
    .el-form-item{
        margin-bottom: 0px!important;
    }
    .infoform{
        box-shadow: 1px 1px 9px 1px #ccc;
        border-radius: 5px;
        overflow: hidden;
        padding: 10px;
        margin-top: 10px;
        width: 40%;
    }
    .imgs{
        position: fixed;
        right:0;
        top:20%;
    }
    
}
</style>