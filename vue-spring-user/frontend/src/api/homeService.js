import axios from "@/api/config/axios";

/**
 * 홈 화면에 사용하는 게시판을 조회합니다.
 * @returns {Promise<Object>} 공지사항, 자유게시판, 갤러리, 문의게시판의 목록을 조회합니다
 * 각 프로퍼티에 목록을 배열로 반환합니다.
 * noticeList: 공지사항 목록
 * freeList: 자유게시글 목록
 * galleryList: 갤러리 목록
 * qnaList: 문의게시글 목록
 */
export function getHomeBoardList() {
  return axios.get('/api/home/')
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      })
}