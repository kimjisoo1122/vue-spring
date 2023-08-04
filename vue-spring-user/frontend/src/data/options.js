import { ref } from 'vue';

export const options = ref({
  limit: [
    {name: 10, value: 10},
    {name: 20, value: 20},
    {name: 30, value: 30},
    {name: 40, value: 40},
    {name: 50, value: 50}
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
