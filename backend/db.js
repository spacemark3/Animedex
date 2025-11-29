const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('./database.db');

db.configure('busyTimeout', 5000);

db.serialize(() => {
    db.run(`
    CREATE TABLE IF NOT EXISTS users (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      username TEXT UNIQUE NOT NULL,
      password TEXT NOT NULL,
      created_at DATETIME DEFAULT CURRENT_TIMESTAMP
    )
  `);

    db.run(`
    CREATE TABLE IF NOT EXISTS completed_anime (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      user_id INTEGER NOT NULL,
      anime_id INTEGER NOT NULL,
      title TEXT NOT NULL,
      image_url TEXT,
      score REAL,
      episodes INTEGER,
      added_at DATETIME DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY (user_id) REFERENCES users(id),
      UNIQUE(user_id, anime_id)
    )
  `);

});
module.exports = db;