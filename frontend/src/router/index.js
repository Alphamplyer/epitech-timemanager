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
    component: () => import('../views/Dashboard.vue')
  },
  {
    path: '/user/teams',
    name: 'Teams',
    component: () => import('../views/Teams.vue')
  },
  {
    path: '/user/users',
    name: 'Users',
    component: () => import('../views/Users.vue')
  },
  {
    path: '/user/profile',
    name: 'Profile',
    component: () => import('../views/Profile.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
