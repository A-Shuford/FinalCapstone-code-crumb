<template>
    <header-vue />
    <nav-bar-vue />
    <mascot-modal-vue />

    <div class="form-container">
        
        <h5>Contact Us</h5>

        <form @submit.prevent="submitOrder">
            <table>
                <tr>
                    <td><label for="name">Name:</label></td>
                    <td><input type="text" v-model="name" id="name"></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="email" v-model="email" id="email"></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone:</label></td>
                    <td><input type="tel" v-model="phone" id="phone"></td>
                </tr>
                <tr>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" v-model="address" id="address"></td>
                </tr>
                <tr>
                    <td><label for="city">City:</label></td>
                    <td><input type="text" v-model="city" id="city"></td>
                </tr>
                <tr>
                    <td><label for="state">State:</label></td>
                    <td><input type="text" v-model="state" id="state"></td>
                </tr>
                <tr>
                    <td><label for="zip">Zip:</label></td>
                    <td><input type="text" v-model="zip" id="zip"></td>
                </tr>
                <tr>
                    <td colspan="2">
                    </td>
                </tr>
                <tr>
                    <td><label for="Message">Message:</label></td>
                    <td><textarea v-model="message" id="Message"></textarea></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submitContactUs" @click="submitContactUs">Submit Contact Us</button>
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

export default {
    name: "HomeView",
    components: {
        HeaderVue,
        NavBarVue,
        MascotModalVue,
        FooterVue
    },
};
</script>

<style scoped>
.form-container {
    margin: 20px auto;
    padding: 20px;
    background-color: #FBECEB;
    border-radius: 8px;
    max-width: 600px;
    text-align: center;
    /* Center the titles and form elements */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    /* Light shadow for emphasis */
}

table {
    width: 100%;
    /* Ensure table takes up full width */
    border-spacing: 10px;
    /* Add spacing between cells */
}

label {
    font-weight: bold;
}

input,
select,
textarea {
    width: 100%;
    /* Ensure input elements fill their container */
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
    /* Make button a block-level element */
    margin: 20px auto;
    /* Center button horizontally */
}

button:hover {
    background-color: #921A39;
}

/* Ensure the last row containing the button is centered */
tr:last-child td {
    text-align: center;
}



h5{
    font-family: "Press Start 2P", system-ui;
    font-size: 1.5rem;
    color: #4B1202;
}
</style>