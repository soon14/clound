export const tableOption = {
    dialogFullscreen: true,
    dialogHeight: '100%',
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    addBtn: false,
    editBtn:false,
    viewBtn: true,
    delBtn: false,
    column: [
        {
            label: "方案名称",
            prop: "name",
            search: true,
            rules: [{
                required: true,
                message: '查询方案名称不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "查询方式",
            prop: "searchType",
            type:"select",
            dicMethod: 'get',
            dicUrl: '/admin/dict/type/search_type',
            dicQuery: {},
            search: true,
            rules: [{
                required: true,
                message: '查询方式不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "接口用户名",
            prop: "apiUserName",
            labelWidth:100,
            addDisplay: false,
            editDisplay: false,
            viewDisplay: false,
            hide:true,
            rules: [{
                required: true,
                message: '接口用户名不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "接口密码",
            prop: "apiPwd",
            addDisplay: false,
            editDisplay: false,
            viewDisplay: false,
            hide:true,
            rules: [{
                required: true,
                message: '接口密码不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "token地址",
            prop: "tokenAddress",
            labelWidth:100,
            addDisplay: false,
            editDisplay: false,
            viewDisplay: false,
            hide:true,
            rules: [{
                required: true,
                message: 'token请求地址不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "请求方式",
            prop: "requestType",
            type:"select",
            dicUrl: '/admin/dict/type/request_type',
            dicMethod: 'get',
            dicQuery: {},
            addDisplay: false,
            editDisplay: false,
            viewDisplay: false,
            hide:true,
            rules: [{
                required: true,
                message: '请求方式不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label: "业务表名/接口地址",
            prop: "searchInput",
            rules: [{
                required: true,
                message: '接口地址或业务表名不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label:'排序',
            prop:'sorts',
            type:'number',
            sortable:true,
        },
        {
            label: "状态",
            prop: "status",
            type:'select',
            search:true,
            dicUrl: '/admin/dict/type/point_status',
            dicMethod: 'get',
            dicQuery: {},
            slot:true,
            sortable:true,
            rules: [{
                required: true,
                message: '状态不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label:'分类',
            prop:'classification',
            search:true,
            type:'tree',
            sortable:true,
            dicUrl: '/admin/dict/tree',
            dicMethod: 'get',
            expandLevel: 1,
            dicQuery: {type:'search_scheme_classification'},
            rules: [{
                required: true,
                message: '分类不能为空',
                trigger: 'blur'
            }
            ]
        },
        {
            label:'说明',
            prop:'remark',
            type:'input',
            span:24,
            hide:true,
        },
        {
            label:'查询内容',
            prop:'searchContent',
            span:24,
            hide:true,
            formslot:true,
            rules:[
                {
                    required: true,
                    message: "查询内容不能为空",
                    trigger: "blur"
                },
            ]
        },
    ]
}

export const defaultOption = {
    dialogFullscreen: true,
    dialogHeight: "100%",
    border: true,
    index: true,
    indexLabel: "序号",
    stripe: true,
    menuAlign: "center",
    align: "center",
    addBtn: false,
    editBtn:false,
    viewBtn: false,
    delBtn: false,
    refreshBtn:false,
    columnBtn:false,
    searchBtn:false,
    menuWidth:100,
    dataScope: "",
    lyxt:"",
    pk:"",
    column: [
        {
            label: "方案名称",
            prop: "name",
            search: true
        },
        {
            label: "查询方式",
            prop: "searchType",
        },
        {
            label:"排序",
            prop:"sorts",
            type:"number"
        },
        {
            label:"说明",
            prop:"remark",
            type:"textarea"
        }
    ]
}

