import Vue from 'vue'
import 'es6-promise/auto'
import Vuex from 'vuex'
import VuexPersistence from "vuex-persist"

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
      time: '00:00:00'
    }
  },
  mutations: {
    setTokens(state, {access_token, refresh_token}) {
      state.access_token = "Bearer " + access_token
      state.refresh_token = "Bearer " + refresh_token
    },
    setUser(state, {user}) {
      state.user = {
        id: user.id,
        username: user.username,
        email: user.email,
        role: user.role,
      }
    },
    setUserTeams(state, teams) {
      state.user.teams = teams
    },
    clearUser(state) {
      state.user = {
        id: '',
        username: '',
        email: '',
        role: '',
        teams: {}
      }
      state.access_token = ''
      state.refresh_token = ''
    },
    setClock(state, clock) {
      state.clock = clock
    },
    updateClockTime(state, time) {
      state.clock.time = time
    }
  },
  actions: {
  },
  modules: {
  }
})