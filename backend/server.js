const express = require('express')
const app = express()
const port = 3000
const db = require('./db');

const animeRouter = require('./routes/animeRoutes')
const authRouter = require('./routes/register');
const animeCompletionRouter = require('./routes/animeCompletionRoutes');
app.use(express.json());
app.use("/anime", animeRouter)
app.use('/api/auth', authRouter);
app.use('/api',animeCompletionRouter);

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})

