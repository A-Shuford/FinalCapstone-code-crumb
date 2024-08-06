<template>
    <div id = "customorder">
        <h1>Custom Order</h1>
        <h2 id ="login-message" v-if=" this.$store.state.token == ''">
            Please <router-link v-bind:to="{name:'login'}">login</router-link> to place an order.</h2>
            </div>
</template>

<script>
export default {
};
</script>