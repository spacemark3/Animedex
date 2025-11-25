const express = require('express');
const axios = require('axios');
const app = express();
const PORT = 3000;
app.use(express.json());

app.get('/anime/list', async (req, res) => {
    try {
        const response = await axios.get('https://api.jikan.moe/v4/top/anime');
        const animeList = response.data.data;

        const cleanedList = animeList.map(anime => ({
            id: anime.mal_id,
            title: anime.title,
            image: anime.images.jpg.image_url,
            description: anime.synopsis
        }));

        res.json(cleanedList);
    } catch (error) {
        res.status(500).json({ error: "Failed to load anime list" });
    }
});

app.get('/', (req, res) => {
    res.send('Anime API Server is running!');
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});