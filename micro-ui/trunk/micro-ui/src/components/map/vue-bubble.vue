<template>
    <transition>
        <div :id="id" data-bbproxy="vue" v-show="render"><slot></slot></div>
    </transition>
</template>
<script>
    export default {
      name: 'vue-bubble',
      props: {
        id: {
          type: String
        }
      },
      data() {
        return {
          render: false,
          originParent: null
        };
      },
      /**
       *  vue、angular等外部框架开发地图气泡容器组件时实现init、getElement、open、close三个方法
       */
      methods: {
        init(){
          this.originParent = this.$el.parentElement;
          var compId = this.id;
          if(!FMapScope['compCache']){
            FMapScope['compCache'] = {};
          }
          FMapScope['compCache'][compId]= this;
        },
        getElement(){
          return this.$el;
        },
        open(){
          this.render = true;
        },
        close(){
          this.render = false;
          this.originParent.appendChild(this.$el);
        }
      },
      mounted() {
          this.init();
      },
      destroyed() {
        // todo
      }
    };
</script>
