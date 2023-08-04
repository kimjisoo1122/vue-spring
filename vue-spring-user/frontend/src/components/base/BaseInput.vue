<template>
  <input
    :type="type"
    :value="modelValue"
    :placeholder="placeholder"
    @change="onChange">

</template>

<script>
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
    onChange(event) {
      this.$emit('update:modelValue', event.target.value);
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