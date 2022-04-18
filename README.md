<!-- @format -->

# CanyonTrails

# History

This is a quick project I put together in graduate school to display GPS tracks on a map, along with descriptions and photos of hikes.

** This project is unmaintained and likely is using outdated and insecure dependencies. **

# Usage

To start this application, make sure Maven 3 is installed, and then just run `mvn tomcat7:run`.

If you want to compile a WAR file, run `mvn war:war`.

You can also use Docker, by building the project `docker build -t canyontrails .` and then running it with port 8080 exposed: `docker run -ti -p8080:8080 canyontrails`. You can then access the site at http://localhost:8080/CanyonTrails.
