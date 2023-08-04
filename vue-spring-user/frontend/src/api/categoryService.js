import axios from "@/api/config/axios";

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