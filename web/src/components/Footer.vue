<template>
  <div style="display: flex;width: 100%;justify-content: center">
    <el-button style="border: 1px solid transparent" text @click="dialogVisible = true">© 2022 . All Rights Reserved | Designed by 王琳珊，张开源 | 3.0
    </el-button>
    <el-dialog
        v-model="dialogVisible"
        title="开发人员"
        width="25%"
        style="text-align: left"
    >
      <div style="text-align: left">
        <div>指导老师: 王琳珊</div>
        <div>开发者：张开源</div>
        <div>版本：3.0</div>
      </div>
      <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false" size="small"
        >OK</el-button
        >
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Footer",
  data(){
    return{
      dialogVisible:false,
    }
  }
}
</script>

<style scoped>

</style>
