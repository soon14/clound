export const tableOption = {
    dialogFullscreen: true,
    dialogHeight: '100%',
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    addBtn: false,
    editBtn:false,
    viewBtn: false,
    delBtn: false,
    submitBtn: true,
    emptyBtn: true,
    column: [
        {
            label: "评定名称",
            prop: "name",
            span:24,
            search: true,
            rules: [{
                required: true,
                message: '等级评定名称不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "适用场所",
            prop: "placeType",
            dicUrl: '/admin/dict/tree',
            type:"tree",
            dicMethod: 'get',
            dicQuery: {type:'HYLX_DL'},
            search: true,
            rules: [{
                required: true,
                message: '适用场所不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "适用地区",
            prop: "cityCode",
            dicUrl: '/portal/area/level/tree',
            type:"tree",
            dicMethod: 'get',
            search:true,
            dicQuery: {
                level:2
            },
            props: {
                label: "name",
                value: 'code'
            },
            rules: [{
                required: true,
                message: '适用地区不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "通用",
            prop: "universal",
            type:'select',
            search:true,
            dicUrl: '/admin/dict/type/yes_no',
            dicMethod: 'get',
            dicQuery: {},
            placeholder:'请选择 是否通用',
            rules: [{
                required: true,
                message: '是否通用不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "状态",
            prop: "status",
            type:'select',
            search:true,
            dicUrl: '/admin/dict/type/point_status',
            dicMethod: 'get',
            dicQuery: {},
            slot:true,
            editDisabled: true,
            rules: [{
                required: true,
                message: '状态不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "创建人",
            prop: "creator",
            addDisplay: false,
            editDisplay: false,
            viewDisplay: false,
        },
        {
            label: "更新时间",
            prop: "updateTime",
            addDisplay: false,
            editDisplay: false,
            viewDisplay:false,
            overHidden:true
        },
        {
            label:'评定等级',
            hide:true,
            formslot: true,
            slot: true,
            prop:'levelChild',
            span:24,
        }
    ]
}

export const childOption = {
    addBtn:false,
    editBtn:false,
    addRowBtn:true,
    cancelBtn:false,
    delBtn:false,
    cellBtn:false,
    refreshBtn:false,
    columnBtn:false,
    filterBtn:false,
    border:true,
    menuWidth:80,
    column: [
        {
            label: '评定方案id',
            prop: 'levelId',
            hide: true,
        },
        {
            label: '等级',
            prop: 'type',
            dicUrl: '/admin/dict/type/jfdj',
            type:"select",
            dicMethod: 'get',
            cell: true,
            align: 'center',
        },
        {
            label: '链接类型',
            prop: 'linkType',
            type:"select",
            cell: true,
            align: 'center',
            slot:true,
           /* dicUrl: '/admin/dict/type/point_level_link_type',
            dicMethod: 'get',
            dicQuery: {},*/
        },
        {
            label: '分值',
            prop: 'score',
            type:'number',
            cell: true,
            align: 'center',
            slot:true,
        },
        {
            label: '说明',
            prop: 'remark',
            cell: true,
            align: 'center',
        }
    ]
}
