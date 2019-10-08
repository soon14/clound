export const tableOption = {
    selection: false,
    filterBtn: false,
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    refreshBtn: true,
    showClomnuBtn: true,
    searchSize: 'mini',
    editBtn: false,
    delBtn: false,
    expandAll: false,
    expandLevel: 2,
    tree: true,
    addBtn: false,
    addRowBtn: false,
    column: [
        {
            width: 150,
            label: '数据值',
            prop: 'value',
            rules: [{
                required: true,
                message: '请输入数据值',
                trigger: 'blur'
            }]
        }, {
            label: '标签名',
            prop: 'label',
            rules: [{
                required: true,
                message: '请输入标签名',
                trigger: 'blur'
            }]
        }, {
            label: '类型',
            prop: 'type',
            //'search': true,
            readonly: true,
            valueDefault: '',
            rules: [{
                required: true,
                message: '请输入字典类型',
                trigger: 'blur'
            }]
        }, {
            label: '描述',
            prop: 'description',
            rules: [{
                required: true,
                message: '请输入字典描述',
                trigger: 'blur'
            }]
        }, {
            label: '排序',
            prop: 'sort',
            type: 'number',
            rules: [{
                required: true,
                message: '请输入排序',
                trigger: 'blur'
            }]
        }, {
            label: '备注信息',
            prop: 'remarks'
        },{
            label: '父id',
            prop: 'parentId',
            valueDefault: '0',
            readonly: true,
            addDisplay: false,
            editDisplay: true,
            hide: true
        } ,{
            label: '父ids',
            prop: 'parentIds',
            valueDefault: '0',
            readonly: true,
            addDisplay: false,
            editDisplay: true,
            hide: true
        }
    ]
}