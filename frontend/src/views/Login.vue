<template>
  <v-container
    fill-height
    class="d-flex flex-row mr-12 align-center justify-center"
  >
    <v-responsive
      style="
        border: 1px solid;
        border-color: #a7a7a7;
        background-color: white;
        font-size: 42px;
      "
      class="rounded-lg pa-8 blue--text text-center"
      max-width="350px"
      min-width="250px"
    >
      <h3 style="line-height: 1" class="mb-8">Time Manager</h3>

      <v-divider style="background-color: #becdda" />

      <v-form class="py-4" v-model="isFormValid">
        <v-text-field
          required
          outlined
          autofocus
          min="8"
          label="Username"
          placeholder="Username"
          v-model="identifier"
          :rules="[usernameRules]"
        />

        <v-text-field
          required
          outlined
          min="8"
          type="password"
          label="Password"
          placeholder="Password"
          v-model="password"
          :rules="[passwordRules]"
        />

        <p style="font-size: 16px" class="red--text font-weight-bold">
          {{ this.error }}
        </p>
      </v-form>

      <v-divider style="background-color: #becdda" />

      <v-form class="d-flex flex-column justify-space-between">
        <v-btn
          block
          color="primary"
          style="font-size: 18px; border: 2px solid black !important"
          class="font-weight-bold rounded-0 text-capitalize"
          v-on:click="logIn()"
          :disabled="!isFormValid"
        >
          Log In
        </v-btn>

        <h5 style="font-size: 14px" class="my-8 grey--text">Or</h5>

        <v-btn
          style="font-size: 18px; border: 2px solid black"
          class="rounded-0 text-capitalize font-weight-bold"
          v-on:click="register()"
        >
          Register
        </v-btn>
      </v-form>
    </v-responsive>

    <v-responsive class="d-flex justify-center">
      <v-img
        src="@/assets/welcome.svg"
        max-height="700px"
        max-width="800px"
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
            const contentType = 'application/x-www-form-urlencoded'

            var urlencoded = new URLSearchParams()
            urlencoded.append("identifier", this.identifier)
            urlencoded.append("password", this.password)

            const res = await fetch('http://localhost:4000/api/login', {
                method: 'POST',
                headers: {
                    'Content-Type': contentType
                },
                body: urlencoded
            })

            if (!res.ok) {
                switch (res.status) {
                    case 403:
                        this.error = "Identifiants incorrects."
                        break;

                    default:
                        this.error = "Erreur lors de l'identification."
                        break;
                }
            } else {
              const result = await res.json()

              this.$store.commit('setTokens', {
                access_token: result.access_token, 
                refresh_token: result.refresh_token
              })
              this.$store.commit('setUser', {
                user: result.user
              })

              this.$router.push(`/user/${result.user.id}/dashboard`)
            }
        },
        register() {
            this.$router.push('/register')
        }
    },
    setup() {
        const { email, identifier } = ref('')

        return {
            email,
            identifier
        }
    },
    data() {
        return {
            error: '',
            identifier: this.identifier,
            password: this.password,
            isFormValid: false,
        }
    },
    computed: {
        passwordRules() {
            return (
                () => (v) => !!v || "Password is required",
                (v) =>
                (v && v.length >= 8) || "Password must have at least 8 characters"
            );
        },
        usernameRules() {
            return (
                () => (v) => !!v || "Username is required",
                (v) => (v && !!v.trim()) || "Value cannot be blank"
            );
        },
    }
}
</script>
