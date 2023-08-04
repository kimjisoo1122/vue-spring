import axios from "@/api/config/axios";
import {MULTIPART_CONFIG} from "@/constants";

/**
 * 자유게시글을 등록합니다.
 *
 * @param formData 게시글 등록폼 정보
 * @return 등록된 게시글 번호를 반환하거나 실패한 경우 에러필드에 에러메시지가 담겨 반환합니다.
 */
export function registerFree (formData) {
  return axios.post('/api/frees', formData, MULTIPART_CONFIG)
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
 * 자유게시글 목록을 조회합니다.
 *
 * @param condition 검색조건
 * @returns {Promise<unknown>}
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
 * 게시글번호로 자유게시글 상세정보를 조회합니다.
 * @param boardId
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
 * @param boardId 게시글번호
 * @param formData 업데이트폼 정보
 * @return boardId
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
 * 자유게시글 삭제를 요청합니다.
 * @param boardId 게시글번호
 * @return boardId
 */
export function deleteFree(boardId) {
  return axios.delete(`/api/frees/${boardId}`)
      .then(() => {
        return boardId;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        };
      });
}