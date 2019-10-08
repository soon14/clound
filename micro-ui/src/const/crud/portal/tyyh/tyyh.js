
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
    column: [{
        fixed: true,
        label: 'id',
        prop: 'userId',
        span: 12,
        hide: true,
        addDisplay:false,
        editDisplay: false,
    },
    {
        label: "责任民警ID",
        prop: "policeid",
        hide:true,
        span:6
    },
    {
        label: "警号",
        prop: "policenumber",
        span:6,

    },
    {
        label: "责任民警姓名",
        prop: "policename",
        span:6
    },

    {
        label:"辅警类型",
        prop:"auxiliarytype",
        span:6
    },
    {
        label:"在职类型",
        prop:"jobtype",
        search:true,
        slot:true,
        span:6
    },
    {
        label:"职务",
        prop:"posttype",
        search:true,
        span:6
    },
    {
        label:"创建日期",
        prop:"createtimestr",
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
