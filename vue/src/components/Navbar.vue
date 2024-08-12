<template>
<div id="nav">
        <router-link v-bind:to="{ name: 'home' }"><img src="/src/assets/navIcons/home.png"> Home</router-link>
        <router-link v-bind:to="{ name: 'instock' }"><img src="/src/assets/navIcons/inStock.png"> In-Stock</router-link>
        <!-- Conditionally display the Orders link if the user is an admin -->
        <router-link v-if="isAdmin" v-bind:to="{ name: 'orders' }"><img src="/src/assets/navIcons/custom.png"> Orders</router-link>
        <router-link v-bind:to="{ name: 'customorder' }"><img src="/src/assets/navIcons/custom.png"> Custom Order</router-link>
        <router-link v-bind:to="{ name: 'contactus' }"><img src="/src/assets/navIcons/contactus.png"> Contact Us</router-link>
        <router-link v-bind:to="{ name: 'reviews' }"><img src="/src/assets/navIcons/reviews.png"> Reviews</router-link>
</div>

</template>

<script>
import { mapState } from 'vuex';

export default {
  name: "NavBarVue",
  computed: {
    ...mapState({
      // Updated to check the authorities array
      isAdmin: state => state.user.authorities && state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')
    })
  }
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
    font-size: large;
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

@media (max-width: 600px) {
    #nav {
        flex-direction: column;
    }
}
</style>

