<template>

  <div
      v-for="board in boardList"
      :key="board.boardId"
      :class="{'board-list-alarm-container': isAlarm }"
      class="board-list-container">
    <div class="board-list-id">{{ board.boardId }}</div>
    <div class="board-list-category">{{ board.categoryName }}</div>
    <div class="board-list-title" id="board-list-title">
      <div @click="$emit('detail-router', board.boardId);" class="board-list-title-link">{{ board.boardTitle }}</div>
      <span v-if="board.replyCnt !== 0" class="board-list-title-reply">({{ board.replyCnt }})</span>
      <span v-if="board.newStatus" class="board-list-title-new">new</span>
      <font-awesome-icon icon="paperclip" v-if="board.fileStatus" class="board-list-title-file" />
    </div>
    <div class="board-list-view">{{ board.viewCnt }}</div>
    <div class="board-list-regdate">{{ formatDate(board.createDate) }}</div>
    <div class="board-list-name"> {{ board.userName }}</div>
  </div>

</template>

<script>
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
      description: '문의글 타입 -> 게시글 카테고리 없음'
    }
  },
  methods: {
    formatDate,
  }
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

.board-list-regdate {
  flex-basis: 15%;
}

.board-list-name {
  flex-basis: 10%;
}

</style>