<template>
  <div class="home">
    <div id="heading-line">
      <h1>
        In Stock Cakes 
      </h1>
      <div>
        <span
          :class="{ 'view-icon': true, active: cardView }"
          @click="cardView = true"
          title="View tiles"
        >🔲</span>
        <span
          :class="{ 'view-icon': true, active: !cardView }"
          @click="cardView = false"
          title="View table"
        >🔳</span>
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
            🔍
          </button>
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
  </div>
</template>

<script>
import InStock from "../services/InStockService";
import InstockTable from "../components/InstockTable.vue";
import InstockCards from "../components/InstockCards.vue";

export default {
  components: {
    InstockTable,
    InstockCards,
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

      InStock
        .getCakes()
        .then((response) => {
          this.cakes = response.data;
        })
        .catch((error) => {
          const response = error.response;
          const message =
            "Getting in-stock cakes was unsuccessful: " +
            (response ? response.message : "Could not reach server");
          this.$store.commit("SET_ERROR", message);
          console.error(message);
        });
    },
    searchCakes() {
      InStock
        .searchCakes(this.filter)
        .then((response) => {
          this.cakes = response.data;
        })
        .catch((error) => {
          const response = error.response;
          const message =
            "Getting In-stock cakes was unsuccessful: " +
            (response ? response.message : "Could not reach server");
          this.$store.commit("SET_ERROR", message);
          console.error(message);
        });
    },

    checkSearchEnter(e) {
      if (e.key === "Enter") {
        this.getCakes();
      }
    },
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

@media (max-width: 768px) {
  #heading-line {
    flex-direction: column;
    align-items: flex-start;
  }

  .view-icon {
    margin-bottom: 10px;
  }

  #search-box {
    margin-top: 10px;
  }
}
</style>
