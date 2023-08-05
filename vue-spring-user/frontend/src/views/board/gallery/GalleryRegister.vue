<template>

  <board-title title="갤러리"></board-title>

  <div class="register-container">

    <div class="register-category-container">
      <board-form-title name="분류" class="board-form-title-category">
      </board-form-title>

      <div class="register-category-input-container">
        <category-select
            v-model="registerForm.categoryId"
            @change="errorFields.categoryId = validateCategory(registerForm.categoryId)"
            :category-list="categoryList"
            class="register-category">
        </category-select>
        <input-error :error-msg="errorFields.categoryId"></input-error>
      </div>

    </div>

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

    <div class="register-file-container">
      <board-form-title
          :required="false"
          name="갤러리 이미지"
          class="board-form-title-file">
      </board-form-title>
      <div class="register-file-input-container">
        <board-file-list></board-file-list>
        <input-error :error-msg="errorFields.saveFiles"></input-error>
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
import CategorySelect from "@/components/CategorySelect.vue";
import BaseTextarea from "@/components/base/BaseTextarea.vue";
import {ref} from "vue";
import BaseButton from "@/components/base/BaseButton.vue";
import {createCondition} from "@/util/queryParamUtil";
import {useRoute, useRouter} from "vue-router";
import {GALLERY_CATEGORY_ID} from "@/constants";
import {getCategoryList} from "@/api/categoryService";
import BoardFileList from "@/components/board/BoardFileList.vue";
import {validateCategory, validateContent, validateTitle} from "@/util/boardValidUtil";
import {useStore} from "vuex";
import {registerFree} from "@/api/board/freeService";
import {registerGallery} from "@/api/board/galleryService";

const route = useRoute();
const router = useRouter();
const store = useStore();

/* 자유게시글 등록정보 */
const registerForm = ref({
  categoryId: '',
  boardTitle: '',
  boardContent: '',
  saveFiles: [],
})
/* 유효성검증 */
const errorFields = ref({
  categoryId: '',
  boardTitle: '',
  boardContent: '',
  saveFiles: '',
})
const categoryList = ref([]); // 카테고리 목록
const condition = ref({}); // 검색조건

initGalleryRegister();

/**
 * 갤러리 등록 컴포넌트를 초기화합니다.
 * @returns {Promise<void>}
 */
async function initGalleryRegister() {
  condition.value = createCondition(route.query, 3);

  try {
    categoryList.value = await getCategoryList(GALLERY_CATEGORY_ID);
  } catch ({message}) {
    console.error(message);
  }
}

/**
 * 게시글을 등록합니다.
 */
async function onRegister() {
  if (!validateRegisterForm()) {
    return false;
  }

  try {
    const formData = createFormData();

    const freeId = await registerGallery(formData);

    store.commit('boardFileStore/clearFile');

    router.push({
      path: `/galleries/${freeId}`,
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
 * @returns FormData 게시글form정보
 */
function createFormData() {
  const formData = new FormData();

  for (const field in registerForm.value) {
    formData.append(field, registerForm.value[field]);
  }

  /* 첨부파일 추가 */
  store.getters['boardFileStore/getSaveFiles']
      .forEach(file => {
        formData.append('saveFiles', file);
      })

  return formData;
}

/**
 * 등록폼을 검증합니다.
 * @returns {boolean}
 */
function validateRegisterForm() {
  errorFields.value.boardTitle = validateTitle(registerForm.value.boardTitle);
  errorFields.value.boardContent = validateContent(registerForm.value.boardContent);
  errorFields.value.categoryId = validateCategory(registerForm.value.categoryId);

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
    router.push({path: '/galleries', query: condition.value});
  }
}

</script>


<script>
export default {
  name: "GalleryRegister"
}
</script>

<style scoped>

.register-category-container, .register-title-container,
.register-content-container, .register-file-container {
  border-bottom: 1px solid var(--border-color-gray);
  height: 100%;
  display: flex;
}

.register-category-input-container, .register-title-input-container,
.register-content-input-container, .register-file-input-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 5px 0 5px 10px;
}

.register-category-container {
  border-top: 1px solid var(--border-color-gray);
}

.register-category {
  width: 200px;
}

.register-title {
  width: 95%;
  height: 40px;
}

.register-content {
  width: 95%;
  min-height: 200px;
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