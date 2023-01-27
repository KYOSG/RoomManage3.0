<template>
  <el-page-header content="系统管理" style="margin-bottom: 30px" @back="this.$router.push('/Login')"/>
  <div class="bac" style="padding-left: 40px">
    <el-space>
      <!--原因管理-->
      <el-card shadow="always" :v-loading="isLoading" style="width: 500px;margin-right: 40px;height: 300px">
        <el-space style="margin-bottom: 10px">
          <el-input placeholder="原因名称" v-model="this.reasonName"/>
          <el-button @click="addReason">添加原因</el-button>
        </el-space>
        <el-table
            :data="reasons"
            highlight-current-row
            max-height="220">
          <el-table-column label="ID" prop="id"></el-table-column>
          <el-table-column label="原因名称" prop="name"></el-table-column>
          <el-table-column label="操作" >
            <template #default="scope">
              <el-button type="danger" @click="removeReason(scope.row.id)" v-show="scope.row.id!==0">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
      <!--时间管理-->
      <el-card shadow="always" :v-loading="isLoading" style="width: 500px;height: 300px">
        <el-space style="margin-bottom: 10px">
          <el-input placeholder="时间段名称" v-model="this.timeName"/>
          <el-button @click="addTime">添加时间段</el-button>
        </el-space>
        <el-table
            :data="times"
            highlight-current-row
            max-height="220">
          <el-table-column label="ID" prop="id"></el-table-column>
          <el-table-column label="时间段名称" prop="name"></el-table-column>
          <el-table-column label="操作" >
            <template #default="scope">
              <el-button type="danger" @click="removeTime(scope.row.id)" v-show="scope.row.id!==0">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-space>

    <!--教室管理-->
    <el-card shadow="always" :v-loading="isLoading" style="width: 1050px;height: 450px;margin-top: 40px;">
      <el-space>
        <el-button @click="dialogVisible = true" style="margin-bottom: 10px">添加教室</el-button>
      </el-space>
      <el-table
          :data="rooms"
          highlight-current-row
          max-height="500">
        <el-table-column align="center" label="ID" prop="id" width="50px"></el-table-column>
        <el-table-column align="center" label="教室名称" prop="name" ></el-table-column>
        <el-table-column align="center" label="容量" prop="capacity" width="60px"></el-table-column>
        <el-table-column align="center" label="描述" prop="description" width="300px"></el-table-column>
        <el-table-column align="center" label="状态">
          <template #default="scope">
            <el-tag v-show="scope.row.status === '1'">可用</el-tag>
            <el-tag type="danger" v-show="scope.row.status === '2'">维护中</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" >
          <template #default="scope">
            <el-button type="primary" @click="getRoomFromRow(scope.row)">更新</el-button>
            <el-button type="danger" @click="removeRoom(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--添加教室-->
    <el-dialog
        v-model="dialogVisible"
        title="活动管理"
        width="30%"
    >
      <el-form
          label-width="100px"
          :model="roomInfo"
          style="max-width: 460px"
      >
        <el-form-item label="教室ID">
          <el-input v-model="roomInfo.id" :disabled="isUpdate"/>
        </el-form-item>
        <el-form-item label="教室名称">
          <el-input v-model="roomInfo.name" />
        </el-form-item>
        <el-form-item label="容量">
          <el-input type="number" v-model="roomInfo.capacity" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="roomInfo.description" />
        </el-form-item>
        <el-form-item label="状态">
          <el-switch
              v-model="roomInfo.status"
              style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
              active-text="可用"
              inactive-text="维护"
          />
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmForm">确认</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {ElMessage, ElMessageBox} from "element-plus";
let formData = new FormData();
export default {
  name: "RoomAndReasonManage",
  data(){
    return{
      dialogVisible:false,
      isLoading:true,
      //原因
      reasonName:'',
      reasons:[],
      //时间段
      timeName:'',
      times:[],
      //教室
      roomInfo:{
        id:'',
        name:'',
        capacity:'',
        description:'',
        status:true,
        imgUrl:'',
      },
      rooms:[],
      isUpdate:false,
    }
  },
  mounted() {
    this.getAllReason();
    this.getAllTime();
    this.getAllRoom();
  },
  methods:{
    //原因
    getAllReason(){
      this.$http({
        method:'get',
        url:'/reason'
      }).then(({data}) =>{
        this.reasons = data.list;
      })
    },
    addReason(){
      this.$http({
        method:'post',
        url:'/reason/' + this.reasonName,
      }).then(({data})=>{
        if (data.code ===200){
          this.getAllReason();
          this.reasonName = '';
        }
      })
    },
    removeReason(id){
      this.$http({
        method:'delete',
        url:'/reason/?id=' + id,
      }).then(({data})=>{
        if (data.code === 200){
          ElMessage({
            type: 'success',
            message: '删除成功',
          })
          this.getAllReason();
        }
      })
    },
    //时间段
    getAllTime(){
      this.$http({
        method:'get',
        url:'/timeOption'
      }).then(({data}) =>{
        this.times = data.list;
      })
    },
    addTime(){
      this.$http({
        method:'post',
        url:'/timeOption/' + this.timeName,
      }).then(({data})=>{
        if (data.code ===200){
          //利用延时解决更新列表错误的问题
          this.timer = setTimeout(() => {   //设置延迟执行
            this.getAllTime();
            this.timeName = '';
          }, 100);

        }
      })
    },
    removeTime(id){
      this.$http({
        method:'delete',
        url:'/timeOption/' + id,
      }).then(({data})=>{
        if (data.code === 200){
          ElMessage({
            type: 'success',
            message: '删除成功',
          })
          this.getAllTime();
        }
      })
    },
    //教室
    getAllRoom(){
      this.$http({
        method:'get',
        url:'/room'
      }).then(res =>{
        this.rooms = res.data.list;
      })
    },
    confirmForm(){
      if (this.isUpdate) {
        this.updateRoom();
      }else {
        this.addRoom()
      }
    },
    addRoom(){
      if (this.roomInfo.status){
        this.roomInfo.status = '1';
      }else {
        this.roomInfo.status = '2';
      }
        this.$http({
          method:'post',
          url:'/room',
          data:this.roomInfo
        }).then(({data})=>{
          if (data.code ===200){
            ElMessage({
              type: 'success',
              message: '添加成功',
            })
          }else {
            ElMessage({
              type: 'error',
              message: data.msg,
            })
          }
        })
      this.dialogVisible = false;
      this.roomInfo.status = true;
      this.roomInfo.name = '';
      this.roomInfo.capacity = '';
      this.roomInfo.imgUrl = '';
      this.roomInfo.description = '';
      this.isUpdate = false;
      //利用延时解决更新列表错误的问题
      this.timer = setTimeout(() => {   //设置延迟执行
        this.getAllTime();
        this.getAllRoom();
      }, 100);

    },
    removeRoom(id){
      this.$http({
        method:'delete',
        url:'/room/' + id,
      }).then(({data})=>{
        if (data.code === 200){
          ElMessage({
            type: 'success',
            message: '删除成功',
          })
          this.getAllRoom();
        }
      })
    },

    getRoomFromRow(row) {
      this.isUpdate = true;
      this.roomInfo = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
      this.roomInfo.status = this.roomInfo.status === '1';
    },

    updateRoom(){
      if (this.roomInfo.status){
        this.roomInfo.status = '1';
      }else {
        this.roomInfo.status = '2';
      }
        this.$http({
          method: 'put',
          url: '/room',
          data: this.roomInfo
        }).then(({data}) => {
          if (data.code === 200) {
            ElMessage({
              type: 'success',
              message: '修改成功',
            })
          } else {
            ElMessage({
              type: 'error',
              message: data.msg,
            })
          }
        })
      this.timer = setTimeout(() => {   //设置延迟执行
        this.getAllTime();
        this.getAllRoom();
      }, 200);

      this.dialogVisible = false;
      this.roomInfo.status = true;
      this.roomInfo.name = '';
      this.roomInfo.capacity = '';
      this.roomInfo.imgUrl = '';
      this.roomInfo.description = '';
      this.isUpdate = false;
    },
  }
}
</script>

<style scoped>

</style>
