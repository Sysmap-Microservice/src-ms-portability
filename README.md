# src-ms-portability

### Run the project image from DockerHub
To run the image of this project from the DockerHub, run this comand in your command line (Terminal or CMD):
> docker push sysmapmsteam/src-ms-portability:tagname

### Run the project image from DockerHub with Docker Compose
If you want to run this project with an automated process (like CI/CD), put the following code in your file named 'docker-compose.yaml':
```yaml
version: "3.4"

services:
  src-ms-portability:
    container_name: src-ms-portability
    image: sysmapmsteam/src-ms-portability:0.0.1-SNAPSHOT
    ports:
      - "8080:8080"
```

And, into the folder that was created the docker-compose.yaml file, run the following command (Terminal or CMD):
> docker-compose up
