<template>
  <el-card shadow="always"
           style="margin-top: 10px;margin-left: 10px;margin-right: 10px;height: 98vh;">
    <el-row justify="center">
      <el-button link @click="ChangeArrow()">
        <el-icon v-if="isCollapse">
          <ArrowRight/>
        </el-icon>
        <el-icon v-if="!isCollapse">
          <ArrowLeft/>
        </el-icon>
      </el-button>
    </el-row>
    <el-menu :collapse="isCollapse" class="el-menu-vertical-demo">
      <el-menu-item index="1" @click="router.push('/cat/cat')">
        <el-icon>
            <svg class="icon" height="200" p-id="5323" t="1695739108568" version="1.1"
                 viewBox="0 0 1024 1024" width="200" xmlns="http://www.w3.org/2000/svg">
                <path d="M760.533 183.893s-56.107 74.24-68.16 117.333c-12.053 43.093-57.173 53.12-80.213 76.16-23.04 23.04-241.707 50.133-305.813 402.133 0 0 0 49.173-60.16 63.147-60.16 14.08-128.427 30.507-140.373 15.04-11.093-14.293 76.16-47.147 76.16-47.147s43.093-28.053 43.093-39.147c0-10.987-79.253 20.053-79.253 20.053S-8.639 843.625 44.587 899.838c53.12 56.107 249.707 0 286.827-16 37.12-16 91.2-24.107 116.373-2.027 25.067 22.08 42.133 32.107 128.32 27.093 86.187-5.013 158.4-5.013 158.4-5.013s33.067-32.107-28.053-47.147l-56.107-4.053s-46.08 0.96-9.067-48.107c37.12-49.173 35.093-81.173 35.093-81.173s23.04-0.96 31.04 37.12 20.053 114.347 85.227 120.32c0 0 14.08 46.08 86.187 21.013 0 0 37.12-20.053-9.067-42.133l-17.067-2.987s-72.213-135.36-69.227-175.467c0 0-8-32.107 19.093-55.147s99.307-113.28 57.173-275.733c0 0-9.067-20.053 52.16-27.093 61.12-7.04 69.227-39.147 77.227-78.187 0 0-2.987-13.013-17.067-24.107l-13.013-15.147s-18.027-61.12-101.227-55.147l-7.04-9.067-10.027-43.093s-23.04 5.973-24.107 31.04l-16-20.053c0 0.107-37.12 18.133-40.107 74.347z"
                      p-id="5324"></path>
            </svg>
        </el-icon>
        <span>猫档案</span>
      </el-menu-item>
      <el-menu-item index="6">
        <el-icon>
          <document/>
        </el-icon>
        <span>猫咪小报</span>
      </el-menu-item>
      <el-menu-item index="2">
        <el-icon>
          <document/>
        </el-icon>
        <span>猫友论坛</span>
      </el-menu-item>
      <el-menu-item index="3">
        <el-icon>
          <document/>
        </el-icon>
        <span>猫友家庭</span>
      </el-menu-item>
      <el-menu-item index="4">
        <el-icon>
          <document/>
        </el-icon>
        <span>公益活动</span>
      </el-menu-item>
      <el-menu-item index="5">
        <el-icon>
          <document/>
        </el-icon>
        <span>爪印画廊</span>
      </el-menu-item>

      <el-menu-item index="7" @click="router.push('/cat/user')">
        <el-icon>
          <setting/>
        </el-icon>
        <span>个人中心</span>
      </el-menu-item>
    </el-menu>

    <el-row align="top" style="width: 60px;">
      <el-col style="height: 10vh;width: 0;">
      </el-col>

      <el-col>
        <el-row justify="center">
          <el-avatar :size="30" shape="square" src="/img/重.png"
                     style="background-color: white;"/>
        </el-row>
      </el-col>

      <el-col style="margin-top: 2px;">
        <el-row justify="center">
          <el-avatar :size="30" shape="square" src="/img/工.png"
                     style="background-color: white;"/>
        </el-row>
      </el-col>

      <el-col style="margin-top: 2px;">
        <el-row justify="center">
          <el-avatar :size="30" shape="square" src="/img/小.png"
                     style="background-color: white;"/>
        </el-row>
      </el-col>

      <el-col style="margin-top: 2px;">
        <el-row justify="center">
          <el-avatar :size="30" shape="square" src="/img/猫.png"
                     style="background-color: white;"/>
        </el-row>
      </el-col>

      <el-col>
        <el-row justify="center">
          <el-avatar :size="90" shape="square"
                     src="/img/猫(1).png" style="background-color: white;cursor: pointer"
                     @click="router.push('/cat')"/>
        </el-row>
      </el-col>
    </el-row>
  </el-card>
</template>

<script setup>

import router from "@/router";
import {ArrowLeft, ArrowRight, Document, Setting,} from "@element-plus/icons-vue";
import {markRaw, ref} from "vue";

const isArrow = markRaw(ArrowRight)
const isCollapse = ref(true)
const ChangeArrow = () => {
  isCollapse.value = !isCollapse.value
  if (isCollapse.value) {
    isArrow.value = ArrowRight
  } else {
    isArrow.value = ArrowLeft
  }
}
</script>

<style scoped>
.el-menu {
  border-right: 0 !important;
}

.el-main {
  padding: 0;
}

.el-header {
  padding: 0;
}

.el-card__body {
  padding: 1px !important;
}
</style>