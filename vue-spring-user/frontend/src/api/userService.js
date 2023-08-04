import axios from "@/api/config/axios";

/**
 * 회원가입을 요청합니다.
 *
 * @param user 가입자 정보
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export function signUp(user) {
  return axios.post('/api/users', user)
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorFields}}}) => {
        throw {
          data: errorFields
        }
      });
}

/**
 * 입력한 아이디의 중복체크를 합니다.
 *
 * @param userId
 * @returns {Promise<axios.AxiosResponse<any>>}
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