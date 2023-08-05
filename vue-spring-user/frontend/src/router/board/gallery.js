export default [
  {
    path: '/galleries',
    component: () => import('@/views/board/gallery/GalleryList.vue'),
    meta: {
      requiresAuth: false,
    },
  },
  {
    path: '/galleries/:boardId',
    component: () => import('@/views/board/gallery/GalleryDetail.vue'),
    meta: {
      requiresAuth: false,
    }
  },
  {
    path: '/galleries/register',
    component: () => import('@/views/board/gallery/GalleryRegister.vue'),
    meta: {
      requiresAuth: true,
    }
  },
  {
    path: '/galleries/update/:boardId',
    component: () => import('@/views/board/gallery/GalleryUpdate.vue'),
    meta: {
      requiresAuth: true,
    }
  }
]
