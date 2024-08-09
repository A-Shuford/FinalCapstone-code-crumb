<template>
<header-vue />
<nav-bar-vue />
<mascot-modal-vue />

    <div class = "reviews">
        <h1>Reviews</h1>
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