import axios from 'axios';


export default{

    getCakes(){
        return axios.get('/instock'); //WHERE STATEMENT IS EQUALS TO STANDARD
    },

    getCakesAdmin(){
        return axios.get('/admincakes');
    },

    getdetails(cakeId){
        return axios.get(`/cakes/${cakeId}`);
    },

    updateCakeAmountAvailable(cake) {
        return axios.put('/cakes/update-amount', cake);
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