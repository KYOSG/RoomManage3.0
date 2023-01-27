<template>
<!--教室使用情况表-->
  <el-table :data="tableData" border>
    <el-table-column prop="date"/>
    <el-table-column v-for="(val,index) in timeSet" :key="index" :label="val" align="center">
      <template #default="scope">
        <el-space direction="vertical">
          <div v-for="(room,i) in  scope.row.rooms[index]">
            <el-tag size="small">{{room}}</el-tag>
          </div>
        </el-space>
      </template>
    </el-table-column>
  </el-table>
  <el-form label-width="120px" :inline="true" style="margin-top: 20px;margin-bottom: -20px">
    <el-form-item label="教室筛选">
      <div v-for="(item,i) in rooms">
        <el-tag type="warning" @click="subForm.roomName = item.name" style="cursor: pointer;margin-right: 20px;">{{ item.name }}</el-tag>
      </div>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "Table",
  props: ["msg"],
  data(){
    return{
      rooms:[],
      subForm:{
        date:'',
        roomName:''
      },
      timeSet:['第一节','第二节','第三节','第四节','第五节','第六节','第七节','第八节','第九节','第十节'],
      tableData:[],
    }
  },
  watch:{
    'msg.date'(newVal, oldVal) {
      //监测到日期变化，自动更新时间
      this.subForm.date = newVal;
      this.init();
    },
    'subForm.roomName'(newVal, oldVal) {
      //监测到教室变化，自动更新时间
      this.init();
    },
  },
  mounted() {
    //此处传递的是引用
    this.subForm.date = this.$props.msg.date;
    this.subForm.roomName = this.$props.msg.roomName;
    this.init();
    this.getAllRoom();
  },
  methods:{
    init(){
      //初始化表格数据函数
      this.tableData = [];
      //因为borrowForm中的Date会在发送请求的时候发生变化，所以在此处先取出来
      let tempDate = this.subForm.date;
      for(let i=0;i<7;i++){
        this.tableData.push({
          date: this.setTimes(tempDate,i).substring(5,10),
          rooms:[]
        })
        this.subForm.date = this.setTimes(tempDate,i);
        this.getTableData(i);
      }
      //将日期恢复至最初的时间，防止下次调用时改变周期
      this.subForm.date = tempDate;
    },
    getAllRoom(){
      this.$http({
        method:'get',
        url:'/room'
      }).then(res =>{
        this.rooms = res.data.list;
      })
    },
    getTableData(offset){
      this.$http({
        url:'/borrowInfo/getTableData',
        method:'post',
        data:this.subForm
      }).then(res =>{
        this.tableData[offset].rooms = res.data.list;
      })
    },
    //以指定的时间为基准，获取偏移量
    setTimes(selDate,offset) {
      let dataOp = null;
      if (selDate!=null){
        //selDate的格式为2022-10-14，先转换
        let year = parseInt(selDate.substring(0,4));
        //Date中月份是0-11
        let month = parseInt(selDate.substring(5,7))-1;
        let day = parseInt(selDate.substring(8,10));
        dataOp = new Date(year,month,day);
      }else {
        //获取当前时间
        dataOp = new Date()
      }

      dataOp.setDate(dataOp.getDate() + offset)
      return String(dataOp.getFullYear())+ '-' + String((dataOp.getMonth() + 1) < 10 ? '0' + (dataOp.getMonth() + 1) : (dataOp.getMonth() + 1)) + '-' + ((dataOp.getDate() + 1) <= 10 ? '0' + (dataOp.getDate()) : (dataOp.getDate()));
    },
  }
}
</script>

<style scoped>

</style>
