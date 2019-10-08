<template>
    <basic-container>
        <div style="height: auto">
            <popTree
                    :treeNode="treeNode"
                    :asyncCall="asyncCall"
                    @treeSeach="treeSeach"
                    @submit="submit"
                    label="label"
                    placeholder="占位符不设默认:请输入"
                    :checkBox="true"
            >
            </popTree>
           <!--参数在这里-->
            <p>参数：不写则使用默认值</p>
            <div>
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
            },
                <br>
            treeNode: {
            type: Array,
            },
                <br>
            allOpen: {
            type: Boolean,
            default: false,
            },
                <br>
            nodeTrigger: {
            type: Boolean,
            default: false,
            },
                <br>
            checkBox: {
            type: Boolean,
            default: true,
            },
                <br>
            checkBoxType: {
            type: Boolean,
            default: true,
            },
                <br>
            asyncCall: {
            type: Function,
            },
                <br>
            hiddenLine: {
            type: Boolean,
            default: false,
            },
                <br>
            async: {
            type: Boolean,
            default: true,
            },
            </div>
           <!-- <div style="padding:0 20px">
                单选、复选、复选是/否级联选择（默认级联选中）、是否展示下拉虚线、 <br>
                点击节点回调函数，返回true 选中并触发选中事件，false相反， <br>
                节点选中事件，返回选中节点信息和上个选中节点信息，选中/取消选中复选框事件， <br>
                将返回所有选中复选框数据 异步加载数据 <br>
                <h4>自定义变量和函数说明</h4>
                <p class="canshu">默认开启和选中由后台数据返回，字段checked: true,open: true,</p>
                <p><span class="canshu">:treeNode  type:json  default:[]</span><span>&nbsp;&nbsp;标准json tree节点，name属性必填</span></p>
                <p><span class="canshu">:checkBox    type:boolean     default:false </span><span>&nbsp;&nbsp;开启/关闭复选框</span></p>
                <p><span class="canshu">:checkBoxType    type:boolean     default:true</span> <span>&nbsp;&nbsp;开启/关闭级联选中</span></p>
                <p><span class="canshu">:async    type:boolean     default:true </span><span>&nbsp;&nbsp;是否开启异步 默认开启</span></p>
                <p><span class="canshu">:beforeClick    type:Function  参数：当前节点信息 </span> <span>&nbsp;&nbsp;击节点前触发回调函数，return false 无法触发clickNode事件</span></p>
                <p><span class="canshu">:clickNode    type:Function     参数1:当前节点信息  参数2(上一个选中的节点信息)</span> <span>&nbsp;&nbsp;点击节点 回调函数</span></p>
                <p><span class="canshu">@checkBoxCall    type:Function     参数:所有选中的节点集合 </span> <span>&nbsp;&nbsp;选中/取消选中复选框回调函数</span></p>
                <p><span class="canshu">:asyncCall    type:Function </span><span>异步回调函数function(data,call)，data:current node， call 回调执行，接收一个数组参数</span></p>
            </div>-->

           <!-- <div class="mytree">
                <div>基本demo</div>
                <Tree
                        :isShow="isShow"
                        v-model="inputValue"
                        :treeSeach="treeSeach"
                        :treeNode="treeNode"
                        :checkBox="checkBox"
                        :beforeClick="beforeClick"
                        :clickNode="clickNode"
                        :checkBoxType="checkBoxType"
                        @checkBoxCall="asyncCheckBoxCall"
                        :async="async"
                        :asyncCall="asyncCall"
                        :loadImg="loadImg"
                />
            </div>-->
            <!--弹框demo-->
        </div>
    </basic-container>
</template>
<script>
   import Tree from "@/components/ztree/vtree";
    import popTree from "@/components/ztree/poptree";
   //import {fetchList,fetchTree,fetchUser,fetchUserOne} from "@/api/portal/tyyh/tyyh";
   import {fetchTree} from "@/api/admin/dept";

    export default {
        components: {Tree,popTree},
        data() {
            return {
                treeNode: [],
            }
        },
        methods: {
            //搜索处理，isShow=false可不写
            treeSeach(){
                fetchTree().then(data=>{
                    let zNodesData = data.data.data;
                    this.treeNode=zNodesData;
                }).catch(() => {
                });
            },
            /*异步回调函数 data 当前节点数据 call 回调函数*/
            asyncCall(data, call) {
                let pasmes={pid:data.id,name:data.name};
                fetchTree(pasmes).then(data=>{
                    let zNodesData = data.data.data;
                    call(zNodesData);
                    }).catch(() => {
                });
            },
            //data为当前节点数据
            submit(data){
                console.log(data)
            }
        },
        mounted(){


        },
    created() {
        //初始化
        fetchTree().then(data=>{
            let zNodesData = data.data.data;
            this.treeNode=zNodesData;
         }).catch(() => {
         });
    },
  }
</script>

<style lang="scss">
    .canshu{
        color:red;
    }
    .shili{
        border:2px solid #cc3a28;
        width:400px;
        margin:10px auto;
    }
</style>

