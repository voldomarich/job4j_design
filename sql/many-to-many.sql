 create table follower(
     id serial primary key,
     name varchar(255)
 );
 
 create table youTubeChannel(
     id serial primary key,
     name varchar(255)
 );
 
 create table follower_youTubeChannel(
     id serial primary key,
     follower_id int references follower(id),
     youTubeChannel_id int references youTubeChannel(id)
 );
