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

function validateFile(thisElm) {
  const idx = thisElm.getAttribute('idx');
  const fileElm = document.querySelector(`.form-file-input[idx="${idx}"]`);
  const fileErrElm = document.querySelector('.form-file-error');
  const thumbElm = document.querySelector(`.form-file-thumb[idx='${idx}']`);
  const file = fileElm.files[0];

  if (file === undefined) {
    fileErrElm.innerHTML = '';
    thumbElm.src = ''
    thumbElm.style.display = 'none';

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

  /* 파일 썸네일 */
  showThumb(file, fileExt, thumbElm);

  return true;
}

/**
 * 첨부한 이미지파일의 썸네일을 보여줍니다.
 * @param file 파일객체
 * @param fileExt 파일확장자
 * @param thumbElm 썸네일 엘리먼트
 */
function showThumb(file, fileExt, thumbElm) {
  if (fileExt !== 'zip') {
    const reader = new FileReader();
    reader.onload = e => {
      thumbElm.src = e.target.result;
      thumbElm.style.display = 'block';
    }
    reader.readAsDataURL(file);
  } else {
    thumbElm.src = ''
    thumbElm.style.display = 'none';
  }
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
        <img idx="${idx}" class="form-file-thumb">
        <label for="${idx}" class="form-file-label"></label>
        <input idx="${idx}"
               id="${idx}"
               type="file"
               name="saveFiles"
               onchange="validateFile(this)"
               class="form-file-input">
      `;

  return fileInputBox;
}

/**
 * 게시글의 댓글을 등록합니다.
 * @param boardId 게시글 번호
 */
function registerReply(boardId) {
  const inputElm = document.querySelector('.reply-register-input');
  const replyContent = inputElm.value;

  const xhr = new XMLHttpRequest();
  xhr.onreadystatechange = () => {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 201) {
        const {data: {reply}} = JSON.parse(xhr.responseText);
        const containerElm = document.querySelector('.reply-list-container');

        const replyElm =
            `
            <div class="reply">
              <div class="reply-writer">${reply.userName}</div>
              <div class="reply-date">${formatDate(reply.createDate)}</div>
              <div class="reply-content">${reply.replyContent}</div>
            </div>
            `

        containerElm.insertAdjacentHTML('beforeend', replyElm);
      } else {
        console.error(xhr.responseText);
      }
      inputElm.value = '';
    }
  }

  xhr.open('POST', '/api/reply', true);
  xhr.setRequestHeader("Content-Type", "application/json")

  const registerReplyDto = {
    boardId: boardId,
    replyContent: replyContent,
  }

  xhr.send(JSON.stringify(registerReplyDto));
}

/**
 * 게시글의 댓글을 삭제합니다.
 * @param thisElm
 */
function deleteReply(thisElm) {
  if (!confirm('댓글을 삭제하시겠습니까?')) {
    return false;
  }

  const xhr = new XMLHttpRequest();
  xhr.onreadystatechange = () => {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 204) {
        thisElm.parentElement.remove();
      } else {
        console.error(xhr.responseText);
      }
    }
  }

  const replyId = thisElm.getAttribute('replyId');

  xhr.open('DELETE', `/api/reply/${replyId}`, true);
  xhr.setRequestHeader("Content-Type", "application/json")

  xhr.send();
}

/**
 * 자바 LocalDateTime 형식의 문자열을 yyyy.MM.dd HH:mm 포맷으로 변경
 * @param date
 * @returns yyyy.MM.dd HH:mm 포맷
 */
function formatDate(date)  {
  return date.replace('T', ' ').replaceAll('-', '.').slice(0, -3);
}

