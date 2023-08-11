<template>

  <div class="board-file-list-container">

    <div class="allowed-file-msg">{{ allowedFileMsg }}</div>
    <!-- 첨부파일 목록 -->
    <file-list
        @delete-file="$emit('delete-file', $event)"
        :file-list="fileList"
        :is-update="isUpdate">
    </file-list>

    <!-- 새로 첨부할 수 있는 베이스 파일 타입 인풋 -->
    <base-file-input
        v-for="num in addInputSize"
        v-model="uploadFiles[num]"
        :key="num"
        :file-id="num"
        :allowed-extensions="allowedExtensions"
        :allowed-file-size="allowedFileSize">
    </base-file-input>

  </div>

</template>

<script>
/**
 * 게시글의 등록된 파일목록 + 새롭게 등록할 수 있는 파일인풋 컴포넌트
 */
import BaseFileInput from "@/components/base/BaseFileInput.vue";
import FileList from "@/components/FileList.vue";

export default {
  name: "BoardFileList",
  components: {FileList, BaseFileInput},
  data() {
    return {
      uploadFiles: {}, /* 인풋 업로드 파일 목록 */
    }
  },
  props: {
    fileList: {
      type: Array,
      default: () => ([]),
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
    },
    allowedExtensions: {
      type: Array,
      default: () => ['jpg', 'gif', 'png', 'zip'],
      required: false,
      description: '허용된 파일 확장자'
    },
    allowedFileSize: {
      type: Number,
      default: 1000 * 1000 * 2,
      required: false,
      description: '허용된 파일 최대 크기'
    },
  },
  watch: {
    /* 상위 컴포넌트에 업로드 파일 목록을 전송합니다. */
    uploadFiles: {
      handler: function (uploadFiles) {
        this.$emit('upload-file', uploadFiles);
      },
      deep: true
    }

  },

  computed: {
    /* 새로 추가할 인풋 개수 */
    addInputSize() {
      const fileListLength = this.fileList === undefined ? 0 : this.fileList.length;
      return this.inputSize - fileListLength;
    },
    /* 허용 가능한 파일 공지 메시지*/
    allowedFileMsg() {
      return `
        ${this.allowedExtensions.join(', ')} 파일만
        파일사이즈 ${this.allowedFileSize / (1000 * 1000)}MB 까지 업로드 가능합니다.`;
    },
  },
}
</script>

<style scoped>

.allowed-file-msg {
  font-size: var(--small-font-size);
  font-weight: bold;
  margin-left: 3px;
}
</style>