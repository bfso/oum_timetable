This is a Kotlin Multiplatform project targeting Android, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…



# OUM TIMETABLE ---- IVO

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
