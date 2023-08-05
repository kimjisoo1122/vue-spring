import axios from "@/api/config/axios";
import {MULTIPART_CONFIG} from "@/constants";

/**
 * 자유게시글을 등록합니다.
 * @param formData 등록 폼
 * @returns {Promise<number>}
 * 등록된 게시글 번호를 반환하거나,
 * data: 에러필드에 에러메시지가 담겨진 객체를 반환합니다.
 * message: API 통신 에러메시지
 */
export function registerFree (formData) {
  return axios.post('/api/frees', formData, MULTIPART_CONFIG)
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorFields, errorMessage}}}) => {
        throw {
          data: errorFields,
          message: errorMessage
        }
      });
}

/**
 * 자유게시글 목록을 조회합니다.
 * @param condition 검색조건
 * @returns {Promise<Array>} 자유게시글 목록을 배열로 반환합니다.
 */
export function getFreeList(condition) {
  return axios.get('/api/frees', {
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
 * 자유게시글 상세정보를 조회합니다.
 * @param boardId 자유게시글 번호
 * @returns {Promise<Object>} 자유게시글 상세정보
 */
export function getFreeDetail(boardId) {
  return axios.get(`/api/frees/${boardId}`)
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
 * 자유게시글을 업데이트합니다.
 * @param boardId 자유게시글 번호
 * @param formData 업데이트 폼
 * @return {Promise<number>} 자유게시글 번호
 */
export function updateFree(boardId, formData) {
  return axios.put(`/api/frees/${boardId}`, formData, MULTIPART_CONFIG)
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
 * 자유게시글을 삭제합니다.
 * @param boardId 자유게시글 번호
 * @return {Promise<void>}
 */
export function deleteFree(boardId) {
  return axios.delete(`/api/frees/${boardId}`)
      .then(() => {
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        };
      });
}