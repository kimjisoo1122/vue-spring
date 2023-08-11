/**
 * 수정폼에서 조회한 게시글의 정보를 폼에 입력합니다.
 * @param form 수정 폼
 * @param board 게시글 정보
 */
export function initFormValue(form, board) {
  for (const field in form) {
    form[field] = board[field];
  }
}

export function createFormData(form, saveFiles, deleteFiles) {
  const formData = new FormData();

  for (const field in form) {
    formData.append(field, form[field]);
  }

  /* 첨부파일 추가 */
  for (const file of Object.values(saveFiles)) {
    if (file) {
      formData.append('saveFiles', file);
    }
  }

  if (deleteFiles) {
    /* 삭제파일 추가 */
    for (const deleteFileId of deleteFiles) {
      formData.append('deleteFiles', deleteFileId);
    }
  }

  return formData;
}