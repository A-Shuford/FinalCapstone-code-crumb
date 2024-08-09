<template>
  <div id="header">
    <img id="cakelogo" src="/src/assets/CC Logo.png" alt="Code & Crumb Logo">
    
    <div class="page-title">
      <p>"Serving one byte at a time!"</p>
      <h2>For questions or order</h2>
      <h2>Call 911</h2>
      <p>1234 Baker St, Confection City</p>  
      <h2>EST 2004</h2>
    </div>
    
    <div>
      <social-media-icons-vue />
    </div>
    
    <div id="loginlogoutregister">
     <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp; -->

      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">
          Logout
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-if="!$store.state.token">Login</router-link>&nbsp;|
        <router-link v-bind:to="{ name: 'register'}" v-if="!$store.state.token">Register</router-link>&nbsp;

    </div>
    
    <div id="login-status">
      <p v-if="isLoggedIn">Welcome, {{ username }}! </p>
      <img id="login-status-heart" src="/src/assets/Cake_Images/pixelheart.png" alt="Heart Icon">
    </div>
    
    <div id="cart">
      <router-link v-bind:to="{ name: 'cart-item' }">
        <img src="/src/assets/cart.png" alt="Cart Icon">
      </router-link>
    </div>
    
  </div>  
</template>

<script>
import SocialMediaIconsVue from '../components/SocialMediaIcons.vue';

export default {
  name: "HeaderVue",
  components: {
    SocialMediaIconsVue
  },
  computed: {
    isLoggedIn() {
      // Replace this logic with your actual login check
      return this.$store.state.token !== ''; 
    },
    username() {
      // Replace this logic with how you store and retrieve the username
      return this.$store.state.username || 'Guest';
    }
  }
};
</script>

<style scoped>
#header {
  display: flex;
  align-items: center;
  justify-content: space-around;
  background-color: #FAEDCD;
  padding: 10px;
  flex-wrap: wrap;
}

#cakelogo {
  width: 150px;
  height: auto;
}

.page-title {
  flex-grow: 1;
  text-align: center;
  max-width: 500px;
}

#loginlogoutregister {
  text-align: right;
  font-size: 1rem;
}

#login-status {
  display: flex;
  align-items: center;
  justify-content: center;
}

#login-status-heart {
  width: 50px; /* Adjust the width to your desired size */
  height: auto; /* Maintain aspect ratio */
  margin-left: 10px;
}

#cart img {
  width: 40px; /* Adjust the width to your desired size */
  height: auto; /* Maintain aspect ratio */
}

@media (max-width: 768px) {
  #header {
    flex-direction: column;
    align-items: center;
  }
  
  .page-title {
    margin: 10px 0;
    text-align: center;
  }
  
  #loginlogoutregister {
    margin-top: 10px;
  }
}
</style>
