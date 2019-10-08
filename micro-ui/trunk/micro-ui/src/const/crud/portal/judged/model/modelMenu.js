import {checkUdId} from "../../../../../api/portal/judged/model/modelMenu";

/**
 * 校验udId是否存在
 */
let validateUdId = (rule, value, callback) => {
       if (window.boxType === 'edit') callback()
        checkUdId(value).then(response => {
                let result = response.data.data
                if (result) {
                    callback(new Error('该标识已被占用、请更换'))
                } else {
                    callback()
                }
        })
}

export const tableOption = {
    dialogWidth: '80%',
    dialogHeight: '80%',
    expandLevel: 2,
    border: true,
    index: true,
    tree: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    editBtn: false,
    delBtn: false,
    align: 'center',
    addBtn: false,
    viewBtn: false,
    menuWidth: '190',
    column: [
        {
            overHidden: true,
            label: '模型名称',
            prop: 'name',
            span: 12,
            search: 'true',
            align: 'left',
            rules: [{
                required: true,
                message: '模型名称不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: '父级模型',
            prop: 'parentId',
            span: 12,
            hide: true,
            formslot: true,
            slot: true,
            valueDefault: ''
        },
        {
            label: "模型标识",
            prop: "udId",
            editDisabled: true,
            slot: true,
            align: 'left',
            overHidden: true,
            rules: [
                {
                    required: true,
                    message: '指标标识不能为空',
                    trigger: 'blur'
                }, {
                    min: 3,
                    max: 32,
                    message: '长度在 3 到 32 个字符',
                    trigger: 'blur'
                }
                , {
                    pattern: /^\w+$/,
                    message: '只能由数字、字母、下划线组成',
                    trigger: 'blur'
                }, {
                    validator: validateUdId,
                    trigger: 'blur'
                }
            ],
        },
        {
            overHidden: true,
            label: '类型',
            prop: 'type',
            type: 'select',
            dicUrl: '/admin/dict/type/model_type',
            dicMethod: 'get',
            dicQuery: {},
            search: "true",
            span: 12,
            align: 'left',
            disabled: false,
            rules: [{
                required: true,
                message: '类型不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: '权限标识',
            prop: 'permission',
            span: 12,
            align: 'left',
        },
        {
            label: '排序',
            prop: 'sort',
            span: 12,
            type: 'number',
            align: 'left',
        },
        {
            label: '链接地址',
            prop: 'links',
            span: 12,
            overHidden:true,
            align: 'left',
            rules: [{
                required: true,
                message: '链接地址不能为空',
                trigger: 'blur'
            }, {
                pattern: /^[^\u4e00-\u9fa5]+$/,
                message: '链接地址不允许输入中文',
                trigger: 'blur'
            }]
        },
        {
            label: '打开方式',
            prop: 'openType',
            type: 'select',
            dicUrl: '/admin/dict/type/model_open_type',
            dicMethod: 'get',
            dicQuery: {},
            span: 12,
            overHidden: true,
            align: 'left',
            hide: true,
            rules: [{
                required: true,
                message: '模型打开方式不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: '简要信息',
            prop: 'remark',
            type: 'textarea',
            span: 24,
            overHidden: true,
            align: 'left',
            hide: true,
        },
        {
            label: '预案库',
            prop: 'planLibId',
            span: 24,
            formslot: true,
            overHidden: true,
            align: 'left',
            hide: true,
            addDisplay: false,
            editDisplay: false,
            viewDisplay: false
        },
        {
            label: '图标',
            prop: 'icon',
            type: 'upload',
            formslot:true,
            span: 24,
            overHidden: true,
            align: 'left',
            hide: true
        },
        {
            label: '是否显示',
            prop: 'showHide',
            type: 'radio',
            valueDefault: '0',
            dicUrl: '/admin/dict/type/yes_no',
            dicMethod: 'get',
            dicQuery: {},
            span: 6,
            overHidden: true,
            align: 'left'
        },
        {
            label: '推荐使用',
            prop: 'useBest',
            type: 'radio',
            valueDefault: '0',
            dicUrl: '/admin/dict/type/yes_no',
            dicMethod: 'get',
            dicQuery: {},
            span: 6,
            overHidden: true,
            align: 'left',
        },
        {
            label: '是否启用',
            prop: 'state',
            type: 'radio',
            valueDefault: '0',
            dicUrl: '/admin/dict/type/yes_no',
            dicMethod: 'get',
            dicQuery: {},
            span: 6,
            overHidden: true,
            align: 'left',
        }

    ]
}
