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
          <h3>"Serving one byte at a time"</h3>

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
  height: 100px;
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
  animation: float 15s infinite ease-in-out;
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
    transform: translateY(-200%) translateX(-50%);
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
  animation-duration: 14s;
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
  width: 300px;
}

#title-column {
  text-align: center;
  padding: 10px;
}

.page-title {
  display: flex;
  justify-content: space-between;
  padding-bottom: 20px;
}

.info-block-1 {
  padding: 0 1px;
  text-align: center;
}

.info-block-2 {
  padding: 0 1px;
  text-align: center;
}

.info-block-3 {
  padding: 0 1px;
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
    width: 200px;
  }

  #login-status-heart {
    width: 25px;
  }

  #cart img {
    width: 30px;
  }
}
</style>
