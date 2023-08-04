<template>
  <header>
    <h1>{{ title }}</h1>
  </header>
</template>

<script>
export default {
  name: "BoardTitle",
  props: {
    title: {
      type: String,
      default: '',
      required: true,
      description: '게시글 타이틀',
    }
  }
}
</script>

<style scoped>

header {
  margin: 20px 0;
}

h1 {
  font-size: var(--title-font-size);
  margin: 0;
}

</style>