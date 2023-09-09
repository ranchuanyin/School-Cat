<template>
    <div class="flip-card">
        <div class="flip-card-inner">
            <div class="flip-card-front">
                <p class="title">最高评分</p>
                <p>Hover Me</p>
                <el-image src="/img/_cfdf09cf8446143896be2b02794fb7da_2099343116_1693128746072.png">

                </el-image>
            </div>
            <div class="flip-card-back">
                <div v-for="i in 5" class="cat-card" style="margin-bottom: 5px">
                    <div class="img"></div>
                    <div class="textBox">
                        <div class="textContent">
                            <p class="h1">Clans of Clash</p>
                        </div>
                        <div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
</script>

<style scoped>
.cat-card {
    width: 100%;
    max-width: 290px;
    height: 70px;
    background: #377906;
    border-radius: 20px;
    display: flex;
    align-items: center;
    justify-content: left;
    backdrop-filter: blur(10px);
    transition: 0.5s ease-in-out;
}

.cat-card:hover {
    cursor: pointer;
    transform: scale(1.05);
}

.img {
    width: 50px;
    height: 50px;
    margin-left: 10px;
    border-radius: 10px;
    background: linear-gradient(#d7cfcf, #9198e5);
}


.textBox {
    width: calc(100% - 90px);
    margin-left: 10px;
    color: white;
    font-family: 'Poppins' sans-serif;
}

.textContent {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.span {
    font-size: 10px;
}

.h1 {
    font-size: 16px;
    font-weight: bold;
}

.p {
    font-size: 12px;
    font-weight: lighter;
}

.flip-card {
    background-color: transparent;
    width: 13vw;
    height: 50vh;
    perspective: 1000px;
    font-family: sans-serif;
}

.title {
    font-size: 1.5em;
    font-weight: 900;
    text-align: center;
    margin: 0;
}

.flip-card-inner {
    position: relative;
    width: 100%;
    height: 100%;
    text-align: center;
    transition: transform 0.8s;
    transform-style: preserve-3d;
}

.flip-card:hover .flip-card-inner {
    transform: rotateY(180deg);
}

.flip-card-front, .flip-card-back {
    box-shadow: 0 8px 14px 0 rgba(0, 0, 0, 0.2);
    position: absolute;
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
    height: 100%;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
    border: 1px solid #5de11c;
    border-radius: 1rem;
}

.flip-card-front {
    background: linear-gradient(120deg, #c6f5aa 60%, rgb(251, 255, 222) 88%,
    rgb(235, 255, 195) 40%, rgba(159, 255, 80, 0.6) 48%);
    color: #5de11c;
}

.flip-card-back {
    background: linear-gradient(120deg, rgb(189, 255, 145) 30%, #5ff315 88%,
    #b5e79c 40%, rgb(231, 255, 160) 78%);
    color: white;
    transform: rotateY(180deg);
}


</style>