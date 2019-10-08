<template>
    <div class="execution">
        <basic-container>
           <avue-crud ref="crud"
                      :option="option"
                      :data="data"
                      :table-loading="tableLoading"
                      @search-change="handleFilter">
               <template slot="shry" slot-scope="scope">
                   <el-button type="text" @click="showDetail('08',scope.row.code)">{{scope.row.shry}}</el-button>
               </template>
               <template slot="dbry" slot-scope="scope">
                   <el-button type="text" @click="showDetail('09',scope.row.code)">{{scope.row.dbry}}</el-button>
               </template>
               <template slot="skry" slot-scope="scope">
                   <el-button type="text" @click="showDetail('01',scope.row.code)">{{scope.row.skry}}</el-button>
               </template>
               <template slot="swry" slot-scope="scope">
                   <el-button type="text" @click="showDetail('02',scope.row.code)">{{scope.row.swry}}</el-button>
               </template>
               <template slot="ztry" slot-scope="scope">
                   <el-button type="text" @click="showDetail('03',scope.row.code)">{{scope.row.ztry}}</el-button>
               </template>
               <template slot="xdry" slot-scope="scope">
                   <el-button type="text" @click="showDetail('04',scope.row.code)">{{scope.row.xdry}}</el-button>
               </template>
               <template slot="xsfzry" slot-scope="scope">
                   <el-button type="text" @click="showDetail('05',scope.row.code)">{{scope.row.xsfzry}}</el-button>
               </template>
               <template slot="zszhjsbry" slot-scope="scope">
                   <el-button type="text" @click="showDetail('06',scope.row.code)">{{scope.row.zszhjsbry}}</el-button>
               </template>
               <template slot="zdsfry" slot-scope="scope">
                   <el-button type="text" @click="showDetail('07',scope.row.code)">{{scope.row.zdsfry}}</el-button>
               </template>
           </avue-crud>
        </basic-container>

        <el-dialog title="查看" :visible.sync="detailVible"  :append-to-body="applyToBody" width="80%" :before-close="closeDialog">
            <cyry-list v-bind:param-form="dialogForm"></cyry-list>
            <span slot="footer"
                  class="dialog-footer">
                <el-button @click="closeDialog">关闭</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

    import {treeList} from "@/api/portal/statistics/employee/employee.js";
    import CyryList from "./list";

    export default {
        components: {CyryList},
        data() {
            return {
                dialogForm:{
                    rylx:undefined,
                    gxqh:undefined,
                    hylx:"",
                    ranNum:0
                },
                detailVible:false,
                applyToBody: true,
                tableLoading:false,
                data: [],
                option: {
                    headerAlign: 'center',
                    align: 'center',
                    border: true,
                    index: true,
                    menu:false,
                    addBtn:false,
                    tree:true,
                    expandRowKeys:['1'],
                    column: [
                        {
                            label: "所属区划",
                            prop: "code",
                            dicUrl: '/portal/area/areaData',
                            type: "tree",
                            dicMethod: 'get',
                            search: true,
                            editDisabled: true,
                            addDisplay: false,
                            editDisplay: false,
                            dicQuery: {
                                level: 2
                            },
                            props: {
                                label: "name",
                                value: 'code'
                            }
                        },
                        {
                            label:'行业类型',
                            prop:'hylx',
                            search:true,
                            type:'select',
                            dicUrl: '/admin/dict/type/HYLX_DL',
                            dicMethod: 'get',
                            dicQuery: {},
                            span:12,
                            hide:true
                        },
                        /*{
                            label:'统计起止时间',
                            prop:'tjqzsj',
                            search:true,
                            type:'datetimerange',
                            hide:true,
                            span:12,
                        },*/
                        {
                            label:'从业人员',
                            prop:'total',
                            span:12,
                        },
                        {
                            label:'涉黄人员',
                            prop:'shry',
                            span:12,
                            slot:true
                        },
                        {
                            label:'涉赌人员',
                            prop:'dbry',
                            span:12,
                            slot:true
                        },
                        {
                            label:'涉恐人员',
                            prop:'skry',
                            span:12,
                            slot:true
                        },
                        {
                            label:'涉稳人员',
                            prop:'swry',
                            span:12,
                            slot:true
                        },
                        {
                            label:'在逃人员',
                            prop:'ztry',
                            span:12,
                            slot:true
                        },
                        {
                            label:'涉毒人员',
                            prop:'xdry',
                            span:12,
                            slot:true
                        },
                        {
                            label:'刑事犯罪前科人员',
                            prop:'xsfzry',
                            span:12,
                            slot:true
                        },
                        {
                            label:'肇事肇祸精神病人',
                            prop:'zszhjsbry',
                            span:12,
                            slot:true
                        },
                        {
                            label:'重点上访人员',
                            prop:'zdsfry',
                            span:12,
                            slot:true
                        },
                    ],
                },
            }
        },
        created() {
            //初始化

            this.getList({});

        },
        mounted(){
        },
        watch:{
        },
        computed: {

        },
        methods:{
            getList(params){
                this.tableLoading = true
                treeList(params).then(response=>{
                    this.tableLoading = false;
                    this.data = response.data.data;
                }).catch(()=>{});
            },
            handleFilter(params){
                this.dialogForm.hylx = params.hylx;
                console.log(params);
                this.getList(params);
            },
            showDetail(type,code){
                this.dialogForm.ranNum = new Date().getTime();
                this.dialogForm.rylx = type;
                this.dialogForm.gxqh = code;
                this.detailVible = true;
            },
            closeDialog:function () {
                this.detailVible = false;
            }
        }
    }
</script>

<style scoped>

</style>