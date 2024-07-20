create schema if not exists linkPool;


create table linkPool.chats(
    id serial primary key
);


create table linkPool.links(
    id serial primary key,
    l_url varchar(1000) not null check (length(trim(l_url)>=10))
);


create table linkPool.chat_link(
    chat_id int references linkPool.chats(id) on delete cascade,
    link_id int references linkPool.links(id) on delete cascade,
    primary key (chat_id, link_id)
);
