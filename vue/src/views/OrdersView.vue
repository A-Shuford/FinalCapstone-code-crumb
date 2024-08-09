<template>
    <header-vue />
    <nav-bar-vue />
    <div class="orders-view">
      <h1>Manage Orders</h1>
      
      <!-- Tabs for order status filtering -->
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
  
      <!-- Search bar for filtering orders within the active tab -->
      <div class="search-bar">
        <input
          type="text"
          v-model="filters.name"
          placeholder="Search by name"
          @input="filterOrders"
        />
        <input
          type="date"
          v-model="filters.pickUpDate"
          placeholder="Search by pick-up date"
          @input="filterOrders"
        />
        <input
          type="time"
          v-model="filters.pickUpTime"
          placeholder="Search by pick-up time"
          @input="filterOrders"
        />
        <input
          type="text"
          v-model="filters.style"
          placeholder="Search by style"
          @input="filterOrders"
        />
        <input
          type="text"
          v-model="filters.size"
          placeholder="Search by size"
          @input="filterOrders"
        />
      </div>
  
      <!-- Table for displaying and managing filtered orders -->
      <div v-if="filteredOrders.length === 0">
        <p>No orders found for the selected status and filters.</p>
      </div>
      <form v-if="filteredOrders.length > 0" @submit.prevent="submitChanges">
        <table class="orders-table">
          <thead>
            <tr>
              <th>Name</th>
              <th>Style</th>
              <th>Size</th>
              <th>Flavor</th>
              <th>Pick-up Date</th>
              <th>Pick-up Time</th>
              <th>Status</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="order in filteredOrders" :key="order.id">
              <td>{{ order.name }}</td>
              <td>{{ order.style }}</td>
              <td>{{ order.size }}</td>
              <td>{{ order.flavor }}</td>
              <td>{{ order.pickUpDate }}</td>
              <td>{{ order.pickUpTime }}</td>
              <td>
                <select v-model="order.newStatus" @change="enableSubmit(order)">
                  <option :value="order.status" disabled>{{ order.status }}</option>
                  <option v-if="order.status === 'Pending'" value="Rejected">Rejected</option>
                  <option v-if="order.status === 'Pending'" value="Ready to be Pick-up">Ready to be Pick-up</option>
                  <option v-if="order.status === 'Ready to be Pick-up'" value="Order Completed">Order Completed</option>
                </select>
              </td>
              <td>
                <button type="button" @click="submitChange(order)" :disabled="!order.canSubmit">Submit</button>
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
        orders: [],
        filteredOrders: [],
        statuses: ["Pending", "Rejected", "Cancelled", "Ready to be Pick-up", "Completed"],
        activeTab: "Pending",
        filters: {
          name: "",
          pickUpDate: "",
          pickUpTime: "",
          style: "",
          size: "",
        },
      };
    },
    methods: {
      fetchOrders() {
        orderService.getAllOrders()
          .then((response) => {
            this.orders = response.data.map(order => ({
              ...order,
              newStatus: order.status,
              canSubmit: false
            }));
            this.filterOrders(); // Apply initial filtering
          })
          .catch((error) => {
            console.error("Error fetching orders:", error);
          });
      },
      setActiveTab(status) {
        this.activeTab = status;
        this.filterOrders();
      },
      filterOrders() {
        this.filteredOrders = this.orders.filter(order => {
          return (
            order.status === this.activeTab &&
            (this.filters.name === "" || order.name.includes(this.filters.name)) &&
            (this.filters.pickUpDate === "" || order.pickUpDate === this.filters.pickUpDate) &&
            (this.filters.pickUpTime === "" || order.pickUpTime === this.filters.pickUpTime) &&
            (this.filters.style === "" || order.style.includes(this.filters.style)) &&
            (this.filters.size === "" || order.size.includes(this.filters.size))
          );
        });
      },
      enableSubmit(order) {
        order.canSubmit = order.newStatus !== order.status;
      },
      submitChange(order) {
        if (order.canSubmit) {
          orderService.updateOrderStatus(order.id, order.newStatus)
            .then(() => {
              order.status = order.newStatus;
              order.canSubmit = false;
              this.filterOrders();
            })
            .catch((error) => {
              console.error("Error updating order status:", error);
            });
        }
      }
    },
    created() {
      this.fetchOrders();
    },
  };
  </script>
  
  <style scoped>
  .orders-view {
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
  
  .orders-table {
    width: 100%;
    border-collapse: collapse;
  }
  
  .orders-table th, .orders-table td {
    padding: 10px;
    text-align: left;
    border: 1px solid #ddd;
  }
  
  .orders-table th {
    background-color: #C26060;
    color: white;
  }
  
  .orders-table select {
    padding: 5px;
    border-radius: 5px;
  }
  
  .orders-table button {
    padding: 5px 10px;
    background-color: #C26060;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  .orders-table button:disabled {
    background-color: #ddd;
    cursor: not-allowed;
  }
  </style>
  