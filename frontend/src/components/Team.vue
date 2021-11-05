<template>
<v-card
    elevation="2"
    outlined
    style="width: 200px; height: 33%;">
    <v-card-title style="justify-content: center;" class="cursor-pointer">{{ object.title }}</v-card-title>
    <v-row
    style="justify-content: center;">
        <v-card-actions>
            <v-btn
            elevation="2"
            x-small>
                Users
            </v-btn>
        </v-card-actions>
        <v-card-actions>
            <v-btn
            elevation="2"
            x-small
            color="error">
                Delete
            </v-btn>
        </v-card-actions>
    </v-row>
</v-card>
</template>

<script>
export default {
    name: "Team",
    props: ['object'],
}
</script>

<style>

</style>