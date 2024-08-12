<template>
  <header-vue />
  <nav-bar />
  <mascot-modal-vue />

  <div class="form-container">
    <h1>Create Account</h1>
    <form v-on:submit.prevent="register">
      <table>
        <tr>
          <td colspan="2">
            <div role="alert" v-if="registrationErrors">
              {{ registrationErrorMsg }}
            </div>
          </td>
        </tr>
        <tr>
          <td><label for="username">Username:</label></td>
          <td><input type="text" id="username" v-model="user.username" required autofocus /></td>
        </tr>
        <tr>
          <td><label for="name">Name:</label></td>
          <td><input type="text" id="name" placeholder="John Smith" v-model="user.yourName" required /></td>
        </tr>
        <tr>
          <td><label for="email">Email:</label></td>
          <td><input type="email" id="email" placeholder="example@example.com" v-model="user.email" required /></td>
        </tr>
        <tr>
          <td><label for="phoneNumber">Phone Number:</label></td>
          <td><input type="tel" id="phone" placeholder="123-456-7890" v-model="user.phoneNumber" required /></td>
        </tr>
        <tr>
          <td><label for="password">Password:</label></td>
          <td><input type="password" id="password" v-model="user.password" required /></td>
        </tr>
        <tr>
          <td><label for="confirmPassword">Confirm Password:</label></td>
          <td><input type="password" id="confirmPassword" v-model="user.confirmPassword" required /></td>
        </tr>
        <tr>
          <td colspan="2">
            <button type="submit">Create Account</button>
          </td>
        </tr>
      </table>
    </form>
    <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
  </div>

  <footer-vue />
</template>

<script>
import authService from '../services/AuthService';
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
        username: '',
        yourName: '',
        email: '',
        phoneNumber: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
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
    text-align: center; /* Center the titles */
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
</style>
