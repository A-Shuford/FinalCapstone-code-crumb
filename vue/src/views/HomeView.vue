<template>
  <div id="capstone-app">
    <header-vue />
    <nav-bar-vue />
    <mascot-modal-vue />
      <div class="home">
        <h3>Welcome to Code and Crumb </h3>
      </div>  
        <h2 id="login-message" v-if="$store.state.token == ''">
          <router-link v-bind:to="{ name: 'login' }">Please login</router-link> to place an order.
        </h2>
      </div>
      <div class="cake-gallery">
        <div class="slider-container">
          <div class="slider-wrapper">
            <img src="/src/assets/Cake_Images/apple_jelly_cake_image_1.jpg" alt="Apple Jelly Cake" />
            <img src="/src/assets/Cake_Images/Chocolate_cake_ganache_2.jpg" alt="Chocolate Cake Ganache" />
            <img src="/src/assets/Cake_Images/Butterscotch_Cake.jpg" alt="Butterscotch Cake" />
            <img src="/src/assets/Cake_Images/cupcakes.jpg" alt="Cupcakes" />
            <img src="/src/assets/Cake_Images/funfetti.jpg" alt="Funfetti Cake" />
            <img src="/src/assets/Cake_Images/lemoncake.jpg" alt="Lemon Cake" />
            <img src="/src/assets/Cake_Images/vanillacupcakes.jpg" alt="Vanilla Cupcakes" />
          </div>
       </div>
      </div>
      <div class="home">
       <h3>"Serving one byte at a time"</h3>
      </div>
     
    
    <footer-vue />
</template>

<script>
import HeaderVue from '../components/Header.vue';
import NavBarVue from '../components/Navbar.vue';
import MascotModalVue from '../components/MascotModal.vue';
import FooterVue from '../components/Footer.vue';

export default {
  name: "HomeView",
  components: {
    HeaderVue,
    NavBarVue,
    MascotModalVue,
    FooterVue
  },

  mounted() {
    const wrapper = this.$el.querySelector('.slider-wrapper');
    const images = Array.from(wrapper.children);

    // Clone images to make the scrolling continuous
    images.forEach(image => {
      const clone = image.cloneNode(true);
      wrapper.appendChild(clone);
    });
  }
};
</script>

<style scoped>

.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 10px;
}

.cake-gallery {
  width: 100%;
  position: relative;
  z-index: 1;
  display: flex;
  justify-content: center;
  padding-bottom: 50px;
}

.slider-container {
  width: 100%;
  max-width: 800px;
  overflow: hidden;
  width: 100%;
  
} 

.slider-wrapper {
  display: flex;
  animation: scroll 55s linear infinite;
}

.slider-wrapper img {
  width: 300px;
  height: 300px;
  object-fit: cover;
}

@keyframes scroll {
  0% {
    transform: translateX(0);
  }

  100% {
    transform: translateX(-100%);
  }
}

h5 {
  font-family: "Petit Formal Script", cursive;
  ;
  font-size: 1.5rem;
  color: #4B1202;
}
</style>
