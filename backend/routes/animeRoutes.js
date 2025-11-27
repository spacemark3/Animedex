const express = require('express');
const axios = require('axios');
const router = express.Router();

router.get('/', async (req, res) => {
    try {
        const page = req.query.page || 1;
        const limit = req.query.limit || 25;

        const response = await axios.get('https://api.jikan.moe/v4/anime', {
            params: {
                page: page,
                limit: limit,
                order_by: 'score',
                sort: 'desc'
            }
        });

        const animeList = response.data.data.map(anime => ({
            id: anime.mal_id,
            title: anime.title,
            image: anime.images.jpg.image_url,
            score: anime.score
        }));

        res.json({
            success: true,
            data: animeList,
            pagination: response.data.pagination
        });

    } catch (error) {
        console.error('Error fetching anime:', error.message);
        res.status(500).json({
            success: false,
            error: 'Failed to fetch anime data',
            message: error.message
        });
    }
});

router.get('/search', async (req, res) => {
    try {
        const query = req.query.q;

        if (!query) {
            return res.status(400).json({
                success: false,
                error: 'Search query is required'
            });
        }

        const response = await axios.get('https://api.jikan.moe/v4/anime', {
            params: {
                q: query,
                limit: 25
            }
        });

        const animeList = response.data.data.map(anime => ({
            id: anime.mal_id,
            title: anime.title,
            image: anime.images.jpg.image_url,
            score: anime.score
        }));

        res.json({
            success: true,
            data: animeList
        });

    } catch (error) {
        console.error('Error searching anime:', error.message);
        res.status(500).json({
            success: false,
            error: 'Failed to search anime',
            message: error.message
        });
    }
});

router.get('/:id', async (req, res) => {
    try {
        const { id } = req.params;

        const response = await axios.get(`https://api.jikan.moe/v4/anime/${id}`);

        const anime = {
            id: response.data.data.mal_id,
            title: response.data.data.title,
            image: response.data.data.images.jpg.image_url,
            score: response.data.data.score,
            synopsis: response.data.data.synopsis,
            year: response.data.data.year,
            status: response.data.data.status
        };

        res.json({
            success: true,
            data: anime
        });

    } catch (error) {
        console.error('Error fetching anime details:', error.message);
        res.status(500).json({
            success: false,
            error: 'Failed to fetch anime details',
            message: error.message
        });
    }
});

module.exports = router;