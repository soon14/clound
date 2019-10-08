
export const tableOption = {
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    showClomnuBtn: false,
    searchSize: 'mini',
    refreshBtn: true,
    editBtn: false,
    delBtn: false,
    addBtn: false,
    column: [{
        width: 150,
        label: '文件名',
        prop: 'fileName'
    }, {
        label: '文件类型',
        prop: 'extName'
    }, {
        label: '文件大小',
        prop: 'fileSize'
    }, {
        label: '上传时间',
        prop: 'createTime'
    }]
}