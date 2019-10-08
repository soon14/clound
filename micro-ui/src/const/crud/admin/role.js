import {getRoleCode} from "@/api/admin/role";

var validateRoleCode = (rule, value, callback) => {
    getRoleCode(value).then(response => {
        if (window.boxType === 'edit') callback()
        let result = response.data.data
        if (result !== null) {
            callback(new Error('角色标识已经存在'))
        } else {
            callback()
        }
    })
}
export const tableOption = {
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    editBtn: false,
    delBtn: false,
    align: 'center',
    addBtn: false,
    viewBtn: true,
    column: [{
        label: '角色名称',
        prop: 'roleName',
        search: 'true',
        span: 12,
        rules: [{
            required: true,
            message: '角色名称不能为空',
            trigger: 'blur'
        },
            {
                min: 3,
                max: 20,
                message: '长度在 3 到 20 个字符',
                trigger: 'blur'
            }]
    }, {
        width: 120,
        label: '角色标识',
        prop: 'roleCode',
        span: 12,
        editDisabled: true,
        rules: [{
            required: true,
            message: '角色标识不能为空',
            trigger: 'blur'
        },
            {
                min: 3,
                max: 20,
                message: '长度在 3 到 20 个字符',
                trigger: 'blur'
            },
            { validator: validateRoleCode, trigger: 'blur' }
        ]
    },
        {
            width: 150,
            label: '角色描述',
            prop: 'roleDesc',
            overHidden: true,
            span: 12
        },

        {
            width: 120,
            label: '锁住',
            prop: 'lockUp',
            type: 'switch',
            tip: '锁住后只有创建者可进行删除或修改',
            valueDefault: '1',
            span: 12,
            rules: [{
                required: true,
                message: '该状态不能为空',
                trigger: 'blur'
            }
            ],
            dicUrl: '/admin/dict/type/yes_no',
            dicMethod: 'get',
            dicQuery: {}
        } ,
        {
            width: 180,
            label: '数据权限',
            prop: 'dsType',
            formslot: true,
            slot: true,
            span: 24,
            rules: [{
                required: true,
                message: '请选择数据权限类型',
                trigger: 'blur'
            }]

        }, {
            label: '',
            prop: 'dsScope',
            formslot: true,
            showColumn: false,
            hide: true
        }, {
            label: '创建时间',
            prop: 'createTime',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            valueFormat: 'yyyy-MM-dd HH:mm:ss',
            editDisplay: false,
            addDisplay: false,
            span: 24
        }]
}
