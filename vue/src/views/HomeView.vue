<template>
  <div>
    <div class="home">
      <h2>Welcome to Code and Crumb</h2>
      <h2 id="login-message" v-if="$store.state.token == ''">
        <router-link v-bind:to="{ name: 'login' }">Please login</router-link> to place an order.
      </h2>
    </div>

    <div class="cake-gallery">
      <div class="slider-container">
        <div class="slider-wrapper">
<<<<<<< HEAD
          <img src="/src/assets/Cake Images/apple jelly cake image 1.jpg" />
          <img src="/src/assets/Cake Images/Chocolate cake ganache 2.jpg" />
          <img src="/src/assets/Cake Images/Butterscotch-Cake.jpg" />
          <img src="/src/assets/Cake Images/cupcakes.jpg" />
          <img src="/src/assets/Cake Images/funfetti.jpg" />
          <img src="/src/assets/Cake Images/lemoncake.jpg" />
          <img src="/src/assets/Cake Images/vanillacupcakes.jpg" />
=======
          <img src="/src/assets/Cake Images/apple jelly cake image 1.jpg" alt="Apple Jelly Cake 1" />
          <img src="/src/assets/Cake Images/Chocolate cake ganache 2.jpg" alt="Chocolate Cake Ganache 2" />
          <img src="/src/assets/Cake Images/Butterscotch-Cake.jpg" alt="Butterscotch Cake" />
          <img src="/src/assets/Cake Images/cupcakes.jpg" alt="Cupcakes" />
          <img src="/src/assets/Cake Images/funfetti.jpg" alt="Funfetti Cake" />
          <img src="/src/assets/Cake Images/lemoncake.jpg" alt="Lemon Cake" />
          <img src="/src/assets/Cake Images/vanillacupcakes.jpg" alt="Vanilla Cupcakes" />
>>>>>>> 29707dff4e36f4cfc73ae24098c950c46569414f
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
div {
  text-align: center;
  align-items: center;
  
}


.home {
  position: relative;
  z-index: 2;
  padding: 10px;
  
}

.cake-gallery {
  overflow: hidden;
  width: 100%;
  position: relative;
  z-index: 1;
  display: flex;
  justify-content: center;
}

.slider-container {
  width: 90%; /* Adjust width as needed */
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

<script>
export default {
  name: "HomeView",
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
