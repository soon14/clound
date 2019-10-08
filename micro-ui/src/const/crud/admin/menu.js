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
    menuWidth:'300',
    column: [

        {
        overHidden:true,
        label: '名称',
        prop: 'name',
        span: 12,
        search:'true',
        minWidth:'150',
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
            width: 120,
            label: '权限标识',
            prop: 'permission',
            span: 12

        },
        {
            width: 180,
            label: '图标',
            prop: 'icon',
            slot: true,
            span: 12
        },
        {
            label: '类型',
            prop: 'type',
            span: 12,
            type:'select',
            dicUrl: '/admin/dict/type/menu_type',
            dicMethod: 'get',
            dicQuery: {},
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
            rules: [{
                required: true,
                message: '请输入排序',
                trigger: 'blur'
            }]
        },
        {
            label: '前端组件',
            prop: 'component',
            span: 12,
            overHidden:true,
            rules: [{
                required: true,
                message: '前端组件不能为空',
                trigger: 'blur'
            }
            ]
        }, {
            label: '前端地址',
            prop: 'path',
            span: 12,
            rules: [{
                required: true,
                message: '前端地址不能为空',
                trigger: 'blur'
            }
            ]
        }
        , {
            label: '路由缓冲',
            prop: 'keepAlive',
            type:'switch',
            span: 12,
            hide: true,
            dicUrl: '/admin/dict/type/route_buffer',
            dicMethod: 'get',
            dicQuery: {},
            valueDefault: '1'
        }


        ]
}
