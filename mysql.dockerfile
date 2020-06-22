FROM mysql:8

COPY ./mysqlscript.sql /docker-entrypoint-initdb.d/
EXPOSE 3306