<template>
  <header-vue />
  <nav-bar />
  <mascot-modal-vue />
  
  <div class="logout-container">
    <h5>Logout</h5>
    <p>You have been logged out.</p>
    <p>Thank you for visiting our site.</p>
    <p>
      Click <router-link v-bind:to="{ name: 'login' }">here</router-link> to log back in.
    </p>
  </div>
  
  <footer-vue />
</template>

<script>
import authService from "../services/AuthService";
import HeaderVue from "../components/Header.vue";
import NavBar from "../components/NavBar.vue";  
import MascotModalVue from "../components/MascotModal.vue";
import FooterVue from "../components/Footer.vue";

export default {
  components: {
    HeaderVue,
    NavBar,
    MascotModalVue,
    FooterVue
  },
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};

</script>

<style scoped>
.logout-container {
  text-align: center;
  margin-top: 20px;
  
}

h4 {
  font-family: "Press Start 2P", system-ui;
  font-size: 1.5rem;
  color: #4B1202;
}

h5 {
  font-family: "Petit Formal Script", cursive;
  font-size: 2rem;
  color: #4B1202;
  text-align: center;
}
</style>
