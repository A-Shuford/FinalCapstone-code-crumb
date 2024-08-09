<template>

  <header-vue />
  <nav-bar />
  <mascot-modal-vue />

  <div id="register" class="text-center">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="name">Name</label>
        <input type="text" id="name" placeholder="John Smith" v-model="user.yourName" required />
      </div>
      <div class="form-input-group">
        <label for="email">Email</label>
        <input type="email" id="email" placeholder="example@example.com" v-model="user.email" required />
      </div>
      <div class="form-input-group">
        <label for="phoneNumber">Phone Number</label>
        <input type="tel" id="phone" placeholder="123-456-7890" v-model="user.phoneNumber" required />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
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
.form-input-group {
  margin-bottom: 1rem;
}

button {
  border-radius: 5px;
}

input {
  border-radius: 5px;
}

label {
  margin-right: 0.5rem;
}
</style>
