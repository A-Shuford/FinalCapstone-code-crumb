<template>
  <div id="capstone-app">
    <header>
    <img id="cakelogo" src = "/src/assets/CC Logo.png">
  </header>
  <div class = "pagetitle">
    <h1>Code and Crumb</h1>
    <p>"Serving one byte at the time!"</p>
    <h2>QUESTIONS OR ORDER CALLS</h2>
    <h1>911</h1>
    <p>Philadelphia, PA EST2004</p>
  </div>

  <div>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
  </div>

    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
    </div>
    <router-view />
  </div>
</template>


<style scoped>



#cakelogo{
  width: 200px;
  height: 200px;
}

</style>