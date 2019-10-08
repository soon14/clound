
<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="option"
                       :data="tableData"
                       v-model="form"
                       ref="crud"
                       @on-load="getList"
                       @refresh-change="refreshChange"
                       @search-change="searchChange"
                       :page="page"
                       :table-loading="tableLoading">

                <template slot="jdchphm" slot-scope="scope">
                    <button type="button" class="el-button el-button--text el-button--small"
                            @click="openClxqDialog(scope.row)"
                    >{{scope.row.jdchphm}}</button>
                </template>
            </avue-crud>
            <el-dialog title="车辆详情"  :visible.sync="cjclFormView" :fullscreen="true" :append-to-body="true">
                <avue-detail :option="cjclFormOption" v-model="cjclFromDetail" >
                    <template slot="imgUrl" slot-scope="scope">
                        <img style="height: 250px;width: 250px"  id="imgCjcl" :src="initClImg(cjclFromDetail.xxid,'imgCjcl')"/>
                    </template>
                </avue-detail>
                <div slot="footer"
                     class="dialog-footer">
                    <el-button type="primary" @click="cjclFormView=false">关 闭
                    </el-button>
                </div>
            </el-dialog>
        </basic-container>
    </div>
</template>


<script>
    import {findCjclPage,getClxqDetail,getClImg} from "@/api/portal/archive/mechanical/jxyxt";
    import {clxqOption} from "@/const/crud/portal/archive/mechanical/jxyxt";
    export default {
        props: ["paramForm"],
        name:"jxyxt",
        data(){
            return {
                form:{
                    dwmc:'',
                    dwbh:''
                },
                tableLoading: false,
                cjclFormView:false,
                cjclFormOption:clxqOption,
                cjclFromDetail:{},
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    ascs: "",
                    descs: ""
                },
                tableData: [],
                option: {
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    page:false,
                    align:'center',
                    indexLabel: '序号',
                    border: true,
                    index: true,
                    editBtn:false,
                    delBtn:false,
                    addBtn:false,
                    viewBtn:false,
                    menu:false,
                    menuWidth:'100',
                    card:true,
                    column:[
                        {
                            label: "业务流水号",
                            prop: "ywlsh",
                            hide:true,
                            span:6
                        },
                        {
                            label: "单位编号",
                            prop: "zagldwbm",
                            hide:true,
                            span:6
                        },
                        {
                            label: "单位名称",
                            prop: "dwmc",
                            hide:true,
                            span:6,

                        },
                        {
                            label:"机动车所有人",
                            prop:"jdcsyrmc",
                            span:6
                        },
                        {
                            label:"车牌号码",
                            prop:"jdchphm",
                            search:true,
                            slot:true,
                            span:6
                        },
                        {
                            label:"车辆类型",
                            prop:"jdccllxdm",
                            search:true,
                            span:6
                        },
                        {
                            label:"品牌名称",
                            prop:"zwppmc",
                            hide:true,
                            span:6
                        },
                        {
                            label:"车辆型号",
                            prop:"clxh",
                            span:6
                        },
                        {
                            label:"车身颜色",
                            prop:"jdccsysdm",
                            viewDisplay:true,
                            span:6
                        },
                        {
                            label:"送修人",
                            prop:"sxrxm",
                            search:true,
                            span:6
                        },
                        {
                            label:"送修人证件号码",
                            prop:"sxrzjhm",
                            search:true,
                            span:6
                        },
                        {
                            label:"送修人证件类型",
                            prop:"sxrcyzjdm",
                            span:6
                        },
                        {
                            label:"送修时间",
                            prop:"sxsj",
                            type: "daterange",
                            search:"true",
                            startPlaceholder: '日期开始',
                            endPlaceholder: '日期结束',
                            span:6,
                        },
                        {
                            label:"收车人",
                            prop:"scrxm",
                            span:6
                        },
                        {
                            label:"交接状态",
                            prop:"jjzt",
                            span:6
                        },
                        {
                            label:"简要情况",
                            prop:"clgzjyqk",
                            hide:true,
                            span:6
                        }
                    ]
                },
            }
        },
        created(){
            this.form.dwbh = this.paramForm.dwbh;
        },
        computed: {
        },
        methods:{
            initClImg(id,imgId) {
                getClImg(id,imgId)
            },
            refreshChange() {
                this.getList(this.page)
            },
            searchChange(form) {
                this.getList(this.page, form)
            },
            getClxqFormDetail(ywlsh) {
                getClxqDetail(ywlsh).then(response => {
                    this.cjclFromDetail = response.data.data
                })
            },
            openClxqDialog(row){
                this.cjclFormView = true
                this.getClxqFormDetail(row.ywlsh)
            },
            getList(page, params) {
                this.tableLoading = true
                findCjclPage(Object.assign({
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