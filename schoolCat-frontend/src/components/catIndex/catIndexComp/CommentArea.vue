<template>
    <el-card class="box-card" style="background-color: #F3F4F6FF">
        <div v-for="o in Comment" :key="o" class="text item">{{ 'List item ' + o }}</div>
    </el-card>
</template>

<script setup>
const CommentData = defineProps(["CommentData"])
const Comment = CommentData.CommentData
</script>

<style scoped>

</style>