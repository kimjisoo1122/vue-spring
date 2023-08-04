import axios from "@/api/config/axios";

/**
 * 로그인을 요청합니다.
 *
 * @param loginForm 로그인 폼 정보
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export function login(loginForm) {
  return axios.post('/api/login', loginForm)
      .then(({data: {data}}) => {
        console.log(data);
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      })
}