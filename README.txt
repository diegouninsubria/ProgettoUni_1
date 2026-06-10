Manuale di installazione e compilazione del progetto Cinemax

Requisiti fondamentali:
- installare java sul proprio dispositivo alla versione x
Se già installato verificare la versione da terminale inserendo la seguente linea di codice:
java -version

-installare git, permetterà di poter interagire con la repository del progetto

-(opzionale) Scaricare l'ambiente di sviluppo Intellij, IDEE apposito per la gestione del linguaggio java

Passi da seguire per una corretta eseguibilità del progetto:

1)Preso il corretto link alla repository su git hub, aprire il terminale e scrivere il seguente codice:
git clone https://github.com/diegouninsubria/ProgettoUni_1.git

Permetterà di clonare la repository

2)Entrare nella cartella con la seguente linea di codice sempre sul terminale:
cd ProgettoUni_1

3)Entrai nella cartella del progetto, entrare nella cartella src dove è presente il codice sorgente di tutte le classi implementate
cd src

4)Posizionatosi nella cartella src, è il momento di compilare il codice sorgente, da notare che tutte le classi del progetto si trovano all'interno del package cinemax.
Per compilar ciò eseguire nel cmd la seguente riga di codice:
javac cinemax\*.java
Permette di compilare ogni file con estensione .java presente nel package

5)Fatto ciò si creeranno i file con estensione.class e per eseguire il codice completo bisognerà eseguire la classe main cioè Cinemax:
java cinemax.Cinemax
Tale linea di codice dovrà essere eseguita sempre all'interno della cartella src
Oppure visto che nella cartella bin è già presente l'archivio jar digitare la seguente linea di codice nel cmd:
java -jar bin/ProgettoUni_1.jar

IMPORTANTE:
java deve essere installato correttamente
per eseguire il jar bisgona essere nella cartella principale del progetto


Note:
-Assicurarsi che prima di eseguire le linee di codice nel terminale si è posizionati nella corretta directory
-Se ci sono errori, controllare Java e i percorsi della cartella

Autori
Proserpio Fabio
Demontis Tommaso
Piantalunga Diego