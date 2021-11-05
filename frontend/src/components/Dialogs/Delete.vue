<template>
    <div class="text-center">
        <v-dialog v-model="dialogDelete" width="30%">
            <template v-slot:activator="{ on, attrs }">
                <v-btn elevation="2" x-small color="error" v-bind="attrs" v-on="on">Delete</v-btn>
            </template>
            <v-card>
                <v-card-title v-if="type == 'userType'"
                    class="text-h6 lighten-2"
                >Are you sure you want to delete {{ object.firstname }} {{ object.lastname }} ?
                </v-card-title>
                <v-card-title v-if="type == 'teamType'"
                    class="text-h6 lighten-2"
                >Are you sure you want to delete {{ object.title }} ?
                </v-card-title>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text @click="dialogDelete = false">YES</v-btn>
                    <v-btn color="error" text @click="dialogDelete = false">NO</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
export default {
    name: "Delete",
    props: ["object", "type"],
    data() {
        return {
            dialogDelete: false,
        };
    },
};
</script>