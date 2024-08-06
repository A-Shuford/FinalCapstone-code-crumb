<template>
    <div id = "customorder">
        <h1>Custom Order</h1>
        <p id ="login-message" v-if=" this.$store.state.token == ''">
            Please <router-link v-bind:to="{name:'login'}">login</router-link> to place an order.</p>
            </div>
</template>

<script>
export default {
};
</script>