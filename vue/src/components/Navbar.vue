<template>
    <div id="nav">
        <router-link v-bind:to="{ name: 'home' }"><img src="/src/assets/navIcons/home.png"> Home</router-link>
        <router-link v-bind:to="{ name: 'instock' }"><img src="/src/assets/navIcons/inStock.png"> In-Stock</router-link>
        <router-link v-bind:to="{ name: 'customorder' }"><img src="/src/assets/navIcons/custom.png"> Custom Order</router-link>
        <router-link v-bind:to="{ name: 'contactus' }"><img src="/src/assets/navIcons/contactus.png"> Contact Us</router-link>
        <router-link v-bind:to="{ name: 'reviews' }"><img src="/src/assets/navIcons/reviews.png"> Reviews</router-link>
    </div>

</template>

<script>
export default {
    name: "NavBarVue",
};
</script>

<style scoped>
#nav {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    background-color: #D4A373;
    padding: 10px 0;
}

#nav a {
    font-size: x-large;
    font-weight: bold;
    display: flex;
    align-items: center;
    color: #FBECEB;
    text-decoration: none;
}

#nav img {
    width: 25px;
    height: 25px;
    margin-right: 5px;
}
</style>
