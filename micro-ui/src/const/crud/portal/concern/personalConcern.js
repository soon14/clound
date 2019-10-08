
import { getOneByidCard } from '@/api/portal/concern/personalConcern';


let newVue = {
    obj: null
}







var validateIdCard = (rule, value, callback) => {

    getOneByidCard(value).then(response => {
        let result = response.data.data

        if (window.boxType === 'edit'){
            if (result.id==newVue.obj.editId) {
                callback()
            }else{
                callback(new Error('证件号码已经存console.log(result);在'))
            }
        }
        if (result !== null) {
            callback(new Error('证件号码已经存console.log(result);在'))
        } else {
            callback()
        }
    })
}

export const tableOption = {
  border: true,
  /*selection: true,*/
  stripe: true,
  menuAlign: 'center',
  align: 'center',
  editBtn: false,
  delBtn: false,
  addBtn: false,
  viewBtn:true,
  column: [
      {
          label: 'ID',
          prop: 'id',
          hide:true,
          display:false,
      },
      {
      label: '名单来源',
      prop: 'listSource',
      type: 'select',
      overHidden: true,
      valueDefault:'01',
      addDisabled:true,
      editDisabled:true,
      dicUrl: '/admin/dict/type/LIST_SOURCE',
      dicMethod: 'get',
      dicQuery: {}
      },
	  {
      label: '被关注人姓名',
      prop: 'name',
      search:'true',
      width: 150,
      overHidden: true,
      rules: [{
          required: true,
          message: '请输入被关注人的姓名',
          trigger: 'blur'
      }]


    },
      {
          label: '人员类型',
          prop: 'peopleType',
          search:'true',
          type: 'select',
          overHidden: true,
          dicUrl: '/admin/dict/type/rylx',
          dicMethod: 'get',
          dicQuery: {},
          rules: [{
              required: true,
              message: '请选择人员类型',
              trigger: 'blur'
          }]
      },
      {
          label: '证件类型',
          prop: 'cardType',
          search:'true',
          overHidden: true,
          type: 'select',
          dicUrl: '/admin/dict/type/CARD_TYPE',
          dicMethod: 'get',
          dicQuery: {},
          rules: [{
              required: true,
              message: '请选择证件类型',
              trigger: 'blur',
          }

          ]

      },
	  {
      label: '证件号码',
      prop: 'idCard',
      search:'true',
      overHidden: true,
      rules: [{
          required: true,
          message: '请输入证件号码',
          trigger: 'blur'
      },{ validator: validateIdCard, trigger: 'blur' }

      ]
    },
      {
          label: '关注开始时间',
          prop: 'startTime',
          type: 'datetime',
          format: 'yyyy-MM-dd HH:mm',
          valueFormat: 'yyyy-MM-dd HH:mm:ss',
          overHidden: true,
          startPlaceholder:"sssss",
          width: 150,
          rules: [{
              required: true,
              message: '请选择关注开始时间',
              trigger: 'blur'
          }]
      },
      {
          label: '关注结束时间',
          prop: 'endTime',
          type: 'datetime',
          format: 'yyyy-MM-dd HH:mm',
          valueFormat: 'yyyy-MM-dd HH:mm:ss',
          endPlaceholder:"ddddd",
          overHidden: true,
          width: 150,
          rules: [{
              required: true,
              message: '请选择关注结束时间',
              trigger: 'blur'
          }]
      },
      {
          label: '结果推送渠道',
          prop: 'channel',
          overHidden: true,
          type: 'select',
          width: 150,
          dicUrl: '/admin/dict/type/PUSH_CHANNEL',
          dicMethod: 'get',
          dicQuery: {},
          rules: [{
              required: true,
              message: '请选择结果推送渠道',
              trigger: 'blur'
          }]
      },
      {
          label: '更新时间',
          prop: 'updateDate',
          type: 'datetime',
          format: 'yyyy-MM-dd HH:mm',
          valueFormat: 'yyyy-MM-dd HH:mm:ss',
          overHidden: true,
          width: 150,
      }
  ]
}

export { newVue }




