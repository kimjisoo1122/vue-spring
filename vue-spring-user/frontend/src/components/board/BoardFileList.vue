<template>

  <div class="board-file-list-container">
    <file-list
        @delete-file="$emit('delete-file', $event)"
        :file-list="fileList"
        :is-update="isUpdate"
    ></file-list>
    <base-file-input
        v-for="num in initInputSize"
        :key="num"
        :file-id="num">
    </base-file-input>

  </div>

</template>

<script>
import BaseFileInput from "@/components/base/BaseFileInput.vue";
import FileList from "@/components/FileList.vue";

export default {
  name: "BoardFileList",
  components: {FileList, BaseFileInput},
  props: {
    fileList: {
      type: Array,
      default: undefined,
      required: false,
      description: '게시글에 등록된 첨부파일 목록'
    },
    inputSize: {
      type: Number,
      default: 5,
      required: false,
      description: '새로 추가할 첨부파일 개수'
    },
    isUpdate: {
      type: Boolean,
      default: false,
      required: false,
      description: '업데이트의 경우 삭제버튼 등장'
    }
  },

  computed: {
    initInputSize() {
      const fileListLength = this.fileList === undefined ? 0 : this.fileList.length;
      return this.inputSize - fileListLength;
    },
  },
}
</script>

<style scoped>
</style>