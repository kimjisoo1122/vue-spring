<template>

  <GNB></GNB>

  <!-- 타이틀 -->
  <board-title title="갤러리"></board-title>

  <div class="update-container">

    <!-- 게시글 카테고리 -->
    <div class="update-category-container">

      <board-form-title name="분류" class="board-form-title-category"></board-form-title>

      <div class="update-category-input-container">
        <category-select
            v-model="updateForm.categoryId"
            @change="errorFields.categoryId = validateCategory(updateForm.categoryId)"
            :category-list="categoryList"
            class="update-category">
        </category-select>
        <input-error :error-msg="errorFields.categoryId"></input-error>
      </div>

    </div>

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

      <board-form-title name="내용" class="board-form-title-content"></board-form-title>

      <div class="update-content-input-container">
        <base-textarea
            v-model="updateForm.boardContent"
            @change="errorFields.boardContent = validateContent(updateForm.boardContent)"
            class="update-content">
        </base-textarea>
        <input-error :error-msg="errorFields.boardContent"></input-error>
      </div>

    </div>

    <!-- 첨부 파일 -->
    <div class="update-file-container">

      <board-form-title
          :required="false"
          name="첨부"
          class="board-form-title-file">
      </board-form-title>

      <div class="update-file-input-container">
        <board-file-list
            @upload-file="onUploadFile"
            @delete-file="onDeleteFile"
            :is-update="true"
            :file-list="fileList"></board-file-list>
        <input-error :error-msg="errorFields.saveFiles"></input-error>
      </div>

    </div>

    <!-- 수정 폼 버튼 -->
    <board-form-btn-container
        @update="onUpdate"
        @cancel="router.push({path: '/galleries', query: condition});"
        form-type="update">
    </board-form-btn-container>

  </div>
</template>
<script setup>
/**
 * 갤러리 수정 폼 컴포넌트
 */

import BoardTitle from "@/components/board/BoardTitle.vue";
import BoardFormTitle from "@/components/board/BoardFormTitle.vue";
import {ref} from "vue";
import CategorySelect from "@/components/CategorySelect.vue";
import InputError from "@/components/base/BaseInputError.vue";
import BaseInput from "@/components/base/BaseInput.vue";
import {validateCategory, validateContent, validateTitle} from "@/util/boardValidUtil";
import BaseTextarea from "@/components/base/BaseTextarea.vue";
import BoardFileList from "@/components/board/BoardFileList.vue";
import {useRoute, useRouter} from "vue-router";
import {GALLERY_CATEGORY_ID} from "@/constants";
import {getCategoryList} from "@/api/categoryService";
import {useStore} from "vuex";
import {getGalleryDetail, updateGallery} from "@/api/board/galleryService";
import {isCurrentUserId} from "@/util/authUtil";
import GNB from "@/components/GNB.vue";
import BoardFormBtnContainer from "@/components/board/BoardFormBtnContainer.vue";
import {createFormData, initFormValue} from "@/util/boardUtil";
import {createCondition} from "@/util/queryParamUtil";

const store = useStore();
const route = useRoute();
const router = useRouter();

/* 갤러리 수정정보 */
const updateForm = ref({
  categoryId: '', /* 카테고리 번호 */
  boardTitle: '', /* 게시글 제목 */
  boardContent: '', /* 게시글 내용 */
})
/* 유효성검증 에러메시지 */
const errorFields = ref({
  categoryId: '', /* 카테고리 번호*/
  boardTitle: '', /* 게시글 제목 */
  boardContent: '', /* 게시글 내용 */
  saveFiles: '', /* 첨부파일 */
})

const gallery = ref({}); // 해당게시글
const categoryList = ref([]); // 카테고리 목록
const fileList = ref([]); // 첨부파일 목록
const condition = ref({}); // 검색조건
const deleteFiles = ref([]); // 삭제 파일 목록
const saveFiles = ref({}); /* 저장 파일 목록 */

initGalleryUpdate();

/**
 * 갤러리 수정 컴포넌트를 초기화합니다.
 */
async function initGalleryUpdate() {
  condition.value = createCondition(route.query, 3);

  try {
    const boardId = route.params.boardId;
    const [galleryResult, categoryResult] =await Promise.all([
      getGalleryDetail(boardId),
      getCategoryList(GALLERY_CATEGORY_ID)
    ])

    gallery.value = galleryResult.gallery;
    fileList.value = galleryResult.fileList;
    categoryList.value = categoryResult;

    initFormValue(updateForm.value, gallery.value);
  } catch ({message}) {
    console.error(message);
  }
}

/**
 * 갤러리를 수정합니다.
 */
async function onUpdate() {
  if (!validateUpdateForm() || !isCurrentUserId(gallery.value.userId)) {
    return false;
  }

  try {
    const formData = createFormData(updateForm.value, saveFiles.value, deleteFiles.value);

    const boardId = await updateGallery(route.params.boardId, formData);

    store.commit('boardFileStore/clearFile');

    router.push({path: `/galleries/${boardId}`, query: condition.value});

  } catch ({data, message}) {
    // 유효성검증에 실패한 필드의 에러메시지를 저장합니다.
    for (const field in data) {
      errorFields.value[field] = data[field];
    }
    console.error(message);
  }
}

/**
 * 첨부파일의 삭제버튼을 클릭시 삭제목록에 추가합니다.
 *
 * @param fileId 삭제파일번호
 */
function onDeleteFile(fileId) {
  deleteFiles.value.push(fileId);
  fileList.value = fileList.value.filter(e => e.fileId !== fileId);
}

/**
 * 수정폼을 검증합니다.
 * @returns {boolean}
 */
function validateUpdateForm() {
  errorFields.value.boardTitle = validateTitle(updateForm.value.boardTitle);
  errorFields.value.boardContent = validateContent(updateForm.value.boardContent);
  errorFields.value.categoryId = validateCategory(updateForm.value.categoryId);

  for (const errorMsg of Object.values(errorFields.value)) {
    if (errorMsg) {
      return false;
    }
  }

  return true;
}

/**
 * 파일 인풋 컴포넌트에서 전송된 파일들을 저장합니다.
 *
 * @param uploadFiles
 */
function onUploadFile(uploadFiles) {
  saveFiles.value = uploadFiles
}

</script>


<script>
export default {
  name: "GalleryUpdate"
}
</script>

<style scoped>

.update-category-container, .update-title-container,
.update-content-container, .update-file-container {
  border-bottom: 1px solid var(--border-color-gray);
  height: 100%;
  display: flex;
}

.update-category-input-container, .update-title-input-container,
.update-content-input-container, .update-file-input-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 5px 0 5px 10px;
}

.update-category {
  width: 200px;
}

.update-title {
  width: 95%;
  height: 40px;
}

.update-content {
  width: 95%;
  min-height: 200px;
}

</style>