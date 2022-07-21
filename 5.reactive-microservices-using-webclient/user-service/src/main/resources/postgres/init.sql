--DROP TABLE public.user_transaction CASCADE;
-- DROP TABLE public.users CASCADE;


CREATE TABLE public.users (
	id serial NOT NULL,
	"name" varchar NULL,
	balance int4 NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
);



CREATE TABLE public.user_transaction (
	id serial NOT NULL,
	user_id int8 NULL,
	amount int4 NULL,
	transaction_date timestamp NULL,
	CONSTRAINT user_transaction_pk PRIMARY KEY (id),
	CONSTRAINT user_transaction_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE
);


INSERT INTO public.users ("name", balance) VALUES('sam', 1000);
INSERT INTO public.users ("name", balance) VALUES('mike', 1200);
INSERT INTO public.users ("name", balance) VALUES('jake', 800);
INSERT INTO public.users ("name", balance) VALUES('marshal', 2000);
