<template>
    <header-vue />
    <nav-bar-vue />
    <section id="instock-cards">
      <article
        class="instock-card"
        v-for="cake in cakes"
        v-bind:key="cake.cakeId"
      >
      <div class="id">{{ cake.cakeId }}</div>
        <div class="price">{{ currency(cake.price) }}</div>
        <div class="cake-name action" v-on:click="details(cake.cakeId)">
          {{ cake.cakeName }}
        </div>
      
        <div class="cake-image">
          <img v-bind:src="cake.imageName" />
        </div>
        
        <div class="cart">
          <img src="../assets/InStockIcons/addToCart.png" alt="Cart Icon"  class="icon action" v-on:click="addToCart(cake)" title="Add cake to cart">
        </div>
        
      </article>
    </section>
    <footer-vue />
  </template>

<script>
import inStock from "../services/InStockService.js";
import InstockTable from "../components/InstockTable.vue";
import InstockCards from "../components/InstockCards.vue";
import HeaderVue from "../components/Header.vue";
import NavBarVue from "../components/Navbar.vue";
import MascotModalVue from "../components/MascotModal.vue";
import FooterVue from "../components/Footer.vue";

export default {
  components: {
    InstockTable,
    InstockCards,
    HeaderVue,
    NavBarVue,
    MascotModalVue,
    FooterVue,
  },
  
  data() {
    return {
      cakes: [],
      filter: "",
      cardView: true,
    };
  },
  methods :{
    getCakes (){
        inStock.getCakesAdmin().then((response) => {
          this.cakes = response.data;
        })
        .catch(this.handleError("Getting in-stock cakes was unsuccessful"));
    },
    handleError(baseMessage) {
      return (error) => {
        const response = error.response;
        const message =
          baseMessage + ": " + (response ? response.message : "Could not reach server");
        this.$store.commit("SET_ERROR", message);
        console.error(message);
      };
    },


  },
};
</script>