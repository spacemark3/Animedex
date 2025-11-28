const express = require('express')
const app = express()
const port = 3000
const db = require('./db');

const animeRouter = require('./routes/animeRoutes')
const authRouter = require('./routes/register');
app.use(express.json());
app.use("/anime", animeRouter)
app.use('/api/auth', authRouter);

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})

