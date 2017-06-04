require(RJDBC)
drv<- JDBC("com.mysql.jdbc.Driver","/usr/share/java/mysql-connector-java-5.1.39.jar","")
conn<- dbConnect(drv,"jdbc:mysql://localhost/shop_db","root","")
distinct_CName<- dbGetQuery(conn,"select distinct(CName) from Products_info")
for(j in 1:nrow(distinct_CName))
{
  for(i in 1:80)
  {
    rand_qty<-floor(runif(1,70,120))
    rand_price<-floor(runif(1,100,200))
    query<-paste0("INSERT INTO Products_info values(",i+100*j,",'",distinct_CName[j,1],i+100*j,"',",rand_qty,",",rand_qty,",",rand_qty,",'",distinct_CName[j,1],"','http://cdn1.brandwiki.today/sites/12/2015/07/old-monk.jpg',1,",rand_price,")")
    dbSendUpdate(conn,query)
  }
}
