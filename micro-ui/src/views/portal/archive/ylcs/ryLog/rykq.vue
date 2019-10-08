<template>
 <div class="execution">
   <basic-container>
    <avue-crud :option="option"
               :data="tableData"
               v-model="form"
               ref="crud"
               :page="page"
               @on-load="getList"
               @search-change="handleFilter"
               @refresh-change="handleFilter"
               :table-loading="tableLoading">

        <template slot="rybaMc" slot-scope="scope">
            <button type="button" class="el-button el-button--text el-button--small"
                    @click="openRyxqDialog(scope.row)" >{{scope.row.rybaMc}}</button>
        </template>

    </avue-crud>
   </basic-container>

     <el-dialog :title="formTitle"  :visible.sync="formView" :fullscreen="true" :append-to-body="true">
         <avue-detail :option="formOption" v-model="fromDetail" >
         </avue-detail>
         <div slot="footer"
              class="dialog-footer">
             <el-button type="primary" @click="formView=false">关 闭
             </el-button>
         </div>
     </el-dialog>

    </div>
</template>


<script>
    import {findRykqPage,getRyKqDetail} from "@/api/portal/archive/ylcs/csba";
    import {ylcsDict}  from  "@/const/crud/portal/archive/ylcs/ylcsDict";
    export default {
        name: 'rykq',
        props:["csbaForm"],
        data(){
            return {
                csbaBabh:'',
                compId:'',
                form:{
                },
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableData: [],
                fromDetail:{},
                formView:false,
                formTitle:'',
                formOption:{
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
                    refreshBtn: false,
                    searchBtn:false,
                    columnBtn:false,
                    menu:false,
                    card:true,
                    group: [
                        {
                            label: '考勤信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column:[
                                { label:"人员姓名", prop:"rybaMc",},
                                { label: "证件号码",prop: 'idcardNum',},
                                { label: "业务类型",prop: 'ywLx',dicData:ylcsDict.ywlx},
                                { label:"打卡时间", prop:"rylogDksj",},
                                { label:"打卡类型", prop:"rylogDklx",type:'select',dicData:ylcsDict.dklx},
                                { label:"打卡情况", prop:"sxbState",type:'select',dicData:ylcsDict.dkqk},
                                { label:"岗位信息", prop:"rylogGwxx",},
                                { label: "工作地点",prop: 'rylogGzdd',},
                            ]
                        },
                        {
                            label: '基本信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column: [
                                {label: '别名', prop: 'rybaBieming',},
                                {label: '婚姻状况', prop: 'rybaHyzkdm',
                                 dicUrl: '/portal/dict/getDict/婚姻/yldm',
                                 dicMethod: 'get',
                                 dicQuery: {},
                                },
                                {label: '文化程度', prop: 'rybaWhcd',
                                 dicUrl: '/portal/dict/getDict/文化程度/yl',
                                 dicMethod: 'get',
                                 dicQuery: {},
                                },
                                {label: '性别',prop: 'rybaXb',type:'select',dicData:ylcsDict.sex},
                                {label: '户籍地址', prop: 'rybaHjdz',},
                                {label: '居住地址', prop: 'rybaJzdz',},
                                {label: '民族', prop: 'rybaMz',},
                                {label: '签发机关', prop: 'rybaQfjg',},
                                {label: '健康状况', prop: 'rybaJkzk',},
                                {label: '国籍', prop: 'rybaGjdm',
                                 dicUrl: '/portal/dict/getDict/国籍/yl',
                                 dicMethod: 'get',
                                 dicQuery: {},
                                },
                                {label: '身份证号', prop: 'rybaZjhm'},
                                {label: '常用证件', prop: 'rybaZjdm',
                                 dicUrl: '/portal/dict/getDict/证件/yldm',
                                 dicMethod: 'get',
                                 dicQuery: {},
                                },
                                {label: '身高', prop: 'rybaSg',},
                                {label: '暂住证号', prop: 'rybaZzzhm'},
                                {label: '出生日期', prop: 'rybaCsrq',},
                                {label: '血型', prop: 'rybaXx',},
                                {label: '备注', prop: 'rybaBz',},
                                {label: '户籍所在地区划代码', prop: 'rybaHjqhdm'},
                            ]
                        },
                        {
                            label: '从业情况',
                            prop: 'cyqk',
                            icon: 'el-icon-info',
                            column: [
                                {label: '从业单位', prop: 'compName',},
                                {label: '联系电话', prop: 'rybaLxdh',},
                                {label: '从业人员类别', prop: 'rybaLb',dicData:ylcsDict.rylb},
                                {label: '岗位名称', prop: 'rybaGwmc',},
                                {label: '岗位职责', prop: 'rybaGwzrms',},
                                {label: '从业人员编号', prop: 'rybaBh',},
                                {label: '从业状态', prop: 'rybaCyzt',dicData:ylcsDict.ryzt},
                                {label: '所属负责人', prop: 'rybaSsfzr',},
                                {label: '所属负责人电话', prop: 'rybaSsfzrdh',},
                                {label: '岗位编号', prop: 'rybaGwbh',},
                                {label: '紧急联系人', prop: 'rybaJjlxr',},
                                {label: '紧急联系人电话', prop: 'rybaJjlxrdh',},
                                {label: '离职时间', prop: 'rybaLzsj',},
                            ]
                        },
                    ]
                },
                option:{
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
                    menuWidth:'100',
                    card:true,
                    menu:false,
                    column:[
                        { label:"人员姓名", prop:"rybaMc",slot:true,search:true,},
                        { label: "工作地点",prop: 'rylogGzdd',},
                        { label:"打卡时间", prop:"rylogDksj",},
                        { label:"打卡类型", prop:"rylogDklx",type:'select',dicData:ylcsDict.dklx},
                        { label:"打卡情况", prop:"sxbState",search:true,type:'select',dicData:ylcsDict.dkqk},
                        { label:"岗位信息", prop:"rylogGwxx",},
                    ]
                }
            }
        },
        created(){
            this.csbaBabh = this.csbaForm.csbaBabh
            this.compId = this.csbaForm.compId
        },
        computed: {
        },
        watch: {
            csbaForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.csbaBabh = this.csbaForm.csbaBabh
                    this.compId = this.csbaForm.compId
                    this.getList(this.page,{});
                },
                deep: true
            },
        },
        methods:{
            getList(page, params) {
                this.tableLoading = true
                findRykqPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    csbaBabh:this.csbaBabh,
                    compId:this.compId
                }, params)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },
            handleFilter(param) {
                this.page.page = 1;
                this.getList(this.page, param);
            },
            getFormDetail(id) {
                getRyKqDetail(id).then(response => {
                    this.fromDetail = response.data.data
                })
            },
            openRyxqDialog(row){
                console.log(row.rylogId)
                this.formView = true
                this.formTitle = "人员详情"
                this.getFormDetail(row.rylogId)
            }
        }
    }
</script>