import { createApp } from 'vue';
import App from './App.vue';
import vuetify from './plugin/vuetify.js';
import router from './router/index.js';
import Aos from 'aos';
import { createPinia } from 'pinia';
import toast from 'vue-toastification';
import './assets/style/css-vars.css';
import 'vue-toastification/dist/index.css';

const pinia = createPinia();
const toastOptions = {
    position: 'bottom-right',
    timeout: 5000
};
Aos.init({
    offset: 30,
    once: true,
    useClassNames: true,
    initClassName: false,
    animatedClassName: 'animate__animated'
});
createApp(App)
    .use(pinia)
    .use(router)
    .use(vuetify)
    .use(toast, toastOptions)
    .mount('#app');