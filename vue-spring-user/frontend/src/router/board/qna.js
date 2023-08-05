export default [
  {
    path: '/qna',
    component: () => import('@/views/board/qna/QnaList.vue'),
    meta: {
      requiresAuth: false,
    }
  },
  {
    path: '/qna/:boardId',
    component: () => import('@/views/board/qna/QnaDetail.vue'),
    meta: {
      requiresAuth: false,
    }
  },
  {
    path: '/qna/register',
    component: () => import('@/views/board/qna/QnaRegister.vue'),
    meta: {
      requiresAuth: true,
    }
  },
  {
    path: '/qna/update/:boardId',
    component: () => import('@/views/board/qna/QnaUpdate.vue'),
    meta: {
      requiresAuth: true,
    }
  },
]
