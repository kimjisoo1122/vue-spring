<template>

  <div class="search-condition-container" >

    <form @submit.prevent="onSearch" class="search-form" >

      <div class="search-first-container">

        <!-- fromDate, toDate -->
        <div class="search-date-container">
          <span class="search-date-title">등록일시</span>
          <base-input
              v-model="searchForm.fromDate"
              type="date"
              class="search-date-from">
          </base-input>
          <span class="search-date-between"> ~ </span>
          <base-input
              v-model="searchForm.toDate"
              type="date"
              class="search-date-to">
          </base-input>
        </div>

        <!-- searchCategory 문의게시글인 경우 카테고리가 존재하지 않습니다. -->
        <div class="search-keyword-container">
          <category-select
              v-if="!isQna"
              v-model="searchForm.searchCategory"
              :category-list="categoryList"
              class="search-keyword-select">
          </category-select>

          <!-- search 문의게시글인 경우 검색어 인풋의 크기가 늘어납니다. -->
          <base-input
              v-model="searchForm.search"
              :class="{'search-keyword-input-qna' : isQna}"
              placeholder="제목 OR 내용"
              class="search-keyword-input">
          </base-input>

          <base-button @click="onSearch" name="검색" class="search-keyword-btn"></base-button>

        </div>

      </div>

      <div class="search-second-container">

        <!-- limit -->
        <div class="search-limit-container">
          <base-select
              v-model="searchForm.limit"
              :options="isGalleryLimit ? options.galleryLimit : options.limit"
              @change="onSearch"
              class="search-limit-select">
          </base-select>
          <span class="search-limit-text">개씩 보기</span>
        </div>

        <!-- orderCondition, order -->
        <div class="search-order-container">

          <span class="search-order-title">정렬</span>
          <base-select
              v-model="searchForm.orderCondition"
              :options="options.orderCondition"
              @change="onSearch"
              class="search-ordercondition-select">
          </base-select>

          <base-select
              v-model="searchForm.order"
              :options="options.order"
              @change="onSearch"
              class="search-order-select">
          </base-select>

        </div>

      </div>

    </form>

  </div>

</template>

<script>
/**
 * 게시글 검색조건 컴포넌트
 */
import BaseInput from "@/components/base/BaseInput.vue";
import CategorySelect from "@/components/CategorySelect.vue";
import BaseButton from "@/components/base/BaseButton.vue";
import BaseSelect from "@/components/base/BaseSelect.vue";
import {options} from "@/data/options";
import moment from "moment";

export default {
  name: "BoardSearchCondition",
  components: {BaseSelect, BaseButton, CategorySelect, BaseInput},
  data() {
    return {
      /* 검색 정보 */
      searchForm: {
        page: '', /* 현재 페이지 */
        fromDate: '', /* 이후 날짜 */
        toDate: '', /* 이전 날짜 */
        searchCategory: '', /* 검색 카테고리 타입*/
        search: '', /* 검색어 (제목 OR 내용) */
        limit: '', /* 게시글 조회 개수 */
        orderCondition: '', /* 게시글 검색 조건 */
        order: '', /* 내림차순 OR 오름차순 */
      },
      options, /* 게시글 Select Option */
    }
  },

  props: {
    categoryList: {
      type: Array,
      default: undefined,
      required: false,
      description: '카테고리 목록'
    },
    condition: {
      type: Object,
      required: true,
      description: '게시글 검색조건'
    },
    maxDatePeriod: {
      type: Number,
      default: 365,
      required: false,
      description: '날짜검색 최대 기간'
    },
    isGalleryLimit: {
      type: Boolean,
      default: false,
      required: false,
      description: '갤러리 게시판 리밋옵션여부'
    },
    isQna: {
      type: Boolean,
      default: false,
      required: false,
      description: '문의글 타입여부'
    }
  },

  created() {
    /* 상위컴포넌트의 검색조건을 검색 폼에 매핑합니다.*/
    for (const value in this.searchForm) {
      this.searchForm[value] = this.condition[value];
    }
  },

  methods: {
    /* 검색조건을 상위컴포넌트에 전달하는 핸들러 */
    onSearch() {
      if (!this.validateDate()) {
        return false;
      }
      this.$emit('condition-search', this.searchForm);
    },

    /**
     * 날짜는 최대 1년 까지 검색가능합니다.
     * 날짜조건중 하나만 값이 있는 경우 모두 빈 값으로 설정합니다.
     */
    validateDate() {
      const fromDate = this.searchForm.fromDate;
      const toDate = this.searchForm.toDate;

      const fromMoment = moment(fromDate);
      const toMoment = moment(toDate);
      const diffDay = toMoment.diff(fromMoment, 'days');

      if (diffDay > this.maxDatePeriod) {
        alert(`날짜는 최대 ${this.maxDatePeriod}일 까지 검색이 가능합니다.`);

        return false;
      }

      /* 날짜조건중 한 쪽에만 존재하는 경우 모두 빈 값으로 설정합니다.*/
      if (fromDate.length * toDate.length === 0) {
        this.searchForm.fromDate = '';
        this.searchForm.toDate = '';
      }

      return true;
    },
  }
}
</script>

<style scoped>

  .search-date-from, .search-date-to  {
    width: 130px;
    font-size: var(--small-font-size);
    height: 30px;
  }


  .search-condition-container {
    height: 100px;
    display: flex;
    flex-direction: column;
  }

  .search-date-container {
    width: 400px;
    height: 30px;
  }

  .search-keyword-container {
    width: 700px;
    display: flex;
    justify-content: space-between;
  }

  .search-first-container {
    border: var(--main-border);
    padding: 5px 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 40px;
    gap: 10px;
  }

  .search-date-title {
    font-size: var(--middle-font-size);
    margin-right: 10px;
  }

  .search-keyword-select {
    width: 150px;
  }

  .search-keyword-input {
    width: 400px;
    height: 30px;
    font-size: var(--small-font-size);
  }

  .search-keyword-input::placeholder {
    font-size: var(--small-font-size);
  }

  .search-keyword-input-qna {
    width: 500px;
  }

  .search-keyword-btn {
    width: 80px;
    margin-left: 10px;
  }

  .search-second-container {
    margin: 20px 0;
    display: flex;
    justify-content: space-between;
    font-size: var(--small-font-size);
  }

  .search-limit-select {
    font-size: var(--middle-font-size);
  }

  .search-limit-text {
    font-size: var(--middle-font-size);
    margin-left: 5px;
  }

  .search-order-title {
    font-size: var(--middle-font-size);
    margin-right: 10px;
  }

  .search-ordercondition-select {
    margin-right: 10px;
  }

  .search-ordercondition-select, .search-order-select {
    width: 100px;
    font-size: var(--small-font-size)
  }

  .search-date-error {
    border: 1px solid red;
  }

  .my-qna-container {
    /*flex-basis: 100%;*/
  }

</style>