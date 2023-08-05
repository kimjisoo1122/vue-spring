/**
 * jwt이름을 포맷팅합니다.
 * @param jwt
 * @returns {string} 'Bearer '+ jwt
 */
export function formatJwt(jwt) {
  return 'Bearer ' + jwt;
}

/**
 * 자바 LocalDateTime 형식의 문자열을 yyyy.MM.dd HH:mm 포맷으로 변경
 * @param date 자바 LocalDateTime 형식의 문자열
 * @returns {string} yyyy.MM.dd HH:mm 포맷
 */
export function formatDate(date) {
  return date.replace('T', ' ').replaceAll('-', '.').slice(0, -3);
}
