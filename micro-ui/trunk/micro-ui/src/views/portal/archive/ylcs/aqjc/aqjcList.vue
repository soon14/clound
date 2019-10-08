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

        <template slot="menu" slot-scope="scope" >
            <el-button size="mini"
                       type="text"
                       icon="el-icon-view"
                       @click="handleDetail(scope.row)">查看
            </el-button>
        </template>
    </avue-crud>

   </basic-container>
     <el-dialog :title="formTitle"  :visible.sync="formView" :fullscreen="true" :append-to-body="true">
         <avue-detail :option="formOption" v-model="fromDetail" >
             <template slot="jcxq" slot-scope="scope">
                 <el-table :data="checkData"
                           border
                           style="width: 100%"
                 >
                     <el-table-column
                             label="检查内容"
                             prop="proName"
                             align="center"
                             width="307">
                     </el-table-column>

                     <el-table-column
                             label="检查情况"
                             width="220"
                             align="center">
                         <template slot-scope="scope" >
                             <div v-text="getJcqk(scope.row.state)"></div>
                         </template>
                     </el-table-column>

                     <el-table-column
                             label="备注"
                             prop="remark"
                             width="700"
                             align="center">
                     </el-table-column>
                 </el-table>
             </template>

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
    import {findAqjcPage,getAqjcDetail,getAqjcContent} from "@/api/portal/archive/ylcs/csba";
    import {ylcsDict}  from  "@/const/crud/portal/archive/ylcs/ylcsDict";

    export default {
        name: 'aqjc',
        props:["csbaForm"],
        data(){
            return {
                sjCompId:'',
                form:{
                },
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableData: [],
                checkData:[],
                fromDetail:{},
                formView:false,
                formTitle:'',
                formOption:{
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    page:false,
                    align:'center',
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
                            label: '基本信息',
                            prop: 'jbxx',
                            icon: 'el-icon-info',
                            column:[
                                { label:"检查单位", prop:"compName"},
                                { label:"检查时间", prop:"checkDate",},
                                { label:"登记人", prop:"compUser",},
                                { label:"登记时间", prop:"recordDate",},
                                { label:"检查类型", prop:"checkType",dicData: [{label: '日常检查', value:0}]},
                                { label:"检查人员", prop:"checkMan1",},
                                { label:"其他检查人", prop:"checkMan2",},
                                { label:"参检人数", prop:"checkPersNum",},
                                { label:"受检单位", prop:"sjCompName",},
                                { label:"是否歌舞", prop:"gw",dicData: ylcsDict.yes_no,},
                                { label:"是否游艺", prop:"yy",dicData: ylcsDict.yes_no,},
                                { label:"是否按摩", prop:"am",dicData: ylcsDict.yes_no,},
                                { label:"受检单位类型", prop:"sjCompNature",
                                  dicUrl: '/portal/dict/getDict/单位类型/yl',
                                  dicMethod: 'get',
                                  dicQuery: {},
                                },
                                { label:"受检单位当值负责人", prop:"sjCompFzr"},
                            ]
                        },
                        {
                            label: '检查详情',
                            prop: 'jcxq',
                            slot:true,
                            formslot:true,
                            icon: 'el-icon-info',
                        },
                        {
                            label: '检查结果',
                            prop: 'jcjg',
                            icon: 'el-icon-info',
                            column: [
                                {label: '当前分数', prop: 'dqfs',},
                                {label: '扣分分数', prop: 'kffs',},
                                {label: '处理意见', prop: 'zgyj',},
                                {label: '处理结果', prop: 'cljg',},
                                {label: '是否需整改', prop: 'zhenggai',dicData:ylcsDict.yes_no},
                                {label: '整改期限', prop: 'zhenggaiDate',},
                                {label: '其他违规情况和安全隐患', prop: 'aqyh',span:24},
                            ]
                        }
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
                    menuWidth:100,
                    column:[
                        { label:"检查时间", prop:"checkDate",},
                        {
                            label: '检查日期',
                            prop: 'searchDate',
                            type: 'daterange',
                            search:true,
                            hide:true,
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyyMMdd'
                        },
                        { label:"检查单位", prop:"compName",overHidden:true},
                        { label:"检查人", prop:"checkMan1",},
                        { label:"随同检查人", prop:"checkMan2",},
                        { label:"处理结果", prop:"cljg",},
                        { label:"处理意见",prop: 'zgyj',},
                        {label:"是否需整改", prop:"zhenggai",type:'select',dicData:ylcsDict.yes_no,search:true},
                    ]
                }
            }
        },
        created(){
            this.sjCompId = this.csbaForm.compId
        },
        computed: {
        },
        watch: {
            csbaForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.sjCompId = this.csbaForm.compId
                    this.getList(this.page,{});
                },
                deep: true
            },
        },
        methods:{
            getList(page, params) {
                try{
                    let param=this.$refs["crud"].searchForm||{}
                    params=param
                    if(params){
                        let date=params.searchDate;
                        if(date&&Array.isArray(date) && date.length === 2){
                            params.startTime=date[0]
                            params.endTime=date[1]
                        }else{
                            params.startTime=date[0]
                            params.endTime=date[0]
                        }
                    }
                }catch (e) {}
                this.tableLoading = true
                findAqjcPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    sjCompId:this.sjCompId
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
            handleDetail(row){
                this.formView = true
                this.formTitle = "检查详情"
                this.getFormDetail(row.aqjcId)
                this.getFormCheckDetail(row.aqjcId)
            },
            getFormDetail(id) {
                getAqjcDetail(id).then(response => {
                    this.fromDetail = response.data.data
                })
            },
            getFormCheckDetail(id){
                getAqjcContent(id).then(response => {
                    this.checkData = response.data.data
                    console.log(this.checkData)
                })
            },
            getJcqk(state){
                if(state == '0'){
                    return "否"
                }
                if(state == '1'){
                    return "是"
                }
            },
        }
    }
</script>