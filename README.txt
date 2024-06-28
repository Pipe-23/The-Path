DESKTOP CLEANER

Track every type of files in a specific directory and keep them organized into another folder in such a easy way.

SET UP

This project requires Python3, PIP.
You'll also need to ensure your SSH keys are stored in Github for the machine you're creating projects from.



How it works

This script is simply based on:
Track a specific directory(e.g: desktop), if new file is added then move
move files
run in the background
System for file organization
Folders for each file type category (e.g Images, Video, Audio, Text etc.)
Within folders we need to organise by date, create subfolders with date as name something like
2024 July 9th
need to find all file types to check what file has been added


Installation

Clone this project
Navigate to this project directory
Optional: python3 -m venv venv, to install the virtual env
Optional: To activate env : source venv/bin/activate, and to deactivate it simply type deactivate
Requirements:
Create 1 folders: Desktop(folder to track files, your actual desktop) and newDesktop(new destination) or new ones created by yourself but remember to make the changes on  file script
Install watchdog, pip install watchdog
Check that the folder path is correct
Have in the new destination, the main folders declared in the extension type section(i.e: Media, Images, Videos, Programming, etc) if not the script will be fail


TO RUN THE SCRIPT: python3 cleandesk.py.
When the script starts, just move something in the folder to track to see the changes.

Only for MacOS system: if you want to run it in background, it's necessary open Automator.app and create an Application, click on Library, and then on Utilities and run shell script and type the command to use the script and save it.
