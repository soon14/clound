<template>
    <div class="execution">
        <basic-container>
           <avue-crud :option="option" v-model="form" :data="data">
               <template slot="menu" slot-scope="scope" >
                   <el-button size="mini"
                              type="text"
                              icon="el-icon-plus"
                              @click="zgDialogVisible=true">添加整改通知书
                   </el-button>
                   <el-button size="mini"
                              type="text"
                              @click="hisDialogVisible=true"
                              icon="el-icon-time">查看历史轨迹</el-button>
               </template>
               <template slot-scope="scope" slot="detailForm">
                    <target-model/>
               </template>
           </avue-crud>

            <el-dialog
                    title="责令修改通知书"
                    :visible.sync="zgDialogVisible"
                    width="70%"
                    append-to-body>
                <el-row>
                    <el-col :span="8"><input type="text" autocomplete="off" placeholder="请输入" class="el-form-item__content el-input__inner" style="width: 300px!important;"/>：</el-col>
                </el-row>
                <el-row>
                    <el-col :span="24">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据《中华人民共和国消防法》第五十三条的规定，我所于
                        <el-date-picker
                            type="date"
                            placeholder="选择日期">
                        </el-date-picker>
                        对你单位（场所）进行消防监督检查，发现你单位（场所）存在下列第<input type="text" autocomplete="off" placeholder="请输入" class="el-form-item__content el-input__inner" style="width: 100px!important;"/>
                        项消防安全违法行为，现责令修改：
                    </el-col>
                </el-row>
                <el-row style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);margin-top:10px;margin-bottom: 10px;padding: 5px; ">
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/>
                        <label class="el-checkbox__label">1. 未制定消防安全制度；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/>
                        <label class="el-checkbox__label">2. 未组织：<input type="checkbox" class="el-checkbox-button"/>防火检查/<input type="checkbox" class="el-checkbox-button"/>消防安全教育培训/<input type="checkbox" class="el-checkbox-button"/>消防演练；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/>
                        <label class="el-checkbox__label">3. <input type="checkbox" class="el-checkbox-button"/>占用/<input type="checkbox" class="el-checkbox-button"/>堵塞/<input type="checkbox" class="el-checkbox-button"/>封闭疏散通道、安全出口；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/><label class="el-checkbox__label">4. <input type="checkbox" class="el-checkbox-button"/>占用/<input type="checkbox" class="el-checkbox-button"/>堵塞/<input type="checkbox" class="el-checkbox-button"/>封闭消防车通道，妨碍消防车通行；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/><label class="el-checkbox__label">5. <input type="checkbox" class="el-checkbox-button"/>埋压/<input type="checkbox" class="el-checkbox-button"/>圈占/<input type="checkbox" class="el-checkbox-button"/>遮挡消防栓，<input type="checkbox" class="el-checkbox-button"/>占用防火间距；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/><label class="el-checkbox__label">6. <input type="checkbox" class="el-checkbox-button"/>室内消火栓/<input type="checkbox" class="el-checkbox-button"/>灭火器/<input type="checkbox" class="el-checkbox-button"/>疏散指示标志/<input type="checkbox" class="el-checkbox-button"/>应急照明未保持完好有效；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/><label class="el-checkbox__label">7. 人员密集场所外墙门窗上设置影响逃生、灭火救援的障碍物；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/><label class="el-checkbox__label">8. 违反消防安全规定进入<input type="checkbox" class="el-checkbox-button"/>生产/<input type="checkbox" class="el-checkbox-button"/>储存易燃易爆危险品场所；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/><label class="el-checkbox__label">9. 违反规定使用明火作业；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/><label class="el-checkbox__label">10. 在具有火灾、爆炸危险的场所<input type="checkbox" class="el-checkbox-button"/>吸烟/<input type="checkbox" class="el-checkbox-button"/>使用明火；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/><label class="el-checkbox__label">11. <input type="checkbox" class="el-checkbox-button"/>生产/<input type="checkbox" class="el-checkbox-button"/>储存/<input type="checkbox" class="el-checkbox-button"/>经营易燃易爆危险品的场所与居住场所设置在同一建筑物内的；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/><label class="el-checkbox__label">12. 未对建筑消防设施定期进行维修保养；</label>
                    </el-col></el-row>
                    <el-row><el-col :span="24">
                        <input type="checkbox" class="el-checkbox-button"/><label class="el-checkbox__label">13. 其他消防安全违法行为和火灾隐患<input type="text" autocomplete="off" placeholder="请输入" class="el-form-item__content el-input__inner" style="width: 650px!important;"/>；</label>
                    </el-col></el-row>
                </el-row>
                <el-form ref="form" :model="form" label-width="150px">
                    <el-row>
                        <el-col>
                            <el-form-item label="备注：">
                                <el-input
                                        type="textarea"
                                        :rows="3"
                                        placeholder="请输入内容">
                                </el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="被检查单位负责人：">
                                <input type="text" autocomplete="off" placeholder="请输入" class="el-form-item__content el-input__inner"/>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="被检查人签名日期：">
                                <el-date-picker
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="检查民警：">
                                <input type="text" autocomplete="off" placeholder="请输入" class="el-form-item__content el-input__inner"/>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="检查人签名日期：">
                                <el-date-picker
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="录入人：">
                                <input type="text" autocomplete="off" placeholder="请输入" class="el-form-item__content el-input__inner"/>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="录入时间：">
                                <el-date-picker
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="最后修改人：">
                                <input type="text" autocomplete="off" placeholder="请输入" class="el-form-item__content el-input__inner"/>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="最后修改时间：">
                                <el-date-picker
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="zgDialogVisible = false">保存</el-button>
                    <el-button type="info" @click="zgDialogVisible = false">打印</el-button>
                    <el-button @click="zgDialogVisible = false">取 消</el-button>
                </span>
            </el-dialog>

            <el-dialog
                    title="责令修改通知书"
                    :visible.sync="hisDialogVisible"
                    width="70%"
                    append-to-body>
                <history-page/>
            </el-dialog>
        </basic-container>
    </div>
</template>

<script>

    import TargetModel from "../program/model";
    import HistoryPage from "../../../log/history/index"
    import { dateFormat } from "@/util/date";

    let dict = {
        gxhylb: [
            {"label": "旅馆业", "value": "01"},
            {"label": "刻印章业", "value": "02"},
            {"label": "旧货业", "value": "03"},
            {"label": "印刷业", "value": "04"},
            {"label": "机动车修理业", "value": "05"},
            {"label": "开锁业", "value": "06"},
            {"label": "其他管理行业", "value": "07"},
            {"label": "歌舞娱乐场所", "value": "08"},
            {"label": "按摩服务业", "value": "09"},
            {"label": "游戏游艺场所", "value": "10"},
            {"label": "录像放映场所", "value": "11"},
            {"label": "上网服务场所", "value": "12"},
            {"label": "体育场馆", "value": "13"},
            {"label": "广场公园", "value": "14"},
            {"label": "其他场所", "value": "15"},
            {"label": "报废机动车拆解业", "value": "16"},
            {"label": "典当业", "value": "17"},
            {"label": "废旧金属业", "value": "18"},
            {"label": "涉枪单位", "value": "19"},
            {"label": "易制爆单位", "value": "20"},
            {"label": "剧毒化学品单位", "value": "21"},
            {"label": "旅业式出租屋", "value": "22"},
            {"label": "烟花爆竹单位", "value": "23"},
            {"label": "重点单位", "value": "24"},
            {"label": "一般单位", "value": "25"},
            {"label": "学校/幼儿园", "value": "26"},
            {"label": "保安服务公司", "value": "27"},
            {"label": "保安押运公司", "value": "28"},
            {"label": "保安培训机构", "value": "29"},
            {"label": "内部保安组织", "value": "30"},
            {"label": "放射性同位素", "value": "31"},
            {"label": "其他单位", "value": "32"}
        ],
        clyj: [{
            label: '罚款',
            value: '0'
        }, {
            label: '限期整改',
            value: '1'
        }, {
            label: '停业整顿',
            value: '2'
        }, {
            label: '发函告知',
            value: '3'
        }, {
            label: '收回许可证号',
            value: '4'
        }, {
            label: '吊销营业执照',
            value: '5'
        }
        ],
        jcjg: [{
            label: '正常',
            value: '0'
        }, {
            label: '存在问题',
            value: '1'
        }
        ],
        sspcs: [{
            label: '白沙派出所',
            value: '440703500000'
        }, {
            label: '北街派出所',
            value: '440703510000'
        },
           {
             label: '仓后派出所',
             value: '440703520000'
       },
           {
             label: '江门市公安局环市派出所',
             value: '440703530000'
       }],
        checkPeople:[{
            lable:'张三',
            value:'0'
        },{
            lable:'李四',
            value:'1'
        },{
            lable:'王五',
            value:'2'
        },{
            lable:'赵六',
            value:'3'
        }],
        checkType:[{
            lable:'日常检查',
            value:'0'
        },{
            lable:'消防检查',
            value:'1'
        }],
        dwxzDict:[
            {
                label:'内资',
                value:'100',
                children:[{
                    label:'国有全资',
                    value:'110'
                },
                {
                    label:'集体全资',
                    value:'120'
                },
                {
                    label:'股份合作',
                    value:'130'
                },
                {
                    label:'联营',
                    value:'140',
                    children:[
                        {
                            label:'国有联营',
                            value:'141'
                        },
                        {
                            label:'集体联营',
                            value:'141'
                        },
                        {
                            label:'国有与集体联营',
                            value:'141'
                        },
                        {
                            label:'其他联营',
                            value:'141'
                        }]
                },
                {
                    label:'有限责任（公司）',
                    value:'150',
                    children:[
                        {
                            label:'国有独资（公司）',
                            value:'151'
                        },
                        {
                            label:'其他有限责任（公司）',
                            value:'159'
                        }]
                },
                {
                    label:'股份有限（公司）',
                    value:'160'
                },
                {
                    label:'私有',
                    value:'170',
                    children:[
                        {
                            label:'私有独资',
                            value:'171'
                        },
                        {
                            label:'私有合伙',
                            value:'172'
                        },
                        {
                            label:'私营有限责任（公司）',
                            value:'173'
                        },
                        {
                            label:'私有股份有限（公司）',
                            value:'174'
                        },
                        {
                            label:'个体经营',
                            value:'175'
                        },
                        {
                            label:'其他私有',
                            value:'179'
                        }]
                },
                {
                    label:'其他内资',
                    value:'190'
                }]
            },
            {
                label:'港澳台投资',
                value:'200',
                children:[
                    {
                        label:'内地和港澳台合资',
                        value:'210'
                    },
                    {
                        label:'内地和港澳台合作',
                        value:'220'
                    },
                    {
                        label:'港澳台独资',
                        value:'230'
                    },
                    {
                        label:'港澳台投资股份有限（公司）',
                        value:'240'
                    },
                    {
                        label:'其他港澳台投资',
                        value:'290'
                    }
                ]
            },
            {
                label:'国外投资',
                value:'300',
                children:[{
                    label:'中外合资',
                    value:'310'
                },
                {
                    label:'中外合作',
                    value:'320'
                },
                {
                    label:'外资',
                    value:'330'
                },
                {
                    label:'国外投资股份有限（公司）',
                    value:'340'
                },
                {
                    label:'其他国外投资',
                    value:'390'
                }]
            },
            {
                label:'其他',
                value:'900'
            }
        ],
        sqjwqDict:[
            {
                label:'海傍社区警务区',
                value:'1'
            },
            {
                label:'甘化社区警务区',
                value:'2'
            },
            {
                label:'良化南一社区警务区',
                value:'3'
            },
            {
                label:'良化南二社区警务区',
                value:'4'
            },
            {
                label:'良化西一社区警务区',
                value:'5'
            },
            {
                label:'良化西二社区警务区',
                value:'6'
            },
            {
                label:'良化东一社区警务区',
                value:'7'
            },
            {
                label:'良化东二社区警务区',
                value:'8'
            }
        ],
        sq: [
            {
                label: '虎山',
                value: '1'
            },
            {
                label: '黄屋',
                value: '2'
            }, {
                label: '建新',
                value: '3'
            }

        ],
        jcfs:[
            {
                label:'日常检查',
                value:'01'
            },
            {
                label:'暗访',
                value:'02'
            },
            {
                label:'实时抽查',
                value:'03'
            },
            {
                label:'监督检查',
                value:'04'
            },
            {
                label:'公司奖罚',
                value:'05'
            },
            {
                label:'内保管理',
                value:'06'
            },
            {
                label:'其它',
                value:'07'
            }
        ],
        jcjg:[
            {
                label:'正常',
                value:'1'
            },
            {
                label:'存在问题',
                value:'2'
            }
        ]
    }

    export default {
        components: {
            "TargetModel":TargetModel,
            "HistoryPage":HistoryPage
        },
        data() {
            return {
                form: {
                    name: ''
                },
                upForm:{

                },
                zgDialogVisible:false,
                hisDialogVisible:false,
                data: [{
                    no:'[20180]第00260号',
                    name:'江门市七天酒店',
                    inChargePerson:'张某某',
                    address:'广东省江门市莲江区环市街道',
                    unitProperty:'175',
                    areaOfStructure:'1000',
                    floorOfStructure:'2',
                    heightOfStructure:'18',
                    inspectDate:'2019-03-03',
                    inspectType:'01',
                    superviseInspector:'董小姐',
                    followInspector:'何先生',
                    ssjwq:'1',
                    sssq:'1',
                    remark:'',
                    isPass:'1',
                    created:'董小姐',
                    createDate:new Date(),
                    updated:'董小姐',
                    updateDate:new Date(),
                    imgUrl: [
                        {
                            label: '检查1',
                            value:
                                'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553504657036&di=3ae1cc7501406e11bd820df47daa91d3&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ffd%2Ftuangou%2Fg1%2FM03%2FB3%2F8A%2FCghzflT-HqKAQmHmAAcKHIDDcB0675.jpg'
                        },
                        {
                            label: '检查2',
                            value:
                                'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553504657036&di=ca18eb20b772985160004c220b6fc29c&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ffd%2Ftuangou%2Fg2%2FM00%2FE1%2FEA%2FCghzf1US1kOAdSTdAAKSuONlVQI579.jpg'
                        }
                    ]
                },
                {
                    no:'[20180]第00261号',
                    name:'江门市七天酒店',
                    inChargePerson:'张某某',
                    address:'广东省江门市莲江区环市街道',
                    unitProperty:'175',
                    areaOfStructure:'1000',
                    floorOfStructure:'2',
                    heightOfStructure:'18',
                    inspectDate:'2019-03-04',
                    inspectType:'02',
                    superviseInspector:'董小姐',
                    followInspector:'何先生',
                    ssjwq:'1',
                    sssq:'1',
                    remark:'',
                    isPass:'2',
                    created:'董小姐',
                    createDate:new Date(),
                    updated:'董小姐',
                    updateDate:new Date(),
                    imgUrl: [
                        {
                            label: '检查1',
                            value:
                                'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553504657036&di=3ae1cc7501406e11bd820df47daa91d3&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ffd%2Ftuangou%2Fg1%2FM03%2FB3%2F8A%2FCghzflT-HqKAQmHmAAcKHIDDcB0675.jpg'
                        },
                        {
                            label: '检查2',
                            value:
                                'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553504657036&di=ca18eb20b772985160004c220b6fc29c&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ffd%2Ftuangou%2Fg2%2FM00%2FE1%2FEA%2FCghzf1US1kOAdSTdAAKSuONlVQI579.jpg'
                        }
                    ]
                },
                {
                    no:'[20180]第00260号',
                    name:'江门市七天酒店',
                    inChargePerson:'张某某',
                    address:'广东省江门市莲江区环市街道',
                    unitProperty:'175',
                    areaOfStructure:'1000',
                    floorOfStructure:'2',
                    heightOfStructure:'18',
                    inspectDate:'2019-03-03',
                    inspectType:'01',
                    superviseInspector:'董小姐',
                    followInspector:'何先生',
                    ssjwq:'1',
                    sssq:'1',
                    remark:'',
                    isPass:'1',
                    created:'董小姐',
                    createDate:new Date(),
                    updated:'董小姐',
                    updateDate:new Date(),
                    imgUrl: [
                        {
                            label: '检查1',
                            value:
                                'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553504657036&di=3ae1cc7501406e11bd820df47daa91d3&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ffd%2Ftuangou%2Fg1%2FM03%2FB3%2F8A%2FCghzflT-HqKAQmHmAAcKHIDDcB0675.jpg'
                        },
                        {
                            label: '检查2',
                            value:
                                'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553504657036&di=ca18eb20b772985160004c220b6fc29c&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ffd%2Ftuangou%2Fg2%2FM00%2FE1%2FEA%2FCghzf1US1kOAdSTdAAKSuONlVQI579.jpg'
                        }
                    ]
                },
                {
                    no:'[20180]第00261号',
                    name:'江门市七天酒店',
                    inChargePerson:'张某某',
                    address:'广东省江门市莲江区环市街道',
                    unitProperty:'175',
                    areaOfStructure:'1000',
                    floorOfStructure:'2',
                    heightOfStructure:'18',
                    inspectDate:'2019-03-04',
                    inspectType:'02',
                    superviseInspector:'董小姐',
                    followInspector:'何先生',
                    ssjwq:'1',
                    sssq:'1',
                    remark:'',
                    isPass:'2',
                    created:'董小姐',
                    createDate:new Date(),
                    updated:'董小姐',
                    updateDate:new Date(),
                    imgUrl: [
                        {
                            label: '检查1',
                            value:
                                'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553504657036&di=3ae1cc7501406e11bd820df47daa91d3&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ffd%2Ftuangou%2Fg1%2FM03%2FB3%2F8A%2FCghzflT-HqKAQmHmAAcKHIDDcB0675.jpg'
                        },
                        {
                            label: '检查2',
                            value:
                                'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553504657036&di=ca18eb20b772985160004c220b6fc29c&imgtype=0&src=http%3A%2F%2Fimages4.c-ctrip.com%2Ftarget%2Ffd%2Ftuangou%2Fg2%2FM00%2FE1%2FEA%2FCghzf1US1kOAdSTdAAKSuONlVQI579.jpg'
                        }
                    ]
                }],
                option: {
                    dialogWidth: '100%',
                    dialogHeight: '100%',
                    dialogFullscreen: true,
                    labelWidth:180,
                    indexLabel: '序号',
                    border:true,
                    index: true,
                    viewBtn:true,
                    column: [
                        {
                            label: '编号',
                            prop: 'no',
                            hide:true
                        },
                        {
                            label: '单位（场所）名称',
                            prop: 'name',
                            hide:true
                        },
                        {
                            label: '法定代表人/主要负责人',
                            prop: 'inChargePerson',
                            hide:true
                        },
                        {
                            label: '地址',
                            prop: 'address',
                            hide:true
                        },
                        {
                            label: '单位性质',
                            prop:'unitProperty',
                            hide:true,
                            type: "tree",
                            dicData:dict.dwxzDict
                        },
                        {
                            label: '使用的建筑面积（平方米）',
                            prop: 'areaOfStructure',
                            type: 'number',
                            hide:true
                        },
                        {
                            label: '使用的建筑具体层数',
                            prop: 'floorOfStructure',
                            type: 'number',
                            hide:true
                        },
                        {
                            label: '所在建筑高度（米）',
                            prop: 'heightOfStructure',
                            type: 'number',
                            hide:true
                        },
                        {
                            label:'检查日期',
                            prop:'inspectDate',
                            type:'date',
                            format: 'yyyy-MM-dd',
                            valueFormat: 'yyyy-MM-dd',
                            type: "daterange",
                            search:true
                        },
                        {
                            label: '检查方式',
                            prop: 'inspectType',
                            type:'select',
                            dicData:dict.jcfs,
                            search:true
                        },
                        {
                            label: '监督检查人员',
                            prop: 'superviseInspector'
                        },
                        {
                            label: '单位随同检查人员',
                            prop: 'followInspector'
                        },
                        {
                            label:'所属警务区',
                            prop: 'ssjwq',
                            hide:true,
                            type:'select',
                            dicData:dict.sqjwqDict
                        },
                        {
                            label:'所属社区',
                            prop:'sssq',
                            hide:true,
                            type:'select',
                            dicData:dict.sq
                        },
                        {
                            label:'备注',
                            prop:'remark',
                            hide:true
                        },
                        {
                            label:'检查是否合格',
                            prop:'isPass',
                            type:'select',
                            dicData:dict.jcjg,
                            search:true,
                        },
                        {
                            label:'创建人',
                            prop:'created',
                            hide:true
                        },
                        {
                            label:'创建时间',
                            prop:'createDate',
                            hide:true
                        },
                        {
                            label:'最后修改人',
                            prop:'updated',
                            hide:true
                        },
                        {
                            label:'最后时间',
                            prop:'updateDate',
                            hide:true
                        },
                        {
                            prop: 'detail',
                            slot:true,
                            formslot:true,
                            span:24,
                            hide:true
                        },
                        {
                            label: '相关图片',
                            prop: 'imgUrl',
                            type: 'upload',
                            span: 24,
                            listType: 'picture-card',
                            tip: '只能上传jpg/png文件，且不超过500kb',
                            action: 'https://jsonplaceholder.typicode.com/posts/'
                        }
                    ],
                    group: [
                        {
                            label: '基本信息',
                            prop: 'jbxx',
                            icon: 'el-icon-edit-outline',
                            column: [
                                {
                                    label: '编号',
                                    prop: 'no',
                                    hide:true
                                },
                                {
                                    label: '单位（场所）名称',
                                    prop: 'name',
                                    hide:true
                                },
                                {
                                    label: '法定代表人',
                                    prop: 'inChargePerson',
                                    hide:true
                                },
                                {
                                    label: '地址',
                                    prop: 'address',
                                    hide:true
                                },
                                {
                                    label: '单位性质',
                                    prop:'unitProperty',
                                    hide:true,
                                    type: "tree",
                                    dicData:dict.dwxzDict
                                },
                                {
                                    label: '使用的建筑面积（平方米）',
                                    prop: 'areaOfStructure',
                                    type: 'number',
                                    hide:true
                                },
                                {
                                    label: '使用的建筑具体层数',
                                    prop: 'floorOfStructure',
                                    type: 'number',
                                    hide:true
                                },
                                {
                                    label: '所在建筑高度（米）',
                                    prop: 'heightOfStructure',
                                    type: 'number',
                                    hide:true
                                },
                                {
                                    label:'所属警务区',
                                    prop: 'ssjwq',
                                    hide:true,
                                    type:'select',
                                    dicData:dict.sqjwqDict
                                },
                                {
                                    label:'所属社区',
                                    prop:'sssq',
                                    hide:true,
                                    type:'select',
                                    dicData:dict.sq
                                }
                            ]
                        },
                        {
                            label: '检查详情',
                            prop: 'jcxq',
                            icon: 'el-icon-view',
                            column: [{
                                prop: 'detail',
                                slot:true,
                                formslot:true,
                                span:24
                            }]
                        }
                        ,
                        {
                            label: '检查结果',
                            prop: 'jcjg',
                            icon: 'el-icon-edit-outline',
                            column: [
                                {
                                    label:'检查日期',
                                    prop:'inspectDate',
                                    type:'date',
                                    format: 'yyyy-MM-dd',
                                    valueFormat: 'yyyy-MM-dd'
                                },
                                {
                                    label: '检查方式',
                                    prop: 'inspectType',
                                    type:'select',
                                    dicData:dict.jcfs
                                },
                                {
                                    label: '监督检查人员',
                                    prop: 'superviseInspector'
                                },
                                {
                                    label: '单位随同检查人员',
                                    prop: 'followInspector'
                                },
                                {
                                    label: '上传相关图片',
                                    prop: 'imgUrl',
                                    type: 'upload',
                                    span: 24,
                                    listType: 'picture-card',
                                    tip: '只能上传jpg/png文件，且不超过500kb',
                                    action: 'https://jsonplaceholder.typicode.com/posts/'
                                },

                                {
                                    label:'检查是否合格',
                                    prop:'isPass',
                                    span:24,
                                    type:'select',
                                    dicData:dict.jcjg
                                },
                                {
                                    label:'备注',
                                    prop:'remark',
                                    hide:true,
                                    span:24,
                                    type:'textarea'
                                },
                                {
                                    label:'创建人',
                                    prop:'created',
                                    hide:true,
                                    readonly:true
                                },
                                {
                                    label:'创建时间',
                                    prop:'createDate',
                                    hide:true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                    readonly:true
                                },
                                {
                                    label:'最后修改人',
                                    prop:'updated',
                                    hide:true,
                                    readonly:true
                                },
                                {
                                    label:'最后时间',
                                    prop:'updateDate',
                                    hide:true,
                                    type: 'datetime',
                                    format: 'yyyy-MM-dd HH:mm',
                                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                                    readonly:true
                                }
                            ]
                        }
                    ]
                }
            }
        },
        created(){
        },
        mounted() {

        },
        methods: {
            handleHis(row){

            }
        }
    }
</script>

