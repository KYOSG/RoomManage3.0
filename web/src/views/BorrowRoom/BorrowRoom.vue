<template>
  <el-page-header content="借用登记" style="margin-bottom: 30px" @back="this.$router.push('/Login')"/>
  <el-card>
    <Table :msg="borrowForm"/>
    <el-divider />
    <div>
      <el-form label-width="120px" :inline="true">
        <el-form-item label="日期">
          <el-date-picker
              v-model="borrowForm.date"
              type="date"
              placeholder="请选择借用日期"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
              @change="getRoom"
              style="width: 200px"
          />
        </el-form-item>
        <el-form-item label="时间">
          <el-select
              v-model="borrowForm.time"
              placeholder="请选择借用时间"
              style="width: 200px"
              multiple
              collapse-tags
              @change="getRoom"
              placement="right"
          >
            <el-option
                v-for="item in TimeOption"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="原因">
          <el-select
              v-model="borrowForm.reason"
              placeholder="请选择借用原因"
              style="width: 200px"
              @change="getRoom"
              placement="right">
            <el-option
                v-for="item in ReasonOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
    </div>
    <el-divider />
    <div style="margin-left:20px;margin-right:20px;padding-left: 70px;padding-top: 40px;padding-bottom: 30px">
      <el-space :size="45" wrap>
        <div v-for="(item,i) in Rooms">
          <el-card :body-style="{ padding: '0px' }" style="width: 200px;">

            <div style="padding: 14px">
              <div style="margin-bottom: 10px">{{ item.name }}</div>
              <div style="width: 100%;padding-left: 10px;padding-right: 10px">
                  <el-space wrap v-for="(subItem,j) in item.description">
                    <el-tag style="margin-top: 10px">{{subItem}}</el-tag>
                  </el-space>
              </div>
            </div>

            <el-button type="primary" @click="preBorrow(item.name)" v-if="item.status === '1'" style="width: 100%">借用该教室</el-button>
            <el-button type="info" disabled v-if="item.status === '2'" style="width: 100%">维护中</el-button>
          </el-card>
        </div>
      </el-space>
    </div>
  </el-card>
  <el-dialog
      v-model="confirmDialogVisible"
      title="请确认您的借用信息"
      width="30%"
  >
    <el-form label-width="120px">
      <el-form-item label="教室">
        {{ borrowForm.roomName }}
      </el-form-item>
      <el-form-item label="借用日期">
        {{ borrowForm.date }}
      </el-form-item>
      <el-form-item label="借用时间">
        <el-space v-for="(item,i) in borrowForm.time">
          <el-tag>{{ item }}</el-tag>
        </el-space>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="confirmDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmBorrow">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import {ElMessage, ElMessageBox} from "element-plus";
import Table from "../../components/Table"
export default {
  name: "BorrowRoom",
  data(){
    return{
      TimeOption:[],
      ReasonOptions:[],
      userInfo:{
        userId:'',
        role:'',
        username:'',
        userDepart:'',
        newPwd:'',
        oldPwd:''
      },

      Rooms:[],

      isMedia:'',

      borrowForm:{
        username:'',
        time:[],
        date:'',
        reason:'',
        applyDate:'',
        roomName:'',
      },

      confirmDialogVisible:false,
    }
  },
  created() {
    //因为执行顺序问题，不宜使用Table组件中的setTimes
    this.borrowForm.date = this.setTimes(0);
  },
  mounted() {
    this.userInfo.role = window.sessionStorage.getItem('role');
    this.userInfo.username = window.sessionStorage.getItem("username");
    this.userInfo.userId = window.sessionStorage.getItem("userId");
    this.getAllTimeOptions();
    this.getAllReasons();
  },
  methods:{
    getRoom(){
      if (this.borrowForm.date==='' || this.borrowForm.time.length === 0){
        return;
      }
      this.$http({
        method:'post',
        url:'/borrowInfo/notBorrowedYet',
        data:this.borrowForm
      }).then(({data}) =>{
        if (data.code !== 200){
          ElMessage({
            message: '教室信息获取失败，请联系管理员',
            type: 'error',
          })
        }else {
          this.Rooms = data.list;

          for(let i=0;i<this.Rooms.length;i++){
            this.Rooms[i].description = this.Rooms[i].description.split(';');
          }
        }
      })

    },
    //借用请求预处理
    preBorrow(roomName){
      if(this.borrowForm.date === '' || this.borrowForm.time.length === 0|| this.borrowForm.reason === ''){
        ElMessage({
          message: '请完善借用信息',
          type: 'warning',
        })
        return;
      }

      this.borrowForm.roomName = roomName;
      //添加借用人信息
      this.borrowForm.username = this.userInfo.username;
      //添加申请时间信息
      let year = new Date().getFullYear();
      let month = new Date().getMonth() +1;
      let day = new Date().getDate();
      let hour = new Date().getHours();
      let minute = new Date().getMinutes();
      let second = new Date().getSeconds();
      this.borrowForm.applyDate = year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
      this.confirmDialogVisible = true;
      },

    confirmBorrow(){
      this.confirmDialogVisible = false;
      this.$http({
        method:'post',
        url:'/borrowInfo/borrow',
        data:this.borrowForm
      }).then(res =>{
          if (res.data.code !== 200){
            ElMessage({
              message: '登记失败，' + res.data.msg,
              type: 'warning',
            })
          }else {
            this.messageAlert();
            this.getRoom();
          }
      })
    },

    messageAlert(){
      ElMessageBox.confirm(
          '请等待管理员审核',
          '登记成功',
          {
            confirmButtonText: '确认',
            cancelButtonText: '确认:)',
            type: 'success',
          }
      )
          .then(() => {

          })
          .catch(() => {

          })
    },

    getAllReasons(){
      this.$http({
        url:'/reason',
        method:'get'
      }).then(({data}) => {
        for (let i = 0; i < data.list.length; i++) {
          this.ReasonOptions.push({
            label: data.list[i].name,
            key: data.list[i].name,
            value: data.list[i].name
          })
        }
      })
    },

    getAllTimeOptions(){
      this.$http({
        url:'/timeOption',
        method:'get',
      }).then(({data})=>{
        for(let i=0;i<data.list.length;i++){
          this.TimeOption.push({
           label:data.list[i].name,
           key:data.list[i].name,
           value:data.list[i].name
          })
        }
     })
    },
    //以指定的时间为基准，获取偏移量
    setTimes(offset) {
        //获取当前时间
      let dataOp = new Date()

      dataOp.setDate(dataOp.getDate() + offset)
      return String(dataOp.getFullYear())+ '-' + String((dataOp.getMonth() + 1) < 10 ? '0' + (dataOp.getMonth() + 1) : (dataOp.getMonth() + 1)) + '-' + ((dataOp.getDate() + 1) <= 10 ? '0' + (dataOp.getDate()) : (dataOp.getDate()));
    },

  },
  components:{
    Table,
  }
}
</script>

<style scoped>

</style>
