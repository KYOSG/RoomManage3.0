"use strict";(self["webpackChunkrbs"]=self["webpackChunkrbs"]||[]).push([[981],{2981:function(e,a,t){t.r(a),t.d(a,{default:function(){return w}});var l=t(3396);const d={style:{display:"flex","justify-content":"space-between","align-items":"center"}},r=(0,l.Uk)("添加成员"),o={style:{"margin-top":"20px"}},i=(0,l._)("span",{style:{"margin-left":"3px"}},"更新",-1),s=(0,l._)("span",{style:{"margin-left":"3px"}},"删除",-1),n={slot:"footer",class:"dialog-footer"},u=(0,l.Uk)("确 定"),m=(0,l.Uk)("取 消");function p(e,a,t,p,g,h){const c=(0,l.up)("el-page-header"),f=(0,l.up)("el-button"),b=(0,l.up)("el-table-column"),w=(0,l.up)("el-table"),W=(0,l.up)("el-pagination"),y=(0,l.up)("el-input"),F=(0,l.up)("el-form-item"),V=(0,l.up)("el-form"),_=(0,l.up)("el-dialog"),U=(0,l.up)("el-card"),k=(0,l.Q2)("loading");return(0,l.wg)(),(0,l.iD)(l.HY,null,[(0,l.Wm)(c,{content:"用户管理",style:{"margin-bottom":"30px"},onBack:a[0]||(a[0]=e=>this.$router.push("/Login"))}),(0,l.Wm)(U,{style:{"max-width":"1200px",margin:"0 auto","background-color":"#f9fafb"}},{default:(0,l.w5)((()=>[(0,l._)("div",d,[(0,l._)("div",null,[(0,l.Wm)(f,{type:"primary",style:{"margin-right":"10px"},onClick:a[1]||(a[1]=e=>g.dialogVisibleForAdd=!0)},{default:(0,l.w5)((()=>[r])),_:1})])]),(0,l._)("div",o,[(0,l.wy)(((0,l.wg)(),(0,l.j4)(w,{data:g.userList,border:"",stripe:""},{default:(0,l.w5)((()=>[(0,l.Wm)(b,{align:"center",prop:"id",label:"工号"}),(0,l.Wm)(b,{align:"center",prop:"username",label:"姓名"}),(0,l.Wm)(b,{align:"center",prop:"userDepart",label:"班级"}),(0,l.Wm)(b,{align:"center",prop:"role",label:"身份"}),(0,l.Wm)(b,{align:"center",prop:"password",label:"密码"}),(0,l.Wm)(b,{align:"center",label:"操作"},{default:(0,l.w5)((e=>[(0,l.Wm)(f,{type:"primary",onClick:a=>h.update(e.row)},{default:(0,l.w5)((()=>[i])),_:2},1032,["onClick"]),(0,l.Wm)(f,{type:"danger",onClick:a=>h.remove(e.row.id)},{default:(0,l.w5)((()=>[s])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"])),[[k,g.loading]])]),(0,l.Wm)(W,{onSizeChange:h.getUser,onCurrentChange:h.getUser,"current-page":g.pageNum,"onUpdate:current-page":a[2]||(a[2]=e=>g.pageNum=e),"page-size":g.pageSize,"onUpdate:page-size":a[3]||(a[3]=e=>g.pageSize=e),layout:"total, sizes, prev, pager, next, jumper",total:g.total},null,8,["onSizeChange","onCurrentChange","current-page","page-size","total"]),(0,l.Wm)(_,{title:"添加成员",modelValue:g.dialogVisibleForAdd,"onUpdate:modelValue":a[10]||(a[10]=e=>g.dialogVisibleForAdd=e),width:"30%",style:{"padding-top":"30px"}},{default:(0,l.w5)((()=>[(0,l.Wm)(V,{ref:"addForm",model:g.addForm,"label-width":"100px"},{default:(0,l.w5)((()=>[(0,l.Wm)(F,{label:"工号"},{default:(0,l.w5)((()=>[(0,l.Wm)(y,{disabled:g.isEdit,modelValue:g.addForm.id,"onUpdate:modelValue":a[4]||(a[4]=e=>g.addForm.id=e),placeholder:"请输入工号..."},null,8,["disabled","modelValue"])])),_:1}),(0,l.Wm)(F,{label:"姓名"},{default:(0,l.w5)((()=>[(0,l.Wm)(y,{modelValue:g.addForm.username,"onUpdate:modelValue":a[5]||(a[5]=e=>g.addForm.username=e),placeholder:"请输入姓名..."},null,8,["modelValue"])])),_:1}),(0,l.Wm)(F,{label:"班级"},{default:(0,l.w5)((()=>[(0,l.Wm)(y,{modelValue:g.addForm.userDepart,"onUpdate:modelValue":a[6]||(a[6]=e=>g.addForm.userDepart=e),placeholder:"请输入班级..."},null,8,["modelValue"])])),_:1}),(0,l.Wm)(F,{label:"密码"},{default:(0,l.w5)((()=>[(0,l.Wm)(y,{modelValue:g.addForm.password,"onUpdate:modelValue":a[7]||(a[7]=e=>g.addForm.password=e),placeholder:"请输入密码..."},null,8,["modelValue"])])),_:1}),(0,l.Wm)(F,{label:"身份"},{default:(0,l.w5)((()=>[(0,l.Wm)(y,{modelValue:g.addForm.role,"onUpdate:modelValue":a[8]||(a[8]=e=>g.addForm.role=e),placeholder:"请输入密码..."},null,8,["modelValue"])])),_:1})])),_:1},8,["model"]),(0,l._)("span",n,[(0,l.Wm)(f,{type:"primary",onClick:h.addUser},{default:(0,l.w5)((()=>[u])),_:1},8,["onClick"]),(0,l.Wm)(f,{onClick:a[9]||(a[9]=e=>g.dialogVisibleForAdd=!1)},{default:(0,l.w5)((()=>[m])),_:1})])])),_:1},8,["modelValue"])])),_:1})],64)}var g=t(2807),h=t(6902),c={name:"UserManage",data(){return{loading:!0,isEdit:!1,pageSize:30,pageNum:1,total:0,addForm:{id:"",username:"",role:"teacher",password:"123456",userDepart:"",department:""},userList:[],dialogVisibleForAdd:!1}},mounted(){this.getUser()},methods:{getUser(){this.loading=!0,this.userList=[],this.$http({method:"get",url:"/user/"+this.pageNum+"/"+this.pageSize}).then((({data:e})=>{this.userList=e.userList,this.total=e.total,this.loading=!1}))},addUser(){let e="";e=this.isEdit?"put":"post",this.$http({method:e,url:"/user",data:this.addForm}).then((({data:e})=>{200===e.code?((0,g.z8)({message:"操作成功",type:"success"}),this.isEdit=!1,this.dialogVisibleForAdd=!1,this.addForm.username="",this.addForm.id="",this.addForm.password="123456",this.getUser()):(0,g.z8)({message:e.msg,type:"error"})}))},update(e){this.addForm=JSON.parse(JSON.stringify(e)),this.isEdit=!0,this.dialogVisibleForAdd=!0},remove(e){h.T.confirm("该操作不可撤销","警告",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((()=>{this.$http({method:"delete",url:"/user/"+e}).then((e=>{this.getUser(),(0,g.z8)({type:"success",message:"删除成功"})}))})).catch((()=>{(0,g.z8)({type:"info",message:"删除已取消"})}))}}},f=t(89);const b=(0,f.Z)(c,[["render",p]]);var w=b}}]);
//# sourceMappingURL=981.f697260e.js.map