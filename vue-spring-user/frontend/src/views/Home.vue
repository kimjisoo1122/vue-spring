<template>

  <GNB></GNB>

  <main>

    <section class="home-main-board-list-container">

      <!-- 공지사항 -->
      <article class="home-notices-container">

        <!-- 공지시항 타이틀 -->
        <div class="home-notices-header">
          <h3 class="home-notices-title">공지사항</h3>
          <button
              @click="router.push('/notices')"
              class="home-board-see-more">
            더보기+
          </button>
        </div>

        <!-- 공지사항 목록 -->
        <div class="home-notices-list-container">

          <!-- 공지사항 헤더 -->
          <div class="notices-header">
            <div class="notices-header-no">번호</div>
            <div class="notices-header-category">분류</div>
            <div class="notices-header-title">제목</div>
          </div>

          <!-- 공지사항 -->
          <div
              v-for="notice in noticeList"
              :key="notice.boardId"
              :class="{'notices-alarm' : notice.categoryName === '알림'}"
              class="home-notices">

            <!-- 공지사항 번호 -->
            <div class="notices-no">{{ notice.boardId }}</div>

            <!-- 공지사항 카테고리 -->
            <div class="notices-category">{{ notice.categoryName }}</div>

            <!-- 공지사항 제목 -->
            <div class="notices-title-container">
              <div
                  @click="onDetailRouter('/notices', notice)"
                  class="notices-title">
                {{ notice.boardTitle }}
              </div>

              <!-- 공지사항 new -->
              <div class="home-board-new">new</div>
            </div>
          </div>

        </div>

      </article>

      <!-- 자유게시판 -->
      <article class="home-frees-container">

        <!-- 자유게시판 타이틀 -->
        <div class="home-frees-header">
          <h3 class="home-frees-title">자유게시판</h3>
          <button
              @click="router.push('/frees')"
              class="home-board-see-more">
            더보기+
          </button>
        </div>

        <!-- 자유게시글 목록 -->
        <div class="home-frees-list-container">

          <!-- 자유게시글 헤더 -->
          <div class="frees-header">
            <div class="frees-header-no">번호</div>
            <div class="frees-header-category">분류</div>
            <div class="frees-header-title">제목</div>
          </div>

          <!-- 자유게시글 -->
          <div
              v-for="free in freeList"
              :key="free.boardId"
              class="home-frees">

            <!-- 자유게시글 번호 -->
            <div class="frees-no">{{ free.boardId }}</div>

            <!-- 자유게시글 카테고리 -->
            <div class="frees-category">{{ free.categoryName }}</div>

            <!-- 자유게시글 제목 -->
            <div class="frees-title-container">
              <div
                  @click="onDetailRouter('/frees', free)"
                  class="frees-title">
                {{ free.boardTitle }}
              </div>

              <!-- 자유게시글 댓글 개수 -->
              <div
                  v-if="free.replyCnt !== 0"
                  class="frees-title-reply-cnt">({{ free.replyCnt }})</div>
              <div class="home-board-new">new</div>

              <!-- 자유게시글 파일상태 -->
              <font-awesome-icon
                  v-if="free.fileStatus"
                  icon="paperclip"
                  class="frees-title-file" />
            </div>

          </div>

        </div>

      </article>

      <!-- 갤러리 -->
      <article class="home-galleries-container">

        <!-- 갤러리 타이틀 -->
        <div class="home-galleries-header">
          <h3 class="home-galleries-title">갤러리</h3>
          <button
              @click="router.push('/galleries')"
              class="home-board-see-more">
            더보기+
          </button>
        </div>

        <!-- 갤러리 목록 -->
        <div class="home-galleries-list-container">

          <!-- 갤러리 헤더 -->
          <div class="galleries-header">
            <div class="galleries-header-no">번호</div>
            <div class="galleries-header-category">분류</div>
          </div>

          <!-- 갤러리 -->
          <div
              v-for="gallery in galleryList"
              :key="gallery.boardId"
              class="home-galleries">
            <!-- 갤러리 번호 -->
            <div class="galleries-no">{{ gallery.boardId }}</div>

            <!-- 갤러리 카테고리 -->
            <div class="galleries-category">{{ gallery.categoryName }}</div>

            <!-- 갤러리 이미지 -->
            <div class="galleries-title-container">
              <div
                  @click="onDetailRouter('/galleries', gallery)"
                  class="galleries-img-container">
                <img :src="`${dynamicDomain}/api/file/image/${gallery.galleryThumbName}`" alt="갤러리 썸네일">
              </div>

              <!-- 갤러리 개수-->
              <div
                  v-if="gallery.galleryCnt !== 1"
                  class="galleries-img-cnt">+{{ gallery.galleryCnt - 1 }}</div>

              <!-- 갤러리 new -->
              <div class="home-board-new">new</div>
            </div>
          </div>

        </div>

      </article>

      <!-- 문의게시판 -->
      <article class="home-qna-container">

        <!-- 문의게시판 타이틀 -->
        <div class="home-qna-header">
          <button @click="onMyQnaList" class="home-qna-myqna" >나의 문의 내역</button>
          <h3 class="home-qna-title">문의 게시판</h3>
          <button
              @click="router.push('/qna')"
              class="home-board-see-more">
            더보기+
          </button>
        </div>

        <!-- 문의게시글 목록 -->
        <div class="home-qna-list-container">

          <!-- 문의게시글 헤더 -->
          <div class="qna-header">
            <div class="qna-header-no">번호</div>
            <div class="qna-header-title">제목</div>
          </div>

          <!-- 문의게시글 -->
          <div
              v-for="qna in qnaList"
              :key="qna.boardId"
              class="home-qna">
            <!-- 문의게시글 번호 -->
            <div class="qna-no">{{ qna.boardId }}</div>

            <!-- 문의게시글 제목 -->
            <div class="qna-title-container">
              <div
                  @click="onDetailRouter('/qna', qna)"
                  class="qna-title">
                {{ qna.boardTitle }}
              </div>

              <!-- 문의게시글 답변상태 -->
              <div class="qna-answer-status">
                ({{ qna.answerStatus ? '답변완료' : '미답변' }})
              </div>

              <!-- 문의게시글 new-->
              <div class="home-board-new">new</div>

              <!-- 문의게시글 비밀글 -->
              <font-awesome-icon
                  v-if="qna.qnaSecret"
                  icon="lock"
                  class="qna-secret"/>
            </div>
          </div>

        </div>

      </article>

    </section>

  </main>

</template>

<script setup>
/**
 * 홈 화면 컴포넌트
 */

import {ref} from "vue";
import {getHomeBoardList} from "@/api/homeService";
import {useRoute, useRouter} from "vue-router";
import {useStore} from "vuex";
import {isAuthenticated, isCurrentUserId} from "@/util/authUtil";
import GNB from "@/components/GNB.vue";

const router = useRouter();
const route = useRoute();
const store = useStore();

const noticeList = ref([]); /* 공지사항 */
const freeList = ref([]); /* 자유게시판 */
const galleryList = ref([]); /* 갤러리 */
const qnaList = ref([]); /* 문의게시판 */
const dynamicDomain = ref(process.env.VUE_APP_API_ENDPOINT); /* 갤러리 동적 도메인 */

initHome();

/**
 * 홈 화면 컴포넌트를 초기화합니다.
 */
async function initHome() {
  try {
    const homeBoard = await getHomeBoardList();

    noticeList.value = homeBoard.noticeList;
    freeList.value = homeBoard.freeList;
    galleryList.value = homeBoard.galleryList;
    qnaList.value = homeBoard.qnaList;
  } catch ({message}) {
    console.error(message);
  }
}

/**
 * 해당 게시글 상세컴포넌트로 라우팅합니다.
 * 문의게시글은 작성자만 라우팅 가능합니다.
 *
 * @param path 라우팅 경로
 * @param board 해당게시글
 */
function onDetailRouter(path, board) {
  if (path === '/qna' && board.qnaSecret) {
    if (!isCurrentUserId(board.userId)) {
      alert('해당 문의글은 작성자만 이용가능합니다.');
      return false;
    }
  }

  router.push(`${path}/${board.boardId}`);
}

function onMyQnaList() {
  if (!isAuthenticated()) {
    router.push('/login');

    return;
  }

  router.push({
    path: '/qna',
    query: {
      myQna: true
    }
  });
}

</script>

<script>
export default {
  name: "Home"
}
</script>

<style scoped>

main {
  padding: 10px;
}

button {
  cursor: pointer;
  background: white;
  border: none;
}

section {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  gap: 30px;
}

article {
  flex-basis: 45%;
  height: 270px;
}

.home-board-see-more {
  position: absolute;
  right: 5px;
  font-weight: bold;
  font-size: var(--small-font-size);
}

.home-board-new {
  font-size: 11px;
  color: orange;
}

.home-notices-header, .home-frees-header,
.home-galleries-header, .home-qna-header {
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  margin-bottom: 10px;
}

.notices-header, .frees-header,
.galleries-header, .qna-header {
  padding: 3px 0;
  display: flex;
  font-size: var(--middle-font-size);
  font-weight: bold;
  align-items: center;
  border-top: 2px solid var(--border-color-gray);
  border-bottom: 2px solid var(--border-color-gray);
  box-sizing: border-box;
}

.notices-header-no, .frees-header-no,
.notices-header-category, .frees-header-category,
.notices-no, .frees-no ,.notices-category, .frees-category {
  flex-basis: 10%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.notices-header-title, .frees-header-title {
  flex-basis: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.home-notices-list-container, .home-frees-list-container,
.home-galleries-list-container, .home-qna-list-container{
  display: flex;
  flex-direction: column;
  height: 230px;
}

.home-notices, .home-frees {
  box-sizing: border-box;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: var(--main-border);
  flex-grow: 1;
}

.notices-alarm {
  background-color: var(--sub-color-blue);
}

.notices-title-container, .frees-title-container {
  flex-basis: 80%;
  display: flex;
  align-items: center;
  padding-left: 5px;
}

.notices-title, .frees-title {
  cursor: pointer;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
  max-width: 330px;
  margin-right: 10px;
}

.frees-title {
  max-width: 280px;
}

.frees-title-reply-cnt {
  margin-right: 10px;
}

.frees-title-file {
  margin-left: 10px;
  padding-top: 3px;
}

.galleries-header-no, .galleries-header-category,
.galleries-no, .galleries-category {
  flex-basis: 10%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.home-galleries {
  display: flex;
  align-items: center;
  height: 60px;
  border-bottom: var(--main-border);
  flex-grow: 1;
}

.galleries-title-container {
  display: flex;
  align-items: center;
  margin-left: 15px;
}

.galleries-img-container {
  cursor: pointer;
  margin: 0 20px;
}

.galleries-img-cnt {
  margin-right: 20px;
  font-weight: bold;
}

.qna-header-no, .qna-no {
  flex-basis: 10%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.qna-header-title {
  flex-grow: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.home-qna {
  display: flex;
  align-items: center;
  height: 30px;
  border-bottom: var(--main-border);
  flex-grow: 1;
}

.qna-title-container {
  display: flex;
  align-items: center;
  padding-left: 5px;
}

.qna-title {
  cursor: pointer;
  max-width: 260px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}

.qna-answer-status {
  margin: 0 5px
}

.qna-secret {
  color: gray;
  margin-left: 10px;
}

.home-qna-myqna {
  position: absolute;
  left: 5px;
  font-size: var(--small-font-size);
}
</style>