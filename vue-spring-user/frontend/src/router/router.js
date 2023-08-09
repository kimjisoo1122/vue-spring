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
  /* 게시글 (공지사항, 자유게시판, 갤러리게시판, 문의게시판) */
  ...noticeRoutes,
  ...freeRoutes,
  ...galleryRoutes,
  ...qnaRoutes,

  {
    path: '/404',
    component: (() => import('@/views/error/NotFound.vue')),
  },
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

  /* 로그인 상태에서 로그인 접근방지 */
  if (to.path === '/login' && authenticated) {
    next('/');
  }

  /* 인증이 필요 없거나 인증상태 */
  if (!requiresAuth || authenticated) {
    next();
  }

  /* 비 인증 상태 -> 기존 주소 포함하여 로그인 라우팅 */
  next({
    path: '/login',
    query: {redirect: to.fullPath}
  });

});


export default router;