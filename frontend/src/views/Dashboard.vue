<template>
    <div id="content">
        <UserBar />

        <div id="cells">
            <div id="row">
                <Cell title="test" />
                <Switcher title="Working ?" />
            </div>

            <div id="row">
                <Cell title="test2" />
                <Cell title="test4" />
            </div>
        </div>
    </div>
</template>

<script>
import Cell from '../components/Cell.vue'
import Switcher from '../components/Switcher.vue'
import UserBar from '../components/UserBar.vue'

export default {
    name: 'Dashboard',
    components: {
        UserBar,
        Cell,
        Switcher,
    },
}
</script>

<style>
#content {
    display: flex;
    height: 100%;
    flex-direction: column;
    flex-wrap: wrap;
}

#cells {
    display: table;
    justify-content: center;
}

#row {
    display: flex;
    width: 100%;
    flex-direction: row;
}
</style>
