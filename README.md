# Facharbeit

Das Thema der Facharbeit war es einen echten Zufallsgenerator zu schreiben, dazu sollte ein Foto mit einem Raspberry Pi von einem Würfel gemacht werden, nachdem dieser gewürfelt wurde und das Bild sollte dann mit einem Java Programm analysiert werden und in der Konsole das Ergebnis des Wurfes ausgegeben werden.
Im Verzeichnis Beispielbilder können die Demo-Bilder von einem Würfel verwendet werden.

# Facharbeit V2

Diese Version war mein erster Einfall, die Taktik war es, ähnlich wie in GameOfLife die Nachbar Pixel solange zu reduzieren, bis es nur noch einen schwarzen Pixel pro Pixelhaufen gibt und diese dann in einer neuen Iteration zu zählen.
Das Problem war, dass das Programm nicht funktioniert hat, wenn die Pixelhaufen nicht gleichmäßig skaliert waren, daher habe ich einen neuen Algorithmus geschrieben, der im nachfolgenden Absatz beschrieben wird.

# Facharbeit V3

In dieser Version werden die Pixelhaufen immer noch solange verkleinert, bis ein schwarzer Pixel übrig ist, jedoch werden die Pixelhaufen rekursiv entfernt.
Auf diese Weise kann man auch unproportional skalierte Bilder als Input stellen.


