<template>
    <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
        <div style="flex: 1;">
            <el-image style="width: 100%;height: 100%;"
                      src="../src/img/重工小猫.png" fit="cover"></el-image>
        </div>
        <el-divider style="height: 100%" direction="vertical"  border-style="double" />

        <div style="width: 25vw;height: 100vh;background-color: white">
            <router-view v-slot="{ Component }">
                <transition name="el-fade-in-linear">
                    <component :is="Component" style="height: 100%" />
                </transition>
            </router-view>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
</style>