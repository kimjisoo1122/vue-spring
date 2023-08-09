<template>

  <div class="login-container" >

    <!-- 타이틀 -->
    <div class="login-title-container">
      <h1 class="login-title">로그인</h1>
    </div>

    <div class="login-input-container">

      <!-- 아이디 인풋 -->
      <base-input
          v-model="loginForm.userId"
          placeholder="아이디"
          class="login-input-id">
      </base-input>
      <input-error :error-msg="errorFields.userId" class="login-id-error"></input-error>

      <!-- 비밀번호 인풋 -->
      <base-input
          v-model="loginForm.userPw"
          type="password"
          placeholder="비밀번호"
          class="login-input-pw">
      </base-input>
      <input-error :error-msg="errorFields.userPw" class="login-pw-error"></input-error>

    </div>

    <!-- 로그인 폼 버튼 -->
    <div class="login-btn-container">

      <base-button
          @click="onLogin"
          name="로그인"
          class="login-btn-submit">
      </base-button>

      <base-button
          @click="$router.push('/signup')"
          name="회원가입"
          class="login-btn-signup">
      </base-button>

    </div>

  </div>

</template>

<script setup>
/**
 * 로그인 컴포넌트
 */
import BaseButton from "@/components/base/BaseButton.vue";
import BaseInput from "@/components/base/BaseInput.vue";
import InputError from "@/components/base/BaseInputError.vue";
import {onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import {login} from "@/api/loginService";
import {useStore} from "vuex";

const store = useStore();
const route = useRoute();
const router = useRouter();

/* 로그인 정보 */
const loginForm = ref({
  userId: '', /* 로그인 아이디 */
  userPw: '' /* 로그인 비밀번호 */
});
/* 로그인 에러필드 메시지 */
const errorFields = ref({
  userId: '', /* 로그인 아이디 에러메시지 */
  userPw: '' /* 로그인 비밀번호 에러메시지 */
})

onMounted(() => {
  /* 로그인에 실패한경우 쿼리에서 아이디 조회 */
  loginForm.value.userId = route.query.userId;
})

/**
 * 로그인을 요청하여 성공시 로컬스토리지에 JWT를 저장합니다.
 * 전역스토어에 인증상태를 true 변경, 로그인된 유저정보를 저장합니다.
 */
async function onLogin() {
  if (validateLoginForm()) {
    try {

      const {jwt, user} = await login(loginForm.value);

      store.commit('loginStore/login',
          {
            user: user,
            jwt: jwt
          });

      router.push(route.query.redirect || '/');
    } catch ({message}) {
      loginForm.value.userPw = '';
      alert(message);
    }
  }
}

/**
 * 로그인 요청 값을 검증합니다.
 * @returns {boolean}
 */
function validateLoginForm() {
  const fieldErrors = {
    userId: '아이디를 입력해주세요.',
    userPw: '비밀번호를 입력해주세요.'
  };

  for (const field in loginForm.value) {
    if (!loginForm.value[field]) {
      errorFields.value[field] = fieldErrors[field];
      return false;
    }
    errorFields.value[field] = '';
  }
  return true;
}

</script>

<script>

export default {
  name: "Login",
}
</script>

<style scoped>

  .login-container {
    width: 300px;
    margin: 0 auto;
  }

  .login-title-container {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 50px;
  }

  .login-title {
    font-size: var(--title-font-size);
  }

  .login-input-container {
    display: flex;
    gap: 10px;
    flex-direction: column;
    align-items: center;
    margin-bottom: 10px;
  }

  .login-btn-container {
    display: flex;
    gap: 10px;
    flex-direction: column;
    align-items: center;
  }

  .login-btn-container button {
    box-sizing: border-box;
    width: 300px;
    height: 40px;
  }

  .login-btn-signup {
    background-color: var(--sub-color-violet);
  }

  .login-id-error, .login-pw-error {
    margin-right: auto;
    padding-left: 3px;
  }

</style>