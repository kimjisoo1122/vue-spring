import axios from "axios";
import {formatJwt} from "@/util/formatUtil";
import {AUTHORIZATION} from "@/constants";

const instance = axios.create({});

/* axios의 모든 api통신에 헤더를 자동으로 설정합니다. */
instance.interceptors.request.use(config => {
  config.headers.Authorization = getJwt();
  return config;
})

export default instance;

/**
 * 로컬스토리지에서 Jwt를 조회합니다.
 * @returns {string} jwt
 */
function getJwt() {
  const jwt = localStorage.getItem(AUTHORIZATION);
  return jwt ? formatJwt(jwt) : null;
}