import axios from 'axios';


export default{

    getCakes(){
        return axios.get('/instock'); //WHERE STATEMENT IS EQUALS TO STANDARD
    },

    getdetails(cakeId){
        return axios.get(`/cakes/${cakeId}`);
    },

    updateCake(cake) {
        return axios.put(`/cakes/${cake.cakeId}`, cake);
      },

    searchCakes(name){
        let querystring = "";
        if (name) {
            querystring += `name=${name}`;
        }
        if (querystring) {
            querystring = "?" + querystring;
        }
        return axios.get('/instock' + querystring);
      },
    
      getCakeById() {
        return axios.get(`/cake`);
      },
      

}