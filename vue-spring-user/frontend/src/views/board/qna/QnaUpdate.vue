<template>

  <board-title title="문의 게시판"></board-title>

  <div class="update-container">

    <div class="update-title-container">
      <board-form-title name="제목" class="board-form-title-title">
      </board-form-title>
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

    <div class="update-btn-container">
      <base-button
          @click="onUpdate"
          name="수정"
          class="update-btn-save">
      </base-button>
      <base-button
          @click="onCancel"
          name="취소"
          class="update-btn-cancel">
      </base-button>
    </div>

  </div>
</template>
<script setup>

import BoardTitle from "@/components/board/BoardTitle.vue";
import BoardFormTitle from "@/components/board/BoardFormTitle.vue";
import {ref} from "vue";
import CategorySelect from "@/components/CategorySelect.vue";
import InputError from "@/components/InputError.vue";
import BaseInput from "@/components/base/BaseInput.vue";
import {validateCategory, validateContent, validateTitle} from "@/util/boardValidUtil";
import BaseTextarea from "@/components/base/BaseTextarea.vue";
import BoardFileList from "@/components/board/BoardFileList.vue";
import BaseButton from "@/components/base/BaseButton.vue";
import {getBoardFileList} from "@/api/fileService";
import {useRoute, useRouter} from "vue-router";
import {getFreeDetail, updateFree} from "@/api/board/freeService";
import {FREE_CATEGORY_ID} from "@/constants";
import {getCategoryList} from "@/api/categoryService";
import {useStore} from "vuex";
import {getQnaDetail, updateQna} from "@/api/board/qnaService";
import {isCurrentUserId} from "@/util/authUtil";

const store = useStore();
const route = useRoute();
const router = useRouter();

/* 문의게시글 등록정보 */
const updateForm = ref({
  boardTitle: '',
  boardContent: '',
  qnaSecret: true,
})
/* 유효성검증 */
const errorFields = ref({
  boardTitle: '',
  boardContent: '',
})

const qna = ref({}); // 해당게시글
const condition = ref({}); // 검색조건

initQnaUpdate();

/**
 * 문의게시글 수정컴포넌트를 초기화합니다.
 * @returns {Promise<void>}
 */
async function initQnaUpdate() {
  try {
    const boardId = route.params.boardId;
    const [qnaResult] =await Promise.all([
      getQnaDetail(boardId),
    ])

    qna.value = qnaResult;

    for (const field in updateForm.value) {
      updateForm.value[field] = qna.value[field];
    }
    console.log(qna.value.qnaSecret);
  } catch ({message}) {
    console.error(message);
  }
}


/**
 * 게시글을 수정합니다.
 */
async function onUpdate() {
  if (confirm('수정 하시겠습니까?')) {
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

}

function onCancel() {
  if (confirm('수정을 취소하시겠습니까?')) {
    router.push({path: '/qna', query: condition.value});
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

.update-btn-container {
  margin: 30px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
}

.update-btn-save, .update-btn-cancel {
  width: 70px;
}

.update-btn-cancel {
  background-color: var(--sub-color-violet);
}


</style>