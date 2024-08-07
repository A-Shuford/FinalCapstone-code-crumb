<template>
  <div class="home">
    <h2>Welcome to Code and Crumb</h2>
    <h2 id="login-message" v-if="$store.state.token == ''">
      Please <router-link v-bind:to="{ name: 'login' }">login</router-link> to place an order.
    </h2>
  </div>

  <div>
    <h2>Our cakes are made with the finest ingredients made to order.</h2>
  </div>

  <div class="cake-gallery">
    <Slide><img src="/src/assets/Cake Images/apple jelly cake image 1.jpg" alt="Apple Jelly Cake 1" /></Slide>
    <Slide><img src="/src/assets/Cake Images/apple jelly cake image 2.jpg" alt="Apple Jelly Cake 2" /></Slide>
    <Slide><img src="/src/assets/Cake Images/Chocolate cake ganache 2.jpg" alt="Chocolate Cake Ganache 2" /></Slide>
    <Slide><img src="/src/assets/Cake Images/chocolate cake ganache 3.jpg" alt="Chocolate Cake Ganache 3"/></Slide>
    <Slide><img src="/src/assets/Cake Images/chocolate cake ganache.jpg" alt="Chocolate Cake Ganache" /></Slide>
    <Slide><img src="/src/assets/Cake Images/cupcakes.jpg" alt="Cupcakes" /></Slide>
  </div>
</template>

<style scoped>
.cake-gallery {
  display: flex;
  flex-wrap: wrap;
  gap: 10px; /* Adjust gap as needed */
}

.cake-gallery Slide {
  flex: 1 1 calc(50% - 10px); /* 2 columns layout */
  display: flex;
  justify-content: center;
  align-items: center;
}

.cake-gallery img {
  width: 100px; /* 2 inches in pixels */
  height: 100px; /* 2 inches in pixels */
  object-fit: cover;
}
</style>
