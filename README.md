# wallahack
Wallapop scraper and notifier

Usage example:
java -jar ./build/libs/wallhack-1.0.jar -keyword "marco fotos" -limit 5 -max 500 -lat 40.41877 -long -3.69622

Options:
 -cat VAL       : Category e.g: 12345
 -keyword VAL   : Keyword e.g: casita
 -lat VAL       : Latitude e.g: 41.398077; See http://mondeca.com/index.php/en/a
                  ny-place-en
 -limit N       : Limit e.g 10
 -longitude VAL : Longitude e.g: 2.170432; See http://mondeca.com/index.php/en/a
                  ny-place-en
 -max N         : Max price e.g 50
 -min N         : Min price e.g 0

Compile:
./gradlew clean build