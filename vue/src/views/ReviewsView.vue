<template>
  <header-vue />
  <nav-bar-vue />
  <mascot-modal-vue />

  
  <div class="reviews">
    <div class="title-box">
      <h1>WHAT OUR CUSTOMERS THINK ABOUT US...</h1>
      
    </div>
    
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
