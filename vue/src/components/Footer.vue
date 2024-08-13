<template>
  <footer>
    <p>&copy; 2024 Code and Crumb "Serving one byte at a time" All Rights Reserved.</p>
    <social-media-icons-vue />
    <P>Our favorite mascot &#x1f60d Cookie Codey Saunders at your service.</P>
  </footer>
</template>

<script>
import SocialMediaIconsVue from '../components/SocialMediaIcons.vue';

export default {
  components: {
    SocialMediaIconsVue
  }
};
</script>

<style scoped>
footer {
  grid-area: footer;
  background-color: #D4A373;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  padding: 10px;
}
</style>