import {createStore} from 'vuex'
import createPersistedState from 'vuex-persistedstate';
import loginStore from "@/store/modules/loginStore";
import boardFileStore from "@/store/modules/boardfileStore";
/* store변경은 store에서만 /*/
const store = createStore({
  state(){
  },
  mutations: {
  },
  getters: {
  },
  modules: {
    loginStore, boardFileStore
  },
  plugins: [
      createPersistedState({
        paths: ['loginStore']
      })
  ],
})

export default store