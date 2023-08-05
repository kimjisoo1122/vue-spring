<template>

  <!-- 공지사항중 알림글인 경우 클래스를 추가합니다. (색상부여) -->
  <div
      v-for="board in boardList"
      :key="board.boardId"
      :class="{'board-list-alarm-container': isAlarm }"
      class="board-list-container">

    <!-- 게시글 번호 -->
    <div class="board-list-id">{{ board.boardId }}</div>

    <!-- 게시글 카테고리 이름, 문의게시글은 카테고리가 표시되지 않습니다. -->
    <div v-if="!isQna" class="board-list-category">{{ board.categoryName }}</div>

    <!-- 게시글 제목 컨테이너 -->
    <div class="board-list-title" id="board-list-title">

      <div @click="$emit('detail-router', board);" class="board-list-title-link">{{ board.boardTitle }}</div>

      <!-- 답변상태가 제목에 첨삭됩니다. (문의게시글) -->
      <span v-if="isQna" class="board-list-title-answer">{{ board.answerStatus ? '(답변완료)' : '(미답변)' }}</span>

      <!-- 댓글이 존재하면 개수가 표시됩니다. (자유게시글) -->
      <span v-if="board.replyCnt !== 0" class="board-list-title-reply">({{ board.replyCnt }})</span>

      <!-- 해당 글이 최신상태인지 표시됩니다. -->
      <span v-if="board.newStatus" class="board-list-title-new">new</span>

      <!-- 첨부파일여부를 표시합니다. (자유게시글) -->
      <font-awesome-icon icon="paperclip" v-if="board.fileStatus" class="board-list-title-file" />

      <!-- 비밀글 상태를 표시합니다. (문의게시글) -->
      <font-awesome-icon icon="lock" v-if="board.qnaSecret" class="board-list-title-secret"/>
    </div>

    <!-- 조회수 -->
    <div class="board-list-view">{{ board.viewCnt }}</div>

    <!-- 생성일시 -->
    <div class="board-list-regdate">{{ formatDate(board.createDate) }}</div>

    <!-- 작성자 이름 -->
    <div class="board-list-name"> {{ board.userName }}</div>
  </div>

</template>

<script>
/**
 * 게시글목록을 표시하는 컴포넌트
 */
import {formatDate} from "@/util/formatUtil";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

export default {
  name: "BoardList",
  components: {FontAwesomeIcon},
  props: {
    boardList: {
      type: Array,
      default: undefined,
      required: true,
      description: '게시글 리스트'
    },
    condition: {
      type: Object,
      default: undefined,
      required: true,
      description: '검색 조건'
    },
    isAlarm: {
      type: Boolean,
      default: false,
      required: false,
      description: '공지사항 알림글 여부'
    },
    isQna: {
      type: Boolean,
      default: false,
      required: false,
      description: '문의글 타입'
    }
  },
  methods: {
    formatDate,
  },
}
</script>

<style scoped>

.board-list-container {
  border-bottom: 1px solid var(--border-color-gray);
  height: 30px;
  display: flex;
  align-items: center;
}

.board-list-alarm-container {
  background-color: var(--sub-color-blue);
}

.board-list-container > div {
  font-size: var(--middle-font-size);
  flex-grow: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

.board-list-id, .board-list-category, .board-list-view{
  flex-basis: 2%;
}

#board-list-title {
  flex-basis: 50%;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.board-list-title-link {
  cursor: pointer;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 450px;
  margin-right: 5px;
}

.board-list-title-answer {
  margin: 0 10px;
  font-size: var(--small-font-size);
}

.board-list-title-reply {
  margin-right: 5px;
}

.board-list-title-new {
  font-size: 11px;
  color: orange;
}

.board-list-title-file {
  margin-left: 5px;
  display: block;
  padding-top: 3px;
}

.board-list-title-secret {
  color: gray;
  margin-left: 10px;
}

.board-list-regdate {
  flex-basis: 15%;
}

.board-list-name {
  flex-basis: 10%;
}

</style>