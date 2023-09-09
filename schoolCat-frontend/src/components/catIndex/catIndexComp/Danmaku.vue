<template>
    <vue-danmaku ref="danmaku" v-model:danmus="danmus" style="position: absolute;height:100%; width:78%;"
                 v-bind="config">
        <template v-slot:dm="{ danmu, index }">
            <div class="danmu-item">
                <img :src="danmu.avatar" alt="" class="img"/>
                <el-text size="small">{{ danmu.message }}</el-text>
            </div>
        </template>
    </vue-danmaku>
</template>

<script setup>
import VueDanmaku from 'vue3-danmaku'
import {reactive, ref, watch} from "vue";
import {ElMessage} from "element-plus";

const danmaku = ref(null)
const danmus = ref([{
    avatar: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
    name: "X",
    message: "你好"
},
    {avatar: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png", name: "X", message: "你好"},
    {avatar: "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png", name: "X", message: "你好"}
])
watch(
    () => barrageData.barrageData.message,
    () => {
        danmus.value.push(barrageData.barrageData)
        ElMessage.success("发送成功")
        danmaku.value.push(barrageData.barrageData)
    }
)
const barrageData = defineProps(["barrageData"])
const config = reactive({
    channels: 5, // 轨道数量，为0则弹幕轨道数会撑满容器
    useSlot: true, // 是否开启slot
    loop: true, // 是否开启弹幕循环
    speeds: 150, // 弹幕速度，实际为弹幕滚动完一整屏的秒数，值越小速度越快
    fontSize: 15, // 文本模式下的字号
    top: 20, // 弹幕轨道间的垂直间距
    right: 10, // 同一轨道弹幕的水平间距
    debounce: 200, // 弹幕刷新频率（多少毫秒插入一条弹幕，建议不小于50）
    randomChannel: true, // 随机弹幕轨道
})

</script>

<style scoped>
.danmu-item {
    display: flex;
    align-items: center;

    .img {
        height: 25px;
        width: 25px;
        border-radius: 50%;
        margin-right: 5px;
    }
}
</style>