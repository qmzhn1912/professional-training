import { createApp } from 'vue'
import axios from 'axios'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
import piniaPluginPersist from 'pinia-plugin-persist'

const store = createPinia();
store.use(piniaPluginPersist);

const app = createApp(App);
app.use(store).use(router);

app.mount('#app');

app.config.globalProperties.$axios = axios;
app.config.globalProperties.$router = router;

