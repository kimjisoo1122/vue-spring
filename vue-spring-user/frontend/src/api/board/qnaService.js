import axios from "@/api/config/axios";

/**
 * 문의게시글을 등록합니다.
 * @param formData 게시글 등록 폼
 * @returns {Promise<number>}
 * 등록된 게시글 번호를 반환하거나,
 * data: 에러필드에 에러메시지가 담겨진 객체를 반환합니다.
 * message: API 통신 에러메시지
 */
export function registerQna (formData) {
  return axios.post('/api/qna', formData)
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
 * 문의게시글 목록을 조회합니다.
 * @param condition 검색조건
 * @returns {Promise<Array>} 문의게시글 목록을 배열로 반환합니다.
 */
export function getQnaList(condition) {
  return axios.get('/api/qna', {
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
 * 문의게시글 상세정보를 조회합니다.
 * @param boardId 문의게시글 번호
 * @return {Promise<Object>} 문의게시글 상세정보
 */
export function getQnaDetail(boardId) {
  return axios.get(`/api/qna/${boardId}`)
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
 * 문의게시글을 업데이트합니다.
 * @param boardId 문의게시글 번호
 * @param formData 업데이트 폼
 * @return {Promise<number>} 해당 문의게시글 번호
 */
export function updateQna(boardId, formData) {
  return axios.put(`/api/qna/${boardId}`, formData)
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
 * 문의게시글을 삭제합니다.
 * @param boardId 게시글번호
 * @return {Promise<void>}
 */
export function deleteQna(boardId) {
  return axios.delete(`/api/qna/${boardId}`)
      .then(() => {
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        };
      });
}