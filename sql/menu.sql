drop table if exists Menu;
drop table if exists Dish;
drop table if exists Ingredient;
drop table if exists Menu_Dish;
drop table if exists Dish_Ingredient;

create table Menu(
id              integer auto_increment,
menu_name 		varchar(255),
description 	varchar(255),
primary key (id)
);

insert into Menu(menu_name,description) values ('Breakfast','Variety of light snacks and continental dishes!');
insert into Menu(menu_name,description) values ('Lunch','Burgers, Burritos and Lost more!');
insert into Menu(menu_name,description) values ('Dinner','Variety of dishes to fill you up for the night!');


create table Dish(
id				integer auto_increment,
dish_name		varchar(255),
primary key (id)
);

insert into Dish(dish_name) values('Hamburger');
insert into Dish(dish_name) values('Hotdog');
insert into Dish(dish_name) values('Chicken Burrito');
insert into Dish(dish_name) values('Chicken Wings');
insert into Dish(dish_name) values('Steak and rice');



create table Ingredient(
id 				integer auto_increment,
ingredient_name varchar(255),
price			integer,
primary key (id)
);

insert into Ingredient(ingredient_name,price) values('Cheese',5);
insert into Ingredient(ingredient_name,price) values('Sausage',10);
insert into Ingredient(ingredient_name,price) values('Patty',7);
insert into Ingredient(ingredient_name,price) values('Chicken Wings',3);
insert into Ingredient(ingredient_name,price) values('Marinated Steak',25);
insert into Ingredient(ingredient_name,price) values('Rice',20);
insert into Ingredient(ingredient_name,price) values('Buns',4);
insert into Ingredient(ingredient_name,price) values('Tortilla',2);
insert into Ingredient(ingredient_name,price) values('Ketchup',8);



create table Menu_Dish(
Menu_id integer references Menu(id),
Dish_id integer references Dish(id),
foreign key(Menu_id) references Menu(id),
foreign key(Dish_id) references Dish(id)
);

insert into Menu_Dish values(1,1);
insert into Menu_Dish values(1,2);
insert into Menu_Dish values(2,3);
insert into Menu_Dish values(2,5);
insert into Menu_Dish values(3,4);



create table Dish_Ingredient(
Dish_id integer references Dish(id),
Ingredient_id integer references Ingredient(id),
Ingredient_amount integer,
foreign key(Dish_id) references Dish(id),
foreign key(Ingredient_id) references Ingredient(id)
);

insert into Dish_Ingredient values(1,1,1);
insert into Dish_Ingredient values(1,3,2);
insert into Dish_Ingredient values(1,9,3);
insert into Dish_Ingredient values(2,2,4);
insert into Dish_Ingredient values(2,7,2);
insert into Dish_Ingredient values(2,9,8);
insert into Dish_Ingredient values(3,2,4);
insert into Dish_Ingredient values(3,5,8);
insert into Dish_Ingredient values(3,6,4);
insert into Dish_Ingredient values(3,8,7);
insert into Dish_Ingredient values(4,4,6);
insert into Dish_Ingredient values(5,5,3);
insert into Dish_Ingredient values(5,6,2);