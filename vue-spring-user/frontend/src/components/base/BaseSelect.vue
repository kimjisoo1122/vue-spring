<template>

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
/**
 * 베이스 셀렉트 컴포넌트
 */
export default {
  name: "BaseSelect",
  props: {
    modelValue: {
      type: String,
      required: true,
      description: '입력 값'
    },
    options: {
      type: Array,
      required: true,
      description: '옵션 목록'
    }
  },
  methods: {
    /* 선택한 값이 부모 컴포넌트로 전송됩니다. */
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