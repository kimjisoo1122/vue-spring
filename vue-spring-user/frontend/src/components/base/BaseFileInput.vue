<template>

  <div class="file-input-container">

    <!-- 이미지 썸네일 표시-->
    <img
        v-if="fileThumbSrc"
        :src="fileThumbSrc"
        alt="업로드 파일 썸네일"
        class="file-thumb">

    <!-- 파일 이름 표시 -->
    <input
        v-model="fileName"
        type="text"
        class="file-input-disabled"
        disabled>

    <!-- 파일 업로드 버튼  -->
    <label :for="fileId" class="file-upload-label">파일 찾기</label>
    <input
        @change="onUpload"
        :id="fileId"
        type="file"
        class="file-input">

    <!-- 파일 에러 메시지 표시 -->
    <input-error :error-msg="fileError" class="file-error"/>

  </div>

</template>

<script>
/**
 * 파일 타입의 베이스 인풋 컴포넌트
 * 파일 업로드를 위한 인풋 필드를 제공하며, 이미지 파일의 경우 썸네일을 표시합니다.
 */
import InputError from "@/components/base/BaseInputError.vue";

export default {
  name: "BaseFileInput",
  components: {InputError},

  data() {
    return {
      fileName: '', /* 파일 이름 */
      fileError: '', /* 파일 에러메시지 */
      fileThumbSrc: '' /* 이미지 파일의 썸네일 소스 */
    }
  },

  props: {
    allowedFileSize: {
      type: Number,
      default: 1000 * 1000 * 2,
      required: false,
      description: '파일 최대 크기'
    },
    fileId: {
      type: Number,
      required: true,
      description: '파일 번호'
    },
    allowedExtensions: {
      type: Array,
      default: () => ['jpg', 'gif', 'png', 'zip'],
      required: false,
      description: '허용 가능한 파일 확장자'
    }
  },

  methods: {
    /**
     * 파일 업로드 이벤트 핸들러
     * @param event
     */
    onUpload(event) {
      const file = event.target.files[0];

      if (file === undefined) {
        this.$store.commit('boardFileStore/cancelFile', this.fileId);
        this.fileName = '';

        return false;
      }

      if (!this.validateFile(file)) {
        this.$store.commit('boardFileStore/cancelFile', this.fileId);
        return false;
      }

      this.showThumb(file, event.target.result);

      this.fileError = '';
      file.fileId = this.fileId;
      this.fileName = file.name;

      this.$store.commit('boardFileStore/uploadFile', {fileId: this.fileId, file: file});
    },

    /**
     * 파일 사이즈와 확장자를 검증합니다.
     *
     * @param file 파일정보
     * @returns {boolean}
     */
    validateFile(file) {
      if (file.size > this.allowedFileSize) {
        const formattedMaxSize = this.allowedFileSize / (1000 * 1000) + 'MB';
        alert(`파일사이즈는 ${formattedMaxSize}를 넘길수 없습니다.`);

        return false;
      }

      const dotIdx = file.name.lastIndexOf('.')
      const fileExt = file.name.substring(dotIdx + 1);
      if (!this.allowedExtensions.includes(fileExt)) {
        alert('허용된 확장자가 아닙니다.');

        return false;
      }

      return true;
    },

    /**
     * 첨부한 이미지 파일의 썸네일을 보여줍니다.
     * @param file 파일객체
     */
    showThumb(file) {
      const dotIdx = file.name.lastIndexOf('.')
      const fileExt = file.name.substring(dotIdx + 1);

      if (fileExt !== 'zip') {
        const fileReader = new FileReader();

        fileReader.onload = e => {
          this.fileThumbSrc = e.target.result;
        }

        fileReader.readAsDataURL(file);
      }
    }
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

  .file-thumb {
    width: 30px;
    height: 30px;
    margin-right: 10px;
  }

  .file-input {
    display: none;
  }

  .file-error {
    font-size: 12px;
    flex-basis: 100%;
  }

</style>