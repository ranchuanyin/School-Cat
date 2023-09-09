import {reactive} from 'vue'
import {defineStore} from 'pinia'

export const useStore = defineStore('store', () => {
    const auth = reactive({
        user: null,

    })
    const loginNum = reactive({
        loginNum: null
    })
    const isShowBarrage = reactive({
        isShowBarrage: true
    })
    return {auth, loginNum, isShowBarrage}
}, {
    persist: true
})
