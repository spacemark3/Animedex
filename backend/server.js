const express = require('express')
const app = express()
const port = 3000

const animeRouter = require('./routes/animeRoutes')

app.use("/anime", animeRouter)

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})

