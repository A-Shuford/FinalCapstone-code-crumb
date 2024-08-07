<template>
  <div class="home">
    <h2>Welcome to Code and Crumb</h2>
    <h2 id="login-message" v-if="$store.state.token == ''">
      Please <router-link v-bind:to="{ name: 'login' }">login</router-link> to place an order.
    </h2>
  </div>

  <div class="cake-gallery">
    <div class="slider-wrapper">
      <img src="/src/assets/Cake Images/apple jelly cake image 1.jpg" alt="Apple Jelly Cake 1" />
      <img src="/src/assets/CC Logo.png" alt="Code and Crumb Logo" />
      <img src="/src/assets/Cake Images/apple jelly cake image 2.jpg" alt="Apple Jelly Cake 2" />
      <img src="/src/assets/CC Mascot.png" alt="Cookie Codey Sanders" />
      <img src="/src/assets/Cake Images/Chocolate cake ganache 2.jpg" alt="Chocolate Cake Ganache 2" />
      <img src="/src/assets/Cake Images/chocolate cake ganache 3.jpg" alt="Chocolate Cake Ganache 3"/>
      <img src="/src/assets/Cake Images/chocolate cake ganache.jpg" alt="Chocolate Cake Ganache" />
      <img src="/src/assets/Cake Images/cupcakes.jpg" alt="Cupcakes" />
    </div>
  </div>
</template>

<style scoped>
h2{
  text-align: center;

}
div.home{
  text-align: center;
  align-content: center;
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 10px;
  padding-bottom: 10px;
  background: white;
  border-radius: 10px;
  border-color: black;
  left: 100px;
}
.cake-gallery {
  overflow: hidden;
  width: 100%;
  position: relative;
  
}

.slider-wrapper {
  display: flex;
  width: 200%; /* Assuming 6 images, this may need to be adjusted */
  animation: scroll 30s linear infinite;
}

.slider-wrapper img {
  width: 500px; /* 2 inches in pixels */
  height: 500px; /* 2 inches in pixels */
  object-fit: cover;
}

@keyframes scroll {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-50%); /* Adjust based on the number of images */
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