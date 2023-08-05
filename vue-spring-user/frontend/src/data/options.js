/**
 * 기본 값 데이터입니다.
 */
import { ref } from 'vue';

export const options = ref({
  /* 검색조건 limit select */
  limit: [
    {name: 10, value: 10},
    {name: 20, value: 20},
    {name: 30, value: 30},
    {name: 40, value: 40},
    {name: 50, value: 50}
  ],
  /* 검색조건 갤러리 limit select */
  galleryLimit: [
    {name: 3, value: 3},
    {name: 6, value: 6},
    {name: 9, value: 9},
    {name: 12, value: 12},
    {name: 15, value: 15}
  ],
  /* 검색조건 orderCondition select */
  orderCondition: [
    {name: '등록 일시', value: ''},
    {name: '제목', value: 'title'},
    {name: '조회수', value: 'viewCnt'}
  ],
  /* 검색조건 order select */
  order: [
    {name: '내림차순', value: 'desc'},
    {name: '오름차순', value: 'asc'}
  ]
});
