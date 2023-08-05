<template>

  <div class="file-list-container">
    <div
        v-for="file in fileList"
        :key="file.fileId"
        class="file-container">
      <img
          v-if="file.galleryThumbName != null"
          :src="`/api/file/image/${file.galleryThumbName}`"
          alt="썸네일 이미지"
          class="file-thumb">
      <font-awesome-icon v-else icon="paperclip" class="file-icon" />
      <a  :href="`/api/file/${file.fileId}`" class="file-link">{{ file.fileOrgName }}</a>
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
import BaseButton from "@/components/base/BaseButton.vue";

export default {
  name: "FileList",
  components: {BaseButton},
  props: {
    fileList: {
      type: Array,
      default: undefined,
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