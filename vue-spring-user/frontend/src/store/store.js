import {createStore} from 'vuex'
import createPersistedState from 'vuex-persistedstate';
import loginStore from "@/store/modules/loginStore";

const store = createStore({
  state(){
  },
  mutations: {
  },
  getters: {
  },
  modules: {
    loginStore
  },
  plugins: [
      /* 등록된 모듈은 로컬스토리지에서 영속적으로 관리합니다. */
      createPersistedState({
        paths: ['loginStore']
      })
  ],
})

export default store