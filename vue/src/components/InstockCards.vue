<template>
  <section id="instock-cards">
    <div class="instock-card" v-for="cake in cakes" :key="cake.cakeId">
      <div class="cake-name action" @click="details(cake.cakeId)">
       <h3>{{ cake.cakeName }}</h3>
      </div>
      <!--put cake quantity here -->
      <div class="price"><h3>{{ currency(cake.price) }}</h3></div>
      <div class="cake-image" @click="details(cake.cakeId)"><img :src="cake.imageName" />
      </div>
      <div class="cart">
        <img 
          src="../assets/InStockIcons/addToCart.png" 
          alt="Cart Icon" 
          class="icon action" 
          @click="addToCart(cake)" 
          title="Add cake to cart" 
        />
      </div>
    </div>
  </section>
</template>

<script>
import cartService from "../services/CartService";

export default {
  props: {
    cakes: Array,
  },

  computed: {
    isLoggedIn() {
      return this.$store.state.token.length > 0;
    },
  },

  methods: {
    currency(value) {
      return new Intl.NumberFormat('en-US', {
        currency: 'USD',
        style: 'currency',
      }).format(value);
    },

    addToCart(cake) {
      this.isLoading = true;
      cartService.addCake(cake)
        .then(() => {
          this.$store.commit('SET_SUCCESS', `Added '${cake.cakeName}' to cart`);
          setTimeout(() => {
            this.$store.commit('CLEAR_SUCCESS');
          }, 5000);
        })
        .catch((error) => {
          this.isLoading = false;
          const response = error.response;
          const message = `Add cake was unsuccessful: ${response ? response.message : 'Could not reach server'}`;
          this.$store.commit('SET_ERROR', message);
          console.error(message);
        });
    },

    details(id) {
      this.$router.push({ name: 'inStockDetails', params: { id } });
    },
  },
};
</script>

<style scoped>
#instock-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  gap: 12px 20px;
}

.instock-card {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 700px;
  padding: 5px;
  background-color: rgba(255, 255, 255, 0.4);
  border: 1px solid rgb(248, 169, 250);
  border-radius: 10px;
}

.cake-name, .id, .price, .cake-image {
  text-align: center;
  margin-bottom: 10px;
}

.cake-name {
  cursor: pointer;
}

.cake-image img {
  max-width: 100%;
  border-radius: 5px;
}

.cart { 
  position: absolute;
  top: 5px;
  right: 5px;
}

.cart img {
  height: 60px;
}

.icon.action {
  font-size: 1.2em;
  color: #444;
}

.action:hover {
  color: hotpink;
  background-color: rgba(249, 250, 250, 0.1);
}
</style>
