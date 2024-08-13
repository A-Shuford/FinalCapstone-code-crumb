<template>
  <header-vue />
  <nav-bar-vue />
  <mascot-modal-vue />

  <div id="cart-container">
    <h5>Your Shopping Cart</h5>

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
              <label for="cart.pickupDate">Pick Up Date:</label>
              <input type="date" v-model="cart.pickupDate" id="cart.pickupDate" required>
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="4" class="centered">
            <div class="date-time-fields">
              <label for="cart.pickupTime">Pick Up Time:</label>
              <input type="time" v-model="cart.pickupTime" id="cart.pickupTime" required>
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="4" class="submitCartOrder">
            <button id="submitCartOrder" @click="submitCartOrder">Submit your Orders</button>
          </td>
        </tr>
        <tr>
          <td colspan="4" class="clearCartOrder">
            <button id="clear-cart" @click="clearCart">Clear Cart</button>
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

      cart:{
        item:[
          {
          pickupDate: "",
          pickupTime: "",
          }
        ],

      },
      cart2: {
        item: [
          {
          pickupDate: "",
          pickupTime: "",
          }
        ],
      },
      storeHours: {
        open: 9, // Store opening hour (24-hour format)
        close: 18, // Store closing hour (24-hour format)
        closedDays: [1] // Store closed on Mondays (0 = Sunday, 1 = Monday, ..., 6 = Saturday)
      },
    };
  },
  methods: {
    getCart() {
      cartService.getCart()
        .then((response) => {
          this.cart = response.data;
          this.cart2 = this.cart;
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
    isStoreClosed(date) {
      // Parse the date string into a Date object
      const parsedDate = new Date(date + 'T00:00:00'); // Adding 'T00:00:00' to ensure it's treated as a full date
      
      // Extract the day of the week (0 = Sunday, 1 = Monday, ..., 6 = Saturday)
      const dayOfWeek = parsedDate.getDay();
      
      // Check if the store is closed on this day
      return this.storeHours.closedDays.includes(dayOfWeek);
    },
    checkTimeAndDate() {
      const pickupDateTime = new Date(`${this.cart.pickupDate} ${this.cart.pickupTime}`);
      const pickupHour = pickupDateTime.getHours();

      // Check if the selected pick-up date is on a closed day
      if (this.isStoreClosed(this.cart.pickupDate)) {
        alert('Sorry, the store is closed on the selected pick-up date. Please choose another day.');
        return false;
      }

      // Check if the selected pick-up time is within the store's opening hours
      if (pickupHour >= this.storeHours.open && pickupHour < this.storeHours.close) {
        // Store is open, proceed with the order submission
        alert('Your order has been submitted successfully!');
        return true;
      } else {
        // Store is closed, display an alert
        alert('Sorry, the store is currently closed. Please select a different pick-up time.');
        return false;
      }

      
    },

    async submitCartOrder(){
      if(this.checkTimeAndDate() == true){
         cartService.submitOrderForRevision(this.cart).then((response) => {
          this.cart = response.data;
        }).catch((error) => {
          this.isLoading = false;
          const response = error.response;
          const message =
            "Getting cart was unsuccessful: " +
            (response ? response.message : "Could not reach server");
          this.$store.commit("SET_ERROR", message);
          console.error(message);
        });
      }else{
        alert("Please select a pick up date and time")
      }
    },
  },
  created() {
    this.getCart();
  },
};
</script>

<style scoped>
#cart-container {
  margin: 20px auto;
    padding: 20px;
    background-color: #FBECEB;
    border-radius: 8px;
    max-width: 600px;
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
  /* Make button a block-level element */
  margin: 20px auto;
  /* Center button horizontally */
}


button:hover {
  background-color: #921A39;
}

h5{
    font-family: "Press Start 2P", system-ui;
    font-size: 1.5rem;
    color: #4B1202;
    text-align: center
}
</style>
