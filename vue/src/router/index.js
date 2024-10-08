import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import InStockView from '../views/InStockView.vue';
import CartItemView from '../views/CartItemView.vue';
import CustomOrderView from '../views/CustomOrderView.vue';
import ContactUsView from '../views/ContactUsView.vue';
import ReviewsView from '../views/ReviewsView.vue';
import InStockDetailsView from '../views/InStockDetailsView.vue';
import OrdersView from '../views/OrdersView.vue';
import AdminInStockView from '../views/AdminInStockView.vue';
/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/instock",
    name: "instock",
    component: InStockView,
    meta:{
      requiresAuth: false
    }
  },
  {
    path: "/cakes/:id",
    name: 'inStockDetails',
    component: InStockDetailsView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/cartitem",
    name: "cart-item",
    component: CartItemView,
    meta:{
      requiresAuth: false
    }
  },
  {
    path: "/customorder",
    name: "customorder",
    component: CustomOrderView,
    meta:{
      requiresAuth: false
    }
  },
  {
    path: "/contactus",
    name: "contactus",
    component: ContactUsView,
    meta:{
      requiresAuth: false
    }
  },
  {
    path: "/reviews",
    name: "reviews",
    component: ReviewsView,
    meta:{
      requiresAuth: false
    }
  },
  {path: "/orders",
    name: "orders",
    component: OrdersView,
    meta:{
      requiresAuth: true,
      requiresAdmin: true
    }
  },
  {path: "/admincakes",
    name: "admincakes",
    component: AdminInStockView,
    meta:{
      requiresAuth: true,
      requiresAdmin: true
    }

  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  //Determine if the route requires Admin privileges
  const requiresAdmin = to.matched.some(x => x.meta.requiresAdmin);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }

  if (requiresAdmin && !store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')) {
    return {name: "home"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
