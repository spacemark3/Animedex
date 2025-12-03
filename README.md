# **Animedex**
Il progetto consiste nella realizzazione di un’applicazione mobile nativa per Android, sviluppato per tutti gli appassionati di animazione giapponese (anime)
L’obiettivo principale è duplice: offrire un enciclopedia di anime e fornire agli utenti uno strumento semplice per monitorare i contenuti che hanno già visto.

#**Funzionalità**
Catalogo anime tramite API Jikan
Sistema per segnare gli anime come completati
Sistema di tracciamento di ore e serie completate (personal score)
Sistema di ricerca
Backend Node + Express per la gestione delle API
Mobile Android Java
Retrofit per le richieste HTTP

**Cartella Resources_animedex**
  Wireframe - bozza semplificata dell'interfaccia, senza colori, immagini o stili elaborati
  Design - versione grafica finale dell'interfaccia (le immagini recuperate direttamente dall'API Jikan che ho utilizzato nel progetto)

**Cartella app** - contiene tutto il codice sorgente e le risorse dell'applicazione

**Cartella backend** - contiene il server node express per le chiamate API

***
**PREREQUISITI**
Node.js
Android Studio
NPM o Yarn

**Backend**
1. entra nella cartella backend
2. Installa le dipendenze: npm install
3. Avvia il server: npm start
4. Il server sarà disponibile all'indirizzo: http://localhost:3000

**App android**
1. apri Android Studio
2. Seleziona Open Project e carica la cartella "app"
3. Attendi la sincronizzazione di Gradle
4. Avvia l'app su un emulatore o dispositivo fisico

Nota: Se utilizzi un dispositivo fisico per testare l’app, sostituisci localhost con l’indirizzo IP del computer sulla rete locale.
