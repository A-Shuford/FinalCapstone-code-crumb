<template>
    <header-vue />
    <nav-bar-vue />
    <mascot-modal-vue />
    <div id="customorder">
      <h1>Custom Order</h1>
  
      <form @submit.prevent="submitOrder">
        <table>
          <tr>
            <td><label for="name">Name:</label></td>
            <td><input type="text" v-model="name" id="name" required></td>
          </tr>
          <tr>
            <td><label for="email">Email:</label></td>
            <td><input type="email" v-model="email" id="email" required></td>
          </tr>
          <tr>
            <td><label for="phone">Phone:</label></td>
            <td><input type="tel" v-model="phone" id="phone" required></td>
          </tr>
          <tr>
            <td colspan="2">
              <h2>Our cakes are made with the finest ingredients and are made to order.</h2>
            </td>
          </tr>
          <tr>
            <td><label for="cake_name">Custom Cake Name:</label></td>
            <td><input type="text" v-model="cake.cakeName" id="cake_name" required></td>
          </tr>
          <tr>
            <td colspan="2">
              <h2>Please select the style, size, flavor, frosting, and filling for your cake.</h2>
            </td>
          </tr>
          <tr>
            <td><label for="cake_style">Cake Style:</label></td>
            <td>
              <select v-model="cake.cakeStyle" id="cake_style" required>
                <option value="Layered">Layered</option>
                <option value="Sheet">Sheet</option>
                <option value="Cupcakes">Cupcakes</option>
              </select>
            </td>
          </tr>
          <tr>
            <td><label for="cake_size">Cake Size:</label></td>
            <td>
              <select v-model="cake.cakeSize" id="cake_size" required>
                <option value="Small">Small</option>
                <option value="Medium">Medium</option>
                <option value="Large">Large</option>
              </select>
            </td>
          </tr>
          <tr>
            <td><label for="cake_flavor">Cake Flavor:</label></td>
            <td>
              <select v-model="cake.cakeFlavor" id="cake_flavor" required>
                <option value="Butterscotch">Butterscotch</option>
                <option value="Vanilla">Vanilla</option>
                <option value="Chocolate">Chocolate</option>
                <option value="Lemon">Lemon</option>
                <option value="Cookie_&_Cream">Cookie & Cream</option>
                <option value="Funfetti">Funfetti</option>
                <option value="Pumpkin">Pumpkin</option>
                <option value="Key_Lime">Key Lime</option>
              </select>
            </td>
          </tr>
          <tr>
            <td><label for="cake_frosting">Cake Frosting:</label></td>
            <td>
              <select v-model="cake.cakeFrosting" id="cake_frosting" required>
                <option value="Vanilla">Vanilla</option>
                <option value="Chocolate Ganache">Chocolate Ganache</option>
                <option value="Coffee">Coffee</option>
                <option value="Strawberry">Strawberry</option>
                <option value="Mint">Mint</option>
                <option value="Cream_Cheese">Cream Cheese</option>
              </select>
            </td>
          </tr>
          <tr>
            <td><label for="cake_filling">Cake Filling:</label></td>
            <td>
              <select v-model="cake.cakeFilling" id="cake_filling">
                <option value="Apple Jelly">Apple Jelly</option>
                <option value="Raspberry Jelly">Raspberry Jelly</option>
                <option value="Vanilla Custard">Vanilla Custard</option>
                <option value="Chocolate Custard">Chocolate Custard</option>
                <option value="Cookie Dough">Cookie Dough</option>
                <option value="Caramel">Caramel</option>
                <option value="Peanut Butter">Peanut Butter</option>
              </select>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <h2>If you would like any sayings like Congratulations or Happy Birthday, please double-check the spelling of any names!</h2>
              <label for="cake_has_writing">Writing on the cake:</label>
              <textarea v-model="cake.hasWriting" id="cake_has_writing"></textarea>
              <p v-if="cake.hasWriting">There is an additional fee for writing on the cake.</p>
  
              <h2>Please provide any additional notes or instructions.</h2>
              <label for="additional_notes">Additional Notes:</label>
              <textarea v-model="cake.additional_notes" id="additional_notes"></textarea>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <button type="submit">Submit</button>
            </td>
          </tr>
        </table>
      </form>
    </div>
  
    <footer-vue />
  </template>
  
  
  
  <script>
  import HeaderVue from '../components/Header.vue';
  import NavBarVue from '../components/Navbar.vue';
  import MascotModalVue from '../components/MascotModal.vue';
  import FooterVue from '../components/Footer.vue';
  import cartService from '../services/CartService';
  
  export default {
    name: "CustomOrder",
    components: {
      HeaderVue,
      NavBarVue,
      MascotModalVue,
      FooterVue
    },
    data() {
      return {
        name: '',
        email: '',
        phone: '',
        cake: {
          cakeName: '',
          cakeStyle: '',
          cakeSize: '',
          cakeFlavor: '',
          cakeFrosting: '',
          cakeFilling: '',
          pickupDate: '',
          pickupTime: '',
          hasWriting: '',
          additional_notes: '',
        }
      };
    },
    methods: {
      submitOrder() {
        cartService.createCustomCake(this.cake)
          .then((response) => {
            if (response.status === 201) {
              this.$router.push({ path: '/' });
            }
          })
          .catch((error) => {
            const response = error.response;
            const message = 'Add cake was unsuccessful: ' + (response ? response.message : 'Could not reach server');
            this.$store.commit('SET_ERROR', message);
        });
    }
  }
};
</script>

<style scoped>
#customorder {
    margin: 20px auto;
    padding: 20px;
    background-color: #FBECEB;
    border-radius: 8px;
    max-width: 600px;
}

table {
    width: 100%;
    border-spacing: 10px;
}

td {
    vertical-align: top; /* Ensure all cells align at the top */
}

td:first-child {
    width: 150px;
    text-align: right;
    padding-right: 10px;
}

h1, h2, p {
    text-align: center;
}

label {
    font-weight: bold;
}

input,
select,
textarea {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    background-color: #8C3F09;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    display: block;
    margin: 20px auto 0;
    width: 100px;
}

button:hover {
    background-color: #921A39;
}
</style>

