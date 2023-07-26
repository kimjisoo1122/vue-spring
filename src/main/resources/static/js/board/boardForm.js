/**
 * 폼 유효성검증
 * @returns {boolean}
 */
function validateForm() {
  const titleResult = validateTitle();
  const contentResult = validateContent();

  return titleResult && contentResult;
}

/**
 * 게시글 제목을 검증합니다.
 * @returns {boolean}
 */
function validateTitle() {
  const titleElm = document.querySelector('.form-title-input');
  if (!validElm(titleElm, 100)) {
    const titleErrElm = document.querySelector('.form-title-error');
    titleErrElm.innerHTML = '필수 입력, 100자 미만이여야 합니다.';

    return false;
  }

  return true;
}

/**
 * 게시글 내용을 검증합니다.
 * @returns {boolean}
 */
function validateContent() {
  const contentElm = document.querySelector('.form-content-textarea');
  if (!validElm(contentElm, 4000)) {
    const contentErrElm = document.querySelector('.form-content-error');
    contentErrElm.innerHTML = '필수 입력, 4000자 미만이여야 합니다.';

    return false;
  }

  return true;
}

/**
 * 게시글 파일을 검증합니다.
 * @returns {boolean}
 */

function validateFile() {


    return false;
}

/**
 * 엘리먼트값을 검증하고 에러를 처리합니다.
 * @param elm
 * @param maxSize
 * @returns {boolean}
 */

function validElm(elm, maxSize) {
  if (elm.value.length >= maxSize || elm.value.length === 0) {
    elm.classList.add('input-error');

    return false;
  } else {
    elm.classList.remove('input-error');

    return true;
  }
}

/**
 * 게시글을 삭제요청합니다.
 * @param action 게시글 삭제URL
 */
function deleteBoard(action) {
  if (confirm('게시글을 삭제하시겠습니까?')) {
    const boardForm = document.querySelector('.board-form');
    boardForm.setAttribute('action', action);

    boardForm.submit();
  }
}