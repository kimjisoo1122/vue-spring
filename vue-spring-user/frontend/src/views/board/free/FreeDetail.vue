<template>

  <GNB></GNB>

  <!-- 게시글 제목 -->
  <board-title title="자유게시판"></board-title>

  <!-- 게시글 상세 헤더 -->
  <board-detail-header :board="free"></board-detail-header>

  <!-- 게시글 상세 내용 -->
  <board-detail-content :board="free" class="free-detail-content"></board-detail-content>

  <!-- 첨부 파일 목록 -->
  <file-list :file-list="fileList" class="free-detail-file-list"></file-list>

  <!-- 댓글 -->
  <reply
      @register-reply="onRegisterReply"
      @delete-reply="onDeleteReply"
      :reply-list="replyList">
  </reply>

  <!-- 게시글 상세 버튼 목록 -->
  <board-detail-btn-container
      @delete-board="onDelete"
      :board="free"
      :condition="condition"
      path="/frees"
      class="free-detail-btn-container">
  </board-detail-btn-container>

</template>

<script setup>
/**
 * 자유게시글 상세페이지 컴포넌트
 */

import BoardDetailContent from "@/components/board/BoardDetailContent.vue";
import BoardDetailHeader from "@/components/board/BoardDetailHeader.vue";
import BoardTitle from "@/components/board/BoardTitle.vue";
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import {createCondition} from "@/util/queryParamUtil";
import {formatDate} from "@/util/formatUtil";
import {deleteFree, getFreeDetail} from "@/api/board/freeService";
import {getBoardReplyList} from "@/api/replyService";
import {getBoardFileList} from "@/api/fileService";
import FileList from "@/components/FileList.vue";
import Reply from "@/components/Reply.vue";
import BoardDetailBtnContainer from "@/components/board/BoardDetailBtnContainer.vue";
import GNB from "@/components/GNB.vue";

const route = useRoute();
const router = useRouter();

const condition = ref({}); /* 검색조건 */
const free = ref({}); /* 자유게시글 */
const fileList = ref([]); /* 파일 목록 */
const replyList = ref([]); /* 댓글 목록*/

initFreeDetail();

/**
 * 자유게시글 상세컴포넌트를 초기화합니다.
 */
async function initFreeDetail() {
  condition.value = createCondition(route.query);

  try {
    const boardId = route.params.boardId;
    const [freeResult, fileResult, replyResult] = await Promise.all([
      getFreeDetail(boardId),
      getBoardFileList(boardId),
      getBoardReplyList(boardId)
    ]);
    free.value = freeResult;
    free.value.createDate = formatDate(free.value.createDate);
    fileList.value = fileResult;
    replyList.value = replyResult;
    replyList.value.forEach(e => e.createDate = formatDate(e.createDate));
  } catch ({message}) {
    console.error(message);
  }
}

/**
 * 댓글 컴포넌트에서 등록한 현재 댓글목록에 추가합니다.
 * @param reply 등록된 댓글
 */
function onRegisterReply(reply) {
  reply.createDate = formatDate(reply.createDate);
  replyList.value.push(reply)
}

/**
 * 댓글 컴포넌트에서 삭제한 댓글을 목록에서 삭제합니다.
 * @param replyId 삭제된 댓글번호
 */
function onDeleteReply(replyId) {
  replyList.value = replyList.value.filter(e => e.replyId !== replyId);
}

/**
 * 자유게시글을 삭제합니다.
 */
async function onDelete() {
  try {
    await deleteFree(route.params.boardId);
    router.push({path: '/frees', query: condition.value});
  } catch ({message}) {
    alert(message);
  }
}
</script>

<script>

export default {
  name: "FreeDetail"
}
</script>

<style scoped>

.free-detail-content {
  margin-top: 50px;
}

.free-detail-file-list {
  padding: 10px;
  margin: 10px 0;
}

.free-detail-btn-container {
  margin: 30px 0;
}

</style>