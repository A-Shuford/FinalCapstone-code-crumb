<template>
  <div>
    <div class="home">
      <h2>Welcome to Code and Crumb</h2>
      <h2 id="login-message" v-if="$store.state.token == ''">
        <router-link v-bind:to="{ name: 'login' }">Please login</router-link> to place an order.
      </h2>
    </div>

    <div class="cake-gallery">
      <div class="slider">
        <div class="slider-wrapper">
          <img src="/src/assets/Cake Images/apple jelly cake image 1.jpg"/>
          <img src="/src/assets/Cake Images/Chocolate cake ganache 2.jpg"/>
          <img src="/src/assets/Cake Images/Butterscotch-Cake.jpg"/>
          <img src="/src/assets/Cake Images/cupcakes.jpg"/>
          <img src="/src/assets/Cake Images/funfetti.jpg"/>
          <img src="/src/assets/Cake Images/lemoncake.jpg" />
          <img src="/src/assets/Cake Images/vanillacupcakes.jpg"/>
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
  background-color: transparent;
  padding: 10px;
}

.cake-gallery {
  overflow: hidden;
  width: 100%;
  position: relative;
  z-index: 1;
}

.slider {
  overflow: hidden;
  width: 100%;
  display: flex;
  justify-content: center;
}

.slider-wrapper {
  display: flex;
  animation: scroll 45s linear infinite;
}

.slider-wrapper img {
  width: 200px;
  height: 200px;
  object-fit: cover;
  flex-shrink: 0;
}

@keyframes scroll {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(calc(-200px * 7)); /* Adjust based on the number of images and their width */
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
}
</script>
