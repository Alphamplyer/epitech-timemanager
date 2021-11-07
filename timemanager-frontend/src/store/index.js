import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from "vuex-persist";

Vue.use(Vuex)

const vuexLocalStorage = new VuexPersistence({
  storage: window.localStorage
})

export default new Vuex.Store({
  plugins: [vuexLocalStorage.plugin],
  state: {
    access_token: '',
    refresh_token: '',
    user: {
      id: '',
      username: '',
      email: '',
      role: '',
      teams: {}
    },
    clock: {
      enabled: false,
      started_at: '',
      time: ''
    }
  },
  mutations: {
    setTokens(state, access_token, refresh_token) {
      state.access_token = access_token;
      state.refresh_token = refresh_token;
    },
    setUser(state, user) {
      state.user = user;
    },
    clearUser(state) {
      state.user = {
        id: '',
        username: '',
        email: '',
        role: '',
        teams: {}
      }
    }
  },
  actions: {
  },
  modules: {
  }
})
