<template>

  <div class="board-detail-header-container">

    <!-- 문의게시글인 경우에는 카테고리가 표시되지 않습니다. -->
    <div v-if="!isQna" class="board-detail-category">{{ board.categoryName }}</div>

    <!-- 문의게시글인 경우 답변상태가 제목에 표시됩니다. -->
    <div class="board-detail-title-container">
      <div class="board-detail-title">{{ board.boardTitle }}</div>
      <span v-if="isQna" class="board-detail-title-answer">{{ answerStatus }}</span>
    </div>

    <!-- 생성일시 -->
    <div class="board-detail-regdate">{{ board.createDate }}</div>

    <!-- 작성자 이름 -->
    <div class="board-detail-username">{{ board.userName }}</div>

  </div>

  <!-- 조회수 -->
  <div class="board-detail-viewcnt">조회수 : {{ board.viewCnt }}</div>

</template>

<script>
/**
 * 게시글 상세페이지의 헤더 컴포넌트
 */
export default {
  name: "BoardDetailHeader",
  props: {
    board: {
      type: Object,
      required: true,
      description: '게시글 정보'
    },
    isQna: {
      type: Boolean,
      default: false,
      required: false,
      description: '문의게시글 여부'
    }
  },
  computed: {
    /* 문의게시글의 답변상태에 따라 반환 값을 제목에 첨삭합니다. */
    answerStatus() {
      return this.isQna && this.board.answerStatus ? '(답변완료)' : '(미답변)'
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