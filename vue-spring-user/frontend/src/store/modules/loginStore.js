import {AUTHORIZATION} from "@/constants";

export default {
  namespaced: true,
  state: {
    authState: false, /* 전역 인증 상태 */
    currentUser: {}, /* 로그인중인 현재 사용자 정보 */
    jwt: '' /* jwt 인증 토큰 */
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
      state.authState = true;
      state.currentUser = user;
      state.jwt = jwt;
    },
    /**
     * 인증상태를 변경합니다.
     *
     * @param state
     */
    logout(state) {
      state.authState = false;
      state.currentUser = {};
      state.jwt = '';
    }
  },
  getters: {
    /**
     * 인증상태, 로그인한 사용자 정보, 로컬스토리지의 정보를 확인하여 인증상태를 반환합니다.
     * @param state
     * @return {boolean} 인증상태
     */
    isAuthenticated(state) {
      return state.authState;
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
