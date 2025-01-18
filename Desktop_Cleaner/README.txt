					LIMPIADOR DE ESCRITORIO

Rastrea todo tipo de archivos en un directorio específico y mantenlos organizados en otra carpeta de una manera muy sencilla.

CONFIGURACIÓN

Este proyecto requiere Python3 y PIP.  
También debes asegurarte de que tus claves SSH estén almacenadas en GitHub para la máquina desde la cual estás creando proyectos.

-----------------------

Cómo funciona

Este script se basa en:  
- Rastrear un directorio específico (por ejemplo: el escritorio). Si se agrega un nuevo archivo, moverlo.  
- Mover archivos.  
- Ejecutarse en segundo plano.  
- Sistema para organizar archivos.  
- Crear carpetas para cada categoría de tipo de archivo (por ejemplo: Imágenes, Videos, Audio, Texto, etc.).  
- Dentro de las carpetas, organizar por fecha y crear subcarpetas con la fecha como nombre, algo como:  
  **9 de julio de 2024**  
- Identificar todos los tipos de archivo para verificar qué archivo se ha agregado.  

------------------------

Instalación

1. Clona este proyecto.  
2. Navega al directorio del proyecto.  
3. Opcional: Ejecuta `python3 -m venv venv` para instalar un entorno virtual.  
4. Opcional: Para activar el entorno virtual, ejecuta `source venv/bin/activate` y para desactivarlo simplemente escribe `deactivate`.  


------------------------
Requisitos: 
- Crea dos carpetas:  
  - `Desktop` (carpeta para rastrear archivos, tu escritorio actual).  
  - `newDesktop` (nueva carpeta de destino).  
  - También puedes crear otras carpetas, pero recuerda realizar los cambios correspondientes en el script.  
- Instala `watchdog`: `pip install watchdog`.  
- Verifica que la ruta de la carpeta sea correcta.  
- Asegúrate de que en la nueva carpeta de destino existan las carpetas principales declaradas en la sección de tipos de extensión (por ejemplo: Multimedia, Imágenes, Videos, Programación, etc.). De lo contrario, el script fallará.  

---------------------------

PARA EJECUTAR EL SCRIPT:

Ejecuta: `python3 cleandesk.py`.  
Cuando el script se inicie, mueve algo a la carpeta que estás rastreando para ver los cambios.  

Solo para sistemas MacOS:**  
Si deseas ejecutarlo en segundo plano, es necesario abrir la aplicación Automator y crear una Aplicación. Haz clic en Biblioteca, luego en Utilidades, selecciona "Ejecutar script shell", escribe el comando para usar el script y guárdalo.