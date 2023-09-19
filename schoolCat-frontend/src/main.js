import {createApp} from 'vue'
import {createPinia} from 'pinia'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import axios from "axios";
import 'element-plus/theme-chalk/display.css'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import ElementPlus from 'element-plus'


const app = createApp(App)
app.use(ElementPlus, {
    locale: zhCn,
})

axios.defaults.baseURL = "http://localhost:8080"
axios.defaults.headers.common["Authorization"] = localStorage.getItem("SCHOOL_CAT_TOKEN")
axios.interceptors.response.use(function (response) {
    if (response.data.status.toString().includes("40")){
        window.location.href="http://"+window.location.host;
    }
    // 对响应数据做点什么
    return response
}, function (error) {
    // 对响应错误做点什么
    console.log(error)
})
app.use(createPinia().use(piniaPluginPersistedstate))
app.use(router)
app.mount('#app')

