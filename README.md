Harkkatyö Turun yliopiston Soveltavat projektit -kurssiin

Työparina Janek Tuisk (github: jTuisk)

![Picture of ui](https://github.com/tuijalei/k-nearest-neighbors-classifier/blob/main/src/main/resources/org/knn/pictures/knn-classifier-ui.jpg)

______________________________________________________________________________________________________________

Luokittelijan toiminta

Luokittelija ennustaa käyttäjän syöttämälle **IrisData**-oliolle sen tyypin kolmesta eri **IrisTypesta**: Iris-setosasta, Iris-versicolorista ja Iris-virginiasta. IrisDatalle tulee syöttää _sepalLength, sepalWidth, petalLength ja petalWidth_ -attribuuteille jokin arvo väliltä 0.1-9.9, jotta IrisData-olio on mahdollista lisätä aineistoon.  Lisäksi _arvon K_ (eli kuinka monen lähimmän naapurin mukaan luokittelu tapahtuu) voi käyttäjä määrätä itse väliltä 1-10. 

Aluksi luokittelija lataa **DataLoader**-luokan avulla iris.datan, joka sisältää 150 **IrisData**-olion mukaisen datapisteen ja luo  **CustomLineChart**-olion avulla diagrammit kuvaamaan niitä. CustomLineChart on LineChart-luokan aliluokka. Diagrammien ympyräpisteet piirretään IrisType-tyypin mukaan: Iris_SETOSA piirretään oranssina, Iris_VERSICOLOR piirretään vihreänä ja Iris_VIRGINICA piirretään violettina pisteenä. Uusi IrisData-olio piirretään CustomLineChartiin hieman tummemmalla värillä kuin koulutusaineisto on piirretty eli esim. Iris_SETOSA piirretään tumman oranssina ympyränä.

Uusi IrisData lisätään aineistoon **Add**-buttonin avulla.Luokittelija ennustaa käyttäjän syöttämälle **IrisData**-oliolle sen tyypin kolmesta eri **IrisTypesta**: Iris-setosasta, Iris-versicolorista ja Iris-virginiasta. IrisDatalle tulee syöttää _sepalLength, sepalWidth, petalLength ja petalWidth_ -attribuuteille jokin arvo väliltä 0.1-9.9, jotta IrisData-olio on mahdollista lisätä aineistoon.  Lisäksi _arvon K_ (eli kuinka monen lähimmän naapurin mukaan luokittelu tapahtuu) voi käyttäjä määrätä itse väliltä 1-10. 

Aluksi luokittelija lataa **DataLoader**-luokan avulla iris.datan, joka sisältää 150 **IrisData**-olion mukaisen datapisteen ja luo  **CustomLineChart**-olion avulla diagrammit kuvaamaan niitä. CustomLineChart on LineChart-luokan aliluokka. Diagrammien ympyräpisteet piirretään IrisType-tyypin mukaan: Iris_SETOSA piirretään oranssina, Iris_VERSICOLOR piirretään vihreänä ja Iris_VIRGINICA piirretään violettina pisteenä. Uusi IrisData-olio piirretään CustomLineChartiin hieman tummemmalla värillä kuin koulutusaineisto on piirretty eli esim. Iris_SETOSA piirretään tumman oranssina ympyränä.

Uusi IrisData lisätään aineistoon **Add**-buttonin avulla.
