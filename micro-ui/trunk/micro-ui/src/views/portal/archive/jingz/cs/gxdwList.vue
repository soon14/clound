<template>
 <div class="execution">
   <basic-container>
    <avue-crud :option="option"
               :data="tableData"
               v-model="form"
               ref="crud"
               :page="page"
               @on-load="getList"
               @search-reset="clearForm"
               @size-change="sizeChange"
               @search-change="searchChange"
               :table-loading="tableLoading">

        <template slot="menu" slot-scope="scope" >
            <el-button size="mini"
                       type="text"
                       icon="el-icon-view"
                       @click="handleDetail(scope.row)">查看
            </el-button>
            <el-button size="mini"
                       type="text"
                       icon="el-icon-edit"
                       @click="contact(scope.row)">场所关联
            </el-button>
        </template>
    </avue-crud>
   </basic-container>
     <el-dialog :title="detailTitle" fullscreen :visible.sync="showDetail" :append-to-body="applyToBody">
         <keep-alive>
             <place-tab v-bind:param-form="paramForm"/>
         </keep-alive>

     </el-dialog>

     <el-dialog :title="contactTitle" fullscreen :visible.sync="showContact"  :append-to-body="applyToBody">
         <keep-alive>
             <jz-contact-list v-bind:contact-form="contactForm"/>
         </keep-alive>
     </el-dialog>

    </div>
</template>


<script>
    import {countAll, findListPage, findOne} from "@/api/portal/archive/common/unitQuery";
    import placeTab from "./placeTab";
    import jzContactList from "../../contact/jzContactList";
    import { validatenull } from "@/util/validate";
    import {fetchDictTree} from "@/api/admin/dict";
    export default {
        components: {
            "placeTab": placeTab,
            "jzContactList":jzContactList
        },
        props:["searchForm"],
        data(){
            return {
                form:{
                },
                contactTitle:'',//添加关联弹窗title
                showContact:false,//添加关联弹窗是否展示
                contactForm: {
                    lyxtType: undefined,
                    jzdwbh:undefined,
                    dwmc:undefined,
                    hylx: undefined,
                    placeState:undefined,
                    areaCode:undefined,
                    address:undefined,
                    sspcs:undefined,
                    compId:undefined,//单位id 娱乐场所用到
                },

                showDetail: false,
                applyToBody: true,
                detailTitle: '',
                viewFlag:false,
                tableLoading: false,
                page: {
                    total: 0, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 10 // 每页显示多少条
                },
                paramForm: {
                    menuId: undefined,
                    unitId: undefined,
                    placeType:undefined,
                },
                tableData: [],
                dictPlaceTypeData:[],
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
                    column:[
                        {  label:"企业名称", prop:"qyzcmc",search:true,searchDefault:'',  overHidden:true,},
                        {  label:"行业类型", prop:"gxhylx", search:true ,searchFilterable:true,
                            type:'tree',
                            dicUrl: '/portal/dict/getDict/za_024/jzxt' ,
                            dicMethod: 'get',
                            searchMultiple: true
                        },

                        {  label:"经营状态", prop:"jyzt",search:true,searchFilterable: true,type:'select',
                            dicUrl: '/portal/dict/getDict/za_003/jzxt',
                            dicMethod: 'get',

                        },
                        {  label:"责任民警", prop:"zrmjxm", },
                        {  label:"行政区划", prop:"dwszdxzqh", search:true ,type:'tree',
                            dicUrl: '/portal/area/areaData',
                            dicMethod: 'get',
                            dicQuery: {},
                            props: {
                                label: "name",
                                value: 'code'
                            },
                            multiple:true
                        },
                        {  label:"管辖单位", prop:"deptName",
                            /*dicUrl: '/portal/dict/getDict/06/jzxt',
                            dicMethod: 'get',*/
                            overHidden:true,
                        },
                        {
                            label:"总积分", prop:"pointScore",

                        },
                        {
                            label: "积分等级", prop: "pointType", search: true, type: 'select', searchFilterable: true
                            , dicUrl: '/admin/dict/type/jfdj',
                            dicMethod: 'get',
                            dicQuery: {},
                        },

                        {  label:"按摩床(椅)数量 -按摩", prop:"amcysl", hide:true ,},
                        {  label:"按摩室数量 -按摩", prop:"amssl", hide:true ,},
                        {  label:"安全员数量", prop:"aqysl", hide:true ,},
                        {  label:"保安人员来源", prop:"baly", hide:true ,},
                        {  label:"派驻单位", prop:"bapzdw", hide:true ,},
                        {  label:"保安人员总数", prop:"baryzs", hide:true ,},
                        {  label:"保安组织负责人姓名", prop:"bazzfzrxm", hide:true ,},
                        {  label:"保安组织联系电话", prop:"bazzlxdf", hide:true ,},
                        {  label:"实际经营人办公电话", prop:"bglxdh", hide:true ,},
                        {  label:"被兼营单位", prop:"bjydw", hide:true ,},
                        {  label:"备注", prop:"bz", hide:true ,},
                        {  label:"创建时间", prop:"createdtime", hide:true ,},
                        {  label:"创建者", prop:"creator", hide:true ,},
                        {  label:"", prop:"crksl", hide:true ,},
                        {  label:"床位数-旅业", prop:"cws", hide:true ,},
                        {  label:"从业人员总数", prop:"cyryzs", hide:true ,},
                        {  label:"删除标志", prop:"deleteflag", hide:true ,},

                        {  label:"等级-旅业", prop:"dj", hide:true ,},
                        {  label:"", prop:"downloadflag", hide:true ,},
                        {  label:"单位编号/企业编号", prop:"dwbh", hide:true ,},
                        {  label:"单位所在地派出所", prop:"dwszdpcs", hide:true ,},
                        /*{  label:"单位所在地区", prop:"dwszdxzqh", hide:true ,},*/
                        {  label:"单位英文名称", prop:"dwywmc", hide:true ,},
                        {  label:"电子邮箱", prop:"email", hide:true ,},
                        {  label:"房间数-旅业", prop:"fjs", hide:true ,},
                        {  label:"法人代表", prop:"frdb", hide:true ,},
                        {  label:"服务器数量- 上网服务", prop:"fwqsl", hide:true ,},
                        {  label:"放映厅数量 -放映厅", prop:"fytsl", hide:true ,},

                        {  label:"", prop:"hdrl", hide:true ,},
                        {  label:"行业类别", prop:"hylb", hide:true ,},
                        {  label:"技防设施", prop:"jfss", hide:true ,},
                        {  label:"街路巷", prop:"jlx", hide:true ,},
                        {  label:"技术委托单位电话 -上网服务", prop:"jswtdwdh", hide:true ,},
                        {  label:"技术委托单位地址 -上网服务", prop:"jswtdwdz", hide:true ,},
                        {  label:"技术服务委托单位名称 -上网服务", prop:"jswtdwmc", hide:true ,},
                        {  label:"警务区联系电话", prop:"jwqlxdh", hide:true ,},
                        {  label:"经营地址", prop:"jydz", hide:true ,},
                        {  label:"经营项目兼营", prop:"jyxmjy", hide:true ,},
                        {  label:"经营项目主营", prop:"jyxmzy",hide:true  },

                        {  label:"经营人员数量(兼职)", prop:"jzjyrsl", hide:true ,},
                        {  label:"开锁工艺-开锁业", prop:"ksgy", hide:true ,},
                        {  label:"开业日期", prop:"kyrq", hide:true ,},
                        {  label:"刻字方式-刻字业", prop:"kzfs", hide:true ,},
                        {  label:"最终修改人", prop:"lastupdatedby", hide:true ,},
                        {  label:"最后修改时间", prop:"lastupdatedtime", hide:true ,},
                        {  label:"楼层数-旅业", prop:"lcs", hide:true ,},
                        {  label:"旅馆编号", prop:"lgbh", hide:true ,},
                        {  label:"联系电话", prop:"lxdh", hide:true ,},
                        {  label:"录入人", prop:"lyr", hide:true ,},
                        {  label:"录入时间", prop:"lysj", hide:true ,},
                        {  label:"面积", prop:"mj", hide:true ,},
                        {  label:"门楼牌", prop:"mlp", hide:true ,},
                        {  label:"培训上岗人数", prop:"pxsgrs", hide:true ,},
                        {  label:"企业实际经营名称", prop:"qysjjymc", hide:true ,},
                        {  label:"企业性质", prop:"qyxz", hide:true ,},
                        {  label:"", prop:"refreshtime", hide:true ,},
                        {  label:"注1：以被用为：注销原因", prop:"reservation01", hide:true ,},
                        {  label:"", prop:"reservation02", hide:true ,},
                        {  label:"备注:地址编码", prop:"reservation03", hide:true ,},
                        {  label:"该字段标识外网生成帐号", prop:"reservation04", hide:true ,},
                        {  label:"行政区划名称", prop:"reservation05", hide:true ,},
                        {  label:"街路巷名称", prop:"reservation06", hide:true ,},
                        {  label:"警区名称", prop:"reservation07", hide:true ,},
                        {  label:"社区名称", prop:"reservation08", hide:true ,},
                        {  label:"单位管辖派出所名称", prop:"reservation09", hide:true ,},
                        {  label:"", prop:"reservation10", hide:true ,},
                        {  label:"注：用为：机构组织代码", prop:"reservation11", hide:true ,},
                        {  label:"乡镇（街道）代码", prop:"reservation12", hide:true ,},
                        {  label:"乡镇（街道）名称", prop:"reservation13", hide:true ,},
                        {  label:"治安负责人证件种类", prop:"reservation14", hide:true ,},
                        {  label:"治安负责人证件号码", prop:"reservation15", hide:true ,},
                        {  label:"主营 1 | 兼营 0 | 否则 空", prop:"reservation16", hide:true ,},
                        {  label:"主兼营单位", prop:"reservation17", hide:true ,},
                        {  label:"法定代表人手机号码", prop:"reservation18", hide:true ,},
                        {  label:"单位负责人手机号码", prop:"reservation19", hide:true ,},
                        {  label:"治安负责人手机号码", prop:"reservation20", hide:true ,},
                        {  label:"", prop:"reservation21", hide:true ,},
                        {  label:"", prop:"reservation22", hide:true ,},
                        {  label:"", prop:"reservation23", hide:true ,},
                        {  label:"", prop:"reservation24", hide:true ,},
                        {  label:"注：是否是社会信息采集用户  1为是，空或0为否", prop:"reservation25", hide:true ,},
                        {  label:"消防管理员名称", prop:"reservation26", hide:true ,},
                        {  label:"消防管理员联系电话", prop:"reservation27", hide:true ,},
                        {  label:"消防管负责人名称", prop:"reservation28", hide:true ,},
                        {  label:"消防管负责人联系电话", prop:"reservation29", hide:true ,},
                        {  label:"", prop:"reservation30", hide:true ,},
                        {  label:"", prop:"reservation31", hide:true ,},
                        {  label:"", prop:"reservation32", hide:true ,},
                        {  label:"", prop:"reservation33", hide:true ,},
                        {  label:"", prop:"reservation34", hide:true ,},
                        {  label:"", prop:"reservation35", hide:true ,},
                        {  label:"注1：已被用为：注销日期", prop:"reservation36", hide:true ,},
                        {  label:"", prop:"reservation37", hide:true ,},
                        {  label:"", prop:"reservation38", hide:true ,},
                        {  label:"", prop:"reservation39", hide:true ,},
                        {  label:"", prop:"reservation40", hide:true ,},
                        {  label:"密级", prop:"securitygrade", hide:true ,},
                        {  label:"是否记录网吧内IP地址分配情况 -上网服务", prop:"sfjldzfp", hide:true ,},
                        {  label:"是否安装网络安全设备", prop:"sftgaqsb", hide:true ,},
                        {  label:"是否提供上网电脑", prop:"sftgswdn", hide:true ,},
                        {  label:"是否提供上网服务", prop:"sftgswfw", hide:true ,},
                        {  label:"实际经营人姓名", prop:"sjjyrxm", hide:true ,},
                        {  label:"上级主管部门", prop:"sjzgbm", hide:true ,},
                        {  label:"所属警务区", prop:"ssjwq", hide:true ,},
                        {  label:"所属社区", prop:"sssq", hide:true ,},
                        {  label:"", prop:"sstdsl", hide:true ,},
                        {  label:"上网电脑数量 -上网服务", prop:"swdnsl", hide:true ,},
                        {  label:"上网方式 -上网服务", prop:"swfs", hide:true ,},
                        {  label:"上网接口数量", prop:"swjksl", hide:true ,},
                        {  label:"IP地址(上网帐号) -上网服务", prop:"swzh", hide:true ,},
                        {  label:"系统主键", prop:"systemid", hide:true ,},
                        {  label:"填表人", prop:"tbr", hide:true ,},
                        {  label:"填表时间", prop:"tbsj", hide:true ,},
                        {  label:"", prop:"uploadflag", hide:true ,},
                        {  label:"舞池核定人数 --歌舞娱乐场所", prop:"wchdrs", hide:true ,},
                        {  label:"舞池面积 --歌舞娱乐场所", prop:"wcmj", hide:true ,},
                        {  label:"网管负责人姓名", prop:"wgfzrxm", hide:true ,},
                        {  label:"网管负责人联系电话", prop:"wglxdh", hide:true ,},
                        {  label:"网管软件版本 -上网服务", prop:"wgrjbb", hide:true ,},
                        {  label:"网管软件开发单位 -上网服务", prop:"wgrjkfdw", hide:true ,},
                        {  label:"网管软件名称 -上网服务", prop:"wgrjmc", hide:true ,},
                        {  label:"网管负责人身份证号码", prop:"wgzjhm", hide:true ,},
                        {  label:"网络接入提供商-上网服务", prop:"wljrtgs", hide:true ,},
                        {  label:"防审核单位", prop:"xfshdw", hide:true ,},
                        {  label:"消防审核情况", prop:"xfshqk", hide:true ,},
                        {  label:"厢房数量-歌舞娱乐场所", prop:"xfsl", hide:true ,},
                        {  label:"星级-旅业", prop:"xj", hide:true ,},
                        {  label:"详细地址", prop:"xxdz", hide:true ,},
                        {  label:"实际经营人手机", prop:"ydlxdh", hide:true ,},
                        {  label:"印刷机机型-印刷业", prop:"ysjjx", hide:true ,},
                        {  label:"印刷机数量-印刷业", prop:"ysjsl", hide:true ,},
                        {  label:"游戏游艺机数 -游戏厅", prop:"yxyyjs", hide:true ,},
                        {  label:"治安单位编号", prop:"zadwbh", hide:true ,},
                        {  label:"单位编号", prop:"zbdwbh", hide:true ,},
                        {  label:"注册地址", prop:"zcdz", hide:true ,},
                        {  label:"注册资金(万元)", prop:"zczj", hide:true ,},
                        {  label:"总核定人数 -歌舞娱乐场所", prop:"zhdrs", hide:true ,},
                        {  label:"", prop:"zjxx", hide:true ,},
                        {  label:"责任民警警号", prop:"zrmjjh", hide:true ,},

                        {  label:"座位数 -放映厅", prop:"zws", hide:true ,},
                        {  label:"组织机构代码", prop:"zzjgdm" ,hide:true},
                        {  label:"经营人员数量(专职)", prop:"zzjyrsl", hide:true ,}

                    ]
                }
            }
        },
        created(){


            this.initPlaceTypeData()
            this. initDefaultValue()

        },
        computed: {

        },
        methods:{

            clearForm() {
                try{
                    this.$refs["crud"].searchForm.qyzcmc=undefined
                    this.$refs["crud"].searchForm.pointType=undefined
                    this.$refs["crud"].searchForm.gxhylx=undefined
                    this.$refs["crud"].searchForm.dwszdxzqh=undefined
                }catch (e) {

                }

            },
            initDefaultValue(){
                this.option.column[0].searchDefault=this.searchForm.dwmc
                this.option.column[7].searchDefault=this.searchForm.pointType

                setTimeout(() => {
                    try{
                        this.$refs["crud"].searchForm.gxhylx=this.searchForm.placeType
                        this.$refs["crud"].searchForm.dwszdxzqh=this.searchForm.orgCode
                    }catch (e) {}
                }, 1500);
            },
            sizeChange(size) {
                this.page.pageSize = size;
            },
            initPlaceTypeData(){
                fetchDictTree({ type: 'HYLX'}).then(response => {
                    this.dictPlaceTypeData = response.data.data
                })
            },
            getList(page, params) {
                this.tableLoading = true
                findListPage(Object.assign({
                    current: page.currentPage,
                    size: page.pageSize,
                }, this.searchForm)).then(response => {
                    this.tableData = response.data.data.records
                    this.page.total = response.data.data.total
                    this.tableLoading = false
                })
            },

            searchChange(form) {

                 this.searchForm.dwmc = form.qyzcmc
                 this.searchForm.placeState=form.jyzt
                 this.searchForm.pointType= form.pointType
                 this.searchForm.orgCode=form.dwszdxzqh
                 if(!validatenull(form.gxhylx)){
                     this.searchForm.placeType= form.gxhylx.join(",")
                 }else {
                     this.searchForm.placeType=undefined
                 }
                this.getList(this.page, form)
                this.$emit("changeCount", this.searchForm);

            },
            handleDetail(row) {
                this.showDetail = true
                this.detailTitle = row.qyzcmc
                this.paramForm.menuId = this.searchForm.menuId
                this.paramForm.unitId = row.systemId
                this.paramForm.placeType=row.gxhylx
            },

            contact(row){
                this.contactTitle='场所关联'
                this.showContact=true
                this.contactForm.lyxtType=''
                this.contactForm.jzdwbh=row.systemId
                this.contactForm.dwmc=row.qyzcmc
                this.contactForm.hylx=row.gxhylx
                this.contactForm.address=row.jydz
                this.contactForm.areaCode=row.dwszdxzqh
                this.contactForm.sspcs=row.departmentCode
            }

        }
    }
</script>