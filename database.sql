CREATE DATABASE "sinuApp"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
CREATE TABLE public.useraccount
(
    userid integer NOT NULL DEFAULT nextval('useraccount_userid_seq'::regclass),
    username character varying(15) COLLATE pg_catalog."default" NOT NULL,
    userpassword character varying(10) COLLATE pg_catalog."default" NOT NULL,
    usertype character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT useraccount_pkey PRIMARY KEY (userid)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.useraccount
    OWNER to postgres;
	
CREATE TABLE public.studentinformation
(
    studentinfoid integer NOT NULL,
    idstudent integer NOT NULL,
    studgroup character varying(8) COLLATE pg_catalog."default",
    scholarshipstate character varying(10) COLLATE pg_catalog."default",
    average double precision,
    CONSTRAINT studentinformation_pkey PRIMARY KEY (studentinfoid),
    CONSTRAINT studentinfo_student_fk FOREIGN KEY (idstudent)
        REFERENCES public.student (studentid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.studentinformation
    OWNER to postgres;
	
CREATE TABLE public.studentactivity
(
    idactivity integer NOT NULL DEFAULT nextval('studentactivity_studactid_seq'::regclass),
    idstudent integer NOT NULL,
    activitydate date,
    activitytype character varying(10) COLLATE pg_catalog."default",
    description character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT studentactivity_pkey PRIMARY KEY (idactivity),
    CONSTRAINT studactivity_student_fk FOREIGN KEY (idstudent)
        REFERENCES public.student (studentid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.studentactivity
    OWNER to postgres;
	
CREATE TABLE public.student
(
    studentid integer NOT NULL,
    userid integer,
    CONSTRAINT student_pkey PRIMARY KEY (studentid),
    CONSTRAINT student_user_fk FOREIGN KEY (userid)
        REFERENCES public.useraccount (userid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.student
    OWNER to postgres;
	
CREATE TABLE public.personalinformation
(
    idpersonal integer NOT NULL DEFAULT nextval('personalinformation_persinfoid_seq'::regclass),
    idstudent integer NOT NULL,
    firstname character varying(15) COLLATE pg_catalog."default",
    lastname character varying(15) COLLATE pg_catalog."default",
    icn character varying(8) COLLATE pg_catalog."default",
    pnc character varying(13) COLLATE pg_catalog."default",
    CONSTRAINT personalinformation_pkey PRIMARY KEY (idpersonal),
    CONSTRAINT personalinfo_student_fk FOREIGN KEY (idstudent)
        REFERENCES public.student (studentid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.personalinformation
    OWNER to postgres;
	
CREATE TABLE public.enrollment
(
    idenrollment integer NOT NULL DEFAULT nextval('enrollment_enrollmentid_seq'::regclass),
    idcourse integer NOT NULL,
    idstudent integer NOT NULL,
    finalgrade double precision,
    CONSTRAINT enrollment_pkey PRIMARY KEY (idenrollment),
    CONSTRAINT enrollment_course_fk FOREIGN KEY (idcourse)
        REFERENCES public.course (courseid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT enrollment_student_fk FOREIGN KEY (idstudent)
        REFERENCES public.student (studentid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.enrollment
    OWNER to postgres;
	
CREATE TABLE public.course
(
    courseid integer NOT NULL DEFAULT nextval('course_courseid_seq'::regclass),
    coursename character varying(20) COLLATE pg_catalog."default",
    coursesession character varying(9) COLLATE pg_catalog."default",
    examdate date,
    enrollmentkey character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT course_pkey PRIMARY KEY (courseid)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.course
    OWNER to postgres;
	
CREATE TABLE public.contactinformation
(
    idcontact integer NOT NULL DEFAULT nextval('contactinformation_coninfoid_seq'::regclass),
    idstudent integer NOT NULL,
    address character varying(30) COLLATE pg_catalog."default",
    phonenumber character varying(11) COLLATE pg_catalog."default",
    emailaddress character varying(30) COLLATE pg_catalog."default",
    CONSTRAINT contactinformation_pkey PRIMARY KEY (idcontact),
    CONSTRAINT contactinfo_student_fk FOREIGN KEY (idstudent)
        REFERENCES public.student (studentid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.contactinformation
    OWNER to postgres;