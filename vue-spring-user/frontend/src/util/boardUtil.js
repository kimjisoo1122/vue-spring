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