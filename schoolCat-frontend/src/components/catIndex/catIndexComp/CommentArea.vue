<template>
    <el-card class="box-card" style="background-color: #F3F4F6FF">
        <el-row>
            <el-col :span="3">
                <el-avatar :size="60"
                           :src="store.auth.user.avatar"
                />
            </el-col>
            <el-col :span="16" style="margin-top: 10px">
                <n-input v-model:value="comment" placeholder="发送一条友善的评论" size="large" type="text"/>
            </el-col>
            <el-col :span="4" style="margin-top: 11px">
                <n-button type="info"
                          @click="activateTwo(Comment,-1,-1,store.auth.user.id,null,store.auth.user.username,comments)">
                    发送
                </n-button>
            </el-col>
        </el-row>
        <el-card v-for="comment in comments" style="margin-bottom: 5px">
            <el-row>
                <el-col :span="24">
                    <el-row>
                        <el-avatar :size="50"
                                   :src="comment.avatar"
                        />
                        <p>{{ comment.userName }}</p>
                    </el-row>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="2"></el-col>
                <el-col :span="21">
                    <el-text>{{ comment.content }}</el-text>
                </el-col>
            </el-row>
            <el-row justify="end">
                <el-col :span="1">
                    <el-icon :size="20" style="cursor: pointer">
                        <Star/>
                    </el-icon>

                </el-col>
                <el-col :span="2">
                    <el-text style="cursor: pointer"
                             @click="activate(comment.articleId,comment.id,comment.id,comment.createBy,comment.userName,store.auth.user.username,comment.children)">
                        回复
                    </el-text>
                </el-col>
            </el-row>

            <n-collapse v-if="comment.children.length !== 0">
                <n-collapse-item name="1" title="更多">
                    <el-card v-for="children in comment.children" style="margin-bottom: 5px">
                        <el-row>
                            <el-col :span="24">
                                <el-row>
                                    <el-avatar :size="50"
                                               :src="children.avatar"
                                    />
                                    <p>{{ children.userName }}&nbsp; 回复 &nbsp;{{ children.toCommentUserName }}</p>
                                </el-row>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="2"></el-col>
                            <el-col :span="22">
                                <el-text>{{ children.content }}</el-text>
                            </el-col>
                        </el-row>
                        <el-row justify="end">
                            <el-col :span="1">
                                <el-icon :size="20" style="cursor: pointer">
                                    <Star/>
                                </el-icon>

                            </el-col>
                            <el-col :span="2">
                                <el-text style="cursor: pointer"
                                         @click="activate(comment.articleId,comment.id,children.id,children.createBy,children.userName,store.auth.user.username,comment.children)">
                                    回复
                                </el-text>
                            </el-col>
                        </el-row>
                    </el-card>
                </n-collapse-item>
            </n-collapse>
        </el-card>
        <n-pagination v-if="comments.length!== 0" v-model:item-count="fatherCommentPage.total" v-model:page="page"
                      v-model:page-size="fatherCommentPage.size"/>
    </el-card>

    <n-drawer v-model:show="active" :width="300" placement="bottom">
        <n-drawer-content title="评论">
            <el-row justify="end" style="height: 90%">
                <el-col :span="17">
                    <n-input
                        v-model:value="comment"
                        :autosize="{
               minRows: 5
               }"
                        placeholder="发送一条友善的评论"
                        type="textarea"
                    />
                </el-col>
                <el-col :span="1"></el-col>
                <el-col :span="2">
                    <el-row></el-row>
                    <el-row></el-row>
                    <el-row>
                        <n-button style="min-width: 80%" type="info" @click="sendComments()">
                            发送
                        </n-button>
                    </el-row>
                </el-col>

            </el-row>
        </n-drawer-content>
    </n-drawer>
</template>

<script setup>
import {NButton, NCollapse, NCollapseItem, NDrawer, NDrawerContent, NInput, NPagination} from "naive-ui"
import {onBeforeMount, reactive, ref, watch} from "vue";
import {post} from "@/net";
import {Star} from "@element-plus/icons-vue";
import {useStore} from "@/stores"
import {ElMessage} from "element-plus"

const page = ref(1)
const comments = ref([])
const CommentData = defineProps(["CommentData"])
const Comment = CommentData.CommentData
const fatherCommentPage = reactive({
        size: 5,
        total: 0
    }
)
const store = useStore()
const active = ref(false);
const comment = ref(null)
const commentCopy = ref([])
const commentDTO = reactive({
    id: '',
    type: '',
    articleId: '',
    rootId: '',
    toCommentId: '',
    toCommentUserId: '',
    toCommentUserName: '',
    content: '',
    createBy: '',
    createTime: '',
    userName: '',
    avatar: '',
    children: []
})


const activate = (articleId, rootId, toCommentId, toCommentUserId, toCommentUserName, userName, comments) => {
    active.value = true;
    commentDTO.toCommentUserName = toCommentUserName
    commentDTO.userName = userName
    commentDTO.articleId = articleId
    commentDTO.rootId = rootId
    commentDTO.toCommentId = toCommentId
    commentDTO.toCommentUserId = toCommentUserId
    commentCopy.value = comments

};
const activateTwo = (articleId, rootId, toCommentId, toCommentUserId, toCommentUserName, userName, comments) => {
    commentDTO.toCommentUserName = toCommentUserName
    commentDTO.userName = userName
    commentDTO.articleId = articleId
    commentDTO.rootId = rootId
    commentDTO.toCommentId = toCommentId
    commentDTO.toCommentUserId = toCommentUserId
    commentCopy.value = comments

    sendComments()
};

const sendComments = () => {
    commentDTO.avatar = store.auth.user.avatar
    const copy = comment.value
    commentDTO.content = copy
    if (comment.value === '' || comment.value === null) {
        ElMessage.warning("评论不能为空")
        return
    }
    post("/cat/comment/send", {
        articleId: commentDTO.articleId,
        rootId: commentDTO.rootId,
        toCommentId: commentDTO.toCommentId,
        toCommentUserId: commentDTO.toCommentUserId,
        content: comment.value,
        createBy: store.auth.user.id,
    }, (message) => {
        ElMessage.success(message.message)
        commentCopy.value.push(commentDTO)
        active.value = false
        comment.value = null
    }, (message) => {
        active.value = false
    })
}



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