<template>

  <GNB></GNB>

  <!-- 타이틀 -->
  <board-title title="문의 게시판" ></board-title>

  <!-- 게시글 검색조건 -->
  <board-search-condition
      @condition-search="onConditionSearch"
      :condition="condition"
      :is-qna="true">
  </board-search-condition>

  <!-- 나의 문의내역, 글등록 -->
  <div class="my-qna-register-container">

    <div class="my-qna-container">
      <span class="my-qna-span">나의 문의내역만 보기</span>
      <base-input
          v-model="condition.myQna"
          @change="onConditionSearch"
          type="checkbox"
          class="my-qna-input">
      </base-input>
    </div>

    <base-button
        @click="router.push({path: '/qna/register',query: condition})"
        name="글 등록"
        class="my-qna-register-btn">
    </base-button>

  </div>

  <!-- 문의게시글 목록 -->
  <div class="qna-list-container">

    <!-- 게시글 헤더 -->
    <board-list-header :is-qna="true"></board-list-header>

    <!-- 문의게시글 -->
    <board-list
        @detail-router="onDetailRouter"
        :is-qna="true"
        :board-list="qnaList"
        :condition="condition">
    </board-list>

    <!-- 페이징 처리 -->
    <pagination
        @page-router="onPageRouter"
        :total-cnt="totalCnt"
        :limit="Number(condition.limit)"
        :page="Number(condition.page)"
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
import {getQnaList} from "@/api/board/qnaService";
import GNB from "@/components/GNB.vue";
import {getCurrentUserId} from "@/util/authUtil";
import BaseInput from "@/components/base/BaseInput.vue";

const router = useRouter();
const route = useRoute();
const store = useStore();

const qnaList = ref([]); /* 문의게시글 목록 */
const totalCnt = ref(0); /* 게시글 총 개수 */
const condition = ref({}); /* 게시글 검색조건 */

initQnaList(); /* 컴포넌트 초기화 */
watch(route, initQnaList); /* 컴포넌트 URL 변경을 감지합니다.(페이징 처리) */

/**
 * 문의게시글 목록 컴포넌트를 초기화 합니다.
 *
 * @returns {Promise<void>}
 */
async function initQnaList() {
  condition.value = createCondition(route.query);

  /* 나의 문의 내역 */
  if (route.query.myQna === 'true') {
    condition.value.userId = getCurrentUserId();
    condition.value.myQna = true;
  } else {
    condition.value.myQna = false;
  }

  try {
    const [qnaListResult] = await Promise.all([
      getQnaList(condition.value),
    ]);

    qnaList.value = qnaListResult.qnaList;
    totalCnt.value = qnaList.value.length === 0 ? 0 : qnaListResult.totalCnt;

  } catch ({message}) {
    console.error(message);
  }
}

/**
 * 게시글 검색조건 컴포넌트 핸들러
 * @param searchForm 게시글 검색정보
 */
function onConditionSearch(searchForm) {
  searchForm.myQna = condition.value.myQna;
  router.push({
    path: '/qna',
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
    path: `/qna`,
    query: condition.value
  });
}

/**
 * 문의게시글 상세페이지로 라우팅합니다.
 * @param board 문의글 정보
 */
function onDetailRouter(board) {
  if (board.qnaSecret && board.userId !== store.getters['loginStore/getCurrentUser']) {
    alert('해당 문의글은 작성자만 이용가능합니다.');
    return false;
  }

  router.push({
    path: `/qna/${board.boardId}`,
    query: condition.value
  });
}

</script>

<script>
export default {
  name: "QnaList"
}
</script>

<style scoped>

.qna-list-container {
  margin-top: 10px;
}

.my-qna-register-container {
  margin: 20px 0;
  height: 20px;
  display: flex;
  justify-content: space-between;

}

.my-qna-container {
  display: flex;
  align-items: center;
  font-size: var(--small-font-size);
}

.my-qna-span {
  padding: 0 5px;
}

.my-qna-input {
  cursor: pointer;
  width: 15px;
}

.my-qna-register-btn {
  height: 20px;
}

.paging-container {
  margin-top: 30px;
}

</style>