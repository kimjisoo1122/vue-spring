export default {
  namespaced: true,
  state: {
    uploadFiles: {}, /* 업로드 파일 (등록/수정) */
  },
  mutations: {
    /**
     * 파일을 업로드파일 스테이트에 저장합니다.
     * @param state uploadFiles 업로드 파일목록
     * @param fileId 파일 번호
     * @param file 파일 객체
     */
    uploadFile(state, {fileId, file}) {
      state.uploadFiles[fileId] = file;
      console.log(state.uploadFiles);
    },
    /**
     * 업로드파일 스테이트에서 해당 번호의 파일을 제거합니다.
     * @param state uploadFiles
     * @param fileId 파일 번호
     */
    cancelFile(state, fileId) {
      delete state.uploadFiles[fileId];
    },
    /**
     * 스테이트의 업로드파일을 비웁니다.
     * @param state
     */
    clearFile(state) {
      state.uploadFiles = {}
    },
  },
  getters: {
    /**
     * 스테이트의 업로드파일에 저장되어 있는 파일 객체들을 배열에 담아 반환합니다.
     * @param state
     * @return {[]}  파일 객체들이 담겨있는 배열
     */
    getSaveFiles(state) {
      const saveFile = [];

      Object.values(state.uploadFiles)
          .forEach(e => {
            saveFile.push(e);
          })

      return saveFile;
    },
  }
}
