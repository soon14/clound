<template>
    <div class="execution">
        <basic-container>
            <avue-crud ref="crud"
                       :page="page"
                       :option="tabOption1"
                       :data="data1"
                       :table-loading="loading"
                       @row-update="handleUpdate"
                       @row-save="handleSav"
                       @row-del="handleDelete"
                       @date-change="dateChange"

                       v-model="form">
                <template slot="menuLeft">
                    <el-button
                            class="filter-item"
                            @click="dialogVisible = true"
                            size="small"
                            type="primary"
                            icon="el-icon-download">导出
                    </el-button>
                </template>

                <template slot="menu" slot-scope="scope" >
                    <el-button size="mini"
                               type="text"
                               icon="el-icon-view"
                               @click="handleView(scope.row,scope.index)">查看积分明细
                    </el-button>
                </template>

                <template slot="infoForm" slot-scope="scope">
                    <avue-crud :option="infoOption" :data="infoData"
                               :page="page"
                               :table-loading="loading"
                               @row-update="handleUpdate"
                               @row-save="handleSav"
                               @row-del="handleDelete()">
                    </avue-crud>
                </template>
                <template slot="detailForm" slot-scope="scope" :span="12">
                    <avue-detail :option="detailoption" v-model="form"></avue-detail>
                </template>

            </avue-crud>
            <export-file :exprotFields="exprotFields"
                         :dialogVisible="dialogVisible"
                         :url="exportUrl"
                         :params="form"
                         :pageSize="page.pageSize"
                         :currentPage="page.currentPage"
                         :ascs="page.ascs"
                         :descs="page.descs"
                         :fileName="fileName"
                         v-on:hiddeDialg="dialogVisible = false" >
            </export-file>
        </basic-container>
    </div>
</template>

<script>
    import exportFile from "@/page/export/exportFile"
    let dict ={
        sydw:[
            {label: '江门市公安局', value: '0'},
            {label: '甘化社区警务区', value: '1',},
            {label: '海傍社区警务区', value: '2',},
            {label: '常安社区警务区',value: '3',}
        ],
        dj:[{label: 'A', value: 'A'},
            {label: 'B', value: 'B'},
            {label: 'C', value: 'C'},],
        sycslb:[
            {"label":"旅馆业","value":"01"},
            {"label":"刻印章业","value":"02"},
            {"label":"旧货业","value":"03"},
            {"label":"印刷业","value":"04"},
            {"label":"机动车修理业","value":"05"},
            {"label":"开锁业","value":"06"},
            {"label":"其他管理行业","value":"07"},
            {"label":"歌舞娱乐场所","value":"08"},
            {"label":"按摩服务业","value":"09"},
            {"label":"游戏游艺场所","value":"10"},
            {"label":"录像放映场所","value":"11"},
            {"label":"上网服务场所","value":"12"},
            {"label":"体育场馆","value":"13"},
            {"label":"广场公园","value":"14"},
            {"label":"其他场所","value":"15"},
            {"label":"报废机动车拆解业","value":"16"},
            {"label":"典当业","value":"17"},
            {"label":"废旧金属业","value":"18"}
        ],
    }
    export default {
        components: { "exportFile" : exportFile},
        data() {
            return {
                page: {
                    total: 2, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                dialogVisible: false,
                exportUrl: 'portal/point/rating_result/export',
                fileName:  '等级评定结果信息',
                exprotFields : {
                    ranking:'排名',
                    csmc: '场所名称',
                    ssnd: '所属年度',
                    cslb: '场所类别',
                    gxpcs:'管辖派出所',
                    jf:'积分',
                },
                form: {},
                tabOption1:{
                    indexLabel: '排名',
                    index: true,
                    editBtn:false,
                    addBtn: false,
                    viewBtn: false,
                    delBtn:false,
                    border: true,
                    dateBtn:true,
                    loading:true,
                    card:true,
                    dialogWidth:'100%',
                    dialogHeight:'100%',
                    dialogFullscreen:true,
                    column: [
                        {
                            label:'',
                            hide:true,
                            formslot: true,
                            slot: true,
                            prop:'detail',
                            span:26,
                        },
                        {
                            label: '场所名称',
                            prop: 'csmc',
                            display:false,
                            span:12,
                        },
                        {
                            label: '所属年度',
                            prop: 'ssnd',
                            type:'year',
                            display:false,
                            span:12,
                        },
                        {
                            label: '场所类别',
                            prop: 'sycslb',
                            type:'select',
                            search:true,
                            display:false,
                            span:12,
                            dicData: dict.sycslb,
                        },
                        {
                            label: '所属辖区',
                            prop: 'ssxq',
                            type:'select',
                            search:true,
                            hide:true,
                            display:false,
                            span:12,
                            dicData: dict.sydw,
                        },
                        {
                            label: '管辖派出所',
                            prop: 'gxpcs',
                            display:false,
                            span:12,
                        },
                        {
                            label: '积分',
                            prop: 'jf',
                            display:false,
                            span:12
                        },
                        {
                            label: '时间范围',
                            prop: 'sjfw',
                            type: 'datetimerange',
                            hide:'true',
                            display:false,
                            span:12,
                            format: 'yyyy-MM-dd HH:mm:ss',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss',
                        },
                        {
                            labelWidth:0,
                            label: '',
                            prop: 'info',
                            span:24,
                            hide:true,
                            formslot:true,
                        }
                    ]
                },
                data1:[
                    {
                        csmc:'七天酒店',
                        ssnd:'2018',
                        sycslb:'01',
                        ssxq:'0',
                        gxpcs:'东街派出所',
                        jf:'95',
                        djpd:'A',
                        info:[
                            {
                                zbmc:'涉黄人员入住',
                                zblx:'自动',
                                fz:'2',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉赌人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉毒人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'人证不一致',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },

                        ]
                    },
                    {
                        csmc:'全家酒店',
                        ssnd:'2018',
                        sycslb:'01',
                        ssxq:'0',
                        gxpcs:'东街派出所',
                        jf:'90',
                        djpd:'A',
                        info:[
                            {
                                zbmc:'涉黄人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉赌人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉毒人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'人证不一致',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },

                        ]
                    },
                    {
                        csmc:'有缘酒店',
                        ssnd:'2018',
                        sycslb:'01',
                        ssxq:'0',
                        gxpcs:'东街派出所',
                        jf:'75',
                        djpd:'A',
                        info:[
                            {
                                zbmc:'涉黄人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉赌人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉毒人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'人证不一致',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },

                        ]
                    },
                    {
                        csmc:'希尔顿酒店',
                        ssnd:'2018',
                        sycslb:'01',
                        ssxq:'0',
                        gxpcs:'东街派出所',
                        jf:'70',
                        djpd:'A',
                        info:[
                            {
                                zbmc:'涉黄人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉赌人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'涉毒人员入住',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },
                            {
                                zbmc:'人证不一致',
                                zblx:'自动',
                                fz:'1',
                                cs:'12',
                                jfxj:'12',
                            },

                        ]
                    },
                ],
                infoOption:{
                    addBtn:false,
                    delBtn:false,
                    editBtn:false,
                    viewBtn:true,
                    menu:false,
                    loading:true,
                    column:[
                        {
                            label:'指标名称',
                            prop:'zbmc',
                            span:12,
                        },
                        {
                            label:'指标类型',
                            prop:'zblx',
                            span:12,
                        },
                        {
                            label:'分值',
                            prop:'fz',
                            span:12,
                        },
                        {
                            label:'次数',
                            prop:'cs',
                            span:12,
                        },
                        {
                            label:'积分小计',
                            prop:'jfxj',
                            span:12,
                        },
                    ]
                },
                detailoption:{
                    icon:'el-icon-info',
                    label: '场所信息',
                    prop: 'group',
                    column:[
                        {
                            label: '场所名称',
                            prop: 'csmc',
                        },
                        {
                            label: '所属年度',
                            prop: 'ssnd',
                            type:'year',
                        },
                        {
                            label: '场所类别',
                            prop: 'sycslb',
                            type:'select',
                            dicData: dict.sycslb,
                        },
                        {
                            label: '所属辖区',
                            prop: 'ssxq',
                            dicData: dict.sydw,
                        },
                        {
                            label: '管辖派出所',
                            prop: 'gxpcs',
                        },
                        {
                            label: '积分',
                            prop: 'jf',
                        },
                    ]},
            }
        },

        computed:{
            infoData(){
                return this.form.info
            }
        },

        methods: {
            dateChange(val){
                this.$message.success(val);
            },

            handleChange(column) {
                this.type = column
            },
            handleView(row, index) {
                this.$refs.crud.rowView(row, index)
            },
            handleEdit (row, index) {
                this.$refs.crud.rowEdit(row, index);
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