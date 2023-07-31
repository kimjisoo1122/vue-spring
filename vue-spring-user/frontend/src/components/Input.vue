<template>
  <input
      :type="type"
      :value="modelValue"
      :placeholder="placeholder"
      @change="onChange">

</template>

<script>
export default {
  name: "Input",

  props: {
    type: {
      type: String,
      default: 'text'
    },
    modelValue: {
      type: String,
      default: ''
    },
    placeholder: {
      type: String,
      default: ''
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