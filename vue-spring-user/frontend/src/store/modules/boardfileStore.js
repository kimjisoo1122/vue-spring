export default {
  namespaced: true,
  state: {
    uploadFiles: {}, /* 업로드 파일 (등록/수정) */
  },
  mutations: {
    uploadFile(state, {fileId, file}) {
      state.uploadFiles[fileId] = file;
      console.log(state.uploadFiles);
    },
    cancelFile(state, fileId) {
      delete state.uploadFiles[fileId];
    },
    clearFile(state) {
      state.uploadFiles = {}
    },
  },
  getters: {
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
