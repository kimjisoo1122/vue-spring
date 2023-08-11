import axios from "@/api/config/axios";

/**
 * 카테고리 목록을 조회합니다.
 * @param categoryId 카테고리 번호
 * @return {Promise<Array>} 카테고리 목록을 배열로 반환합니다.
 */
export function getCategoryList(categoryId) {
  return axios.get(`/api/categories/${categoryId}`)
      .then(({data: {data}}) => {
        return data;
      })
      .catch(({response: {data: {errorMessage}}}) => {
        throw {
          message: errorMessage
        }
      })
}