import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import 'element-plus/dist/index.css'
import axios from "axios";
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import VueAxios from "vue-axios";
const app = createApp(App)
axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.withCredentials = true;
app.config.globalProperties.$http = axios
app.use(createPinia())
app.use(router)
app.use(ElementPlus, {
    locale: zhCn,
})
app.mount('#app')
