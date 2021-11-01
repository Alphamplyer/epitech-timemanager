<template>
    <div id="navbar">
        <ul>
            <li>
                <router-link class="button" to="/"> Home </router-link>
            </li>
            <li>
                <router-link class="button" to="/"> 1 </router-link>
            </li>
            <li>
                <router-link class="button" to="/"> 2 </router-link>
            </li>
            <li>
                <router-link class="button" to="/"> 3 </router-link>
            </li>
            <li>
                <router-link class="button" to="/me"> Account </router-link>
            </li>
        </ul>
    </div>
</template>

<script></script>

<style scoped>
#navbar {
    position: fixed;
    width: 40%;
    margin: auto;
    left: 0;
    right: 0;
    bottom: 0;
}

.button {
    background-color: grey;
    border-radius: 10px;
    width: 5vw;
    height: 5vw;
    display: inline-block;
    margin: 1%;
}

.button.router-link-active {
    background-color: green;
}

ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

li {
    display: inline;
}

img {
    margin: auto;
    transform: translateY(25%);
}
</style>
