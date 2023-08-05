import axios from "@/api/config/axios";

/**
 * 회원가입을 요청합니다.
 * @param user 가입자 정보
 * @returns {Promise<void>}
 * 에러필드에 에러메시지가 담겨진 객체를 반환합니다.
 */
export function signUp(user) {
  return axios.post('/api/users', user)
      .then()
      .catch(({response: {data: {errorFields}}}) => {
        throw {
          data: errorFields
        }
      });
}

/**
 * 아이디 중복체크를 합니다.
 * @param userId 입력한 아이디
 * @returns {Promise<boolean>} 중복유무
 */
export const doubleCheckId = userId => {
  return axios.get('/api/users/exits', {
    params: {
      userId: userId
    }})
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      })
}

/**
 * 사용자 정보를 조회합니다.
 * @param userId 사용자 아이디
 * @returns {Promise<Object>} 사용자 정보
 */
export const getUser = userId => {
  return axios.get(`/api/users/${userId}`)
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      })
}