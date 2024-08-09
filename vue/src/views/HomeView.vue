<template>
  <div id="capstone-app">
    <header>
      <header-vue />
    </header>
    <nav>
      <nav-bar-vue />
    </nav>

    <main>
      <div class="home">
        <h2>Welcome to Code and Crumb</h2>
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
      <div>
        <mascot-modal-vue />
      </div>
    </main>

    <footer>
      <footer-vue />
    </footer>
  </div>
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
#capstone-app {
  display: grid;
  grid-template-rows: auto auto 1fr auto;
  grid-template-areas:
    "header"
    "nav"
    "main"
    "footer";
  height: 100vh;
}

header {
  grid-area: header;
}

nav {
  grid-area: nav;
}

main {
  grid-area: main;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #FAEDCD;
}

footer {
  grid-area: footer;
}

.home {
  position: relative;
  z-index: 2;
  padding: 10px;
  text-align: center;
}

.cake-gallery {
  width: 100%;
  position: relative;
  z-index: 1;
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.slider-container {
  width: 100%;
  max-width: 800px;
  overflow: hidden;
  position: relative;
}

.slider-wrapper {
  display: flex;
  animation: scroll 45s linear infinite;
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
</style>
