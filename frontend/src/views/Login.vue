<template>
    <v-container fill-height class="d-flex flex-row mr-12 align-center justify-center">
        <v-responsive 
            style="border: 1px solid; border-color: #A7A7A7; background-color: white; font-size: 42px"
            class="rounded-lg pa-8 blue--text text-center"
            max-width="350px" 
            min-width="250px"
        >
            <h3 
                style="line-height: 1"
                class="mb-8"
            >
                Time Manager
            </h3>

            <v-divider style="background-color: #becdda" />

            <v-container class="py-4">
                <v-text-field 
                    required
                    outlined
                    autofocus
                    min="8"
                    label="Username"
                    placeholder="Username"
                    hint="At least 8 characters"
                    v-model="username"
                />

                <v-text-field 
                    required
                    outlined 
                    min="8"
                    type="password"
                    label="Password"
                    placeholder="Password"
                    hint="At least 8 characters"
                    v-model="password"
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
                    Log In
                </v-btn>

                <h5 
                    style="font-size: 14px"
                    class="my-8 grey--text"
                >
                    Or
                </h5>

                <v-btn 
                    style="font-size: 18px; border: 2px solid black"
                    class="rounded-0 text-capitalize font-weight-bold"
                    v-on:click="register()"
                >
                    Register
                </v-btn>
            </v-container>
        </v-responsive>

        <v-responsive>
            <v-img
                src="@/assets/welcome.svg"
                max-height="700px"
                lazy
            />
        </v-responsive>
    </v-container>
</template>

<script>
import ref from 'vue'

export default {
    methods: {
        async logIn() {
            const contentType = 'application/json'

            const res = await fetch('http://localhost:4000/api/login', {
                method: 'POST',
                headers: {
                    Accept: contentType,
                    'Content-Type': contentType
                },
                body: JSON.stringify({
                    identifier: this.username,
                    password: this.password                
                })
            })

            if (!res.ok) {
                console.log('res:', res)
            } else {
                this.$router.push('/user/dashboard')
            }
        },
        register() {
            this.$router.push('/register')
        }
    },
    setup() {
        const { email, username } = ref('')

        return {
            email,
            username
        }
    },
    data() {
        return {
            username: this.username,
            password: this.password
        }
    }
}
</script>

