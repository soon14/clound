
<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-tabs :option="option" @change="handleChange"></avue-tabs>
            <span v-if="type.prop==='tab1'">
                <avue-crud :option="tabOption1" :data="data1" v-model="tabForm1" ref="crud">
                     <template slot="icon" slot-scope="scope">
                        <i :class="scope.row.icon" style="font-size:24px"></i>
                     </template>
                    <template slot="parentIdForm" slot-scope="scope">
                        <avue-input v-model="tabForm1.parentId"
                                    type="tree"
                                    placeholder="上级指标"
                                    :dic="targetTree1"
                                    :props="targetTreeProps">
                        </avue-input>
                    </template>

                    <template slot="menu" slot-scope="scope" >
                        <el-button size="mini"
                                 type="text"
                                 icon="el-icon-plus"
                                 v-if="isAddChildren(scope.row)"
                                 @click="handAdd(scope.row)">添加子级
                        </el-button>
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
                    <template slot="fieldContentForm" slot-scope="scope" :span="24">
                        <div v-if="tabForm1.fieldType == 1&&tabForm1.content!=''">
                            <el-row>
                                <el-col :span="24"><font style="font-weight: bold;font-size: 20px;">示例</font></el-col>
                                <el-col :span="24" >
                                    <label style="margin-right: 10px;">{{tabForm1.content}}：</label><input class="el-input__inner" style="width:180px;" readonly="readonly"/>
                                </el-col>
                            </el-row>
                        </div>
                        <div v-if="tabForm1.fieldType == 2">
                            <avue-crud :option="tableOption1" :data="tableData1" @row-update="addUpdate"></avue-crud>
                        </div>
                        <div v-if="tabForm1.fieldType == 3">
                            <avue-crud :option="tableOption2" :data="tableData2" @row-update="addUpdate"></avue-crud>
                        </div>
                        <div v-if="tabForm1.fieldType == 4&&tabForm1.content!=''">
                            <el-row>
                                <el-col :span="24"><font style="font-weight: bold;font-size: 20px;">示例</font></el-col>
                                <el-col :span="24" >
                                    <input type="checkbox" class="el-checkbox-button" style="margin-right: 10px;" readonly="readonly"/><label>{{tabForm1.content}}</label>
                                </el-col>
                            </el-row>
                        </div>
                    </template>
                </avue-crud>
            </span>
            <span v-else-if="type.prop==='tab2'">
                <avue-crud :option="tabOption2" :data="data2" v-model="tabForm2" ref="crud">
                     <template slot="icon" slot-scope="scope">
                        <i :class="scope.row.icon" style="font-size:24px"></i>
                     </template>
                    <template slot="menu" slot-scope="scope" >
                        <el-button size="mini"
                                   type="text"
                                   icon="el-icon-plus"
                                   v-if="isAddChildren(scope.row)"
                                   @click="handAdd(scope.row)">添加子级
                        </el-button>
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
                    <template slot="parentIdForm" slot-scope="scope">
                        <avue-input v-model="tabForm2.parentId"
                                    type="tree"
                                    placeholder="上级指标"
                                    :dic="targetTree2"
                                    :props="targetTreeProps">
                        </avue-input>
                    </template>
                    <template slot="fieldContentForm" slot-scope="scope" :span="24">
                        <div v-if="tabForm2.fieldType == 1&&tabForm2.content!=''">
                            <el-row>
                                <el-col :span="24"><font style="font-weight: bold;font-size: 20px;">示例</font></el-col>
                                <el-col :span="24" >
                                    <label style="margin-right: 20px;">{{tabForm2.content}}：</label><input class="el-input__inner" style="width:280px;" readonly="readonly"/>
                                </el-col>
                            </el-row>
                        </div>
                        <div v-if="tabForm2.fieldType == 2">
                            <avue-crud :option="tableOption3" :data="tableData3" @row-update="addUpdate"></avue-crud>
                        </div>
                        <div v-if="tabForm2.fieldType == 3">
                            <avue-crud :option="tableOption4" :data="tableData4" @row-update="addUpdate"></avue-crud>
                        </div>
                        <div v-if="tabForm2.fieldType == 4&&tabForm2.content!=''">
                            <el-row>
                                <el-col :span="24"><font style="font-weight: bold;font-size: 20px;">示例</font></el-col>
                                <el-col :span="24" >
                                    <input type="checkbox" class="el-checkbox-button" style="margin-right: 10px;" readonly="readonly"/><label>{{tabForm2.content}}</label>
                                </el-col>
                            </el-row>
                        </div>
                    </template>
                </avue-crud>
            </span>
        </basic-container>
        <template>

        </template>
    </div>
</template>
<style>

</style>
<script>
    import {mapGetters} from 'vuex'
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
                id: 'a',
                content: '单位履行消防安全职责情况',
                children: [
                    {
                        id: 'b',
                        content: '合法性'
                    },
                    {
                        id: 'j',
                        content: '消防安全管理'
                    }
                ]
            },
            {
                id: 'l',
                content: '责令更正情况'
            }
        ]
    }

    export default {
        data(){
            return {
                type:{},
                targetManager_btn_edit:true,
                targetManager_btn_del:true,
                tabForm1:{
                    id:''
                },
                tabForm2:{
                    id:''
                },
                targetTree1:[],
                targetTree2:[],
                data1:[
                    {
                        id: 'a1',
                        type: '01',
                        content: '入住登记制度健全落实',
                        isUse: 0,
                        sort:1,
                        fieldType:'2'
                    },
                    {
                        id: 'a2',
                        type: '01',
                        content: '来访登记制度健全落实',
                        isUse: 0,
                        sort:1,
                        fieldType:'2'
                    },
                    {
                        id: 'a3',
                        type: '01',
                        content: '值班巡查制度健全落实',
                        isUse: 0,
                        sort: 1,
                        fieldType: '2'
                    },
                    {
                        id: 'a4',
                        type: '01',
                        content: '财务保管制度情况健全落实',
                        isUse: 0,
                        sort:1,
                        fieldType:'2'
                    },
                    {
                        id: 'a5',
                        type: '01',
                        content: '报告制度健全落实',
                        isUse: 0,
                        sort:1,
                        fieldType:'2'
                    }
                ],
                data2:[
                    {
                        id: 'a',
                        type: '',
                        content: '单位履行消防安全职责情况',
                        isUse: 0,
                        sort:1,
                        fieldType:'',
                        children: [
                            {
                                id: 'b',
                                type: '',
                                content: '合法性',
                                isUse: 0,
                                sort:2,
                                fieldType:'',
                                parentId:'a',
                                children: [
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
                                    }
                                ]
                            },
                            {
                                id: 'j',
                                type: '',
                                content: '消防安全管理',
                                isUse: 0,
                                sort:10,
                                fieldType:'',
                                parentId:'a',
                                children:[
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
                        ]
                    },
                    {
                        id: 'l',
                        type: '',
                        content: '责令更正情况',
                        isUse: 0,
                        sort:12,
                        fieldType:'',
                        children:[{
                            parentId:'l',
                            id: 'm',
                            type: '01',
                            content: '制发的法律文书名称和编号',
                            isUse: 0,
                            sort:11,
                            fieldType:'1'
                        }]
                    }
                ],
                targetTreeProps: {
                    label: "content",
                    value: 'id'
                },
                option:{
                    column: [{
                        icon:'el-icon-date',
                        label: '日常检查指标',
                        prop: 'tab1',
                    }, {
                        icon:'el-icon-bell',
                        label: '消防检查指标',
                        prop: 'tab2',
                    }]
                },
                tabOption1: {
                    expandAll: false,
                    expandLevel:2,
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    tree: true,
                    editBtn: false,
                    delBtn: false,
                    viewBtn: true,
                    formEdit: true,
                    formAdd: true,
                    column: [

                        {
                            label: '指标内容',
                            prop: 'content',
                            align: 'left',
                            search:'true',
                            width: 550,
                            span:24,
                            rules: [
                                {
                                    required: true,
                                    message: '请输入指标内容',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '上级指标',
                            prop: 'parentId',
                            align: 'left',
                            width: 100,
                            hide:true,
                            formslot: true,
                            slot: true,
                            span:12
                        },
                        {
                            label: '行业类型',
                            prop: 'type',
                            align: 'left',
                            width: 100,
                            hide:true,
                            span:12,
                            type: "select",
                            dicData: DIC.typeDicts,
                            multiple : true
                        },
                        {
                            label: '启用',
                            prop: 'isUse',
                            align: 'left',
                            width: 100,
                            search:'true',
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
                                    message: '请输入指标内容',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '排序',
                            prop: 'sort',
                            align: 'left',
                            width: 100,
                            sortable:true,
                            type: 'number',
                            rules: [
                                {
                                    required: true,
                                    message: '请输入数字',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '字段类型',
                            prop: 'fieldType',
                            search:'true',
                            align: 'left',
                            type: "select",
                            dicData: DIC.fieldTypeDict,
                            span:24
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
                    expandLevel:2,
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    tree: true,
                    editBtn: false,
                    delBtn: false,
                    viewBtn: true,
                    formEdit: true,
                    formAdd: true,
                    column: [

                        {
                            label: '指标内容',
                            prop: 'content',
                            align: 'left',
                            search:'true',
                            width: 550,
                            span:24,
                            rules: [
                                {
                                    required: true,
                                    message: '请输入指标内容',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '上级指标',
                            prop: 'parentId',
                            align: 'left',
                            width: 100,
                            hide:true,
                            formslot: true,
                            slot: true,
                            span:12
                        },
                        {
                            label: '行业类型',
                            prop: 'type',
                            align: 'left',
                            width: 100,
                            hide:true,
                            span:12,
                            type: "select",
                            dicData: DIC.typeDicts,
                            multiple : true
                        },
                        {
                            label: '启用',
                            prop: 'isUse',
                            align: 'left',
                            width: 100,
                            search:'true',
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
                                    message: '请输入指标内容',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '排序',
                            prop: 'sort',
                            align: 'left',
                            width: 100,
                            sortable:true,
                            type: 'number',
                            rules: [
                                {
                                    required: true,
                                    message: '请输入数字',
                                    trigger: 'blur'
                                }
                            ]
                        },
                        {
                            label: '字段类型',
                            prop: 'fieldType',
                            search:'true',
                            align: 'left',
                            type: "select",
                            dicData: DIC.fieldTypeDict,
                            span:24
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
                tableOption1:{
                    addBtn:false,
                    editBtn:false,
                    addRowBtn:true,
                    cellBtn:true,
                    refreshBtn:false,
                    columnBtn:false,
                    filterBtn:false,
                    keyId: 'id',
                    border:true,
                    column: [{
                        label: '选项',
                        prop: 'option',
                        cell: true,
                        rules: [
                            {
                                required: true,
                                message: '请输入单选项',
                                trigger: 'blur'
                            }
                        ]
                    },{
                        label:'排序',
                        prop:'sort',
                        cell:true,
                        type: 'number',
                        rules:[
                            {
                                required: true,
                                message: '请输入数字',
                                trigger: 'blur'
                            }
                        ]
                    }]
                },
                tableData1: [
                    {
                        id: 1,
                        option: '是',
                        sort:1
                    },
                    {
                        id: 2,
                        option: '否',
                        sort:2
                    }
                ],
                tableOption2:{
                    addBtn:false,
                    editBtn:false,
                    addRowBtn:true,
                    cellBtn:true,
                    refreshBtn:false,
                    columnBtn:false,
                    filterBtn:false,
                    border:true,
                    keyId: 'id',
                    column: [{
                        label: '选项',
                        prop: 'option',
                        cell: true,
                        rules: [
                            {
                                required: true,
                                message: '请输入单选项',
                                trigger: 'blur'
                            }
                        ]
                    },{
                        label:'排序',
                        prop:'sort',
                        cell:true,
                        type: 'number',
                        rules:[
                            {
                                required: true,
                                message: '请输入数字',
                                trigger: 'blur'
                            }
                        ]
                    }]
                },
                tableData2: [{
                    id: 1,
                    option: '未配置',
                    sort:1
                }, {
                    id: 2,
                    option: '完好有效',
                    sort:2
                }, {
                    id: 3,
                    option: '失效',
                    sort:3
                }, {
                    id: 4,
                    option: '缺少',
                    sort:4
                }],
                tableOption3:{
                    addBtn:false,
                    editBtn:false,
                    addRowBtn:true,
                    cellBtn:true,
                    refreshBtn:false,
                    columnBtn:false,
                    filterBtn:false,
                    border:true,
                    keyId: 'id',
                    column: [{
                        label: '选项',
                        prop: 'option',
                        cell: true,
                        rules: [
                            {
                                required: true,
                                message: '请输入单选项',
                                trigger: 'blur'
                            }
                        ]
                    },{
                        label:'排序',
                        prop:'sort',
                        cell:true,
                        type: 'number',
                        rules:[
                            {
                                required: true,
                                message: '请输入数字',
                                trigger: 'blur'
                            }
                        ]
                    }]
                },
                tableData3: [
                    {
                        id: 1,
                        option: '是',
                        sort:1
                    },
                    {
                        id: 2,
                        option: '否',
                        sort:2
                    }
                ],
                tableOption4:{
                    addBtn:false,
                    editBtn:false,
                    addRowBtn:true,
                    cellBtn:true,
                    refreshBtn:false,
                    columnBtn:false,
                    filterBtn:false,
                    border:true,
                    keyId: 'id',
                    column: [{
                        label: '选项',
                        prop: 'option',
                        cell: true,
                        rules: [
                            {
                                required: true,
                                message: '请输入单选项',
                                trigger: 'blur'
                            }
                        ]
                    },{
                        label:'排序',
                        prop:'sort',
                        cell:true,
                        type: 'number',
                        rules:[
                            {
                                required: true,
                                message: '请输入数字',
                                trigger: 'blur'
                            }
                        ]
                    }]
                },
                tableData4: [{
                    id: 1,
                    option: '未配置',
                    sort:1
                }, {
                    id: 2,
                    option: '完好有效',
                    sort:2
                }, {
                    id: 3,
                    option: '失效',
                    sort:3
                }, {
                    id: 4,
                    option: '缺少',
                    sort:4
                }],
            }
        },
        created(){
            this.type = this.option.column[0];
            this.targetTree2 = DIC.targetTree;
        },
        computed: {
            ...mapGetters([
                'elements',
                'permissions'
            ])
        },
        methods:{
            handleChange(column) {
                this.type = column
                //this.$message.success(JSON.stringify(column.label))
            },
            handAdd: function (row) {
                //第一次进入无法刷新上级指标
                this.tabForm2.parentId = row.id;
                this.$refs.crud.rowAdd();
            },
            handleEdit(row, index) {
                this.$refs.crud.rowEdit(row, index)
            },
            handleDelete(row) {
                this.$confirm('此操作将永久删除, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    delObj(row.id).then(() => {
                        this.getList()
                        this.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success',
                            duration: 2000
                        })
                    })
                })
            },
            isAddChildren:function (row) {
                if(row.fieldType==''||row.fieldType==null){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }
</script>