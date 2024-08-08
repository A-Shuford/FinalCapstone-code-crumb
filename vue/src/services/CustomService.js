import axios from 'axios';


export default{

    getCakes(){
        return axios.get('/cakes');
    },

    getdetails(cakeId){
        return axios.get(`cake/${cakeId}`);
    },

}