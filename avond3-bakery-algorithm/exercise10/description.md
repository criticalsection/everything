# Excercise 10

Er zijn vier cases die we uit moeten werken:
## Read from seperate locations
Als thread A en thread B tegelijk beginnen in de lock(), dan heeft er nog geen communicatie plaatsgevonden. De state is dan gelijk gebleven en je kunt dus geen garanties geven over wie als eerste de critical section in gaat.

## Write to separate locations


## Read from same location
Zie "read from separate locations"

## Write to same location
Als de eerste instructie een schrijf-actie is, dan overschrijft thread B de data van A op het moment dat deze de lock() binnenkomt. Thread A zou kunnen besluiten om de critical section binnen te gaan op het moment dat die vlag gewijzigd is, want dan is A eerder. Maar, als B niets doet, dan komt A nooit aan de beurt. Hetzelfde geldt voor B.
