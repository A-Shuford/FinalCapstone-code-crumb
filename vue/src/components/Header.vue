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
          <div id="floating-hearts">
            <img src="/src/assets/Cake_Images/pixelheart.png" alt="Floating Heart" class="heart">
            <img src="/src/assets/Cake_Images/pixelheart.png" alt="Floating Heart" class="heart">
            <img src="/src/assets/Cake_Images/pixelheart.png" alt="Floating Heart" class="heart">
            <img src="/src/assets/Cake_Images/pixelheart.png" alt="Floating Heart" class="heart">
            <img src="/src/assets/Cake_Images/pixelheart.png" alt="Floating Heart" class="heart">
            <img src="/src/assets/Cake_Images/pixelheart.png" alt="Floating Heart" class="heart">
            <!-- Add more hearts as needed -->
          </div>
         <div class="page-title">
            <div class="info-block-1">
              <h4>Address</h4>
              <h4>1234 Baker Street</h4>
              <h4>Confection City, PA 11111</h4>
            </div>
            <div class="info-block-2">
              <h4>Open</h4>
              <h4>9am-9pm</h4>
              <h4>Tuesday - Sunday</h4>
            </div>
            <div class="info-block-3">
              <h4>Questions</h4>
              <h4>or to Order</h4>
              <h4>877-606-3203</h4>
            </div>
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
            </div>
            <!-- Login/Register/Logout -->
            <div id="loginlogoutregister">
              <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">Logout</router-link>
              <router-link v-bind:to="{ name: 'login' }" v-if="!$store.state.token">Login</router-link>&nbsp;|
              <router-link v-bind:to="{ name: 'register' }" v-if="!$store.state.token">Register</router-link>&nbsp;
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
  position: relative;
  color: #921A39;
}

#floating-hearts {
  position: absolute;
  top: 50%;
  left: 0;
  width: 100%;
  height: 50px;
  /* Adjust the height to cover the middle of the header */
  transform: translateY(-50%);
  /* Center the hearts vertically */
  pointer-events: none;
  overflow: hidden;
  z-index: 1;
  /* Ensure the hearts are above other content */
}

.heart {
  position: absolute;
  width: 30px;
  opacity: 0.8;
  animation: float 30s infinite ease-in-out;
  /* Extend duration */
}

@keyframes float {
  0% {
    transform: translateY(0) translateX(-50%);
    opacity: 0.8;
  }

  50% {
    opacity: 1;
  }

  100% {
    transform: translateY(50%) translateX(-50%);
    /* Move hearts further up */
    opacity: 0;
  }
}

.heart:nth-child(2) {
  left: 20%;
  animation-duration: 15s;
  animation-delay: 1s;
}

.heart:nth-child(3) {
  left: 40%;
  animation-duration: 15s;
  animation-delay: 2s;
}

.heart:nth-child(4) {
  left: 60%;
  animation-duration: 10s;
  animation-delay: 3s;
}

.heart:nth-child(5) {
  left: 80%;
  animation-duration: 15s;
  animation-delay: 4s;
}

/* Additional Hearts with different timings for more variation */
.heart:nth-child(6) {
  left: 10%;
  animation-duration: 10s;
  animation-delay: 1.5s;
}

.heart:nth-child(7) {
  left: 70%;
  animation-duration: 10s;
  animation-delay: 3.5s;
}

table {
  width: 100%;
  border-collapse: collapse;

}

td {
  vertical-align: top;
}

#logo-column {
  text-align: center;
}

#cakelogo {
  width: 200px;
}

#title-column {
  text-align:  center;
  vertical-align: top;  
}

.page-title {
  display: flex;
  justify-content: space-evenly;
  padding: 0px;

}

.info-block-1 {
  text-align: right;
  padding-left: 0px;
}

.info-block-2 {
  text-align: center;
  border-left: 1px solid #921A39;
  border-right: 1px solid #921A39;
  padding-right: 20px;
  padding-left: 20px;
}

.info-block-3 {
  text-align: left;
  padding-right: 0px;
}

#account-column {
  text-align: center;
}

#cart-login-status {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  padding: 0 10px;
}

#cart {
  margin-bottom: 10px;
}

#cart img {
  width: 40px;
}

#loginlogoutregister {
  margin-bottom: 10px;
}

#login-status {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 10px;
}

#login-status-heart {
  width: 30px;
}

#social-media {
  text-align: center;
  margin-top: 10px;
}

@media (max-width: 768px) {
  table {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 0;
  }

  #logo-column, #title-column, #account-column {
    width: 100%;
    text-align: center;
    display:block;
  }

  #cakelogo {
    width: 150px;
    margin-bottom: 10px;
    align-items: center;
  }

  .page-title {
    flex-direction: column;
    align-items: center;
    margin-bottom: 5px;
  }

  .info-block-1, .info-block-2, .info-block-3 {
    text-align: center;
    border: none;
    padding: 5px 0;
    
  }

  #account-column {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  #social-media {
    display: none;
  }

  .heart {
    display: none;
  }

  #loginlogoutregister {
    display: flex;
    justify-content: center;
    width: 100%;
  }
}

</style>
