<template>
    <el-card class="box-card" style="background-color: #F3F4F6FF">
      <el-card v-for="comment in comments" style="margin-bottom: 5px">
        <el-row>
          <el-col :span="24">
            <el-row>
              <el-avatar :size="50"
                  :src="comment.avatar"
              />
              <p>{{comment.userName}}</p>
            </el-row>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="2"></el-col>
          <el-col :span="21">
            <el-text>{{comment.content}}</el-text>
          </el-col>
        </el-row>
        <el-row justify="end">
          <el-col :span="1">
            <el-icon style="cursor: pointer" :size="20"><Star /></el-icon>

          </el-col>
          <el-col :span="2">
            <el-text style="cursor: pointer" @click="activate(comment.articleId,comment.id,comment.id,comment.createBy,comments)">回复</el-text>
          </el-col>
        </el-row>

        <n-collapse v-if="comment.children.length !== 0">
          <n-collapse-item title="更多" name="1">
            <el-card v-for="children in comment.children" style="margin-bottom: 5px">
              <el-row>
                <el-col :span="24">
                  <el-row>
                    <el-avatar :size="50"
                               :src="children.avatar"
                    />
                    <p>{{children.userName}}&nbsp; 回复 &nbsp;{{children.toCommentUserName}}</p>
                  </el-row>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="2"></el-col>
                <el-col :span="22">
                  <el-text>{{children.content}}</el-text>
                </el-col>
              </el-row>
              <el-row justify="end">
                <el-col :span="1">
                  <el-icon style="cursor: pointer" :size="20"><Star /></el-icon>

                </el-col>
                <el-col :span="2">
                  <el-text style="cursor: pointer" @click="activate(comment.articleId,comment.id,children.id,children.createBy)">回复</el-text>
                </el-col>
              </el-row>
            </el-card>
          </n-collapse-item>
        </n-collapse>
      </el-card>
      <n-pagination v-if="comments.length!== 0" v-model:item-count="fatherCommentPage.total" v-model:page="page" v-model:page-size="fatherCommentPage.size"/>
    </el-card>

  <n-drawer v-model:show="active" :width="300" placement="bottom">
    <n-drawer-content title="评论">
      <el-row justify="end" style="height: 90%">
        <el-col :span="17">
          <n-input
              v-model:value="comment"
              type="textarea"
              placeholder="发送一条友善的评论"
              :autosize="{
               minRows: 5
               }"
          />
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="2">
          <el-row></el-row>
          <el-row></el-row>
          <el-row>
            <n-button @click="sendComments()" style="min-width: 80%" type="info">
              发送
            </n-button>
          </el-row>
        </el-col>

      </el-row>
    </n-drawer-content>
  </n-drawer>
</template>

<script setup>
import {NPagination,NCollapse,NCollapseItem,NDrawer,NDrawerContent,NInput,NButton} from "naive-ui"
import {onBeforeMount, reactive, ref, watch} from "vue";
import {post} from "@/net";
import {Star} from "@element-plus/icons-vue";
import {useStore} from "@/stores"
import {ElMessage} from "element-plus"
const store = useStore()
const active = ref(false);
const comment = ref(null)
const commentCopy = ref([])
const commentDTO = reactive({
  articleId:'',
  rootId:'',
  toCommentId:'',
  toCommentUserId:'',
  content:'',
  createBy:'',
  avatar:'',
})


const activate = (articleId,rootId,toCommentId,toCommentUserId,comments) => {
  active.value = true;
  commentDTO.articleId = articleId
  commentDTO.rootId = rootId
  commentDTO.toCommentId = toCommentId
  commentDTO.toCommentUserId = toCommentUserId
  commentCopy.value = comments
};

const sendComments = () => {
  commentDTO.avatar =store.auth.user.avatar
  if (comment.value === ''||comment.value === null){
    ElMessage.warning("评论不能为空")
    return
  }
  post("/cat/comment/send",{
    articleId:commentDTO.articleId,
    rootId:commentDTO.rootId,
    toCommentId:commentDTO.toCommentId,
    toCommentUserId:commentDTO.toCommentUserId,
    content:comment.value,
    createBy:store.auth.user.id,
  },(message) => {
    ElMessage.success(message.message)
    commentCopy.value.push({
          "id": 1,
          "type": "q",
          "articleId": 1,
          "rootId": -1,
          "toCommentUserId": 1,
          "toCommentUserName": "admin",
          "toCommentId": -1,
          "content": "isgBCb2j53",
          "createBy": 1,
          "createTime": "2001-08-26T17:24:38.000+00:00",
          "userName": "admin",
          "avatar": "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          "children": [
            {
              "id": 4,
              "type": "q",
              "articleId": 1,
              "rootId": 1,
              "toCommentUserId": 1,
              "toCommentUserName": "admin",
              "toCommentId": 1,
              "content": "isgBCb2j53",
              "createBy": 2,
              "createTime": "2001-08-26T17:24:38.000+00:00",
              "userName": "user",
              "avatar": "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
              "children": null
            },
            {
              "id": 3,
              "type": "B",
              "articleId": 1,
              "rootId": 1,
              "toCommentUserId": 1,
              "toCommentUserName": "admin",
              "toCommentId": 2,
              "content": "Dzh9uYTQFu",
              "createBy": 4,
              "createTime": "2008-07-26T16:20:53.000+00:00",
              "userName": "10001",
              "avatar": "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
              "children": null
            },
            {
              "id": 2,
              "type": "4",
              "articleId": 1,
              "rootId": 1,
              "toCommentUserId": 1,
              "toCommentUserName": "admin",
              "toCommentId": 1,
              "content": "XQq8ii9qCp",
              "createBy": 2,
              "createTime": "2010-05-28T08:07:44.000+00:00",
              "userName": "user",
              "avatar": "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
              "children": null
            },
            {
              "id": 5,
              "type": "4",
              "articleId": 1,
              "rootId": 1,
              "toCommentUserId": 1,
              "toCommentUserName": "admin",
              "toCommentId": 1,
              "content": "XQq8ii9qCp",
              "createBy": 2,
              "createTime": "2010-05-28T08:07:44.000+00:00",
              "userName": "user",
              "avatar": "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
              "children": null
            }
          ]
        },
        {
          "id": 6,
          "type": "B",
          "articleId": 1,
          "rootId": -1,
          "toCommentUserId": -1,
          "toCommentUserName": null,
          "toCommentId": -1,
          "content": "你好",
          "createBy": 4,
          "createTime": "2008-07-26T16:20:53.000+00:00",
          "userName": "10001",
          "avatar": "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          "children": []
        })
    active.value = false
  },(message) => {
    active.value = false
  })
}


const page = ref(1)
const comments = ref([])
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
        comments.value=message.data
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