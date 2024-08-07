import axios from 'axios';


export default{

    getCakes(){
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
        return axios.get('/instock' + querystring);
      },
    
      getCakeById(cakeId) {
        return axios.get(`/instock/${cakeId}`);
      },
      

}