<template>
  <header-vue />
  <nav-bar-vue />
  <mascot-modal-vue />
  <div class="pink-container">
   <div id="message-bar" :class="'message-' + $store.state.message.level" :title="$store.state.message.text">
      {{ $store.state.message.text }}
    </div>
    <div class="cake-details-container">
      <h6>{{ cake.cakeName }}</h6>
      <tr>
        <td colspan="2" class="cakeImage">
          <img :src="cake.imageName" alt="Product photo" />
        </td>
        <div class="cart">
          <img src="../assets/InStockIcons/addToCart.png" alt="Cart Icon" class="icon action"
            v-on:click="addToCart(cake)" title="Add cake to cart" />
        </div>
      </tr>

      <h4>Details</h4>

      <table class="cake-details-table">
        <tr>
          <td>Price:</td>
          <td>{{ currency(cake.price) }}</td>
        </tr>
        <tr>
          <td>Cake Name:</td>
          <td>{{ cake.cakeName }}</td>
        </tr>
        <tr>
          <td>Style:</td>
          <td>{{ cake.cakeStyle }}</td>
        </tr>
        <tr>
          <td>Flavor:</td>
          <td>{{ cake.cakeFlavor }}</td>
        </tr>
        <tr>
          <td>Frosting:</td>
          <td>{{ cake.cakeFrosting }}</td>
        </tr>
        <tr>
          <td>Filling:</td>
          <td>{{ cake.cakeFilling }}</td>
        </tr>
        <tr>
          <td>Size:</td>
          <td>{{ cake.cakeSize }}</td>
        </tr>
        <tr>
          <td>Available:</td>
          <td>{{ cake.amountAvailable }}</td>
        </tr>
        <tr>
          <td colspan="2">
            <div class="custom-text">
              <input type="checkbox" v-model="cake.hasWriting" /> Optional Writing (no extra charge)
              <div v-if="cake.hasWriting">
                <input type="text" v-model="cake.customText" placeholder="Enter custom text" />
              </div>
            </div>
          </td>
        </tr>
      </table>
    </div>
  </div>
  <footer-vue />
</template>

<script>
import HeaderVue from "../components/Header.vue";
import NavBarVue from "../components/Navbar.vue";
import MascotModalVue from "../components/MascotModal.vue";
import inStockService from "../services/InStockService.js";
import cartService from "../services/CartService";
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
.pink-container {
  background-color: #FBECEB;
  padding: 10px;
  border-radius: 8px;
  max-width: 800px;
  margin: 20px auto;
  text-align: center;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

div.cart img {
  height: 50px;
  padding: 75px;
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
  background-color: transparent;
  position: sticky;
  bottom: 0px;
  align-items: flex-start;
  justify-content: center;
  padding: 10px;
}

.cake-details-container {
  margin: 0 auto; /* Removed extra top and bottom margin */
  max-width: 700px;
  text-align: left;
  padding-top: 5px; /* Reduced top padding */
  padding-bottom: 5px; /* Reduced bottom padding */
}

.cake-details-table {
  width: 100%;
  border-collapse: collapse;
  background-color: transparent;
  margin: 10px auto; /* Adjusted margin to keep table centered */
}

.cake-details-table td {
  border: 1px solid #8C3F09;
  padding: 5px;
  text-align: center;
}
.cakeImage {
  width: 100%;
  box-shadow: 0 2px 10px #8C3F09;
}

h6 {
  font-family: "Petit Formal Script", cursive;
  font-size: 2rem;
  color: #4B1202;
  text-align: left;
}
</style>