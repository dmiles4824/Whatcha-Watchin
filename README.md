# Whatcha-Watchin
Find a movie to watch with your friends and family! A hackCWRU 2016 project.


# WARNINGS

Please, please run the server from the root directory. Otherwise, it will only work on my laptop (i think). Just don't do it!


# Things to do

/general/

Fix up README
Add more documentation

/webserver/

Way to close down server safely
Configuration file
Hash map for HTTP headers
Remove null stuff in ParseTools (change solely to WebExceptions)
Add login/authentication
Create Response interface
make enum for reply type that knows the encoding required

/webserver/js/

CSV stuff
Add javascript error handling on client side (better than just print out string error)
Proper JS response encoding (with type and error status) so it can easily be reconstituted on client side (Also help with help() method!!!)


/sqlserver/

Create sqlserver package
Download sql on RPi