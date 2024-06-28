from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
# pip install watchdog for these packages to work

import os
import json
import time
import shutil
from datetime import datetime
from time import gmtime, strftime

folder_to_track = '/Users/luisfelipecastro/Desktop/Felipe'
extensions_folders = ''

class MyHandler(FileSystemEventHandler):
    def on_modified(self, event):
        for filename in os.listdir(folder_to_track):
            i = 1
            if filename != 'Felipe':
                # try:
                new_name = filename
                extension = 'noname'
                try:
                    extension = str(os.path.splitext(folder_to_track + '/' + filename)[1])
                    path = extensions_folders[extension]
                except Exception:
                    extension = 'noname'

                now = datetime.now()
                year = now.strftime("%Y")
                month = now.strftime("%m")

                folder_destination_path = extensions_folders[extension]

                year_exists = False
                month_exists = False
                for folder_name in os.listdir(extensions_folders[extension]):
                    if folder_name == year:
                        folder_destination_path = extensions_folders[extension] + "/" + year
                        year_exists = True
                        for folder_month in os.listdir(folder_destination_path):
                            if month == folder_month:
                                folder_destination_path = extensions_folders[extension] + "/" + year + "/" + month
                                month_exists = True
                if not year_exists:
                    os.mkdir(extensions_folders[extension] + "/" + year)
                    folder_destination_path = extensions_folders[extension] + "/" + year
                if not month_exists:
                    os.mkdir(folder_destination_path + "/" + month)
                    folder_destination_path = folder_destination_path + "/" + month

                file_exists = os.path.isfile(folder_destination_path + "/" + new_name)
                while file_exists:
                    i += 1
                    new_name = os.path.splitext(folder_to_track + '/' + filename)[0] + str(i) + \
                               os.path.splitext(folder_to_track + '/' + filename)[1]
                    new_name = new_name.split("/")[4]
                    file_exists = os.path.isfile(folder_destination_path + "/" + new_name)
                src = folder_to_track + "/" + filename

                new_name = folder_destination_path + "/" + new_name
                os.rename(src, new_name)
            # except Exception:
            #     print(filename)

    extensions_folders = {
        # No name
        'noname': "/Users/luisfelipecastro/Desktop/Felipe/Other/ETC",
        # Audio
        '.aif': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        '.cda': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        '.mid': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        '.midi': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        '.mp3': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        '.mpa': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        '.ogg': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        '.wav': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        '.wma': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        '.wpl': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        '.m3u': "/Users/luisfelipecastro/Desktop/Felipe/Media/Audio",
        # Text
        '.txt': "/Users/luisfelipecastro/Desktop/Felipe/Text/TextFiles",
        '.doc': "/Users/luisfelipecastro/Desktop/Felipe/Text/Microsoft/Word",
        '.docx': "/Users/luisfelipecastro/Desktop/Felipe/Text/Microsoft/Word",
        '.odt ': "/Users/luisfelipecastro/Desktop/Felipe/Text/TextFiles",
        '.pdf': "/Users/luisfelipecastro/Desktop/Felipe/Text/PDF",
        '.rtf': "/Users/luisfelipecastro/Desktop/Felipe/Text/TextFiles",
        '.tex': "/Users/luisfelipecastro/Desktop/Felipe/Text/TextFiles",
        '.wks ': "/Users/luisfelipecastro/Desktop/Felipe/Text/TextFiles",
        '.wps': "/Users/luisfelipecastro/Desktop/Felipe/Text/TextFiles",
        '.wpd': "/Users/luisfelipecastro/Desktop/Felipe/Text/TextFiles",
        # Video
        '.3g2': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.3gp': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.avi': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.flv': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.h264': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.m4v': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.mkv': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.mov': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.mp4': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.mpg': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.mpeg': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.rm': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.swf': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.vob': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        '.wmv': "/Users/luisfelipecastro/Desktop/Felipe/Media/Video",
        # Images
        '.ai': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.bmp': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.gif': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.ico': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.jpg': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.jpeg': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.png': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.ps': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.psd': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.svg': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.tif': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.tiff': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        '.CR2': "/Users/luisfelipecastro/Desktop/Felipe/Media/Images",
        # Internet
        '.asp': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.aspx': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.cer': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.cfm': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.cgi': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.pl': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.css': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.htm': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.js': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.jsp': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.part': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.php': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.rss': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        '.xhtml': "/Users/luisfelipecastro/Desktop/Felipe/Other/Internet",
        # Compressed
        '.7z': "/Users/luisfelipecastro/Desktop/Felipe/Other/Compressed",
        '.arj': "/Users/luisfelipecastro/Desktop/Felipe/Other/Compressed",
        '.deb': "/Users/luisfelipecastro/Desktop/Felipe/Other/Compressed",
        '.pkg': "/Users/luisfelipecastro/Desktop/Felipe/Other/Compressed",
        '.rar': "/Users/luisfelipecastro/Desktop/Felipe/Other/Compressed",
        '.rpm': "/Users/luisfelipecastro/Desktop/Felipe/Other/Compressed",
        '.tar.gz': "/Users/luisfelipecastro/Desktop/Felipe/Other/Compressed",
        '.z': "/Users/luisfelipecastro/Desktop/Felipe/Other/Compressed",
        '.zip': "/Users/luisfelipecastro/Desktop/Felipe/Other/Compressed",
        # Disc
        '.bin': "/Users/luisfelipecastro/Desktop/Felipe/Other/Disc",
        '.dmg': "/Users/luisfelipecastro/Desktop/Felipe/Other/Disc",
        '.iso': "/Users/luisfelipecastro/Desktop/Felipe/Other/Disc",
        '.toast': "/Users/luisfelipecastro/Desktop/Felipe/Other/Disc",
        '.vcd': "/Users/luisfelipecastro/Desktop/Felipe/Other/Disc",
        # Data
        '.csv': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        '.dat': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        '.db': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        '.dbf': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        '.log': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        '.mdb': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        '.sav': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        '.sql': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        '.tar': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        '.xml': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        '.json': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Databases",
        # Executables
        '.apk': "/Users/luisfelipecastro/Desktop/Felipe/Other/Executables",
        '.bat': "/Users/luisfelipecastro/Desktop/Felipe/Other/Executables",
        '.com': "/Users/luisfelipecastro/Desktop/Felipe/Other/Executables",
        '.exe': "/Users/luisfelipecastro/Desktop/Felipe/Other/Executables",
        '.gadget': "/Users/luisfelipecastro/Desktop/Felipe/Other/Executables",
        '.jar': "/Users/luisfelipecastro/Desktop/Felipe/Other/Executables",
        '.wsf': "/Users/luisfelipecastro/Desktop/Felipe/Other/Executables",
        # Fonts
        '.fnt': "/Users/luisfelipecastro/Desktop/Felipe/Other/Fonts",
        '.fon': "/Users/luisfelipecastro/Desktop/Felipe/Other/Fonts",
        '.otf': "/Users/luisfelipecastro/Desktop/Felipe/Other/Fonts",
        '.ttf': "/Users/luisfelipecastro/Desktop/Felipe/Other/Fonts",
        # Presentations
        '.key': "/Users/luisfelipecastro/Desktop/Felipe/Text/Presentations",
        '.odp': "/Users/luisfelipecastro/Desktop/Felipe/Text/Presentations",
        '.pps': "/Users/luisfelipecastro/Desktop/Felipe/Text/Presentations",
        '.ppt': "/Users/luisfelipecastro/Desktop/Felipe/Text/Presentations",
        '.pptx': "/Users/luisfelipecastro/Desktop/Felipe/Text/Presentations",
        # Programming
        '.java': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Java",
        '.r': "/Users/luisfelipecastro/Desktop/Felipe/Programming/R",
        '.py': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Python",
        '.sh': "/Users/luisfelipecastro/Desktop/Felipe/Programming/Shell",
        '.html': "/Users/luisfelipecastro/Desktop/Felipe/Programming/HTML",
        '.xhtml': "/Users/luisfelipecastro/Desktop/Felipe/Programming/HTML",
        # Spreadsheets
        '.ods': "/Users/luisfelipecastro/Desktop/Felipe/Text/Microsoft/Excel",
        '.xlr': "/Users/luisfelipecastro/Desktop/Felipe/Text/Microsoft/Excel",
        '.xls': "/Users/luisfelipecastro/Desktop/Felipe/Text/Microsoft/Excel",
        '.xlsx': "/Users/luisfelipecastro/Desktop/Felipe/Text/Microsoft/Excel",
        # System
        '.bak': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.cab': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.cfg': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.cpl': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.cur': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.dll': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.dmp': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.drv': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.icns': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.ico': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.ini': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.lnk': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.msi': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.sys': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
        '.tmp': "/Users/luisfelipecastro/Desktop/Felipe/Text/Other",
    }

    folder_to_track = '/Users/luisfelipecastro/Desktop'
    folder_destination = '/Users/luisfelipecastro/Desktop/Felipe'
event_handler = MyHandler()
observer = Observer()
observer.schedule(event_handler, folder_to_track, recursive=True)
observer.start()

try:
    while True:
        time.sleep(10)
except KeyboardInterrupt:
        observer.stop()
        observer.join()