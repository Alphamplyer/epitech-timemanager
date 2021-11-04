<template>
    <div id="users">
        <div id="firstrow" class="row">
            <h1 class="usersTitle">All Users</h1>
        </div>
        <div id="secondrow" class="row">
            secondrow
        </div>
        <div id="thirdrow" class="row">
            thirdrow
        </div>
    </div>
</template>

<style>

#users {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: 0.5fr 1fr 1fr;
    column-gap: 10px;
    row-gap: 10px;
    height: 100%;
    background-color: #DDDDE6;
}

.row {
    margin: 20px;
    box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12);
}

.usersTitle {
    font-size: xxx-large;
    margin-left: 2%;
}

#firstrow {
    grid-column: 1 / 3;
    grid-row: 1;
    background-color: #FFFFFF;
}

#secondrow {
    grid-column: 1 / 3;
    grid-row: 2;
    background-color: #FFFFFF;
}

#thirdrow {
    grid-column: 1 / 3;
    grid-row: 3;
    background-color: #FFFFFF;
}

</style>