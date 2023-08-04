export default {
  namespaced: true,
  state: {
    uploadFiles: {}, /* 업로드 파일 (등록/수정) */
    deleteFiles: [], /* 삭제 파일 (수정) */
  },
  mutations: {
    uploadFile(state, {fileId, file}) {
      state.uploadFiles[fileId] = file;
    },
    cancelFile(state, fileId) {
      delete state.uploadFiles[fileId];
    },
    deleteFile(state, fileId) {
      state.deleteFiles.push(fileId);
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
