import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
	  token:''
  },
  getters: {
	  GET_TOKEN(state) {return state.token}
  },
  mutations: {
	  //类似set方法
	  SET_TOKEN(state,token) {state.token = token}
  },
  actions: {
  },
  modules: {
  }
})
