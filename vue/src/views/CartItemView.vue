<template>
    <header-vue />
    <nav-bar-vue />
    <mascot-modal-vue />
    <div id="heading-line">
      <h1>
        Your Shopping Cart!
    </h1>
    </div>
    <!-- 
      clear buttom must go here
    -->
    <table id="cart-table">
      <thead>
        <tr>
        <th class = "right"> Qty</th>
        <th> Product</th>
        <th class = "right"> Price</th>
        <th class = "right"> Amout</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="cake in cart.cakes" v-bind:key="cake.cartItemId">
          <td class = "right">
            {{ cake.quantity }}
          </td>

          <td class="action">
            <router-link v-bind:to="{
              name:'inStockDetails',
              params: { id: cake.cakeId },
            }">
          {{ cake.cakeName }}  
          </router-link>
          </td>
          <td class = "right">
            {{ currency(cake.price) }}
          </td>
          <td class = "right">
            {{ currency(cake.price * cake.quantity) }}
          </td>
          <td class="actions">
            <img src="../assets/InStockIcons/removeItemIcon.jpg" alt="Cart Icon"  class="icon action" v-on:click="addToCart(cake)" title="Add cake to cart">
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





      </tbody>
    </table>


<footer-vue />
</template>

<script>

import HeaderVue from '../components/Header.vue';
import NavBarVue from '../components/Navbar.vue';
import MascotModalVue from '../components/MascotModal.vue'; 
import FooterVue from '../components/Footer.vue';
import cartService from '../services/CartService';

export default {
  name: "HomeView",
  components: {
    HeaderVue,
    NavBarVue,
    MascotModalVue,
    FooterVue
  },

  data(){
    return{
      cart: {},
    };
  },

  methods:{
    getCart(){
      cartService.getCart()
      .then((response) => {
        this.cart = response.data;
      })
      .catch((error) => {
          this.isLoading = false;
          // Something unexpected happened
          const response = error.response;
          const message =
            "Getting cart was unsuccessful: " +
            (response ? response.message : "Could not reach server");
          this.$store.commit("SET_ERROR", message);
          console.error(message);
      });
    },
    removeItem(itemId){
      cartService.deleteItem(itemId).then(()=>{
        this.getCart();
      })
      .catch((error) => {
          // Something unexpected happened
          const response = error.response;
          const message =
            "Removing item from cart was unsuccessful: " +
            (response ? response.message : "Could not reach server");
          this.$store.commit("SET_ERROR", message);
          console.error(message);
        });
    },
    clearCart(){
      cartService.clearCart.then(()=>{
        this.getCart();
      })
      .catch((error) => {
          this.isLoading = false;
          // Something unexpected happened
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

    created(){
      this.getCart();
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

#spinner {
  color: green;
}
table#cart-table {
  min-width: 500px;
  border-collapse: collapse;
}
th,
td {
  padding: 2px 7px;
  text-align: left;
}
tr.summary {
  font-weight: 600;
}
tr.begin-summary > td {
  border-top: 1px solid black;
}

.right {
  text-align: right;
}
.action,
.delete-action {
  cursor: pointer;
}
.action > a {
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
</style>