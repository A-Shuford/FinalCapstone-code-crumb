
      <!----
      <h2 id="login-message" v-if="this.$store.state.token == ''">
      Please <router-link v-bind:to="{name:'login'}">login</router-link> to place an order.
      </h2> //---->
      
    <template>
        <div id="customorder">
            <h1>Custom Order</h1>
            <h2>Please fill your contact information</h2>
            
              <form @submit.prevent="submitOrder">
                <div>
                    <label for="name">Name:</label>
                    <input type="text" v-model="name" id="name">
                </div>
                <div>
                    <label for="email">Email:</label>
                    <input type="email" v-model="email" id="email">
                </div>
                <div>
                    <label for="phone">Phone:</label>
                    <input type="tel" v-model="phone" id="phone">   
                </div>
                <div>
                    <label for="address">Address:</label>
                    <input type="text" v-model="address" id="address">
                </div>
                <div>
                    <label for="city">City:</label>
                    <input type="text" v-model="city" id="city">
                </div>
                <div>
                    <label for="state">State:</label>
                    <input type="text" v-model="state" id="state">      
                </div>
                <div>
                    <label for="zip">Zip:</label>
                    <input type="text" v-model="zip" id="zip">              
                </div>
               
                <div>
                    <h3>Our cakes are made with the finest ingredients and are made to order.</h3>
                </div>

                <div>
                    <label for="cake_Name">Custom Cake Name:</label>
                    <input type="text" v-model="cake_Name" id="cake_Name">
                </div>

                <div>
                    <h2>Please select the size, flavor, frosting, and filling for your cake.</h2>
                </div>
                <div>
                    <label for="cake_style">Cake Style:</label>
                    <select v-model="cake_style" id="cake_style">
                        <option value="5 inch diameter">Small - 5 inch diameter</option>
                        <option value="9 inch diameter">Medium - 9 inch diameter</option>
                        <option value="12 inch diameter">Large - 12 inch diameter </option>
                        <option value="one fourth sheetcake">Small - 1/4 Sheet Cake</option>
                        <option value="half sheet cake">Medium - 1/2 Sheet Cake</option>
                        <option value="full sheet cake">Large - Full Sheet Cake</option>
                        <option value="eight cupcakes">Small - 8 Cupcakes</option>
                        <option value="twelve cupcakes">Medium - 12 Cupcakes</option>
                        <option value="sixteen cupcakes">Large - 16 Cupcakes</option>
                    </select>
                    </div>
                    <div>
                    <label for="flavor">Cake Flavor:</label>
                    <select v-model="cakeflavor" id="cakeflavor">
                        <option value="butterscotch">Butterscotch</option>
                        <option value="vanilla">Vanilla</option>
                        <option value="chocolate">Chocolate</option>
                        <option value="lemon">Lemon</option>
                        <option value="cookies and cream">Cookies and Cream</option>
                        <option value="funfetti">Funfetti</option>
                        <option value="pumpkin">Pumpkin</option>
                        <option value="key lime">Key Lime</option>   
                    </select>
                </div>
                <div>
                    <label for="frosting">Frosting:</label>
                    <select v-model="frosting" id="Frosting">
                        <option value="vanilla">Vanilla</option>
                        <option value="chocolate ganache">Chocolate Ganache</option>
                        <option value="coffee">Coffee</option>
                        <option value="strawberry">Strawberry</option>
                        <option value="mint">Mint</option>
                        <option value="cream cheese">Cream Cheese</option>                          
                </select>
            </div>
                <div>
                    <label for="filling">Cake Filling:</label>
                    <select v-model="cakeToppings" id="Filling">
                        <option value="apple jelly">Apple Jelly</option>
                        <option value="raspberry jelly">Raspberry Jelly</option>
                        <option value="vanilla custard">Vanilla Custard</option>
                        <option value="chocolate custard">Chocolate Custard</option>
                        <option value="cookie dough">Cookie Dough</option>
                        <option value="caramel">Caramel</option>
                        <option value="peanut butter">Peanut Butter</option>
                    </select>
                </div>
                <div>
                    <h2>Please select Pick Up Date and Time</h2>
                    <label for="pick up date">Pick Up Date:</label>
                    <input type="date" v-model="pickupdate" id="pickupdate">
                </div>
                    <div>
                    <label for="pick up time">Pick Up Time:</label>
                    <input type="time" v-model="pickupTime" id="pickupTime">
                </div>
                <div>
                    <h2>Please provide any additional notes or instructions. </h2>
                    <p>If you would like any sayings like Congratulations or Happy Birthday.</p>
                    <p>Please double check the spelling of any names!</p>     
                    <label for="additionalNotes">Additional Notes:</label>
                    <textarea v-model="additionalNotes" id="additionalNotes"></textarea>
                </div>
                <div>
                <button type="submit" @click="submitOrder">Submit</button>
                </div>
            </form>
        </div>
 
  
    </template>