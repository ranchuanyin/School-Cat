import { createRouter, createWebHistory } from 'vue-router'
import LoginPage from "@/components/welcome/LoginPage.vue";
import Welcome from "@/views/Welcome.vue";
import RegisterPage from "@/components/welcome/RegisterPage.vue";
import {useStore} from "@/stores";
import Curd from "@/views/Curd.vue"


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      name: "welcome",
      component: Welcome,
      children:[
          {
        path: '',
        name: 'welcome-login',
        component:LoginPage
      },{
          path: 'register',
          name: 'welcome-register',
          component:RegisterPage
        },
        {
          path: 'forget',
          name: 'welcome-forget',
          component: () => import('@/components/welcome/ForgetPage.vue')
        }
      ]
    },{
      path: '/index',
      name: 'index',
      component: () => import("@/views/Index.vue"),
      children:[{
        path: '',
        name: 'catIndex',
        component: () => import('@/components/catIndex/CatIndex.vue')
      },
        {
          path: '/crud',
          name:'curd',
          component: Curd
        }]
    }
  ]
})

router.beforeEach((to, from, next) => {
  const store = useStore()
  if(store.auth.user != null && to.name.startsWith('welcome-')) {
    next('/index')
  } else if(store.auth.user == null && to.fullPath.startsWith('/index')) {
    next('/')
  } else if(to.matched.length === 0){
    next('/index')
  } else {
    next()
  }
})

export default router
