<template>
  <div class="home">
    <h2>Welcome to Code and Crumb</h2>
    <h2 id="login-message" v-if="$store.state.token == ''">
      Please <router-link v-bind:to="{ name: 'login' }">login</router-link> to place an order.</h2>
  </div>

  <div>
    <h2>Our cakes are made with the finest ingredients made to order.</h2>
  </div>

<div>
  
    <Slide><img src="/assets/Cake Images/apple jelly cake image 1.jpg" alt="Apple Jelly Cake 1" />
    </Slide>
    <Slide><img src="/assets/Cake Images/apple jelly cake image 2.jpg" alt="Apple Jelly Cake 2" />
    </Slide>
    <Slide><img src="/assets/Cake Images/Chocolate cake ganache 2.jpg" alt="Chocolate Cake Ganache 2" />
    </Slide>
    <Slide><img src="/assets/Cake Images/chocolate cake ganache 3.jpg" alt="Chocolate Cake Ganache 3"/>
    </Slide>
    <Slide><img src="/assets/Cake Images/chocolate cake ganache.jpg" alt="Chocolate Cake Ganache" />
    </Slide>
    <Slide><img src="/assets/Cake Images/cupcakes.jpg" alt="Cupcakes" />
    </Slide>
  
</div>

  


</template>