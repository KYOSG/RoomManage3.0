<template>
<!--个人信息-->
  <el-page-header content="个人信息" style="margin-bottom: 30px" @back="this.$router.push('/Login')"/>
  <el-card style="padding-left: 60px;padding-right: 60px">
    <el-descriptions title="个人信息" border>
      <el-descriptions-item label="用户ID">{{userInfo.userId}}</el-descriptions-item>
      <el-descriptions-item label="用户名">{{userInfo.username}}</el-descriptions-item>
      <el-descriptions-item label="身份">{{userInfo.role}}</el-descriptions-item>
      <el-descriptions-item label="操作">
        <el-button type="primary" @click="pwdVisable=true">修改密码</el-button>
        <el-button type="danger" @click="logout">退出</el-button>
      </el-descriptions-item>
    </el-descriptions>
  </el-card>
  <el-dialog
      v-model="pwdVisable"
      title="修改密码"
      width="30%"
  >
    <el-form label-width="120px">
      <el-form-item label="旧密码">
        <el-input v-model="userInfo.oldPwd"/>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input v-model="userInfo.newPwd"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="pwdVisable = false">取消</el-button>
        <el-button type="primary" @click="changePwd">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "Personal",
  data(){
    return{
      userInfo:{
        userId:'',
        role:'',
        username:'',
        userDepart:'',
        newPwd:'',
        oldPwd:''
      },
      pwdVisable:false
    }
  },
  mounted() {
    this.userInfo.role = window.sessionStorage.getItem('role');
    this.userInfo.name = window.sessionStorage.getItem("username");
    this.userInfo.username = window.sessionStorage.getItem("username");
    this.userInfo.userId = window.sessionStorage.getItem("userId");
  },
  methods:{
    changePwd(){
      this.$http({
        url:'/changePwd',
        method:'put',
        data:this.userInfo
      }).then(({data})=> {
        if (data.code === 200){
          ElMessage({
            message: '修改成功',
            type: 'success',
          })
          this.pwdVisable = false;
        }else {
          ElMessage({
            message: '修改失败，' + data.msg,
            type: 'warning',
          })
        }
      })
    },
    logout(){
      window.sessionStorage.clear();
      this.$router.push('/Login')
    }
  }
}
</script>

<style scoped>

</style>
