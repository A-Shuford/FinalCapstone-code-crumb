<template>
    <footer>
      <p>&copy; 2024 Your Website Name. All Rights Reserved.</p>
      <social-media-icons-vue />
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
  