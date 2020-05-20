-- public.category definition
-- Drop table
-- DROP TABLE public.category;

CREATE TABLE public.category (
	id int4 NOT NULL,
	description varchar(255) NULL,
	CONSTRAINT category_pkey PRIMARY KEY (id)
);