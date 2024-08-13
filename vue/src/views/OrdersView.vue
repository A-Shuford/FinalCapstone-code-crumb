<template>
  <header-vue />
  <nav-bar-vue />
  <div class="cart-items-view">
    <h1>Manage Cart Items</h1>
    
    <!-- Tabs for cart item status filtering -->
    <div class="tabs">
      <button
        v-for="status in statuses"
        :key="status"
        :class="{ active: activeTab === status }"
        @click="setActiveTab(status)"
      >
        {{ status }}
      </button>
    </div>

    <!-- Search bar for filtering cart items within the active tab -->
    <div class="search-bar">
      <input
        type="text"
        v-model="filters.username"
        placeholder="Search by username"
        @input="filterCartItems"
      />
      <input
        type="date"
        v-model="filters.pickUpDate"
        placeholder="Search by pick-up date"
        @input="filterCartItems"
      />
      <input
        type="time"
        v-model="filters.pickUpTime"
        placeholder="Search by pick-up time"
        @input="filterCartItems"
      />
      <input
        type="text"
        v-model="filters.style"
        placeholder="Search by style"
        @input="filterCartItems"
      />
      <input
        type="text"
        v-model="filters.size"
        placeholder="Search by size"
        @input="filterCartItems"
      />
    </div>

    <!-- Table for displaying and managing filtered cart items -->
    <div v-if="filteredCartItems.length === 0">
      <p>No cart items found for the selected status and filters.</p>
    </div>
    <form v-if="filteredCartItems.length > 0" @submit.prevent="submitChanges">
      <table class="cart-items-table">
        <thead>
          <tr>
            <th>Username</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Cake ID</th>
            <th>Cake Name</th>
            <th>Style</th>
            <th>Size</th>
            <th>Flavor</th>
            <th>Filling</th>
            <th>Frosting</th>
            <th>Has Writing</th>
            <th>Custom Text</th>
            <th>Price</th>
            <th>Pick-up Date</th>
            <th>Pick-up Time</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in filteredCartItems" :key="item.cartItemId">
            <td>{{ item.user.username }}</td>
            <td>{{ item.user.yourName }}</td>
            <td>{{ item.user.email }}</td>
            <td>{{ item.user.phoneNumber }}</td>
            <td>{{ item.cake.cakeId }}</td>
            <td>{{ item.cake.cakeName }}</td>
            <td>{{ item.cake.cakeStyle }}</td>
            <td>{{ item.cake.cakeSize }}</td>
            <td>{{ item.cake.cakeFlavor }}</td>
            <td>{{ item.cake.cakeFilling }}</td>
            <td>{{ item.cake.cakeFrosting }}</td>
            <td>{{ item.cake.hasWriting ? 'Yes' : 'No' }}</td>
            <td>{{ item.cake.customText }}</td>
            <td>{{ item.cake.price }}</td>
            <td>{{ item.pickupDate }}</td>
            <td>{{ item.pickupTime }}</td>
            <td>
              <label for="submitChange">Update Order Status:</label>
            <select v-model="item.cartItemStatus" required>
              <option value="Pending">Pending</option>
              <option value="Rejected by bakery">Rejected by bakery</option>
              <option value="Cancelled by customer">Cancelled by customer</option>
              <option value="Ready for pick-up">Ready for pick-up</option>
              <option value="Order Completed">Order Completed</option>
            </select>
            </td>
            <td>
              <button type="button" @click="submitChange(item)">Submit</button>
            </td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
  <footer-vue />
</template>

<script>
import HeaderVue from '../components/Header.vue';
import NavBarVue from '../components/Navbar.vue';
import FooterVue from '../components/Footer.vue';
import CartService from '../services/CartService.js';

export default {
  components: {
    HeaderVue,
    NavBarVue,
    FooterVue,
  },
  data() {
    return {
      cartItems: [],
      filteredCartItems: [],
      statuses: ["Pending", "Rejected", "Cancelled", "Ready to be Pick-up", "Completed"],
      activeTab: "Pending",
      filters: {
        username: "",
        pickUpDate: "",
        pickUpTime: "",
        style: "",
        size: "",
      },
    };
  },
  methods: {
    fetchCartItems() {
      CartService.getAllOrders()
        .then((response) => {
          this.cartItems = response.data.map(item => ({
            ...item,
            newStatus: item.cartItemStatus,
            canSubmit: false
          }));
          this.filterCartItems(); // Apply initial filtering
        })
        .catch((error) => {
          console.error("Error fetching cart items:", error);
        });
    },
    setActiveTab(status) {
      this.activeTab = status;
      this.filterCartItems();
    },
    filterCartItems() {
      this.filteredCartItems = this.cartItems.filter(item => {
        return (
          item.cartItemStatus === this.activeTab &&
          (this.filters.username === "" || item.user.username.includes(this.filters.username)) &&
          (this.filters.pickUpDate === "" || item.pickupDate === this.filters.pickUpDate) &&
          (this.filters.pickUpTime === "" || item.pickupTime === this.filters.pickUpTime) &&
          (this.filters.style === "" || item.cake.cakeStyle.includes(this.filters.style)) &&
          (this.filters.size === "" || item.cake.cakeSize.includes(this.filters.size))
        );
      });
    },
    enableSubmit(item) {
      item.canSubmit = item.newStatus !== item.cartItemStatus;
    },
    async submitChange(item) {
    try {
      await CartService.updateCartItemStatus(item.cartItemId, { cartItemStatus: item.cartItemStatus });
      this.$store.commit("SET_SUCCESS", `Order status for '${item.cake.cakeName}' updated successfully.`);
      this.fetchCartItems();
    } catch (error) {
      this.$store.commit("SET_ERROR", `Failed to update order status: ${error.response?.data?.message || error.message}`);
      console.error("Error updating cart item status:", error);
    }
  },
  },
  created() {
    this.fetchCartItems();
  },
};
</script>

<style scoped>
.cart-items-view {
  padding: 20px;
  background-color: #FAEDCD;
}

.tabs {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}

.tabs button {
  padding: 10px 20px;
  background-color: #C26060;
  color: white;
  border: none;
  cursor: pointer;
}

.tabs button.active {
  background-color: #FAEDCD;
  color: #C26060;
}

.search-bar {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}

.search-bar input {
  padding: 5px;
  border: 1px solid #ddd;
  border-radius: 5px;
  max-width: 150px;
}

.cart-items-table {
  width: 100%;
  border-collapse: collapse;
}

.cart-items-table th, .cart-items-table td {
  padding: 10px;
  text-align: left;
  border: 1px solid #ddd;
}

.cart-items-table th {
  background-color: #C26060;
  color: white;
}

.cart-items-table select {
  padding: 5px;
  border-radius: 5px;
}

.cart-items-table button {
  padding: 5px 10px;
  background-color: #C26060;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.cart-items-table button:disabled {
  background-color: #ddd;
  cursor: not-allowed;
}
</style>
