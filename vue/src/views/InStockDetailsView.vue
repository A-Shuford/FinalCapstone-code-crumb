<template>
  <header-vue />
  <nav-bar-vue />
  <mascot-modal-vue />
    <div class="home">
      <div id="heading-line">
        <h1>
          {{ cake.cakeName }}
        </h1>
        <!--
           id="add-cart"
          title="Add to shopping cart"
          v-if="isLoggedIn && product"
          v-on:click="addToCart"
        >
          <font-awesome-icon class="icon action" icon="fa-solid fa-cart-plus" />
          Add to Cart
        </button> -->
      </div>
      <h2>Details</h2>
      <p>{{ cake.price }}</p>
      <p>{{ cake.cakeName }}</p>
      <img v-bind:src="cake.imageName" alt="Product photo" />
      </div>
    <footer-vue />
</template>


<script>
  import inStockService from "../services/InStockService.js";
  import customService from "../services/CustomService.js";
  import cartService from "../services/CartService";
  //importing the components for header and footer
  import HeaderVue from "../components/Header.vue";
  import NavBarVue from "../components/Navbar.vue";
  import MascotModalVue from "../components/MascotModal.vue";
  import FooterVue from "../components/Footer.vue";

  //import LoadingSpinner from "../components/LoadingSpinner.vue";
  export default {
   components:{
    HeaderVue,
    NavBarVue,
    MascotModalVue,
    FooterVue,
   },
    data() {
      return {
        cake: {},
      };
    }, 
    computed: {
      isLoggedIn() {
        return this.$store.state.token.length > 0;
      },
    },
    methods: {
      getCake(cakeId) {
        inStockService
          .getdetails(cakeId)
          .then((response) => {
            this.cake = response.data;
          })
          .catch((error) => {
            const response = error.response;
            const message =
              "Getting cake was unsuccessful: " +
              (response ? response.message : "Could not reach server");
            this.$store.commit("SET_ERROR", message);
            console.error(message);
          });
      },
  
      addToCart() {
        cartService
          .addProduct(this.cake)
          .then(() => {
            // SUCCESS
            this.$store.commit(
              "SET_SUCCESS",
              `Added '${this.cake.name}' to cart`
            );
          })
          .catch((error) => {
            const response = error.response;
            const message =
              "Add cake was unsuccessful: " +
              (response ? response.message : "Could not reach server");
            this.$store.commit("SET_ERROR", message);
            console.error(message);
          });
      },
    },
    created() {
      this.getCake(this.$route.params.id);
    },
  };

</script>
  
<style scoped>
  #heading-line {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;
  }
  
 /* #spinner {
    color: green;
  }*/
  
  #add-cart:hover {
    color: rgb(229, 18, 18);
    background-color: rgba(0, 0, 0, 0.1);
  }
  </style>