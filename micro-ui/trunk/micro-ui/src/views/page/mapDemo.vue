<template>
    <div class="mapDemo">
        <bacsMap
              v-model="address"/>
    </div>
</template>

<script>
    import bacsMap from "@/components/map/bacsMap"
    export default {
        name: "mapDemo",
        components:{
            bacsMap,
        },
        data(){
            return{
                //地址信息
                address:{
                    //调用的接口
                    type:"matchForward",
                    //参数
                    data:{
                        "xz":"广州南站",
                        "province":"广东省"
                    }
                }
            }
        }
    }
</script>

<style lang="scss">
    .mapDemo{
        height: 600px;
    }

</style>