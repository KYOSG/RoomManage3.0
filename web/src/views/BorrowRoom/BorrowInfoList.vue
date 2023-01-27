<template>
  <el-page-header content="借用记录" style="margin-bottom: 30px" @back="this.$router.push('/Login')"/>
  <el-card>
    <el-form label-width="120px">
      <el-form-item label="日期">
        <el-date-picker
            v-model="subForm.date"
            type="date"
            placeholder="请选择借用日期"
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
            @change="getBorrowInfo"
            style="width: 200px"
        />
      </el-form-item>
      <el-form-item label="时间">
        <el-select
            v-model="subForm.time"
            placeholder="请选择借用时间"
            style="width: 200px"
            @change="getBorrowInfo"
        >
          <el-option
              v-for="item in TimeOption"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="教室">
        <el-select
            v-model="subForm.roomName"
            placeholder="请选择教室"
            style="width: 200px"
            @change="getBorrowInfo">
          <el-option
              v-for="item in Rooms"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="导入课表" v-show="userInfo.role === 'admin'">
        <a href="/static/template.xls" download="课表模板.xlsx">
          <el-button type="primary" style="margin-right: 10px;" :loading="loading">下载模板</el-button>
        </a>
        <el-breadcrumb :separator-icon="ArrowRight" style="margin-right: 20px;">
          <el-date-picker v-model="this.startDate" type="date" format="YYYY-MM-DD" value-format="YYYY-MM-DD" placeholder="学期开始日期"/>
        </el-breadcrumb>
        <el-upload :on-change="fileChange" :show-file-list="false" :auto-upload="false">
          <el-button type="warning" :loading="loading">上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="下载记录" v-show="userInfo.role === 'admin'">
        <el-button type="primary" @click="exportExcel">导出记录</el-button>
      </el-form-item>
    </el-form>

    <el-divider />
    <div>
      <el-table :data="borrowInfo" stripe  border v-loading="loading" :highlight-current-row="true">
        <el-table-column align="center" prop="date" label="日期" width="120" />
        <el-table-column align="center" prop="time" label="时间" width="120" />
        <el-table-column align="center" prop="roomName" label="教室名称" width="100" />
        <el-table-column align="center" prop="reason" label="用途" width="180"/>
        <el-table-column align="center" prop="name" label="借用人" />
        <el-table-column align="center" prop="applyDate" label="申请时间" width="200"/>
        <el-table-column align="center"  width="100" label="状态">
          <template #default="scope">
            <el-tag v-show="scope.row.isAdmit === '0'" type="warning">待审核</el-tag>
            <el-tag v-show="scope.row.isAdmit === '2'" type="danger">拒绝</el-tag>
            <el-tag v-show="scope.row.isAdmit === '1'" type="success">通过</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作">
          <template #default="scope">
            <el-button type="danger" @click="cancel(scope.row.id)" v-show="userInfo.role === 'admin' || userInfo.username === scope.row.name">撤销</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          @size-change="getBorrowInfo"
          @current-change="getBorrowInfo"
          v-model:current-page="subForm.pageNum"

          v-model:page-size="subForm.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="subForm.total">
      </el-pagination>
    </div>
  </el-card>
</template>

<script>
import {ElMessage} from "element-plus";
let formData = new FormData;
export default {
  name: "BorrowInfoList",
  data(){
    return{
      loading:true,
      TimeOption:[],
      Rooms:[],

      userInfo:{
        userId:'',
        role:'',
        username:'',
        userDepart:'',
      },

      borrowInfo:[],

      subForm:{
        date:'',
        time:'',
        reason:'',
        userId:'',
        roomName:'',
        pageNum:1,
        pageSize: 30,
        total:0
      },

      startDate:null,
    }
  },
  mounted() {
    this.userInfo.role = window.sessionStorage.getItem("role");
    this.userInfo.userId = window.sessionStorage.getItem("userId");
    this.userInfo.username = window.sessionStorage.getItem("username");
    this.getBorrowInfo();
    this.getAllRooms();
    this.getAllTimeOptions();
    console.log(this.userInfo)
  },
  methods:{
    getAllTimeOptions(){
      this.loading = true;
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
        this.loading = false;
      })
    },
    getAllRooms(){
      this.loading = true;
      this.$http({
        url:'/room',
        method:'get',
      }).then(({data})=>{
        for(let i=0;i<data.list.length;i++){
          this.Rooms.push({
            label:data.list[i].name,
            key:data.list[i].name,
            value:data.list[i].name
          })
        }
        this.loading = false;
      })
    },
    getBorrowInfo(){
      this.loading = true;
      this.$http({
        method:'post',
        url: '/borrowInfo',
        data:this.subForm
      }).then(res =>{
        console.log(res);
        if (res.data.code !== 200){
          ElMessage({
            message: '教室信息获取失败',
            type: 'error',
          })
        }else {
          this.borrowInfo = res.data.RBI;
          this.subForm.total = res.data.total;
        }
        this.loading = false;
      })
    },
    cancel(id){
      this.$http({
        method:'delete',
        url: '/borrowInfo/' + parseInt(id),
      }).then(res =>{
        ElMessage({
          message: '撤销成功',
          type: 'success',
        })
        this.getBorrowInfo();
      })
    },

    fileChange(files, fileList) {
      formData.append('file', files.raw)
      files = null;
      this.importTimeTable();
    },

    importTimeTable(){
      this.loading = true;
      if (this.startDate === null || this.subForm.roomName === ''){
        alert("请选择日期和教室");
        this.loading = false;
        return;
      }

      formData.append("startDate",this.startDate);
      formData.append("roomName",this.subForm.roomName);

      this.$http({
        method: 'post',
        url: '/file/importTimeTable',
        data: formData,
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        if (res.data.code === 200) {
          this.$notify({
            title: '上传成功',
            message: '',
            type: 'success'
          });
          this.getBorrowInfo();
        } else {
          this.$notify({
            title: '文件解析失败',
            message: res.data.msg,
            type: 'error'
          });
        }
        formData = null;
        formData = new FormData();
        this.loading = false;
      })
    },
    exportExcel() {
      this.loading = true;
      this.$http({
        method: 'get',
        url: '/borrowInfo/exportExcel',
        responseType: "arraybuffer"
      }).then((file) => {
        //流文件下载
        let content = file.data;
        // 组装a标签
        let elink = document.createElement("a");
        // 设置下载文件名

        elink.download ="借用记录.xls";
        elink.style.display = "none";
        let blob = new Blob([content], {type: "application/xls"})
        elink.href = URL.createObjectURL(blob);
        document.body.appendChild(elink);
        elink.click();
        document.body.removeChild(elink);
        this.loading = false;
      })
    },
  }
}
</script>

<style scoped>

</style>
