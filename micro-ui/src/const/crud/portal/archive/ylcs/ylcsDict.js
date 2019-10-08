export const ylcsDict = {
    /**从业人员 人员从业状态 */
    ryzt: [
        {label: '在职', value: '10'},
        {label: '离职', value: '20'},
        {label: '在岗', value: '11'},
        {label: '出差', value: '12'},
        {label: '请假', value: '13'},
        {label: '其他在职状态', value: '19'},
        {label: '其他状态', value: '90'},
        {label: '其他', value: '99'}
    ],
    /**从业人员 人员类别*/
    rylb: [
        {label: '管理人员', value: '10'},
        {label: '法定代表人或主要负责人', value: '11'},
        {label: '主要股东组成人员', value: '12'},
        {label: '治安负责人', value: '13'},
        {label: '其他管理人员', value: '19'},
        {label: '服务人员', value: '20'},
        {label: '一般服务人员', value: '21'},
        {label: '临时服务人员', value: '22'},
        {label: '演艺人员', value: '23'},
        {label: '其他服务人员', value: '29'},
        {label: '保安人员', value: '30'},
        {label: '安全检查人员', value: '40'},
        {label: '其他从业人员', value: '99'}
    ],
    /**从业人员 人员性别 */
    sex: [
        {label: '女', value: 2},
        {label: '男', value: 1},
    ],
    yes_no: [
        {label:'是',value:1},
        {label:'否',value:0}
    ],

    /** 人员考勤 打卡类型 */
    dklx:[
        {label:'上班',value:0},
        {label:'下班',value:1}
    ],
    /** 人员考勤 打卡情况 */
    dkqk:[
        {label:'正常',value:0},
        {label:'上班迟到',value:1},
        {label:'下班早退',value:2}
    ],
    /**企业状态  */
    csbaState: [
        {label: '营业', value: '1'},
        {label: '自主停业', value: '2'},
        {label: '勒令停业', value: '3'},
        {label: '歇业', value: '4'},
        {label: '注销', value: '5'},
        {label: '其他', value: '9'},
    ],
    /**备案进度 */
    csbaJindu: [
        {label: '填写基本信息', value: 1},
        {label: '上传附件信息', value: 2},
        {label: '递交审核', value: 3},
        {label: '进入公安审批', value: 4},
    ],
    /**变更状态  */
    bgState: [
        {label: '未递交审核', value: '-1'},
        {label: '变更中', value: '0'},
        {label: '未申请变更或变更已完成', value: '1'},
    ],
    getLabel: (obj, val) => {
        let name = val
        if (Array.isArray(obj) && obj.length > 0) {
            for (let i = 0; i < obj.length; i++) {
                if (obj[i].value === val) {
                    name = obj[i].label
                    break;
                }
            }
        }return name
    }
}
