<template>
    <header-vue />
    <nav-bar-vue />
    <section id="instock-cards">
      <article
        class="instock-card"
        v-for="cake in cakes"
        v-bind:key="cake.cakeId"
      >
      <div class="id">{{ cake.cakeId }}</div>
        <div class="price">{{ currency(cake.price) }}</div>
        <div class="cake-name action" v-on:click="details(cake.cakeId)">
          {{ cake.cakeName }}
          {{ cake.amountAvailable }}
        </div>
      
        <div class="cake-image">
          <img v-bind:src="cake.imageName" />
        </div>
        <div class = "update" v-bind:to=" 'UpdatedAvailability'">
      <td><label for="updateAvailable">Amount Available:</label></td>
                    <td>
                        <select v-model="cake.amountAvailable" id="cake.amountAvailable" required>
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                    </td>
                    <img src="/public/update_icon.jpg" alt="Update Icon" class="icon action" v-on:click="updateAvailability(cake)" title="Update Availability" />
                    </div>
                  </article>
    </section>
    <footer-vue />
  </template>

<script>
import inStock from "../services/InStockService.js";
import InstockTable from "../components/InstockTable.vue";
import InstockCards from "../components/InstockCards.vue";
import HeaderVue from "../components/Header.vue";
import NavBarVue from "../components/Navbar.vue";
import MascotModalVue from "../components/MascotModal.vue";
import FooterVue from "../components/Footer.vue";

export default {
  components: {
    InstockTable,
    InstockCards,
    HeaderVue,
    NavBarVue,
    MascotModalVue,
    FooterVue,
  },
  
  data() {
    return {
      cakes: [],
      cardView: true,
    };
  },
  methods :{
    getCakes (){
        inStock.getCakesAdmin().then((response) => {
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
        return new Intl.NumberFormat(`en-US`, {
          currency: `USD`,
          style: "currency",
        }).format(value);
      },
      updateAvailability(){
        inStock.updateCakeAmountAvailableFromAdminStock(this.cake).then(() => {
          this.$store.commit(
            "SET_SUCCESS",
            `Updated '${this.cake.cakeName}' availability`
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
        this.$router.push({ name: "inStockDetails", params: { id: id} });
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