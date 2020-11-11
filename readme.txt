------- MODE D'EMPLOI --------

/!\ Ce projet joue de la musique ! Nous vous recommandons de baisser votre son avant de l'exécuter car Java n'y va pas par le dos de la cuillère /!\

** PROJET PRINCIPAL **

- Ouvrir un terminal (bash) dans le dossier "Mandelbrot"
- Entrer la commande "java Principale". Le menu apparaît, vous pouvez dessiner des ensembles de Julia ou de Mandelbrot.
FONCTION DES PARAMETRES :
- rayon convergence : seuil d'écart à la valeur initiale au-delà duquel on considère que la suite est divergente.
- nb iterations max : numéro du terme de la suite qui, si son module appartient au disque de convergence, nous fera considérer que la suite est convergente.
- (ensembles de Julia uniquement) valeur complexe c : valeur de c dans la suite de Julia (cf. rapport).

** CLASSE "ROTATION JULIA" **

- Principe : créer une animation en faisant parcourir à c un cercle du plan complexe de rayon 0.7885.
- l'animation est générée dans un second buffer, cela prend un certain temps, vous pouvez suivre la progression depuis le terminal.
- Cette classe ne possède par d'IHM, il faut modifier le fichier RotationJulia.java pour jouer avec ses paramètres.
- Exécution : "java -Xmx3g RotationJulia"


** CLASSE "ZOOM MANDELBROT"
- Principe : créer une animation de zoom sur un point spécifique dans l'ensemble de Mandelbrot.
- Exécution : "java -Xmx3g ZoomMandelbrot" (20 minutes d'attente environ).

Note : les animations des classes RotationJulia et ZoomMandelbrot s'effectuent avec une résolution de 1280*720 pixel.

/!\ Les commandes avec l'option -Xmx3g allouent à java jusqu'à 3Go de RAM au lieu des 2 maximum par défaut /!\

