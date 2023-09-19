<template>
    <el-container>
        <el-main id="context">
            <el-row v-for="cat in CatList" :key="cat.id">
                <el-col :span="20">
                    <div class="cat-business-card">
                        <div class="header">
                            <el-row>
                                <el-col :span="3">
                                    <div class="image">
                                        <el-avatar class="image" src="/img/cat-thug-life-ns.jpg"></el-avatar>
                                    </div>
                                </el-col>
                                <el-col :span="10">
                                    <p class="name">{{ cat.animalName }}</p>
                                    <n-rate :default-value="cat.score" readonly/>
                                </el-col>
                                <el-col :span="10">
                                    <n-descriptions :column="2" label-placement="left" title="描述">
                                        <n-descriptions-item class="animal-address" label="地点">
                                            {{ cat.place }}
                                        </n-descriptions-item>

                                        <n-descriptions-item class="animal-address" label="性格">
                                            {{ cat.animalCharacter }}
                                        </n-descriptions-item>

                                        <n-descriptions-item class="animal-address" label="关系网">
                                            {{ cat.relationship }}
                                        </n-descriptions-item>
                                    </n-descriptions>
                                </el-col>
                            </el-row>
                        </div>
                        <n-collapse @item-header-click="viewComments">
                            <n-collapse-item :name="cat.id" title="展开">
                              <n-tabs type="line" animated>
                                <n-tab-pane name="comment" tab="讨论">
                                  <CommentArea :CommentData="CommentData"></CommentArea>
                                </n-tab-pane>
                                <n-tab-pane name="score" tab="评分">
                                  <CommentArea :CommentData="CommentData"></CommentArea>
                                </n-tab-pane>
                              </n-tabs>
                            </n-collapse-item>
                        </n-collapse>
                    </div>
                </el-col>

                <el-col :span="4" style="margin-bottom: 5px">
                    <n-card
                            :bordered="false"
                            embedded
                            title="📖 如何成功"
                    >
                        <n-scrollbar style="max-height: 120px">
                            如果你年轻的时候不 996，你什么时候可以 996？你一辈子没有
                            996，你觉得你就很骄傲了？这个世界上，我们每一个人都希望成功，都希望美好生活，都希望被尊重，我请问大家，你不付出超越别人的努力和时间，你怎么能够实现你想要的成功？
                        </n-scrollbar>
                    </n-card>
                </el-col>
            </el-row>
        </el-main>
        <el-footer class="footer">
            <el-row>
                <el-col :span="8">
                </el-col>
                <el-col :span="8">
                    <el-pagination
                            v-model:current-page="currentPage"
                            :page-size="5" :total="CatNums"
                            layout="prev, pager, next"
                            @current-change="paginationQuery"
                    />
                </el-col>
                <el-col :span="8">
                </el-col>
            </el-row>
        </el-footer>
    </el-container>

</template>

<script setup>
import {NTabs,NTabPane,NCard, NCollapse, NCollapseItem, NDescriptions, NDescriptionsItem, NRate, NScrollbar} from 'naive-ui'
import {onMounted, ref, watch} from "vue";
import {get} from "@/net";
import {useRoute} from "vue-router";
import router from "@/router";
import CommentArea from "@/components/catIndex/catIndexComp/CommentArea.vue";

const CatList = ref()
const CatNums = ref(0)

const route = useRoute()

const CommentData = ref(0)

const currentPage = ref(parseInt(route.params.page))

watch(
    () => currentPage.value,
    () => {
        router.replace(`/cat/cat/${currentPage.value}`)
    }
)

onMounted(() => {
    if (isNaN(currentPage.value)) {
        currentPage.value = 1
    }
    get('/cat/cat/num', (data) => {
        CatNums.value = data.data
    })
    get(`/cat/cat/${currentPage.value}`, (data) => {
        CatList.value = data.data
    })
})

const paginationQuery = (val) => {
    document.getElementById("context").scrollIntoView()
    currentPage.value = val
    get(`/cat/cat/${val}`, (data) => {
        CatList.value = data.data
    })
}

const viewComments = (name) => {
    CommentData.value = name.name
}

</script>

<style scoped>
.cat-business-card {
    margin: 30px;
    background-color: rgba(243, 244, 246, 1);
    padding: 2rem;
    border-radius: 10px;
    box-shadow: 0 20px 30px -20px rgba(5, 5, 5, 0.24);
}

.header {

    align-items: center;
    grid-gap: 1rem;
    gap: 1rem;
}

.header .image {
    height: 4rem;
    width: 4rem;
    border-radius: 9999px;
    object-fit: cover;
    background-color: royalblue;
}

.stars {
    display: flex;
    justify-content: center;
    grid-gap: 0.125rem;
    gap: 0.125rem;
    color: rgba(34, 197, 94, 1);
}

.stars svg {
    height: 1rem;
    width: 1rem;
}

.name {
    margin-top: 0.25rem;
    font-size: 1.125rem;
    line-height: 1.75rem;
    font-weight: 600;
    --tw-text-opacity: 1;
    color: rgba(55, 65, 81, 1);
}

.message {
    overflow: hidden;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    margin-top: 1rem;
    color: rgba(107, 114, 128, 1);
}

.animal-address {
    border-radius: 50px;
    background: #c2c2c2;
    box-shadow: 20px 20px 60px #a5a5a5,
    -20px -20px 60px #d5d5d5;
}
</style>