<template>

  <board-title title="문의 게시판"></board-title>

  <board-detail-header :is-qna="true" :board="qna"></board-detail-header>

  <board-detail-content :board="qna" class="qna-detail-content"></board-detail-content>

  <div v-if="qna.answerStatus" class="qna-detail-answer-container">
      <div class="qna-answer-header-container">
        <span class="qna-answer-name">{{ qna.answerWriter }}</span>
        <span class="qna-answer-regdate">{{ qna.createDate }}</span>
      </div>
      <div class="qna-answer-content">{{ qna.qnaAnswer }}</div>
  </div>

  <div v-else class="qna-answer-empty-container">
    <div class="qna-answer-empty">아직 등록된 답변이 없습니다.</div>
  </div>

  <board-detail-btn-container
      @delete-board="onDelete"
      :board="qna"
      :condition="condition"
      path="/qna"
      class="qna-detail-btn-container">
  </board-detail-btn-container>

</template>

<script setup>

import BoardDetailContent from "@/components/board/BoardDetailContent.vue";
import BoardDetailHeader from "@/components/board/BoardDetailHeader.vue";
import BoardTitle from "@/components/board/BoardTitle.vue";
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import {createCondition} from "@/util/queryParamUtil";
import {formatDate} from "@/util/formatUtil";
import {deleteFree} from "@/api/board/freeService";
import BoardDetailBtnContainer from "@/components/board/BoardDetailBtnContainer.vue";
import {deleteQna, getQnaDetail} from "@/api/board/qnaService";

const route = useRoute();
const router = useRouter();
const condition = ref({});
const qna = ref({});

initQnaDetail();

/**
 * 문의게시글 상세컴포넌트를 초기화합니다.
 * @returns {Promise<void>}
 */
async function initQnaDetail() {
  condition.value = createCondition(route.query);

  try {
    const boardId = route.params.boardId;
    const [qnaResult] = await Promise.all([
      getQnaDetail(boardId),
    ]);
    qna.value = qnaResult;
    console.log(qna.value)
    qna.value.createDate = formatDate(qna.value.createDate);
  } catch ({message}) {
    console.error(message);

    if (message) {
      // router.push('/404');
    }
  }
}

/**
 * 문의게시글을 삭제합니다.
 */
async function onDelete() {
  try {
    await deleteQna(route.params.boardId);
    router.push({path: '/qna', query: condition});
  } catch ({message}) {
    alert(message);
  }
}

</script>

<script>

export default {
  name: "QnaDetail"
}
</script>

<style scoped>

.qna-detail-answer-container, .qna-answer-empty-container {
  margin-top: 20px;
  background-color: antiquewhite;
  border-radius: 3px;
  padding: 0 10px 10px 10px;
  font-size: var(--middle-font-size);
}

.qna-detail-answer-container {
  min-height: 200px;
}


.qna-answer-header-container {
  height: 30px;
  border-bottom: 1px solid var(--border-color-gray);
  display: flex;
  align-items: center;
  gap: 20px;
}

.qna-answer-name {
  font-weight: bold;
}

.qna-answer-content {
  padding-top: 20px;
}

.qna-answer-no-content {
  display: flex;
  justify-content: center;
  align-items: center;
}

.qna-detail-content {
  margin-top: 50px;
  min-height: 100px;
}

.qna-detail-btn-container {
  margin: 30px 0;
}

.qna-answer-empty-container{
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>