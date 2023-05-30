-- public.audit_log definition

-- Drop table

-- DROP TABLE public.audit_log;

CREATE TABLE public.audit_log (
	id bigserial NOT NULL,
	http_method varchar(255) NULL,
	http_status varchar(255) NULL,
	"path" varchar(255) NULL,
	query_params varchar(255) NULL,
	request_json json NOT NULL,
	response json NULL,
	"time" timestamp NULL,
	CONSTRAINT audit_log_pkey null
);


-- public.irrigation_period definition

-- Drop table

-- DROP TABLE public.irrigation_period;

CREATE TABLE public.irrigation_period (
	id bigserial NOT NULL,
	amount float8 NULL,
	end_date timestamp NULL,
	irrigate_scheduler_in_days int4 NULL,
	start_date timestamp NULL,
	plot_id int8 NULL,
	CONSTRAINT irrigation_period_pkey PRIMARY KEY (id)
);


-- public.plot definition

-- Drop table

-- DROP TABLE public.plot;

CREATE TABLE public.plot (
	id bigserial NOT NULL,
	sensor_id int8 NULL,
	CONSTRAINT plot_pkey null
);


-- public.sensor definition

-- Drop table

-- DROP TABLE public.sensor;

CREATE TABLE public.sensor (
	id bigserial NOT NULL,
	max_attempt varchar(255) NULL,
	sensor_api varchar(255) NULL,
	sensor_code varchar(255) NULL,
	CONSTRAINT sensor_pkey null
);


-- public.slot definition

-- Drop table

-- DROP TABLE public.slot;

CREATE TABLE public.slot (
	id bigserial NOT NULL,
	amount float8 NULL,
	irrigation_dte date NULL,
	status varchar(255) NULL,
	irrigation_period_id int8 NULL,
	CONSTRAINT slot_pkey PRIMARY KEY (id)
);