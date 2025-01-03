import { createApp } from 'vue';
import App from './App.vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import store from "./store/index";
import router from "./router";


const app = createApp(App);
app.use(router);
app.use(store);
app.mount('#app');

store.dispatch('initializeStore');

