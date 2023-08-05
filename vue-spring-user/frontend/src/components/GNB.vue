<template>

  <header>

    <!-- 홈페이지 타이틀 -->
    <h1 @click="$router.push('/')" class="gnb-title">뷰 스프링 게시판</h1>

    <div class="gnb-nav-container">

      <!-- 홈페이지 네비게이션 -->
      <nav class="gnb-nav">
        <ul class="gnb-nav-list">
          <li @click="this.$router.push('/notices')" class="gnb-nav-notices">공지사항</li>
          <li @click="this.$router.push('/frees')" class="gnb-nav-frees">자유 게시판</li>
          <li @click="this.$router.push('/galleries')" class="gnb-nav-galleries">갤러리</li>
          <li @click="this.$router.push('/qna')" class="gnb-nav-qna">문의 게시판</li>
        </ul>
      </nav>

      <!-- 비회원 로그인 상태 -->
      <div v-if="!isLogin" class="gnb-account-container">
        <div @click="this.$router.push('/login')" class="gnb-login">로그인</div>
        <span>/</span>
        <div @click="this.$router.push('/signup')" class="gnb-signup">회원가입</div>
      </div>

      <!-- 로그인한 회원 상태 -->
      <div v-else class="gnb-account-container">
        <div class="gnb-account-user-name">{{ getCurrentUserName() }}님 안녕하세요!</div>
        <div
            v-if="isLogin"
            @click="onLogout"
            class="gnb-account-logout">
          로그아웃
        </div>
      </div>

    </div>

  </header>

</template>

<script>
/**
 * 홈페이지의 GNB 컴포넌트
 */
import {getCurrentUserName, isAuthenticated} from "@/util/authUtil";

export default {
  name: "GNB",
  data() {
    return {
      isLogin: false,
    }
  },
  created() {
    /* 스토어에서 인증을 체크합니다. */
    this.isLogin = isAuthenticated();
  },
  methods: {
    getCurrentUserName,
    onLogout() {
      if (confirm('로그아웃 하시겠습니까?')) {
        this.$store.commit('loginStore/logout');
      }
    }
  }
}
</script>

<style scoped>

header {
  margin: 20px 0;
  border-bottom: var(--main-border);
}

.gnb-title {
  cursor: pointer;
  margin: 0;
  font-size: var(--title-font-size);
  text-align: center;
}

.gnb-nav-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.gnb-nav-list {
  list-style: none;
  display: flex;
  padding: 0;
  align-items: center;
  justify-content: center;
}

.gnb-nav-list > li {
  border-right: var(--main-border);
  padding: 0 10px;
  font-size: var(--middle-font-size);
  font-weight: bold;
  cursor: pointer;
}

.gnb-nav-list .gnb-nav-notices {
  padding-left: 0;
}

.gnb-account-container {
  display: flex;
  gap: 5px;
  font-size: var(--small-font-size);
}

.gnb-login , .gnb-signup, .gnb-account-logout {
  cursor: pointer;
}

.gnb-account-user-name {
  margin-right: 20px;
}

</style>