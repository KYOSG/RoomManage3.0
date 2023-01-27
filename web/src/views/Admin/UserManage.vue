<template>

  <el-page-header content="用户管理" style="margin-bottom: 30px" @back="this.$router.push('/Login')"/>
  <el-card style="max-width: 1200px;margin:0 auto;background-color: #f9fafb">
    <div style="display: flex;justify-content: space-between;align-items: center">
      <div>
        <el-button type="primary" style="margin-right: 10px;" @click="dialogVisibleForAdd = true">添加成员</el-button>
      </div>
    </div>
    <!--表格展示-->
    <div style="margin-top: 20px">
      <el-table :data="userList" border stripe v-loading="loading">
        <el-table-column align="center" prop="id" label="工号"/>
        <el-table-column align="center" prop="username" label="姓名" />
        <el-table-column align="center" prop="userDepart" label="班级"/>
        <el-table-column align="center" prop="role" label="身份"/>
        <el-table-column align="center" prop="password" label="密码"/>
        <el-table-column align="center" label="操作">
          <template #default="scope">
            <el-button type="primary" @click="update(scope.row)">
              <span style="margin-left: 3px">更新</span>
            </el-button>
            <el-button type="danger" @click="remove(scope.row.id)">
              <span style="margin-left: 3px">删除</span>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-pagination
        @size-change="getUser"
        @current-change="getUser"
        v-model:current-page="pageNum"

        v-model:page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
    <!--添加成员的对话框-->
    <el-dialog title="添加成员" v-model="dialogVisibleForAdd" width="30%" style="padding-top: 30px">
      <el-form ref="addForm" :model="addForm" label-width="100px">
        <el-form-item label="工号">
          <el-input :disabled="isEdit" v-model="addForm.id" placeholder="请输入工号..."></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="addForm.username" placeholder="请输入姓名..."></el-input>
        </el-form-item>
        <el-form-item label="班级">
          <el-input v-model="addForm.userDepart" placeholder="请输入班级..."></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="addForm.password" placeholder="请输入密码..."></el-input>
        </el-form-item>
        <el-form-item label="身份">
          <el-input v-model="addForm.role" placeholder="请输入密码..."></el-input>
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="addUser">确 定</el-button>
          <el-button @click="dialogVisibleForAdd = false">取 消</el-button>
        </span>
    </el-dialog>
  </el-card>

</template>

<script>
import {ElMessage, ElMessageBox} from "element-plus";

export default {
  name: "UserManage",
  data() {
    return {
      loading:true,
      isEdit:false,

      pageSize:30,
      pageNum:1,
      total:0,

      addForm:{
        id:'',
        username:'',
        role:'teacher',
        password:'123456',
        userDepart:'',
        department:''
      },
      userList:[],
      dialogVisibleForAdd: false,
    }
  },
  mounted() {
    this.getUser();
  },
  methods: {
    getUser() {
      this.loading = true;
      this.userList = [];
      this.$http({
        method: 'get',
        url: '/user/' + this.pageNum + '/' + this.pageSize,
      }).then(({data}) => {
        this.userList = data.userList;
        this.total = data.total;
        this.loading = false;
      })
    },
    addUser() {
      let method = '';
      if(this.isEdit){
        method = 'put';
      }else {
        method = 'post';
      }
      this.$http({
        method: method,
        url: '/user' ,
        data: this.addForm
      }).then(({data}) => {
        if (data.code === 200) {
          ElMessage({
            message: '操作成功',
            type: 'success'
          })
          this.isEdit = false;
          this.dialogVisibleForAdd = false;
          this.addForm.username = '';
          this.addForm.id = '';
          this.addForm.password = '123456';
          this.getUser();
        }else {
          ElMessage({
            message: data.msg,
            type: 'error'
          })
        }
      })
    },
    update(row){
      this.addForm = JSON.parse(JSON.stringify(row));
      this.isEdit = true;

      this.dialogVisibleForAdd = true;
    },
    remove(id) {
      ElMessageBox.confirm(
          '该操作不可撤销',
          '警告',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
          .then(() => {
            this.$http({
              method: 'delete',
              url: '/user/' + id,
            }).then(res => {
              this.getUser();
              ElMessage({
                type: 'success',
                message: '删除成功',
              })
            })
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '删除已取消',
            })
          })
    },
  }
}
</script>

<style scoped>

</style>
