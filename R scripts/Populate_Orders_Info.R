require(RJDBC)
drv<- JDBC("com.mysql.jdbc.Driver","/usr/share/java/mysql-connector-java-5.1.39.jar","")
conn<- dbConnect(drv,"jdbc:mysql://localhost/shop_db","root","")
distinct_CID<- dbGetQuery(conn,"select CID from Customer_Info")
distinct_Products<- dbGetQuery(conn,"select distinct(PID) from Products_info")
for(i in 1:200)
{
    rand_date<-floor(runif(1,1,31))
    rand_cust<-distinct_CID[floor(runif(1,1,nrow(distinct_CID)+1)),1]
    #rand_product<-distinct_Products[floor(runif(1,1,nrow(distinct_Products)+1)),1]
    rand_product<-distinct_Products[floor(runif(1,1,6)),1]
    rand_qty_ordered<-floor(runif(1,70,120))
    rand_qty_required<-rand_qty_ordered+floor(runif(1,0,30))
    rand_status<-floor(runif(1,0,2))
    query<-paste0("INSERT INTO Order_Info values(null,",rand_product,",",rand_cust,",",rand_qty_ordered,",",rand_qty_required,",'2017-06-",rand_date,"',",rand_status,")")
    dbSendUpdate(conn,query)
}

