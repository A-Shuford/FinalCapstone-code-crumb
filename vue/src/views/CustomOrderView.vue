<template>
    <header-vue />
    <nav-bar-vue />
    <mascot-modal-vue />
    <div id="customorder">
        <h1>Custom Order</h1>
        <h2>Please fill your contact information</h2>

        <form @submit.prevent="submitOrder">
            <table>
                
                <tr>
                    <td><label for="cake_name">Custom Cake Name:</label></td>
                    <td><input type="text" v-model="cake.cakeName" id="cake.cakeName" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h2>Please select the style, size, flavor, frosting, and filling for your cake.</h2>
                    </td>
                </tr>
                <tr>
                    <td><label for="cake.cakeStyle">Cake Style:</label></td>
                    <td>
                        <select v-model="cake.cakeStyle" id="cake.cakeStyle" required>
                            <option value="Layered">Layered</option>
                            <option value="Sheet">Sheet</option>
                            <option value="Cupcakes">Cupcakes</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="cake.cakeSize">Cake Size:</label></td>
                    <td>
                        <select v-model="cake.cakeSize" id="cake.cakeSize" required>
                            <option value="Small">Small</option>
                            <option value="Medium">Medium</option>
                            <option value="Large">Large</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="cake.cakeFlavor">Cake Flavor:</label></td>
                    <td>
                        <select v-model="cake.cakeFlavor" id="cake.cakeFlavor" required>
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
                    <td><label for="cake.cakeFrosting">Cake Frosting:</label></td>
                    <td>
                        <select v-model="cake.cakeFrosting" id="cake.cakeFrosting" required>
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
                    <td><label for="cake.cakeFilling">Cake Filling:</label></td>
                    <td>
                        <select v-model="cake.cakeFilling" id="cake.cakeFilling">
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
                        <p>If you would like any sayings like Congratulations or Happy Birthday.</p>
                        <p>Please double check the spelling of any names!</p>
                        <label for="cake.customText">Writing on the cake:</label>
                        <textarea v-model="cake.customText" id="cake.customText"></textarea>
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
            cake : {
                cakeName: '',
                cakeStyle: '',
                cakeSize: '',
                cakeFlavor: '',
                cakeFrosting: '',
                cakeFilling: '',
                hasWriting: false,
                cakeType: 'Custom',
                customText: '',
                price: 0,
            },

        };
    },
    methods: {

        async submitOrder() {
            this.cake.hasWriting = this.checkWriting();
            cartService.createCustomCake(this.cake)
                .then((response) => {
                    if (response.status == 201) {
              this.$router.push({
                path: '/cartitem',
              });
            }
          }).catch((error) => {
                    const response = error.response;
                    const message = 'Add cake was unsuccessful: ' + (response ? response.message : 'Could not reach server');
                    this.$store.commit('SET_ERROR', message);
                    console.error(message);
                });

        },


        checkWriting() {
            if(this.cake.customText.length > 0){
                this.cake.hasWriting = true;
              return this.cake.hasWriting;    
            }
        },
    },
};
</script>

<style scoped>
#customorder {
    margin: 20px auto;
    padding: 20px;
    background-color: #FBECEB;
    border-radius: 8px;
    max-width: 600px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  /* Light shadow for emphasis */
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

