Η γλώσσα ενός μαθητή δεν θα πρέπει να είναι ένας παράγοντας που θα τον αποτρέπει να συνεργαστεί
με άλλους.Με ενα chat(βάση σε mysql),με την βοήθεια των  websockets και
κωδικα γραμμένο σε java (spring framework) το οποίο θα μεταφράζει τα μηνύματα στην επιθυμητή
γλώσσα του καθενός μπορούμε να εξαλείψουμε αυτό το πρόβλημα (το front end του
Chat θα είναι γραμμένο σε JavaScript).
To api που θα χρησιμοποιηθεί για την μετάφραση:
https://rapidapi.com/systran/api/systran-io-translation-and-nlp/details

Youtube Link:

https://www.youtube.com/watch?v=vruzbPvbWcQ&fbclid=IwAR14ol0H5ZfPNlciY9zB2J79H-IZANLGZzRJAbHVAmFIm66jHrebjAxsd28

-src/main/java/com.example.messagingstompwebsocket
	- /api:  μετάφραση μηνυμάτων
	- /configuration:  websocket configuration 
	- Database : Java classes/entities που αποτέλουν tables στην Mysql Καθώς και controllers που χειρίζονται την διαχείρηση μηνυμάτων και χρηστών και τα αντίστοιχα repositories
-src/main/java/resources/application.properties καθορίζονται οι ρυθμίσεις για την σύνδεση με την βάση mysql

Επίσης χρησιμοποιήθηκε το java sdk:1.8 και το gradle 6.2.2 ,
Έχοντας εγκαταστήσει τα παραπάνω τρέχουμε gradle bootRun για το Build/Run.Για την μετάφραση τελικά χρησιμοποιήθηκε αυτό το api https://rapidapi.com/translated/api/mymemory-translation-memory.
