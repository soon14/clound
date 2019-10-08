
export const tableOption =  {
  expandAll: false,
      expandLevel: 2,
      border: true,
      index: true,
      tree:true,
      editBtn: false,
      delBtn: false,
      align: 'center',
      addBtn: false,
      column: [
          {
            label: '部门名称',
            prop: 'name',
            align: 'left',
            width: 200,
            span:24,
              rules: [{
                  required: true,
                  message: '部门名称不能为空',
                  trigger: 'blur'
              }]
          },
          {
            label: '部门编码',
            prop: 'code',
            span:24,
              rules: [{
                  required: true,
                  message: '部门编码不能为空',
                  trigger: 'blur'
              }]
          },
          {
            label:'排序',
            prop: 'sort',
            type: 'number',
            span:24
          },
          {
            label: 'id',
            prop: 'id',
            hide:true,
            editDisplay:false,
            addDisplay:false,
          },
          {
            label: '上级部门',
            prop: 'parentId',
            span:24,
            formslot: true,
            hide:true,
            editDisabled:true
          }


  ]
}
