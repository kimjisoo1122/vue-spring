import axios from "@/api/config/axios";

/**
 * 로그인을 요청합니다.
 * @param loginForm 로그인 폼
 * @returns {Promise<string>} JWT를 반환합니다.
 */
export function login(loginForm) {
  return axios.post('/api/login', loginForm)
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      })
}