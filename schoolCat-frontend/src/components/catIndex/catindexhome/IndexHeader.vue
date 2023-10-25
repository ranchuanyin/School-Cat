<template>
    <el-row justify="start">
        <el-col :span="8">

        </el-col>
        <el-col :span="8" style="margin-top: 10px;">
                <label for="search">Search</label>
          <input id="search" v-model="searchValue" class="input"
                 pattern=".*\S.*" required="" type="search" @keyup.enter="router.push(`/cat/search/${searchValue}`)">
                <span class="caret"></span>
        </el-col>

        <el-col :span="5" style="margin-top: 15px;">
            <el-row justify="end">
                <!--消息-->
                <el-col :span="4">
                    <el-dropdown trigger="click">
                        <el-row align="top">
                            <svg class="icon" height="20" p-id="4981" t="1683127314362"
                                 version="1.1" viewBox="0 0 1024 1024" width="25" xmlns="http://www.w3.org/2000/svg">
                                <path d="M960.167183 449.688839c0-17.253966-13.980409-31.233352-31.234375-31.233352S897.699456 432.434873 897.699456 449.688839c0 177.968297-172.802648 322.749752-385.217479 322.749752-0.548492 0-1.059122 0.13303-1.600451 0.161682-6.865361-0.231267-13.842263 1.647523-19.761066 6.081509L374.512672 866.139669l0-83.289961c0-1.542122-0.237407-3.018753-0.453325-4.499477 2.406816-14.398942-5.573949-28.961612-19.739577-34.328853-137.929396-52.209082-227.055273-167.739329-227.055273-294.332539 0-17.253966-13.980409-31.233352-31.233352-31.233352s-31.233352 13.980409-31.233352 31.233352c0 146.375765 96.360651 279.546787 247.248174 344.541057l0 134.378523c0 11.834536 6.680143 22.642696 17.264199 27.939329 4.422729 2.206248 9.201569 3.294023 13.970176 3.294023 6.628978 0 13.227256-2.115174 18.738783-6.243191l158.490687-118.868271C763.656577 831.018777 960.167183 659.787928 960.167183 449.688839z"
                                      fill="#2c2c2c" p-id="4982"></path>
                                <path d="M103.473653 374.938368c16.186657 5.876847 34.121122-2.53166 39.987736-18.758226 48.802496-134.971018 200.438055-229.241055 368.766808-229.241055 169.051207 0 320.879148 94.789876 369.224226 230.501768 4.554736 12.780071 16.582677 20.761859 29.424146 20.761859 3.477194 0 7.015787-0.589424 10.482749-1.819438 16.247033-5.78475 24.727171-23.658839 18.941398-39.906895C882.347816 173.778882 710.317765 64.47136 512.228197 64.47136c-197.224874 0-369.021611 108.698654-427.513794 270.480294C78.848813 351.167988 87.246063 369.071753 103.473653 374.938368z"
                                      fill="#2c2c2c" p-id="4983"></path>
                                <path d="M390.596999 300.54503c-17.253966 0-31.233352 13.980409-31.233352 31.233352l0 30.470989c0 17.253966 13.980409 31.233352 31.233352 31.233352s31.234375-13.980409 31.234375-31.233352l0-30.470989C421.830351 314.524416 407.850965 300.54503 390.596999 300.54503z"
                                      fill="#2c2c2c" p-id="4984"></path>
                                <path d="M634.366955 300.54503c-17.253966 0-31.234375 13.980409-31.234375 31.233352l0 30.470989c0 17.253966 13.980409 31.233352 31.234375 31.233352s31.233352-13.980409 31.233352-31.233352l0-30.470989C665.60133 314.524416 651.620921 300.54503 634.366955 300.54503z"
                                      fill="#2c2c2c" p-id="4985"></path>
                                <path d="M589.507258 441.99153c-14.305821-9.405207-33.593096-5.38873-43.159986 8.764618-0.132006 0.193405-13.614066 19.754926-34.172287 19.754926-19.989263 0-32.423457-18.098193-33.267685-19.36914-9.160637-14.417361-28.254507-18.809391-42.834574-9.770528-14.650675 9.109472-19.155269 28.367071-10.055007 43.017746 11.214413 18.047028 41.970904 48.589648 86.157265 48.589648 43.963281 0 75.105558-30.318516 86.574774-48.223305C607.970772 470.358601 603.78238 451.396737 589.507258 441.99153z"
                                      fill="#2c2c2c" p-id="4986"></path>
                            </svg>
                            <el-col style="">
                              <n-badge :offset="[3, -17]" :value="messageList.length" :max="15">
                                <el-text size="small">消息</el-text>
                              </n-badge>
                            </el-col>
                        </el-row>
                        <template #dropdown>
                            <div class="card">
                                <div class="tools">
                                    <div class="circle">
                                        <span class="red box"></span>
                                    </div>
                                    <div class="circle">
                                        <span class="yellow box"></span>
                                    </div>
                                    <div class="circle">
                                        <span class="green box"></span>
                                    </div>
                                </div>
                                <div class="card__content">
                                    <el-card shadow="hover" v-for="message in messageList"> {{ message.message }} </el-card>
                                </div>
                            </div>
                        </template>
                    </el-dropdown>
                </el-col>
                <!--收藏-->
                <el-col :span="4">
                    <el-dropdown trigger="click">
                        <el-row align="top">
                            <svg class="icon" height="20" p-id="15659" t="1683129053227"
                                 version="1.1" viewBox="0 0 1024 1024" width="25" xmlns="http://www.w3.org/2000/svg">
                                <path d="M512 720.416l232.096 171.904a19.2 19.2 0 0 0 16.864 2.88c9.536-2.88 14.592-12.224 11.84-20.864l-88.96-281.6 237.344-176.96a16.48 16.48 0 0 0 6.816-13.152c0-9.152-7.904-16.928-18.112-16.96l-292-0.448-88.48-277.12a17.568 17.568 0 0 0-11.776-11.264c-9.856-2.976-20.16 2.304-23.04 11.264l-88.48 277.12-292 0.48a18.624 18.624 0 0 0-14.624 6.944 16.096 16.096 0 0 0 3.328 23.136l237.376 176.96-88.992 281.6a15.904 15.904 0 0 0 2.72 14.688c6.048 7.744 17.856 9.312 25.984 3.296L512 720.416z m-194.016 223.36a83.008 83.008 0 0 1-114.56-15.424 79.904 79.904 0 0 1-13.28-73.28l75.296-238.24-200.864-149.76a80.096 80.096 0 0 1-15.424-113.92 82.624 82.624 0 0 1 64.864-31.456l245.312-0.384 74.304-232.672c13.6-42.56 59.52-66.112 102.56-53.024A81.536 81.536 0 0 1 590.4 88.64l74.304 232.64 245.312 0.416c45.184 0.064 82.08 36.16 82.016 81.024a80.48 80.48 0 0 1-32.576 64.352l-200.864 149.76 75.296 238.24c13.568 42.976-11.072 88.352-54.496 101.408a83.2 83.2 0 0 1-73.344-12.736L512 800.064l-194.016 143.68z"
                                      fill="#000000" p-id="15660"></path>
                            </svg>
                            <el-col style="">
                                <el-text size="small">收藏</el-text>
                            </el-col>
                        </el-row>
                        <template #dropdown>
                            <div class="card">
                                <div class="tools">
                                    <div class="circle">
                                        <span class="red box"></span>
                                    </div>
                                    <div class="circle">
                                        <span class="yellow box"></span>
                                    </div>
                                    <div class="circle">
                                        <span class="green box"></span>
                                    </div>
                                </div>
                                <div class="card__content">

                                </div>
                            </div>
                        </template>
                    </el-dropdown>
                </el-col>
            </el-row>
        </el-col>
        <el-col :span="1" style="margin-top: 15px;">
            <n-switch :rail-style="railStyle" :rubber-band="true" @update:value="changeShow">
                <template #checked>
                    弹幕
                </template>
                <template #unchecked>
                    关闭
                </template>
            </n-switch>
        </el-col>
        <!--头像-->
        <el-col :span="2">
            <el-row justify="center" style="margin-top: 10px">
                <el-dropdown trigger="click">
                    <el-avatar
                            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
                    <template #dropdown>
                        <div class="card">
                            <div class="tools">
                                <div class="circle">
                                    <span class="red box"></span>
                                </div>
                                <div class="circle">
                                    <span class="yellow box"></span>
                                </div>
                                <div class="circle">
                                    <span class="green box"></span>
                                </div>
                            </div>
                            <div class="card__content">
                                <el-row justify="center">
                                    <el-avatar :size="50"
                                               src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
                                </el-row>
                                <el-row justify="center" style="margin-top: 5px">
                                    <el-text tag="b">{{ username }}</el-text>
                                </el-row>
                                <el-row justify="center">
                                    <el-progress :color="customColors" :percentage="percentage"
                                                 type="dashboard"/>
                                </el-row>
                                <el-row justify="center">
                                    <el-text tag="b">{{ Lv }}</el-text>
                                </el-row>

                                <el-row justify="end" style="margin-right: 10px">
                                    <el-button :icon="SwitchButton" circle type="danger"
                                               @click="logout()"/>
                                </el-row>
                            </div>
                        </div>
                    </template>
                </el-dropdown>
            </el-row>
        </el-col>
    </el-row>
</template>

<script setup>
import {NBadge, NSwitch} from "naive-ui"
import {onMounted, ref, watch} from 'vue'
import {SwitchButton} from '@element-plus/icons-vue'
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores";

const props = defineProps(["messageList"])
const messageList = props.messageList

watch(
    () => messageList.length,
    () => {
    }
)

const store = useStore()
onMounted(() => {
    computeLv(store.auth.user["experience"])
})
const username = ref(store.auth.user.username)
//计算Lv等级
const percentage = ref(0)
const Lv = ref("LV 1")
const computeLv = (experience) => {
  let lv = experience / 100
  switch (parseInt(String(lv))) {

    case 1:
      Lv.value = 'Lv 1'
      percentage.value = Math.round(experience / 200 * 100)
      break;
    case 2:
    case 3:
      Lv.value = 'Lv 2'
      percentage.value = Math.round((experience - 200) / 200 * 100)
      break;
    case 4:
    case 5:
      Lv.value = 'Lv 3'
      percentage.value = Math.round((experience - 400) / 200 * 100)
      break;
    case 6:
    case 7:
      Lv.value = 'Lv 4'
      percentage.value = Math.round((experience - 600) / 100 * 100)
      break;
    case 8:
      Lv.value = 'Lv 5'
      percentage.value = Math.round((experience - 700) / 100 * 100)
      break;
  }
}

const customColors = [
    {color: '#f56c6c', percentage: 20},
    {color: '#e6a23c', percentage: 40},
    {color: '#5cb87a', percentage: 60},
    {color: '#1989fa', percentage: 80},
    {color: '#6f7ad3', percentage: 100},
]

const changeShow = () => {
    store.isShowBarrage.isShowBarrage = !store.isShowBarrage.isShowBarrage

}
/**
 * logout退出登录方法
 */
const logout = () => {
    get('/cat/auth/logout', (data) => {
        store.auth.user = null
        localStorage.removeItem("SCHOOL_CAT_TOKEN")
        ElMessage.success(data.message)
        router.push('/')
    })
}

const railStyle = ({
                       focused,
                       checked
                   }) => {
    const style = {};
    if (!checked) {
        style.background = "#d03050";
        if (focused) {
            style.boxShadow = "0 0 0 2px #d0305040";
        }
    } else {
        style.background = "#2080f0";
        if (focused) {
            style.boxShadow = "0 0 0 2px #2080f040";
        }
    }
    return style;
}

/**
 * 搜索绑定的ref
 */
const searchValue = ref(null)
</script>

<style scoped>
.input {
    color: black;
    font: 1em/1.5 Hind, sans-serif;
}

form,
.input,
.caret {
    margin: auto;
}

form {
    position: relative;
    width: 100%;
    max-width: 20em;
}

.input,
.caret {
    display: block;
    transition: all calc(1s * 0.5) linear;
}

.input {
    background: transparent;
    border-radius: 50%;
    box-shadow: 0 0 0 0.25em inset;
    caret-color: #255ff4;
    width: 2em;
    height: 2em;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
}

.input:focus,
.input:valid {
    background: powderblue;
    border-radius: 0.25em;
    box-shadow: none;
    padding: 0.75em 1em;
    transition-duration: calc(1s * 0.25);
    transition-delay: calc(1s * 0.25);
    width: 100%;
    height: 3em;
}

.input:focus {
    animation: showCaret 1s steps(1);
    outline: transparent;
}

.input:focus + .caret,
.input:valid + .caret {
    animation: handleToCaret 1s linear;
    background: transparent;
    width: 1px;
    height: 1.5em;
    transform: translate(0, -1em) rotate(-180deg) translate(7.5em, -0.25em);
}

.input::-webkit-search-decoration {
    -webkit-appearance: none;
}

label {
    color: #e3e4e8;
    overflow: hidden;
    position: absolute;
    width: 0;
    height: 0;
}

.caret {
    background: black;
    border-radius: 0 0 0.125em 0.125em;
    margin-bottom: -0.6em;
    width: 0.25em;
    height: 1em;
    transform: translate(0, -1em) rotate(-45deg) translate(0, 0.875em);
    transform-origin: 50% 0;
}

/* Animations */
@keyframes showCaret {
    from {
        caret-color: transparent;
    }

    to {
        caret-color: #255ff4;
    }
}

@keyframes handleToCaret {
    from {
        background: currentColor;
        width: 0.25em;
        height: 1em;
        transform: translate(0, -1em) rotate(-45deg) translate(0, 0.875em);
    }

    25% {
        background: currentColor;
        width: 0.25em;
        height: 1em;
        transform: translate(0, -1em) rotate(-180deg) translate(0, 0.875em);
    }

    50%,
    62.5% {
        background: #255ff4;
        width: 1px;
        height: 1.5em;
        transform: translate(0, -1em) rotate(-180deg) translate(7.5em, 2.5em);
    }

    75%,
    99% {
        background: #255ff4;
        width: 1px;
        height: 1.5em;
        transform: translate(0, -1em) rotate(-180deg) translate(7.5em, -0.25em);
    }

    87.5% {
        background: #255ff4;
        width: 1px;
        height: 1.5em;
        transform: translate(0, -1em) rotate(-180deg) translate(7.5em, 0.125em);
    }

    to {
        background: transparent;
        width: 1px;
        height: 1.5em;
        transform: translate(0, -1em) rotate(-180deg) translate(7.5em, -0.25em);
    }
}
</style>