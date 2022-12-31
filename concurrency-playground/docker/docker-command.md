docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 --name mysql mysql
docker run --name myredis -d -p 6379:6379 redis
