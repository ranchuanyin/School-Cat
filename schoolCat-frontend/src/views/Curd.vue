<template>
  <div>
      <div class="handle-box">
          <el-input v-model="query.animalName" placeholder="用户名" class="handle-input mr10" clearable></el-input>
          <el-select v-model="query.place" placeholder="状态" class="handle-select mr10" clearable>
              <el-option key="1" label="正常" value="NORMAL"></el-option>
              <el-option key="2" label="锁定" value="LOCKED"></el-option>
          </el-select>
          <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
          <el-button type="primary" :icon="Plus" @click="addVisible = true;Object.assign(form, new User())"
                     v-action:user:create>新增
          </el-button>
      </div>
      <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
          <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column>
          <el-table-column prop="username" label="用户名"></el-table-column>
          <el-table-column prop="gender" label="性别">
              <template #default="{ row }">
                  <span>{{ row.gender === 'MALE' ? '男' : '女' }}</span>
              </template>
          </el-table-column>
          <el-table-column label="头像" align="center">
              <template #default="scope">
                  <el-image
                          class="table-td-thumb"
                          :src="scope.row.avatar"
                          :z-index="10"
                          :preview-src-list="[scope.row.avatar]"
                          preview-teleported
                  >
                  </el-image>
              </template>
          </el-table-column>
          <el-table-column label="状态" align="center">
              <template #default="scope">
                  <el-tag
                          :type="scope.row.state === 'NORMAL' ? 'success' : scope.row.state === 'LOCKED' ? 'danger' : ''"
                  >
                      {{ scope.row.state === 'NORMAL' ? '正常' : scope.row.state === 'LOCKED' ? '锁定' : '' }}
                  </el-tag>
              </template>
          </el-table-column>

          <el-table-column prop="createdTime" label="注册时间"></el-table-column>
          <el-table-column prop="orgFullName" label="所属组织"></el-table-column>
          <el-table-column label="操作" width="300" fixed="right">
              <template #default="scope">
                  <el-popconfirm title="确定要禁用?" @confirm="handleDisable(scope.row)">
                      <template #reference>
                          <el-button v-if="scope.row.state==='NORMAL'" text :icon="Lock" v-action:user:update>
                              禁用
                          </el-button>
                      </template>
                  </el-popconfirm>
                  <el-popconfirm title="确定要启用?" @confirm="handleEnable(scope.row)">
                      <template #reference>
                          <el-button v-if="scope.row.state==='LOCKED'" text :icon="Unlock" v-action:user:update>
                              启用
                          </el-button>
                      </template>
                  </el-popconfirm>
                  <el-button text :icon="Edit" @click="handleEdit(scope.$index, scope.row)" v-action:user:update>
                      编辑
                  </el-button>
                  <el-button text :icon="Delete" class="red" @click="handleDelete(scope.row)" v-action:user:delete>
                      删除
                  </el-button>
              </template>
          </el-table-column>
      </el-table>
  </div>
</template>

<script>
import {reactive, ref} from "vue";
import {ElMessage, ElMessageBox} from 'element-plus';

export default {
    name: "curd"
}

const query = reactive({
    animalName: '',
    place: '',
    pageIndex: 1,
    pageSize: 10
});
</script>

<style scoped>

</style>