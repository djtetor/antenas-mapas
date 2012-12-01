antenas-mapas
=============

Proyecto que dibuja las antenas mas cercanas obtenidas por el api de android en un mapa

Este proyecto esta basado en un aplicacion llamada Status

utiliza la clase Telephony de Android para tomar los datos de las antenas mas cercanas al celular

despues por medio de un api en esta url

http://www.open-electronics.org/celltrack/celltxt.php

obtenemos sus datos de latitud y longitud

Ejemplo

======================= GET ======================================

 http://www.open-electronics.org/celltrack/celltxt.php?
 hex=0&mcc=222&mnc=10&lac=00010012&cid=00039309
 &lac0=00010012&cid0=00005188
 &lac1=00010012&cid1=00017252
 &lac2=00010012&cid2=00039303
 &lac3=00010012&cid3=00039301
 &lac4=00010012&cid4=00039307 
 
======================= RESULT ===================================
 45.641507,8.812476,1176&45.657612,8.80447,1061&45.657234,8.812921,1790&45.641633,8.813328,1318&45.656633,8.810523,1400&45.657416,8.813393,1346
 
 una vez que tenemos la latitud y logitud de las antenas las dibujamos en el mapa