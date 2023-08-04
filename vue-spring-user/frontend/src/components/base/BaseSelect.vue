\<template>
  <select @change="onChange" :value="modelValue">
    <option
        v-for="option in options"
        :key="option.value"
        :value="option.value">
      {{ option.name }}
    </option>
  </select>
</template>

<script>
export default {
  name: "BaseSelect",
  props: {
    modelValue: {
      type: String,
      default: '',
      description: '입력 값'
    },
    options: {
      type: Array,
      default: undefined,
      description: '옵션 목록'
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