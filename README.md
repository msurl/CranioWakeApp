# CranioWakeApp
Applikation die im Rahmen der Arbeit "Entwicklung einer Android App zur Erfassung kognitiver Leistungen der Patienten bei Wach OPs" entstanden ist.

## Installation der App
Eine Anleitung zur Installation auf einem Android-Gerät finden sie unter:
[Anleitung](https://mobilsicher.de/ratgeber/apps-aus-apk-datei-installieren-android-2)

## Android Gerät
Die App ist graphisch hauptsächlich auf das Samsung Galaxy S2 Tab Emulator for tests (1536 x 2048 pixels, 9.7 inches)
ausgelegt. Andere Geräte können zwar verwendet werden, jedoch sind leichte Verschiebungen in der UI möglich.

## Abstract 
Zur Entfernung von Hirntumoren oder Metastasen werden in der Klinik für Neurochirurgie in Düsseldorf Wachoperationen durchgeführt. Während der Wachoperation wird der Patient lokal betäubt, ist aber verfügbar. Der Chirurg führt verschiedene Tests durch, um zu überprüfen, ob das Sprachzentrum und weitere Hirnbereiche noch funktionieren. Um die Chirurgen bei der Testung zu unterstützen wurde die App CranioWake entwickelt. Mit der App sollen die Durchführungen von Untersuchungstests schneller, zuverlässiger und flexibler sowie die dabei erzeugten Daten eingespeichert werden. Das Ziel ist die aktuell verwendeten manuellen und folienbasierten Methoden durch die implementierte Anwendung komplett zu ersetzen. Die App umfasst elf Untersuchungstests, eine Komplikationsaufnahme sowie ein User Management und eine Möglichkeit zum Datenexport im CSV Format. Zu den implementierten Tests gehören der Stroop, Digital Span Memory, PPT, Calculus, Line Bisection, Vier Quadranten, Bilder, Token, Trail Making, Lese und Reaction Test.

## Zielgruppen der App

Primär ist die Anwendung für die folgenden Zielgruppen ausgelegt:

* **Chirurgen und Ärzte** - *leiten die Untersuchungstests und halten die gegebenen Antworten fest* 
* **Hirntumorpatienten** - *müssen die Tests durchführen* 
* **Doktoranden und wissenschaftliche Mitarbeiter** - *werten den generierten Datensatz aus*


## Übersicht der Tests

In der App sind die folgenden Untersuchungstests implementiert

* **Stroop Test** - *Die richtige Einfärbung des Wortes muss erkannt werden* 
* **Digital Span Memory Test** - *Drei Zahlen müssen in der richtigen Reihenfolge eingeprägt werden* 
* **PPT Test** - *Zwei von drei Bildern stehen in einer Relation*
* **Calculus Test** - *Eine leichte Rechenaufgabe wird berechnet*
* **Line Bisection Test** - *Der Mittelpunkt einer Linie muss lokalisiert werden*
* **Vier Quadranten Test** - *Erkenne vier Bilder ohne dabei den Blick von einem mittig platzierten Punkt zu nehmen*
* **Bilder Test** - *Bilder müssen erkannt werden*
* **Token Test** - *Token in verschiedenen Farben, Formen und Größen müssen gefunden werden*
* **Trail Making Test** - *Verbinde 10 Punkte in aufteigender Reihenfolge*
* **Lese Test** - *Ein Text wird vorgelesen*
* **Reaction Test** - *Reactionszeit wird gemessen*

## Datenbankexport

Das generierte CSV File befindet sich nach Abschluss des erfolgreichen Exports unter dem folgenden Pfad im internen Speicher der eigenen Dateien auf dem Android Endgerät.
```
Android/data/data/com.app.craniowake/files/Craniowake/backup
``` 

