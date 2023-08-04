import { createApp } from 'vue'
import App from './App.vue'
import store from "@/store/store";
import router from "@/router/router";

import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {library} from "@fortawesome/fontawesome-svg-core";
import {fas} from "@fortawesome/free-solid-svg-icons";



library.add(fas)

createApp(App)
    .use(router)
    .use(store)
    .component('font-awesome-icon', FontAwesomeIcon)
    .mount('#app')
