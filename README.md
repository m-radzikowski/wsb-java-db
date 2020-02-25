# WSB Java DB

Run MS SQL with AdventureWorks Docker image:

```bash
docker run --rm -e 'ACCEPT_EULA=Y' -e 'MSSQL_SA_PASSWORD=password-1234' -p 1433:1433 --name mssql robyvandamme/mssql-server-linux-adventureworks
```

Connect:

```bash
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P password-1234
```
