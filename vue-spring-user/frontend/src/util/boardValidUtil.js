/**
 * 카테고리를 검증합니다.
 * 필수입력 입니다.
 */
export function validateCategory(categoryId) {
  if (!categoryId) {
    return '카테고리는 필수입력 입니다.';
  }
  return '';
}

/**
 * 제목을 검증합니다.
 * 필수입력, 100글자 미만 이여야 합니다.
 */
export function validateTitle(title) {
  if (!title) {
    return '제목은 필수입력 입니다.';
  } else if (title.length >= 100) {
    return '100글자 미만 이여야 합니다.';
  }
  return '';
}

/**
 * 내용을 검증합니다.
 * 필수입력, 4000글자 미만 이여야 합니다
 */
export function validateContent(content) {
  if (!content) {
    return '내용은 필수입력 입니다.';
  } else if (content.length >= 4000) {
    return '4000글자 미만 이여야 합니다.';
  }
}

