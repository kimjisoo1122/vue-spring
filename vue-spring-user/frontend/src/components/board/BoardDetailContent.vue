<template>

  <div class="board-detail-content">{{ board.boardContent }}</div>

</template>

<script>
export default {
  name: "BoardDetailContent",
  props: {
    board: {
      type: Object,
      default: undefined,
      required: true,
      description: '게시글'
    }
  }
}
</script>

<style scoped>

.board-detail-content {
  padding: 10px;
  border: 1px solid var(--border-color-gray);
  border-radius: 3px;
  min-height: 200px;
  font-size: var(--middle-font-size);
}

</style>