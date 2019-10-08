

<template>
    <div class="user">
        <basic-container>
            <el-row :span="24">
                    <el-col :xs="24"
                            :sm="24"
                            :md="5"
                            class="user__tree">
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
                        />
                    </el-col>
                <el-col :xs="24"
                        :sm="24"
                        :md="19"
                        class="user__main">
                    <avue-crud :option="option"
                               ref="crud"
                               v-model="form"
                               :page="page"
                               @on-load="getPage"
                               :table-loading="listLoading"
                               @refresh-change="handleRefresh"
                               @search-change="searchChange"
                               @selection-change="selectionChange"
                               v-loading.fullscreen.lock="fullscreenLoading"
                               :data="tableData">
                        <template slot="menuLeft">
                            <el-button :loading="loading"
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
    import {fetchList,fetchUser,fetchUserOne,getPageData} from "@/api/portal/tyyh/tyyh";
    import {fetchTreelist} from '@/api/admin/dept'
    import {fetchTree} from '@/api/admin/dept'
    import {tableOption} from '@/const/crud/portal/tyyh/tyyh';
    import Tree from "@/components/ztree/vtree";
    import popTree from "@/components/ztree/poptree";
    import {mapGetters} from "vuex";
    import { validatenull } from "@/util/validate";

    export default {
        components: {Tree,popTree},
        name: "table_user",
        data() {
            return {
                isShow:true,//是否显示搜索框
                text:"", //勾选的文本值
                inputValue:"",//搜索输入的值
                popVisible:false,
                treeNode: [],
                checkBox: false, //是否开启复选框
                checkBoxType: false, //是否级联选中
                hiddenLine: false,//是否连线
                async: true,//是否异步加载

                treeData: [],
                option: tableOption,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                },
                search:'',
                userlist:[],
                orglist:[],
                tableData: [],
                tempTableData:[],
                loading:false,
                fullscreenLoading: false,
                listLoading: false,
                form: {},
            };
        },
        computed: {
            ...mapGetters(["permissions"])
        },
        watch:{
            inputValue(newVal){
                console.log(newVal)
            }
        },

        created() {
            //初始化
            fetchTree().then(data=>{
                let zNodesData = data.data.data;
                this.treeNode=zNodesData;
            }).catch(() => {
            });
        },
        methods: {
            treeSeach(){
                console.log(this.inputValue);
                let parmes={name:this.inputValue};
                if (!validatenull(this.inputValue)) {
                    fetchTreelist(parmes).then(data=>{
                        let zNodesData = data.data.data;
                        this.treeNode=zNodesData;
                    }).catch(() => {
                    });
                }else if (this.inputValue!=="公安局" || this.inputValue!=="分局" || this.inputValue!=="派出所" || this.inputValue!=="支队") {
                    fetchTree(parmes).then(data=>{
                        let zNodesData = data.data.data;
                        this.treeNode=zNodesData;
                    }).catch(() => {
                    });
                }else {
                    alert("请输入部门全称")
                }
            },

            asyncCheckBoxCall(checkBox) {
                let checkedNames = [], checkedIds = [];
                for(let i=0;i<checkBox.length;i++){
                    checkedIds.push(checkBox[i].id);
                    checkedNames.push(checkBox[i].name);
                }
                console.log(checkedIds);
                console.log(checkedNames);
                this.orglist=checkedIds
                this.text=checkedNames.join(",");
            },
            //击节点前触发回调函数，return false 无法触发clickNode事件
            beforeClick(node) {
                console.log("击节点前触发回调函数"+node);
                return true;
            },
            /*点击节点信息 上个点击节点信息*/
            clickNode(data, oldData) {
                this.listLoading = true;
                fetchList(Object.assign({
                    orgId: data.id
                }, )).then(response => {
                    this.tempTableData = response.data.data || []
                    this.tableData = getPageData(this.tempTableData, this.page)
                    this.page.total = this.tempTableData.length
                    this.listLoading = false;
                });
            },
            getPage(page) {
                this.tableData = getPageData(this.tempTableData, page)
            },

            /*异步回调函数 data 当前节点数据 call 回调函数*/
            asyncCall(data, call) {
                let pasmes={deptId:data.id,name:data.name};
                fetchTree(pasmes).then(data=>{
                    let zNodesData = data.data.data;
                    //this.treeNode = zNodesData;
                    call(zNodesData);
                }).catch(() => {
                });
            },

            selectionChange(list){
               this.userlist = list;
            },

            searchChange(form) {
                /*this.tableData = this.tempTableData.filter(tableData=>!this.search || tableData.name.toLowerCase().include(this.search.toLowerCase()))*/
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
                    debugger
                    let defineForm ={}
                    var orgnodes = new Array();
                    var nodes = new Array();
                    for(let i = 0; i < this.userlist.length; i++){
                        nodes.push(JSON.parse(JSON.stringify(this.userlist[i])));
                    }
                    for(let i = 0; i < this.orglist.length; i++){
                        orgnodes.push(JSON.parse(JSON.stringify(this.orglist[i])));
                    }
                    defineForm['userList']=nodes
                    defineForm['orgList']=orgnodes
                    this.loading = true;
                    if (nodes.length<=0){
                    fetchUser(defineForm).then(() => {
                        this.$message({
                            showClose: true,
                            message: '同步成功',
                            type: 'success',
                        })
                        this.loading = false;
                    }).catch(function () {
                        this.loading = false;
                        this.msg("同步失败","error")
                    })
                }else {
                        fetchUserOne(defineForm).then(() => {
                            this.$message({
                                showClose: true,
                                message: '同步成功',
                                type: 'success',
                            })
                            this.loading = false;
                        }).catch(function () {
                            this.loading = false;
                            this.msg("同步失败","error")
                        })
                    }
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
