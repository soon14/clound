<template>
    <div class="execution" height="500px">
        <basic-container>
            <avue-ueditor v-model="text" class="quill-editor" ></avue-ueditor>
        </basic-container>
    </div>
</template>
<style>
    .quill-editor {
        height: 500px!important;
        max-height: 800px!important;
    }


</style>
<script>
    import AvueUeditor from 'avue-plugin-ueditor/packages';
    Vue.use(AvueUeditor);
    export default {
        data() {
            return {
                text: "<center><h2 class=\"ql-indent-8\">整改通知书<h2></center></br>" +
                    "七天连锁酒店 单位：</br>" +
                    " &nbsp;&nbsp;&nbsp;&nbsp;我于 2019年3月21日 对你单位进行治安检查，检查过程中发现你单位存在：</br></br></br></br></br></br>" +
                    "，请于&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;前按照要求进行整改，整改完毕函告我单位进行复查。</br></br>"+
                    "<p class=\"ql-align-center\">检查单位：江门市公安局环市派出所</p></br>"+
                    "<p class=\"ql-align-center\">检查人：张三，王某某</p></br>"+
                    "<p class=\"ql-align-center\">被检查单位负责人：李某某</p></br>"+
                    "<p class=\"ql-align-center\">2019年3月25日</p></br>"
            };
        }
    };
</script>
