<template>
    <div class="execution">
        <basic-container>
            <avue-crud
                    ref="crud"
                    v-model="form"
                    :page="page"
                    :data="tableData"
                    :option="tableOption"
                    :table-loading="tableLoading"
                    @on-load="getList"
                    @refresh-change="handleFilter"
                    @row-update="handleUpdate"
                    @row-save="handleSave"
                    @row-del="rowDel"
                    @search-change="handleFilter"
                    :before-open="handleOpenBefore"
                    @size-change="sizeChange"
            >
                <template slot="status" slot-scope="scope">
                    <div v-if="scope.row.status == '0'">
                        <el-tag type="success">{{findLabelByValue(statusData,scope.row.status)}}</el-tag>
                    </div>
                    <div v-else>
                        <el-tag type="danger">{{findLabelByValue(statusData,scope.row.status)}}</el-tag>
                    </div>
                </template>
                <template slot="searchContentForm"
                          slot-scope="scope">
                    <vue-json-editor v-model="form.searchContent"
                                     :show-btns="false"
                                     mode="code"
                                     @json-change="onJsonChange"
                                     @has-error="hasError"></vue-json-editor>
                </template>
                <template slot="menuLeft">
                    <el-button v-if="search_scheme_add"
                               class="filter-item"
                               @click="handleAdd"
                               size="small"
                               type="primary"
                               icon="el-icon-plus">新增
                    </el-button>
                </template>

                <template slot="menu"
                          slot-scope="scope">
                    <el-button v-if="search_scheme_edit"
                               size="small"
                               type="text"
                               :icon="scope.row.status == '1'?'el-icon-caret-right':'el-icon-error'"
                               @click="changeStatus(scope.row)">
                        {{ scope.row.status == '1'?'启用':'停用' }}
                    </el-button>
                    <el-button v-if="search_scheme_edit"
                               size="small"
                               type="text"
                               icon="el-icon-edit"
                               @click="handleEdit(scope.row,scope.index)">编辑
                    </el-button>
                    <el-button v-if="search_scheme_del"
                               size="small"
                               type="text"
                               icon="el-icon-delete"
                               @click="rowDel(scope.row,scope.index)">删除
                    </el-button>
                </template>

                <template slot="menuForm" slot-scope="scope">
                    <el-button type="danger"
                               @click="configHelp"
                               size="medium"
                                >帮助
                    </el-button>
                </template>

            </avue-crud>
        </basic-container>
        <el-dialog title="查询内容配置规则"  :visible.sync="helpView" :append-to-body="true" width="70%">
            <el-input type="textarea"  v-model="helpData" class="textareaStyle">
            </el-input>
        </el-dialog>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import {
        addObj,
        changeStatus,
        delObj,
        fetchList,
        getObj,
        putObj
    } from '@/api/portal/search/scheme/searchScheme'
    import {tableOption,defaultOption} from '@/const/crud/portal/search/scheme/searchScheme'
    import {validatenull} from "@/util/validate";
    import {remote} from "@/api/admin/dict";
    import vueJsonEditor from 'vue-json-editor'


    export default {
        name: 'searchScheme',
        components: {
            vueJsonEditor
        },
        data() {
            return {
                form: {},
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                loadRest: false,
                tableLoading: false,
                tableOption: tableOption,
                defaultOption:defaultOption,
                helpView:false,
                tableData: [],
                statusData: [],//状态字典
                search_scheme_add: false,
                search_scheme_edit: false,
                search_scheme_del: false,
                errorFlag:false,
                helpData:"{\n" +
                    "    dialogFullscreen: true,//是否为全屏Dialog\n" +
                    "    dialogHeight: \"100%\",\n" +
                    "    border: true,\n" +
                    "    index: true,\n" +
                    "    pseudo: false,//是否伪分页 默认显示200条\n" +
                    "    indexLabel: \"序号\",\n" +
                    "    stripe: true,\n" +
                    "    menuAlign: \"center\",\n" +
                    "    align: \"center\",\n" +
                    "    addBtn: false, //添加按钮 默认即可\n" +
                    "    editBtn:false, //编辑按钮 默认即可\n" +
                    "    viewBtn: false, //查看按钮 默认即可\n" +
                    "    delBtn: false, //删除按钮 默认即可\n" +
                    "    refreshBtn:false, //刷新按钮 默认即可\n" +
                    "    columnBtn:false, //列显隐按钮 默认即可\n" +
                    "    searchBtn:false, //搜索显隐按钮（当column中有搜索的属性，或则searchsolt为true时自定义搜索启动起作用）默认即可\n" +
                    "    menuWidth:100, //菜单操作宽度\n" +
                    "    menu:true,// 是否显示操作栏 ，true、false（若无详情查看可配置为false）\n" +
                    "    dataScope: \"\",//权限代码字段\n" +
                    "    lyxt:\"\",//来源系统 （0警综 1娱乐场所 2机修 3旅业 4刻印章业），若该配置项为空 详情则默认展示表格内字段\n" +
                    "    pk:\"\", //主键标识字段\n" +
                    "    column: [\n" +
                    "        {\n" +
                    "            label: \"字典的名称属性值\",//字典的名称属性值,必填项\n" +
                    "            prop: \"name\", //列字段，必填项\n" +
                    "            searchSymbol:\"\",//模糊查询like，左模糊leftLike，右模糊rightLike，大于gt，小于lt等，不配置则默认等于\n" +
                    "            orderBy: \"asc\",//升序asc，降序desc\n" +
                    "            search: true,//搜索栏目自定义内容 是否搜索\n" +
                    "            searchFilterable:true,//是否可以输入搜索 true/false\t默认false\n" +
                    "            type:\"\",//（tree、select、radio、textarea、date、dateRange等）若该配置项为空则默认为input框\n" +
                    "            overHidden: true ,//超出隐藏(true/false)\n" +
                    "            dicUrl: \"/portal/area/areaData\",//字典的网络请求接口（例如配置/xxx/xx/{\\{key}},这样的格式，在column中dicData自动匹配prop字段名）\n" +
                    "            dicMethod: \"get\",//传入字典的请求方式\n" +
                    "            dicQuery: {},//传入字典的请求参数\n" +
                    "            viewDisplay:true,//表单查看时是否可见 true/false 默认true\n" +
                    "            format:\"yyyy-MM-dd\",//显示值时间格式(yyyy/yyyy-MM/yyyy-MM-dd/yyyy-MM-dd HH:mm:ss)（当type为时间类型时)\n" +
                    "            valueFormat:\"yyyy-MM-dd\"//真实值的时间格式(yyyy/yyyy-MM/yyyy-MM-dd/yyyy-MM-dd HH:mm:ss)（当type为时间类型时）\n" +
                    "}\n" +
                    "    ]\n" +
                    "}"
            }
        },
        created() {
            remote('point_status').then(response => {
                this.statusData = response.data.data || {}
            }).catch((err) => {
            });
            this.initPermission()
        },
        mounted: function () {
        },
        computed: {
            ...mapGetters(['permissions', "isAdmin"])
        },
        methods: {
            initPermission() {
                this.search_scheme_add = this.permissions['search_scheme_add']
                this.search_scheme_edit = this.permissions['search_scheme_edit']
                this.search_scheme_del = this.permissions['search_scheme_del']
            },
            /**
             * @title 分页获取等级评定方案
             * @param page 分页信息，params 查询参数
             *
             **/
            getList(page, params) {
                this.tableLoading = true
                fetchList(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            handleFilter(param) {
                this.page.currentPage = 1
                this.getList(this.page, param)
            },
            sizeChange(size) {
                this.page.pageSize = size;
            },
            handleOpenBefore(show, type) {
                window.boxType = type;
                show();
            },
            handleAdd: function () {
                this.form = {}
                this.form.searchContent = this.defaultOption
                this.$refs.crud.rowAdd()
            },

            handleEdit(row, index) {
              /*   if (row.status != "1") {
                         this.$message({
                             title: '错误',
                             message: '启用状态的方案不能被修改、请先停用！',
                             type: 'error',
                             duration: 2000
                         })
                      return
                 }*/
                let json = row.searchContent;
                try {
                    json = JSON.parse(row.searchContent)
                }catch (e) {
                    console.log("error:",e)
                }
                row.searchContent = json
                this.$refs.crud.rowEdit(row, index)
            },


            /**　启用、停用等级评定方案*/
            changeStatus(row) {
                let id = row.id, status = row.status, _this = this;
                if (validatenull(id) || (status != "0" && status != "1")) {
                    _this.$message({
                        showClose: true,
                        message: '信息缺失、操作失败',
                        type: 'error'
                    })
                    return
                }
                this.$confirm('确定要' + (status == "0" ? "停用" : "启用") + '该方案？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return changeStatus(id, status)
                }).then(data => {
                    if (data.data.code === 0) {
                        _this.$message({
                            title: '成功',
                            message: '操作成功',
                            type: 'success',
                            duration: 2000
                        })
                    } else {
                        _this.$message({
                            title: '失败',
                            message: '操作失败、请刷新页面、稍后重试',
                            type: 'error',
                            duration: 2000
                        })
                    }
                    this.handleFilter()
                }).catch(function (err) {

                })
            },


            /**
             * @title 数据新增 保存
             * @param row 为当前的数据
             * @param done 为表单关闭函数
             *
             **/
            handleSave(row, done, loading) {
                if(true==this.errorFlag){
                    this.$message.error("JSON格式错误，请先更正")
                    loading();
                }else {
                    let str = this.form.searchContent
                    if (!(typeof str == 'string')) {
                        this.form.searchContent = JSON.stringify(this.form.searchContent)
                    }
                    addObj(this.form).then(() => {
                        this.getList(this.page)
                        done();
                        this.$notify({
                            title: '成功',
                            message: '创建成功',
                            type: 'success',
                            duration: 2000
                        })
                    }).catch(() => {
                        loading();
                        this.form.searchContent = str
                    });
                }
            },
            /**
             * @title 数据更新
             * @param row 为当前的数据
             * @param index 为当前更新数据的行数
             * @param done 为表单关闭函数
             *
             **/
            handleUpdate: function (row, index, done, loading) {
                if(true==this.errorFlag){
                    this.$message.error("JSON格式错误，请先更正")
                    loading();
                }else{
                    let str = this.form.searchContent
                    if (!(typeof str == 'string')) {
                        this.form.searchContent = JSON.stringify(this.form.searchContent)
                      }
                     putObj(this.form).then(() => {
                        this.getList(this.page)
                        done();
                        this.$notify({
                            title: '成功',
                            message: '更新成功',
                            type: 'success',
                            duration: 2000
                        })
                    }).catch(() => {
                        loading();
                        this.form.searchContent = str
                    });
                }

            },
            /**JSON格式错误*/
            hasError(){
                this.errorFlag=true
                console.log("this.errorFlag:",this.errorFlag)
            },
            /**JSON修改*/
            onJsonChange () {
                this.errorFlag=false
                console.log("this.errorFlag:",this.errorFlag)
            },
            /**删除方案*/
            rowDel(row) {
                let _this = this;
                   /*if (row.status != "1") {
                       _this.$message({
                           title: '失败',
                           message: '启用状态的方案不能被删除、请先停用！',
                           type: 'error',
                           duration: 2000
                       })
                       return
                   }*/
                this.$confirm('是否确认删除(查询配置方案:' + row.name + ')', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    return delObj(row.id)
                }).then(() => {
                    _this.getList(this.page)
                    _this.$notify({
                        title: '成功',
                        message: '删除成功',
                        type: 'success',
                        duration: 2000
                    })

                })
            },

            configHelp(){
              this.helpView =true
            }

        },

        watch: {
            'form.searchType'() {
                    if (this.form.searchType != '1') {
                        this.tableOption.column[2].viewDisplay = false;
                        this.tableOption.column[2].addDisplay = false;
                        this.tableOption.column[2].editDisplay = false;
                        this.tableOption.column[3].viewDisplay = false;
                        this.tableOption.column[3].addDisplay = false;
                        this.tableOption.column[3].editDisplay = false;
                        this.tableOption.column[4].viewDisplay = false;
                        this.tableOption.column[4].addDisplay = false;
                        this.tableOption.column[4].editDisplay = false;
                        this.tableOption.column[5].viewDisplay = false;
                        this.tableOption.column[5].addDisplay = false;
                        this.tableOption.column[5].editDisplay = false;
                        this.tableOption.column[6].label = "业务表名";
                        this.tableOption.column[6].rules[0].message = "业务表名不能为空";
                    } else {
                        this.tableOption.column[2].viewDisplay = true;
                        this.tableOption.column[2].addDisplay = true;
                        this.tableOption.column[2].editDisplay = true;
                        this.tableOption.column[3].viewDisplay = true;
                        this.tableOption.column[3].addDisplay = true;
                        this.tableOption.column[3].editDisplay = true;
                        this.tableOption.column[4].viewDisplay = true;
                        this.tableOption.column[4].addDisplay = true;
                        this.tableOption.column[4].editDisplay = true;
                        this.tableOption.column[5].viewDisplay = true;
                        this.tableOption.column[5].addDisplay = true;
                        this.tableOption.column[5].editDisplay = true;
                        this.tableOption.column[6].label = "接口地址";
                        this.tableOption.column[6].rules[0].message = "接口地址不能为空";
                    }
            },
        }


    }
</script>
<style lang="scss">
    .jsoneditor-outer{
        height: 335px !important;
    }
    .textareaStyle{
        .el-textarea__inner{
            min-height: 300px !important;
        }
    }
</style>