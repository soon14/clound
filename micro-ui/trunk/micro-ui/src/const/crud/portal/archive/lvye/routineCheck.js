
let dictYesNo=[
    {label: '合格', value: '1'},
    {label: '不合格', value: '0'}
]
export const  option={
    align:'center',
    indexLabel: '序号',
    border: true,
    index: true,
    editBtn:false,
    delBtn:false,
    addBtn:false,
    viewBtn:false,
    menu:true,
    menuWidth:100,
    card:true,
    column:[
        { label: "检查民警",prop: 'mj',slot:true,viewDisplay:false},

        { label: "检查时间",prop: 'checkTime',type: 'datetimerange',
            search:true,
            hide:true,
            format: 'yyyy-MM-dd HH:mm',
            valueFormat: 'yyyyMMddHHmm'
        },
        { label: "Ⅰ检查民警",prop: 'jcmjZh',hide:true},
        { label: "Ⅱ检查民警",prop: 'jcmjTwoZh',hide:true},
        { label: "入住登记制度",prop: 'rzdjzd',dicData:dictYesNo},
        { label: "入住登记制度说明",prop: 'rzdjzdBz',dicData:dictYesNo,hide:true},

        { label: "来访登记制度",prop: 'lfdjzd',dicData:dictYesNo},
        { label: "来访登记制度说明",prop: 'lfdjzdBz',dicData:dictYesNo,hide:true},

        { label: "值班巡查制度",prop: 'zbxczd',dicData:dictYesNo,},
        { label: "值班巡查制度说明",prop: 'zbxczdBz',dicData:dictYesNo,hide:true},

        { label: "财务报告制度",prop: 'cwbgzd',dicData:dictYesNo},
        { label: "财务报告制度说明",prop: 'cwbgzdBz',dicData:dictYesNo,hide:true},

        { label: "报告制度",prop: 'bgzd',dicData:dictYesNo},
        { label: "报告制度说明",prop: 'bgzdBz',dicData:dictYesNo,hide:true},

        { label: "信息系统使用",prop: 'xxxt',dicData:dictYesNo},
        { label: "信息系统使用说明",prop: 'xxxtBz',dicData:dictYesNo,hide:true},

        { label: "上传及时",prop: 'scjs',dicData:dictYesNo},
        { label: "上传及时说明",prop: 'scjsBz',dicData:dictYesNo,hide:true},


        { label: "消防设备",prop: 'xfsbjhyq',dicData:dictYesNo},
        { label: "消防设备说明",prop: 'xfsbjhyqBz',dicData:dictYesNo,hide:true},

        { label: "当天录入信息齐全",prop: 'dtlrxx',dicData:dictYesNo},
        { label: "当天录入信息齐全说明",prop: 'dtlrxxBz',dicData:dictYesNo,hide:true},

        { label: "上传数量和录入数量",prop: 'scslylrslxf',dicData:dictYesNo},
        { label: "上传数量和录入数量说明",prop: 'scslylrslxfBz',dicData:dictYesNo,hide:true},

        { label:"检查单位", prop:"jcdwZh",hide:true},
        { label:"检查时间", prop:"jcsj",
            format: 'yyyy-MM-dd HH:mm',
            type: 'datetime',
            valueFormat: 'yyyy-MM-dd HH:mm'
        },
    ]
}
let spanWith=12
export const    optionDetail= {
    card: true,
    group: [
        {
            label: '检查内容',
            prop: 'checkInfo',
            icon: 'el-icon-info',
            column: [
                { label: "入住登记制度",prop: 'rzdjzd',dicData:dictYesNo,span:spanWith},
                { label: "入住登记制度说明",prop: 'rzdjzdBz',dicData:dictYesNo,span:spanWith},

                { label: "来访登记制度",prop: 'lfdjzd',dicData:dictYesNo,span:spanWith},
                { label: "来访登记制度说明",prop: 'lfdjzdBz',dicData:dictYesNo,span:spanWith},

                { label: "值班巡查制度",prop: 'zbxczd',dicData:dictYesNo,span:spanWith},
                { label: "值班巡查制度说明",prop: 'zbxczdBz',dicData:dictYesNo,span:spanWith},

                { label: "财务报告制度",prop: 'cwbgzd',dicData:dictYesNo,span:spanWith},
                { label: "财务报告制度说明",prop: 'cwbgzdBz',dicData:dictYesNo,span:spanWith},

                { label: "报告制度",prop: 'bgzd',dicData:dictYesNo,span:spanWith},
                { label: "报告制度说明",prop: 'bgzdBz',dicData:dictYesNo,span:spanWith},

                { label: "信息系统使用",prop: 'xxxt',dicData:dictYesNo,span:spanWith},
                { label: "信息系统使用说明",prop: 'xxxtBz',dicData:dictYesNo,span:spanWith},

                { label: "上传及时",prop: 'scjs',dicData:dictYesNo,span:spanWith},
                { label: "上传及时说明",prop: 'scjsBz',dicData:dictYesNo,span:spanWith},


                { label: "消防设备",prop: 'xfsbjhyq',dicData:dictYesNo,span:spanWith},
                { label: "消防设备说明",prop: 'xfsbjhyqBz',dicData:dictYesNo,span:spanWith},

                { label: "当天录入信息齐全",prop: 'dtlrxx',dicData:dictYesNo,span:spanWith},
                { label: "当天录入信息齐全说明",prop: 'dtlrxxBz',dicData:dictYesNo,span:spanWith},

                { label: "上传数量和录入数量",prop: 'scslylrslxf',dicData:dictYesNo,span:spanWith},
                { label: "上传数量和录入数量说明",prop: 'scslylrslxfBz',dicData:dictYesNo,span:spanWith},
            ]
        },
        {
            label: '检查信息',
            prop: 'check',
            icon: 'el-icon-info',
            column: [
                { label: "Ⅰ检查民警",prop: 'jcmjZh',span:spanWith},
                { label: "Ⅱ检查民警",prop: 'jcmjTwoZh',span:spanWith},
                { label:"检查单位", prop:"jcdwZh",span:spanWith},
                { label:"被检查单位", prop:"bjcdw",span:spanWith},
                { label:"检查时间", prop:"jcsj",
                    format: 'yyyy-MM-dd HH:mm',
                    type: 'datetime',
                    valueFormat: 'yyyy-MM-dd HH:mm',
                    span:spanWith
                },
            ],

        }
    ]
}

export const chOptionDetail = {
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
            label: '旅馆信息',
            prop: 'lgxx',
            icon: 'el-icon-info',
            column: [
                {label: '旅馆编码', prop: 'lgbm',},
                {label: '旅馆名称', prop: 'lgmc',},
                {label: '旅馆地址', prop: 'lgdz',},
            ]
        },
        {
            label: '旅客信息',
            prop: 'lkxx',
            icon: 'el-icon-info',
            column: [
                {label: '姓名', prop: 'xm'},
                {label: '性别',prop: 'xb'},
                {label: '民族', prop: 'mz'},

                {label: '出生日期', prop: 'csrq',},
                {label: '证件号码', prop: 'zjhm',},
                {label: '证件类型', prop: 'zjlx',},


                {label: '常住行政区划', prop: 'cz_xzqh',},
                {label: '常住地址', prop: 'cz_dzmc',},
                {label: '所属市', prop: 'sss',},


                {label: '入住房号', prop: 'rzfh',},
                {label: '入住时间', prop: 'rzsj',},
                {label: '退房时间', prop: 'tfsj',},

                {label: '备注', prop: 'bz', span: 12},
            ]
        }
    ]
}

export const foFormOption = {
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
            label: '旅馆信息',
            prop: 'lgxx',
            icon: 'el-icon-info',
            column: [
                {label: '旅馆编码', prop: 'lgbm',},
                {label: '旅馆名称', prop: 'lgmc',},
                {label: '旅馆地址', prop: 'lgdz',},
            ]
        },
        {
            label: '旅客信息',
            prop: 'lkxx',
            icon: 'el-icon-info',
            column: [
                {label: '外文姓', prop: 'wwx'},
                {label: '外文名', prop: 'wwm'},
                {label: '姓名', prop: 'xm'},
                {label: '性别',prop: 'xb'},
                {label: '国家和地区', prop: 'gjhdq'},
                {label: '出生日期', prop: 'csrq',},
                {label: '证件号码', prop: 'zjhm',},
                {label: '证件类型', prop: 'zjlxmc',},
                {label: '所属市', prop: 'sss',},
                {label: '入住房号', prop: 'rzfh',},
                {label: '入住时间', prop: 'rzsj',},
                {label: '退房时间', prop: 'tfsj',},
                {label: '备注', prop: 'bz', span: 12},
            ]
        },
        {
            label: '签证信息',
            prop: 'qzxx',
            icon: 'el-icon-info',
            column: [
                {label: '签证号码', prop: 'qzzjhm'},
                {label: '中国签证种类', prop: 'zgqzzl'},
                {label: '签发机关', prop: 'gajgjg'},
                {label: '签证过期时间',prop: 'qzgq_sj'},
            ]
        },
        {
            label: '入境信息',
            prop: 'rjxx',
            icon: 'el-icon-info',
            column: [
                {label: '入境日期', prop: 'rjrq'},
                {label: '入境口岸', prop: 'rjka'},
                {label: '入境身份', prop: 'crjrylbdm'},
                {label: '入境事由',prop: 'jwryrjsydm'},
                {label: '来源地',prop: 'lzy'},
                {label: '去往地',prop: 'qwd'},
            ]
        },
    ]
}

export const hkFormOption = {
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
            label: '旅馆信息',
            prop: 'lgxx',
            icon: 'el-icon-info',
            column: [
                {label: '旅馆编码', prop: 'lgbm',},
                {label: '旅馆名称', prop: 'lgmc',},
                {label: '旅馆地址', prop: 'lgdz',},
            ]
        },
        {
            label: '旅客信息',
            prop: 'lkxx',
            icon: 'el-icon-info',
            column: [
                {label: '外文姓', prop: 'wwx'},
                {label: '外文名', prop: 'wwm'},
                {label: '姓名', prop: 'xm'},
                {label: '性别',prop: 'xb'},
                {label: '国家和地区', prop: 'gjhdq'},
                {label: '出生日期', prop: 'csrq',},
                {label: '证件号码', prop: 'zjhm',},
                {label: '证件类型', prop: 'zjlxmc',},
                {label: '所属市', prop: 'sss',},
                {label: '入住房号', prop: 'rzfh',},
                {label: '入住时间', prop: 'rzsj',},
                {label: '退房时间', prop: 'tfsj',},
                {label: '备注', prop: 'bz', span: 12},
            ]
        },
        {
            label: '签证信息',
            prop: 'qzxx',
            icon: 'el-icon-info',
            column: [
                {label: '签证号码', prop: 'qzzjhm'},
                {label: '中国签证种类', prop: 'zgqzzl'},
                {label: '签发机关', prop: 'gajgjg'},
                {label: '签证过期时间',prop: 'qzgq_sj'},
            ]
        },
        {
            label: '入境信息',
            prop: 'rjxx',
            icon: 'el-icon-info',
            column: [
                {label: '入境日期', prop: 'rjrq'},
                {label: '入境口岸', prop: 'rjka'},
                {label: '入境身份', prop: 'crjrylbdm'},
                {label: '入境事由',prop: 'jwryrjsydm'},
                {label: '来源地',prop: 'lzy'},
                {label: '去往地',prop: 'qwd'},
            ]
        },
    ]
}
