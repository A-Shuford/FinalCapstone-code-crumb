import axios from 'axios';


export default{

    getCake(){
        return axios.get('/instock');
    },

    searchCakes(name){
        let querystring = "";
        if (name) {
            querystring += `name=${name}`;
        }
        if (querystring) {
            querystring = "?" + querystring;
        }
        return axios.get('/products' + querystring);
      },
    
      getProductById(productId) {
        return axios.get(`/products/${productId}`);
      },
      

}