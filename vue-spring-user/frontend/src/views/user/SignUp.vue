<template>

  <div class="signup-container">

    <div class="signup-title-container">
      <h1 class="signup-title">회원가입</h1>
    </div>

    <div class="signup-input-container">

      <!-- 아이디 인풋 -->
      <div class="signup-id-container">

        <base-input v-model="user.userId"
               @change="errorFields.userId = validateUserId($event.target.value)"
               placeholder="아이디"
               :readonly="checkedId"
               class="signup-input-id">
        </base-input>

        <!-- 중복확인 -->
        <base-button @click="onDoubleCheckId"
                name="중복확인"
                class="signup-id-check-btn">
        </base-button>

      </div>
      <input-error :error-msg="errorFields.userId" class="signup-id-error"></input-error>

      <!-- 비밀번호 인풋 -->
      <base-input v-model="user.userPw"
             type="password"
             @change="errorFields.userPw = validateUserPw($event.target.value, user.userId)"
             placeholder="비밀번호"
             class="signup-input-pw">
      </base-input>
      <input-error :error-msg="errorFields.userPw" class="signup-pw-error"></input-error>

      <!-- 비밀번호 확인 -->
      <base-input v-model="user.confirmPw"
             type="password"
             placeholder="비밀번호 확인"
             class="signup-input-confirm-pw">
      </base-input>
      <input-error :error-msg="errorFields.confirmPw" class="signup-confirm-error"></input-error>

      <!-- 이름 인풋 -->
      <base-input v-model="user.userName"
             @change="errorFields.userName = validateUserName($event.target.value)"
             placeholder="이름"
                  class="signup-input-pw">
      </base-input>
      <input-error :errorMsg="errorFields.userName" class="signup-name-error"></input-error>

    </div>

    <!-- 회원가입 버튼 -->
    <div class="signup-btn-container">
      <base-button @click="onSignUp" name="회원가입" class="signup-btn-submit"></base-button>
    </div>

  </div>

</template>

<script setup>
/**
 * 회원가입 컴포넌트
 */
import {ref} from "vue";
import {doubleCheckId, signUp} from "@/api/userService";
import {validateConfirm, validateUserId, validateUserName, validateUserPw} from "@/util/userValidUtil";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import InputError from "@/components/base/BaseInputError.vue";
import BaseInput from "@/components/base/BaseInput.vue";
import BaseButton from "@/components/base/BaseButton.vue";

const router = useRouter();
const store = useStore();

/* 사용자 정보 */
const user = ref({
  userId: '', /* 사용자 아이디 */
  userPw: '', /* 사용자 비밀번호 */
  confirmPw: '', /* 확인용 비밀번호 */
  userName: '', /* 사용자 이름 */
});

/* 사용자정보 유효성검증 */
const errorFields = ref({
  userId: '', /* 아이디 에러메시지 */
  userPw: '', /* 비밀번호 에러메시지 */
  confirmPw: '', /* 확인비밀번호 에러메시지 */
  userName: '', /* 이름 에러메시지 */
});

/* 중복확인 체크여부 */
const checkedId = ref(false);

/**
 * 아이디를 중복확인 합니다.
 *
 * @returns {Promise<boolean>}
 */
const onDoubleCheckId = async () => {
  errorFields.value.userId = validateUserId(user.value.userId);
  if (errorFields.value.userId) {
    return false;
  }

  try {
    checkedId.value = await doubleCheckId(user.value.userId);

    if (checkedId.value) {
      alert("사용가능한 아이디 입니다.")
    } else {
      alert('해당 아이디는 중복입니다.');
    }
  } catch ({message}) {
    console.error(message);
  }
}

/**
 * 회원가입을 서버에 요청합니다.
 * 로그인 폼으로 아이디를 포함하여 라우팅합니다.
 *
 * @returns {Promise<boolean>}
 */
const onSignUp = async () => {
  if (!validateForm()) {
    return false;
  } else if (!checkedId.value) {
    alert('중복확인이 필요합니다.');
    return false;
  }

  try {
    const signUpId = await signUp(user.value);

    router.push({
      path: '/login',
      query: {userId: signUpId}
    });
  } catch ({data}) {
    for (const field in data) {
      errorFields.value[field] = data[field];
    }
  }
};

/**
 * 폼 데이터를 검증합니다.
 * @returns {boolean}
 */
const validateForm = () => {
  errorFields.value.userId = validateUserId(user.value.userId);
  errorFields.value.userPw = validateUserPw(user.value.userPw, user.value.userId);
  errorFields.value.confirmPw = validateConfirm(user.value.userPw, user.value.confirmPw);
  errorFields.value.userName = validateUserName(user.value.userName);

  for (const field in errorFields.value) {
    if (errorFields.value[field].length > 0) {
      return false;
    }
  }

  return true;
}

</script>

<script>
export default {
  name: 'SignUp'
}
</script>

<style scoped>

  .signup-container {
    width: 300px;
    margin: 0 auto;
  }

  .signup-title-container {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 50px;
  }

  .signup-title {
    font-size: var(--title-font-size);
  }

  .signup-input-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 10px;
  }

  .signup-id-container {
    width: 300px;
    display: flex;
    justify-content: space-between;
  }

  .signup-input-id {
    flex-basis: 70%;
  }

  .signup-id-check-btn {
    flex-basis: 27%;
  }

  .signup-id-error, .signup-pw-error, .signup-confirm-error, .signup-name-error {
    margin-right: auto;
    padding: 5px 3px;
  }

  .signup-btn-container {
    display: flex;
    gap: 10px;
    flex-direction: column;
    align-items: center;
  }

  .signup-btn-container button {
    box-sizing: border-box;
    width: 300px;
    height: 40px;
    background-color: var(--sub-color-violet);
  }

</style>