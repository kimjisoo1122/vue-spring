<template>

  <GNB></GNB>

  <!-- 게시글 제목 -->
  <board-title title="갤러리"></board-title>

  <!-- 게시글 상세 헤더 -->
  <board-detail-header :board="gallery"></board-detail-header>

  <div class="gallery-slide-container">

    <!-- 이미지 슬라이더  -->
    <carousel ::mouseDrag="false" class="gallery-carousel-container">
      <slide v-for="file in fileList" :key="file.fileId">
        <img class="gallery-slide" :src="`${dynamicDomain}/api/file/image/${file.galleryImgName}`" alt="">
      </slide>

      <template #addons>
        <navigation class="gallery-carousel-nav"/>
        <pagination class="gallery-carousel-page"/>
      </template>
    </carousel>

  </div>

  <!-- 게시글 상세 내용 -->
  <board-detail-content
      :board="gallery"
      class="board-detail-content">
  </board-detail-content>

  <!-- 게시글 상세 버튼 -->
  <board-detail-btn-container
      @delete-board="onDelete"
      :board="gallery"
      :condition="condition"
      path="/galleries"
      class="board-detail-btn-container">
  </board-detail-btn-container>

</template>

<script setup>
/**
 * 갤러리 상세 컴포넌트
 */
import 'vue3-carousel/dist/carousel.css'
import {Carousel, Navigation, Pagination, Slide} from 'vue3-carousel'
import BoardTitle from "@/components/board/BoardTitle.vue";
import BoardDetailHeader from "@/components/board/BoardDetailHeader.vue";
import BoardDetailContent from "@/components/board/BoardDetailContent.vue";
import BoardDetailBtnContainer from "@/components/board/BoardDetailBtnContainer.vue";
import {createCondition} from "@/util/queryParamUtil";
import {formatDate} from "@/util/formatUtil";
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import {deleteGallery, getGalleryDetail} from "@/api/board/galleryService";
import GNB from "@/components/GNB.vue";

const route = useRoute();
const router = useRouter();

const condition = ref({}); /* 검색조건 */
const gallery = ref({}); /* 갤러리 */
const fileList = ref({}); /* 파일 목록 */
const dynamicDomain = ref(process.env.VUE_APP_API_ENDPOINT); /* 갤러리 동적 도메인 */

initGalleryDetail();

/**
 * 갤러리 상세 컴포넌트를 초기화합니다.
 */
async function initGalleryDetail() {
  condition.value = createCondition(route.query);

  try {
    const boardId = route.params.boardId;
    const [galleryResult] = await Promise.all([
      getGalleryDetail(boardId),
    ]);
    gallery.value = galleryResult.gallery;
    gallery.value.createDate = formatDate(gallery.value.createDate);

    fileList.value = galleryResult.fileList
  } catch ({message}) {
    console.error(message);
  }
}

/**
 * 갤러리를 삭제합니다.
 */
async function onDelete() {
  try {
    await deleteGallery(route.params.boardId);
    router.push({path: '/galleries', query: condition});
  } catch ({message}) {
    alert(message);
  }
}
</script>

<script>
export default {
  name: "GalleryDetail",
}
</script>

<style scoped>

.board-detail-content {
  min-height: 70px;
  margin-top: 30px;
}

.board-detail-btn-container {
  margin: 30px 0;
}

.gallery-slide-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.gallery-carousel-container {
  width: 500px;
}

.gallery-carousel-page {
  margin-right: 30px;
}
.gallery-slide {
  border-radius: 5px;
  width: 250px;
}

</style>