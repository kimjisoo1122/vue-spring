import {createApp} from 'vue'
import App from './App.vue'
import store from "@/store/store";
import router from "@/router/router";

import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {library} from "@fortawesome/fontawesome-svg-core";
import {faPaperclip, faLock} from "@fortawesome/free-solid-svg-icons";

library.add(faPaperclip, faLock)

createApp(App)
    .use(router)
    .use(store)
    .component('font-awesome-icon', FontAwesomeIcon)
    .mount('#app')
