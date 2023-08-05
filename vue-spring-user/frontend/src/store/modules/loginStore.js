import {AUTHORIZATION} from "@/constants";

export default {
  namespaced: true,
  state: {
    authState: false,
    currentUserId: '',
  },
  mutations: {
    login(state, {userId, jwt}) {
      localStorage.removeItem(AUTHORIZATION);

      state.authState = true;
      state.currentUserId = userId;
      localStorage.setItem(AUTHORIZATION, jwt);
    },
    logout(state) {
      localStorage.removeItem(AUTHORIZATION);
      state.authState = false;
      state.currentUserId = '';
    }
  },
  getters: {
    isAuthenticated(state) {
      return state.authState && state.currentUserId !== '' && localStorage.getItem(AUTHORIZATION);
    },
    getCurrentUserId(state) {
      return state.currentUserId;
    },
  }
}
