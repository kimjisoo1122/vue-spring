import axios from "@/api/config/axios";

/**
 * 공지사항을 조회합니다.
 * @param condition 검색조건
 * @returns {Promise<Array>} 공지사항 목록을 배열로 반환합니다.
 */
export function getNoticeList(condition) {
  return axios.get('/api/notices', {
    params: condition
  })
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      })
}

/**
 * 공지사항 알림글을 조회합니다.
 *
 * @param limit 알림글 개수
 * @returns {Promise<Array>} 공지시항 알림글을 배열로 반환합니다.
 */
export function getAlarmList(limit) {
  return axios.get('/api/notices/alarms', {
    params: {
      limit: limit,
    },
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
 * 공지사항 상세정보를 조회합니다.
 * @param boardId 공지사항 번호
 * @return {Promise<Object>} 공지사항 상세정보
 */
export function getNoticeDetail(boardId) {
  return axios.get(`/api/notices/${boardId}`)
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      })
}