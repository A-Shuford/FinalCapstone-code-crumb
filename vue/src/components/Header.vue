<template>
  <div id="header">
    <table>
      <tr>
        <!-- Logo Column -->
        <td id="logo-column">
          <img id="cakelogo" src="/src/assets/CC Logo.png" alt="Code & Crumb Logo">
        </td>
        
        <!-- Page Title Column -->
        <td id="title-column">
          <div class="page-title">
            <p>"Serving one byte at a time!"</p>
            <h2>For questions or order</h2>
            <h2>Call 911</h2>
            <p>1234 Baker St, Confection City</p>  
            <h2>EST 2004</h2>
          </div>
        </td>
        
        <!-- Account Column -->
        <td id="account-column">
          <div id="cart-login-status">
            <!-- Cart -->
            <div id="cart">
              <router-link v-bind:to="{ name: 'cart-item' }">
                <img src="/src/assets/cart.png" alt="Cart Icon">
              </router-link>
     <!-- <div
        id="message-bar"
        v-bind:class="'message-' + $store.state.message.level"
        v-bind:title="$store.state.message.text"
      >
        {{ $store.state.message.text }}
      </div> -->
            </div>
            <!-- Login/Register/Logout -->
            <div id="loginlogoutregister">
              <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">Logout</router-link>
              <router-link v-bind:to="{ name: 'login' }" v-if="!$store.state.token">Login</router-link>&nbsp;|
              <router-link v-bind:to="{ name: 'register'}" v-if="!$store.state.token">Register</router-link>&nbsp;
            </div>
            <!-- Login Status -->
            <div id="login-status">
              <p v-if="isLoggedIn">Welcome, {{ username }}!</p>
              <img id="login-status-heart" src="/src/assets/Cake_Images/pixelheart.png" alt="Heart Icon">
            </div>
          </div>
          <!-- Social Media Icons -->
          <div id="social-media">
            <social-media-icons-vue />
          </div>
        </td>
      </tr>
    </table>
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
      return this.$store.state.token !== ''; 
    },
    username() {
      return this.$store.state.user.username || 'Guest';
    }
  }
};
</script>

<style scoped>
#header {
  background-color: #FAEDCD;
  padding: 10px;
}

table {
  width: 100%;
  table-layout: auto;
  border-collapse: collapse; /* Remove gaps between cells */
}

td {
  vertical-align: center; 
  padding: 10px;
}

#logo-column {
  width: 40%; /* Adjust as necessary */
  text-align: center;
}

#cakelogo {
  width: 400px; /* Adjust as necessary */
  height: auto;
}

#title-column {
  text-align: center;
}

#account-column {
  text-align: center;
}

#cart-login-status {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  height: 100%;
  padding: 0 10px; /* Adjust horizontal padding if needed */
}

#cart {
  margin-bottom: 10px; /* Space between cart and login/register */
}

#cart img {
  width: 30px; /* Adjust size as necessary */
  margin-bottom: 30px; /* Space between cart and login/register */
}

#loginlogoutregister {
  margin-bottom: 30px; /* Space between login/register and heart */
}

#login-status {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 30px; /* Space between heart and social media */
}

#login-status-heart {
  width: 30px; /* Adjust size as necessary */
}

#social-media {
  text-align: center;
  margin-top: 30px; /* Space between heart and social media */
}

/* Media Queries for responsiveness */
@media (max-width: 768px) {
  table {
    display: block;
    width: 100%;
  }

  tr {
    display: block;
    margin-bottom: 20px;
  }

  td {
    display: block;
    width: 100%;
    text-align: center;
    padding: 10px 0;
  }

  #logo-column,
  #title-column,
  #account-column {
    width: 100%;
  }

  #cart-login-status {
    align-items: center;
    height: auto;
  }

  #social-media {
    margin-top: 10px;
  }
}

@media (max-width: 480px) {
  #cakelogo {
    width: 300px;
  }

  #login-status-heart {
    width: 30px;
  }

  #cart img {
    width: 30px;
  }
}
</style>
