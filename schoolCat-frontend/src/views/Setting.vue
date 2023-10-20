<script setup>
import {onMounted, ref} from "vue";
import {useStore} from "@/stores";
import InfoSetting from "@/components/user/info-setting.vue";
import SecuritySetting from "@/components/user/security-setting.vue";
import PrivacySetting from "@/components/user/privacy-setting.vue";

onMounted(() => {
  computeLv(store.auth.user["experience"])
})

const store = useStore()
const activeName = ref('info')

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

</script>

<template>
  <div style="display: flex;padding:20px 30px;background-color: #ffffff ;border-radius: 5px;
  border: solid 1px lightgray;opacity:0.95 ">
    <div style="flex: 1;max-width: 60vw">
      <el-tabs v-model="activeName">
        <el-tab-pane label="个人信息设置" name="info">
          <info-setting/>
        </el-tab-pane>
        <el-tab-pane label="账号安全设置" name="security">
          <security-setting/>
        </el-tab-pane>
        <el-tab-pane label="隐私设置" name="privacy">
          <privacy-setting/>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div style="width: 300px">
      <div class="user-card" style="margin: 15px 0 0 30px">
        <el-row style="padding: 5px 15px 0 15px">
          <el-col :span="8">
            <div style="width: 70px;">
              <el-avatar :size="70" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
              <div style="text-align: center;font-weight: bold">{{ store.auth.user.username }}</div>
            </div>
          </el-col>
          <el-col :span="16">
            <el-progress :color="customColors" :percentage="percentage">
              <el-button text>{{ Lv }}</el-button>
            </el-progress>
          </el-col>
        </el-row>
        <div style="padding: 10px;font-size: 13px">
          他什么都没留下
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.user-card {
  height: 300px;
  width: 260px;
  border-radius: 5px;
  border: solid 1px lightgray;
  background-color: #ffffff;
}

.user-card > div {
  border-bottom: solid 1px lightgray;
}
</style>