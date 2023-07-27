/**
 * 검색조건중 시작날짜와 종료날짜를 검증합니다.
 * @returns {boolean}
 */
function validateDate() {
  const fromDateElm = document.querySelector('.search-date-from');
  const toDateElm = document.querySelector('.search-date-to');

  /* 한쪽만 데이터 있는경우 검증*/
  if (!validDateValue(fromDateElm, toDateElm)) {
    return false;
  }

  const fromMoment = moment(fromDateElm.value);
  const toMoment = moment(toDateElm.value);
  const diffDay = toMoment.diff(fromMoment, 'days');

  if (diffDay > 365) {
    alert('날짜는 최대 1년 까지 검색이 가능합니다.');
    toDateElm.classList.add('search-date-error');
    fromDateElm.classList.add('search-date-error');

    return false;
  }
}

/**
 * 날짜데이터중 한쪽만 있는경우 검증후 에러처리합니다.
 * @param fromDateElm
 * @param toDateElm
 * @returns {boolean}
 */
function validDateValue(fromDateElm, toDateElm) {
  if (fromDateElm.value !== '') {
    if (toDateElm.value === '') {
      toDateElm.classList.add('search-date-error');

      return false;
    } else {
      toDateElm.classList.remove('search-date-error');
    }
  }

  if (toDateElm.value !== '') {
    if (fromDateElm.value === '') {
      fromDateElm.classList.add('search-date-error');

      return  false;
    } else {
      fromDateElm.classList.remove('search-date-error');
    }
  }

  return true;
}

/**
 * 검색조건을 전송합니다.
 */
function submitSearch() {
  const searchForm = document.querySelector('.search-form');
  const fromDateElm = document.querySelector('.search-date-from');
  const toDateElm = document.querySelector('.search-date-to');
  const totalLen = fromDateElm.value.length * toDateElm.value.length;

  if (totalLen === 0) {
    fromDateElm.value = '';
    toDateElm.value = '';
  }

  searchForm.submit();
}