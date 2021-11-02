<template>
    <div id="content">
        <UserBar />

        <div id="cells">
            <div id="row">
                <Cell title="test" />
                <Cell title="test2" />
                <Cell title="test3" />
            </div>

            <div id="row">
                <Cell title="test4" />
            </div>
        </div>
    </div>
</template>

<script>
import Cell from '../components/Cell.vue'
import UserBar from '../components/UserBar.vue'

export default {
    name: 'Dashboard',
    components: {
        UserBar,
        Cell,
    },
}
</script>

<style>
#content {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
}

#cells {
    justify-content: center;
}

#row {
    display: flex;
    width: 100%;
    flex-direction: row;
}
</style>
