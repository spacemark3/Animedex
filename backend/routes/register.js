const express = require('express');
const router = express.Router();
const bcrypt = require('bcryptjs');
const db = require('../db');

router.post('/register', async (req, res) => {
    try {
        const { username, password } = req.body;
        if (!username || !password) {
            return res.status(400).json({
                error: 'Username e password sono obbligatori'
            });
        }
        if (password.length < 6) {
            return res.status(400).json({
                error: 'La password deve avere almeno 6 caratteri'
            });
        }
        db.get(
            'SELECT * FROM users WHERE username = ?',
            [username],
            async (err, row) => {
                if (err) {
                    return res.status(500).json({ error: 'Errore del server' });
                }
                if (row) {
                    return res.status(409).json({
                        error: 'Username già esistente'
                    });
                }

                try {
                    const hashedPassword = await bcrypt.hash(password, 10);
                    db.run(
                        'INSERT INTO users (username, password) VALUES (?, ?)',
                        [username, hashedPassword],
                        function(err) {
                            if (err) {
                                console.error('Errore DB:', err);
                                return res.status(500).json({ error: 'Errore nella registrazione' });
                            }

                            res.status(201).json({
                                message: 'Registrazione completata',
                                user: {
                                    id: this.lastID,
                                    username: username
                                }
                            });
                        }
                    );
                } catch (error) {
                    res.status(500).json({ error: 'Errore nella cifratura' });
                }
            }
        );

    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Errore del server' });
    }
});

module.exports = router;