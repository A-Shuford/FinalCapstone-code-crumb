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
        <h4>Welcome to Code and Crumb</h4>
        <h5>"Serving one byte at a time"</h5>
        <h3 id="login-message" v-if="$store.state.token == ''">
          <router-link v-bind:to="{ name: 'login' }">Please login</router-link> to place an order.
        </h3>
      </div>

      <div class="cake-gallery">
        <div class="slider-container">
          <div class="slider-wrapper">
            <img src="/src/assets/Cake_Images/apple_jelly_cake_image_2.jpg" alt="Apple Jelly Cake" />
            <img src="/src/assets/Cake_Images/strawberry_cupcakes.jpg" alt="Strawberry Cupcakes" />
            <img src="/src/assets/Cake_Images/Chocolate_cake_ganache_3.jpg" alt="Chocolate Cake Ganache" />
            <img src="/src/assets/Cake_Images/Butterscotch_Cake.jpg" alt="Butterscotch Cake" />
            <img src="/src/assets/Cake_Images/cupcakes.jpg" alt="Cupcakes" />
            <img src="src\assets\Cake_Images\standard_funfetti_cake.jpg" alt="Funfetti Cake" />
            <img src="/src/assets/Cake_Images/lemoncake.jpg" alt="Lemon Cake" />
            <img src="src\assets\Cake_Images\coffeeicingcake.jpg" alt="Coffee Icing Cake" />
            <img src="src\assets\Cake_Images\standard_red_velvet_cake.png" alt="Vanilla Cupcakes" />
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
    
    // Adjust the width of the slider-wrapper to account for the cloned images
    wrapper.style.width = `${images.length * 300 * 2}px`;
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

main {
  grid-area: main;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
  background-color: #FAEDCD;
  flex-grow: 1;
}

.home {
  position: relative;
  z-index: 2;
  text-align: center;
}

.cake-gallery {
  width: 100%;
  position: relative;
  z-index: 1;
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

.slider-container {
  width: 100%;
  max-width: 900px;
  overflow: hidden; /* Make sure the slider container hides the overflow */
  position: relative;
}

.slider-wrapper {
  display: flex;
  animation: scroll 60s linear infinite;
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
    transform: translateX(-50%);
  }
}

h4 {
  font-family: "Press Start 2P", system-ui;
  font-size: 1.5rem;
  color: #4B1202;
}

h5 {
  font-family: "Petit Formal Script", cursive;
  font-size: 1.5rem;
  color: #4B1202;
}
</style>
