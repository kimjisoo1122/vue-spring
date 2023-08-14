<template>

  <div class="board-detail-content"><pre>{{ board.boardContent }}</pre></div>

</template>

<script>
/**
 * 게시글 상세페이지의 내용을 나타내는 컴포넌트
 */
export default {
  name: "BoardDetailContent",
  props: {
    board: {
      type: Object,
      required: true,
      description: '게시글 정보'
    }
  }
}
</script>

<style scoped>

.board-detail-content {
  padding: 10px;
  border: 1px solid var(--border-color-gray);
  border-radius: 3px;
  min-height: 150px;
  font-size: var(--middle-font-size);
}

pre {
  white-space: pre-wrap;
  word-wrap: break-word;
}

</style>