import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const account = JSON.parse(localStorage.getItem('vuex'))

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: `/user/:id/dashboard`,
    id: account?.user.id,
    name: 'Dashboard',
    component: () => import('../views/Dashboard.vue'),
    meta: {
      hasToken: account !== undefined
    }
  },
  {
    path: `/user/:id/teams`,
    id: account?.user.id,
    name: 'Teams',
    component: () => import('../views/Teams.vue'),
    meta: {
      hasToken: account !== undefined
    }
  },
  {
    path: `/user/:id/users`,
    id: account?.user.id,
    name: 'Users',
    component: () => import('../views/Users.vue'),
    meta: {
      hasToken: account !== undefined
    }
  },
  {
    path: `/user/:id/profile`,
    id: account?.user.id,
    name: 'Profile',
    component: () => import('../views/Profile.vue'),
    meta: {
      hasToken: account !== undefined
    }
  },
  {
    // will match everything (404)
    path: '*',
    name: '404',
    component: () => import('../views/404.vue'),
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta?.hasToken) {
    if (JSON.parse(localStorage.getItem('vuex'))) { //TODO: Verifier que le token est pas expiré
    //TODO: Vérifier si l'user navigue sur la route '/' ou '/register'. Dans ce cas là, le redirect sur son dashboard
    next()
    } else {
      router.push('/')
    }
  }

  next()
})

export default router
