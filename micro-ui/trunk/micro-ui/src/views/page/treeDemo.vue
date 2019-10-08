<template>
    <basic-container>
        <div style="height: auto">
            <!--这里是调用的父组件-->
            <vTree
                    v-model="inputVal"
                    :treeNode="treeNode"
                    :asyncCall="asyncCall"
                    @treeSearch="treeSearch"
                    :checkBox=false
                    @submit="submit"
                    label="姓名">
                <template>
                        <el-button size="small"   slot="btn">暂时留一个</el-button>
                </template>
            </vTree>
            <br>
            <vTree
                    :treeNode="treeNode"
                    :asyncCall="asyncCall"
                    @treerSeach="treeSearch"
                    :checkBoxType=false
                    @submit="submit"
                    ref="vtree"
            >
                <template>
                    <!--插槽-->
                    <div>
                        插槽, 用this.$refs.vtree.showPop();触发弹框
                    </div>
                </template>
            </vTree>
           <!--参数在这里-->
            <hr>
            <hr>
            <p>参数：不写则使用默认值</p>
            <div>
            showForm:{
                type: Boolean,
                default: true,
            }   默认样式
                <br>
            placeholder:{
                type: String,
                default: "请选择",
            },
                <br>
            label:{
                type: String,
                default: "单位名称",
            },
                <br>
            isShow:{
                type: Boolean,
                default: true,
            },搜索框
                <br>
            treeNode: {
                type: Array,
            },渲染数据
                <br>
            allOpen: {
                type: Boolean,
                default: false,
            },全部展开
                <br>
            checkBox: {
                type: Boolean,
                default: true,
            },复选框
                <br>
            checkBoxType: {
                type: Boolean,
                default: true,
            },级联
                <br>
            asyncCall: {
                type: Function,
            },异步回调
                <br>
            hiddenLine: {
                type: Boolean,
                default: false,
            },连线
                <br>
            async: {
                type: Boolean,
                default: true,
            },异步
                <br>
            call: {
                type: Function,
            },初始化回调数据
            </div>
            <Tree
                    v-model="inputVal"
                    :treeNode="treeNode"
                    :asyncCall="asyncCall"
                    @treeSearch="treeSearch"
                    @submit="submit"
            >
            </Tree>
        </div>
    <!--    <elTree
                :defaultProps="defaultProps"
                ref="eltree"
                @load="loadNode"
                @handleCheckChange="handleCheckChange"
        />-->

    </basic-container>
</template>
<script>
    import Tree from "@/components/vtree/bouncedTree";
    import vTree from "@/components/vtree/unbouncedTree";
   import {fetchTree} from "@/api/admin/dept";

    export default {
        components: {Tree,vTree},
        data() {
            return {
                treeNode: [],
                inputVal:"默认值",

                //elTree的参数
                data:[],
                defaultProps:{
                    children: 'children',
                    label: 'name'
                },
            }
        },
        methods: {
            //搜索处理，isShow=false可不写,data搜索的值

            treeSearch(data){
                //console.log(data)
                fetchTree().then(data=>{
                    let zNodesData = data.data.data;
                    this.treeNode=zNodesData;//
                }).catch(() => {
                });
            },
            /*异步回调函数 data 当前节点数据 call 回调函数*/
            //this.$refs.treeDome.loading = true;
            asyncCall(data, call) {
               // this.$refs.treeDome.loading = true;
                let pasmes={pid:data.id,name:data.name};
                fetchTree(pasmes).then(data=>{
                   // this.$refs.treeDome.loading = false;
                    let zNodesData = data.data.data;
                    call(zNodesData);
                    }).catch(() => {
                });
            },
            //checkBox=true时有3个参数，第3个为勾选中的所有节点信息，第2个为勾选中的的所有节点的name和id,第一个为文本框内容
            //checkBox=false时有2个参数，第2个选中的节点信息，第一个为文本框内容
            submit(data,checkdata){
               console.log(data,checkdata)
            },

            /*elTree的方法*/
            loadNode(node, resolve){
                console.log(node,11111)
                if (node.level === 0) {
                    fetchTree().then(data=>{
                        let zNodesData = data.data.data;
                        return resolve(zNodesData);
                    }).catch(() => {
                    });
                }else{
                    fetchTree({name:node.data.name,id:node.data.id}).then(data=>{
                        let zNodesData = data.data.data;
                            resolve(zNodesData);
                    }).catch(() => {
                    });
                }
            },
             handleCheckChange(data, checked, indeterminate) {
                console.log(data, checked, indeterminate);
             },
             handleNodeClick(data) {
                console.log(data);
             },


        },
        mounted(){
            console.log(this.inputVal);
        },
        created() {
            //初始化
            fetchTree().then(data=>{
                let zNodesData = data.data.data;
                this.treeNode=zNodesData;
             }).catch(() => {
             });


        },
       /* watch:{
            inputVal(newVal){
                console.log(newVal)
            }
        }*/
  }
</script>

<style lang="scss">
    .poptree .el-form-item__content{
        width:300px;
    }
</style>

