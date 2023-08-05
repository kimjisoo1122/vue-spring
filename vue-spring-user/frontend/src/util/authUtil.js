import store from "@/store/store";

/**
 * 로그인스토어에서 인증상태를 반환합니다.
 * @returns {boolean} 인증상태
 */
export function isAuthenticated() {
  return store.getters['loginStore/isAuthenticated'];
}

/**
 * 현재 ID를 조회하여 게시글 작성자와 비교합니다.
 * @returns {boolean} 현재 인증된 사용자와 작성자가 같으면 true 반환
 */
export function isCurrentUserId(userId) {
  return getCurrentUserId() === userId;
}

/**
 * 로그인스토어에서 현재 ID를 반환합니다.
 */
export function getCurrentUserId() {
  return store.getters['loginStore/getCurrentUser'].userId;
}

/**
 * 로그인스토어에서 현재 이름을 반환합니다.
 */
export function getCurrentUserName() {
  return store.getters['loginStore/getCurrentUser'].userName;
}