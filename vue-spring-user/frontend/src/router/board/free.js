export default [
  {
    path: '/frees',
    component: () => import('@/views/board/free/FreeList.vue'),
    meta: {
      requiresAuth: false,
    }
  },
  {
    path: '/frees/:boardId',
    component: () => import('@/views/board/free/FreeDetail.vue'),
    meta: {
      requiresAuth: false,
    }
  },
  {
    path: '/frees/register',
    component: () => import('@/views/board/free/FreeRegister.vue'),
    meta: {
      requiresAuth: true,
    }
  },
  {
    path: '/frees/update/:boardId',
    component: () => import('@/views/board/free/FreeUpdate.vue'),
    meta: {
      requiresAuth: true,
    }
  },
]
