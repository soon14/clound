
<template>
    <div class="app-container calendar-list-container">
        <basic-container>
            <avue-tabs :option="option" @change="handleChange"></avue-tabs>
            <span v-if="type.prop==='ysshlycsfx'">
                <ysshlycsfx/>
            </span>
            <span v-if="type.prop==='jxyxt'">
            <MechanicalList/>
            </span>
            <span v-else-if="type.prop==='lowHotel'">
            <low-hotel/>
            </span>
        </basic-container>
        <template>

        </template>
    </div>
</template>
<style>

</style>
<script>

    import ysshlycsfx from "./ysshlycsfx";
    import MechanicalList from "../../archive/mechanical/jxyxt";
    import lowHotel from "./lowHotel";
    export default {
        components: {
            "ysshlycsfx":ysshlycsfx,
            "jxyxt":jxyxt,
            "lowHotel":lowHotel
        },
        data(){
            return {
                type:{},
                tabForm1:{
                    id:''
                },
                tabForm2:{
                    id:''
                },
                option:{
                    column: [{
                        icon:'el-icon-bell',
                        label: '疑似涉黄旅业场所分析',
                        prop: 'ysshlycsfx',
                    }, {
                        icon:'el-icon-bell',
                        label: '低入住率',
                        prop: 'lowHotel',
                    }]
                },
            }
        },
        created(){
            this.type = this.option.column[0];
        },
        methods:{
            handleChange(column) {
                this.type = column
                //this.$message.success(JSON.stringify(column.label))
            },
        }
    }
</script>