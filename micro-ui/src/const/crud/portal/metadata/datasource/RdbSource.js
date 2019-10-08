import { getOneByName } from '@/api/portal/metadata/datasource/RdbSource'

var validateName = (rule, value, callback) => {

    getOneByName(value).then(response => {
        let result = response.data.data

        if (window.boxType === 'edit'){
            if (result.id==newVue.obj.editId) {
                callback()
            }else{
                callback(new Error('数据库名称已经存在'))
            }
        }
        if (result !== null) {
            callback(new Error('数据库名称已经存在'))
        } else {
            callback()
        }
    })
}

    export const tableOption = {
    addBtn:false,
    props: {
        img: 'img',
        type:'type',
        title: 'title',
        info: 'text',
    }
}
    export const addsourceOption = {

    dialogHeight: '100%',
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    addBtn: false,
    viewBtn: false,
    delBtn: false,
    submitBtn:false,
    emptyBtn:false,
    column: [
        {
            label: 'id',
            prop: 'id',
            hide:'true',
            display:false
        },
    {
        label: '类型',
        prop: 'type',
        search:'true',
        disabled:true,
        type:'select',
        rules: [{
            required: true,
            message: '请选择数据类型',
            trigger: 'blur'
        }
        ],
        dicData:[{
            label:'MySql',
            value:'MySql'
        },{
            label:'Oracle',
            value:'Oracle'
        },{
            label:'DB2',
            value:'DB2'
        },{
            label:'SQLServer',
            value:'SQLServer'
        }
        ]
    },
        {
            label: '库名称',
            prop: 'name',
            search:'true',
            rules: [{
                required: true,
                message: '请输入数据库名称',
                trigger: 'blur'
            },{ validator: validateName, trigger: 'blur' }
            ]
        },
    {
        label: 'IP地址',
        prop: 'ip',
        rules: [{
            required: true,
            message: '请输入数据库IP地址',
            trigger: 'blur'
        }]
    },
    {
        label: '账号',
        prop: 'account',
        rules: [{
            required: true,
            message: '请输入数据库账号',
            trigger: 'blur'
        }]
    },
    {
        label: '密码',
        prop: 'pwd',
        hide: true,
        type:'password',
        rules: [{
            required: true,
            message: '请输入数据库密码',
            trigger: 'blur'
        }]

    },
    {
        label: '端口',
        prop: 'port',
        hide: true,
        type:'number',
        rules: [{
            required: true,
            message: '请输入数据库端口号',
            trigger: 'blur'
        }]

    },
    {
        label: '实例名称',
        prop: 'serverName',
        hide: true,
        rules: [{
            required: true,
            message: '请输入数据库名称、实例名',
            trigger: 'blur'
        }]
    },

    {
        label: 'schema名',
        prop: 'schema',
    },
    {
        label: '来源单位',
        prop: 'dept',
        type:'select',
        hide: true,
        rules: [{
            required: true,
            message: '请选择来源单位',
            trigger: 'blur'
        }],
        dicData:[{
            label:'江门市公安局',
            value:'440700000000'
        },{
            label:'附城派出所',
            value:'440781510000'
        },{
            label:'南塘派出所',
            value:'440781520000'
        },{
            label:'环南派出所',
            value:'440781530000'
        }
        ]
    },
    {
        label: '联系人',
        prop: 'dbOwner',
        hide: true,
        rules: [{
            required: true,
            message: '请输入数据库联系人姓名',
            trigger: 'blur'
        }]
    },
    {
        label: '联系方式',
        hide: true,
        prop: 'phone',
        type: 'phone',

        value: '',
        span: 12,
        rules: [{

            min: 6,
            max: 20,
            message: '长度在 11 个字符',
            trigger: 'blur'
        },
            {
                required: true,
                message: '请输入数据库联系人电话',
                trigger: 'blur'
            }
        ]
    },



    {
        label: '更新时间',
        prop: 'updateDate',
        type: 'date',
        format: 'yyyy-MM-dd HH:mm',
        valueFormat: 'yyyy-MM-dd HH:mm',
        display:false,
    }

]
}

    export const editsourceOption = {

    dialogHeight: '100%',
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    addBtn: false,
    viewBtn: false,
    delBtn: false,
    submitBtn:false,
    emptyBtn:false,
    column: [
        {
            label: 'id',
            prop: 'id',
            hide:'true',
            display:false
        },
        {
            label: '库名称',
            prop: 'name',
            disabled:true,
            rules: [{
                required: true,
                message: '请输入数据库名称',
                trigger: 'blur'
            }
            ]
        },
        {
            label: '类型',
            prop: 'type',
            disabled:true,
            type:'select',
            rules: [{
                required: true,
                message: '请选择数据类型',
                trigger: 'blur'
            }],
            dicData:[{
                label:'MySql',
                value:'MySql'
            },{
                label:'Oracle',
                value:'Oracle'
            },{
                label:'DB2',
                value:'DB2'
            },{
                label:'SQLServer',
                value:'SQLServer'
            }
            ]
        },
        {
            label: 'IP地址',
            prop: 'ip',
            rules: [{
                required: true,
                message: '请输入数据库IP地址',
                trigger: 'blur'
            }]
        },
        {
            label: '账号',
            prop: 'account',
            rules: [{
                required: true,
                message: '请输入数据库账号',
                trigger: 'blur'
            }]
        },
        {
            label: '密码',
            prop: 'pwd',
            hide: true,
            type:'password',
            rules: [{
                required: true,
                message: '请输入数据库密码',
                trigger: 'blur'
            }]

        },
        {
            label: '端口',
            prop: 'port',
            hide: true,
            type:'number',
            rules: [{
                required: true,
                message: '请输入数据库端口号',
                trigger: 'blur'
            }]

        },
        {
            label: '实例名称',
            prop: 'serverName',
            hide: true,
            rules: [{
                required: true,
                message: '请输入数据库名称、实例名',
                trigger: 'blur'
            }]
        },

        {
            label: 'schema名',
            prop: 'schema',
        },
        {
            label: '来源单位',
            prop: 'dept',
            type:'select',
            hide: true,
            rules: [{
                required: true,
                message: '请选择来源单位',
                trigger: 'blur'
            }],
            dicData:[{
                label:'江门市公安局',
                value:'440700000000'
            },{
                label:'附城派出所',
                value:'440781510000'
            },{
                label:'南塘派出所',
                value:'440781520000'
            },{
                label:'环南派出所',
                value:'440781530000'
            }
            ]
        },
        {
            label: '联系人',
            prop: 'dbOwner',
            hide: true,
            rules: [{
                required: true,
                message: '请输入数据库联系人姓名',
                trigger: 'blur'
            }]
        },
        {
            label: '联系方式',
            hide: true,
            prop: 'phone',
            type: 'phone',

            value: '',
            span: 12,
            rules: [{

                min: 6,
                max: 20,
                message: '长度在 11 个字符',
                trigger: 'blur'
            },
                {
                    required: true,
                    message: '请输入数据库联系人电话',
                    trigger: 'blur'
                }
            ]
        },



        {
            label: '更新时间',
            prop: 'updateDate',
            type: 'date',
            format: 'yyyy-MM-dd HH:mm',
            valueFormat: 'yyyy-MM-dd HH:mm',
            display:false,
        }

    ]
}

    export const listOption = {

            dialogHeight: '80%',
            border: true,
            index: true,
            indexLabel: '序号',
            stripe: true,
            menuAlign: 'center',
            align: 'center',
            addBtn: false,
            viewBtn: true,
            delBtn: false,
            editBtn: false,
            refreshBtn: true,
            column: [
                {
                    label: 'id',
                    prop: 'id',
                    hide:'true'
                },
                {
                label: '库名称',
                prop: 'name',
            },
                {
                    label: '类型',
                    prop: 'type',
                    rules: [{
                        required: true,
                        message: '请选择数据类型',
                        trigger: 'blur'
                    }],
                    dicData:[{
                        label:'MySql',
                        value:'MySql'
                    },{
                        label:'Oracle',
                        value:'Oracle'
                    },{
                        label:'DB2',
                        value:'DB2'
                    },{
                        label:'SQLServer',
                        value:'SQLServer'
                    }
                    ]
                },
                {
                    label: 'IP地址',
                    prop: 'ip',
                    rules: [{
                        required: true,
                        message: '请输入数据库IP地址',
                        trigger: 'blur'
                    }]
                },
                {
                    label: '账号',
                    prop: 'account',
                    rules: [{
                        required: true,
                        message: '请输入数据库账号',
                        trigger: 'blur'
                    }]
                },
                {
                    label: '密码',
                    prop: 'pwd',
                    hide: true,
                    type:'password',
                    rules: [{
                        required: true,
                        message: '请输入数据库密码',
                        trigger: 'blur'
                    }]

                },
                {
                    label: '端口',
                    prop: 'port',
                    hide: true,
                    type:'number',
                    rules: [{
                        required: true,
                        message: '请输入数据库端口号',
                        trigger: 'blur'
                    }]

                },
                {
                    label: '实例名称',
                    prop: 'serverName',
                    hide: true,
                    rules: [{
                        required: true,
                        message: '请输入数据库名称、实例名',
                        trigger: 'blur'
                    }]
                },

                {
                    label: 'schema名',
                    prop: 'schema',
                },
                {
                    label: '来源单位',
                    prop: 'dept',
                    type:'select',
                    hide: true,
                    rules: [{
                        required: true,
                        message: '请选择来源单位',
                        trigger: 'blur'
                    }],
                    dicData:[{
                        label:'江门市公安局',
                        value:'440700000000'
                    },{
                        label:'附城派出所',
                        value:'440781510000'
                    },{
                        label:'南塘派出所',
                        value:'440781520000'
                    },{
                        label:'环南派出所',
                        value:'440781530000'
                    }
                    ]
                },
                {
                    label: '联系人',
                    prop: 'dbOwner',
                    hide: true,
                    rules: [{
                        required: true,
                        message: '请输入数据库联系人姓名',
                        trigger: 'blur'
                    }]
                },
                {
                    label: '联系方式',
                    hide: true,
                    prop: 'phone',
                    type: 'phone',
                    value: '',
                    span: 12,
                    rules: [{

                        min: 6,
                        max: 20,
                        message: '长度在 11 个字符',
                        trigger: 'blur'
                    },
                        {
                            required: true,
                            message: '请输入数据库联系人电话',
                            trigger: 'blur'
                        }
                    ]
                },



                {
                    label: '更新时间',
                    prop: 'updateDate',
                    type: 'date',
                    format: 'yyyy-MM-dd HH:mm:ss',
                    valueFormat: 'yyyy-MM-dd HH:mm:ss',
                    editDisplay: false,
                    addDisplay: false,
                }

            ]
}