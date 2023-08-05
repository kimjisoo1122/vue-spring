import {createRouter, createWebHistory} from "vue-router";
import Home from "@/views/Home.vue";
import freeRoutes from "@/router/board/free";
import noticeRoutes from "@/router/board/notice";
import galleryRoutes from "@/router/board/gallery";
import qnaRoutes from "@/router/board/qna";

import {isAuthenticated} from "@/util/authUtil";

const routes = [
  {
    path: '/',
    component: Home,
    meta: {
      requiresAuth: false,
    }
  },
  {
    path: '/login',
    component: (() => import('@/views/user/Login.vue')),
    meta: {
      requiresAuth: false,
    }
  },
  {
    path: '/signup',
    component: (() => import('@/views/user/SignUp.vue')),
    meta: {
      requiresAuth: false,
    }
  },
  {
    path: '/404',
    component: (() => import('@/views/error/NotFound.vue')),
  },

  /* 게시글 (공지사항, 자유게시판, 갤러리게시판, 문의게시판) */
  ...noticeRoutes,
  ...freeRoutes,
  ...galleryRoutes,
  ...qnaRoutes,

  {
    path: '/*',
    component: (() => import('@/views/error/NotFound.vue')),
  },

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth); // 라우터 인증필요여부
  const authenticated = isAuthenticated() // 전역 인증상태

  if (to.path === '/login' && authenticated) {
    next('/');
  }

  if (!requiresAuth) {
    next();
  }

  if (authenticated) {
    next();
  } else {
    next({
      path: '/login',
      query: {redirect: to.fullPath}
    });
  }
})

export default router;