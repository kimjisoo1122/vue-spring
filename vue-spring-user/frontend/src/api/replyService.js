import axios from "@/api/config/axios";

/**
 * 게시글에 등록된 댓글목록을 조회합니다.
 * @param boardId 게시글 번호
 * @return {Promise<Array>} 게시글에 등록된 댓글목록을 배열로 반환합니다.
 */
export function getBoardReplyList(boardId) {
  return axios.get(`/api/replies/board/${boardId}`)
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
 * 댓글을 등록합니다.
 * @param reply 댓글등록정보
 * @return {Promise<Object>} 등록된 댓글정보
 */
export function registerReply(reply) {
  return axios.post('/api/replies', reply)
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
 * 댓글을 삭제합니다.
 * @param replyId 댓글번호
 * @returns {Promise<void>}
 */
export function deleteReply(replyId) {
  return axios.delete(`/api/replies/${replyId}`)
      .then()
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      });
}