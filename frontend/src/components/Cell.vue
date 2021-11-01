<template>
    <div id="cell">
        <h1 id="title">
            {{ title }}
        </h1>
    </div>
</template>

<script>
export default {
    props: ['title']
}
</script>

<style>
#cell {
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
    min-height: 240px;
    justify-content: center;
    align-items: center;
    color: #2c3e50;
    margin: 6px;
    margin-inline: 12px;
    border: 4mm ridge rgba(170, 50, 220, 0.6);
    border-width: 0.25em;
    border-radius: 48px;
    border-color: #2c3e50;
    background-color: #dddde6;
    padding: 6px;
    justify-content: space-between;
}

#title {
    justify-content: start;
    background-color: #2c3e50;
    margin-top: 4px;
    padding: 8px;
    padding-inline: 20px;
    border-radius: 32px;
    color: orange;
    font-size: 28px;
}
</style>
