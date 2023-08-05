<template>

  <header>

    <h1 class="home-header-title">뷰 스프링 게시판</h1>

    <div class="home-header-nav-container">

      <nav class="home-header-nav">
        <ul class="home-nav-list">
          <li class="home-nav-notices">공지사항</li>
          <li class="home-nav-frees">자유 게시판</li>
          <li class="home-nav-galleries">갤러리</li>
          <li class="home-nav-qna">문의 게시판</li>
        </ul>
      </nav>

      <div class="home-header-join-container">
        <div class="home-header-login">로그인</div>
        <span>/</span>
        <div class="home-header-signup">회원가입</div>
      </div>

    </div>

  </header>

  <main>

    <div class="home-main-user-container">
      <div class="home-user-name">유저이름</div>
      <div class="home-user-logout">로그아웃</div>
    </div>

    <section class="home-main-board-list-container">

      <article class="home-notices-container">
        <div class="home-notices-header">
          <h3 class="home-notices-title">공지사항</h3>
          <button class="home-board-see-more">더보기+</button>
        </div>
        <div class="home-notices-list-container">
          <div class="notices-header">
            <div class="notices-header-no">번호</div>
            <div class="notices-header-category">분류</div>
            <div class="notices-header-title">제목</div>
          </div>
          <div class="home-notices">
            <div class="notices-no">번호</div>
            <div class="notices-category">분류</div>
            <div class="notices-title-container">
              <div class="notices-title">제목</div>
              <div class="home-board-new">new</div>
            </div>
          </div>
        </div>
      </article>

      <article class="home-frees-container">
        <div class="home-frees-header">
          <h3 class="home-frees-title">자유게시판</h3>
          <button class="home-board-see-more">더보기+</button>
        </div>
        <div class="home-frees-list-container">
          <div class="frees-header">
            <div class="frees-header-no">번호</div>
            <div class="frees-header-category">분류</div>
            <div class="frees-header-title">제목</div>
          </div>
          <div class="home-frees">
            <div class="frees-no">번호</div>
            <div class="frees-category">분류</div>
            <div class="frees-title-container">
              <div class="frees-title">제목</div>
              <div class="frees-title-reply-cnt">3</div>
              <div class="home-board-new"></div>
              <div class="home-board-file">file</div>
            </div>
          </div>
        </div>
      </article>

      <article class="home-galleries-container">
        <div class="home-galleries-header">
          <h3 class="home-galleries-title">갤러리</h3>
          <button class="home-board-see-more">더보기+</button>
        </div>
        <div class="home-galleries-list-container">
          <div class="galleries-header">
            <div class="galleries-header-no">번호</div>
            <div class="galleries-header-category">분류</div>
          </div>
          <div class="home-galleries">
            <div class="galleries-no">번호</div>
            <div class="galleries-category">분류</div>
            <div class="galleries-title-container">
              <div class="galleries-img-container">
                <img src="" alt="갤러리 썸네일">
              </div>
              <div class="galleries-img-cnt"></div>
              <div class="home-board-new"></div>
            </div>
          </div>
        </div>
      </article>

      <article class="home-qna-container">
        <div class="home-qna-header">
          <button class="home-qna-myqna" >나의 문의 내역</button>
          <h3 class="home-qna-title">문의 게시판</h3>
          <button class="home-board-see-more">더보기+</button>
        </div>
        <div class="home-qna-list-container">
          <div class="qna-header">
            <div class="qna-header-no">번호</div>
            <div class="qna-header-title">제목</div>
          </div>
          <div class="home-qna">
            <div class="qna-no">번호</div>
            <div class="qna-title-container">
              <div class="qna-title">제목</div>
              <div class="qna-answer-status">(미답변)</div>
              <div class="home-board-new">new</div>
              <div class="qna-secret">자물쇠</div>
            </div>
          </div>
        </div>
      </article>

    </section>

  </main>

</template>



<script>
export default {
  name: "Home"
}
</script>

<style scoped>

header {
  padding: 0 10px;
  align-items: center;
  border-bottom: var(--main-border);
}

.home-header-title {
  margin: 0;
  font-size: var(--title-font-size);
  text-align: center;
}

.home-header-nav-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.home-nav-list {
  list-style: none;
  display: flex;
  padding: 0;
  align-items: center;
  justify-content: center;
}

.home-nav-list > li {
  border-right: var(--main-border);
  padding: 0 10px;
  font-size: var(--middle-font-size);
  font-weight: bold;
  cursor: pointer;
}

.home-header-join-container {
  display: flex;
  gap: 5px;
  font-size: var(--small-font-size);
}

.home-header-login , .home-header-signup {
  cursor: pointer;
}

main {
  padding: 10px;
}

button {
  cursor: pointer;
  background: white;
  border: none;
}

.home-main-user-container {
  display: flex;
  justify-content: flex-end;
  font-size: var(--small-font-size);
  gap: 30px;
  margin-bottom: 30px;
}

.home-user-name, .home-user-logout {
  cursor: pointer;
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
  border: var(--main-border)
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
}

.notices-header, .frees-header,
.galleries-header, .qna-header {
  height: 30px;
  display: flex;
  font-size: var(--middle-font-size);
  font-weight: bold;
  align-items: center;
  flex-grow: 1;
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

.home-notices, .home-frees {
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: var(--main-border)
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
}

.galleries-title-container {
  display: flex;
  align-items: center;
  margin-left: 15px;
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
}

.qna-title-container {
  display: flex;
  align-items: center;
  padding-left: 5px;
}

.qna-title {
  max-width: 330px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}



.home-qna-myqna {
  position: absolute;
  left: 5px;
  font-size: var(--small-font-size);

}



</style>