<template>
  <header-vue />
  <nav-bar-vue />
  <mascot-modal-vue />
  <div class="controls">
    <span
      :class="{ 'view-icon': true, active: cardView }"
      @click="cardView = true"
      title="View tiles">
      <img src="../assets/InStockIcons/card.png">
    </span>
    <span
      :class="{ 'view-icon': true, active: !cardView }"
      @click="cardView = false"
      title="View table">
      <img src="../assets/InStockIcons/table.png">
    </span>
    <!--<div id="search-box">
      <input
        type="text"
        name="search-tb"
        id="search-tb"
        placeholder="Search..."
        @keydown="checkSearchEnter"
        v-model="filter"  
      />
      <button
        class="icon-button"
        id="search-button"
        @click="getCakes"
        tabindex="-1">
      </button>
    </div>-->
  </div>
  <div class="pink-container">
    <div class="instockcakes">
    
        <h5>In Stock Cakes</h5>
        <div class="message-bar"
          v-bind:class="'message-' + $store.state.message.level"
          v-bind:title="$store.state.message.text"
          v-if="$store.state.message.text"
          v-on:click="$store.commit('CLEAR_MESSAGE')">
          <img src="../assets/InStockIcons/cancel_message_icon.png">
          {{ $store.state.message.text }}
        </div>
      
    </div>
    <p id="login-message" v-if="!isLoggedIn">You may browse anonymously as much as you wish, 
      but you must <router-link :to="{ name: 'login' }">Login</router-link> to add
      items to your shopping cart.
    </p>
    <InstockCards :cakes="cakes" v-if="cardView" />
    <InstockTable :cakes="cakes" v-else />
  </div>
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
      filter: "",
      cardView: true,
    };
  },

  computed: {
    isLoggedIn() {
      return this.$store.state.token.length > 0;
    },
  },

  methods: {
    getCakes() {
      if (this.filter) {
        this.searchCakes();
        return;
      }

      inStock
        .getCakes()
        .then((response) => {
          this.cakes = response.data;
        })
        .catch(this.handleError("Getting in-stock cakes was unsuccessful"));
    },

    searchCakes() {
      inStock
        .searchCakes(this.filter)
        .then((response) => {
          this.cakes = response.data;
        })
        .catch(this.handleError("Getting in-stock cakes was unsuccessful"));
    },

    checkSearchEnter(e) {
      if (e.key === "Enter") {
        this.getCakes();
      }
    },

    handleError(baseMessage) {
      return (error) => {
        const response = error.response;
        const message =
          baseMessage + ": " + (response ? response.message : "Could not reach server");
        this.$store.commit("SET_ERROR", message);
        console.error(message);
      };
    }
  },

  created() {
    this.getCakes();
  },
};

</script>

<style scoped>
.controls {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin: 10px 20px;
    /* Align to the right and give some spacing from the navbar */
}

.controls .view-icon {
  margin-left: 10px;
  cursor: pointer;

}

#search-box {
  display: inline-block;
  margin-left: 10px;
}

.pink-container {
  background-color: #fce4ec; /* Pink background */
  padding: 20px;
  border-radius: 8px;
  max-width: 800px; /* Limit the container width */
  margin: 20px auto; /* Center the container horizontally */
  text-align: center; /* Center content inside the container */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Light shadow for emphasis */
}

.instockcakes img {
  max-width: 100%; /* Ensure images don't overflow the container */
  height: auto;
}

.heading-line {
  margin: 20px auto;
  padding: 20px;
  background-color: #FBECEB;
  border-radius: 8px;
  max-width: 600px;
  text-align: center;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

div.cart img {
  height: 30px;
}

div.update img {
  height: 30px;
}

.action {
  cursor: pointer;
}

div.message-bar img {
  height: 20px;
  padding-right: 15px;
}

div.message-bar {
  display: flex;
  background-color: transparent;
  position: sticky;
  bottom: 0px;
  align-items: flex-start;
  justify-content: center;
  padding: 5px;
  border: 1px solid hotpink;
  border-radius: 8px;
}

h5{
    font-family: "Petit Formal Script", cursive;
    font-size: 2rem;
    color: #4B1202;
}
</style>