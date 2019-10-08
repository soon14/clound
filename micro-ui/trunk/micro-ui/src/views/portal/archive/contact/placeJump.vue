<template>
    <div>
        <avue-empty v-if="showError"></avue-empty>
        <keep-alive>
            <place-tab v-bind:param-form="paramForm" v-if="showJz"/>
        </keep-alive>
        <keep-alive>
            <csba-index v-bind:param-form="paramForm" v-if="showYlcs"/>
        </keep-alive>
        <keep-alive>
            <jxyxt v-bind:param-form="paramForm" v-if="showJx"/>
        </keep-alive>
        <keep-alive>
            <hotel-tab v-bind:param-form="paramForm" v-if="showLy"/>
        </keep-alive>
        <keep-alive>
            <kyzy v-bind:param-form="paramForm" v-if="showKyzy"/>
        </keep-alive>
    </div>
</template>

<script>
    import csbaIndex from "../ylcs/csba/csbaIndex";
    import index from "../mechanical/fileInfo/index";
    import hotelTab from "../lvye/hotel/hotelTab";
    import placeTab from "../jingz/cs/placeTab";
    import kyzy from "../kyzy/index";

    export default {
        name: "placeJump",
        props: ["param"],
        components: {
            "csbaIndex": csbaIndex,
            "jxyxt":index,
            "hotelTab":hotelTab,
            "placeTab": placeTab,
            "kyzy": kyzy
        },
        data(){
            return{
                showYlcs:false,
                showJx:false,
                showLy:false,
                showJz:false,
                showError:false,
                showKyzy:false,
                paramForm: {
                    placeType:undefined,//单位类型 警综页面需要判断每个场所对应的显示字段和字典翻译
                    menuId: undefined,//场所菜单id
                    unitId: undefined,//旅业单位、警综单位id
                    csbaId:undefined,//娱乐场所备案id（主键）
                    compId:undefined,//娱乐场所单位id
                    dwbh:undefined,//机修业单位id
                    id:undefined,//刻印章业单位id
                },
            }
        },
        created(){
            this.jump( )
        },
        computed: {

        },
        watch: {
            param: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.jump()
                },
                deep: true
            },
        },
        methods:{
            jump() {
                switch (this.param.lyxtType) {
                    case "0":
                        this.paramForm.unitId=this.param.unitId
                        this.paramForm.placeType=this.param.placeType
                        this.showJz=true
                        this.showYlcs=false
                        this.showJx=false
                        this.showLy=false
                        this.showError=false
                        this.showKyzy=false
                        break;
                    case "1":
                        this.paramForm.csbaId=this.param.unitId
                        this.paramForm.compId=this.param.compId
                        this.showYlcs=true
                        this.showJz=false
                        this.showJx=false
                        this.showLy=false
                        this.showError=false
                        this.showKyzy=false
                        break;
                    case "2":
                        this.paramForm.dwbh=this.param.unitId
                        this.showJx=true
                        this.showJz=false
                        this.showYlcs=false
                        this.showLy=false
                        this.showError=false
                        this.showKyzy=false
                        break;
                    case "3":
                        this.paramForm.unitId =this.param.unitId
                        this.showLy=true
                        this.showJz=false
                        this.showJx=false
                        this.showYlcs=false
                        this.showError=false
                        this.showKyzy=false
                        break;
                    case "4":
                        this.paramForm.id =this.param.unitId
                        this.showKyzy=true
                        this.showLy=false
                        this.showJz=false
                        this.showJx=false
                        this.showYlcs=false
                        this.showError=false
                        break;
                    default:
                        this.showError=true
                        this.showLy=false
                        this.showJz=false
                        this.showJx=false
                        this.showYlcs=false
                        this.showKyzy=false
                        break;
                }
            },
        }
    }
</script>
