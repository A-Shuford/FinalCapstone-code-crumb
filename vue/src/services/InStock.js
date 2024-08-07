import axios from 'axios';


export default{

    getCake(){
        return axios.get('/instock');
    }

}