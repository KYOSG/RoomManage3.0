<template>
  <div class="bac">
    <div class="loginBox">
      <p>账号密码登陆</p>
      <el-space direction="vertical" :size="30">
        <el-input v-model="subform.userId" placeholder="学号" clearable style="width: 240px;height: 40px;"/>
        <el-input v-model="subform.pwd" placeholder="密码" type="password" style="width: 240px;height: 40px;"/>
      </el-space>
      <el-button type="primary" @click="login" style="width: 240px;height: 40px;font-size: 14px">登陆</el-button>
    </div>
  </div>
</template>

<script>
import { Avatar, Key } from '@element-plus/icons-vue'
import {ElNotification} from "element-plus";
export default {
  name: 'Login',
  data(){
    return{
      subform:{
        userId:'',
        pwd:'',
      }
    }
  },
  methods:{
    login(){
      this.$http({
        method:'post',
        url:'/user/login',
        data:this.subform
      }).then(res =>{
        if(res.data.code!==200){
          ElNotification({
            title: '登陆失败',
            message: res.data.msg,
            type: 'warning',
          })
        }else if (res.data.code === 200){
          console.log(res);
          ElNotification({
            title: '登陆成功',
            message: '你好，' + res.data.user.username,
            type: 'success',
          })

          //保存信息
          window.sessionStorage.setItem('role',res.data.user.role);
          window.sessionStorage.setItem('username',res.data.user.username);
          window.sessionStorage.setItem('userId', res.data.user.id.toString());

          this.$router.push('/Home');
        }else {
          ElNotification({
            title: '服务器错误',
            message: '工具人QQ：3231977651',
            type: 'error',
          })
        }
      }).catch(function (error) {
        if (error.response) {
          // 请求已发出，且服务器的响应状态码超出了 2xx 范围
          ElNotification({
            title: '服务器错误',
            message: '工具人QQ：3231977651，错误信息：请求已发出，且服务器的响应状态码超出了 2xx 范围',
            type: 'error',
          })
        } else if (error.request) {
          // 请求已发出，但没有接收到任何响应
          ElNotification({
            title: '服务器错误',
            message: '工具人QQ：3231977651，错误信息：请求已发出但没有响应',
            type: 'error',
          })
        } else {
          // 引发请求错误的错误信息
          console.log('Error', error.message);
        }
      })
    }

  },
  components:{
    Avatar,
    Key
  }

}
</script>

<style scoped>
.bac{
  width: 1440px;
  height: 900px;
  background: url("../assets/img/loginBac.png");
}

.loginBox{
  width: 250px;
  height: 320px;
  position: absolute;
  left: 62%;
  top: 320px;
}
</style>
