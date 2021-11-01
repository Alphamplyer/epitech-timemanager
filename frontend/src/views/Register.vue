<template>
    <div id="register">
        <img id="chrono" alt="Vue logo" src="../assets/chrono.svg" />
        <h1>Register</h1>

        <input id="mb" v-model="username" placeholder="Choose a username" />
        <input id="mb" v-model="password" placeholder="Choose a password" />

        <button v-on:click="checkRegister()">Register</button>
        <h3 style="color: red">{{ error && error }}</h3>
    </div>
</template>

<script>
import { getUser } from '../lib/user'
// TODO: Ajouter le addUser to DB

export default {
    name: 'Register',
    methods: {
        async checkRegister() {
            try {
                const res = await getUser()

                if (!res) {
                    this.error = 'Username already used.'
                }

                this.$router.push('/')
            } catch (error) {
                console.log('Error', error)
            }
        }
    },
    data() {
        return {
            username: '',
            password: '',
            error: ''
        }
    }
}
</script>

<style>
#register {
    display: flex;
    flex-direction: column;
    align-items: center;
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
