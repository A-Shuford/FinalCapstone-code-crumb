<template>
  <header-vue />
  <nav-bar-vue />
  <mascot-modal-vue />
    <div class="home">
      <div id="heading-line">
        <h1>
          In Stock Cakes 
        </h1>
        <div class="message-bar"
        v-bind:class="'message-' + $store.state.message.level"
        v-bind:title="$store.state.message.text"
        v-if="$store.state.message.text"
        v-on:click="$store.commit('CLEAR_MESSAGE')"
      >
        <img src="../assets/InStockIcons/cancel_message_icon.png">
        {{ $store.state.message.text }}
        </div>
        <div>
          <span
          :class="{ 'view-icon': true, active: cardView }"
          @click="cardView = true"
          title="View tiles"
        ><img src="../assets/InStockIcons/card.png"></span>
        <span
          :class="{ 'view-icon': true, active: !cardView }"
          @click="cardView = false"
          title="View table"
        ><img src="../assets/InStockIcons/table.png"></span>
        <div id="search-box">
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
            tabindex="-1"
          >
            </button>
          </div>
        </div>
      </div>
    </div>
    <p id="login-message" v-if="!isLoggedIn">
      Welcome! You may browse anonymously as much as you wish,<br />
      but you must
      <router-link :to="{ name: 'login' }">Login</router-link> to add
      items to your shopping cart.
    </p>
    <InstockCards :cakes="cakes" v-if="cardView" />
    <InstockTable :cakes="cakes" v-else />
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
#heading-line {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: flex-start;
}

#spinner {
  color: rgb(0, 0, 0);
}

.view-icon {
  font-size: 1.2rem;
  margin-right: 7px;
  padding: 3px;
  color: #c26060;
  border-radius: 3px;
  cursor: pointer;
}

.view-icon.active {
  background-color: #FAEDCD;
}

.view-icon:not(.active) {
  font-size: 1.2rem;
  margin-right: 7px;
}

.view-icon:not(.active):hover {
  color: blue;
  background-color: rgba(255, 255, 255, 0.7);
}

/* Search box styling */
#search-box {
  display: inline-block;
  border: 1px solid darkgray;
  border-radius: 10px;
}

#search-tb {
  border: none;
  padding: 5px;
  min-width: 200px;
  background-color: transparent;
}

#search-tb:focus-visible {
  outline: none;
}

#search-button {
  color: gray;
  cursor: pointer;
  background-color: transparent;
  border: none;
}

.view-icon img{
  height: 20px;
}

div.message-bar img{
  height: 20px;
  padding-right: 15px;
  
}

div.message-bar{
  display: flex;
  background-color: greenyellow;
  position:fixed;
  top: 0px;
  align-items: flex-start;
  justify-content: center;
  padding: 5px;
}

</style>
