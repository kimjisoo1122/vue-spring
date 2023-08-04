<template>
  <select @change="onChange" :value="modelValue">
    <option value="">전체 분류</option>
    <option
        v-for="category in categoryList"
        :key="category.categoryId"
        :value="category.categoryId">{{ category.categoryName }}
    </option>
  </select>
</template>

<script>
export default {
  name: "CategorySelect",
  props: {
    modelValue: {
      type: String,
      default: '',
      description: '입력 값'
    },
    categoryList: {
      type: Array,
      default: undefined,
      required: true,
      description: '카테고리 목록'
    }
  },
  methods: {
    onChange(event) {
      this.$emit('update:modelValue', event.target.value);
    },
  }
}
</script>

<style scoped>

select {
  height: 30px;
  padding: 5px 10px;
  box-sizing: border-box;
  outline-color: var(--main-color-blue);
  font-size: var(--small-font-size);
  border: var(--main-border);
  border-radius: 3px;
}


</style>