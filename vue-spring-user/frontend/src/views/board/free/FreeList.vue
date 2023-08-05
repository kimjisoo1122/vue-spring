<template>

  <!-- 게시글 제목 -->
  <board-title title="자유게시판" ></board-title>

  <!-- 게시글 검색조건 -->
  <board-search-condition
      :category-list="categoryList"
      :condition="condition"
      @condition-search="onConditionSearch">
  </board-search-condition>

  <div class="register-btn-container">
    <base-button
        @click="router.push({path: '/frees/register',query: condition})"
        name="글 등록">
    </base-button>
  </div>

  <div class="free-list-container">

    <!-- 게시글 헤더 -->
    <board-list-header></board-list-header>

    <!-- 자유게시글 -->
    <board-list
        :board-list="freeList"
        :condition="condition"
        @detail-router="onDetailRouter">
    </board-list>

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
import BoardListHeader from "@/components/board/BoardListHeader.vue";
import Pagination from "@/components/Pagination.vue";
import BoardList from "@/components/board/BoardList.vue";
import {useRoute, useRouter} from "vue-router";
import {useStore} from "vuex";
import {ref, watch} from "vue";
import {createCondition} from "@/util/queryParamUtil";
import {getCategoryList} from "@/api/categoryService";
import {FREE_CATEGORY_ID} from "@/constants";
import {getFreeList} from "@/api/board/freeService";
import BaseButton from "@/components/base/BaseButton.vue";

const router = useRouter();
const route = useRoute();
const store = useStore();

const categoryList = ref([]); /* 카테고리 목록 */
const freeList = ref([]); /* 자유게시글 목록 */
const totalCnt = ref(0); /* 게시글 총 개수 */
const condition = ref({}); /* 게시글 검색조건 */

initFreeList(); /* 컴포넌트 초기화 */
watch(route, initFreeList); /* 컴포넌트 URL 변경을 감지합니다.(페이징 처리) */

/**
 * 자유게시글 목록 컴포넌트를 초기화 합니다.
 *
 * @returns {Promise<void>}
 */
async function initFreeList() {
  condition.value = createCondition(route.query);

  try {
    const [categoryListResult, freeListResult] = await Promise.all([
      getCategoryList(FREE_CATEGORY_ID),
      getFreeList(condition.value),
    ]);

    categoryList.value = categoryListResult;
    freeList.value = freeListResult.freeList;
    totalCnt.value = freeList.value.length === 0 ? 0 : freeListResult.totalCnt;

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
    path: '/frees',
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
    path: `/frees`,
    query: condition.value
  });
}

/**
 * 자유게시글 상세페이지로 라우팅합니다.
 * @param board 자유게시글 번호
 */
function onDetailRouter(board) {
  router.push({
    path: `/frees/${board.boardId}`,
    query: condition.value
  });
}

</script>

<script>
export default {
  name: "FreeList"
}
</script>

<style scoped>

.free-list-container {
  margin-top: 10px;
}

.register-btn-container {
  margin-top: 30px;
  display: flex;
  justify-content: flex-end;
}

.paging-container {
  margin-top: 30px;
}

</style>