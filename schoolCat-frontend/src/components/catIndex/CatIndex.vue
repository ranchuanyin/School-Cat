<template>
    <div>
        <el-row>
            <el-col :offset="1" :span="10">
                <RotationChart></RotationChart>
            </el-col>
            <el-col :offset="1" :span="4" style="margin-top: 10px">
                <PopularOne></PopularOne>
            </el-col>
            <el-col :span="4" style="margin-top: 10px">
                <PopularTwo></PopularTwo>
            </el-col>
            <el-col :span="4" style="margin-top: 10px">
                <PopularThree></PopularThree>
            </el-col>
        </el-row>
        <el-row>
            <el-col :offset="1" :span="19">
                <Barrage>
                    <template #div>
                        <Danmaku v-show="store.isShowBarrage.isShowBarrage" :barrageData="barrageData"
                                 style="margin: 5px"></Danmaku>
                    </template>
                </Barrage>
            </el-col>
            <el-col :span="4">
                <AddBarrage @getBarrageData="getBarrageData"></AddBarrage>
            </el-col>
        </el-row>
    </div>
</template>

<script setup>
import {useStore} from "@/stores";
import PopularOne from "@/components/catIndex/catIndexComp/PopularOne.vue";
import PopularTwo from "@/components/catIndex/catIndexComp/PopularTwo.vue";
import PopularThree from "@/components/catIndex/catIndexComp/PopularThree.vue";
import Danmaku from "@/components/catIndex/catIndexComp/Danmaku.vue";
import RotationChart from "@/components/catIndex/catIndexComp/RotationChart.vue";
import AddBarrage from "@/components/catIndex/catIndexComp/AddBarrage.vue";
import {onMounted, reactive} from "vue";
import Barrage from "@/components/catIndex/catIndexComp/Barrage.vue";
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";

onMounted(() => {
    JWT()
})

const store = useStore()
const barrageData = reactive({
    id: '',
    message: '',
    avatar: '',
    name: ''
})

const getBarrageData = (val) => {
    barrageData.id = val.id
    barrageData.message = val.message
    barrageData.avatar = val.avatar
    barrageData.name = val.name
}

const JWT = () => {
    get('/cat/common/JWT', (data) => {

    }, (data) => {
        ElMessage.warning(data.message)
        router.push("/")
    })
}
</script>

<style scoped>
.box-card {
    border-radius: 14px;
    background: #fcfcfc;
    box-shadow: 5px 5px 10px #d6d6d6,
    -5px -5px 10px #ffffff;
}

</style>