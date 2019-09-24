##Autor: Błażej Woźniak 301740

**Aplikacja jest napisana w jezyku JAVA oraz implementuje ona projekt S12 "Projekt S12. Przeszukiwanie poinformowane. Problem „komiwojażera”. A*" Zmienne oraz metody oraz wszlkie programowalne aspkety są wykonane w języku angielskim, zgodnie z profesora przyzwoleniem z pierwszych zajęć stacjonarnych.**


*dane wejściowe podjamey w pliku typu input (domyslnie file.txt) NAZWA , WSPÓŁRZĘDNA(X), WSPÓŁRZĘDNA(Y)*


# Instrukcja uruchomienia projektu:

- musimy wpisać w linie poleceń, włączoną w katalogu src poniższą komędę w celu skomplilowania projektu.
		- zmieniamy loklaizacje CMD na src
		- wpisujemy: javac *.java

- Do uruchomienia projektu zostało przygotowane generyczne polecenie, które możemy edytować. generyczny wygląd polecenia:

``` bash
java tsp -t <task> -s <start_node> -i <input_file> -op <output_path> -ol <output_log>
```

#### parametr <task> dla nas to wybór użycia heurystyki:

- Single Line Distance (SLD)
- Minimal Spanning Three (MST)

1. `<input_file>`  nazwa pliku który chcemy przekazać w inpucie.
2. `<output_path> `nazwa pliku w którym ma zostac zapisana znaleziona ścieżka przez algorytm.
3. `<output_log>` nazwa pliku w którym maja zostac zapisane logi, czyli wszelkie rozmyslania algorytmu.

##### Domyślny przykład komendy, która stosowałem, oraz która uwzględnia nazwy plików aktualnie utowrzone w projekcie:

dla

 - SLD 
 ```bash
java tsp -t 2 -s "a" -i "file.txt" -op "output_path.txt" -ol "output_log.txt"
```

- MST 
```bash
java tsp -t 2 -s "a" -i "file.txt" -op "output_path.txt" -ol "output_log.txt"
```



