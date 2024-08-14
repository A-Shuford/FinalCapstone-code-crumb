<template>
  <header-vue />
  <nav-bar />
  <mascot-modal-vue />
  
  <div class="form-container">
    <h5>Login</h5>
  
    
    <form v-on:submit.prevent="login">
      <table>
        <tr>
          <td><label for="username">Username:</label></td>
          <td><input type="text" id="username" v-model="user.username" required autofocus /></td>
        </tr>
        <tr>
          <td><label for="password">Password:</label></td>
          <td><input type="password" id="password" v-model="user.password" required /></td>
        </tr>
        <tr>
          <td colspan="2">
            <div role="alert" v-if="invalidCredentials">
              Invalid username and password!
            </div>
            <div role="alert" v-if="this.$route.query.registration">
              Thank you for registering, please sign in.
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <button type="submit">Sign in</button>
          </td>
        </tr>
      </table>
    </form>

    <p>
      <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
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
    FooterVue,
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.form-container {
    margin: 20px auto;
    padding: 20px;
    background-color: #FBECEB;
    border-radius: 8px;
    max-width: 600px;
    text-align: center; /* Center the titles */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  /* Light shadow for emphasis */
}

table {
    width: 100%;
    border-spacing: 10px;
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
   }

button:hover {
    background-color: #921A39;
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
