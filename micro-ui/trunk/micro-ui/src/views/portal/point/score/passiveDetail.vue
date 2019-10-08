<!--
 * @author wengshij
 * @date Created in 2019/7/29 14:41
 * @description:手动积分信息
 * @modified By:
 -->
<template>
    <div class="execution">
        <basic-container>
            <div class="main integralDetail">
                <div class="from-box ">
                    <!--  <h3>场所信息</h3>-->
                    <el-form class="form-main">
                        <el-row class="from-row"><!--行-->
                            <el-col :span="12">
                                <el-form-item label="场所名称:" class="place">
                                    <span v-text="detail.placeName"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="场所地址:">
                                    <span v-text="detail.placeAddress"></span>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row class="from-row">
                            <el-col :span="12">
                                <el-form-item label="行业类型:">
                                    <span v-text="findLabelByValue(dictPlaceTypeData,detail.placeType)"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">

                                <el-form-item label="评分周期:">
                                    <span v-text="detail.cycleName"></span>
                                </el-form-item>
                            </el-col>

                        </el-row>
                        <el-row class="from-row">
                            <el-col :span="12">
                                <el-form-item label="行政区划:">
                                    <span v-text="findLabelByValue(dictCityData,detail.cityCode)"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="管辖派出所:">
                                    <span v-text="detail.deptName"></span>
                                </el-form-item>
                            </el-col>
                        </el-row>

                        <el-row class="from-row">
                            <el-col :span="12">
                                <el-form-item label="当前积分:">
                                    <span v-text="detail.totalScore"></span>
                                </el-form-item>
                            </el-col>
                            <el-col :span="12">
                                <el-form-item label="当前等级:">
                                    <span v-text="findLabelByValue(dictLevelTypeData,detail.levelType) "></span>

                                </el-form-item>
                            </el-col>
                        </el-row>
                    </el-form>
                </div>

            </div>
            <div class="table-box">
                <avue-form :option="passiveOption" v-model="form" @submit="handleSave" @reset-change="emptyChange">
                    <template slot="indexUdId" slot-scope="scope">
                        <treeSelect
                                v-model="form.indexUdId"
                                :disable-branch-nodes="disableBranchNodes"
                                :options="treeDataIndexData"
                                placeholder="请选择指标项"
                                noChildrenText="暂无数据 "
                                zIndex="10099"
                                :append-to-body="appendToBody"
                                :clearable="clearable"
                                @select="selectIndex"
                        />
                    </template>


                </avue-form>

            </div>
        </basic-container>
    </div>
</template>

<script>
    import {findDictArea} from "@/api/portal/archive/common/query"
    import {fetchDictTree} from "@/api/admin/dict"
    import {getPlaceById} from '@/api/portal/point/score/pointScore'
    import {validatenull} from '@/util/validate'
    import {indexTreeByType} from '@/api/portal/point/index/pointIndex'
    import {passivePoint} from '@/api/portal/point/detail/indexDetail'

    export default {
        name: "passiveDetail",
        props: {
            detailRow: {
                type: Object,
            },
        },
        data() {
            return {
                disableBranchNodes: true,
                appendToBody:true,
                clearable: false,
                viewFlag: false,

                dictCityData: [],
                dictPlaceTypeData: [],
                dictLevelTypeData: [],
                dictPointTypeData: [],
                treeDataIndexData: [],
                detail: {},
                tableData: [],
                queryParams: [],
                defaultParams: {
                    placeId: undefined,
                    cycleId: undefined,
                    sourceType: undefined,
                    placeType: undefined,
                    placeName: undefined,
                    deptCode: undefined,
                    cityCode: undefined,
                    totalScore: undefined
                },
                form: {
                    indexUdId: undefined,
                    detail: undefined,
                    score: 0,
                    countScore: 0,
                    num: 1,
                    upperLimit: undefined
                },
                passiveData: [],
                treeOptions: {},
                passiveOption: {
                    labelWidth: 120,
                    refreshBtn: false,
                    filterBtn: false,
                    columnBtn: false,
                    searchBtn: false,
                    menuWidth: 120,
                    addBtn: false,
                    editBtn: false,
                    addRowBtn: true,
                    cellBtn: false,
                    cancelBtn: false,
                    column: [
                        {
                            label: '指标项',
                            prop: 'indexUdId',
                            formslot: true,
                            rules: [
                                {
                                    required: true,
                                    message: '指标项不能为空',
                                    trigger: 'blur'
                                }

                            ]

                        }, {
                            label: '指标项分值',
                            prop: 'score',
                            disabled: true


                        }, {
                            label: '累计分值',
                            prop: 'countScore',
                            disabled: true
                        }, {
                            label: '评分次数',
                            prop: 'num',
                            cell: true,
                            type: 'number',
                            slot: true,
                            rules: [{
                                pattern: /^([1-9]\d{0,1}|100)$/,
                                message: '只能输入1-100之间的数字',
                                trigger: 'blur'
                            }
                            ]

                        }, {
                            span: 24,
                            label: '评分依据',
                            prop: 'detail',
                            type: 'textarea',
                            rules: [

                                {
                                    required: true,
                                    message: '评分依据说明信息不能为空',
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
            }


        },
        created() {
            this.initParams()
            findDictArea().then(response => {
                this.dictCityData = response.data || []
            })
            fetchDictTree({type: 'HYLX'}).then(response => {
                this.dictPlaceTypeData = response.data.data || []
            })
            fetchDictTree({type: 'jfdj'}).then(response => {
                this.dictLevelTypeData = response.data.data || []
            })
            fetchDictTree({type: 'point_index_type'}).then(response => {
                this.dictPointTypeData = response.data.data || []
            })
            indexTreeByType('1', undefined).then(response => {
                this.treeDataIndexData = response.data.data || []
            })


        },
        methods: {
            emptyChange() {
                this.form.indexUdId = undefined
            },
            selectIndex(node, instanceId) {
                this.form.detail = node.remark
                this.form.score = node.score
                this.form.countScore = node.score
                this.form.num = 1
                this.form.upperLimit = node.upperLimit

            },
            handleSave(data, done) {

                if (data.upperLimit != 0) {
                    if (data.num > data.upperLimit) {
                        this.$message.error('该指标存在上限次数、最大上限次数为:' + data.upperLimit);
                        done()
                        return
                    }
                }

                passivePoint(Object.assign(data, this.defaultParams)).then(response => {
                    let result = response.data.data.code
                    if (result === '0' || result === 0) {
                        this.$message.success("手动评分成功");
                    } else {
                        this.$message.error(response.data.data.msg || '手动评分失败！');
                    }
                    done()
                    this.$emit("refresh", false);
                }).catch(function (err) {
                    this.$message.error('手动评分失败！')
                    this.$emit("refresh", false);
                })
            },


            initParams() {
                this.detail = this.detailRow
                this.defaultParams.cycleId = this.detailRow.cycleId
                this.defaultParams.placeId = this.detailRow.placeId
                this.defaultParams.sourceType = this.detailRow.sourceType
                this.defaultParams.placeType = this.detailRow.placeType
                this.defaultParams.placeName = this.detailRow.placeName
                this.defaultParams.cityCode = this.detailRow.cityCode
                this.defaultParams.deptCode = this.detailRow.deptCode
                this.defaultParams.totalScore = this.detailRow.totalScore
                this.form.indexUdId = undefined
                this.form.detail = undefined
                this.form.score = 0
                this.form.countScore = 0
                this.form.num = 1
                this.form.upperLimit = undefined



            },
            getPlace(placeId, score) {
                getPlaceById(placeId).then(data => {
                    let row = data.data.data;
                    if (!validatenull(row)) {
                        this.detail.totalScore = row.totalScore
                        this.detail.levelType = row.levelType
                    } else {
                        this.detail.totalScore = score
                    }

                })
            },


        }
        ,
        watch: {
            detailRow: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.initParams()
                },
                deep: true
            },
            'form.num': {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler(newVal, oldVal) {
                    this.form.countScore = newVal * this.form.score
                },
                deep: true
            },
        }
        ,
    }
</script>

<style lang="scss">
    .integralDetail {


        .main {
            width: 100%;
            margin: auto;
        }

        .el-form-item {
            margin-bottom: 0px !important;
        }

        .el-col {
            margin-bottom: 0px !important;
        }

        .from-box {
            padding: 10px;
            overflow: hidden;
            box-shadow: 0px 1px 8px 0px #979292;
            margin: auto;

            h3 {
                padding: 0;
                padding-left: 20px;
                margin: 0;
                height: 40px;
                line-height: 45px;
                border-bottom: 1px solid #ccc;
            }

            .from-row {
                padding-left: 50px;
            }

            .place {
                color: #0a15cc;
            }

            .form-main {
                overflow: hidden;
            }
        }


    }

    .table-box {
        margin: 45px 0px 20px 0px;

    }
</style>
