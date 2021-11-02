import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/me',
        name: 'User account',
        component: () => import('../views/User.vue'),
    },
    {
        path: '/',
        name: 'Login',
        component: () => import('../views/Login.vue'),
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('../views/Register.vue'),
    },
    {
        path: '/user/dashboard',
        name: 'Dashboard',
        component: () => import('../views/Dashboard.vue'),
    },
    {
        path: '/user/profile',
        name: 'Profile',
        component: () => import('../views/Profile.vue'),
    },
    {
        path: '/user/teams',
        name: 'Teams',
        component: () => import('../views/Teams.vue'),
    },
    {
        path: '/user/users',
        name: 'Users',
        component: () => import('../views/Users.vue'),
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router
