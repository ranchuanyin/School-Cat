<script setup>
import {reactive} from "vue";
import {Select} from "@element-plus/icons-vue";

const privacyForm = reactive({
  qq: false,
  wx: false,
  emile: false,
  sex: false,
  phone: false
})
</script>

<template>
  <div>
    <el-form
        :model="privacyForm"
        label-position="top"
        label-width="100px"
    >
      <el-form-item>
        <el-checkbox v-model="privacyForm.emile" label="是否公开展示我的邮箱" size="large"/>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="privacyForm.wx" label="是否公开展示我的微信" size="large"/>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="privacyForm.qq" label="是否公开展示我的QQ" size="large"/>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="privacyForm.sex" label="是否公开展示我的性别" size="large"/>
      </el-form-item>
      <el-form-item>
        <el-checkbox v-model="privacyForm.phone" label="是否公开展示我的电话号码" size="large"/>
      </el-form-item>
    </el-form>
    <el-button :icon="Select" type="success">保存隐私设置</el-button>
  </div>
</template>

<style scoped>
::v-deep(.el-form-item) {
  margin: 0;
}
</style>