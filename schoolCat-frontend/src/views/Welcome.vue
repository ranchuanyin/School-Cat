<template>
    <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
        <div style="flex: 1;">
            <el-image fit="cover"
                      src="/img/重工小猫.png" style="width: 100%;height: 100%;"></el-image>
        </div>
        <el-divider border-style="double" direction="vertical" style="height: 100%"/>

        <div style="width: 25vw;height: 100vh;background-color: white">
            <router-view v-slot="{ Component }">
                <transition name="el-fade-in-linear">
                    <component :is="Component" style="height: 100%"/>
                </transition>
            </router-view>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
</style>