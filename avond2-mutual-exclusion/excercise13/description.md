# Deel 1: bewijs mutual exclusion

Bewijs bij inductie: als het geldt voor een leaf, en het geldt voor een leaf -1 (richting root), dan geldt het voor de hele boom.

Dat het geldt voor een leaf, is gegeven, want dat is Peterson's Algorithm: in een leaf zitten twee threads.

Voor leaf -1 (richting root) geldt: de twee threads die strijden hebben allebei gewonnen in de onderliggende leafs (siblings). Er zijn dus wederom maximaal twee threads. Volgens Peterson wint er wederom maar 1.

Uiteindelijk belanden we bij root, alwaar de winnaars van alle onderliggende leafs bijeenkomen, en er 1 wint.
