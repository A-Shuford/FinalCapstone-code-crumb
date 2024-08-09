<template>
  <div id="header">
    <img id="cakelogo" src="/src/assets/CC Logo.png">
    <div class="page-title">
      <h1>Code & Crumb</h1>
      <p>"Serving one byte at a time!"</p>
      <h2>QUESTIONS OR ORDER</h2>
      <h2>Call 911</h2>
      <h2>Philadelphia, PA EST 2004</h2>
    </div>
    <div id="login-status">
      <p>Logged in as: {{ $store.state.username }}</p>
    <div id="login-status-heart">  
      <img id="login-status-heart" src="src\assets\Cake_Images\pixelheart.png">
    </div>
    </div>
    <!--<div id="cart">
      <router-link v-bind:to="{ name: 'cart-item' }">
      </router-link>
    </div>-->
    <div>
      <img id="cart" src="/src/assets/cart.png">
    </div>
    <div>
      <social-media-icons-vue />
    </div>
  </div>  

</template>

<script>
import SocialMediaIconsVue from '../components/SocialMediaIcons.vue';

export default {
  name: "HeaderVue",
  components: {
    SocialMediaIconsVue
  }
};

</script>

<style scoped>
#header {
  display: flex;
  align-items: center;
  justify-content:space-around;
  background-color: #FAEDCD;
  padding: 10px;
}


#header h1 {
  font-size: 2rem;
  margin: 0;
}

#header h2 {
  font-size: 1.5rem;
  margin: 5px 0;
}

#header p {
  font-size: 1rem;
  margin: 5px 0;
}

#header #login-status {
  display: flex;
  z-index: 1;
  justify-content: flex-end;
  align-items: center;
  font-size: 1.2rem;
  margin: 0;
}

#header #login-status-heart img {
  width: 50px; /* Adjust the width to your desired size */
  height: auto; /* Maintain aspect ratio */
  margin-left: 10px;
  margin-right: 10px;
  z-index: 2;
}

#header #cart {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  font-size: 1.2rem;
  margin: 0;
  width: 40px; /* Adjust the width to your desired size */
  height: auto; /* Maintain aspect ratio */
}


#cakelogo {
  width: 300px;
  height: auto;
}

#social-media-icons img {
  width: 30px; /* Adjust size as needed */
  height: 30px;
  margin: 0 10px;
  }
</style>
