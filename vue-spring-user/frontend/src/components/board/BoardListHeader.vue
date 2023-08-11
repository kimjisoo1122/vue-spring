<template>

  <div class="board-list-header-container">
    <div class="board-list-header-id">번호</div>
    <!-- 문의게시글의 경우 카테고리가 존재하지 않습니다. -->
    <div v-if="!isQna" class="board-list-header-category">분류</div>
    <div class="board-list-header-title">제목</div>
    <div class="board-list-header-view">조회</div>
    <div class="board-list-header-regdate">등록일시</div>
    <div class="board-list-header-name">등록자</div>
  </div>

</template>

<script>
/**
 * 게시글목록의 헤더 컴포넌트
 */
export default {
  name: "BoardListHeader",
  props: {
    isQna: {
      type: Boolean,
      default: false,
      required: false,
      description: '문의글 타입여부'
    }
  }
}
</script>

<style scoped>

.board-list-header-container {
  border-top: 1px solid var(--border-color-gray);
  border-bottom: 2px solid var(--border-color-gray);
  height: 30px;
  display: flex;
  align-items: center;
}

.board-list-header-container > div {
  font-size: var(--middle-font-size);
  flex-grow: 1;
  font-weight: bold;
  display: flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

.board-list-header-id, .board-list-header-category, .board-list-header-view{
  flex-basis: 2%;
}

.board-list-header-title {
  flex-basis: 50%;
}

.board-list-header-regdate {
  flex-basis: 15%;
}

.board-list-header-name {
  flex-basis: 10%;
}
</style>