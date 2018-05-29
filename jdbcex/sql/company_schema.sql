-- This script was confirmed loadable into Apache Derby, Postgres, and MySQL

CREATE TABLE employee (
    fname character varying(30),
    minit character(1),
    lname character varying(30),
    ssn integer NOT NULL,
    bdate date,
    address character varying(50),
    sex character(1),
    salary double precision,
    superssn integer,
    dno integer
);

CREATE TABLE department (
    dname character varying(30),
    dnumber integer NOT NULL,
    mgrssn integer,
    mgrstartdate date
);

CREATE TABLE dept_locations (
    dnumber integer NOT NULL,
    dlocation character varying(30) NOT NULL
);

CREATE TABLE project (
    pname character varying(30),
    pnumber integer NOT NULL,
    plocation character varying(30),
    dnum integer
);

CREATE TABLE works_on (
    essn integer NOT NULL,
    pno integer NOT NULL,
    hours real
);

CREATE TABLE dependent (
    essn integer NOT NULL,
    dependent_name character varying(30) NOT NULL,
    sex character(1),
    bdate date,
    relationship character varying(30)
);

ALTER TABLE employee
    ADD CONSTRAINT employee_PK PRIMARY KEY (ssn);

ALTER TABLE department
    ADD CONSTRAINT department_dnumber_PK PRIMARY KEY (dnumber);

ALTER TABLE dependent
    ADD CONSTRAINT dependent_essn_dname_PK PRIMARY KEY (essn, dependent_name);

ALTER TABLE dependent
    ADD CONSTRAINT dependent_essn_FK FOREIGN KEY (essn) REFERENCES employee(ssn) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE dept_locations
    ADD CONSTRAINT dept_location_PK PRIMARY KEY (dnumber, dlocation);

ALTER TABLE dept_locations
    ADD CONSTRAINT dept_locations_FK FOREIGN KEY (dnumber) REFERENCES department(dnumber) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE employee
    ADD CONSTRAINT employee_superssn_FK FOREIGN KEY (superssn) REFERENCES employee(ssn) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE employee
    ADD CONSTRAINT employee_dno_FK FOREIGN KEY (dno) REFERENCES department(dnumber) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE project
    ADD CONSTRAINT project_PK PRIMARY KEY (pnumber);

ALTER TABLE works_on
    ADD CONSTRAINT works_on_PK PRIMARY KEY (essn, pno);

ALTER TABLE works_on
    ADD CONSTRAINT works_on_essn_FK FOREIGN KEY (essn) REFERENCES employee(ssn) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE works_on
    ADD CONSTRAINT works_on_pno FOREIGN KEY (pno) REFERENCES project(pnumber) ON UPDATE RESTRICT ON DELETE RESTRICT;

-- Added by KG, didn't see this FK
ALTER TABLE project
	ADD CONSTRAINT project_dnum_fk FOREIGN KEY(dnum) REFERENCES department(dnumber) ON UPDATE RESTRICT ON DELETE RESTRICT;

