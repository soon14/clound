
import { getDetails } from '@/api/admin/user';
import { cardid } from "@/util/validate";

var validateUsername = (rule, value, callback) => {
  getDetails(value).then(response => {
    if (window.boxType === 'edit') callback()
    let result = response.data.data
    if (result !== null) {
      callback(new Error('用户名已经存在'))
    } else {
      callback()
    }
  })
}
var validateUserIdCard = (rule,value,callback)=>{
  if(cardid(value)[0]){
    callback(new Error(cardid(value)[1]))
  }else {
    callback()
  }
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
  column: [{
    fixed: true,
    label: 'id',
    prop: 'userId',
    span: 12,
    hide: true,
    addDisplay:false,
    editDisplay: false,
  }, {
    fixed: true,
    label: '用户名',
    prop: 'username',
    editDisabled: true,
    solt: true,
    search: true,
    span: 12,
    rules: [{
      required: true,
      message: '请输入用户名'
    },
      {
        min: 3,
        max: 20,
        message: '长度在 3 到 20 个字符',
        trigger: 'blur'
      },
      { validator: validateUsername, trigger: 'blur' }
    ]
  },{
    fixed: true,
    label: '警号',
    prop: 'policeNumber',
    editDisabled: true,
    span: 12,
    rules: [{
      required: true,
      message: '请输入警号'
    },
      {
        min: 3,
        max: 20,
        message: '长度在 3 到 20 个字符',
        trigger: 'blur'
      },
    ]
  }, {
    label: '身份证',
    prop: 'idcard',
    type: 'idcard',
    hide: true,
    value: '',
    span: 12,
    rules: [{
      required: true,
      message: '请输入身份证号码'
    },
      { validator: validateUserIdCard, trigger: 'blur' }
      ]
  }, {
    label: '性别',
    prop: 'sex',
    type: 'select',
    hide: true,
    span: 12,
    rules: [{
      required: true,
      message: '请选择状态',
      trigger: 'blur'
    }],
    dicUrl: '/admin/dict/type/sex',
    dicData: 'sex'
  },{
    label: '密码',
    prop: 'password',
    type: 'password',
    value: '',
    hide: true,
    span: 12,
    rules: [{
      min: 6,
      max: 20,
      message: '长度在 6 到 20 个字符',
      trigger: 'blur'
    }]
  }, {
    label: '所属部门',
    prop: 'deptId',
    formslot: true,
    slot: true,
    span: 12,
    hide: true,
    rules: [{
      required: true,
      message: '请选择部门',
      trigger: 'blur'
    }]
  }, {
    label: '手机号',
    prop: 'phone',
    type: 'phone',
    value: '',
    span: 12,
    rules: [{
      min: 6,
      max: 20,
      message: '长度在 11 个字符',
      trigger: 'blur'
    }]
  }, {
    label: '角色',
    prop: 'role',
    formslot: true,
    slot: true,
    overHidden: true,
    span: 12,
    rules: [{
      required: true,
      message: '请选择角色',
      trigger: 'blur'
    }]
  }, {
    label: '状态',
    prop: 'lockFlag',
    type: 'select',
    solt: true,
    span: 12,
    rules: [{
      required: true,
      message: '请选择状态',
      trigger: 'blur'
    }],
    dicData: [{
      label: '有效',
      value: '0'
    }, {
      label: '锁定',
      value: '9'
    }]
  }, {
    width: 180,
    label: '创建时间',
    prop: 'createTime',
    type: 'datetime',
    format: 'yyyy-MM-dd HH:mm',
    valueFormat: 'yyyy-MM-dd HH:mm:ss',
    editDisabled: true,
    addDisplay: false,
    editDisplay:false,
    span: 12
  }]
}
