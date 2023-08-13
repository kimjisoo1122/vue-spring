import {AUTHORIZATION} from "@/constants";

export default {
  namespaced: true,
  state: {
    authState: false, /* 전역 인증 상태 */
    currentUser: null, /* 로그인중인 현재 사용자 정보 */
    jwt: null /* jwt 인증 토큰 */
  },
  mutations: {
    /**
     * 로그인에 성공시 현재 인증 상태를 저장합니다.
     * 서버로 부터 받은 사용자 정보를 스테이트에 저장합니다.
     * jwt를 로컬스토리지에 저장합니다.
     *
     * @param state authState, currentUser
     * @param user 로그인한 사용자 정보
     * @param jwt 발급된 jwt
     */
    login(state, {user, jwt}) {
      localStorage.removeItem(AUTHORIZATION);

      state.authState = true;
      state.currentUser = user;
      state.jwt = jwt;
    },
    /**
     * 로컬스토리지에서 jwt를 지웁니다.
     * 인증상태를 변경합니다.
     * 사용자 정보를 빈 값으로 초기화합니다.
     *
     * @param state
     */
    logout(state) {
      localStorage.removeItem(AUTHORIZATION);
      state.authState = false;
      state.currentUser = null;
      state.jwt = null;
    }
  },
  getters: {
    /**
     * 인증상태, 로그인한 사용자 정보, 로컬스토리지의 정보를 확인하여 인증상태를 반환합니다.
     * @param state
     * @return {boolean} 인증상태
     */
    isAuthenticated(state) {
      return state.authState
          && state.currentUser !== null
          && state.jwt != null;
    },
    /**
     * 사용자 정보를 반환합니다.
     *
     * @param state
     * @return 현재 사용자 정보
     */
    currentUser(state) {
      return state.currentUser;
    },

    /**
     * jwt를 조회합니다.
     *
     * @param state
     * @returns jwt
     */
    jwt(state) {
      return state.jwt;
    },
  }
}
