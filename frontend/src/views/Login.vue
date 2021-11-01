<template>
    <div id="login">
        <img id="chrono" alt="Vue logo" src="../assets/chrono.svg" />
        <h1>Time Manager</h1>

        <input id="mb" v-model.lazy="username" placeholder="Username" />
        <input id="mb" v-model.lazy="password" placeholder="Password" />

        <button id="mb" v-on:click="checkLogin()">Log In</button>
        <h3 style="color: red">{{ error && error }}</h3>
        <router-link to="/register">Register here</router-link>
    </div>
</template>

<script>
import { getUser } from '../lib/user'

export default {
    name: 'Login',
    methods: {
        async checkLogin() {
            try {
                const res = await getUser()

                if (!res) {
                    this.error = 'Invalid username or password.'
                }
                this.$router.push('/dashboard')
            } catch (error) {
                console.log('Error', error)
            }
        },
    },
    data() {
        return {
            username: '',
            password: '',
            error: '',
        }
    },
}
</script>

<style>
#login {
    display: flex;
    align-items: center;
    flex-direction: column;
    position: relative;
    top: 10%;
}

#chrono {
    width: 30vh;
}

#mb {
    margin-bottom: 10px;
}
</style>
