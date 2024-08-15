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
      <div class="cake-content">
        <div class="cakeImage">
          <img :src="cake.imageName" alt="Product photo" />
        </div>
        <div class="cake-description">
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
          </table>
          <div class="custom-text">
            <label>
              <input type="checkbox" v-model="cake.hasWriting" /> Optional Writing (no extra charge)
            </label>
            <textarea
              v-if="cake.hasWriting"
              v-model="cake.customText"
              placeholder="Enter custom text"
              class="custom-text-input"
            ></textarea>
          </div>
          <div class="cart">
            <img src="../assets/InStockIcons/addToCart.png" alt="Cart Icon" class="icon action"
              v-on:click="addToCart(cake)" title="Add cake to cart" />
          </div>
        </div>
      </div>
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
      if (this.cake.hasWriting && this.cake.customText) {
        this.cake.cakeType = 'Custom';
      } else {
        this.cake.hasWriting = false;
        this.cake.cakeType = 'Standard';
      }
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
  padding: 20px;
  border-radius: 10px;
  max-width: 800px;
  margin: 20px auto;
  text-align: center;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

.cake-content {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: space-between;
}

.cakeImage {
  flex: 1;
  text-align: center;
  margin-top: 60px;
}

.cakeImage img {
  max-width: 100%;
  height: auto;
  border-radius: 5px;
  box-shadow: 0 2px 10px #8C3F09;
}

.cake-description {
  flex: 1;
  padding-left: 20px;
  text-align: left;
}

.cake-details-table {
  width: 100%;
  border-collapse: collapse;
  background-color: transparent;
  margin-top: 10px;
}

.cake-details-table td {
  border: 1px solid #8C3F09;
  padding: 10px;
  text-align: left;
}

h6 {
  font-family: "Petit Formal Script", cursive;
  font-size: 2rem;
  color: #4B1202;
  text-align: center;
}

.custom-text {
  margin-top: 15px;
}

.custom-text-input {
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #8C3F09;
  margin-top: 10px;
  font-size: 1rem;
}

.cart {
  margin-top: 20px;
  text-align: center;
}

.cart img {
  height: 50px;
  cursor: pointer;
}
</style>
