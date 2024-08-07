<template>
    <div>
      <div id="heading-line">
        <h1>
          {{ product.name }}
          <loading-spinner id="spinner" v-bind:spin="isLoading" />
        </h1>
        <button
          id="add-cart"
          title="Add to shopping cart"
          v-if="isLoggedIn && product"
          v-on:click="addToCart"
        >
          <font-awesome-icon class="icon action" icon="fa-solid fa-cart-plus" />
          Add to Cart
        </button>
      </div>
      <h2>Details</h2>
      <p>{{ product.price }}</p>
      <p>{{ cake.name }}</p>
      <img v-bind:src="product.imageName" alt="Product photo" />
      <p></p>
    </div>
  </template>
  
  <script>
  import InStockService from "../services/InStockService.js";
  import cartService from "../services/CartService";
  //import LoadingSpinner from "../components/LoadingSpinner.vue";
  export default {
   /*components: { LoadingSpinner },
    data() {
      return {
        product: {},
        isLoading: false,
      };
    }, */
    computed: {
      isLoggedIn() {
        return this.$store.state.token.length > 0;
      },
    },
    methods: {
      getCake(id) {
        this.isLoading = true;
        InStockService
          .getCakeById(id)
          .then((response) => {
            this.cake = response.data;
            this.isLoading = false;
          })
          .catch((error) => {
            this.isLoading = false;
            const response = error.response;
            const message =
              "Getting cake was unsuccessful: " +
              (response ? response.message : "Could not reach server");
            this.$store.commit("SET_ERROR", message);
            console.error(message);
          });
      },
  
      addToCart() {
        this.isLoading = true;
        cartService
          .addItem(this.cake)
          .then(() => {
            // SUCCESS
            this.$store.commit(
              "SET_SUCCESS",
              `Added '${this.cake.name}' to cart`
            );
            this.isLoading = false;
          })
          .catch((error) => {
            this.isLoading = false;
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