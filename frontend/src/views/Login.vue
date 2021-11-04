<template>
    <v-container fill-height class="d-flex align-center justify-center">
        <v-responsive 
            style="border: 1px solid; border-color: #becdda; background-color: white"
            class="rounded-lg pa-12 text-h3 blue--text text-center"
            max-width="350px" 
            min-width="250px"
        >
            <h3 class="mb-8">Time Manager</h3>
            <v-divider style="background-color: #becdda" />
            <v-container class="my-4">
                <v-text-field 
                    solo
                    label="Username"
                />
                <v-text-field solo label="Password" />
            </v-container>
            <v-divider style="background-color: #becdda" />
            <v-btn 
                block
                color="primary"
                class="mt-8"
            >
                Log In
            </v-btn>
            <h5 class="text-h6 thin">Register</h5>
        </v-responsive>
    </v-container>
</template>

<script>

export default {
    setup() {
        
    },
}
</script>

