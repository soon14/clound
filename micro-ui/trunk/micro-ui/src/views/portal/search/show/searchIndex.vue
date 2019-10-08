<template>
    <basic-container>
        <el-container>
            <el-aside class="div-left">
                <el-input placeholder="请输入关键字" v-model="filterText" size="small" style="margin-bottom: 5px">
                    <i slot="suffix" class="el-input__icon el-icon-search"></i>
                </el-input>
                <div class="div-tree">
                    <el-scrollbar style="overflow-x: hidden!important;">
                        <el-tree
                                ref="tree"
                                default-expand-all
                                highlight-current
                                :indent="indent"
                                :props="defaultProps"
                                :data="treeData"
                                :filter-node-method="filterNode"
                                @node-click="nodeClick">
                        </el-tree>
                    </el-scrollbar>
                </div>

            </el-aside>
            <el-main>
                <div
                        :is="modelName"
                        v-bind:detail-row="detailRow"
                        v-bind:scheme-id="schemeId">

                </div>
            </el-main>
        </el-container>
    </basic-container>


</template>

<script>
    import {searchIndex} from '@/api/portal/search/show/searchShow'
    import searchShowList from '@/views/portal/search/show/showList'
    import {fetchSchemeTree} from '@/api/portal/search/scheme/searchScheme'
    import Vue from 'vue'
    import {validatenull} from "@/util/validate";

    export default {
        name: "searchShow",
        components: {
            searchShowList
        },
        data() {
            return {
                showFlag: false,
                filterText: undefined,
                modelName: '',
                indent: 14,
                defaultProps: {
                    label: 'name',
                    value: 'id'
                },
                detailRow: {},
                schemeId: undefined,
                treeData: [],
                fromDetail: {},
                treeDeptData: [],
                checkedKeys: [],
                form: {},


            };
        },

        mounted() {

        },
        created() {
            this.initTree()

        },
        watch: {
            filterText(val) {
                this.$refs.tree.filter(val);
            }
        },
        methods: {
            filterNode(value, data) {
                if (!value) {
                    return true;
                }
                return data.name.indexOf(value) !== -1;
            },
            initTree() {
                fetchSchemeTree().then(response => {
                    this.treeData = response.data.data || []
                    this.findFirst(this.treeData)
                    this.searchIndexById(this.findFirst(this.treeData))

                }).catch((error) => {

                })
            },
            findFirst(treeData) {
                let id = undefined
                if (!validatenull(treeData)) {
                    for (let i = 0, len = treeData.length; i < len; i++) {
                        if (treeData[i].scheme == true) {
                            id = treeData[i].id
                            return id
                        }
                        id = this.findFirst(treeData[i].children)
                        if (!validatenull(id)) {
                            return id
                        }
                    }
                }
                return id
            },
            checkModel(id) {
                let model = "search_" + id
                this.modelName = model
                Vue.component(model, function (resolve) {
                    require([`@/views/portal/search/show/showList`], resolve)
                })
            },
            searchIndexById(id) {
                if (validatenull(id)) {
                    return
                }

                searchIndex({id: id}).then(response => {
                    let config = response.data.data || {};
                    let data = eval('(' + config.searchContent + ')')
                    this.detailRow = data
                    this.schemeId = id
                    this.checkModel(id)
                    this.showFlag = true

                }).catch(e => {

                })
            },
            nodeClick(data) {
                if (data.scheme == true) {
                    this.searchIndexById(data.id)
                } else {
                    // this.$message.success("该节点是目录")
                }

            },
        }
    };
</script>
<style lang="scss">
    .div-left {
        max-width: 220px;
        overflow: hidden;

    }

    .div-tree {
        min-height: 200px;

        overflow-x: auto !important;
        overflow-y: auto !important;
    }

    .div-tree .el-tree {
        min-width: 100%;
        margin: 5px;
        display: inline-block;
    }
</style>

