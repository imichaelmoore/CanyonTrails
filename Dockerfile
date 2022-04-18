FROM maven:3-openjdk-11
COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN /bin/bash -c "mvn install"
CMD /bin/bash -c "mvn tomcat7:run"