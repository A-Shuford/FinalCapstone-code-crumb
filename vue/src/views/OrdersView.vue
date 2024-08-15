<template>
  <header-vue />
  <nav-bar-vue />
  <div class="cart-items-view">
    <h1>Manage Orders</h1>
    
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
        type="email"
        v-model="filters.email"
        placeholder="Search by email"
        @input="filterCartItems"
      />
      <input
        type="tel"
        v-model="filters.phoneNumber"
        placeholder="Search by phone number"
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

    <!-- Date range selection for report generation -->
    <div v-if="activeTab === 'Order Completed'" class="report-generation">
      <h3>Generate Historical Report</h3>
      <label for="startDate">Start Date:</label>
      <input type="date" v-model="reportStartDate" />
      <label for="endDate">End Date:</label>
      <input type="date" v-model="reportEndDate" />
      <button @click="downloadReport">Download Report</button>
    </div>

    <!-- Display and manage filtered cart items -->
    <div v-if="filteredCartItems.length === 0">
      <p>No cart items found for the selected status and filters.</p>
    </div>
    <div v-if="filteredCartItems.length > 0" @submit.prevent="submitChanges">
      <div class="cart-item-container" v-for="item in filteredCartItems" :key="item.cartItemId" :class="{ highlight: item.isUpdated }">
        <!-- Flex container for customer info, cake details, and status update -->
        <div class="order-content">
          <!-- Customer Information -->
          <div class="customer-info">
            <h3>Customer Information</h3>
            <p><strong>Username:</strong> {{ item.user.username }}</p>
            <p><strong>Name:</strong> {{ item.user.yourName }}</p>
            <p><strong>Email:</strong> {{ item.user.email }}</p>
            <p><strong>Phone Number:</strong> {{ item.user.phoneNumber }}</p>
          </div>

          <!-- Cake Details -->
          <div class="cake-details">
            <h3>Cake Details</h3>
            <p><strong>Cake ID:</strong> {{ item.cake.cakeId }}</p>
            <p><strong>Cake Name:</strong> {{ item.cake.cakeName }}</p>
            <p><strong>Style:</strong> {{ item.cake.cakeStyle }}</p>
            <p><strong>Size:</strong> {{ item.cake.cakeSize }}</p>
            <p><strong>Flavor:</strong> {{ item.cake.cakeFlavor }}</p>
            <p><strong>Filling:</strong> {{ item.cake.cakeFilling }}</p>
            <p><strong>Frosting:</strong> {{ item.cake.cakeFrosting }}</p>
            <p><strong>Has Writing:</strong> {{ item.cake.hasWriting ? 'Yes' : 'No' }}</p>
            <p><strong>Custom Text:</strong> {{ item.cake.customText }}</p>
            <p><strong>Quantity:</strong> {{ item.quantity }}</p>
            <p><strong>Price:</strong> {{ formatCurrency(item.cake.price) }}</p>
            <p><strong>Pick-up Date:</strong> {{ item.pickupDate }}</p>
            <p><strong>Pick-up Time:</strong> {{ item.pickupTime }}</p>
            <p><strong>Order Total:</strong> {{ calculateOrderTotal(item) }}</p>
          </div>

          <!-- Status Update -->
          <div class="status-update">
            <h3>Update Order Status</h3>
            <select v-model="item.cartItemStatus" required>
              <option value="Pending">Pending</option>
              <option value="Rejected by bakery">Rejected by bakery</option>
              <option value="Cancelled by customer">Cancelled by customer</option>
              <option value="Ready for pick-up">Ready for pick-up</option>
              <option value="Order Completed">Order Completed</option>
            </select>
            <!-- Hide submit button for completed or rejected orders -->
            <button type="button" @click="submitChange(item)">Submit</button>
          </div>
        </div>

      </div>
    </div>
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
      statuses: ["Pending", "Rejected by bakery", "Cancelled by customer", "Ready for pick-up", "Order Completed"],
      activeTab: "Pending",
      filters: {
        username: "",
        pickUpDate: "",
        email: "",
        phoneNumber: "",
        style: "",
        size: "",
      },
      reportStartDate: "",
      reportEndDate: "",
      taxRate: 0.06 // 6% tax rate
    };
  },
  methods: {
    setActiveTab(status) {
      this.activeTab = status;
      this.filterCartItems();
    },
    filterCartItems() {
      this.filteredCartItems = this.cartItems.filter(item => {
        const itemDate = new Date(item.pickupDate);
        const startDate = this.reportStartDate ? new Date(this.reportStartDate) : null;
        const endDate = this.reportEndDate ? new Date(this.reportEndDate) : null;

        return (
          item.cartItemStatus === this.activeTab &&
          (this.filters.username === "" || item.user.username.includes(this.filters.username)) &&
          (this.filters.pickUpDate === "" || item.pickupDate === this.filters.pickUpDate) &&
          (this.filters.email === "" || item.user.email.includes(this.filters.email)) &&
          (this.filters.phoneNumber === "" || item.user.phoneNumber.includes(this.filters.phoneNumber)) &&
          (this.filters.style === "" || item.cake.cakeStyle.includes(this.filters.style)) &&
          (this.filters.size === "" || item.cake.cakeSize.includes(this.filters.size)) &&
          (!startDate || itemDate >= startDate) &&
          (!endDate || itemDate <= endDate)
        );
      });
    },
    fetchCartItems() {
      CartService.getAllOrders()
        .then((response) => {
          this.cartItems = response.data.map(item => ({
            ...item,
            isUpdated: false, // Add this flag to manage row highlighting
          }));
          this.filterCartItems();
        })
        .catch((error) => {
          console.error("Error fetching cart items:", error);
        });
    },
    async downloadReport() {
      if (!this.reportStartDate || !this.reportEndDate) {
        this.$store.commit("SET_ERROR", "Please select both start and end dates.");
        return;
      }

      // Filter completed orders within the date range
      const completedOrders = this.cartItems.filter(item => {
        const itemDate = new Date(item.pickupDate);
        const startDate = new Date(this.reportStartDate);
        const endDate = new Date(this.reportEndDate);

        return item.cartItemStatus === "Order Completed" &&
          itemDate >= startDate && itemDate <= endDate;
      });

      if (completedOrders.length === 0) {
        this.$store.commit("SET_ERROR", "No completed orders found within the selected dates.");
        return;
      }

      // Generate CSV content
      const headers = "Username,Name,Email,PhoneNumber,CakeName,Style,Size,Flavor,Filling,Frosting,HasWriting,CustomText,Price,PickupDate,PickupTime,OrderTotal\n";
      const rows = completedOrders.map(item => {
        const total = this.calculateOrderTotal(item);
        return `${item.user.username},${item.user.yourName},${item.user.email},${item.user.phoneNumber},${item.cake.cakeName},${item.cake.cakeStyle},${item.cake.cakeSize},${item.cake.cakeFlavor},${item.cake.cakeFilling},${item.cake.cakeFrosting},${item.cake.hasWriting ? 'Yes' : 'No'},${item.cake.customText},${this.formatCurrency(item.cake.price)},${item.pickupDate},${item.pickupTime},${total}`;
      }).join("\n");

      const csvContent = headers + rows;

      // Trigger download
      const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
      const link = document.createElement("a");
      const url = URL.createObjectURL(blob);
      link.setAttribute("href", url);
      link.setAttribute("download", `completed_orders_${this.reportStartDate}_to_${this.reportEndDate}.csv`);
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);

      console.log('Report downloaded successfully.');
    },
    formatCurrency(value) {
      return new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
      }).format(value);
    },
    calculateOrderTotal(item) {
      const subtotal = item.cake.price * item.quantity;
      const tax = subtotal * this.taxRate;
      return this.formatCurrency(subtotal + tax);
    },
    async submitChange(item) {
      try {
        await CartService.updateCartItemStatus(item.cartItemId, { cartItemStatus: item.cartItemStatus });
        this.$store.commit("SET_SUCCESS", `Order status for '${item.cake.cakeName}' updated successfully.`);
        item.isUpdated = true; // Set the flag to true for highlighting

        // Remove highlight after a short delay
        setTimeout(() => {
          item.isUpdated = false;
        }, 2000);

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

.cart-item-container {
  border: 1px solid #ddd;
  padding: 15px;
  margin-bottom: 20px;
  border-radius: 10px;
  background-color: white;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  gap: 20px; /* Space between sections */
}

.order-content {
  display: flex;
  flex-direction: row;
  gap: 20px;
  width: 100%;
}

.customer-info,
.cake-details,
.status-update {
  flex: 1;
}

.customer-info h3,
.cake-details h3,
.status-update h3 {
  margin-bottom: 10px;
  color: #C26060;
}

.status-update select {
  padding: 5px;
  border-radius: 5px;
}

.status-update button {
  padding: 5px 10px;
  margin-top: 10px;
  background-color: #C26060;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.cart-item-container.highlight {
  background-color: #c8e6c9;
}

.report-generation {
  margin-top: 20px;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 10px;
  background-color: #ffffff;
}

.report-generation h3 {
  margin-bottom: 10px;
  color: #C26060;
}

.report-generation label {
  margin-right: 10px;
}

.report-generation input {
  margin-right: 10px;
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #ddd;
}

.report-generation button {
  padding: 5px 10px;
  background-color: #C26060;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
