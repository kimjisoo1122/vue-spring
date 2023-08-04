export default [
  {
    path: '/notices',
    component: (() => import('@/views/board/notice/NoticeList.vue')),
    meta: {
      requiresAuth: false,
    }
  },
  {
    path: '/notices/:boardId',
    component: (() => import('@/views/board/notice/NoticeDetail.vue')),
    meta: {
      requiresAuth: false,
    }
  },
]