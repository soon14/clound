<template>
    <div class="execution">
        <basic-container>
            <avue-detail  :option="viewOption"  v-model="viewData">
            </avue-detail>
        </basic-container>
    </div>
</template>

<script>
    import {findOne} from "@/api/portal/archive/common/unitQuery";
    export default {
        name:"unit",
        data(){
            return {
                form:{
                    dwbh:'',
                    menuId:''
                },
                tableLoading: false,
                viewData:[],
                viewOption:{
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    page:false,
                    align:'center',
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn:false,
                    delBtn:false,
                    addBtn:false,
                    viewBtn:false,
                    menuWidth:'100',
                    card:true,
                    group: [
                        {
                            label: '基本信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [
                                {label:'单位名称',prop:'dwmc',},
                                {label:'经营名称',prop:'jymc',},
                                {label: '',prop: 'imgUi',slot:true,formslot:true,},
                                {label:'经营状态',prop:'qyztdm',},
                                {label:'单位性质',prop:'dwxz',},
                                {label:'行业类别',prop:'dwlbdm',},
                                {label:'开业日期',prop:'kyrq',},
                                {label:'联系电话',prop:'lxdh',},
                                {label:'等级评定',prop:'dwdj',},
                                {label:'上级主管部门',prop:'sjzgbm',},

                                {label:'管辖区派出所',prop:'xqpcs',},
                                {label:'主管公安机关',prop:'djdwgajgmc',},
                                {label:'责任民警警号',prop:'zrmjjh',},
                                {label:'责任民警姓名',prop:'zrmjxm',},
                                {label:'主营',prop:'jyfwzy',},
                                {label:'兼营',prop:'jyxmjy',},
                                {label:'被兼营单位',prop:'bjydw',},
                            ]
                        },{
                            label: '法定代表人',
                            prop: 'fddbr',
                            icon: 'el-icon-info',
                            column: [ {
                                label:'姓名',prop:'fddbrxm',},
                                {label:'证件种类',prop:'fddbrcyzjdm',},
                                {label:'证件号码',prop:'fddbrzjhm',},
                                {label:'联系电话',prop:'fddbrlxdh',},
                            ]
                        },{
                            label: '实际经营人',
                            prop: 'sjjyr',
                            icon: 'el-icon-info',
                            column: [
                                {label:'姓名',prop:'dwfzrxm',},
                                {label:'证件种类',prop:'dwfzrcyzjdm',},
                                {label:'证件号码',prop:'dwfzrzjhm',},
                                {label:'联系电话',prop:'dwfzrlxdh',},
                            ]
                        }, {
                            label: '规模情况',
                            prop: 'gmqk',
                            icon: 'el-icon-info',
                            column: [
                                {label:'面积(平方米)',prop:'jycdmjpfm',},
                                {label:'技防设施',prop:'jfss',},
                            ]
                        },
                        {
                            label: '地址信息',
                            prop: 'dzxx',
                            icon: 'el-icon-info',
                            column: [{label:'行政区划',prop:'dwdzssxqdm',},
                                {label:'街路巷',prop:'jlx',},
                                {label:'所属警务区',prop:'ssjwq',},
                                {label:'所属社区',prop:'sssq',},
                                {label:'门楼牌',prop:'mpl',},
                                {label:'详细地址',prop:'dwdzqhnxxdz',},
                                {label:'经营地址',prop:'dwdzdzbm',},]
                        },{
                            label: '人员情况',
                            prop: 'ryqk',
                            icon: 'el-icon-info',
                            column: [
                                {label:'从业人员总数',prop:'cyrs',},
                                {label:'治安培训人数',prop:'zapxrs',},
                                {label:'保安人员总数',prop:'baryzs',},
                                {label:'自建保安数',prop:'zjbas',},
                                {label:'保安来源',prop:'baly',},
                                {label:'保安派驻单位',prop:'bapzdw',},
                                {label:'安保组织负责人姓名',prop:'bwfzrxm',},
                                {label:'安保组织负责人联系电话',prop:'bwfzrlxdh',},
                            ]
                        },{
                            label: '其他信息',
                            prop: 'swfw',
                            icon: 'el-icon-info',
                            column: [
                                {label:'备注',prop:'bz',span:24},
                                {label:'录入人',prop:'lrr',},
                                {label:'录入日期',prop:'lrrq',},
                                {label:'录入情况',prop:'lrqk',},
                                {label:'最后修改人',prop:'zhxgr',},
                                {label:'最后修改时间',prop:'zhxgsj',},
                            ]
                        }
                    ]
                },
            }
        },
        created(){
            this.getList( )
        },
        computed: {
        },
        methods:{
            getList( ) {
                findOne(Object.assign({ }, this.form)).then(response => {
                    this.viewData = response.data.data
                })
            },
        }
    }
</script>

<style scoped>

</style>