#!bin/bash
#ensin siirrytään change directory komennolla target hakemistoon
cd target
#suoritetaan maven ohjelmalla ohjelman paketointi yhdeksi 'exe' tiedoksia
./mvmn package -DskipTests
#siirretään paikallisesti tehty jar pilveen scp: komennolla
scp bookstore-0.0.1-SNAPSHOT.jar dennisb@softala.haaga-helia.fi:
#ajetaan sovellus pilvessä
ssh dennisb@softala.haaga-helia.fi "java -jar bookstore-0.0.1-SNAPSHOT"