<template>

  <GNB></GNB>

  <!-- 타이틀 -->
  <board-title title="문의 게시판"></board-title>

  <div class="update-container">

    <!-- 게시글 제목 -->
    <div class="update-title-container">

      <board-form-title name="제목" class="board-form-title-title"></board-form-title>

      <div class="update-title-input-container">
        <base-input
            v-model="updateForm.boardTitle"
            @change="errorFields.boardTitle = validateTitle(updateForm.boardTitle)"
            placeholder="제목을 입력해 주세요."
            class="update-title">
        </base-input>
        <input-error :error-msg="errorFields.boardTitle"></input-error>
      </div>

    </div>

    <!-- 게시글 내용 -->
    <div class="update-content-container">
      <board-form-title name="내용" class="board-form-title-content">
      </board-form-title>
      <div class="update-content-input-container">
        <base-textarea
            v-model="updateForm.boardContent"
            @change="errorFields.boardContent = validateTitle(updateForm.boardContent)"
            class="update-content">
        </base-textarea>
        <input-error :error-msg="errorFields.boardContent"></input-error>
      </div>
    </div>

    <!-- 비밀글 체크박스 -->
    <div class="update-secret-container">

      <board-form-title
          :required="false"
          name="비밀글"
          class="board-form-title-secret">
      </board-form-title>

      <div class="update-secret-input-container">
        <base-input
            v-model="updateForm.qnaSecret"
            type="checkbox"
            class="update-secret-input">
        </base-input>
      </div>

    </div>

    <!-- 수정 폼 버튼 -->
    <board-form-btn-container
        @update="onUpdate"
        @cancel="router.push({path: '/qna', query: condition});"
        form-type="update">
    </board-form-btn-container>

  </div>
</template>
<script setup>
/**
 * 문의게시글 수정 컴포넌트
 */

import BoardTitle from "@/components/board/BoardTitle.vue";
import BoardFormTitle from "@/components/board/BoardFormTitle.vue";
import {ref} from "vue";
import InputError from "@/components/base/BaseInputError.vue";
import BaseInput from "@/components/base/BaseInput.vue";
import {validateContent, validateTitle} from "@/util/boardValidUtil";
import BaseTextarea from "@/components/base/BaseTextarea.vue";
import {useRoute, useRouter} from "vue-router";
import {useStore} from "vuex";
import {getQnaDetail, updateQna} from "@/api/board/qnaService";
import {isCurrentUserId} from "@/util/authUtil";
import GNB from "@/components/GNB.vue";
import BoardFormBtnContainer from "@/components/board/BoardFormBtnContainer.vue";
import {initFormValue} from "@/util/boardUtil";
import {createCondition} from "@/util/queryParamUtil";

const store = useStore();
const route = useRoute();
const router = useRouter();

/* 문의게시글 수정 정보 */
const updateForm = ref({
  boardTitle: '', /* 게시글 제목 */
  boardContent: '', /* 게시글 내용 */
  qnaSecret: true, /* 비밀글 여부 */
})
/* 유효성검증 에러메시지 */
const errorFields = ref({
  boardTitle: '', /* 게시글 제목*/
  boardContent: '', /* 게시글 내용*/
})

const qna = ref({}); // 해당게시글
const condition = ref({}); // 검색조건

initQnaUpdate();

/**
 * 문의게시글 수정컴포넌트를 초기화합니다.
 * @returns {Promise<void>}
 */
async function initQnaUpdate() {
  condition.value = createCondition(route.query);
  condition.value.myQna = route.query.myQna || false;

  try {
    const boardId = route.params.boardId;
    const [qnaResult] =await Promise.all([
      getQnaDetail(boardId),
    ])

    qna.value = qnaResult;

    initFormValue(updateForm.value, qna.value)
  } catch ({message}) {
    console.error(message);
  }
}

/**
 * 문의게시글을 수정합니다.
 */
async function onUpdate() {
  if (!validateUpdateForm() && !isCurrentUserId(qna.value.userId)) {
    return false;
  }

  try {
    const formData = createFormData();

    const qnaId = await updateQna(route.params.boardId, formData);

    router.push({path: `/qna/${qnaId}`, query: condition.value});

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
 * @returns FormData 게시글form정보
 */
function createFormData() {
  const formData = new FormData();

  for (const field in updateForm.value) {
    formData.append(field, updateForm.value[field]);
  }

  return formData;
}

/**
 * 수정폼을 검증합니다.
 * @returns {boolean}
 */
function validateUpdateForm() {
  errorFields.value.boardTitle = validateTitle(updateForm.value.boardTitle);
  errorFields.value.boardContent = validateContent(updateForm.value.boardContent);

  for (const errorMsg of Object.values(errorFields.value)) {
    if (errorMsg) {
      return false;
    }
  }

  return true;
}

</script>


<script>
export default {
  name: "QnaUpdate"
}
</script>

<style scoped>

.update-title-container, .update-content-container,
.update-secret-container {
  border-bottom: 1px solid var(--border-color-gray);
  height: 100%;
  display: flex;
}

 .update-title-input-container, .update-content-input-container,
 .update-secret-input-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 5px 0 5px 10px;
}

.update-title {
  width: 95%;
  height: 40px;
}

.update-content {
  width: 95%;
  min-height: 200px;
}

.update-secret-input {
  width: 20px;
}

</style>