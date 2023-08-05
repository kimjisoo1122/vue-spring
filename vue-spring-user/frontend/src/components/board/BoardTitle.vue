<template>

  <header>
    <h1>{{ title }}</h1>
  </header>

</template>

<script>
/**
 * 게시글의 제목 컴포넌트
 */
export default {
  name: "BoardTitle",
  props: {
    title: {
      type: String,
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