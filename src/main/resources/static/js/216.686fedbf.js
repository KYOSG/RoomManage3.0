"use strict";(self["webpackChunkrbs"]=self["webpackChunkrbs"]||[]).push([[216],{5216:function(e,t,o){o.r(t),o.d(t,{default:function(){return k}});var a=o(3396),l=o(9242);const i={class:"bac",style:{"padding-left":"40px"}},m=(0,a.Uk)("添加原因"),s=(0,a.Uk)("删除"),d=(0,a.Uk)("添加时间段"),n=(0,a.Uk)("删除"),r=(0,a.Uk)("添加教室"),h=(0,a.Uk)("可用"),u=(0,a.Uk)("维护中"),p=(0,a.Uk)("更新"),c=(0,a.Uk)("删除"),g={class:"dialog-footer"},f=(0,a.Uk)("取消"),w=(0,a.Uk)("确认");function W(e,t,o,W,b,y){const I=(0,a.up)("el-page-header"),_=(0,a.up)("el-input"),k=(0,a.up)("el-button"),V=(0,a.up)("el-space"),U=(0,a.up)("el-table-column"),R=(0,a.up)("el-table"),x=(0,a.up)("el-card"),v=(0,a.up)("el-tag"),C=(0,a.up)("el-form-item"),A=(0,a.up)("el-switch"),T=(0,a.up)("el-form"),N=(0,a.up)("el-dialog");return(0,a.wg)(),(0,a.iD)(a.HY,null,[(0,a.Wm)(I,{content:"系统管理",style:{"margin-bottom":"30px"},onBack:t[0]||(t[0]=e=>this.$router.push("/Login"))}),(0,a._)("div",i,[(0,a.Wm)(V,null,{default:(0,a.w5)((()=>[(0,a.Wm)(x,{shadow:"always","v-loading":b.isLoading,style:{width:"500px","margin-right":"40px",height:"300px"}},{default:(0,a.w5)((()=>[(0,a.Wm)(V,{style:{"margin-bottom":"10px"}},{default:(0,a.w5)((()=>[(0,a.Wm)(_,{placeholder:"原因名称",modelValue:this.reasonName,"onUpdate:modelValue":t[1]||(t[1]=e=>this.reasonName=e)},null,8,["modelValue"]),(0,a.Wm)(k,{onClick:y.addReason},{default:(0,a.w5)((()=>[m])),_:1},8,["onClick"])])),_:1}),(0,a.Wm)(R,{data:b.reasons,"highlight-current-row":"","max-height":"220"},{default:(0,a.w5)((()=>[(0,a.Wm)(U,{label:"ID",prop:"id"}),(0,a.Wm)(U,{label:"原因名称",prop:"name"}),(0,a.Wm)(U,{label:"操作"},{default:(0,a.w5)((e=>[(0,a.wy)((0,a.Wm)(k,{type:"danger",onClick:t=>y.removeReason(e.row.id)},{default:(0,a.w5)((()=>[s])),_:2},1032,["onClick"]),[[l.F8,0!==e.row.id]])])),_:1})])),_:1},8,["data"])])),_:1},8,["v-loading"]),(0,a.Wm)(x,{shadow:"always","v-loading":b.isLoading,style:{width:"500px",height:"300px"}},{default:(0,a.w5)((()=>[(0,a.Wm)(V,{style:{"margin-bottom":"10px"}},{default:(0,a.w5)((()=>[(0,a.Wm)(_,{placeholder:"时间段名称",modelValue:this.timeName,"onUpdate:modelValue":t[2]||(t[2]=e=>this.timeName=e)},null,8,["modelValue"]),(0,a.Wm)(k,{onClick:y.addTime},{default:(0,a.w5)((()=>[d])),_:1},8,["onClick"])])),_:1}),(0,a.Wm)(R,{data:b.times,"highlight-current-row":"","max-height":"220"},{default:(0,a.w5)((()=>[(0,a.Wm)(U,{label:"ID",prop:"id"}),(0,a.Wm)(U,{label:"时间段名称",prop:"name"}),(0,a.Wm)(U,{label:"操作"},{default:(0,a.w5)((e=>[(0,a.wy)((0,a.Wm)(k,{type:"danger",onClick:t=>y.removeTime(e.row.id)},{default:(0,a.w5)((()=>[n])),_:2},1032,["onClick"]),[[l.F8,0!==e.row.id]])])),_:1})])),_:1},8,["data"])])),_:1},8,["v-loading"])])),_:1}),(0,a.Wm)(x,{shadow:"always","v-loading":b.isLoading,style:{width:"1050px",height:"450px","margin-top":"40px"}},{default:(0,a.w5)((()=>[(0,a.Wm)(V,null,{default:(0,a.w5)((()=>[(0,a.Wm)(k,{onClick:t[3]||(t[3]=e=>b.dialogVisible=!0),style:{"margin-bottom":"10px"}},{default:(0,a.w5)((()=>[r])),_:1})])),_:1}),(0,a.Wm)(R,{data:b.rooms,"highlight-current-row":"","max-height":"500"},{default:(0,a.w5)((()=>[(0,a.Wm)(U,{align:"center",label:"ID",prop:"id",width:"50px"}),(0,a.Wm)(U,{align:"center",label:"教室名称",prop:"name"}),(0,a.Wm)(U,{align:"center",label:"容量",prop:"capacity",width:"60px"}),(0,a.Wm)(U,{align:"center",label:"描述",prop:"description",width:"300px"}),(0,a.Wm)(U,{align:"center",label:"状态"},{default:(0,a.w5)((e=>[(0,a.wy)((0,a.Wm)(v,null,{default:(0,a.w5)((()=>[h])),_:2},1536),[[l.F8,"1"===e.row.status]]),(0,a.wy)((0,a.Wm)(v,{type:"danger"},{default:(0,a.w5)((()=>[u])),_:2},1536),[[l.F8,"2"===e.row.status]])])),_:1}),(0,a.Wm)(U,{label:"操作"},{default:(0,a.w5)((e=>[(0,a.Wm)(k,{type:"primary",onClick:t=>y.getRoomFromRow(e.row)},{default:(0,a.w5)((()=>[p])),_:2},1032,["onClick"]),(0,a.Wm)(k,{type:"danger",onClick:t=>y.removeRoom(e.row.id)},{default:(0,a.w5)((()=>[c])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"])])),_:1},8,["v-loading"]),(0,a.Wm)(N,{modelValue:b.dialogVisible,"onUpdate:modelValue":t[10]||(t[10]=e=>b.dialogVisible=e),title:"活动管理",width:"30%"},{footer:(0,a.w5)((()=>[(0,a._)("span",g,[(0,a.Wm)(k,{onClick:t[9]||(t[9]=e=>b.dialogVisible=!1)},{default:(0,a.w5)((()=>[f])),_:1}),(0,a.Wm)(k,{type:"primary",onClick:y.confirmForm},{default:(0,a.w5)((()=>[w])),_:1},8,["onClick"])])])),default:(0,a.w5)((()=>[(0,a.Wm)(T,{"label-width":"100px",model:b.roomInfo,style:{"max-width":"460px"}},{default:(0,a.w5)((()=>[(0,a.Wm)(C,{label:"教室ID"},{default:(0,a.w5)((()=>[(0,a.Wm)(_,{modelValue:b.roomInfo.id,"onUpdate:modelValue":t[4]||(t[4]=e=>b.roomInfo.id=e),disabled:b.isUpdate},null,8,["modelValue","disabled"])])),_:1}),(0,a.Wm)(C,{label:"教室名称"},{default:(0,a.w5)((()=>[(0,a.Wm)(_,{modelValue:b.roomInfo.name,"onUpdate:modelValue":t[5]||(t[5]=e=>b.roomInfo.name=e)},null,8,["modelValue"])])),_:1}),(0,a.Wm)(C,{label:"容量"},{default:(0,a.w5)((()=>[(0,a.Wm)(_,{type:"number",modelValue:b.roomInfo.capacity,"onUpdate:modelValue":t[6]||(t[6]=e=>b.roomInfo.capacity=e)},null,8,["modelValue"])])),_:1}),(0,a.Wm)(C,{label:"描述"},{default:(0,a.w5)((()=>[(0,a.Wm)(_,{modelValue:b.roomInfo.description,"onUpdate:modelValue":t[7]||(t[7]=e=>b.roomInfo.description=e)},null,8,["modelValue"])])),_:1}),(0,a.Wm)(C,{label:"状态"},{default:(0,a.w5)((()=>[(0,a.Wm)(A,{modelValue:b.roomInfo.status,"onUpdate:modelValue":t[8]||(t[8]=e=>b.roomInfo.status=e),style:{"--el-switch-on-color":"#13ce66","--el-switch-off-color":"#ff4949"},"active-text":"可用","inactive-text":"维护"},null,8,["modelValue"])])),_:1})])),_:1},8,["model"])])),_:1},8,["modelValue"])])],64)}var b=o(2807);new FormData;var y={name:"RoomAndReasonManage",data(){return{dialogVisible:!1,isLoading:!0,reasonName:"",reasons:[],timeName:"",times:[],roomInfo:{id:"",name:"",capacity:"",description:"",status:!0,imgUrl:""},rooms:[],isUpdate:!1}},mounted(){this.getAllReason(),this.getAllTime(),this.getAllRoom()},methods:{getAllReason(){this.$http({method:"get",url:"/reason"}).then((({data:e})=>{this.reasons=e.list}))},addReason(){this.$http({method:"post",url:"/reason/"+this.reasonName}).then((({data:e})=>{200===e.code&&(this.getAllReason(),this.reasonName="")}))},removeReason(e){this.$http({method:"delete",url:"/reason/?id="+e}).then((({data:e})=>{200===e.code&&((0,b.z8)({type:"success",message:"删除成功"}),this.getAllReason())}))},getAllTime(){this.$http({method:"get",url:"/timeOption"}).then((({data:e})=>{this.times=e.list}))},addTime(){this.$http({method:"post",url:"/timeOption/"+this.timeName}).then((({data:e})=>{200===e.code&&(this.timer=setTimeout((()=>{this.getAllTime(),this.timeName=""}),100))}))},removeTime(e){this.$http({method:"delete",url:"/timeOption/"+e}).then((({data:e})=>{200===e.code&&((0,b.z8)({type:"success",message:"删除成功"}),this.getAllTime())}))},getAllRoom(){this.$http({method:"get",url:"/room"}).then((e=>{this.rooms=e.data.list}))},confirmForm(){this.isUpdate?this.updateRoom():this.addRoom()},addRoom(){this.roomInfo.status?this.roomInfo.status="1":this.roomInfo.status="2",this.$http({method:"post",url:"/room",data:this.roomInfo}).then((({data:e})=>{200===e.code?(0,b.z8)({type:"success",message:"添加成功"}):(0,b.z8)({type:"error",message:e.msg})})),this.dialogVisible=!1,this.roomInfo.status=!0,this.roomInfo.name="",this.roomInfo.capacity="",this.roomInfo.imgUrl="",this.roomInfo.description="",this.isUpdate=!1,this.timer=setTimeout((()=>{this.getAllTime(),this.getAllRoom()}),100)},removeRoom(e){this.$http({method:"delete",url:"/room/"+e}).then((({data:e})=>{200===e.code&&((0,b.z8)({type:"success",message:"删除成功"}),this.getAllRoom())}))},getRoomFromRow(e){this.isUpdate=!0,this.roomInfo=JSON.parse(JSON.stringify(e)),this.dialogVisible=!0,this.roomInfo.status="1"===this.roomInfo.status},updateRoom(){this.roomInfo.status?this.roomInfo.status="1":this.roomInfo.status="2",this.$http({method:"put",url:"/room",data:this.roomInfo}).then((({data:e})=>{200===e.code?(0,b.z8)({type:"success",message:"修改成功"}):(0,b.z8)({type:"error",message:e.msg})})),this.timer=setTimeout((()=>{this.getAllTime(),this.getAllRoom()}),200),this.dialogVisible=!1,this.roomInfo.status=!0,this.roomInfo.name="",this.roomInfo.capacity="",this.roomInfo.imgUrl="",this.roomInfo.description="",this.isUpdate=!1}}},I=o(89);const _=(0,I.Z)(y,[["render",W]]);var k=_}}]);
//# sourceMappingURL=216.686fedbf.js.map