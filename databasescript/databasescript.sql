Create TABLE product(
	id SERIAL PRIMARY KEY,
	name VARCHAR(20) NOT null,
	category VARCHAR(20) Not null,
	unit BIGINT NOT null ,
	measure BIGINT NOT null ,
	purchasePrice BIGINT NOT null ,
	salePrice BIGINT NOT null ,
	description VARCHAR(20) NOT null 
	
					 );

Insert into product("name",category,unit,measure,purchaseprice,saleprice,description) values('Edzopolo','Póló',5,400,3000,5000,'Nagyon jó edzős póló')