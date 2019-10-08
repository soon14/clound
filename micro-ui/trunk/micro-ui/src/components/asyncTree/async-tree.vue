
<template>
  <div class="async-tree" >
    <el-form ref="form" label-width="80px">
      <el-input v-model="inputValue" :placeholder="placeholder" @focus="showPop" :size="inputSize" :style="inputStyle"></el-input>
    </el-form>
    <el-dialog  :visible.sync="popVisible" append-to-body width="30%">
      <el-input
              placeholder="请输入内容"
              size="small"
              v-show="isSearch"
              v-model="searchValue">
        <el-button slot="append" icon=" el-icon-search" @click="treeSearch"></el-button>
      </el-input>
      <el-tree
              ref="tree"
              :show-checkbox="showCheckbox"
              :default-expand-all="expandAll"
              node-key="id"
              highlight-current
              :props="props"
              :load="loadNode"
              :lazy="async">
      </el-tree>

    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "asyncTree",
  props: {
    placeholder:{
      type: String,
      default: "请选择",
    },
    isSearch:{
      type: Boolean,
      default: true,
    },
    showCheckbox: {
      type: Boolean,
      default: true
    },
    expandAll:{
      type:Boolean,
      default:false
    },
    async:{
      type:Boolean,
      default:true,
    },
    expandLevel:{
      type:Number,
      default:2
    },
    label:{
      type:String,
      default:"label"
    },
    props:{
      type:Object,
      default:{
        label: 'label',
        code:'code',
      }
    },
    inputSize:{
      type:String,
      default:"small"
    },
    inputStyle:String,
    result:Object //返回值
  },
  data() {
    return {
      params:undefined,
      inputValue:"",
      searchValue:"",
      popVisible:false,
      treeNodes:[{
        id: 1,
        label: '一级 1',
        children: [{
          id: 4,
          label: '二级 1-1',
          children: [{
            id: 9,
            label: '三级 1-1-1'
          }, {
            id: 10,
            label: '三级 1-1-2'
          }]
        }]
      }, {
        id: 2,
        label: '一级 2',
        children: [{
          id: 5,
          label: '二级 2-1'
        }, {
          id: 6,
          label: '二级 2-2'
        }]
      }, {
        id: 3,
        label: '一级 3',
        children: [{
          id: 7,
          label: '二级 3-1'
        }, {
          id: 8,
          label: '二级 3-2'
        }]
      }],
    }
  },
  created() {
    this.initTree()

  },
  computed: {

  },
  watch: {
  },
  methods: {
    initTree:function () {

      //展开层级

    },
    loadNode:function (node, resolve) {
      //this.$emit("treeSearch",node)
    },
    treeSearch:function(){

    },
    showPop(){
      this.popVisible=true
    },
  },
}
</script>

<style>
  .async-tree{
    display: -webkit-inline-box;
  }
</style>
