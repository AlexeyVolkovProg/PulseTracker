create schema if not exists linkPool;


create table linkPool.chats(
    id serial primary key,
    tg_id int not null
);


create table linkPool.links(
    id serial primary key,
    l_url varchar(1000) not null check (length(trim(l_url)>=10)),
    chat_id int references linkPool.chats(id) on delete cascade
);
