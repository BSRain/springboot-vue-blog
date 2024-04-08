<template>
  <div >
    <el-container class="home_container">
      <el-header>
        <el-menu
            class="el-menu-demo"
            mode="horizontal"
            :ellipsis="false"
            @select="handleSelect"
            router
        >
          <el-menu-item index="0">
            <el-icon>
              <home-filled/>
            </el-icon>
            主页
          </el-menu-item>
          <el-sub-menu index="1">
            <template #title>
              <el-icon>
                <files/>
              </el-icon>
              分类
            </template>
            <el-menu-item index="1-1">选项1</el-menu-item>
            <el-menu-item index="1-2">选项2</el-menu-item>
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="2" disabled>
            <el-icon>
              <chat-dot-square/>
            </el-icon>
            留言板
          </el-menu-item>
          <el-menu-item index="3" disabled>
            <el-icon>
              <timer/>
            </el-icon>
            归档
          </el-menu-item>
          <div class="flex-grow"/>
          <el-sub-menu index="4">
            <template #title> {{ store.auth.user.username }}</template>
            <el-menu-item index="4-1" @click="logout">退出登录</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-header>
      <el-main>
        <router-view v-slot="{ Component }"></router-view>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
    }
  }, methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    },
  },
};
</script>
<script setup>
import {get} from "../net";
import {ElMessage} from "element-plus";
import router from "../router";
import {useStore} from "../stores";
import { ChatDotSquare, Files, HomeFilled, Timer} from "@element-plus/icons-vue";

const store = useStore()

const logout = () => {
  get('/api/auth/logout', (message) => {
    ElMessage.success(message)
    store.auth.user = null
    router.push('/')
  })
}
</script>
<style scoped>
.home_container{
  height: 100%;
  width: 100%;
}
.el-header {
  padding: 0 !important;
  opacity: 0.8;
}
.el-menu {
  border: 0 !important;
}
.el-menu-demo{
  width: 100%;
}
.flex-grow {
  flex-grow: 1;
}
</style>