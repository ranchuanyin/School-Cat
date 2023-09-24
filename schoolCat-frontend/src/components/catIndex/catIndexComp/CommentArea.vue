<template>
    <el-card class="box-card" style="background-color: #F3F4F6FF">
        <div v-for="o in Comment" :key="o" class="text item">{{ 'List item ' + o }}</div>

        <n-pagination v-model:item-count="fatherCommentPage.total" v-model:page="page" :page-size="1"/>
    </el-card>
</template>

<script setup>
import {NPagination} from "naive-ui"
import {onBeforeMount, reactive, ref, watch} from "vue";
import {post} from "@/net";

const page = ref(1)
const comments = ref(null)
const CommentData = defineProps(["CommentData"])
const Comment = CommentData.CommentData
const fatherCommentPage = reactive({
        size: 5,
        total: 0
    }
)
const getComments = () => {
    post("/cat/comment/commentList", {
        articleId: Comment,
        pageNum: page.value,
        pageSize: 5
    }, (message) => {
        comments.value = message.data
        fatherCommentPage.total = message.page.total
    }, (message) => {
    })
}
onBeforeMount(getComments)
watch(
    () => page,
    () => {
        console.log(page.value)
    }
)


</script>

<style scoped>

</style>