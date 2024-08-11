<template>
    <header-vue />
    <nav-bar-vue />
    <mascot-modal-vue />
    <div id="customorder">
        <h1>Custom Order</h1>
        <h2>Please fill your contact information</h2>

        <form @submit.prevent="submitOrder">
            <table>
                <tr>
                    <td><label for="name">Name:</label></td>
                    <td><input type="text" v-model="name" id="name" required></td>
                </tr>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="email" v-model="email" id="email" required></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone:</label></td>
                    <td><input type="tel" v-model="phone" id="phone" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h2>Our cakes are made with the finest ingredients and are made to order.</h2>
                    </td>
                </tr>
                <tr>
                    <td><label for="cake_name">Custom Cake Name:</label></td>
                    <td><input type="text" v-model="cake_name" id="cake_name" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h2>Please select the style, size, flavor, frosting, and filling for your cake.</h2>
                    </td>
                </tr>
                <tr>
                    <td><label for="cake_style">Cake Style:</label></td>
                    <td>
                        <select v-model="cake_style" id="cake_style" required>
                            <option value="Layered">Layered</option>
                            <option value="Sheet">Sheet</option>
                            <option value="Cupcakes">Cupcakes</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="cake_size">Cake Size:</label></td>
                    <td>
                        <select v-model="cake_size" id="cake_size" required>
                            <option value="Small">Small</option>
                            <option value="Medium">Medium</option>
                            <option value="Large">Large</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="cake_flavor">Cake Flavor:</label></td>
                    <td>
                        <select v-model="cake_flavor" id="cake_flavor" required>
                            <option value="Butterscotch">Butterscotch</option>
                            <option value="Vanilla">Vanilla</option>
                            <option value="Chocolate">Chocolate</option>
                            <option value="Lemon">Lemon</option>
                            <option value="Cookie_&_Cream">Cookie & Cream</option>
                            <option value="Funfetti">Funfetti</option>
                            <option value="Pumpkin">Pumpkin</option>
                            <option value="Key_Lime">Key Lime</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="cake_frosting">Cake Frosting:</label></td>
                    <td>
                        <select v-model="cake_frosting" id="cake_frosting" required>
                            <option value="Vanilla">Vanilla</option>
                            <option value="Chocolate Ganache">Chocolate Ganache</option>
                            <option value="Coffee">Coffee</option>
                            <option value="Strawberry">Strawberry</option>
                            <option value="Mint">Mint</option>
                            <option value="Cream_Cheese">Cream Cheese</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="cake_filling">Cake Filling:</label></td>
                    <td>
                        <select v-model="cake_filling" id="cake_filling">
                            <option value="Apple Jelly">Apple Jelly</option>
                            <option value="Raspberry Jelly">Raspberry Jelly</option>
                            <option value="Vanilla Custard">Vanilla Custard</option>
                            <option value="Chocolate Custard">Chocolate Custard</option>
                            <option value="Cookie Dough">Cookie Dough</option>
                            <option value="Caramel">Caramel</option>
                            <option value="Peanut Butter">Peanut Butter</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <h2>Please select Pick Up Date and Time</h2>
                    </td>
                </tr>
                <tr>
                    <td><label for="pickup_date">Pick Up Date:</label></td>
                    <td><input type="date" v-model="pickup_date" id="pickup_date" required></td>
                </tr>
                <tr>
                    <td><label for="pickup_time">Pick Up Time:</label></td>
                    <td><input type="time" v-model="pickup_time" id="pickup_time" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <p>If you would like any sayings like Congratulations or Happy Birthday.</p>
                        <p>Please double check the spelling of any names!</p>
                        <label for="writing">Writing on the cake:</label>
                        <textarea v-model="writing" id="writing"></textarea>
                        
                        <p v-if="hasWriting">There is an additional fee for writing on the cake</p>
                        
                        <h2>Please provide any additional notes or instructions.</h2>
                        <label for="additionalNotes">Additional Notes:</label>
                        <textarea v-model="additional_notes" id="additionalNotes"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit">Submit</button>
                    </td>
                </tr>
            </table>
        </form>
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
            name: '',
            email: '',
            phone: '',
            cake_name: '',
            cake_style: '',
            cake_size: '',
            cake_flavor: '',
            cake_frosting: '',
            cake_filling: '',
            pickup_date: '',
            pickup_time: '',
            writing: '', 
            additional_notes: ''
        };
    },
    computed: {
        hasWriting() {
            return this.writing.trim().length > 0;
        },
        isShopOpen() {
            const selectedDate = new Date(this.pickup_date);
            const day = selectedDate.getDay(); 
            const time = this.pickup_time;

            if (day === 1) { 
                return false;
            }

            const [hours, minutes] = time.split(':').map(Number);
            return hours >= 8 && (hours < 21 || (hours === 21 && minutes === 0));
        }
    },
    methods: {
        async submitOrder() {
            if (!this.isShopOpen) {
                alert('The selected pickup time is outside our operating hours (8 AM to 9 PM) or the shop is closed on Monday. Please select a different time.');
                return;
            }

            try {
                const response = await fetch('http://localhost:9000/api/cart_item', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        name: this.name,
                        email: this.email,
                        phone: this.phone,
                        cake_name: this.cake_name,
                        cake_style: this.cake_style,
                        cake_size: this.cake_size,
                        cake_flavor: this.cake_flavor,
                        cake_frosting: this.cake_frosting,
                        cake_filling: this.cake_filling,
                        pickup_date: this.pickup_date,
                        pickup_time: this.pickup_time,
                        writing: this.writing,
                        additional_notes: this.additional_notes,
                    }),
                });

                if (!response.ok) {
                    throw new Error('Failed to submit order');
                }

                const data = await response.json();
                console.log('Order submitted:', data);
                alert('Order placed successfully!');
            } catch (error) {
                console.error('Error submitting order:', error);
                alert('There was an error placing your order. Please try again.');
            }

            if (this.hasWriting) {
                console.log("Additional fee applies for writing on the cake.");
            }
        }
    }
};
</script>

<style scoped>
#customorder {
    margin: 20px auto;
    padding: 20px;
    background-color: #FBECEB;
    border-radius: 8px;
    max-width: 600px;
}

table {
    width: 100%;
    border-spacing: 10px;
}

table td {
    padding: 10px;
}

h1 {
    text-align: center;
}

h2 {
    text-align: center;
}

p {
    margin: 5px 0; 
    text-align: center;
}

label {
    display: block;
    margin-bottom: 5px; 
    font-weight: bold;
}

input,
select,
textarea {
    width: calc(100% - 10px); 
    padding: 10px; 
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    background-color: #8C3F09;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    width: 100%;
    text-align: center;
}

button:hover {
    background-color: #921A39;
}
</style>
