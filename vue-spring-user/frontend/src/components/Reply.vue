<template>

  <div class="reply-container">

    <!-- 댓글목록 -->
    <div class="reply-list-container">

      <div v-for="reply in replyList"
           :key="reply.replyId"
           class="reply">

        <div class="reply-left-container">
          <div class="reply-writer-date-container">
            <div class="reply-writer">{{ reply.userName }}</div>
            <div class="reply-date">{{ reply.createDate }}</div>
          </div>
          <div class="reply-content">{{ reply.replyContent }}</div>
        </div>

        <!-- 해당 댓글 작성자만 삭제가 표시됩니다. -->
        <div
            v-if="isCurrentUserId(reply.userId)"
            @click="onDelete(reply.replyId)"
            class="reply-right-container">
          <div class="reply-delete">삭제</div>
        </div>

      </div>

    </div>

    <!-- 댓글등록 (인증된상태에만 표시합니다.) -->
    <div v-if="isAuthenticated()" class="reply-register-container">
      <base-input
          v-model="replyContent"
          :placeholder="replyPlaceHolder"
          class="reply-register-input"></base-input>
      <base-button @click="onRegister" name="등록" class="reply-register-button"></base-button>
    </div>

  </div>
</template>

<script>
import {deleteReply, registerReply} from "@/api/replyService";
import {isAuthenticated, isCurrentUserId} from "@/util/authUtil";
import BaseButton from "@/components/base/BaseButton.vue";
import BaseInput from "@/components/base/BaseInput.vue";

export default {
  name: "Reply",
  components: {BaseInput, BaseButton},

  data() {
    return {
      replyContent: '', /* 댓글 내용 */
      replyError: '', /* 댓글 에러 메시지*/
      replyPlaceHolder: '댓글을 입력해 주세요.',
    }
  },

  props: {
    replyList: {
      type: Array,
      required: true,
      description: '댓글 목록'
    },
  },

  methods: {
    isCurrentUserId,
    isAuthenticated,

    /**
     * 댓글을 등록하고 등록된 댓글을 게시글 상세 컴포넌트에 전송합니다.
     */
    onRegister() {
      console.log(isAuthenticated());
      if (this.validateReply()) {
        const replyDto = {
          boardId: this.$route.params.boardId,
          replyContent: this.replyContent,
        };

        registerReply(replyDto)
            .then(reply => {
              this.$emit('register-reply', reply);
              this.replyContent = '';
            })
            .catch(({message}) => {
              console.error(message);
              alert(message);
            });
      }
    },

    /**
     * 댓글을 삭제합니다.
     */
    async onDelete(replyId) {
      if (confirm('댓글을 삭제하시겠습니까?')) {
        try {
          await deleteReply(replyId);
          this.$emit('delete-reply', replyId);
        } catch ({message}) {
          console.error(message);
        }
      }
    },

    /**
     * 빈 댓글을 검증합니다.
     * @returns {boolean}
     */
    validateReply() {
      if (!this.replyContent) {
        alert('댓글을 입력해주세요.');
        return false;
      }
      return true;
    },
  }
}
</script>

<style scoped>

.reply-container {
  background-color: antiquewhite;
  padding: 10px;
  border-radius: 3px;
}

.reply {
  border-bottom: 1px solid black;
  padding: 10px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.reply-writer-date-container {
  display: flex;
  gap: 10px;
}

.reply-writer {
  font-size: var(--small-font-size);
  font-weight: bold;
}

.reply-date {
  font-size: 12px;
}

.reply-content {
  font-size: 14px;
}

.reply-delete {
  font-size: 12px;
  cursor: pointer;
}

.reply-register-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100px;
}

.reply-register-input {
  font-size: 12px;
  height: 50px;
  flex-grow: 1;
}

.reply-register-input::placeholder {
  color: #ccc;
  font-size: var(--small-font-size);
}

.reply-register-button {
  margin-left: 10px;
  height: 50px;
  width: 50px;
  background-color: white;
  color: black;
  border: var(--main-border);
}

</style>