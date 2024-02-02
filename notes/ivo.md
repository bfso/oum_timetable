# Notes - IVO

## Links

* [Anforderungen](https://sapium.gitbook.io/bfo/module/2.-lehrjahr/426/anforderungen/projekt-oum/2.-timetable)

## Anforderungen?

* persistent local data? sollen Daten auf dem Gerät gespeichert werden auch wenn die app geschlossen wird
* Wie genau soll die App ausschauen?
    * Navigations bar unten/oben?
    * welche navigationspunkte?
* Welche Struktur soll die Verbindung zum Webserver haben? Rest API (GET/POST json)? WebSocket?

## Aufbau

* Beim start der app kann der spieltag ausgewählt werden
* Die Matchdaten zum gewählten Spieltag werden zu beginn alle geladen
    * ausgenommen sind die Spielerbilder
    * Spielerbilder können auf knopfdruck vorgeladen werden
