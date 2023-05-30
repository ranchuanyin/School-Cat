<script setup>
import {get} from "@/net";
import {useStore} from "@/stores";
import router from "@/router";
import {onMounted, onBeforeUnmount } from 'vue';
const store = useStore()

onMounted(startTimer)
function startTimer() {
    // 设置定时器，每30分钟执行一次
    setInterval(() => {
        // 执行定时任务的逻辑
        console.log('定时任务执行');
    }, 30*60*1000); // 每30分钟（30 * 60 * 1000毫秒）
}

if(store.auth.user == null) {
    get('/cat/user/me', (message) => {
        store.auth.user = message
        router.push('/index')
    }, () => {
        store.auth.user = null
    })
}
</script>

<template>
    <router-view/>
</template>

<style scoped>

</style>