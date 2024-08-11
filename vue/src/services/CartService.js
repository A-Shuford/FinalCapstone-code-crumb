import axios from 'axios';

/**
 * This service class is used to interact with the Product API.
 * All methods return a Promise so that the calling code can handle both success and 
 * error responses appropriately. 
 */
export default {

  getCart() {
    return axios.get('/cartitem');
  },

  addCake(cake) {
    const item = {
      cakeId: cake.cakeId,
      cartItemStatus: null,
      pickupDate: null ,  
      pickupTime: null,
    };
    return axios.post('/cartitem/items', item);
  },

  clearCart() {
    return axios.delete(`/cart`);
  },

  deleteItem(itemId) {
    return axios.delete(`/cart/items/${itemId}`);
  },

}
