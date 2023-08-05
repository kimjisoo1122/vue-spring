import store from "@/store/store";

/**
 *로그인스토어에서 인증상태를 반환합니다.
 * @returns {any}
 */
export function isAuthenticated() {
  return store.getters['loginStore/isAuthenticated'];
}

/**
 * 로그인스토어에서 현재 ID를 조회하여 게시글 작성자와 비교합니다.
 * @returns {any}
 */
export function isCurrentUserId(userId) {
  return store.getters['loginStore/getCurrentUserId'] === userId;
}