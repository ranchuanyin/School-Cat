<template>
  {{ search }}
</template>

<script setup>
import {useRoute} from "vue-router";
import {ref, watch} from "vue";

const route = useRoute()
const search = ref(route.params.search)
watch(
    () => route.params.search,
    () => {
      search.value = route.params.search
    })
</script>

<style>

</style>