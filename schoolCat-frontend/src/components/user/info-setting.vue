<script setup>
import {reactive} from "vue";
import {Select} from "@element-plus/icons-vue";

const infoForm = reactive({
  username: '',
  desc: '',
  qq: '',
  wx: '',
  emile: '',
  sex: '',
  phone: ''
})
</script>

<template>
  <el-form
      :model="infoForm"
      label-position="top"
      label-width="100px"
      style="max-width: 60vw">
    <el-form-item label="用户名">
      <el-input v-model="infoForm.username"/>
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="infoForm.sex" class="ml-4">
        <el-radio label="1" size="large">男</el-radio>
        <el-radio label="2" size="large">女</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="手机号">
      <el-input v-model="infoForm.phone"/>
    </el-form-item>
    <el-form-item label="qq">
      <el-input v-model="infoForm.qq"/>
    </el-form-item>
    <el-form-item label="微信">
      <el-input v-model="infoForm.wx"/>
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model="infoForm.emile"/>
    </el-form-item>
    <el-form-item label="个人简介">
      <el-input v-model="infoForm.desc" type="textarea"/>
    </el-form-item>
  </el-form>
  <el-button :icon="Select" type="success">保存个人信息设置</el-button>
</template>

<style scoped>

</style>