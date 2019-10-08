
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
  column: [{
    width: 150,
    label: '字典类型',
    prop: 'innerFlag',
    'search': true,
    rules: [{
      required: true,
      message: '请输入字典类型',
      trigger: 'blur',
      min: 1,
      max: 64,
    }]
  }, {
    label: '字典类型名称',
    prop: 'chineseName',
    search: true,
    rules: [{
      required: true,
      message: '请输入字典类型名称',
      trigger: 'blur'
    }]
  }, {
    label: '字典结构',
    prop: 'isTree',
    type: 'select',
    search: true,
    rules: [{
      required: true,
      message: '请输入字典结构',
      trigger: 'blur'
    }],
    dicUrl: '/admin/dict/type/DICTTYPE',
    dicMethod:'get',
    dicQuery:{},
  }, {
    label: '来源系统',
    prop: 'source',
    addDisplay: false
  }, {
    label: '备注',
    hide: true,
    prop: 'remarks'
  }, {
    label: '最后更新时间',
    prop: 'updateTime',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm',
    valueFormat: 'yyyy-MM-dd HH:mm:ss',
    readonly: true,
    addDisplay: false,
    editDisabled: true
  }]
}