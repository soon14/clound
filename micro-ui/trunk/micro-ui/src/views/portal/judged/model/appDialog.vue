<template>
  <div>
      <div :is="modelName"> </div>

  </div>
</template>

<script>

    import Vue from 'vue'

    export default {
        name: "appDialog",
        props: ["dialogParam"],
        data() {
            return {
                id:'',
                links:'',
                modelName:''
            }
        },
        created() {
            this.id = this.dialogParam.id
            this.links = this.dialogParam.links
            let link=this.dialogParam.links
            this.modelName=this.id
            Vue.component(this.id, function (resolve) {
                 require([`@/${link}.vue`], resolve)
            })
        },
        watch: {
            dialogParam: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.id = this.dialogParam.id
                    this.links = this.dialogParam.links
                    let link=this.dialogParam.links
                   this.modelName=this.id
                    Vue.component( this.id, function (resolve) {
                        require([`@/${link}.vue`], resolve)
                    })

                },
                deep: true
            },
        }
    };
</script>

<style lang="scss">
</style>
