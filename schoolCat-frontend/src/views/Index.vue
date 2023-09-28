<template>
    <div class="common-layout">
        <el-container>
            <div>
                <el-aside width="auto">
                    <Menu></Menu>
                </el-aside>
            </div>
            <el-container>
                <el-header style="margin-top: 10px;margin-right: 5px;margin-bottom: 5px;border-radius: 17px;background: #ffffff;box-shadow:  5px 5px 17px #d1d1d1,
             -5px -5px 17px #ffffff;">
                    <IndexHeader :messageList="messageList"></IndexHeader>
                </el-header>
                <el-main class="cat-index-main" style="height: 80vh;margin-right: 5px;border-radius: 17px;background-size: cover;background-image: url('/img/background.png');box-shadow:  5px 5px 17px #d1d1d1,
             -5px -5px 17px #ffffff;overflow-y: auto;overflow-x: hidden">
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script setup>
import "../assets/index.css"
import Menu from "@/components/catIndex/catindexhome/IndexMenu.vue";
import IndexHeader from "@/components/catIndex/catindexhome/IndexHeader.vue";
import {onMounted, ref} from "vue";
import {get} from "@/net";
import {ElMessage} from "element-plus";
import {useStore} from "@/stores"
import router from "@/router";
const store = useStore();
const messageList = ref([])


let socket;
// 判断当前浏览器是否支持webSocket
if(window.WebSocket){
  socket = new WebSocket("wss://www.ourcats.top/websocket")
  // 相当于channel的read事件，ev 收到服务器回送的消息
  socket.onmessage = function (ev) {
    messageList.value.push(JSON.parse(ev.data))
  }
  // 相当于连接开启
  socket.onopen = function (ev) {
    console.log("连接开启了...")
    socket.send(
        JSON.stringify({
          // 连接成功将，用户ID传给服务端
          uid: store.auth.user.id
        })
    );
  }
  // 相当于连接关闭
  socket.onclose = function (ev) {
    console.log("连接关闭了...")
  }
}else{
  alert("当前浏览器不支持webSocket")
}

</script>
<style scoped>


</style>