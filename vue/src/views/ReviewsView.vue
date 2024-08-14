<template>
  <header-vue />
  <nav-bar-vue />
  <mascot-modal-vue />

  <!-- Pop-Up Message -->
  <div class="popup" v-if="showPopup">
    <p>✩°｡⋆You can’t buy happiness, but you can buy cake, and that’s pretty close⋆｡°✩</p>
  </div>

  <div class="reviews">
    <div class="title-box">
      <h1>WHAT OUR CUSTOMERS THINK ABOUT US...</h1>
      <p>REVIEW BY – 
        <a href="https://www.yelp.com/search?find_desc=cake+images&find_loc=Newark%2C+DE" target="_blank" class="yelp-link">YELP</a>
      </p>
    </div>
    <div v-for="(review, index) in reviews" :key="index" class="review" v-show="currentReview === index">
      <div class="stars">
        ★★★★★
      </div>
      <p>{{ review.text }}</p>
      <span>{{ review.author }}</span>
    </div>
    <div class="pagination">
      <span v-for="(review, index) in reviews" :key="index" :class="{ active: currentReview === index }" @click="currentReview = index"></span>
    </div>
  </div>

  <footer-vue />
</template>

<script>
import HeaderVue from '../components/Header.vue';
import NavBarVue from '../components/Navbar.vue';
import MascotModalVue from '../components/MascotModal.vue'; 
import FooterVue from '../components/Footer.vue';

export default {
  name: "HomeView",
  components: {
    HeaderVue,
    NavBarVue,
    MascotModalVue,
    FooterVue
  },
  data() {
    return {
      currentReview: 0,
      showPopup: true, // Initialize to show the pop-up
      reviews: [
        { text: "My husband surprised me with two delightful cakes from Code and Crumb. The Chocolate Ganache Cake was irresistibly rich and moist. The second was an Apple Jelly Cake that defied expectations—despite looking dry when I cut into it, it turned out to be incredibly moist, a unique and unforgettable experience!", author: "Megan M." },
        { text: "I recently had the pleasure of trying the Chocolate Ganache Cake from Code and Crumb, and it was a chocolate lover's dream come true. The rich, velvety ganache was perfectly complemented by the moist, tender cake. It was indulgent without being overwhelming—just the right balance of sweetness and decadence.", author: "Rommel S." },
        { text: "The Vanilla Buttercream Cake was a delightful surprise. The cake itself was incredibly soft and fluffy, while the vanilla buttercream frosting was smooth and creamy with a hint of vanilla richness. It's a classic done right—simple yet utterly satisfying.", author: "Anton S." },
        { text: "If you're in the mood for something fun and festive, the Funfetti Cake from Code and Crumb is a must-try. The vibrant sprinkles add a cheerful touch, and the cake is moist and flavorful with just the right amount of sweetness. It's a perfect choice for celebrations or just a sweet treat.", author: "Crystal C." },
        { text: "The Red Velvet Cake was simply divine. The cake was incredibly moist with a subtle cocoa flavor, and the cream cheese frosting was the perfect creamy complement. It was visually stunning and tasted even better—definitely a showstopper for any occasion.", author: "Shu S." },
        { text: "Code and Crumb's Pound Cake is the epitome of buttery goodness. It had a rich, dense texture that was incredibly satisfying, and the subtle vanilla flavor was just right. This cake is perfect for those who appreciate a classic, no-fuss dessert.", author: "Saba S." },
        { text: "I was pleasantly surprised by the Butterscotch Cake. The butterscotch flavor was bold and distinctive, and the cake itself was moist and well-balanced. The caramel filling added an extra layer of sweetness that made each bite a delightful experience.", author: "Tyree W." },
        { text: "The chocolate ganache cake was heavenly! The perfect balance of rich chocolate and smooth texture.", author: "Markevis W." },
        { text: "I ordered the apple jelly cake for my daughter’s birthday, and it was a hit! Fresh, fruity, and beautifully decorated.", author: "Terry G." },
        { text: "The cupcakes were delicious and had the perfect amount of sweetness. Definitely coming back for more!", author: "Colin E." },
        { text: "I’m amazed at the variety and quality of cupcakes here. The customer service was also top-notch.", author: "Ryan E." }
      ]
    };
  },
  mounted() {
    setTimeout(() => {
      this.showPopup = false; 
    }, 10000); // pop-up timeout
  }
};
</script>

<style scoped>
.reviews {
  background-image: url('/src/assets/Cake_Images/background.png');
  background-size: cover;
  background-position: center;
  padding: 40px;
  border-radius: 8px;
  margin: 20px auto;
  max-width: 800px;
  color: #fff;
  text-align: center;
}

.title-box {
  background-color: rgba(67, 66, 66, 0.5); 
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 20px;
}

.title-box h1, .title-box p {
  margin: 0;
  color: #fff;
}

.reviews h1 {
  font-size: 2em;
  margin-bottom: 10px;
  color: #111111;
}

.reviews p {
  font-size: 1.2em;
  margin-bottom: 5px;
  color: #bbb;
}

.review {
  padding: 20px;
  background-color: rgba(51, 51, 51, 0.8); 
  border-radius: 8px;
  margin: 20px 0;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
}

.stars {
  color: #FFD700;
  font-size: 1.5em;
  margin-bottom: 10px;
}

.review p {
  font-size: 1.1em;
  color: #ccc;
  margin-bottom: 10px;
}

.review span {
  display: block;
  font-size: 1em;
  color: #999;
}

.pagination {
  display: flex;
  justify-content: center;
  margin-top: 15px;
}

.pagination span {
  width: 12px;
  height: 12px;
  background-color: #f5b2b2;
  border-radius: 50%;
  margin: 0 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.pagination span.active {
  background-color: #fff;
}

.yelp-link {
  color: #4a0e77;
  text-decoration: underline;
  font-weight: bold;
}

.yelp-link:hover {
  color: rgb(249, 66, 66);
}

/* Pop-Up Styling */
.popup {
  position: fixed;
  bottom: 20px;
  left: 20px;
  background-color: rgba(228, 40, 185, 0.8);
  color: #fff;
  padding: 10px 20px;
  border-radius: 8px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.3);
  font-size: 1em;
  z-index: 1000; 
  transition: opacity 0.20s ease;
}

.popup p {
  margin: 0;
}

.template {
  font: italic;
}


</style>
