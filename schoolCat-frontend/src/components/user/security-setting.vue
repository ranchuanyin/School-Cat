<script setup>

import {reactive} from "vue";
import {Lock, Message, Select} from "@element-plus/icons-vue";

const securityForm = reactive({
  email: '',
  password_old: '',
  password_new: '',
  password_new_repeat: ''
})
</script>

<template>
  <div>
    <h1>
      <el-icon>
        <Message/>
      </el-icon>
      邮箱设置
    </h1>
    <el-form
        :model="securityForm"
        label-position="top"
        label-width="100px"
        style="max-width: 60vw">
      <el-form-item label="邮箱地址">
        <el-input v-model="securityForm.email"/>
      </el-form-item>
    </el-form>
    <el-button :icon="Select" type="success">保存邮箱地址</el-button>
  </div>
  <el-divider/>
  <div>
    <h1>
      <el-icon>
        <Lock/>
      </el-icon>
      密码设置
    </h1>
    <el-form
        :model="infoForm"
        label-position="top"
        label-width="100px"
        style="max-width: 60vw">
      <el-form-item label="原密码">
        <el-input v-model="securityForm.password_old" type="password"/>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input v-model="securityForm.password_new" type="password"/>
      </el-form-item>
      <el-form-item label="重复新密码">
        <el-input v-model="securityForm.password_new_repeat" type="password"/>
      </el-form-item>
    </el-form>
    <el-button :icon="Select" type="success">修改密码</el-button>
  </div>

</template>

<style scoped>

</style>