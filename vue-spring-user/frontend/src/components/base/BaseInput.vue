<template>

  <input
      @change="onChange"
      :type="type"
      :value="modelValue"
      :checked="modelValue"
      :placeholder="placeholder">

</template>

<script>
/**
 * 베이스 파일 인풋
 */
export default {
  name: "BaseInput",

  props: {
    type: {
      type: String,
      default: 'text',
      required: false,
      description: '인풋 타입'
    },
    modelValue: {
      type: String,
      default: '',
      required: false,
      description: '양방향 값'
    },
    placeholder: {
      type: String,
      default: '',
      required: false,
      description: '플레이스 홀더'
    }
  },

  methods: {
    /* 값이 변경될때 실행되는 핸들러 */
    onChange(event) {
      if (this.type === 'checkbox') {
        this.$emit('update:modelValue', event.target.checked);
      } else {
        this.$emit('update:modelValue', event.target.value);
      }
    },
  }
}
</script>

<style scoped>

input {
  box-sizing: border-box;
  width: 300px;
  height: 40px;
  padding: 10px;
  font-size: var(--middle-font-size);
  border-radius: 2px;
  border: 1px solid var(--border-color-gray);
  outline-color: var(--main-color-blue);
}

input::placeholder {
  font-size: var(--middle-font-size);
}

</style>