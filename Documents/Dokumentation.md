# Daily Helper
Abd Algani Khrfan ak319  - Jayoung Byeun jb255 - Janosch Koutelas jk255 - Jannika Seybold js443 - Julius Steib js411 - Alessa Wiesner aw184

# Projektbeschreibung
- dreiteiliger Daily Helper mit TaskManager, Scheduler und DecisionMatrix.
- TaskManager: ToDos speichern und bearbeiten können
- Scheduler: zufällig zugeteilte Aufgaben aus dem TaskManager bekommen
- DecisionMatrix: eine Tabelle, die einem hilft, Optionen nach numerischen Werten zu vergleichen und so eine objektivere Sicht auf Entscheidungen zu bekommen (diesen Teil haben wir aus Zeitgründen leider nur angefangen und nicht fertig gestellt)


# Hauptarchitektur-Entscheidungen und Prinzipien
#### Architektur:
- Model-View-Controller:
	- gängiges Pattern um Daten zu visualisieren
	- sinnvolles Packagemanagement dadurch möglich und leicht verständlich
#### Klassen
- SOLID-Prinzipien
- Interfaces und Factories sinnvoll: 
	- Scheduler:
		- weil das der Teil ist, der am ehesten noch ausgetauscht werden würde, wenn er optimiert wird.
		- ermöglicht hier, dass der Nutzer später auch selbst zwischen verschiedenen Schedulern wählen kann
	- Task:
		- bei Tasks haben wir uns gegen eine Factory oder ein Interface entschieden, da wir auf unnötige Abstraktionen verzichtet haben, weil wir für ein kleines Gerät nur programmieren 
		- wäre unnötiges Aufblasen einer Klasse gewesen, die absehbar wirklich einfach nur das tun können soll, was sie aktuell kann

# Reflexion

#### Gruppenaufteilung
- hat am Anfang etwas gebraucht, bis wir ins Tun gekommen sind
- in Kleingruppen aufteilen hat geholfen und Aufgaben nach Priorisierung abarbeiten hat geholfen
- haben zuerst in dreier Gruppen geteilt, als das Grundgerüst dann stand haben wir je nach Größe der Aufgabe alleine oder zu zweit daran gearbeitet
- das hat gut funktioniert
- was wir anders machen würden: ein bisschen früher aufteilen, anstatt im großen Team alles zu versuchen zu lösen

### Aufgabenstellung
- wir haben unsere Zeit und unsere Motivation bezüglich des Projektes maßlos überschätzt.
- Insbesondere den MVP wollten wir sehr schnell fertig haben, bis uns klar wurde, dass wir selbst den MVP noch kleiner machen müssen
- gerade weil es in SE3 gar nicht so sehr ums Programmieren an sich geht, sondern der Fokus auf anderen Dingen liegt, mussten wir unseren Programmieranteil um einiges weiter herunterschrauben, als wir gedacht hätten
- Am Ende sind wir aber stolz und zufrieden, was da herausgekommen ist.
- was wir anders machen würden: 
	- früher "unnötige" Sachen herausschmeißen und mit etwas ganz kleinem anfangen, damit man dadurch Momentum erhält.
	- früher klarere Aufgaben verteilen
	- kleinere Aufgabenpakete machen
	- die Orientierung am Bewertungsbogen war sehr hilfreich, um sich immer wieder aufs Wesentliche zu besinnen

# Klassendiagramm


![Uml Class Diagramm](/UML Diagrams/UmlDiagrammFinal.png)


# SOLID
Die Prinzipien wurden in den folgenden Klassen umgesetzt:
#### Single Responsibility
- Task
- Scheduler
- TaskDao
- EditTaskFragment
- AddTaskFragment
- TaskManagerActivity

#### Open/Close-Prinzip
- Scheduler mit den Factories

#### Liskovsches Substitutionsprinzip
- RecyclerViewAdapter erweitert RecyclerView, statt ihn einzuschränken

#### Interface-Segregation-Prinzip
- Scheduler: haben nur die Methoden, die sie brauchen

#### Dependency-Inversion-Prinzip
- Scheduler und SchedulerFactory mit Scheduler-Interface um es erweiterbar zu halten und weitere Scheduler hinzufügen zu können

# Git Vorgehen
- Git-Flow mit Main, Develop-Branch mit Featurebranches, Production-Branch
- Featurebranches erfordern zwar eine Aufteilung im Vorhinein, aber so kann dediziert an einzelnen Features gearbeitet werden, ohne dass man anderen Leuten den Code kaputt macht
- was wir anders machen würden:
	- im Vorhinein schon die branches sauber anlegen und auf Namenskonventionen zu Beginn festlegen (eigentlich haben wir uns festgelegt, nur dann haben wir es nicht sofort angelegt und damit hat es sich doch ein bisschen anders ergeben, als besprochen)
	- bessere Branch-Pflege: CleanCode-Richtlinien bei fertigem Stand stärker berücksichtigen

# CI/CD
- Folgesteps werden nur gemacht, wenn die vorangegangenen erfolgreich waren
-> Begründung: man muss nicht die Tests laufen lassen, wenn lint schon nicht erfolgreich war
- deploy / build: nur wenn auf den Mainbranch kommt
-> builden und deployen brauchen sonst so lange, wenn man nur Integrationstests machen will, aber es noch nicht fertig ist muss nicht jedes Mal deployed werden
- Pipeline wird nur aktiviert, wenn Änderungen in relevanten Files und Ordnern erfolgt sind
-> wenn etwas in der Dokumentation oder der Readme geändert wurde, muss dafür nicht der ganze Prozess durchgegangen werden
-> Anmerkung: gerade wird die Pipeline als gefailed angezeigt, wenn etwas zum Beispiel hier in der Doku verändert wird, wenn aber eines der relevanten Files geändert wird, läuft sie ganz normal durch



# Bekannte Probleme
## Espresso-Tests
Bei einer Android-App gibt es noch eigene Test, sogenannte Espresso-Tests. Diese haben funktioniert, bis wir die Eingabe auf Dropdown-Menü umgestellt haben. Da dies erst relativ spät erfolgt ist, hatten wir nicht genug Zeit, den Fehler zu finden und zu beheben. Wir wissen aber, dass dies grundsätzlich bei einer App dabei sein sollte. Die normalen Unittests zur Datenbank und Modell funktioneren wie gehabt. 
