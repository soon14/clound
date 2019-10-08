<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  export default {
    name: 'app',
    data() {
      return {}
    },
    watch: {},
    created() {
    },
    methods: {},
    computed: {}
  }
</script>
<style lang="scss">
  #app {
    width: 100%;
    height: 100%;
    overflow: hidden;
  }
  @font-face{
    font-family: "DS-Digital";
    src: url("../public/dsdigit/DS-Digital-Bold.ttf") format("truetype");
    font-weight: bold;
    font-style: normal;
  }
  .avue-crud .el-table th {
    word-break: break-word;
    color: #258cf5 !important;
    background-color: #eff7ff !important;
  }
</style>
