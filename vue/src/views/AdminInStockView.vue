<template>
  <header-vue />
  <nav-bar-vue />
  <section id="instock-cards">
    <article class="instock-card" v-for="cake in cakes" :key="cake.cakeId">
      <div class="cake-name action" @click="details(cake.cakeId)">
        {{ cake.cakeName }}
      </div>
      <div class="cake-content">
        <div class="cake-image">
          <img :src="cake.imageName" alt="Cake Image" />
        </div>
        <div class="cake-description">
          <h3>Cake Description</h3>
          <p>Price: {{ currency(cake.price) }}</p>
          <p>Style: {{ cake.cakeStyle }}</p>
          <p>Flavor: {{ cake.cakeFlavor }}</p>
          <p>Frosting: {{ cake.cakeFrosting }}</p>
          <p>Filling: {{ cake.cakeFilling }}</p>
          <p>Size: {{ cake.cakeSize }}</p>
          <p>(Available: {{ cake.amountAvailable }})</p>
          
          <!-- Update Availability Section -->
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
              src="/update_icon.jpg"
              alt="Update Icon"
              class="icon action"
              @click="updateAvailability(cake)"
              title="Update Availability"
            />
          </div>
        </div>
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
#instock-cards {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.instock-card {
  background-color: #FBECEB;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  width: 100%;
  max-width: 800px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

.cake-name {
  font-weight: bold;
  font-size: 1.5em; /* Larger font size for cake name */
  margin-bottom: 10px;
  text-align: center;
}

.cake-content {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: space-between;
}

.cake-image {
  flex: 1;
  text-align: center;
}

.cake-image img {
  max-width: 100%;
  height: auto;
  border-radius: 5px;
}

.cake-description {
  flex: 1;
  padding-left: 20px;
}

.cake-description h3 {
  font-size: 1.2em; /* Smaller heading for description */
  margin-bottom: 10px;
}

.update {
  display: flex;
  align-items: center;
  margin-top: 20px;
}

div.update img {
  height: 30px;
  margin-left: 10px;
}

p {
  margin: 5px 0;
}
</style>
