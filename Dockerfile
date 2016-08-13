FROM maven:3.3.9-jdk-8
COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN /bin/bash -c "mvn install"