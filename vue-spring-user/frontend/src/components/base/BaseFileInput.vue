<template>

  <div class="file-input-container">
    <input type="text"
           class="file-input-disabled"
           v-model="fileName"
           disabled>
    <label :for="fileId" class="file-upload-label">파일 찾기</label>
    <input type="file"
           class="file-input"
           :id="fileId"
           @change="onUpload">
    <input-error :error-msg="fileError" class="file-error"/>
  </div>

</template>

<script>
import InputError from "@/components/InputError.vue";

export default {
  name: "BaseFileInput",
  components: {InputError},

  data() {
    return {
      fileName: '', // 파일이름
      fileError: '', // 파일에러
    }
  },

  props: {
    fileMaxSize: {
      type: Number,
      default: 1000 * 1000 * 2,
      required: false,
      description: '파일최대크기'
    },
    fileId: {
      type: Number,
      default: 1,
      required: true,
      description: '파일번호'
    },
    allowedExtensions: {
      type: Array,
      default: () => ['jpg', 'gif', 'png', 'zip'],
      required: false,
      description: '허용가능한 파일확장자'
    }
  },

  methods: {
    /**
     * 파일객체를 상위 컴포넌트에 전송합니다
     * @param event 이벤트
     */
    onUpload(event) {
      const file = event.target.files[0];

      if (file === undefined) {
        this.$store.commit('boardFileStore/cancelFile', this.fileId);
        this.fileName = '';

        return false;
      }

      this.fileName = file.name;

      if (!this.validateFile(file)) {
        this.$store.commit('boardFileStore/cancelFile', this.fileId);
        return false;
      }

      this.fileError = '';
      file.fileId = this.fileId;

      this.$store.commit('boardFileStore/uploadFile', {fileId: this.fileId, file: file});
      this.$emit('upload-file', file);
    },

    /**
     * 파일 사이즈와 확장자를 검증합니다.
     *
     * @param file 파일정보
     * @returns {boolean}
     */
    validateFile(file) {
      if (file.size > this.fileMaxSize) {
        const formattedMaxSize = this.fileMaxSize / (1000 * 1000) + 'MB';
        this.fileError = `파일사이즈는 ${formattedMaxSize}를 넘길수 없습니다.`;

        return false;
      }

      const dotIdx = file.name.lastIndexOf('.')
      const fileExt = file.name.substring(dotIdx + 1);
      if (!this.allowedExtensions.includes(fileExt)) {

        this.fileError = '허용된 확장자가 아닙니다.';

        return false;
      }

      return true;
    },
  },
}
</script>

<style scoped>

  .file-input-container {
    display: flex;
    flex-wrap: wrap;
    gap: 5px;
    margin: 5px 0;
  }

  .file-upload-label {
    border: var(--main-border);
    background-color: var(--sub-color-blue);
    cursor: pointer;
    font-size: 12px;
    height: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 5px;
    box-sizing: border-box;
    border-radius: 3px;
  }

  .file-input-disabled {
    box-sizing: border-box;
    width: 400px;
    height: 30px;
    background-color: white;
    border: var(--main-border);
    border-radius: 3px;
    padding: 10px;
  }

  .file-input {
    display: none;
  }

  .file-error {
    font-size: 12px;
    flex-basis: 100%;
  }

</style>