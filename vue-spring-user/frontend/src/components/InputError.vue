<template>
  <span>{{ errorMsg }}</span>
</template>

<script>
export default {
  name: "InputError",
  props: {
    errorMsg: {
      type: String,
      default: '',
      required: true,
      description: '인풋 에러 메시지'
    }
  }
}
</script>

<style scoped>

span {
  display: inline-block;
  font-size: var(--small-font-size);
  color: red;
}

</style>