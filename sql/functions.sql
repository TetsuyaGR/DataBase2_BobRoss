--- drop function

CREATE FUNCTION dropdb()
returns void AS 
$$

DROP TABLE IF EXISTS 
  katalogos,
  paraggelia,
  receipt,
  trapezi,
  servitoros,
  log_file;
  
$$ LANGUAGE SQL;


--- create function

CREATE FUNCTION creatdb()
returns void as 
$$

CREATE TABLE katalogos(
  kid SERIAL,
  konoma VARCHAR(25),
  price real constraint c_price check (price>0),
  availability INT constraint c_availability check (availability=0 or availability>0),
  category VARCHAR(25) constraint c_category 
  check (category in ('Κυριος','Ορεκτικο','Επιδορπιο','Σαλατα','Αναψυκτικο','Κρασι','Μπυρα')),
  constraint c_kid PRIMARY KEY(kid)
 );


CREATE TABLE servitoros (
  sid serial,
  onoma VARCHAR(50),
  constraint c_sid PRIMARY KEY(sid)
);                                                                
                                                                 
CREATE TABLE receipt (
  rid serial,
  dateTime TIMESTAMP,
  servitorosID INT not null constraint f_key3 references servitoros(sid),
  constraint c_rid primary key(rid)
);

CREATE TABLE paraggelia(
  pid serial,
  katalogosID integer not null constraint f_key1 references katalogos(kid) ,
  amount INT,
  receiptID INT not null constraint f_key2 references receipt(rid),
  constraint c_pid primary key(pid)
);

CREATE TABLE trapezi (
  tid serial,
  receiptID INT not null constraint f_key references receipt(rid),
  available boolean,
  constraint c_tid primary key(tid)
);

$$ LANGUAGE SQL;

--- insert function

CREATE FUNCTION insertdb()
returns void as 
$$

INSERT INTO katalogos(konoma,price,availability,category) VALUES ('τουρτα', 7.5, 20, 'Επιδορπιο');
INSERT INTO katalogos(konoma,price,availability,category)  VALUES ('μουσακας', 6.9000001, 15, 'Κυριος');
INSERT INTO katalogos(konoma,price,availability,category)  VALUES ('σουβλακια', 6.19999981, 35, 'Κυριος');
INSERT INTO katalogos(konoma,price,availability,category)  VALUES ('μπριζολα', 9, 32, 'Κυριος');
INSERT INTO katalogos(konoma,price,availability,category)  VALUES ('πατατες', 2.5, 40, 'Ορεκτικο');
INSERT INTO katalogos(konoma,price,availability,category)  VALUES ('χωριατικη', 2.5, 40, 'Σαλατα');

INSERT INTO servitoros(onoma) VALUES ('Θανος');
INSERT INTO servitoros(onoma) VALUES ('Σοφια');
INSERT INTO servitoros(onoma) VALUES ('Κωστας');                                                                 

INSERT INTO receipt(dateTime,servitorosID) VALUES (NOW(), 1);
INSERT INTO receipt(dateTime,servitorosID) VALUES (NOW(),2);
INSERT INTO receipt(dateTime,servitorosID) VALUES (NOW(),1);
INSERT INTO receipt(dateTime,servitorosID) VALUES (NOW(),2);
INSERT INTO receipt(dateTime,servitorosID) VALUES (NOW(),3);
INSERT INTO receipt(dateTime,servitorosID) VALUES (NOW(),3);


INSERT INTO paraggelia(katalogosID,amount,receiptID) VALUES (2, 1,6);
INSERT INTO paraggelia(katalogosID,amount,receiptID) VALUES (1, 1,5);
INSERT INTO paraggelia(katalogosID,amount,receiptID) VALUES (2, 2,4);
INSERT INTO paraggelia(katalogosID,amount,receiptID) VALUES (1, 3,3);
INSERT INTO paraggelia(katalogosID,amount,receiptID) VALUES (2, 2,2);
INSERT INTO paraggelia(katalogosID,amount,receiptID) VALUES (4, 3,1);
INSERT INTO paraggelia(katalogosID,amount,receiptID) VALUES (3, 2,5);                                        

INSERT INTO trapezi(receiptid,available) VALUES (5, true);
INSERT INTO trapezi(receiptid,available) VALUES (3, false);
INSERT INTO trapezi(receiptid,available) VALUES (1, true);
INSERT INTO trapezi(receiptid,available) VALUES (6, false);
INSERT INTO trapezi(receiptid,available) VALUES (2, true);
INSERT INTO trapezi(receiptid,available) VALUES (4, false);

$$ LANGUAGE SQL;

---log file

create table log_file(
table_name text not null,
operation char(1) NOT NULL,
stamp timestamp NOT NULL,
userid varchar(20) NOT NULL
);

CREATE OR REPLACE FUNCTION process_log_file()
RETURNS TRIGGER AS $$
declare tname text ;
BEGIN
tname := TG_TABLE_NAME || ' ';
IF (TG_OP = 'DELETE') THEN
INSERT INTO log_file SELECT tname,'D', now(), user;
ELSIF (TG_OP = 'UPDATE') THEN
INSERT INTO log_file SELECT tname,'U', now(), user;
ELSIF (TG_OP = 'INSERT') THEN
INSERT INTO log_file SELECT tname,'I', now(), user;
END IF;
RETURN NULL;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER log_file
AFTER INSERT OR UPDATE OR DELETE ON katalogos
FOR EACH ROW EXECUTE PROCEDURE process_log_file();

CREATE TRIGGER log_file
AFTER INSERT OR UPDATE OR DELETE ON servitoros
FOR EACH ROW EXECUTE PROCEDURE process_log_file();

CREATE TRIGGER log_file
AFTER INSERT OR UPDATE OR DELETE ON receipt
FOR EACH ROW EXECUTE PROCEDURE process_log_file();

CREATE TRIGGER log_file
AFTER INSERT OR UPDATE OR DELETE ON paraggelia
FOR EACH ROW EXECUTE PROCEDURE process_log_file();

CREATE TRIGGER log_file
AFTER INSERT OR UPDATE OR DELETE ON trapezi
FOR EACH ROW EXECUTE PROCEDURE process_log_file();

select * from log_file;
