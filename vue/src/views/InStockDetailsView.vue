<template>
  <header-vue />
  <nav-bar-vue />
  <mascot-modal-vue />
  <div class="home">
    <div id="heading-line">
      <h1>
        {{ cake.cakeName }}
      </h1>
    </div>
    <div
      id="message-bar"
      v-bind:class="'message-' + $store.state.message.level"
      v-bind:title="$store.state.message.text"
    >
      {{ $store.state.message.text }}
    </div>
    <h2>Details</h2>
    <p>Price: {{ currency(cake.price) }}</p>
    <p>Cake Name: {{ cake.cakeName }}</p>
    <p>Style: {{ cake.cakeStyle }}</p>
    <p>Flavor: {{ cake.cakeFlavor }}</p>
    <p>Frosting: {{ cake.cakeFrosting }}</p>
    <p>Filling: {{ cake.cakeFilling }}</p>
    <p>Size: {{ cake.cakeSize }}</p>
    <p>Available: {{ cake.amountAvailable }}</p>
    <img v-bind:src="cake.imageName" alt="Product photo" />
    <div class="custom-text">
      <input type="checkbox" v-model="cake.hasWriting" /> Optional Writing (no extra charge)
      <div v-if="cake.hasWriting">
        <input type="text" v-model="cake.customText" placeholder="Enter custom text" />
      </div>
    </div>
    <div class="cart">
      <img src="../assets/InStockIcons/addToCart.png" alt="Cart Icon" class="icon action" v-on:click="addToCart(cake)" title="Add cake to cart" />
    </div>
  </div>
  <footer-vue />
</template>

<script>
  import inStockService from "../services/InStockService.js";
  import cartService from "../services/CartService";
  import HeaderVue from "../components/Header.vue";
  import NavBarVue from "../components/Navbar.vue";
  import MascotModalVue from "../components/MascotModal.vue";
  import FooterVue from "../components/Footer.vue";

  export default {
    components: {
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
      isAdmin() {
        return this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN');
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
      currency(value) {
      return new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
      }).format(value);
    },
  
      addToCart() {
        cartService
          .addCake(this.cake)
          .then(() => {
            this.$store.commit(
              "SET_SUCCESS",
              `Added '${this.cake.cakeName}' to cart`
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

  div.cart img {
    height: 30px;
  }

  div.update img {
    height: 30px;
  }

  .action {
    cursor: pointer;
  }

  div.message-bar img {
    height: 20px;
    padding-right: 15px;
  }

  div.message-bar {
    display: flex;
    background-color: greenyellow;
    position: sticky;
    bottom: 0px;
    align-items: flex-start;
    justify-content: center;
    padding: 10px;
  }
</style>
