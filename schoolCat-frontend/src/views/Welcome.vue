<template>
      <el-row style="width: 100vw;height: 100vh;overflow: hidden">
        <el-col :sm="14" :md="17" :lg="17" :xl="17" class="hidden-xs-only" :span="18">
          <div  style="flex: 1;">
            <el-image fit="cover"
                      src="/img/重工小猫.png" style="width: 60vw;height: 100vh;"></el-image>
          </div>
        </el-col>

        <el-divider class="hidden-xs-only" border-style="double" direction="vertical" style="height: 100%"/>
        <el-col :xs="24" :sm="6" :md="6" :lg="6" :xl="2">
          <el-row class="hidden-sm-and-up" justify="center">
            <el-col :span="5">
                <el-image style="width: 100px;height: 100px" src="/img/猫(1).png" />
            </el-col>
          </el-row>

          <div style="height: 100vh;background-color: white">
            <router-view v-slot="{ Component }">
              <transition name="el-fade-in-linear">
                <component :is="Component" style="height: 100%"/>
              </transition>
            </router-view>
          </div>
        </el-col>
      </el-row>
</template>

<script setup>
import 'element-plus/theme-chalk/display.css'
</script>

<style scoped>
</style>