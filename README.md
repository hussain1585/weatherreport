# weather report
# build steps
1. navigate to root folder
2. cd $project_folder ('sapientweatherreport')
3. build the project using command
4. mvn clean install

# build docker image
1. navigate to root folder
2. cd $project_folder ('sapientweatherreport')
3. build docker image using the command
4. docker build . -t hussain:dev

# execute the image on a container
1. docker run -p 8080:8080 hussain:dev

# running the application from web browser
1. browse the url 'http://localhost:8080'


# test input entries for city name
1. London,UK
2. Ahvaz,IR
3. Delhi,IN
4. Kolkata,IN
5. Delhi,IN
6. Varanasi,IN
7. Bangalore,IN
8. Mysore,IN
9. NEW YORK,USA
10. Paris,FR


