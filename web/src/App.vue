<template>
  <div class="background">
    <router-view></router-view>
  </div>
  <Footer/>
</template>

<script>

import Footer from "@/components/Footer";
export default {
  name: 'App',
  mounted() {
    document.title = "计算机科学与技术学院教室预约系统";

  },
  created() {
    this.bodyScale()
  },
  methods:{
    bodyScale() {
      var devicewidth = document.documentElement.clientWidth; //获取当前分辨率下的可视区域宽度
      var scale = devicewidth / 1800; // 除以设计稿的尺寸
      document.body.style.zoom = scale; // 放大缩小相应倍数
    }
  },
  components: {
    Footer

  }
}
</script>

<style>
.background{

  display: flex;
  justify-content: center;
  min-width: 1024px;
  min-height: 900px !important;
  /*width: 1440px;*/
  /*height: 900px;*/
  /*display: flex;*/
  /*justify-content: center;*/
  /*min-width: 1440px;*/
}
#app {
  font-family: Noto Sans SC, emoji;
  /*position: absolute;*/
  /*top: 0;*/
  /*right: 0;*/
  /*left: 0;*/
  /*bottom: 0;*/
  /*margin: auto;*/
  /*overflow-x: hidden;*/
  /*min-width: 1024px !important;*/
  /*min-height: 1080px !important;*/
}
</style>
