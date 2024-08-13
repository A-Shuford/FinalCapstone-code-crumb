<template>
  <table>
    <thead>
      <tr>
        <th>Quantity in Stock</th>
        <th>Cake Name</th>
        <th>Price</th>
        <th>Add to Cart</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="cake in cakes" :key="cake.cakeId">
        <td class="action" @click="details(cake.cakeId)">
          {{ cake.cakeId }}
        </td>
        <td class="action" @click="details(cake.cakeId)">
          {{ cake.cakeName }}
        </td>
        <td class="right">
          {{ currency(cake.price) }}
        </td>
        <td>
          <img src="../assets/InStockIcons/addToCart.png" alt="Cart Icon" class="icon action" @click="addToCart(cake)" title="Add cake to cart">
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
      return this.$store.state.token.length === 0;
    },
  },

  methods: {
    currency(value) {
      return new Intl.NumberFormat(`en-US`, {
        currency: `USD`,
        style: "currency",
      }).format(value);
    },

    addToCart(cake) {
      this.isLoading = true;
      cartService
        .addCake(cake)
        .then(() => {
          this.$store.commit("SET_SUCCESS", `Added '${cake.cakeName}' to cart`);
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
      this.$router.push({ name: "inStockDetails", params: { id: id} });
    },
  },
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  background-color: transparent;
}

th, td {
  padding: 10px;
  text-align: center;
}

th {
  border-bottom: 2px solid black;
}

td {
  text-align: center;
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

img {
  height: 20px;
}
</style>
