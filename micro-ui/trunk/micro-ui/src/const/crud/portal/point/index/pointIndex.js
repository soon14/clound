import {udIdExit} from '@/api/portal/point/index/pointIndex'

/**
 * 校验IdId是否存在
 */
let validateUdId = (rule, value, callback) => {
    udIdExit(undefined, value).then(response => {
        if (window.boxType === 'edit') {
            callback()
        } else {
            let result = response.data.data
            if (result) {
                callback(new Error('该标识已被占用、请更换'))
            } else {
                callback()
            }
        }
    })
}

export const defaultUdId = () => {
    let date = new Date();
    let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    let strDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    let currentDate = "POINT_INDEX_" + date.getFullYear() + month + strDate + date.getHours() + date.getMinutes() + date.getSeconds() + date.getMilliseconds();
    return currentDate;
}

/**
 * 校验上限次数是否在指定的范围内
 */
let validateUpperLimit = (rule, value, callback) => {
    if (value >= 0 && value <= 100) {
        callback()
    } else {
        callback(new Error('上限范围只能 0-100 、0表示不限制'))
    }
}
/**
 *校验指标分值
 */
let validateScore = (rule, value, callback) => {
    if ((value >= -100 && value <= 100) && value != 0) {
        callback()
    } else {
        callback(new Error('上限范围只能 -100 至 100 、不能为0'))
    }
}

export const tableOption = {
   /* dialogType:'drawer',*/
    dialogHeight:'90%',
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    addBtn: false,
    delBtn: false,
    viewBtn:false,
    editBtn:false,
    menuWidth:'200',
    submitBtn: true,
    emptyBtn: true,
    column: [
        {
            label: "指标名称",
            prop: "name",
            search: true,
            overHidden: true,
            slot: true,
            rules: [
                {
                    required: true,
                    message: '指标名称不能为空',
                    trigger: 'blur'
                }, {
                    min: 3,
                    max: 100,
                    message: '长度在 3 到 100 个字符',
                    trigger: 'blur'
                }
            ]

        },

        {
            label: "状态",
            prop: "status",
            type: 'select',
            searchFilterable:true,
            filterable:true,
            search: true,
            slot:true,
            dicUrl: '/admin/dict/type/point_status',
            dicMethod: 'get',
            dicQuery: {},
            rules: [
                {
                    required: true,
                    message: '状态不能为空',
                    trigger: 'blur'
                }
            ]

        },
        {
            label: "类型",
            prop: "type",
            type: 'select',
            searchFilterable:true,
            filterable:true,
            search: true,
            dicUrl: '/admin/dict/type/point_index_type',
            dicMethod: 'get',
            dicQuery: {},
            addDisplay: false,
            editDisplay: false,
            editDisabled: true,
            rules: [

                {
                    required: true,
                    message: '类型不能为空',
                    trigger: 'blur'
                }
            ]
        },
        {
            label: "适用地区",
            prop: "city",
            dicUrl: '/portal/area/level/tree',
            type: "tree",
            dicMethod: 'get',
            search: true,
            editDisabled: true,
            addDisplay: false,
            editDisplay: false,
            dicQuery: {
                level: 2
            },
            props: {
                label: "name",
                value: 'code'
            },
            rules: [
                {
                    required: true,
                    message: '适用地区不能为空',
                    trigger: 'blur'
                }
            ]
        },
        {
            label: "适用场所",
            prop: "placeType",
            dicUrl: '/admin/dict/type/HYLX_DL',
            type: "select",
            dicMethod: 'get',
            searchFilterable:true,
            filterable:true,
            rules: [
                {
                    required: true,
                    message: '适用场所不能为空',
                    trigger: 'blur'
                }
            ],

            search: true,
            editDisabled: true
        },

        {
            label: "指标分值",
            prop: "score",
            type: "number",
            valueDefault: 1,
            rules: [

                {
                    required: true,
                    message: '指标分值不能为空',
                    trigger: 'blur'
                }, {
                    validator: validateScore,
                    trigger: 'blur'
                }
            ]
        },
        {
            label: "上限次数",
            prop: "upperLimit",
            type: "number",
            placeholder: "0表示不限制",
            slot: true,
            rules: [

                {
                    required: true,
                    message: '上限次数不能为空、0 表示 不限制',
                    trigger: 'blur'
                }, {
                    validator: validateUpperLimit,
                    trigger: 'blur'
                }
            ]

        },
        {
            label: "指标标识",
            prop: "udId",
            editDisabled: true,
            slot: true,
            overHidden: true,
            hide:true,
            rules: [
                {
                    required: true,
                    message: '指标标识不能为空',
                    trigger: 'blur'
                }, {
                    min: 3,
                    max: 32,
                    message: '长度在 3 到 32 个字符',
                    trigger: 'blur'
                }
                , {
                    pattern: /^\w+$/,
                    message: '只能由数字、字母、下划线组成',
                    trigger: 'blur'
                }, {
                    validator: validateUdId,
                    trigger: 'blur'
                }
            ],
        },
        {
            label: "创建者",
            prop: "creator",
            addDisplay: false,
            editDisplay: false
        },
        {
            label: "创建时间",
            prop: "createTime",
            addDisplay: false,
            editDisplay: false,
            hide: true,
        },
        {
            label: "创建者",
            prop: "createBy",
            hide: true,
            addDisplay: false,
            viewDisplay: false,
            editDisplay: false
        },
        {
            label: "更新者",
            prop: "updater",
            hide: true,
            addDisplay: false,
            editDisplay: false,
        },
        {
            label: "更新时间",
            prop: "updateTime",
            addDisplay: false,
            editDisplay: false,
            overHidden: true
        },
        {
            label: "规则说明",
            prop: "remark",
            span: 24,
            type: "textarea",
            overHidden: true,
            rules: [

                {
                    required: true,
                    message: '说明信息不能为空',
                    trigger: 'blur'
                },
                {
                    min: 10,
                    max: 2000,
                    message: '长度在 10 到 2000 个字符',
                    trigger: 'blur'
                }
            ]
        }


    ]
}

