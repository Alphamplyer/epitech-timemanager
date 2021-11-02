<template>
    <div id="team">
        <img id="teamPic" alt="Team" src="../assets/team.svg" />

        <div>
            {{ this.team.title }}
        </div>
    </div>
</template>

<script>
export default {
    name: 'Team',
    props: ['team'],
}
</script>

<style>
#team {
    display: flex;
    flex-direction: column;
    align-items: center;
    color: #2c3e50;
    width: 200px;
    height: 200px;
    border-radius: 32px;
    background-color: #dddde6;
}

#teamPic {
    width: 90px;
}
</style>
