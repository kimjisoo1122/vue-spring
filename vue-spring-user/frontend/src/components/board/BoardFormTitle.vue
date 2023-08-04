<template>
  <div class="board-form-title-container">
    <span class="board-form-title-name">{{ name }}</span>
    <span v-if="required" class="board-form-title-required-star">*</span>
  </div>
</template>

<script>
export default {
  name: "BoardFormTitle",
  props: {
    name: {
      type: String,
      default: '',
      required: true,
      description: '타이틀 이름'
    },
    required: {
      type: Boolean,
      default: true,
      required: false,
      description: '필수 여부'
    }
  }
}
</script>

<style scoped>

.board-form-title-container {
  width: 170px;
  background-color: var(--sub-color-blue);
  font-size: var(--middle-font-size);
  border-right: 1px solid var(--border-color-gray);
  box-sizing: border-box;
  padding: 10px 0 10px 20px;
  display: flex;
  align-items: center;
}


.board-form-title-required-star {
  color: red;
}


</style>