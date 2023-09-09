<template>
    <el-card class="box-card" style="background-color: #F3F4F6FF;border-radius: 1rem">
        <div v-for="o in CommentData.CommentData" :key="o" class="text item">{{ 'List item ' + o }}</div>
    </el-card>
</template>

<script setup>
const CommentData = defineProps(["CommentData"])
</script>

<style scoped>

</style>