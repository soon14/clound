<!-- 模态弹窗 -->
<template>
    <el-tree class="filter-tree"
             :data="data"
             :check-strictly="false"
             highlight-current
             :props="treeProps"
             ref="scopeTree"
             :default-checked-keys="checkedDsScope"
             :default-expand-all="true"
             show-checkbox
             node-key="id"
             :filter-node-method="filterNode">
    </el-tree>
</template>
<script>

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
        ]
    }

    export default {
        name: 'target-list',
        data () {
            return {
                type:"",
                form:{
                    id:''
                },
                treeProps:{
                    label: "content",
                    value: 'id'
                },
                checkedDsScope: [],
                data:[
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
                ]
            }
        },
        props: {
            styleObj: { // 用于承接样式类
                type: Object,
                default: function () {
                    return {
                        position: 'fixed',
                        bottom: 0,
                        top: 0,
                        left: 0,
                        width: '100%',
                        background: 'rgba(113,111,111,0.5)'
                    }
                }
            },
            dialogVisible: {
                type: Boolean,
                default: false
            },
            unitType:{
                type: String,
                required: true
            },
            result:{
                type:Object,
                required:false
            }
        },
        created(){
        },
        methods: {
            // 确定
            sureClick () {
                this.dialogVisible = false;
                return new Promise(function (resolve) {
                    resolve('sure')
                }, function (reject) {})
            },
            // 取消
            cancelClick () {
                this.dialogVisible = false;
                return new Promise(function (resolve) {
                    resolve('cancel')
                }, function (reject) {})
            },
            hiddeDialg:function(){
                this.$emit("hiddeDialg") //触发父组件中的increaseMore事件,并将num传递给父组件
            },
            nodeClick(){

            },
            handleCheckChange(){

            },
            filterNode(value, data) {
                if (!value) return true
                return data.label.indexOf(value) !== -1
            },
            commitChoices(){
                this.$emit("hiddeDialg");
                this.result = this.$refs.scopeTree.getCheckedNodes();
            },
        }
    }
</script>
<style scoped lang='scss'>
    .self-modal{
        z-index: 3000;
    }
</style>