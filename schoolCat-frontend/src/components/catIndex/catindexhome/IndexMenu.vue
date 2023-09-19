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
          <icon-menu/>
        </el-icon>
        <span>猫</span>
      </el-menu-item>
      <el-menu-item index="2">
        <el-icon>
          <document/>
        </el-icon>
        <span>问答</span>
      </el-menu-item>
      <el-menu-item index="3">
        <el-icon>
          <document/>
        </el-icon>
        <span>用户</span>
      </el-menu-item>
      <el-menu-item index="4">
        <el-icon>
          <setting/>
        </el-icon>
        <span>个人中心</span>
      </el-menu-item>
    </el-menu>

    <el-row align="top" style="width: 60px;">
      <el-col style="height: 35vh;width: 0;">
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
import {ArrowLeft, ArrowRight, Document, Menu as IconMenu, Setting,} from "@element-plus/icons-vue";
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