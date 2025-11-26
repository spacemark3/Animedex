const express = require("express")
const router = express.Router()

router.get('/',(req, res) => {
    res.status(200).send('User list')
})
router.post('/', (req, res) => {
    res.status(201).send('Create user')
})
router.get('/new', (req, res) => {
    res.status(200).send('User new form')
})

router
    .route('/:id')
    .get((req, res,) => {
        res.status(200).send(`User details for user ${req.params.id}`)
        console.log(req.pippo)
    })
    .put((req, res) => {
        res.status(200).send(`Modify User details for user ${req.params.id}`)
    })
    .delete((req, res) => {
        res.status(200).send(`Delete user ${req.params.id}`)
    })

const users = [{name: 'Alice'}, {name: 'Bob'}, {name: 'Charlie'}]
router.param('id',(req,res,next,id) => {
    req.pippo = users[id]
    next()
})
module.exports = router