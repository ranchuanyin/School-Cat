import {createRouter, createWebHistory} from 'vue-router'
import LoginPage from "@/components/welcome/LoginPage.vue";
import Welcome from "@/views/Welcome.vue";
import RegisterPage from "@/components/welcome/RegisterPage.vue";
import {useStore} from "@/stores";


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: "welcome",
            component: Welcome,
            children: [
                {
                    path: '',
                    name: 'welcome-login',
                    component: LoginPage,
                    meta: {keepAlive: true}
                }, {
                    path: 'register',
                    name: 'welcome-register',
                    component: RegisterPage,
                    meta: {keepAlive: true}
                },
                {
                    path: 'forget',
                    name: 'welcome-forget',
                    component: () => import('@/components/welcome/ForgetPage.vue'),
                    meta: {keepAlive: true}
                }
            ]
        },
        {
            path: '/cat',
            name: 'index',
            component: () => import("@/views/Index.vue"),
            children: [{
                path: '',
                name: 'catIndex',
                component: () => import('@/components/catIndex/CatIndex.vue')
            },
                {
                    path: '/cat/cat/:page?',
                    name: 'schoolCat-index',
                    component: () => import('@/components/catIndex/SchoolCatIndex.vue')
                },
                {
                    path: '/cat/search/:search?',
                    name: 'schoolCat-search',
                    component: () => import('@/components/catIndex/search/SearchIndex.vue')
                },
                {
                    path: '/cat/user',
                    name: 'userIndex',
                    component: () => import("@/views/Setting.vue")
                }
            ]
        }

    ]
})

router.beforeEach((to, from, next) => {
    const store = useStore()
    if (store.auth.user != null && to.name.startsWith('welcome-')) {
        next('/cat')
    } else if ((store.auth.user == null) && to.fullPath.startsWith('/cat')) {
        next('/')
    } else if (to.matched.length === 0) {
        next('/cat')
    } else {
        next()
    }
})

export default router
