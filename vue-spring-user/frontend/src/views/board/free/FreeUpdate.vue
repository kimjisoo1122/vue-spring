<template>

  <GNB></GNB>

  <!-- 타이틀 -->
  <board-title title="자유게시판"></board-title>


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
            @change="errorFields.boardContent = validateTitle(updateForm.boardContent)"
            class="update-content">
        </base-textarea>
        <input-error :error-msg="errorFields.boardContent"></input-error>
      </div>
    </div>

    <!-- 첨부파일 -->
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
            :file-list="fileList">
        </board-file-list>
        <input-error :error-msg="errorFields.saveFiles"></input-error>
      </div>

    </div>

    <!-- 수정 폼 버튼 -->
    <board-form-btn-container
        @update="onUpdate"
        @cancel="onCancel"
        form-type="update">
    </board-form-btn-container>

  </div>

</template>

<script setup>
/**
 * 자유게시글 수정 폼 컴포넌트
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
import {getBoardFileList} from "@/api/fileService";
import {useRoute, useRouter} from "vue-router";
import {getFreeDetail, updateFree} from "@/api/board/freeService";
import {FREE_CATEGORY_ID} from "@/constants";
import {getCategoryList} from "@/api/categoryService";
import {useStore} from "vuex";
import {isCurrentUserId} from "@/util/authUtil";
import GNB from "@/components/GNB.vue";
import BoardFormBtnContainer from "@/components/board/BoardFormBtnContainer.vue";
import {createFormData, initFormValue} from "@/util/boardUtil";
import {createCondition} from "@/util/queryParamUtil";

const store = useStore();
const route = useRoute();
const router = useRouter();

/* 자유게시글 수정정보 */
const updateForm = ref({
  categoryId: '', /* 카테고리 번호 */
  boardTitle: '', /* 게시글 제목 */
  boardContent: '', /* 게시글 내용 */
})
/* 유효성검증 에러메시지 */
const errorFields = ref({
  categoryId: '', /* 카테고리 번호 */
  boardTitle: '', /* 게시글 제목 */
  boardContent: '', /* 게시글 내용 */
  saveFiles: '', /* 첨부파일 */
})

const free = ref({}); // 해당 자유게시글
const categoryList = ref([]); // 카테고리 목록
const condition = ref({}); // 검색조건
const fileList = ref([]); // 첨부 파일 목록
const deleteFiles = ref([]); // 삭제 파일 목록
const saveFiles = ref({}); /* 저장 파일 목록 */

initFreeUpdate();

/**
 * 자유게시글 수정 컴포넌트를 초기화합니다.
 */
async function initFreeUpdate() {
  condition.value = createCondition(route.query);

  try {
    const boardId = route.params.boardId;

    const [fileListResult, freeResult, categoryResult] =await Promise.all([
      getBoardFileList(boardId),
      getFreeDetail(boardId),
      getCategoryList(FREE_CATEGORY_ID)
    ])

    fileList.value = fileListResult;
    free.value = freeResult;
    categoryList.value = categoryResult;

    initFormValue(updateForm.value, free.value);
  } catch ({message}) {
    console.error(message);
  }
}

/**
 * 자유게시글을 수정합니다.
 */
async function onUpdate() {
    if (!validateUpdateForm() && !isCurrentUserId(free.value.userId)) {
      return false;
    }

    try {
      const formData = createFormData(updateForm.value, saveFiles.value, deleteFiles.value);

      const boardId = await updateFree(route.params.boardId, formData);

      router.push({path: `/frees/${boardId}`, query: condition.value});

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

/* 수정을 취소합니다. */
function onCancel() {
  router.push({path: '/frees', query: condition.value});
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
      console.log(errorMsg);
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
  name: "FreeUpdate"
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

.update-category-container {
  border-top: 1px solid var(--border-color-gray);
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