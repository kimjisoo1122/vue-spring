<template>

  <div class="form-btn-container">
    <!-- 등록 폼 -->
    <base-button
        v-if="this.formType === 'register'"
        @click=onRegister
        name="등록"
        class="register-btn-save">
    </base-button>

    <base-button
        v-if="this.formType === 'update'"
        @click=onUpdate
        name="수정"
        class="update-btn-save">
    </base-button>

    <base-button
        @click="onCancel"
        name="취소"
        class="form-btn-cancel">
    </base-button>

  </div>

</template>

<script>
import BaseButton from "@/components/base/BaseButton.vue";

export default {
  name: "BoardFormBtnContainer",
  components: {BaseButton},
  props: {
    formType: {
      type: String,
      required: true,
      description: 'register 또는 update로 폼을 구별합니다.'
    }
  },
  methods: {
    onRegister() {
      if (confirm('등록 하시겠습니까?')) {
        this.$emit('register');
      }
    },
    onUpdate() {
      if (confirm('수정 하시겠습니까?')) {
        this.$emit('update');
      }
    },
    onCancel() {
      if (confirm('취소 하시겠습니까?')) {
        this.$emit('cancel');
      }
    }
  }
}
</script>

<style scoped>

.form-btn-container {
  margin: 30px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
}

.register-btn-save, .update-btn-save, .form-btn-cancel {
  width: 70px;
}

.form-btn-cancel {
  background-color: var(--sub-color-violet);
}

</style>