<template>

  <div class="login-title-container">
    <h1 class="login-title">로그인</h1>
  </div>
  <div class="login-input-container">
    <Input v-model="userId"
           placeholder="아이디"
           class="login-input-id" />
    <Input v-model="userPw"
           type="password"
           placeholder="비밀번호"
           class="login-input-pw"/>
  </div>

  <div class="login-btn-container">
    <Button name="로그인" class="login-btn-join"/>
    <Button name="회원가입" class="login-btn-sign"/>
  </div>

</template>

<script>

import Button from "@/components/Button.vue";
import Input from "@/components/Input.vue";

export default {
  name: "Login",
  data() {
    return {
      userId:'',
      userPw:'',
    }
  },

  methods: {
    test() {
      console.log(this.userId, this.userPw);
    },
  },

  components: {Button, Input}
}
</script>

<style scoped>

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
    height: 30px;
  }

  .login-btn-sign {
    background-color: var(--sub-color-violet);
  }

</style>