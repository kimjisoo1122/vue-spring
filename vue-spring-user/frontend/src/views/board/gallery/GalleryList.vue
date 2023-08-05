<template>

  <!-- 게시글 제목 -->
  <board-title title="갤러리" ></board-title>

  <!-- 게시글 검색조건 -->
  <board-search-condition
      :category-list="categoryList"
      :condition="condition"
      :is-gallery-limit="true"
      @condition-search="onConditionSearch">
  </board-search-condition>

  <div class="register-btn-container">
    <base-button
        @click="router.push({path: '/galleries/register',query: condition})"
        name="갤러리 등록"></base-button>
  </div>

  <div class="gallery-list-container">

    <div
        v-for="gallery in galleryList"
        :key="gallery.boardId"
        class="gallery-container">

      <div class="gallery-img-container">
        <img
            @click="onDetailRouter(gallery.boardId)"
            :src="`/api/file/image/${gallery.galleryImgName}`"
            alt="갤러리 이미지"
            class="gallery-img">
      </div>
      <div class="gallery-info-container">
        <div class="gallery-title-container">
          <div
              @click="onDetailRouter(gallery.boardId)"
              class="gallery-title">
            {{ gallery.boardTitle }}
          </div>
          <span class="galley-title-new">new</span>
        </div>

        <div class="gallery-content">{{ gallery.boardContent }}</div>
      </div>

    </div>

    <!-- 페이징 처리 -->
    <pagination
        :total-cnt="totalCnt"
        :limit="Number(condition.limit)"
        :page="Number(condition.page)"
        @page-router="onPageRouter"
        class="paging-container">
    </pagination>

  </div>

</template>
<script setup>

import BoardTitle from "@/components/board/BoardTitle.vue";
import BoardSearchCondition from "@/components/board/BoardSearchCondition.vue";
import Pagination from "@/components/Pagination.vue";
import {useRoute, useRouter} from "vue-router";
import {useStore} from "vuex";
import {ref, watch} from "vue";
import {createCondition} from "@/util/queryParamUtil";
import {getCategoryList} from "@/api/categoryService";
import {GALLERY_CATEGORY_ID} from "@/constants";
import BaseButton from "@/components/base/BaseButton.vue";
import {getGalleryList} from "@/api/board/galleryService";

const router = useRouter();
const route = useRoute();
const store = useStore();

const categoryList = ref([]); /* 카테고리 목록 */
const galleryList = ref([]); /* 갤러리 목록 */
const totalCnt = ref(0); /* 게시글 총 개수 */
const condition = ref({}); /* 게시글 검색조건 */

initGalleryList(); /* 컴포넌트 초기화 */
watch(route, initGalleryList); /* 컴포넌트 URL 변경을 감지합니다.(페이징 처리) */

/**
 * 갤러리 목록 컴포넌트를 초기화 합니다.
 *
 * @returns {Promise<void>}
 */
async function initGalleryList() {
  condition.value = createCondition(route.query, 3);
  console.log(condition.value);

  try {
    const [categoryListResult, galleryListResult] = await Promise.all([
      getCategoryList(GALLERY_CATEGORY_ID),
      getGalleryList(condition.value),
    ]);

    categoryList.value = categoryListResult;
    galleryList.value = galleryListResult.galleryList;
    totalCnt.value = galleryList.value.length === 0 ? 0 : galleryListResult.totalCnt;

  } catch ({message}) {
    console.error(message);

    if (message) {
      // store.commit('logout');
      // router.push('/login');
    }
  }
}

/**
 * 게시글 검색조건으로 재 조회 합니다.
 * @param searchForm 게시글 검색정보
 */
function onConditionSearch(searchForm) {
  router.push({
    path: '/galleries',
    query: searchForm
  })
}

/**
 * 페이징처리
 * @param page 해당 페이지
 */
function onPageRouter(page) {
  condition.value.page = page;

  router.push({
    path: `/galleries`,
    query: condition.value
  });
}

/**
 * 갤러리 상세페이지로 라우팅합니다.
 * @param board 갤러리 정보
 */
function onDetailRouter(board) {
  router.push({
    path: `/galleries/${board.boardId}`,
    query: condition.value
  });
}

</script>

<script>
export default {
  name: "GalleryList"
}
</script>

<style scoped>

.register-btn-container {
  margin-top: 30px;
  display: flex;
  justify-content: flex-end;
}

.gallery-list-container {
  margin-top: 10px;
}

.gallery-container {
  border: var(--main-border);
  padding: 10px;
  display: flex;
  margin-bottom: 10px;
}

.gallery-img-container {
  width: 200px;
  height: 120px;
}

.gallery-img {
  display: block;
  width: 100%;
  height: 100%;
  cursor: pointer;
}

.gallery-info-container {
  margin-left: 30px;
  font-size: var(--middle-font-size);
  max-width: 500px;
}

.gallery-title-container {
  display: flex;
  align-items: center;
}

.gallery-title {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  font-weight: bold;
  margin: 20px 0;
  cursor: pointer;
}

.galley-title-new {
  margin-left: 10px;
  font-size: var(--small-font-size);
  color: orange;
}

.gallery-content {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.paging-container {

  margin-top: 30px;
}

</style>