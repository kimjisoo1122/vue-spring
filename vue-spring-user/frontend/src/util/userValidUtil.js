/**
 * 사용자 아이디를 검증합니다.
 * 4글자 이상, 12글자 미만 이여야 합니다.
 * 영문/숫자를 포함 하여야 합니다.
 * @param userId 사용자 아이디
 */
export const validateUserId = userId => {
  if (isEmpty(userId)) {
    return '아이디는 필수항목 입니다.';
  }

  const validateLenResult = validateLength(userId, 4, 12);
  if (validateLenResult.length > 0) {
    return validateLenResult;
  }

  const validateRexResult = validateRex(userId);
  if (validateRexResult.length > 0) {
    return validateRexResult;
  }

  return '';
}

/**
 * 비밀번호를 검증합니다.
 * 4글자 이상, 12글자 미만 이여야 합니다.
 * 영문/숫자를 포함 하여야 합니다.
 * 연속된 문자가 3개 이상은 불가능합니다.
 * 아이디랑 동일할 수 없습니다.
 *
 * @param userPw 사용자 비밀번호
 * @param userId 사용자 아이디
 */
export const validateUserPw = (userPw, userId) => {
  if (isEmpty(userPw)) {
    return '비밀번호는 필수항목 입니다.';
  }

  const validateLenResult = validateLength(userPw, 4, 12);
  if (validateLenResult.length > 0) {
    return validateLenResult;
  }

  const validateRexResult = validateRex(userPw);
  if (validateRexResult.length > 0) {
    return validateRexResult;
  }

  if (/(.)\1{2,}/.test(userPw)) {
    return '연속된 문자가 3개 이상은 불가능합니다.'
  }

  if (userId === userPw) {
    return '아이디랑 동일할 수 없습니다.';
  }

  return '';
}

/**
 * 비밀번호를 서로 확인합니다.
 *
 * @param userPW 1차 비밀번호
 * @param confirmPw 2차 비밀번호
 * @returns 유효하면 빈 값 유효하지 않으면 에러메시지를 반환합니다.
 */
export const validateConfirm = (userPW, confirmPw) => {
  return userPW === confirmPw ? '' : '비밀번호가 서로 맞지 않습니다.';
}

/**
 * 사용자 이름을 검증합니다.
 *
 * @param userName 사용자 이름
 * @returns 유효하면 빈 값 유효하지 않으면 에러메시지를 반환합니다.
 */
export const validateUserName = userName => {
  return validateLength(userName, 2, 5)
}

/**
 * 빈 값을 체크합니다.
 *
 * @param value
 * @returns {boolean}
 */
function isEmpty(value) {
  return value === undefined || value.length === 0;
}

/**
 * 입력값의 최소 값 이상 최대 값 미만을 검증합니다.
 * @param value 입력 값
 * @param min 최소 값
 * @param max 최대 값
 * @returns 유효하면 빈 값 유효하지 않으면 에러메시지를 반환합니다.
 */
function validateLength(value, min, max) {
  const lengthCondition = value.length >= min && value.length < max;
  return lengthCondition ? '' : `${min}글자 이상, ${max}글자 미만 이여야 합니다.`;
}

/**
 * 입력 값의 영문/숫자 값을 검증합니다.
 *
 * @param value 입력 값
 * @returns 유효하면 빈 값 유효하지 않으면 에러메시지를 반환합니다.
 */
function validateRex(value) {
  const rex = /^(?=.*[a-zA-Z])(?=.*\d).*$/;
  return rex.test(value) ? '' : '영문/숫자를 포함 하여야 합니다.';
}
