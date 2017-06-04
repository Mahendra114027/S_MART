require(RJDBC)
drv<- JDBC("com.mysql.jdbc.Driver","/usr/share/java/mysql-connector-java-5.1.39.jar","")
conn<- dbConnect(drv,"jdbc:mysql://localhost/shop_db","root","")

#Most Populur Product Monthly
popular_products<-dbGetQuery(conn,"SELECT PID,sum(Qty_Required),count(DISTINCT(Date)) FROM `Order_Info` group by PID order by sum(Qty_Required) desc")

#Date wise requirements
Product_Wise_DB<-dbGetQuery(conn,"SELECT PID,Date,sum(Qty_Required) FROM `Order_Info` group by PID,Date order by PID")
names(Product_Wise_DB)<-c("PID","Date","Sum")
unique_products<-unique(Product_Wise_DB$PID)
length(unique_products)
require(ggplot2)
for(i in 1:5)
{
  Per_Product_DB<-Product_Wise_DB[which(Product_Wise_DB$PID==unique_products[i]),c(2:ncol(Product_Wise_DB))]
  Per_Product_DB$Date<-c(1:nrow(Per_Product_DB))
  p=ggplot( data = Per_Product_DB, aes( Date, Sum ,group=1)) + geom_line() +labs(x = "Date", y = "Required Quantity",title = paste0("Product ",unique_products[i]," analysis"))+geom_hline(yintercept = mean(Per_Product_DB$Sum), color="blue")
  ggsave(filename=paste0("/home/pramay/Desktop/R scripts/plots/Product ",unique_products[i],".png"),plot=p)
  #dev.off()
}

