<template>

  <div class="paging-container" >

    <!-- 이전 전체페이지 -->
    <div v-if="isPreviousTotal" class="paging-prev-total-container">
      <div @click="onPage(this.beginPage - 1)" class="paging-prev-total">&lt;&lt;</div>
    </div>

    <!-- 이전 페이지 -->
    <div v-if="isPrevious" class="paging-prev-container">
      <div @click="onPage(this.page - 1)" class="paging-prev">&lt;</div>
    </div>

    <!-- 페이지 -->
    <div v-if="totalCnt !== 0" class="paging-index-container">
      <div
          @click="onPage(page)"
          v-for="page in endPage"
          :key="page"
          class="paging-page">
        {{ page }}
      </div>
    </div>

    <!-- 다음 페이지 -->
    <div v-if="isNext" class="paging-next-container">
      <div @click="onPage( this.page + 1)" class="paging-next">&gt;</div>
    </div>

    <!-- 다음 전체페이지 -->
    <div v-if="isNextTotal" class="paging-next-total-container">
      <div @click="onPage(this.endPage + 1)" class="paging-next-total">&gt;&gt;</div>
    </div>

  </div>

</template>

<script>
export default {
  name: "Pagination",
  props: {
    totalCnt: {
      type: Number,
      default: 0,
      required: true,
      description: '게시글 총 개수'
    },
    page: {
      type: Number,
      default: 1,
      required: false,
      description: '현재 페이지'
    },
    limit: {
      type: Number,
      default: 10,
      required: false,
      description: '페이지 크기'
    },
    navSize: {
      type: Number,
      default: 10,
      required: false,
      description: '네비게이션 크기'
    }
  },
  methods: {
    onPage(page) {
      this.$emit('page-router', page);
    },
  },
  computed: {
    maxPage(){
      return Math.ceil(this.totalCnt / this.limit); // 게시글 총 페이지
    },
    beginPage() {
      return Math.trunc((this.page - 1) / this.navSize) * this.navSize + 1; // 시작페이지
    },
    endPage() {
      return Math.min((this.beginPage + this.navSize - 1), this.maxPage); // 마지막페이지
    },
    isPrevious() {
      return this.totalCnt !== 0 && this.page !== this.beginPage; // 이전페이지 유무
    },
    isNext() {
      return this.totalCnt !== 0 && this.page !== this.endPage; // 다음페이지 유무
    },
    isPreviousTotal() {
      return this.beginPage > 1; // 이전 전체페이지 유무
    },
    isNextTotal() {
      return this.maxPage !== this.endPage; // 다음 전체페이지 유무
    }
  }
}
</script>

<style scoped>

.paging-container {
  align-items: center;
  display: flex;
  justify-content: center;
  gap: 5px;
}

.paging-container a {
  color: black;
  font-size: 12px;
  text-decoration: none;
}

.paging-page, .paging-prev, .paging-next, .paging-prev-total, .paging-next-total {
  display: flex;
  align-items: center;
  justify-content: center;
  padding-bottom: 2px;
  cursor: pointer;
}


.paging-prev-total-container,.paging-prev-container,
.paging-next-container, .paging-next-total-container {
  width: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.paging-index-container {
  display: flex;
  justify-content: center;
  gap: 5px;
}

</style>