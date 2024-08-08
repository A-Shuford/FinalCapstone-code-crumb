<template>
  <div id="capstone-app">
    <header>
      <img id="cakelogo" src="/src/assets/CC Logo.png">
      <div class="pagetitle">
        <h1>Code & Crumb</h1>
        <p>"Serving one byte at the time!"</p>
        <h2>QUESTIONS OR ORDER</h2>
        <h2>Call 911</h2>
        <h2>Philadelphia, PA EST 2004</h2>
      </div>
      <div id="loginstatus">
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">Logout /</router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else>Login /</router-link>
        <router-link v-bind:to="{ name: 'cartitem' }">
          <img src="/src/assets/cart.png"> Cart
        </router-link>
      </div>
    </header>
    <nav>
      <router-link v-bind:to="{ name: 'home' }"><img src="/src/assets/navIcons/home.png"> Home</router-link>
      <router-link v-bind:to="{ name: 'instock' }"><img src="/src/assets/navIcons/inStock.png"> In-Stock</router-link>
      <router-link v-bind:to="{ name: 'customorder' }"><img src="/src/assets/navIcons/custom.png"> Custom Order</router-link>
      <router-link v-bind:to="{ name: 'contactus' }"><img src="/src/assets/navIcons/contactus.png"> Contact Us</router-link>
      <router-link v-bind:to="{ name: 'reviews' }"><img src="/src/assets/navIcons/reviews.png"> Reviews</router-link>
    </nav>
    <main>
      <router-view />
      <MascotModal />
    </main>
    <footer>&copy; 2022. All rights reserved.</footer>
  </div>
</template>

<script>
import MascotModal from './views/MascotModal.vue';

export default {
  components: {
    MascotModal
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
  display: flex;
  align-items: center;
  justify-content: space-between;
  grid-area: header;
  background-color: #FAEDCD;
  padding: 10px;
}

header h1 {
  font-size: 2rem;
  margin: 0;
}

header h2 {
  font-size: 1.5rem;
  margin: 5px 0;
}

header p {
  font-size: 1rem;
  margin: 5px 0;
}

header #loginstatus {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  font-size: 1.2rem;
  margin: 0;
}

header #loginstatus img {
  width: 20px;
  height: 20px;
}

#cakelogo {
  width: 300px;
  height: auto;
}

nav {
  display: flex;
  justify-content: space-around;
  align-items: center;
  grid-area: nav;
  background-color: #D4A373;
  padding: 10px 0;
}

nav a {
  font-size: 1.2rem;
  font-weight: bold;
  display: flex;
  align-items: center;
}

nav img {
  width: 25px;
  height: 25px;
  margin-right: 5px;
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
  background-color: #D4A373;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
}
</style>
