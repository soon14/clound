<template>
  <el-tree
          lazy
          :props="defaultProps"
          :load="loadNode"
          @node-click="handleNodeClick">
  </el-tree>
</template>
<script>
  import {fetchDeptTree,fetchTree} from "@/api/admin/dept";
  export default {
    name: "table_user",
    data() {
      return {
          defaultProps: {
              children: 'children',
              label: 'name',
              isLeaf: "leaf"
          },
          rid:0,
      };
    },
    mounted() {
    },
    methods: {
        handleNodeClick(data) {
            console.log("node", data);
        },
        // 异步树叶子节点懒加载逻辑
        loadNode(node, resolve) {
            // 一级节点处理
            if (node.level === 0) {
                this.requestTree(resolve);
            }
            // 其余节点处理
            if (node.level >= 1) {
                this.getIndex(node, resolve);
            }
        },
        // 异步加载叶子节点数据函数
        getIndex(node, resolve) {
            console.log(node)
            var param = {
            };
            fetchTree(param).then(response => {
                let treeData = response.data.data;
                // 此处需要后台传过来一个值来判断每个节点有无子节点,实际项目中必须加上（如isRealLeaf），不传都默认为叶子节点
                // treeData.forEach(value => {
                //   if (value.isRealLeaf === 'Y') {
                //     value.leaf = true//不为叶子节点
                //   } else {
                //     value.leaf = false//为叶子节点
                //   }
                // })
                resolve(treeData);
            }).catch(err => {
            });
        },
        // 首次加载一级节点数据函数
        requestTree(resolve) {
            //要传的参数
            let param = {
            };
            fetchTree(param).then(response => {
                let treeData = response.data.data;
                // 此处需要后台传过来一个值来判断每个节点有无子节点,实际项目中必须加上（如isRealLeaf），不传都默认为叶子节点
                //  treeData.forEach(value => {
                //   if (value.isRealLeaf === 'Y') {
                //     value.leaf = true//不为叶子节点
                //   } else {
                //     value.leaf = false//为叶子节点
                //   }
                // })
                resolve(treeData);
            }).catch(err => {
            });
        }
    }

  };
</script>


