const express = require('express');
const router = express.Router();
const db = require('../db');

router.post('/completed', (req, res) => {
    try {
        const { user_id, anime_id, title, image, score, episodes } = req.body;
        console.log("episodi: ", episodes);
        if (!user_id || !anime_id || !title) {
            return res.status(400).json({
                error: 'user_id, anime_id e title sono obbligatori'
            });
        }
        db.serialize(() => {
        db.run(
            'INSERT INTO completed_anime (user_id, anime_id, title, image, score, episodes) VALUES (?, ?, ?, ?, ?, ?)',
            [user_id, anime_id, title, image, score, episodes],
            function(err) {
                if (err) {
                    if (err.message.includes('UNIQUE')) {
                        return res.status(409).json({ error: 'Anime già nei completati' });
                    }
                    console.error('Errore DB:', err);
                    return res.status(500).json({ error: 'Errore nell\'aggiunta anime' });
                }
                res.status(201).json({
                    message: 'Anime aggiunto ai completati',
                    anime: {
                        id: this.lastID,
                        user_id,
                        anime_id,
                        title,
                        image,
                        score,
                        episodes
                    }
                });
            }
        );
        });

    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Errore del server' });
    }
});
router.post('/completed/list', (req, res) => {
    try {
        const { user_id } = req.body;

        if (!user_id) {
            return res.status(400).json({ error: 'user_id è obbligatorio' });
        }
        db.all(
            'SELECT * FROM completed_anime WHERE user_id = ? ORDER BY added_at DESC',
            [user_id],
            (err, rows) => {
                if (err) {
                    console.error('Errore DB:', err);
                    return res.status(500).json({ error: 'Errore nel recupero anime' });
                }
                const totalEpisodes = rows.reduce((sum, anime) => {
                    return sum + (anime.episodes || 0);
                }, 0);
                res.status(200).json({
                    message: 'Anime completati recuperati',
                    totalEpisodes: totalEpisodes,
                    totalAnime: rows.length,
                    completed: rows || []
                });
            }
        );
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Errore del server' });
    }
});

module.exports = router;