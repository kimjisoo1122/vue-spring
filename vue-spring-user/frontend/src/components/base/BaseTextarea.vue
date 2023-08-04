<template>
  <textarea
      :value="modelValue"
      @change="onChange">
  </textarea>
</template>

<script>
export default {
  name: "BaseTextarea",
  props: {
    modelValue: {
      type: String,
      default: '',
      required: false,
      description: '양방향 값'
    },
  },
  methods: {
    onChange(event) {
      this.$emit('update:modelValue', event.target.value);
    },
  }
}
</script>

<style scoped>

textarea {
  outline-color: var(--main-color-blue);
  resize: none;
  border: var(--main-border);
  border-radius: 3px;
  padding: 10px;
  font-size: var(--middle-font-size);
  box-sizing: border-box;
}

</style>