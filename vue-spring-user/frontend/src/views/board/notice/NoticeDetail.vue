<template>

  <GNB></GNB>

  <!-- 타이틀 -->
  <board-title title="공지사항"></board-title>

  <!-- 게시글 상세 헤더 -->
  <board-detail-header :board="notice" class="board-detail-header"></board-detail-header>

  <!-- 게시글 상세 내용 -->
  <board-detail-content :board="notice" class="board-detail-content"></board-detail-content>

  <!-- 게시글 상세 버튼 -->
  <board-detail-btn-container
      :board="notice"
      path="/notices"
      :condition="condition">
  </board-detail-btn-container>

</template>

<script setup>
/**
 * 공지사항 상세 컴포넌트
 */
import {createCondition} from "@/util/queryParamUtil";
import {getNoticeDetail} from "@/api/board/noticeService";
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import BoardDetailHeader from "@/components/board/BoardDetailHeader.vue";
import BoardTitle from "@/components/board/BoardTitle.vue";
import {formatDate} from "@/util/formatUtil";
import BoardDetailContent from "@/components/board/BoardDetailContent.vue";
import GNB from "@/components/GNB.vue";
import BoardDetailBtnContainer from "@/components/board/BoardDetailBtnContainer.vue";

const route = useRoute();
const router = useRouter();
const condition = ref({});
const notice = ref({});

initNoticeDetail();

/**
 * 공지시항 상세 컴포넌트를 초기화합니다.
 */
async function initNoticeDetail() {
  condition.value = createCondition(route.query);

  try {
    notice.value = await getNoticeDetail(route.params.boardId);
    notice.value.createDate = formatDate(notice.value.createDate);
  } catch ({message}) {
    console.error(message);
  }
}

</script>

<script>
export default {
  name: "NoticeDetail"
}
</script>

<style scoped>

.board-detail-content {
  margin-top: 50px;
}

.notice-detail-btn-container {
  margin: 30px 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.notice-detail-btn-list {
  width: 100px;
  background-color: var(--sub-color-violet);
}
</style>