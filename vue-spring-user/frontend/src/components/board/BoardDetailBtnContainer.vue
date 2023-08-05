<template>

  <div class="board-detail-btn-container">

    <!-- 해당 작성자만 표시되는 수정폼으로 이동하는 버튼 -->
    <base-button
        v-if="isCurrentUserId(board.userId)"
        @click="this.$router.push({path: `${path}/update/${board.boardId}`, query: condition})"
        class="board-detail-btn-update" name="수정">
    </base-button>

    <!-- 목록으로 이동하는 버튼 -->
    <base-button
        @click="this.$router.push({path: `${path}`, query: condition})"
        class="board-detail-btn-list" name="목록">
    </base-button>

    <!-- 해당 작성자만 표시되는 삭제 버튼 -->
    <base-button
        v-if="isCurrentUserId(board.userId)"
        @click="onDelete"
        class="board-detail-btn-delete" name="삭제">
    </base-button>

  </div>

</template>

<script>
/**
 * 게시글 상세페이지의 버튼 컨테이너 컴포넌트
 */
import BaseButton from "@/components/base/BaseButton.vue";
import {isCurrentUserId} from "@/util/authUtil";

export default {
  name: "BoardDetailBtnContainer",
  components: {BaseButton},
  props: {
    condition: {
      type: Object,
      required: true,
      description: '검색조건'
    },
    path: {
      type: String,
      required: true,
      description: '게시글 경로'
    },
    board: {
      type: Object,
      default: undefined,
      required: true,
      description: '게시글'
    }
  },
  methods: {
    isCurrentUserId,
    onDelete() {
      if (confirm('삭제 하시겠습니까?')) {
        this.$emit('delete-board');
      }
    },
  },
}
</script>

<style scoped>

.board-detail-btn-container {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  margin: 30px 0;
}

.board-detail-btn-container button {
  width: 70px;
}

.board-detail-btn-list {
  background-color: var(--sub-color-violet)
}

.board-detail-btn-delete {
  color: black;
  background-color: white;
  border: var(--main-border);
}

</style>