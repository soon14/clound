
export const tableOption = {
  dialogWidth: '60%',
  dialogHeight: '60%',
  dialogFullscreen: false,
  border: true,
  index: true,
  indexLabel: '序号',
  stripe: true,
  menuAlign: 'center',
  menuWidth: 200,
  align: 'center',
  addBtn: true,
  editBtn: false,
  delBtn: false,
  viewBtn: true,
  column: [{
    label: '参数键值',
    prop: 'parameterKey',
    search: true,
    span:24,
    rules: [{
      required: true,
      message: '参数键值不能为空',
      trigger: 'blur'
    }
    ]
  }, {
    label: '参数数值',
    prop: 'value',
    span:24,
    rules: [{
      required: true,
      message: '参数数值不能为空',
      trigger: 'blur'
    }
    ]
  }, {
    label: '备注',
    prop: 'remarks',
    type: 'textarea',
    span:24
  }, {
    label: '创建时间',
    prop: 'createDate',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm',
    valueFormat: 'yyyy-MM-dd HH:mm:ss',
    addDisplay:false,
    viewDisplay: false,
    editDisplay:false
  }
  ]
}
