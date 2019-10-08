
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
    menu:false,
    selection: true,
    column: [
        {
            label: "警号/辅警号",
            prop: "policeNumber",
            span:6
        },
    {
        label: "姓名",
        prop: "name",
        span:6
    },
    {
        label: "证件号码",
        prop: "idCard",
        span:6,

    },
    {
        label: "民族",
        prop: "nation",
        dicUrl:'portal/dict/getDict/mz/tyyh',
        span:6
    },

    {
        label:"性别",
        prop:"sex",
        dicUrl:'portal/dict/getDict/0122/ly',
        span:6
    },
    {
        label:"出生日期",
        prop:"birth",
        span:6
    },
    {
        label:"创建日期",
        prop:"createTime",
        type: "daterange",
        startPlaceholder: '日期开始',
        endPlaceholder: '日期结束',
        span:6,
    }
]
}

export const tyyhlistOption = {
            dialogWidth: '100%',
            dialogHeight: '100%',
            dialogFullscreen: true,
            align:'center',
            indexLabel: '序号',
            border: true,
            index: true,
            editBtn:false,
            delBtn:false,
            addBtn:false,
            viewBtn:false,
            menu:false,
            menuWidth:'100',

    }
