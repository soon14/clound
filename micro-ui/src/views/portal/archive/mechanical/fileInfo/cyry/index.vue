
<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="tableOption"
                       :data="tableData"
                       v-model="form"
                       ref="crud"
                       @on-load="getList"
                       @refresh-change="refreshChange"
                       @search-change="searchChange"
                       :page="page"
                       :table-loading="tableLoading">

                <template slot="xm" slot-scope="scope">
                    <button type="button" class="el-button el-button--text el-button--small"
                            @click="openRyxqDialog(scope.row)"
                    >{{scope.row.xm}}</button>
                </template>
            </avue-crud>
            <el-dialog title="人员详情"  :visible.sync="cyryFormView" :fullscreen="true" :append-to-body="true">
                <avue-detail :option="cyryFormOption" v-model="cyryFromDetail" @on-load="getList">
                    <template slot="imgUrl" slot-scope="scope">
                        <img style="height: 100px;width: 100px"  id="imgPerson" :src="initRyImg(cyryFromDetail.xxid,'imgPerson')"/>
                    </template>
                </avue-detail>
                <div slot="footer"
                     class="dialog-footer">
                    <el-button type="primary" @click="cyryFormView=false">关 闭
                    </el-button>
                </div>
            </el-dialog>
        </basic-container>
    </div>
</template>


<script>
    import {findCyryPage,getRyDetail,getRyImg} from "@/api/portal/archive/mechanical/jxyxt";
    import {formOption} from "@/const/crud/portal/archive/mechanical/jxyxt";
    export default {
        props: ["paramForm"],
        name:"cyry",
        data(){
            return {
                form:{
                    dwmc:'',
                    dwbh:'',
                },
                tableLoading: false,
                cyryFormView:false,
                cyryFormOption:formOption,
                cyryFromDetail:{},
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                tableData: [],
                tableOption: {
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    viewBtn: false,
                    editBtn: false,
                    delBtn: false,
                    menu:false,
                    card:true,
                    column: [
                        {
                            label: '头像',
                            prop: 'imgUi',
                            hide:true,
                            slot:true,
                            formslot:true,
                        },
                        {
                            label: '姓名',
                            prop: 'xm',
                            search:'true',
                            slot:true,
                        },
                        {
                            label: '证件号码',
                            search:"true",
                            prop: 'zjhm',
                            overHidden: true,
                        },
                        {
                            label: '性别',
                            prop: 'xbdm',
                            type: 'select',
                            span: 6,
                            dicUrl: '/portal/dict/getDict/0122/ly',
                            dicMethod:'get',
                            dicQuery:{},
                        },
                        {
                            label: '出生日期',
                            prop: 'csrq',
                        },
                        {
                            label: '职务类别',
                            prop: 'gzmc',
                            search:'true',
                            type: 'select',
                            overHidden: true,
                            dicData: [{
                                label: '服务人员',
                                value: '0'
                            }, {
                                label: '管理人员',
                                value: '1'
                            }, {
                                label: '一般服务人员',
                                value: '2'
                            }, {
                                label: '其他管理人员',
                                value: '3'
                            }
                            ]
                        },
                        {
                            label: '所属单位类别',
                            prop: 'ryssdwlb',
                            type: 'select',
                            overHidden: true,
                            dicData: [{
                                label: '旅馆业',
                                value: '0'
                            }, {
                                label: '歌舞娱乐场所',
                                value: '1'
                            }, {
                                label: '游戏游艺场所',
                                value: '2'
                            }, {
                                label: '机修业',
                                value: '3'
                            }, {
                                label: '按摩服务场所',
                                value: '4'
                            }
                            ]
                        }
                        ,
                        {
                            label: '上岗日期',
                            prop: 'rzrq',
                            type: "daterange",
                            search:"true",
                            startPlaceholder: '日期开始',
                            endPlaceholder: '日期结束',
                            overHidden: true,
                        },
                        {
                            label: '离岗日期',
                            prop: 'lzrq',
                            type: "daterange",
                        },

                        {
                            label: '人员状态',
                            prop: 'ryzt',
                            type:"select",
                            search:"true",
                            dicData: [{
                                label: '在职',
                                value: '0'
                            }, {
                                label: '离职',
                                value: '1'
                            }
                            ]
                        } ,
                        {
                            label:'七类人员类型',
                            prop:'persontype',
                        },
                        {
                            label: '现住址详址',
                            prop: 'dzmcqhnxxdz',
                            hide:true,
                        },
                        {
                            label: '数据来源',
                            prop: 'source',
                            type: 'select',
                            hide:true,
                            overHidden: true,
                            dicData: [{
                                label: '治综',
                                value: '0'
                            }, {
                                label: '警钟',
                                value: '1'
                            }, {
                                label: '旅业',
                                value: '2'
                            }
                            ]
                        }/*,
                        {
                            label: '所属警务区',
                            prop: 'ssjwq',
                            type: 'select',
                            search:'true',
                            hide:'true',
                            dicData: [{
                                label: '海傍社区警务区',
                                value: '0'
                            }, {
                                label: '甘化社区警务区',
                                value: '1'
                            }, {
                                label: '常安社区警务区',
                                value: '2'
                            }, {
                                label: '常安社区警务区',
                                value: '3'
                            }, {
                                label: '良化北社区警务区',
                                value: '4'
                            }
                            ]
                        }*/
                    ],

                },
            }
        },
        created(){
            this.form.dwbh = this.paramForm.dwbh;
        },
        computed: {
        },
        methods:{
            initRyImg(id,imgId) {
                getRyImg(id,imgId)
            },
            refreshChange() {
                this.getList(this.page)
            },
            searchChange(form) {
                this.getList(this.page, form)
            },
            getRyxqFormDetail(xxid) {
                getRyDetail(xxid).then(response => {
                    this.cyryFromDetail = response.data.data
                })
            },
            openRyxqDialog(row){
                this.cyryFormView = true
                this.getRyxqFormDetail(row.xxid)
            },
            getList(page, params) {
                this.tableLoading = true
                findCyryPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    dwbh: this.form.dwbh
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
        }
    }
</script>