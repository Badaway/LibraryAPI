CREATE TABLE authors
(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    biography character varying(255) ,
    created_at TIMESTAMP      NOT NULL ,
    dob character varying(255) ,
    name character varying(255)  NOT NULL
);

CREATE TABLE members
(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    email character varying(255)  NOT NULL UNIQUE,
    membership_date TIMESTAMP      NOT NULL ,
    name character varying(255)  NOT NULL
);

CREATE TABLE roles
(
    id bigint PRIMARY KEY  GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name smallint,
    CONSTRAINT roles_name_check CHECK (name >= 0 AND name <= 1)
);


CREATE TABLE users
(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    created_at TIMESTAMP      NOT NULL ,
    email character varying(255)  NOT NULL UNIQUE,
    name character varying(255)  NOT NULL,
    password character varying(255)  NOT NULL,
    updated_at TIMESTAMP      NOT NULL 
);

CREATE TABLE user_roles
(
    user_id integer NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT role_user FOREIGN KEY (role_id)
        REFERENCES roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_role FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);

CREATE TABLE book
(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    isbn bigint NOT NULL UNIQUE,
    published_date TIMESTAMP      NOT NULL ,
    title character varying(255)  NOT NULL,
    author_id integer NOT NULL,
    member_id integer NOT NULL,
    CONSTRAINT book_member FOREIGN KEY (member_id)
        REFERENCES members (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT book_author FOREIGN KEY (author_id)
        REFERENCES authors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
