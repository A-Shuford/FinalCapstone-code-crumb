<template>
  <div class="home">
    <h1>Home</h1>
    <h2>Welcome!</h2>
    <p id ="login-message" v-if=" this.$store.state.token == ''">
      Please <router-link v-bind:to="{name:'login'}">login</router-link> to place an order.</p>
  </div>
</template>

<script>
export default {
};
</script>
