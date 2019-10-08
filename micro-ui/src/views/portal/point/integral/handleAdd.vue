<template>
    <div class="execution">
        <basic-container>
            <avue-form
                       :option="tableOption"
                       v-model="data"
                       @submit="submit" >
            </avue-form>

        </basic-container>
    </div>
</template>

<script>
    let dict = {
        cslb: [
            {"label": "旅馆业", "value": "01"},
            {"label": "刻印章业", "value": "02"},
            {"label": "旧货业", "value": "03"},
            {"label": "印刷业", "value": "04"},
            {"label": "机动车修理业", "value": "05"},
            {"label": "开锁业", "value": "06"},
            {"label": "其他管理行业", "value": "07"},
            {"label": "歌舞娱乐场所", "value": "08"},
            {"label": "按摩服务业", "value": "09"},
            {"label": "游戏游艺场所", "value": "10"},
            {"label": "录像放映场所", "value": "11"},
            {"label": "上网服务场所", "value": "12"},
            {"label": "体育场馆", "value": "13"},
            {"label": "广场公园", "value": "14"},
            {"label": "其他场所", "value": "15"},
            {"label": "报废机动车拆解业", "value": "16"},
            {"label": "典当业", "value": "17"},
            {"label": "废旧金属业", "value": "18"},
            {"label": "涉枪单位", "value": "19"},
            {"label": "易制爆单位", "value": "20"},
            {"label": "剧毒化学品单位", "value": "21"},
            {"label": "旅业式出租屋", "value": "22"},
            {"label": "烟花爆竹单位", "value": "23"},
            {"label": "重点单位", "value": "24"},
            {"label": "一般单位", "value": "25"},
            {"label": "学校/幼儿园", "value": "26"},
            {"label": "保安服务公司", "value": "27"},
            {"label": "保安押运公司", "value": "28"},
            {"label": "保安培训机构", "value": "29"},
            {"label": "内部保安组织", "value": "30"},
            {"label": "放射性同位素", "value": "31"},
            {"label": "所有", "value": "32"}
        ],
        type: [{
            label: '自动',
            value: '0'
        }, {
            label: '手动',
            value: '1'
        }],
        name:[{
            label: '涉黄人员入住',
            value: '0'
        },{
            label: '涉赌人员入住',
            value: '1'
        },{
            label: '涉毒人员入住',
            value: '2'
        },{
            label: '其他',
            value: '3'
        }
        ]
    }

    export default {
        name: 'handleAdd',
      /*  props:['data'],*/
        data() {
            return {
                form:{},
                tableOption: {
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    delBtn: false,
                    viewBtn: false,
                    editBtn: false,
                    dateBtn:true,
                    card:true,
                    column: [
                        {
                            label: '场所类别',
                            prop: 'cslb',
                            search:'true',
                            type: 'select',
                            dicData: dict.cslb,
                            editDisabled: true,
                            valueDefault:'0',
                            rules: [{
                                required: true,
                                message: '请选择场所类别',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '场所名称',
                            prop: 'csmc',
                            editDisabled: true,
                            valueDefault:'七天酒店',
                            rules: [{
                                required: true,
                                message: '请输入场所名称',
                                trigger: 'blur'
                            }]
                        },
                        {
                        label: '选择指标',
                        prop: 'name',
                        type:'select',
                        editDisabled: true,
                        dicData: dict.name,
                         rules: [{
                                required: true,
                                message: '请选择指标',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '指标类型',
                            prop: 'type',
                            type: 'select',
                            dicData: dict.type,
                            valueDefault:'1',
                            rules: [{
                                required: true,
                                message: '请选择指标类型',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '单项分值',
                            prop: 'score',
                            type: 'number',
                            rules: [{
                                required: true,
                                message: '请输入单项分值',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '次数',
                            prop: 'count',
                            type: 'number',
                            rules: [{
                                required: true,
                                message: '请输入次数',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '本次加分',
                            prop: 'bcjf',
                            type: 'number',
                            rules: [{
                                required: true,
                                message: '请输入本次加分',
                                trigger: 'blur'
                            }]
                        },
                        {
                            label: '积分小计',
                            prop: 'scoreSum',
                            overHidden: true,
                            addDisplay:true,
                            editDisplay:true
                        },
                        {
                            label: '描述',
                            type:'textarea',
                            prop: 'remarks',
                            span:24
                        },
                        {
                            label: '创建人',
                            prop: 'createBy',
                            overHidden: true,
                            span:6
                        },
                        {
                            label: '创建时间',
                            prop: 'createDate',
                            type: "datetime",
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            overHidden: true,
                            span:6
                        },
                        {
                            label: '创建单位',
                            prop: 'createDept',
                            span:6
                        }
                        ]

                },

            }
        },
        computed: {


        },
        methods: {
            submit () {
                this.$message.success('当前数据'+JSON.stringify(this.data));
            }
        }
    }
</script>

