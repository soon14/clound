

<template>
    <div class="user">
        <basic-container>
            <el-row :span="24">
                <el-col :xs="24"
                        :sm="24"
                        :md="5"
                        class="user__tree">
                    <el-tree
                            :props="props"
                            :load="loadNode"
                            lazy
                            @node-click="handleNodeClick">
                    </el-tree>
                </el-col>
                <el-col :xs="24"
                        :sm="24"
                        :md="19"
                        class="user__main">
                    <avue-crud :option="option"
                               ref="crud"
                               v-model="form"
                               :page="page"
                               @on-load="getList"
                               :table-loading="listLoading"
                               @refresh-change="handleRefresh"
                               v-loading.fullscreen.lock="fullscreenLoading"
                               :data="list">
                        <template slot="menuLeft">
                            <el-button
                                       @click="DownUser"
                                       size="small"
                                       type="primary"
                                       icon="el-icon-upload">同步用户
                            </el-button>
                        </template>
                    </avue-crud>
                </el-col>
            </el-row>
        </basic-container>
    </div>

</template>

<script>
    import {fetchList,fetchTree,fetchUser} from "@/api/portal/tyyh/tyyh";
    import {tableOption} from '@/const/crud/portal/tyyh/tyyh';
    import {mapGetters} from "vuex";

    export default {
        name: "table_user",
        data() {
            return {
                props: {
                    value:'id',
                    label: 'text',
                    children: 'children'
                },
                orgId:'',
                treeData: [],
                option: tableOption,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                list: [],
                fullscreenLoading: false,
                listLoading: false,
                form: {},
            };
        },
        computed: {
            ...mapGetters(["permissions"])
        },

        created() {

        },
        methods: {
            handleNodeClick(data) {
                this.orgId=data.id
                this.page.page = 1;
                this.listLoading = true;
                fetchList(Object.assign({
                    current: this.page.currentPage,
                    size: this.page.pageSize,
                    id: data.id
                }, )).then(response => {
                    this.list = response.data.data.records;
                    this.page.total = response.data.data.total
                    this.listLoading = false;
                });
            },
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
                       pid:node.data.id,
                    };
                    fetchTree(param).then(response => {
                        let treeData = response.data.data;
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
                        this.orgId=treeData[1].id
                        resolve(treeData);
                    }).catch(err => {
                    });
                },

            getList(page, params) {
                this.listLoading = true;
                fetchList(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, params)).then(response => {
                    this.list = response.data.data.records;
                    this.page.total = response.data.data.total
                    this.listLoading = false;
                    this.fullscreenLoading = false;
                });
            },

            handleRefresh() {
                this.getList(this.page)
            },

            DownUser() {
                this.$confirm('确定同步用户吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.fullscreenLoading = true;
                    fetchUser(this.orgId).then(() => {
                        this.$message({
                            showClose: true,
                            message: '同步成功',
                            type: 'success'
                        })
                        this.getList(this.page,this.orgId);
                    })
                })
            },
        },
    };
</script>
<style lang="scss">
    .user {
        height: 100%;

        &__tree {
            padding-top: 3px;
            padding-right: 20px;
        }

        &__main {
            .el-card__body {
                padding-top: 0;
            }
        }
    }
</style>

