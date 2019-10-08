<template>
    <div class="execution">
        <basic-container>
            <avue-crud
                       :data="tableData"
                       :option="tableOption"
                       :page="page"
                       v-model="form"
                      >
            </avue-crud>
        </basic-container>
    </div>
</template>

<script>

    export default {
        name:"history-page",
        data() {
            return {
                page: {
                    total: 6, // 总页数
                    currentPage: 1, // 当前页数
                    pageSize: 20, // 每页显示多少条,
                    isAsc: false//是否倒序
                },
                form:{},
                tableData: [
                    {
                        name:'歌舞娱乐场所',
                        field:'责任民警姓名',
                        before:'张三',
                        after: '李四',
                        updateDate:'2019-03-20',
                        updateBy:'超级管理员',
                    },
                    {
                        name:'歌舞娱乐场所',
                        field:'开业日期',
                        before:'',
                        after: '2019-03-20',
                        updateDate:'2019-03-20',
                        updateBy:'超级管理员',
                    },
                    {
                        name:'歌舞娱乐场所',
                        field:'从业人员总数',
                        before:'',
                        after: '2',
                        updateDate:'2019-03-20',
                        updateBy:'超级管理员',
                    },
                    {
                        name:'按摩服务场所',
                        field:'责任民警姓名',
                        before:'张三',
                        after: '李四',
                        updateDate:'2019-03-20',
                        updateBy:'超级管理员',
                    },
                    {
                        name:'按摩服务场所',
                        field:'开业日期',
                        before:'',
                        after: '2019-03-20',
                        updateDate:'2019-03-20',
                        updateBy:'超级管理员',
                    },
                    {
                        name:'按摩服务场所',
                        field:'从业人员总数',
                        before:'',
                        after: '2',
                        updateDate:'2019-03-20',
                        updateBy:'超级管理员',
                    }

                ],
                tableOption: {
                    border: true,
                    index: true,
                    indexLabel: '序号',
                    stripe: true,
                    menuAlign: 'center',
                    align: 'center',
                    addBtn: false,
                    viewBtn: false,
                    editBtn: false,
                    delBtn: false,
                    menu: false,
                    refreshBtn:false,
                    /*searchShow:false,*/
                    column: [{
                        label: '名称',
                        prop: 'name',
                        overHidden: true,
                        search:'true'
                    },
                        {
                            label: '字段',
                            prop: 'field',
                            overHidden: true,
                            search:'true'
                        },
                        {
                            label: '变更前',
                            overHidden: true,
                            prop: 'before'
                        },

                        {
                            label: '变更后',
                            overHidden: true,
                            prop: 'after'
                        },
                        {
                            label: '变更人',
                            prop: 'updateBy',
                            search:'true'
                        },
                        {
                            label: '变更时间',
                            prop: 'updateDate',
                            type: 'datetimerange',
                            search: 'true',
                            overHidden: true,
                            format: 'yyyy-MM-dd HH:mm',
                            valueFormat: 'yyyy-MM-dd HH:mm:ss'
                        }
                        ]
                }

            }
        },
        computed: {
            infoData() {
                return this.form.info
            }
        },

    }
</script>

