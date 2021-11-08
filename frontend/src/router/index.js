import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

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
    path: '/user/dashboard',
    name: 'Dashboard',
    component: () => import('../views/Dashboard.vue'),
    meta: {
      hasToken: localStorage.getItem('token') !== undefined
    }
  },
  {
    path: '/user/teams',
    name: 'Teams',
    component: () => import('../views/Teams.vue'),
    meta: {
      hasToken: localStorage.getItem('token') !== undefined
    }
  },
  {
    path: '/user/users',
    name: 'Users',
    component: () => import('../views/Users.vue'),
    meta: {
      hasToken: localStorage.getItem('token') !== undefined
    }
  },
  {
    path: '/user/profile',
    name: 'Profile',
    component: () => import('../views/Profile.vue'),
    meta: {
      hasToken: localStorage.getItem('token') !== undefined
    }
  },
  {
    // will match everything (404)
    path: '*',
    name: '404',
    component: () => import('../views/404.vue'),
    meta: {
      hasToken: localStorage.getItem('token') !== undefined
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta?.hasToken) {
    if (localStorage.token !== undefined) { //TODO: Verifier que le token est pas expiré
    //TODO: Vérifier si l'user navigue sur la route '/' ou '/register'. Dans ce cas là, le redirect sur son dashboard
    next()
    } else {
      router.push('/')
    }
  }

  next()
})

export default router
