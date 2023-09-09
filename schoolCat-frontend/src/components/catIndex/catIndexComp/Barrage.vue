<template>
    <div class="card">
        <div style="margin: 10px">
            <slot name="div">

            </slot>
        </div>
    </div>

</template>

<script setup>

</script>

<style scoped>
.card {
    width: 100%;
    height: 100%;
    border-radius: 1rem;
    background-color: #00ffd3;
    background-image: linear-gradient(43deg, #a7b4ff 0%, #fff0b2 46%, #b2ffe4 100%);
    box-shadow: rgba(0, 0, 0, 0.17) 0px -23px 25px 0px inset, rgba(0, 0, 0, 0.15) 0px -36px 30px 0px inset, rgba(0, 0, 0, 0.1) 0px -79px 40px 0px inset, rgba(0, 0, 0, 0.06) 0px 2px 1px, rgba(0, 0, 0, 0.09) 0px 4px 2px, rgba(0, 0, 0, 0.09) 0px 8px 4px, rgba(0, 0, 0, 0.09) 0px 16px 8px, rgba(0, 0, 0, 0.09) 0px 32px 16px;
}
</style>