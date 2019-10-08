<template>
    <div class="poptree">
        <div>
            <el-form ref="form"  v-show="showForm">
                <el-form-item :label="label">
                    <el-input
                            v-model="formName"
                            :placeholder="placeholder"
                            @focus="showPop"
                            :size="inputSize">
                    </el-input>
                </el-form-item>
            </el-form>
            <template>
                <slot></slot>
            </template>
        </div>

        <el-dialog  :visible.sync="popVisible" append-to-body>
            <div>
                <el-input
                        placeholder="请输入内容"
                        v-show="isShow"
                        v-model="newValue">
                    <el-button slot="append" icon=" el-icon-search" @click="treeSearch"></el-button>
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
                    <template>
                        <slot name="btn"></slot>
                    </template>

                </div>
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
            showForm:{
                type: Boolean,
                default: true,
            },
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
            value:{
                default:" "
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
                formName:"",
                data:"",
                checkedBoxArr:[]
            }
        },
        methods: {
            treeSearch(){
                let value = this.newValue;
                this.$emit('treeSearch',value);
            },
            clickNode(data, oldData){
                this.data=data;
            },
            beforeClick(node){
                return true
            },
            clear(){
                this.checkedBoxCallArr.forEach(item=>{
                    item.checked=false;
                });
                this.checkedBoxCallArr=[];
                this.newValue="";
                this.formName="";
                if(this.checkBox){
                    this.checkedBoxArr=[];
                    this.$emit('submit', this.formName,this.checkedBoxArr,this.checkedBoxCallArr);
                }else{
                    this.data.id="";
                    this.$emit('submit',this.formName,this.data.id);
                }
            },
            close(){
                this.popVisible=false
            },
            init() {
                if(this.treeNode.length<1 || !this.treeNode){
                    this.showWait=true;
                }else{
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
            submit(){
                this.popVisible=false;
                let checkedArr=[];
                this.checkedBoxArr=[];
                this.checkedBoxCallArr.forEach(item=>{
                    checkedArr.push(item.name);
                    this.checkedBoxArr.push({name:item.name,id:item.id})
                });
                checkedArr=checkedArr.toString();

                if(this.checkBox){
                    this.formName = checkedArr;
                    this.$emit('submit', this.formName,this.checkedBoxArr,this.checkedBoxCallArr);
                }else{
                    this.formName=this.data.name;
                    this.$emit('submit',this.formName,this.data.id);
                }
            },
            showPop(){
                this.popVisible=true
            },
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
                        this.treeSearch();
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
            value:{
                handler(newVal) {
                    this.formName = newVal;
                },
                immediate: true
            },
            formName:{
                handler(newVal) {
                    this.$emit('input',newVal);
                },
                deep: true
            }
        }
    }
</script>

<style lang="scss">
    .poptree{
        display: inline-block;
        .el-dialog{
            width:400px !important;
        }
        .showWait{
            height: 100px;
            line-height: 100px;
            text-align: center;
        }
        .el-form-item__content{
            float: left;
        }
        .el-input--small .el-input__inner {
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    }
</style>