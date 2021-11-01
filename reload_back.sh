docker-compose stop timemanager-back

cd "./timemanager-api"
mvn clean package -DskipTests
cd ".."

docker-compose up --build -d timemanager-back