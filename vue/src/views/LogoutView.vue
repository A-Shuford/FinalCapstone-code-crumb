<template>
  <header-vue />
  <nav-bar />
  <mascot-modal-vue />
  
  <div class="logout-container">
    <h1>Logout</h1>
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
  methods: {
    logout() {
      authService
        .logout()
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("LOGOUT");
            this.$router.push("/login");
          }
        })
        .catch(error => {
          console.error("Error logging out: ", error);
        });
    }
  },
  mounted() {
    this.logout(); // Call logout when the component is mounted
  }
};
</script>

<style scoped>
.logout-container {
  text-align: center;
  margin-top: 20px;
}
</style>
