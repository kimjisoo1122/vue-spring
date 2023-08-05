<template>

  <board-title title="문의 게시판"></board-title>

  <div class="register-container">

    <div class="register-title-container">
      <board-form-title name="제목" class="board-form-title-title">
      </board-form-title>
      <div class="register-title-input-container">
        <base-input
            v-model="registerForm.boardTitle"
            @change="errorFields.boardTitle = validateTitle(registerForm.boardTitle)"
            placeholder="제목을 입력해 주세요."
            class="register-title">
        </base-input>
        <input-error :error-msg="errorFields.boardTitle"></input-error>
      </div>
    </div>

    <div class="register-content-container">
      <board-form-title name="내용" class="board-form-title-content">
      </board-form-title>
      <div class="register-content-input-container">
        <base-textarea
            v-model="registerForm.boardContent"
            @change="errorFields.boardContent = validateTitle(registerForm.boardContent)"
            class="register-content">
        </base-textarea>
        <input-error :error-msg="errorFields.boardContent"></input-error>
      </div>
    </div>

    <div class="register-secret-container">
      <board-form-title
          :required="false"
          name="비밀글"
          class="board-form-title-secret">
      </board-form-title>
      <div class="register-secret-input-container">
        <base-input
            v-model="registerForm.qnaSecret"
            type="checkbox"
            class="register-secret-input">
        </base-input>
      </div>
    </div>

    <div class="register-btn-container">
      <base-button
          @click="onRegister"
          name="등록"
          class="register-btn-save">
      </base-button>
      <base-button
          @click="onCancel"
          name="취소"
          class="register-btn-cancel">
      </base-button>
    </div>

  </div>

</template>

<script setup>

import BoardTitle from "@/components/board/BoardTitle.vue";
import BoardFormTitle from "@/components/board/BoardFormTitle.vue";
import BaseInput from "@/components/base/BaseInput.vue";
import InputError from "@/components/InputError.vue";
import BaseTextarea from "@/components/base/BaseTextarea.vue";
import {ref} from "vue";
import BaseButton from "@/components/base/BaseButton.vue";
import {createCondition} from "@/util/queryParamUtil";
import {useRoute, useRouter} from "vue-router";
import {validateContent, validateTitle} from "@/util/boardValidUtil";
import {useStore} from "vuex";
import {registerQna} from "@/api/board/qnaService";

const route = useRoute();
const router = useRouter();
const store = useStore();

/* 문의게시글 등록정보 */
const registerForm = ref({
  boardTitle: '',
  boardContent: '',
  qnaSecret: false,
})
/* 유효성검증 */
const errorFields = ref({
  boardTitle: '',
  boardContent: '',
})

const condition = ref({}); // 검색조건

initQnaRegister();

/**
 * 문의게시글 등록 컴포넌트를 초기화합니다.
 * @returns {Promise<void>}
 */
function initQnaRegister() {
  condition.value = createCondition(route.query);
}

/**
 * 문의게시글을 등록합니다.
 */
async function onRegister() {
  if (!validateRegisterForm()) {
    return false;
  }

  try {
    const formData = createFormData();

    const qnaId = await registerQna(formData);

    router.push({
      path: `/qna/${qnaId}`,
      query: condition.value
    })
  } catch ({data, message}) {
    // 유효성검증에 실패한 필드의 에러메시지를 저장합니다.
    for (const field in data) {
      errorFields.value[field] = data[field];
    }
    console.error(message);
  }
}

/**
 * FormData를 생성합니다
 * @returns FormData
 */
function createFormData() {
  const formData = new FormData();

  for (const field in registerForm.value) {
    formData.append(field, registerForm.value[field]);
    console.log(field, registerForm.value[field]);
  }

  return formData;
}

/**
 * 등록폼을 검증합니다.
 * @returns {boolean}
 */
function validateRegisterForm() {
  errorFields.value.boardTitle = validateTitle(registerForm.value.boardTitle);
  errorFields.value.boardContent = validateContent(registerForm.value.boardContent);

  for (const errorMsg of Object.values(errorFields.value)) {
    if (errorMsg) {
      return false;
    }
  }

  return true;
}

/**
 * 게시글 작성 취소
 */
function onCancel() {
  if (confirm('작성을 취소하시겠습니까?')) {
    router.push({path: '/qna', query: condition.value});
  }
}

</script>


<script>
export default {
  name: "QnaRegister"
}
</script>

<style scoped>

.register-title-container, .register-content-container,
.register-secret-container {
  border-bottom: 1px solid var(--border-color-gray);
  height: 100%;
  display: flex;
}

.register-title-input-container, .register-content-input-container,
.register-secret-input-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 5px 0 5px 10px;
}

.register-title-container {
  border-top: var(--main-border);
}

.register-title {
  width: 95%;
  height: 40px;
}

.register-content {
  width: 95%;
  min-height: 200px;
}

.register-secret-input {
  width: 20px;
}

.register-btn-container {
  margin: 30px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
}

.register-btn-save, .register-btn-cancel {
  width: 70px;
}

.register-btn-cancel {
  background-color: var(--sub-color-violet);
}

</style>