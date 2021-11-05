<template>
<div id="secondrow" class="customRow">
    <div class="user" v-for="object in objects" :key="object.id">
        <div v-if="type == 'teamType'">
            <Team v-bind:object="object" />
        </div>
        <div v-if="type == 'userType'">
            <User v-bind:object="object" />
        </div>
    </div>
</div>
</template>

<script>
import User from "./User.vue";
import Team from "./Team.vue";

export default {
  name: "Grid",
  components: { User, Team },
  props: ['objects', 'type'],
};

</script>

<style>

</style>