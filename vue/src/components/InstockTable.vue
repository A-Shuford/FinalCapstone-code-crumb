<template>
    <table>
      <thead>
        <tr>
          <th>Cake</th>
          <th class="right">Price</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="cake in cakes" v-bind:key="cake.cakeId">
          <td class="action" v-on:click="details(cake.cakeId)">
            {{ cake.cakeId }}
          </td>
          <td class="action" v-on:click="details(cake.cakeId)">
            {{ cake.cakeName }}
          </td>
          <td class="right">
            {{ currency(cake.price) }}
          </td>
          <td>
            <font-awesome-icon
              v-if="isLoggedIn"
              class="icon action"
              icon="fa-solid fa-cart-plus"
              v-on:click="addToCart(cake)"
              title="Add cake to cart"
            />
          </td>
        </tr>
      </tbody>
    </table>
  </template>
  
  <script>
  import cartService from "../services/CartService";
  export default {
    props: {
      cakes: [],
    },
  
    computed: {
      isLoggedIn() {
        return this.$store.state.token.length > 0;
      },
    },
  
    methods: {
      currency(value) {
        return new Intl.NumberFormat(`en-US`, {
          currency: `USD`,
          style: "currency",
        }).format(value);
      },
  
      addToCart(product) {
        this.isLoading = true;
        cartService
          .addItem(product)
          .then(() => {
            // SUCCESS
            this.$store.commit("SET_SUCCESS", `Added '${product.name}' to cart`);
            this.isLoading = false;
          })
          .catch((error) => {
            this.isLoading = false;
            const response = error.response;
            const message =
              "Add item was unsuccessful: " +
              (response ? response.message : "Could not reach server");
            this.$store.commit("SET_ERROR", message);
            console.error(message);
          });
      },
  
      details(id) {
        this.$router.push({ name: "InStockDetails", params: { id: id } });
      },
    },
  };
  </script>
  
  <style scoped>
  table {
    min-width: 500px;
  }
  th,
  td {
    padding: 2px 7px;
    text-align: left;
  }
  .right {
    text-align: right;
  }
  .action {
    cursor: pointer;
  }
  .icon.action {
    font-size: 1.2em;
    color: #444;
  }
  .action:hover {
    color: blue;
    background-color: rgba(0, 0, 0, 0.1);
  }
  </style>