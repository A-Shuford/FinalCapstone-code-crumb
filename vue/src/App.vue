<template>
  <div id="capstone-app">
    <header>
    <img id="cakelogo" src = "/src/assets/CC Logo.png">
  </header> 

  <div class = "pagetitle">
    <h1>Code and Crumb</h1>
    <p>"Serving one byte at the time!"</p>
    <p>QUESTIONS OR ORDER CALLS</p>
    <p>911</p>
    <p>Philadelphia, PA EST 2004</p>
  </div>
<nav>


  <div>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
  </div>

    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
    </div>
    <router-view />
</nav>  
  </div>

</template>


<style scoped>
#capstone-app {
  height: 100%;
  display: grid;
  grid-template-rows: 2fr 2fr;
  grid-template-areas:
    "header pagetitle"
    "nav nav";
}

header {
  display: flex;
  align-items: left;
  justify-content: left;
  grid-area: header;
  background-color: white;
  font-size: 2rem;
  padding: 0 20px;
}

#cakelogo{
  width: 200px;
  height: 200px;
}

nav {
  grid-area: nav;
  display: grid;
  grid-template-columns: auto 1fr auto;
  background-color: white;
  border-top: 1px solid black;
  border-bottom: 2px solid black;
}

</style>