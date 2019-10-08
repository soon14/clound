<template>
  <div class="app-container calendar-list-container">
    <basic-container>

      <avue-crud  :option="option"  ref="crud" :data="treeData" v-model="form">
        <template slot="icon" slot-scope="scope">
          <i v-if="scope.row.icon" :class="scope.row.icon" style="font-size:24px"></i>
        </template>

        <template slot="parentIdForm" slot-scope="scope">
          <avue-input v-model="form.parentId"
                      type="tree"
                      placeholder="父级模型"
                      :dic="dicTreeData"
                      :props="dicTreeProps">
          </avue-input>
        </template>
        <template slot="menu"
                  slot-scope="scope" >
          <el-button size="mini"
                     type="text"
                     icon="el-icon-plus"
                     v-if="model_btn_add"
                     @click="handAdd(scope.row)">添加子级
          </el-button>
            <el-button size="mini"
                       type="text"
                       icon="el-icon-edit"
                       v-if="model_btn_edit"
                       @click="handleEdit(scope.row,scope.index)">编辑
            </el-button>
            <el-button size="mini"
                       type="text"
                       icon="el-icon-delete"
                       v-if="model_btn_del"
                       @click="handleDelete(scope.row)">删除
            </el-button>

        </template>
      </avue-crud>


    </basic-container>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  let dict={
    isLook:[
      {"label":"是","value":"0"},
      {"label":"否","value":"1"}
    ]

  }
  export default {
    name: 'model',
    data() {
      return {
        model_btn_edit:true,
        model_btn_del:true,
        model_btn_add:true,
        checkedKeys:[],
        dicTreeData:[
          {
            id: 'a',
            name: '场所涉黄',
            children: [
              {
                id: 'b',
                name: '涉黄人员聚集'
              },
              {
                id: 'j',
                name: '涉黄人员场所预警'
              }
            ]
          },
          {
            id: 'l',
            name: '场所涉毒'
          }
        ],
        form: {
          menuId:''
        },
        dicTreeProps: {
          label: "name",
          value: 'id'
        },
        treeData:[
          {
            id: 'a',
            name:'场所涉黄',
            type: 13,
            createDate: '2019-03-27',
            version:'1',
            permission:'',
            condition: '',
            remarks:'场所涉黄分析模型',
            connect:'',
            icon:'',
            sort:'1',
            isLook:'0',
            children: [
              {
                id: 'b',
                name:'涉黄人员聚集',
                type: 13,
                createDate: '2019-03-28',
                version:'1',
                permission:'',
                condition: '',
                remarks:'涉黄人员聚集分析',
                connect:'',
                icon:'',
                sort:'1',
                isLook:'0',
                parentId:'a',
                children: [
                  {
                    parentId:'b',
                    id: 'c',
                    name:'疑似涉黄旅业场所预警',
                    type: 13,
                    createDate: '2019-03-28',
                    version:'1',
                    permission:'',
                    condition: '',
                    remarks:'疑似涉黄旅业场所预警',
                    connect:'',
                    icon:'',
                    sort:'10',
                    isLook:'0',
                  },
                  {
                    parentId:'b',
                    id: 'd',
                    name:'高纯男房率的旅业预警',
                    type: 13,
                    createDate: '2019-03-28',
                    version:'1',
                    permission:'',
                    condition: '',
                    remarks:'高纯男房率的旅业预警',
                    connect:'',
                    icon:'',
                    sort:'20',
                    isLook:'0',
                  },
                  {
                    parentId:'b',
                    id: 'e',
                    name:'未/少入住房间分析模型',
                    type: 13,
                    createDate: '2019-03-28',
                    version:'1',
                    permission:'',
                    condition: '',
                    remarks:'未/少入住房间分析模型',
                    connect:'',
                    icon:'',
                    sort:'30',
                    isLook:'0',
                  }
                ]
              },
              {
                id: 'j',
                name:'涉黄场所预警',
                type: 13,
                createDate: '2019-03-28',
                version:'1',
                permission:'',
                condition: '',
                remarks:'涉黄预警',
                connect:'',
                icon:'',
                sort:'2',
                isLook:'0',
                parentId:'a',
                children:[
                  {
                    parentId:'j',
                    id: 'k',
                    type: 13,
                    name:'涉黄场所预警',
                    createDate: '2019-03-28',
                    version:'1',
                    permission:'',
                    condition: '',
                    remarks:'涉黄场所预警',
                    connect:'',
                    icon:'',
                    sort:'3',
                    isLook:'0',
                  }
                ]
              }
            ]
          },
          {
            id: 'l',
            name:'场所涉毒',
            type: 11,
            createDate: '2019-03-27',
            version:'1',
            permission:'',
            condition: '',
            remarks:'场所涉毒分析模型',
            connect:'',
            icon:'',
            sort:'2',
            isLook:'0',
            children:[{
              parentId:'l',
              id: 'm',
              name:'涉毒人员聚集',
              type: 11,
              createDate: '2019-03-28',
              version:'1',
              permission:'',
              condition: '',
              remarks:'涉毒人员聚集分析',
              connect:'',
              icon:'',
              sort:'1',
              isLook:'0',
            }]
          }
        ],
        option:{
          dialogWidth: '100%',
          dialogHeight: '100%',
          dialogFullscreen: true,
          expandLevel: 1,
          border: true,
          index: true,
          tree:true,
          indexLabel: '序号',
          stripe: true,
          menuAlign: 'center',
          editBtn: false,
          delBtn: false,
          align: 'center',
          addBtn: true,
          viewBtn: true,
          menuWidth:'300',
          column: [
            {
              overHidden:true,
              label: '模型名称',
              prop: 'name',
              minWidth:'150',
              align: 'left',
              span: 12,
              search:'true',
              rules: [{
                required: true,
                message: '名称不能为空',
                trigger: 'blur'
              }
              ]
            },
            {
              label: '模型分类',
              prop: 'type',
              span: 12,
              type:'tree',
              align: 'left',
              search:true,
              width:120,
              props:{
                label:'label',
                value:'value'
              },
              dicData: [
                {
                  label:'旅馆业',
                  value:1,
                  children:[
                    {
                      label:'聚纵吸毒',
                      value:11,
                    },{
                      label:'非法聚赌',
                      value:12,
                    } ,{
                      label:'非法涉黄',
                      value:13,
                    }
                  ]
                },{
                  label:'机修业',
                  value:2,
                  children:[
                    {
                      label:'盗抢车辆维修',
                      value:21,
                    },{
                      label:'频繁维修',
                      value:22,
                    }
                  ]
                }],
              rules: [{
                required: true,
                message: '模型分类不能为空',
                trigger: 'blur'
              }
              ]
            },
            {
              label: '父级模型',
              prop: 'parentId',
              align: 'left',
              span: 12,
              hide: true,
              formslot: true,
              slot: true
            },
            {
              label: '排序',
              align: 'left',
              prop: 'sort',
              sortable:true,
              span: 12,
              type: 'number',
              rules: [{
                required: true,
                message: '请输入排序',
                trigger: 'blur'
              }]
            },
            {
              label: '链接',
              prop: 'connect',
              span: 12,
              align: 'left',
              overHidden:true,
              hide:true
            },
            {
              label: '图标',
              prop: 'icon',
              slot: true,
              align: 'left',
              span: 12,
              hide:true,
            },
            {
              label: '模型版本',
              prop: 'version',
              span: 12,
              align: 'left',
              hide: true
            },
            {
              label: '使用范围',
              placeholder:'权限标识',
              prop: 'permission',
              align: 'left',
              span: 12,
              hide:true
            },
            {
              label: '限制条件',
              prop: 'condition',
              type:'textarea',
              span: 24,
              align: 'left',
              hide:true
            },
            {
              label: '使用说明',
              prop: 'remarks',
              type:'textarea',
              align: 'left',
              span: 24
            },
       {
         label: '是否可见',
               prop: 'isLook',
               type: 'radio',
               valueDefault:'0',
               align: 'left',
               dicData:dict.isLook,
               rules: [{
                 required: true,
                 message: '请选择是否可见',
                 trigger: 'blur'
                }]
       }

          ]
        },
      }
    },
    created() {
    },
    computed: {
      ...mapGetters([
        'elements',
        'permissions'
      ])
    },
    methods: {
      handAdd: function (row) {
        this.form.parentId=row.id
        this.$refs.crud.rowAdd()
      },
      handleEdit(row, index) {
        this.$refs.crud.rowEdit(row, index)
      },

      handleDelete(row) {
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delObj(row.id).then(() => {
            this.getList()
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success',
              duration: 2000
            })
          })
        })
      },

      handleUpdate(row, index, done, loading) {
        this.form.menuId=row.id
        putObj(this.form).then(() => {
          this.getList()
          done();
          this.$notify({
            title: '成功',
            message: '更新成功',
            type: 'success',
            duration: 2000
          })
        }).catch(() => {
          loading();
        });
      },
      handleSave(row, done, loading) {
        addObj(this.form).then(() => {
          this.getList()
          done();
          this.$notify({
            title: '成功',
            message: '创建成功',
            type: 'success',
            duration: 2000
          }).catch(() => {
            loading();
          });
        })
      }


    }
  }
</script>

