<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       :page="page"
                       :data="tableData"
                       :option="tableOption"
                       @row-update="handleUpdate"
                       @row-save="handleSav"
                       v-model="form"
                      >
                <template slot="icon" slot-scope="scope">
                    <i :class="scope.row.icon" style="font-size:24px"></i>
                </template>
                <template slot="parentIdForm" slot-scope="scope">
                    <avue-input v-model="form.parentId"
                                type="tree"
                                placeholder="父级菜单"
                                :dic="dicTreeData"
                                :props="dicTreeProps">
                    </avue-input>
                </template>
                <template slot="menu" slot-scope="scope">
                    <el-button :size="scope.size" :type="scope.type" @click="handleAdd(scope.row)">添加子级</el-button>
                    <el-button :size="scope.size" :type="scope.type" @click="handleEdit(scope.row,scope.index)">编辑</el-button>
                    <el-button :size="scope.size" :type="scope.type" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </avue-crud>
        </basic-container>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                form:{
                    id:""
                },
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                formEdit: true,
                formAdd: true,
                listLoading: true,
                tableData: [
                    {
                        id:0,
                        parentId:0,
                        hybm:'lgy',
                        hymc:'旅馆业',
                        gxsj:'2019-03-18',
                        children:[
                            {
                                id:10,
                                parentId:0,
                                hybm:"09",
                                hymc:"按摩服务场所",
                                hypy:"amfwcs",
                                gxsj:"2019-3-19",
                            }, {
                                id:11,
                                parentId:0,
                                hybm:"10",
                                hymc:"游戏游艺场所",
                                hypy:"yxyycs",
                                gxsj:"2019-3-19",
                            }
                        ]
                    },
                    {
                        id:1,
                        parentId:1,
                        hybm:'lysczw',
                        hymc:'旅业式出租屋',
                        gxsj:'2019-03-19',
                        children:[
                            {
                                id:101,
                                parentId:1,
                                hybm:"0101",
                                hymc:"宾馆",
                                hypy:"bg",
                                gxsj:"2019-3-19",
                            }, {
                                id:102,
                                parentId:1,
                                hybm:"0102",
                                hymc:"酒店",
                                hypy:"jd",
                                gxsj:"2019-3-19",
                            },{
                                id:103,
                                parentId:1,
                                hybm:"0103",
                                hymc:"饭店",
                                hypy:"fd",
                                gxsj:"2019-3-19",
                            },{
                                id:104,
                                parentId:1,
                                hybm:"0104",
                                hymc:"招待所",
                                hypy:"zds",
                                gxsj:"2019-3-19",
                            },{
                                id:105,
                                parentId:1,
                                hybm:"0105",
                                hymc:"度假村",
                                hypy:"djc",
                                gxsj:"2019-3-19",
                            },
                            {
                                id:106,
                                parentId:1,
                                hybm:"0106",
                                hymc:"其他旅业",
                                hypy:"qtly",
                                gxsj:"2019-3-19",
                            }
                        ]
                    },
                    {
                        id:2,
                        parentId:2,
                        hybm:'bfqchs',
                        hymc:'报废机动车拆解业',
                        gxsj:'2019-03-19',
                        children:[
                            {
                                id:30,
                                parentId:2,
                                hybm:"1601",
                                hymc:"报废汽车回收(拆解)",
                                hypy:"bfqchs",
                                gxsj:"2019-3-19",
                            },
                        ]
                    },
                ],
                tableOption: {
                    expandLevel: 1,
                    border: true,
                    index: true,
                    tree:true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    editBtn: false,
                    delBtn: false,
                    addBtn: true,
                    viewBtn: true,
                    column: [
                        {
                        label: '行业代码',
                        prop: 'hybm',
                        search: 'true',
                        overHidden: true,
                            rules: [{
                                required: true,
                                message: '代码不能为空',
                                trigger: 'blur'
                            }
                            ]
                        },
                        {
                            label: '父级菜单',
                            prop: 'parentId',
                            hide: true,
                            formslot: true,
                            slot: true
                        },
                        {
                        label: '行业名称',
                        prop: 'hymc',
                        search: 'true',
                        overHidden: true,
                            rules: [{
                                required: true,
                                message: '名称不能为空',
                                trigger: 'blur'
                            }
                            ]
                         },
                        {
                            label: '行业拼音',
                            prop: 'hypy',
                            overHidden: false,
                        },{
                            label: '备注',
                            prop: 'bz',
                            overHidden: true,
                        },
                        {
                            label: '更新时间',
                            prop: 'gxsj',
                            type: 'datetime',
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                    ],
                },
                dicTreeData:[
                    {
                        id:0,
                        parentId:0,
                        hybm:'lgy',
                        hymc:'旅馆业',
                        gxsj:'2019-03-18',
                        children:[
                            {
                                id:10,
                                parentId:0,
                                hybm:"09",
                                hymc:"按摩服务场所",
                                hypy:"amfwcs",
                                gxsj:"2019-3-19",
                            }, {
                                id:11,
                                parentId:0,
                                hybm:"10",
                                hymc:"游戏游艺场所",
                                hypy:"yxyycs",
                                gxsj:"2019-3-19",
                            }
                        ]
                    },
                    {
                        id:1,
                        parentId:1,
                        hybm:'lysczw',
                        hymc:'旅业式出租屋',
                        gxsj:'2019-03-19',
                        children:[
                            {
                                id:101,
                                parentId:1,
                                hybm:"0101",
                                hymc:"宾馆",
                                hypy:"bg",
                                gxsj:"2019-3-19",
                            }, {
                                id:102,
                                parentId:1,
                                hybm:"0102",
                                hymc:"酒店",
                                hypy:"jd",
                                gxsj:"2019-3-19",
                            },{
                                id:103,
                                parentId:1,
                                hybm:"0103",
                                hymc:"饭店",
                                hypy:"fd",
                                gxsj:"2019-3-19",
                            },{
                                id:104,
                                parentId:1,
                                hybm:"0104",
                                hymc:"招待所",
                                hypy:"zds",
                                gxsj:"2019-3-19",
                            },{
                                id:105,
                                parentId:1,
                                hybm:"0105",
                                hymc:"度假村",
                                hypy:"djc",
                                gxsj:"2019-3-19",
                            },
                            {
                                id:106,
                                parentId:1,
                                hybm:"0106",
                                hymc:"其他旅业",
                                hypy:"qtly",
                                gxsj:"2019-3-19",
                            }
                        ]
                    },
                    {
                        id:2,
                        parentId:2,
                        hybm:'bfqchs',
                        hymc:'报废机动车拆解业',
                        gxsj:'2019-03-19',
                        children:[
                            {
                                id:30,
                                parentId:2,
                                hybm:"1601",
                                hymc:"报废汽车回收(拆解)",
                                hypy:"bfqchs",
                                gxsj:"2019-3-19",
                            },
                        ]
                    },
                ],
                dicTreeProps: {
                    label: "hymc",
                    value: 'id'
                },
            }
        },
        methods:{
            handleAdd: function (row) {
                this.form.parentId=row.id;
                this.$refs.crud.rowAdd()
            },
            handleEdit(row, index) {
                this.$refs.crud.rowEdit(row, index)
            },
            handleSav(row, done, loading){
                done();
                this.$notify({
                    title: '成功',
                    message: '新增成功',
                    type: 'success',
                    duration: 2000
                })
            },
            handleUpdate(row, index, done, loading) {
                this.form.id=row.id
                putObj(this.form).then(() => {
                    this.getList()
                    done();
                    this.$notify({
                        title: '成功',
                        message: '更新成功',
                        type: 'success',
                        duration: 2000
                    })
                }).catch(() => {
                    loading();
                });
            },
            handleDelete() {
                var _this = this
                this.$confirm('是否确认删除该记录吗"?', '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    _this.$message({
                        showClose: true,
                        message: '删除成功',
                        type: 'success'
                    })
                }).catch(function (err) {
                })
            },
        }
    }
</script>

<style scoped>

</style>