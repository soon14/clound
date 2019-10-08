<template>
    <basic-container>
        <label class="el-form--label-left">字典：</label>
        <async-tree :result="result"
            :show-checkbox="showCheckbox"
            :expand-all="expandAll"
            :expand-level="expandLevel"
            :async="async"
            :label="label"
            :props="props"
            :input-style="inputStyle"
        ></async-tree>
    </basic-container>
</template>
<script>

    import asyncTree from "@/components/asyncTree/async-tree";

    export default {
        components: {asyncTree},
        data() {
            return {
                showCheckbox:true,  //显示复选框，默认为true，非必填
                expandAll:false,  //展开所有节点，默认为false，非必填
                expandLevel:2,  //展开树层级，默认为2，非必填
                async:true,  //是否异步加载子节点，非必填
                label:"label",  //显示值的字段名.默认为label,非必填
                props:{  //设置树节点包含信息(需要规定显示值？指定获取值？)，非必填
                    pid:'pid',
                    id:'id',
                    label: 'label',
                    value:'code',
                    disabled: 'disabled',
                    children: 'children',
                },
                result:{
                    label:"", code:""
                },
                inputStyle:"width:300px;",  //输入框长度，非必填
            }
        },
        created() {
        },
        mounted(){
        },
        methods: {
        },
  }
</script>

<style lang="scss">
</style>

