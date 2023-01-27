import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect:'/Login'
  },
  {
    path: '/Home',
    name: 'Home',
    redirect: '/BorrowRoom',
    component: () => import('../views/Home.vue'),
    //---------------------教室借用相关-----------------------------
    children:[
      {
        path: '/BorrowRoom',
        name: 'BorrowRoom',
        component: () => import('../views/BorrowRoom/BorrowRoom.vue')
      },
      {
        path: '/BorrowInfoList',
        name: 'BorrowInfoList',
        component: () => import('../views/BorrowRoom/BorrowInfoList.vue')
      },
      //---------------------管理员部分-----------------------------
      {
        path: '/Admit',
        name: 'Admit',
        component: () => import('../views/Admin/Admit.vue')
      },
      {
        path: '/UserManage',
        name: 'UserManage',
        component: () => import('../views/Admin/UserManage.vue')
      },
      {
        path: '/RoomTimeAndReasonManage',
        name: 'RoomTimeAndReasonManage',
        component: () => import('../views/Admin/RoomTimeAndReasonManage.vue')
      },
      {
        path: '/Personal',
        name: 'Personal',
        component: () => import('../views/Personal.vue')
      },
    ]
  },
  //---------------------用户/Admin--------------------------------------------------------------------
  {
    path: '/Login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },


]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

//导航守卫
//开启前先做白名单
router.beforeEach((to, from, next) => {
  //页面拦截
  if (to.name !== 'Login') {
    let role = window.sessionStorage.getItem("role");
    // let token = window.sessionStorage.getItem("token");
    if (!role) {
      return next({ name: 'Login' });
    }
    return next();
  } else {
    return next();
  }


})

export default router
