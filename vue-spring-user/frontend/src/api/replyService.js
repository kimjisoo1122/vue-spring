import axios from "@/api/config/axios";

/**
 * 게시글에 등록된 댓글목록을 조회합니다.
 * @param boardId 게시글번호
 */
export function getBoardReplyList(boardId) {
  return axios.get(`/api/reply/board/${boardId}`)
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
 *
 * @param replyDto 댓글등록정보
 * @return reply 등록된 댓글
 */
export function registerReply(replyDto) {
  return axios.post('/api/reply', replyDto)
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
 *
 * @param replyId
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export function deleteReply(replyId) {
  return axios.delete(`/api/reply/${replyId}`)
      .then(data => {
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      });
}