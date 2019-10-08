<template>
    <div class="poptree">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item :label="label" v-if="!isShowLabel">
                <el-input v-model="form.name" :placeholder="placeholder" @focus="showPop" :size="inputSize">
                </el-input>
            </el-form-item>
            <el-input v-model="form.name" :placeholder="placeholder" @focus="showPop" v-if="isShowLabel" :size="inputSize"></el-input>
        </el-form>
        <el-dialog  :visible.sync="popVisible" append-to-body>
            <el-input
                    placeholder="请输入内容"
                    v-show="isShow"
                    v-model="newValue">
                <i slot="prefix" class="el-input__icon el-icon-search" @click="treeSeach"></i>
            </el-input>
            <ul class="ztree">
                <vTree v-for="(node,index) in treeData"
                       :key="index"
                       :checkBoxType="checkBoxType"
                       :allOpen="allOpen"
                       :beforeClick="beforeClick"
                       :checkBox="checkBox"
                       :nodeTrigger="nodeTrigger"
                       :index="index"
                       :tree="node"
                       :first="index===0"
                       :last="treeData.length-1===index"
                       :currentArray="treeData"
                       :parentTree="node.parentTree"
                       :rootData="treeData"
                       :checkBoxCallInit="checkBoxCallInit"
                       :checkBoxCall="checkBoxCall"
                       :clickNode="clickNode"
                       :hiddenLine="hiddenLine"
                       :async="async"
                       :asyncCall="asyncCall"
                       :popVisible="popVisible"
                />
            </ul>
            <div v-show="showWait" class="showWait">暂无数据，请刷新后再试...</div>
            <div slot="footer"
                 class="dialog-footer">
                <el-button type="primary" size="small" @click="submit()">确 定</el-button>
                <el-button size="small" @click="clear()">清 空 </el-button>
                <el-button size="small" @click="close()">关 闭</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import vTree from './tree-core'
    export default {
        name: "poptree",
        components: {vTree},
        props: {
            placeholder:{
                type: String,
                default: "请选择",
            },
            label:{
                type: String,
                default: "",
            },
            isShow:{
                type: Boolean,
                default: true,
            },
            treeNode: {
                type: Array,
            },
            allOpen: {
                type: Boolean,
                default: false,
            },
            nodeTrigger: {
                type: Boolean,
                default: false,
            },
            checkBox: {
                type: Boolean,
                default: true,
            },
            checkBoxType: {
                type: Boolean,
                default: true,
            },
            asyncCall: {
                type: Function,
            },
            hiddenLine: {
                type: Boolean,
                default: false,
            },
            async: {
                type: Boolean,
                default: true,
            },
            inputSize:{
                type:String,
                default:"small"
            },
            defaultInput:{
                type:String,
                default:""
            }
        },
        data() {
            return {
                newValue:"",
                popVisible:false,
                showWait:false,
                treeData: [],
                line: '',
                first: true,
                allOpens: this.allOpen,
                checkedBoxCallArr: [],
                form:{
                    name:this.defaultInput,
                },
                data:"",
            }
        },
        methods: {
            treeSeach(){
                let value = this.newValue;
                this.$emit('treeSeach',value);
            },
            clickNode(data, oldData){
                this.data=data;
            },
            beforeClick(node){
               return true
            },
            clear(){
                this.newValue="";
                this.form.name="";
                this.$emit("clear");
                this.popVisible=false;
            },
            close(){
                this.popVisible=false
            },
            init() {
                if(!this.treeNode==[]){
                    this.showWait=false;
                    let tempList = JSON.parse(JSON.stringify(this.treeNode));
                    let initTree = (tree, parent) => {
                        for (let index = 0; index < tree.length; index++) {
                            let m = tree[index];
                            //tree.forEach((m, index) => {
                            if (!m.hasOwnProperty("id")) {
                                m.id = m.hasOwnProperty("id") ? m.id : null;
                            }
                            if (!m.hasOwnProperty("open")) {
                                m.open = m.hasOwnProperty("open") ? m.open : false;
                            }
                            if (!m.hasOwnProperty("checked")) {
                                m.checked = m.hasOwnProperty("checked") ? m.checked : false;
                            }
                            if (!m.hasOwnProperty("checkBox")) {
                                m.checkBox = m.hasOwnProperty("checkBox") ? m.checkBox : false;
                            }
                            if (!m.hasOwnProperty("nodeTrigger")) {
                                m.nodeTrigger = m.hasOwnProperty("nodeTrigger") ? m.nodeTrigger : false;
                            }
                            if (!m.hasOwnProperty("checkBoxType")) {
                                m.checkBoxType = this.checkBoxType
                            }
                            if (!m.hasOwnProperty("last")) {
                                m.last = m.hasOwnProperty("last") ? m.last : false;
                            }
                            if (!m.hasOwnProperty("first")) {
                                m.first = m.hasOwnProperty("first") ? m.first : false;
                            }
                            if (!m.hasOwnProperty("active")) {
                                m.active = m.hasOwnProperty("active") ? m.active : false;
                            }
                            if (!m.hasOwnProperty("async")) {
                                m.async = this.async;
                            }
                            if (!m.hasOwnProperty("hiddenLine")) {
                                m.hiddenLine = this.hiddenLine;
                            }
                            if (!m.hasOwnProperty("parentTree")) {
                                m.parentTree = parent ? parent : null;
                            }

                            m.children = m.children || [];
                            if (m.children.length > 0)
                                initTree(m.children, m);
                            //});
                        }
                    };
                    initTree(tempList, null);

                    this.treeData = tempList;
                    this.line = 'line';
                    if (this.first)
                        this.$emit('call', this.treeData);
                }else{
                    this.showWait=true;
                }

            },
            changeOpenStatus() {
                let changeOpen = (data) => {
                    data.forEach(d => {
                        d.open = this.allOpen;
                        if (d.children) changeOpen(d.children);
                    });
                };
                changeOpen(this.treeData);
            },
            checkBoxCallInit(arr) {
                arr.forEach(a => {
                    this.checkedBoxCallArr.push(a);
                });
            },
            checkBoxCall(arr, isAdd) {
                if (isAdd)
                    arr.forEach(a => {
                        this.checkedBoxCallArr.push(a);
                    });
                else {
                    arr.forEach(a => {
                        if (this.checkBoxCall.length === 0)
                            return;
                        let key = (a.id ? a.id : null) + a.index + a.name;

                        this.checkedBoxCallArr.forEach((ss, index) => {
                            if (((ss.id ? ss.id : null) + ss.index + ss.name) === key) {
                                this.checkedBoxCallArr.splice(index, 1);
                            }
                        });
                    });
                }
                this.$emit('checkBoxCall', this.checkedBoxCallArr);
            },
            submit(data){
                //console.log(data);
                let flag = true;
                if(!this.checkBoxType){
                    if(this.data.hasChildren){
                        flag = false;
                    }
                }
                if(flag){
                    data=this.data;
                    this.popVisible=false;
                    this.form.name=data.name;
                    this.$emit('submit',data);
                }else{
                    this.$message.warning("请勾选子节点");
                }
            },
            showPop(){
                this.popVisible=true
            },
            isShowLabel(){
                return (this.label==null||this.label==''||this.label==undefined);
            }
        },
        created() {
            this.init();
        },
        update() {
            // this.init();
        },
        mounted() {
            /*Vue.nextTick(() => {
              this.init();
            });*/
            /*复选框回调*/

            this.$emit('checkBoxCall', this.checkedBoxCallArr);
        },
        watch: {
            popVisible:{
                handler(newVal){
                    if(newVal==true){
                        this.newValue=""
                        this.treeSeach();
                    }
                },
                deep:true
            },
            allOpen() {
                this.changeOpenStatus()
            },
            treeNode:{
                handler(newVal, oldval) {
                    this.oldval
                    this.treeNode=newVal;

                    this.init();
                },
                deep: true
            },
        }
    }
</script>

<style lang="scss">
    .poptree{
        display: inline-block;
       /* .el-form{
            display: inline-block;
        }*/
        .el-dialog{
            width:400px !important;
        }
        .showWait{
            height: 100px;
            line-height: 100px;
            text-align: center;
        }
    }
</style>