<template>
  <div>
  <div class="home">
    <h2>Welcome to Code and Crumb</h2>
    <h2 id="login-message" v-if="$store.state.token == ''">
    <router-link v-bind:to="{ name: 'login' }">Please login</router-link> to place an order.
    </h2>
  </div>
  <div class="cake-gallery">
    <div class="slider-wrapper">
            <img src="/src/assets/Cake Images/apple jelly cake image 1.jpg" alt="Apple Jelly Cake 1" />
            <img src="/src/assets/CC Logo.png" alt="Code and Crumb Logo" />
            <img src="/src/assets/Cake Images/apple jelly cake image 2.jpg" alt="Apple Jelly Cake 2" />
            <img src="/src/assets/CC Mascot.png" alt="CC Mascot" />
            <img src="/src/assets/Cake Images/Chocolate cake ganache 2.jpg" alt="Chocolate Cake Ganache 2" />
            <img src="/src/assets/CC Logo.png" alt="Code and Crumb Logo" />
            <img src="/src/assets/Cake Images/apple jelly cake image 2.jpg" alt="Apple Jelly Cake 2" />
            <img src="/src/assets/CC Mascot.png" alt="CC Mascot" />
            <img src="/src/assets/Cake Images/chocolate cake ganache 3.jpg" alt="Chocolate Cake Ganache 3"/>
            <!--<img src="/src/assets/CC Logo.png" alt="Code and Crumb Logo" />
            <img src="/src/assets/Cake Images/chocolate cake ganache.jpg" alt="Chocolate Cake Ganache" />
            <img src="/src/assets/CC Mascot.png" alt="CC Mascot" />
            <img src="/src/assets/Cake Images/cupcakes.jpg" alt="Cupcakes" />-->
          </div>
        </div>
        </div>
      </template>
      <style scoped>
      .home {
        position: relative;
        z-index: 2;
        background-color: white;
        padding: 10px;
      }
      .cake-gallery {
        overflow: hidden;
        width: 100%;
        position: relative;
        z-index: 1;
        }
      .slider-wrapper {
        display: flex;
        width: 50%; /* Assuming 6 images, this may need to be adjusted */
        animation: scroll 60s linear infinite;
      }
      .slider-wrapper img {
        width: 175px; /* 2 inches in pixels */
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