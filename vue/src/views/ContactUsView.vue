<template>
    <header-vue />
    <nav-bar-vue />
    <mascot-modal-vue />
    
    <div class="contactus">
        <h1>Contact Us</h1>
        <h2>Please fill your contact information</h2>
    </div>


    <form @submit.prevent="submitOrder">
        <div>
            <label for="name">Name:</label>
            <input type="text" v-model="name" id="name">
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" v-model="email" id="email">
        </div>
        <div>
            <label for="phone">Phone:</label>
            <input type="tel" v-model="phone" id="phone">
        </div>
        <div>
            <label for="address">Address:</label>
            <input type="text" v-model="address" id="address">
        </div>
        <div>
            <label for="city">City:</label>
            <input type="text" v-model="city" id="city">
        </div>
        <div>
            <label for="state">State:</label>
            <input type="text" v-model="state" id="state">
        </div>
        <div>
            <label for="zip">Zip:</label>
            <input type="text" v-model="zip" id="zip">
        </div>
        <div>
            <h2>Your message to us</h2>
            <label for="Message">Message:</label>
            <textarea v-model="message" id="Message"></textarea>
        </div>
        <div>
                <button type="submit" @click="submitOrder">Submit</button>
        </div>
    </form>
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
