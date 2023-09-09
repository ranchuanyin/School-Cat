<template>
    <div style="text-align: center;margin: 0 20px">
        <div style="margin-top: 15vh;">
            <div style="font-size: 25px;margin-bottom: 5vh;font-weight: bold">登录</div>
            <div style="font-size: 14px;color: grey">在进入系统之前请先输入用户名和密码进行登录</div>
        </div>
        <div style="margin-top: 30px">
            <el-input v-model="form.username" placeholder="用户名/邮箱" style="margin-top: 30px" type="text">
                <template #prefix>
                    <el-icon>
                        <User/>
                    </el-icon>
                </template>
            </el-input>
            <el-input v-model="form.password" placeholder="密码" style="margin-top: 30px" type="password">
                <template #prefix>
                    <el-icon>
                        <Lock/>
                    </el-icon>
                </template>
            </el-input>
        </div>
        <el-row style="margin-top: 10px">
            <el-col :span="12" style="text-align: left">
                <el-checkbox v-model="form.remember" label="记住我" size="large"/>
            </el-col>
            <el-col :span="12" style="text-align: right">
                <el-link @click="router.push('/forget')">忘记密码</el-link>
            </el-col>
        </el-row>
        <div style="margin-top: 5vh">
            <el-button plain style="width: 270px;" type="success" @click="login()">登录</el-button>
        </div>
        <el-divider>
            <span style="color: grey;font-size: 10px">没有账号？</span>
        </el-divider>
        <div>
            <el-button plain style="width: 270px" type="warning" @click="router.push('/register')">注册账号</el-button>
        </div>
    </div>
</template>

<script setup>
import {Lock, User} from "@element-plus/icons-vue";
import {reactive} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";
import router from "@/router";
import {useStore} from "@/stores";

const form = reactive({
    username: '',
    password: '',
    remember: false
})
const store = useStore()
const login = () => {
    if (!form.username || !form.password) {
        ElMessage.warning('请输入用户名和密码')
    } else {
        post('/cat/auth/login', {
            username: form.username,
            password: form.password,
            remember: form.remember
        }, (data) => {
            ElMessage.success(data.message)
            store.auth.user = data.data
            localStorage.setItem("SCHOOL_CAT_TOKEN", data.data.jWT)
            router.push("/cat")
        }, (data) => {
            console.log(data)
            ElMessage.warning(data.message)
        })
    }
}
</script>

<style scoped>

</style>