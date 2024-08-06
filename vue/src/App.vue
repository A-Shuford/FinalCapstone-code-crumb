<template>
  <div id="capstone-app">
    <header>
    <img id="cakelogo" src = "/src/assets/CC Logo.png">
    <div class="pagetitle">
        <h1>Code & Crumb</h1>
        <p>"Serving one byte at the time!"</p>
        <h2>QUESTIONS OR ORDER</h2>
        <h2>Call 911</h2>
        <h2>Philadelphia, PA EST 2004</h2>
      </div>
    <div id = "loginstatus">
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">
          Logout /
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else>Login /</router-link>
        <router-link v-bind:to="{name: 'cartitem'}"> 
       <img src = "/src/assets/cart.png"> Cart</router-link>
    </div>
  </header> 

  
  <nav>
    <div id="home">
        <router-link v-bind:to="{ name: 'home' }"><img src ="/src/assets/navIcons/home.png"> Home </router-link>
    </div>
    <div id="stocks">
      <router-link v-bind:to = "{name: 'instock'}"><img src="/src/assets/navIcons/inStock.png"> In-Stock </router-link>
    </div>
    <div id="custom-order">
      <router-link v-bind:to="{ name: 'customorder' }"><img src = "/src/assets/navIcons/custom.png"> Custom Order </router-link>
    </div>
    <div id="contact-us">
      <router-link v-bind:to="{ name: 'contactus' }"><img src = "/src/assets/navIcons/contactus.png"> Contact Us </router-link>
    </div>
    <div id="review">
      <router-link v-bind:to="{ name: 'reviews' }"><img src = "/src/assets/navIcons/reviews.png"> Reviews </router-link>  
    </div>
  </nav>
  <body>
    <router-view />
    <img src = "/src/assets/CC Mascot.png">
  </body>
    
 
  </div>

  <footer>&copy; 2022. All rights reserved.</footer>

</template>


<style scoped>
#capstone-app {
  height: 100%;
  display: grid;
  grid-template-rows: 2fr 0.25fr 3fr;
  grid-template-areas:
    "header header header"
    "nav nav nav"
    "body body body"
    "footer footer footer";    ;
}

header {
  display: flex;
  align-items:flex-start;
  justify-content: space-between;
  grid-area: header;
  background-color: #FAEDCD;
  font-size: 2rem;
  padding: 0 0px;
}

header h1 {
  font-size: 2rem;
  margin: 10px 0;
  font-style: italic;
}

header h2 {
  font-size: 1.5rem;
  margin: 10px 0;
}

header p {
  font-size: 1rem;
  margin: 20px 0;
}

header #loginstatus {
  display: flex;
  justify-content: flex-end;
  align-items: flex-start;
  font-size: 1.5rem;
  margin: 0;
}

header #loginstatus img{
  width: 20px;
  height: 20px; 
}

#cakelogo{
  width: 300px;
  height: 300px;
}

nav {
  grid-area: nav;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  grid-template-columns: auto 1fr auto;
  background-color: #D4A373;
  border-top: 1px solid black;
  border-bottom: 2px solid black;
}
nav #nav {
display: inline-block;
justify-content: space-evenly;
align-items: flex-start;
}

nav img {
  width: 25px;
  height: 25px;
}

#capstone-app {
  background-color: #FAEDCD;
}

body > img {
  width: 200px;
  height: 335px;
}

body {
  grid-area: body;
  display: flex;
  justify-content: space-evenly;
  align-items:flex-start;
  background-color: #FAEDCD;
}



</style>