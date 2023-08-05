import { ref } from 'vue';

export const options = ref({
  limit: [
    {name: 10, value: 10},
    {name: 20, value: 20},
    {name: 30, value: 30},
    {name: 40, value: 40},
    {name: 50, value: 50}
  ],
  galleryLimit: [
    {name: 3, value: 3},
    {name: 6, value: 6},
    {name: 9, value: 9},
    {name: 12, value: 12},
    {name: 15, value: 15}
  ],
  orderCondition: [
    {name: '등록 일시', value: ''},
    {name: '제목', value: 'title'},
    {name: '조회수', value: 'viewCnt'}
  ],
  order: [
    {name: '내림차순', value: 'desc'},
    {name: '오름차순', value: 'asc'}
  ]
});
