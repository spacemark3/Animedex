# 

# 

# 

# **Animedex**

*Corso ITS Software Architect Specialist \- Sez. A*

**![][image1]**

# 

**Progetto a cura di:**  
Mark Andro Guevarra

# 

Il progetto consiste nella realizzazione di un’applicazione mobile nativa per Android, sviluppato per tutti gli appassionati di animazione giapponese (anime)

L’obiettivo principale è duplice: offrire un enciclopedia di anime e fornire agli utenti uno strumento semplice per monitorare i contenuti che hanno già visto.

L’app è rappresentata dall’integrazione di **API Jikan**, che consente di popolare la schermata principale con un catalogo di anime,con informazioni fondamentali come la trama, le valutazioni, e altri dettagli rilevanti.   
L’interfaccia è progettata per favorire la scoperta e la condivisione: con un semplice tocco su un titolo, l’utente accede a una scheda dettagliata che rende l’app una vera e propria risorsa informativa completa.

Il vero valore aggiunto risiede però nel **sistema di tracciamento personale**. Dopo una rapida registrazione o login, l’utente può accedere a un pannello privato dedicato alla gestione dei propri progressi. Tramite un pratico pulsante *“Completato”* presente nella pagina di ogni anime, è possibile archiviare i titoli già completati. Il sistema, oltre a salvare i dati, elabora automaticamente **statistiche personalizzate**: il numero totale di anime completati, il conteggio complessivo degli episodi visualizzati e una stima delle ore totali dedicate alla visione.

# 

# 

# 

# 

# 

# 

# 

## **Requisiti funzionali**

**1\. Gestione Utenti:**   
**Funzionalità di AUTENTICAZIONE per accedere** 

1. L’utente deve potersi registrare fornendo **username** e **password**  
2. L’utente deve potersi autenticare **LOGIN**  
3. L’utente ha un pannello personale **DASHBOARD**

**2\. Visualizzazione degli anime (JIKAN API)**  
**L’app mostra un elenco di anime dalla Jikan API sulla schermata principale:**

Ogni elemento nell’elenco deve mostrare  
**\- TITOLO DELL’ANIME**  
**\- IMMAGINE DI ANTEPRIMA (THUMBNAIL)**  
**\- RATING** generale

**3\. Tracciamento personale:**  
**Un sistema di tracciamento che permetta di salvare gli anime nella nostra dashboard personale. Il tracciamento avverrà salvando i dati su un database**

**PULSANTE \[ADD\]**  
a. L’utente autenticato deve vedere un pulsante **“ADD”** nella schermata dettagli, quando premuto:

- Salva l’ID univoco dell’anime nel database personale  
- Registra l’anime come “Completato”  
- Registra il numero totale di episodi dell’anime salvato  
- Aggiorna immediatamento le statistiche personali

**PANNELLO PERSONALE (DASHBOARD)**  
a. L’utente deve visualizzare in tempo reale le seguenti statistiche:

- Totale anime visti: Contatore degli anime salvati  
- Totale episodi visti: somma del numero totale di episodi di tutti gli anime salvati  
- Tempo speso: calcolo totale delle ore spese guardando anime

##  **ATTORI:**

- **Utente**  
- **Sistema di Autenticazione**  
- **API Jikan**


## **Casi d’uso:**

UTENTE

- L’utente si registra  
- L’utente accede alla propria dashboard  
- L’utente visualizza le statistiche personali  
- L’utente consulta il catalogo anime  
- L’utente visualizza i dettagli dell’anime  
- L’utente aggiunge gli anime completati alla propria dashboard con un bottone

## 

[image1]: <data:image/png;base64,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>
