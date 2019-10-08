import {checkName} from "@/api/portal/metadata/standardfields/field";


let newVue = {
    obj: null
}
var validateName = (rule, value, callback) => {
    checkName(value,newVue.obj.tableId).then(response => {
        let result = response.data.data
        if (window.boxType === 'edit'){
            if (result.id===newVue.obj.editId) {
                callback()
            }else{
                callback(new Error('字段名已经存在'))
            }
        }
        if (result !== null) {
            callback(new Error('字段名已经存在'))
        } else {
            callback()
        }
    })
}
export const tableOption = {
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
  menuWidth:'180',
  dialogFullscreen: true,
  column: [{
    label: 'id',
    prop: 'id',
    span: 24,
    hide: true,
    viewDisplay:false,
    addDisplay:false,
    editDisplay: false,
  }, {
    label: '所属表',
    prop: 'tableId',
    hide:true,
    editDisabled: true,
    addDisabled:true,
    span: 24
  },{
    label: '名称',
    prop: 'name',
    search:true,
    span: 24,
    rules: [{
      required: true,
      message: '字段名不能为空',
      trigger: 'blur'
    },{ validator: validateName, trigger: 'blur' }
    ],
  }, {
    label: '中文名称',
    prop: 'cname',
    span: 24
  }, {
    label: '类型',
    prop: 'type',
    type: 'select',
    searchFilterable:true,
    span: 24,
    dicUrl: '/admin/dict/type/field_type',
    rules: [{
      required: true,
      message: '字段类型不能为空',
      trigger: 'blur'
    }],
  },{
    label: '长度',
    prop: 'length',
    span: 24
  },{
    label: '主键',
    prop: 'pk',
    type: 'radio',
    valueDefault: '1',
    dicData:[
      {
        label:'否',
        value:'1'
      },{
        label:'是',
        value:'0'
      }
    ],
    span: 24
},{
    label: '允许为空',
    prop: 'notNull',
    type: 'radio',
    span: 24,
    valueDefault: '1',
    dicData:[
      {
        label:'是',
        value:'1'
      },{
        label:'否',
        value:'0'
      }
    ]
  },{
    label: '描述',
    prop: 'remarks',
    span: 24
  },{
    label: '创建时间',
    prop: 'createDate',
    format: 'yyyy-MM-dd HH:mm',
    type: 'date',
    valueFormat: 'yyyy-MM-dd HH:mm:ss',
    editDisplay: false,
    addDisplay: false,
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
    label: '名称',
    prop: 'name',
  }, {
    label: '类型',
    prop: 'type',
    dicUrl: '/admin/dict/type/field_type',
  },{
    label: '长度',
    prop: 'length'
  },{
    label: '主键',
    prop: 'pk',
    dicData:[
      {
        label:'否',
        value:'1'
      },{
        label:'是',
        value:'0'
      }
    ]
  },{
    label: '允许为空',
    prop: 'notNull',
    dicData:[
      {
        label:'是',
        value:'1'
      },{
        label:'否',
        value:'0'
      }
    ]
  },{
    label: '描述',
    prop: 'remarks'
  }]
}
export { newVue }