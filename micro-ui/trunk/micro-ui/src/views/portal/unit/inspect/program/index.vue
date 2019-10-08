
<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-tabs :option="option" @change="handleChange"></avue-tabs>
            <span v-if="type.prop==='tab1'">
                <avue-crud :option="tabOption1"
                           :data="data1"
                           v-model="tabForm1"
                           ref="crud"
                           :page="page"
                           :table-loading="tableLoading">
                    <template slot="menu" slot-scope="scope" >
                        <el-button size="mini"
                                   type="text"
                                   icon="el-icon-edit"
                                   v-if="targetManager_btn_edit"
                                   @click="handleEdit(scope.row,scope.index)">编辑
                        </el-button>
                        <el-button size="mini"
                                   type="text"
                                   icon="el-icon-delete"
                                   v-if="targetManager_btn_del"
                                   @click="handleDelete(scope.row)">删除
                        </el-button>
                    </template>
                    <template slot="fieldContentForm" slot-scope="scope" :span="24" :table-loading="tableLoading">
                       <div>
                            <label class="el-checkbox__label">指标项</label>
                            <target-list :dialogVisible="dialogVisible"
                                         :unitType="tabForm1.type"
                                         v-on:hiddeDialg="dialogVisible=false"></target-list>
                        </div>
                    </template>
                </avue-crud>

            </span>
            <span v-else-if="type.prop==='tab2'">
                <avue-crud :option="tabOption2"
                           :data="data2"
                           v-model="tabForm2"
                           ref="crud"
                           :page="page"
                           :table-loading="tableLoading">
                    <template slot="menu" slot-scope="scope" >
                        <el-button size="mini"
                                   type="text"
                                   icon="el-icon-edit"
                                   v-if="targetManager_btn_edit"
                                   @click="handleEdit(scope.row,scope.index)">编辑
                        </el-button>
                        <el-button size="mini"
                                   type="text"
                                   icon="el-icon-delete"
                                   v-if="targetManager_btn_del"
                                   @click="handleDelete(scope.row)">删除
                        </el-button>
                    </template>
                    <template slot="fieldContentForm" slot-scope="scope" :span="24" >
                        <div>
                            <label class="el-checkbox__label">指标项</label>
                            <target-list :dialogVisible="dialogVisible"
                                    :unitType="tabForm1.type"
                                    v-on:hiddeDialg="dialogVisible=false"></target-list>
                        </div>

                    </template>
                </avue-crud>
            </span>
        </basic-container>
    </div>
</template>

<style>

</style>
<script>
    import {mapGetters} from 'vuex'
    import TargetList from "../target/modal";
    const DIC = {
        typeDicts: [
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
        fieldTypeDict:[
            {"label":"文本框","value":"1"},
            {"label":"单选框","value":"2"},
            {"label":"多选框","value":"3"},
            {"label":"判断项","value":"4"}
        ],
        targetTree:[
            {
                "value": 'a',
                "label": '单位履行消防安全职责情况'
            },
            {
                "value": 'b',
                "label": '合法性'
            },
            {
                "value": 'j',
                "label": '消防安全管理'
            },
            {
                "value": 'l',
                "label": '责令更正情况'
            }
        ],
        targetList:[
            {
                parentId:'b',
                id: 'c',
                type: '01',
                content: '被查建筑物名称',
                isUse: 0,
                sort:3,
                fieldType:'1'
            },
            {
                parentId:'b',
                id: 'd',
                type: '01',
                content: '1998年9月1日之前竣工建筑且此后来改建(含装修、用途变更)',
                isUse: 0,
                sort:4,
                fieldType:'4'
            },
            {
                parentId:'b',
                id: 'e',
                type: '01',
                content: '依法通过消防验收',
                isUse: 0,
                sort:5,
                fieldType:'4'
            },
            {
                parentId:'b',
                id: 'f',
                type: '01',
                content: '依法进行竣工验收消防备案',
                isUse: 0,
                sort:6,
                fieldType:'4'
            },
            {
                parentId:'b',
                id: 'g',
                type: '01',
                content: '其他情况',
                isUse: 0,
                sort:7,
                fieldType:'1'
            },
            {
                parentId:'b',
                id: 'h',
                type: '01',
                content: '公众聚集场所',
                isUse: 0,
                sort:8,
                fieldType:'2'
            },
            {
                parentId:'b',
                id: 'i',
                type: '01',
                content: '依法通过投入使用、营业前消防安全检查',
                isUse: 0,
                sort:9,
                fieldType:'2'
            },
            {
                parentId:'j',
                id: 'k',
                type: '01',
                content: '消防安全制度',
                isUse: 0,
                sort:11,
                fieldType:'2'
            }
        ]
    }

    export default {
        components: {TargetList},
        data(){
            return {
                type: {},
                dialogVisible:false,
                targetManager_btn_edit:true,
                targetManager_btn_del:true,
                tabForm1: {
                    id: ''
                },
                tabForm2: {
                    id: ''
                },
                data1: [{
                    name:"旅馆业日常检查方案",
                    type:"01",
                    isUse:0,
                    updateTime:new Date()
                },{
                    name:"歌舞娱乐场所日常检查方案",
                    type:"08",
                    isUse:0,
                    updateTime:new Date()
                }],
                data2: [{
                    name:"旅馆业消防检查方案",
                    type:"01",
                    isUse:0,
                    updateTime:new Date()
                },{
                    name:"歌舞娱乐场所消防检查方案",
                    type:"08",
                    isUse:0,
                    updateTime:new Date()
                }],
                result:[],
                option: {
                    column: [{
                        icon: 'el-icon-date',
                        label: '日常检查方案',
                        prop: 'tab1',
                    }, {
                        icon: 'el-icon-bell',
                        label: '消防检查方案',
                        prop: 'tab2',
                    }]
                },
                tabOption1: {
                    expandAll: false,
                    expandLevel: 2,
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn: false,
                    delBtn: false,
                    viewBtn: true,
                    formEdit: true,
                    formAdd: true,
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    column: [
                        {
                            label: '方案名称',
                            prop: 'name',
                            align: 'left',
                            search: 'true',
                            width: 350,
                            span: 24,
                            rules: [
                                {
                                    required: true,
                                    message: '请输入方案名称',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '行业类型',
                            prop: 'type',
                            align: 'left',
                            width: 180,
                            span: 12,
                            type: "select",
                            search:'true',
                            dicData: DIC.typeDicts,
                            rules: [
                                {
                                    required: true,
                                    message: '请选择适用行业类型',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '启用',
                            prop: 'isUse',
                            align: 'left',
                            width: 100,
                            search: 'true',
                            type: 'radio',
                            dicData: [{
                                label: '是',
                                value: 0
                            }, {
                                label: '否',
                                value: 1,
                            }],
                            rules: [
                                {
                                    required: true,
                                    message: '请选择方案状态',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '修改时间',
                            prop: 'updateTime',
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            readonly:true
                        },
                        {
                            label:'',
                            hide:true,
                            formslot: true,
                            slot: true,
                            prop:'fieldContent',
                            span:24
                        }
                    ]
                },
                tabOption2: {
                    expandAll: false,
                    expandLevel: 2,
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn: false,
                    delBtn: false,
                    viewBtn: true,
                    formEdit: true,
                    formAdd: true,
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    column: [
                        {
                            label: '方案名称',
                            prop: 'name',
                            align: 'left',
                            search: 'true',
                            width: 350,
                            span: 24,
                            rules: [
                                {
                                    required: true,
                                    message: '请输入方案名称',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '行业类型',
                            prop: 'type',
                            align: 'left',
                            width: 180,
                            span: 12,
                            type: "select",
                            search:'true',
                            dicData: DIC.typeDicts,
                            rules: [
                                {
                                    required: true,
                                    message: '请选择适用行业类型',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '启用',
                            prop: 'isUse',
                            align: 'left',
                            width: 100,
                            search: 'true',
                            type: 'radio',
                            dicData: [{
                                label: '是',
                                value: 0
                            }, {
                                label: '否',
                                value: 1,
                            }],
                            rules: [
                                {
                                    required: true,
                                    message: '请选择方案状态',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '修改时间',
                            prop: 'updateTime',
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                            readonly:true
                        },
                        {
                            label:'',
                            hide:true,
                            formslot: true,
                            slot: true,
                            prop:'fieldContent',
                            span:24
                        }
                    ]
                },
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                tableLoading: false,
                tableOption1:{
                    addBtn:false,
                    editBtn:false,
                    refreshBtn:false,
                    columnBtn:false,
                    filterBtn:false,
                    cellBtn:true,
                    indexLabel: '序号',
                    border:true,
                    index:true,
                    keyId: 'id',
                    column: [{
                        label: '指标内容',
                        prop: 'content'
                    },{
                        label: '字段类型',
                        prop: 'fieldType',
                        dicData: DIC.fieldTypeDict
                    },{
                        label:'排序',
                        prop:'sort',
                        cell:true,
                        type: 'number'
                    }]
                },
                tableData:[],
                tableOption2:{
                    addBtn:false,
                    editBtn:false,
                    refreshBtn:false,
                    columnBtn:false,
                    filterBtn:false,
                    cellBtn:true,
                    indexLabel: '序号',
                    border:true,
                    index:true,
                    keyId: 'id',
                    column: [{
                        label: '指标内容',
                        prop: 'content'
                    },{
                        label: '字段类型',
                        prop: 'fieldType',
                        dicData: DIC.fieldTypeDict
                    },{
                        label:'排序',
                        prop:'sort',
                        cell:true,
                        type: 'number'
                    }]
                }
            }
        },
        created(){
            this.type = this.option.column[0];
        },
        computed: {
            ...mapGetters([
                'elements',
                'permissions'
            ])
        },
        methods:{
            handleEdit(row,index){
                this.tableData = DIC.targetList;
                this.$refs.crud.rowEdit(row, index);
            },
            handleChange(column) {
                this.type = column
                //this.$message.success(JSON.stringify(column.label))
            },
            handleDelete(row, index) {
                this.$refs.crud.rowDel(row, index);
            },
            exportTarget:function(){
                if(this.tabForm1.type===''){
                    this.$message.warning("请选择行业类别");
                }else{
                    this.dialogVisible = true;
                }
            },

        }
    }
</script>