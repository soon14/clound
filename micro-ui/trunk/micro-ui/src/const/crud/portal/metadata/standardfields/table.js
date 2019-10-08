import {checkName} from "@/api/portal/metadata/standardfields/table";

let newVue = {
  obj: null
}
var validateName = (rule, value, callback) => {
    checkName(value,newVue.obj.tempDbId).then(response => {
      alert(newVue.obj.tempDbId)
      let result = response.data.data
      if (window.boxType === 'edit'){
        if (result.id===newVue.obj.editId) {
          callback()
        }else{
          callback(new Error('表名已经存在'))
        }
      }
        if (result !== null) {
            callback(new Error('表名已经存在'))
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
  menuWidth:'180',
  column: [{
    label: 'id',
    prop: 'id',
    span: 24,
    hide: true,
    viewDisplay:false,
    addDisplay:false,
    editDisplay: false,
  }, {
    label: '来源库',
    prop: 'dbId',
    slot: true,
    type:'select',
    editDisabled: true,
    span: 24
  },{
    label: '英文名称',
    prop: 'name',
    search:true,
    span: 24,
    rules: [{
      required: true,
      message: '表名不能为空',
      trigger: 'blur'
    },{ validator: validateName, trigger: 'blur' }
    ],
  }, {
    label: '中文名称',
    prop: 'cname',
    overHidden: true,
    span: 24
  }, {
    label: '类型',
    prop: 'type',
    type: 'select',
    span: 24,
    dicUrl: '/admin/dict/type/table_type',
    editDisabled: true,
    rules: [{
      required: true,
      message: '类型不能为空',
      trigger: 'blur'
    }]
  },{
    label: '描述',
    prop: 'remarks',
    hide: true,
    span: 24
  }, {
    label: '创建时间',
    prop: 'createDate',
    format: 'yyyy-MM-dd HH:mm',
    type: 'date',
    valueFormat: 'yyyy-MM-dd HH:mm:ss',
    editDisplay: false,
    addDisplay: false,
    overHidden: true,
    span: 24
  }]
}
export const preViewOption = {
  selection: true,
  border: true,
  index: true,
  indexLabel: '序号',
  stripe: true,
  menuAlign: 'center',
  editBtn: false,
  delBtn: false,
  align: 'center',
  addBtn: false,
  viewBtn: false,
  menu:false,
  column: [{
    label: '表名',
    prop: 'name',
    search:true
  }, {
    label: '类型',
    prop: 'type',
    type: 'select',
    dicUrl: '/admin/dict/type/table_type'
  },{
    label: '描述',
    prop: 'remarks',
    span: 24
  }]
}
export { newVue }

