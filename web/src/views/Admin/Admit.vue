<template>
  <el-table :data="admitlist" stripe  border v-loading="loading" :highlight-current-row="true">
    <el-table-column align="center" prop="date" label="日期" width="120" />
    <el-table-column align="center" prop="time" label="时间" width="120" />
    <el-table-column align="center" prop="roomName" label="教室名称" width="100" />
    <el-table-column align="center" prop="reason" label="用途" width="150"/>
    <el-table-column align="center" prop="name" label="借用人" />
    <el-table-column align="center" prop="applyDate" label="申请时间" width="180"/>
    <el-table-column align="center" label="操作">
      <template #default="scope">
        <el-button type="success" @click="access(scope.row.id)" v-show="userInfo.role === 'admin'">允许</el-button>
        <el-button type="danger" @click="deny(scope.row.id)" v-show="userInfo.role === 'admin'">拒绝</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "Admit",
  data(){
    return{
      loading: true,
      userInfo:{
        userId:'',
        role:'',
        username:'',
        userDepart:'',
      },
      admitlist:[],
    }
  },
  mounted() {
    this.userInfo.role = window.sessionStorage.getItem("role");
    this.userInfo.userId = window.sessionStorage.getItem("userId");
    this.getAllAdmit()
  },
  methods:{
    getAllAdmit(){
      this.loading = true;
      this.$http({
        url:'/admit',
        method:'get',
      }).then(({data})=>{
        if (data.code !== 200){
          ElMessage({
            message: '信息获取失败',
            type: 'error',
          })
        }else {
          this.admitlist = data.list;
        }

        this.loading = false;
      })
    },
    access(id){
      this.loading = true;
      this.$http({
        url:'/admit/access/' + id,
        method:'put',
      }).then(({data})=>{
        if (data.code !== 200){
          ElMessage({
            message: '操作失败',
            type: 'error',
          })
        }else {
          this.getAllAdmit();
        }
        this.loading = false;
      })
    },
    deny(id){
      this.loading = true;
      this.$http({
        url:'/admit/deny/' + id,
        method:'put',
      }).then(({data})=>{
        if (data.code !== 200){
          ElMessage({
            message: '操作失败',
            type: 'error',
          })
        }else {
          this.getAllAdmit();
        }
        this.loading = false;
      })
    },
  }
}
</script>

<style scoped>

</style>
