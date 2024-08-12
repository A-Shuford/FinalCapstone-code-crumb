<template>
  <header-vue />
  <nav-bar-vue />
  <mascot-modal-vue />

  <div id="cart-container">
    <h1>Your Shopping Cart</h1>

    <table id="cart-table">
      <thead>
        <tr>
          <th class="right">Qty</th>
          <th>Cake</th>
          <th class="right">Price</th>
          <th class="right">Amount</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in cart.items" v-bind:key="item.cartItemId">
          <td class="right">{{ item.quantity }}</td>
          <td class="action">
            <router-link v-bind:to="{
              name: 'inStockDetails',
              params: { id: item.cake.cakeId },
            }">
              {{ item.cake.cakeName }}
            </router-link>
          </td>
          <td class="right">{{ currency(item.cake.price) }}</td>
          <td class="right">{{ currency(item.cake.price * item.quantity) }}</td>
          <td class="actionRemove">
            <img src="../assets/InStockIcons/removeItemIcon.jpg" alt="Cart Icon" class="icon action"
              v-on:click="removeCake(cake)" title="Remove cake from cart">
          </td>
        </tr>

        <tr class="begin-summary">
          <td></td>
          <td style="border-top: 1px solid black">Item subtotal:</td>
          <td></td>
          <td class="right">{{ currency(cart.itemSubtotal) }}</td>
          <td></td>
        </tr>
        <tr class="summary">
          <td></td>
          <td>Tax:</td>
          <td></td>
          <td class="right">{{ currency(cart.tax) }}</td>
          <td></td>
        </tr>
        <tr class="summary">
          <td></td>
          <td>Total:</td>
          <td></td>
          <td class="right">{{ currency(cart.total) }}</td>
          <td></td>
        </tr>

        <!-- Pick Up Date and Time Section -->
        <tr>
          <td colspan="4" class="centered">
            <h2>Select Pick Up Date and Time</h2>
          </td>
        </tr>
        <tr>
          <td colspan="4" class="centered">
            <div class="date-time-fields">
              <label for="pickup_date">Pick Up Date:</label>
              <input type="date" v-model="pickup_date" id="pickup_date" required>
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="4" class="centered">
            <div class="date-time-fields">
              <label for="pickup_time">Pick Up Time:</label>
              <input type="time" v-model="pickup_time" id="pickup_time" required>
            </div>
          </td>
          </tr>
          <tr>

                    <td colspan="2">
                        <button type="submit" @click="submitOrder">Submit</button>
                    </td>
                </tr>
      </tbody>
    </table>
  </div>

  <footer-vue />
</template>

<script>
import HeaderVue from '../components/Header.vue';
import NavBarVue from '../components/Navbar.vue';
import MascotModalVue from '../components/MascotModal.vue';
import FooterVue from '../components/Footer.vue';
import cartService from '../services/CartService';

export default {
  name: "CartItemView",
  components: {
    HeaderVue,
    NavBarVue,
    MascotModalVue,
    FooterVue
  },
  data() {
    return {
      cart: {},
    };
  },
  methods: {
    getCart() {
      cartService.getCart()
        .then((response) => {
          this.cart = response.data;
        })
        .catch((error) => {
          this.isLoading = false;
          const response = error.response;
          const message =
            "Getting cart was unsuccessful: " +
            (response ? response.message : "Could not reach server");
          this.$store.commit("SET_ERROR", message);
          console.error(message);
        });
    },
    removeCake(itemId) {
      cartService.deleteItem(itemId).then(() => {
        this.getCart();
      })
        .catch((error) => {
          const response = error.response;
          const message =
            "Removing item from cart was unsuccessful: " +
            (response ? response.message : "Could not reach server");
          this.$store.commit("SET_ERROR", message);
          console.error(message);
        });
    },
    clearCart() {
      cartService.clearCart.then(() => {
        this.getCart();
      })
        .catch((error) => {
          this.isLoading = false;
          const response = error.response;
          const message =
            "Clear cart was unsuccessful: " +
            (response ? response.message : "Could not reach server");
          this.$store.commit("SET_ERROR", message);
          console.error(message);
        });
    },
    currency(value) {
      if (isNaN(value)) return "";
      return new Intl.NumberFormat(`en-US`, {
        currency: `USD`,
        style: "currency",
      }).format(value);
    },
  },
  created() {
    this.getCart();
  },
};
</script>

<style scoped>
#cart-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 0px;
  background-color: #FBECEB;
  /* Pink background */
  border-radius: 8px;
  /* Rounded corners */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  /* Light shadow for emphasis */
}

#cart-container h1 {
  text-align: center;
  margin-bottom: 20px;
  font-size: 2rem;
  color: #333;
}

table#cart-table {
  width: 100%;
  /* Make the table take the full width of the container */
  border-collapse: collapse;
}

th,
td {
  padding: 10px;
  text-align: left;
}

td.centered {
  text-align: center;
}

.date-time-fields {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.date-time-fields label,
.date-time-fields input {
  display: block;
  margin: 5px 0;
}

tr.summary {
  font-weight: 600;
}

tr.begin-summary>td {
  border-top: 1px solid black;
}

.right {
  text-align: right;
}

.action,
.delete-action {
  cursor: pointer;
}

.action>a {
  text-decoration: none;
  color: inherit;
}

.icon.delete-action {
  font-size: 1.2em;
  color: #444;
}

.delete-action:hover,
#clear-cart:hover {
  color: red;
  background-color: rgba(0, 0, 0, 0.1);
}

.action:hover {
  color: blue;
  background-color: rgba(0, 0, 0, 0.1);
}

.actions {
  padding-left: 20px;
}

.actionRemove img {
  height: 20px;
}


button {
    background-color: #8C3F09;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    display: block;
    margin: 20px auto 0;
    width: 100px;
  
}

button:hover {
    background-color: #921A39;
}
</style>
