<template>
  <button type="button">{{ name }}</button>
</template>

<script>
/**
 * 베이스 버튼 컴포넌트
 */
export default {
  name: "BaseButton",

  props: {
    name: {
      type: String,
      required: true,
      description: '버튼 이름'
    }
  }
}
</script>

<style scoped>

button {
  border: none;
  border-radius: 2px;
  background-color: var(--main-color-blue);
  color: white;
  padding: 3px 10px;
  font-size: var(--small-font-size);
  cursor: pointer;
}

</style>