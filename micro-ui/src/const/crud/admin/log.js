
export const tableOption = {
  border: true,
  index: true,
  indexLabel: '序号',
  stripe: true,
  menuAlign: 'center',
  menuWidth: 150,
  align: 'center',
  refreshBtn: true,
  showClomnuBtn: false,
  searchSize: 'mini',
  addBtn: false,
  editBtn: false,
  delBtn: false,
  viewBtn: true,
  props: {
    label: 'label',
    value: 'value'
  },
  column: [{
    label: '类型',
    prop: 'type',
    type: 'select',
    dicUrl: '/admin/dict/type/log_type',
    dicMethod:'get',
    dicQuery:{},
    search: true,
    field: 'type',
    sortable: true
  }, {
    label: '标题',
    prop: 'title'
  }, {
    label: 'IP地址',
    prop: 'remoteAddr',
    sortable: true
  }, {
    label: '用户名',
    prop: 'userName',
    slot: true,
    viewDisplay: false


  }, {
    label: '警号',
    prop: 'policeNumber',

    slot: true,
    viewDisplay: false

  }, {
    label: '请求方式',
    prop: 'method'
  },{
    label: '请求参数',
    prop: 'params',
    overHidden:'true'
  }, {
    label: '客户端',
    prop: 'serviceId'
  }, {
    width: 80,
    label: '请求时间',
    prop: 'time'
  }, {
    width: 150,
    label: '创建时间',
    prop: 'createTime',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm',
    valueFormat: 'yyyy-MM-dd HH:mm:ss'
  }]
}
