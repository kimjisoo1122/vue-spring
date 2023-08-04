<template>

  <board-title title="공지사항"></board-title>

  <board-detail-header :board="notice" class="board-detail-header"></board-detail-header>

  <board-detail-content :board="notice" class="board-detail-content"></board-detail-content>

  <div class="notice-detail-btn-container">
    <base-button
        @click="router.push({path: '/notices', query: condition})"
        class="notice-detail-btn-list" name="목록">
    </base-button>
  </div>

</template>

<script setup>
import {createCondition} from "@/util/queryParamUtil";
import {getNoticeDetail} from "@/api/board/noticeService";
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import BaseButton from "@/components/base/BaseButton.vue";
import BoardDetailHeader from "@/components/board/BoardDetailHeader.vue";
import BoardTitle from "@/components/board/BoardTitle.vue";
import {formatDate} from "@/util/formatUtil";
import BoardDetailContent from "@/components/board/BoardDetailContent.vue";

const route = useRoute();
const router = useRouter();
const condition = ref({});
const notice = ref({});

initNoticeDetail();

/**
 * 공지시항 상세컴포넌트를 초기화합니다.
 * @returns {Promise<void>}
 */
async function initNoticeDetail() {
  condition.value = createCondition(route.query);

  try {
    notice.value = await getNoticeDetail(route.params.boardId);
    notice.value.createDate = formatDate(notice.value.createDate);
  } catch ({message}) {
    console.error(message);

    if (message) {
      router.push('/404');
    }
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