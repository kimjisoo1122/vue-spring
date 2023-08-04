<template>
  <div class="main-container">
    <router-view></router-view>
  </div>
</template>

<script>

import {mapMutations, mapState} from "vuex";
import Login from "@/views/user/Login.vue";
import SignUp from "@/views/user/SignUp.vue";

export default {
  name: 'App',
  components: {Login},
  methods: {
    ...mapMutations(['이름변경', '나이증가']),
    ...mapMutations({함수: "이름변경"}),
  },

  /* 메소드는 실행할때마다 메소드코드를 읽고 재실행 */
  /* 컴퓨티드는 사용되는값이 변경되기전에는 재실행안함 한번저장후 끝 */
  /* 컴퓨티드는 항상 return쓰면 됨 */
  computed: {
    ...mapState(['name']),
    ...mapState({내이름: 'name'}),
  }
};
</script>

<style>
:root {
  --title-font-size: 20px;
  --middle-font-size: 15px;
  --small-font-size: 12px;

  --main-border: 1px solid #C0C0C0;
  --border-color-gray: #C0C0C0;

  --main-color-blue: #0090F9;
  --sub-color-blue: #F0F6FA;
  --sub-color-violet: rgb(134 84 246);
}
.main-container {
  width: 1100px;
  margin: 0 auto;
}

</style>
