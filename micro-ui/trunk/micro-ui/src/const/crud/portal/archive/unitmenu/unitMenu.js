export const tableOption = {
    expandLevel: 1,
    border: true,
    index: true,
    tree:true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    editBtn: false,
    delBtn: false,
    align: 'center',
    addBtn: false,
    viewBtn: true,
    menuWidth:'190',
    column: [
        {
        overHidden:true,
        label: '名称',
        prop: 'name',
        span: 12,
        search:'true',
        minWidth:'120',
        align:'left',
        rules: [{
            required: true,
            message: '名称不能为空',
            trigger: 'blur'
        }
        ]
    },
        {
            label: '父级菜单',
            prop: 'parentId',
            span: 12,
            hide: true,
            formslot: true,
            slot: true,
            valueDefault:''
        },
        {
            label: '类型',
            prop: 'type',
            width: 120,
            span: 12,
            type:'select',
            dicUrl: '/admin/dict/type/unit_source_type',
            dicMethod: 'get',
            dicQuery: {},
            overHidden:true,
            rules: [{
                required: true,
                message: '类型不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: '排序',
            prop: 'sort',
            span: 12,
            type: 'number',
            hide:true,
            rules: [{
                required: true,
                message: '请输入排序',
                trigger: 'blur'
            }]
        },
        {
            width: 120,
            label: '权限字段',
            prop: 'dataScope',
            span: 12,
            overHidden:true,
            rules: [{
                required: true,
                message: '权限字段不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            width: 120,
            label: 'BEAN名称',
            prop: 'beanName',
            labelWidth: 100,
            overHidden:true,
            span: 12,
            rules: [{
                required: true,
                message: 'BEAN名称不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: '链接地址',
            prop: 'links',
            overHidden:true,
            span: 12,
            rules: [{
                required: true,
                message: '链接地址不能为空',
                trigger: 'blur'
            }]
        }, {
            label: '图标',
            prop: 'icon',
            slot: true,
            overHidden:true,
            span: 12
        },{
            label: '是否显示',
            prop: 'showHide',
            type: 'radio',
            valueDefault: '0',
            span: 24,
            dicData:[{
                label:'否',
                value:'1'
            },{
                label:'是',
                value:'0'
            }],
        }

        ]
}
