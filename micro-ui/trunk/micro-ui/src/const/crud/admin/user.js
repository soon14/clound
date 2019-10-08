
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
  dialogWidth: '70%',
  dialogHeight: '100%',
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
      }, {
        pattern: /^[a-zA-Z0-9_-]{3,20}$/,
        message: '只能由数字、字母、下划线组成',
        trigger: 'blur'
      },

      { validator: validateUsername, trigger: 'blur' }
    ]
  }, {
      fixed: true,
      label: '姓名',
      prop: 'name',
      search: true,
      span: 12,
      rules: [{
        required: true,
        message: '请输入姓名'
      }, {
        pattern: /^[\u4E00-\u9FA5\uf900-\ufa2d·s]+|([a-zA-Z]+\s?)$/,
        message: '姓名输入不规范',
        trigger: 'blur'
      }, {
        pattern: /^\S*$/,
        message: '姓名不能有空格',
        trigger: 'blur'
      },
        {
          min: 2,
          max: 40,
          message: '长度在 2 到 40 个字符',
          trigger: 'blur'
        },
        { validator: validateUsername, trigger: 'blur' }
      ]
    },

    {
    fixed: true,
    label: '警号',
    prop: 'policeNumber',
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

