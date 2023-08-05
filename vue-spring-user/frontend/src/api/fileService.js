import axios from "@/api/config/axios";

/**
 * 게시글에 첨부된 파일목록을 조회합니다.
 * @param boardId 게시글 번호
 * @return {Promise<Array>} 첨부된 파일목록을 배열로 반환합니다.
 */
export function getBoardFileList(boardId) {
  return axios.get(`/api/file/board/${boardId}`)
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
 * 파일을 다운로드합니다.
 * @param fileId 파일번호
 */
export const fileDown = (fileId) => {
  return axios({
    url: `/api/file/${fileId}`,
    method: 'GET',
    responseType: 'blob',  // 바이너리데이터를 받을수있는 브라우저전용 데이터타입
  })
      .then((response) => {
        const url = window.URL.createObjectURL(new Blob([response.data]))
        const link = document.createElement('a')
        link.href = url
        link.download = extractDownloadFilename(response);

        document.body.appendChild(link)
        link.click();
        link.remove();

        window.URL.revokeObjectURL(url);
      })
      .catch(error => {
        if (error.response.data) {
          const reader = new FileReader();
          reader.onload = function() {
            const errorData = JSON.parse(reader.result);
            console.error(errorData.errorMessage);
            alert(errorData.errorMessage);
          };
          reader.readAsText(error.response.data);
        }
      })
};

/**
 * 다운로드 파일이름을 추출합니다.
 * @param response 서버 응답데이터
 * @returns {string} 다운로드 파일이름을 반환합니다.
 */
function extractDownloadFilename(response) {
  const disposition = response.headers["content-disposition"];
  return decodeURI(
      disposition
          .match(/filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/)[1]
          .replace(/['"]/g, "")
  );
}