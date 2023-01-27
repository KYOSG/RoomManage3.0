<template>
  <div class="background">
    <el-container>
      <el-aside width="200px">
        <div style="height: 100%;background-color: #334154">
          <el-menu
              active-text-color="#1989FA"
              background-color="#334154"
              class="el-menu-vertical-demo"
              default-active="2"
              text-color="#fff"
              :router="true">
            <div style="height: 70px;width: 100%;padding-top: 20px">
              <img src="../assets/img/logoW.png" style="height: 60%;width: 80%; margin-left: 20px;margin-top: 5px" alt="">
            </div>
            <el-menu-item index="/BorrowRoom">教室借用</el-menu-item>
            <el-menu-item index="/BorrowInfoList">教室借用记录查询</el-menu-item>
            <el-menu-item index="/Admit" v-show="userInfo.role === 'admin'">借用审核</el-menu-item>
            <el-menu-item index="/UserManage" v-show="userInfo.role === 'admin'">用户管理</el-menu-item>
            <el-menu-item index="/RoomTimeAndReasonManage" v-show="userInfo.role === 'admin'">系统管理</el-menu-item>
            <el-menu-item index="/Personal">个人信息</el-menu-item>
          </el-menu>
        </div>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>

  <el-dialog
      v-model="Visable"
      title="使用说明"
      width="60%"
  >
    <div>
      <Roles/>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="Visable = false">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
export default {
  name: "Home",
  data(){
    return{
      userInfo:{
        userId:'',
        role:'',
        username:'',
        userDepart:'',
      },

      Visable:false
    }
  },
  mounted() {
    this.userInfo.role = window.sessionStorage.getItem("role");
    this.userInfo.userId = window.sessionStorage.getItem("userId");
  },
}
</script>

<style scoped>
.background{
  width: 1440px;
  height: 100%;
}
</style>
