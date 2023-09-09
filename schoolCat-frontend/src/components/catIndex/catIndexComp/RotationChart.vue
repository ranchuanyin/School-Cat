<template>
    <n-carousel autoplay draggable>
        <img v-for="img in imgs" :src="img.url" alt="" class="carousel-img">
    </n-carousel>
</template>

<script setup>
import {NCarousel} from "naive-ui";
import {reactive} from "vue";

const imgs = reactive([{
    key: '1',
    url: '../img/cat-thug-life-ns.jpg'
}, {
    key: '2',
    url: '../img/cat-thug-life-ns.jpg'
}, {
    key: '3',
    url: '../img/cat-thug-life-ns.jpg'
}])
</script>

<style scoped>
.carousel-img {
    width: 100%;
    height: 50vh;
    object-fit: cover;
    margin: 10px;
}
</style>