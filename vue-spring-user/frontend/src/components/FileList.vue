<template>

  <div class="file-list-container">

    <div
        v-for="file in fileList"
        :key="file.fileId"
        class="file-container">

      <!-- 썸네일이 존재하는 경우 썸네일을 표시합니다. (갤러리) -->
      <img
          v-if="file.galleryThumbName != null"
          :src="`/api/file/image/${file.galleryThumbName}`"
          alt="썸네일 이미지"
          class="file-thumb">

      <!-- 첨부파일 아이콘 -->
      <font-awesome-icon v-else icon="paperclip" class="file-icon" />

      <!-- 첨부파일 다운로드 링크 -->
      <a :href="`/api/file/${file.fileId}`" class="file-link">{{ file.fileOrgName }}</a>

      <!-- 첨부파일 삭제 핸들러 버튼 -->
      <base-button
          v-if="isUpdate"
          @click="onDelete(file.fileId)"
          class="file-btn-delete"
          name="X">
      </base-button>

    </div>

  </div>

</template>

<script>
/**
 * 게시글의 첨부파일 목록 컴포넌트
 */
import BaseButton from "@/components/base/BaseButton.vue";

export default {
  name: "FileList",
  components: {BaseButton},
  props: {
    fileList: {
      type: Array,
      required: true,
      description: '게시글 첨부파일 목록'
    },
    isUpdate: {
      type: Boolean,
      default: false,
      required: false,
      description: '업데이트의 경우 삭제버튼 등장'
    }
  },
  methods: {
    /* 삭제할 첨부파일을 상위컴포넌트에 전송합니다.*/
    onDelete(fileId) {
      this.$emit('delete-file', fileId);
    },
  }
}
</script>

<style scoped>

.file-list-container {
  font-size: var(--middle-font-size);
}

.file-container {
  margin: 10px 0;
  display: flex;
  align-items: center;
}

.file-thumb {
  border-radius: 3px;
  margin-right: 10px;
}
.file-icon {
  margin-right: 5px;
}

.file-link {
  padding-bottom: 2px;
}
.file-btn-delete {
  margin-left: 30px;
}

</style>