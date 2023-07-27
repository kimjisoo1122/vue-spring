/**
 * 폼 유효성검증
 * @param formTypeValue 폼 타입 이름 (등록, 수정)
 * @param boardType 게시글 타입
 * @returns {boolean}
 */
function validateForm(formTypeValue, boardType) {
  const titleResult = validateTitle();
  const contentResult = validateContent();
  let fileError = false;

  /* 자유게시판인 경우 파일검증을 합니다. */
  if (boardType === 'FREE') {
    /* 에러태그에 메시지가 존재하면 에러존재 */
    fileError = hasFileError();
  }

  if (!(titleResult && contentResult && !fileError)) {
    return false;
  }



  return confirm(`${formTypeValue} 하시겠습니까?`);
}

/**
 * 게시글 제목을 검증합니다.
 * @returns {boolean}
 */
function validateTitle() {
  const titleElm = document.querySelector('.form-title-input');
  const titleErrElm = document.querySelector('.form-title-error');

  if (!validElm(titleElm, 100)) {
    titleErrElm.innerHTML = '필수 입력, 100자 미만이여야 합니다.';

    return false;
  }

  titleErrElm.innerHTML = '';

  return true;
}

/**
 * 게시글 내용을 검증합니다.
 * @returns {boolean}
 */
function validateContent() {
  const contentElm = document.querySelector('.form-content-textarea');
  const contentErrElm = document.querySelector('.form-content-error');

  if (!validElm(contentElm, 4000)) {
    contentErrElm.innerHTML = '필수 입력, 4000자 미만이여야 합니다.';

    return false;
  }

  contentErrElm.innerHTML = '';

  return true;
}

/**
 * 게시글 파일을 검증합니다.
 * @returns {boolean}
 */

function validateFile(idx) {
  const fileElm = document.querySelector(`.form-file-input[idx="${idx}"]`);
  const fileErrElm = document.querySelector('.form-file-error');
  const file = fileElm.files[0];

  if (file === undefined) {
    fileErrElm.innerHTML = '';

    return true;
  }

  const fileMaxSize = 1024 * 1024 * 2; // 2MB
  if (file.size > fileMaxSize) {
    fileErrElm.innerHTML = '파일사이즈는 2MB를 넘길 수 없습니다.';

    return false;
  }

  const dotIdx = file.name.lastIndexOf('.')
  const fileExt = file.name.substring(dotIdx + 1);
  const allowedExts = ['jpg', 'gif', 'png', 'zip'];
  if (!allowedExts.includes(fileExt)) {

    fileErrElm.innerHTML = '허용된 확장자가 아닙니다.';

    return false;
  }

  fileErrElm.innerHTML = '';

  return true;
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
 * 파일에러가 존재하는지 확인합니다.
 * @returns {boolean}
 */
function hasFileError() {
    const fileErrElm = document.querySelector('.form-file-error');
    return fileErrElm.innerHTML.length > 0;
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

/**
 * 첨부파일의 파일아이디를 히든필드에 담고
 * 인풋파일을 1개 추가합니다.
 * 해당 첨부파일 엘리먼트를 현재 DOM에서 삭제합니다.
 * @param thisElm
 */
function hideFile(thisElm) {
  const fileId = thisElm.getAttribute('fileId');
  const hiddenElm = createHiddenElm(fileId);
  const hiddenContainer = document.querySelector('.form-hidden-container');
  hiddenContainer.appendChild(hiddenElm);

  const fileBoxContainer = document.querySelector('.form-file-input-box-container');
  const idx = fileBoxContainer.children.length + 1;
  const fileInputBox = createFileInputBox(idx);
  fileBoxContainer.appendChild(fileInputBox);

  thisElm.parentElement.remove();
}

/**
 * 첨부파일의 아이디를 담은 히든필드를 생성합니다.
 * @returns {HTMLInputElement}
 */
function createHiddenElm(fileId) {
  const hiddenElm = document.createElement('input');
  hiddenElm.setAttribute('type', 'hidden');
  hiddenElm.setAttribute('name', 'deleteFiles');
  hiddenElm.setAttribute('value', fileId);

  return hiddenElm;
}

/**
 * 파일인풋필드를 생성합니다.
 * @param idx
 * @returns {HTMLDivElement}
 */
function createFileInputBox(idx) {
  const fileInputBox = document.createElement('div');
  fileInputBox.setAttribute('class', 'form-file-input-box');
  fileInputBox.innerHTML =
      `
        <label th:for="${idx}" class="form-file-label"></label>
        <input idx="${idx}"
               id="${idx}"
               type="file"
               value="test.jpg"
               name="saveFiles"
               onchange="validateFile(this.getAttribute('idx'))"
               class="form-file-input">
      `;

  return fileInputBox;
}