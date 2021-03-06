Android App for GPS information retrieval and remote monitoring

This app is hosted on Heroku. 
http://secret-river-4905.herokuapp.com/Log.php

Heroku was chosen because it's free, reliable and it provides free hosting. This makes the user's job easier without having to download a WAMP/XAMPP server and connect to the localhost and go through that hassle. The database is deployed on the cloud, and the data can be cleared by redirecting toward the Data.php page. It removes all tables, and creates new tables with new logins. 

There is a php file which sets up the tables and the admin login credentials. The password is not hashed. 

A MySQLi database is created on the Heroku cloud. The login and password are admin and password. The app that should show up is called LoginActivity. This enables the users to create unique userID which is then passed through JSON into the MySQL table form which we can perform our queries. 


The LoginActivity page redirects to the AndroidGPS page. THe correct device drivers need to be installed. This app was tested on an HTC One M8 device. Since the GPS would not make sense on a virtual device, this was developed for a native app. 

There are several PHP files as well as the Android files. THere is an RSA key which was used to deploy the application on Heroku. 

It is imperitive that the app work first, (hopefully by then, the Data.php which creates the database tables is already been executed), and the login page is Log.php. 

The google map which is displayed is built on markers provided by the Google API. The android app sends batches of 20 resultant rows at a time. 

The Android app registers for the Location Service, and based on our movements , we get events. These events are appended to a JSON Latitude and Longitude, which are part of a JSON array. It also includes phone ID, userID, Timestamp(based on current data) and this is parsed through the Welcome.php webpage. THe AndroidTest.php file also is used to ensure that there are no errors in the file.

The table which shows up on the admin page , is the list of the Phone IDs, userID, by time and date - with the latitude and longitude.This was done to give a more comprehensive view of the entire system rather than just map the co-ordinates. 


References:

https://devcenter.heroku.com/articles/getting-started-with-php
https://getcomposer.org/doc/01-basic-usage.md#composer-lock-the-lock-file
https://devcenter.heroku.com/articles/dynos

https://devcenter.heroku.com/articles/cleardb
http://stackoverflow.com/questions/13825108/undefined-function-mysql-connect

http://www.rackspace.com/knowledge_center/article/mysql-connect-to-your-database-remotely
https://toolbelt.heroku.com/windowshttp://stackoverflow.com/questions/159255/what-is-the-ideal-data-type-to-use-when-storing-latitude-longitudes-in-a-mysql

http://developer.android.com/tools/extras/oem-usb.html#Drivers
http://developer.android.com/guide/topics/location/strategies.html


Website: http://secret-river-4905.herokuapp.com/Log.php