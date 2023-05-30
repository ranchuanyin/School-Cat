<template>
    <div style="text-align: center;margin: 0 20px">
            <div style="margin-top: 15vh;">
                <div style="font-size: 25px;margin-bottom: 5vh;font-weight: bold">注册新用户</div>
                <div style="font-size: 14px;color: grey" >欢迎注册</div>
            </div>
            <div style="margin-top: 30px">
                <el-form :model="form" :rules="rules" @validate="onValidDate" ref="formRef">
                    <el-form-item prop="username">
                        <el-input v-model="form.username" :minlength="2" :maxlength="8" type="text" placeholder="用户名">
                            <template #prefix>
                                <el-icon><User/></el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input v-model="form.password" :minlength="6" maxlength="20" type="password" style="margin-top: 10px" placeholder="密码">
                            <template #prefix>
                                <el-icon><Lock/></el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="password_repeat">
                        <el-input v-model="form.password_repeat" :minlength="6" :maxlength="20" type="password" style="margin-top: 10px" placeholder="重复密码">
                            <template #prefix>
                                <el-icon><Lock/></el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="email">
                        <el-input v-model="form.email" style="margin-top: 10px" placeholder="电子邮箱地址">
                            <template #prefix>
                                <el-icon><Message/></el-icon>
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="code">
                        <el-input v-model="form.code" :maxlength="6" :minlength="6" type="text" placeholder="输入验证码">
                                    <template #prefix>
                                        <el-icon><EditPen /></el-icon>
                                    </template>
                                    <template #append>
                                        <el-button @click="validateEmail()" :disabled="!isEmailValid" text>{{EmailMessage}}</el-button>
                                    </template>
                                </el-input>
                    </el-form-item>
                </el-form>
            </div>
            <div style="margin-top: 5vh">
                <el-button type="warning" @click="register()" style="width: 10vw">立即注册</el-button>
            </div>
            <el-divider>
                <span style="color: grey;font-size: 10px">已有账号</span>
            </el-divider>
            <div>
                <el-link @click="router.push('/')" type="primary" style="font-size: 14px">立即登录</el-link>
            </div>
        </div>
</template>

<script setup>
import {EditPen, Lock, Message, User} from "@element-plus/icons-vue";
import router from "@/router";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";

const form =reactive({
    username:'',
    password:'',
    password_repeat:'',
    email:'',
    code:''
})
const validateUserName = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入用户名'))
    } else if (!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)) {

        callback(new Error('用户名不能包含特殊字符，只能是中文/英文'))
    }else
        callback()
}
const validatePass2 = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== form.password) {
        callback(new Error("两次输入的密码不一致!"))
    } else {
        callback()
    }
}
const rules = {
    username: [
        { validator:validateUserName,trigger:['blur','change'] },
        { min: 2, max: 8, message: '用户名长度必须在2-8个字符之间', trigger:['blur','change']},
    ],
    password:[
        { required: true, message: '请输入密码', trigger: ['blur','change'] },
        { min: 6, max: 20, message: '密码长度必须在6-20个字符之间', trigger:['blur','change']}
    ],
    password_repeat:[{ validator:validatePass2,trigger:['blur','change'] }],
    email:[
        { required: true, message: '请输入电子邮箱地址',trigger: ['blur','change'] },
        { type: 'email', message: '电子邮箱格式不正确',trigger: ['blur','change'] }
    ],
    code:[
        { required: true, message: '请输入验证码',trigger: ['blur'] },

    ]
}

const isEmailValid = ref(false)
const EmailMessage = ref("获取验证码")
let countDown = ref(60)
const validateEmail = () => {
    if (!isEmailValid)
        return
    isEmailValid.value = false
    let timer = setInterval(() => {
        if (countDown.value > 0) {
            EmailMessage.value = countDown.value+'s后重新获取验证码'
            countDown.value--
        } else {
            clearInterval(timer);
            isEmailValid.value = true; // 启用按钮
            countDown.value = 60; // 重置倒计时时间
            EmailMessage.value = "重新获取验证码"
        }
    }, 1000);
    post('/cat/auth/valid-register-email',{
        email: form.email
    },(message) => {
        ElMessage.success(message)
    })
}
const onValidDate = (prop,isValid) => {
    if (prop === 'email'){
        isEmailValid.value = isValid
    }
}

const formRef = ref()
const register = () => {
    formRef.value.validate((isValid) => {
        if (isValid){
            post('/cat/auth/register',{
                username: form.username,
                password: form.password,
                email: form.email,
                code: form.code
            },(message) => {
                ElMessage.success(message)
                router.push("/")
            })
        }else {
            ElMessage.warning('请完整填写注册表单内容！')
        }
    })
}


</script>

<style scoped>

</style>
