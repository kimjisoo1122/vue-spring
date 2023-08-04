<template>

  <!-- 게시글 제목 -->
  <board-title title="공지사항" ></board-title>

  <!-- 게시글 검색조건 -->
  <board-search-condition
      :category-list="categoryList"
      :condition="condition"
      @condition-search="onConditionSearch">
  </board-search-condition>

  <div class="notice-list-container">

    <!-- 게시글 헤더 -->
    <board-list-header></board-list-header>

    <!-- 알림글 -->
    <board-list
        v-if="alarmList"
        :board-list="alarmList"
        :condition="condition"
        @detail-router="onDetailRouter"
        is-alarm="true">
    </board-list>

    <!-- 공지사항 -->
    <board-list
        :board-list="noticeList"
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
import BoardSearchCondition from "@/components/board/BoardSearchCondition.vue";
import BoardTitle from "@/components/board/BoardTitle.vue";
import BoardListHeader from "@/components/board/BoardListHeader.vue";
import BoardList from "@/components/board/BoardList.vue";
import {ALARM_LIST_LIMIT, NOTICE_CATEGORY_ID} from "@/constants";
import {getCategoryList} from "@/api/categoryService";
import {ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import {useStore} from "vuex";
import {getAlarmList, getNoticeList} from "@/api/board/noticeService";
import {createCondition} from "@/util/queryParamUtil";
import Pagination from "@/components/Pagination.vue";

const router = useRouter();
const route = useRoute();
const store = useStore();

const categoryList = ref([]); /* 카테고리 목록 */
const alarmList = ref([]); /* 알림글 목록 */
const noticeList = ref([]); /* 공지사항 목록 */
const totalCnt = ref(0); /* 게시글 총 개수 */
const condition = ref({}); /* 게시글 검색조건 */

initNoticeList(); /* 컴포넌트 초기화 */
watch(route, initNoticeList); /* 컴포넌트 URL 변경을 감지합니다.(페이징 처리) */

/**
 * 공지사항 목록 컴포넌트를 초기화 합니다.
 *
 * @returns {Promise<void>}
 */
async function initNoticeList() {
  condition.value = createCondition(route.query);

  try {
    const [categoryListResult, noticeListResult, alarmListResult] = await Promise.all([
      getCategoryList(NOTICE_CATEGORY_ID),
      getNoticeList(condition.value),
      getAlarmList(ALARM_LIST_LIMIT)
    ]);

    categoryList.value = categoryListResult;
    noticeList.value = noticeListResult.noticeList;
    totalCnt.value = noticeList.value.length === 0 ? 0 : noticeListResult.totalCnt;
    alarmList.value = alarmListResult;

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
    path: '/notices',
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
    path: `/notices/`,
    query: condition.value
  });
}

/**
 * 공지사항 상세페이지로 라우팅합니다.
 * @param boardId 공지사항 번호
 */
function onDetailRouter(boardId) {
  router.push({
    path: `/notices/${boardId}`,
    query: condition.value
  });
}

</script>

<script>
export default {
  name: "NoticeList"
}
</script>

<style scoped>

.notice-list-container {
  margin-top: 30px;
}

.paging-container {
  margin-top: 30px;
}

</style>