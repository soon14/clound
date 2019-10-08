<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       :page="page"
                       :option="tabOption"
                       :data="data"
                       @row-update="handleUpdate"
                       @row-save="handleSav"
                       @row-del="handleDelete"
                       v-model="form">
                <template slot="menuLeft">
                    <el-button
                            class="filter-item"
                            @click="dialogVisible = true"
                            size="small"
                            type="primary"
                            icon="el-icon-download">导出
                    </el-button>
                </template>

                <template slot="menu" slot-scope="scope" >
                        <el-button size="mini"
                                   type="text"
                                   icon="el-icon-edit"
                                   @click="editFlag=true">超时处理
                        </el-button>
                </template>

            </avue-crud>
            <export-file :exprotFields="exprotFields"
                         :dialogVisible="dialogVisible"
                         :url="exportUrl"
                         :params="form"
                         :pageSize="page.pageSize"
                         :currentPage="page.currentPage"
                         :ascs="page.ascs"
                         :descs="page.descs"
                         :fileName="fileName"
                         v-on:hiddeDialg="dialogVisible = false" >
            </export-file>
        </basic-container>
        <el-dialog title="超时处理" fullscreen :visible.sync="editFlag" :append-to-body="true">
            <avue-form  :option="formOption"  v-model="formData" >
            </avue-form>
            <div slot="footer"  class="el-dialog--center">
                <el-button type="primary" @click="handleSav">下发警告
                </el-button>
                <el-button type="primary" @click="handleSav">撤 销
                </el-button>
                <el-button type="primary" @click="editFlag=false">关 闭
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import exportFile from "@/page/export/exportFile"
    let dict ={
        zrdw:[
            {label: '江门市公安局', value: '0'},
            {label: '甘化社区警务区', value: '1',},
            {label: '海傍社区警务区', value: '2',},
            {label: '常安社区警务区',value: '3',}
        ],
        zyjb:[{label: '一级', value: 'A'},
            {label: '二级', value: 'B'},
            {label: '三级', value: 'C'},
            {label: '橙色', value: 'D'},
            {label: '黄色', value: 'E'},
            {label: '红色', value: 'F'},],

        clzt:[{label:'已处理',value:'01'},
            {label:'未处理',value:'02'},
            {label:'已超时',value:'03'},
        ],
        sycslb:[
            {"label":"旅馆业","value":"01"},
            {"label":"刻印章业","value":"02"},
            {"label":"旧货业","value":"03"},
            {"label":"印刷业","value":"04"},
            {"label":"机动车修理业","value":"05"},
            {"label":"开锁业","value":"06"},
            {"label":"其他管理行业","value":"07"},
            {"label":"歌舞娱乐场所","value":"08"},
            {"label":"按摩服务业","value":"09"},
            {"label":"游戏游艺场所","value":"10"},
            {"label":"录像放映场所","value":"11"},
            {"label":"上网服务场所","value":"12"},
            {"label":"体育场馆","value":"13"},
            {"label":"广场公园","value":"14"},
            {"label":"其他场所","value":"15"},
            {"label":"报废机动车拆解业","value":"16"},
            {"label":"典当业","value":"17"},
            {"label":"废旧金属业","value":"18"}
        ],
    }
    export default {
        components: { "exportFile" : exportFile},
        data() {
            return {
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                editFlag: false,
                dialogVisible: false,
                exportUrl: 'portal/feedback/dealCenter/export',
                fileName: '处置中心信息',
                exprotFields: {
                    zlms: '指令描述',
                    zllx: '指令类型',
                    yqwcsj: '要求完成时间',
                    zyjb: '重要级别',
                    zrdw: '责任单位',
                },
                form: {},
                tabOption: {
                    indexLabel: '序号',
                    index: true,
                    editBtn: false,
                    addBtn: false,
                    viewBtn: false,
                    delBtn: false,
                    border: true,
                    card: true,
                    column: [
                        {
                            label: '指令描述',
                            prop: 'zlms',
                            search: true,
                            span: 12,
                        },
                        {
                            label: '指令类型',
                            prop: 'zllx',
                            type: 'select',
                            search: true,
                            span: 12,
                            dicData: dict.sycslb,
                        },
                        {
                            label: '重要级别',
                            prop: 'zyjb',
                            type: 'select',
                            dicData: dict.zyjb,
                            search: true,
                            span: 12
                        },
                        {
                            label: '责任单位',
                            prop: 'zrdw',
                            display: false,
                            search: true,
                            type: 'select',
                            dicData: dict.zrdw,
                            span: 12,
                        },
                        {
                            label: '要求完成时间',
                            prop: 'yqwcsj',
                            type: 'datetimerange',
                            display: false,
                            search: true,
                            span: 12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            label: '处理状态',
                            prop: 'clzt',
                            type: 'select',
                            dicData: dict.clzt,
                            span: 12,
                        },
                        {
                            label: '处理时间',
                            prop: 'clsj',
                            type: 'datetimerange',
                            hide: 'true',
                            display: false,
                            span: 12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                    ]
                },
                data: [
                    {
                        zlms: '涉黄人员集聚',
                        zllx: '01',
                        yqwcsj: '2019-03-28',
                        zyjb: 'A',
                        zrdw: '0',
                        clzt: '03',
                    },
                    {
                        zlms: '涉黄人员集聚',
                        zllx: '01',
                        yqwcsj: '2019-03-28',
                        zyjb: 'A',
                        zrdw: '0',
                        clzt: '03',
                    },
                    {
                        zlms: '涉黄人员集聚',
                        zllx: '01',
                        yqwcsj: '2019-03-28',
                        zyjb: 'A',
                        zrdw: '0',
                        clzt: '03',
                    },
                    {
                        zlms: '涉黄人员集聚',
                        zllx: '01',
                        yqwcsj: '2019-03-28',
                        zyjb: 'A',
                        zrdw: '0',
                        clzt: '03',
                    },
                    {
                        zlms: '涉黄人员集聚',
                        zllx: '01',
                        yqwcsj: '2019-03-28',
                        zyjb: 'A',
                        zrdw: '0',
                        clzt: '03',
                    },
                ],
                formOption: {
                    editBtn: false,
                    addBtn: false,
                    viewBtn: false,
                    delBtn: false,
                    border: true,
                    emptyBtn: false,
                    submitBtn: false,
                    card: true,
                    group: [
                        {
                            label: '指令信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [
                                {
                                    label: '指令描述',
                                    prop: 'zlms',
                                    disabled:true,
                                    span: 12,
                                },
                                {
                                    label: '指令类型',
                                    prop: 'zllx',
                                    type: 'select',
                                    disabled:true,
                                    span: 12,
                                    dicData: dict.sycslb,
                                },
                                {
                                    label: '处理状态',
                                    prop: 'clzt',
                                    type:'select',
                                    disabled:true,
                                    dicData:dict.clzt,
                                    span:12,
                                },
                                {
                                    label: '发布时间',
                                    prop: 'fbsj',
                                    type:'datetime',
                                    disabled:true,
                                    span:12,
                                },
                                {
                                    label: '预警级别',
                                    prop: 'yjjb',
                                    type:'select',
                                    dicData:dict.zyjb,
                                    disabled:true,
                                    span:12
                                },
                                {
                                    label: '要求反馈时间',
                                    prop: 'yqwcsj',
                                    type: 'datetime',
                                    span:12,
                                    format: 'yyyy-MM-dd HH:mm:ss',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                                {
                                    label: '所属派出所',
                                    prop: 'sspcs',
                                    dicData:dict.zrdw,
                                    disabled:true,
                                    span:12,
                                },
                                {
                                    label:'预警描述',
                                    prop:'yjmsxx',
                                    type:'textarea',
                                    disabled:true,
                                    span:24,
                                },
                                {
                                    label:'场所名称',
                                    prop:'csmc',
                                    disabled:true,
                                    span:12,
                                },
                                {
                                    label:'场所类别',
                                    prop:'cslb',
                                    type:'select',
                                    disabled:true,
                                    dicData:dict.sycslb,
                                    span:12,
                                },
                                {
                                    label: '处理单位',
                                    prop: 'cldw',
                                    type:'select',
                                    dicData:dict.zrdw,
                                    span:12,
                                },
                                {
                                    label: '处理结果',
                                    prop: 'cljg',
                                    type:'textarea',
                                    span:24,
                                },
                                {
                                    label: '处理人',
                                    prop: 'clr',
                                    span:12,
                                },
                                {
                                    label: '处理时间',
                                    prop: 'clsj',
                                    type: 'datetime',
                                    span:12,
                                    format: 'yyyy-MM-dd HH:mm:ss',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                },
                            ]

                        }],
                },
                formData:
                    {
                        zlms:'涉黄人员集聚',
                        zllx:'01',
                        clzt:'01',
                        fbsj:'2019-03-28',
                        yjjb:'A',
                        yqwcsj:'2019-03-28',
                        sspcs:'0',
                        yjmsxx:'九天酒店在2019年03月15日有6名涉黄人员聚集',
                        csmc:'九天酒店',
                        cslb:'01',
                        cljg:'',
                        cldw:'',
                        clr:'李一天',
                        clsj:'',
                    },
            }
        },

        computed:{

        },

        methods: {
            handleChange(column) {
                this.type = column
            },
            handleView(row, index) {
                this.$refs.crud.rowView(row, index)
            },
            handleEdit (row, index) {
                this.$refs.crud.rowEdit(row, index);
            },
        }
    }
</script>