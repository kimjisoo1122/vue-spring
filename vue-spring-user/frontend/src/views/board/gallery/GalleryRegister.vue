<template>

  <GNB></GNB>

  <!-- 타이틀 -->
  <board-title title="갤러리"></board-title>

  <div class="register-container">

    <!-- 게시글 카테고리 -->
    <div class="register-category-container">

      <board-form-title name="분류" class="board-form-title-category"></board-form-title>

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

    <!-- 게시글 제목 -->
    <div class="register-title-container">

      <board-form-title name="제목" class="board-form-title-title"></board-form-title>

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

    <!-- 게시글 내용 -->
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

    <!-- 첨부 파일 -->
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

    <!-- 등록 폼 -->
    <board-form-btn-container
        @register="onRegister"
        @cancel="router.push({path: '/galleries', query: condition})"
        form-type="register">
    </board-form-btn-container>

  </div>

</template>

<script setup>
/**
 * 갤러리 등록 컴포넌트
 */

import BoardTitle from "@/components/board/BoardTitle.vue";
import BoardFormTitle from "@/components/board/BoardFormTitle.vue";
import BaseInput from "@/components/base/BaseInput.vue";
import InputError from "@/components/base/BaseInputError.vue";
import CategorySelect from "@/components/CategorySelect.vue";
import BaseTextarea from "@/components/base/BaseTextarea.vue";
import {ref} from "vue";
import {createCondition} from "@/util/queryParamUtil";
import {useRoute, useRouter} from "vue-router";
import {GALLERY_CATEGORY_ID} from "@/constants";
import {getCategoryList} from "@/api/categoryService";
import BoardFileList from "@/components/board/BoardFileList.vue";
import {validateCategory, validateContent, validateTitle} from "@/util/boardValidUtil";
import {useStore} from "vuex";
import {registerGallery} from "@/api/board/galleryService";
import GNB from "@/components/GNB.vue";
import BoardFormBtnContainer from "@/components/board/BoardFormBtnContainer.vue";
import {isAuthenticated} from "@/util/authUtil";

const route = useRoute();
const router = useRouter();
const store = useStore();

/* 갤러리 등록정보 */
const registerForm = ref({
  categoryId: '', /* 카테고리 번호 */
  boardTitle: '', /* 게시글 제목 */
  boardContent: '', /* 게시글 내용 */
})
/* 유효성검증 에러메시지 */
const errorFields = ref({
  categoryId: '', /* 카테고리 번호 */
  boardTitle: '', /* 게시글 제목*/
  boardContent: '', /* 게시글 내용 */
  saveFiles: '', /* 첨부파일 */
})
const categoryList = ref([]); // 카테고리 목록
const condition = ref({}); // 검색조건

initGalleryRegister();

/**
 * 갤러리 등록 컴포넌트를 초기화합니다.
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
 * 갤러리를 등록합니다.
 */
async function onRegister() {
  if (!validateRegisterForm() && isAuthenticated()) {
    return false;
  }

  try {
    const formData = createFormData();

    if (formData.get('saveFiles') === null) {
      alert('이미지를 첨부해주세요.');

      return false;
    }

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
 * @return formData
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

function validateGalleryFile() {

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

</style>