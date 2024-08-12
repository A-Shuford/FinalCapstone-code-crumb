<template>
  <header-vue />
  <nav-bar-vue />
  <section id="instock-cards">
    <article
      class="instock-card"
      v-for="cake in cakes"
      :key="cake.cakeId"
    >
      <div class="id">{{ cake.cakeId }}</div>
      <div class="price">{{ currency(cake.price) }}</div>
      <div class="cake-name action" @click="details(cake.cakeId)">
        {{ cake.cakeName }} (Available: {{ cake.amountAvailable }})
      </div>
      <div class="cake-image">
        <img :src="cake.imageName" alt="Cake Image" />
      </div>
      <div class="update">
        <label for="updateAvailable">Amount Available:</label>
        <select v-model="cake.amountAvailable" required>
          <option value="0">0</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
        <img
          src="/public/update_icon.jpg"
          alt="Update Icon"
          class="icon action"
          @click="updateAvailability(cake)"
          title="Update Availability"
        />
      </div>
    </article>
  </section>
  <footer-vue />
</template>

<script>
import inStock from "../services/InStockService.js";
import HeaderVue from "../components/Header.vue";
import NavBarVue from "../components/Navbar.vue";
import FooterVue from "../components/Footer.vue";

export default {
  components: {
    HeaderVue,
    NavBarVue,
    FooterVue,
  },

  data() {
    return {
      cakes: [],
      cardView: true,
    };
  },

  methods: {
    getCakes() {
      inStock
        .getCakesAdmin()
        .then((response) => {
          this.cakes = response.data;
        })
        .catch(this.handleError("Getting in-stock cakes was unsuccessful"));
    },

    handleError(baseMessage) {
      return (error) => {
        const response = error.response;
        const message =
          baseMessage + ": " + (response ? response.message : "Could not reach server");
        this.$store.commit("SET_ERROR", message);
        console.error(message);
      };
    },

    currency(value) {
      return new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
      }).format(value);
    },

    updateAvailability(cake) {
      inStock
        .updateCakeAmountAvailableFromAdminStock(cake)
        .then(() => {
          this.$store.commit(
            "SET_SUCCESS",
            `Updated '${cake.cakeName}' availability`
          );
        })
        .catch((error) => {
          const response = error.response;
          const message =
            "Unable to update cake amount: " +
            (response ? response.message : "Could not reach server");
          this.$store.commit("SET_ERROR", message);
          console.error(message);
        });
    },

    details(id) {
      this.$router.push({ name: "inStockDetails", params: { id: id } });
    },
  },

  created() {
    this.getCakes();
  },
};
</script>

<style scoped>
div.update img {
  height: 30px;
}
</style>
