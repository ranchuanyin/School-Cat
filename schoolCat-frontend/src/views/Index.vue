<template>
    <div class="common-layout">
        <el-container>
            <div>
                <el-aside class="hidden-sm-and-down" width="auto">
                    <Menu></Menu>
                </el-aside>
            </div>
            <el-container>
                <el-header style="margin-top: 10px;margin-right: 5px;margin-bottom: 5px;border-radius: 17px;background: #ffffff;box-shadow:  5px 5px 17px #d1d1d1,
             -5px -5px 17px #ffffff;">
                    <IndexHeader></IndexHeader>
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
</script>
<style scoped>


</style>