import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "../store/index";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: ()=>import('../views/Manage.vue'),
    redirect:"/home",
    children:[
      {
        path:"home",
        name: '首页',
        component: ()=>import('../views/Home.vue'),
      },
      {
        path:"Yiqing",
        name: '疫情管理',
        component: ()=>import('../views/Admin/Yiqing.vue'),
      },
      {
        path:"adminuser",
        name: '管理员商家管理',
        component: ()=>import('../views/Admin/User.vue'),
      },
      {
        path:"admincustomer",
        name: '管理员用户管理',
        component: ()=>import('../views/Admin/Customer.vue'),
      },
      {
        path:"adminFood",
        name: '管理员食物管理',
        component: ()=>import('../views/Admin/Food.vue'),
      },
      {
        path:"person",
        name: '店家信息管理',
        component: ()=>import('../views/Shoper/Person.vue'),
      },
      {
        path:"product",
        name: '店家商品管理',
        component: ()=>import('../views/Shoper/Product.vue'),
      },
      {
        path:"order",
        name: '店家订单管理',
        component: ()=>import('../views/Shoper/Order.vue'),
      },
      {
        path:"menu",
        name: '菜单管理',
        component: ()=>import('../views/Menu.vue'),
      },
      {
        path:"person",
        name: '个人信息',
        component: ()=>import('../views/Shoper/Person.vue'),
      },
      {
        path:"file",
        name: '文件管理',
        component: ()=>import('../views/File.vue'),
      },
      {
        path:"food",
        name: '菜单管理',
        component: ()=>import('../views/Shoper/Food.vue'),
      },
      {
        path:"updatePassword",
        name: '修改密码',
        component: ()=>import('../views/UpdatePassword.vue'),
      },
      {
        path:"adminShop",
        name: '管理员店铺管理',
        component: ()=>import('../views/Admin/Shop.vue'),
      },
      {
        path:"userShop",
        name: '店家店铺管理',
        component: ()=>import('../views/Shoper/Shop.vue'),
      },
    ]
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import( '../views/Login.vue')
  },
 {
   path: '/register',
   name: 'Register',
   component: () => import( '../views/Register.vue')
 },
  {
    path: '/im',
    name: 'Im',
    component: () => import( '../views/Shoper/Im/Im.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
router.beforeEach((to,from,next)=>{
  localStorage.setItem("currentPathName",to.name)
  store.commit("setPath")
  next()
})
export default router
