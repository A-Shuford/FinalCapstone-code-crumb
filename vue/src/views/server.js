// for custom order

const express = require('express');
const bodyParser = require('body-parser');
const { Pool } = require('pg'); 

const app = express();
const pool = new Pool({
  connectionString: 'final_capstone' 
});

app.use(bodyParser.json());

app.post('/api/orders', async (req, res) => {
  const {
    name,
    email,
    phone,
    cake_name,
    cake_style,
    cake_size,
    cake_flavor,
    cake_frosting,
    cake_filling,
    pickup_date,
    pickup_time,
    writing,
    
  } = req.body;

  try {
    // Insert the user into the users table
    const userResult = await pool.query(
      'INSERT INTO users (username, yourname, email, phone_number, password_hash, role) VALUES ($1, $2, $3, $4, $5, $6) RETURNING user_id',
      [email, name, email, phone, 'default_password_hash', 'ROLE_USER'] 
    );

    const userId = userResult.rows[0].user_id;

    // Insert the cake order into the cake table
    const cakeResult = await pool.query(
      'INSERT INTO cake (cake_name, cake_style, cake_size, cake_flavor, cake_filling, cake_frosting, has_writing, custom_text) VALUES ($1, $2, $3, $4, $5, $6, $7, $8) RETURNING cake_id',
      [cake_name, cake_style, cake_size, cake_flavor, cake_filling, cake_frosting, writing ? true : false, writing]
    );

    const cakeId = cakeResult.rows[0].cake_id;

    // Insert the cart item into the cart_item table
    await pool.query(
      'INSERT INTO cart_item (user_id, cake_id, status_id, pickup_date, pickup_time) VALUES ($1, $2, 1, $3, $4)',
      [userId, cakeId, pickup_date, pickup_time]
    );

    res.status(201).json({ message: 'Order placed successfully' });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

const port = process.env.PORT || 9000;
app.listen(port, () => console.log(`Server running on port ${port}`));
