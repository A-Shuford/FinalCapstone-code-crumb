<template>
    <div v-if="isVisible" class="modal-content">
      <img src="/src/assets/CC Mascot.png" alt="CC Mascot">
      <button class="close-button" @click="closeModal">X</button>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        isVisible: true
      };
    },
    methods: {
      closeModal() {
        this.isVisible = false;
      }
    }
  };
  </script>
  
  <style scoped>
  .modal-content {
    position: fixed;
    bottom: 20px;
    right: 20px;
    background: transparent;
    padding: 20px;
    border-radius: 10px;
    text-align: center;
    z-index: 1000;
  }
  
  .modal-content img {
    width: 200px;
    height: auto;
  }
  
  .close-button {
    position: absolute;
    top: 0;
    right: 0;
    background: none;
    border: none;
    font-size: 1.5rem;
    cursor: pointer;
  }
  </style>
  