<template>
  <button>{{ name }}</button>
</template>

<script>
export default {
  name: "Button",

  props: {
    name: {
      type: String,
      default: '',
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
    font-size: 13px;
    cursor: pointer;
  }
</style>