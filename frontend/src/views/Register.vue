<template>
    <v-container fill-height class="d-flex flex-row mr-12 align-center justify-center">
        <v-responsive 
            style="border: 1px solid; border-color: #A7A7A7; background-color: white; font-size: 48px"
            class="rounded-lg pa-8 blue--text text-center"
            max-width="350px" 
            min-width="250px"
        >
            <h3 class="mb-8">Register</h3>

            <v-divider style="background-color: #becdda" />

            <v-container class="py-4">
                <v-text-field 
                    solo
                    label="Email"
                    autofocus
                />

                <v-text-field 
                    solo
                    label="Username"
                />

                <v-text-field 
                    solo 
                    label="Password"
                    type="password"
                />
            </v-container>

            <v-divider style="background-color: #becdda" />

            <v-container class="d-flex flex-column justify-space-between">
                <v-btn 
                    block
                    color="primary"
                    style="font-size: 18px; border: 2px solid black !important"
                    class="font-weight-bold rounded-0 text-capitalize"
                    v-on:click="logIn()"
                >
                    Register
                </v-btn>
            </v-container>
        </v-responsive>

        <v-responsive>
            <v-img
                src="@/assets/register.svg"
                max-height="700px"
                lazy
            />
        </v-responsive>
    </v-container>
</template>

<script>
import { createUser } from '../../lib/user.js'
import ref from 'vue'

export default {
    setup() {
        const { email, username, password } = ref('')

        return {
            email,
            username,
            password
        }
    },
    methods: {
        async logIn() {
            const res = await createUser({
                email: 'mail',
                username: 'username',
                password: 'password'
            })

            console.log('res:', res)
            // this.$router.push('/')
        }
    },
}
</script>

