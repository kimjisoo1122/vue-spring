import axios from "@/api/config/axios";
import {MULTIPART_CONFIG} from "@/constants";

/**
 * 갤러리를 등록합니다.
 * @param formData 등록 폼
 * @return {Promise<number>} 등록된 게시글 번호를 반환하거나,
 * data: 에러필드에 에러메시지가 담겨진 객체를 반환합니다.
 * message: API 통신 에러메시지
 */
export function registerGallery (formData) {
  return axios.post('/api/galleries', formData, MULTIPART_CONFIG)
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorFields, errorMessage}}}) => {
        throw {
          data: errorFields,
          message: errorMessage,
        }
      });
}

/**
 * 갤러리 목록을 조회합니다.
 * @param condition 검색조건
 * @returns {Promise<Array>} 갤러리 목록이 배열로 반환됩니다.
 */
export function getGalleryList(condition) {
  return axios.get('/api/galleries', {
    params: condition
  })
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      });
}

/**
 * 갤러리 상세정보를 조회합니다.
 * @param boardId 갤러리 번호
 * @return
 */
export function getGalleryDetail(boardId) {
  return axios.get(`/api/galleries/${boardId}`)
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      });
}

/**
 * 갤러리를 업데이트합니다.
 * @param boardId 갤러리 번호
 * @param formData 업데이트폼 정보
 * @return {Promise<number>} 갤러리 번호
 */
export function updateGallery(boardId, formData) {
  return axios.put(`/api/galleries/${boardId}`, formData, MULTIPART_CONFIG)
      .then(() => {
        return boardId;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      });
}

/**
 * 갤러리를 삭제합니다.
 * @param boardId 갤러리 번호
 * @return {Promise<void>}
 */
export function deleteGallery(boardId) {
  return axios.delete(`/api/galleries/${boardId}`)
      .then(() => {
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        };
      });
}