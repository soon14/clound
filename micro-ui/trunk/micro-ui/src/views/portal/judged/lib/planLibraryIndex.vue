<template>
  <div class="app-container calendar-list-container">
    <basic-container>


      <avue-crud  ref="crud"
                  :option="option"
                  :data="treeData"
                  :page="page"
                  @search-change="searchChange"
                  @refresh-change="handleFilter"
                  @row-update="handleUpdate"
                  @row-save="handleSave"
                  @row-del="handleDelete"
                  :table-loading="listLoading"
                  v-model="form"
                  @on-load="onLoad"
      >
        <template slot-scope="scope" slot="menuBtn">
           <el-dropdown-item divided
                             @click.native="handleDelete(scope.row)">删 除
           </el-dropdown-item>
         </template>
          <template slot="describeForm"
                    slot-scope="scope">
              <avue-ueditor v-model="form.describe" :min-rows="8"></avue-ueditor>
          </template>
      </avue-crud>


    </basic-container>
  </div>
</template>

<script>
  import {addObj, delObj, fetchList, putObj} from '@/api/portal/judged/lib/PlanLibrary'
  import {mapGetters} from 'vuex'
  import avueUeditor from 'avue-plugin-ueditor/packages';
  Vue.use(avueUeditor);
  export default {
    name: 'unitMenu',
    data() {
      return {
        list: null,
        total: null,
        formEdit: true,
        formAdd: true,
        page: {
          total: 0, // 总页数
          currentPage: 1, // 当前页数
          pageSize: 10, // 每页显示多少条,
        },
        option: {
            dialogFullscreen: true,
            dialogHeight:'100%',
            border: true,
            index: true,
            delBtn:false,
            indexLabel: '序号',
            stripe: true,
            menuAlign: 'center',
            editBtn: true,
            menuType:'menu',
            align: 'center',
            addBtn: true,
            viewBtn:true,
            menuWidth:'120',
            column: [
                {
                    label: '名称',
                    prop: 'name',
                    search: true,
                    rules: [{
                        required: true,
                        message: "请输入名称",
                        trigger: "blur"
                    }]
                },
                {
                    label: '指令类型',
                    prop: 'type',
                    type: 'select',
                    search:true,
                    dicData: [
                        { label: '人工指令', value: "0" },
                        { label: '研判指令', value: "1" }
                    ],
                    rules:[
                        {
                            required: true,
                            message: "请输入指令类型",
                            trigger: "blur"
                        },
                    ]
                },
                {
                    label: '场所类型',
                    prop: 'placeType',
                    type: 'tree',
                    search:true,
                    dicUrl: '/admin/dict/tree',
                    dicMethod: 'get',
                    expandLevel: 1,
                    dicQuery: {type:'HYLX_DL'},
                    rules:[
                        {
                            required: true,
                            message: "请输入适用场所类型",
                            trigger: "blur"
                        },
                    ]
                },
                {
                    label:"所属区划",
                    prop:'cityCode',
                    type:'tree',
                    expandLevel: 1,
                    search:true,
                    searchDefault:'',
                    dicUrl: '/portal/area/areaData',
                    dicMethod: 'get',
                    dicQuery: {},
                    props:{
                        label: "name",
                        value: 'code'
                    },
                    rules:[
                        {
                            required: true,
                            message: "请输入所属行政区划",
                            trigger: "blur"
                        },
                    ]
                },
                {
                    label: '内容描述',
                    prop: 'describe',
                    span:24,
                    slot:true,
                    formslot:true,
                    hide:true,
                    rules:[
                        {
                            required: true,
                            message: "请输入预案内容描述",
                            trigger: "blur"
                        },
                    ]
                },
                {
                    label: '创建时间',
                    prop: 'createTime',
                    type: "datetime",
                    format: 'yyyy-MM-dd',
                    valueFormat: 'yyyy-MM-dd',
                    addDisplay:false,
                    editDisplay:false,
                },
                {
                    label: '状态',
                    prop: 'status',
                    type: 'select',
                    dicData: [{ label: '启用', value: '0' }, { label: '停用', value: '1' }],
                    search: true,
                    rules:[
                        {
                            required: true,
                            message: "请输入状态",
                            trigger: "blur"
                        },
                    ]
                 },
            ]
        },
        listLoading: true,
        listQuery: {
            name: undefined,
            type: undefined,
            placeType: undefined,
            cityCode: undefined,
            status: undefined,
        },
        checkedKeys:[],
        treeData: [],
        dicTreeData:[],
        form: {
          id:''
        },
        dicTreeProps: {
          label: "name",
          value: 'id',
        },
        unitMenu_btn_add: false,
        unitMenu_btn_edit: false,
        unitMenu_btn_del: false
      }
    },
    created() {
      this.getList(this.page)
      this.unitMenu_btn_add = this.permissions['unit_menu_add']
      this.unitMenu_btn_edit = this.permissions['unit_menu_edit']
      this.unitMenu_btn_del = this.permissions['unit_menu_del']
    },
    computed: {
      ...mapGetters([
          'elements',
          'permissions',
          "userInfo",
          "userDept"
      ])
    },
    methods: {
      getList(page) {
          this.listLoading = true
        fetchList(Object.assign({
            current: page.currentPage,
            size: page.pageSize
        }, this.listQuery)).then(response => {
          this.treeData = response.data.data.records
            this.page.total = response.data.data.total
            this.listLoading = false
        })
      },
      getDicTreeData(){
          if(this.treeData){
          }

      },
      searchChange:function(param){
          this.listQuery.name=param.name
          this.listQuery.type=param.type
          this.listQuery.placeType=param.placeType
          this.listQuery.cityCode=param.cityCode
          this.listQuery.status=param.status
          this.getList(this.page);
      },
      handleAdd: function () {
        this.form={}
        this.form.parentId=''
        this.$refs.crud.rowAdd()
      },
      handAdd: function (row) {
        this.form.parentId=row.id
        this.option.column[1].valueDefault = this.form.parentId
        this.$refs.crud.rowAdd()
      },
      handleEdit(row, index) {
        this.$refs.crud.rowEdit(row, index)
      },

      handleDelete(row) {
        this.$confirm("此操作将删除(" + row.name + ") , 是否继续?", '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          delObj(row.id).then(() => {
            this.getList(this.page)
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success',
              duration: 2000
            })
          }).cache(() => {
            this.$notify({
              title: "失败",
              message: "删除失败",
              type: "error",
              duration: 2000
            });
          });

        })
      },
      handleUpdate(row, index, done, loading) {
        this.form.id=row.id
        putObj(this.form).then(() => {
          this.getList(this.page)
          // this.getDicTreeData()
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
          this.getList(this.page)
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
      } ,
      handleFilter(param) {
        this.listQuery.name=param.name
        this.getList(this.page);
      },
        onLoad:function(page){
            this.getList(page);
        },

    },

    watch:{
      'form.parentId'(){
        if(this.form.parentId==='-1'||this.form.parentId===''||this.form.parentId===undefined){
          this.option.column[4].rules[0].required=false;
          this.option.column[5].rules[0].required=false;
          this.option.column[6].rules[0].required=false;
        }else{
          this.option.column[4].rules[0].required=true;
          this.option.column[5].rules[0].required=true;
          this.option.column[6].rules[0].required=true;
        }
      }
    }

  }
</script>

