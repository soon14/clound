<template>
    <div class="execution">
        <basic-container>
            <avue-crud :option="option"
                       :data="fileData"
                       v-model="form"
                       ref="crud"
                       :page="page"
                       @size-change="sizeChange"
                       @on-load="findPlaceFile"
                       @search-change="handleFilter"
                       :table-loading="tableLoading">

                <template slot="menu" slot-scope="scope" >
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-view"
                               @click="downLoad(scope.row)">下载
                    </el-button>

                </template>
            </avue-crud>
        </basic-container>
    </div>
</template>


<script>

    import {findPlaceFilePage,downLoadFile} from "@/api/portal/archive/jingz/placeFile";
    import { validatenull } from "@/util/validate";
    export default {

        name: 'placeFile',
        props:["paramForm"],
        data(){
            return {
                form:{
                },
                searchFrom: {
                    unitId: undefined,
                    menuId: undefined,
                },
                formDetail:{},
                formView:false,
                formTitle:'',
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                tableData: [],
                fileData:[],
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
                    refreshBtn: false,
                    menuWidth:'100',
                    card:true,
                    column:[
                        { label:"文件名", prop:"wjmc", search:true},
                        { label:"附件名称", prop:"fjmc",},
                        /*{ label:"行业类型", prop:"gxhylx",
                            dicUrl: '/portal/dict/getDict/za_002/jzxt',
                            dicMethod: 'get',},*/
                        { label:"文件大小", prop:"wjdx",},
                        { label:"文件类型", prop:"wjlx",},
                     /*   { label:"上传人", prop:"scr",},*/
                        { label:"上传时间", prop:"scsj",}
                    ]
                }
            }
        },
        created(){
           this.searchFrom.menuId=this.paramForm.menuId
            this.searchFrom.unitId=this.paramForm.unitId
        },
        computed: {
        },
        watch: {
            paramForm: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.searchFrom.dwbh = this.paramForm.unitId;
                    this.searchFrom.menuId = this.paramForm.menuId;
                    this.findPlaceFile(this.page,{});
                },
                deep: true
            },
        },
        methods:{
            downLoad(row){
                let mc=row.wjmc
                if(!validatenull(row.fjmc)&&!validatenull(row.wjlx)){
                    mc=row.fjmc+"."+row.wjlx
                }
                downLoadFile(row.systemId,mc);
            },
            sizeChange(size) {
                this.page.pageSize = size;
            },
            findPlaceFile(page,params) {
                try{
                    let   param=this.$refs["crud"].searchForm||{}
                    params=param
                }catch (e) {}
                this.tableLoading=true
                findPlaceFilePage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                    zbbh:this.searchFrom.unitId
                },params)).then(response => {
                    this.fileData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false

                })
            },

            handleFilter(param) {

                this.page.page = 1;
                this.findPlaceFile(this.page, param);
            },


        }
    }
</script>

