<template>

  <div class="board-detail-header-container">
    <div v-if="!isQna" class="board-detail-category">{{ board.categoryName }}</div>
    <div class="board-detail-title-container">
      <div class="board-detail-title">{{ board.boardTitle }}</div>
      <span v-if="isQna" class="board-detail-title-answer">{{ answerStatus }}</span>
    </div>
    <div class="board-detail-regdate">{{ board.createDate }}</div>
    <div class="board-detail-username">{{ board.userName }}</div>
  </div>

  <div class="board-detail-viewcnt">조회수 : {{ board.viewCnt }}</div>

</template>

<script>
export default {
  name: "BoardDetailHeader",
  props: {
    board: {
      type: Object,
      default: undefined,
      required: true,
      description: '게시글'
    },
    isQna: {
      type: Boolean,
      default: false,
      required: false,
      description: '문의글 타입 -> 게시글 카테고리 없음'
    }
  },
  computed: {
    answerStatus() {
      return this.board.answerStatus ? '(답변완료)' : '(미답변)'
    },
  }

}
</script>

<style scoped>

.board-detail-header-container {
  height: 50px;
  border-bottom: 2px solid var(--border-color-gray);
  display: flex;
  align-items: center;
  font-size: var(--middle-font-size);
}

.board-detail-category {
  width: 50px;
  font-weight: bold;
  text-align: left;
  padding-left: 10px;
}

.board-detail-title-container {
  display: flex;
  flex-grow: 1;
  text-align: left;
}

.board-detail-title-answer {
  margin-left: 10px;
}

.board-detail-regdate {
  width: 150px;
  font-size: var(--middle-font-size);
  text-align: right;
}

.board-detail-username {
  width: 70px;
  font-size: var(--middle-font-size);
  text-align: right;
}




.board-detail-viewcnt {
  margin-top: 10px;
  text-align: right;
  font-size: var(--middle-font-size)
}

</style>