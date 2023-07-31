import { createStore } from 'vuex'
import axios from "axios";
/* store변경은 store에서만 /*/
const store = createStore({
  state(){
    return {
      name: 'kim',
      age: 20,
      more: {},
    }
  },

  mutations: {
    이름변경(state) {
      state.name = 'park';
    },
    나이증가(state, payload) {
      state.age += payload.age1;
      console.log(payload.age2);
    },
    setMore(state, payload) {
      state.more = {};
    },
  },

  /* 모든 ajax 요청 */
  /* mutation 메소드는 비동기임 순서를 보장해주지 않음 */
  actions: {
    getData(context) {
      axios.get().then((a) => {
        context.commit('setMore', a.data);
      })
    },
    /*axios후 데이터변경은? ,mutations 써야함 */
  }
})

export default store